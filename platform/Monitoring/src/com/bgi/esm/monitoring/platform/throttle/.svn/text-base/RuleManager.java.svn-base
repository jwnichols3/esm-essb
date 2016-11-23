package com.bgi.esm.monitoring.platform.throttle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;
import com.bgi.esm.monitoring.platform.utility.XmlParser;

import com.bgi.esm.monitoring.platform.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.value.ThrottleRule;

/**
 * Obtain throttle rules from the rule file.
 * Periodically inspect the rule file for updates.
 * When the rule file is updated, read and parse the contents into 
 * a rule map, which is consulted as each ticket message arrives.
 * 
 * <p>
 * RuleMananger runs as a thread which polls the rule file.
 * Polling frequency and rule (extract) file name is specified within 
 * the global configuration file.
 *
 * <p>
 * Thread runs until shutDown() is invoked.
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class RuleManager extends Thread {

    /**
     * Entry point for thread
     */
    public void run() {
	while (_run_flag) {
	    _log.debug("...run active...");

	    Map map = fileTest();
	    if (map != null) {
		synchronized(this) {
		    _rules = map;
		}
	    }

	    sleep();
	}
    }

    /**
     * Return current throttle rules
     *
     * @return current throttle rules
     */
    public synchronized Map getRuleMap() {
	return(_rules);
    }

    /**
     * Given a key, return a rule or null if not found
     *
     * @param arg rule key
     * @return given a key, return throttle rules.  Never null or empty.
     */
    public synchronized List getRules(String arg) {
	ArrayList result = new ArrayList();

	//
	// First rule is default rule
	//
	ThrottleRule rule = new ThrottleRule();
	rule.setGroup(arg);
	rule.setStormLevel(0);
	rule.setDuration(10000L);
	rule.setThreshold(0);
	rule.setAction(ThrottleAction.PASS_THRU);

	result.add(rule);

	// TODO - something better
	Iterator iterator = _rules.keySet().iterator();
	while (iterator.hasNext()) {
	    String key = (String) iterator.next();
	    rule = (ThrottleRule) _rules.get(key);

	    if (rule.getGroup().equals(arg)) {
		result.add(rule);
	    }
	}

	return(result);
    }

    /**
     * Cause the thread to exit
     */ 
    public void shutDown() {
	_run_flag = false;

	interrupt(); 
    }

    /**
     * Determine if the rule (extract) file has been updated.  
     * If so, return a map of Rules to test ticket messages.
     * Rule file is specified within configuration file.
     *
     * @return map of rules or null if no update.
     */
    private Map fileTest() {
	String target = StaticConfiguration.getString("throttle.rule.file");
	if (target == null) {
	    _log.error("missing file name from configuration");
	    return(null);
	} 

	File file = new File(target);
	if (file.exists() && file.isFile()) {
	    //empty
	} else {
	    _log.error("missing rule file:" + target);
	    return(null);
	}

	long modified = file.lastModified();

	if (_rule_file_modified != modified) {
	    _log.info("must read fresh rule file:" + target);
	} else {
	    _log.debug("unchanged rule file:" + target);
	    return(null);
	}

	try {
	    BufferedReader br = new BufferedReader(new FileReader(file));

	    String inbuffer;
	    StringBuffer sb = new StringBuffer();
	    while ((inbuffer = br.readLine()) != null) {
		sb.append(inbuffer);
	    }

	    br.close();

	    XmlParser xp = new XmlParser();

	    Map map = xp.throttleRuleParser(sb.toString());
	    if (map != null) {
		_rules = map;
		_rule_file_modified = modified;
	    }

	    _log.info("rule population:" + _rules.size());
	    
	    return(_rules);
	} catch(Exception exception) {
	    _log.error("choke", exception);
	}

	return(null);
    }

    /**
     * Sleep until next file test.  
     * Blocks thread until duration expires.
     * Duration from configuration file.
     * If the configuration value is not available, use default value.
     */
    private void sleep() {
	int sleep_value = DEFAULT_SLEEP;
	Integer sleep_temp = StaticConfiguration.getInteger("throttle.rule.sleep");
	if (sleep_temp != null) {
	    sleep_value = sleep_temp.intValue() * 1000;
	}

	if (sleep_value < 1) {
	    sleep_value = DEFAULT_SLEEP;
	}

	_log.debug("sleep:" + sleep_value);

	try {
	    sleep(sleep_value);
	} catch(InterruptedException exception) {
	    _log.error("choke", exception);
	}
    }

    /**
     * Current rules
     */
    private Map _rules;

    /**
     * Rule file length in milliseconds from last read.  
     * Employed to determine if rule file has been updated.
     */
    private long _rule_file_modified = 0L;

    /**
     * Determine when thread should gracefully exit
     */
    private boolean _run_flag = true;

    /**
     * Default sleep value of 60 seconds.
     * Only used if configuration file failure.
     */
    private static final int DEFAULT_SLEEP = 60 * 1000;
    
    /**
     * Logger
     */
    private final Log _log = LogFactory.getLog(RuleManager.class);
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
