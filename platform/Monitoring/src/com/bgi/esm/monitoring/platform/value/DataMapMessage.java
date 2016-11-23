package com.bgi.esm.monitoring.platform.value;

/**
 * Message to DataMap buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMapMessage extends AbstractMessage {

	/**
	 * Define raw OVO ticket payload
	 * 
	 * @param arg raw OVO ticket payload
	 */
	public void setTicketMessage(TicketMessage arg) {
		_ticket_message = arg;
	}

	/**
	 * Return raw OVO ticket payload or null if not defined.
	 * 
	 * @return raw OVO ticket payload or null if not defined.
	 */
	public TicketMessage getTicketMessage() {
		return(_ticket_message);
	}

	/**
	 * Return DataMapMessage as a String
	 * 
	 * @return DataMapMessage as a String
	 */
	public String toString() {
		return("DataMapMessage");
	}

	/**
	 * Return DataMapMessage as a XML formatted String
	 * 
	 * @return DataMapMessage as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(LINE_SEPARATOR);

		sb.append("<EmsContainer>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <DataMapMessage>");
		sb.append(LINE_SEPARATOR);

		sb.append("    <ticket>");
		sb.append(LINE_SEPARATOR);

		sb.append(_ticket_message.toXml());

		sb.append("    </ticket>");
		sb.append(LINE_SEPARATOR);

		sb.append("  </DataMapMessage>");
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