package com.bgi.esm.monitoring.platform.utility.test;

import java.util.Properties;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.utility.test.CommonTestCase;

public class TestStaticConfiguration extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestStaticConfiguration.class );

    public TestStaticConfiguration ( String param )
    {
        super ( param );
    }

    public void testChangeSystemProperties()
    {
        Properties sysprops = (Properties) System.getProperties().clone();

        System.getProperties().setProperty ( "user.name", "test_user" );

        assertEquals ( "test_user", System.getProperties().getProperty ( "user.name" ) );
    }
}
