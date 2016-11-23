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
import java.util.Random;
import java.util.Vector;
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

public class RegressionTestExponential extends Thread
{
    final private static Logger _log = Logger.getLogger ( RegressionTestExponential.class );
    final private static String SC_USERNAME              = "VPO";
    final private static String SC_PASSWORD              = "";
    final private static String format                   = "UTF-8";
    final private static int num_ms_between_tickets      = 450000;  // Expecting 8 tickets per hour, or 1 ticket per 450,000 ms
    final private static Random random                   = new Random();
    final private static Vector <RegressionTestExponential> jobs  = new Vector <RegressionTestExponential> ();

    private int index = 0;
    public boolean isUser = true;
    private boolean is_finished = false;
    public RegressionTestExponential ( int index )
    {
        this.index = index;
    }

    public void run()
    {
        if ( true == isUser )
        {
            run_user_thread();
        }
        else
        {
            run_openview_thread();
        }
    }

    public void run_user_thread()
    {
        long timestamp_start = 0L;
        long timestamp_end   = 0L;

        Event event = EventHibernateFacade.select ( index );
        DataMap data_map = DataMap.selectByGroupName ( event.getMessageGroup() );

        long runtime_start = System.currentTimeMillis();
        long sleep_time    = 100;
        long diff_time     = 0L;
        double test        = 0.0;
        try
        {
            _log.info ( "Thread-" + runtime_start + " is sleeping" );
            sleep ( sleep_time );
            test      = random.nextDouble();
            diff_time =  System.currentTimeMillis() - runtime_start;

            while ( test <= calculateExponential ( diff_time, num_ms_between_tickets ) )
            {
                _log.info ( "Thread-" + runtime_start + " is sleeping with diff_time=" + diff_time );
                sleep ( sleep_time );
                diff_time =  System.currentTimeMillis() - runtime_start;
            }
        }
        catch ( InterruptedException exception )
        {
            _log.error ( "Thread interrupted", exception );
        }

        if ( null == event )
        {
            _log.error ( "Found null event in User Thread: " + index );
        }
        if ( null == data_map )
        {
            _log.error ( "Found null data map rule in User Thread: " + index );
        }
        

        timestamp_start = System.currentTimeMillis();
            //String ticket_number = "Skipped-" + timestamp_start;
            //*
            String ticket_number = createNewTicket ( "VPO", null, 
                event.getMessageText(), data_map.getPerCat(), data_map.getPerSubcat(), 
                data_map.getPerProblem(), data_map.getPerProduct() );
            //*/
        timestamp_end = System.currentTimeMillis();

        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Duration - User Thread: " );
            message.get().append ( index );
            message.get().append ( ", " );
            message.get().append ( timestamp_start );
            message.get().append ( ", " );
            message.get().append ( timestamp_end );
            message.get().append ( ", " );
            message.get().append ( timestamp_end-timestamp_start );
            message.get().append ( ", " );
            message.get().append ( ticket_number );
        _log.info ( message.get().toString() );

        is_finished = true;
    }

    public void run_openview_thread()
    {
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

                _log.info ( "Timestamp: " + new java.util.Date() );
                Event event = EventHibernateFacade.select ( counter );
                StringBuilder command = new StringBuilder();

                DataMap data_map = DataMap.selectByGroupName ( event.getMessageGroup() );

                String ticket_number = createNewTicket ( "VPO", null, 
                        event.getMessageText(), data_map.getPerCat(), data_map.getPerSubcat(), 
                        data_map.getPerProblem(), data_map.getPerProduct() );

                _log.info ( "Created SC Ticket #" + ticket_number );

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
    
                    Thread.sleep ( 100 );
                }
                catch ( Exception exception )
                {
                    _log.error ( "Error when executing command: " + command.toString(), exception );
                }
                //*/
            }
        }
    }

    public static void main ( String args[] )
    {
        Toolkit.reconfigureLog4j();

        //  Start the EEB thread
        RegressionTestExponential eeb_thread = new RegressionTestExponential( 0 );
        eeb_thread.isUser = false;
        eeb_thread.start();

        int num_records = (int) EventHibernateFacade.count();

        Runtime rt = Runtime.getRuntime();
        byte contents[] = new byte[1024];

        long timestamp_start = 0L;
        long timestamp_end   = 0L;

        RegressionTestExponential thread = null;
        for ( int c = 0; c < 10; c++ )
        {
            for ( int counter = 1; counter <= num_records; counter++ )
            {
                try
                {
                    while ( jobs.size() >= 10 )
                    {
                        _log.info ( "10 active jobs detected at " + System.currentTimeMillis() );
                        Thread.sleep ( 500 );

                        scanForStoppedJobs();
                    }

                    thread = new RegressionTestExponential ( counter );
                    thread.start();

                    jobs.add ( thread );
                    _log.info ( "Number of active jobs in: " + jobs.size() );
    
                    Thread.sleep ( 500 );
                    scanForStoppedJobs();
                }
                catch ( Exception exception )
                {
                    _log.error ( "Could not run job " + counter, exception );
                }
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

    private static void scanForStoppedJobs()
    {
        for ( int j = jobs.size()-1; j >= 0; j-- )
        {
            RegressionTestExponential job = jobs.get ( j );
            if ( job.isFinished() )
            {
                jobs.removeElementAt ( j );
            }
        }
    }

    public boolean isFinished()
    {
        return is_finished;
    }

    private static double calculateExponential ( long time_in_millis, int rate_in_millis )
    {
        double rate = ((double) time_in_millis) / ((double) rate_in_millis);

        double cumulative_prob = 1.0 - Math.exp ( -rate );

        return cumulative_prob;
    }


    private static double calculatePoisson ( int time_in_millis, int rate_in_millis, int num_occurances )
    {
        double rate = ((double) time_in_millis) / ((double) rate_in_millis);

        double numerator = Math.exp ( -rate ) * Math.pow ( rate, (double) num_occurances );
        double result    = numerator;
        for ( int counter = 1; counter <= num_occurances; counter++ )
        {
            result /= counter;
        }

        return result;
    }
};
