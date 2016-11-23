package com.bgi.esm.monitoring.portlets.Suppressions.actions;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.AddEntry;

/**
 * @author  Dennis Lin
 *
 */
public class ProcessVpoSuppressAdd extends Action
{
    final private static BackEndFacade bef = new BackEndFacade();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        _log.debug ( "com.bgi.esm.monitoring.portlets.Suppressions.actions.AddEntryAction::execute()" );
        
        AddEntry data_form = (AddEntry) form;
        
        ActionErrors errors = form.validate ( mapping, request );
       
        if ( _log.isDebugEnabled() )
        {
            data_form.DumpToFile ( "c:\\test\\add_entry_form.out" );
        }
       
        _log.debug ( "Number of errors found: " + errors.size() );
        
        if ( errors.size() > 0 )
        {
            HttpSession session = request.getSession();
            session.setAttribute ( "org.apache.struts.action.ERROR", errors );

            _log.error ( "Failed execution because errors were detected: " + errors.size() );

            return ( mapping.findForward ( "bad-form" ) );
        }

        //  Find out what the timezone offset is

        //Calendar calendar_start = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        //Calendar calendar_end   = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        String timezone_string = null;
        try
        {
            timezone_string = bef.selectEebProperty ( "server.time_zone" ).getPropertyValue();
        }
        catch ( Exception exception )
        {
            _log.error ( "Could not retrieve time zone", exception );
        }

        TimeZone tz = TimeZone.getTimeZone ( timezone_string );
        Calendar calendar_start = Calendar.getInstance(tz);
        Calendar calendar_end   = Calendar.getInstance(tz);
        int daylight_savings_offset = tz.getDSTSavings();
        int timezone_offset         = tz.getRawOffset();

        calendar_start.setTime ( Toolkit.getSupStartDate ( data_form ) );
        calendar_start.add ( Calendar.MILLISECOND, timezone_offset+daylight_savings_offset );

        calendar_end.setTime   ( Toolkit.getSupEndDate   ( data_form ) );
        calendar_end.add ( Calendar.MILLISECOND, timezone_offset+daylight_savings_offset );

        int num_minutes_prior; 
        try
        {
            num_minutes_prior = Integer.parseInt ( data_form.getNumMinutesPrior() );
        }
        catch ( NumberFormatException exception )
        {
            num_minutes_prior = Integer.MIN_VALUE;
        }
        
        String temp_string = data_form.getRemoveOnReboot();
        
        try
        {
            //  Compute the offset
            //int time_offset = Toolkit.computeTimeZoneOffset ( request );
            int initial_time_offset = Integer.parseInt ( data_form.getTimeZoneOffset() );
            int time_offset = initial_time_offset - (timezone_offset + daylight_savings_offset);
        
            if ( request.getHeader ( "user-agent" ).indexOf ( "libwww-perl" ) >= 0 )
            {
                String strTimezone = bef.selectEebProperty( "server.time_zone" ).getPropertyValue();
                TimeZone tz_server = TimeZone.getTimeZone ( strTimezone );
                int tz_daylight_savings_offset = tz_server.getDSTSavings();
                int tz_timezone_offset         = tz_server.getRawOffset();
                int tz_offset                  = tz_timezone_offset + tz_daylight_savings_offset;

                time_offset += tz_offset;

                _log.info ( "Perl agent detected... adding back server timezone offset: " + tz_offset );
            }


            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "AddEntryAction::execute() - timezone offset=" );
                message.get().append ( initial_time_offset );
                message.get().append ( ", Current offset=" );
                message.get().append ( calendar_start.getTimeZone().getRawOffset() );
                message.get().append ( ", Daylight Savings offset=" );
                message.get().append ( daylight_savings_offset );
                message.get().append ( ", final offset is " );
                message.get().append ( time_offset );

                _log.info ( message.get().toString() );
            }

            calendar_start.add ( Calendar.MILLISECOND, -time_offset );
            calendar_end.add   ( Calendar.MILLISECOND, -time_offset );


            //  Add the suppression
            SuppressionRule suppressionRule = new SuppressionRule();
            suppressionRule.setApplicationName  ( data_form.getApplication()  );
            suppressionRule.setNodeName         ( data_form.getNode()         );
            suppressionRule.setStartTime        ( calendar_start );
            suppressionRule.setEndTime          ( calendar_end   );
            suppressionRule.setDatabaseServerName ( data_form.getDbServer()     );
            suppressionRule.setMessage          ( data_form.getDbServerMsg()  );
            suppressionRule.setDescription      ( data_form.getDescription()  );
            suppressionRule.setDeletedFlag      ( false                       );
            suppressionRule.setNotifyMinutes    ( num_minutes_prior           );
            suppressionRule.setNotifyEmail      ( data_form.getEmail()        );
            suppressionRule.setRemoveOnReboot   ( ( null != temp_string ) && ( temp_string.equals ( "on" ) ));

            String username = request.getRemoteUser();
            if ( null == username )
            {
                username = data_form.getUsername();
            }

            suppressionRule.setOwner            ( username                    );
            SuppressionRule addResult = bef.addOrUpdateSuppressionRule ( suppressionRule );

            long new_entry_num = addResult.getSuppressId();
            HttpSession session = request.getSession();
            session.setAttribute ( "NewSuppressionID", new Long ( new_entry_num ) );
        }
        catch (NullPointerException e)
        {
            _log.error ( e );

            return mapping.findForward ( "failure" );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( exception );

            return mapping.findForward ( "failure" );
        }

        String user_agent  = request.getHeader ( "user-agent" );

        if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
        {
            _log.info ( "Perl agent detected" );

            return ( mapping.findForward ( "success-perl" ) );
        }
        else
        {
            return ( mapping.findForward ( "success" ) );
        }
    }
  
    private static final Logger _log = Logger.getLogger(ProcessVpoSuppressAdd.class);
}



