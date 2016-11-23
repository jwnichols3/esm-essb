package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractDataMap;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

public class DataMap extends AbstractDataMap implements IDataMap
{
    /**
     *
     */
    final private static long serialVersionUID = 435819213241961291L;
    final private static Logger _log           = Logger.getLogger ( DataMap.class );

    public static Digester getDigester()
    {
        Digester _digester = new Digester();
        _digester.setValidating   ( false );
        _digester.addObjectCreate ( "data_map", "java.util.Vector" );

        _digester.addObjectCreate ( "data_map/item", "com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.xml.DataMap" );
        _digester.addSetNext      ( "data_map/item", "add" );

        _digester.addCallMethod   ( "data_map/item/rule_id",          "setRuleID",            0 );
        _digester.addCallMethod   ( "data_map/item/application_name", "setApplicationName",   0 );
        _digester.addCallMethod   ( "data_map/item/hostname",         "setHostname",          0 );
        _digester.addCallMethod   ( "data_map/item/target_path",      "setTargetPath",        0 );

        return _digester;
    }

    @SuppressWarnings ( "unchecked" )
    public static List <IDataMap> parseFile ( String filename )
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( filename ) );
            Digester digester         = getDigester();
            List <IDataMap> list  = (List <IDataMap>) digester.parse ( bais );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( DataMap.class.getName() );
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
                message.get().append ( DataMap.class.getName() );
                message.get().append ( "::parseFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - IOException when parsing configuration file: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( SAXException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( DataMap.class.getName() );
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
        template.append ( "<data_map>\n" );
        template.append ( "    <item>\n" );
        template.append ( "        <rule_id><![CDATA[]]></rule_id>\n" );
        template.append ( "        <application_name><![CDATA[]]></application_name>\n" );
        template.append ( "        <hostname><![CDATA[]]></hostname>\n" );
        template.append ( "        <target_path><![CDATA[]]></target_path>\n" );
        template.append ( "    </item>\n" );
        template.append ( "</data_map>\n" );

        return template.toString();
    }

    public static void generateXMLFile ( List <IDataMap> objects, String filename )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes() );
                outfile.write ( "<data_map>\n".getBytes() );
                for ( int counter = 0; counter < objects.size(); counter++ )
                {
                    IDataMap object = (IDataMap) objects.get ( counter );
                    outfile.write ( object.toXML().getBytes() );

                    if ( counter+1 < objects.size() )
                    {
                        outfile.write ( "\n".getBytes() );
                    }
                }
                outfile.write ( "</data_map>".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( DataMap.class.getName() );
                message.get().append ( "::generateXMLFile ( filename=" );
                message.get().append ( filename );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
    }
}