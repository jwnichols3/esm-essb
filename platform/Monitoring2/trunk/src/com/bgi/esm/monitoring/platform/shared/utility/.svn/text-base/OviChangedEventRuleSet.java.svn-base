package com.bgi.esm.monitoring.platform.shared.utility;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Digester parsing rules for messages arriving from the OVI topic.
 * 
 * @author Dennis Lin (linden)
 */
public class OviChangedEventRuleSet extends RuleSetBase {

	/**
	 * Digester parsing rules for OVI messages
	 * 
	 * @param digester
	 *            instance
	 */
	public void addRuleInstances(Digester digester) {
		digester.addObjectCreate ( "ovMessageChangeEvent", "com.bgi.esm.monitoring.platform.shared.value.ParsedOvi");

		//
		// envelope
		//

		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/messageUUID",        "setMessageId",      0 );
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/timeStamp/seconds",  "setTimeStamp",      0 );
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/severity",           "setSeverity",       0 );

		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/messageSource",      "setMessageSource",  0 );

		// application
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageEnvelope/eventSource",        "setEventSource",    0 );

		//
		// body
		//

		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageBody/state",                  "setState",          0 );

		// suppress count
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageBody/numberOfDuplicates",     "setDuplicateCount", 0 );

		digester.addObjectCreate ( "ovMessageChangeEvent/ovMessage/messageBody/messageData/data",       "com.bgi.esm.monitoring.platform.shared.value.KeyValue");
		digester.addSetNext      ( "ovMessageChangeEvent/ovMessage/messageBody/messageData/data",       "addKeyValue");

		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageBody/messageData/data/name",  "setKey",            0 );
		digester.addCallMethod   ( "ovMessageChangeEvent/ovMessage/messageBody/messageData/data/value", "setValue",          0 );
	}
}
