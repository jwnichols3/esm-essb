package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving from the OVI topic.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class OviRuleSet extends RuleSetBase {

	/**
	 * Digester parsing rules for messages arriving from the OVI topic.
	 * 
	 * @param digester instance
	 */
	public void addRuleInstances(Digester digester) {
		//
		// OVI Message parsing rules
		//
		digester.addObjectCreate("ovMessage", "com.bgi.esm.monitoring.platform.value.OviMessage");

		digester.addCallMethod("ovMessage/messageEnvelope/messageUUID", "setMessageId", 0);
		digester.addCallMethod("ovMessage/messageEnvelope/eventSource", "setSourceNode", 0);
		digester.addCallMethod("ovMessage/messageEnvelope/severity", "setSeverity", 0);

		digester.addCallMethod("ovMessage/messageBody/numberOfDuplicates", "setSuppressCount", 0);

		digester.addObjectCreate("ovMessage/messageBody/messageData/data", "com.bgi.esm.monitoring.platform.value.KeyValue");
		digester.addSetNext("ovMessage/messageBody/messageData/data", "addKeyValue");

		digester.addCallMethod("ovMessage/messageBody/messageData/data/name", "setKey", 0);
		digester.addCallMethod("ovMessage/messageBody/messageData/data/value", "setValue", 0);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */