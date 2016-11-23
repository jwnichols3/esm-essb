package com.bgi.esm.monitoring.platform.throttle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bgi.esm.monitoring.platform.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.value.ThrottleMessage;
import com.bgi.esm.monitoring.platform.value.ThrottleRule;

/**
 * Fresh message arrives Check storm level, perform storm level action
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class HistoBin {

	/**
	 * 
	 */
	public HistoBin(OutputQueue output, String name) {
		_queue = output;
		_group = name;
	}

	/**
	 * 
	 */
	public synchronized void addFreshMessage(ThrottleMessage arg) {
		if (_current_rule == null) {
			//
			// default is pass thru
			//
			_queue.queueWriter(arg);

			_log.debug("passthru w/null current rule");
		} else {
			ThrottleAction action = _current_rule.getAction();

			if (action.equals(ThrottleAction.PASS_THRU)) {
				_queue.queueWriter(arg);
			} else if (action.equals(ThrottleAction.SPOOL)) {
				_log.debug("todo - write to database");
			} else if (action.equals(ThrottleAction.DISCARD)) {
				// empty
			} else {
				_log.error("unknown throttle action:" + action);
			}
		}

		_time_stamps.add(new Date());
	}

	/**
	 * @param rule_list rule list
	 */
	public synchronized void census(List rule_list) {
		//
		// Rule zero is always default rule
		//
		ThrottleRule max_rule = (ThrottleRule) rule_list.get(0);

		if (_time_stamps.isEmpty()) {
			_log.info("empty time stamps");
			setCurrentRule(max_rule);
			return;
		}

		long max_duration = 0L;
		int time_limit = _time_stamps.size();

		Date time_now = new Date();
		long time_now_ms = time_now.getTime();

		//
		// Iterate for each rule and determine the population.
		// If the population exceeds rule threshold, nominate
		// rule for consideration.
		//
		for (int ii = 1; ii < rule_list.size(); ii++) {
			ThrottleRule temp_rule = (ThrottleRule) rule_list.get(ii);
			if (max_duration < temp_rule.getDurationInMilliSeconds()) {
				max_duration = temp_rule.getDurationInMilliSeconds();
			}

			long threshold = time_now_ms
					- temp_rule.getDurationInMilliSeconds();

			int population = 0;
			for (int jj = 0; jj < time_limit; jj++) {
				Date candidate = (Date) _time_stamps.get(jj);
				if (threshold < candidate.getTime()) {
					++population;
				}
			}

			if (population > temp_rule.getThreshold()) {
				if (max_rule.getStormLevel() < temp_rule.getStormLevel()) {
					max_rule = temp_rule;
				}
			}
		}

		setCurrentRule(max_rule);

		//
		// Prune time stamps list, messages older than the
		// largest rule window are eligible for deletion.
		//
		Collections.sort(_time_stamps);

		long bin_limit = time_now_ms - max_duration;

		Date candidate = (Date) _time_stamps.get(0);

		do {
			if (candidate.getTime() < bin_limit) {
				_time_stamps.remove(0);

				if (_time_stamps.isEmpty()) {
					candidate = null;
				} else {
					candidate = (Date) _time_stamps.get(0);
				}
			} else {
				candidate = null;
			}
		} while (candidate != null);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGroup() {
		return(_group);
	}

	/**
	 * 
	 */
	public ThrottleRule getCurrentRule() {
		return(_current_rule);
	}

	/**
	 * 
	 */
	private void setCurrentRule(ThrottleRule arg) {
		if (_current_rule == null) {
			_current_rule = arg;
			return;
		}

		if (arg.getStormLevel() != _current_rule.getStormLevel()) {
			_log.info("storm level:" + arg.getStormLevel());
			_current_rule = arg;
		}
	}

	/**
	 * Define current rule
	 */
	private ThrottleRule _current_rule;

	/**
	 * Array of Date objects representing processed messages. Must be Date,
	 * because Date implments Comparable
	 */
	private final ArrayList _time_stamps = new ArrayList();

	/**
	 * Group identity for this bin.
	 */
	private final String _group;

	/**
	 * Queue to Dispatcher
	 */
	private final OutputQueue _queue;

	/**
	 * Logger
	 */
	private final Log _log = LogFactory.getLog(HistoBin.class);
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
