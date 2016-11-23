package com.bgi.esm.monitoring.platform.data_map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.utility.DataMapQueue;
import com.bgi.esm.monitoring.platform.utility.StaticConfiguration;
import com.bgi.esm.monitoring.platform.utility.ThrottleQueue;

import com.bgi.esm.monitoring.platform.value.DataMapMessage;
import com.bgi.esm.monitoring.platform.value.DataMapRule;
import com.bgi.esm.monitoring.platform.value.KillMessage;
import com.bgi.esm.monitoring.platform.value.ThrottleMessage;
import com.bgi.esm.monitoring.platform.value.TicketMessage;
import com.bgi.esm.monitoring.platform.value.XmlIf;

/**
 * Main for the DataMap buss modules.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMap {

	/**
	 * ctor, discover queue names and start rule manager thread
	 * 
	 * @param arg instance name (i.e. primary, secondary, etc)
	 */
	public DataMap(String arg) throws Exception {
		_instance_name = arg;

		_in_queue_name = StaticConfiguration.getString("queue.data_map.name");
		_out_queue_name = StaticConfiguration.getString("queue.throttle.name");

		_log.info("start:" + _instance_name);
		_log.info("inbound:" + _in_queue_name);
		_log.info("outbound:" + _out_queue_name);

		_rm.start();
	}

	/**
	 * Process incoming messages (kill or tickets).
	 * 
	 * @throws Exception for anything unsavory
	 */
	public void execute() throws Exception {
		_dmq = new DataMapQueue(_in_queue_name);
		_tq = new ThrottleQueue(_out_queue_name);

		while (_run_flag) {
			_log.debug("blocking for queue read");
			XmlIf message = _dmq.dataMapQueueReader();
			_log.debug("fresh message:" + message);

			if (message instanceof KillMessage) {
				_log.debug("kill message type noted");

				_tq.queueWriterKiller();

				shutDown();
			} else if (message instanceof DataMapMessage) {
				_log.debug("data map message type noted");

				throttleMessage((DataMapMessage) message);
			} else {
				_log.error("unknown message type");
			}
		}
	}

	/**
	 * Graceful shut down
	 */
	public void shutDown() {
		_log.warn("orderly shutdown");

		_run_flag = false;

		_rm.shutDown();

		if (_dmq != null) {
			_dmq.shutDown();
		}

		if (_tq != null) {
			_tq.shutDown();
		}
	}

	/**
	 * Build and send a message to throttle. 
	 * Messages which have no supporting DataMapRule are not forwarded.
	 * 
	 * @param arg input message
	 */
	public void throttleMessage(DataMapMessage arg) {
		TicketMessage tix = arg.getTicketMessage();

		String key = tix.getMessageGroup();

		DataMapRule rule = _rm.getRule(key);
		if (rule == null) {
			_log.error("unable to get rule for:" + key);
			return;
		}

		ThrottleMessage tm = new ThrottleMessage();
		tm.setTicketMessage(tix);
		tm.setDataMapRule(rule);

		_tq.queueWriter(tm);
	}

	/**
	 * Main entry point for the DataMap Buss module.
	 * 
	 * @param args command line arguments
	 * @throws Exception for anything
	 */
	public static void main(String[] args) throws Exception {
		DataMap data_map = new DataMap(args[0]);
		
		Thread.sleep(1000L); //read rule file
		
		data_map.execute();
	}

	/**
	 * Name of this instance (i.e. "primary", "secondary", etc)
	 */
	private final String _instance_name;

	/**
	 * Inbound queue name, defined in configuration file.
	 */
	private final String _in_queue_name;

	/**
	 * Input queue (from suppression)
	 */
	private DataMapQueue _dmq;

	/**
	 * Outbound queue name, defined in configuration file.
	 */
	private final String _out_queue_name;

	/**
	 * Output queue (to throttle)
	 */
	private ThrottleQueue _tq;

	/**
	 * Determine when DataMap should gracefully exit
	 */
	private boolean _run_flag = true;

	/**
	 * Rule manager thread
	 */
	private final RuleManager _rm = new RuleManager();

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(DataMap.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */