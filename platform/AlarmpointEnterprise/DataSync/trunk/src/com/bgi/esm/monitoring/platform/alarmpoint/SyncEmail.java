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

public class SyncEmail
{
    final private static Logger _log = Logger.getLogger ( SyncEmail.class );

    public static void synchronizeDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con_src                            = Common.getSourceConnection();
        Connection con                                = Common.getDestConnection();
        ResultSet results                             = Common.retrievePeople();
        ResultSet results_igi                         = null;
        Properties default_values                     = Common.defaultProperties();
        //PreparedStatement ps_insert                   = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + "ds_email_devices ( EXTERNAL_KEY, STATUS, USER_KEY, TARGET_NAME, ADDRESS, DEVICE_NAME, SERVICE_PROVIDER_NAME, DELAY, DEVICE_ORDER, DEFAULT_DEVICE, ALARMPOINT_ACTION ) VALUES ( ?, ?, ?, ?, ?, Work Email', 'BGI E-Mail', 0, 1, 'N', 'Y' )" );
        PreparedStatement ps_insert                   = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.insert" ) );
        PreparedStatement ps_update                   = con.prepareStatement ( default_values.getProperty ( "queries.sync_email_devices.update" ) );
        //PreparedStatement ps_insert                   = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + "ds_email_devices ( EXTERNAL_KEY, STATUS, USER_KEY, TARGET_NAME, ADDRESS, DEVICE_NAME, SERVICE_PROVIDER_NAME, DELAY, DEVICE_ORDER, DEFAULT_DEVICE, ALARMPOINT_ACTION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
        //PreparedStatement ps_update                   = con.prepareStatement ( "UPDATE " + Common.getDestDatabaseOwner() + "ds_email_devices SET STATUS=?, USER_KEY=?, TARGET_NAME=?, ADDRESS=?, DEVICE_NAME=?, SERVICE_PROVIDER_NAME=?, DELAY=?, DEVICE_ORDER=?, DEFAULT_DEVICE=?, ALARMPOINT_ACTION=? WHERE EXTERNAL_KEY=?" );
        PreparedStatement ps_retrieve_email_device_id = con.prepareStatement ( "SELECT dvc_id FROM EMAIL_DVC_DTL WHERE address LIKE ?" );
        PreparedStatement ps_retrieve_igi             = con_src.prepareStatement ( "SELECT attr_value FROM people_attributes WHERE people_id IN ( SELECT people_id FROM people WHERE people_nm=? ) AND attr_nm='employee_id'" );

        String entity_id                              = null;  //  Unique identifier for this email device
        String entity_status                          = null;  //  Must be a "y" or "n" (case insensitive)
        String user_key                               = null;  //  External key for the User who owns this device 
        String target_name                            = null;  //  Must match USER_ID for the associated user
        String email_address                          = null;  //  The email address for this device
        String external_key                           = null;  //  Must match up with the "dvc_id" filed in the "email_dvc_dtl" table
        String phone_code_country                     = null;
        String phone_code_area                        = null;
        String phone_number                           = null;

        //er() + "ds_email_devices SET STATUS=?, USER_KEY=?, TARGET_NAME=?, ADDRESS=?, DEVICE_NAME='Work Email', SERVICE_PROVIDER_NAME='BGI E-Mail', DELAY=0, DEVICE_ORDER=1, DEFAULT_DEVICE='N', ALARMPOINT_ACTION='Y' WHERE EXTERNAL_KEY=?" );
        String default_device_name                    = default_values.getProperty ( "default_values.email.device_name" );
        String default_service_provider_name          = default_values.getProperty ( "default_values.email.service_provider_name" );
        String default_device                         = default_values.getProperty ( "default_values.email.default_device" );
        String default_alarmpoint_action              = default_values.getProperty ( "default_values.email.alarmpoint_action" );
        String default_external_key_prefix            = default_values.getProperty ( "default_values.email.external_key_prefix" );
        Integer default_delay                         = new Integer ( default_values.getProperty ( "default_values.email.delay" ) );
        Integer default_device_order                  = new Integer ( default_values.getProperty ( "default_values.email.device_order" ) );

        int num_records      = 0;

        while ( results.next() )
        {
            entity_id     = results.getString ( "EntityID"       );
            entity_status = results.getString ( "EntityStatus"   );
            target_name   = results.getString ( "EntityFirstName" ) + " " + results.getString ( "EntityLastName" );
            email_address = results.getString ( "EntityBumped"   );
            user_key      = results.getString ( "EntityUserName" );
            phone_number  = results.getString ( "EntityPhoneNumber" );

            external_key  = user_key;

            //  Retrieve the device ID
            ps_retrieve_email_device_id.setString ( 1, email_address );
            ResultSet retrieve_device_id = ps_retrieve_email_device_id.executeQuery();

            ps_retrieve_igi.setString ( 1, user_key );
            results_igi = ps_retrieve_igi.executeQuery();

            if ( !results_igi.next() )
            {
                //  If we don't have an IGI, then this is not a valid record
                continue;
            }
            
            Integer igi = new Integer ( results_igi.getString ( 1 ) );
            user_key    = igi.toString();

            //  If no email address is detected in this record, then we can't sync
            if ( null == email_address )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Null email address detected for ( EntityId, EntityStatus, EntityLoginName, Targetname ) = ( " );
                message.get().append ( entity_id );
                message.get().append ( ", " );
                message.get().append ( entity_status );
                message.get().append ( ", " );
                message.get().append ( user_key );
                message.get().append ( ", " );
                message.get().append ( target_name );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );

                continue;
            }

            int arg_counter = 1;

            ps_update.setString ( arg_counter++, entity_status                   );
            ps_update.setString ( arg_counter++, user_key                        );
            //ps_update.setString ( arg_counter++, target_name                     );
            ps_update.setString ( arg_counter++, email_address                   );
            ps_update.setString ( arg_counter++, default_device_name             );
            //  Switch on the country code of the phone number to assign the closest email server
            if ( null != phone_number )
            {
                //  Create a default StringTokenizer on "phone_number", which will split by whitespace (default behavior)
                WeakReference <StringTokenizer> tokens = new WeakReference <StringTokenizer> ( new StringTokenizer ( phone_number ) );
                phone_code_country = tokens.get().nextToken();
                phone_code_area    = tokens.get().nextToken();

                if ( phone_code_country.equals ( "+1" ) )
                {
                    _log.info ( "US User detected" );
                    ps_update.setString ( arg_counter++, "BGI Mail US" );
                }
                else if ( phone_code_country.equals ( "+44" ) )
                {
                    _log.info ( "London User detected" );
                    ps_update.setString ( arg_counter++, "BGI Mail UK" );
                }
                else if ( phone_code_country.equals ( "+81" ) )
                {
                    _log.info ( "Tokyo User detected" );
                    ps_update.setString ( arg_counter++, "BGI Mail TKY" );
                }
                else if ( phone_code_country.equals ( "+61" ) )
                {
                    _log.info ( "Sydney User detected" );
                    ps_update.setString ( arg_counter++, "BGI Mail SYD" );
                }
                else
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "Invalid country code detected for ( EntityId, EntityStatus, EntityLoginName, Targetname, Country Code, Phone Number ) = ( " );
                    message.get().append ( entity_id );
                    message.get().append ( ", " );
                    message.get().append ( entity_status );
                    message.get().append ( ", " );
                    message.get().append ( user_key );
                    message.get().append ( ", " );
                    message.get().append ( target_name );
                    message.get().append ( ", " );
                    message.get().append ( phone_code_country );
                    message.get().append ( ", " );
                    message.get().append ( phone_number );
                    message.get().append ( " )" );

                    _log.error ( message.get().toString() );

                    ps_update.setString ( arg_counter++, default_service_provider_name );
                }
            }
            else
            {
                // Could not detect phone number information.  Default to BGI Mail
                ps_update.setString ( arg_counter++, default_service_provider_name   );
            }

            ps_update.setInt    ( arg_counter++, default_delay.intValue()        );
            ps_update.setInt    ( arg_counter++, default_device_order.intValue() );
            ps_update.setString ( arg_counter++, default_device                  );
            ps_update.setString ( arg_counter++, default_alarmpoint_action       );
            ps_update.setString ( arg_counter++, default_external_key_prefix + user_key );
            int num_updated = ps_update.executeUpdate();

            //  The email address doesn't exist, so we add it into the staging table
            if ( 0 == num_updated )
            {
                arg_counter = 1;
                ps_insert.setString ( arg_counter++, default_external_key_prefix + user_key );
                ps_insert.setString ( arg_counter++, entity_status                   );
                ps_insert.setString ( arg_counter++, user_key                        );
                //ps_insert.setString ( arg_counter++, target_name                     );
                ps_insert.setString ( arg_counter++, email_address                   );
                ps_insert.setString ( arg_counter++, default_device_name             );
                if ( null != phone_number )
                {
                    //  Create a default StringTokenizer on "phone_number", which will split by whitespace (default behavior)
                    WeakReference <StringTokenizer> tokens = new WeakReference <StringTokenizer> ( new StringTokenizer ( phone_number ) );
                    phone_code_country = tokens.get().nextToken();
                    phone_code_area    = tokens.get().nextToken();

                    if ( phone_code_country.equals ( "+1" ) )
                    {
                        _log.info ( "US User detected" );
                        ps_insert.setString ( arg_counter++, "BGI Mail US" );
                    }
                    else if ( phone_code_country.equals ( "+44" ) )
                    {
                        _log.info ( "London User detected" );
                        ps_insert.setString ( arg_counter++, "BGI Mail UK" );
                    }
                    else if ( phone_code_country.equals ( "+81" ) )
                    {
                        _log.info ( "Tokyo User detected" );
                        ps_insert.setString ( arg_counter++, "BGI Mail TKY" );
                    }
                    else if ( phone_code_country.equals ( "+61" ) )
                    {
                        _log.info ( "Sydney User detected" );
                        ps_insert.setString ( arg_counter++, "BGI Mail SYD" );
                    }
                    else
                    {
                        _log.info ( "Defaulting to US mail server because location unknown with country code: " + phone_code_country );
                        ps_insert.setString ( arg_counter++, default_service_provider_name );
                    }
                }
                else
                {
                    // Could not detect phone number information.  Default to BGI Mail
                    ps_insert.setString ( arg_counter++, default_service_provider_name   );
                }

                //ps_insert.setString ( arg_counter++, default_service_provider_name   );
                ps_insert.setInt    ( arg_counter++, default_delay.intValue()        );
                ps_insert.setInt    ( arg_counter++, default_device_order.intValue() );
                ps_insert.setString ( arg_counter++, default_device                  );
                ps_insert.setString ( arg_counter++, default_alarmpoint_action       );

                num_updated = ps_insert.executeUpdate();
            }

            num_records++;

            if ( num_records % 100 == 0 )
            {
                _log.info ( "Number of records to add into database: " + num_records );
            }
 
        }

       _log.info ( "Number of records to add into database: " + num_records );
    }
};

