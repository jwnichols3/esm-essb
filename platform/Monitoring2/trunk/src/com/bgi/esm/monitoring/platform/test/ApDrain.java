package com.bgi.esm.monitoring.platform.test;

import java.util.Calendar;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Stimulate Alarm Point Drain
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ApDrain {

	/**
	 * Simulate a timed event
	 * <UL>
	 * <LI>args[0] = queue name
	 * </UL>
	 * 
	 * @param args command line arguments, see above
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		String queue_name = args[0];

		Context context = new InitialContext();

		Queue queue = (Queue) context.lookup(queue_name);

		QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(QUEUE_FACTORY);

		QueueConnection connection = factory.createQueueConnection();

		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		ObjectMessage message = session.createObjectMessage();
		message.setObject(Calendar.getInstance());
		QueueSender sender = session.createSender(queue);
		sender.send(message);
		session.close();
		connection.close();
	}

	public static final String QUEUE_FACTORY = "weblogic.examples.jms.QueueConnectionFactory";
}
