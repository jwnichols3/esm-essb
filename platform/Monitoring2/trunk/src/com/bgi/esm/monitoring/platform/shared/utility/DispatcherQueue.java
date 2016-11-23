package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;

/**
 * Queue which feeds the dispatcher buss module.
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
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(DispatcherMessage arg) {
		return(super.queueWriter(arg));
	}
}
