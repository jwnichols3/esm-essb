package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.shared.utility.Base64;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Configuration;
import weblogic.logging.log4j.Log4jLoggingHelper;
import weblogic.logging.LoggerNotAvailableException;

/**
 *  Abstracts away the action of sending an alert to Alarmpoint AND the action of
 *  Retrieving the Alarmpoint event ID.  
 *
 *  NOTE:  The Alarmpoint Java Client should be installed locally
 *
 *  @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Notification
{
    private String AlarmpointTargetID               = null;
    private String Device                           = null;
    private String Situation                        = null;
    private String MessageId                        = null;
    private String AlarmpointScript                 = null;
    private String Severity                         = null;

    private static final String externalLogFileName = "c:\\test\\JMS-Alarmpoint.out";

    private static Logger _log                = null;

    static
    {
    	try
    	{
    		_log = Log4jLoggingHelper.getLog4jServerLogger();
    	}
    	catch (LoggerNotAvailableException e)
    	{
    		_log = Logger.getLogger ( Notification.class );
    	}
    }
    
    /**
     *  Creates the Notification object
     */
    public Notification ( String alarmpoint_target_id, String device, String situation, String message_id, String alarmpoint_script, String severity )
    {
        AlarmpointTargetID = alarmpoint_target_id;
        Device             = device;
        Situation          = situation;
        MessageId          = message_id;
        AlarmpointScript   = alarmpoint_script;
        Severity           = severity;

        if ( null != Device )
        {
            StringTokenizer tokens = new StringTokenizer( Device, "." );
            Device = tokens.nextToken();
        }
    }

    public void setUserID ( String user_id )
    {
        AlarmpointTargetID = user_id;
    }

    public String getUserID ()
    {
        return AlarmpointTargetID;
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

    public void setSeverity ( String severity )
    {
        Severity = severity;
    }

    public String getSeverity ()
    {
        return Severity;
    }

    public String getMessageId()
    {
        return MessageId;
    }

    public boolean sendAlert ()
    {
    	final String ENCODING = "UTF-8";
    	
        LogToFile ( externalLogFileName, "Retrieving Alarmpoint Java Client configuration" );
        Configuration conf       = Configuration.getInstance();
        String url               = conf.getHttpClientFormActionURL();
        LogToFile ( externalLogFileName, "Alarmpoint Java Client URL: " + url );
        String ap_authentication = Base64.encodeBytes ( "zzito:zzito".getBytes() );
        LogToFile ( externalLogFileName, "Creating Alarmpoint message" );
        String apjc_message      = generateAlarmpointMessage ( MessageId, AlarmpointScript );
        LogToFile ( externalLogFileName, "Alarmpoint message: " + apjc_message );

        //  Sanity check ... make sure that the URL to submit the alert to is not null
        if ( null == url )
        {
            _log.error ( "Could not send alert... Alarmpoint Java Client URL was null" );

            return false;
        }
        else if ( url.length() < 2 )
        {
            _log.error ( "Could not send alert... invalid Alarmpoint Java Client URL: " + url.toString() );

            return false;
        }


        StringBuilder submit_url = new StringBuilder ( url );
        submit_url.append ( "?message=" );
        
        try
        {
        	submit_url.append ( URLEncoder.encode ( apjc_message, ENCODING ) );
        }
        catch(UnsupportedEncodingException e)
        {
        	_log.warn("Encoding " + ENCODING + " not supported.  Adding message to URL " +
        			"without encoding it.");
        	
        	submit_url.append( apjc_message );
        }
        
        LogToFile ( externalLogFileName, "Alarmpoint Java Client URL: " + submit_url.toString() );

        //  Generate the message to send to the Alarmpoint java client
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
            message.append ( "Sending Alarmpoint Alert ( Device, Situation, AlarmpointTargetID ) = ( " );
            message.append ( Device );
            message.append ( ", " );
            message.append ( Situation );
            message.append ( ", " );
            message.append ( AlarmpointTargetID );
            message.append ( " ) with the following message:\n" );
            message.append ( apjc_message );

            _log.info ( message.toString() );
            LogToFile ( externalLogFileName, "Sample Alert Message:\n" + message.toString() );
        }


        Authenticator.setDefault ( new AgentAuthenticator ( "zzito", "zzito" ) );
        URL httpURL            = null;
        try
        {
            httpURL = new URL ( submit_url.toString() );
        }
        catch ( MalformedURLException exception )
        {
            _log.error ( "Invalid URL: " + submit_url.toString(), exception );
        }
        HttpURLConnection http = null;
        int response_code      = 0;
        try
        {
            http = (HttpURLConnection) httpURL.openConnection();
            http.setRequestProperty ( "Authorization", "Basic " + ap_authentication ); 
            http.setRequestMethod   ( "POST" );
            response_code = http.getResponseCode();
        }
        catch ( IOException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::sendAlert() - Could not connect to Alarmpoitn Agent at url=" );
                message.append ( submit_url.toString() );
                message.append ( " - " );
                message.append ( exception.getMessage() );
            _log.error ( message.toString() );
        }

        if ( response_code == HttpURLConnection.HTTP_OK )
        {
            BufferedReader in      = null;
            StringBuilder response = null;
            
            try
            {
                in = new BufferedReader ( new InputStreamReader ( http.getInputStream() ) );
                String read = null;
                response = new StringBuilder();
                while (( read = in.readLine() ) != null )
                {
                    response.append ( read );
                }
            }
            catch ( IOException exception )
            {
                _log.error ( "Could not retrieve HTTP response from ServiceCenter Archway", exception );
            }

            LogToFile ( externalLogFileName, "Alarmpoint Response SUCCESS: " + response.toString() );

            return true;
        }
        else
        {
            StringBuilder message = new StringBuilder();
            message.append ( "Could not connect to the Alarmpoint Java Client.  Error message: " );
            try
            {
                message.append ( http.getResponseMessage() );
            }
            catch ( IOException exception )
            {
                message.append ( exception.getMessage() );
            }

            LogToFile ( externalLogFileName, message.toString() );

            _log.error ( message.toString() );
        }

        return false;
    }

    /**
     *  Generates the APXML (Alarmpoint XML) message that the Alarmpoint Java Client uses to send the alert
     */
    private String generateAlarmpointMessage ( String message_id, String dataMapScript )
    {
        StringBuilder message = new StringBuilder ();
        message.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"                     );

        if ( dataMapScript.equalsIgnoreCase ( "BGI VPO" )  )
        {
            message.append ( "<transaction id=\"1\">"                                         );
            message.append ( "<header>"                                                       );
            message.append ( "<method>Add</method>"                                           );
            message.append ( "<subclass>Action</subclass>"                                    );
            message.append ( "</header>"                                                      );
            message.append ( "<data>"                                                         );
            message.append ( "<action_script_set type=\"string\">" );
            message.append ( dataMapScript );
            message.append ( "</action_script_set>" );
            message.append ( "<agent_client_id>openview</agent_client_id>"                    );
            message.append ( "<device type=\"string\">"                                       );
            message.append ( Device                                                           );
            message.append ( "</device>"                                                      );
            message.append ( "<incident_id>"                                                  );
            message.append ( message_id                                                       );
            message.append ( "</incident_id>"                                                 );
            message.append ( "<situation type=\"string\">"                                    );
            message.append ( Situation                                                        );
            message.append ( "</situation>"                                                   );
            message.append ( "<person_or_group_id type=\"string\">"                           );
            message.append ( AlarmpointTargetID                                               );
            message.append ( "</person_or_group_id>"                                          );
            if ( Severity.equalsIgnoreCase ( "critical" ) )
            {
                message.append ( "<severity type=\"string\">1</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "major" ) )
            {
                message.append ( "<severity type=\"string\">2</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "minor" ) )
            {
                message.append ( "<severity type=\"string\">3</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "warning" ) )
            {
                message.append ( "<severity type=\"string\">4</severity>"                     );
            }
            else
            {
                message.append ( "<severity type=\"string\">4</severity>"                     );
            }
            //message.append ( "<debug>true</debug>"                                            );
            message.append ( "</data>"                                                        );
            message.append ( "</transaction>"                                                 );
        }
        else if ( dataMapScript.equalsIgnoreCase ( "BGI On-call" ) )
        {
            message.append ( "<transaction id=\"1\">"                                         );
            message.append ( "<header>"                                                       );
            message.append ( "<method>Add</method>"                                           );
            message.append ( "<subclass>Action</subclass>"                                    );
            message.append ( "</header>"                                                      );
            message.append ( "<data>"                                                         );
            message.append ( "<action_script_set type=\"string\">" );
            message.append ( dataMapScript );
            message.append ( "</action_script_set>" );
            message.append ( "<agent_client_id>peregrine</agent_client_id>"                   );
            message.append ( "<device type=\"string\">"                                       );
            message.append ( Device                                                           );
            message.append ( "</device>"                                                      );
            message.append ( "<incident_id>"                                                  );
            message.append ( message_id                                                       );
            message.append ( "</incident_id>"                                                 );
            message.append ( "<situation type=\"string\">"                                    );
            message.append ( Situation                                                        );
            message.append ( "</situation>"                                                   );
            message.append ( "<person_or_group_id type=\"string\">"                           );
            message.append ( AlarmpointTargetID                                               );
            message.append ( "</person_or_group_id>"                                          );
            if ( Severity.equalsIgnoreCase ( "critical" ) )
            {
                message.append ( "<severity type=\"string\">1</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "major" ) )
            {
                message.append ( "<severity type=\"string\">2</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "minor" ) )
            {
                message.append ( "<severity type=\"string\">3</severity>"                     );
            }
            else if ( Severity.equalsIgnoreCase ( "warning" ) )
            {
                message.append ( "<severity type=\"string\">4</severity>"                     );
            }
            else
            {
                message.append ( "<severity type=\"string\">4</severity>"                     );
            }
            //message.append ( "<debug>true</debug>"                                            );
            message.append ( "</data>"                                                        );
            message.append ( "</transaction>"                                                 );
        }

        return message.toString();
    }

    public final static void LogToFile ( String filename, String message )
    {
        if ( _log.isDebugEnabled() )
        {
            try
            {
                FileOutputStream outfile = new FileOutputStream ( filename, true );
                    outfile.write ( (new java.util.Date()).toString().getBytes() );
                    outfile.write ( " - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
            catch ( IOException exception )
            {
                _log.fatal ( "Could not log message to filename: " + filename );
            }
        }
    }

    public String getAlarmpointEventID()
    {
        return null;
    }
};
