package com.bgi.esm.monitoring.platform.value;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Value object representing a HPOV ticket.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class TicketMessage extends AbstractMessage {

	/**
	 * Unique message ID, assigned by OVO manager
	 */
	private String _message_id;

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

		if (arg.length() > 0) {
			_message_id = arg;
		}
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
	public String getSourceNode() {
		return(_source_node);
	}

	/**
	 * Define source node (hostname)
	 * 
	 * @param arg source node (hostname)
	 * @throws NullPointerException if null arg
	 */
	public void setSourceNode(String arg) {
		if (arg == null) {
			throw new NullPointerException("null source node");
		}

		if (arg.length() > 0) {
			_source_node = arg;
		}
	}

	/**
	 * Source node type (i.e. Sun SPARC)
	 */
	private String _source_node_type;

	/**
	 * Return source node type (i.e. Sun SPARC)
	 * 
	 * @return source node type
	 */
	public String getSourceNodeType() {
		return(_source_node_type);
	}

	/**
	 * Define source node type (i.e. Sun SPARC)
	 * 
	 * @param arg source node type
	 * @throws NullPointerException if null arg
	 */
	public void setSourceNodeType(String arg) {
		if (arg == null) {
			throw new NullPointerException("null source node type");
		}

		if (arg.length() > 0) {
			_source_node_type = arg;
		}
	}

	/**
	 * Dete of alert creation, assigned by agent.
	 */
	private DateOnly _creation_date;

	/**
	 * Return alert creation date as assigned by agent
	 * 
	 * @return alert creation date as assigned by agent
	 */
	public DateOnly getCreationDate() {
		return(_creation_date);
	}

	/**
	 * Define alert creation date as assigned by agent
	 * 
	 * @param arg alert creation date as assigned by agent
	 * @throws NullPointerException if null arg
	 */
	public void setCreationDate(DateOnly arg) {
		if (arg == null) {
			throw new NullPointerException("null creation date");
		}

		_creation_date = arg;
	}

	/**
	 * Define alert creation date as assigned by agent
	 * 
	 * @param arg alert creation date as assigned by agent
	 * @throws NullPointerException if null arg
	 * @throws ParseException if date parse problem
	 */
	public void setCreationDate(String arg) throws ParseException {
		if (arg == null) {
			throw new NullPointerException("null creation date");
		}

		if (arg.length() > 0) {
			_creation_date = new DateOnly(arg);
		}
	}

	/**
	 * Time of alert creation, assigned by agent.
	 */
	private TimeOnly _creation_time;

	/**
	 * Return alert creation time as assigned by agent
	 * 
	 * @return alert creation time as assigned by agent
	 */
	public TimeOnly getCreationTime() {
		return(_creation_time);
	}

	/**
	 * Define alert creation time as assigned by agent
	 * 
	 * @param arg alert creation time as assigned by agent
	 * @throws NullPointerException if null arg
	 */
	public void setCreationTime(TimeOnly arg) {
		if (arg == null) {
			throw new NullPointerException("null creation time");
		}

		_creation_time = arg;
	}

	/**
	 * Define alert creation time as assigned by agent
	 * 
	 * @param arg alert creation time as assigned by agent
	 * @throws NullPointerException if null arg
	 */
	public void setCreationTime(String arg) {
		if (arg == null) {
			throw new NullPointerException("null creation time");
		}

		if (arg.length() > 0) {
			_creation_time = new TimeOnly(arg);
		}
	}

	/**
	 * Date of manager receipt
	 */
	private DateOnly _receive_date;

	/**
	 * Return alert received date as assigned by manager
	 * 
	 * @return alert received date as assigned by manager
	 */
	public DateOnly getReceiveDate() {
		return(_receive_date);
	}

	/**
	 * Define alert received date as assigned by manager
	 * 
	 * @param arg alert received date as assigned by manager
	 * @throws NullPointerException if null arg
	 */
	public void setReceiveDate(DateOnly arg) {
		if (arg == null) {
			throw new NullPointerException("null received date");
		}

		_receive_date = arg;
	}

	/**
	 * Define alert received date as assigned by manager
	 * 
	 * @param arg alert received date as assigned by manager
	 * @throws NullPointerException if null arg
	 * @throws ParseException if date parse problem
	 */
	public void setReceiveDate(String arg) throws ParseException {
		if (arg == null) {
			throw new NullPointerException("null received date");
		}

		if (arg.length() > 0) {
			_receive_date = new DateOnly(arg);
		}
	}

	/**
	 * Time of manager receipt
	 */
	private TimeOnly _receive_time;

	/**
	 * Return alert received time as assigned by manager
	 * 
	 * @return alert received time as assigned by manager
	 */
	public TimeOnly getReceiveTime() {
		return(_receive_time);
	}

	/**
	 * Define alert received time as assigned by manager
	 * 
	 * @param arg alert received time as assigned by manager
	 * @throws NullPointerException if null arg
	 */
	public void setReceiveTime(TimeOnly arg) {
		if (arg == null) {
			throw new NullPointerException("null received time");
		}

		_receive_time = arg;
	}

	/**
	 * Define alert received time as assigned by manager
	 * 
	 * @param arg alert received time as assigned by manager
	 * @throws NullPointerException if null arg
	 */
	public void setReceiveTime(String arg) {
		if (arg == null) {
			throw new NullPointerException("null received time");
		}

		if (arg.length() > 0) {
			_receive_time = new TimeOnly(arg);
		}
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
	public String getApplication() {
		return(_application);
	}

	/**
	 * Define application name
	 * 
	 * @param arg application name
	 * @throws NullPointerException if null arg
	 */
	public void setApplication(String arg) {
		if (arg == null) {
			throw new NullPointerException("null application name");
		}

		if (arg.length() > 0) {
			_application = arg;
		}
	}

	/**
	 * Message group
	 */
	private String _message_group;

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getMessageGroup() {
		return(_message_group);
	}

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 * @throws NullPointerException if null arg
	 */
	public void setMessageGroup(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message group");
		}

		if (arg.length() > 0) {
			_message_group = arg;
		}
	}

	/**
	 * Object
	 */
	private String _object;

	/**
	 * Return object
	 * 
	 * @return object
	 */
	public String getObject() {
		return(_object);
	}

	/**
	 * Define OVO object
	 * 
	 * @param arg OVO object
	 * @throws NullPointerException if null arg
	 */
	public void setObject(String arg) {
		if (arg == null) {
			throw new NullPointerException("null object");
		}

		if (arg.length() > 0) {
			_object = arg;
		}
	}

	/**
	 * Alert severity (i.e. normal, critical, etc)
	 */
	private String _severity;

	/**
	 * Return alert severity (i.e. normal, critical, etc)
	 * 
	 * @return alert severity
	 */
	public String getSeverity() {
		return(_severity);
	}

	/**
	 * Define alert severity (i.e. normal, critical, etc)
	 * 
	 * @param arg alert serverity
	 * @throws NullPointerException if null arg
	 */
	public void setSeverity(String arg) {
		if (arg == null) {
			throw new NullPointerException("null severity");
		}

		if (arg.length() > 0) {
			_severity = arg;
		}
	}

	/**
	 * Define operators (accounts which receive this message)
	 */
	private List _operators = new ArrayList();

	/**
	 * Return operators
	 * 
	 * @return operators
	 */
	public List getOperators() {
		return(_operators);
	}

	/**
	 * Define operators
	 * 
	 * @param arg List of String
	 * @throws NullPointerException if null arg
	 */
	public void setOperators(List arg) {
		if (arg == null) {
			throw new NullPointerException("null operators");
		}

		_operators = arg;
	}

	/**
	 * Define operators
	 * 
	 * @param arg single operator to add to list
	 * @throws NullPointerException if null arg
	 */
	public void setOperator(String arg) {
		if (arg == null) {
			throw new NullPointerException("null operators");
		}

		_operators.add(arg);
	}

	/**
	 * Message text (from agent)
	 */
	private String _message_text;

	/**
	 * Return message text from agent
	 * 
	 * @return message text from agent
	 */
	public String getMessageText() {
		return(_message_text);
	}

	/**
	 * Define message text from agent
	 * 
	 * @param arg message text from agent
	 * @throws NullPointerException if null arg
	 */
	public void setMessageText(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message text");
		}

		if (arg.length() > 0) {
			_message_text = arg;
		}
	}

	/**
	 * Instruction text (from manager)
	 */
	private String _instruction_text;

	/**
	 * Return instruction text from manager
	 * 
	 * @return instruction text from manager
	 */
	public String getInstructionText() {
		return(_instruction_text);
	}

	/**
	 * Define instruction text from manager
	 * 
	 * @param arg instruction text from manager
	 * @throws NullPointerException if null arg
	 */
	public void setInstructionText(String arg) {
		if (arg == null) {
			throw new NullPointerException("null instruction text");
		}

		if (arg.length() > 0) {
			_instruction_text = arg;
		}
	}

	/**
	 * Message Attributes
	 */
	private Map _attributes = new HashMap();

	/**
	 * Return message attributes
	 * 
	 * @return message attributes
	 */
	public Map getAttributes() {
		return(_attributes);
	}

	/**
	 * Return an attribute given a key (or null if not found)
	 * 
	 * @param arg key
	 * @return an attribute given a key (or null if not found)
	 */
	public String getAttribute(String arg) {
		return((String) _attributes.get(arg));
	}

	/**
	 * Define message attributes
	 * 
	 * @param arg message attributes
	 * @throws NullPointerException if null arg
	 */
	public void setAttributes(Map arg) {
		if (arg == null) {
			throw new NullPointerException("null message attributes");
		}

		_attributes = arg;
	}

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

		_attributes.put(arg.getKey(), arg.getValue());
	}

	/**
	 * Quantity of suppressed messages
	 */
	private int _suppress_count;

	/**
	 * Return suppressed message population
	 * 
	 * @return suppressed message population
	 */
	public int getSuppressCount() {
		return(_suppress_count);
	}

	/**
	 * Define suppressed message count
	 * 
	 * @param arg suppressed message population
	 */
	public void setSuppressCount(int arg) {
		_suppress_count = arg;
	}

	/**
	 * Define suppressed message count
	 * 
	 * @param arg suppressed message population
	 * @throws NullPointerException if null arg
	 */
	public void setSuppressCount(String arg) {
		if (arg == null) {
			throw new NullPointerException("null suppress count");
		}

		if (arg.length() > 0) {
			_suppress_count = Integer.parseInt(arg);
		}
	}

	/**
	 * Return TicketMessage as a String
	 * 
	 * @return TicketMessage as a String
	 */
	public String toString() {
		String temp1 = _message_id + ":" + _source_node + ":"
				+ _source_node_type + ":" + _creation_date + ":"
				+ _creation_time + ":" + _receive_date + ":" + _receive_time
				+ ":" + _application + ":" + _message_group + ":" + _object
				+ ":" + _severity + ":";

		if (_operators.isEmpty()) {
			temp1 += "empty:";
		} else {
			for (int ii = 0; ii < _operators.size(); ii++) {
				String temp2 = (String) _operators.get(ii);
				temp1 += temp2 + ":";
			}
		}

		temp1 += _message_text + ":" + _instruction_text + ":";

		if (_attributes.isEmpty()) {
			temp1 += "empty:";
		} else {
			Iterator ii = _attributes.keySet().iterator();
			do {
				String key = (String) ii.next();
				String value = (String) _attributes.get(key);

				temp1 += key + "=" + value + ":";
			} while (ii.hasNext());
		}

		temp1 += _suppress_count;

		return(temp1);
	}

	/**
	 * Return TicketMessage as a XML formatted String
	 * 
	 * @return TicketMessage as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		// sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		// sb.append(LINE_SEPARATOR);

		// sb.append("<TicketMessage>");
		// sb.append(LINE_SEPARATOR);
		sb.append("  <message_id>" + _message_id + "</message_id>");
		sb.append(LINE_SEPARATOR);
		sb.append("  <source_node>" + _source_node + "</source_node>");
		sb.append(LINE_SEPARATOR);

		if (_source_node_type == null) {
			sb.append("  <source_node_type/>");
		} else {
			sb.append("  <source_node_type>" + _source_node_type + "</source_node_type>");
		}

		sb.append(LINE_SEPARATOR);

		if (_creation_date == null) {
			sb.append("  <creation_date/>");
		} else {
			sb.append("  <creation_date>" + _creation_date + "</creation_date>");
		}

		sb.append(LINE_SEPARATOR);

		if (_creation_time == null) {
			sb.append("  <creation_time/>");
		} else {
			sb.append("  <creation_time>" + _creation_time + "</creation_time>");
		}

		sb.append(LINE_SEPARATOR);

		if (_receive_date == null) {
			sb.append("  <receive_date/>");
		} else {
			sb.append("  <receive_date>" + _receive_date + "</receive_date>");
		}

		sb.append(LINE_SEPARATOR);

		if (_receive_time == null) {
			sb.append("  <receive_time/>");
		} else {
			sb.append("  <receive_time>" + _receive_time + "</receive_time>");
		}

		sb.append(LINE_SEPARATOR);

		if (_application == null) {
			sb.append("  <application/>");
		} else {
			sb.append("  <application>" + _application + "</application>");
		}

		sb.append(LINE_SEPARATOR);

		if (_message_group == null) {
			sb.append("  <message_group/>");
		} else {
			sb.append("  <message_group>" + _message_group + "</message_group>");
		}

		sb.append(LINE_SEPARATOR);

		if (_object == null) {
			sb.append("  <object/>");
		} else {
			sb.append("  <object>" + _object + "</object>");
		}

		sb.append(LINE_SEPARATOR);

		if (_severity == null) {
			sb.append("  <severity/>");
		} else {
			sb.append("  <severity>" + _severity + "</severity>");
		}

		sb.append(LINE_SEPARATOR);

		if (_operators.size() < 1) {
			sb.append("  <operators/>");
		} else {
			sb.append("  <operators>");
			sb.append(LINE_SEPARATOR);

			for (int ii = 0; ii < _operators.size(); ii++) {
				String operator = (String) _operators.get(ii);
				sb.append("    <operator>" + operator + "</operator>");
				sb.append(LINE_SEPARATOR);
			}

			sb.append("  </operators>");
		}

		sb.append(LINE_SEPARATOR);

		if (_message_text == null) {
			sb.append("  <message_text/>");
		} else {
			sb.append("  <message_text>" + escapeString(_message_text) + "</message_text>");
		}

		sb.append(LINE_SEPARATOR);

		if (_instruction_text == null) {
			sb.append("  <instruction_text/>");
		} else {
			sb.append("  <instruction_text>" + escapeString(_instruction_text) + "</instruction_text>");
		}

		sb.append(LINE_SEPARATOR);

		if (_attributes.isEmpty()) {
			sb.append("  <message_attributes/>");
			sb.append(LINE_SEPARATOR);
		} else {
			sb.append("  <message_attributes>");
			sb.append(LINE_SEPARATOR);

			Iterator ii = _attributes.keySet().iterator();
			do {
				String key = (String) ii.next();
				String value = (String) _attributes.get(key);

				sb.append("    <attribute>");
				sb.append(LINE_SEPARATOR);
				sb.append("      <key>" + escapeString(key) + "</key>");
				sb.append(LINE_SEPARATOR);
				sb.append("      <value>" + escapeString(value) + "</value>");
				sb.append(LINE_SEPARATOR);
				sb.append("    </attribute>");
				sb.append(LINE_SEPARATOR);
			} while (ii.hasNext());

			sb.append("  </message_attributes>");
			sb.append(LINE_SEPARATOR);
		}

		sb.append("  <suppress_count>" + _suppress_count + "</suppress_count>");
		sb.append(LINE_SEPARATOR);
		// sb.append("</TicketMessage>");
		// sb.append(LINE_SEPARATOR);

		return(sb.toString());
	}

    /**
     * Serial version identifier.  
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */
