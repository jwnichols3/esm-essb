package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestUpdateServiceCenterTicket extends TestCase
{
    private static Logger _log    = Logger.getLogger ( TestUpdateServiceCenterTicket.class );
    private static Connection con = null;

    //private static message_duplicate="<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\"><changeEventTypeList><changeEventType>DuplicateChange</changeEventType></changeEventTypeList><changeEventData application=\"OVO\"><timeStamp><seconds>1169762300</seconds><dateTime>2007-01-25T13:58:20-08:00</dateTime></timeStamp><dataList/></changeEventData><ovMessage version=\"1.0\"><messageEnvelope><messageUUID>9ae7eaa8-acab-71db-08a0-4534661e0000</messageUUID><timeStamp><seconds>1169753897</seconds><dateTime>2007-01-25T11:38:17-08:00</dateTime></timeStamp><severity>Normal</severity><messageSource>rdcuxsrv217.insidelive.net</messageSource><eventSource>rdcuxsrv217.insidelive.net</eventSource><primaryMessageRepository/><messageMetadataIdentifier/><managedEntityReference/></messageEnvelope><messageBody><owner/><numberOfDuplicates>28</numberOfDuplicates><messageKey></messageKey><messageKeyRelation></messageKeyRelation><state>ACTIVE</state><creatingCondition/><messageData application=\"OVO\"><data><name>application</name><value type=\"string\">/etc/cron(1M) Clock Daemon</value></data><data><name>group</name><value type=\"string\">unix</value></data><data><name>lastReceiveTimeSeconds</name><value type=\"long\">1169762300</value></data><data><name>object</name><value type=\"string\">cron</value></data><data><name>originalText</name><value type=\"string\">&gt;  CMD: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>receiveTimeSeconds</name><value type=\"long\">1169753899</value></data><data><name>serviceName</name><value type=\"string\"></value></data><data><name>source</name><value type=\"string\">Cron (Solaris)</value></data><data><name>text</name><value type=\"string\">Cron command registered: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>type</name><value type=\"string\"></value></data></messageData><actionResult/><messageMetadataAnnotation/></messageBody></ovMessage></ovMessageChangeEvent>]]></xml></row><row><row_id><![CDATA[5b45d84f45346a1501d03f10ff0ae11e]]></row_id><time_stamp><![CDATA[2007-01-25 14:00:08.527]]></time_stamp><module><![CDATA[NOTIFIER]]></module><message_id><![CDATA[unknown]]></message_id><xml><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public TestUpdateServiceCenterTicket ( String param )
    {
        super ( param );

        try
        {
            Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );
            //Class.forName ( "com.mysql.jdbc.Driver" );
            con = DriverManager.getConnection ( "jdbc:jtds:sqlserver://calntesm201:1433/test_monitoring_dennis", "monitoring", "HYPertext01" );
        }
        catch ( Exception exception )
        {
            throw new NullPointerException ( "could not instantiate connection to test database" );
        }
    }

    public void testCreateAndUpdateTicket() throws IOException
    {
        ServiceCenterTicket ticket1 = ServiceCenterTicket.createNewTicket ( "ESM-Default", "linden", "Sample ticket1 message", "Sample Category", "Sample SubCategory", "Sample Problem", "Sample Product" );

        assertNotNull ( "Could not create new ticket", ticket1 );

        _log.info ( "Created Service Center ticket1 #" + ticket1.getTicketNumber() );

        ServiceCenterTicket ticket2 = new ServiceCenterTicket ( ticket1.getTicketNumber() );

        assertEquals ( "Could not retrieve ticket that was just created", ticket1.getStatus(), ticket2.getStatus() );

        ServiceCenterTicket.updateExistingTicket ( ticket1.getTicketNumber(), "Updated entity", "Updated source" );

        ServiceCenterTicket ticket3 = new ServiceCenterTicket ( ticket1.getTicketNumber() );

        _log.info ( "Updated ticket - source=" + ticket3.getStatus() );
    }

    private static String createOriginalTicket()
    {
        // original message: <ovMessageChangeEvent xmlns="http://openview.hp.com/xmlns/ico/message" version="1.0"><changeEventTypeList><changeEventType>DuplicateChange</changeEventType></changeEventTypeList><changeEventData application="OVO"><timeStamp><seconds>1169754497</seconds><dateTime>2007-01-25T11:48:17-08:00</dateTime></timeStamp><dataList/></changeEventData><ovMessage version="1.0"><messageEnvelope><messageUUID>9ae7eaa8-acab-71db-08a0-4534661e0000</messageUUID><timeStamp><seconds>1169753897</seconds><dateTime>2007-01-25T11:38:17-08:00</dateTime></timeStamp><severity>Normal</severity><messageSource>rdcuxsrv217.insidelive.net</messageSource><eventSource>rdcuxsrv217.insidelive.net</eventSource><primaryMessageRepository/><messageMetadataIdentifier/><managedEntityReference/></messageEnvelope><messageBody><owner/><numberOfDuplicates>2</numberOfDuplicates><messageKey></messageKey><messageKeyRelation></messageKeyRelation><state>ACTIVE</state><creatingCondition/><messageData application="OVO"><data><name>application</name><value type="string">/etc/cron(1M) Clock Daemon</value></data><data><name>group</name><value type="string">unix</value></data><data><name>lastReceiveTimeSeconds</name><value type="long">1169754497</value></data><data><name>object</name><value type="string">cron</value></data><data><name>originalText</name><value type="string">&gt;  CMD: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>receiveTimeSeconds</name><value type="long">1169753899</value></data><data><name>serviceName</name><value type="string"></value></data><data><name>source</name><value type="string">Cron (Solaris)</value></data><data><name>text</name><value type="string">Cron command registered: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>type</name><value type="string"></value></data></messageData><actionResult/><messageMetadataAnnotation/></messageBody></ovMessage></ovMessageChangeEvent>]]></xml></row><row><row_id><![CDATA[5acef56a45346a1501a1860b3477ac00]]></row_id><time_stamp><![CDATA[2007-01-25 11:50:17.193]]></time_stamp><module><![CDATA[NOTIFIER]]></module><message_id><![CDATA[47c8d47a-acad-71db-08a0-4534661e0000]]></message_id><xml><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
        StringBuilder message = new StringBuilder();
        message.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        message.append (    "<changeEventTypeList>" );
        message.append (        "<changeEventType>DuplicateChange</changeEventType>" );
        message.append (    "</changeEventTypeList>" );
        message.append (    "<changeEventData application=\"OVO\">" );
        message.append (        "<timeStamp>" );
        message.append (            "<seconds>1169754497</seconds>" );
        message.append (            "<dateTime>2007-01-25T11:48:17-08:00</dateTime>" );
        message.append (        "</timeStamp>" );
        message.append (        "<dataList/>" );
        message.append (    "</changeEventData>" );
        message.append (    "<ovMessage version=\"1.0\">" );
        message.append (        "<messageEnvelope>" );
        message.append (        "<messageUUID>9ae7eaa8-acab-71db-08a0-4534661e0000</messageUUID>" );
        message.append (            "<timeStamp>" );
        message.append (                "<seconds>1169753897</seconds>" );
        message.append (                "<dateTime>2007-01-25T11:38:17-08:00</dateTime>" );
        message.append (            "</timeStamp>" );
        message.append (            "<severity>Normal</severity>" );
        message.append (            "<messageSource>rdcuxsrv217.insidelive.net</messageSource>" );
        message.append (            "<eventSource>rdcuxsrv217.insidelive.net</eventSource>" );
        message.append (            "<primaryMessageRepository/>" );
        message.append (            "<messageMetadataIdentifier/>" );
        message.append (            "<managedEntityReference/>" );
        message.append (         "</messageEnvelope>" );
        message.append (         "<messageBody>" );
        message.append (            "<owner/>" );
        message.append (            "<numberOfDuplicates>2</numberOfDuplicates>" );
        message.append (            "<messageKey></messageKey>" );
        message.append (            "<messageKeyRelation></messageKeyRelation>" );
        message.append (            "<state>ACTIVE</state>" );
        message.append (            "<creatingCondition/>" );
        message.append (            "<messageData application=\"OVO\">" );
        message.append (                "<data>" );
        message.append (                    "<name>application</name>" );
        message.append (                    "<value type=\"string\">/etc/cron(1M) Clock Daemon</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>group</name>" );
        message.append (                    "<value type=\"string\">unix</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>lastReceiveTimeSeconds</name>" );
        message.append (                    "<value type=\"long\">1169754497</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>object</name>" );
        message.append (                    "<value type=\"string\">cron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>originalText</name>" );
        message.append (                    "<value type=\"string\">&gt;  CMD: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>receiveTimeSeconds</name>" );
        message.append (                    "<value type=\"long\">1169753899</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>serviceName</name>" );
        message.append (                    "<value type=\"string\"></value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>source</name>" );
        message.append (                    "<value type=\"string\">Cron (Solaris)</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>text</name>" );
        message.append (                    "<value type=\"string\">Cron command registered: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>type</name>" );
        message.append (                    "<value type=\"string\"></value>" );
        message.append (                "</data>" );
        message.append (            "</messageData>" );
        message.append (            "<actionResult/>" );
        message.append (            "<messageMetadataAnnotation/>" );
        message.append (        "</messageBody>" );
        message.append (    "</ovMessage>" );
        message.append ( "</ovMessageChangeEvent>" );

        return message.toString();
    }

    private static String createDuplicateMessage ( int numDuplicates )
    {
        // original message: <ovMessageChangeEvent xmlns="http://openview.hp.com/xmlns/ico/message" version="1.0"><changeEventTypeList><changeEventType>DuplicateChange</changeEventType></changeEventTypeList><changeEventData application="OVO"><timeStamp><seconds>1169762300</seconds><dateTime>2007-01-25T13:58:20-08:00</dateTime></timeStamp><dataList/></changeEventData><ovMessage version="1.0"><messageEnvelope><messageUUID>9ae7eaa8-acab-71db-08a0-4534661e0000</messageUUID><timeStamp><seconds>1169753897</seconds><dateTime>2007-01-25T11:38:17-08:00</dateTime></timeStamp><severity>Normal</severity><messageSource>rdcuxsrv217.insidelive.net</messageSource><eventSource>rdcuxsrv217.insidelive.net</eventSource><primaryMessageRepository/><messageMetadataIdentifier/><managedEntityReference/></messageEnvelope><messageBody><owner/><numberOfDuplicates>28</numberOfDuplicates><messageKey></messageKey><messageKeyRelation></messageKeyRelation><state>ACTIVE</state><creatingCondition/><messageData application="OVO"><data><name>application</name><value type="string">/etc/cron(1M) Clock Daemon</value></data><data><name>group</name><value type="string">unix</value></data><data><name>lastReceiveTimeSeconds</name><value type="long">1169762300</value></data><data><name>object</name><value type="string">cron</value></data><data><name>originalText</name><value type="string">&gt;  CMD: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>receiveTimeSeconds</name><value type="long">1169753899</value></data><data><name>serviceName</name><value type="string"></value></data><data><name>source</name><value type="string">Cron (Solaris)</value></data><data><name>text</name><value type="string">Cron command registered: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value></data><data><name>type</name><value type="string"></value></data></messageData><actionResult/><messageMetadataAnnotation/></messageBody></ovMessage></ovMessageChangeEvent>]]></xml></row><row><row_id><![CDATA[5b45d84f45346a1501d03f10ff0ae11e]]></row_id><time_stamp><![CDATA[2007-01-25 14:00:08.527]]></time_stamp><module><![CDATA[NOTIFIER]]></module><message_id><![CDATA[unknown]]></message_id><xml><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
        StringBuilder message = new StringBuilder();
        message.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        message.append (     "<changeEventTypeList>" );
        message.append (         "<changeEventType>DuplicateChange</changeEventType>" );
        message.append (     "</changeEventTypeList>" );
        message.append (     "<changeEventData application=\"OVO\">" );
        message.append (        "<timeStamp>" );
        message.append (            "<seconds>1169762300</seconds>" );
        message.append (            "<dateTime>2007-01-25T13:58:20-08:00</dateTime>" );
        message.append (        "</timeStamp>" );
        message.append (        "<dataList/>" );
        message.append (     "</changeEventData>" );
        message.append (     "<ovMessage version=\"1.0\">" );
        message.append (         "<messageEnvelope>" );
        message.append (             "<messageUUID>9ae7eaa8-acab-71db-08a0-4534661e0000</messageUUID>" );
        message.append (             "<timeStamp>" );
        message.append (                "<seconds>1169753897</seconds>" );
        message.append (                "<dateTime>2007-01-25T11:38:17-08:00</dateTime>" );
        message.append (             "</timeStamp>" );
        message.append (             "<severity>Normal</severity>" );
        message.append (             "<messageSource>rdcuxsrv217.insidelive.net</messageSource>" );
        message.append (             "<eventSource>rdcuxsrv217.insidelive.net</eventSource>" );
        message.append (             "<primaryMessageRepository/>" );
        message.append (             "<messageMetadataIdentifier/>" );
        message.append (             "<managedEntityReference/>" );
        message.append (        "</messageEnvelope>" );
        message.append (        "<messageBody>" );
        message.append (            "<owner/>" );
        message.append (            "<numberOfDuplicates>" + numDuplicates + "</numberOfDuplicates>" );
        message.append (            "<messageKey></messageKey>" );
        message.append (            "<messageKeyRelation></messageKeyRelation>" );
        message.append (            "<state>ACTIVE</state>" );
        message.append (            "<creatingCondition/>" );
        message.append (            "<messageData application=\"OVO\">" );
        message.append (                "<data>" );
        message.append (                    "<name>application</name>" );
        message.append (                    "<value type=\"string\">/etc/cron(1M) Clock Daemon</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>group</name>" );
        message.append (                    "<value type=\"string\">unix</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>lastReceiveTimeSeconds</name>" );
        message.append (                    "<value type=\"long\">1169762300</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>object</name>" );
        message.append (                    "<value type=\"string\">cron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>originalText</name>" );
        message.append (                    "<value type=\"string\">&gt;  CMD: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>receiveTimeSeconds</name>" );
        message.append (                    "<value type=\"long\">1169753899</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>serviceName</name>" );
        message.append (                    "<value type=\"string\"></value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>source</name>" );
        message.append (                    "<value type=\"string\">Cron (Solaris)</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>text</name>" );
        message.append (                    "<value type=\"string\">Cron command registered: /opt/BGIcron/bin/crondir /etc/cron.freq &gt;/var/cron/cron.freq 2&gt;&amp;1#BGIcron</value>" );
        message.append (                "</data>" );
        message.append (                "<data>" );
        message.append (                    "<name>type</name>" );
        message.append (                    "<value type=\"string\"> </value>" );
        message.append (                "</data>" );
        message.append (            "</messageData>" );
        message.append (            "<actionResult/>" );
        message.append (            "<messageMetadataAnnotation/>" );
        message.append (        "</messageBody>" );
        message.append (    "</ovMessage>" );
        message.append (    "</ovMessageChangeEvent>>" );

        return message.toString();
    }

    private static String createOriginalMessage()
    {
        return null;
    }
};
