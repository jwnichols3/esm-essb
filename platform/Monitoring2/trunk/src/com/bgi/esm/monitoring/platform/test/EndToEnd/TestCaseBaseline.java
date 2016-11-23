package com.bgi.esm.monitoring.platform.test.EndToEnd;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractQueue;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractTopic;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 *  Collection of tools for an end-to-end test.
 *
 *  @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestCaseBaseline extends TestCase
{
    final private static Logger _log                      = Logger.getLogger ( TestCaseBaseline.class );
    protected HashMap <String, String> default_properties = null;
    protected BackEndFacade bef                           = new BackEndFacade();

    //  Common text formattersa
    final private static DecimalFormat df2                = new DecimalFormat ( "00" );
    final public static SimpleDateFormat sdf              = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    //  For simulating messages received from Openview Interconnect
    final protected String topic_name                     = "replatform.topic.notify";
    protected Context context                             = null;
    protected Topic topic                                 = null;
    protected TopicConnectionFactory topicFactory         = null;
    protected TopicConnection _topicConnection            = null;
    protected TopicPublisher _topicPublisher              = null;
    protected TopicSession _topicSession                  = null;

    final protected String queue_name_APDrain             = "replatform.queue.ap_drain";
    protected Queue queueAPDrain                          = null;
    protected QueueConnectionFactory queueAPDrainFactory  = null;
    protected QueueConnection _queueAPDrainConnection     = null;
    protected QueueSender _queueAPDrainSender             = null;
    protected QueueSession _queueAPDrainSession           = null;

    final protected String queue_name_SCDrain             = "replatform.queue.sc_drain";
    protected Queue queueSCDrain                          = null;
    protected QueueConnectionFactory queueSCDrainFactory  = null;
    protected QueueConnection _queueSCDrainConnection     = null;
    protected QueueSender _queueSCDrainSender             = null;
    protected QueueSession _queueSCDrainSession           = null;

    public TestCaseBaseline ( String param )
    {
        super ( param );

        //  Assigning base properties
        default_properties = new HashMap <String, String> ();
        default_properties.put ( "Alarmpoint.JavaClient.CommandLine.client",           "openview"                          );
        default_properties.put ( "Alarmpoint.JavaClient.CommandLine.full_path",        "C:/APAgent/APClient.bin.exe"       );
        default_properties.put ( "Alarmpoint.JavaClient.CommandLine.options",          ""                                  );
        default_properties.put ( "Alarmpoint.JavaClient.hostname",                     "localhost"                         );
        default_properties.put ( "Alarmpoint.JavaClient.http.baseref",                 "/agent/http"                       );
        default_properties.put ( "Alarmpoint.JavaClient.http.form.action",             "POST"                              );
        default_properties.put ( "Alarmpoint.JavaClient.http.form.enctype",            "application/x-www-form-urlencoded" );
        default_properties.put ( "Alarmpoint.JavaClient.http.form.message_field_name", "message"                           );
        default_properties.put ( "Alarmpoint.JavaClient.http.form.submit_target",      "/agent"                            );
        default_properties.put ( "Alarmpoint.JavaClient.port",                         "2010"                              );

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
            queueAPDrain            = (Queue) context.lookup(queue_name_APDrain);
            queueAPDrainFactory     = (QueueConnectionFactory) context.lookup(AbstractQueue.QUEUE_FACTORY);
            _queueAPDrainConnection = queueAPDrainFactory.createQueueConnection();
            _queueAPDrainSession    = _queueAPDrainConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            _queueAPDrainSender     = _queueAPDrainSession.createSender(queueAPDrain);
        }
        catch ( NamingException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name_APDrain, exception );
        }
        catch ( JMSException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name_APDrain, exception );
        }

        try
        {
            queueSCDrain            = (Queue) context.lookup(queue_name_SCDrain);
            queueSCDrainFactory     = (QueueConnectionFactory) context.lookup(AbstractQueue.QUEUE_FACTORY);
            _queueSCDrainConnection = queueSCDrainFactory.createQueueConnection();
            _queueSCDrainSession    = _queueSCDrainConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            _queueSCDrainSender     = _queueSCDrainSession.createSender(queueSCDrain);
        }
        catch ( NamingException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name_SCDrain, exception );
        }
        catch ( JMSException exception )
        {
            _log.fatal ( "Could not create connections to JMS queue: " + queue_name_SCDrain, exception );
        }

    }

    public DataMapRule createDataMapRule()
    {
        return com.bgi.esm.monitoring.platform.test.datamap.CommonTestCase.createTestDataMapRule();
    }

    public String createOriginalOVOTestMessage ( Calendar start_time, String ovo_message_id )
    {
        return com.bgi.esm.monitoring.platform.test.dispatcher.CommonTestCase.createOriginalOVOTestMessage  ( start_time, ovo_message_id );
    }

    public String createOriginalOVODuplicateMessage ( Calendar creation_time, String ovo_message_id, int num_duplicates )
    {
        return com.bgi.esm.monitoring.platform.test.dispatcher.CommonTestCase.createOriginalOVODuplicateMessage ( creation_time, ovo_message_id, num_duplicates );
    }

    public String createOriginalVPOSendMessage ( Calendar creation_time, String ovo_message_id )
    {
        return com.bgi.esm.monitoring.platform.test.dispatcher.CommonTestCase.createOriginalVPOSendMessage ( creation_time, ovo_message_id );
    }

    public boolean startAPDrain()
    {
        try
        {
    		ObjectMessage message = _queueAPDrainSession.createObjectMessage();
    		message.setObject ( Calendar.getInstance() );

            _queueAPDrainSender.send ( message );

            return true;
        }
        catch ( JMSException exception )
        {
            return false;
        }
    }

    public boolean startSCDrain()
    {
        try
        {
    		ObjectMessage message = _queueSCDrainSession.createObjectMessage();
    		message.setObject ( Calendar.getInstance() );

            _queueSCDrainSender.send ( message );

            return true;
        }
        catch ( JMSException exception )
        {
            return false;
        }
    }

    public void testDefaultProperties() throws BackEndFailure
    {
        _log.info ( "**************************************** testDefaultProperties()" );

        Iterator <String> keys    = default_properties.keySet().iterator();
        Vector <String> bad_keys  = new Vector <String> (); 
        Vector <String> bad_vals  = new Vector <String> (); 
        Vector <String> good_vals = new Vector <String> ();

        while ( keys.hasNext() )
        {
            String key       = keys.next();
            String expected  = default_properties.get ( key );
            String retrieved = null;

            _log.info ( "Testing key: " + key );
           
            try
            { 
                retrieved = bef.selectEebProperty ( key ).getPropertyValue();
            }
            catch ( NullPointerException exception )
            {
                retrieved = "null value";
            }

            if ( !expected.equals ( retrieved ) )
            {
                bad_keys.add  ( key       );
                bad_vals.add  ( retrieved );
                good_vals.add ( expected  );
            }
        }

        if ( bad_keys.size() != 0 )
        {
            StringBuilder message = new StringBuilder();

            for ( int counter = 0; counter < bad_keys.size(); counter++ )
            {
                message.append ( "\t( Key, Expected, Retrieved ) = ( " );
                message.append ( bad_keys.elementAt  ( counter ) );
                message.append ( ", " );
                message.append ( good_vals.elementAt ( counter ) );
                message.append ( ", " );
                message.append ( bad_vals.elementAt  ( counter ) );
                message.append ( " )\n" );
            }

            _log.error ( message.toString() );

            assertTrue ( "Bad values detected.  See logs for more details.", bad_keys.size() == 0 );
        }
    }

    protected void dumpAuditList ( List audit_list )
    {
        dumpAuditList ( "", audit_list );
    }

    protected void dumpAuditList ( String message, List audit_list )
    {
        if ( _log.isInfoEnabled() )
        {
            _log.info ( "***** Dumping audit list: " + message + ", num_items=" + audit_list.size() );
            Iterator audit_list_i = audit_list.iterator();
            while ( audit_list_i.hasNext() )
            {
                _log.info ( "\t" + audit_list_i.next().toString() );
            }
        }
    }

    public void testAPDrain()
    {
        _log.info ( "**************************************** testAPDrain()" );

        assertTrue ( "Could not stimulate APDrain buss module", startAPDrain() );
    }

    public void testSCDrain()
    {
        _log.info ( "**************************************** testSCDrain()" );

        assertTrue ( "Could not stimulate SCDrain buss module", startSCDrain() );
    }

    public void testDataMapRuleForTestingExists() throws BackEndFailure
    {
        _log.info ( "**************************************** testDataMapRuleForTestingExists()" );

        DataMapRule rule = bef.selectDataMapRule ( "test-eeb" );

        if ( null == rule )
        {
            _log.warn ( "Could not find data map rule 'test-eeb' for testing... adding" );

            rule = createDataMapRule();
            
            assertNotNull ( bef.addOrUpdateDataMapRule ( rule ) );

            _log.warn ( "Add successful!" );
        }
    }

    public void testSimpleEndToEnd() throws BackEndFailure, JMSException, InterruptedException
    {
        _log.info ( "**************************************** testSimpleEndToEnd()" );

        //  Create and send a sample test message
        Calendar start_time   = Calendar.getInstance();
        long timestamp        = start_time.getTime().getTime();
        String ovo_message_id = "test-message-" + timestamp;
        String message_string = createOriginalOVOTestMessage ( start_time, ovo_message_id );

        TextMessage message   = _topicSession.createTextMessage();
        message.setText ( message_string );
        _topicPublisher.publish ( message );

        //  Wait a little bit to give the EEB time for processing
        Thread.currentThread().sleep ( 30000 );

        //  Stimulate the AP drain to retrieve the Alarmpoint Event and wait for processing
        startAPDrain();
        Thread.currentThread().sleep ( 10000 );

        //  Retrieve all the audit entries for this message
        List audit_list       = bef.getAllAuditForMessageId ( ovo_message_id );
        assertNotNull ( "Could not finish end-to-end", audit_list );

        dumpAuditList ( audit_list );
    }

    public void testSimpleDuplicateTicketUpdate() throws BackEndFailure, JMSException, InterruptedException
    {
        _log.info ( "**************************************** testSimpleDuplicateTicketUpdate()" );

        //  Create and send a sample test message
        Calendar start_time    = Calendar.getInstance();
        long timestamp         = start_time.getTime().getTime();
        String ovo_message_id  = "test-message-" + timestamp;
        String message_string1 = createOriginalOVOTestMessage      ( start_time, ovo_message_id );
        String message_string2 = createOriginalOVODuplicateMessage ( start_time, ovo_message_id, 5 );

        TextMessage message1   = _topicSession.createTextMessage();
        message1.setText ( message_string1 );
        _topicPublisher.publish ( message1 );

        //  Wait a little bit of time before sending duplicate message
        Thread.currentThread().sleep ( 5000 );
        TextMessage message2   = _topicSession.createTextMessage();
        message2.setText ( message_string2 );
        _topicPublisher.publish ( message2 );

        //  Wait a little bit to give the EEB time for processing
        Thread.currentThread().sleep ( 30000 );

        //  Stimulate the AP drain to retrieve the Alarmpoint Event and wait for processing
        startAPDrain();
        Thread.currentThread().sleep ( 10000 );

        //  Retrieve all the audit entries for this message
        List audit_list       = bef.getAllAuditForMessageId ( ovo_message_id );
        assertNotNull ( "Could not finish end-to-end", audit_list );

        dumpAuditList ( audit_list );
    }
};
