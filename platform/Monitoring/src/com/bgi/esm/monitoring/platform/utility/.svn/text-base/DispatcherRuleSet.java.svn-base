package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Digester parsing rules for messages arriving on the DispatcherQueue
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class DispatcherRuleSet extends RuleSetBase {

    /**
     * Digester parsing rules.
     *
     * @param digester instance
     *
     */
    public void addRuleInstances(Digester digester) {
	//
        // KillMessage parsing rules
        //
	digester.addObjectCreate("EmsContainer/KillMessage", "com.bgi.esm.monitoring.platform.value.KillMessage");

	//
	// TicketMessage parsing rules
	//
 	digester.addObjectCreate("EmsContainer/DispatcherMessage", "com.bgi.esm.monitoring.platform.value.DispatcherMessage");

 	digester.addObjectCreate("EmsContainer/DispatcherMessage/ticket", "com.bgi.esm.monitoring.platform.value.TicketMessage");
        digester.addSetNext("EmsContainer/DispatcherMessage/ticket", "setTicketMessage");

	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/message_id", "setMessageId", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/source_node", "setSourceNode", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/source_node_type", "setSourceNodeType", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/creation_date", "setCreationDate", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/creation_time", "setCreationTime", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/receive_date", "setReceiveDate", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/receive_time", "setReceiveTime", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/application", "setApplication", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/message_group", "setMessageGroup", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/object", "setObject", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/severity", "setSeverity", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/operators/operator", "setOperator", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/message_text", "setMessageText", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/instruction_text", "setInstructionText", 0);
	digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/suppress_count", "setSuppressCount", 0);

        digester.addObjectCreate("EmsContainer/DispatcherMessage/ticket/message_attributes/attribute", "com.bgi.esm.monitoring.platform.value.KeyValue");
        digester.addSetNext("EmsContainer/DispatcherMessage/ticket/message_attributes/attribute", "addKeyValue");

        digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/message_attributes/attribute/key", "setKey", 0);
        digester.addCallMethod("EmsContainer/DispatcherMessage/ticket/message_attributes/attribute/value", "setValue", 0);

	//
	// DispatcherRule parsing rules
	//
 	digester.addObjectCreate("Dispatchers", "com.bgi.esm.monitoring.platform.value.DataHashMap");
 	digester.addObjectCreate("Dispatchers/Dispatcher", "com.bgi.esm.monitoring.platform.value.DispatcherRule");
 	digester.addSetNext("Dispatchers/Dispatcher", "addRule");

 	digester.addCallMethod("Dispatchers/Dispatcher/group", "setGroup", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/method", "setMethod", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/alarm_point_group", "setAlarmPointGroup", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/alarm_point_script", "setAlarmPointScript", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_category", "setPeregrineCategory", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_subcategory", "setPeregrineSubCategory", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_product", "setPeregrineProduct", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_problem", "setPeregrineProblem", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_assignment_group", "setPeregrineAssignment", 0);
 	digester.addCallMethod("Dispatchers/Dispatcher/peregrine_location", "setPeregrineLocation", 0);
    }

    /**
     * Define logger
     */
    private static final Log _log = LogFactory.getLog(DispatcherRuleSet.class);
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
