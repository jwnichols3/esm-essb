package com.bgi.esm.monitoring.platform.filters.Archway.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class ArchwayThread extends Thread
{
    final private static Logger _log = Logger.getLogger ( ArchwayThread.class );
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    private boolean is_alive = true;

    public ArchwayThread()
    {
        _log.info ( "ArchwayThread has been created at " + sdf.format ( new Date() ) );
    }

    public void run()
    {
        while ( true == is_alive )
        {
            _log.info ( "Running new cycle at " + sdf.format ( new Date() ) );

            try
            {
                sleep ( 1000 ); //  Automatically sleep for 1 second
            }
            catch ( InterruptedException exception )
            {
                _log.info ( "Interrupted exception", exception );
            }
        }
    }

    public void shutdown()
    {
        _log.info ( "ArchwayThread asked to shutdown at " + sdf.format ( new Date() ) );
        is_alive = false;
    }
}
