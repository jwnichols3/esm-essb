package tools.databases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import tools.struts.Common;
import tools.databases.Column;
import tools.databases.Table;

public class Database
{
    private static Properties common_properties = null;
    
    private static Connection con               = null;
    private static String db_name               = null;
    private static String db_driver             = null;
    private static String db_connection_url     = null;
    private static String db_username           = null;
    private static String db_password           = null;
    
    private HashMap <String, Table> tables      = new HashMap <String, Table> ();
    private Vector <Table> all_tables           = new Vector <Table> ();
    private Vector <String> all_table_names     = new Vector <String> ();
    private int max_length_table_name           = 0;

    public static void main ( String args[] ) throws IOException, SQLException, ClassNotFoundException
    {
        Database database = new Database();

        Enumeration <Table> e = database.getTables().elements();
        Table table           = null;
        Column column         = null;

        while ( e.hasMoreElements() )
        {
            table = e.nextElement();
            System.out.println ( "Found table: " + table.getName() );
            Vector <Column> all_columns = table.getAllColumns();
            for ( int counter = 0; counter < all_columns.size(); counter++ )
            {
                column = all_columns.elementAt ( counter );
                if ( column.isPrimaryKey() )
                {
                    System.out.println ( "\tFound PK column: " + column.getName() );
                }
                else
                {
                    System.out.println ( "\tFound column:    " + column.getName() );
                }
            }
        }

    }

    public Database() throws IOException, SQLException, ClassNotFoundException
    {
        readProperties();

        initialize();
    }

	/**
	 *
	 */
    public Database ( String name ) throws IOException, SQLException, ClassNotFoundException
    {
        readProperties();

        initialize();
    }

    /**
     * Organizes and prepares the data to build the Database object to represent the database that
     * backs the portlet.
     */
    public void initialize() throws SQLException, IOException, ClassNotFoundException
    {
        getDatabaseConnection();

        Statement stmt    = con.createStatement();
        ResultSet results = stmt.executeQuery ( "SHOW Tables" );
        String table_name = null;
        Table table       = null;

        while ( results.next() )
        {
            //  Retrieve the next table name
            table_name = results.getString ( 1 );

            if ( table_name.length() > max_length_table_name )
            {
                max_length_table_name = table_name.length();
            }

            //  Store the table name
            all_table_names.add ( table_name );

            //  Retrieve information about the table
            table = new Table ( db_name, table_name, con );

            //  Store table information into HashMap
            tables.put ( table_name, table );

            // Store table information into Vector
            all_tables.add ( table );
        }
    }

    /**
     * Returns a Vector of all the Table objects for this database
     *
     * @return a Vector of all the Table objects for this database
     */
    public Vector <Table> getTables()
    {
        return all_tables;
    }

    /**
     * Returns the length of the longest table name
     *
     * @return the length of the longest table name
     */
    public int getMaxLengthTableName()
    {
        return max_length_table_name;
    }

    /**
     * Gets a handle to the portlet properties that this object needs
     */
    private static void readProperties() throws IOException
    {
        if ( null == common_properties )
        {
			common_properties = Common.getCommonProperties();
            db_driver         = common_properties.getProperty ( "database.driver" );
            db_connection_url = common_properties.getProperty ( "database.connection_url" );
            db_username       = common_properties.getProperty ( "database.username" );
            db_password       = common_properties.getProperty ( "database.password" );
            db_name           = common_properties.getProperty ( "database.name" );
        }
    }

	/**
	 * Returns a connection to the database backing this portlet.  The connection could be an existing valid connection 
	 * or could be newly created.
	 *
	 * @return a connection to the database backing this portlet
	 */
    private void getDatabaseConnection() throws SQLException, ClassNotFoundException, IOException
    {
        if ( null == con )
        {
			readProperties();

            Class.forName ( db_driver );
            con  = DriverManager.getConnection( db_connection_url, db_username, db_password );
        }
    }
}
