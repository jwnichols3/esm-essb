package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;

public class HttpHeader extends AbstractHttpHeader implements IHttpHeader
{
    /**
     *
     */
    final private static long serialVersionUID = 2163976928657849073L;
    final private static Logger _log           = Logger.getLogger ( HttpHeader.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "http_header", "java.util.Vector" );

        _digester.addObjectCreate ( "http_header/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.HttpHeader" );
        _digester.addSetNext      ( "http_header/item", "add" );

        _digester.addCallMethod   ( "http_header/item/row_id",             "setRowID",               0 );
        _digester.addCallMethod   ( "http_header/item/request_id",         "setRequestID",           0 );
        _digester.addCallMethod   ( "http_header/item/header_name",        "setHeaderName",          0 );
        _digester.addCallMethod   ( "http_header/item/header_persistence", "setHeaderPersistence",   0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IHttpHeader> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IHttpHeader> list  = (List <IHttpHeader>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( HttpHeader.class.getName() );
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
                message.get().append ( HttpHeader.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpHeader.class.getName() );
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
        template.append ( "<http_header>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <row_id><![CDATA[]]></row_id>\n" );
        template.append ( "        <request_id><![CDATA[]]></request_id>\n" );
        template.append ( "        <header_name><![CDATA[]]></header_name>\n" );
        template.append ( "        <header_persistence><![CDATA[]]></header_persistence>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</http_header>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IHttpHeader> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<http_header>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IHttpHeader object = (IHttpHeader) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</http_header>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpHeader.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}