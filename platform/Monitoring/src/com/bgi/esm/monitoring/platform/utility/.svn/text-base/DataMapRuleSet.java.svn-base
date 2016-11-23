package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving on the DataMapQueue.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMapRuleSet extends RuleSetBase {

	/**
	 * Digester parsing rules for messages arriving on the DataMapQueue.
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
		digester.addObjectCreate("EmsContainer/DataMapMessage", "com.bgi.esm.monitoring.platform.value.DataMapMessage");

		digester.addObjectCreate("EmsContainer/DataMapMessage/ticket", "com.bgi.esm.monitoring.platform.value.TicketMessage");
		digester.addSetNext("EmsContainer/DataMapMessage/ticket", "setTicketMessage");

		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/message_id", "setMessageId", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/source_node", "setSourceNode", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/source_node_type", "setSourceNodeType", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/creation_date", "setCreationDate", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/creation_time", "setCreationTime", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/receive_date", "setReceiveDate", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/receive_time", "setReceiveTime", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/application", "setApplication", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/message_group", "setMessageGroup", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/object", "setObject", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/severity", "setSeverity", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/operators/operator", "setOperator", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/message_text", "setMessageText", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/instruction_text", "setInstructionText", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/suppress_count", "setSuppressCount", 0);

		digester.addObjectCreate("EmsContainer/DataMapMessage/ticket/message_attributes/attribute", "com.bgi.esm.monitoring.platform.value.KeyValue");
		digester.addSetNext("EmsContainer/DataMapMessage/ticket/message_attributes/attribute", "addKeyValue");

		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/message_attributes/attribute/key", "setKey", 0);
		digester.addCallMethod("EmsContainer/DataMapMessage/ticket/message_attributes/attribute/value", "setValue", 0);

		//
		// DataMapRule parsing rules
		//
		digester.addObjectCreate("DataMaps", "com.bgi.esm.monitoring.platform.value.DataHashMap");
		digester.addObjectCreate("DataMaps/DataMap", "com.bgi.esm.monitoring.platform.value.DataMapRule");
		digester.addSetNext("DataMaps/DataMap", "addRule");

		digester.addCallMethod("DataMaps/DataMap/group", "setGroup", 0);
		digester.addCallMethod("DataMaps/DataMap/method", "setMethod", 0);
		digester.addCallMethod("DataMaps/DataMap/alarm_point_group", "setAlarmPointGroup", 0);
		digester.addCallMethod("DataMaps/DataMap/alarm_point_script", "setAlarmPointScript", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_category", "setPeregrineCategory", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_subcategory", "setPeregrineSubCategory", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_product", "setPeregrineProduct", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_problem", "setPeregrineProblem", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_assignment_group", "setPeregrineAssignment", 0);
		digester.addCallMethod("DataMaps/DataMap/peregrine_location", "setPeregrineLocation", 0);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */