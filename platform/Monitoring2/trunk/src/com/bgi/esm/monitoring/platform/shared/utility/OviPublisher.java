package com.bgi.esm.monitoring.platform.shared.utility;

import javax.jms.JMSException;

import javax.naming.NamingException;

/**
 * Write to the specified topic.
 * 
 * @author coleguy
 */
public class OviPublisher extends AbstractTopic {

	/**
	 * ctor, establish connection to topic
	 * 
	 * @param topic topic name
	 * @param reply topic name, can be null
	 * 
	 * @throws JMSException if JMS problem
	 * @throws NamingException if JNDI problem
	 */
	public OviPublisher(String topic, String reply) throws JMSException, NamingException {
		super(topic, reply);
	}
}
