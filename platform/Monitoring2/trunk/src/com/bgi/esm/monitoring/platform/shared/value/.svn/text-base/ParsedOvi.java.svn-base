package com.bgi.esm.monitoring.platform.shared.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import com.bgi.esm.monitoring.platform.shared.value.KeyValue;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

/**
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class ParsedOvi implements XmlIf {

	/**
	 * Unique message ID, assigned by OVO manager
	 */
	private String _message_id = "bogus";

	/**
	 * Return message ID
	 * 
	 * @return message ID
	 */
	public String getMessageId() {
		return(_message_id);
	}

	/**
	 * Define message ID (assigned by OVO manager)
	 * 
	 * @param arg message ID
	 * @throws NullPointerException if null arg
	 */
	public void setMessageId(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message id");
		}

		_message_id = arg;
	}

	private String _raw_time;

	public String getTimeStamp() {
		return(_raw_time);
	}

	public void setTimeStamp(String arg) {
		_raw_time = arg;
	}

	private String _severity;

	public String getSeverity() {
		return(_severity);
	}

	public void setSeverity(String arg) {
		_severity = arg;
	}

	/**
	 * Source node (hostname)
	 */
	private String _source_node;

	/**
	 * Return source node (hostname)
	 * 
	 * @return source node (hostname)
	 */
	public String getMessageSource() {
		return(_source_node);
	}

	/**
	 * Define source node (hostname)
	 * 
	 * @param arg source node (hostname)
	 * @throws NullPointerException if null arg
	 */
	public void setMessageSource(String arg) {
		if (arg == null) {
			throw new NullPointerException("null source node");
		}

		_source_node = arg;
	}

	/**
	 * Application name
	 */
	private String _application;

	/**
	 * Return application name
	 * 
	 * @return application name
	 */
	public String getEventSource() {
		return(_application);
	}

	/**
	 * Define application name
	 * 
	 * @param arg application name
	 * @throws NullPointerException if null arg
	 */
	public void setEventSource(String arg) {
		if (arg == null) {
			throw new NullPointerException("null application name");
		}

		_application = arg;
	}

	private String _state;

	public String getState() {
		return(_state);
	}

	public void setState(String arg) {
		_state = arg;
	}

	/**
	 * 
	 */
	private int _duplicates;

	/**
	 * Return suppressed message population
	 * 
	 * @return suppressed message population
	 */
	public int getDuplicateCount() {
		return(_duplicates);
	}

	/**
	 * Define suppressed message count
	 * 
	 * @param arg suppressed message population
	 */
	public void setDuplicateCount(int arg) {
		_duplicates = arg;
	}

	/**
	 * Define suppressed message count
	 * 
	 * @param arg suppressed message population
	 * @throws NullPointerException if null arg
	 */
	public void setDuplicateCount(String arg) {
		if (arg == null) {
			throw new NullPointerException("null duplicate count");
		}

		_duplicates = Integer.parseInt(arg);
	}

	private ArrayList<KeyValue> _key_value = new ArrayList<KeyValue>();

	/**
	 * Define message attribute
	 * 
	 * @param arg single message attribute for Map insertion
	 * @throws NullPointerException if null arg
	 */
	public void addKeyValue(KeyValue arg) {
		if (arg == null) {
			throw new NullPointerException("null message attributes/keyval");
		}

		_key_value.add(arg);
	}

	/**
	 * Inspect key value list for an item
	 * 
	 * @param arg key
	 * @return value or null if not found
	 */
	private String finder(String arg) {
		for (int ii = 0; ii < _key_value.size(); ii++) {
			KeyValue temp = (KeyValue) _key_value.get(ii);
			if (temp.getKey().equals(arg)) {
				return(temp.getValue());
			}
		}

		return(null);
	}

    private String _change_event_type = null;

    public void setChangeEventType ( String change_event_type )
    {
        _change_event_type = change_event_type;
    }

    public String getChangeEventType()
    {
        return _change_event_type;
    }

	public TicketMessage getTicketMessage() {
		TicketMessage result = new TicketMessage();

		result.setMessageId(_message_id);
		result.setSourceNode(_source_node);
		result.setSeverity(_severity);
		result.setSuppressCount(_duplicates);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String temp = finder("receiveTimeSeconds");
		if (temp != null) {
			calendar.setTimeInMillis(Long.parseLong(_raw_time) * 1000L);
		} else {
			// _log.error("bad time");
		}
		result.setTimeStamp(calendar);

		result.setApplication  ( finder ( "application" ) );
		result.setMessageGroup ( finder ( "group" )       );
		result.setObject       ( finder ( "object" )      );
		result.setMessageText  ( finder ( "text" )        );

		return(result);
	}

	/**
	 * Return OVI Message as a String
	 * 
	 * @return OVI Message as a String
	 */
	public String toString() {
		return("OVI:" + _message_id + ":");
	}
}
