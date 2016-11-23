package com.bgi.esm.monitoring.platform.utility;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.AbstractMessage;
import com.bgi.esm.monitoring.platform.value.KillMessage;

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
	 */
	public AbstractQueue(String arg) throws JMSException, NamingException {
		_queue_name = arg;

		String queue_factory = StaticConfiguration.getString("jms.queue.factory");

		Context context = new InitialContext();

		_queue = (Queue) context.lookup(_queue_name);

		QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(queue_factory);

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
			_log.error("queue close failure:" + _queue_name, exception);
			return(false);
		}

		return(true);
	}
	
	/**
	 * Write an object to the (already specified) queue.
	 * 
	 * @param arg serialized object bound for queue
	 * @return true successful write
	 */
	boolean queueWriter(AbstractMessage arg) {
		_log.debug("queue write:" + _queue_name + ":" + arg);

		QueueSession session = null;

		try {
			session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			ObjectMessage message = session.createObjectMessage(arg);

			QueueSender sender = session.createSender(_queue);

			sender.send(message);
		} catch (Exception exception) {
			_log.error("queue writer failure:" + _queue_name, exception);
			return(false); // failure
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (JMSException exception) {
				_log.error("queue write/close failure:" + _queue_name, exception);
			}
		}

		return(true); // success
	}

	/**
	 * Write a XML formatted string to the (already specified) queue.
	 * 
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	boolean queueWriter(String arg) {
		_log.debug("queue write:" + _queue_name + ":" + arg);

		QueueSession session = null;

		try {
			session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			TextMessage message = session.createTextMessage(arg);

			QueueSender sender = session.createSender(_queue);

			sender.send(message);
		} catch (Exception exception) {
			_log.error("queue writer failure:" + _queue_name, exception);
			return(false); // failure
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (JMSException exception) {
				_log.error("queue write/close failure:" + _queue_name, exception);
			}
		}

		return(true); // success
	}

	/**
	 * Write a kill a message
	 * 
	 * @return true successful write
	 */
	public boolean queueWriterKiller() {
		return(queueWriter(new KillMessage()));
	}

	/**
	 * Read a serialized object from the (already specified) queue.
	 * Blocks until message arrives.
	 * 
	 * @return message or null if problem
	 */
	AbstractMessage queueObjectReader() {
		_log.debug("queue read:" + _queue_name);

		QueueSession session = null;

		try {
			_connection.start();

			session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			QueueReceiver receiver = session.createReceiver(_queue);

			Message message = receiver.receive();

			Object results = ((ObjectMessage) message).getObject();

			_connection.stop();

			return((AbstractMessage) results);
		} catch (Exception exception) {
			_log.error("queue read failure:" + _queue_name, exception);
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (JMSException exception) {
				_log.error("queue read/close failure:" + _queue_name, exception);
			}
		}

		return(null); // failure
	}
	
	/**
	 * Read a string from the (already specified) queue. 
	 * Blocks until message arrives.
	 * 
	 * @return message or null if problem
	 */
	String queueStringReader() {
		_log.debug("queue read:" + _queue_name);

		QueueSession session = null;

		try {
			_connection.start();

			session = _connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			QueueReceiver receiver = session.createReceiver(_queue);

			Message message = receiver.receive();

			String results = ((TextMessage) message).getText();

			_connection.stop();

			return(results);
		} catch (Exception exception) {
			_log.error("queue read failure:" + _queue_name, exception);
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (JMSException exception) {
				_log.error("queue read/close failure:" + _queue_name, exception);
			}
		}

		return(null); // failure
	}

	/**
	 * Ensure JMS is properly shut down on my way to oblivion.
	 */
	public void finalize() {
		_log.info("finalize runs");

		shutDown();
	}

	/**
	 * Queue Name
	 */
	private final String _queue_name;

	/**
	 * Queue
	 */
	private final Queue _queue;

	/**
	 * Connection to JMS
	 */
	private final QueueConnection _connection;

	/**
	 * Define logger
	 */
	private static final Log _log = LogFactory.getLog(AbstractQueue.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */