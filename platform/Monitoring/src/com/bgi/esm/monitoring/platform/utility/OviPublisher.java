package com.bgi.esm.monitoring.platform.utility;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Write to the specified topic.
 * 
 * @author coleguy
 */
public class OviPublisher {
	
	/**
	 * ctor, establish connection to topic
	 * 
	 * @param arg topic name
	 * 
	 * @throws Exception if any problem w/initialization
	 */
	public OviPublisher(String arg) throws Exception {
		Context context = new InitialContext();

		Topic topic = (Topic) context.lookup(arg);
		
		_reply = (Topic) context.lookup(StaticConfiguration.getString("topic.reply"));

		String topic_factory = StaticConfiguration.getString("jms.topic.factory");

		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(topic_factory);

		_connection = factory.createTopicConnection();

		_session = _connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		_publisher = _session.createPublisher(topic);
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
    	_log.info("finalize runs");

    	shutDown();
    }

    /**
     * Publish to the topic.
     *
     * @param arg xml to publish
     * @return true successful write
     */
    public boolean topicWriter(String arg) {
    	_log.debug("topic write:" + arg);

    	try {
    		TextMessage message = _session.createTextMessage();
    		message.setJMSReplyTo(_reply);
    		message.setText(arg);
    		_publisher.publish(message);
    	} catch(Exception exception) {
    		_log.error("topic choke", exception);
    		return(false);
    	}
    	
    	return(true);
    }
	
	/**
	 * 
	 */
	private final TopicConnection _connection;

	/**
	 * 
	 */
	private final TopicPublisher _publisher;

	/**
	 * 
	 */
	private final TopicSession _session;
	
	/**
	 * 
	 */
	private final Topic _reply;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(OviPublisher.class);
}
