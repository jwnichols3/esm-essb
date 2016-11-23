package tools.struts;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.StringTokenizer;

import tools.databases.Column;
import tools.databases.Table;

public class Common
{
    private static Properties struts_properties   = null;
    private static Properties common_properties   = null;
    private static Properties database_properties = null;

    /**
     * Retrieves and populates a Properties object with all the properties 
     * necessary to configure the portlet generator
     *
     * @return a Properties object with all the properties necessary to configure 
     *         the portlet generator
     */
    public static Properties getStrutsToolsProperties() throws IOException
    {
        if ( null == struts_properties )
        {
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "StrutsTools.properties" );
            struts_properties = new Properties();
            struts_properties.load ( is );
        }

        return struts_properties;
    }

    /**
     * Retrieves and populates a Properties object with all the properties 
     * common to both the portlet generator and the portlet
     *
     * @return a Properties object with all the properties common to both the 
     *         the portlet generator and the portlet
     */
    public static Properties getCommonProperties() throws IOException
    {
        if ( null == common_properties )
        {
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "Common.properties" );
            common_properties = new Properties();
            common_properties.load ( is );
        }

        return common_properties;
    }

    /**
     * Retrieves and populates a Properties object with all the portlet-specific 
     * database properties
     *
     * @return a Properties object with all the portlet-specific database properties 
     */
    public static Properties getDatabaseProperties() throws IOException
    {
        if ( null == database_properties )
        {
            ClassLoader cl      = new URLClassLoader ( new URL[0] );
            InputStream is      = cl.getResourceAsStream ( "content/databases.properties" );
            database_properties = new Properties();
            database_properties.load ( is );
        }

        return database_properties;
    }

    /**
     * Formats the name of the column into the property format to be used in
     * the Struts forms.  The name "SomeColumn" will be changed to "someColumn"
     * 
     * @param column  The name of the column
     * @return the formatted column name
     */
    public static String formatPropertyName ( Column column )
    {
        String column_name = column.getName();
        String token       = formatColumnName ( column_name );

        return token.substring(0,1).toLowerCase() + token.substring ( 1 );
    }


    /**
     * Formats the name of the column into the property format to be used in
     * the Struts forms.  The name "SomeColumn" will be changed to "someColumn"
     * 
     * @param column_name  The name of the column
     * @return the formatted column name
     */
    public static String formatPropertyName ( String column_name )
    {
        String token = formatColumnName ( column_name );

        return token.substring(0,1).toLowerCase() + token.substring ( 1 );
    }

    /**
     * Removes underscores and formats the column name into the standard
     * format used by Struts forms.  The name "some_table_column" will be
     * formatted into "someTableColumn"
     * 
     * @param column The name of the column
     * @return the formatted column name
     */
    public static String formatColumnName ( Column column)
    {
        String column_name            = column.getName();
        StringTokenizer tokens        = new StringTokenizer ( column_name, "_" );
        StringBuilder new_column_name = new StringBuilder ();
        String token                  = null;

        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            new_column_name.append ( token.toUpperCase().substring(0,1) );
            new_column_name.append ( token.substring( 1 ) );
        }

        return new_column_name.toString();
    }

    /**
     * Removes underscores and formats the column name into the standard
     * format used by Struts forms.  The name "some_table_column" will be
     * formatted into "someTableColumn"
     * 
     * @param column_name The name of the column
     * @return the formatted column name
     */
    public static String formatColumnName ( String column_name )
    {
        StringTokenizer tokens        = new StringTokenizer ( column_name, "_" );
        StringBuilder new_column_name = new StringBuilder ();
        String token                  = null;

        while ( tokens.hasMoreTokens() )
        {
            token = tokens.nextToken();
            new_column_name.append ( token.toUpperCase().substring(0,1) );
            new_column_name.append ( token.substring( 1 ) );
        }

        return new_column_name.toString();
    }

    /**
     * Formats the column name into the suffix of the standard Struts
     * getter/setter functions.  The column name "some_column_name" will
     * be formatted as "SomeColumnName".
     * 
     * @param column The name of the column
     * @return The formatted column name
     */
    public static String formatFunctionName ( Column column )
    {
        String column_name = column.getName();
        String token       = formatColumnName ( column_name );

        return token.substring(0,1).toUpperCase() + token.substring ( 1 );
    }


    /**
     * Formats the column name into the suffix of the standard Struts
     * getter/setter functions.  The column name "some_column_name" will
     * be formatted as "SomeColumnName".
     * 
     * @param column_name  The name of the column
     * @return The formatted column name
     */
    public static String formatFunctionName ( String column_name )
    {
        String token = formatColumnName ( column_name );

        return token.substring(0,1).toUpperCase() + token.substring ( 1 );
    }
    
    /**
     * Formats the name of a table into the the standard format for "Add" forms.
     *
     * @param tabl The table whose name to format
     * @return the name of a table into the the standard format for "Add" forms.
     */
    public static String formatAddFormName ( Table table )
    {
        String table_name  = table.getName();
        StringBuilder data = new StringBuilder ( formatPropertyName ( table_name ) );
        data.append ( "AddForm" );

        return data.toString();
    }

    /**
     * Formats the name of a table into the the standard format for "Add" forms.
     *
     * @param table_name The name of the table
     * @return the name of a table into the the standard format for "Add" forms.
     */
    public static String formatAddFormName ( String table_name )
    {
        StringBuilder data = new StringBuilder ( formatPropertyName ( table_name ) );
        data.append ( "AddForm" );

        return data.toString();
    }

    /**
     * Formats the name of a table into the the standard format for "Edit" forms.
     *
     * @param tabl The table whose name to format
     * @return the name of a table into the the standard format for "Edit" forms.
     */
    public static String formatEditFormName ( Table table )
    {
        String table_name  = table.getName();
        StringBuilder data = new StringBuilder ( formatPropertyName ( table_name ) );
        data.append ( "EditForm" );

        return data.toString();
    }

    /**
     * Formats the name of a table into the the standard format for "Edit" forms.
     *
     * @param table_name The name of the table
     * @return the name of a table into the the standard format for "Edit" forms.
     */
    public static String formatEditFormName ( String table_name )
    {
        StringBuilder data = new StringBuilder ( formatPropertyName ( table_name ) );
        data.append ( "EditForm" );

        return data.toString();
    }


	/**
	 * Forms a String object to the desired length by adding whitespace to the end.
	 *
	 * @param data the String object to format
	 * @param length the desired length of the formatted string
	 * @return the formatted String
	 */
	public static String formatStringToLength ( String data, int length )
	{
		StringBuilder builder = new StringBuilder ( data );
		for ( int counter = data.length(); counter< length; counter++ )
		{
			builder.append ( " " );
		}

		return builder.toString();
	}
};
