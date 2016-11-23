package com.bgi.esm.platform.VpoSuppress;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

public class TestHarness
{
    final private static Logger _log                 = Logger.getLogger ( TestHarness.class );
    final private static String COMMAND_ADD          = "-A";
    final private static String COMMAND_UPDATE       = "-U";
    final private static String COMMAND_DELETE       = "-D";
    final private static DecimalFormat df2           = new DecimalFormat ( "00" );
    final private static DecimalFormat df4           = new DecimalFormat ( "0000" );

    private static Connection con                    = null;
    private static Properties properties             = null;
    private static String COMMAND_PERL_COMPILE       = null;
    private static String COMMAND_VPO_SUPPRESS       = null;
    private static String database_type              = null;
    private static String database_hostname          = null;
    private static String database_name              = null;
    private static String database_port              = null;
    private static String database_url               = null;
    private static String database_username          = null;
    private static String database_password          = null;

    private static String default_application        = null;
    private static String default_node               = null;
    private static String default_creator            = null;
    private static String default_description        = null;
    private static String default_instance           = null;
    private static String default_message_text       = null;
    private static String default_email_address      = null;
    private static String default_portal             = null;

    private static String hostname                   = null;

    private static void checkProperties()
    {
        if ( null == properties )
        {

            try
            {
	            java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();	

                hostname = localMachine.getHostName();
            }
            catch ( java.net.UnknownHostException uhe )
            {
                _log.error ( "Error encountered when trying to determine hostname" );
            }

            _log.info ( "Reading properties files..." );
        
            try
            {   
                _log.info ( "Read from default properties file" );

                Class c               = TestHarness.class;
                ClassLoader cl        = c.getClassLoader();
                InputStream is        = cl.getResourceAsStream ( "testing.properties" );
                properties            = new Properties(); 
                properties.load ( is );

                database_type         = properties.getProperty ( "suppression.database.type",        null );
                database_name         = properties.getProperty ( "suppression.database.name",        null );
                database_port         = properties.getProperty ( "suppression.database.port",        null );
                database_hostname     = properties.getProperty ( "suppression.database.hostname",    null );
                database_username     = properties.getProperty ( "suppression.database.username",    null );
                database_password     = properties.getProperty ( "suppression.database.password",    null );

                COMMAND_PERL_COMPILE  = properties.getProperty ( "suppression.command.perl_compile", null );
                COMMAND_VPO_SUPPRESS  = properties.getProperty ( "suppression.command.suppress",     null );

                default_application   = properties.getProperty ( "suppression.command.default.application",   null );
                default_node          = properties.getProperty ( "suppression.command.default.node",          null );
                default_creator       = properties.getProperty ( "suppression.command.default.creator",       null );
                default_description   = properties.getProperty ( "suppression.command.default.description",   null );
                default_instance      = properties.getProperty ( "suppression.command.default.instance",      null );
                default_message_text  = properties.getProperty ( "suppression.command.default.message_text",  null );
                default_email_address = properties.getProperty ( "suppression.command.default.email_address", null );
                default_portal        = properties.getProperty ( "suppression.command.default.portal",        null );
            }
            catch ( IOException exception )
            {
                _log.error ( "Error reading default properties file", exception );
            }

            try 
            {
                _log.info ( "Read from user/machine-specific properties file" );

                StringBuilder prop_file = new StringBuilder ( "props/testing." );
                prop_file.append ( hostname );
                prop_file.append ( "." );
                prop_file.append ( System.getProperties().getProperty ( "user.name" ) );
                prop_file.append ( ".properties" );

                _log.info ( "Loading properties file: " + prop_file.toString() );


                Class c               = TestHarness.class;
                ClassLoader cl        = c.getClassLoader();
                InputStream is        = cl.getResourceAsStream ( prop_file.toString() );
                Properties temp_props = new Properties();
                if ( null != is )
                {
                    _log.info ( "Loading properties file: " + prop_file.toString() );
                    temp_props.load ( is );

                    _log.info ( "User/Machine-specific Properties File Portal: " + temp_props.getProperty ( "suppression.command.default.portal" ) );
                }

                database_type         = temp_props.getProperty ( "suppression.database.type",                 database_type         );
                database_name         = temp_props.getProperty ( "suppression.database.name",                 database_name         );
                database_port         = temp_props.getProperty ( "suppression.database.port",                 database_port         );
                database_hostname     = temp_props.getProperty ( "suppression.database.hostname",             database_hostname     );
                database_username     = temp_props.getProperty ( "suppression.database.username",             database_username     );
                database_password     = temp_props.getProperty ( "suppression.database.password",             database_password     );

                COMMAND_PERL_COMPILE  = temp_props.getProperty ( "suppression.command.perl_compile",          COMMAND_PERL_COMPILE  );
                COMMAND_VPO_SUPPRESS  = temp_props.getProperty ( "suppression.command.suppress",              COMMAND_VPO_SUPPRESS  );

                default_application   = temp_props.getProperty ( "suppression.command.default.application",   default_application   );
                default_node          = temp_props.getProperty ( "suppression.command.default.node",          default_node          );
                default_creator       = temp_props.getProperty ( "suppression.command.default.creator",       default_creator       );
                default_description   = temp_props.getProperty ( "suppression.command.default.description",   default_description   );
                default_instance      = temp_props.getProperty ( "suppression.command.default.instance",      default_instance      );
                default_message_text  = temp_props.getProperty ( "suppression.command.default.message_text",  default_message_text  );
                default_email_address = temp_props.getProperty ( "suppression.command.default.email_address", default_email_address );
                default_portal        = temp_props.getProperty ( "suppression.command.default.portal",        default_portal        );

                _log.info ( "Processed user/machine-specific properties" );
            }
            catch ( IOException exception )
            {
                _log.error ( "Error reading machine/user-specific properties file", exception );
            }

            _log.info ( "Default portal: " + default_portal );
        }
    }


    public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException, IOException
    {
        checkProperties();

        if (( null != con ) && ( con.isClosed() ))
        {
            con = null;
        }

        if ( null == con )
        {
            database_url = createConnectionURL ( database_type, database_hostname, database_name, database_port );
            con = DriverManager.getConnection ( database_url, database_username, database_password );
        }

        return con;
    }

    private static void appendArgs ( StringBuilder arg_list, String flag, String value )
    {
        if ( null != value )
        {
            arg_list.append ( flag );
            arg_list.append ( " \"" );
            arg_list.append ( value );
            arg_list.append ( "\" " );
        }
    }

    private static void appendArgs ( StringBuilder arg_list, String flag, Date value )
    {
        if ( null != value )
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime ( value );
            int year   = calendar.get ( Calendar.YEAR        );
            int month  = calendar.get ( Calendar.MONTH       ) + 1;  // 0 to 11
            int date   = calendar.get ( Calendar.DATE        );  // 1 to 31
            int hour   = calendar.get ( Calendar.HOUR_OF_DAY );  // 0 to 23
            int minute = calendar.get ( Calendar.MINUTE      );  // 0 to 59
            int second = calendar.get ( Calendar.SECOND      );  // 0 to 59

            arg_list.append ( flag );
            arg_list.append ( " \"" );
            arg_list.append ( year );
            arg_list.append ( df2.format ( month  ) );
            arg_list.append ( df2.format ( date   ) );
            arg_list.append ( " " );
            arg_list.append ( df2.format ( hour   ) );
            arg_list.append ( ":" );
            arg_list.append ( df2.format ( minute ) );
            arg_list.append ( ":" );
            arg_list.append ( df2.format ( second ) );
            arg_list.append ( "\" " );
        }
    }

    /**
     *
     * @return the suppression ID of the newly created suppression
     */
    public static String createExecuteCommandAdd ( String application, String portal, String node, String instance, String text, String description, Date start_date, Date end_date, String caller, String email_address,
           int num_minutes_before_expire ) throws IOException
    {
        checkProperties();

        StringBuilder command_string = new StringBuilder();
        command_string.append ( COMMAND_VPO_SUPPRESS );
        command_string.append ( " " );
        command_string.append ( COMMAND_ADD );
        command_string.append ( " " );

        appendArgs ( command_string, "-g", application    );
        appendArgs ( command_string, "-n", node           );
        appendArgs ( command_string, "-i", instance       );
        appendArgs ( command_string, "-d", description    );
        appendArgs ( command_string, "-t", text           );
        appendArgs ( command_string, "-s", start_date     );
        appendArgs ( command_string, "-e", end_date       );
        appendArgs ( command_string, "-a", email_address  );
        appendArgs ( command_string, "-c", caller         );
        appendArgs ( command_string, "-p", portal         );

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Executing ADD command: " );
            message.get().append ( command_string.toString() );

            _log.info ( message.get().toString() );
        }

        return command_string.toString();
    }

    public static String createExecuteCommandUpdate ( String suppression_id, String portal, String application, String node, String instance, String description, Date start_date, Date end_date, String caller ) throws IOException
    {
        checkProperties();

        StringBuilder command_string = new StringBuilder();
        command_string.append ( COMMAND_VPO_SUPPRESS );
        command_string.append ( " " );
        command_string.append ( COMMAND_UPDATE );
        command_string.append ( " " );
        command_string.append ( suppression_id );
        command_string.append ( " " );

        appendArgs ( command_string, "-g", application    );
        appendArgs ( command_string, "-n", node           );
        appendArgs ( command_string, "-i", instance       );
        appendArgs ( command_string, "-d", description    );
        appendArgs ( command_string, "-s", start_date     );
        appendArgs ( command_string, "-e", end_date       );
        appendArgs ( command_string, "-c", caller         );
        appendArgs ( command_string, "-p", portal         );

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Executing UPDATE command: " );
            message.get().append ( command_string.toString() );

            _log.info ( message.get().toString() );
        }

        return command_string.toString();
    }

    public static String createExecuteCommandDelete ( String suppression_id, String portal, String caller ) throws IOException
    {
        checkProperties();

        StringBuilder command_string = new StringBuilder();
        command_string.append ( COMMAND_VPO_SUPPRESS );
        command_string.append ( " " );
        command_string.append ( COMMAND_DELETE );
        command_string.append ( " " );
        command_string.append ( suppression_id );
        command_string.append ( " " );

        appendArgs ( command_string, "-p", portal );
        appendArgs ( command_string, "-c", caller );

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Executing DELETE command: " );
            message.get().append ( command_string.toString() );

            _log.info ( message.get().toString() );
        }

        return command_string.toString();
    }

    /**
     * Creates the con URl to a JDBC database
     * 
     * @param database_type The type of database (Sybase, SQL Server Oracle, MySQL, PostgreSQL, ODBC)
     * @param server the server the database resides on
     * @param name the name of the database
     * @param port the port that the database is listening on
     * @return the con URl to a JDBC database
     */
    private static String createConnectionURL ( String database_type, String server, String name, String port ) throws ClassNotFoundException
    {
        StringBuilder connection_url = new StringBuilder();
       
        if ( null == database_type )
        {
            return "";
        } 

        //"sun.jdbc.odbc.JdbcOdbcDriver", 

        if ( database_type.equals ( "Sybase" ) )
        {
            Class.forName ( "com.sybase.jdbc2.jdbc.SybDriver" );

            connection_url.append ( "jdbc:sybase:Tds:" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "SQL Server" ) )
        {
            Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );

            connection_url.append ( "jdbc:jtds:sqlserver://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "MySQL" ) )
        {
            Class.forName ( "com.mysql.jdbc.Driver" );

            connection_url.append ( "jdbc:mysql://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "Oracle" ))
        {
            Class.forName ( "oracle.jdbc.driver.OracleDriver" );

            connection_url.append ( "jdbc:oracle:thin:@" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( ":" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "PostgreSQL" ) )
        {
            Class.forName ( "org.postgresql.Driver" );

            connection_url.append ( "jdbc:postgresql://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( ":" );
            connection_url.append ( name );
        }
        
        _log.info ( "Created con URL: " + connection_url.toString() );
        return connection_url.toString();
    }

    public static String getDefaultApplication()
    {
        checkProperties();

        return default_application;
    }

    public static String getDefaultNode()
    {
        checkProperties();

        return default_node;
    }

    public static String getDefaultCreator()
    {
        checkProperties();

        return default_creator;
    }

    public static String getDefaultDescription()
    {
        checkProperties();

        return default_description;
    }

    public static String getDefaultInstance()
    {
        checkProperties();

        return default_instance;
    }

    public static String getDefaultMessageText()
    {
        checkProperties();

        return default_message_text;
    }

    public static String getDefaultEmailAddress()
    {
        checkProperties();

        return default_email_address;
    }

    public static String getDefaultPortal()
    {
        checkProperties();

        return default_portal;
    }

    public static Properties LogCommand ( String command ) throws IOException, InterruptedException
    {
        return LogCommand ( command, null, null );
    }

    public static Properties LogCommand ( String command, String output_file ) throws IOException, InterruptedException
    {
        return LogCommand ( command, output_file, null );
    }

    public static Properties LogCommand ( String command, String output_file, String error_file ) throws IOException, InterruptedException
    {
        _log.info ( "Executing command: " + command );
        FileOutputStream outfile        = null;
        String output_string            = null;
        InputStream error               = null;
        WeakReference <Runtime> rt      = new WeakReference <Runtime> ( Runtime.getRuntime() );
        WeakReference <Process> process = new WeakReference <Process> ( rt.get().exec ( command ) );
        //process.get().waitFor();  //  Waiting for the process will time out
        Properties properties = new Properties();

        InputStream input    = process.get().getInputStream();
        byte read_bytes[]    = new byte[1024];
        int num_bytes_read   = input.read ( read_bytes ); 
        StringBuilder output = new StringBuilder();

        while ( num_bytes_read > 0 )
        {
            if ( _log.isDebugEnabled() )
            {
                _log.debug ( "Input bytes read: " + num_bytes_read );
            }
            output.append ( new String ( read_bytes ) );

            num_bytes_read = input.read ( read_bytes ); 
        }
        output_string = output.toString().trim();

        if ( null != output_file )
        {
            outfile = new FileOutputStream ( output_file, true );
                if ( output_string.length() > 0 )
                {
                    outfile.write ( output_string.getBytes() );
                    outfile.write ( "\n".getBytes() );
                }
            outfile.close();
        }
        else
        {
            _log.info ( "Stdout: " + output_string );
        }
        properties.setProperty ( "stdout", output_string );

        error          = process.get().getInputStream();
        read_bytes     = new byte[1024];
        num_bytes_read = error.read ( read_bytes ); 

        output         = new StringBuilder();
        while ( num_bytes_read > 0 )
        {
            if ( _log.isDebugEnabled() )
            {
                _log.debug ( "Error bytes read: " + num_bytes_read );
            }
            output.append ( new String ( read_bytes ) );

            num_bytes_read = error.read ( read_bytes ); 
        }
        output_string = output.toString().trim();
        if ( null != error_file )
        {
            outfile = new FileOutputStream ( error_file, true );
                if ( output_string.length() > 0 )
                {
                    outfile.write ( output_string.getBytes() );
                    outfile.write ( "\n".getBytes() );
                }
            outfile.close();
        }
        else
        {
            _log.info ( "Stderr: " + output_string );
        }
        properties.setProperty ( "stderr", output_string );

        return properties;
    }
};
