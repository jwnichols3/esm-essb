package com.bgi.esm.monitoring.platform.killer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;

/**
 * Killer is a command line utility which writes a "kill" message to JMS.
 * Downstream buss modules will note the kill message, forward it to the next
 * module and perform an orderly shutdown.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Killer {

	/**
	 * Process this OVO message
	 * 
	 * @param args OVO message elements, must be 16
	 * @throws Exception for anything
	 */
	public void execute(String[] args) throws Exception {
		String queue_name = StaticConfiguration.getString("queue.suppression.name");

		SuppressionQueue sq = new SuppressionQueue(queue_name);

		boolean flag = sq.queueWriterKiller();

		sq.shutDown();

		if (flag) {
			_log.debug("queue write success");
		} else {
			_log.error("queue write failure");
		}
	}

	/**
	 * Main entry point for the Notifier.
	 * 
	 * @param args OVO message elements, must be 16
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		Killer killer = new Killer();
		killer.execute(args);
	}

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(Killer.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */