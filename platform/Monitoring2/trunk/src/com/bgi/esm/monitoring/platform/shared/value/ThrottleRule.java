package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

/**
 * Value object for throttle rule.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class ThrottleRule implements Comparable<ThrottleRule>, Serializable {

	/**
	 * empty ctor
	 */
	public ThrottleRule() {
		// empty
	}

	/**
	 * ctor w/defined row key
	 * 
	 * @param arg row key
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public ThrottleRule(String arg) {
		if (arg == null) {
			throw new NullPointerException("null rule key");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty rule key");
		}

		_key = arg;
	}

	/**
	 * rule/row key generated by sequence
	 */
	private String _key = DEFAULT_KEY;

	/**
	 * Return rule key
	 * 
	 * @return rule key
	 */
	public String getRuleKey() {
		return(_key);
	}

	/**
	 * group, i.e. "accounts-barclaysglobal"
	 */
	private String _group = "group";

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setGroup(String arg) {
		if (arg == null) {
			throw new NullPointerException("null group");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty group");
		}

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
	 * Associated storm level
	 */
	private int _storm_level;

	/**
	 * Define storm level
	 * 
	 * @param arg storm level, must be non negative
	 * @throws IllegalArgumentException if negative value
	 */
	public void setStormLevel(int arg) {
		if (arg < 0) {
			throw new IllegalArgumentException("negative storm level");
		}
		
		_storm_level = arg;
	}

	/**
	 * Define storm level
	 * 
	 * @param arg storm level
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg or negative value
	 * @throws NumberFormatException if bad value
	 */
	public void setStormLevel(String arg) {
		if (arg == null) {
			throw new NullPointerException("null storm level");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty storm level");
		}

		_storm_level = Integer.parseInt(arg);
		
		if (_storm_level < 0) {
			throw new IllegalArgumentException("negative storm level");
		}
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
	 * duration in seconds
	 */
	private long _duration;

	/**
	 * Define bin duration in seconds
	 * 
	 * @param arg bin duration in seconds
	 * @throws IllegalArgumentException if negative arg
	 */
	public void setDuration(long arg) {
		if (arg < 0) {
			throw new IllegalArgumentException("negative duration");
		}

		_duration = arg;
	}

	/**
	 * Define bin duration in seconds
	 * 
	 * @param arg bin duration in seconds
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg or negative value
	 * @throws NumberFormatException if bad value
	 */
	public void setDuration(String arg) {
		if (arg == null) {
			throw new NullPointerException("null duration");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty duration");
		}

		_duration = Long.parseLong(arg);

		if (_duration < 0) {
			throw new IllegalArgumentException("negative duration");
		}
	}

	/**
	 * Return bin duration in seconds
	 * 
	 * @return bin duration in seconds
	 */
	public long getDuration() {
		return(_duration);
	}

	/**
	 * Return bin duration in milliseconds
	 * 
	 * @return bin duration in milliseconds
	 */
	public long getDurationInMillis() {
		return(_duration * 1000L);
	}

	/**
	 * Define threshold population (message count)
	 */
	private int _threshold;

	/**
	 * Define threshold population (message count)
	 * 
	 * @param arg threshold population
	 * @throws IllegalArgumentException if negative arg
	 */
	public void setThreshold(int arg) {
		if (arg < 0) {
			throw new IllegalArgumentException("negative threshold");
		}

		_threshold = arg;
	}

	/**
	 * Define threshold population (message count)
	 * 
	 * @param arg threshold population
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg or negative value
	 * @throws NumberFormatException if bad value
	 */
	public void setThreshold(String arg) {
		if (arg == null) {
			throw new NullPointerException("null threshold");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty threshold");
		}

		_threshold = Integer.parseInt(arg);

		if (_threshold < 0) {
			throw new IllegalArgumentException("negative threshold");
		}
	}

	/**
	 * Return threshold population
	 * 
	 * @return threshold population
	 */
	public int getThreshold() {
		return(_threshold);
	}

	/**
	 * Associated action
	 */
	private ThrottleAction _action = ThrottleAction.PASS_THRU;

	/**
	 * Define associated throttle action
	 * 
	 * @param arg associated throttle action
	 * @throws NullPointerException if null arg
	 */
	public void setAction(ThrottleAction arg) {
		if (arg == null) {
			throw new NullPointerException("null action");
		}
		
		_action = arg;
	}

	/**
	 * Define associated throttle action
	 * 
	 * @param arg throttle action
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg or bad value
	 */
	public void setAction(String arg) {
		if (arg == null) {
			throw new NullPointerException("null action");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty action");
		}

		_action = ThrottleAction.getInstance(arg);

		if (_action == null) {
			throw new IllegalArgumentException("bad action:" + arg);
		}
	}
	
	/**
	 * Return associated throttle action
	 * 
	 * @return associated throttle action
	 */
	public ThrottleAction getAction() {
		return(_action);
	}
	
	/**
	 * True, enable "in progress" storm messages
	 */
	private boolean _enable_storm_messages = true;
	
	/**
	 * Define if "in progress" storm messages should be dispatched.
	 * 
	 * @param arg true, enable "in progress" storm messages
	 */
	public void setEnableStormMessages(boolean arg) {
		_enable_storm_messages = arg;
	}

	/**
	 * Return true, "in progress" storm messages should be dispatched
	 * 
	 * @return true, "in progress" storm messages should be dispatched
	 */
	public boolean isEnableStormMessages() {
		return(_enable_storm_messages);
	}


	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
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
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}

		ThrottleRule temp = (ThrottleRule) arg;

		if (!_group.equals(temp.getGroup())) {
			return(false);
		}

		if (_storm_level != temp.getStormLevel()) {
			return(false);
		}
		
		if (!_action.equals(temp.getAction())) {
			return(false);
		}
		
		if (_duration != temp.getDuration()) {
			return(false);
		}
		
		if (_threshold != temp.getThreshold()) {
			return(false);
		}

		return(true);
	}

	/**
	 * Support for Comparable.compareTo
	 * 
	 * Sort by storm level
	 * 
	 * @param arg item to compare
	 * @return -1 if less, 1 if greater or zero if equal
	 * @throws NullPointerException if null arg
	 */
	public int compareTo(ThrottleRule arg) {
	//public int compareTo(Object arg) {
		if (arg == null) {
			throw new NullPointerException("null arg");
		}
		
		if (arg.getStormLevel() > _storm_level) {
			return(-1);
		} else if (arg.getStormLevel() < _storm_level) {
			return(1);
		}

		return(0);
	}

    private int RuleID = -1;

    public void setRuleId ( int rule_id ) {
        RuleID = rule_id;
    }

    public void setRuleId ( String arg ) {
		if (arg == null) {
			throw new NullPointerException("null storm level");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty storm level");
		}

		RuleID = Integer.parseInt(arg);
		
		if (_storm_level < 0) {
			throw new IllegalArgumentException("negative storm level");
		}
    }

    public int getRuleId() {
        return RuleID;
    }

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return ("rule:" + _group + ":" + _storm_level + ":" + _duration + ":" + _threshold + ":" + _action);
	}

	/**
	 * Serial version identifier. Be sure to update this when the class is
	 * updated.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";
}
