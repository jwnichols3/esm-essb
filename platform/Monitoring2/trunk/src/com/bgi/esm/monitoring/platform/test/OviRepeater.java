package com.bgi.esm.monitoring.platform.test;

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

import com.bgi.esm.monitoring.platform.shared.utility.AbstractTopic;

/**
 * Simulate OVI. Read captured OVI events from a file and write them to a topic.
 * 
 * @author Dennis Lin (linden)
 */
public class OviRepeater {

	/**
	 * 
	 */
	public OviRepeater(String arg) throws Exception {
		String topic_name = arg;
		
		Context context = new InitialContext();

        System.out.println ( "Topic name: " + topic_name );
		
		Topic topic = (Topic) context.lookup(topic_name);
		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(AbstractTopic.TOPIC_FACTORY);
		
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
                for ( int counter = 0; counter < 10; counter++ )
                {
				    writeResult(result);
                }
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
		System.out.println("=======================================");
		System.out.println(arg);
		System.out.println("=======================================");
		
		TextMessage message = _session.createTextMessage();
		
		message.setText(arg);
		
		_publisher.publish(message);
	}

	/**
	 * Simulate traffic from OVI
	 * <UL>
	 * <LI>args[0] = topic name
	 * <LI>args[1] = data file name
	 * </UL>
	 * 
	 * @param args command line arguments, see above
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		OviRepeater os = new OviRepeater(args[0]);
		os.execute(args[1]);
	}

	/**
	 * 
	 */
	TopicConnection _connection;
	TopicPublisher _publisher;
	TopicSession _session;
}
