package com.bgi.esm.monitoring.platform.alarmpoint;

import java.lang.ref.WeakReference;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class SyncPeople
{
    final private static Logger _log = Logger.getLogger ( SyncPeople.class );

    //  SQL Statement to retrieve employee Id based on user login
    //  "SELECT * FROM shareit_copy.people_attributes p WHERE people_id IN ( SELECT people_id FROM shareit_copy.people WHERE people_nm='linden' ) AND attr_nm='employee_id'"
    private static PreparedStatement ps_retrieve_igi = null;

    public static void synchronizeDatabase() throws SQLException, ClassNotFoundException, IOException
    {
        Connection con_src          = Common.getSourceConnection();
        Connection con              = Common.getDestConnection();
        ResultSet results           = Common.retrievePeople();
        Properties default_values   = Common.defaultProperties();
        PreparedStatement ps_insert = con.prepareStatement ( "INSERT INTO " + Common.getDestDatabaseOwner() + "ds_users ( EXTERNAL_KEY, STATUS, SITE_NAME, USER_ID, FIRST_NAME, LAST_NAME, ROLE_LIST, WEB_LOGIN, PHONE_LOGIN, PHONE_PASSWORD, WEB_LOGIN_TYPE, ALARMPOINT_ACTION, WEB_PASSWORD, LDAP_DOMAIN ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
        PreparedStatement ps_update = con.prepareStatement ( "UPDATE " + Common.getDestDatabaseOwner() + "ds_users SET STATUS=?, SITE_NAME=?, USER_ID=?, FIRST_NAME=?, LAST_NAME=?, ROLE_LIST=?, WEB_LOGIN=?, PHONE_LOGIN=?, PHONE_PASSWORD=?, WEB_LOGIN_TYPE=?, ALARMPOINT_ACTION=?, WEB_PASSWORD=?, LDAP_DOMAIN=? WHERE EXTERNAL_KEY=?" );

        ResultSetMetaData md        = results.getMetaData(); 
        int num_columns             = md.getColumnCount();

        _log.info ( "Database tables" );
        
        _log.info ( "Database column names: " );
        for ( int counter = 1; counter <= num_columns; counter++ )
        {
            _log.info ( "\t" + md.getColumnName ( counter ) );
        }

        String entity_id          = null;
        String entity_status      = null;
        String building           = null;
        String user_id            = null;
        String first_name         = null;
        String last_name          = null;
        String phone_code_country = null;  //  the +XX number at the beginning denoting the country coe
        String phone_code_area    = null;  //  the next space-delimited token, denoting the area code
        String phone_code_number  = null;  //  the rest of the phone number, denoting the actual 
        String phone_number       = null;

        String default_password          = default_values.getProperty ( "default_values.people.default_password"  ); 
        String default_web_login_type    = default_values.getProperty ( "default_values.people.web_login_type"    );
        String default_role_list         = default_values.getProperty ( "default_values.people.role_list"         );
        String default_alarmpoint_action = default_values.getProperty ( "default_values.people.alarmpoint_action" );
        String default_ldap_domain       = default_values.getProperty ( "default_values.people.ldap_domain"       );

        _log.info ( "Appending user ( EntityID, EntityStatus, Building, User_ID, First_Name, Last_Name )" );

        int num_records      = 0;

        ps_retrieve_igi      = con_src.prepareStatement ( "SELECT attr_value FROM people_attributes WHERE people_id IN ( SELECT people_id FROM people WHERE people_nm=? ) AND attr_nm='employee_id'" );
        ResultSet results_igi= null;

        Vector <String> building_codes = new Vector <String> ();
        String token = null;

        while ( results.next() )
        {
            entity_id        = results.getString ( "EntityId"        );
            entity_status    = results.getString ( "EntityStatus"    );
            building         = results.getString ( "Building"        );
            user_id          = results.getString ( "EntityUserName"  );
            first_name       = results.getString ( "EntityFirstName" );
            last_name        = results.getString ( "EntityLastName"  );
            phone_number     = results.getString ( "EntityPhoneNumber" );

            if ( null == phone_number )
            {
                continue;
            }
            phone_number = phone_number.replaceAll ( "\\+ ", "\\+" );

            //  Create a default StringTokenizer on "phone_number", which will split by whitespace (default behavior)
            StringTokenizer tokens = new StringTokenizer ( phone_number );

            //  First token is country code with +, i.e +1, +44
            phone_code_country = tokens.nextToken();
            phone_code_country = phone_code_country.replaceAll ( "\\+ ", "\\+" ).trim();

            //  Next token is area code
            if ( null != tokens )
            {
                phone_code_area    = tokens.nextToken();
            }

            //  The remaining tokens make up the phone number.  We will put "-" between each part
            WeakReference <StringBuilder> temp_phone_number = new WeakReference <StringBuilder> ( new StringBuilder() );
            while ( tokens.hasMoreTokens() )
            {
                temp_phone_number.get().append ( tokens.nextToken() );

                if ( tokens.hasMoreTokens() )
                {
                    temp_phone_number.get().append ( "-" );
                }
            }
            phone_code_number  = temp_phone_number.get().toString();

 
            if ( !building_codes.contains ( building ) )
            {
                building_codes.add ( building );
            }

            if ( null == first_name )
            {
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "Null first name detected for ( EntityId, EntityStatus, Building, Username ) = ( " );
                    message.get().append ( entity_id );
                    message.get().append ( ", " );
                    message.get().append ( entity_status );
                    message.get().append ( ", " );
                    message.get().append ( building );
                    message.get().append ( ", " );
                    message.get().append ( user_id );
                    message.get().append ( " )" );

                    _log.info ( message.get().toString() );
                }
                
                continue;
            }
            if ( null == last_name )
            {
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "Null last name detected for ( EntityId, EntityStatus, Building, Username ) = ( " );
                    message.get().append ( entity_id );
                    message.get().append ( ", " );
                    message.get().append ( entity_status );
                    message.get().append ( ", " );
                    message.get().append ( building );
                    message.get().append ( ", " );
                    message.get().append ( user_id );
                    message.get().append ( " )" );

                    _log.info ( message.get().toString() );
                }

                continue;
            }

            String site_codes[] = 
            {
                "unk", "tob", "lon", "syg", "nym", "sng", "bos", "syd", "rcp", "tok", "sfo", "sdc", "ldj", "sya", "ldl", "ams", "lds", "yok"
            };

            String site_names[] =
            {
                "San Francisco",    // unk
                "San Francisco",    // tob
                "London",           // lon
                "San Francisco",    // syg
                "San Francisco",    // nym
                "San Francisco",    // sng
                "San Francisco",    // bos
                "Sydney",           // syd
                "Rancho Cordova",   // rcp
                "Tokyo",            // tok
                "San Francisco",    // sfo
                "San Francisco",    // sdc
                "San Francisco",    // ldj
                "Sydney",           // sya
                "London",           // ldl
                "London",           // ams
                "London",           // lds
                "Tokyo"             // tok
            };

            /*
                Amsterdam*      267 or AMS
                Chicago         244 or CHI
                Hong Kong       465 or HOK
                London          566 or LON
                Rancho Cordova  732 or RDC
                Sacramento      722 or SAC
                San Francisco   736 or SFO
                Sydney          793 or SYD
                Tokyo           865 or TOK
                Toronto         867 or TOR
                Walnut Creek    922 or WAC
                Waltham         925 or WAL
            //*/
            //
            
            /*
                Amsterdam*      267 or AMS
                Hong Kong       465 or HOK
            //*/
            //
            
            String site_country_codes[] = 
            {
                "+1",       //  US and Canada
                "+44",      //  UK
                "+31",      //  Netherlands
                "+61",      //  Australia
                "+81",      //  Japan
                "+33",      //  France
                "+65",      //  Singapore
                "+85",      //  Hong Kong
                "+852",     //  Hong Kong
                "+41",      //  Switzerland
                "+80",      //  (unknown) - use San Francisco
                "+011"      //  (unknown) - use San Francisco
            };

            String site_country_sites[] =
            {
                "San Francisco",    // US and Canada
                "London",           // UK
                "London",           // Netherlands
                "Sydney",           // Australia
                "Tokyo",            // Japan
                "London",           // France
                "Tokyo",            // Singapore
                "Tokyo",            // Hong Kong
                "Tokyo",            // Hong Kong
                "London",           // Switzerland
                "San Francisco",    // (unknown) - use San Francisco
                "San Francisco"     // (unknown) - use San Francisco
            };

            if ( null == phone_code_country )
            {
                building = "San Francisco";
            }
            else
            {
                boolean found = false;

                for ( int counter = 0; counter < site_country_codes.length; counter++ )
                {
                    if ( phone_code_country.equals ( site_country_codes[counter] ) )
                    {
                        building = site_country_sites[counter];

                        found = true;
                        
                        break;
                    }
                }

                if ( false == found )
                {
                    _log.error ( "Could not find site for phone_code_country: " + phone_code_country );
                    building = "San Francisco";
                }
            }

            /*  Old scheme based on ShareIT site code
            if ( null == building )
            {
                building = "San Francisco";
            }
            else
            {
                boolean found = false;
                for ( int counter = 0; counter < site_codes.length; counter++ )
                {
                    if ( building.equals ( site_codes[counter] ) )
                    {
                        building = site_names[counter];

                        found = true;
                    }
                }

                if ( false == found )
                {
                    building = "San Francisco";
                }
            }
            //*/
            if ( _log.isEnabledFor ( Level.INFO ) )
            {
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "( " );
                    message.get().append ( entity_id );
                    message.get().append ( ", " );
                    message.get().append ( entity_status );
                    message.get().append ( ", " );
                    message.get().append ( building );
                    message.get().append ( ", " );
                    message.get().append ( user_id );
                    message.get().append ( ", " );
                    message.get().append ( first_name );
                    message.get().append ( ", " );
                    message.get().append ( last_name );
                    message.get().append ( ", " );
                    message.get().append ( results.getString ( "ViewGroupsLink" ) );
                    message.get().append ( ", " );
                    message.get().append ( phone_code_country );
                    message.get().append ( " )" );

                    _log.info ( message.get().toString() );
                }
            }


            ps_retrieve_igi.setString ( 1, user_id );
            results_igi = ps_retrieve_igi.executeQuery();

            if ( results_igi.next() )
            {
                Integer igi = new Integer ( results_igi.getString ( 1 ) );

                _log.info ( "Processing ( " + igi.toString() + ", " + first_name + ", " + last_name + " )" );

                ps_update.setString (  1, entity_status.toUpperCase() );  //  Status
                ps_update.setString (  2, building                    );  //  Site name
                ps_update.setString (  3, igi.toString()              );  //  User ID (LDAP login)
                ps_update.setString (  4, first_name                  );  //  First name
                ps_update.setString (  5, last_name                   );  //  Last name
                ps_update.setString (  6, default_role_list           );  //  Role list
                ps_update.setString (  7, user_id                     );  //  web login (same as external key, same as LDAP login)
                ps_update.setString (  8, igi.toString()              );  //  Phone login, temporariy set to employee ID
                ps_update.setString (  9, igi.toString()              );  //  Phone password, temporariy set to employee ID
                ps_update.setString ( 10, default_web_login_type      );  //  Web login type
                ps_update.setString ( 11, default_alarmpoint_action   );  //  Alarmpoint Action, Y=update or insert, R=remove
                ps_update.setString ( 12, default_password            );  //  Default password, found in "default_values.properties"
                ps_update.setString ( 13, default_ldap_domain         );  //  LDAP domain
                ps_update.setString ( 14, igi.toString()              );  //  External key, same as employee ID
                int num_updated = ps_update.executeUpdate();

                if ( 0 == num_updated )
                {
                    //ps.setString ( 1, entity_id     );
                    ps_insert.setString (  1, igi.toString()  );              //  External key, same as employee ID
                    ps_insert.setString (  2, entity_status.toUpperCase() );  //  Status
                    ps_insert.setString (  3, building                    );  //  Site name
                    //ps_insert.setString (  4, user_id                     );  //  User ID (LDAP login)
                    ps_insert.setString (  4, igi.toString()              );  //  User ID (LDAP login)
                    ps_insert.setString (  5, first_name                  );  //  First name
                    ps_insert.setString (  6, last_name                   );  //  Last name
                    ps_insert.setString (  7, default_role_list           );  //  Role list
                    ps_insert.setString (  8, user_id                     );  //  web login (same as external key, same as LDAP login)
                    ps_insert.setString (  9, igi.toString()              );  //  Phone login, temporariy set to employee ID
                    ps_insert.setString ( 10, igi.toString()              );  //  Phone password, temporariy set to employee ID
                    ps_insert.setString ( 11, default_web_login_type      );  //  Web login type
                    ps_insert.setString ( 12, default_alarmpoint_action   );  //  Alarmpoint Action, Y=update or insert, R=remove
                    ps_insert.setString ( 13, default_password            );  //  Default password, found in "default_values.properties"
                    ps_insert.setString ( 14, default_ldap_domain         );  //  LDAP domain
                    num_updated = ps_insert.executeUpdate();
                }

                num_records++;
            }


            if ( num_records % 100 == 0 )
            {
                _log.info ( "Number of records to add into database: " + num_records );
            }
        }

        _log.info ( "Number of records to add into database: " + num_records );
        _log.info ( "Sync non-LDAP users in AP Server" );

        if ( _log.isInfoEnabled() )
        {
            for ( int counter = 0; counter < building_codes.size(); counter++ )
            {
                _log.info ( "Found building code: " + building_codes.elementAt ( counter ) );
            }
        }

        Connection con_apserver          = Common.getAPServerConnection();
        Statement stmt                   = con_apserver.createStatement();
        ResultSet results_non_ldap_users = stmt.executeQuery ( "SELECT * FROM Persons WHERE (PersonID LIKE '999%') AND (CONVERT(int, PersonID) > 100000)" );

    }
};
