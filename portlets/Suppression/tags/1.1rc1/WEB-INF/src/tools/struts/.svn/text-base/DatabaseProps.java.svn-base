package tools.struts;

import java.io.*;
import java.util.*;

import tools.databases.Column;
import tools.databases.Database;
import tools.databases.Table;
import tools.struts.Common;

public class DatabaseProps
{
    private static Properties common_properties   = null;
    private static Properties properties          = null;
    private static String db_name                 = null;
    private static String db_driver               = null;
    private static String db_connection_url       = null;
    private static String db_username             = null;
    private static String db_password             = null;
    private static String portlet_name            = null;
    private static String struts_template         = null;
    private static String output_directory        = null;
    private static String directory_forms         = null;
    private static String directory_actions       = null;
    
    public static String getDBUsername()
    {
        return db_username;
    }
    
    public static String getDBPassword()
    {
        return db_password;
    }
    
    public static String getDBConnectionURL()
    {
        return db_connection_url;
    }
    
    public static String getDBName()
    {
        return db_name;
    }

    public static String getPortletName()
    {
        return portlet_name;
    }
    
    public static String getOutputDirectory()
    {
        return output_directory;
    }
    
    public static String getDirectoryForms()
    {
        return directory_forms;
    }
    
    public static String getDirectoryActions()
    {
        return directory_actions;
    }
    
    private Vector <Table> all_tables             = null;

    /**
     * The main function is for debugging and test purposes only
     */
    public static void main ( String args[] )
    {
    }

    /**
     * Constructor that takes a Database object that represents the database of interest.
     */
    public DatabaseProps ( Database database )
    {
        all_tables = database.getTables();
    }

    /**
     * Constructor that takes all the Table objects that represent the tables of the database
     */
    public DatabaseProps ( Vector <Table> tables )
    {
        all_tables = tables;
    }

    /**
     * Create the database properties file
     */
    public void BuildFile () throws IOException
    {
        StringBuilder file          = new StringBuilder();
        Column column               = null;
        Table table                 = null;
        Vector <Column> all_columns = null;

        for ( int counter = 0; counter < all_tables.size(); counter++ )
        {
            table       = all_tables.elementAt ( counter );
            all_columns = table.getAllColumns();

            String header = "databases." + table.getDBName() + "." + table.getName() + ".";

            file.append ( "#########################################################################################\n" );
            file.append ( "#  Database properties for the table '" );
            file.append ( table.getName() );
            file.append ( "'\n" );
            file.append ( "#########################################################################################\n" );
            file.append ( "#   singular, lower-case form of the name of the data object that this table represents\n" );
            file.append ( header );
            file.append ( "table.data_name=" );
            file.append ( table.getName() );
            file.append ( "\n\n" );
            file.append ( "#   the capitalized, plural form of the name of the data object that this table represents\n" );
            file.append ( header );
            file.append ( "table.name=" );
            file.append ( Column.formatFunctionName ( table.getName() ) );
            file.append ( "\n\n" );
            file.append ( "#   The names of the columns that should show up in the default HTML view.  If no columns\n" );
            file.append ( "#   are provided, then the default is to show all the primary key columns of the table\n" );
            file.append ( "#" );
            file.append ( header );
            file.append ( "table.view_columns=" );
            file.append ( "#   Begin listing attributes for each column of this table\n" );
            for ( int c = 0; c < all_columns.size(); c++ )
            {
                column = all_columns.elementAt ( c );
                file.append ( header );
                file.append ( column.getName() );
                file.append ( ".name=" );
                file.append ( column.getName() );
                file.append ( "\n" );
                file.append ( header );
                file.append ( column.getName() );
                file.append ( ".visible=true\n" );
            }
            file.append ( "\n\n" );
        }

        FileOutputStream outfile = null;
        outfile = new FileOutputStream ( "WEB-INF/src/content/databases.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/src/content/databases_en.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/src/content/databases_ja.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/src/content/databases_zh.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/classes/content/databases.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/classes/content/databases_en.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/classes/content/databases_ja.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
        outfile = new FileOutputStream ( "WEB-INF/classes/content/databases_zh.properties" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }

    /**
     * Retrieve the user-defined portlet-specific properties
     */
    @SuppressWarnings("unused")
    private static void readProperties() throws IOException
    {
        if ( null == properties ) 
        {
            properties        = Common.getStrutsToolsProperties();

            struts_template   = properties.getProperty ( "struts.config.template" );
            output_directory  = properties.getProperty ( "directory.struts-config.out" );
            directory_forms   = properties.getProperty ( "directory.forms" );
            directory_actions = properties.getProperty ( "directory.actions" );

            FileInputStream infile = null;
            byte file_contents[]   = null;
            
            infile            = new FileInputStream ( struts_template );
            file_contents     = new byte[infile.available()];
            infile.read ( file_contents );
            infile.close();
            struts_template   = new String ( file_contents );
            struts_template   = struts_template.replaceAll ( "\r", "" );
        }
        if ( null == common_properties )
        {
            common_properties = Common.getCommonProperties();

            db_driver         = common_properties.getProperty ( "database.driver" );
            db_connection_url = common_properties.getProperty ( "database.connection_url" );
            db_username       = common_properties.getProperty ( "database.username" );
            db_password       = common_properties.getProperty ( "database.password" );
            portlet_name      = common_properties.getProperty ( "portlet.name" );
        }
    }
    
    public String getDBDriver()
    {
        return db_driver;
    }
}
