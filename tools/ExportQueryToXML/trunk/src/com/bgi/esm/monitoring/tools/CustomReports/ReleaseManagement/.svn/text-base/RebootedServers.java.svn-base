package com.bgi.esm.monitoring.tools.CustomReports.ReleaseManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.Properties;

public class RebootedServers
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
        else if ( database_type.equals ( "sqlserver-ms" ) )
        {
            return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
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

    public static void main ( String args[] ) throws ClassNotFoundException, SQLException, IOException, URISyntaxException
    {
        File moduleFile = new File (RebootedServers.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        String JarFileName = moduleFile.getName();
        
        if ( args.length < 2 )
        {
            System.err.println ( "Custom Report Utility - August 20, 2009" );
            System.err.println ( "Requested by Ashish Tamhane (Enterprise Systems Management)" );
            System.err.println ( "Created by Dennis Lin (Enterprise Systems Management / Global Shared Solutions)" );
            System.err.println ( "" );
            System.err.println ( "Usage: Need to specify start and end date/times in \"YYYY-MM-DD [HH:mm:ss]\" for query\n" );
            System.err.println ( "    java " + JarFileName + " [start-date] [end-date]\n" );

            System.exit ( 0 );
        }

        String dbDriverClassName = null;
        String jdbcURL           = null;

        //  Load Oracle
        dbDriverClassName = getDatabaseDriverClassName ( "oracle" );
        Class.forName ( dbDriverClassName );

        Connection con1 = DriverManager.getConnection ( createConnectionURL ( "oracle", "vpo", "openview", "1521" ), "system", "manager" );
        Statement stmt1 = con1.createStatement();
        StringBuilder query1 = new StringBuilder();
            query1.append ( "SELECT node_name " );
            query1.append ( "FROM opc_node_names n " );
            query1.append ( "WHERE n.node_id IN " );
            query1.append (     "(SELECT node_id " );
            query1.append (     " FROM opc_nodes_in_group " );
            query1.append (     " WHERE node_group_id IN " );
            query1.append (         "(SELECT node_group_id " );
            query1.append (         " FROM opc_node_groups " );
            query1.append (         " WHERE node_group_name='release_mgmt' ) )" );
        ResultSet results_openview = stmt1.executeQuery ( query1.toString() );

        Vector <String> node_names = new Vector <String> ();
        while ( results_openview.next() )
        {
            node_names.add ( results_openview.getString ( 1 ) );
        }

        dbDriverClassName = getDatabaseDriverClassName ( "sybase" );
        Class.forName ( dbDriverClassName );
        Connection con2 = DriverManager.getConnection ( createConnectionURL ( "sybase", "rdcuxsrv210", "monitoring_db", "14111" ), "esm_eeb", "App1talk" );
        Statement stmt2 = con2.createStatement();

        StringBuilder eeb_query = new StringBuilder();
            eeb_query.append ( "SELECT * FROM events_by_group WHERE (" );
            for ( int counter = 0; counter < node_names.size(); counter++ )
            {
                eeb_query.append ( "source_node='" );
                eeb_query.append ( node_names.get ( counter ) );
                eeb_query.append ( "' " );
                if ( counter + 1 < node_names.size() )
                {
                    eeb_query.append ( " OR " );
                }
            }
            eeb_query.append ( ") AND message_text LIKE '%eboot%' " );

            //System.out.println ( "EEB query: " + eeb_query.toString() );
        ResultSet results_eeb = stmt2.executeQuery ( eeb_query.toString() );

        saveResultsToFile ( "rebooted_servers.csv", results_eeb );
    }

    private static void saveResultsToFile ( String output_filename, ResultSet results ) throws IOException, SQLException
    {
        //OutputStream outputStream = new FileOutputStream ( output_filename );
        OutputStream outputStream = System.out;
        if ( results.next() )
        {
            ResultSetMetaData md = results.getMetaData();
            int num_columns      = md.getColumnCount();
            StringBuilder row    = new StringBuilder();

            row = new StringBuilder();
            for ( int counter = 0; counter < num_columns; counter++ )
            {
                row.append ( md.getColumnName ( counter + 1 ) );
                if ( counter + 1 < num_columns )
                {
                    row.append ( "," );
                }
            }
            row.append ( "\n" );
            outputStream.write ( row.toString().getBytes() );

            do
            {
                row = new StringBuilder();
                for ( int counter = 0; counter < num_columns; counter++ )
                {
                    String value = results.getString ( counter + 1 );
                    if ( null == value )
                    {
                        value= "";
                    }
                    else
                    {
                        value = value.replaceAll ( "\n", "|" );
                    }
                    row.append ( value );
                    if ( counter + 1 < num_columns )
                    {
                        row.append ( "," );
                    }
                }
                row.append ( "\n" );
                outputStream.write ( row.toString().getBytes() );
            }
            while ( results.next() );

            outputStream.close();
        }
    }
}
