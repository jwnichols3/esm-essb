package com.bgi.esm.monitoring.platform.throttle;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

/**
 * Inspector is a thread which iterates across all message groups 
 * and adjusts storm levels.
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class Inspector extends Thread {

    /**
     *
     */
    public Inspector(RuleManager rm, HistoMap arg) {
	_rm = rm;
	_map = arg;
    }

    /**
     * Iterate across all message groups to test storm levels.
     */
    public void run() {

	while (_run_flag) {
	    _log.debug("...run active...");

 	    Iterator iterator = _map.getKeys();
 	    while (iterator.hasNext()) {
		String key = (String) iterator.next();
		_log.debug("testing:" + key);

		HistoBin current = (HistoBin) _map.getBin(key);
		current.census(_rm.getRules(key));

		_log.debug(key + ":" + current.getCurrentRule());
 	    }
	    
	    sleep();
	}
    }

    /**
     * Cause the thread to exit
     */ 
    public void shutDown() {
	_run_flag = false;

	interrupt(); 
    }

    /**
     * Sleep until next file test.  
     * Blocks thread until duration expires.
     * Duration from configuration file.
     * If the configuration value is not available, use default value.
     */
    private void sleep() {
	int sleep_value = DEFAULT_SLEEP;
	Integer sleep_temp = StaticConfiguration.getInteger("throttle.inspector.sleep");

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
     *
     */
    private HistoMap _map;

    /**
     *
     */
    private RuleManager _rm;

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
    private final Log _log = LogFactory.getLog(Inspector.class);
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
