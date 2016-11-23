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

public class SyncTeams
{
    final private static Logger _log = Logger.getLogger ( SyncTeams.class );

    public static void synchronizeDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con              = Common.getDestConnection();
        
        Properties default_values   = Common.defaultProperties();
        Connection con_apserver     = Common.getAPServerConnection();
        Statement stmt_teams        = con_apserver.createStatement();
        ResultSet results_teams     = stmt_teams.executeQuery ( "SELECT GroupID, CoverageName, COUNT(*) AS Expr1 FROM PersonsByGroups GROUP BY GroupID, CoverageName" );

        String group_name           = null;
        int num_updated             = 0;

        //  Delete old records
        PreparedStatement ps_delete = con.prepareStatement ( "DELETE FROM " + Common.getDestDatabaseOwner() + ".ds_teams" );
        num_updated = ps_delete.executeUpdate();
        _log.info ( "Num old records removed: " + num_updated );

        PreparedStatement ps        = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + ".ds_teams ( EXTERNAL_KEY, GROUP_KEY, DESCRIPTION, USE_DEFAULT_DEVICES, SUB_TARGET_NAME, ESCALATION_SCHEDULE, IS_ROUND_ROBIN, ADD_24X7_COVERAGE, ALARMPOINT_ACTION ) VALUES ( ?, ?, ?, ?, ?, '10', 'N', 'Y', 'Y' )" );
        PreparedStatement ps_user_exist = con.prepareStatement ( "SELECT count(*) FROM " + Common.getDestDatabaseOwner() + ".recipients WHERE sync_external_key=?" );
        String phone_code_country   = null;
        String supervisor_key       = null;
        String group_desc           = null;

        //PreparedStatement ps_select_members = con.prepareStatement ( "SELECT count(*) FROM " + Common.getDestDatabaseOwner() + ".ds_team_memberships WHERE member_sequence_number=? AND team_key=? AND member_key=?" );
        PreparedStatement ps_select_members = con.prepareStatement ( "SELECT count(*) FROM " + Common.getDestDatabaseOwner() + ".ds_team_memberships WHERE team_key=? AND member_key=?" );
        PreparedStatement ps_team_member    = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + ".ds_team_memberships ( team_key, member_key, member_type, member_sub_type, member_sequence_number, alarmpoint_action ) VALUES ( ?, ?, ?, ?, ?, 'Y' )" );
        PreparedStatement ps_update_member  = con.prepareStatement ( "UPDATE " + Common.getDestDatabaseOwner() + ".ds_team_memberships SET alarmpoint_action='y' WHERE member_sequence_number=? AND team_key=? AND member_key=?" );

        int num_groups = 0;

        String previous_group_id = "";
        int team_counter = 1;
        while ( results_teams.next() )
        {
            String group_id       = results_teams.getString ( "GroupID" );
            String group_coverage = results_teams.getString ( "CoverageName" );
            String external_key   = group_id + "-" + group_coverage;

            if ( !group_id.equals ( previous_group_id ) )
            {
                team_counter = 1;
                previous_group_id = group_id;
            }

            //  Construct the supervisors/users list
            //PreparedStatement ps_group_supervisors = con_apserver.prepareStatement ( "SELECT SupervisorID FROM GroupSupervisors WHERE GroupID=?" );
            PreparedStatement ps_group_supervisors = con_apserver.prepareStatement ( "SELECT PersonID FROM PersonsByGroups WHERE GroupID=? AND CoverageName=?" );
            ps_group_supervisors.setString ( 1, group_id       );
            ps_group_supervisors.setString ( 2, group_coverage );

            ResultSet results_supervisors = ps_group_supervisors.executeQuery();
            Vector <String> supervisor_ids = new Vector <String> ();
            int member_counter = 1;
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
                //( team_key, member_key, member_type, member_sub_type, member_sequence_number ) VALUES ( ?, ?, 'Full Access User', ? ? )" );
                //ps_update_member = con.prepareStatement ( "UPDATE " + Common.getDestDatabaseOwner() + ".ds_team_memberships SET member_sequence_number=? WHERE team_key=? AND member_key=?" );
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "Attempting to set membership ( External Key, Supervisor Key, Member Counter ) = ( " );
                    message.get().append ( member_counter );
                    message.get().append ( ", " );
                    message.get().append ( external_key );
                    message.get().append ( ", " );
                    message.get().append ( supervisor_key );
                    message.get().append ( " )" );

                    _log.info ( message.get().toString() );
                }

                ps_update_member.setInt ( 1, member_counter );
                ps_update_member.setString ( 2, external_key );
                ps_update_member.setString ( 3, supervisor_key );
                num_updated = ps_update_member.executeUpdate();

                if ( 0 == num_updated )
                {
                    ps_select_members.setString ( 1, external_key );
                    ps_select_members.setString ( 2, supervisor_key );
                    //ps_select_members.setInt    ( 3, member_counter );
                    ResultSet results_select = ps_select_members.executeQuery();
                    results_select.next();

                    if ( results_select.getInt ( 1 ) < 1 )
                    {
                        _log.info ( "Parameters!" );
                        _log.info ( "\t1: " + external_key );
                        _log.info ( "\t2: " + supervisor_key );
                        _log.info ( "\t5: " + member_counter );
                        ps_team_member.setString ( 1, external_key );
                        ps_team_member.setString ( 2, supervisor_key );
                        ps_team_member.setString ( 3, "PERSON"       ); // should be left blank because this is a person
                        ps_team_member.setString ( 4, ""             ); // should be left blank because this is a person
                        ps_team_member.setInt    ( 5, member_counter );
               
                        try
                        { 
                            _log.info ( "Num memberships inserted: " + ps_team_member.executeUpdate() );
                        }
                        catch ( SQLException exception )
                        {
                            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( "Could not insert ( TeamName, Supervisor, MemberCounter ) = ( " );
                            message.get().append ( external_key );
                            message.get().append ( ", " );
                            message.get().append ( supervisor_key );
                            message.get().append ( ", " );
                            message.get().append ( member_counter );
                            message.get().append ( " )" );
                            _log.error ( message.get().toString() );
                        }
                    }
                }
                else
                {
                    _log.info ( "Num memberships updated: " + num_updated );
                }
                
    
                member_counter++;
                _log.info ( "Member counter: " + member_counter );
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

            group_desc = (null != group_desc)? group_desc : "Default group description";
            /*
        EXTERNAL_KEY, 
        GROUP_KEY, 
		DESCRIPTION, 
		USE_DEFAULT_DEVICES, 
		SUB_TARGET_NAME, 
		IS_ROUND_ROBIN, 
		ADD_24X7_COVERAGE, 
		ALARMPOINT_ACTION ) VALUES ( ?, 
		?, 
		?, 
		?, 
		?, 
		?, 
		'N', 
		'Y', 
		'Y' )" );
//*/
            ps.setString ( 1, external_key );     //  External key
            ps.setString ( 2, group_id     );     //  group name
            ps.setString ( 3, group_desc   );     //  group description
            ps.setString ( 4, "Y"          );     //  status (ACTIVE, INACTIVE)
            //ps.setString ( 5, Integer.toString ( team_counter ) );     //  use default devices? (Y, N)
            ps.setString ( 5, group_coverage );
            //ps.setString ( 5, group_coverage );     //  use default devices? (Y, N)
            // EXTERNAL_KEY, GROUP_KEY, DESCRIPTION, USE_DEFAULT_DEVICES, SUB_TARGET_NAME, 
            // ESCALATION_SCHEDULE, IS_ROUND_ROBIN, ADD_24X7_COVERAGE, ALARMPOINT_ACTION 
            // ?, ?, ?, ?, ?, 
            // '10', 'N', 'Y', 'Y' )"

            team_counter++;

            num_updated = ps.executeUpdate();

            _log.info ( "Num updated: " + num_updated );

            num_groups++;
        }
        _log.info ( "Num groups processed: " + num_groups );
        //*/
    }
};

