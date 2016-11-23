package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Define legal throttle actions. Treat as enumerated type.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class ThrottleAction implements Serializable {

	/**
	 * 
	 */
	public static final ThrottleAction PASS_THRU = new ThrottleAction("PASS_THRU");

	/**
	 * 
	 */
	public static final ThrottleAction SPOOL = new ThrottleAction("SPOOL");

	/**
	 * 
	 */
	public static final ThrottleAction DISCARD = new ThrottleAction("DISCARD");

	/**
	 * Return a string representing the state (value) of this instance.
	 * 
	 * @return a string representing the state (value) of this instance.
	 */
	public String toString() {
		return(_value);
	}

	/**
	 * Return the type corresponding to arg, or null if not found.
	 * 
	 * @param arg type (key) to search for.
	 * @return the type corresponding to arg, or null if not found.
	 */
	public static ThrottleAction getInstance(String arg) {
		return((ThrottleAction) _instances.get(arg));
	}

	/**
	 * Return all instances
	 * 
	 * @return all instances
	 */
	public static Map getAll() {
		return(_instances);
	}

	/**
	 * Private ctor
	 * 
	 * @param arg throttle_action value
	 */
	private ThrottleAction(String arg) {
		_value = arg;
	}

	/**
	 * Return hash code for this object.
	 * 
	 * @return hash code for this object
	 */
	public int hashCode() {
		return(31 * _value.hashCode());
	}

	/**
	 * Return true if objects match
	 * 
	 * @param arg test candidate
	 * @return true if periods match
	 * @throws ClassCastException if arg cannot be cast
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}

		ThrottleAction temp = (ThrottleAction) arg;
		return(_value.equals(temp._value));
	}

	/**
	 * ThrottleAction value.
	 */
	private final String _value;

	/**
	 * Map of instances, employed to look up types.
	 */
	private static final Map<String, ThrottleAction> _instances = new HashMap<String, ThrottleAction>();

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	static {
		_instances.put(PASS_THRU.toString(), PASS_THRU);
		_instances.put(SPOOL.toString(), SPOOL);
		_instances.put(DISCARD.toString(), DISCARD);
	}
}
