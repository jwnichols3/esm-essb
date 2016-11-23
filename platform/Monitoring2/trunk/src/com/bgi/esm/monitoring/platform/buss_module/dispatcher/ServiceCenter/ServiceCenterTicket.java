package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import weblogic.net.http.HttpURLConnection;
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
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.utility.Base64;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenterAuthenticator;
import com.bgi.esm.monitoring.platform.authentication.User;
import com.bgi.esm.monitoring.platform.authentication.ldap.LDAPConnection;
import weblogic.logging.log4j.Log4jLoggingHelper;
import weblogic.logging.LoggerNotAvailableException;

/**
 *  DAO pattern for creating/maintaining a Service Center / Peregrine Ticket
 *
 *  @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterTicket
{
    private static Logger _log                           = null;
    final private static OrmFacade _orm                  = new OrmFacade();
    final private static BackEndFacade bef               = new BackEndFacade();
    final private static String SC_PROP_WEBSERVICE_TABLE = "ServiceCenter.web_service_table";
    final private static String SC_PROP_HOSTNAME         = "ServiceCenter.hostname";
    final private static String SC_USERNAME              = "VPO";
    final private static String SC_PASSWORD              = "";
    final private static String ENCODING                 = "UTF-8";

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

    static
    {
        try
        {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        }
        catch ( Throwable th )
        {
            _log = Logger.getLogger ( ServiceCenterAuthenticator.class );
        }
        /*
        catch (LoggerNotAvailableException e)
        {
            _log = Logger.getLogger ( ServiceCenterTicket.class );
        }
        //*/
    }
    
    private static final String externalLogFileName = "c:\\test\\JMS-ServiceCenterTicket.out";

    public static boolean closeExistingTicket ( String ticket_number, String resolution )
    {
        return ServiceCenterTicket.closeExistingTicket ( ticket_number, resolution, "VPO", "VPO" );
    }

    public static boolean closeExistingTicket ( String ticket_number, String resolution, String user_id, String user_name )
    {
        StringBuilder url_string = new StringBuilder();
        url_string.append ( "http://" );
        //url_string.append ( _orm.selectEebProperty ( SC_PROP_HOSTNAME ).getPropertyValue() );
        url_string.append ( getServiceCenterPropertyHostname() );
        url_string.append ( "/oaa/servlet/archway?P4Connect.closeIncident&Id=" );
        try
        {
            url_string.append ( URLEncoder.encode ( ticket_number, ENCODING ) );
            url_string.append ( "&Resolution=" );
            url_string.append ( URLEncoder.encode ( resolution, ENCODING ) );
            url_string.append ( "&By=" );
            url_string.append ( URLEncoder.encode ( user_id, ENCODING ) );
            url_string.append ( "&ByName=" );
            url_string.append ( URLEncoder.encode ( user_name, ENCODING ) );
        }
        catch ( UnsupportedEncodingException exception )
        {
            _log.error ( "Could not create submission URL to close a ticket: ", exception );

            return false;
        }

        try
        {
            String result = makeHTTPRequest ( url_string.toString(), "c:\\test\\close-ticket" );
            LogToFile ( "c:\\test\\close-ticket.out", result );

            return true;
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not connect to URL: " + url_string.toString(), exception );
        }

        return false;
    }

    /**
     *  Updates a Service Center / Peregrine ticket with the duplicate count
     *
     *  @param ticket_number the Service Center ticket to update
     *  @param update_entity the entity that is updating the Service Center ticket
     *  @param update_source the source of the update
     *  @return true if the ticket was successfully updated, false otherwise
     */
    public static boolean updateExistingTicket ( String ticket_number, String update_entity, String update_source ) throws IOException
    {
        boolean found_errors = false;
        StringBuilder error_message = new StringBuilder();
        if ( null == ticket_number )
        {
            error_message.append ( "Ticket number can not be null" );

            found_errors = true;
        }

        if ( null == update_entity )
        {
            if ( true == found_errors )
            {
                error_message.append ( ", " );
            }
            error_message.append ( "Update entity can not be null" );

            found_errors = true;
        }

        if ( null == update_source )
        {
            if ( true == found_errors )
            {
                error_message.append ( ", " );
            }
            error_message.append ( "Update source can not be null" );

            found_errors = true;
        }

        if ( true == found_errors )
        {
            _log.error ( "Errors detected: " + error_message.toString() );

            return false;
        }

        //  Creating the update URL

        //
        //  Sample URL: http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.updateIncident&number=IM01206617&src=dennis&by=haha
        StringBuilder url_string = new StringBuilder();
        url_string.append ( "http://" );
        //url_string.append ( _orm.selectEebProperty ( SC_PROP_HOSTNAME ).getPropertyValue() );
        url_string.append ( getServiceCenterPropertyHostname() );
        url_string.append ( "/oaa/servlet/archway?P4Connect.updateIncident&number=" );

        _log.debug ( "Using Service Center URL: " + url_string.toString() );
        
        try
        {
            url_string.append ( URLEncoder.encode ( ticket_number, ENCODING ) );
            url_string.append ( "&src=" );
            url_string.append ( URLEncoder.encode ( update_source, ENCODING ) );
            url_string.append ( "&by=" );
            url_string.append ( URLEncoder.encode ( update_entity, ENCODING ) );
        }
        catch (UnsupportedEncodingException e)
        {
            _log.warn("Encoding " + ENCODING + " not supported.  Appending parameters to " + 
                    "URL without encoding.");
            
            url_string.append ( ticket_number );
            url_string.append ( "&src=" );
            url_string.append ( update_source );
            url_string.append ( "&by=" );
            url_string.append ( update_entity );
        }

        LogToFile ( externalLogFileName, "ServiceCenterTicket::updateExistingTicket() - url: " + url_string.toString() );

        try
        {
            Authenticator.setDefault ( new ServiceCenterAuthenticator ( SC_USERNAME, SC_PASSWORD ) );

            LogToFile ( externalLogFileName, "Authenticator class: " + Authenticator.class.getName() );

            URL httpURL            = new URL ( url_string.toString() );
            HttpURLConnection http = (HttpURLConnection) httpURL.openConnection();

            http.setRequestMethod ( "GET" );
            http.connect();

            makeHTTPRequest ( url_string.toString(), "c:\\test\\update-ticket" );

            return true;

            /*
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

                FileOutputStream outfile = new FileOutputStream ( "c:\\test\\updateTickets" );
                    outfile.write ( response.toString().getBytes() );
                    outfile.write ( "\n\n".getBytes() );
                outfile.close();

                return true;
            }
            else
            {
                StringBuilder message = new StringBuilder();
                message.append ( "Could not connect to ServiceCenter Archway.  Error message: " );
                message.append ( http.getResponseMessage() );

                throw new IOException ( message.toString() );
            }
            //*/
        }
        catch ( MalformedURLException exception )
        {
            _log.error ( "Could not update ticket" );

            return false;
        }
    }

    public static ServiceCenterTicket retrieveTicket ( String ticket_number )
    {
        StringBuilder request_url  = null;
        ServiceCenterTicket ticket = null;

        try
        {
            readProperties();
            
            request_url = new StringBuilder ( web_service_url );
            request_url.append ( ticket_number );

            ticket = new ServiceCenterTicket();

            String ticket_contents = makeHTTPRequest ( request_url.toString(), "c:\\test\\retrieve-ticket" );
            ticket.setRequestContents ( ticket_contents );

            return ( ticket.processQueryForRetrievedTicket() )? ticket : null;
        }
        catch ( IOException exception )
        {
            LogToFile ( externalLogFileName, "Could not retrieve ticket: " + ticket_number );
            _log.error ( "Could not retrieve ticket: " + ticket_number, exception );

            return null;
        }
    }

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
    public static ServiceCenterTicket createNewTicket ( String ticket_owner, String assignment, String description, String ticket_category, String ticket_subcategory, String problem_type, String product_type )
    {
        try
        {
            LogToFile ( externalLogFileName, "Create new ticket - Authenticator class: " + Authenticator.class.getName() );

            ///////////////////////////////////////////////////////////////////////
            //  First, check to see if ticket owner is a valid team in Peregrine
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            //  Next retrieve user information from LDAP
            ///////////////////////////////////////////////////////////////////////
            /*
            User user = null;
            if (( null != user_id ) && ( !user_id.equals ( "VPO" ) ))
            {
                _log.debug ( "looking up LDAP user: " + user_id );
                user = LDAPConnection.retrieveUserInformation ( user_id );
                _log.debug ( "finished looking up LDAP user: " + user_id );
            }
            
            if ( null == user )
            {
                String message = "Could not find user: " + user_id;
                _log.error ( message );
                LogToFile ( externalLogFileName, message );

                //throw new NullPointerException ( message );
                user = null;
            }
            else
            {
                user.LogObject();
            }
            //*/

            _log.debug("After user.LogObject().");

            ///////////////////////////////////////////////////////////////////////
            //  Then, we create the URL to used to submit to the Archway API to
            //  create a ticket in Service Center
            ///////////////////////////////////////////////////////////////////////
            String url_string = createNewTicketSubmissionURL ( ticket_owner, assignment, description, ticket_category, ticket_subcategory, problem_type, product_type );

            _log.info ( "Ticket URL: " + url_string );

            try
            {
                ServiceCenterTicket ticket = new ServiceCenterTicket();
                ticket.setRequestContents ( makeHTTPRequest ( url_string, "c:\\test\\test-ticket" ) );

                return ( ticket.processQueryForNewTicket() )? ticket : null;
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
        catch ( UnsupportedEncodingException exception )
        {
            _log.error ( exception );
        }

        return null;
    }

    public static String getServiceCenterPropertyHostname()
    {
        EebProperty eeb_sc_hostname = _orm.selectEebProperty ( SC_PROP_HOSTNAME );
        if ( null != eeb_sc_hostname )
        {
            _log.info ( "Retrieved Service Center property (hostname) from local interface" );

            return eeb_sc_hostname.getPropertyValue();
        }

        _log.info ( "Retrieved Service Center property (hostname) from remote interface" );
        try
        {
            eeb_sc_hostname = bef.selectEebProperty ( SC_PROP_HOSTNAME );
            
            return eeb_sc_hostname.getPropertyValue();
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Could not retrieve EEB Property SC_PROP_HOSTNAME", exception );
        }

        try
        {
            readProperties();

            String hostname = common_properties.getProperty ( "service_center.server" );

            if ( null == hostname )
            {
                hostname = "peregrine-app-qa:8080";
            }

            return hostname;
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not read from properties files", exception );
        }
        
        return null;
    }

    private static String createNewTicketSubmissionURL ( String ticket_owner, String assignment, String description, String ticket_category, String ticket_subcategory, String problem_type, String product_type )
        throws UnsupportedEncodingException
    {
        /*
        EebProperty eeb_sc_hostname = _orm.selectEebProperty ( SC_PROP_HOSTNAME );
        if ( null == eeb_sc_hostname )
        {
            try
            {
                eeb_sc_hostname = bef.selectEebProperty ( SC_PROP_HOSTNAME );
            }
            catch ( BackEndFailure exception )
            {
                _log.error ( "Could not retrieve EEB Property SC_PROP_HOSTNAME", exception );
            }
        }
        //*/

        final String format      = "UTF-8";
        StringBuilder url_string = new StringBuilder();
        url_string.append ( "http://" );
        //url_string.append ( eeb_sc_hostname.getPropertyValue() );
        url_string.append ( getServiceCenterPropertyHostname() );
        url_string.append ( "/oaa/servlet/archway?P4Connect.genericAddIncident" );

        /////////////////////////////////////////////////////////////////////////////
        //  Creating the URL to submit for creating the Service Center ticket
        /////////////////////////////////////////////////////////////////////////////
        //  Common characteristics
   
        //  Ticket owner
        /*
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
        //*/

        //  User information
        /*
        if ( null != user )
        {
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
        }
        else if ( false != ticket_owner.equals ( "VPO" ) )
        {
            _log.error ( "Could not find user in LDAP for ticket_owner=" + ticket_owner );
        }
        //*/

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

            ClassLoader cl = null;
            InputStream is = null;

            try
            {
                LogToFile ( externalLogFileName, "Retrieving properties from ServiceCenter.properties" );
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

                LogToFile ( externalLogFileName, "Finished retrieving properties from ServiceCenter.properties" );
            }
            catch ( NullPointerException exception )
            {
                LogToFile ( externalLogFileName, "Unable to retrieve default properties from ServiceCenter.properties" );
                _log.warn ( "Unable to retrieve default properties from ServiceCenter.properties" );
            }

            //  Attempt to read from local interface
            EebProperty eeb_sc_prop_hostname    = _orm.selectEebProperty ( SC_PROP_HOSTNAME );
            EebProperty eeb_sc_webservice_table = _orm.selectEebProperty ( SC_PROP_WEBSERVICE_TABLE  );

            if (( null == eeb_sc_prop_hostname ) || ( null == eeb_sc_webservice_table ))
            {
                try
                {
                    LogToFile ( externalLogFileName, "Retrieve EEB Properties for the ServiceCenter module" );
                    eeb_sc_prop_hostname    = bef.selectEebProperty ( SC_PROP_HOSTNAME );
                    eeb_sc_webservice_table = bef.selectEebProperty ( SC_PROP_WEBSERVICE_TABLE  );

                    service_center_server  = eeb_sc_prop_hostname.getPropertyValue();
                    service_center_servlet = "/oaa/servlet/archway";
                    service_center_action  = "sc.query";

                    StringTokenizer tokenizer = new StringTokenizer ( service_center_server, ":" );

                    common_properties = new Properties();
                    common_properties.put ( "service_center.server",  tokenizer.nextToken()  );
                    common_properties.put ( "service_center.port",    tokenizer.nextToken()  );
                    common_properties.put ( "service_center.servlet", service_center_servlet );
                    common_properties.put ( "service_center.action",  service_center_action  );

                    LogToFile ( externalLogFileName, "Finished retrieving EEB Properties for the ServiceCenter module" );
                }
                catch ( BackEndFailure exception )
                {
                    _log.error ( "Could not retrieve EEB Properties for the ServiceCenter module", exception );

                    LogToFile ( externalLogFileName, "Could not retrieve EEB Properties for the ServiceCenter module" );
                }
            }
            else
            {
                LogToFile ( externalLogFileName, "Successfully retrieved EEB properties" );

                service_center_server  = eeb_sc_prop_hostname.getPropertyValue();
                service_center_servlet = "/oaa/servlet/archway";
                service_center_action  = "sc.query";
            }

            

            //  Build the URL to request the data from
            StringBuilder buffer = new StringBuilder ( "http://" );
            buffer.append ( service_center_server );
            /*
            buffer.append ( service_center_server );
            if (( null != service_center_port ) && ( service_center_port.length() > 0 ))
            {
                buffer.append ( ":" );
                buffer.append ( service_center_port );
            }
            //*/

            buffer.append ( service_center_servlet );
            buffer.append ( "?" );
            buffer.append ( service_center_action  );
            buffer.append ( "&_table=" );
            if ( null != eeb_sc_webservice_table )
            {
                buffer.append ( eeb_sc_webservice_table.getPropertyValue() );
            }
            else
            {
                _log.warn ( "Could not retrieve '" + SC_PROP_WEBSERVICE_TABLE + "' property from the EEB" );

                buffer.append ( "probsummary" );
            }
            buffer.append ( "&number=" );

            web_service_url = buffer.toString();

            LogToFile ( externalLogFileName, "URL to make requests against: " + web_service_url );
        }
    }

    private boolean processQueryForRetrievedTicket()
    {
        if ( null == RequestContents )
        {
            _log.error ( "ServiceCenterTicket::processQueryForRetrievedTicket() - Null ticket contents detected" );

            return false;
        }

        try
        {
            System.out.println ( "processQueryForNewTicket() - num bytes: " + RequestContents.length() );
            LogToFile ( externalLogFileName, "processQueryForNewTicket() - num bytes: " + RequestContents.length() );
            //  Common XML parsing variables
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( RequestContents.getBytes() );
            Document document              = builder.parse ( bais );

            NodeList node_list             = document.getChildNodes();
            Node search_node               = null;
            int num_nodes                  = node_list.getLength();
            LogToFile ( externalLogFileName, "Starting XML parsing for retrieved ticket" );

            //  First, find the <recordset /> node
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );
                LogToFile ( externalLogFileName, "    Found node: " + node.getNodeName() );

                if ( node.getNodeName().equals ( "recordset" ) )
                {
                    search_node = node;
                    break;
                }
            }
            if ( null == search_node )
            {
                _log.error ( "Could not find the <recordset  /> node" );

                throw new NullPointerException ( "Could not find the <recordset /> node" );
            }

            // Next, find the <probsummary /> node
            node_list = search_node.getChildNodes();
            for ( int counter = 0; counter < num_nodes; counter++ )
            {
                Node node = node_list.item ( counter );
                if ( null == node )
                {
                    continue;
                }

                LogToFile ( externalLogFileName, "    Found node: " + node.getNodeName() );

                if ( node.getNodeName().equals ( "probsummary" ) )
                {
                    search_node = node;
                    break;
                }
            }
            if ( null == search_node )
            {
                _log.error ( "Could not find the <probsummary /> node" );

                throw new NullPointerException ( "Could not find the <probsummary /> node" );
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
                else if ( node.getNodeName().equals ( "status" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_status  = data_node.getNodeValue();
                }
            }

            LogToFile ( externalLogFileName, "Finished parsing new ticket" );

            return true;
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

        return false;
    }

    private boolean processQueryForNewTicket()
    {
        if ( null == RequestContents )
        {
            _log.error ( "ServiceCenterTicket::processQueryForNewTicket() - Null ticket contents detected" );

            return false;
        }

        try
        {
            System.out.println ( "processQueryForNewTicket() - num bytes: " + RequestContents.length() );
            LogToFile ( externalLogFileName, "processQueryForNewTicket() - num bytes: " + RequestContents.length() );
            //  Common XML parsing variables
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( RequestContents.getBytes() );
            Document document              = builder.parse ( bais );

            NodeList node_list             = document.getChildNodes();
            Node search_node               = null;
            int num_nodes                  = node_list.getLength();
            LogToFile ( externalLogFileName, "Starting XML parsing for new ticket" );

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

            LogToFile ( externalLogFileName, "Finished parsing new ticket" );

            return true;
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

        return false;
    }

    private static String makeHTTPRequest ( String url, String output_filename ) throws IOException
    {
        _log.debug("Entering ServiceCenterTicket.makeHTTPRequest.");
        
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "ServiceCenter::makeHTTPRequest ( url, outfile ) = ( " );
                message.append ( url );
                message.append ( ", " );
                message.append ( output_filename );
                message.append ( " )" );
            LogToFile ( externalLogFileName, message.toString() );
        }
        
        HttpURLConnection http = null;

        try
        {
            StringBuilder encode_string = new StringBuilder();
            encode_string.append ( SC_USERNAME );
            encode_string.append ( ":" );
            encode_string.append ( SC_PASSWORD );

            String sc_authentication = Base64.encodeBytes ( encode_string.toString().getBytes() );

            LogToFile ( externalLogFileName, "Creating authenticator" );
            ServiceCenterAuthenticator scAuthenticator = new ServiceCenterAuthenticator ( SC_USERNAME, SC_PASSWORD );
            Authenticator.setDefault ( scAuthenticator );
            LogToFile ( externalLogFileName, "Creating httpURL connection to URL=" + url );
            LogToFile ( externalLogFileName, "Encode string: " + encode_string.toString() );
            LogToFile ( externalLogFileName, "Auth string:   " + sc_authentication );
            LogToFile ( externalLogFileName, "Username:      " + SC_USERNAME );
            LogToFile ( externalLogFileName, "Password:      " + SC_PASSWORD );
            URL httpURL            = new URL ( url );
            
            LogToFile ( externalLogFileName, "Preparing to open HTTP Connection" );
            _log.debug( "The URL is as follows: " + url );
            
            http = (HttpURLConnection) httpURL.openConnection();

            LogToFile ( externalLogFileName, "Setting basic authentication parameters: " + sc_authentication );
            http.setRequestProperty ( "Authorization", "Basic " + sc_authentication ); 

            _log.debug("After call to httpURL.openConnection.");
            http.setRequestMethod ( "GET" );

            LogToFile ( externalLogFileName, "Before connecting... " );

            Map keys = http.getHeaderFields();
            Iterator i = keys.keySet().iterator();
            while ( i.hasNext() )
            {
                Object o     = i.next();
                if ( null == o ) continue;
                String key   = o.toString();
                String value = http.getHeaderField ( key );
                value        = ( null != value )? value : "null";
                LogToFile ( externalLogFileName, "    HTTP-Field " + key + ": " + value );
            }

            LogToFile ( externalLogFileName, scAuthenticator.getRequestInfo() );

            //http.connect();
            LogToFile ( externalLogFileName, "After connecting..." );

            if ( http.getResponseCode() == HttpURLConnection.HTTP_OK )
            {
                _log.debug ( "Connected to ServiceCenter Archway." );
                BufferedReader in = new BufferedReader ( new InputStreamReader ( http.getInputStream() ) );
                String read = null;
                StringBuilder response = new StringBuilder();
                while (( read = in.readLine() ) != null )
                {
                    response.append ( read );
                }

                LogToFile (externalLogFileName, "Response: " + response.toString() );

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

                LogToFile ( externalLogFileName, message.toString() );

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
    }

    /*
    private static void makeHTTPRequest2 ( String url, String TicketNumber ) throws IOException
    {
        //  Create the HTTPClient  object used to connect to the server
        HttpClient client    = new HttpClient();

        //  Login through HTTP authentication
        AuthScope auth_scope = new AuthScope ( "peregrine-app-prod", 8080, null ); 
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials ( SC_USERNAME, SC_PASSWORD );
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
        
            LogToFile ( externalLogFileName, "String size: " + contents.length() );
        }
    }
    //*/

    private void makeRequest() throws IOException
    {
        //  Construct the URL used to make the web request to retrieve the ticket information
        _log.info ( "Web Service URL: " + web_service_url );
        LogToFile ( externalLogFileName, "Web Service URL: " + web_service_url );
        StringBuilder request_url      = new StringBuilder ( web_service_url );
        StringBuilder request_contents = new StringBuilder ();
        request_url.append ( TicketNumber );
       
        //  Create the HTTPClient  object used to connect to the server
        HttpClient client    = new HttpClient();

        //  Login through HTTP authentication
        AuthScope auth_scope = new AuthScope ( "peregrine-app-qa", 8080, null ); 
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials ( SC_USERNAME, SC_PASSWORD );
        client.getState().setCredentials ( auth_scope, credentials ); 

        LogToFile ( externalLogFileName, "Setting up authentication credentials" );

        _log.info ( "makeRequest() URL: " + request_url.toString() );
        LogToFile ( externalLogFileName, "makeRequest() URL: " + request_url.toString() );
        //  Retrieve the results of the HTTP request
        GetMethod get = new GetMethod ( request_url.toString() );
        client.executeMethod ( get );

        LogToFile ( externalLogFileName, "Retrieving ticket information" );

        //  Retrieve the response body
        String contents = get.getResponseBodyAsString();

        if ( _log.isInfoEnabled() )
        {
            FileOutputStream outfile = new FileOutputStream ( TicketNumber );
                outfile.write ( contents.getBytes() );
            outfile.close();
        
            LogToFile ( externalLogFileName, "String size: " + contents.length() );
        }

        long start_time = System.currentTimeMillis();

        get = new GetMethod ( request_url.toString() );
        //Header header = new Header ( "Content-Type", "text/xml" );
        //get.setRequestHeader ( header );
        client.executeMethod ( get );

        LogToFile ( externalLogFileName, "Successfully retrieved ticket information" );

        //  Retrieve the contents (XML File)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is  = get.getResponseBodyAsStream();
        byte data[]     = new byte[1024];
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
            LogToFile ( externalLogFileName, "Stream size: " + data_size );
            FileOutputStream outfile = new FileOutputStream ( TicketNumber + ".stream" );
                outfile.write ( baos.toByteArray() );
            outfile.close();
        }

        long end_time = System.currentTimeMillis();

        LogToFile ( externalLogFileName, "Retrieval took " + (end_time-start_time) + " ms" );

        //  Save the contents
        RequestContents = new String ( baos.toByteArray() );

        //  Process the XML document that was retrieved
        processContents();
    }

    /*
    private void makeRequest2() throws IOException
    {
        StringBuilder request_url      = new StringBuilder ( web_service_url );
        StringBuilder request_contents = new StringBuilder ();
        request_url.append ( TicketNumber );

        LogToFile ( externalLogFileName, "Making request to Service Center with URL: " + request_url.toString() );

        String encoding = "Basic " + Base64.encodeObject ( "zzito:zzito".getBytes() );

        LogToFile ( externalLogFileName, "Encoding: " + encoding );

        URL url = new URL ( request_url.toString() );
        URLConnection uc = url.openConnection();

        LogToFile ( externalLogFileName, "*** Retrieving URL properties" );

        uc.setRequestProperty ( "authorization", encoding);
        uc.setRequestProperty ( "Authorization", encoding);
        uc.setRequestProperty ( "www-Authorization", encoding);
        uc.setRequestProperty ( "WWW-Authorization", encoding);
        uc.setRequestProperty ( "Proxy-Authorization", encoding );
        LogToFile ( externalLogFileName, "DEBUG: " + uc.getRequestProperty ( "WWW-Authorization" ) );
        uc.addRequestProperty ( "authorization", encoding);
        uc.addRequestProperty ( "www-Authorization", encoding);
        uc.addRequestProperty ( "Authorization", encoding);
        uc.addRequestProperty ( "WWW-Authorization", encoding);
        LogToFile ( externalLogFileName, "DEBUG: " + uc.getRequestProperty ( "www-Authorization" ) );
        LogToFile ( externalLogFileName, "DEBUG: " + uc.getRequestProperty ( "authorization" ) );
        LogToFile ( externalLogFileName, "DEBUG: " + uc.getRequestProperty ( "Authorization" ) );
        LogToFile ( externalLogFileName, "DEBUG: " + uc.getRequestProperty ( "Proxy-Authorization" ) );

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
        LogToFile ( externalLogFileName, "Finished Retrieving URL properties" );

        InputStream input_stream         = url.openStream();
        DataInputStream data_instream    = new DataInputStream ( input_stream );
        BufferedReader reader            = new BufferedReader ( new InputStreamReader ( data_instream ) );
        String temp_string               = null;

        while ( null != ( temp_string = reader.readLine() ) )
        {
            request_contents.append ( temp_string );
            request_contents.append ( "\n" );
        }

        LogToFile ( externalLogFileName, "Successfully retrieved ticket information from Peregrine for Ticket #" + TicketNumber );

        RequestContents = request_contents.toString();
    }
    //*/

    /**
     *  Process and extract information from the XML document that constitutes the ticket and put the 
     *  document into corresponding member variables with respective getter functions
     */
    private void processContents()
    {
        long start_time = System.currentTimeMillis();
        try
        {
            LogToFile ( externalLogFileName, "processContents()" );

            //  Common XML parsing variables
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( RequestContents.getBytes() );
            Document document              = builder.parse ( bais );

            NodeList node_list             = document.getChildNodes();
            Node search_node               = null;
            int num_nodes                  = node_list.getLength();
            LogToFile ( externalLogFileName, "Starting XML parsing for ticket #" + TicketNumber );

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
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( data_value );
                        message.append ( " ), Total value for 'entry' is now: " );
                        message.append ( ticket_entry.toString() );

                        _log.debug ( message.toString() );
                    }

                }
                else if ( node_name.equals ( "category" ) )
                {
                    Node data_node  = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_category = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_category );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
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
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append ( ", " );
                        message.append ( ticket_category );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "opened.by" ) )
                {
                    Node data_node   = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_opened_by = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_opened_by );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "priority.code" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_priority_code = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append ( ", " );
                        message.append ( ticket_priority_code );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "severity.code" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );

                    if ( null == data_node ) continue;

                    ticket_severity_code = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_severity_code );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "update.time" ) )
                {
                    Node data_node     = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_update_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_update_time );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "assignment" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_assignment = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_assignment );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "alert.time" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_alert_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_alert_time );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "status" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_status  = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_status );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "close.time" ) )
                {
                    Node data_node    = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_close_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_close_time );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "closed.by" ) )
                {
                    Node data_node   = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_closed_by = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_closed_by );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "flag" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_flag    = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_flag );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "assignee.name" ) )
                {
                    Node data_node       = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_assignee_name = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_assignee_name );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "respond.time" ) )
                {
                    Node data_node      = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_respond_time = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_respond_time );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "contact.name" ) )
                {
                    Node data_node      = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_contact_name = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_contact_name );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "actor" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_actor   = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append  ( ", " );
                        message.append ( ticket_actor );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "format" ) )
                {
                    Node data_node = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_format  = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append ( ", " );
                        message.append ( ticket_format );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "deadline.group" ) )
                {
                    Node data_node        = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_deadline_group = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append ( ", " );
                        message.append ( ticket_deadline_group );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
                else if ( node_name.equals ( "deadline.alert" ) )
                {
                    Node data_node        = node.getChildNodes().item( 0 );
                    if ( null == data_node ) continue;

                    ticket_deadline_alert = data_node.getNodeValue();

                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Retrieve info for ( Node, Value ) = ( " );
                        message.append ( node_name );
                        message.append ( ", " );
                        message.append ( ticket_deadline_alert );
                        message.append ( " )" );

                        _log.debug ( message.toString() );
                    }
                }
            }

            LogToFile ( externalLogFileName, "Finished XML parsing..." );
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

        LogToFile ( externalLogFileName, "Processing took " + (end_time-start_time) + " ms" );
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

    public boolean isTicketOpen()
    {
        if ( null == ticket_status )
        {
            return false;
        }
        else if (( true == ticket_status.toLowerCase().equals ( "open" ) ) ||
            ( true == ticket_status.toLowerCase().equals ( "updated" ) ))
        {
            return true;
        }
        else if ( true == ticket_status.toLowerCase().equals ( "closed" ) )
        {
            return false;
        }
        else
        {
            throw new IllegalStateException ( "Could not determine ticket state from ticket_status=" + ticket_status );
        }
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

    public static OrmFacade getOrmFacade()
    {
        return _orm;
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
            }
        }
    }
};
