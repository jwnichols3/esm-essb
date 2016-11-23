package com.bgi.esm.monitoring.portlets.Openview2EEBBridge.test;

import java.util.Calendar;
import java.util.Vector;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Openview2EEBBridge.Toolkit;

public class TestToolkit extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestToolkit.class );

    public TestToolkit()
    {
    }

    private void logErrorMessages ( Vector <String> error_messages )
    {
        if ( error_messages.size() > 0 )
        {
            _log.error ( "Number of error messages: " + error_messages.size() );
            for ( int counter = 0; counter < error_messages.size(); counter++ )
            {
                _log.error ( error_messages.get ( counter ) );
            }

            fail();
        }
    }

    public void testConstructTimestamp()
    {
        String date = "06/01/2007";
        String time = "13:00:04";

        Calendar calendar = Toolkit.getTimestamp ( date, time );
        Vector <String> error_messages = new Vector <String> ();

        if ( 2007 != calendar.get ( Calendar.YEAR ) )
        {
            error_messages.add ( "Could not parse Calendar.YEAR from date= " + date + ", retrieved " + calendar.get ( Calendar.YEAR ) );
        }

        if (    1 != calendar.get ( Calendar.DATE  ) )
        {
            error_messages.add ( "Could not parse Calendar.DATE from date= " + date + ", retrieved " + calendar.get ( Calendar.DATE ) );
        }

        if (    5 != calendar.get ( Calendar.MONTH ) )
        {
            error_messages.add ( "Could not parse Calendar.MONTH from date= " + date + ", retrieved " + calendar.get ( Calendar.MONTH ) );
        }

        if (   13 != calendar.get ( Calendar.HOUR_OF_DAY ) )
        {
            error_messages.add ( "Could not parse Calendar.HOUR_OF_DAY from time= " + time + ", retrieved " + calendar.get ( Calendar.HOUR_OF_DAY ) );
        }

        if (    0 != calendar.get ( Calendar.MINUTE ) )
        {
            error_messages.add ( "Could not parse Calendar.MINUTE from time= " + time + ", retrieved " + calendar.get ( Calendar.MINUTE ) );
        }

        if (    4 != calendar.get ( Calendar.SECOND ) )
        {
            error_messages.add ( "Could not parse Calendar.SECOND from time= " + time + ", retrieved " + calendar.get ( Calendar.SECOND ) );
        }

        logErrorMessages ( error_messages );
    }
}
