package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.shared.value.AbstractMessage;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * Write to a buss module using JMS. Works for queues or topics (only one per
 * instance). This is not a great labor saving class, but it consolidates queue
 * names, etc.
 * 
 * @author coleguy
 */
public class JmsFacade {

	/**
	 * 
	 * @param arg destination buss module
	 * @throws JMSException if JMS problem
	 * @throws NamingException if naming problem
	 */
	public JmsFacade(BussModule arg) throws JMSException, NamingException {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
            _log = Logger.getLogger ( JmsFacade.class );
		}

        if ( null == _log ) {
			System.err.println("JmsFacade ctor failure");
        }
		
		if (arg.equals(BussModule.AP_DRAIN)) {
			_queue = new DrainQueue("replatform.queue.ap_drain");
		} else if (arg.equals(BussModule.DATA_MAP)) {
			_queue = new DataMapQueue("replatform.queue.data_map");
		} else if (arg.equals(BussModule.DISPATCHER)) {
			_queue = new DispatcherQueue("replatform.queue.dispatcher");
		} else if (arg.equals(BussModule.EVENT_MINE)) {
			_queue = new EventMineQueue("replatform.queue.event_mine");
		} else if (arg.equals(BussModule.MODIFIER)) {
			_topic = new OviPublisher(MODIFY_TOPIC, NOTIFY_TOPIC);
		} else if (arg.equals(BussModule.NOTIFIER)) {
			_topic = new OviPublisher(NOTIFY_TOPIC, null);
		} else if (arg.equals(BussModule.SC_DRAIN)) {
			_queue = new DrainQueue("replatform.queue.sc_drain");
		} else if (arg.equals(BussModule.OPENVIEW_ACKNOWLEDGE)) {
			_queue = new DrainQueue("replatform.queue.openview_acknowledge");
		} else if (arg.equals(BussModule.SUPPRESSION_EMAIL)) {
			_queue = new DrainQueue("replatform.queue.suppression_email");
		} else if (arg.equals(BussModule.SC_BUFFER)) {
			_queue = new DrainQueue("replatform.queue.sc_buffer");
		} else if (arg.equals(BussModule.STORM)) {
			_queue = new StormQueue("replatform.queue.storm");
		} else if (arg.equals(BussModule.SUPPRESSION)) {
			_queue = new SuppressionQueue("replatform.queue.suppression");
		} else if (arg.equals(BussModule.SUPPRESSION_DRAIN)) {
			_queue = new SuppressionQueue("replatform.queue.suppress_drain");
		} else if (arg.equals(BussModule.THROTTLE)) {
			_queue = new ThrottleQueue("replatform.queue.throttle");
		} else {
			String error = "unknown buss module type:" + arg;
			_log.error(error);
			throw new IllegalArgumentException(error);
		}
	}

    public String getQueueName()
    {
        return _queue.getQueueName();
    }

	/**
	 * Publish to the topic.
	 * 
	 * @param arg xml to publish
	 * @return true successful write
	 */
	public boolean topicWriter(String arg) {
		if (_topic != null) {
			return(_topic.topicWriter(arg));
		}

		_log.error("bad topic write:" + _topic.topic_name);
		return(false);
	}

	/**
	 * Write an object to the queue.
	 * 
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(AbstractMessage arg) {
		if (_queue != null) {
			return(_queue.queueWriter(arg));
		}

		_log.error("bad queue write:" + _queue.queue_name);
		return(false);
	}

	/**
	 * Perform orderly shutdown
	 * 
	 * @return true success
	 */
	public boolean shutDown() {
		if (_queue != null) {
			return(_queue.shutDown());
		}

		if (_topic != null) {
			return(_topic.shutDown());
		}

		return(false);
	}

	/**
	 * Associated queue (if required)
	 */
	private AbstractQueue _queue;

	/**
	 * Associated topic (if required)
	 */
	private AbstractTopic _topic;
	
	/**
	 * Define logger
	 */
	private Logger _log;
	
	/**
	 * 
	 */
	public static final String NOTIFY_TOPIC = "replatform.topic.notify";
	public static final String MODIFY_TOPIC = "replatform.topic.modify";
	public static final String READ_TOPIC = "replatform.topic.read";
	public static final String TEST_TOPIC = "weblogic.examples.jms.TopicConnectionFactory";
}
