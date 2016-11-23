package com.bgi.esm.portlets.testing.suppression;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
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

public class TestVPOSuppressPerl extends SuppressionTestcase
{
    final private static Logger _log            = Logger.getLogger ( TestVPOSuppressPerl.class );
    final private static String TEST_PORTAL     = "caldte1pc31359";

    private static String file_separator        = null;
    private static String vpo_suppress_cmd      = null;
    private static Properties common_properties = null;
    private static DecimalFormat digits_two     = null;
    private static DecimalFormat digits_four    = null;

    private void LogToFile ( String message )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( "test.log", true );
                outfile.write ( (new Date()).toString().getBytes() );
                outfile.write ( " - ".getBytes() );
                outfile.write ( message.getBytes() );
                outfile.write ( "\n".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
    }

    public TestVPOSuppressPerl ( String param )
    {
        super ( param );

        digits_two  = new DecimalFormat ( "00" );
        digits_four = new DecimalFormat ( "0000" );

        //  Retrieve the OS that we are running on
        String os = System.getProperties().getProperty      ( "os.name" );
        file_separator = System.getProperties().getProperty ( "file.separator" );

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
                is = cl.getResourceAsStream ( "test-suppressions.properties" );
                common_properties.load ( is );           
            }
            catch ( IOException exception )
            {
                exception.printStackTrace();
            }
        }

        if ( null == vpo_suppress_cmd )
        {
            if ( os.indexOf ( "Windows" ) >= 0 )
            {
                vpo_suppress_cmd = common_properties.getProperty ( "command.vpo_suppress.windows" );
            }
            else
            {
                vpo_suppress_cmd = common_properties.getProperty ( "command.vpo_suppress.unix" );
            }
        }
    }

    /**
     *  Creates the command-line entry for creating a suppression with the "vpo_suppress" command
     */
    private String createAddSuppressionCommand ( String description, String node, String instance, String message, Date start_time, Date end_time )
    {
        assertNotNull ( vpo_suppress_cmd );

        StringBuilder arguments = new StringBuilder ( "." );
        arguments.append ( file_separator );
        arguments.append ( "WEB-INF" );
        arguments.append ( file_separator );
        arguments.append ( "vpo_suppress -A -p " );
        arguments.append ( TEST_PORTAL );

        if ( null != description )
        {
            arguments.append ( " -d \"" );
            arguments.append ( description );
            arguments.append ( "\"" );
        }

        if ( null != node )
        {
            arguments.append ( " -n \"" );
            arguments.append ( node );
            arguments.append ( "\"" );
        }

        if ( null != instance )
        {
            arguments.append ( " -i \"" );
            arguments.append ( instance );
            arguments.append ( "\"" );
        }

        if ( null != message )
        {
            arguments.append ( " -t \"" );
            arguments.append ( message );
            arguments.append ( "\"" );
        }

        if ( null != start_time )
        {
            arguments.append ( " -s \"" );
            arguments.append ( createTimeString ( start_time ) );;
            arguments.append ( "\"" );
        }

        if ( null != end_time )
        {
            arguments.append ( " -e \"" );
            arguments.append ( createTimeString ( end_time ) );
            arguments.append ( "\"" );
        }

        arguments.append ( " -c " );
        arguments.append ( TEST_USERNAME );

        return arguments.toString();
    }

    /**
     *  Creates the command-line entry for deleting a suppression with the "vpo_suppress" command
     */
    private String createDeleteSuppressionCommand ( int suppress_id )
    {
        assertNotNull ( vpo_suppress_cmd );

        StringBuilder arguments = new StringBuilder ( "." );
        arguments.append ( file_separator );
        arguments.append ( "WEB-INF" );
        arguments.append ( file_separator );
        arguments.append ( "vpo_suppress -D " );
        arguments.append ( suppress_id );
        arguments.append ( " -p " );
        arguments.append ( TEST_PORTAL );
        arguments.append ( " -c " );
        arguments.append ( TEST_USERNAME );

        return arguments.toString();
    }

    /**
     *  Creates the command-line entry for updating a suppression with the "vpo_suppress" command
     */
    private String createEditSuppressionCommand ( int suppress_id, String description, String node, String instance, Date start_time, Date end_time )
    {
        assertNotNull ( vpo_suppress_cmd );

        StringBuilder arguments = new StringBuilder ( "." );
        arguments.append ( file_separator );
        arguments.append ( "WEB-INF" );
        arguments.append ( file_separator );
        arguments.append ( "vpo_suppress -U " );
        arguments.append ( suppress_id );
        arguments.append ( " -p " );
        arguments.append ( TEST_PORTAL );

        if ( null != description )
        {
            arguments.append ( " -d \"" );
            arguments.append ( description );
            arguments.append ( "\"" );
        }

        if ( null != node )
        {
            arguments.append ( " -n \"" );
            arguments.append ( node );
            arguments.append ( "\"" );
        }

        if ( null != instance )
        {
            arguments.append ( " -i \"" );
            arguments.append ( instance );
            arguments.append ( "\"" );
        }

        if ( null != start_time )
        {
            arguments.append ( " -s \"" );
            arguments.append ( createTimeString ( start_time ) );;
            arguments.append ( "\"" );
        }

        if ( null != end_time )
        {
            arguments.append ( " -e \"" );
            arguments.append ( createTimeString ( end_time ) );
            arguments.append ( "\"" );
        }

        arguments.append ( " -c " );
        arguments.append ( TEST_USERNAME );

        return arguments.toString();
    }

    /**
     *  Formats a java.util.Date object into "YYYYMMDD HH:MM:SS" format for the suppression tool
     *
     *  @param date the date to format
     *  @return the formatted string
     */
    private String createTimeString ( Date date )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime ( date );

        StringBuilder time_string = new StringBuilder();
        time_string.append ( calendar.get ( Calendar.YEAR  ) );
        time_string.append ( digits_two.format ( calendar.get ( Calendar.MONTH ) + 1 ) );
        time_string.append ( digits_two.format ( calendar.get ( Calendar.DATE  ) ) );
        time_string.append ( " " );
        time_string.append ( digits_two.format ( calendar.get ( Calendar.HOUR_OF_DAY ) ) );
        time_string.append ( ":" );
        time_string.append ( digits_two.format ( calendar.get ( Calendar.MINUTE ) ) );
        time_string.append ( ":" );
        time_string.append ( digits_two.format ( calendar.get ( Calendar.SECOND ) ) );

        return time_string.toString();
    }

    /**
     *  Runs the command to create a suppression
     *
     *  @param description the description of the suppression
     *  @param node the node the suppression applies to (a null value will default to the default test node)
     *  @param instance the database instance that the suppression  
     *  @param start_time the start time of the suppression (a null value defaults to the current time)
     *  @param end_time the end time of the suppression (a null value defaults to 4 hours later than the specified current time)
     */
    private void createSuppression ( String description, String node, String instance, String message, Date start_time, Date end_time )
    {
        Runtime rt     = Runtime.getRuntime();
        String command = createAddSuppressionCommand ( description, node, instance, message, start_time, end_time );

        try
        {
            Process p  = rt.exec ( command );
            p.waitFor();

            p = null;
        }
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }

        //  Clean up
        rt        = null;
        command   = null;
    }

    /**
     *  Executes a command in a shell and returns the output as a java.lang.String
     *
     *  @param command the command to execute
     *  @return the resulting output, null if the command failed
     */
    private String runCommand ( String command ) throws IOException, InterruptedException
    {
        StringBuilder output = null;

        Runtime rt         = Runtime.getRuntime();
        Process process    = rt.exec ( command );
        process.waitFor();

        System.out.println ( "Exit code: " + process.exitValue() );

        InputStream  is    = process.getInputStream();
        byte read_bytes[]  = new byte[1024];
        int num_bytes_read = is.read ( read_bytes ); 
        output             = new StringBuilder();
        while ( num_bytes_read >= 0 )
        {
            output.append ( new String ( read_bytes ) );

            num_bytes_read = is.read ( read_bytes ); 
        }

        return (null != output)? output.toString() : null;
    }

    /**
     *  Tests for the existence of the "vpo_suppress" command
     */
    public void testValidVPOSuppressPerlCommand()
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );
    }

    /**
     *  Tests the argument validation for the "vpo_suppress command
     */
    public void testCreateDefaultSuppressionCommand()
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );
        String expected = "." + file_separator + "WEB-INF" + file_separator + "vpo_suppress -A -p " + TEST_PORTAL + " -d \"test description\" -n \"test_node\" -i \"test_instance\" -t \"test_message\" -c " + TEST_USERNAME;

        assertNotNull ( command );

        _log.info ( "Command:  *" + command  + "*" );
        _log.info ( "Expected: *" + expected + "*" );

        if ( !command.equals ( expected ) )
        {
            int index = 0;
            for ( int counter = 0; counter < command.length(); counter++ )
            {
                if ( command.charAt(counter) != expected.charAt(counter) )
                {
                    index = counter;
                    break;
                }
            }

            _log.info ( "Mismatch found after index " + index + ": " + command.substring ( 0, index ) );
            _log.info ( "    Command:  " + command.charAt  ( index ) );
            _log.info ( "    Expected: " + expected.charAt ( index ) );
        }

        assertTrue ( command.equals ( expected ) );
    }

    /**
     *  Tests creating a default suppression
     */
    public void testCreateDefaultSuppression() throws IOException, InterruptedException
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );

        assertNotNull ( command );

        String output   = runCommand ( command );

        assertNotNull ( output );
        assertTrue    ( "Could not connect to portal: " + TEST_PORTAL, output.indexOf ( "500" ) != 0 );

        int suppress_id = Integer.parseInt ( output.trim() );

        _log.info ( "Created suppression ID: " + suppress_id );
    }

    /**
     *  Tests creating a default suppression and then editing it
     */
    public void testCreateDefaultSuppression_Delete () throws IOException, InterruptedException
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );

        assertNotNull ( command );

        String output   = runCommand ( command );

        assertNotNull ( output );
        assertTrue    ( "Could not connect to portal: " + TEST_PORTAL, output.indexOf ( "500" ) != 0 );

        int suppress_id = Integer.parseInt ( output.trim() );

        _log.info ( "Created suppression ID: " + suppress_id );

        command         = createDeleteSuppressionCommand ( suppress_id );

        LogToFile ( "Delete command: " + command );

        output          = runCommand ( command );

        assertTrue ( "Could not delete suppression.  Message: " + output, output.indexOf ( "Successfully edited" ) < 0 );
    }

    /**
     *  Tests creating a default suppression and then editing it
     */
    public void testCreateDefaultSuppression_EditDescription() throws IOException, InterruptedException
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );

        assertNotNull ( command );

        String output   = runCommand ( command );

        assertNotNull ( output );
        assertTrue    ( "Could not connect to portal: " + TEST_PORTAL, output.indexOf ( "500" ) != 0 );

        int suppress_id = Integer.parseInt ( output.trim() );

        _log.info ( "Created suppression ID: " + suppress_id );

        command         = createEditSuppressionCommand ( suppress_id, "test description - edited", "test_node", "test_instance", null, null );

        output          = runCommand ( command );

        assertTrue ( "Could not edit suppression.  Message: " + output, output.indexOf ( "Successfully edited" ) < 0 );
    }

    /**
     *  Tests creating a default suppression and then editing it
     */
    public void testCreateDefaultSuppression_EditNode() throws IOException, InterruptedException
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );

        assertNotNull ( command );

        String output   = runCommand ( command );

        assertNotNull ( output );
        assertTrue    ( "Could not connect to portal: " + TEST_PORTAL, output.indexOf ( "500" ) != 0 );

        int suppress_id = Integer.parseInt ( output.trim() );

        _log.info ( "Created suppression ID: " + suppress_id );

        command         = createEditSuppressionCommand ( suppress_id, "test description", "test_node_edited", "test_instance", null, null );

        output          = runCommand ( command );

        assertTrue ( "Could not edit suppression.  Message: " + output, output.indexOf ( "Successfully edited" ) < 0 );
    }

    /**
     *  Tests creating a default suppression and then editing it
     */
    public void testCreateDefaultSuppression_EditInstance() throws IOException, InterruptedException
    {
        assertNotNull ( vpo_suppress_cmd, "Could not find the predefined property 'vpo_suppress' pre-compiled Perl binary" );

        String command  = createAddSuppressionCommand ( "test description", "test_node", "test_instance", "test_message", null, null );

        assertNotNull ( command );

        String output   = runCommand ( command );

        assertNotNull ( output );
        assertTrue    ( "Could not connect to portal: " + TEST_PORTAL, output.indexOf ( "500" ) != 0 );

        int suppress_id = Integer.parseInt ( output.trim() );

        _log.info ( "Created suppression ID: " + suppress_id );

        command         = createEditSuppressionCommand ( suppress_id, "test description", "test_node", "test_instance-edited", null, null );

        output          = runCommand ( command );

        assertTrue ( "Could not edit suppression.  Message: " + output, output.indexOf ( "Successfully edited" ) < 0 );
    }
}
