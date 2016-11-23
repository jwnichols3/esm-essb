package com.bgi.esm.portlets.testing.suppression;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;

public class TestVPOSuppressCheck extends SuppressionTestcase
{
    final private static Logger _log             = Logger.getLogger ( TestVPOSuppressCheck.class );
    private static Properties common_properties  = null;
    private static String suppress_check_command = null;
    private static String file_separator         = null;

    private static String TEST_SUPPRESS_APP      = "test_app";
    private static String TEST_SUPPRESS_NODE     = "test_node";
    private static String TEST_SUPPRESS_MESSAGE  = "test_message";
    private static String TEST_SUPPRESS_INSTANCE = "test_instance";

    /**
     * Tests the Perl suppress check utility.  Please make sure that the extract files are property set to match the suppress
     * ones specified in the suppress check utility.
     */
    public TestVPOSuppressCheck( String param )
    {
        super ( param );

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
                is = cl.getResourceAsStream ( "test-suppress-check.properties" );
                common_properties.load ( is );           
            }
            catch ( IOException exception )
            {
                _log.error ( "Could not initialize properties from test-suppress-check.properties",  exception );
            }
        }

        String os = System.getProperties().getProperty      ( "os.name" );
        file_separator = System.getProperties().getProperty ( "file.separator" );

        if ( null == suppress_check_command )
        {
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                suppress_check_command = common_properties.getProperty ( "suppress_check.command.windows" );

                StringBuilder command  = new StringBuilder ( "." );
                command.append ( file_separator );
                command.append ( "WEB-INF" );
                command.append ( file_separator );
                command.append ( suppress_check_command );
                suppress_check_command = command.toString();
            }
            else
            {
                suppress_check_command = common_properties.getProperty ( "suppress_check.command.unix" );
            }
        }
    }

    private String suppressCheckCommand ( String message_group, String node, String text, String instance )
    {
        StringBuilder command_string = new StringBuilder ( suppress_check_command );
        command_string.append ( " " );
        command_string.append ( message_group );
        command_string.append ( " " );
        command_string.append ( node );
        command_string.append ( " '" );
        command_string.append ( text );
        command_string.append ( "' '" ); 
        command_string.append ( instance );
        command_string.append ( "'" );

        return command_string.toString();
    }

    private int suppressCheck ( String message_group, String node, String text, String instance )
    {
        Runtime rt     = Runtime.getRuntime();
        String command = suppressCheckCommand ( message_group, node, text, instance );
        int exit_value = -1;

        try
        {
            Process p  = rt.exec ( command );
            p.waitFor();

            exit_value = p.exitValue();

            p = null;
        }
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();

            exit_value = -2;
        }
        catch ( IOException exception )
        {
            _log.error ( "Bad suppress check command: " + command );

            exception.printStackTrace();

            exit_value = -3;
        }

        //  Clean up
        rt             = null;

        return exit_value;
    }

    public void testSuppressCheckCommand()
    {
        assertNotNull ( suppress_check_command );
    }

    public void testRetrieveExtractFiles() throws SAXException, ParserConfigurationException, TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testAddEntryDefaultTimesCreatesExtractFile()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
        portletSetup();

    	assertTrue ( clickLink ( "portletSettings" ) );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        System.out.println ( "Extract file (suppressions): " + extract_file_suppressions );
        System.out.println ( "Extract file (vpo): " + extract_file_vpo );
        System.out.println ( "Extract file (inst): " + extract_file_inst );
    }

    public void testSuppressCheck_Pass_Time()
    {
        int exit_value = suppressCheck ( TEST_SUPPRESS_APP, TEST_SUPPRESS_NODE, TEST_SUPPRESS_MESSAGE, TEST_SUPPRESS_INSTANCE );
        _log.info ( "Exit value: " + exit_value );
        assertTrue ( 2 == exit_value );
    }
}
