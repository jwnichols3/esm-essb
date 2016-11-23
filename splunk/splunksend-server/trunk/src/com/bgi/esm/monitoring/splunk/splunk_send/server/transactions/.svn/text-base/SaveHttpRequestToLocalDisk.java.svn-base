package com.bgi.esm.monitoring.splunk.splunk_send.server.transactions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpAttributeFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpHeaderFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;


public class SaveHttpRequestToLocalDisk
{
    final private static Logger _log = Logger.getLogger ( SaveHttpRequestToLocalDisk.class );

    /**
     *  Saves an HTTP request into the database
     *
     *  @param request the HTTP request to process
     *  @return null if successful, a List <String> of error messages otherwise
     */
    public static List <String> saveHttpRequest ( HttpServletRequest request ) 
    {
        List <String> ErrorMessages = new Vector <String> ();
        Map <String, String> params = BaseDispatchAction.retrieveHttpRequestParameters ( request );
        Cookie cookies[]            = request.getCookies();
        Enumeration headerNames     = request.getHeaderNames();
        Enumeration attributeNames  = request.getAttributeNames();

        
        //  Required to have the following parameters:
        //  - Application
        //  - Severity
        //  - Message
        String Application = params.get ( "Application" );
        String Severity    = params.get ( "Severity" );
        String Message     = params.get ( "Message" );

        if (( null == Application ) || ( Application.trim().length() == 0 ))
        {
            ErrorMessages.add ( "No value for 'Application' parameter received" );
        }

        if (( null == Severity ) || ( Severity.trim().length() == 0 ))
        {
            ErrorMessages.add ( "No value for 'Severity' parameter received" );
        }

        if (( null == Message ) || ( Message.trim().length() == 0 ))
        {
            ErrorMessages.add ( "No value for 'Message' parameter received" );
        }

        if ( ErrorMessages.size() > 0 )
        {
            return ErrorMessages;
        }

        IHttpAttributeFinder httpAttributeFinder = Configuration.getInstance().getHttpAttributeFinder();
        IHttpCookieFinder httpCookieFinder       = Configuration.getInstance().getHttpCookieFinder();
        IHttpHeaderFinder httpHeaderFinder       = Configuration.getInstance().getHttpHeaderFinder();
        IHttpParameterFinder httpParameterFinder = Configuration.getInstance().getHttpParameterFinder();
        IHttpRequestFinder httpRequestFinder     = Configuration.getInstance().getHttpRequestFinder();

        IHttpRequest httpRequest = httpRequestFinder.newInstance();
            httpRequest.setAuthType ( request.getAuthType() );
            httpRequest.setCharacterEncoding ( request.getCharacterEncoding() );
            httpRequest.setContentLength ( request.getContentLength() );
            httpRequest.setContentType   ( request.getContentType() );
            httpRequest.setContextPath   ( request.getContextPath() );
            httpRequest.setIsProcessed   ( false );
            httpRequest.setLocalAddress  ( request.getLocalAddr() );
            httpRequest.setLocalPort     ( request.getLocalPort() );
            httpRequest.setMethod        ( request.getMethod()    );
            httpRequest.setPathInfo      ( request.getPathInfo()  );
            httpRequest.setProcessTime   ( null );
            httpRequest.setProtocol      ( request.getProtocol() );
            httpRequest.setRemoteAddress ( request.getRemoteAddr() );
            httpRequest.setRemoteHost    ( request.getRemoteHost() );
            httpRequest.setRemotePort    ( request.getRemotePort() );
            httpRequest.setRemoteUser    ( request.getRemoteUser() );
            httpRequest.setRequestedSessionID ( request.getRequestedSessionId() );
            httpRequest.setRequestTime   ( Calendar.getInstance()  );
            httpRequest.setRequestUri    ( request.getRequestURI() );
            httpRequest.setRequestUrl    ( request.getRequestURL().toString() );
        boolean result = httpRequestFinder.insert ( httpRequest, request.getRemoteUser(), request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort() );
 
        if ( false == result )
        {
            ErrorMessages.add ( "Could not save HTTP request" );

            return ErrorMessages;
        }

        if ( null != cookies )
        {
            for ( int counter = 0; counter < cookies.length; counter++ )
            {
                IHttpCookie cookie = httpCookieFinder.newInstance();
                    cookie.setCookieComment     ( cookies[counter].getComment() );
                    cookie.setCookieName        ( cookies[counter].getName()    );
                    cookie.setDomain            ( cookies[counter].getDomain()  );
                    cookie.setMaxAge            ( cookies[counter].getMaxAge()  );
                    cookie.setPath              ( cookies[counter].getPath()    );
                    cookie.setIsSecure          ( cookies[counter].getSecure()  );
                    cookie.setCookiePersistence ( cookies[counter].getValue()   );
                    cookie.setVersion           ( cookies[counter].getVersion() );
                    cookie.setRequestID         ( httpRequest.getRequestID()    );
                httpCookieFinder.insert ( cookie, request.getRemoteUser(), request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort() );
            }
        }

        Iterator <String> paramNames = params.keySet().iterator();
        while ( paramNames.hasNext() )
        {
            String paramName = paramNames.next();
            
            IHttpParameter httpParameter = httpParameterFinder.newInstance();
                httpParameter.setParameterName ( paramName );
                httpParameter.setParameterPersistence ( params.get ( paramName ) );
                httpParameter.setRequestID ( httpRequest.getRequestID() );
            httpParameterFinder.insert ( httpParameter, request.getRemoteUser(), request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort() );
        }
 
        while ( headerNames.hasMoreElements() )
        {
            String headerName = headerNames.nextElement().toString();
            IHttpHeader httpHeader = httpHeaderFinder.newInstance();
                httpHeader.setHeaderName ( headerName );
                httpHeader.setHeaderPersistence ( request.getHeader ( headerName ) );
                httpHeader.setRequestID ( httpRequest.getRequestID() );
            httpHeaderFinder.insert ( httpHeader, request.getRemoteUser(), request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort() );
        }

        while ( attributeNames.hasMoreElements() )
        {
            String attributeName = attributeNames.nextElement().toString();
            IHttpAttribute httpAttribute = httpAttributeFinder.newInstance();
                httpAttribute.setAttributeName          ( attributeName );
                httpAttribute.setAttributePersistence ( request.getAttribute( attributeName ).toString() );
                httpAttribute.setRequestID ( httpRequest.getRequestID() );
            httpAttributeFinder.insert ( httpAttribute, request.getRemoteUser(), request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort() );
        }

        saveRequestToDisk ( httpRequest.getRequestID(), params );

        return null;
    }

    public static void saveHttpRequest ( String request_id )
    {
    }

    private static boolean saveRequestToDisk ( String request_id, Map <String, String> parameters )
    {
        StringBuilder message = new StringBuilder();
        message.append ( "RequestID=" );
        message.append ( request_id );
        message.append ( "\n" );

        Iterator <String> paramNames = parameters.keySet().iterator();
        while ( paramNames.hasNext() )
        {
            String name  = paramNames.next();
            String value = parameters.get ( name );

            message.append ( name );
            message.append ( "=" );
            message.append ( value );
            message.append ( "\n" );
        }

        try
        {
            FileOutputStream outfile = new FileOutputStream ( "C:/SplunkFeeds/SplunkSend_Test/" + request_id );
                outfile.write ( message.toString().getBytes() );
            outfile.close();

            return true;
        }
        catch ( IOException exception )
        {
            _log.error ( exception );
        }

        return false;
    }


    private static boolean saveRequestToDisk ( String request_id, List <String> paramNames, List <String> paramValues )
    {
        StringBuilder message = new StringBuilder();
        message.append ( "RequestID=" );
        message.append ( request_id );
        message.append ( "\n" );
        for ( int counter = 0; counter < paramNames.size(); counter++ )
        {
            String name  = paramNames.get  ( counter );
            String value = paramValues.get ( counter );

            message.append ( name );
            message.append ( "=" );
            message.append ( value );
            message.append ( "\n" );
        }

        try
        {
            FileOutputStream outfile = new FileOutputStream ( "C:/SplunkFeeds/SplunkSend_Test/" + request_id );
                outfile.write ( message.toString().getBytes() );
            outfile.close();

            return true;
        }
        catch ( IOException exception )
        {
            _log.error ( exception );
        }

        return false;
    }
}
