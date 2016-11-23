package com.bgi.esm.monitoring.platform.throttle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;
import com.bgi.esm.monitoring.platform.utility.ThrottleQueue;

import com.bgi.esm.monitoring.platform.value.KillMessage;
import com.bgi.esm.monitoring.platform.value.ThrottleMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * The throttle module monitors message activity by group.
 * Depending upon activity, messages are either:
 * <OL>
 * <LI> Passed on to dispatcher
 * <LI> Written to a database where they can be viewed as a collection
 * <LI> Discarded
 * </OL> 
 * 
 * Decisions are reached by monitoring message volume over time and applying
 * user defined rules.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Throttle {

	/**
	 * ctor connect to queues, start threads
	 * 
	 * @param arg instance name (i.e. primary, secondary, etc)
	 */
	public Throttle(String arg) throws Exception {
		_instance_name = arg;
		_log.debug("start:" + _instance_name);

		String name = StaticConfiguration.getString("queue.throttle.name");
		_log.debug("inbound:" + name);
		_tq = new ThrottleQueue(name);

		_oq = new OutputQueue();

		_hm = new HistoMap(_oq);

		_rule_manager.start();

		_inspector = new Inspector(_rule_manager, _hm);
		_inspector.start();
	}

	/**
	 * Process incoming messages and dispatch accordingly.
	 * 
	 * @throws Exception for anything
	 */
	public void execute() throws Exception {
		while (_run_flag) {
			_log.debug("blocking for queue read");
			XmlIf message = _tq.throttleQueueReader();
			_log.debug("fresh message:" + message);

			if (message instanceof KillMessage) {
				_log.debug("kill message type noted");

				_oq.queueWriterKiller();

				shutDown();
			} else if (message instanceof ThrottleMessage) {
				_log.debug("throttle message type noted");

				_hm.addFreshMessage((ThrottleMessage) message);
			} else {
				_log.error("unknown message type:" + message);
			}
		}
	}

	/**
	 * Graceful shut down
	 */
	public void shutDown() {
		_log.warn("orderly shutdown");

		_run_flag = false;

		if (_oq != null) {
			_oq.shutDown();
		}

		if (_tq != null) {
			_tq.shutDown();
		}

		_inspector.shutDown();

		_rule_manager.shutDown();
	}

	/**
	 * Main entry point for the Throttle Buss module.
	 * 
	 * @param args command line arguments
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		Throttle throttle = new Throttle(args[0]);
		throttle.execute();
	}

	/**
	 * 
	 */
	private final HistoMap _hm;

	/**
	 * 
	 */
	private final Inspector _inspector;

	/**
	 * Name of this instance (i.e. "primary", "secondary", etc)
	 */
	private final String _instance_name;

	/**
	 * Rule manager
	 */
	private final RuleManager _rule_manager = new RuleManager();

	/**
	 * Input queue
	 */
	private final ThrottleQueue _tq;

	/**
	 * Output queue
	 */
	private final OutputQueue _oq;

	/**
	 * Determine when Throttle should gracefully exit
	 */
	private boolean _run_flag = true;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(Throttle.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
