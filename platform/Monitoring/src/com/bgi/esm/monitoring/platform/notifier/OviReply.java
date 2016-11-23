package com.bgi.esm.monitoring.platform.notifier;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

public class OviReply implements MessageListener {
	
	public OviReply() throws Exception {
		//
		// OVI Topic Setup
		//
		String topic_name = StaticConfiguration.getString("topic.reply");

		Context context = new InitialContext();

		Topic topic = (Topic) context.lookup(topic_name);

		String topic_factory = StaticConfiguration.getString("jms.topic.factory");

		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(topic_factory);

		TopicConnection connection = factory.createTopicConnection();

		TopicSession sub_session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		//String sub_name = StaticConfiguration.getString("topic.msi.subscription");
		//TopicSubscriber subscriber = sub_session.createDurableSubscriber(topic, sub_name);
		TopicSubscriber subscriber = sub_session.createSubscriber(topic);

		subscriber.setMessageListener(this);

		connection.start();
	}

	/**
	 * Service a fresh incoming message.  All traffic written to dump file if enabled.
	 * Only "ovMessage" from OVI will be processed for Suppression.
	 * 
	 * @param arg raw JMS message
	 */
	public void onMessage(Message arg) {
		try {
			TextMessage message = (TextMessage) arg;
			String text = message.getText();
			System.out.println(text);
		} catch (Exception exception) {
			_log.error("choke", exception);
		}
	}

	/**
	 * No work here, everything done in onMessage()
	 * 
	 * @throws Exception for anything
	 */
	public void execute() throws Exception {
		// empty
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		OviReply ovi = new OviReply();
		ovi.execute();
	}
	
	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(OviReply.class);
}
