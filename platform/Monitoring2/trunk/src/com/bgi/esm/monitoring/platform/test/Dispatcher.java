package com.bgi.esm.monitoring.platform.test;

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

public class Dispatcher
{
    public Dispatcher ( String queue_name ) throws Exception
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

        DispatcherMessage dm = new DispatcherMessage();
        dm.setTicketMessage ( createTestTicketMessage() );
        dm.setDataMapRule   ( createTestDataMapRule()   );

        ObjectMessage om = _session.createObjectMessage ( dm );

        _sender.send ( om );
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

    private static TicketMessage createTestTicketMessage()
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

    public static void main ( String args[] ) throws Exception
    {
        System.out.println("begin");

        Dispatcher d = new Dispatcher( args[0] );
        d.runClient();

        System.out.println("end");
    }

    /**
     * 
     */
    QueueConnection _connection;
    QueueSender _sender;
    QueueSession _session;
    String _queueName;
};
