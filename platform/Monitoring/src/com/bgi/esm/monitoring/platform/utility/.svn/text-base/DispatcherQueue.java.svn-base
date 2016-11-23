package com.bgi.esm.monitoring.platform.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.AbstractMessage;
import com.bgi.esm.monitoring.platform.value.DispatcherMessage;

/**
 * Queue which feeds the dispatcher buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DispatcherQueue extends AbstractQueue {

	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 */
	public DispatcherQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a DispatcherMessage to the (already specified) queue.
	 * 
	 * @param arg serialized object bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(DispatcherMessage arg) {
		return(queueWriter(arg));
	}

	/**
	 * Read a DispatcherMessage from the (already specified) queue. 
	 * Blocks until message arrives.
	 * 
	 * @return message or null if problem
	 */
	public AbstractMessage dispatcherQueueReader() {
		AbstractMessage message = queueObjectReader();
		_log.debug(message);

		return(message);
	}

	/**
	 * Define logger
	 */
	private static final Log _log = LogFactory.getLog(DispatcherQueue.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
