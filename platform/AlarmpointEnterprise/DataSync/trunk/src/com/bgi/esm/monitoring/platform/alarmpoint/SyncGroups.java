package com.bgi.esm.monitoring.platform.alarmpoint;

import java.lang.ref.WeakReference;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class SyncGroups
{
    final private static Logger _log = Logger.getLogger ( SyncGroups.class );

    public static void synchronizeDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con            = Common.getDestConnection();
        
        Properties default_values = Common.defaultProperties();
        Connection con_apserver   = Common.getAPServerConnection();
        Statement stmt_groups     = con_apserver.createStatement();
        ResultSet results_groups  = stmt_groups.executeQuery ( "SELECT * FROM groups" );

        String group_name         = null;
        PreparedStatement ps_delete = con.prepareStatement ( "DELETE FROM " + Common.getDestDatabaseOwner() + ".ds_groups" );
        _log.error ( "Num old records removed: " + ps_delete.executeUpdate() );

        PreparedStatement ps      = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + ".ds_groups ( EXTERNAL_KEY, GROUP_NAME, DESCRIPTION, STATUS, USE_DEFAULT_DEVICES, SUPERVISOR_LIST, ALLOW_DUPLICATES, ALARMPOINT_ACTION, TIMEZONE, OBSERVER_LIST ) VALUES ( ?, ?, ?, ?, ?, ?, 'Y', 'Y', 'US/Pacific', 'Full Access User' )" );
        PreparedStatement ps_user_exist = con.prepareStatement ( "SELECT count(*) FROM " + Common.getDestDatabaseOwner() + ".recipients WHERE sync_external_key=?" );

        int num_groups = 0;
        while ( results_groups.next() )
        {
            String group_id       = results_groups.getString ( "GroupID" );
            String group_desc     = results_groups.getString ( "GroupDescription" );
            String group_type     = results_groups.getString ( "GroupType" );
            String supervisor_key = null;

            //  Construct the supervisors list
            PreparedStatement ps_group_supervisors = con_apserver.prepareStatement ( "SELECT SupervisorID FROM GroupSupervisors WHERE GroupID=?" );
            ps_group_supervisors.setString ( 1, group_id );

            ResultSet results_supervisors = ps_group_supervisors.executeQuery();
            Vector <String> supervisor_ids = new Vector <String> ();
            while ( results_supervisors.next() )
            {
                supervisor_key = results_supervisors.getString ( 1 );
                ps_user_exist.setString ( 1, supervisor_key );
                ResultSet exist = ps_user_exist.executeQuery();

                if ( exist.next() && ( exist.getInt(1) > 0 ))
                {
                    supervisor_ids.add ( supervisor_key );
                }
                else
                {
                    _log.error ( "Could not find supervisor ID: " + supervisor_key );
                }
            }

            StringBuilder supervisors_list = new StringBuilder();
            String supervisor_list         = null;
            if ( supervisor_ids.size() > 0 )
            {
                for ( int counter = 0; counter < supervisor_ids.size(); counter++ )
                {
                    supervisors_list.append ( supervisor_ids.elementAt(counter) );
                    if ( counter + 1 < supervisor_ids.size() )
                    {
                        supervisors_list.append ( "," );
                    }
                }

                supervisor_list = supervisors_list.toString();
            }
            else
            {
                supervisor_list = "11312";
            }


            ps.setString ( 1, group_id   );     //  External key
            ps.setString ( 2, group_id   );     //  group name
            ps.setString ( 3, group_desc );     //  group description
            ps.setString ( 4, "ACTIVE"   );     //  status (ACTIVE, INACTIVE)
            ps.setString ( 5, "Y"        );     //  use default devices? (Y, N)
            ps.setString ( 6, supervisor_list );    //  supervisors list

            int num_updated = ps.executeUpdate();

            _log.info ( "Num updated: " + num_updated );

            num_groups++;
        }
        _log.info ( "Num groups processed: " + num_groups );
        //*/
    }
};

