package com.bgi.esm.platform.VpoSuppress.test;

import java.io.InputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.TestHarness;
import junit.framework.TestCase;

/**
 *  NOTE:  These test cases require an active Liferay application server that is running the suppressions portlet
 */
public class CommonTestCase extends TestCase
{
    final private static Logger _log = Logger.getLogger ( CommonTestCase.class );
    final private static long ACCEPTABLE_DELTA_SECONDS = 300; //  a 5 minute delay is acceptable because of the interactions with the web/app server
    protected static String default_application        = null;
    protected static String default_node               = null;
    protected static String default_creator            = null;
    protected static String default_description        = null;
    protected static String default_instance           = null;
    protected static String default_message_text       = null;
    protected static String default_email_address      = null;
    protected static String default_portal             = null;

    public CommonTestCase ( String param )
    {
        super ( param );

        default_application   = TestHarness.getDefaultApplication();
        default_node          = TestHarness.getDefaultNode();
        default_creator       = TestHarness.getDefaultCreator();
        default_description   = TestHarness.getDefaultDescription();
        default_instance      = TestHarness.getDefaultInstance();
        default_message_text  = TestHarness.getDefaultMessageText();
        default_email_address = TestHarness.getDefaultEmailAddress();
        default_portal        = TestHarness.getDefaultPortal();
    }

    public Properties readPropertiesFile ( String path ) throws IOException
    {
        Properties properties = new Properties(); 
        Class c               = CommonTestCase.class;
        ClassLoader cl        = c.getClassLoader();
        InputStream is        = cl.getResourceAsStream ( path );
        properties.load ( is );

        _log.info ( "Loaded properties file: " + path );

        return properties;
    }

    public static Date getDefaultEndDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add ( Calendar.HOUR, 4 );

        return calendar.getTime();
    }

    public static Date createDateDelta ( int years, int months, int days, int hours, int minutes, int seconds, int milliseconds )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add ( Calendar.YEAR,        years        );
        calendar.add ( Calendar.MONTH,       months       );
        calendar.add ( Calendar.DATE,        days         );
        calendar.add ( Calendar.HOUR,        hours        );
        calendar.add ( Calendar.MINUTE,      minutes      );
        calendar.add ( Calendar.SECOND,      seconds      );
        calendar.add ( Calendar.MILLISECOND, milliseconds );

        return calendar.getTime();
    }

    public static boolean compareLocalDateToGMT ( Date local_date, Date gmt_date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( local_date );
        //calendar.set ( Calendar.SECOND, 0 );
        calendar.set ( Calendar.MILLISECOND, 0 );
        
        TimeZone timezone = calendar.getTimeZone();
        long offset       = timezone.getRawOffset();
        long calc_time    = calendar.getTime().getTime() - offset;
        long delta        = ((gmt_date.getTime() - calc_time )/1000);

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Local:       " + local_date.getTime() );
            _log.info ( "Clean:       " + calendar.getTime().getTime() );
            _log.info ( "Raw offset:  " + offset );
            _log.info ( "Calc offset: " + calc_time );
            _log.info ( "GMT:         " + gmt_date.getTime() );
            if ( calc_time != gmt_date.getTime() )
            {
                _log.info ( "Difference:  " + delta );
            }
        }

        return ( delta < ACCEPTABLE_DELTA_SECONDS  );
    }
};
