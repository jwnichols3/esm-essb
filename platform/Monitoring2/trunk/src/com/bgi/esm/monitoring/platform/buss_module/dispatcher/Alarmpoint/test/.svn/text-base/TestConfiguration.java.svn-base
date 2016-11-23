package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.test;

import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Configuration;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestConfiguration extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestConfiguration.class );

    public TestConfiguration ( String param )
    {
        super ( param );
    }

    public void testSingleton()
    {
        Configuration conf       = Configuration.getInstance();
        boolean exception_thrown = false;
        try
        {
            conf.clone();
            exception_thrown = false;
        }
        catch ( CloneNotSupportedException exception )
        {
            exception_thrown = true;
        }

        assertTrue ( "Singleton implemention failed", exception_thrown );
    }

    public void testGetAlarmpointSubmissionURL()
    {
        Configuration conf = Configuration.getInstance();
        String submit_url  = conf.getHttpClientFormActionURL();

        assertNotNull ( "Could not determine Alarmpoint Java Client submission URL", submit_url );
    }
}
