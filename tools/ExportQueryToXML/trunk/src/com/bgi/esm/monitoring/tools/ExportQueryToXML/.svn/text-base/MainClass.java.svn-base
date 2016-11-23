package com.bgi.esm.monitoring.tools.ExportQueryToXML;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainClass
{
    private static String createConnectionURL ( String database_type, String server, String name, String port )
    {
        StringBuilder connection_url = new StringBuilder();
       
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
    private static String getDatabaseDriverClassName ( String database_type )
    {
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
    private static boolean no_cdata       = false;
    private static String output_filename = "query.out.xml";

    private static boolean processArguments ( String args[] )
    {
        for ( int counter = 0; counter < args.length; counter++ )
        {
            String argument = args[counter];

            if ( argument.indexOf ( "-server=" ) == 0 )
            {
                server = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-database=" ) == 0 )
            {
                name = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-port=" ) == 0 )
            {
                port = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-username=" ) == 0 )
            {
                username = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-password=" ) == 0 )
            {
                password = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-query=" ) == 0 )
            {
                query = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
            else if ( argument.indexOf ( "-dbtype=" ) == 0 )
            {
                db_type = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
                db_type = db_type.toLowerCase();
            }
            else if ( argument.equals ( "-no-cdata" ) )
            {
                no_cdata = true;
            }
            else if ( argument.indexOf ( "-outfile=" ) == 0 )
            {
                output_filename = argument.substring ( 1 + argument.indexOf ( "=" ) ).trim();
            }
        }

        return (( null != server   ) &&
                ( null != name     ) &&
                ( null != port     ) &&
                ( null != query    ) &&
                ( null != username ) && 
                ( null != password ) &&
                ( null != db_type  ));
    }

    public static void main ( String args[] ) throws ClassNotFoundException, SQLException, IOException
    {
        boolean found_errors = false;

        if ( !processArguments ( args ) )
        {
            System.err.println ( "ERROR: You did not specify the following arguments:" );
            if ( null == server   )
            {
                System.err.println ( "    server" );
            }
            if ( null == name     )
            {
                System.err.println ( "    database" );
            }
            if ( null == port     )
            {
                System.err.println ( "    port" );
            }
            if ( null == username )
            {
                System.err.println ( "    username" );
            }
            if ( null == password )
            {
                System.err.println ( "    password" );
            }
            if ( null == db_type  )
            {
                System.err.println ( "    dbtype" );
            }
            if ( null == query    )
            {
                System.err.println ( "    query" );
            }

            found_errors = true;
        }

        if ( (null != db_type ) && !db_type.equals ( "oracle" )    && !db_type.equals ( "sybase" ) && 
             !db_type.equals ( "sqlserver" ) && !db_type.equals ( "mysql"  ))
        {
            System.err.println ( "ERROR: You specified an invalid database type: " + db_type );

            found_errors = true;
        }
          
        if ( true == found_errors )
        {
            System.err.println ( "" ); 
            System.err.println ( "Usage:\n" );
            System.err.println ( "    java -jar ExportQueryToXML -server=? -database=? -port=? -username=? -password=? -dbtype=? -outfile=? <-no-cdata> -query\n" );
            System.err.println ( "        server     hostname that the database lives on" );
            System.err.println ( "        database   the name of the database to connect to" );
            System.err.println ( "        port       the port on which the database listens to connetions" );
            System.err.println ( "        username   login credentials" );
            System.err.println ( "        password   login credentials" );
            System.err.println ( "        dbtype     Database type.  This is case insensitive" );  
            System.err.println ( "                   Must be one of the following: Oracle, Sybase, SQLServer, MySQL" );
            System.err.println ( "        no-cdata   Does *NOT* wrap the data values in a CDATA field\n" );
            System.err.println ( "        query      The SQL query to run" );
            System.err.println ( "        outfile    Optional.  The name of the output XML file.  Defaults to \"query.out.xml\"\n" );
            System.err.println ( "" ); 
            System.err.println ( "Version 1.01 - March 25, 2007" ); 

            System.exit ( 0 );
        }
        /*
        String server   = "rdcuxsrv038";
        String name     = "vpo_support_db";
        String port     = "14110";
        String username = "vpo_suppress_web";
        String password = "thyroiditis";
        String db_type  = "Sybase";

        String server   = "calntesm502";
        String name     = "Reporter";
        String port     = "1433";
        String username = "openview";
        String password = "HYPertext01";
        String db_type  = "SQLServer";
        //*/
       
        //* 
        //*/

        String db_driver = getDatabaseDriverClassName ( db_type );
        String connection_url = createConnectionURL ( db_type, server, name, port );

        System.out.println ( "Connection URL: " + connection_url );
        System.out.println ( "Outputting to " + output_filename );

        Class.forName ( db_driver );
        Connection con = DriverManager.getConnection ( connection_url, username, password );
        Statement stmt = con.createStatement();

        ResultSet results = stmt.executeQuery ( query );
        //ResultSet results = stmt.executeQuery ( "select DATETIME, TARGET, AVAILABILITY, RESPONSETIME from IOPS_DETAIL_DATA_DAILY" );
        //ResultSet results = stmt.executeQuery ( "select DATETIME, TARGET, AVAILABILITY, RESPONSETIME from IOPS_DETAIL_DATA_DAILY where SERVICENAME='bgicash_pages'" );
        //ResultSet results = stmt.executeQuery ( "select * from t_vpo_suppress" );

        if ( results.next() )
        {
            ResultSetMetaData md = results.getMetaData();
            int num_columns      = md.getColumnCount();
            StringBuilder row    = new StringBuilder();

            FileOutputStream outfile = new FileOutputStream ( output_filename );
            outfile.write ( "<query-results>\n".getBytes() );

            do
            {
                outfile.write ( "    <row>\n".getBytes() );
                row = new StringBuilder();
                for ( int counter = 0; counter < num_columns; counter++ )
                {
                    if ( no_cdata )
                    {
                        row.append ( "        <"  );
                        row.append ( md.getColumnName ( counter + 1 ) );
                        row.append ( ">" );
                        row.append ( results.getString ( counter+1 ) );
                        row.append ( "</"  );
                        row.append ( md.getColumnName ( counter + 1 ) );
                        row.append ( ">\n" );
                    }
                    else
                    {
                        row.append ( "        <"  );
                        row.append ( md.getColumnName ( counter + 1 ) );
                        row.append ( "><![CDATA[" );
                        row.append ( results.getString ( counter+1 ) );
                        row.append ( "]]></"  );
                        row.append ( md.getColumnName ( counter + 1 ) );
                        row.append ( ">\n" );
                    }
                }
                outfile.write ( row.toString().getBytes() );
                outfile.write ( "    </row>\n".getBytes() );
            }
            while ( results.next() );
            outfile.write ( "</query-results>".getBytes() );

            outfile.close();
        }
    }
}
