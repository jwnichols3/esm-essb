package com.bgi.esm.monitoring.platform.value;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for DataMap rules. 
 * Employed by DataMap buss module during rule parsing.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataHashMap implements XmlIf {

	/**
	 * Add fresh rule
	 * 
	 * @param arg fresh rule
	 * @throws NullPointerException if null arg
	 */
	public void addRule(DataMapRule arg) {
		if (arg == null) {
			throw new NullPointerException("null rule");
		}

		_hm.put(arg.getGroup(), arg);
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
		return("data hash map size:" + _hm.size());
	}

	/**
	 * Return DataMapRule as a XML formatted String
	 * 
	 * @return DataMapRule as a XML formatted String
	 */
	public String toXml() {
		return("data hash map xml");
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