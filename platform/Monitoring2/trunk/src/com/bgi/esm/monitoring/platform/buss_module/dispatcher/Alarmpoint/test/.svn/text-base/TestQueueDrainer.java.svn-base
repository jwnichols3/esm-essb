package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.test;

import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.QueueDrainer;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestQueueDrainer extends TestCase
{
    private final static Logger _log = Logger.getLogger ( TestQueueDrainer.class );

    public TestQueueDrainer ( String param )
    {
        super ( param );
    }

    public void testSingleton()
    {
        QueueDrainer qd         = QueueDrainer.getInstance();
        boolean exception_found = false;

        try
        {
            qd.clone();
            exception_found = false;
        }
        catch ( CloneNotSupportedException exception )
        {
            exception_found = true;
        }

        assertTrue ( "This class is a Singleton", exception_found );
    };

    public void testRetrieveValidAlarmpointResponse()
    {
    }

    public void testRetrieveInvalidAlarmpointResponse()
    {
    }
};
