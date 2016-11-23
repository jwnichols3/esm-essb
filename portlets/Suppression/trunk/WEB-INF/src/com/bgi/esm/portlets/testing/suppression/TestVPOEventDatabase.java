package com.bgi.esm.portlets.testing.suppression;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;

public class TestVPOEventDatabase extends SuppressionTestcase
{
    final private static Logger _log            = Logger.getLogger ( TestVPOEventDatabase.class );
    private static String vpo_send_cmd          = null;
    private static Properties common_properties = null;

    public TestVPOEventDatabase ( String param )
    {
        super ( param );

        //  Retrieve the OS that we are running on
        String os = System.getProperties().getProperty ( "os.name" );

        //  Retrieve the test properties
        if ( null == common_properties )
        {
            _log.info ( "Reading from properties file" );

            Class c        = null;
            ClassLoader cl = null;
            InputStream is = null;

            try
            {
                common_properties = new Properties(); 
                c  = this.getClass();
                cl = c.getClassLoader();
                is = cl.getResourceAsStream ( "test-vpo.properties" );
                common_properties.load ( is );           
            }
            catch ( IOException exception )
            {
                exception.printStackTrace();
            }
        }

        if ( null == vpo_send_cmd )
        {
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                vpo_send_cmd = common_properties.getProperty ( "command.vpo_send.windows" );
            }
            else
            {
                vpo_send_cmd = common_properties.getProperty ( "command.vpo_send.unix" );
            }
        }
    }
}
