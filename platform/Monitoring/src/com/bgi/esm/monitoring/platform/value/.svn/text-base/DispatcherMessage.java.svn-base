package com.bgi.esm.monitoring.platform.value;

/**
 * Message to Dispatch buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DispatcherMessage extends AbstractMessage {

	/**
	 * Define raw OVO ticket payload
	 * 
	 * @param arg raw OVO ticket payload
	 */
	public void setTicketMessage(TicketMessage arg) {
		_ticket_message = arg;
	}

	/**
	 * Return raw OVO ticket payload
	 * 
	 * @return raw OVO ticket payload
	 */
	public TicketMessage getTicketMessage() {
		return(_ticket_message);
	}

	/**
	 * Define supporting data map rule
	 * 
	 * @param arg supporting data map rule
	 */
	public void setDataMapRule(DataMapRule arg) {
		_data_map_rule = arg;
	}

	/**
	 * Return supporting data map rule
	 * 
	 * @return supporting data map rule.
	 */
	public DataMapRule getDataMapRule() {
		return(_data_map_rule);
	}

	/**
	 * Return DispatcherMessage as a String
	 * 
	 * @return DispatcherMessage as a String
	 */
	public String toString() {
		return("dispatcher message");
	}

	/**
	 * Return DispatcherMessage as a XML formatted String
	 * 
	 * @return KillMessage as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(LINE_SEPARATOR);

		sb.append("<EmsContainer>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <DispatcherMessage>");
		sb.append(LINE_SEPARATOR);

		sb.append("    <ticket>");
		sb.append(LINE_SEPARATOR);

		sb.append(_ticket_message.toXml());

		sb.append("    </ticket>");
		sb.append(LINE_SEPARATOR);

		sb.append("  </DispatcherMessage>");
		sb.append(LINE_SEPARATOR);

		sb.append("</EmsContainer>");
		sb.append(LINE_SEPARATOR);

		return(sb.toString());
	}

	/**
	 * Message payload
	 */
	private TicketMessage _ticket_message;

	/**
	 * Message payload
	 */
	private DataMapRule _data_map_rule;
	
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