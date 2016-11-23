package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving on the SuppressionQueue.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class SuppressionRuleSet extends RuleSetBase {

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
		digester.addObjectCreate("EmsContainer/SuppressionMessage", "com.bgi.esm.monitoring.platform.value.SuppressionMessage");

		digester.addObjectCreate("EmsContainer/SuppressionMessage/ticket", "com.bgi.esm.monitoring.platform.value.TicketMessage");
		digester.addSetNext("EmsContainer/SuppressionMessage/ticket", "setTicketMessage");

		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/message_id", "setMessageId", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/source_node", "setSourceNode", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/source_node_type", "setSourceNodeType", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/creation_date", "setCreationDate", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/creation_time", "setCreationTime", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/receive_date", "setReceiveDate", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/receive_time", "setReceiveTime", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/application", "setApplication", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/message_group", "setMessageGroup", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/object", "setObject", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/severity", "setSeverity", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/operators/operator", "setOperator", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/message_text", "setMessageText", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/instruction_text", "setInstructionText", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/suppress_count", "setSuppressCount", 0);

		digester.addObjectCreate("EmsContainer/SuppressionMessage/ticket/message_attributes/attribute", "com.bgi.esm.monitoring.platform.value.KeyValue");
		digester.addSetNext("EmsContainer/SuppressionMessage/ticket/message_attributes/attribute", "addKeyValue");

		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/message_attributes/attribute/key", "setKey", 0);
		digester.addCallMethod("EmsContainer/SuppressionMessage/ticket/message_attributes/attribute/value", "setValue", 0);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
