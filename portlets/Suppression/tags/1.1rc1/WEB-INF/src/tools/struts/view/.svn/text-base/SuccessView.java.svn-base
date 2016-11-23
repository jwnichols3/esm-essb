package tools.struts.view;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import tools.databases.Column;
import tools.databases.Table;

public class SuccessView
{
	private static Properties form_properties   = null;
    private static Properties common_properties = null;
    private static String output_directory      = null;
    private static String prop_header           = null;

    private Table table                         = null;

    public static void main ( String args[] ) throws IOException, SQLException, ClassNotFoundException
    {
        Table t = new Table ( "real_estate", "tenants" );
        t.initializeFromDatabase();

        SuccessView htmlview = new SuccessView ( t );

        htmlview.BuildFile ( "real_estate_manager", "tenants_view.jsp" );
    }

    public SuccessView ( Table new_table ) throws IOException
    {
        table = new_table;

        readFormProperties( table.getDBName(), table.getName() );
    }

    public void BuildFile ( String portlet_name, String filename ) throws IOException
    {
        String columns_to_display    = form_properties.getProperty ( prop_header + "table.view_columns" );
        StringTokenizer tokenizer    = (null != columns_to_display)? new StringTokenizer ( columns_to_display, "," ) : null;
        String temp_string           = null;
        StringBuilder file           = new StringBuilder ();
        Vector <Column> pk_columns   = table.getPrimaryKeyColumns();
        Vector <String> show_columns = new Vector <String> ();
        Column column                = null;
        int num_columns              = 0;
        int counter                  = 0;
        int column_width             = 0;

        if ( null != tokenizer )
        {
            while ( tokenizer.hasMoreTokens() )
            {
                temp_string = tokenizer.nextToken().trim();

                show_columns.add ( temp_string );

                num_columns++;
            }
        }
        else
        {
            num_columns = 0;
        }

        if ( num_columns <= 0 )
        {
            num_columns = pk_columns.size();

            for ( counter = 0; counter < num_columns; counter++ )
            {
                show_columns.add  ( pk_columns.elementAt(counter).getName() );
            }
            
        }

        if ( 0 == num_columns )
        {
            return;
        }

        column_width = 100 / num_columns;


        file.append ( "<%@ include file=\"/html/portlet/" + portlet_name + "/init.jsp\" %>\n" );
        file.append ( "<%\n" );
        file.append ( "    Map userInfo        = (Map)renderRequest.getAttribute(PortletRequest.USER_INFO);\n" );
        file.append ( "    String username     = (String) userInfo.get ( \"liferay.user.id\" );\n" );
        file.append ( "    java.util.Date date = new java.util.Date();\n\n" );
        file.append ( "    String widths[] =\n" );
        file.append ( "    {\n" );
        for ( counter = 0; counter < num_columns-1; counter++ )
        {
            file.append ( "        \"" );
            file.append ( column_width );
            file.append ( "%\",\n" );
        }
        file.append ( "        \"*\"\n" );
        file.append ( "    };\n\n" );
        file.append ( "    Statement stmt    = Toolkit.createDatabaseStatement();\n" );
        file.append ( "    ResultSet results = stmt.executeQuery ( \"SELECT " );
        for ( counter = 0; counter < num_columns; counter++ )
        {
            file.append ( show_columns.elementAt ( counter ) );
            file.append ( ", " );
        }
        for ( counter = 0; counter < pk_columns.size()-1; counter++ )
        {
            file.append ( pk_columns.elementAt( counter ).getName() );
            file.append ( ", " );
        }
        file.append ( pk_columns.elementAt( counter ).getName() );
        file.append ( " FROM " );
        file.append ( table.getName() );
        file.append ( " ORDER BY " );
        for ( counter = 0; counter < num_columns-1; counter++ )
        {
            file.append ( show_columns.elementAt ( counter ) );
            file.append ( ", " );
        }
        file.append ( show_columns.elementAt ( counter ) );
        file.append ( "\" );\n" );
        file.append ( " %>\n" );
        file.append ( "<font class=\"portlet-font\" style=\"font-size: x-small;\">\n" );
        file.append ( "    <br />\n" );
        file.append ( "    <br />\n" );
        file.append ( "    <html:link page=\"/"+portlet_name+"/view\"><font class=\"portlet-font\" style=\"font-size: x-small;\">Home<font></html:link>\n" );
        file.append ( "    &#8594;\n" );
        file.append ( "    <b><bean:message bundle=\"databases\" key=\"databases."+table.getDBName()+"."+table.getName()+".table.name\"/></b>\n" );
        file.append ( "    <br>\n" );
        file.append ( "    <br>\n" );
        file.append ( "    Successfully added a new <bean:message bundle=\"databases\" key=\"databases." );
        file.append ( table.getDBName() );
        file.append ( "." );
        file.append ( table.getName() );
        file.append ( ".table.data_name\"/>" );
        file.append ( "    <br>\n" );
        file.append ( "    <br>\n" );
        file.append ( "    <html:link action=\"/" );
        file.append ( portlet_name );
        file.append ( "/" );
        file.append ( table.getName() );
        file.append ( "_add\">Add a new <bean:message bundle=\"databases\" key=\"databases." );
        file.append ( table.getDBName() );
        file.append ( "." );
        file.append ( table.getName() );
        file.append ( ".table.data_name\"/>" );
        file.append ( "</html:link>\n" );
        file.append ( "    <br>\n" );
        file.append ( "    <br>\n" );
        file.append ( "    <table cellspacing=\"0\" cellpadding=\"2\" id=\"header\" border=\"0\" width=\"100%\">\n" );
        file.append ( "        " );
        for ( counter = 0; counter < num_columns; counter++ )
        {
            file.append ( "<col width=\"<%= widths[" );
            file.append ( counter );
            file.append ( "] %>\">" );
        }
        file.append ( "\n" );
        file.append ( "        <tr>\n" );
        for ( counter = 0; counter < num_columns; counter++ )
        {
            file.append ( "            <th><font class=\"portlet-font\" style=\"font-size: x-small;\"><bean:message bundle=\"databases\" key=\"" );
            file.append ( prop_header );
            file.append ( show_columns.elementAt ( counter ) );
            file.append ( ".name\"/></font></th>\n" );
        }
        file.append ( "        </tr>\n" );

        file.append ( "    </table>\n" );
        file.append ( "    <div style=\"overflow: auto; width: 100%; height: 300; border-right: 1px gray solid; border-left: 1px gray solid; border-bottom: 1px gray solid; border-top: 1px gray solid; padding:0px; margin: 0px\">\n" );
        file.append ( "        <table border=\"1\" width=\"100%\">\n" );
        file.append ( "            " );
        for ( counter = 0; counter < num_columns; counter++ )
        {
            file.append ( "<col width=\"<%= widths[" );
            file.append ( counter );
            file.append ( "] %>\">" );
        }
        file.append ( "\n" );
        file.append ( "        <%\n" );
        file.append ( "            StringBuffer buffer = null;\n" );
        file.append ( "            while ( results.next() )\n" );
        file.append ( "            {\n" );
        file.append ( "                buffer = new StringBuffer();\n" );
        for ( counter = 0; counter < pk_columns.size()-1; counter++ )
        {
            column = pk_columns.elementAt ( counter );
            file.append ( "                buffer.append ( \"" );
            file.append ( column.getName() );
            file.append ( "=\" );\n" );
            file.append ( "                buffer.append ( results." );
            file.append ( column.generatePSGetValueCall() );
            file.append ( " ( " );
            file.append ( show_columns.size()+counter+1 );
            file.append ( " ) )\n" );
            file.append ( "                buffer.append ( \"?\" );\n" );
        }
        column = pk_columns.elementAt ( counter );
        if ( null != column )
        {
            file.append ( "                buffer.append ( \"" );
            file.append ( column.getName() );
            file.append ( "=\" );\n" );
            file.append ( "                buffer.append ( results." );
            file.append ( column.generatePSGetValueCall() );
            file.append ( " ( " );
            file.append ( show_columns.size()+counter+1 );
            file.append ( " ) );\n" );
        }
        file.append ( "                %>\n" );
        file.append ( "            <tr>\n" );

        for ( counter = 1; counter <= num_columns; counter++ )
        {
            file.append ( "                <td><font class=\"portlet-font\" style=\"font-size: x-small;\"><html:link action='<%= \"/" );
            file.append ( portlet_name );
            file.append ( "/" );
            file.append ( table.getName() );
            file.append ( "_edit?\" +  buffer.toString() %>'>" );
            file.append ( "<%= results.getString ( " );
            file.append ( counter );
            file.append ( " ) %></html:link></font></td>\n" );
        }
        file.append ( "            </tr>\n" );
        file.append ( "               <%\n" );
        file.append ( "            }\n" );
        file.append ( "         %>\n" );
        file.append ( "        </table>\n" );
        file.append ( "    </div>\n" );
        file.append ( "</font>\n" );

        FileOutputStream outfile = new FileOutputStream ( output_directory + "/" + filename );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }

    private void readFormProperties ( String database_name, String table_name ) throws IOException
	{
		MessageFormat fp  = new MessageFormat ( "databases.{0}.{1}." );
        String args[]     = new String[2];
        args[0] = database_name;
        args[1] = table_name;
		prop_header       = fp.format ( args );

		if ( null == SuccessView.form_properties )
		{
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "content/databases.properties" );
            SuccessView.form_properties   = new Properties();
            SuccessView.form_properties.load ( is );
		}

        if ( null == SuccessView.common_properties )
        {
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "StrutsTools.properties" );
            common_properties = new Properties();
            common_properties.load ( is );

            output_directory  = common_properties.getProperty ( "directory.html.forms.out" );
        }
	}

};
