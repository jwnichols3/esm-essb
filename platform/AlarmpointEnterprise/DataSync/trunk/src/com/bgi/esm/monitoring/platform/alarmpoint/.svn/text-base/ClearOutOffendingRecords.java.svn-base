package com.bgi.esm.monitoring.platform.alarmpoint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class ClearOutOffendingRecords
{
    final private static Logger _log             = Logger.getLogger ( ClearOutOffendingRecords.class );

    private static Connection con                = null;
    private static Statement stmt                = null;
    private static ResultSet results_bad_records = null;
    private static Properties default_values     = null;

    public static void main ( String args[] ) throws SQLException, ClassNotFoundException, IOException
    {
        default_values = Common.defaultProperties();
        con            = Common.getDestConnection();
        stmt           = con.createStatement();

        clearOutBadUsers();

        clearOutBadEmailDevices();

        clearOutOldDevices();
    }

    private static void clearOutBadUsers() throws SQLException, ClassNotFoundException, IOException
    {
        int num_cleared = 0;
        PreparedStatement ps = con.prepareStatement ( "UPDATE recipients SET sync_external_key=? WHERE sync_external_key=?" );

        results_bad_records  = stmt.executeQuery ( "SELECT ALARMPOINT3.DS_USERS.USER_ID, ALARMPOINT3.DS_USERS.EXTERNAL_KEY, ALARMPOINT3.RECIPIENTS.SYNC_EXTERNAL_KEY FROM ALARMPOINT3.DS_USERS INNER JOIN ALARMPOINT3.WEB_CREDS ON ALARMPOINT3.DS_USERS.WEB_LOGIN = ALARMPOINT3.WEB_CREDS.NAME INNER JOIN ALARMPOINT3.PERSONS ON ALARMPOINT3.PERSONS.PERSON_ID = ALARMPOINT3.WEB_CREDS.WEB_CRED_ID INNER JOIN ALARMPOINT3.RECIPIENTS ON ALARMPOINT3.WEB_CREDS.WEB_CRED_ID = ALARMPOINT3.RECIPIENTS.RECIPIENT_ID WHERE (ALARMPOINT3.DS_USERS.ALARMPOINT_ACTION = 'F')" );

        while ( results_bad_records.next() )
        {
            try
            {
                ps.setString ( 1, results_bad_records.getString ( 2 ) );
                ps.setString ( 2, results_bad_records.getString ( 3 ) );

                int num_updated = ps.executeUpdate();

                _log.info ( "Num records cleared out for " + results_bad_records.getString ( 1 ) + ": " + num_updated );

                num_cleared += num_updated;
            }
            catch ( SQLException exception )
            {
                _log.error ( "Error handling: " + results_bad_records.getString ( 3 ) );
            }
        }

        _log.info ( "Total cleared: " + num_cleared );
    }

    private static void clearOutBadEmailDevices() throws SQLException, ClassNotFoundException, IOException
    {
        if ( null != con )
        {
            con = Common.getDestConnection();
        }
    }

    /**
     *  Clears out the old devices by
     *    1.  Retrieving the offending record
     *    2.  Inserting/creating the proper record for removal 
     */
    private static void clearOutOldDevices() throws SQLException, ClassNotFoundException, IOException
    {
        int num_cleared             = 0;
        int num_updated             = 0;
        con                         = Common.getDestConnection();
        stmt                        = con.createStatement();
        PreparedStatement ps_insert = null;
        PreparedStatement ps_update = null;

        //  Email devices
        results_bad_records                  = stmt.executeQuery ( default_values.getProperty ( "queries.remove_existing_devices.email" ) );
        ps_update                            = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.update" ) );
        ps_insert                            = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.insert" ) );

        String entity_id                     = null;  //  Unique identifier for this email device
        String entity_status                 = null;  //  Must be a "y" or "n" (case insensitive)
        String user_key                      = null;  //  External key for the User who owns this device 
        String target_name                   = null;  //  Must match USER_ID for the associated user
        String email_address                 = null;  //  The email address for this device
        String external_key                  = null;  //  Must match up with the "dvc_id" filed in the "email_dvc_dtl" table

        String default_device_name           = default_values.getProperty ( "default_values.email.device_name" );
        String default_service_provider_name = default_values.getProperty ( "default_values.email.service_provider_name" );
        String default_device                = default_values.getProperty ( "default_values.email.default_device" );
        String default_alarmpoint_action     = default_values.getProperty ( "default_values.email.alarmpoint_action" );
        String default_external_key_prefix   = default_values.getProperty ( "default_values.email.external_key_prefix" );
        Integer default_delay                = new Integer ( default_values.getProperty ( "default_values.email.delay" ) );
        Integer default_device_order         = new Integer ( default_values.getProperty ( "default_values.email.device_order" ) );


        while ( results_bad_records.next() )
        {
            entity_status   = "y";
            user_key        = results_bad_records.getString ( "target_name" );
            target_name     = results_bad_records.getString ( "target_name" );
            email_address   = results_bad_records.getString ( "address" );
            external_key    = results_bad_records.getString ( "sync_external_key" );

            ps_update.setString (  1, entity_status                   );
            ps_update.setString (  2, user_key                        );
            ps_update.setString (  3, target_name                     );
            ps_update.setString (  4, email_address                   );
            ps_update.setString (  5, default_device_name             );
            ps_update.setString (  6, default_service_provider_name   );
            ps_update.setInt    (  7, default_delay.intValue()        );
            ps_update.setInt    (  8, default_device_order.intValue() );
            ps_update.setString (  9, default_device                  );
            ps_update.setString ( 10, "R"                             );
            ps_update.setString ( 11, external_key                    );

            num_updated = ps_update.executeUpdate();

            if ( 0 == num_updated )
            {
                ps_insert.setString (  1, external_key                    );
                ps_insert.setString (  2, entity_status                   );
                ps_insert.setString (  3, user_key                        );
                ps_insert.setString (  4, target_name                     );
                ps_insert.setString (  5, email_address                   );
                ps_insert.setString (  6, default_device_name             );
                ps_insert.setString (  7, default_service_provider_name   );
                ps_insert.setInt    (  8, default_delay.intValue()        );
                ps_insert.setInt    (  9, default_device_order.intValue() );
                ps_insert.setString ( 10, default_device                  );
                ps_insert.setString ( 11, default_alarmpoint_action       );

                num_updated = ps_insert.executeUpdate();
            }

            num_cleared += num_updated;
        }

        _log.info ( "Num email devices scheduled for removal: " + num_cleared );

        //  Voice devices
        /*
        ps_update            = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.update" ) );
        ps_insert            = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.insert" ) );

        num_cleared = 0;
        results_bad_records  = stmt.executeQuery ( default_values.getProperty ( "queries.remove_existing_devices.voice" ) );

        String phone_code_area   = null;
        String phone_code_number = null;
        String default_extension = "";

        while ( results_bad_records.next() )
        {
            entity_status     = "y";
            user_key          = results_bad_records.getString ( "target_name" );
            target_name       = results_bad_records.getString ( "target_name" );
            email_address     = results_bad_records.getString ( "address" );
            external_key      = results_bad_records.getString ( "sync_external_key" );

            phone_code_area   = results_bad_records.getString ( "area_code" );
            phone_code_number = results_bad_records.getString ( "phone_num" );

            ps_update.setString (  1, phone_code_area               );
            ps_update.setString (  2, phone_code_number             );
            ps_update.setString (  3, default_extension             );
            ps_update.setString (  4, user_login                    );
            ps_update.setString (  5, external_key                  );
            ps_update.setString (  6, default_device_name           );
            ps_update.setString (  7, default_service_provider_name );
            ps_update.setString (  8, default_status                );
            ps_update.setString (  9, default_device                );
            ps_update.setString ( 10, default_device_order          );
            ps_update.setString ( 11, default_delay                 );
            ps_update.setString ( 12, default_alarmpoint_action     );
            ps_update.setString ( 13, default_external_key_prefix + external_key );

            int num_updated = ps_update.executeUpdate();

            if ( 0 == num_updated )
            {
                ps_insert.setString (  1, default_external_key_prefix + external_key );
                ps_insert.setString (  2, phone_code_area               );
                ps_insert.setString (  3, phone_code_number             );
                ps_insert.setString (  4, default_extension             );
                ps_insert.setString (  5, user_login                    );
                ps_insert.setString (  6, external_key                  );
                ps_insert.setString (  7, default_device_name           );
                ps_insert.setString (  8, default_service_provider_name );
                ps_insert.setString (  9, default_status                );
                ps_insert.setString ( 10, default_device                );
                ps_insert.setString ( 11, default_device_order          );
                ps_insert.setString ( 12, default_delay                 );
                ps_insert.setString ( 13, default_alarmpoint_action     );

                num_updated = ps_insert.executeUpdate();
            }
        }
        //*/
    }
};
