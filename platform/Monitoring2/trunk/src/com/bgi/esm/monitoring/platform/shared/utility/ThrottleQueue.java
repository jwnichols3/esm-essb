package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.ThrottleMessage;

/**
 * Queue which feeds the throttle buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class ThrottleQueue extends AbstractQueue {

	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 */
	public ThrottleQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a ThrottleMessage to the (already specified) queue.
	 * 
	 * @param arg xml bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(ThrottleMessage arg) {
		return(super.queueWriter(arg));
	}
}
