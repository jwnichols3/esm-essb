package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;

public class HttpParameter extends AbstractHttpParameter implements IHttpParameter
{
    /**
     *
     */
    final private static long serialVersionUID = -1295960072374475274L;
    final private static Logger _log           = Logger.getLogger ( HttpParameter.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "http_parameter", "java.util.Vector" );

        _digester.addObjectCreate ( "http_parameter/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.HttpParameter" );
        _digester.addSetNext      ( "http_parameter/item", "add" );

        _digester.addCallMethod   ( "http_parameter/item/row_id",                "setRowID",                  0 );
        _digester.addCallMethod   ( "http_parameter/item/request_id",            "setRequestID",              0 );
        _digester.addCallMethod   ( "http_parameter/item/parameter_name",        "setParameterName",          0 );
        _digester.addCallMethod   ( "http_parameter/item/parameter_persistence", "setParameterPersistence",   0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IHttpParameter> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IHttpParameter> list  = (List <IHttpParameter>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( HttpParameter.class.getName() );
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
                message.get().append ( HttpParameter.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpParameter.class.getName() );
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
        template.append ( "<http_parameter>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <row_id><![CDATA[]]></row_id>\n" );
        template.append ( "        <request_id><![CDATA[]]></request_id>\n" );
        template.append ( "        <parameter_name><![CDATA[]]></parameter_name>\n" );
        template.append ( "        <parameter_persistence><![CDATA[]]></parameter_persistence>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</http_parameter>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IHttpParameter> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<http_parameter>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IHttpParameter object = (IHttpParameter) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</http_parameter>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( HttpParameter.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}