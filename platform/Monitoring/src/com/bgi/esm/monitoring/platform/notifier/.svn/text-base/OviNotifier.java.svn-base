package com.bgi.esm.monitoring.platform.notifier;

import java.io.BufferedWriter;
import java.io.FileWriter;

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

import com.bgi.esm.monitoring.platform.utility.EventMineQueue;
import com.bgi.esm.monitoring.platform.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;
import com.bgi.esm.monitoring.platform.utility.XmlParser;

import com.bgi.esm.monitoring.platform.value.EventMineMessage;
import com.bgi.esm.monitoring.platform.value.OviMessage;
import com.bgi.esm.monitoring.platform.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * OviNotifier is the bridge between OVO manager and JMS.
 * 
 * <p>
 * OviNotifier is a persistent process.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class OviNotifier implements MessageListener {

	/**
	 * ctor, initialize dump file, suppression queue and topic. 
	 */
	public OviNotifier() throws Exception {
		if (StaticConfiguration.getBoolean("notifier.dump.enable") == Boolean.TRUE) {
			String file_name = StaticConfiguration.getString("notifier.dump.file");
			_log.debug("creating dump file:" + file_name);
			_bw = new BufferedWriter(new FileWriter(file_name));
		}
		
		//
		// Event Mine Queue Setup
		//
		String queue_name = StaticConfiguration.getString("queue.event_mine.name");
		_emq = new EventMineQueue(queue_name);

		//
		// Suppression Queue Setup
		//
		queue_name = StaticConfiguration.getString("queue.suppression.name");
		_sq = new SuppressionQueue(queue_name);

		//
		// OVI Topic Setup
		//
		_topic_name = StaticConfiguration.getString("topic.msi.name");

		Context context = new InitialContext();

		Topic topic = (Topic) context.lookup(_topic_name);

		String topic_factory = StaticConfiguration.getString("jms.topic.factory");

		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(topic_factory);

		TopicConnection connection = factory.createTopicConnection();

		TopicSession sub_session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		String sub_name = StaticConfiguration.getString("topic.msi.subscription");
		TopicSubscriber subscriber = sub_session.createDurableSubscriber(topic, sub_name);
		// TopicSubscriber subscriber = sub_session.createSubscriber(topic);

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
			_log.debug(text);
			System.out.println(text);
			
			if (_bw != null) {
				_bw.write(text);	
				_bw.newLine();
			}
			
			XmlParser xp = new XmlParser();
			XmlIf candidate = xp.oviParser(text);
			if (candidate == null) {
				_log.debug("reject:" + text);
			} else {
				EventMineMessage emm = new EventMineMessage();
				emm.setSource(_topic_name);
				emm.setPayload(text);
				_emq.eventMineQueueWriter(emm);
				
				OviMessage om = (OviMessage) candidate;	
				SuppressionMessage sm = new SuppressionMessage();
				sm.setTicketMessage(om.getTicketMessage());
				_sq.queueWriter(sm);
			}
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
	 * Main entry point for the Notifier.
	 * 
	 * @param args OVO message elements, must be 16
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		OviNotifier notifier = new OviNotifier();
		notifier.execute();
	}

	/**
	 * Dump file
	 */	
	private BufferedWriter _bw;
	
	/**
	 * Downstream queue to Suppression
	 */
	private final EventMineQueue _emq;

	/**
	 * Downstream queue to Suppression
	 */
	private final SuppressionQueue _sq;
	
	/**
	 * Topic name, required for EventMine messages
	 */
	private final String _topic_name;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(OviNotifier.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
