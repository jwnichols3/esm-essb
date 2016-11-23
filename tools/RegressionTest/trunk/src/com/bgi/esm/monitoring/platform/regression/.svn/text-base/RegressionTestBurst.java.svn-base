package com.bgi.esm.monitoring.platform.regression;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.regression.data.DataMap;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenterAuthenticator;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;
import com.bgi.esm.monitoring.platform.shared.utility.Base64;
import com.bgi.esm.monitoring.platform.regression.Toolkit;
//import weblogic.net.http.HttpURLConnection;
import java.net.HttpURLConnection;

public class RegressionTestBurst
{
    final private static Logger _log = Logger.getLogger ( RegressionTestBurst.class );
    final private static String SC_USERNAME              = "VPO";
    final private static String SC_PASSWORD              = "";
    final private static String format                   = "UTF-8";
    final private static int burst_size                  = 10;
    final private static int burst_pause_ms              = 30000;
    final private static int time_between_tickets_ms     = 100;

    public static void main ( String args[] )
    {
        Toolkit.reconfigureLog4j();

        int num_records = (int) EventHibernateFacade.count();

        Runtime rt = Runtime.getRuntime();
        byte contents[] = new byte[1024];

        long timestamp_start = 0L;
        long timestamp_end   = 0L;

        for ( int c = 0; c < 10; c++ )
        {
            for ( int counter = 1; counter <= num_records; counter++ )
            {
                timestamp_start = System.currentTimeMillis();
                // ARG[0] = Message ID
                // ARG[1] = Node Name
                // ARG[2] = Node Type
                // ARG[3] = Creation Date
                // ARG[4] = Creation Time
                // ARG[5] = Receive Date
                // ARG[6] = Receive Time
                // ARG[7] = Application
                // ARG[8] = Message Group
                // ARG[9] = Object
                // ARG[10] = Severity
                // ARG[11] = Operators
                // ARG[12] = Message
                // ARG[13] = Instruction
                // ARG[14] = Message Attributes
                // ARG[15] = Suppression Count
    
                _log.info ( "Timestamp: " + new java.util.Date() );
                Event event = EventHibernateFacade.select ( counter );
                StringBuilder command = new StringBuilder();

                _log.info ( "Looking for message group: " + event.getMessageGroup() );
                DataMap data_map = DataMap.selectByGroupName ( event.getMessageGroup() );

                if ( null == event )
                {
                    _log.error ( "Detected null event" );
                }
                if ( null == data_map )
                {
                    _log.error ( "Detected null data map rule" );

                    continue;
                }

                String ticket_number = createNewTicket ( "VPO", null, 
                        event.getMessageText(), data_map.getPerCat(), data_map.getPerSubcat(), 
                        data_map.getPerProblem(), data_map.getPerProduct() );

                _log.info ( "Created SC Ticket #" + ticket_number );


                /*
                command.append ( "c:/development/esm-platform/Tools/RegressionTestBurst/trunk/src/" );
                command.append ( "OpenviewTTIClient.exe " );
                command.append ( "\"" );
                command.append ( event.getMessageID()         );
                command.append ( "\" \"" );
                command.append ( event.getNodeName()          );
                command.append ( "\" \"" );
                command.append ( event.getNodeType()          );
                command.append ( "\" \"" );
                command.append ( event.getNodeDate()          );
                command.append ( "\" \"" );
                command.append ( event.getNodeTime()          );
                command.append ( "\" \"" );
                command.append ( event.getServerDate()        );
                command.append ( "\" \"" );
                command.append ( event.getServerTime()        );
                command.append ( "\" \"" );
                command.append ( event.getApplication()       );
                command.append ( "\" \"" );
                command.append ( event.getMessageGroup()      );
                command.append ( "\" \"" );
                command.append ( event.getObject()            );
                command.append ( "\" \"" );
                command.append ( event.getSeverity()          );
                command.append ( "\" \"" );
                command.append ( event.getInstructions()      );
                command.append ( "\" \"" );
                command.append ( "\" \"" );
                command.append ( event.getMessageAttributes() );
                command.append ( "\" \"" );
                command.append ( 0 );
                command.append ( "\"" );
    
                _log.info ( "Execute command: " + command.toString() );
                //*/
                timestamp_end = System.currentTimeMillis();

                try
                {
                    StringBuilder message = new StringBuilder ( "Duration: " );
                    message.append ( timestamp_start );
                    message.append ( ", " );
                    message.append ( timestamp_end );
                    message.append ( ", " );
                    message.append ( timestamp_end-timestamp_start );

                    _log.info ( "Duration: " + message.toString() );
                    /*
                    Process p = rt.exec ( command.toString() );
                    InputStream is =  p.getInputStream();
                    int available = is.read ( contents );
                    _log.info ( "Command output: " + (new String ( contents ) ) );
                    p.waitFor();
                    //*/
    
                    Thread.sleep ( time_between_tickets_ms );

                    if ( counter % burst_size == 0 )
                    {
                        _log.info ( "Burst limit reached.... sleeping for " + burst_pause_ms + " ms" );

                        Thread.sleep ( burst_pause_ms );
                    }
                }
                catch ( Exception exception )
                {
                    _log.error ( "Error when executing command: " + command.toString(), exception );
                }
                //*/
            }
        }
    }

    public static String createNewTicket ( String ticket_owner, String assignment, String description, String ticket_category, String ticket_subcategory, String problem_type, String product_type )
    {

        try
        {
            String url_string = createNewTicketSubmissionURL ( ticket_owner, assignment, description, ticket_category, ticket_subcategory, problem_type, product_type );

            String contents = makeHTTPRequest ( url_string, "c:\\test\\test-ticket" );

            int index1 = contents.indexOf ( "<number>" );
            if ( index1 > 0 )
            {
                index1 = index1 + "<number>".length();
                int index2 = contents.indexOf ( "</number>" );

                return contents.substring ( index1, index2 );
            }
            else
            {
                return null;
            }
        }
        catch ( IOException exception )
        {
            StringBuilder message = new StringBuilder();
            message.append ( "Could not create ticket for ( TicketOwner, Assignment, Description ) VALUES ( " );
            message.append ( ticket_owner );
            message.append ( ", " );
            message.append ( assignment );
            message.append ( ", " );
            message.append ( description );
            message.append ( " )" );

            _log.error ( message.toString(), exception );

            return null;
        }
    }

    private static String makeHTTPRequest ( String url, String output_filename ) throws IOException
    {
    	_log.debug("Entering ServiceCenterTicket.makeHTTPRequest.");
    	
        HttpURLConnection http = null;

        try
        {
            StringBuilder encode_string = new StringBuilder();
            encode_string.append ( SC_USERNAME );
            encode_string.append ( ":" );
            encode_string.append ( SC_PASSWORD );

            String sc_authentication = Base64.encodeBytes ( encode_string.toString().getBytes() );

            ServiceCenterAuthenticator scAuthenticator = new ServiceCenterAuthenticator ( SC_USERNAME, SC_PASSWORD );
            Authenticator.setDefault ( scAuthenticator );
            URL httpURL            = new URL ( url );
            
            _log.debug( "The URL is as follows: " + url );
            
            http = (HttpURLConnection) httpURL.openConnection();

            http.setRequestProperty ( "Authorization", "Basic " + sc_authentication ); 

            _log.debug("After call to httpURL.openConnection.");
            http.setRequestMethod ( "GET" );


            Map keys = http.getHeaderFields();
            Iterator i = keys.keySet().iterator();
            while ( i.hasNext() )
            {
                Object o     = i.next();
                if ( null == o ) continue;
                String key   = o.toString();
                String value = http.getHeaderField ( key );
                value        = ( null != value )? value : "null";
            }

            if ( http.getResponseCode() == HttpURLConnection.HTTP_OK )
            {
            	_log.debug("Connected to ServiceCenter Archway.");
                BufferedReader in = new BufferedReader ( new InputStreamReader ( http.getInputStream() ) );
                String read = null;
                StringBuilder response = new StringBuilder();
                while (( read = in.readLine() ) != null )
                {
                    response.append ( read );
                }

                FileOutputStream outfile = new FileOutputStream ( output_filename, true );
                    outfile.write ( response.toString().getBytes() );
                    outfile.write ( "\n\n".getBytes() );
                outfile.close();

                //http.getInputStream().close();
                //http.getOutputStream().close();

                return response.toString();
            }
            else
            {
                StringBuilder message = new StringBuilder();
                message.append ( "Could not connect to ServiceCenter Archway.  HTTP Response code was: " );
                message.append ( http.getResponseCode() );
                message.append ( ", Error message: " );
                message.append ( http.getResponseMessage() );

                if ( null != _log )
                {
                    _log.error("Throwing IOException from ServiceCenterTicket.makeHTTPRequest: " + message.toString() );
                }

                //http.getInputStream().close();
                //http.getOutputStream().close();

                throw new IOException ( message.toString() );
            }
        }
        catch ( MalformedURLException exception )
        {
            _log.error ( "Could not create ticket", exception );

                //http.getInputStream().close();
                //http.getOutputStream().close();

            return null;
        }
        catch ( UnsupportedEncodingException exception )
        {
            _log.error ( "Unsupported encoding: " + format, exception );
        }

        return null;
    }

    private static String createNewTicketSubmissionURL ( String ticket_owner, String assignment, String description, String ticket_category, String ticket_subcategory, String problem_type, String product_type )
        throws UnsupportedEncodingException
    {
        StringBuilder url_string = new StringBuilder();
        url_string.append ( "http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.genericAddIncident" );

        /////////////////////////////////////////////////////////////////////////////
        //  Creating the URL to submit for creating the Service Center ticket
        /////////////////////////////////////////////////////////////////////////////
        
        //  Group assignment
        if ( null != assignment )
        {
            url_string.append ( "&assignment=" );
            url_string.append ( URLEncoder.encode ( assignment, format ) );
        }
    
        //  Incident description
        if ( null != description )
        {
            url_string.append ( "&incident.desc=" );
            url_string.append ( URLEncoder.encode ( description,          format ) );
        }
        else
        {
            _log.error ( "Null value for Ticket Description detected" );

            throw new NullPointerException ( "You must provide a description in order to create a ticket" );
        }

        if ( null != ticket_category )
        {
            url_string.append ( "&category=" );
            url_string.append ( URLEncoder.encode ( ticket_category, format ) );
        }

        if ( null != ticket_subcategory )
        {
            url_string.append ( "&subcategory=" );
            url_string.append ( URLEncoder.encode ( ticket_subcategory, format ) );
        }

        if ( null != problem_type )
        {
            url_string.append ( "&problem.type=" );
            url_string.append ( URLEncoder.encode ( problem_type, format ) );
        }

        if ( null != product_type )
        {
            url_string.append ( "&product.type=" );
            url_string.append ( URLEncoder.encode ( product_type, format ) );
        }

        if ( _log.isInfoEnabled() )
        {
            //System.out.println ( "Creation URL: " + url_string );
            try
            {
                FileOutputStream outfile = new FileOutputStream ( "c:\\test\\ServiceCenterTicket", true );
                    outfile.write ( url_string.toString().getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
            catch ( IOException exception )
            {
            }
        }

        return url_string.toString();
    }

};
