package com.bgi.esm.monitoring.platform.utility;

import java.io.ByteArrayInputStream;

import java.util.Map;

import org.apache.commons.digester.ExtendedBaseRules;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.DataHashMap;
import com.bgi.esm.monitoring.platform.value.XmlIf;
import com.bgi.esm.monitoring.platform.value.ThrottleHashMap;

/**
 * Perform XML to Java conversion. Not reentrant.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class XmlParser {

	/**
	 * Generic XML parse support
	 * 
	 * @param message XML message
	 * @param rules digester rule set
	 * @return populated message value object or null if parse failure.
	 */
	public XmlIf xmlParser(String message, RuleSetBase rules) {
		_digester.setValidating(false);

		rules.addRuleInstances(_digester);

		ByteArrayInputStream bais = new ByteArrayInputStream(message.getBytes());

		try {
			return((XmlIf) _digester.parse(bais));
		} catch (Exception exception) {
			_log.error("parser choke:", exception);
		}

		return(null);
	}
	
	public XmlIf xmlParser(String message, ExtendedBaseRules rules) {
		_digester.setValidating(false);

		_digester.setRules(rules);

		ByteArrayInputStream bais = new ByteArrayInputStream(message.getBytes());

		try {
			return((XmlIf) _digester.parse(bais));
		} catch (Exception exception) {
			_log.error("parser choke:", exception);
		}

		return(null);
	}

	/**
	 * Parse a XML message from the data map queue.
	 * 
	 * @param arg XML data map message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf dataMapParser(String arg) {
		XmlIf result = xmlParser(arg, new DataMapRuleSet());
		_log.debug(result);
		return(result);
	}

	/**
	 * Parse a XML message from the data mine queue.
	 * 
	 * @param arg XML data mine message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf dataMineParser(String arg) {
		return(xmlParser(arg, new SuppressionRuleSet()));
	}

	/**
	 * Parse a XML message from the parser queue.
	 * 
	 * @param arg XML dispatcher message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf dispatcherParser(String arg) {
		return(xmlParser(arg, new DispatcherRuleSet()));
	}

	/**
	 * Parse a XML message from the event mine queue.
	 * 
	 * @param arg XML event mine message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf eventMineParser(String arg) {
		XmlIf result = xmlParser(arg, new EventMineRuleSet());
		_log.debug(result);
		return(result);
	}

	/**
	 * Parse a XML message from the OVI topic.
	 * 
	 * @param arg XML topic message
	 * @return populated ticket message or null if parse failure.
	 */
	public XmlIf oviParser(String arg) {
		XmlIf result = xmlParser(arg, new OviRuleSet());
		_log.debug(result);
		return(result);
	}

	/**
	 * Parse a XML message from the suppression queue.
	 * 
	 * @param arg XML suppression message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf suppressionParser(String arg) {
		XmlIf result = xmlParser(arg, new SuppressionRuleSet());
		_log.debug(result);
		return(result);
	}

	/**
	 * Parse a XML message from the throttle queue.
	 * 
	 * @param arg XML throttle message
	 * @return populated message or null if parse failure.
	 */
	public XmlIf throttleParser(String arg) {
		XmlIf result = xmlParser(arg, new ThrottleRuleSet());
		_log.debug(result);
		return(result);
	}

	/**
	 * Return the Throttle rules (configuration file, not message)
	 * 
	 * @param arg XML message
	 * @return rule map or null if parse failure.
	 */
	public Map throttleRuleParser(String arg) {
		XmlIf result = xmlParser(arg, new ThrottleRuleSet());
		_log.debug(result);

		if (result instanceof ThrottleHashMap) {
			ThrottleHashMap thm = (ThrottleHashMap) result;
			return(thm.getMap());
		}

		_log.error("bad result type:" + result);

		return(null);
	}

	/**
	 * Return the DataMap rules (configuration file, not message)
	 * 
	 * @param arg XML message
	 * @return rule map or null if parse failure.
	 */
	public Map dataMapRuleParser(String arg) {
		XmlIf result = xmlParser(arg, new DataMapRuleSet());
		_log.debug(result);

		if (result instanceof DataHashMap) {
			DataHashMap dhm = (DataHashMap) result;
			return(dhm.getMap());
		}

		_log.error("bad result type:" + result);

		return(null);
	}

	/**
	 * Duty Digester
	 */
	private final Digester _digester = new Digester();

	/**
	 * Define logger
	 */
	private static final Log _log = LogFactory.getLog(XmlParser.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
