package com.bgi.esm.monitoring.platform.value;

/**
 * Value object for throttle rule. Throttle rules come from a configuration file
 * and specify actions for for varying levels of event activity.
 * 
 * Group/StormLevel are "keys" and must be unique.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class ThrottleRule extends AbstractMessage {

	/**
	 * group, i.e. "accounts-barclaysglobal"
	 */
	private String _group;

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 */
	public void setGroup(String arg) {
		_group = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getGroup() {
		return(_group);
	}

	/**
	 * Storm level/key. Must be unique
	 */
	private int _storm_level;

	/**
	 * Define storm level/key. Must be unique across rule set.
	 * 
	 * @param arg storm level
	 */
	public void setStormLevel(int arg) {
		_storm_level = arg;
	}

	/**
	 * Define storm level/key. Must be unique across rule set
	 * 
	 * @param arg storm level
	 */
	public void setStormLevel(String arg) {
		_storm_level = Integer.parseInt(arg);
	}

	/**
	 * Return storm level
	 * 
	 * @return storm level
	 */
	public int getStormLevel() {
		return(_storm_level);
	}

	/**
	 * Rule duration in seconds
	 */
	private long _duration;

	/**
	 * Define rule bin duration in seconds
	 * 
	 * @param arg duration in seconds
	 */
	public void setDuration(long arg) {
		_duration = arg;
	}

	/**
	 * Define rule bin duration in seconds
	 * 
	 * @param arg duration in seconds.
	 */
	public void setDuration(String arg) {
		_duration = Long.parseLong(arg);
	}

	/**
	 * Return rule bin duration in seconds
	 * 
	 * @return rule bin duration in seconds
	 */
	public long getDuration() {
		return(_duration);
	}

	/**
	 * Return rule bin duration in milliseconds
	 * 
	 * @return rule bin duration in milliseconds
	 */
	public long getDurationInMilliSeconds() {
		return(_duration * 1000L);
	}

	/**
	 * Rule population threshold
	 */
	private int _threshold;

	/**
	 * Define rule population threshold
	 * 
	 * @param arg rule population threshold
	 */
	public void setThreshold(int arg) {
		_threshold = arg;
	}

	/**
	 * Define rule population threshold
	 * 
	 * @param arg rule population threshold
	 */
	public void setThreshold(String arg) {
		_threshold = Integer.parseInt(arg);
	}

	/**
	 * Return rule population threshold
	 * 
	 * @return rule population threshold
	 */
	public int getThreshold() {
		return(_threshold);
	}

	/**
	 * Rule action. No JDK 1.5 enums for me, thank you.
	 */
	private ThrottleAction _action;

	/**
	 * Define rule action
	 * 
	 * @param arg rule action
	 */
	public void setAction(ThrottleAction arg) {
		_action = arg;
	}

	/**
	 * Define rule action
	 * 
	 * @param arg rule action
	 */
	public void setAction(String arg) {
		_action = ThrottleAction.getInstance(arg);
	}

	/**
	 * Return rule action
	 * 
	 * @return rule action
	 */
	public ThrottleAction getAction() {
		return(_action);
	}

	/**
	 * Return hash code value for this object based on group and level.
	 * 
	 * @return hash code value for this object based on group and level.
	 */
	public int hashCode() {
		int result = _group.hashCode() * _storm_level;

		return(result);
	}

	/**
	 * Return true if the Rules match. Compares all fields.
	 * 
	 * @param arg candidate to match
	 * @return true if successful match
	 */
	public boolean equals(ThrottleRule arg) {
		if (!_group.equals(arg.getGroup())) {
			return(false);
		}

		if (_storm_level != arg.getStormLevel()) {
			return(false);
		}

		return(true);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return("rule:" + _group + ":" + _storm_level + ":" + _duration + ":" + _threshold + ":" + _action);
	}

	/**
	 * Return ThrottleRule as a XML formatted String
	 * 
	 * @return ThrottleRule as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<ThrottleRule>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <group>" + _group + "</group>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <level>" + _storm_level + "</level>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <duration>" + _duration + "</duration>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <threshold>" + _threshold + "</threshold>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <action>" + _action + "</action>");
		sb.append(LINE_SEPARATOR);

		sb.append("</ThrottleRule>");
		sb.append(LINE_SEPARATOR);

		return(sb.toString());
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */