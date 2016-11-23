package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

public class HttpCookie extends AbstractHttpCookie implements IHttpCookie
{
    /**
     *
     */
    final private static long serialVersionUID = 9189627568218586356L;
    final private static Logger _log           = Logger.getLogger ( HttpCookie.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "http_cookie", "java.util.Vector" );

        _digester.addObjectCreate ( "http_cookie/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.HttpCookie" );
        _digester.addSetNext      ( "http_cookie/item", "add" );

        _digester.addCallMethod   ( "http_cookie/item/row_id",             "setRowID",               0 );
        _digester.addCallMethod   ( "http_cookie/item/request_id",         "setRequestID",           0 );
        _digester.addCallMethod   ( "http_cookie/item/cookie_comment",     "setCookieComment",       0 );
        _digester.addCallMethod   ( "http_cookie/item/domain",             "setDomain",              0 );
        _digester.addCallMethod   ( "http_cookie/item/path",               "setPath",                0 );
        _digester.addCallMethod   ( "http_cookie/item/cookie_name",        "setCookieName",          0 );
        _digester.addCallMethod   ( "http_cookie/item/cookie_persistence", "setCookiePersistence",   0 );
        _digester.addCallMethod   ( "http_cookie/item/is_secure",          "setIsSecure",            0 );
        _digester.addCallMethod   ( "http_cookie/item/max_age",            "setMaxAge",              0 );
        _digester.addCallMethod   ( "http_cookie/item/version",            "setVersion",             0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IHttpCookie> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IHttpCookie> list  = (List <IHttpCookie>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( HttpCookie.class.getName() );
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
                message.get().append ( HttpCookie.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpCookie.class.getName() );
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
        template.append ( "<http_cookie>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <row_id><![CDATA[]]></row_id>\n" );
        template.append ( "        <request_id><![CDATA[]]></request_id>\n" );
        template.append ( "        <cookie_comment><![CDATA[]]></cookie_comment>\n" );
        template.append ( "        <domain><![CDATA[]]></domain>\n" );
        template.append ( "        <path><![CDATA[]]></path>\n" );
        template.append ( "        <cookie_name><![CDATA[]]></cookie_name>\n" );
        template.append ( "        <cookie_persistence><![CDATA[]]></cookie_persistence>\n" );
        template.append ( "        <is_secure><![CDATA[]]></is_secure>\n" );
        template.append ( "        <max_age><![CDATA[]]></max_age>\n" );
        template.append ( "        <version><![CDATA[]]></version>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</http_cookie>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IHttpCookie> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<http_cookie>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IHttpCookie object = (IHttpCookie) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</http_cookie>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpCookie.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}