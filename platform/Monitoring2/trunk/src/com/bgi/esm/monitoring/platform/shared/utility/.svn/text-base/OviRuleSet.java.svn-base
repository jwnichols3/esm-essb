package com.bgi.esm.monitoring.platform.shared.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving from the OVI topic.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class OviRuleSet extends RuleSetBase {

	/**
	 * Digester parsing rules for OVI messages
	 * 
	 * @param digester
	 *            instance
	 */
	public void addRuleInstances(Digester digester) {
		digester.addObjectCreate("ovMessage", "com.bgi.esm.monitoring.platform.shared.value.ParsedOvi");

		//
		// envelope
		//

		digester.addCallMethod   ( "ovMessage/messageEnvelope/messageUUID",        "setMessageId",      0 );
		digester.addCallMethod   ( "ovMessage/messageEnvelope/timeStamp/seconds",  "setTimeStamp",      0 );
		digester.addCallMethod   ( "ovMessage/messageEnvelope/severity",           "setSeverity",       0 );

		digester.addCallMethod   ( "ovMessage/messageEnvelope/messageSource",      "setMessageSource",  0 );

		// application
		digester.addCallMethod   ( "ovMessage/messageEnvelope/eventSource",        "setEventSource",    0 );

		//
		// body
		//

		digester.addCallMethod   ( "ovMessage/messageBody/state",                  "setState",          0 );

		// suppress count
		digester.addCallMethod   ( "ovMessage/messageBody/numberOfDuplicates",     "setDuplicateCount", 0 );

		digester.addObjectCreate ( "ovMessage/messageBody/messageData/data",       "com.bgi.esm.monitoring.platform.shared.value.KeyValue");
		digester.addSetNext      ( "ovMessage/messageBody/messageData/data",       "addKeyValue");

		digester.addCallMethod   ( "ovMessage/messageBody/messageData/data/name",  "setKey",            0 );
		digester.addCallMethod   ( "ovMessage/messageBody/messageData/data/value", "setValue",          0 );
	}
}
