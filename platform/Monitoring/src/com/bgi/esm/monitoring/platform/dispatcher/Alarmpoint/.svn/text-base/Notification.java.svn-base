package com.bgi.esm.monitoring.platform.dispatcher.Alarmpoint;

import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *  Abstracts away the action of sending an alert to Alarmpoint AND the action of
 *  Retrieving the Alarmpoint event ID.  
 *
 *  NOTE:  The Alarmpoint Java Client should be installed locally
 */
public class Notification
{
    final private static Logger _log                   = Logger.getLogger ( Notification.class );

    private String EmployeeID                          = null;
    private String Device                              = null;
    private String Situation                           = null;

    public Notification ( String user_id, String device, String situation )
    {
        EmployeeID = user_id;
        Device     = device;
        Situation  = situation;
    }

    public void setUserID ( String user_id )
    {
        EmployeeID = user_id;
    }

    public String getUserID ()
    {
        return EmployeeID;
    }

    public void setSituation ( String situation )
    {
        Situation = situation;
    }

    public String getSituation ()
    {
        return Situation;
    }

    public void setDevice ( String device )
    {
        Device = device;
    }

    public String getDevice ()
    {
        return Device;
    }

    public boolean sendAlert ( String url )
    {
        //  Generate the message to send to the Alarmpoint java client
        String apjc_message = generateAlarmpointMessage();
        if ( _log.isDebugEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Sending Alarmpoint Alert ( Device, Situation, EmployeeID ) = ( " );
            message.get().append ( Device );
            message.get().append ( ", " );
            message.get().append ( Situation );
            message.get().append ( ", " );
            message.get().append ( EmployeeID );
            message.get().append ( " ) with the following message:\n" );
            message.get().append ( apjc_message );
        }

        Authenticator.setDefault ( new AgentAuthenticator ( "zzito", "zzito" ) );
        URL httpURL            = new URL ( url );
        HttpURLConnection http = (HttpURLConnection) httpURL.openConnection();
        http.setRequestMethod ( "POST" );
        http.connect();

        if ( http.getResponseCode() == HttpURLConnection.HTTP_OK )
        {
            BufferedReader in = new BufferedReader ( new InputStreamReader ( http.getInputStream() ) );
            String read = null;
            StringBuilder response = new StringBuilder();
            while (( read = in.readLine() ) != null )
            {
                response.append ( read );
            }

            System.out.println ( "Response: " );
            System.out.println ( response.toString() );

            FileOutputStream outfile = new FileOutputStream ( "c:\\test\\sampletickets" );
                outfile.write ( response.toString().getBytes() );
                outfile.write ( "\n\n".getBytes() );
            outfile.close();

            return response.toString();
        }
        else
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Could not connect to ServiceCenter Archway.  Error message: " );
            message.get().append ( http.getResponseMessage() );

            throw new IOException ( message.get().toString() );
        }

        return true;
    }

    /**
     *  Generates the APXML (Alarmpoint XML) message that the Alarmpoint Java Client uses to send the alert
     */
    private String generateAlarmpointMessage()
    {
        StringBuilder message = new StringBuilder ();
        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                     );
        message.append ( "<transaction id=\"1\">"                                         );
        message.append ( "<header>"                                                       );
        message.append ( "<method>Add</method>"                                           );
        message.append ( "<subclass>Action</subclass>"                                    );
        message.append ( "</header>"                                                      );
        message.append ( "<data>"                                                         );
        message.append ( "<action_script_set type=\"string\">BGI VPO</action_script_set>" );
        message.append ( "<agent_client_id>openview</agent_client_id>"                    );
        message.append ( "<device type=\"string\">"                                       );
        message.append ( Device                                                           );
        message.append ( "</device>"                                                      );
        message.append ( "<situation type=\"string\">"                                    );
        message.append ( Situation                                                        );
        message.append ( "</situation>"                                                   );
        message.append ( "<person_or_group_id type=\"string\">"                           );
        message.append ( EmployeeID                                                       );
        message.append ( "</person_or_group_id>"                                          );
        message.append ( "<severity>4</severity>"                                         );
        message.append ( "<debug>true</debug>"                                            );
        message.append ( "</data>"                                                        );
        message.append ( "</transaction>"                                                 );

        return message.toString();
    }

    public String getAlarmpointEventID()
    {
        return null;
    }
};
//  Alarmpoint message
// 

//  <!-- Sample message -->
//  <?xml version="1.0" encoding="UTF-8"?>
//  <transaction id="1">
//  <header>
//  <method>OK</method>
//  </header>
//  <data>
//  <action_script_set>BGI VPO</action_script_set>
//  <debug>true</debug>
//  <agent_client_id>openview</agent_client_id>
//  <device>device</device>
//  <situation>situation</situation>
//  <person_or_group_id>11312</person_or_group_id>
//  <apm_transmit_checksum>1103bbb38dc155821066fa115a</apm_transmit_checksum>
//  <agent_application_id>calntesv818/69.52.15.39</agent_application_id>
//  <incident_id>INCIDENT_ID-201458</incident_id>
//  <event_id>201458</event_id>
//  <severity>2</severity>
//  </data>
//  </transaction>
//
//
//  <!-- Sample status message -->
//  <?xml version="1.0" encoding="UTF-8"?>
//  <transaction id="0">
//  <header>
//  <method>Agent</method>
//  <subclass>Status</subclass>
//  </header>
//  <data>
//  <apagent>3.0.2 release (Build 2430/OCT-17-2006)</apagent>
//  <apagent_address>calntesv818/69.52.15.39</apagent_address>
//  <primary_status>Active</primary_status>
//  <primary_address>ldnntalp004:2004</primary_address>
//  </data>
//  </transaction>
