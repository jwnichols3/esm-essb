package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for SPOOL table
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Spool implements Serializable {

	/**
	 * empty ctor
	 */
	public Spool() {
		// empty
	}

	/**
	 * ctor w/defined row key
	 * 
	 * @param arg row key
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public Spool(String arg) {
		if (arg == null) {
			throw new NullPointerException("null row key");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty row key");
		}

		_key = arg;
	}

	/**
	 * row key, generated by sequence
	 */
	private String _key = DEFAULT_KEY;

	/**
	 * Return row key
	 * 
	 * @return row key
	 */
	public String getRowKey() {
		return(_key);
	}

	/**
	 * Row time stamp
	 */
	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Return spool time
	 * 
	 * @return spool time
	 */
	public Calendar getTimeStamp() {
		return(_time_stamp);
	}

	/**
	 * Define spool time
	 * 
	 * @param arg spool time
	 * @throws NullPointerException if null arg
	 */
	public void setTimeStamp(Calendar arg) {
		if (arg == null) {
			throw new NullPointerException("null spool time");
		}

		_time_stamp = arg;
	}

	/**
	 * message group, i.e. "accounts-barclaysglobal"
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
			throw new NullPointerException("null group name");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty group name");
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
	 * Key for this spool session
	 */
	private String _spool_key = "bogus";

	/**
	 * Define spool key
	 * 
	 * @param arg spool key
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setSpoolKey(String arg) {
		if (arg == null) {
			throw new NullPointerException("null spool key");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty spool key");
		}

		_spool_key = arg;
	}

	/**
	 * Return spool key
	 * 
	 * @return spool key
	 */
	public String getSpoolKey() {		
		return(_spool_key);
	}

	/**
	 * OVO message key
	 */
	private String _ovo_key = "bogus";

	/**
	 * Define OVO key
	 * 
	 * @param arg spool key
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setOvoKey(String arg) {
		if (arg == null) {
			throw new NullPointerException("null spool key");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty spool key");
		}

		_ovo_key = arg;
	}

	/**
	 * Return OVO key
	 * 
	 * @return OVO key
	 */
	public String getOvoKey() {
		return(_ovo_key);
	}

	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
	 */
	public int hashCode() {
		return(_key.hashCode());
	}

	/**
	 * Return true if the Spool keys match.
	 * 
	 * @param arg candidate to match
	 * @return true if successful match
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}

		Spool temp = (Spool) arg;

		return(temp.getRowKey().equals(_key));
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return(_key + ":" + _group);
	}

	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
