package com.bgi.esm.monitoring.platform.test.storm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Storm;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestStorm extends TestCase
{
    final private static Logger _log         = Logger.getLogger ( TestStorm.class );
    final public static SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );
    private static BackEndFacade bef         = null;

    public TestStorm ( String param )
    {
        super ( param );

        bef = new BackEndFacade();
    }

    public void testSearchActiveStorms() throws BackEndFailure
    {
        long timestamp        = System.currentTimeMillis();
        String row_id         = "row-" + timestamp;
        String message_id     = "message-" + timestamp;

        List active_storms = bef.getAllActiveStorms();

        assertNotNull ( "Could not retrieve list of active storms", active_storms );

        for ( int counter = 0; counter < active_storms.size(); counter++ )
        {
            Storm storm = (Storm) active_storms.get ( counter );
            _log.info ( "Active storm: " + storm.getGroup() );
        }
    }
}
