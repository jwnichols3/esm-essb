package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

import com.bgi.esm.monitoring.platform.shared.value.DataMapMessage;

/**
 * Queue which feeds the dataMap buss module.
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
		return(super.queueWriter(arg));
	}
}
