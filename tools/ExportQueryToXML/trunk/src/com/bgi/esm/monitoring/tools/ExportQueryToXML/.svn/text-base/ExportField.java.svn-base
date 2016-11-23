package com.bgi.esm.monitoring.tools.ExportQueryToXML;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ExportField
{
    private static String createConnectionURL ( String database_type_string, String server, String name, String port )
    {
        StringBuilder connection_url = new StringBuilder();

        String database_type = database_type_string.toLowerCase();
       
        if ( null == database_type )
        {
            return "";
        } 
        if ( database_type.equals ( "sybase" ) )
        {
            connection_url.append ( "jdbc:sybase:Tds:" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "sqlserver" ) )
        {
            connection_url.append ( "jdbc:jtds:sqlserver://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "mysql" ) )
        {
            connection_url.append ( "jdbc:mysql://" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( "/" );
            connection_url.append ( name );
        }
        else if ( database_type.equals ( "oracle" ))
        {
            connection_url.append ( "jdbc:oracle:thin:@" );
            connection_url.append ( server );
            connection_url.append ( ":" );
            connection_url.append ( port );
            connection_url.append ( ":" );
            connection_url.append ( name );
        }
        
        return connection_url.toString();
    }

    /**
     * @param database_type the type of the database (Sybase, SQL Server, Oracle, PostgreSQL, MySQL)
     * @return the class name of the driver that the database will use
     */
    private static String getDatabaseDriverClassName ( String database_type_string )
    {
        String database_type = database_type_string.toLowerCase();

        if ( null == database_type )
        {
            return "Unknown";
        }
        else if ( database_type.equals ( "sybase" ) )
        {
            return "com.sybase.jdbc2.jdbc.SybDriver";
        }
        else if ( database_type.equals ( "sqlserver" ) )
        {
            return "net.sourceforge.jtds.jdbc.Driver";
        }
        else if ( database_type.equals ( "mysql" ) )
        {
            return "com.mysql.jdbc.Driver";
        }
        else if ( database_type.equals ( "oracle" ))
        {
            return "oracle.jdbc.driver.OracleDriver";
        }
        
        return "Unknown";
    }

    private static String server          = null;
    private static String name            = null;
    private static String port            = null;
    private static String username        = null;
    private static String password        = null;
    private static String db_type         = null;
    private static String query           = null;
    private static String output_filename = "query.out.xml";

    public static void main ( String args[] ) throws Exception
    {
        /*
        String server   = "rdcuxsrv038";
        String name     = "vpo_support_db";
        String port     = "14110";
        String username = "vpo_suppress_web";
        String password = "thyroiditis";
        String db_type  = "Sybase";
        //*/

        String server   = "calntesm201";
        String name     = "replatform2db";
        String port     = "1433";
        String username = "openjms";
        String password = "HYPertext01";
        String db_type  = "SQLServer";
        //*/
       
        //* 
        //*/

        String db_driver = getDatabaseDriverClassName ( db_type );
        String connection_url = createConnectionURL ( db_type, server, name, port );

        System.out.println ( "Database type:  " + db_type  );
        System.out.println ( "Driver type:    " + db_driver );
        System.out.println ( "Connection URL: " + connection_url );
        System.out.println ( "Outputting to " + output_filename );

        Class.forName ( db_driver );
        Connection con = DriverManager.getConnection ( connection_url, username, password );
        Statement stmt = con.createStatement();
        ResultSet results = stmt.executeQuery ( "SELECT xml FROM raw_ovi" );

        FileOutputStream outfile = new FileOutputStream ( output_filename );
        int num_records = 200;
        int counter     = 0;
        FileOutputStream indices = new FileOutputStream ( "messageid.out" );
        Vector <String> messages_new = new Vector <String> ();
        Vector <String> messages_dup = new Vector <String> ();

        while ( results.next() )
        {
            String string = results.getString ( 1 );
            string = string.replaceAll ( "\n", "" );
            outfile.write ( string.getBytes() );
            outfile.write ( "\n".getBytes() );

            String string0 = "DuplicateChange";
            String string1 = "<messageUUID>";
            String string2 = "</messageUUID>";
            String data    = null;

            int index1 = string.indexOf ( string1 );
            data = string.substring ( index1 + string1.length() );
            int index2 = data.indexOf ( string2 );
            data = data.substring ( 0, index2 );

            if ( string.indexOf ( string0 ) > 0 )
            {
                messages_dup.add ( data );
            }
            else
            {
                messages_new.add ( data );
            }

            if ( counter < num_records )
            {
                counter++;
            }
            else
            {
                break;
            }
        }
        indices.close();
        outfile.close();

        FileOutputStream common = new FileOutputStream ( "common.out" );
            for ( counter = 0; counter < messages_new.size(); counter++ )
            {
                String key = messages_new.elementAt ( counter );

                if ( messages_dup.contains ( key ) )
                {
                    common.write ( key.getBytes() );
                    common.write ( "\n".getBytes() );
                }
            }
        common.close();
    }

};
