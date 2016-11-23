package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving on the ThrottleQueue
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class ThrottleRuleSet extends RuleSetBase {

	/**
	 * Digester parsing rules.
	 * 
	 * @param digester instance
	 */
	public void addRuleInstances(Digester digester) {
		//
		// KillMessage parsing rules
		//
		digester.addObjectCreate("EmsContainer/KillMessage", "com.bgi.esm.monitoring.platform.value.KillMessage");

		//
		// TicketMessage parsing rules
		//
		digester.addObjectCreate("EmsContainer/ThrottleMessage", "com.bgi.esm.monitoring.platform.value.ThrottleMessage");

		digester.addObjectCreate("EmsContainer/ThrottleMessage/ticket", "com.bgi.esm.monitoring.platform.value.TicketMessage");
		digester.addSetNext("EmsContainer/ThrottleMessage/ticket", "setTicketMessage");

		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/message_id", "setMessageId", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/source_node", "setSourceNode", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/source_node_type", "setSourceNodeType", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/creation_date", "setCreationDate", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/creation_time", "setCreationTime", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/receive_date", "setReceiveDate", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/receive_time", "setReceiveTime", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/application", "setApplication", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/message_group", "setMessageGroup", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/object", "setObject", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/severity", "setSeverity", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/operators/operator", "setOperator", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/message_text", "setMessageText", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/instruction_text", "setInstructionText", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/suppress_count", "setSuppressCount", 0);

		digester.addObjectCreate("EmsContainer/ThrottleMessage/ticket/message_attributes/attribute", "com.bgi.esm.monitoring.platform.value.KeyValue");
		digester.addSetNext("EmsContainer/ThrottleMessage/ticket/message_attributes/attribute", "addKeyValue");

		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/message_attributes/attribute/key", "setKey", 0);
		digester.addCallMethod("EmsContainer/ThrottleMessage/ticket/message_attributes/attribute/value", "setValue", 0);

		//
		// ThrottleRule parsing rules
		//
		digester.addObjectCreate("Throttles", "com.bgi.esm.monitoring.platform.value.ThrottleHashMap");
		digester.addObjectCreate("Throttles/ThrottleRule", "com.bgi.esm.monitoring.platform.value.ThrottleRule");
		digester.addSetNext("Throttles/ThrottleRule", "addRule");

		digester.addCallMethod("Throttles/ThrottleRule/group", "setGroup", 0);
		digester.addCallMethod("Throttles/ThrottleRule/level", "setStormLevel", 0);
		digester.addCallMethod("Throttles/ThrottleRule/duration", "setDuration", 0);
		digester.addCallMethod("Throttles/ThrottleRule/threshold", "setThreshold", 0);
		digester.addCallMethod("Throttles/ThrottleRule/action", "setAction", 0);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */