package tools.struts.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import tools.struts.Common;
import tools.databases.Table;

public class Toolkit
{
    private Properties common_properties = null;
    private Properties properties        = null;
    private String output_directory      = null;
    private String package_name          = null;


    private String db_driver             = null;
    private String db_connection_url     = null;
    private String db_username           = null;
    private String db_password           = null;

    private Vector <Table> all_tables    = new Vector <Table> ();

    public Toolkit () throws IOException
    {
        readProperties();
    }

    public void addTable ( Table new_table )
    {
        all_tables.add ( new_table );
    }

    public void BuildFile() throws IOException
    {
        StringBuilder file         = new StringBuilder();
        String temp_string         = null;
        Table table                = null;
        int max_length_table_names = 0;
        int counter                = 0;

        for ( counter = 0; counter < all_tables.size(); counter++ )
        {
            temp_string = all_tables.elementAt( counter ).getName();

            max_length_table_names = ( temp_string.length() > max_length_table_names )? temp_string.length() : max_length_table_names;
        }

        file.append ( "package "+package_name+";\n" );
        file.append ( "\n" );
        file.append ( "import java.sql.Connection;\n" );
        file.append ( "import java.sql.DriverManager;\n" );
        file.append ( "import java.sql.PreparedStatement;\n" );
        file.append ( "import java.sql.ResultSet;\n" );
        file.append ( "import java.sql.SQLException;\n" );
        file.append ( "import java.sql.Statement;\n" );
        file.append ( "\n" );
        file.append ( "public class Toolkit\n" );
        file.append ( "{\n" );
        file.append ( "    private static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException\n" );
        file.append ( "    {\n" );
        file.append ( "        if ( null == con )\n" );
        file.append ( "        {\n" );
        file.append ( "            Class.forName ( \""+db_driver+"\" );\n" );
        file.append ( "            con " );
        file.append ( Common.formatStringToLength ( "", max_length_table_names ) );
        file.append ( "= DriverManager.getConnection ( \""+db_connection_url+"\", \""+db_username+"\", \""+db_password+"\" );\n" );
        for ( counter = 0; counter < all_tables.size(); counter++ )
        {
            table       = all_tables.elementAt(counter);
            temp_string = table.getName();

            file.append ( "            ps_" );
            file.append ( temp_string );
            file.append ( Common.formatStringToLength ( "", max_length_table_names-temp_string.length() ) );
            file.append ( " = con.prepareStatement ( \"" );
            file.append ( table.getQuerySelectAll() );
            file.append ( "\" );\n" );
        }
        file.append ( "        }\n" );
        file.append ( "\n" );
        file.append ( "        return con;\n" );
        file.append ( "    }\n" );
        file.append ( "\n" );
        file.append ( "    public static Statement createDatabaseStatement() throws ClassNotFoundException, SQLException\n" );
        file.append ( "    {\n" );
        file.append ( "        return getDatabaseConnection().createStatement();\n" );
        file.append ( "    }\n" );
        file.append ( "\n" );
        file.append ( "    public static PreparedStatement createPreparedStatement ( String query ) throws ClassNotFoundException, SQLException\n" );
        file.append ( "    {\n" );
        file.append ( "        return con.prepareStatement ( query );\n" );
        file.append ( "    }\n" );
        file.append ( "\n" );
        for ( counter = 0; counter < all_tables.size(); counter++ )
        {
            table       = all_tables.elementAt(counter);
            temp_string = table.getName();
            file.append ( "    public static ResultSet selectAllFrom" );
            file.append ( Common.formatFunctionName ( temp_string ) );
            file.append ( "() throws SQLException, ClassNotFoundException\n" );
            file.append ( "    {\n" );
            file.append ( "        if ( null == ps_" );
            file.append ( temp_string );
            file.append ( " )\n" );
            file.append ( "        {\n" );
            file.append ( "            Toolkit.getDatabaseConnection();\n" );
            file.append ( "        }\n" );
            file.append ( "\n" );
            file.append ( "        return ps_" );
            file.append ( temp_string );
            file.append ( ".executeQuery();\n" );
            file.append ( "    }\n\n" );
        }

        file.append ( "    private static Connection con " );
        file.append ( Common.formatStringToLength ( "", max_length_table_names+6) );
        file.append ( " = null;\n" );
        for ( counter = 0; counter < all_tables.size(); counter++ )
        {
            temp_string = all_tables.elementAt(counter).getName();

            file.append ( "    private static PreparedStatement ps_" );
            file.append ( temp_string );
            file.append ( Common.formatStringToLength ( "", max_length_table_names-temp_string.length() ) );
            file.append ( " = null;\n" );
        }
        file.append ( "}\n" );

        FileOutputStream outfile = new FileOutputStream ( output_directory + "/Toolkit.java" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }


    private void readProperties () throws IOException
    {
        if ( null == properties )
        {
			properties = Common.getStrutsToolsProperties();

            package_name      = properties.getProperty ( "directory.toolkit" );
            output_directory  = properties.getProperty ( "directory.toolkit.out" );
        }

        if ( null == common_properties )
        {
			common_properties = Common.getCommonProperties();

            db_driver         = common_properties.getProperty ( "database.driver" );
            db_connection_url = common_properties.getProperty ( "database.connection_url" );
            db_username       = common_properties.getProperty ( "database.username" );
            db_password       = common_properties.getProperty ( "database.password" );
        }
    }
}
