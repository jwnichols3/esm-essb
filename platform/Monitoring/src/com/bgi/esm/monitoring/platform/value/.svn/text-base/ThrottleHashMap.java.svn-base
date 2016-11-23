package com.bgi.esm.monitoring.platform.value;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for Throttle rules. 
 * Employed by Throttle buss module during rule parsing.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class ThrottleHashMap implements XmlIf {

	/**
	 * Add fresh rule
	 * 
	 * @param arg fresh rule
	 * @throws NullPointerException if null arg
	 */
	public void addRule(ThrottleRule arg) {
		if (arg == null) {
			throw new NullPointerException("null rule");
		}

		String key = arg.getGroup() + "." + Integer.toString(arg.getStormLevel());

		_hm.put(key, arg);
	}

	/**
	 * Return entire rule collection.
	 * 
	 * @return entire rule collection.
	 */
	public Map getMap() {
		return(_hm);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return("throttle map size:" + _hm.size());
	}

	/**
	 * Return ThrottleHashMap as a XML formatted String
	 * 
	 * @return ThrottleHashMap as a XML formatted String
	 */
	public String toXml() {
		return("throttle hash map xml");
	}

	/**
	 * Results
	 */
	private final HashMap _hm = new HashMap();
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
