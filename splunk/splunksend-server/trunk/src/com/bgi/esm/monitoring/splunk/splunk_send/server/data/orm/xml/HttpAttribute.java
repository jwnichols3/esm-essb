package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

public class HttpAttribute extends AbstractHttpAttribute implements IHttpAttribute
{
    /**
     *
     */
    final private static long serialVersionUID = 7506432935251540975L;
    final private static Logger _log           = Logger.getLogger ( HttpAttribute.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "http_attribute", "java.util.Vector" );

        _digester.addObjectCreate ( "http_attribute/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.HttpAttribute" );
        _digester.addSetNext      ( "http_attribute/item", "add" );

        _digester.addCallMethod   ( "http_attribute/item/row_id",                "setRowID",                  0 );
        _digester.addCallMethod   ( "http_attribute/item/request_id",            "setRequestID",              0 );
        _digester.addCallMethod   ( "http_attribute/item/attribute_name",        "setAttributeName",          0 );
        _digester.addCallMethod   ( "http_attribute/item/attribute_persistence", "setAttributePersistence",   0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IHttpAttribute> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IHttpAttribute> list  = (List <IHttpAttribute>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( HttpAttribute.class.getName() );
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
                message.get().append ( HttpAttribute.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpAttribute.class.getName() );
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
        template.append ( "<http_attribute>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <row_id><![CDATA[]]></row_id>\n" );
        template.append ( "        <request_id><![CDATA[]]></request_id>\n" );
        template.append ( "        <attribute_name><![CDATA[]]></attribute_name>\n" );
        template.append ( "        <attribute_persistence><![CDATA[]]></attribute_persistence>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</http_attribute>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IHttpAttribute> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<http_attribute>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IHttpAttribute object = (IHttpAttribute) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</http_attribute>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpAttribute.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}