package com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

//  For handling XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import tools.Base64;
import com.bgi.esm.monitoring.platform.dispatcher.ServiceCenterAuthenticator;
import com.bgi.esm.monitoring.platform.authentication.User;
import com.bgi.esm.monitoring.platform.authentication.ldap.LDAPConnection;

/**
 *  DAO pattern for creating/maintaining a Service Center / Peregrine Ticket
 */
public class ServiceCenterTicket
{
    final private static Logger _log              = Logger.getLogger ( ServiceCenterTicket.class );
    final private static String web_service_table = "probsummary";

    private static Properties common_properties   = null;
    private static String service_center_server   = null;
    private static String service_center_port     = null;
    private static String service_center_servlet  = null;
    private static String service_center_action   = null;
    private static String web_service_url         = null;

    private String TicketNumber                   = null;
    private String RequestContents                = null;

    private StringBuilder ticket_entry            = null;  //  entry
    private String ticket_category                = null;  //  category
    private String ticket_open_time               = null;  //  open.time
    private String ticket_opened_by               = null;  //  opened.by
    private String ticket_priority_code           = null;  //  priority.code
    private String ticket_severity_code           = null;  //  severity.code
    private String ticket_update_time             = null;  //  update.time
    private String ticket_assignment              = null;  //  assignment
    private String ticket_alert_time              = null;  //  alert.time
    private String ticket_status                  = null;  //  status
    private String ticket_close_time              = null;  //  close.time
    private String ticket_closed_by               = null;  //  closed.by
    private String ticket_flag                    = null;  //  flag
    private String ticket_assignee_name           = null;  //  assignee.name
    private String ticket_respond_time            = null;  //  respond.time
    private String ticket_contact_name            = null;  //  contact.name  -- IGI
    private String ticket_actor                   = null;  //  actor
    private String ticket_format                  = null;  //  format
    private String ticket_deadline_group          = null;  //  deadline.group
    private String ticket_deadline_alert          = null;  //  deadline.alert

    /**
     *  Create a Service Center / Peregrine ticket for a given a ticket owner group, user's Windows 
     *  username/password, and a problem description
     *
     *  TODO: check that the person that owns the ticket actually belongs in the group that owns the ticket
     *
     *  @param ticket_owner  the group that owns the ticket
     *  @param user_id       the Windows username/password of the person owning the ticket
     *  @param description   a short description of the incident the ticket is about
     */
    public static ServiceCenterTicket createNewTicket ( String ticket_owner, String user_id, String description )
    {
        try
        {
            ///////////////////////////////////////////////////////////////////////
            //  First, check to see if ticket owner is a valid team in Peregrine
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            //  Next retrieve user information from LDAP
            ///////////////////////////////////////////////////////////////////////
            System.out.println ( "looking up LDAP user: " + user_id );
            User user = LDAPConnection.retrieveUserInformation ( user_id );
            if ( null == user )
            {
                String message = "Could not find user: " + user_id;
                _log.error ( message );

                throw new NullPointerException ( message );
            }

            user.LogObject();


            ///////////////////////////////////////////////////////////////////////
            //  Then, we create the URL to used to submit to the Archway API to
            //  create a ticket in Service Center
            ///////////////////////////////////////////////////////////////////////
            String url_string = createNewTicketSubmissionURL ( ticket_owner, user, description );

            try
            {
                ServiceCenterTicket ticket = new ServiceCenterTicket();
                ticket.setRequestContents ( makeHTTPRequest ( url_string, "c:\\test\\test-ticket" ) );
                ticket.processQueryForNewTicket();

                return ticket;
            }
            catch ( IOException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Could not create ticket for ( TicketOwner, UserID, Description ) VALUES ( " );
                message.get().append ( ticket_owner );
                message.get().append ( ", " );
                message.get().append ( user_id );
                message.get().append ( ", " );
                message.get().append ( description );
                message.get().append ( " )" );

                _log.error ( message.get().toString() );

                return null;
            }
    
            //  Create the HTTP request to create a ticket in Peregrine
            /*
             *&ticket.owner=test_group&user.id=linden&contact.name=011312&first.nameDennis&last.nameLin&dept=113-6207&incident.desc=test_description

http://peregrine-app-qa:8080/oaa/servlet/archway?sc.query&_table=probsummary&number=IM01006952 
http://peregrine-app-qa:8080/oaa/servlet/archway?sc.query&_table=probsummary&number=IM01131287

http://peregrine-app-prod:8080/oaa/servlet/archway?P4Connect.getTickets&_return=number;assignment;user.id;status;flag;open.time;close.time;art.id;ticket.owner;first.time.fix&status=closed&_count=15
http://peregrine-app-qz:8080/oaa/servlet/archway?P4Connect.getTickets&_return=number;assignment;user.id;status;flag;open.time;close.time;art.id;ticket.owner;first.time.fix&status=closed&incident.desc=test_description


http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.addIncident&ticket.owner=test_group&user.id=linden&contact.name=011312&first.name=Dennis&last.name=Lin&dept=113-6207&incident.desc=test_description
        http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.addIncident&
            corp.title=Associate
            title=Systems%20Developer
            company=BGI
            contact.location=SFO1
            contact.email=Kenny.Ma@barclaysglobal.com
            device.name=caldte1pc33879
            isxp=true
            incident.desc=Requests%20for%20CHANGES%20%2D%20Test%20helpme%2E
        
            Ticket Info
            Ticket.owner = ticket owner 
            Incident.desc = Ticket description
            Contact Info
            Contact.* = ticket contact info
            First.name
            Last.name
            Phone
            Corp.title
            eveeitle
            Dept
            Corp.structure = mrc
            Company
            Device.name = contact's PC
        
                //*/
        }
        catch ( UnsupportedEncodingException exception )
        {
            _log.error ( exception );
        }

        return null;
    }

    private static String createNewTicketSubmissionURL ( String ticket_owner, User user, String description )
        throws UnsupportedEncodingException
    {
        final String format      = "UTF-8";
        StringBuilder url_string = new StringBuilder();
        url_string.append ( "http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.addIncident" );

        /////////////////////////////////////////////////////////////////////////////
        //  Creating the URL to submit for creating the Service Center ticket
        /////////////////////////////////////////////////////////////////////////////
        //  Common characteristics
   
        //  Ticket owner
        if ( null != ticket_owner )
        {
            url_string.append ( "&ticket.owner=" );
            url_string.append ( URLEncoder.encode ( ticket_owner,         format ) );
        }
        else
        {
            _log.error ( "A ticket owner must be provided" );

            throw new NullPointerException ( "A ticket owner must be provided in order to create a ticket" );
        }

        //  User information
        url_string.append ( "&user.id=" );
        url_string.append ( URLEncoder.encode ( user.getUsername(),   format ) );
        
        if ( null != user.getEmployeeID() )
        {
            url_string.append ( "&contact.name=" );
            url_string.append ( URLEncoder.encode ( user.getEmployeeID(), format ) );
        }

        if ( null != user.getFirstName() )
        {
            url_string.append ( "&first.name=" );
            url_string.append ( URLEncoder.encode ( user.getFirstName(),  format ) );
        }

        if ( null != user.getLastName() )
        {
            url_string.append ( "&last.name=" );
            url_string.append ( URLEncoder.encode ( user.getLastName(),   format ) );
        }

        if ( null != user.getDepartment() )
        {
            url_string.append ( "&dept=" );
            url_string.append ( URLEncoder.encode ( user.getDepartment(), format ) );
        }

        if ( null != user.getEmployeeType() )
        {
            url_string.append ( "&corp.title=" );
            url_string.append ( URLEncoder.encode ( user.getEmployeeType(), format ) );
        }

        if ( null != user.getJobTitle() )
        {
            url_string.append ( "&title=" );
            url_string.append ( URLEncoder.encode ( user.getJobTitle(), format ) );
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

    private ServiceCenterTicket ()
    {
    }

    public ServiceCenterTicket ( String ticket_number ) throws IOException
    {
        TicketNumber = ticket_number;

        readProperties();

        makeRequest();
    }

    private static void readProperties() throws IOException
    {
        if (( null == common_properties ) || ( null == web_service_url ))
        {
            _log.info ( "Retrieving properties from ServiceCenter.properties" );

            ClassLoader cl = null;
            InputStream is = null;

            //  Read the properties file
            common_properties = new Properties(); 
            cl = ServiceCenterTicket.class.getClassLoader();
            is = cl.getResourceAsStream ( "ServiceCenter.properties" );
            common_properties.load ( is );

            //  Retrieve the properties from the properties file
            service_center_server  = common_properties.getProperty ( "service_center.server"  ).trim();
            service_center_port    = common_properties.getProperty ( "service_center.port"    ).trim();
            service_center_servlet = common_properties.getProperty ( "service_center.servlet" ).trim();
            service_center_action  = common_properties.getProperty ( "service_center.action"  ).trim();

            //  Build the URL to request the data from
            WeakReference <StringBuilder> buffer = new WeakReference <StringBuilder> ( new StringBuilder ( "http://" ) );
            buffer.get().append ( service_center_server );
            if (( null != service_center_port ) && ( service_center_port.length() > 0 ))
            {
                buffer.get().append ( ":" );
                buffer.get().append ( service_center_port );
            }
            buffer.get().append ( service_center_servlet );
            buffer.get().append ( "?" );
            buffer.get().append ( service_center_action  );
            buffer.get().append ( "&_table=" );
            buffer.get().append ( web_service_table );
            buffer.get().append ( "&number=" );

            web_service_url = buffer.get().toString();

            _log.info ( "URL to make requests against: " + web_service_url );
        }
    }

    private void processQueryForNewTicket()
    {
        try
        {
            System.out.println ( "processQueryForNewTicket() - num bytes: " + RequestContents.length() );
            //  Common XML parsing variables
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( RequestContents.getBytes() );
            Document document              = builder.parse ( bais );

            NodeList node_list             = document.getChildNodes();
            Node search_node               = null;
            int num_nodes                  = node_list.getLength();
            _log.info ( "Starting XML parsing for new ticket" );

            //  First, find the <recordset /> node
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );

                if ( node.getNodeName().equals ( "epmo" ) )
                {
                    search_node = node;
                    break;
                }
            }
            if ( null == search_node )
            {
                _log.error ( "Could not find the <epmo /> node" );

                throw new NullPointerException ( "Could not find the <epmo /> node" );
            }

            node_list = search_node.getChildNodes();
            num_nodes = node_list.getLength();
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );

                if ( node.getNodeName().equals ( "number" ) )
                {
                    Node cdata = node.getChildNodes().item ( 0 );

                    TicketNumber = cdata.getNodeValue();
                }
            }

            _log.info ( "Finished parsing new ticket" );
        }
        catch ( ParserConfigurationException exception )
        {
            _log.error ( "Could not parse response from Service Center for creating a new ticket", exception );
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not parse response from Service Center for creating a new ticket", exception );
        }
        catch ( SAXException exception )
        {
            _log.error ( "Could not parse response from Service Center for creating a new ticket", exception );
        }
    }

    private static String makeHTTPRequest ( String url, String ticket_number ) throws IOException
    {
        try
        {
            Authenticator.setDefault ( new ServiceCenterAuthenticator ( "zzito", "zzito" ) );
            URL httpURL            = new URL ( url );
            HttpURLConnection http = (HttpURLConnection) httpURL.openConnection();
            http.setRequestMethod ( "GET" );
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
        }
        catch ( MalformedURLException exception )
        {
            _log.error ( "Could not create ticket" );

            return null;
        }
    }

    private static void makeHTTPRequest2 ( String url, String TicketNumber ) throws IOException
    {
        //  Create the HTTPClient  object used to connect to the server
        HttpClient client    = new HttpClient();

        //  Login through HTTP authentication
        AuthScope auth_scope = new AuthScope ( "peregrine-app-prod", 8080, null ); 
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials ( "zzito", "zzito" );
        client.getState().setCredentials ( auth_scope, credentials ); 

        if ( _log.isInfoEnabled() )
        {
            FileOutputStream outfile = new FileOutputStream ( "c:\\test\\ServiceCenterTicket2", true );
                outfile.write ( url.getBytes() );
                outfile.write ( "\n".getBytes() );
            outfile.close();
        }

        //  Retrieve the results of the HTTP request
        System.out.println ( "Requested URL: "  + url );
        GetMethod get = new GetMethod ( url );
        client.executeMethod ( get );

        //  Retrieve the response body
        String contents = get.getResponseBodyAsString();

        if ( _log.isInfoEnabled() )
        {
            FileOutputStream outfile = new FileOutputStream ( TicketNumber );
                outfile.write ( contents.getBytes() );
            outfile.close();
        
            _log.info ( "String size: " + contents.length() );
        }

    }

    private void makeRequest() throws IOException
    {
        //  Construct the URL used to make the web request to retrieve the ticket information
        WeakReference <StringBuilder> request_url      = new WeakReference <StringBuilder> ( new StringBuilder ( web_service_url ) );
        WeakReference <StringBuilder> request_contents = new WeakReference <StringBuilder> ( new StringBuilder () );
        request_url.get().append ( TicketNumber );
       
        //  Create the HTTPClient  object used to connect to the server
        HttpClient client    = new HttpClient();

        //  Login through HTTP authentication
        AuthScope auth_scope = new AuthScope ( "peregrine-app-qa", 8080, null ); 
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials ( "zzito", "zzito" );
        client.getState().setCredentials ( auth_scope, credentials ); 

        //  Retrieve the results of the HTTP request
        GetMethod get = new GetMethod ( request_url.get().toString() );
        client.executeMethod ( get );

        //  Retrieve the response body
        String contents = get.getResponseBodyAsString();

        if ( _log.isInfoEnabled() )
        {
            FileOutputStream outfile = new FileOutputStream ( TicketNumber );
                outfile.write ( contents.getBytes() );
            outfile.close();
        
            _log.info ( "String size: " + contents.length() );
        }

        long start_time = System.currentTimeMillis();

        get = new GetMethod ( request_url.get().toString() );
        //Header header = new Header ( "Content-Type", "text/xml" );
        //get.setRequestHeader ( header );
        client.executeMethod ( get );

        //  Retrieve the contents (XML File)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is  = get.getResponseBodyAsStream();
        byte data[] = new byte[1024];
        int data_size   = 0;
        int num_read    = is.read ( data );
        while ( num_read > 0 )
        {
            baos.write ( data, 0, num_read );
            data_size += num_read;
            num_read   = is.read ( data );
        }

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Stream size: " + data_size );
            FileOutputStream outfile = new FileOutputStream ( TicketNumber + ".stream" );
                outfile.write ( baos.toByteArray() );
            outfile.close();
        }

        long end_time = System.currentTimeMillis();

        _log.info ( "Retrieval took " + (end_time-start_time) + " ms" );

        //  Save the contents
        RequestContents = new String ( baos.toByteArray() );

        //  Process the XML document that was retrieved
        processContents();
    }

    private void makeRequest2() throws IOException
    {
        WeakReference <StringBuilder> request_url      = new WeakReference <StringBuilder> ( new StringBuilder ( web_service_url ) );
        WeakReference <StringBuilder> request_contents = new WeakReference <StringBuilder> ( new StringBuilder () );
        request_url.get().append ( TicketNumber );

        _log.info ( "Making request to Service Center with URL: " + request_url.get().toString() );

        String encoding = "Basic " + Base64.encodeObject ( "zzito:zzito".getBytes() );

        _log.info ( "Encoding: " + encoding );

        URL url = new URL ( request_url.get().toString() );
        URLConnection uc = url.openConnection();

        _log.info ( "*** Retrieving URL properties" );

        uc.setRequestProperty ( "authorization", encoding);
        uc.setRequestProperty ( "Authorization", encoding);
        uc.setRequestProperty ( "www-Authorization", encoding);
        uc.setRequestProperty ( "WWW-Authorization", encoding);
        uc.setRequestProperty ( "Proxy-Authorization", encoding );
        _log.info ( "DEBUG: " + uc.getRequestProperty ( "WWW-Authorization" ) );
        uc.addRequestProperty ( "authorization", encoding);
        uc.addRequestProperty ( "www-Authorization", encoding);
        uc.addRequestProperty ( "Authorization", encoding);
        uc.addRequestProperty ( "WWW-Authorization", encoding);
        _log.info ( "DEBUG: " + uc.getRequestProperty ( "www-Authorization" ) );
        _log.info ( "DEBUG: " + uc.getRequestProperty ( "authorization" ) );
        _log.info ( "DEBUG: " + uc.getRequestProperty ( "Authorization" ) );
        _log.info ( "DEBUG: " + uc.getRequestProperty ( "Proxy-Authorization" ) );

        Map props = uc.getRequestProperties();
        Iterator i = props.keySet().iterator();
        while ( i.hasNext() )
        {
            String temp_string = i.next().toString();

            System.out.print ( "\t( " );
            System.out.print ( temp_string );
            System.out.print ( ", " );
            System.out.print ( uc.getRequestProperty( temp_string ) );
            System.out.println ( " )" );
        }
        _log.info ( "Finished Retrieving URL properties" );

        InputStream input_stream         = url.openStream();
        DataInputStream data_instream    = new DataInputStream ( input_stream );
        BufferedReader reader            = new BufferedReader ( new InputStreamReader ( data_instream ) );
        String temp_string               = null;

        while ( null != ( temp_string = reader.readLine() ) )
        {
            request_contents.get().append ( temp_string );
            request_contents.get().append ( "\n" );
        }

        _log.info ( "Successfully retrieved ticket information from Peregrine for Ticket #" + TicketNumber );

        RequestContents = request_contents.get().toString();
    }

    /**
     *  Process and extract information from the XML document that constitutes the ticket and put the 
     *  document into corresponding member variables with respective getter functions
     */
    private void processContents()
    {
        long start_time = System.currentTimeMillis();
        try
        {
            _log.info ( "processContents()" );

            //  Common XML parsing variables
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( RequestContents.getBytes() );
            Document document              = builder.parse ( bais );

            NodeList node_list             = document.getChildNodes();
            Node search_node               = null;
            int num_nodes                  = node_list.getLength();
            _log.info ( "Starting XML parsing for ticket #" + TicketNumber );

            //  First, find the <recordset /> node
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );

                if ( node.getNodeName().equals ( "recordset" ) )
                {
                    search_node = node;
                    break;
                }
            }
            if ( null == search_node )
            {
                _log.error ( "Could not find the <recordset /> node" );

                throw new NullPointerException ( "Could not find the <recordset /> node" );
            }

            //  Next, search for the <probsummary /> node
            node_list = search_node.getChildNodes();
            num_nodes = node_list.getLength();
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );

                if ( node.getNodeName().equals ( "probsummary" ) )
                {
                    search_node = node;
                    break;
                }
            }
            if ( null == search_node )
            {
                _log.error ( "Could not find the <probsummary /> node" );

                throw new NullPointerException ( "Could not find the <recordset /> node" );
            }

            node_list = search_node.getChildNodes();
            num_nodes = node_list.getLength();
            //  Check to see if this is a valid ticket
            if ( num_nodes < 1 )
            {
                _log.error ( "Invalid Ticket #" + TicketNumber );

                return;
            }
            //  Parse through each child and retrieve the data.  Each of nodes have exactly
            //  one child that is a CDATASection object.
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node        = node_list.item ( counter );
                String node_name = node.getNodeName();

                if ( node_name.equals ( "entry" ) )
                {
                    if ( null == ticket_entry )
                    {
                        ticket_entry = new StringBuilder();
                    }

                    // Process node
                    //
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    String data_value = data_node.getNodeValue();
                    ticket_entry.append ( data_value );
                    ticket_entry.append ( "\n" );

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( data_value );
                        message.get().append ( " ), Total value for 'entry' is now: " );
                        message.get().append ( ticket_entry.toString() );

                        _log.debug ( message.get().toString() );
                    }

                }
                else if ( node_name.equals ( "category" ) )
                {
                    Node data_node  = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_category = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_category );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "open.time" ) )
                {
                    // ticket_open_time
                    Node data_node  = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_category = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_category );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "opened.by" ) )
                {
                    Node data_node   = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_opened_by = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_opened_by );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "priority.code" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_priority_code = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_priority_code );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "severity.code" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    if ( null == data_node ) continue;

                    ticket_severity_code = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_severity_code );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "update.time" ) )
                {
                    Node data_node     = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_update_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_update_time );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "assignment" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_assignment = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_assignment );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "alert.time" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_alert_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_alert_time );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "status" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_status  = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_status );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "close.time" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_close_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_close_time );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "closed.by" ) )
                {
                    Node data_node   = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_closed_by = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_closed_by );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "flag" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_flag    = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_flag );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "assignee.name" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_assignee_name = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_assignee_name );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "respond.time" ) )
                {
                    Node data_node      = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_respond_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_respond_time );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "contact.name" ) )
                {
                    Node data_node      = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_contact_name = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_contact_name );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "actor" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_actor   = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_actor );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "format" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_format  = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_format );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "deadline.group" ) )
                {
                    Node data_node        = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_deadline_group = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_deadline_group );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
                else if ( node_name.equals ( "deadline.alert" ) )
                {
                    Node data_node        = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_deadline_alert = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.get().append ( node_name );
                        message.get().append  ( ", " );
                        message.get().append ( ticket_deadline_alert );
                        message.get().append ( " )" );

                        _log.debug ( message.get().toString() );
                    }
                }
            }

            _log.info ( "Finished XML parsing..." );
        }
        catch ( ParserConfigurationException exception )
        {
            _log.error ( "Exception detected", exception );
        }
        catch ( IOException exception )
        {
            _log.error ( "Exception detected", exception );
        }
        catch ( SAXException exception )
        {
            _log.error ( "Exception detected", exception );
        }

        long end_time = System.currentTimeMillis();

        _log.info ( "Processing took " + (end_time-start_time) + " ms" );
    }

    public String getEntry()
    {
        return ticket_entry.toString();
    }

    public String getCategory()
    {
        return ticket_category;
    }

    public String getOpenTime()
    {
        return ticket_open_time;
    }

    public String getOpenedBy()
    {
        return ticket_opened_by;
    }

    public String getPriorityCode()
    {
        return ticket_priority_code;
    }

    public String getSeverityCode()
    {
        return ticket_severity_code;
    }

    public String getUpdateTime()
    {
        return ticket_update_time;
    }

    public String getAssignment()
    {
        return ticket_assignment;
    }

    public String getAlertTime()
    {
        return ticket_alert_time;
    }

    public String getStatus()
    {
        return ticket_status;
    }

    public String getCloseTime()
    {
        return ticket_close_time;
    }

    public String getClosedBy()
    {
        return ticket_closed_by;
    }

    public String getFlag()
    {
        return ticket_flag;
    }

    public String getAssigneeName()
    {
        return ticket_assignee_name;
    }

    public String getRespondTime()
    {
        return ticket_respond_time;
    }

    public String getContactName()
    {
        return ticket_contact_name;
    }

    public String getActor()
    {
        return ticket_actor;
    }

    public String getFormat()
    {
        return ticket_format;
    }

    public String getDeadlineGroup()
    {
        return ticket_deadline_group;
    }

    public String getDeadlineAlert()
    {
        return ticket_deadline_alert;
    }

    public String getTicketNumber()
    {
        return TicketNumber;
    }

    private void setRequestContents ( String contents )
    {
        RequestContents = contents;
    }
};
