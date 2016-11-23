package com.bgi.esm.monitoring.platform.alarmpoint;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Common
{
    final private static Logger _log                         = Logger.getLogger ( Common.class );
    final private static String stored_procedure_people      = "exec p_get_people";
    final private static String stored_procedure_app_people  = "exec p_get_app_people_info";

    private static Properties default_values                 = null;
    private static Properties properties                     = null;
    private static Connection con_source                     = null;
    private static Connection con_dest                       = null;
    private static Connection con_apserver                   = null;

    private static String database_apserver_url              = null;
    private static String database_apserver_type             = null;
    private static String database_apserver_name             = null;
    private static String database_apserver_port             = null;
    private static String database_apserver_hostname         = null;
    private static String database_apserver_username         = null;
    private static String database_apserver_password         = null;
    private static String database_source_url                = null;
    private static String database_source_type               = null;
    private static String database_source_name               = null;
    private static String database_source_port               = null;
    private static String database_source_hostname           = null;
    private static String database_source_username           = null;
    private static String database_source_password           = null;
    private static String database_dest_url                  = null;
    private static String database_dest_type                 = null;
    private static String database_dest_name                 = null;
    private static String database_dest_port                 = null;
    private static String database_dest_owner                = null;
    private static String database_dest_hostname             = null;
    private static String database_dest_username             = null;
    private static String database_dest_password             = null;

    public static ResultSet retrievePeople() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con          = getSourceConnection();
        CallableStatement cstmt = con.prepareCall ( stored_procedure_people );

        return cstmt.executeQuery();
    }

    public static ResultSet retrieveAppPeople() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con          = getSourceConnection();
        CallableStatement cstmt = con.prepareCall ( stored_procedure_app_people );

        return cstmt.executeQuery();
    }

    public static Connection getSourceConnection() throws SQLException, ClassNotFoundException, IOException
    {
        //  Check for null connections
        if (( null != con_source ) && ( con_source.isClosed() ))
        {
            con_source = null;
        }

        if ( null == con_source )
        {
            //  Check properties
            checkProperties();

            _log.info ( "Connecting to source database URL: " + database_source_url );

            con_source = DriverManager.getConnection ( database_source_url, database_source_username, database_source_password );
        }

        return con_source;
    }

    public static Connection getDestConnection() throws SQLException, ClassNotFoundException, IOException
    {
        //  Check for null connections
        if (( null != con_dest ) && ( con_dest.isClosed() ))
        {
            con_dest = null;
        }

        if ( null == con_dest )
        {
            //  Check properties
            checkProperties();

            _log.info ( "Connecting to destination database URL: " + database_dest_url );

            con_dest = DriverManager.getConnection ( database_dest_url, database_dest_username, database_dest_password );
        }

        return con_dest;
    }

    public static Connection getAPServerConnection() throws SQLException, ClassNotFoundException, IOException
    {
        //  Check for null connections
        if (( null != con_apserver ) && ( con_apserver.isClosed() ))
        {
            con_apserver = null;
        }

        if ( null == con_apserver )
        {
            //  Check properties
            checkProperties();

            _log.info ( "Connecting to destination database URL: " + database_apserver_url );

            con_apserver = DriverManager.getConnection ( database_apserver_url, database_apserver_username, database_apserver_password );
        }

        return con_apserver;
    }

    public static String getDestDatabaseOwner() throws IOException, ClassNotFoundException
    {
        checkProperties();

        return database_dest_owner;
    }

    /**
     *
     */
    public static void clearStagingTables() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con = Common.getDestConnection();

        //  Change "alarmpoint_action='R'" so the data sync operation can clear out old users.  
        //  If the user exists in LDAP, then the other Data Sync utilities will change the alarmpoint_action='Y'
        Statement stmt = con.createStatement();
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_users SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_email_devices SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_im_devices SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_numeric_pager_devices SET alarmpoint_action='R'" );
            /*
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_groups SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_team_memberships SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_teams SET alarmpoint_action='R'" );
            //*/
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_text_pager_devices SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_text_phone_devices SET alarmpoint_action='R'" );
            stmt.executeUpdate ( "UPDATE " + database_dest_owner + "ds_voice_devices SET alarmpoint_action='R'" );
        stmt.close();
    }

    public static void queryDump ( ResultSet results, String filename ) throws SQLException, IOException
    {
        ResultSetMetaData md = results.getMetaData();

        int record_counter = 0;
        int counter = 0;
        int num_columns = md.getColumnCount();
        String temp_string = null;

        FileOutputStream outfile = new FileOutputStream(filename);
        for (counter = 1; counter <= num_columns; counter++)
        {
            outfile.write(md.getColumnName(counter).getBytes());
            // if ( counter+1 != num_columns )
            if (true)
            {
                outfile.write("|".getBytes());
            }
        }
        outfile.write("\n".getBytes());
        while (results.next())
        {
            if (record_counter % 500 == 0)
            {
                System.out.println("\tProcessing record #" + record_counter);
            }
            for (counter = 1; counter <= num_columns; counter++)
            {
                temp_string = results.getString(counter);
                temp_string = (null != temp_string) ? temp_string : "";

                outfile.write(temp_string.getBytes());
                // if ( counter+1 != num_columns )
                if (true)
                {
                    outfile.write("|".getBytes());
                }
            }
            outfile.write("\n".getBytes());
            record_counter++;
        }
        System.out.println("num records: " + record_counter);
        outfile.close();
    }

    private static void checkProperties() throws IOException, ClassNotFoundException
    {
        if ( null == properties )
        {
            _log.info ( "Read from default properties file" );
           
            Class c         = Common.class;
            ClassLoader cl  = c.getClassLoader();
            InputStream is  = cl.getResourceAsStream ( "databases.properties" );
            properties      = new Properties(); 
            properties.load ( is );

            database_apserver_type     = properties.getProperty ( "apserver.database.type",     null );
            database_apserver_name     = properties.getProperty ( "apserver.database.name",     null );
            database_apserver_port     = properties.getProperty ( "apserver.database.port",     null );
            database_apserver_hostname = properties.getProperty ( "apserver.database.hostname", null );
            database_apserver_username = properties.getProperty ( "apserver.database.username", null );
            database_apserver_password = properties.getProperty ( "apserver.database.password", null );
            database_apserver_url      = createConnectionURL ( database_apserver_type, database_apserver_hostname, database_apserver_name, database_apserver_port );

            database_source_type       = properties.getProperty ( "source.database.type",     null );
            database_source_name       = properties.getProperty ( "source.database.name",     null );
            database_source_port       = properties.getProperty ( "source.database.port",     null );
            database_source_hostname   = properties.getProperty ( "source.database.hostname", null );
            database_source_username   = properties.getProperty ( "source.database.username", null );
            database_source_password   = properties.getProperty ( "source.database.password", null );
            database_source_url        = createConnectionURL ( database_source_type, database_source_hostname, database_source_name, database_source_port );

            database_dest_type         = properties.getProperty ( "dest.database.type",       null );
            database_dest_name         = properties.getProperty ( "dest.database.name",       null );
            database_dest_port         = properties.getProperty ( "dest.database.port",       null );
            database_dest_owner        = properties.getProperty ( "dest.database.owner",      null );
            database_dest_hostname     = properties.getProperty ( "dest.database.hostname",   null );
            database_dest_username     = properties.getProperty ( "dest.database.username",   null );
            database_dest_password     = properties.getProperty ( "dest.database.password",   null );
            database_dest_url          = createConnectionURL ( database_dest_type, database_dest_hostname, database_dest_name, database_dest_port );

            if ( null != database_dest_owner )
            {
                database_dest_owner = database_dest_owner + ".";
            }
            else
            {
                database_dest_owner = "";
            }
        }

        if ( null == default_values )
        {
            _log.info ( "Retrieving properties values containing default values" );
            Class c         = Common.class;
            ClassLoader cl  = c.getClassLoader();
            InputStream is  = cl.getResourceAsStream ( "default_values.properties" );
            default_values  = new Properties(); 
            default_values.load ( is );
        }
    }

    public static Properties defaultProperties() throws IOException, ClassNotFoundException
    {
        checkProperties();

        return default_values;
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
        
        _log.info ( "Created connection URL: " + connection_url.toString() );
        return connection_url.toString();
    }
};
