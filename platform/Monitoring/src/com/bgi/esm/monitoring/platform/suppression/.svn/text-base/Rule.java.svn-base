package com.bgi.esm.monitoring.platform.suppression;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for suppression rule.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Rule implements Comparable {

	/**
	 * Accept a raw Rule string in the form "start time", "end time",
	 * "application name", "node name", "db server" and "message text"
	 * 
	 * @param arg raw line from extract file delimited by "=-="
	 * 
	 * @throws IllegalArgumentException if bad argument population
	 */
	public Rule(String arg) {
		String[] argz = arg.split("=-=");

		if (argz.length != 6) {
			throw new IllegalArgumentException("bad argument population");
		}

		long temp = 0L;

		temp = Long.parseLong(argz[0].trim());
		_start_time.setTimeInMillis(temp * 1000);

		temp = Long.parseLong(argz[1].trim());
		_end_time.setTimeInMillis(temp * 1000);

		_application_name = argz[2].trim();

		_node_name = argz[3].trim();

		_db_server = argz[4].trim();

		_message = argz[5].trim();
	}

	/**
	 * Return suppression start time
	 * 
	 * @return suppression start time
	 */
	public Calendar getStartTime() {
		return(_start_time);
	}

	/**
	 * Return suppression end time
	 * 
	 * @return suppression end time
	 */
	public Calendar getEndTime() {
		return(_end_time);
	}

	/**
	 * Return application name
	 * 
	 * @return application name
	 */
	public String getApplicationName() {
		return(_application_name);
	}

	/**
	 * Return node name
	 * 
	 * @return node name
	 */
	public String getNodeName() {
		return(_node_name);
	}

	/**
	 * Return database server name
	 * 
	 * @return database server name
	 */
	public String getDatabaseServerName() {
		return(_db_server);
	}

	/**
	 * Return suppression message
	 * 
	 * @return suppression message
	 */
	public String getMessage() {
		return(_message);
	}

	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
	 */
	public int hashCode() {
		int result = _start_time.hashCode() * _end_time.hashCode();

		result *= _application_name.hashCode() * _node_name.hashCode();

		result *= _db_server.hashCode() * _message.hashCode();

		return(result);
	}

	/**
	 * Return true if the Rules match. Compares all fields.
	 * 
	 * @param arg candidate to match
	 * @return true if successful match
	 */
	public boolean equals(Rule arg) {
		if (!_start_time.equals(arg.getStartTime())) {
			return(false);
		}

		if (!_end_time.equals(arg.getEndTime())) {
			return(false);
		}

		if (!_application_name.equals(arg.getApplicationName())) {
			return(false);
		}

		if (!_node_name.equals(arg.getNodeName())) {
			return(false);
		}

		if (!_db_server.equals(arg.getDatabaseServerName())) {
			return(false);
		}

		if (!_message.equals(arg.getMessage())) {
			return(false);
		}

		return(true);
	}

	/**
	 * Return true if current time is within rule boundaries
	 * 
	 * @return true if current time is within rule boundaries
	 */
	public boolean isActive() {
		if (isExpired()) {
			return(false);
		}

		return(isStarted());
	}

	/**
	 * Returns true if the end time has already passed.
	 * 
	 * @return true if the end time has already passed.
	 */
	public boolean isExpired() {
		return(_end_time.before(timeNow()));
	}

	/**
	 * Returns true if the start time has already passed.
	 * 
	 * @return true if the start time has already passed.
	 */
	public boolean isStarted() {
		return(_start_time.before(timeNow()));
	}

	/**
	 * Compare this object w/the specified object.
	 * 
	 * @return -1 (less than), 0 (equals), 1 (greater than)
	 * 
	 * @throws ClassCastException if arg cannot be cast
	 * @throws NullPointerException if null arg
	 */
	public int compareTo(Object arg) {
		Rule temp = (Rule) arg;

		if (_start_time.after(temp.getStartTime())) {
			return(1);
		}

		if (_start_time.before(temp.getStartTime())) {
			return(-1);
		}

		return(0);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		String temp1 = _sdf.format(_start_time.getTime());
		String temp2 = _sdf.format(_end_time.getTime());

		return(temp1 + " " + temp2 + " " + _application_name + " " + _node_name + " " + _db_server + " " + _message);
	}

	/**
	 * Return current time in GMT.
	 */
	private Calendar timeNow() {
		return(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
	}

	/**
	 * 
	 */
	private Calendar _start_time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * 
	 */
	private Calendar _end_time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * 
	 */
	private String _application_name;

	/**
	 * 
	 */
	private String _node_name;

	/**
	 * 
	 */
	private String _db_server;

	/**
	 * 
	 */
	private String _message;

	/**
	 * format employed by toString() method, MM/DD/YYYY HH:mm:ss
	 */
	private static SimpleDateFormat _sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */