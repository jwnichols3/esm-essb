package com.bgi.esm.platform.VpoSuppress.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.test.CommonTestCase;

public class TestDefaultValues extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDefaultValues.class );

    public TestDefaultValues ( String param )
    {
        super ( param );
    }

    public void testDefaultValues()
    {
        assertNotNull ( default_application   );
        assertNotNull ( default_node          );
        assertNotNull ( default_creator       );
        assertNotNull ( default_description   );
        assertNotNull ( default_instance      );
        assertNotNull ( default_message_text  );
        assertNotNull ( default_email_address );
    }

    public void testRetrieveHostname() throws UnknownHostException 
    {
	    InetAddress localMachine = InetAddress.getLocalHost();	

        String hostname = localMachine.getHostName();

        assertNotNull ( "Could not determine name of localhost", hostname );
    }
};
