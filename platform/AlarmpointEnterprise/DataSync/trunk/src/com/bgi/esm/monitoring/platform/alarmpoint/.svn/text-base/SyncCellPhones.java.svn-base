package com.bgi.esm.monitoring.platform.alarmpoint;

import java.lang.ref.WeakReference;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class SyncCellPhones
{
    final private static Logger _log = Logger.getLogger ( SyncVoiceDevices.class );

    public static void synchronizeDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con                       = Common.getDestConnection();
        Connection con_src                   = Common.getSourceConnection();
        ResultSet results                    = Common.retrievePeople();
        Properties default_values            = Common.defaultProperties();
        PreparedStatement ps_insert          = con.prepareStatement ( default_values.getProperty ( "queries.sync_voice_devices.insert" ) );
        PreparedStatement ps_update          = con.prepareStatement ( default_values.getProperty ( "queries.sync_voice_devices.update" ) );
        //PreparedStatement ps_insert          = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + "ds_voice_devices ( EXTERNAL_KEY, AREA_CODE, PHONE_NUMBER, EXTENSION, TARGET_NAME, USER_KEY, DEVICE_NAME, SERVICE_PROVIDER_NAME, STATUS, DEFAULT_DEVICE, DEVICE_ORDER, DELAY, ALARMPOINT_ACTION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
        //PreparedStatement ps_update          = con.prepareStatement ( "UPDATE " + Common.getDestDatabaseOwner() + "ds_voice_devices SET AREA_CODE=?, PHONE_NUMBER=?, EXTENSION=?, TARGET_NAME=?, USER_KEY=?, DEVICE_NAME=?, SERVICE_PROVIDER_NAME=?, STATUS=?, DEFAULT_DEVICE=?, DEVICE_ORDER=?, DELAY=?, ALARMPOINT_ACTION=? WHERE EXTERNAL_KEY=?" );

        String entity_id                     = null;  //  Unique identifier for this email device
        String user_login                    = null;
        String user_phone                    = null;
        String phone_code_country            = null;  //  the +XX number at the beginning denoting the country coe
        String phone_code_area               = null;  //  the next space-delimited token, denoting the area code
        String phone_code_number             = null;  //  the rest of the phone number, denoting the actual 

        String external_key                  = null;  //  The unique identifier for this device
        String area_code                     = null;  //  The area code for this phone number
        String phone_number                  = null;  //  The phone number
        String extension                     = null;  //  The extension for this phone number
        String target_name                   = null;  //  Must match the USER_ID that this device is associated with
        String user_key                      = null;  //  External key for the user who owns this device, the IGI

        //  These values are documented in /src/default_values.properties
        String default_device_name           = default_values.getProperty ( "default_values.mobile.device_name"           );
        String default_service_provider_name = default_values.getProperty ( "default_values.mobile.service_provider_name" );
        String default_status                = default_values.getProperty ( "default_values.mobile.status"                );
        String default_device                = default_values.getProperty ( "default_values.mobile.default_device"        );
        String default_delay                 = default_values.getProperty ( "default_values.mobile.delay"                 );
        String default_extension             = default_values.getProperty ( "default_values.mobile.extension"             );
        String default_device_order          = default_values.getProperty ( "default_values.mobile.device_order"          );
        String default_alarmpoint_action     = default_values.getProperty ( "default_values.mobile.alarmpoint_action"     );
        String default_external_key_prefix   = default_values.getProperty ( "default_values.mobile.external_key_prefix"   );

        PreparedStatement ps_retrieve_igi    = con_src.prepareStatement ( "SELECT attr_value FROM people_attributes WHERE people_id IN ( SELECT people_id FROM people WHERE people_nm=? ) AND attr_nm='employee_id'" );
        ResultSet results_igi                = null;
        Integer igi                          = null;

        PreparedStatement ps_retrieve_mobile = con_src.prepareStatement ( "SELECT attr_value FROM people_attributes WHERE people_id IN ( SELECT people_id FROM people WHERE people_nm=? ) AND attr_nm='mobile_number'" );
        ResultSet results_mobile             = null;
  
        int counter = 1; 
        while ( results.next() )
        {
            entity_id      = results.getString ( "EntityID"          );
            user_login     = results.getString ( "EntityLoginName"   );
            //phone_number   = results.getString ( "EntityPhoneNumber" );
            String user_id = results.getString ( "EntityUserName"    );

            ps_retrieve_mobile.setString ( 1, user_id );
            results_mobile = ps_retrieve_mobile.executeQuery();

            if ( !results_mobile.next() )
            {
                continue;
            }

            phone_number   = results_mobile.getString ( 1 );


            ps_retrieve_igi.setString ( 1, user_id );
            results_igi = ps_retrieve_igi.executeQuery();

            if ( !results_igi.next() ) continue;
            igi = new Integer ( results_igi.getString ( 1 ) );

            external_key  = igi.toString();
            counter++;

            if ( null == phone_number )
            {
                continue;
            }

            //  Create a default StringTokenizer on "phone_number", which will split by whitespace (default behavior)
            WeakReference <StringTokenizer> tokens = new WeakReference <StringTokenizer> ( new StringTokenizer ( phone_number ) );

            //  First token is country code with +, i.e +1, +44
            phone_code_country = tokens.get().nextToken();

            //  Next token is area code
            phone_code_area    = tokens.get().nextToken();

            //  The remaining tokens make up the phone number.  We will put "-" between each part
            WeakReference <StringBuilder> temp_phone_number = new WeakReference <StringBuilder> ( new StringBuilder() );
            while ( tokens.get().hasMoreTokens() )
            {
                temp_phone_number.get().append ( tokens.get().nextToken() );

                if ( tokens.get().hasMoreTokens() )
                {
                    temp_phone_number.get().append ( "-" );
                }
            }
            phone_code_number  = temp_phone_number.get().toString();

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Found voice device: ( " );
                message.get().append ( entity_id          );
                message.get().append ( ", " );
                message.get().append ( user_login         );
                message.get().append ( ", " );
                message.get().append ( phone_number       );
                message.get().append ( ", " );
                message.get().append ( phone_code_country );
                message.get().append ( ", " );
                message.get().append ( phone_code_area    );
                message.get().append ( ", " );
                message.get().append ( phone_code_number  );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }

        //PreparedStatement ps_insert          = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + "ds_voice_devices ( EXTERNAL_KEY, AREA_CODE, PHONE_NUMBER, EXTENSION, TARGET_NAME, USER_KEY, DEVICE_NAME, SERVICE_PROVIDER_NAME, STATUS, DEFAULT_DEVICE, DEVICE_ORDER, DELAY, ALARMPOINT_ACTION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
        //DEVICE_NAME=?, SERVICE_PROVIDER_NAME=?, STATUS=?, DEFAULT_DEVICE=?, DEVICE_ORDER=?, DELAY=?, ALARMPOINT_ACTION=? WHERE EXTERNAL_KEY=?" );
            int arg_index = 1;
            ps_update.setString ( arg_index++, phone_code_area               );
            ps_update.setString ( arg_index++, phone_code_number             );
            ps_update.setString ( arg_index++, default_extension             );
            //ps_update.setString ( arg_index++, user_login                    );
            ps_update.setString ( arg_index++, external_key                  );
            ps_update.setString ( arg_index++, default_device_name           );
            ps_update.setString ( arg_index++, default_service_provider_name );
            ps_update.setString ( arg_index++, default_status                );
            ps_update.setString ( arg_index++, "N" ); // default device
            ps_update.setString ( arg_index++, default_device_order          );
            ps_update.setString ( arg_index++, default_delay                 );
            ps_update.setString ( arg_index++, default_alarmpoint_action     );
            ps_update.setString ( arg_index++, default_external_key_prefix + external_key );

            int num_updated = ps_update.executeUpdate();

            if ( 0 == num_updated )
            {
                arg_index = 1;
                ps_insert.setString ( arg_index++, default_external_key_prefix + external_key );
                ps_insert.setString ( arg_index++, phone_code_area               );
                ps_insert.setString ( arg_index++, phone_code_number             );
                ps_insert.setString ( arg_index++, default_extension             );
                //ps_insert.setString ( arg_index++, user_login                    );
                ps_insert.setString ( arg_index++, external_key                  );
                ps_insert.setString ( arg_index++, default_device_name           );
                ps_insert.setString ( arg_index++, default_service_provider_name );
                ps_insert.setString ( arg_index++, default_status                );
                ps_insert.setString ( arg_index++, "N" ); // default device
                ps_insert.setString ( arg_index++, default_device_order          );
                ps_insert.setString ( arg_index++, default_delay                 );
                ps_insert.setString ( arg_index++, default_alarmpoint_action     );

                num_updated = ps_insert.executeUpdate();
            }
        }
    }
};

