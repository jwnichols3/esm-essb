package tools.databases;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;

import tools.databases.Column;

public class Table
{
    private static Properties common_properties                  = null;
    private static String db_name                                = null;
    private static String db_driver                              = null;
    private static String db_connection_url                      = null;
    private static String db_username                            = null;
    private static String db_password                            = null;
    private static String table_schema_name                      = null;
    private static HashMap <Object, Object> generator_properties = null;
    private static Properties properties                         = null;
    
    private String name                       = null;

    private int max_col_name_length           = 0;
    private int max_func_name_length          = 0;

    private static Connection con             = null;
    private Statement stmt                    = null;
    private ResultSet results                 = null;
    private ResultSetMetaData rsmd            = null;
    private DatabaseMetaData dmd              = null;

    //  Table data
	private Vector <Column> all_columns       = new Vector <Column> ();
    private Vector <Column> pk_columns        = new Vector <Column> ();
    private Vector <Column> data_columns      = new Vector <Column> ();
    private Vector <String> primary_keys      = new Vector <String> ();
    private int num_columns                   = 0;

    private StringBuilder query_select_all    = null;
    private StringBuilder query_select        = null;
    private StringBuilder query_insert        = null;
    private StringBuilder query_update        = null;
    private StringBuilder query_delete        = null;


    public static void main ( String args[] ) throws SQLException, ClassNotFoundException, IOException
    {
        //Table t = new Table ( "bgi_monitoring", "pingmonitor_filters" );
    }

    public Table ( String database, String new_name ) throws SQLException, ClassNotFoundException, IOException
    {
        name    = new_name.trim();
        db_name = database.trim();

        initializeFromDatabase();
    }

    public Table ( String database, String new_name, Connection c ) throws SQLException, ClassNotFoundException, IOException
    {
        name    = new_name;
        db_name = database;
        con     = c;

        initializeFromDatabase();
    }

    public String getName()
    {
        return name;
    }

    public String getDBName()
    {
        return db_name;
    }

    public Vector <Column> getAllColumns()
    {
        return all_columns;
    }

    public Vector <Column> getDataColumns()
    {
        return data_columns;
    }

    public Vector <Column> getPrimaryKeyColumns()
    {
        return pk_columns;
    }

    public int getNumPrimaryKeys()
    {
        return pk_columns.size();
    }

    public int getNumDataColumns()
    {
        return data_columns.size();
    }

    public int getNumAllColumns()
    {
        return all_columns.size();
    }

    private void getDatabaseConnection() throws SQLException, ClassNotFoundException
    {
        if ( null == con )
        {
            Class.forName ( db_driver );
            con  = DriverManager.getConnection( db_connection_url, db_username, db_password );
        }

        stmt = con.createStatement();
        dmd  = con.getMetaData();
        table_schema_name  = dmd.getSchemaTerm().trim();
    }

    public void initializeFromDatabase() throws SQLException, IOException, ClassNotFoundException
    {
        //readProperties();

        getDatabaseConnection();

        if ( null == rsmd )
        {
            String col_name        = null;
            StringBuilder query    = new StringBuilder ( "SELECT * FROM " );
            query.append ( name );
            results                = dmd.getPrimaryKeys ( db_name, table_schema_name, name );
            rsmd                   = results.getMetaData();

            num_columns            = rsmd.getColumnCount();
            while ( results.next() )
            {
                col_name = results.getString ( "COLUMN_NAME" );

                if ( null != col_name )
                {
                    col_name = col_name.trim().toLowerCase();
                    if ( !primary_keys.contains ( col_name ) )
                    {
                        primary_keys.add ( col_name );
                    }
                }
            }

            results                = stmt.executeQuery ( query.toString() );
            rsmd                   = results.getMetaData();
            num_columns            = rsmd.getColumnCount();
            int type               = 0;
			Column column          = null;

            for ( int counter = 1; counter <= num_columns; counter++ )
            {
                col_name = rsmd.getColumnName( counter ).toLowerCase();
				column = new Column ( col_name );
                all_columns.add ( column );
				if ( primary_keys.contains ( col_name ) )
				{
					column.setIsPrimaryKey ( true );
                    pk_columns.add ( column );
				}
                else
                {
                    data_columns.add ( column );
                }

                if ( rsmd.isAutoIncrement( counter ) )
                {
                    column.setIsAutoIncrement ( true );
                }

                if ( col_name.length() > max_col_name_length )
                {
                    max_col_name_length = col_name.length();
                }

                type = rsmd.getColumnType( counter );

                column.setType ( type );
            }
        }

        stmt.close();

        createDatabaseStatements();
    }


    @SuppressWarnings("unused")
    private static void readProperties() throws IOException
    {
        if ( null == properties ) 
        {
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "StrutsTools.properties" );
            properties        = new Properties();
            properties.load ( is );
        }

        if ( null == common_properties )
        {
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "Common.properties" );
            common_properties = new Properties();
            common_properties.load ( is );
            db_driver         = common_properties.getProperty ( "database.driver" );
            db_connection_url = common_properties.getProperty ( "database.connection_url" );
            db_username       = common_properties.getProperty ( "database.username" );
            db_password       = common_properties.getProperty ( "database.password" );
        }
    }

    @SuppressWarnings("unused")
    private String formatStringToLength ( String string, int length )
    {
        StringBuilder s = new StringBuilder ( string ); while ( s.length() < length )
        {
            s.append ( " " );
        }

        return s.toString();
    }

    private void createDatabaseStatements() throws SQLException
    {
        StringBuilder columns_select      = new StringBuilder ();
        StringBuilder columns_insert      = new StringBuilder ();
        StringBuilder data_insert         = new StringBuilder ();
        StringBuilder columns_update      = new StringBuilder ();
        StringBuilder database_query      = new StringBuilder ( "SELECT * FROM " );
        StringBuilder pk_string           = new StringBuilder ();
        database_query.append ( getName() );
        query_select_all                  = new StringBuilder ( "SELECT " );
        query_select                      = new StringBuilder ( "SELECT " );
        query_insert                      = new StringBuilder ( "INSERT INTO " );
        query_update                      = new StringBuilder ( "UPDATE " );
        query_delete                      = new StringBuilder ( "DELETE FROM " );

        String column_name                = null;
        int num_data_columns              = 0;
        int counter                       = 0;
        
        Column column                     = null;

        num_data_columns = data_columns.size();
        for ( counter = 0; counter < num_data_columns; counter++ )
        {
            column      = data_columns.elementAt ( counter );
            column_name = column.getName();

            columns_select.append ( column_name );
            columns_insert.append ( column_name );
            data_insert.append ( "?" );
            columns_update.append ( column_name );
            columns_update.append ( "=?" );

            if ( counter+1 < num_data_columns )
            {
                columns_select.append ( ", " );
                columns_insert.append ( ", " );
                data_insert.append ( ", " );
                columns_update.append ( ", " );
            }
        }

        boolean first_insert = false;
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column      = pk_columns.elementAt(counter);
            column_name = column.getName();

            pk_string.append ( column_name );
            pk_string.append ( "=?" );

            if ( false == column.isAutoIncrement() )
            {
                columns_insert.append ( column_name );
                data_insert.append ( "?" );
            }

            if ( counter+1 < pk_columns.size() )
            {
                pk_string.append ( ", " );
                if ( false == column.isAutoIncrement() )
                {
                    if ( false == first_insert )
                    {
                        data_insert.append ( ", " );
                    }
                    columns_insert.append ( ", " );
                }
                data_insert.append ( ", " );
            }
        }

        query_select_all.append ( columns_select.toString() );
        query_select_all.append ( " FROM " );
        query_select_all.append ( getName() );

        query_select.append ( columns_select.toString() );
        query_select.append ( " FROM " );
        query_select.append ( getName() );
        query_select.append ( " WHERE " );
        query_select.append ( pk_string.toString() );

        query_update.append ( getName() );
        query_update.append ( " SET " );
        query_update.append ( columns_update.toString() );
        query_update.append ( " WHERE " );
        query_update.append ( pk_string );

        query_insert.append ( getName() );
        query_insert.append ( " ( " );
        query_insert.append ( columns_insert.toString() );
        query_insert.append ( " ) VALUES ( " );
        query_insert.append ( data_insert.toString() );
        query_insert.append ( " )" );

        query_delete.append ( getName() );
        query_delete.append ( " WHERE " );
        query_delete.append ( pk_string );
    }

    public String formatPropertyName ( String column )
    {
        String token = formatColumnName ( column );

        return token.substring(0,1).toLowerCase() + token.substring ( 1 );
    }

    public String formatColumnName ( String column )
    {
        StringTokenizer tokens    = new StringTokenizer ( column, "_" );
        StringBuilder column_name = new StringBuilder ();
        String token              = null;

        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            column_name.append ( token.toUpperCase().substring(0,1) );
            column_name.append ( token.substring( 1 ) );
        }

        max_col_name_length  = (column.length() > max_col_name_length)? column.length() : max_col_name_length;
        max_func_name_length = (token.length() > max_func_name_length)? token.length() : max_func_name_length;

        return column_name.toString();
    }

    public String formatFunctionName ( String column )
    {
        String token = formatColumnName ( column );

        return token.substring(0,1).toUpperCase() + token.substring ( 1 );
    }

    public String buildStrutsConfigEntry ( String portlet_name, String path_forward, String path_success, String bean_name, String form_name )
    {
        StringBuffer buffer = new StringBuffer();

        return buffer.toString();
    }

	public void setProperties ( HashMap <Object, Object> map )
	{
		generator_properties = map;
	}

	public Object getProperty ( Object key )
	{
		return generator_properties.get ( key );
	}

	public void setProperty ( Object key, Object value )
	{
		generator_properties.put ( key, value );
	}

	public void addColumn ( Column column )
	{
		all_columns.add ( column );

        if ( column.isPrimaryKey() )
        {
            pk_columns.add ( column );
        }
        else
        {
            data_columns.add ( column );
        }
	}

    public String getQuerySelectAll()
    {
        return ( null != query_select_all )? query_select_all.toString() : "";
    }

    public String getQuerySelect()
    {
        return ( null != query_select )? query_select.toString() : "";
    }

    public String getQueryInsert()
    {
        return ( null != query_insert )? query_insert.toString() : "";
    }

    public String getQueryUpdate()
    {
        return ( null != query_update )? query_update.toString() : "";
    }

    public String getQueryDelete()
    {
        return ( null != query_delete )? query_delete.toString() : "";
    }
};
