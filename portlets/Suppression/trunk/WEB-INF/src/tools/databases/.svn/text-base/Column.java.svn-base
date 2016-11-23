package tools.databases;

import java.sql.Types;
import java.util.HashMap;

import tools.struts.Common;
 

public class Column
{
    private String name                         = null;
	private String function_name                = null;
	private String property_name                = null;
    private String column_type_name             = null;
	private String friendly_name                = null;
	private String comment                      = null;
	private HashMap <Object, Object> properties = null;

	private boolean is_primary_key              = false;
	private boolean is_autoincrement            = false;
	private int length                          = 0;
	private int java_sql_type                   = java.sql.Types.VARCHAR;

    /**
     * Basic constructor.  Creates a new object to encapsulate the information about a
     * column in a database table.  The default is to assume the column is of type
     * java.sql.Types.CHAR, and is not a primary key.
     * 
     * @param new_name
     */
    public Column ( String new_name )
    {
    	setName ( new_name );
    }

    /**
     * Renames this column and property formats all the commonly used forms of the column name
     * 
     * @param new_name
     */
    public void setName ( String new_name )
    {
        name = new_name;

		formatName();
    }

    /**
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

	/**
	 * formats and stores the most commonly used versions of the name of this column 
	 */
	private void formatName()
	{
		function_name = formatFunctionName ( name );
		property_name = formatPropertyName ( name );
	}

	/**
	 * Formats a string to the desired length.  If the string is longer than the specified
	 * length, then no modifications are made
	 * 
	 * @param length the desired length
	 * @return the String formatted to the specified length
	 */
	public String formatNameToLength ( int length )
	{
		StringBuffer buffer = new StringBuffer ( name );
		while ( buffer.length() < length )
		{
			buffer.append ( " " );
		}

		return buffer.toString();
	}

	/**
	 * Sets the name of the column type.  
	 *
	 * @param data_type The String name of the data type of this column
	 */
    public void setColumnTypeName ( String data_type )
    {
        column_type_name = data_type;
    }

	/**
	 * Returns the name of the data type of this column
	 *
	 * @return the name of the the data type of this column
	 */
    public String getColumnTypeName()
    {
        return column_type_name;
    }

	/**
	 * Sets the default length that is used to format the column name for display purposes.
	 *
	 * @param new_length the default length of the column
	 */
	public void setLength ( String new_length )
	{
		length = Integer.parseInt ( new_length );
	}

	/**
	 * Sets the default length that is used to format the column name for display purposes.
	 *
	 * @param new_length the default length of the column
	 */
	public void setLength ( int new_length )
	{
		length = new_length;
	}

	/**
	 * Returns the default length that is used to format the column name for display purposes.
	 *
	 * @return the default length of the column
	 */
	public int getLength ()
	{
		return length;
	}

	/**
	 * Sets the user-defined easy-to-understand name for this column.
	 *
	 * @param new_friendly_name the user-defined easy-to-understand name for this column.
	 */
	public void setFriendlyName ( String new_friendly_name )
	{
		friendly_name = new_friendly_name;
	}

	/**
	 * Returns the user-defined easy-to-understand name for this column.
	 *
	 * @return the user-defined easy-to-understand name for this column.
	 */
	public String getFriendlyName ()
	{
		return ( null != friendly_name )? friendly_name : name;
	}

	/**
	 * Some database engines (like MySQL) allow comments to be associated with the columns.
	 * This sets the comment associated with this column
	 * 
	 * @param strColumnComment
	 */
	public void setComment ( String strColumnComment )
	{
		comment = strColumnComment;
	}

	/**
	 * Some database engines (like MySQL) allow comments to be associated with the columns.
	 * This returns the comment associated with this column
	 * 
	 * @return the comment associated with this column
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Retrieves the function form the name of this column to be used as a suffix for Struts
	 * getter/seter functions.  If the original column name was "some_column_name", the name
	 * will be formatted as "SomeFunctionName".
	 *  
	 * @return the formatted function name form of the column name
	 */
	public String getFunctionName()
	{
		return function_name;
	}

	/**
	 * Retrieves the property form the name of this column to be used as a suffix for Struts
	 * forms .  If the original column name was "some_column_name", the name will be formatted
	 * as "someFunctionName".
	 *  
	 * @return the formatted form property form of the column name
	 */
	public String getPropertyName()
	{
		return property_name;
	}

	/**
	 * Determines whether or not this column is an auto-increment type of column
	 *
	 * @param bIsAutoIncrement
	 */
	public void setIsAutoIncrement ( boolean bIsAutoIncrement )
	{
		is_autoincrement = bIsAutoIncrement;
	}

	/**
	 * Reterns whether or not this column is an auto-increment type of column
	 * 
	 * @return true if this column is an auto-increment type, false otherwise
	 */
	public boolean isAutoIncrement()
	{
		return is_autoincrement;
	}

	/**
	 * Determines whether or not this column is a primary key
	 * 
	 * @param param
	 */
	public void setIsPrimaryKey ( boolean param )
	{
		is_primary_key = param;
	}

	/**
	 * Returns whether or not this column is a primary key
	 * 
	 * @return true if the column is a primary key, false otherwise
	 */
	public boolean isPrimaryKey ()
	{
		return is_primary_key;
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
        return Common.formatPropertyName ( column_name );
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
        return Common.formatColumnName ( column_name );
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
        return Common.formatFunctionName ( column_name );
    }

	public void setProperties ( HashMap <Object, Object> map )
	{
		properties       = map;

		validateProperties();
	}

	private void validateProperties()
	{
		String token1    = null;
		
		//  Whether or not this column is a 
		token1           = (String) properties.get( "is_pk" );
		if ( (null != token1) && ( token1.equals ( "true" ) ) )
		{
			is_primary_key = true;
		}
		else
		{
			is_primary_key = false;
			properties.put ( "is_pk", "false" );
		}

		token1           = (String) properties.get( "autoincrement" );
		if ( (null != token1) && ( token1.equals ( "true" ) ) )
		{
			is_autoincrement = true;
		}
		else
		{
			is_autoincrement = false;
			properties.put ( "autoincrement", "false" );
		}

		//  Column type
	}

	public void setProperty ( Object key, Object value )
	{
		properties.put ( key, value );

		validateProperties();
	}

	public Object getProperty ( Object key )
	{
		return properties.get ( key );
	}

	public HashMap getProperties ()
	{
		return properties;
	}

	/**
	 * Sets the java.sql.Type value that represents the data type this column encapsulates.
	 *
	 * @param new_type the java.sql.Type value that represents the data type this column encapsulates
	 */
	public void setType ( int new_type )
	{
		java_sql_type = new_type;
	}

	/**
	 * Returns the java.sql.Type value that represents the data type this column encapsulates.
	 *
	 * @return the java.sql.Type value that represents the data type this column encapsulates
	 */
	public int getType()
	{
		return java_sql_type;
	}

    public boolean isColumnDataType()
    {
        return !(( Types.BIGINT  == java_sql_type ) || ( Types.DECIMAL  == java_sql_type ) || 
                 ( Types.DOUBLE  == java_sql_type ) || ( Types.FLOAT    == java_sql_type ) || 
                 ( Types.INTEGER == java_sql_type ) || ( Types.SMALLINT == java_sql_type ) ||
                 ( Types.TINYINT == java_sql_type ));
    }
    public String generatePSGetValueCall()
    {
        if      (( java_sql_type == Types.CHAR )     || ( java_sql_type == Types.VARCHAR ) ||
                 ( java_sql_type == Types.LONGVARCHAR ))
        {
            return "getString";
        }
        else if (( java_sql_type == Types.SMALLINT   ) || ( java_sql_type == Types.INTEGER ))
        {
            return "getInt   ";
        }
        else if  ( java_sql_type == Types.BIGINT )
        {
            return "getLong  ";
        }
        else if  ( java_sql_type == Types.FLOAT )
        {
            return "getFloat ";
        }
        else if  (( java_sql_type == Types.DOUBLE )  || ( java_sql_type == Types.REAL ))
        {
            return "getDouble";
        }
        else if  (( name.indexOf ( "is" ) == 0 ) || ( name.indexOf ( "has" ) == 0 ))
        {
            return "getInt   ";
        }
        else
        {
            return "getString";
        }
    }
    public String generatePSSetValueCall()
    {
        if      (( java_sql_type == Types.CHAR )     || ( java_sql_type == Types.VARCHAR ) ||
                 ( java_sql_type == Types.LONGVARCHAR ))
        {
            return "setString";
        }
        else if (( java_sql_type == Types.SMALLINT   ) || ( java_sql_type == Types.INTEGER ))
        {
            return "setInt   ";
        }
        else if  ( java_sql_type == Types.BIGINT )
        {
            return "setLong  ";
        }
        else if  ( java_sql_type == Types.FLOAT )
        {
            return "setFloat ";
        }
        else if  (( java_sql_type == Types.DOUBLE )  || ( java_sql_type == Types.REAL ))
        {
            return "setDouble";
        }
        else if  (( name.indexOf ( "is" ) == 0 ) || ( name.indexOf ( "has" ) == 0 ))
        {
            return "setInt   ";
        }
        else
        {
            return "setString";
        }
    }
    public String generateDatabaseRetrieveType ( int index )
    {
        StringBuffer buffer = new StringBuffer ();
        if      (( java_sql_type == Types.CHAR )     || ( java_sql_type == Types.VARCHAR ) ||
                 ( java_sql_type == Types.LONGVARCHAR ))
        {
            buffer.append ( "results.getString ( " );
            buffer.append ( index );
            buffer.append ( " )" );
        }
        else if (( name.indexOf ( "has" ) == 0 ) || ( name.indexOf ( "is" ) == 0 ))  // automatically generated checkboxes
        {
            buffer.append ( "(( 1 == results.getInt ( " );
            buffer.append ( index );
            buffer.append ( " ))? \"on\" : \"off\" )" );
            return buffer.toString();
        }
        else if (( java_sql_type == Types.SMALLINT   ) || ( java_sql_type == Types.INTEGER ))
        {
            buffer.append ( "Integer.toString ( results.getInt    ( " );
            buffer.append ( index );
            buffer.append ( " ) )" );
        }
        else if  ( java_sql_type == Types.BIGINT )
        {
            buffer.append ( "Long.toString ( results.getLong   ( " );
            buffer.append ( index );
            buffer.append ( " ) )" );
        }
        else if  ( java_sql_type == Types.FLOAT )
        {
            buffer.append ( "Float.toString ( results.getFloat  ( " );
            buffer.append ( index );
            buffer.append ( " ) )" );
        }
        else if  (( java_sql_type == Types.DOUBLE )  || ( java_sql_type == Types.REAL ))
        {
            buffer.append ( "Double.toString ( results.getDouble ( " );
            buffer.append ( index );
            buffer.append ( " ) )" );
        }
        else 
        {
            buffer.append ( "results.getString ( " );
            buffer.append ( index );
            buffer.append ( " )" );
        }

        return buffer.toString();
    }

	/**
	 * Generates the handler/cast operations for propertly setting the various parameters of the PreparedStatement object
	 * that is used to modify the data in the table that this column belongs to.  The data is retrieved from Struts form.
	 * 
	 * 
	 * @param data
	 * @return
	 */ 
    public String generatePSSetDataWrapper ( String data )
    {
        StringBuffer buffer = new StringBuffer();
        if      (( java_sql_type == Types.CHAR )     || ( java_sql_type == Types.VARCHAR ) ||
                 ( java_sql_type == Types.LONGVARCHAR ))
        {
            return data;
        }
        else if (( name.indexOf ( "has" ) == 0 ) || ( name.indexOf ( "is" ) == 0 ))  // automatically generated checkboxes
        {
            buffer.append ( "( " );
            buffer.append ( data );
            buffer.append ( ".equals ( \"on\" ) ? 1 : 0 )" );
            return buffer.toString();
        }
        else if (( java_sql_type == Types.SMALLINT   ) || ( java_sql_type == Types.INTEGER ))
        {
            buffer.append ( "Integer.parseInt ( " );
            buffer.append ( data );
            buffer.append ( " )" );
            return buffer.toString();
        }
        else if  ( java_sql_type == Types.BIGINT )
        {
            buffer.append ( "Long.parseLong( " );
            buffer.append ( data );
            buffer.append ( " )" );
            return buffer.toString();
        }
        else if  ( java_sql_type == Types.FLOAT )
        {
            buffer.append ( "Float.parseFloat( " );
            buffer.append ( data );
            buffer.append ( " )" );
            return buffer.toString();
        }
        else if  (( java_sql_type == Types.DOUBLE )  || ( java_sql_type == Types.REAL ))
        {
            buffer.append ( "Double.parseDouble( " );
            buffer.append ( data );
            buffer.append ( " )" );
            return buffer.toString();
        }
        else 
        {
            return data;
        }
    }

    /**
     * Formats the string to the desired length by adding whitespace to the end.  If the string is longer
	 * than the specified length, then no modifications are made.
     * 
     * @param string The string to be formatted
	 * @param length The desired length of the string
     */
    public static String formatStringToLength ( String string, int length )
    {
        StringBuilder s = new StringBuilder ( string ); while ( s.length() < length )
        {
            s.append ( " " );
        }

        return s.toString();
    }

};
