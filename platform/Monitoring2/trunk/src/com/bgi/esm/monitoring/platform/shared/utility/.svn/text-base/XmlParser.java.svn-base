package com.bgi.esm.monitoring.platform.shared.utility;

import java.io.ByteArrayInputStream;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.shared.value.XmlIf;

/**
 * Perform XML to Java conversion. Not reentrant.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */

public class XmlParser {

	/**
	 * ctor, instantiate WebLogic logging
	 */
	public XmlParser() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
            _log = Logger.getLogger ( XmlParser.class );
		}

        if ( null == _log ) {
			System.err.println("XmlParser ctor failure");
        }
	}

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
		} catch(Exception exception) {
			_log.error("parser choke:", exception);
		}

		return(null);
	}

	/**
	 * Parse a Open View XML message from the notifier
	 * 
	 * @param arg Open View XML
	 * @return populated message or null if parse failure.
	 */
	public XmlIf rawOviNotifyParser(String arg) {
		return(xmlParser(arg, new OviRuleSet()));
	}

	/**
	 * Parse a Open View XML message from the notifier
	 * 
	 * @param arg Open View XML
	 * @return populated message or null if parse failure.
	 */
	public XmlIf rawOviModifyParser(String arg) {
		// return(xmlParser(arg, new OviRuleSet()));
		// stub
		return(null);
	}

	/**
	 * Parse a Open View XML message from the notifier
	 * 
	 * @param arg Open View XML
	 * @return populated message or null if parse failure.
	 */
	public XmlIf rawOviReadParser(String arg) {
		// return(xmlParser(arg, new OviRuleSet()));
		// stub
		return(null);
	}

	/**
	 * Duty Digester
	 */
	private final Digester _digester = new Digester();

	/**
	 * Define logger
	 */
	private Logger _log;
}
