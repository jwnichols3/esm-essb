package com.bgi.esm.monitoring.platform.value;

/**
 * Simple container for key/value pairs. Employed by XmlParser.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class KeyValue {

	/**
	 * key
	 */
	private String _key;

	/**
	 * Return key
	 * 
	 * @return key
	 */
	public String getKey() {
		return(_key);
	}

	/**
	 * Define key
	 * 
	 * @param arg key
	 */
	public void setKey(String arg) {
		_key = arg;
	}

	/**
	 * value
	 */
	private String _value;

	/**
	 * Return value
	 * 
	 * @return value
	 */
	public String getValue() {
		return (_value);
	}

	/**
	 * Define value
	 * 
	 * @param arg value
	 */
	public void setValue(String arg) {
		_value = arg;
	}

	/**
	 * State as a string
	 */
	public String toString() {
		return ("key:" + _key + ":value:" + _value);
	}
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
