package tools.struts.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import tools.struts.Common;
import tools.databases.Column;
import tools.databases.Table;

public class FormBean
{
    private Table table                         = null;
    private int max_col_name_length             = 0;

    private static Properties properties        = null;
    private static Properties common_properties = null;
    private static String package_name          = null;
    private static String output_directory      = null;


    /**
     * The main function - used only for debugging purposes.
     */
    public static void main ( String args[] )
    {
    }

	/**
	 * This object creates an two form beans for each table in the database - an "Add" version and an "Edit" version.
	 *
	 * @param new_table The table to create form beans for
	 */
    public FormBean ( Table new_table )
    {
        table = new_table;
    }

	/**
	 * Obtain the necessary portlet-specific properties.
	 */
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
        }
    }

	/**
	 * Constructs and saves the form bean files.  The "Add" and "Edit" versions of the form bean files will
	 * be created together.
	 */
    public void BuildFile () throws IOException
    {
        readProperties();

        String token               = Common.formatColumnName ( table.getName() );
        StringBuilder classname    = new StringBuilder ( token.toUpperCase().substring(0,1) );
        classname.append ( token.substring ( 1 ) );
        String temp_string         = null;
        String col_name            = null;
        Column column              = null;
		Vector <Column> columns    = table.getAllColumns();
        Vector <String> col_names  = new Vector <String>();
        Vector <String> func_names = new Vector <String>();
        Enumeration <String> e     = null;
        Hashtable <String, String> function_names   = new Hashtable <String, String> ();

		int num_columns            = columns.size();



        //  Relate token1_token2 as token1Token2
        for ( int counter = 1; counter <= num_columns; counter++ )
        {
			column      = columns.elementAt ( counter-1 );
			temp_string = column.getName();
            col_name    = Common.formatColumnName ( temp_string );
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
                file.append ( Common.formatStringToLength ( token, max_col_name_length ) );
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
                file.append ( Common.formatStringToLength ( token, max_col_name_length ) );
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
    }
};
