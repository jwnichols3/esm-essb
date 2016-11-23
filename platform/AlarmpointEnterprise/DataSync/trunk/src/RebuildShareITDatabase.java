import java.io.*;
import java.sql.*;
import java.util.*;

public class RebuildShareITDatabase
{
    private final String port         = "14110";
    private Vector <Integer> app_nums = new Vector <Integer>() ;
    
	public static void main ( String args[] )
	{
        RebuildShareITDatabase d = new RebuildShareITDatabase();
        
        d.run();
	}

	public RebuildShareITDatabase()
	{
	}

	public void run()
	{
		try
        {
		    //  Connect to the database
            System.out.println ( "Connecting to database..." );
            Connection source                        = connectToSourceDatabase();
            Connection output                        = connectToOutputDatabase();
            
            //  Reset the database
            System.out.println ( "Resetting database..." );
            resetDatabase( output.createStatement() );

            //  Retrieve applications and their owners from the database
            System.out.println ( "Retrieving application owners..." );
            rebuildAppOwners ( source, output );
            
            //  Retrieve applications
            System.out.println ( "Retrieving applications" );
            rebuildApplications ( source, output );
            
            //  Retrieve the server/application relationships
            System.out.println ( "Building app/server relationships..." );
            rebuildAppServers ( source, output );
    		
            System.out.println ( "Finished" );
        }
        catch ( ClassNotFoundException exception )
        {
            exception.printStackTrace();
        }
        catch ( SQLException exception )
        {
            exception.printStackTrace();
        } 
	}

	private Connection connectToSourceDatabase() throws ClassNotFoundException, SQLException
	{
		Properties props = new Properties();
        props.put("user", "shareit_read");
        props.put("password",  "relay8");

		Class.forName ( "com.sybase.jdbc2.jdbc.SybDriver" );
        String db_connection_string = "jdbc:sybase:Tds:RC_AUTO:"+port+"/shareit_db";
		Connection con              = DriverManager.getConnection (db_connection_string,  props);

		return con;
	}
    
    private Connection connectToOutputDatabase() throws ClassNotFoundException, SQLException
    {
		Properties props = new Properties();
        props.put("user", "shareit_read");
        props.put("password",  "relay8");

		Class.forName ( "com.sybase.jdbc2.jdbc.SybDriver" );
        String db_connection_string = "jdbc:sybase:Tds:RC_AUTO:"+port+"/shareit_db";
		Connection con              = DriverManager.getConnection (db_connection_string,  props);

        return con;
    }
    
    private void resetDatabase ( Statement stmt ) throws SQLException
    {
        stmt.executeUpdate ( "DELETE FROM bgi_monitoring.shareit_app_owners" );
        stmt.executeUpdate ( "DELETE FROM bgi_monitoring.shareit_app_owners_type_index" );
        stmt.executeUpdate ( "DELETE FROM bgi_monitoring.shareit_app_servers" );
        stmt.executeUpdate ( "DELETE FROM bgi_monitoring.shareit_apps" );
        
        // share_it_app_owners
        //stmt.executeUpdate ( "DROP TABLE bgi_monitoring.shareit_app_owners" );
        //stmt.executeUpdate ( "CREATE TABLE shareit_app_owners ( group_type_index int(10) unsigned NOT NULL default '0', app_id int(10) unsigned NOT NULL default '0', person_name varchar(45) NOT NULL default '', PRIMARY KEY  (group_type_index,app_id,person_name)) ENGINE=MyISAM DEFAULT CHARSET=utf8;" );

        //   shareit_app_owners_type_index
        //stmt.executeUpdate ( "DROP TABLE IF EXISTS bgi_monitoring.shareit_app_owners_type_index;" );
        //stmt.executeUpdate ( "CREATE TABLE shareit_app_owners_type_index ( app_owner_type_id int(10) unsigned NOT NULL default '0', app_owner_type varchar(45) NOT NULL default '', PRIMARY KEY  (app_owner_type_id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;" );

        //  shareit_app_servers
        //stmt.executeUpdate ( "DROP TABLE IF EXISTS bgi_monitoring.shareit_app_servers;" );
        //stmt.executeUpdate ( "CREATE TABLE shareit_app_servers ( app_id int(10) unsigned NOT NULL default '0', server varchar(200) NOT NULL default '', PRIMARY KEY  (app_id,server)) ENGINE=InnoDB DEFAULT CHARSET=utf8;" );

        //  shareit_apps
        //stmt.executeUpdate ( "DROP TABLE IF EXISTS bgi_monitoring.shareit_apps;" );
        //stmt.executeUpdate ( "CREATE TABLE shareit_apps ( app_id int(10) unsigned NOT NULL default '0', app_name varchar(200) NOT NULL default '', PRIMARY KEY  (app_id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;" );

        //  sw_hw_relate
        //stmt.executeUpdate ( "DROP TABLE IF EXISTS bgi_monitoring.sw_hw_relate;" );
        //stmt.executeUpdate ( "CREATE TABLE sw_hw_relate ( sw_hw_parent_id int(10) unsigned NOT NULL default '0', sw_hw_id int(10) unsigned NOT NULL default '0', relationship_type_cd varchar(200) NOT NULL default '', sw_hw_rel_notes varchar(200) default NULL, source_nm varchar(200) NOT NULL default '', created_by varchar(200) NOT NULL default '', last_seen_by varchar(200) NOT NULL default '', last_changed_by varchar(200) NOT NULL default '', retired_by varchar(200) default NULL, created_dt varchar(200) NOT NULL default '', last_changed_dt varchar(200) NOT NULL default '', last_seen_dt varchar(200) NOT NULL default '', retired_dt varchar(200) default NULL, environment_cd varchar(200) default NULL, PRIMARY KEY  (sw_hw_parent_id,sw_hw_id,relationship_type_cd)) ENGINE=MyISAM DEFAULT CHARSET=utf8;" );        
    }
    
    private void rebuildAppServers ( Connection source, Connection output ) throws SQLException
    {
        StringBuilder query = new StringBuilder ( "SELECT DISTINCT d.sw_hw_nm " );
        query.append ( " FROM   sw_hw_relate AS s, sw_hw AS d " );
        query.append ( " WHERE  s.sw_hw_id IN " );
        query.append ( "        (" );
        query.append ( "         SELECT sw_hw_parent_id " );
        query.append ( "           FROM sw_hw_relate t " );
        query.append ( "          WHERE t.sw_hw_id=? " );
        query.append ( "        ) " );
        query.append ( " AND   s.sw_hw_parent_id=d.sw_hw_id" );
        
        PreparedStatement get_servers      = source.prepareStatement ( query.toString() );
        PreparedStatement add_app_server   = output.prepareStatement ( "INSERT INTO shareit_app_servers ( app_id, server ) VALUES ( ?, ? )" );
        Enumeration <Integer> e            = app_nums.elements();
        ResultSet results                 = null;
        Integer app_num                    = null;
        Hashtable <Integer, Vector <String> > apps   = new Hashtable <Integer, Vector <String> >();
        Vector <String> servers            = null;
        String server                      = null;
        int index                          = 0;
        
        while ( e.hasMoreElements() )
        {
            app_num = e.nextElement();
            
            get_servers.setInt ( 1, app_num.intValue() );
            results = get_servers.executeQuery();
            
            while ( results.next() )
            {
                server = results.getString ( 1 ).toLowerCase();
                servers = apps.get ( app_num );
                if ( null == servers )
                {
                    servers = new Vector <String> ();
                    servers.add ( server );
                    apps.put ( app_num, servers );
                }
                else
                {
                    index = servers.indexOf ( server );
                    if ( index < 0 )
                    {
                        servers.add ( server );
                    }
                }
            } 
        }
        
        e = apps.keys();
        FileOutputStream outfile;
        try
        {
            outfile = new FileOutputStream ( "c:\\test\\app_servers" );
                while ( e.hasMoreElements() )
                {
                    app_num = e.nextElement();
                    servers = apps.get( app_num );
                    for ( int counter = 0; counter < servers.size(); counter++ )
                    {
                        outfile.write ( app_num.toString().getBytes() );
                        outfile.write ( ",".getBytes() );
                        outfile.write ( servers.elementAt(counter).toString().getBytes() );
                        outfile.write ( "\n".getBytes() );
                        add_app_server.setInt ( 1, app_num.intValue() );
                        add_app_server.setString ( 2, servers.elementAt(counter) );
                        add_app_server.executeUpdate();
                    }
                    //*/
                }
            outfile.close();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
    private void rebuildApplications ( Connection source, Connection output ) throws SQLException
    {
        CallableStatement cstmt                  = source.prepareCall("exec p_get_app_components");
        ResultSet results                        = cstmt.executeQuery();
        PreparedStatement insert_applications    = output.prepareStatement ( "INSERT INTO shareit_apps ( app_id, app_name ) VALUES ( ?, ? )" );
        String app_name                          = null;
        Hashtable <Integer, Vector <String> > applications = new Hashtable <Integer, Vector <String> > ();
        Vector <String> app_numbers              = null;
        Integer app_id                           = 0;
        int record_counter                       = 1;
        int index                                = 0;
        
        while ( results.next() )
        {
            app_id   = new Integer ( results.getInt ( "appID" ) );
            app_name = results.getString  ( "appNm" );
            
            if ( false == app_nums.contains( app_id ) )
            {
                app_nums.add ( app_id );
            }
            
            app_numbers = applications.get ( app_id );
            if ( null == app_numbers )
            {
                app_numbers = new Vector <String> ();
                app_numbers.add( app_name );
                applications.put ( app_id, app_numbers );
            }
            else
            {
                index = app_numbers.indexOf ( app_name );
                if ( index < 0 )
                {
                    app_numbers.add ( app_name );
                }
            }            
            
            if ( record_counter%100 == 0 )
            {
                record_counter++;
            }
        }
        
        Enumeration <Integer> e = applications.keys();
        while ( e.hasMoreElements() )
        {
            app_id = e.nextElement();
            app_numbers = applications.get ( app_id );
            
            for ( int counter = 0; counter < app_numbers.size(); counter++ )
            {
                app_name = app_numbers.elementAt ( counter );
                insert_applications.setInt ( 1, app_id.intValue() );
                insert_applications.setString( 2, app_name );
                insert_applications.executeUpdate();
            }
        }
    }
    
    private void rebuildAppOwners ( Connection source, Connection output ) throws SQLException
    {
        CallableStatement cstmt                         = source.prepareCall("exec p_get_app_people_info");
        ResultSet results                               = cstmt.executeQuery();    
        Hashtable <String, Vector <String> > app_owners = new Hashtable <String, Vector <String> >();
        Vector <String> group_types                     = new Vector <String> ();
        Vector <String> values                          = new Vector <String> ();    
        String person_name                              = null;
        String group_name                               = null;
        String template_name                            = null;
        String app_num                                  = null;
        StringTokenizer splitter                        = null;        
        int index                                       = 0;
        
        PreparedStatement insert_app_owners      = output.prepareStatement ( "INSERT INTO shareit_app_owners ( group_type_index, app_id, person_name ) VALUES ( ?, ?, ? )" );
        PreparedStatement insert_app_owner_types = output.prepareStatement ( "INSERT INTO shareit_app_owners_type_index ( app_owner_type_id, app_owner_type ) VALUES ( ?, ? )" );
        
        while ( results.next() )
        {
            person_name   = results.getString ( "person_name" ).trim();
            group_name    = results.getString ( "group_name" ).trim();
            template_name = results.getString ( "template_name" ).trim();
            
            index = group_types.indexOf ( template_name );
            if ( -1 == index )
            {
                group_types.add ( template_name );
                index = group_types.size();
            }
            else
            {
                index++;
            }
            
            splitter = new StringTokenizer ( group_name, "_" );
            while ( splitter.hasMoreTokens() )
            {
                app_num = splitter.nextToken();
            }
            
            insert_app_owners.setInt    ( 1, index );
            insert_app_owners.setInt    ( 2, Integer.parseInt ( app_num ) );
            insert_app_owners.setString ( 3, person_name );
            insert_app_owners.executeUpdate();
            
            values = app_owners.get ( app_num );
            if ( null != values )
            {
                values.add ( person_name );
            }
            else
            {
                values = new Vector <String> ();
                values.add ( person_name );
                app_owners.put ( app_num, values );
            }
        }
        
        for ( int counter = 0; counter < group_types.size(); counter++ )
        {
            insert_app_owner_types.setInt ( 1, counter+1 );
            insert_app_owner_types.setString ( 2, group_types.elementAt(counter) );
            insert_app_owner_types.executeUpdate();
        }
    }
}
