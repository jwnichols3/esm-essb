package tools.struts.view;

import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import tools.struts.Common;

public class BuildStrutsActionForm
{
    private static Connection con               = null;
    private static Statement stmt               = null;
    private static String db_driver             = null;
    private static String db_connection_url     = null;
    private static String db_username           = null;
    private static String db_password           = null;
    
    private static Vector <String> tables       = new Vector <String> ();
    private static int max_col_name_length      = 0;
    private static int max_func_name_length     = 0;
    private static Properties common_properties = null;
    private static Properties properties        = null;
    private static String package_name          = null;
    private static String output_directory      = null;

    public static void main ( String args[] ) throws SQLException, ClassNotFoundException, IOException
    {
        System.out.println ( "Building struts action form beans..." );

        readProperties();

        getDatabaseConnection();

        if ( null != con )
        {
            getTables();

            Enumeration <String> e = tables.elements();
            while ( e.hasMoreElements() )
            {
                BuildFormBean ( e.nextElement() );
            }
        }
        else
        {
            System.out.println ( "Unsuccessful at obtaining database connection" );
        }
    }

    private static void readProperties() throws IOException
    {
        if ( null == properties ) 
        {
			properties       = Common.getStrutsToolsProperties();
            package_name     = properties.getProperty ( "directory.forms" );
            output_directory = properties.getProperty ( "directory.forms.out" );
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

    private static void getDatabaseConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName ( db_driver );
        con  = DriverManager.getConnection( db_connection_url, db_username, db_password );
        stmt = con.createStatement();
    }

    private static void getTables() throws SQLException, IOException
    {
        Statement stmt     = con.createStatement();
        ResultSet results  = stmt.executeQuery ( "SHOW tables;" );
        String temp_string = null;
        while ( results.next() )
        {
            temp_string = results.getString ( 1 );
            if ( temp_string.indexOf ( "index_" ) < 0 )
            {
                tables.add ( results.getString ( 1 ) );
            }
        }
    }

    public static void BuildFormBean ( String table ) throws SQLException, IOException, ClassNotFoundException
    {
        readProperties();

        getDatabaseConnection();

        String token               = formatColumnName ( table );
        StringBuilder classname    = new StringBuilder ( token.toUpperCase().substring(0,1) );
        classname.append ( token.substring ( 1 ) );
        StringBuilder query        = new StringBuilder ( "SELECT * FROM " );
        query.append ( table );
        ResultSet results          = stmt.executeQuery ( query.toString() );
        ResultSetMetaData rsmd     = results.getMetaData();
        String temp_string         = null;
        String col_name            = null;
        Vector <String> col_names  = new Vector <String>();
        Vector <String> func_names = new Vector <String>();
        Enumeration <String> e     = null;
        Hashtable <String, String> function_names   = new Hashtable <String, String> ();
        int num_columns            = rsmd.getColumnCount();


        //  Relate token1_token2 as token1Token2
        for ( int counter = 1; counter <= num_columns; counter++ )
        {
            temp_string = rsmd.getColumnName ( counter );
            col_name    = formatColumnName ( temp_string );
            col_names.add ( temp_string );
            func_names.add ( col_name );
            function_names.put ( temp_string, col_name );
        }

        StringBuilder file        = new StringBuilder();
        //  Set up common header for all form beans
        file.append ( "package " );
        file.append ( package_name );
        file.append ( ";\n\n" );
        file.append ( "import java.io.FileOutputStream;\n" );
        file.append ( "import java.io.IOException;\n\n" );
        file.append ( "import javax.servlet.http.HttpServletRequest;\n\n" );
        file.append ( "import org.apache.struts.action.ActionErrors;\n" );
        file.append ( "import org.apache.struts.action.ActionForm;\n" );
        file.append ( "import org.apache.struts.action.ActionMapping; \n" );
        file.append ( "import org.apache.struts.action.ActionMessage;\n\n" );
        file.append ( "public class " );
        file.append ( classname.toString() );
        file.append ( "XXX___EXTRA___XXX extends ActionForm\n" );
        file.append ( "{\n" );

        //  Create a blank validate function
        file.append ( "\tpublic ActionErrors validate( ActionMapping mapping, HttpServletRequest req) \n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tActionErrors errors = new ActionErrors();\n\n" );
        file.append ( "\t\treturn errors;\n" );
        file.append ( "\t}\n\n" );

        //  Create a default reset function
        file.append ( "\tpublic void reset(ActionMapping mapping, HttpServletRequest req)\n" );
        file.append ( "\t{\n" );
            e = col_names.elements();
            while ( e.hasMoreElements() )
            {
                token = e.nextElement();
                file.append ( "\t\t" );
                file.append ( formatStringToLength ( token, max_col_name_length ) );
                file.append ( " = \"\";\n" );
            }

        file.append ( "\t}\n\n" );

        //  Create getter/setter functions
        e = col_names.elements();
        while ( e.hasMoreElements() )
        {
            token       = e.nextElement();
            temp_string = function_names.get ( token );
            file.append ( "\tpublic String get" );
            file.append ( temp_string );
            file.append ( "()\n" );
            file.append ( "\t{\n" );
            file.append ( "\t\treturn " );
            file.append ( token );
            file.append ( ";\n" );
            file.append ( "\t}\n\n" );

            file.append ( "\tpublic void set" );
            file.append ( temp_string );
            file.append ( " ( String param )\n" );
            file.append ( "\t{\n" );
            file.append ( "\t\t" );
            file.append ( token );
            file.append ( " = param;\n" );
            file.append ( "\t}\n\n" );
        }

        //  Create Dump function
        file.append ( "\tpublic void Dump ( String filename )\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tStringBuffer buffer      = new StringBuffer();\n" );
        file.append ( "\t\tFileOutputStream outfile = null;\n\n" );
        file.append ( "\t\ttry\n" );
        file.append ( "\t\t{\n" );
        file.append ( "\t\t\tbuffer.append ( \"\" );\n" );
        file.append ( "\t\t\toutfile = new FileOutputStream ( \"test.out\" );\n" );
        file.append ( "\t\t\t\toutfile.write ( \"testing\\n\".getBytes() );\n" );
        file.append ( "\t\t\toutfile.close();\n" );
        file.append ( "\t\t}\n" );
        file.append ( "\t\tcatch ( IOException exception )\n" );
        file.append ( "\t\t{\n" );
        file.append ( "\t\t\texception.printStackTrace();\n" );
        file.append ( "\t\t}\n" );
        file.append ( "\t}\n" );


        //  Create member variables
        file.append ( "\t//  Member variables\n" );
            e = col_names.elements();
            while ( e.hasMoreElements() )
            {
                token = e.nextElement();
                file.append ( "\tprivate String " );
                file.append ( formatStringToLength ( token, max_col_name_length ) );
                file.append ( " = \"\";\n" );
            }
        file.append ( "\n" );

        //  Set up common footer for all form beans
        file.append ( "}\n" );


        FileOutputStream outfile = null;
        outfile = new FileOutputStream ( output_directory + "/" + classname.toString() + "Add.java" );
            outfile.write ( file.toString().replaceAll ( "XXX___EXTRA___XXX", "Add" ).getBytes() );
        outfile.close();

        outfile = new FileOutputStream ( output_directory + "/" + classname.toString() + "Edit.java" );
            outfile.write ( file.toString().replaceAll ( "XXX___EXTRA___XXX", "Edit" ).getBytes() );
        outfile.close();

        stmt.close();
        con.close();
    }

    private static String formatStringToLength ( String string, int length )
    {
        StringBuilder s = new StringBuilder ( string ); while ( s.length() < length )
        {
            s.append ( " " );
        }

        return s.toString();
    }

    private static String formatColumnName ( String column )
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
};
