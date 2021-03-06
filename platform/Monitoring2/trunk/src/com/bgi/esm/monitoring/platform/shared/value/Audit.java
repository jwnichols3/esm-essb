package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for event mine AUDIT table.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class Audit implements Serializable {

	/**
	 * empty ctor
	 */
	public Audit() {
		// empty
	}
	
	/**
	 * ctor w/defined row key
	 *
	 * @param arg row key
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public Audit(String arg) {
		if (arg == null) {
			throw new NullPointerException("null row key");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty row key");
		}
		
		_key = arg;
	}

	/**
	 * Create from AuditMessage
	 * 
	 * @param arg seed values
	 */
	public Audit(AuditMessage arg) {
		if (arg == null) {
			throw new NullPointerException("null audit message");
		}

		_module = arg.getModule();
		_action = arg.getAction();
		_message = arg.getMessageId();
	}

	/**
	 * Create an AuditMessage populated from Audit table row
	 * 
	 * @return audit message
	 */
	public AuditMessage getAuditMessage() {
		AuditMessage result = new AuditMessage();

		result.setModule(_module);
		result.setAction(_action);
		result.setMessageId(_message);

		return(result);
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
	 * Time stamp, initialized to creation time.
	 */
	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Time of audit event
	 * 
	 * @return audit event time
	 */
	public Calendar getTimeStamp() {
		return(_time_stamp);
	}

	/**
	 * Time of audit event
	 * 
	 * @param arg audit event time
	 * @throws NullPointerException if null arg
	 */
	public void setTimeStamp(Calendar arg) {
		if (arg == null) {
			throw new NullPointerException("null timestamp");
		}
		
		_time_stamp = arg;
	}

	/**
	 * Buss module which is reporting event (i.e. suppression, data map, throttle, etc)
	 */
	private BussModule _module = BussModule.UNKNOWN;

	/**
	 * Return reporting BussModule
	 * 
	 * @return reporting BussModule
	 */
	public BussModule getModule() {
		return(_module);
	}

	/**
	 * Define reporting BussModule
	 * 
	 * @param arg reporting BussModule
	 * @throws NullPointerException if null arg
	 */
	public void setModule(BussModule arg) {
		if (arg == null) {
			throw new NullPointerException("null module");
		}
		
		_module = arg;
	}

	/**
	 * audit action, essentially free form string
	 */
	private String _action = "unknown";

	/**
	 * Return audit action
	 * 
	 * @return audit action
	 */
	public String getAction() {
		return(_action);
	}

	/**
	 * Define audit action
	 * 
	 * @param arg audit action
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setAction(String arg) {
		if (arg == null) {
			throw new NullPointerException("null action");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty action");
		}
		
		_action = arg;
	}

	/**
	 * message identifier, taken from HP OVO message
	 */
	private String _message = "unknown";

	/**
	 * Return HP OVO message ID
	 * 
	 * @return HP OVO message ID
	 */
	public String getMessageId() {
		return(_message);
	}

	/**
	 * Define HP OVO message ID
	 * 
	 * @param arg HP OVO message ID
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setMessageId(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message ID");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty message ID");
		}
		
		_message = arg;
	}
	
	/**
	 * Hash code value for this instance, derived from key
	 * 
	 * @return hash code value for this instance
	 */
	public int hashCode() {
		return(_key.hashCode());
	}
	
	/**
	 * Return true if arg equals this instance.  Only tests key.
	 * 
	 * @throws ClassCastException if arg cannot be cast
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}
		
		Audit temp = (Audit) arg;
		if (temp._key.equals(_key)) {
			return(true);
		}
		
		return(false);
	}
	
	/**
	 * Return object state as a string
	 * 
	 * @return object state as a string
	 */
	public String toString() {
		return("Audit:" + _key + ":" + _module + ":" + _action + ":" + _message);
	}
	
	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
	
	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";
}
