package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Message to Suppression buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class SuppressionMessage extends AbstractMessage {

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
	 * Return SuppressionMessage as a String
	 * 
	 * @return SuppressionMessage as a String
	 */
	public String toString() {
		return "Suppression message, TicketMessage=" + _ticket_message.toString();
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
