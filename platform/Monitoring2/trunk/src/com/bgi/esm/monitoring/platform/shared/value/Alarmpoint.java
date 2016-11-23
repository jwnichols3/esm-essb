package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for census item, used to calculate storm levels. 
 * This class is for application internal use only, it is not 
 * shared w/RMI clients.
 * 
 * @author Dennis Lin (linden)
 */
public final class Alarmpoint implements Comparable, Serializable {

	/**
	 * empty ctor
	 */
	public Alarmpoint() {
		//empty
	}

	public Alarmpoint(String arg) {
		if (arg == null) {
			throw new NullPointerException("null row key");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty row key");
		}
		
		_key = arg;
	}


	private String _key = DEFAULT_KEY;

	public String getRowKey() {
		return (_key);
	}

	public void setRowKey(String arg) {
		_key = arg;
	}

	/**
	 * Alarmpoint event ID
	 */
	private String _eventId = "default-event-id";

	/**
	 * Define message group
	 * 
	 * @param arg Alarmpoint event ID
	 */
	public void setEventId(String arg) {
		_eventId = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getEventId() {
		return(_eventId);
	}

    /**
     *  Openview Interconnect message ID
     */
    private String _messageId = null;

    /**
     *
     * @param arg Openview messageID
     */
    public void setMessageId ( String arg ) {
        if (( null != arg ) && ( false == arg.equals ( "null" ))) {
            _messageId = arg;
        } else {
            _messageId ="";
        }
    }

    /**
     * @return Openview message ID
     */
    public String getMessageId() {
        return _messageId;
    }

	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 */
	public Calendar getTimestamp() {
		return(_time_stamp);
	}

	public void setTimestamp(Calendar arg) {
		_time_stamp = arg;
	}

    /**
     *  The Alarmpoint notification message
     */
    private String _ap_message = "";

    public String getAlarmpointMessage()
    {
        return _ap_message;
    }

    public void setAlarmpointMessage ( String message )
    {
        _ap_message = message;
    }

    /**
     *  The device that Alarmpoint is notifying about
     */
    private String _ap_device = "";

    public String getAlarmpointDevice()
    {
        return _ap_device;
    }

    public void setAlarmpointDevice ( String device )
    {
        _ap_device = device;
    }


    /**
     *  The Alarmpoint notification target
     */
    private String _ap_notification_target = "";
    public String getAlarmpointNotificationTarget()
    {
        return _ap_notification_target;
    }

    public void setAlarmpointNotificationTarget ( String notification_target )
    {
        _ap_notification_target = notification_target;
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

		Alarmpoint temp = (Alarmpoint) arg;

		return(temp.getRowKey().equals(_key));
	}

	/**
	 * Support for Comparable.compareTo
	 * 
	 * @param arg item to compare
	 * @return -1 if less, 1 if greater or zero if equal
	 * @throws NullPointerException if null arg
	 */
	public int compareTo(Object arg) {
		if (arg == null) {
			throw new NullPointerException("null arg");
		}

		Alarmpoint temp = (Alarmpoint) arg;

		return(_time_stamp.compareTo(temp.getTimestamp()));
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

		return("EventID:" + _eventId + ", Timestamp=:" + temp1 + ", OVI Message ID=" + _messageId );
	}

	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";
}
