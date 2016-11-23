package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.webservices.metro;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jms.HttpCookieRequestMessage;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jms.HttpCookieResponseMessage;


/**
 *  Class that backs the HttpCookie web service
 *
 */
@WebService ( name="HttpCookie", serviceName="HttpCookie" )
public class HttpCookieWebService
{
    final private static Logger _log = Logger.getLogger ( HttpCookieWebService.class );
    private static IHttpCookieFinder finder = null;

    @Resource WebServiceContext webServiceContext;

    private IHttpCookieFinder getFinder()
    {
        if ( null == finder )
        {
            finder = Configuration.getInstance().getHttpCookieFinder();
        }

        return finder;
    }

    @WebMethod ( action="addHttpCookie" )
    public HttpCookieResponseMessage addHttpCookie ( @WebParam ( name="request" ) HttpCookieRequestMessage request )
    {
        Calendar timestamp = Calendar.getInstance();
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::addHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - begin" );
            _log.info ( message.get().toString() );
        }

        String remoteUsername         = webServiceContext.getUserPrincipal().getName();
        MessageContext mc             = webServiceContext.getMessageContext();
        ServletRequest servletRequest = (ServletRequest) mc.get ( MessageContext.SERVLET_REQUEST );
        String remoteHostname         = servletRequest.getRemoteHost();
        String remoteAddress          = servletRequest.getRemoteAddr();
        int remotePort                = servletRequest.getRemotePort();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::addHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", RemoteUsername=" );
                message.get().append ( remoteUsername );
                message.get().append ( ", RemoteHostname=" );
                message.get().append ( remoteHostname );
                message.get().append ( ", RemoteAddress=" );
                message.get().append ( remoteAddress );
                message.get().append ( ", RemotePort=" );
                message.get().append ( remotePort );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - end" );
            _log.info ( message.get().toString() );
        }

        return null;
    }

    @WebMethod ( action="editHttpCookie" )
    public HttpCookieResponseMessage editHttpCookie ( @WebParam ( name="request" ) HttpCookieRequestMessage request )
    {
        Calendar timestamp = Calendar.getInstance();
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::editHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - begin" );
            _log.info ( message.get().toString() );
        }

        IHttpCookie record = getFinder().select ( request.getRowID() );
        HttpCookieResponseMessage response = new HttpCookieResponseMessage();

        if ( null == record )
        {
            //  Could not find the record to update
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::editHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - could not find record to edit" );
            response.setResponseMessage ( message.get().toString() );
        }
        else
        {
            //  Found the record to update
            record.setValue ( request );

            String remoteUsername         = webServiceContext.getUserPrincipal().getName();
            MessageContext mc             = webServiceContext.getMessageContext();
            ServletRequest servletRequest = (ServletRequest) mc.get ( MessageContext.SERVLET_REQUEST );
            String remoteHostname         = servletRequest.getRemoteHost();
            String remoteAddress          = servletRequest.getRemoteAddr();
            int remotePort                = servletRequest.getRemotePort();

            boolean result = getFinder().update ( record, remoteUsername, remoteHostname, remoteAddress, remotePort );

            if ( true == result )
            {
                //  Set response message for a successfully updated record
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::editHttpCookie( Request=" );
                    message.get().append ( request.toString() );
                    message.get().append ( ", RemoteUsername=" );
                    message.get().append ( remoteUsername );
                    message.get().append ( ", RemoteHostname=" );
                    message.get().append ( remoteHostname );
                    message.get().append ( ", RemoteAddress=" );
                    message.get().append ( remoteAddress );
                    message.get().append ( ", Timestamp=" );
                    message.get().append ( CommonObject.sdf.format ( timestamp ) );
                    message.get().append ( " ) - record successfully updated" );
                response.setResponseMessage ( message.get().toString() );
            }
            else
            {
                //  Set response message for a successfully updated record
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::editHttpCookie( Request=" );
                    message.get().append ( request.toString() );
                    message.get().append ( ", RemoteUsername=" );
                    message.get().append ( remoteUsername );
                    message.get().append ( ", RemoteHostname=" );
                    message.get().append ( remoteHostname );
                    message.get().append ( ", RemoteAddress=" );
                    message.get().append ( remoteAddress );
                    message.get().append ( ", Timestamp=" );
                    message.get().append ( CommonObject.sdf.format ( timestamp ) );
                    message.get().append ( " ) - unable to update" );
                response.setResponseMessage ( message.get().toString() );
            }
        }

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::editHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - end" );
            _log.info ( message.get().toString() );
        }

        return response;
    }

    @WebMethod ( action="deleteHttpCookie" )
    public HttpCookieResponseMessage deleteHttpCookie ( @WebParam ( name="request" ) HttpCookieRequestMessage request )
    {
        Calendar timestamp = Calendar.getInstance();
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::deleteHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - begin" );
            _log.info ( message.get().toString() );
        }

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::deleteHttpCookie( Request=" );
                message.get().append ( request.toString() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - end" );
            _log.info ( message.get().toString() );
        }

        return null;
    }
}