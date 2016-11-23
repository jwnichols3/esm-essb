package com.bgi.esm.monitoring.portlets.Openview2EEBBridge;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class Toolkit
{
    final private static Logger _log = Logger.getLogger ( Toolkit.class );
    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     * @return a HashMap &lt;String, String&gt; of all the HTTP parameters in the HTTP request
     */
    public static HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        HashMap <String, String> param_map = new HashMap <String, String> ();

        if ( request.getParameter ( "_spage" ) != null ) // Liferay parameter handling
        {
            String parameter = request.getParameter ( "_spage" );
            parameter = parameter.substring ( parameter.indexOf ( "?" )+1 );

            // Parse out the tokens
            StringTokenizer tokenizer = new StringTokenizer ( parameter, "&" );
            String key                = null;
            String value              = null;
            int index                 = 0;
            param_map                 = new HashMap <String, String> ();
    
            while ( tokenizer.hasMoreTokens() )
            {
                parameter = tokenizer.nextToken();
                index     = parameter.indexOf ( "=" );
                if ( index >= 0 )
                {
                    key       = parameter.substring ( 0, index );
                    value     = parameter.substring ( index+1 );

                    param_map.put ( key, value );
                }
            }
        }
        else // Struts parameter handling
        {
            Enumeration e = request.getParameterNames();
            String es     = null;
            param_map     = new HashMap <String, String>();
            while ( e.hasMoreElements() )
            {
                es = e.nextElement().toString();
                param_map.put ( es, request.getParameter ( es ) );
            }
        }

        return param_map;
    }

    public static Calendar getTimestamp ( String date, String time )
    {
        Calendar calendar = Calendar.getInstance();

        String tokens_date[] = date.split ( "/" );
        String tokens_time[] = time.split ( ":" );

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Original date: " + date );
            _log.info ( "Original time: " + time );

            if ( tokens_date.length >= 0 ) _log.info ( "Date-0: " + tokens_date[0] );
            if ( tokens_date.length >= 1 ) _log.info ( "Date-1: " + tokens_date[1] );
            if ( tokens_date.length >= 2 ) _log.info ( "Date-2: " + tokens_date[2] );
            if ( tokens_time.length >= 0 ) _log.info ( "Time-0: " + tokens_time[0] );
            if ( tokens_time.length >= 1 ) _log.info ( "Time-1: " + tokens_time[1] );
            if ( tokens_time.length >= 2 ) _log.info ( "Time-2: " + tokens_time[2] );
        }

        String cal_year   = tokens_date[2];
        String cal_month  = tokens_date[0];
        String cal_date   = tokens_date[1];
        String cal_hour   = tokens_time[0];
        String cal_minute = tokens_time[1];
        String cal_second = tokens_time[2];

        calendar.set ( Calendar.YEAR,        Integer.parseInt ( cal_year   ) );
        calendar.set ( Calendar.MONTH,       Integer.parseInt ( cal_month  ) - 1);
        calendar.set ( Calendar.DATE,        Integer.parseInt ( cal_date   ) );
        calendar.set ( Calendar.HOUR_OF_DAY, Integer.parseInt ( cal_hour   ) );
        calendar.set ( Calendar.MINUTE,      Integer.parseInt ( cal_minute ) );
        calendar.set ( Calendar.SECOND,      Integer.parseInt ( cal_second ) );

        return calendar;
    }
}
