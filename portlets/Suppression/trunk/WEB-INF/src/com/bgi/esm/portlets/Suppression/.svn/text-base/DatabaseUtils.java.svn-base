package com.bgi.esm.portlets.Suppression;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;


public class DatabaseUtils
{
    /**
	 * Converts a java.sql.ResultSet object into a java.util.HashMap object with a java.util.Vector object representing the values of 
	 * a column in the ResultSet object
	 * 
	 * @param results the java.sql.ResultSet object to process
	 * @return a java.util.HashMap object with a java.util.Vector object representing the values of a column in the ResultSet object
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap <String, Vector <?> > convertResultsToHashMaps ( final ResultSet results ) throws SQLException
	{
		HashMap <String, Vector <?> > return_value = new HashMap <String, Vector <?> > ();
		
		ResultSetMetaData rsmd = results.getMetaData();
		String column_name     = null;
		int column_type        = 0;
		int num_columns        = rsmd.getColumnCount();
		
		//  First initialize the objects that will hold the data
		for ( int counter = 1; counter <= num_columns; counter++ )
		{
			column_name = rsmd.getColumnName ( counter );
			column_type = rsmd.getColumnType ( counter );
			
			if (( Types.INTEGER == column_type ) || ( Types.TINYINT == column_type ) || ( Types.SMALLINT == column_type ))
			{
				Vector <Integer> v = new Vector <Integer> ();
				
				return_value.put ( column_name, v );
			}
			else if (( Types.NUMERIC == column_type ) || ( Types.DECIMAL == column_type ) || ( Types.FLOAT == column_type ) ||
					 ( Types.DOUBLE == column_type ) || ( Types.REAL == column_type ))
			{
				Vector <Double> v = new Vector <Double> ();
				
				return_value.put ( column_name, v );
			}
			else if (( Types.CHAR == column_type ) || ( Types.LONGVARCHAR == column_type ) || ( Types.VARCHAR == column_type ))
			{
				Vector <String> v = new Vector <String> ();
				
				return_value.put ( column_name, v );
			}
			else if ( Types.BIGINT == column_type )
			{
				Vector <Long> v = new Vector <Long> ();
				
				return_value.put ( column_name, v );
			}
			else if ( Types.DATE == column_type )
			{
				Vector <java.util.Date> v = new Vector <java.util.Date> ();
				
				return_value.put ( column_name, v );
			}
			else if ( Types.TIMESTAMP == column_type )
			{
				Vector <java.util.Date> v = new Vector <java.util.Date> ();
				
				return_value.put ( column_name, v );
			}
			else if ( Types.CLOB == column_type )
			{
				Vector <java.sql.Clob> v = new Vector <java.sql.Clob> ();
				
				return_value.put ( column_name, v );
			}
			else if ( Types.BLOB == column_type )
			{
				Vector <java.sql.Blob> v = new Vector <Blob> ();
				
				return_value.put ( column_name, v );
			}
			else
			{
				throw new SQLException ( "DatabaseUtils::convertResultsToHashMaps() - unknown SQL type encountered: " + rsmd.getColumnTypeName ( counter ) );
			}
		}
		
		//  Next, iterate through the data and put the data in each column in their
		//  respective Vector objects
		while ( results.next() )
		{
			for ( int counter = 1; counter <= num_columns; counter++ )
			{
				column_name = rsmd.getColumnName ( counter );
				column_type = rsmd.getColumnType ( counter );
				
				if (( Types.INTEGER == column_type ) || ( Types.TINYINT == column_type ) || ( Types.SMALLINT == column_type ))
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( new Double ( results.getInt ( counter ) ) );
				}
				else if (( Types.NUMERIC == column_type ) || ( Types.DECIMAL == column_type ) || ( Types.FLOAT == column_type ) ||
						 ( Types.DOUBLE == column_type ) || ( Types.REAL == column_type ))
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( new Double ( results.getDouble ( counter ) ) );
				}
				else if (( Types.CHAR == column_type ) || ( Types.LONGVARCHAR == column_type ) || ( Types.VARCHAR == column_type ))
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( results.getString ( counter ) );
				}
				else if ( Types.BIGINT == column_type )
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( new Long ( results.getLong ( counter ) ) );
				}
				else if ( Types.DATE == column_type )
				{
					Vector v = return_value.get ( column_name );
					
					Date d = new Date ( results.getDate( counter).getTime() );
					
					v.add ( d );
				}
				else if ( Types.TIMESTAMP == column_type )
				{
					Vector v = return_value.get ( column_name );
					
					Date d = new Date ( results.getTimestamp( counter ).getTime() );
					
					v.add ( d );
				}
				else if ( Types.CLOB == column_type )
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( results.getClob ( counter ) );
				}
				else if ( Types.BLOB == column_type )
				{
					Vector v = return_value.get ( column_name );
					
					v.add ( results.getBlob ( counter ) );
				}
			}
		}
		return return_value;
	}

   
};
