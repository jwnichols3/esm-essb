package com.bgi.esm.monitoring.platform.notifier;

import java.io.FileReader;
import java.io.BufferedReader;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicPublisher;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

/**
 * Simulate OVI. Read captured OVI events from a file and write them to a topic.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class OviSimulator {

	/**
	 * 
	 */
	public OviSimulator() throws Exception {
		String topic_name = StaticConfiguration.getString("topic.msi.name");
		if (topic_name == null) {
			_log.error("unable to read properties file");
			return;
		}

		Context context = new InitialContext();

		Topic topic = (Topic) context.lookup(topic_name);

		String topic_factory = StaticConfiguration.getString("jms.topic.factory");

		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(topic_factory);

		_connection = factory.createTopicConnection();

		_session = _connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		_publisher = _session.createPublisher(topic);
	}

	/**
	 * 
	 * @throws Exception for anything
	 */
	public void execute(String arg) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(arg));

		String result = "";
		String inbuffer;
		while ((inbuffer = br.readLine()) != null) {
			result += inbuffer;
			if (inbuffer.indexOf("/ovMessage") >= 0) {
				writeResult(result);
				result = "";
			}
		}

		br.close();

		_connection.close();
	}

	/**
	 * 
	 */
	private void writeResult(String arg) throws Exception {
		// System.out.println("=======================================");
		// System.out.println(arg);
		// System.out.println("=======================================");

		_log.debug("write");
		
		TextMessage message = _session.createTextMessage();

		message.setText(arg);

		_publisher.publish(message);
	}

	/**
	 * Main entry point for the Notifier.
	 * 
	 * @param args OVO message elements, must be 16
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		OviSimulator os = new OviSimulator();
		os.execute(args[0]);
	}

	/**
	 * 
	 */
	TopicConnection _connection;

	TopicPublisher _publisher;

	TopicSession _session;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(OviSimulator.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
