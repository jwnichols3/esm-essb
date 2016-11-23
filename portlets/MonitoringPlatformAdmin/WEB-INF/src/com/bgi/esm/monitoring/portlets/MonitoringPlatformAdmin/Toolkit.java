package com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

//  Portlet stuff
import javax.servlet.http.HttpServletRequest;
import javax.portlet.PortletPreferences;

public class Toolkit
{
    final private static Logger _log                           = Logger.getLogger ( Toolkit.class );

    private static Properties database_queries                 = null;
    private static Properties common_properties                = null;

    //  Database connection properties
    private static Connection con_primary                      = null;
    private static Connection con_backup                       = null;

    //  Database connection properties
    private static String database_primary_driver              = null;
    private static String database_primary_hostname            = null;
    private static String database_primary_username            = null;
    private static String database_primary_password            = null;
    private static String database_primary_database            = null;
    private static String database_primary_port                = null;
    private static String database_primary_type                = null;
    private static String database_primary_url                 = null;

    private static String database_backup_driver               = null;
    private static String database_backup_hostname             = null;
    private static String database_backup_username             = null;
    private static String database_backup_password             = null;
    private static String database_backup_database             = null;
    private static String database_backup_port                 = null;
    private static String database_backup_type                 = null;
    private static String database_backup_url                  = null;

    //  Database PreparedStatement objects
    private static PreparedStatement ps_total_events           = null;
    private static PreparedStatement ps_num_events_time_window = null;

    public static Connection getPrimaryDatabaseConnection() throws SQLException
    {
        getDatabaseConnection();

        return con_primary;
    }

    public static Connection getBackupDatabaseConnection() throws SQLException
    {
        getDatabaseConnection();

        return con_backup;
    }

    public static Properties getCommonProperties()
    {
        return common_properties;
    }

    public static Properties getDatabaseQueriesProperties()
    {
        return database_queries;
    }

    public static Connection getDatabaseConnection() throws SQLException
    {
        //  Checking primary coonection
        if (( null != con_primary ) && ( con_primary.isClosed() ))
        {
            con_primary = null;
        }

        if ( null == con_primary )
        {
            try
            {
                checkCommonProperties();

                if ( _log.isInfoEnabled() )
                {
                    _log.info ( "Attempting to connect to primary database: " );
                    _log.info ( "    Connection URL: " + database_primary_url );
                    _log.info ( "    Username:       " + database_primary_username );
                    _log.info ( "    Password:       " + database_primary_password );
                }

                con_primary = DriverManager.getConnection ( database_primary_url, database_primary_username, database_primary_password );

                _log.info ( "Creating PreparedStatement objects" );

                checkPrimaryDatabaseQueries();
            }
            catch ( SQLException exception )
            {
                _log.error ( "Could not connect to primary database... failing over to backup database", exception );
            }
        }

        //  Checking backup connection
        if (( null != con_backup ) && ( con_backup.isClosed() ))
        {
            con_backup = null;
        }

        if ( null == con_backup )
        {
            try
            {
                checkCommonProperties();

                if ( _log.isInfoEnabled() )
                {
                    _log.info ( "Attempting to connect to backup database: " );
                    _log.info ( "    Connection URL: " + database_backup_url );
                    _log.info ( "    Username:       " + database_backup_username );
                    _log.info ( "    Password:       " + database_backup_password );
                }

                con_backup = DriverManager.getConnection ( database_backup_url, database_backup_username, database_backup_password );

                checkBackupDatabaseQueries();
            }
            catch ( SQLException exception )
            {
                _log.error ( "Could not connect to backup database... " );
            }
        }


        if (( null != con_primary ) && ( con_primary.isClosed() == false ))
        {
            return con_primary;
        }
        else
        {
            return con_backup;
        }
    }

    public static synchronized HashMap <String, Integer> retrieveTotalNumEvents() throws SQLException
    {
        HashMap <String, Integer> return_val = new HashMap <String, Integer> ();
        ResultSet results                    = ps_total_events.executeQuery();

        while ( results.next() )
        {
            return_val.put ( results.getString ( 1 ), new Integer ( results.getInt ( 2 ) ) );
        }

        return return_val;
    }

    /**
     *  If the message queue exists, returns the number of messages that have happened within 
     *  the specified time window.
     *
     *  @param queue_name the name of the message queu
     *  @param start_time the start of the time period in question
     *  @param end_time the end of the time period in question
     *  @return the number of events in the message queue during the specified time period 
     *          if the message queue exists, -1 otherwise.
     */
    public static int retrieveNumEventsInTimeWindow ( String queue_name, Date start_time, Date end_time ) throws SQLException
    {
        if ( start_time.getTime() > end_time.getTime() )
        {
            throw new SQLException ( "Start time is after end time" );
        }

        ResultSet results = psNumEventsTimeWindow ( queue_name, start_time, end_time );
        if ( results.next() )
        {
            int num_events = results.getInt ( 1 );
            results.close();

            return num_events;
        }
        else
        {
            results.close();

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Could not find number of events in time window for ( " );
                message.get().append ( queue_name );
                message.get().append ( ", " );
                message.get().append ( start_time.getTime() );
                message.get().append ( ", " );
                message.get().append ( end_time.getTime() );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }

            return -1;
        }
    }

    public static HashMap <Date, Integer> getNumEventsInPast24Hours ( String queue_name ) throws SQLException
    {
        HashMap <Date, Integer> return_val = new HashMap <Date, Integer> ();

        //  Initialize Calendar to the start of the hour
        Calendar calendar = Calendar.getInstance();
        calendar.set ( Calendar.MINUTE,      0 );
        calendar.set ( Calendar.SECOND,      0 );
        calendar.set ( Calendar.MILLISECOND, 0 );
        Date start_time   = calendar.getTime();

        //  Initial end time is current time
        Date end_time     = new Date();

        for ( int counter = 0; counter < 25; counter++ )
        {
            ResultSet results = psNumEventsTimeWindow ( queue_name, start_time, end_time );
            if ( results.next() )
            {
                return_val.put ( start_time, new Integer ( results.getInt ( 1 ) ) );
            }
            else
            {
                return_val.put ( start_time, new Integer ( 0 ) );
            }

            //  Adjust the time window by 1 hour backwards
            end_time = start_time;

            calendar.add ( Calendar.HOUR, -1 );
            start_time = calendar.getTime();
        }

        return return_val;
    }

    private static synchronized ResultSet psNumEventsTimeWindow ( String queue_name, Date start_time, Date end_time ) throws SQLException
    {
        getDatabaseConnection();

        ps_num_events_time_window.setString ( 1, queue_name           );
        ps_num_events_time_window.setLong   ( 2, start_time.getTime() );
        ps_num_events_time_window.setLong   ( 3, end_time.getTime()   );

        return ps_num_events_time_window.executeQuery();
    }


    /**
     *  Forces a re-read of the props/database_queries.properties file and re-creates the necessary
     *  PreparedStatement objects for the primary database.
     */
    public static void checkPrimaryDatabaseQueries () throws SQLException
    {
        checkDatabaseQueries ( con_primary );
    }

    /**
     *  Forces a re-read of the props/database_queries.properties file and re-creates the necessary
     *  PreparedStatement objects for the backup database.
     */
    public static void checkBackupDatabaseQueries() throws SQLException
    {
        checkDatabaseQueries ( con_backup );
    }


    private static void checkDatabaseQueries ( Connection con ) throws SQLException
    {
        if ( null == database_queries )
        {
            String filename  = "props/database_queries.properties";
            database_queries = new Properties();
            ClassLoader cl   = Toolkit.class.getClassLoader();
            InputStream is   = cl.getResourceAsStream ( filename );
            try
            {
                database_queries.load ( is );

                //  Load the statements
                ps_total_events           = con.prepareStatement ( database_queries.getProperty ( "database.events.num_events.total" ) );
                ps_num_events_time_window = con.prepareStatement ( database_queries.getProperty ( "database.events.num_events.time_window" ) );
            }
            catch ( IOException exception )
            {
                _log.error ( "Could not load database queries from property file: " + filename );
            }
        }
    }

    public static void checkCommonProperties ()
    {
        //  Loading common properties
        if ( null == common_properties )
        {
            String filename   = "props/common.properties";
            common_properties = new Properties();
            ClassLoader cl    = Toolkit.class.getClassLoader();
            InputStream is    = cl.getResourceAsStream ( filename );

            try
            {
                common_properties.load ( is );
            }
            catch ( IOException exception )
            {
                _log.error ( "Could not load common portlet properties" );
            }

            database_primary_hostname = common_properties.getProperty ( Constants.DATABASE_PRIMARY_HOSTNAME, "" );
            database_primary_username = common_properties.getProperty ( Constants.DATABASE_PRIMARY_USERNAME, "" );
            database_primary_password = common_properties.getProperty ( Constants.DATABASE_PRIMARY_PASSWORD, "" );
            database_primary_database = common_properties.getProperty ( Constants.DATABASE_PRIMARY_DATABASE, "" );
            database_primary_port     = common_properties.getProperty ( Constants.DATABASE_PRIMARY_PORT,     "" );
            database_primary_type     = common_properties.getProperty ( Constants.DATABASE_PRIMARY_TYPE,     "" );
            database_primary_url      = createConnectionURL ( database_primary_type, database_primary_hostname, database_primary_database, database_primary_port );
            database_primary_driver   = getDatabaseDriverClassName ( database_primary_type );

            try
            {
                Class.forName ( database_primary_driver );
            }
            catch ( ClassNotFoundException exception )
            {
                _log.error ( "Could not load primary database driver: " + database_primary_driver, exception );
            }

            database_backup_hostname  = common_properties.getProperty ( Constants.DATABASE_BACKUP_HOSTNAME,  "" );
            database_backup_username  = common_properties.getProperty ( Constants.DATABASE_BACKUP_USERNAME,  "" );
            database_backup_password  = common_properties.getProperty ( Constants.DATABASE_BACKUP_PASSWORD,  "" );
            database_backup_database  = common_properties.getProperty ( Constants.DATABASE_BACKUP_DATABASE,  "" );
            database_backup_port      = common_properties.getProperty ( Constants.DATABASE_BACKUP_PORT,      "" );
            database_backup_type      = common_properties.getProperty ( Constants.DATABASE_BACKUP_TYPE,      "" );
            database_backup_url       = createConnectionURL ( database_backup_type, database_backup_hostname, database_backup_database, database_backup_port );
            database_backup_driver    = getDatabaseDriverClassName ( database_backup_type );

            try
            {
                Class.forName ( database_backup_driver );
            }
            catch ( ClassNotFoundException exception )
            {
                _log.error ( "Could not load backup database driver: " + database_backup_driver, exception );
            }
        }

        // Checking for user/machine specific properties
        StringBuilder user_props_filename = new StringBuilder ( "props/common." );
        user_props_filename.append ( System.getProperties().getProperty ( "user.name" ) );
    }

    public static void checkPortletPreferences ( PortletPreferences prefs )
    {
        if ( null != prefs )
        {
            database_primary_hostname = prefs.getValue ( Constants.DATABASE_PRIMARY_HOSTNAME, "" );
            database_primary_username = prefs.getValue ( Constants.DATABASE_PRIMARY_USERNAME, "" );
            database_primary_password = prefs.getValue ( Constants.DATABASE_PRIMARY_PASSWORD, "" );
            database_primary_database = prefs.getValue ( Constants.DATABASE_PRIMARY_DATABASE, "" );
            database_primary_port     = prefs.getValue ( Constants.DATABASE_PRIMARY_PORT,     "" );
            database_primary_type     = prefs.getValue ( Constants.DATABASE_PRIMARY_TYPE,     "" );

            database_backup_hostname  = prefs.getValue ( Constants.DATABASE_BACKUP_HOSTNAME,  "" );
            database_backup_username  = prefs.getValue ( Constants.DATABASE_BACKUP_USERNAME,  "" );
            database_backup_password  = prefs.getValue ( Constants.DATABASE_BACKUP_PASSWORD,  "" );
            database_backup_database  = prefs.getValue ( Constants.DATABASE_BACKUP_DATABASE,  "" );
            database_backup_port      = prefs.getValue ( Constants.DATABASE_BACKUP_PORT,      "" );
            database_backup_type      = prefs.getValue ( Constants.DATABASE_BACKUP_TYPE,      "" );
        }
    }


    /**
     * Creates the connection URl to a JDBC database
     * 
     * @param database_type The type of database (Sybase, SQL Server Oracle, MySQL, PostgreSQL, ODBC)
     * @param server the server the database resides on
     * @param name the name of the database
     * @param port the port that the database is listening on
     * @return the connection URl to a JDBC database
     */
    private static String createConnectionURL ( String database_type, String server, String name, String port )
    {
        StringBuilder connection_url = new StringBuilder();
       
        if ( null == database_type )
        {
            return "";
        } 
        if ( database_type.equals ( "Sybase" ) )
        {
            connection_url.append ( "jdbc:sybase:Tds:" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "SQL Server" ) )
        {
            connection_url.append ( "jdbc:jtds:sqlserver://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "MySQL" ) )
        {
            connection_url.append ( "jdbc:mysql://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "Oracle" ))
        {
            connection_url.append ( "jdbc:oracle:thin:@" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( ":" );
            connection_url.append ( name );
        }
        
        _log.info ( "Created connection URL: " + connection_url.toString() );
        return connection_url.toString();
    }
    
    /**
     * @param database_type the type of the database (Sybase, SQL Server, Oracle, PostgreSQL, MySQL)
     * @return the class name of the driver that the database will use
     */
    private static String getDatabaseDriverClassName ( String database_type )
    {
        if ( null == database_type )
        {
            return "Unknown";
        }
        else if ( database_type.equals ( "Sybase" ) )
        {
            return "com.sybase.jdbc2.jdbc.SybDriver";
        }
        else if ( database_type.equals ( "SQL Server" ) )
        {
            return "net.sourceforge.jtds.jdbc.Driver";
        }
        else if ( database_type.equals ( "MySQL" ) )
        {
            return "com.mysql.jdbc.Driver";
        }
        else if ( database_type.equals ( "Oracle" ))
        {
            return "oracle.jdbc.driver.OracleDriver";
        }
        
        return "Unknown";
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     * @return a HashMap &lt;String, String&gt; of all the HTTP parameters in the HTTP request
     */
    public static HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        HashMap <String, String> param_map = new HashMap <String, String> ();

        if ( request.getParameter ( "_spage" ) != null ) // Liferay parameter handling
        {
            String parameter = request.getParameter ( "_spage" );
            parameter = parameter.substring ( parameter.indexOf ( "?" )+1 );

            // Parse out the tokens
            StringTokenizer tokenizer = new StringTokenizer ( parameter, "&" );
            String key                = null;
            String value              = null;
            int index                 = 0;
            param_map                 = new HashMap <String, String> ();
    
            while ( tokenizer.hasMoreTokens() )
            {
                parameter = tokenizer.nextToken();
                index     = parameter.indexOf ( "=" );
                if ( index >= 0 )
                {
                    key       = parameter.substring ( 0, index );
                    value     = parameter.substring ( index+1 );

                    param_map.put ( key, value );
                }
            }
        }
        else // Struts parameter handling
        {
            Enumeration e = request.getParameterNames();
            String es     = null;
            param_map     = new HashMap <String, String>();
            while ( e.hasMoreElements() )
            {
                es = e.nextElement().toString();
                param_map.put ( es, request.getParameter ( es ) );
            }
        }

        return param_map;
    }

    public static void LogToFile ( String message )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( "c:\\test\\MonitoringPlatformAdmin.out", true );
            outfile.write ( (new Date()).toString().getBytes() );
            outfile.write ( " - ".getBytes() );
            outfile.write ( message.getBytes() );
            outfile.write ( "\n".getBytes() );
        }
        catch ( IOException exception )
        {
        }
    }
};
