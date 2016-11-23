package com.bgi.esm.monitoring.platform.shared.value;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for census item, used to calculate storm levels. 
 * This class is for application internal use only, it is not 
 * shared w/RMI clients.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class Census implements Comparable<Census> {

	/**
	 * empty ctor
	 */
	public Census() {
		//empty
	}

	private String _key;

	public String getRowKey() {
		return (_key);
	}

	public void setRowKey(String arg) {
		_key = arg;
	}

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

	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 */
	public Calendar getTimeStamp() {
		return(_time_stamp);
	}

	public void setTimeStamp(Calendar arg) {
		_time_stamp = arg;
	}

	/**
	 * Return hash code value for this object, based on key.
	 * 
	 * @return hash code value for this object, based on key.
	 */
	public int hashCode() {
		return(_key.hashCode());
	}

	/**
	 * Return true if the key matches
	 * 
	 * @param arg if key matches
	 * @return true if successful match
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}

		Census temp = (Census) arg;

		return(temp.getRowKey().equals(_key));
	}

	/**
	 * Support for Comparable.compareTo
	 * 
	 * @param arg item to compare
	 * @return -1 if less, 1 if greater or zero if equal
	 * @throws NullPointerException if null arg
	 */
	public int compareTo(Census arg) {
		if (arg == null) {
			throw new NullPointerException("null arg");
		}

		return(_time_stamp.compareTo(arg.getTimeStamp()));
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String temp1 = sdf.format(_time_stamp.getTime());

		return("census:" + _group + ":" + temp1);
	}

	/**
	 * format employed by toString() method, MM/DD/YYYY HH:mm:ss
	 */
//
}
