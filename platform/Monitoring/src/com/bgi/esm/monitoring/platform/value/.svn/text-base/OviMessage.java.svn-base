package com.bgi.esm.monitoring.platform.value;

import java.util.Calendar;

/**
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public class OviMessage implements XmlIf {

    /**
     * ctor
     */
    public OviMessage() {
        LINE_SEPARATOR = System.getProperty("line.separator");
    }
    
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
	
	_message_id = arg;
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
	
	_source_node = arg;
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
	
	_severity = arg;
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

	_object = arg;
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

	_application = arg;
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

	_message_group = arg;
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

	_message_text = arg;
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

	_suppress_count = Integer.parseInt(arg);
    }

    /////////////////////////////

    private long _binary_time = 0L;

    /////////////////////////////

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

	if (arg.getKey().equals("application")) {
	    _application = arg.getValue();
	} else if (arg.getKey().equals("group")) {
	    _message_group = arg.getValue();
	} else if (arg.getKey().equals("object")) {
	    _object = arg.getValue();
	} else if (arg.getKey().equals("receiveTimeSeconds")) {
	    _binary_time = Long.parseLong(arg.getValue());
	} else if (arg.getKey().equals("text")) {
	    _message_text = arg.getValue();
	}
    }

    /**
     *
     */
    public TicketMessage getTicketMessage() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(_binary_time * 1000L);

	DateOnly date = new DateOnly(calendar);
	TimeOnly time = new TimeOnly(calendar);

	TicketMessage tm = new TicketMessage();

	tm.setMessageId(_message_id);
	tm.setSourceNode(_source_node);
	tm.setReceiveDate(date);
	tm.setReceiveTime(time);
	tm.setApplication(_application);
	tm.setMessageGroup(_message_group);
	tm.setObject(_object);
	tm.setSeverity(_severity);
	tm.setMessageText(_message_text);
	//custom message
	tm.setSuppressCount(_suppress_count);

	return(tm);
    }

    /**
     * Return OVI Message as a String
     *
     * @return OVI Message as a String
     */
    public String toString() {
	return("OVI message");
    }

    /**
     * Return OVI Message as a XML formatted String
     *
     * @return OVI Message as a XML formatted String
     */
    public String toXml() {
	StringBuffer sb = new StringBuffer();

 	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
 	sb.append(LINE_SEPARATOR);

	return(sb.toString());
    }

    /**
     * Define line separator string.
     */
    public final String LINE_SEPARATOR;
}

/*
 * Development Environment:
 *   Fedora 4
 *   Sun Java Developers Kit 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
