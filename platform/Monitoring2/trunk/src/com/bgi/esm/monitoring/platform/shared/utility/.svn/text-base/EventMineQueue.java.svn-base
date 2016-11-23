package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.EventMineMessage;

/**
 * Queue which feeds the eventMine buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class EventMineQueue extends AbstractQueue {

	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 */
	public EventMineQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a EventMineMessage to the (already specified) queue.
	 * 
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(EventMineMessage arg) {
		return(super.queueWriter(arg));
	}
}
