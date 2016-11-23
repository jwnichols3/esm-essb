package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;

/**
 * Queue which feeds the suppression buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class SuppressionQueue extends AbstractQueue {
	
	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public SuppressionQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a SuppressionMessage to the (already specified) queue.
	 * 
	 * @param arg message bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(SuppressionMessage arg) {
		return(super.queueWriter(arg));
	}
}
