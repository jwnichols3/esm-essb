package com.bgi.esm.portlets.testing.alarmpoint;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import org.apache.log4j.Logger;
import com.bgi.esm.portlets.testing.AlarmpointTestcase;

public class PopulateGroupsTeams
{
    final private static Logger _log                                 = Logger.getLogger ( PopulateGroupsTeams.class );
    private static Properties apserver_properties                    = null;
    private static Connection con                                    = null;
    private static AlarmpointTestcase testcase                       = new AlarmpointTestcase ( "populate_groups_teams" );
    private static HashMap <String, Vector<String> > group_coverages = new HashMap <String, Vector<String> > ();

    //public static void main ( String args[] ) throws IOException, SQLException, ClassNotFoundException
    public static void main ( String args[] ) throws Exception
    {
        try
        {
            testcase.initialize();

            //testcase.addNewGroup_People ( "Test Group 02",  "Test Team 01", new String[] { "linden", "nichj" } );
            //testcase.addNewGroup_People ( "Test Group 02",  "Test Team 02", new String[] { "linden", "nichj" } );
        }
        catch ( Exception exception )
        {
            _log.error ( exception );
            exception.printStackTrace();
        }

        if ( true )
        {
            readAlarmpointServerProperties();

            createDatabaseConnection();

            retrieveGroupCoverages();

            processUsersInGroupCoverages();
        }
    }

    private static void processUsersInGroupCoverages() throws Exception
    {
        PreparedStatement ps_retrieve_users = con.prepareStatement ( "SELECT PersonID FROM PersonsByGroups WHERE GroupID=? AND CoverageName=?" );
        PreparedStatement ps_retrieve_login = con.prepareStatement ( "SELECT LoginName FROM Persons WHERE PersonID=?" );
        Iterator <String> keys              = group_coverages.keySet().iterator();
        Vector <String> coverages           = null;
        ResultSet results                   = null;
        String group_id                     = null;
        String coverage_name                = null;

        while ( keys.hasNext() )
        {
            group_id = keys.next();

            coverages = group_coverages.get ( group_id );

            int num_coverages = coverages.size();
            for ( int counter = 0; counter < num_coverages; counter++ )
            {
                coverage_name = coverages.elementAt ( counter );

                if ( null == coverage_name )
                {
                    coverage_name = "Default Team";
                }

                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( group_id );
                message.get().append ( "/" );
                message.get().append ( coverage_name );

                _log.info ( message.get().toString() );
                ps_retrieve_users.setString ( 1, group_id      );
                ps_retrieve_users.setString ( 2, coverage_name );
                results = ps_retrieve_users.executeQuery();

                Vector <String> usernames = new Vector <String> ();

                while ( results.next() )
                {
                    String username   = results.getString ( 1 );

                    ps_retrieve_login.setString ( 1, username );
                    ResultSet results_login = ps_retrieve_login.executeQuery();
                    results_login.next();
                    String login            = results_login.getString ( 1 );

                    usernames.add ( login );
                }

                try
                {
                    testcase.addNewGroup_People ( group_id,  coverage_name, usernames.toArray() );
                }
                catch ( Exception exception )
                {
                    _log.error ( "Error detected!", exception );

                    exception.printStackTrace();

                    Object array[] = usernames.toArray();

                    FileOutputStream outfile = new FileOutputStream ( "c:\\test\\populate_alarmpoint.log", true );
                        outfile.write ( (new Date()).toString().getBytes() );
                        outfile.write ( " - Error detected when processing ( Group, Team ) - ( ".getBytes() );
                        outfile.write ( group_id.getBytes() );
                        outfile.write ( ", ".getBytes() );
                        outfile.write ( coverage_name.getBytes() );
                        outfile.write ( " )\n".getBytes() );

                        for ( int c = 0; c < array.length; c++ )
                        {
                            outfile.write ( "    ".getBytes() );
                            outfile.write ( array[c].toString().getBytes() );
                            outfile.write ( "\n".getBytes() );
                        }
                    outfile.close();
                }
            }
        }
    }

    private static void retrieveGroupCoverages() throws SQLException
    {
        PreparedStatement ps_retrieve_groups_coverages = 
            con.prepareStatement ( "SELECT GroupID, CoverageName, COUNT(*) AS NumPeople FROM PersonsByGroups GROUP BY GroupID, CoverageName" );

        ResultSet results         = ps_retrieve_groups_coverages.executeQuery();
        Vector <String> coverages = null;
        String group_id           = null;
        String coverage           = null;

        while ( results.next() )
        {
            group_id  = results.getString ( 1 ).trim();
            coverage  = results.getString ( 2 ).trim();

            coverages = group_coverages.get ( group_id );
            _log.info ( "Processing group: " + group_id );

            if ( null == coverages )
            {
                _log.info ( "\tFirst coverage in group: " + group_id );
                coverages = new Vector <String> ();
            }

            if ( !coverages.contains ( coverage ) )
            {
                _log.info ( "\tAdding new coverage " + group_id + "/" + coverage );
                coverages.add ( coverage );
                group_coverages.put ( group_id, coverages );
            }
        }
    }

    private static void readAlarmpointServerProperties() throws IOException
    {
        _log.info ( "Reading Alarmpoint Server properties..." );

        apserver_properties = new Properties(); 
        Class c             = PopulateGroupsTeams.class;
        ClassLoader cl      = c.getClassLoader();
        InputStream is      = cl.getResourceAsStream ( "alarmpoint_server.properties" );
        apserver_properties.load ( is );
    }

    private static void createDatabaseConnection() throws ClassNotFoundException, SQLException
    {
        _log.info ( "Creating connection to Alarmpoint Server database..." );

        Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );
        String hostname     = apserver_properties.getProperty ( "alarmpoint.server.database.hostname" );
        String username     = apserver_properties.getProperty ( "alarmpoint.server.database.username" );
        String password     = apserver_properties.getProperty ( "alarmpoint.server.database.password" );
        String name         = apserver_properties.getProperty ( "alarmpoint.server.database.name"     );

        StringBuilder connection_url = new StringBuilder ( "jdbc:jtds:sqlserver://" );
        connection_url.append ( hostname );
        connection_url.append ( ":1433/" );
        connection_url.append ( name );

        con = DriverManager.getConnection ( connection_url.toString(), username, password );
    }
};

