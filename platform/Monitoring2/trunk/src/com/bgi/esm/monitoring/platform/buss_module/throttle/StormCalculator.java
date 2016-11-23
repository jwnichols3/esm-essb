package com.bgi.esm.monitoring.platform.buss_module.throttle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import javax.jms.JMSException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.orm.SpoolEjbUtil;
import com.bgi.esm.monitoring.platform.shared.utility.JmsFacade;
import com.bgi.esm.monitoring.platform.shared.value.Census;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.StormMessage;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;

/**
 * Calculate storm levels
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class StormCalculator {

	/**
	 * ctor, setup weblogic logging
	 */
	public StormCalculator() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch (Exception exception) {
			System.err.println("StormCalculator ctor failure");
            _log = Logger.getLogger ( StormCalculator.class );
		}

		_log.debug("storm calculator ctor");
	}

	/**
	 * Perform storm calculations for all known data map groups
	 * 
	 * @param dispatcher handle to dispatcher
	 */
	public void execute(JmsFacade dispatcher) {
		_log.debug("storm calculate execute start");

		_time_now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		//
		// Iterate for all groups w/data maps
		//
		List candidates = _orm.getAllDataMapRules();

		for (int ii = 0; ii < candidates.size(); ii++) {
			DataMapRule rule = (DataMapRule) candidates.get(ii);
			ThrottleRule winner = calculate(rule.getGroup());
			_log.debug("winner:" + winner);

			Storm storm = _orm.selectStorm(rule.getGroup());	
			if ((storm != null) && (storm.getStormLevel() == winner.getStormLevel())) {
				_log.debug("no change in storm level:" + rule.getGroup());
			} else {
				_log.info("change in storm level:" + rule.getGroup());

				storm = new Storm();
				storm.setGroup(rule.getGroup());
				storm.setStormLevel(winner.getStormLevel());
				storm.setAction(winner.getAction());
				storm.setSpoolKey(SpoolEjbUtil.generateGUID(this));

				storm = _orm.addOrUpdateStorm(storm);
				
				stormInProgress(storm, dispatcher);
			}
				
			long temp1 = storm.getReminderTimeStamp().getTimeInMillis();
			long temp2 = _time_now.getTimeInMillis();
		
			if (winner.getAction().equals(ThrottleAction.PASS_THRU)) {
				//empty
			} else if (winner.getAction().equals(ThrottleAction.SPOOL)) {
				if (winner.isEnableStormMessages()) {
					if ((temp1+NAG_PERIOD) < temp2) {
						stormInProgress(storm, dispatcher);
					
						storm.setReminderTimeStamp(_time_now);
					
						_orm.addOrUpdateStorm(storm);
					}
				} else {
					_log.debug("suppressing in progress message");
				}
			} else if (winner.getAction().equals(ThrottleAction.DISCARD)) {
				if (winner.isEnableStormMessages()) {
					if ((temp1+NAG_PERIOD) < temp2) {
						stormInProgress(storm, dispatcher);
					
						storm.setReminderTimeStamp(_time_now);
					
						_orm.addOrUpdateStorm(storm);
					}
				} else {
					_log.debug("suppressing in progress message");
				}
			} else {
				_log.error("unknown throttle action:" + winner.getAction());
			}			
		}

		_log.info("storm calculate execute stop");
	}

	/**
	 * Calculate storm level for a given group. 
	 * Reap census table rows which are too old to impact histogram. 
	 * If no throttle rules exist, create default rules.
	 * 
	 * @param arg group name
	 * @return winning rule
	 *
	 */
	private ThrottleRule calculate(String arg) {
		_log.info("calculate:" + arg);

		List<ThrottleRule> rules = _orm.selectThrottleRuleGroup(arg);
		_log.debug("rule population:" + rules.size());
		if (rules.isEmpty()) {
			rules = defaultRules(arg);
		}

		Collections.sort(rules); // sort by storm level
		ThrottleRule max_rule = rules.get(0);

		List<Census> rows = _orm.selectCensusGroup(arg);
		_log.debug("row population:" + rows.size());
		if (rows.isEmpty()) {
			return(max_rule);
		}

		Collections.sort(rows); // sort by ascending time (oldest first)
		int census_limit = rows.size();

		long time_now_ms = _time_now.getTimeInMillis();

		long max_duration = 0L;

		//
		// iterate for each rule and determine the
		// highest matching storm level
		//

		for (int ii = 0; ii < rules.size(); ii++) {
			ThrottleRule rule = (ThrottleRule) rules.get(ii);
			_log.debug(rule);

			if (max_duration < rule.getDurationInMillis()) {
				max_duration = rule.getDurationInMillis();
			}

			long threshold = time_now_ms - rule.getDurationInMillis();

			int population = 0;
			for (int jj = 0; jj < census_limit; jj++) {
				Census census = (Census) rows.get(jj);
				if (threshold < census.getTimeStamp().getTimeInMillis()) {
					++population;
				}
			}

			_log.debug("current population:" + population + ":for rule:" + rule.getStormLevel() + ":" + rule.getThreshold());

			if (population > rule.getThreshold()) {
				max_rule = rule;
			}
		}

		//
		// Prune list, remove items which are too old for calculations
		//
        
		long threshold = time_now_ms - max_duration;
		
		for (int jj = 0; jj < census_limit; jj++) {
			Census census = (Census) rows.get(jj);
			if (threshold > census.getTimeStamp().getTimeInMillis()) {
				_orm.deleteCensus(census);
			}
		}
        /*
        //*/

		return(max_rule);
	}

	/**
	 * If throttle rules have not been specified, create reasonable defaults.
	 */
	private List<ThrottleRule> defaultRules(String arg) {
		List<ThrottleRule> list = new ArrayList<ThrottleRule>();

		ThrottleRule rule = new ThrottleRule();
        rule.setRuleId ( _orm.nextMonotonicSequenceForThrottle() );
		rule.setGroup(arg);
		rule.setStormLevel(0);
		rule.setDuration(0);
		rule.setThreshold(0);
		rule.setAction(ThrottleAction.PASS_THRU);

		list.add(_orm.addOrUpdateThrottleRule(rule));

		rule = new ThrottleRule();
        rule.setRuleId ( _orm.nextMonotonicSequenceForThrottle() );
		rule.setGroup(arg);
		rule.setStormLevel(1);
		rule.setDuration(30);
		rule.setThreshold(100);
		rule.setAction(ThrottleAction.SPOOL);

		list.add(_orm.addOrUpdateThrottleRule(rule));

		rule = new ThrottleRule();
        rule.setRuleId ( _orm.nextMonotonicSequenceForThrottle() );
		rule.setGroup(arg);
		rule.setStormLevel(2);
		rule.setDuration(30);
		rule.setThreshold(300);
		rule.setAction(ThrottleAction.DISCARD);

		list.add(_orm.addOrUpdateThrottleRule(rule));

		return(list);
	}

	/**
	 * Write a "storm in progress" message to dispatcher
	 * 
	 * @param arg storm details
	 */
	public void stormInProgress(Storm arg, JmsFacade dispatcher) {
		StormMessage sm = new StormMessage();
		
		sm.setAction(arg.getAction());
		sm.setGroup(arg.getGroup());
		sm.setSpoolKey(arg.getSpoolKey());
		sm.setStormLevel(arg.getStormLevel());

        LogToFile ( "C:\\test\\JMS-StormCalc.out", "Writing message to queue: " + dispatcher.getQueueName() );
        LogToFile ( "C:\\test\\JMS-StormCalc.out", "Message is of type: "       + sm.getClass().getName() );
		dispatcher.queueWriter(sm);
	}

    private final void LogToFile ( String filename, String message )
    {
        if ( _log.isDebugEnabled() )
        {
            try
            {
                FileOutputStream outfile = new FileOutputStream ( filename, true );
                    outfile.write ( (new java.util.Date()).toString().getBytes() );
                    outfile.write ( " - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
            catch ( IOException exception )
            {
                _log.info ( "Could not log to filename: " + filename );
            }
        }
    }

	/**
	 * Handle to ORM dispatcher
	 */
	private final OrmFacade _orm = new OrmFacade();
	
	/**
	 * 
	 */
	private Calendar _time_now;

	/**
	 * Fresh message every 3 minutes 
	 */
	private static final long NAG_PERIOD = 3 * 60 * 1000L;
	
	/**
	 * Define logger
	 */
	private Logger _log;
}
