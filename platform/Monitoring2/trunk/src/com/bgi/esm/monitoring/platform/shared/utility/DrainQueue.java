package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.DrainMessage;

/**
 * Queue which feeds the drain buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DrainQueue extends AbstractQueue {
	
	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public DrainQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a DrainMessage to the (already specified) queue.
	 * 
	 * @param arg message bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(DrainMessage arg) {
		return(super.queueWriter(arg));
	}
}
