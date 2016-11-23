package com.bgi.esm.monitoring.portlets.Suppressions.actions;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.AddEntry;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.EditEntry;

/**
 *
 * @author  Dennis Lin
 *
 */
public class ProcessVpoSuppressEdit extends Action
{
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
    final private static BackEndFacade bef = new BackEndFacade();
    /**
     *
     */
    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
        throws BackEndFailure
    {
        _log.info ( "SuppressionAction::edit()" );

        EditEntry data_form = (EditEntry) form;

        if ( _log.getLevel() == Level.DEBUG )
        {
            data_form.DumpToFile ( "c:\\test\\edit_entry.out" );
        }

        data_form.validate ( mapping, request );

        Calendar calendar_start = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        Calendar calendar_end   = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        if ( data_form.getStartChoice().equals ( "now" ) )
        {
            calendar_start = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
            
            calendar_start.setTime ( new Date() );
        }
        else
        {
            calendar_start = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
            calendar_start.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear() ) );
            calendar_start.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth() ) );
            calendar_start.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate() ) );
            calendar_start.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour() ) );
            calendar_start.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
            calendar_start.set ( Calendar.AM_PM,  (0==data_form.getSupStartAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        }
        //*/

        if ( data_form.getEndChoice().equals ( "offset" ) )
        {
            int offset   = Integer.parseInt ( data_form.getEndChoiceNum() );
            int unit     = Integer.parseInt ( data_form.getEndChoiceUnit() ); 
            calendar_end = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
            calendar_end.setTime( calendar_start.getTime() );
            calendar_end.add ( Calendar.SECOND, offset*unit );
        }
        else
        {
            calendar_end = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
            calendar_end.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear() ) );
            calendar_end.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth() ) );
            calendar_end.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate() ) );
            calendar_end.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour() ) );
            calendar_end.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
            calendar_end.set ( Calendar.AM_PM,  (0==data_form.getSupEndAmpm().compareTo ( "PM" ))? Calendar.PM : Calendar.AM );
        } 
       
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
        SuppressionRule suppressionRule = bef.selectSuppressionRuleBySuppressId ( Integer.parseInt ( data_form.getSuppressId() ) );
        suppressionRule.setApplicationName  ( data_form.getApplication()  );
        suppressionRule.setNodeName         ( data_form.getNode()         );
        //suppressionRule.setStartTime        ( calendar_start );
        suppressionRule.setEndTime          ( calendar_end   );
        suppressionRule.setDatabaseServerName ( data_form.getDbServer()     );
        suppressionRule.setMessage          ( data_form.getDbServerMsg()  );
        suppressionRule.setDescription      ( data_form.getDescription()  );
        suppressionRule.setDeletedFlag      ( false                       );
        suppressionRule.setNotifyMinutes    ( num_minutes_prior           );
        suppressionRule.setRemoveOnReboot   ( ( null != temp_string ) && ( temp_string.equals ( "on" ) ));

        SuppressionRule result = bef.addOrUpdateSuppressionRule ( suppressionRule );

        if ( null != result )
        {
            return mapping.findForward ( "success" );
        }
        else
        {
            return mapping.findForward ( "failure" );
        }
    }

    public ActionForward execute2 (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception 
    {
        _log.debug ( "com.bgi.esm.portlets.Suppression.actions.EditEntryAction::execute()" );
        
        EditEntry data_form = (EditEntry) form;

        if ( _log.getLevel() == Level.DEBUG )
        {
            data_form.DumpToFile ( "c:\\test\\edit_entry.out" );
        }

        data_form.validate ( mapping, request );
        
        _log.info ( "finish" );

        String user_agent  = request.getHeader ( "user-agent" );

        if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
        {
            _log.info ( "Perl agent detected - checking for empty values for DB Mesasge, DB Instance, and Node" );
            long suppress_id   = Long.parseLong ( data_form.getSuppressId() );
            String db_server   = data_form.getDbServer();
            String db_message  = data_form.getDbServerMsg();
            String node        = data_form.getNode();
            String description = data_form.getDescription();
            String application = data_form.getApplication();
            AddEntry original  = Toolkit.retrieveEntry ( suppress_id );

            if (( null == node ) || ( node.trim().length() == 0 ))
            {
                _log.info ( "Defaulting to original node: " + original.getNode() );
                data_form.setNode ( original.getNode() );
            }
            if (( null == db_server ) || ( db_server.trim().length() == 0 ))
            {
                _log.info ( "Defaulting to original DB server: " + original.getDbServer() );
                data_form.setDbServer ( original.getDbServer() );
            }
            if (( null == db_message ) || ( db_message.trim().length() == 0 ))
            {
                _log.info ( "Defaulting to original DB server message: " + original.getDbServerMsg() );
                data_form.setDbServerMsg( original.getDbServerMsg() );
            }
            if (( null == description ) || ( description.trim().length() == 0 ))
            {
                _log.info ( "Defaulting to original description: " + original.getDescription() );
                data_form.setDescription ( original.getDescription() );
            }
            if (( null == application ) || ( application.trim().length() == 0 ))
            {
                _log.info ( "Defaulting to original application: " + original.getApplication() );
                data_form.setApplication ( original.getApplication() );
            }


            Toolkit.updateEntry ( request, data_form );

            return ( mapping.findForward ( "success-perl" ) );
        }
        else
        {
            Toolkit.updateEntry ( request, data_form );

            return ( mapping.findForward ( "success" ) );
        }
    }
  
    private static final Logger _log = Logger.getLogger(ProcessVpoSuppressEdit.class);
}

