package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.ObjectMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;
import weblogic.logging.LoggerNotAvailableException;

import com.bgi.esm.monitoring.platform.shared.value.AbstractMessage;

/**
 * Generic queue support.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public abstract class AbstractQueue {

	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public AbstractQueue(String arg) throws JMSException, NamingException {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch (LoggerNotAvailableException exception) {
			_log = Logger.getLogger ( AbstractQueue.class );
		} catch(Exception exception) {
			System.err.println("AbstractQueue ctor/log failure");
		}
		
		queue_name = arg;

		Context context = new InitialContext();

		_queue = (Queue) context.lookup(queue_name);

		QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(QUEUE_FACTORY);

		_connection = factory.createQueueConnection();
	}

	/**
	 * Perform orderly queue shutdown
	 * 
	 * @return true success
	 */
	public boolean shutDown() {
		try {
			_connection.close();
		} catch (Exception exception) {
			_log.error("queue close failure:" + queue_name, exception);
			return(false);
		}

		return(true);
	}

	/**
	 * Write an object to the (already specified) queue.
	 * 
	 * @param arg object bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(AbstractMessage arg) {
		_log.debug("queue write:" + queue_name + ":" + arg);

		QueueSession session = null;

		try {
			session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			ObjectMessage message = session.createObjectMessage();
			message.setObject(arg);

			QueueSender sender = session.createSender(_queue);
			sender.send(message);
		} catch(Exception exception) {
			_log.error("queue writer failure:" + queue_name, exception);
			return(false); // failure
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch(JMSException exception) {
				_log.error("queue write/close failure:" + queue_name, exception);
			}
		}

		return(true); // success
	}

	/**
	 * Ensure JMS is properly shut down on my way to oblivion.
	 */
	public void finalize() {
		_log.debug("finalize runs");

		shutDown();
	}

    public String getQueueName()
    {
        return queue_name;
    }

	/**
	 * Queue Name
	 */
	protected final String queue_name;

	/**
	 * Queue
	 */
	private final Queue _queue;

	/**
	 * Connection to JMS
	 */
	private final QueueConnection _connection;
	
	/**
	 * WebLogic JMS connection factory
	 */
	//public final String QUEUE_FACTORY = "weblogic.jms.QueueConnectionFactory";
	public static final String QUEUE_FACTORY = "weblogic.examples.jms.QueueConnectionFactory";

	/**
	 * Define logger
	 */
	private Logger _log;
}
