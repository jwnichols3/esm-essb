package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Toolkit
{
    final private static Logger _log            = Logger.getLogger ( Toolkit.class );
    private static Properties common_properties = null;

    private static Connection con               = null;
    private static String db_connection_url     = null;
    private static String db_driver             = null;
    private static String db_type               = null;
    private static String db_server             = null;
    private static String db_name               = null;
    private static String db_port               = null;
    private static String db_username           = null;
    private static String db_password           = null;

    //  Common formatter objects
    private static DateFormat date_format       = DateFormat.getDateInstance();

    /**
     * Retrieves the instance instance-specific information maintained by the portal from the session and
     * updates the portlet properties
     * 
     * @param request the HttpServletRequest object that holds the instance-specific information from the portal
     */
    public static void setPortletProperties ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        setPortletProperties ( renderRequest );
    } 
    
    /**
     * Retrieves the instance-specific information maintained by the portal from the session and
     * updates the portlet properties
     * 
     * @param renderRequest the javax.portlet.RenderRequest object that holds the instance-specific information from the portal
     */
    public static void setPortletProperties ( RenderRequest renderRequest )
    {
        if ( null == renderRequest )
        {
            _log.error ( "Null renderRequest detected" );
        }
        else
        {
            _log.info ( "Set portlet properties" );

            PortletPreferences prefs = renderRequest.getPreferences();
        }
    }

    /**
     * Retrieve common portlet properties from the portlet preferences
     *
     * @param renderRequest the javax.portlet.RenderRequest object that holds the instance-specific information from the portal
     */
    private static void getDefaultPortletProperties ( RenderRequest renderRequest ) throws IOException
    {
        if ( null == renderRequest )
        {
            _log.error ( "Null renderRequest detected" );

            getDefaultPortletProperties();
        }
        else
        {
            _log.info ( "Set portlet properties" );

            PortletPreferences prefs = renderRequest.getPreferences();
        }
    }

    /**
     * Retrieve common portlet properties from the default properties file.  Useful for unit testing
     */
    private static void getDefaultPortletProperties() throws IOException
    {
        if ( null == common_properties )
        {
            ClassLoader cl = null;
            InputStream is = null;

            common_properties = new Properties(); 
            Class c           = (new Toolkit()).getClass();
            cl                = c.getClassLoader();
            is                = cl.getResourceAsStream ( "ServiceCenter.properties" );
            common_properties.load ( is );           
            
	        retrieveProperties();
        }
    }

    /**
     * Sets the values of common variables from the values in a java.util.Properties object .  
     * Make sure this is called AFTER the properties are retrieved
     */
    private static void retrieveProperties() throws IOException
    {
        getDefaultPortletProperties();

        if ( null != common_properties )
        {
            db_type               = common_properties.getProperty ( "database.type"     );
            db_server             = common_properties.getProperty ( "database.server"   );
            db_name               = common_properties.getProperty ( "database.name"     );
            db_port               = common_properties.getProperty ( "database.port"     );
            db_username           = common_properties.getProperty ( "database.username" );
            db_password           = common_properties.getProperty ( "database.password" );
        }
        else
        {
            _log.fatal ( "Common portlet properties were not retrieved" );
        }
    }

    public static Connection getDatabaseConnection ( RenderRequest renderRequest ) throws SQLException, ClassNotFoundException, IOException
    {
        //  Checking for stale database connection
        if (( null != con ) && ( con.isClosed() ))
        {
            //  The database connection is stale.  Set it to null to 
            //  so the later block of code can create a new one
            con = null;
        }

        //  Checking for null database connection
        if ( null == con )
        {
            getDefaultPortletProperties ( renderRequest );
        }

        return getDatabaseConnection();
    }

    /**
     * Returns a connection to the database backing this portlet.  The connection could be an existing valid connection 
     * or could be newly created.  The properties for this connection are taken from the portlet properties files.
     *
     * @return a connection to the database backing this portlet
     */
    public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException, IOException
    {
        //  Checking for stale database connection
        if (( null != con ) && ( con.isClosed() ))
        {
            //  The database connection is stale.  Set it to null to 
            //  so the later block of code can create a new one
            con = null;
        }

        if ( null == con )
        {
            getDefaultPortletProperties();

            if ( db_type.equals ( "SQL Server" ) )
            {
                _log.info ( "Getting source connection to SQL Server" ); 
                con         = getDatabaseConnectionForSQLServer ( db_server, Integer.parseInt ( db_port ), db_name, db_username, db_password );
            }
            else if ( db_type.equals ( "MySQL" ) )
            {
                _log.info ( "Getting source connection to MySQL" ); 
                con         = getDatabaseConnectionForMySQL ( db_server, Integer.parseInt ( db_port ), db_name, db_username, db_password );
            }
            else if ( db_type.equals ( "Sybase" ) )
            {
                _log.info ( "Getting source connection to Sybase" ); 
                con         = getDatabaseConnectionForSybase ( db_server, Integer.parseInt ( db_port ), db_name, db_username, db_password );
            }
            else if ( db_type.equals ( "Oracle" ) )
            {
                _log.info ( "Getting source connection to Oracle" ); 
                con         = getDatabaseConnectionForOracle ( db_server, Integer.parseInt ( db_port ), db_name, db_username, db_password );
            }
            else if ( db_type.equals ( "PostGreSQL" ) )
            {
                _log.info ( "Getting source connection to PostGreSQL" ); 
                con         = getDatabaseConnectionForPostGreSQL ( db_server, Integer.parseInt ( db_port ), db_name, db_username, db_password );
            }
            else
            {
                _log.fatal ( "Unknown source database settings: " + db_type );
            }
    
        }

        return con;
    }

    /**
     * Creates a java.sql.Connection to a MySQL database on the default port
     *
     * @return a java.sql.Connection to the specified MySQL database
     */
    public static Connection getDatabaseConnectionForMySQL ( String host, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForMySQL ( host, 3306, database, username, password );
    }

    /**
     * Creates a java.sql.Connection to a MySQL database on any port
     *
     * @return a java.sql.Connection to the specified MySQL database
     */
    public static Connection getDatabaseConnectionForMySQL ( String host, int port, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        //"jdbc:mysql://localhost/bgi_monitoring",
        db_driver = "com.mysql.jdbc.Driver";

        Class.forName ( "com.mysql.jdbc.Driver" );
        String db_connection_url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        Connection con           = DriverManager.getConnection ( db_connection_url, username, password );

        return con;
    }

    /**
     * Creates a java.sql.Connection to an database on any port with the default username/password pair
     *
     * @return a java.sql.Connection to the specified Oracle database
     */
    public static Connection getDatabaseConnectionForOracle ( String host, String database )
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForOracle ( host, 1521, database, "system", "manager" );
    }

    /**
     * Creates a java.sql.Connection to a Oracle database on the default port
     *
     * @return a java.sql.Connection to the specified Oracle database
     */
    public static Connection getDatabaseConnectionForOracle ( String host, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForOracle ( host, 1521, database, username, password );
    }

    /**
     * Creates a java.sql.Connection to a Oracle database on the any port
     *
     * @return a java.sql.Connection to the specified Oracle database
     */
    public static Connection getDatabaseConnectionForOracle ( String host, int port, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        //"jdbc:oracle:thin:@vpo:1521:ov_net" 
        db_driver = "oracle.jdbc.driver.OracleDriver";

        Class.forName ( "oracle.jdbc.driver.OracleDriver" );
        String db_connection_url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
        Connection con           = DriverManager.getConnection ( db_connection_url, username, password );

        return con;
    }

    /**
     * Creates a java.sql.Connection to a PostGreSQL database on the default port
     *
     * @return a java.sql.Connection to the specified PostGreSQL database
     */
    public static Connection getDatabaseConnectionForPostGreSQL ( String host, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForPostGreSQL ( host, 5432, database, username, password );
    }

    /**
     * Creates a java.sql.Connection to a PostGreSQL database on the any port
     *
     * @return a java.sql.Connection to the specified PostGreSQL database
     */
    public static Connection getDatabaseConnectionForPostGreSQL ( String host, int port, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        db_driver = "org.postgresql.Driver";

        Class.forName ( "org.postgresql.Driver" );
        String db_connection_url = "jdbc:postgresql://" + host + ":" + port + ":" + database;
        Connection con           = DriverManager.getConnection ( db_connection_url, username, password );

        return con;
    }
    /**
     * Creates a java.sql.Connection to a Sybase database on the default port
     *
     * @return a java.sql.Connection to the specified Sybase database
     */
    public static Connection getDatabaseConnectionForSybase ( String host, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForSybase ( host, 14110, database, username, password );
    }

    /**
     * Creates a java.sql.Connection to a Sybase database on the any port
     *
     * @return a java.sql.Connection to the specified Sybase database
     */
    public static Connection getDatabaseConnectionForSybase ( String host, int port, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        //"jdbc:sybase:Tds:localhost:14110/bgi_monitoring"
        db_driver = "com.sybase.jdbc2.jdbc.SybDriver";

        Class.forName ( "com.sybase.jdbc2.jdbc.SybDriver" );
        String db_connection_url = "jdbc:sybase:Tds:"+host+":"+port+"/"+database;
        Connection con           = DriverManager.getConnection ( db_connection_url, username, password );

        return con;
    }

    /**
     * Creates a java.sql.Connection to a Microsoft SQL Server database on the default port
     *
     * @return a java.sql.Connection to the specified Microsoft SQL Server database
     */
    public static Connection getDatabaseConnectionForSQLServer ( String host, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        return getDatabaseConnectionForSQLServer ( host, 1433, database, username, password );
    }

    /**
     * Creates a java.sql.Connection to a Microsoft SQL Server database on the any port
     *
     * @return a java.sql.Connection to the specified Microsoft SQL Server database
     */
    public static Connection getDatabaseConnectionForSQLServer ( String host, int port, String database, String username, String password ) 
            throws SQLException, IOException, ClassNotFoundException
    {
        //"jdbc:jtds:sqlserver://localhost:1433/bgi_monitoring",
        db_driver = "net.sourceforge.jtds.jdbc.Driver";

        Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );
        String db_connection_url = "jdbc:jtds:sqlserver://"+host+":"+port+"/"+database;
        Connection con           = DriverManager.getConnection ( db_connection_url, username, password );

        return con;
    }

    /**
     * The version of retrieveEvents to call from a portlet.
     *
     * @param start_time the java.util.Date object specifying the time of the first event to view
     * @param end_time the java.util.Date object specifying the time of the last event to view
     * @param renderRequest the javax.portlet.RenderRequest object that holds the instance-specific information from the portal
     *
     * @return a java.sql.ResultSet object with the query results
     */
    public static ResultSet retrieveEvents ( Date start_time, Date end_time, RenderRequest renderRequest ) throws SQLException, ClassNotFoundException, IOException
    {
        getDatabaseConnection ( renderRequest );

        return retrieveEvents ( start_time, end_time );
    }

    /**
     * Retrieves all the Alarmpoint-Peregrine-VPO events within a specified time period 
     *
     * @param start_time the java.util.Date object specifying the time of the first event to view
     * @param end_time the java.util.Date object specifying the time of the last event to view
     *
     * @return a java.sql.ResultSet object with the query results
     */
    public static ResultSet retrieveEvents ( Date start_time, Date end_time ) throws SQLException, ClassNotFoundException, IOException
    {
        getDatabaseConnection();

        PreparedStatement ps = con.prepareStatement ( "SELECT PeregrineCategory, NumAlarmpointEvents, min(timestamp) AS EventStartTime, max(timestamp) AS EventEndTime, message_number, anno_text_id, AlarmpointEvents FROM testdump_dest.EventsPeregrineAlarmpoint e WHERE timestamp > ? and timestamp < ? AND AlarmpointEvents IS NOT NULL AND NumAlarmpointEvents > 0 GROUP BY PeregrineCategory ORDER BY PeregrineCategory DESC" );

        //  The +1 or -1 at the end is to make it inclusive in the parameters without altering the SQL
        long millis_start_time = start_time.getTime() - 1;
        long millis_end_time   = end_time.getTime()   + 1;

        if ( _log.isInfoEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "retrieving events between "  );
                message.append ( date_format.format ( start_time ) );
                message.append ( " and " );
                message.append ( date_format.format ( end_time ) );
                message.append ( ", with timestamps of ( " );
                message.append ( millis_start_time );
                message.append ( ", " );
                message.append ( millis_end_time );
                message.append ( " )" );
            _log.info ( message.toString() );
        }

        ps.setLong ( 1, millis_start_time );
        ps.setLong ( 2, millis_end_time   );

        return ps.executeQuery();
    }
};
