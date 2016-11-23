package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;
import com.bgi.esm.portlets.Suppression.forms.EditEntry;

/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class InitEditEntryAction extends Action
{
    public InitEditEntryAction()
    {
        super();

        _log.debug ("InitEditEntryAction -- created" );
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception 
    {
        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );

        int suppress_id     = Integer.parseInt ( param_map.get ( "suppress_id" ) );
        AddEntry add_form   = Toolkit.retrieveEntry ( suppress_id );
        EditEntry data_form = (EditEntry) form;

        data_form.copyFromAddForm ( add_form );

        String data = null;

        data = data_form.getNode();
        
        if (( null != data ) && ( data.length() > 0 ))
        {
            data_form.setByNode ( "on" );
        }

        data = data_form.getApplication();
        if (( null != data ) && ( data.length() > 0 ))
        {
            data_form.setByApplication ( "on" );
        }
        
                data = data_form.getWithDbServerInstance();
        if (( null != data ) && ( data.length() > 0 ))
        {
            data_form.setWithDbServer ( "on" );
            data_form.setWithDbServerInstance ( "on" );
        }
        
        data = data_form.getWithDbServerMsg();
        if (( null != data ) && ( data.length() > 0 ))
        {
            data_form.setWithDbServer ( "on" );
            data_form.setWithDbServerMsg ( "on" );
        }
        
        data_form.setStartChoice ( "later" );
        data_form.setEndChoice   ( "specified" );
        
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        
        start_time.setTimeInMillis ( data_form.getStartTimestamp().getTime() );
        end_time.setTimeInMillis   ( data_form.getEndTimestamp().getTime()   );
        
        TimeZone time_zone  = TimeZone.getDefault();
        int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
        time_zone.setRawOffset ( timezone_offset );
        start_time.setTimeZone ( time_zone );
        end_time.setTimeZone ( time_zone );
        
        int hour_of_day = start_time.get ( Calendar.HOUR_OF_DAY );
        data_form.setSupStartYear   ( Integer.toString ( start_time.get ( Calendar.YEAR   ) ) );
        data_form.setSupStartMonth  ( Integer.toString ( start_time.get ( Calendar.MONTH  ) ) );
        data_form.setSupStartDate   ( Integer.toString ( start_time.get ( Calendar.DATE   ) ) );
        data_form.setSupStartMinute ( Integer.toString ( start_time.get ( Calendar.MINUTE ) ) );
        if ( 0 == hour_of_day )
        {
            data_form.setSupStartHour   ( "12" );
            data_form.setSupStartAmpm   ( "AM" );
        }
        else if ( hour_of_day < 12 )
        {
            data_form.setSupStartHour ( Integer.toString ( hour_of_day ) );
            data_form.setSupStartAmpm   ( "AM" );
        }
        else
        {
            data_form.setSupStartHour ( Integer.toString ( hour_of_day-12 ) );
            data_form.setSupStartAmpm   ( "PM" );
        }
        
        hour_of_day = end_time.get ( Calendar.HOUR_OF_DAY );
        data_form.setSupEndYear   ( Integer.toString ( end_time.get ( Calendar.YEAR   ) ) );
        data_form.setSupEndMonth  ( Integer.toString ( end_time.get ( Calendar.MONTH  ) ) );
        data_form.setSupEndDate   ( Integer.toString ( end_time.get ( Calendar.DATE   ) ) );
        data_form.setSupEndMinute ( Integer.toString ( end_time.get ( Calendar.MINUTE ) ) );
        if ( 0 == hour_of_day )
        {
            data_form.setSupEndHour   ( "12" );
            data_form.setSupEndAmpm   ( "AM" );
        }
        else if ( hour_of_day < 12 )
        {
            data_form.setSupEndHour ( Integer.toString ( hour_of_day ) );
            data_form.setSupEndAmpm   ( "AM" );
        }
        else
        {
            data_form.setSupEndHour ( Integer.toString ( hour_of_day-12 ) );
            data_form.setSupEndAmpm   ( "PM" );
        }

        data_form.setSuppressId ( Integer.toString ( suppress_id ) );
        
        _log.debug ( "successful" );
                     
        return(mapping.findForward("success"));
    }

    private static final Logger _log = Logger.getLogger ( InitEditEntryAction.class );
}
