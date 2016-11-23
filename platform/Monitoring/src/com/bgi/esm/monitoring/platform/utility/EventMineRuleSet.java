package com.bgi.esm.monitoring.platform.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;

/**
 * Digester parsing rules for messages arriving on the EventMineQueue
 * 
 * @author coleguy
 */
public class EventMineRuleSet extends ExtendedBaseRules {

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
		// EventMineMessage parsing rules
		//
		digester.addObjectCreate("EmsContainer/EventMineMessage", "com.bgi.esm.monitoring.platform.value.EventMineMessage");

		digester.addCallMethod("EmsContainer/EventMineMessage/source", "setSource", 0);
		digester.addCallMethod("EmsContainer/EventMineMessage/payload", "setPayload", 0);
		digester.addCallMethod("EmsContainer/EventMineMessage/payload/?", "addPayload", 0);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */