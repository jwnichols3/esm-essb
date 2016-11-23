package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.StormCalculationMessage;

/**
 * Queue which feeds the storm buss module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class StormQueue extends AbstractQueue {
	
	/**
	 * ctor, load configuration, perform JNDI lookup and create a connection
	 * 
	 * @param arg queue name
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public StormQueue(String arg) throws JMSException, NamingException {
		super(arg);
	}

	/**
	 * Write a StormMessage to the (already specified) queue.
	 * 
	 * @param arg message bound for queue
	 * @return true successful write
	 */
	public boolean queueWriter(StormCalculationMessage arg) {
		return(super.queueWriter(arg));
	}
}
