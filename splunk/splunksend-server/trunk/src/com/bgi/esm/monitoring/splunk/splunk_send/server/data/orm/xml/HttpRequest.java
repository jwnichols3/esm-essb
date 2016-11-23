package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class HttpRequest extends AbstractHttpRequest implements IHttpRequest
{
    /**
     *
     */
    final private static long serialVersionUID = -51431911115677323L;
    final private static Logger _log           = Logger.getLogger ( HttpRequest.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "http_request", "java.util.Vector" );

        _digester.addObjectCreate ( "http_request/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.HttpRequest" );
        _digester.addSetNext      ( "http_request/item", "add" );

        _digester.addCallMethod   ( "http_request/item/request_id",           "setRequestID",             0 );
        _digester.addCallMethod   ( "http_request/item/auth_type",            "setAuthType",              0 );
        _digester.addCallMethod   ( "http_request/item/context_path",         "setContextPath",           0 );
        _digester.addCallMethod   ( "http_request/item/method",               "setMethod",                0 );
        _digester.addCallMethod   ( "http_request/item/path_info",            "setPathInfo",              0 );
        _digester.addCallMethod   ( "http_request/item/path_info_translated", "setPathInfoTranslated",    0 );
        _digester.addCallMethod   ( "http_request/item/remote_user",          "setRemoteUser",            0 );
        _digester.addCallMethod   ( "http_request/item/requested_session_id", "setRequestedSessionID",    0 );
        _digester.addCallMethod   ( "http_request/item/request_uri",          "setRequestUri",            0 );
        _digester.addCallMethod   ( "http_request/item/request_url",          "setRequestUrl",            0 );
        _digester.addCallMethod   ( "http_request/item/servlet_path",         "setServletPath",           0 );
        _digester.addCallMethod   ( "http_request/item/user_principal",       "setUserPrincipal",         0 );
        _digester.addCallMethod   ( "http_request/item/character_encoding",   "setCharacterEncoding",     0 );
        _digester.addCallMethod   ( "http_request/item/content_type",         "setContentType",           0 );
        _digester.addCallMethod   ( "http_request/item/local_address",        "setLocalAddress",          0 );
        _digester.addCallMethod   ( "http_request/item/protocol",             "setProtocol",              0 );
        _digester.addCallMethod   ( "http_request/item/remote_address",       "setRemoteAddress",         0 );
        _digester.addCallMethod   ( "http_request/item/remote_host",          "setRemoteHost",            0 );
        _digester.addCallMethod   ( "http_request/item/scheme",               "setScheme",                0 );
        _digester.addCallMethod   ( "http_request/item/server_name",          "setServerName",            0 );
        _digester.addCallMethod   ( "http_request/item/request_time",         "setRequestTime",           0 );
        _digester.addCallMethod   ( "http_request/item/process_time",         "setProcessTime",           0 );
        _digester.addCallMethod   ( "http_request/item/content_length",       "setContentLength",         0 );
        _digester.addCallMethod   ( "http_request/item/local_port",           "setLocalPort",             0 );
        _digester.addCallMethod   ( "http_request/item/remote_port",          "setRemotePort",            0 );
        _digester.addCallMethod   ( "http_request/item/server_port",          "setServerPort",            0 );
        _digester.addCallMethod   ( "http_request/item/is_processed",         "setIsProcessed",           0 );
        _digester.addCallMethod   ( "http_request/item/was_successful",       "setWasSuccessful",         0 );
        _digester.addCallMethod   ( "http_request/item/return_code",          "setReturnCode",            0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IHttpRequest> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IHttpRequest> list  = (List <IHttpRequest>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( HttpRequest.class.getName() );
                    message.get().append ( "::parseFile ( filename=" );
                    message.get().append ( filename );
                    message.get().append ( " ) - number of elements parsed: " );
                    message.get().append ( list.size() );
                _log.info ( message.get().toString() );
            }

            return list;
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpRequest.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpRequest.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - SAXException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }

        return null;
    }

    public static String generateXMLTemplate()
    {
        StringBuilder template = new StringBuilder();
        template.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
        template.append ( "<http_request>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <request_id><![CDATA[]]></request_id>\n" );
        template.append ( "        <auth_type><![CDATA[]]></auth_type>\n" );
        template.append ( "        <context_path><![CDATA[]]></context_path>\n" );
        template.append ( "        <method><![CDATA[]]></method>\n" );
        template.append ( "        <path_info><![CDATA[]]></path_info>\n" );
        template.append ( "        <path_info_translated><![CDATA[]]></path_info_translated>\n" );
        template.append ( "        <remote_user><![CDATA[]]></remote_user>\n" );
        template.append ( "        <requested_session_id><![CDATA[]]></requested_session_id>\n" );
        template.append ( "        <request_uri><![CDATA[]]></request_uri>\n" );
        template.append ( "        <request_url><![CDATA[]]></request_url>\n" );
        template.append ( "        <servlet_path><![CDATA[]]></servlet_path>\n" );
        template.append ( "        <user_principal><![CDATA[]]></user_principal>\n" );
        template.append ( "        <character_encoding><![CDATA[]]></character_encoding>\n" );
        template.append ( "        <content_type><![CDATA[]]></content_type>\n" );
        template.append ( "        <local_address><![CDATA[]]></local_address>\n" );
        template.append ( "        <protocol><![CDATA[]]></protocol>\n" );
        template.append ( "        <remote_address><![CDATA[]]></remote_address>\n" );
        template.append ( "        <remote_host><![CDATA[]]></remote_host>\n" );
        template.append ( "        <scheme><![CDATA[]]></scheme>\n" );
        template.append ( "        <server_name><![CDATA[]]></server_name>\n" );
        template.append ( "        <request_time><![CDATA[]]></request_time>\n" );
        template.append ( "        <process_time><![CDATA[]]></process_time>\n" );
        template.append ( "        <content_length><![CDATA[]]></content_length>\n" );
        template.append ( "        <local_port><![CDATA[]]></local_port>\n" );
        template.append ( "        <remote_port><![CDATA[]]></remote_port>\n" );
        template.append ( "        <server_port><![CDATA[]]></server_port>\n" );
        template.append ( "        <is_processed><![CDATA[]]></is_processed>\n" );
        template.append ( "        <was_successful><![CDATA[]]></was_successful>\n" );
        template.append ( "        <return_code><![CDATA[]]></return_code>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</http_request>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IHttpRequest> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<http_request>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IHttpRequest object = (IHttpRequest) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</http_request>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpRequest.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}