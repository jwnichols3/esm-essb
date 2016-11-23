package com.bgi.esm.monitoring.platform.test.openview;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class AcknowledgeEvent
{
    final private static Logger _log = Logger.getLogger ( AcknowledgeEvent.class );

    public static void main ( String args[] ) throws Exception
    {
        if ( args.length > 0 )
        {
            BackEndFacade bef    = new BackEndFacade();
            SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
            Calendar calendar    = null;

            for ( int counter = 0; counter < args.length; counter++ )
            {
                _log.info ( "Attempting to acknowledge EventID=" + args[counter] );
                calendar = calendar.getInstance();
                bef.acknowledgeOpenviewEvent ( args[counter], "AcknowledgeEvent - acknowledged at " + sdf.format ( calendar.getTime() ) );
            }
        }
        else
        {
            _log.error ( "No Openview MessageIDs were specified" );
        }
    }
};
