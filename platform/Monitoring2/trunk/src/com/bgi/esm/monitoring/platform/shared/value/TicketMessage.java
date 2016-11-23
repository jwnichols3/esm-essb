package com.bgi.esm.monitoring.platform.shared.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;

/**
 * Value object representing a HPOV ticket.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class TicketMessage extends AbstractMessage {

    private static Logger _log = null;

    static {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("TicketMessage - could not create logger");
            _log = Logger.getLogger ( TicketMessage.class );
        }
    }

    final public static String INTERFACE_TTI          = "TroubleTicketInterface";
    final public static String INTERFACE_NOTIFICATION = "NotificationInterface";

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

	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	public Calendar getTimeStamp() {
		return(_time_stamp);
	}

	public void setTimeStamp(Calendar arg) {
		_time_stamp = arg;
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
	private List<String> _operators = new ArrayList<String>();

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
	public void setOperators(List<String> arg) {
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
	// public void addKeyValue(KeyValue arg) {
	// if (arg == null) {
	// throw new NullPointerException("null message attributes/keyval");
	// }
	// _attributes.put(arg.getKey(), arg.getValue());
	// }
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

    private String _event_interface;

    /**
     *  Determines the type of interface that the event came in from.
     */
    public void setEventInterface ( String event_interface )
    {
        if (( false == event_interface.equals ( INTERFACE_TTI )) &&
            ( false == event_interface.equals ( INTERFACE_NOTIFICATION )))
        {
            throw new IllegalArgumentException ( "Invalid event interface: " + event_interface );
        }

        _event_interface = event_interface;
    }

    public String getEventInterface()
    {
        return _event_interface;
    }

    private String _message_key = null;

    public static String createMessageKey ( String severity, String message_group, String source_node, String application, String object, int message_text_hash )
    {
        StringBuilder message = new StringBuilder();
        message.append ( (null != severity)? severity : "" );
        message.append ( ":" );
        message.append ( (null != message_group)? message_group : "" );
        message.append ( ":" );
        message.append ( (null != source_node)? source_node : "" );
        message.append ( ":" );
        message.append ( (null != application)? application : "" );
        message.append ( ":" );
        message.append ( (null != object)? object : "" );
        message.append ( ":" );
        message.append ( Integer.toString ( message_text_hash ) );

        if ( _log.isDebugEnabled() )
        {
            StringBuilder debug = new StringBuilder();
                debug.append ( TicketMessage.class.getName() );
                debug.append ( "::createMessageKey() - resulting message key is " );
                debug.append ( message.toString() );
            _log.debug ( debug.toString() );
        }

        if ( message.toString().length() < 253 )
        {
            return message.toString();
        }
        else
        {
            return message.toString().substring ( 0, 253 );
        }
    }

    public String getMessageKey()
    {
        return TicketMessage.createMessageKey ( _severity, _message_group, _source_node, _application, _object, _message_text.hashCode() );
    }

	/**
	 * Return TicketMessage as a String
	 * 
	 * @return TicketMessage as a String
	 */
	public String toString() {
		// String temp1 = _message_id + ":" + _source_node + ":" +
		// _source_node_type + ":" + _creation_date + ":" + _creation_time + ":"
		// + _receive_date + ":" + _receive_time + ":" + _application + ":" +
		// _message_group + ":" + _object + ":" + _severity + ":";

		// if (_operators.isEmpty()) {
		// temp1 += "empty:";
		// } else {
		// for (int ii = 0; ii < _operators.size(); ii++) {
		// String temp2 = (String) _operators.get(ii);
		// temp1 += temp2 + ":";
		// }
		// }

		// temp1 += _message_text + ":" + _instruction_text + ":";

		// if (_attributes.isEmpty()) {
		// temp1 += "empty:";
		// } else {
		// Iterator ii = _attributes.keySet().iterator();
		// do {
		// String key = (String) ii.next();
		// String value = (String) _attributes.get(key);

		// temp1 += key + "=" + value + ":";
		// } while(ii.hasNext());
		// }

		// temp1 += _suppress_count;

		//String temp1 = _message_id;
        
        StringBuilder message = new StringBuilder( "TicketMessage for Message ID: " );
        message.append ( _message_id );

		return message.toString();
	}
	
	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
