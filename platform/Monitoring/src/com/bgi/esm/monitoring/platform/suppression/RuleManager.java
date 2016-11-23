package com.bgi.esm.monitoring.platform.suppression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

/**
 * Obtain suppression rules from the extract file. Periodically inspect the rule
 * (extract) file for updates. When the rule (extract) file is updated, read and
 * parse the contents into a rule list, which is consulted as each ticket
 * message arrives.
 * 
 * <p>
 * RuleMananger runs as a thread which polls the rule (extract) file. Polling
 * frequency and rule (extract) file name is specified within the global
 * configuration file.
 * 
 * <p>
 * Thread runs until shutDown() is invoked.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class RuleManager extends Thread {

	/**
	 * Entry point for thread
	 */
	public void run() {
		_log.info("entering run");

		while (_run_flag) {
			_log.debug("...run active...");

			List list = fileTest();
			if (list != null) {
				synchronized (this) {
					_rule_list = list;
				}
			}

			sleep();
		}

		_log.info("exiting run");
	}

	/**
	 * Return current suppression rules
	 * 
	 * @return current suppression rules
	 */
	public synchronized List getRuleList() {
		return(_rule_list);
	}

	/**
	 * Cause the thread to exit
	 */
	public void shutDown() {
		_run_flag = false;

		interrupt();
	}

	/**
	 * Determine if the rule (extract) file has been updated. If so, return a
	 * list of Rules to test ticket messages. Rule file is specified within
	 * configuration file.
	 * 
	 * @return list of rules or null if no update.
	 */
	private List fileTest() {
		String target = StaticConfiguration.getString("suppression.rule.file");
		if (target == null) {
			_log.error("missing file name from configuration");
			return (null);
		}

		File file = new File(target);
		if (file.exists() && file.isFile()) {
			// empty
		} else {
			_log.error("missing rule file:" + target);
			return (null);
		}

		long modified = file.lastModified();

		if (_rule_file_modified != modified) {
			_log.info("must read fresh rule file:" + target);
		} else {
			_log.debug("unchanged rule file:" + target);
			return (null);
		}

		try {
			ArrayList results = new ArrayList();
			int expired = 0;

			BufferedReader br = new BufferedReader(new FileReader(file));

			String inbuffer;
			while ((inbuffer = br.readLine()) != null) {
				Rule rule = new Rule(inbuffer);
				if (rule.isExpired()) {
					// inhibit loading rule into results
					_log.info("expired:" + rule);
					++expired;
				} else {
					// add to results
					results.add(rule);
				}
			}

			br.close();

			_rule_file_modified = modified;

			_log.info("active rule population:" + results.size() + " expired:"
					+ expired);

			return(results);
		} catch (Exception exception) {
			_log.error("choke", exception);
		}

		return(null);
	}

	/**
	 * Sleep until next file test. Blocks thread until duration expires.
	 * Duration from configuration file. If the configuration value is not
	 * available, use default value.
	 */
	private void sleep() {
		int sleep_value = DEFAULT_SLEEP;
		Integer sleep_temp = StaticConfiguration.getInteger("suppression.rule.sleep");
		if (sleep_temp != null) {
			sleep_value = sleep_temp.intValue() * 1000;
		}

		if (sleep_value < 1) {
			sleep_value = DEFAULT_SLEEP;
		}

		_log.debug("sleep:" + sleep_value);

		try {
			sleep(sleep_value);
		} catch (InterruptedException exception) {
			_log.error("choke", exception);
		}
	}

	/**
	 * Current rule list.
	 */
	private List _rule_list = new ArrayList();

	/**
	 * Rule file length in binary from last read. Employed to determine if rule
	 * file has been updated.
	 */
	private long _rule_file_modified = 0L;

	/**
	 * Determine when thread should gracefully exit
	 */
	private boolean _run_flag = true;

	/**
	 * Default sleep value of 60 seconds. Only used if configuration file
	 * failure.
	 */
	private static final int DEFAULT_SLEEP = 60 * 1000;

	/**
	 * 
	 */
	private final Log _log = LogFactory.getLog(RuleManager.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */