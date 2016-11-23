package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.net.HttpURLConnection;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.shared.utility.Base64;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenterAuthenticator;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterTicketFactory
{
    private static final String externalLogFileName = "c:\\test\\JMS-ServiceCenterTicketFactory.out";
    private static final String ARCHWAY_URL_STATUS  = "http://peregrine-app-qa:8080/oaa/servlet/archway?sc.query&_table=probsummary&number=";
    private static final String ARCHWAY_URL_CREATE  = "http://peregrine-app-qa:8080/oaa/servlet/archway?P4Connect.genericAddIncident&company=BGI";

    private static final Logger _log                = Logger.getLogger ( ServiceCenterTicketFactory.class );
    private static final SimpleDateFormat sdf       = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss z Z" );
    private static final int HTTP_TIMEOUT_SECONDS   = 30;

    private static String SC_USERNAME = "VPO";
    private static String SC_PASSWORD = "";

    private static ServiceCenterTicket makeHTTPRequest ( String url_string ) throws IOException
    {
        ServiceCenterTicket sct  = null;
        byte request_contents[]  = retrieveHTTPRequestContents ( url_string );

        if ( null == request_contents )
        {
            throw new IOException ( "Failed HTTP request with URL: " + url_string );
        }

        //  Instantiating an Authenticator
        ServiceCenterAuthenticator scAuthenticator = new ServiceCenterAuthenticator ( SC_USERNAME, SC_PASSWORD );

        return sct;
    }

    public static ServiceCenterTicketNew createNewTicket ( ServiceCenterTicketNew sct )
    {
        sct.validate();
        if ( sct.hasErrors() )
        {
            return null;
        }

        try
        {
            StringBuilder url = new StringBuilder ( ARCHWAY_URL_CREATE );
            url.append ( "&company=BGI" );
            url.append ( "&ticket.owner=" );
            url.append ( SC_USERNAME );
            url.append ( "&user.id=" );
            url.append ( SC_USERNAME );
            url.append ( "&category=" );
            url.append ( URLEncoder.encode ( sct.getCategory(), "UTF-8" ) );
            url.append ( "&subcategory=" );
            url.append ( URLEncoder.encode ( sct.getSubcategory(), "UTF-8" ) );
            url.append ( "&product.type=VPO" );
            url.append ( "&assignment=" );
            url.append ( URLEncoder.encode ( sct.getAssignmentGroup(), "UTF-8" ) );
            url.append ( "&contact.location=GLOBAL" );
            if ( sct.getPriorityCode() != 0 )
            {
                url.append ( "&priority.code=" );
                url.append ( sct.getPriorityCode() );
            }
            url.append ( "&severity=" );
            url.append ( sct.getSeverityCode() );
            if ( sct.hasBriefDescription () )
            {
                url.append ( "&brief.desc=" );
                url.append ( URLEncoder.encode ( sct.getBriefDescription(), "UTF-8" ) );
            }
            if ( sct.hasLongDescription() )
            {
                url.append ( "&incident.desc=" );
                url.append ( URLEncoder.encode ( sct.getLongDescription(), "UTF-8" ) );
            }

            _log.info ( "ServiceCenterTicketFactory::createNewTicket() - url: " + url.toString() );

            Digester digester = new Digester();
            digester.setValidating ( false );

            RuleSetBase rules = new ServiceCenterTicketRulesCreateRequest();
            rules.addRuleInstances ( digester );

            byte http_response[] = retrieveHTTPRequestContents  ( url.toString() );

            if ( null == http_response )
            {
                _log.error ( "Unable to create new Service Center ticket" );

                return null;
            }

            ByteArrayInputStream bais = new ByteArrayInputStream ( http_response );

            try
            {
                return (ServiceCenterTicketNew) digester.parse ( bais );
            }
            catch ( Exception exception )
            {
                _log.error ( "Could not parse Archway response", exception );
            }
        }
        catch ( UnsupportedEncodingException exception )
        {
            _log.error ( "URL Encoding exception detected", exception );
        }


        return null;
    }

    public static ServiceCenterTicketNew createNewTicket ( String long_description, String short_description, String ticket_owner, 
            int severity_code, int priority_code, String category, String sub_category, String assignment_group, String contact_location )
    {
        ServiceCenterTicketNew sct = new ServiceCenterTicketNew();
        sct.setDescriptionLong  ( long_description  );
        sct.setDescriptionBrief ( short_description );
        sct.setOpenedBy         ( ticket_owner      );
        sct.setSeverityCode     ( severity_code     );
        sct.setPriorityCode     ( priority_code     );
        sct.setCategory         ( category          );
        sct.setSubcategory      ( sub_category      );
        sct.setAssignmentGroup  ( assignment_group  );
        sct.setContactLocation  ( contact_location  );

        return ServiceCenterTicketFactory.createNewTicket ( sct );
    }

    public static ServiceCenterTicketNew retrieveServiceCenterTicket ( String ticket_number )
    {
        Digester digester = new Digester();
        digester.setValidating ( false );

        RuleSetBase rules = new ServiceCenterTicketRulesRequest();
        rules.addRuleInstances ( digester );

        byte http_response[] = retrieveTicketInformation ( ticket_number );

        if ( null == http_response )
        {
            _log.error ( "Unable to retrieve Service Center Ticket #" + ticket_number );

            return null;
        }

        ByteArrayInputStream bais = new ByteArrayInputStream ( http_response );

        try
        {
            return (ServiceCenterTicketNew) digester.parse ( bais );
        }
        catch ( Exception exception )
        {
            _log.error ( "Could not parse Archway response", exception );
        }

        return null;
    }

    public static byte[] retrieveTicketInformation ( String ticket_number )
    {
        StringBuilder url = new StringBuilder ( ARCHWAY_URL_STATUS );
        url.append ( ticket_number );

        if ( _log.isInfoEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "ServiceCenterTicketFactory::retrieveTicketInformation ( " );
                message.append ( ticket_number );
                message.append ( " ) with url=" );
                message.append ( url.toString() );
            _log.info ( message.toString() );
        }

        return retrieveHTTPRequestContents ( url.toString() );
    }

    public static byte[] retrieveHTTPRequestContents ( String url_string )
    {
        URL httpURL              = null;
        HttpURLConnection http   = null;

        //  Create the encode string
        StringBuilder encode_string = new StringBuilder();
        encode_string.append ( SC_USERNAME );
        encode_string.append ( ":" );
        encode_string.append ( SC_PASSWORD );
        String sc_authentication    = Base64.encodeBytes ( encode_string.toString().getBytes() );

        try
        {
            Authenticator.setDefault ( new ServiceCenterAuthenticator ( SC_USERNAME, SC_PASSWORD ) );

            httpURL = new URL ( url_string );
            http    = (HttpURLConnection) httpURL.openConnection();
            http.setConnectTimeout ( HTTP_TIMEOUT_SECONDS );
            http.setRequestProperty ( "Authorization", "Basic " + sc_authentication );
            http.setRequestMethod   ( "GET" );
            dumpHTTPHeaders ( url_string, http );

            byte contents[] = new byte[http.getInputStream().available()];
            http.getInputStream().read ( contents );

            return contents;
        }
        catch ( MalformedURLException exception )
        {
            _log.error ( "Could not create ticket because of mangled URL: " + url_string, exception );
        }
        catch ( ConnectException exception )
        {
            _log.error ( "Could not connect to Service Center Archway.", exception );
        }
        catch ( IOException exception )
        {
            _log.error ( "IOException detected", exception );
        }

        return null;
    }

    private static synchronized void dumpHTTPHeaders ( String url_string, HttpURLConnection http )
    {
        if ( _log.isDebugEnabled() )
        {
            LogToFile ( externalLogFileName, "URL String: " + url_string );

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
        }
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
