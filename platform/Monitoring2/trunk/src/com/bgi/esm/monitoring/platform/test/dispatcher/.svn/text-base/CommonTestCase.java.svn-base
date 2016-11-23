package com.bgi.esm.monitoring.platform.test.dispatcher;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.Session;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractQueue;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractTopic;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    final private static DecimalFormat df2        = new DecimalFormat ( "00" );
    final protected Logger _log                   = Logger.getLogger ( CommonTestCase.class );
    protected BackEndFacade bef                   = new BackEndFacade();

    //  For sending test messages
    final protected String queue_name             = "replatform.queue.dispatcher";
    final protected String topic_name             = "replatform.topic.notify";
    protected Context context                     = null;
    protected Queue queue                         = null;
    protected QueueConnectionFactory queueFactory = null;
    protected QueueConnection _queueConnection    = null;
    protected QueueSender _queueSender            = null;
    protected QueueSession _queueSession          = null;
    protected Topic topic                         = null;
    protected TopicConnectionFactory topicFactory = null;
	protected TopicConnection _topicConnection    = null;
	protected TopicPublisher _topicPublisher      = null;
	protected TopicSession _topicSession          = null;

    public CommonTestCase ( String param )
    {
        super ( param );

        _log.info ( "Initializing JMS connections" );
        try
        {
            context          = new InitialContext();
        }
        catch ( NamingException exception )
        {
            _log.fatal ( "Could not create initial context", exception );
        }


        try
        {
    		topic            = (Topic) context.lookup(topic_name);
    		topicFactory     = (TopicConnectionFactory) context.lookup(AbstractTopic.TOPIC_FACTORY);
    		_topicConnection = topicFactory.createTopicConnection();
    		_topicSession    = _topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            _topicPublisher  = _topicSession.createPublisher(topic);
        }
        catch ( NamingException exception )
        {
            _log.fatal ( "Could not create connections to JMS topic: " + topic_name, exception );
        }
        catch ( JMSException exception )
        {
            _log.fatal ( "Could not create connections to JMS topic: " + topic_name, exception );
        }

        try
        {
            queue            = (Queue) context.lookup(queue_name);
            queueFactory     = (QueueConnectionFactory) context.lookup(AbstractQueue.QUEUE_FACTORY);
            _queueConnection = queueFactory.createQueueConnection();
            _queueSession    = _queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            _queueSender     = _queueSession.createSender(queue);
        }
        catch ( NamingException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name, exception );
        }
        catch ( JMSException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name, exception );
        }
    }

    public DataMapRule createTestDataMapRule()
    {
        DataMapRule rule = new DataMapRule ( "group" );
        rule.setMethod               ( "ticket" );
        rule.setAlarmpointGroup      ( "Dennis Test Group" );
        rule.setAlarmpointScript     ( "BGI VPO" );
        rule.setPeregrineCategory    ( "peregrin cat" );
        rule.setPeregrineSubCategory ( "peregrin subcat" );
        rule.setPeregrineProduct     ( "peregrin product" );
        rule.setPeregrineProblem     ( "peregrin problem" );
        rule.setPeregrineAssignment  ( "peregrin assignment" );
        rule.setPeregrineLocation    ( "peregrin location" );

        return rule;
    }

    public TicketMessage createTestTicketMessage ()
    {
		Calendar calendar = Calendar.getInstance();
        TicketMessage tm  = new TicketMessage();
		tm.setMessageId     ( "test-message-num-" + System.currentTimeMillis() );
		tm.setSourceNode    ( "test-source-node" );
		tm.setSeverity      ( "test-severity" );
		tm.setSuppressCount ( 0 );

		tm.setTimeStamp     ( calendar);
		tm.setApplication   ( "test-app" );
		tm.setMessageGroup  ( "test-group" );
		tm.setObject        ( "test-object" );
		tm.setMessageText   ( "test-message-text" );

        return tm;
    }

    public TicketMessage createTestOriginalTicketMessage ( long timestamp )
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

    private String createMessageID ( long timestamp )
    {
        StringBuilder buffer = new StringBuilder ( "test-message-num-" );
        buffer.append ( timestamp );

        return buffer.toString();
    }


    public TicketMessage createTestDuplicateTicketMessage ( long timestamp, int num_duplicates )
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



    public static String createOriginalOVOTestMessage ( Calendar creation_time, String ovo_message_id )
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
        buffer.append ( "<value type=\"string\">test-eeb</value>" );
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

    public static String createOriginalOVODuplicateMessage ( Calendar original_time_of_creation, String ovo_message_id, int num_duplicates )
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
        buffer.append ( "<messageUUID>" );
        buffer.append ( ovo_message_id );
        buffer.append ( "</messageUUID>" );
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
        buffer.append ( "<value type=\"string\">test-eeb</value>" );
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

    public static String createOriginalVPOSendMessage ( Calendar creation_time, String ovo_message_id )
    {
        long timestamp      = creation_time.getTime().getTime();
        long timestamp_secs = timestamp / 1000L;
        int timezone_offset = creation_time.getTimeZone().getRawOffset();
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
        buffer.append ( "<seconds>1177129339</seconds>" );
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
        buffer.append ( "<dataList>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>operator</name>" );
        buffer.append ( "<value type=\"string\">opc_adm</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "</dataList>" );
        buffer.append ( "</changeEventData>" );
        buffer.append ( "<ovMessage version=\"1.0\">" );
        buffer.append ( "<messageEnvelope>" );
        buffer.append ( "<messageUUID>" );
        buffer.append ( ovo_message_id );
        buffer.append ( "</messageUUID>" );
        buffer.append ( "<timeStamp>" );
        buffer.append ( "<seconds>1177129283</seconds>" );
        buffer.append ( "<dateTime>2007-04-20T21:21:23-07:00</dateTime>" );
        buffer.append ( "</timeStamp>" );
        buffer.append ( "<severity>Minor</severity>" );
        buffer.append ( "<messageSource>rdcuxsrv217.insidelive.net</messageSource>" );
        buffer.append ( "<eventSource>test_node.insidelive.net</eventSource>" );
        buffer.append ( "<primaryMessageRepository/>" );
        buffer.append ( "<messageMetadataIdentifier/>" );
        buffer.append ( "<managedEntityReference/>" );
        buffer.append ( "</messageEnvelope>" );
        buffer.append ( "<messageBody>" );
        buffer.append ( "<owner>opc_adm</owner>" );
        buffer.append ( "<numberOfDuplicates>0</numberOfDuplicates>" );
        buffer.append ( "<messageKey>" );
        buffer.append ( "</messageKey>" );
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
        buffer.append ( "<value type=\"string\">test-eeb</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>lastReceiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">1177129283</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>object</name>" );
        buffer.append ( "<value type=\"string\">SSM-no-obj-def-CLI</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>originalText</name>" );
        buffer.append ( "<value type=\"string\">Node:          \nMessage group: esm \nApplication:   SSM-no-app-def \nObject:        SSM-no-obj-def-CLI \nSeverity:      Minor \nText:          CLI-test message from rdcuxsrv217 by dennis - 02 \n</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>receiveTimeSeconds</name>" );
        buffer.append ( "<value type=\"long\">1177129283</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>serviceName</name>" );
        buffer.append ( "<value type=\"string\">INFRA@@rdcuxsrv217.insidelive.net</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>source</name>" );
        buffer.append ( "<value type=\"string\">SSM - opcmsg</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>text</name>" );
        buffer.append ( "<value type=\"string\">test message from rdcuxsrv217 by dennis - 02</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data>" );
        buffer.append ( "<name>type</name>" );
        buffer.append ( "<value type=\"string\">" );
        buffer.append ( "</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "<data sequenceNumber=\"1\">" );
        buffer.append ( "<name>annotation</name>" );
        buffer.append ( "<value type=\"string\">Peregrine Ticket #:         IM01292234 \nPeregrine Category:         SERVERS \nPeregrine Subcategory:      ENTERPRISE SYSTEMS MANAGEMENT \nPeregrine Product.Type:     VPO \nPeregrine Problem.Type:     FAULT \nPeregrine Assignment Group: ENTERPRISE SYSTEMS MANAGEMENT \nAlarmPoint Group ID:        ESM</value>" );
        buffer.append ( "</data>" );
        buffer.append ( "</messageData>" );
        buffer.append ( "<actionResult/>" );
        buffer.append ( "<messageMetadataAnnotation/>" );
        buffer.append ( "</messageBody>" );
        buffer.append ( "</ovMessage>" );
        buffer.append ( "</ovMessageChangeEvent>" );

        return buffer.toString();
    }

    public boolean sendDispatcherMessage() throws BackEndFailure
    {
        return false;
    }

    public boolean sendDispatcherMessage ( DispatcherMessage message ) throws BackEndFailure
    {
        return false;
    }
};
