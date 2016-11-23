package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Configuration;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Notification;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestAlarmpointJavaClientConnection extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestAlarmpointJavaClientConnection.class );

    public TestAlarmpointJavaClientConnection ( String param )
    {
        super ( param );
    }

    public void testConfiguration()
    {
        Configuration conf = Configuration.getInstance();

        assertNotNull ( conf );

        _log.info ( "Alarmpoint Connection URL: " + conf.getHttpClientFormActionURL() );
    }

    public void testNotificationToUser()
    {
        long timestamp = System.currentTimeMillis();

        Notification notification = new Notification ( "Dennis Test Group", "some device", "some situation", Long.toString ( timestamp ), "BGI VPO", "major" );

        assertTrue ( notification.sendAlert() );
    }
}
