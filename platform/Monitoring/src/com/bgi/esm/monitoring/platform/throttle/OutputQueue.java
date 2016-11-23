package com.bgi.esm.monitoring.platform.throttle;

import javax.jms.JMSException;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.DispatcherQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

import com.bgi.esm.monitoring.platform.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.value.ThrottleMessage;

/**
 * Synchronized output queue to Dispatcher
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class OutputQueue {

	/**
	 * 
	 */
	public OutputQueue() throws JMSException, NamingException {
		String name = StaticConfiguration.getString("queue.dispatcher.name");

		_dq = new DispatcherQueue(name);
	}

	/**
	 * 
	 */
	public synchronized boolean queueWriterKiller() {
		_log.debug("killer");
		
		return (_dq.queueWriterKiller());
	}

	/**
	 * 
	 */
	public synchronized boolean queueWriter(ThrottleMessage arg) {
		_log.debug("throttle message");
		
		DispatcherMessage dm = new DispatcherMessage();
		dm.setTicketMessage(arg.getTicketMessage());
		dm.setDataMapRule(arg.getDataMapRule());

		return (_dq.queueWriter(dm));
	}

	/**
	 * 
	 */
	public void shutDown() {
		_dq.shutDown();
	}

	/**
	 * 
	 */
	private final DispatcherQueue _dq;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(OutputQueue.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */