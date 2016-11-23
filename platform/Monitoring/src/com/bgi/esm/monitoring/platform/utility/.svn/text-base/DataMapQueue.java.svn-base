package com.bgi.esm.monitoring.platform.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.AbstractMessage;
import com.bgi.esm.monitoring.platform.value.DataMapMessage;

/**
 * Queue which feeds the data map buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMapQueue extends AbstractQueue {

	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 */
	public DataMapQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a DataMapMessage to the (already specified) queue.
	 * 
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(DataMapMessage arg) {
		return(queueWriter(arg));
	}

	/**
	 * Read a DataMapMessage from the (already specified) queue. 
	 * Blocks until message arrives.
	 * 
	 * @return message or null if problem
	 */
	public AbstractMessage dataMapQueueReader() {
		AbstractMessage message = queueObjectReader();
		_log.debug(message);

		return(message);
	}

	/**
	 * Define logger
	 */
	private static final Log _log = LogFactory.getLog(DataMapQueue.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */