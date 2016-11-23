package com.bgi.esm.monitoring.platform.notifications;

import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author Dennis S. Lin (linden)
 */
public class Suppressions
{
    private static Logger _log                        = Logger.getLogger ( Suppressions.class );
    private static Properties db_props                = null;
    private static Connection con_local               = null;
    private static Connection con_primary             = null;
    private static Connection con_secondary           = null;

    private static String database_local_driver       = null;
    private static String database_local_url          = null;
    private static String database_local_username     = null;
    private static String database_local_password     = null;

    private static String database_primary_driver     = null;
    private static String database_primary_url        = null;
    private static String database_primary_username   = null;
    private static String database_primary_password   = null;

    private static String database_secondary_driver   = null;
    private static String database_secondary_url      = null;
    private static String database_secondary_username = null;
    private static String database_secondary_password = null;

    public static void main ( String args[] )
    {
        loadDatabaseProperties();

        while ( true )
        {
            try
            {
            }
            catch ( Exception exception )
            {
                _log.error ( "Unknown exception detected", exception );
            }
        }
    }

    /**
     *  Loads the database connection properties from the default properties file
     */
    public static void loadDatabaseProperties()
    {
        loadDatabaseProperties ( "database.properties" );
    }

    /**
     *  Loads the database connection properties from the specified properties file
     */
    public static void loadDatabaseProperties ( String filename )
    {
        if ( null != db_props )
        {
            _log.info ( "Database properties have already been initialized" );
            return;
        }

        try
        {
            _log.info ( "Initializing database connection properties from database.properties" );
            ClassLoader cl = Suppressions.class.getClassLoader();
            InputStream is = cl.getResourceAsStream ( filename );
            db_props       = new Properties();
            db_props.load ( is );

            //  Retrieve all properties
            database_local_driver       = db_props.getProperty ( "suppressions.database.local.driver"       );
            database_local_url          = db_props.getProperty ( "suppressions.database.local.url" 		    );
            database_local_username     = db_props.getProperty ( "suppressions.database.local.username"     );
            database_local_password     = db_props.getProperty ( "suppressions.database.local.password"     );

            database_primary_driver     = db_props.getProperty ( "suppressions.database.primary.driver"     );
            database_primary_url        = db_props.getProperty ( "suppressions.database.primary.url"        );
            database_primary_username   = db_props.getProperty ( "suppressions.database.primary.username"   );
            database_primary_password   = db_props.getProperty ( "suppressions.database.primary.password"   );

            database_secondary_driver   = db_props.getProperty ( "suppressions.database.secondary.driver"   );
            database_secondary_url      = db_props.getProperty ( "suppressions.database.secondary.url"      );
            database_secondary_username = db_props.getProperty ( "suppressions.database.secondary.username" );
            database_secondary_password = db_props.getProperty ( "suppressions.database.secondary.password" );
        }
        catch ( IOException exception )
        {
			WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
			message.get().append ( "Could not load props file '" );
			message.get().append ( filename );
			message.get().append ( "'" );
            _log.error ( message.get().toString(), exception );
        }
    }

    /**
     *  Tries to create a database connection
     *
     *  @param driver the name of the Java class used as a database driver
     *  @param url the connection URL to the database
     *  @param username login credentials to the database
     *  @param password login credentials to the database
     *  @return if successful, a valid connection to the database; null otherwise
     */
	private static Connection createDatabaseConnection ( String driver, String url, String username, String password )
	{
		Connection con = null;

		try
		{
			Class.forName ( driver );
			con = DriverManager.getConnection ( url, username, password );
		}
		catch ( ClassNotFoundException exception )
		{
			WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
			message.get().append ( "Could not find database driver '" );
			message.get().append ( driver );
			message.get().append ( "'" );

			_log.error ( message.get().toString(), exception );

			con = null;
		}
		catch ( SQLException exception )
		{
			WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
			message.get().append ( "Could not connect to database with the parameters ( URL, Username, Password ) = ( " );
			message.get().append ( url );
			message.get().append ( ", " );
			message.get().append ( username );
			message.get().append ( ", " );
			message.get().append ( password );
			message.get().append ( " )" );
			_log.error ( message.get().toString(), exception );

			con = null;
		}

		return con;
	}

    /**
     *  Retrieves the copy of the database properties object that this object is using
     *
     *  @return the copy of the database properties object that this object is using
     */
    public static Properties getDatabaseProperties()
    {
        return db_props;
    }

    /**
     *  Connect to the local database that caches suppression ID and notification information in
     *  order to prevent duplicate notifications
     *
     *  @return valid connection to the LOCAL suppressions database cache
     */
    public static Connection getLocalDatabaseConnection()
    {
        if ( null != con_local )
        {
            try
            {
                if ( con_local.isClosed() )
                {
                    _log.info ( "Detected dead LOCAL database connection" );
                    con_local.close();
                    con_local = null;
                }
            }
            catch ( SQLException exception )
            {
                _log.error ( "Error when trying to close stale database connection", exception );
            }
        }

        if ( null == con_local )
        {
			con_local = createDatabaseConnection ( database_local_driver, database_local_url, database_local_username, database_local_password );
        }

		return con_local;
    }

    /**
     *  Tries to retrieve a valid database connection to either the primary or secondary
     *  databases.  This function will always try to obtain a connection from the primary
     *  database before, even if the last call returned a valid connection to the secondary
     *  database
     *
     *  @return a valid database connection to either the primary or secondary databases
     */
    public static Connection getRemoteDatabaseConnection() throws SQLException
    {
        Connection con = getPrimaryRemoteDatabaseConnection();

        if ( null != con )
        {
            return con;
        }

        con = getSecondaryRemoteDatabaseConnection();

        if ( null != con )
        {
            return con;
        }

        _log.fatal ( "Could not obtain connection to Suppressions database" );

        throw new SQLException ( "Could not obtain connection to Suppressions database" );
    }

    /**
     *  Checks to see if the database to the PRIMARY suppressions database is alive, and returns a
     *  valid connection to it if possible
     *
     *  @return valid connection to the PRIMARY suppressions database
     */
    public static Connection getPrimaryRemoteDatabaseConnection()
    {
        if ( null != con_primary )
        {
            try
            {
                Class.forName ( database_primary_driver );
                con_primary = DriverManager.getConnection ( database_primary_url, database_primary_username, database_primary_password );
            }
            catch ( ClassNotFoundException exception )
            {
                _log.fatal ( "Could not load database driver for PRIMARY database connection", exception );
            }
            catch ( SQLException exception )
            {
                _log.error ( "Error when trying to connect to PRIMARY database connection", exception );
            }
        }

        if ( null == con_primary )
        {
			con_primary = createDatabaseConnection ( database_primary_driver, database_primary_url, database_primary_username, database_primary_password );
        }

        return con_primary;
    }

    /**
     *  Checks to see if the database to the SECONDARY suppressions database is alive, and returns a
     *  valid connection to it if possible
     *
     *  @return valid connection to the SECONDARY suppressions database
     */
    public static Connection getSecondaryRemoteDatabaseConnection()
    {
        if ( null != con_secondary )
        {
            try
            {
                Class.forName ( database_secondary_driver );
                con_secondary = DriverManager.getConnection ( database_secondary_url, database_secondary_username, database_secondary_password );
            }
            catch ( ClassNotFoundException exception )
            {
                _log.fatal ( "Could not load database driver for SECONDARY database connection", exception );
            }
            catch ( SQLException exception )
            {
                _log.error ( "Error when trying to connect to SECONDARY database connection", exception );
            }
        }

        if ( null == con_secondary )
        {
			con_secondary = createDatabaseConnection ( database_secondary_driver, database_secondary_url, database_secondary_username, database_secondary_password );
        }

        return con_secondary;
    }
};
