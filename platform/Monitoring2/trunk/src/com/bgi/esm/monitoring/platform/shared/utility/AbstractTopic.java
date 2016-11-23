package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

/**
 * Generic topic support.
 * 
 * @author coleguy
 */
public abstract class AbstractTopic {

	/**
	 * ctor, establish connection to topic
	 * 
	 * @param topic topic name
	 * @param reply topic name, may be null
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public AbstractTopic(String topic, String reply) throws JMSException, NamingException {	
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
            _log = Logger.getLogger ( AbstractTopic.class );
		}
	
        if ( null == _log ) {    
			System.err.println("AbstractTopic ctor/log failure");
        }

		topic_name = topic;
		reply_name = reply;

		Context context = new InitialContext();

		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(TOPIC_FACTORY);

		_connection = factory.createTopicConnection();
		
		_topic = (Topic) context.lookup(topic_name);

		if (reply == null) {
			_reply = null;
		} else {
			_reply = (Topic) context.lookup(reply);
		}
	}

	/**
	 * Perform orderly shutdown
	 * 
	 * @return true success
	 */
	public boolean shutDown() {
		try {
			_connection.close();
		} catch(Exception exception) {
			_log.error("close failure", exception);
			return(false);
		}

		return(true);
	}

	/**
	 * Ensure JMS is properly shut down on my way to oblivion.
	 */
	public void finalize() {
		_log.debug("finalize runs");

		shutDown();
	}

	/**
	 * Publish to the topic.
	 * 
	 * @param arg message to publish
	 * @return true successful write
	 */
	public boolean topicWriter(String arg) {
		_log.debug("topic write:" + topic_name + ":" + arg);
		
		TopicSession session = null;

		try {
			session = _connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			TopicPublisher publisher = session.createPublisher(_topic);

			TextMessage message = session.createTextMessage();

			if (_reply == null) {
				_log.debug("Null Reply");
			} else {
				message.setJMSReplyTo(_reply);
			}

			message.setText(arg);

			publisher.publish(message);
		} catch(Exception exception) {
			_log.error("topic writer failure" + topic_name, exception);
			return(false);
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch(JMSException exception) {
				_log.error("topic write/close failure", exception);
			}
		}

		return(true);
	}

	/**
	 * Connection (from factory).  Shared by topic and reply.
	 */
	private final TopicConnection _connection;

	/**
	 * Topic name
	 */
	protected final String topic_name;

	/**
	 * Reply topic name, may be null
	 */
	protected final String reply_name;

	/**
	 * Topic handle
	 */
	private final Topic _topic;
	
	/**
	 * Reply topic handle, may be null
	 */
	private final Topic _reply;

	/**
	 * WebLogic JMS connection factory
	 */
	public static final String TOPIC_FACTORY = "weblogic.jms.ConnectionFactory";

	/**
	 * Define logger
	 */
	private Logger _log;
}
