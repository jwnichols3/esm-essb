package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Message to Dispatcher buss module
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
     *  Set the DataMap rule
     */
    public void setDataMapRule ( DataMapRule arg )
    {
        _rule = arg;
    }

    public DataMapRule getDataMapRule ()
    {
        return _rule;
    }

	/**
	 * Return DispatcherMessage as a String
	 * 
	 * @return DispatcherMessage as a String
	 */
	public String toString() {
		return "Dispatcher message, TicketMessage=" + _ticket_message.toString();
	}

    /**
     * Datamap rule
     */
    private DataMapRule _rule;

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
