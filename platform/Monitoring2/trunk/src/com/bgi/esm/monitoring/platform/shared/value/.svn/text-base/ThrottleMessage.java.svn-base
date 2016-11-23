package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Message to Throttle buss module
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class ThrottleMessage extends AbstractMessage {

	/**
	 * Define raw OVO ticket payload
	 * 
	 * @param arg raw OVO ticket payload
	 * @throws NullPointerException if null arg
	 */
	public void setTicketMessage(TicketMessage arg) {
		if (arg == null) {
			throw new NullPointerException("null ticket message");
		}
		
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
	 * Define data map rule
	 * 
	 * @param arg data map rule
	 * @throws NullPointerException if null arg
	 */
	public void setDataMapRule(DataMapRule arg) {
		if (arg == null) {
			throw new NullPointerException("null data map rule");
		}
		
		_map_rule = arg;
	}

	/**
	 * Return data map rule
	 * 
	 * @return data map rule
	 */
	public DataMapRule getDataMapRule() {
		return(_map_rule);
	}

	/**
	 * Return ThrottleMessage as a String
	 * 
	 * @return ThrottleMessage as a String
	 */
	public String toString() {
		return "Throttle message, TicketMessage=" + _ticket_message.toString();
	}

	/**
	 * Message payload
	 */
	private TicketMessage _ticket_message;

	/**
	 * Data map
	 */
	private DataMapRule _map_rule;
	
	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
