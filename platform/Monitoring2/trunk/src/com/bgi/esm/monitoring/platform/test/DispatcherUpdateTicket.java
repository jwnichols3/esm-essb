package com.bgi.esm.monitoring.platform.test;

import java.text.DecimalFormat;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.Session;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractQueue;

public class DispatcherUpdateTicket
{
    public DispatcherUpdateTicket ( String queue_name ) throws Exception
    {
        _queueName = queue_name;

        Context context = new InitialContext();

        System.out.println ( "Using dispatcher queue: " + queue_name );
        
        Queue queue = (Queue) context.lookup(queue_name);
        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(AbstractQueue.QUEUE_FACTORY);
        
        _connection = factory.createQueueConnection();
        
        _session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        _sender = _session.createSender(queue);
    }

    public void runClient() throws Exception
    {
        BackEndFacade bef = new BackEndFacade();

        long timestamp = System.currentTimeMillis();
        System.out.println ( "Timestamp: " + timestamp );

        DispatcherMessage dm1 = new DispatcherMessage();
        dm1.setTicketMessage ( createTestOriginalTicketMessage ( timestamp ) );
        dm1.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om1     = _session.createObjectMessage ( dm1 );
        _sender.send ( om1 );

        byte input[] = new byte[1024];
        System.out.println ( "NOTE TO TESTER:" );
        System.out.println ( "   The EEB just simulated an event that and sent an Alarmpoint notification " );
        System.out.println ( "   with the associated Service Center ticket number.  You can take the chance " );
        System.out.println ( "   to review both the notification and ticket number." );
        System.out.println ( "" );
        System.out.println ( "   When you are ready to perform the ticket update..." );
        System.out.println ( "" );
        System.out.println ( "Press the <ENTER> key to continue..." );
        System.in.read ( input );

        DispatcherMessage dm2 = new DispatcherMessage();
        dm2.setTicketMessage ( createTestDuplicateTicketMessage ( timestamp, 1 ) );
        dm2.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om2     = _session.createObjectMessage ( dm2 );
        _sender.send ( om2 );
    }

    private static DataMapRule createTestDataMapRule()
    {
        DataMapRule rule = new DataMapRule("group");
        rule.setMethod("ticket");
        rule.setAlarmpointGroup("Dennis Test Group");
        rule.setAlarmpointScript("BGI VPO");
        rule.setPeregrineCategory("peregrin cat");
        rule.setPeregrineSubCategory("peregrin subcat");
        rule.setPeregrineProduct("peregrin product");
        rule.setPeregrineProblem("peregrin problem");
        rule.setPeregrineAssignment("peregrin assignment");
        rule.setPeregrineLocation("peregrin location");

        return rule;
    }

    private static String createMessageID ( long timestamp )
    {
        StringBuilder buffer = new StringBuilder ( "test-message-num-" );
        buffer.append ( timestamp );

        return buffer.toString();
    }

    private static TicketMessage createTestOriginalTicketMessage ( long timestamp )
    {
		Calendar calendar = Calendar.getInstance();
        TicketMessage tm  = new TicketMessage();
		tm.setMessageId      ( createMessageID ( timestamp ) );
		tm.setSourceNode     ( "test-source-node" );
		tm.setSeverity       ( "test-severity" );
		tm.setSuppressCount  ( 0 );

		tm.setTimeStamp      ( calendar);
		tm.setApplication    ( "test-app" );
		tm.setMessageGroup   ( "Dennis Test Group" );
		tm.setObject         ( "test-object" );
		tm.setMessageText    ( "test-message-text" );

        return tm;
    }

    private static TicketMessage createTestDuplicateTicketMessage ( long timestamp, int num_duplicates )
    {
		Calendar calendar = Calendar.getInstance();
        TicketMessage tm  = new TicketMessage();
		tm.setMessageId      ( createMessageID ( timestamp ) );
		tm.setSourceNode     ( "test-source-node" );
		tm.setSeverity       ( "test-severity" );
		tm.setSuppressCount  ( num_duplicates );

		tm.setTimeStamp      ( calendar);
		tm.setApplication    ( "test-app" );
		tm.setMessageGroup   ( "Dennis Test Group" );
		tm.setObject         ( "test-object" );
		tm.setMessageText    ( "test-message-text" );

        return tm;
    }

    public static void main ( String args[] ) throws Exception
    {
        System.out.println("begin");

        DispatcherUpdateTicket d = new DispatcherUpdateTicket( args[0] );
        d.runClient();

        System.out.println("end");
    }

    private static String createOriginalOVOTestMessage ( Calendar creation_time, String ovo_message_id )
    {
        long timestamp      = creation_time.getTime().getTime();
        long timestamp_secs = timestamp / 1000L;
        int timezone_offset = creation_time.getTimeZone().getRawOffset();
        int offset_hours    = timezone_offset / 3600000;
        int offset_minutes  = timezone_offset % 3600000 / 60000;

        StringBuilder buffer = new StringBuilder();
        buffer.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
        buffer.append ( "<ovMessage xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        buffer.append ( "<messageEnvelope>" );
        buffer.append ( "<messageUUID>" );
        buffer.append ( ovo_message_id );
        buffer.append ( "</messageUUID>" );
        buffer.append ( "<timeStamp>" );
        buffer.append ( "<seconds>" );
        buffer.append ( timestamp_secs );
        buffer.append ( "</seconds>" );
        buffer.append ( "<dateTime>" );
        buffer.append ( creation_time.get ( Calendar.YEAR ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( creation_time.get ( Calendar.MONTH ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( creation_time.get ( Calendar.DATE ) ) );
        buffer.append ( "T" );
        buffer.append ( df2.format ( creation_time.get ( Calendar.HOUR_OF_DAY ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( creation_time.get ( Calendar.MINUTE ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( creation_time.get ( Calendar.SECOND ) ) );
        if ( offset_hours > 0 )
        {
            buffer.append ( "+" );
            buffer.append ( df2.format ( offset_hours ) );
        }
        else
        {
            buffer.append ( df2.format ( offset_hours ) );
        }
        buffer.append ( ":" );
        buffer.append ( df2.format ( offset_minutes ) );
        buffer.append ( "</dateTime>" );
        buffer.append ( "</timeStamp>" );
        buffer.append ( "<severity>Normal</severity>" );
        buffer.append ( "<messageSource>test_node</messageSource>" );
        buffer.append ( "<eventSource>test_node.insidelive.net</eventSource>" );
        buffer.append ( "<primaryMessageRepository>test_node</primaryMessageRepository>" );
        buffer.append ( "<messageMetadataIdentifier/>" );
        buffer.append ( "<managedEntityReference/>" );
        buffer.append ( "</messageEnvelope>" );
        buffer.append ( "<messageBody>" );
        buffer.append ( "<owner/>" );
        buffer.append ( "<numberOfDuplicates>0</numberOfDuplicates>" );
        buffer.append ( "<messageKey>test_node:test:mytest</messageKey>" );
        buffer.append ( "<messageKeyRelation>" );
        buffer.append ( "</messageKeyRelation>" );
        buffer.append ( "<state>PENDING</state>" );
        buffer.append ( "<creatingCondition/>" );
        buffer.append ( "<messageData application=\"OVO\">" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>INSTANCE</name>" );
        buffer.append ( "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>application</name>" );
        buffer.append ( "<value type=\"string\">SSM-no-app-def</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>group</name>" );
        buffer.append ( "<value type=\"string\">test</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>lastReceiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">0</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>object</name>" );
        buffer.append ( "<value type=\"string\">SSM-no-obj-def-CLI</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>originalText</name>" );
        buffer.append ( "<value type=\"string\">Node:          Message group: testApplication:   SSM-no-app-defObject:        SSM-no-obj-def-CLISeverity:      NormalText:          CLI-test_111++k++mytest</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>receiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">1172536359</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>serviceName</name>" );
        buffer.append ( "<value type=\"string\">INFRA@@test_node.insidelive.net</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>source</name>" );
        buffer.append ( "<value type=\"string\">SSM - opcmsg</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>text</name>" );
        buffer.append ( "<value type=\"string\">test_111</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>type</name>" );
        buffer.append ( "<value type=\"string\">" );
        buffer.append ( "</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "</messageData>" );
        buffer.append ( "<actionResult/>" );
        buffer.append ( "<messageMetadataAnnotation/>" );
        buffer.append ( "</messageBody>" );
        buffer.append ( "</ovMessage>" );

        return buffer.toString();
    }

    private static String createOriginalOVODuplicateMessage ( Calendar original_time_of_creation, int num_duplicates )
    {
        long timestamp      = original_time_of_creation.getTime().getTime();
        long timestamp_secs = timestamp / 1000L;
        int timezone_offset = original_time_of_creation.getTimeZone().getRawOffset();
        int offset_hours    = timezone_offset / 3600000;
        int offset_minutes  = timezone_offset % 3600000 / 60000;

        StringBuilder buffer = new StringBuilder();
        buffer.append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
        buffer.append ( "<ovMessageChangeEvent xmlns=\"http://openview.hp.com/xmlns/ico/message\" version=\"1.0\">" );
        buffer.append ( "<changeEventTypeList>" );
        buffer.append ( "<changeEventType>Own</changeEventType>" );
        buffer.append ( "</changeEventTypeList>" );
        buffer.append ( "<changeEventData application=\"OVO\">" );
        buffer.append ( "<timeStamp>" );
        buffer.append ( "<seconds>" );
        buffer.append ( timestamp_secs );
        buffer.append ( "</seconds>" );
        buffer.append ( "<dateTime>" );
        buffer.append ( original_time_of_creation.get ( Calendar.YEAR ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( original_time_of_creation.get ( Calendar.MONTH ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( original_time_of_creation.get ( Calendar.DATE ) ) );
        buffer.append ( "T" );
        buffer.append ( df2.format ( original_time_of_creation.get ( Calendar.HOUR_OF_DAY ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( original_time_of_creation.get ( Calendar.MINUTE ) ) );
        buffer.append ( "-" );
        buffer.append ( df2.format ( original_time_of_creation.get ( Calendar.SECOND ) ) );
        if ( offset_hours > 0 )
        {
            buffer.append ( "+" );
            buffer.append ( df2.format ( offset_hours ) );
        }
        else
        {
            buffer.append ( df2.format ( offset_hours ) );
        }
        buffer.append ( ":" );
        buffer.append ( df2.format ( offset_minutes ) );
        buffer.append ( "</dateTime>" );
        buffer.append ( "</timeStamp>" );
        buffer.append ( "<dataList>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>operator</name>" );
        buffer.append ( "<value type=\"string\">alarmpoint</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "</dataList>" );
        buffer.append ( "</changeEventData>" );
        buffer.append ( "<ovMessage version=\"1.0\">" );
        buffer.append ( "<messageEnvelope>" );
        buffer.append ( "<messageUUID>07480dce-c5fa-71db-0317-4534661e0000</messageUUID>" );
        buffer.append ( "<timeStamp>" );
        buffer.append ( "<seconds>1172536359</seconds>" );
        buffer.append ( "<dateTime>2007-02-26T16:32:39-08:00</dateTime>" );
        buffer.append ( "</timeStamp>" );
        buffer.append ( "<severity>Normal</severity>" );
        buffer.append ( "<messageSource>test_node.insidelive.net</messageSource>" );
        buffer.append ( "<eventSource>test_node.insidelive.net</eventSource>" );
        buffer.append ( "<primaryMessageRepository/>" );
        buffer.append ( "<messageMetadataIdentifier/>" );
        buffer.append ( "<managedEntityReference/>" );
        buffer.append ( "</messageEnvelope>" );
        buffer.append ( "<messageBody>" );
        buffer.append ( "<owner>alarmpoint</owner>" );
        buffer.append ( "<numberOfDuplicates>" );
        buffer.append ( num_duplicates );
        buffer.append ( "</numberOfDuplicates>" );
        buffer.append ( "<messageKey>test_node:test:mytest</messageKey>" );
        buffer.append ( "<messageKeyRelation>" );
        buffer.append ( "</messageKeyRelation>" );
        buffer.append ( "<state>OWNED</state>" );
        buffer.append ( "<creatingCondition/>" );
        buffer.append ( "<messageData application=\"OVO\">" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>INSTANCE</name>" );
        buffer.append ( "<value type=\"string\">&lt;$OPTION(instance)&gt;</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>application</name>" );
        buffer.append ( "<value type=\"string\">SSM-no-app-def</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>group</name>" );
        buffer.append ( "<value type=\"string\">test</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>lastReceiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">1172536359</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>object</name>" );
        buffer.append ( "<value type=\"string\">SSM-no-obj-def-CLI</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>originalText</name>" );
        buffer.append ( "<value type=\"string\">Node:          Message group: testApplication:   SSM-no-app-defObject:        SSM-no-obj-def-CLISeverity:      NormalText:          CLI-test_111++k++mytest</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>receiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">1172536359</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>serviceName</name>" );
        buffer.append ( "<value type=\"string\">INFRA@@test_node.insidelive.net</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>source</name>" );
        buffer.append ( "<value type=\"string\">SSM - opcmsg</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>text</name>" );
        buffer.append ( "<value type=\"string\">test_111</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>type</name>" );
        buffer.append ( "<value type=\"string\">" );
        buffer.append ( "</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data sequenceNumber=\"1\">" );
        buffer.append ( "<name>annotation</name>" );
        buffer.append ( "<value type=\"string\">Sent to AP group: </value>" );
        buffer.append ( "</data>" );
        buffer.append ( "</messageData>" );
        buffer.append ( "<actionResult/>" );
        buffer.append ( "<messageMetadataAnnotation/>" );
        buffer.append ( "</messageBody>" );
        buffer.append ( "</ovMessage>" );
        buffer.append ( "</ovMessageChangeEvent>" );

        return buffer.toString();
    }

    /**
     * 
     */
    final private static DecimalFormat df2 = new DecimalFormat ( "00" );
    QueueConnection _connection;
    QueueSender _sender;
    QueueSession _session;
    String _queueName;
};
