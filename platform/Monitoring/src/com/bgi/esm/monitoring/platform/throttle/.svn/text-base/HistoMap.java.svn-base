package com.bgi.esm.monitoring.platform.throttle;

import java.util.Iterator;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.ThrottleMessage;

/**
 * Parent container for all histogram bins.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class HistoMap {

	/**
	 * 
	 */
	public HistoMap(OutputQueue arg) {
		_oq = arg;
	}

	/**
	 * 
	 */
	public synchronized void addFreshMessage(ThrottleMessage arg) {
		String target = arg.getTicketMessage().getMessageGroup();

		HistoBin bin = (HistoBin) _map.get(target);

		if (bin == null) {
			_log.debug("creating new bin:" + target);

			bin = new HistoBin(_oq, target);

			_map.put(target, bin);
		} else {
			_log.debug("existing bin:" + target);
		}

		bin.addFreshMessage(arg);
	}

	/**
	 * 
	 */
	public synchronized HistoBin getBin(String arg) {
		return((HistoBin) _map.get(arg));
	}

	/**
	 * 
	 */
	public synchronized Iterator getKeys() {
		return(_map.keySet().iterator());
	}

	/**
	 * Downstream to Dispatcher
	 */
	private final OutputQueue _oq;

	/**
	 * Map of candidates
	 */
	private final HashMap _map = new HashMap();

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(HistoMap.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
