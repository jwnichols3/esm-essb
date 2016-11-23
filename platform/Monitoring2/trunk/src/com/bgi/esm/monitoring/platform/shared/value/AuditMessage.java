package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Event Mine Message (AUDIT)
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class AuditMessage extends AbstractMessage {

	/**
	 * Buss module which is reporting event (i.e. suppression, data map, throttle, etc)
	 */
	private BussModule _module = BussModule.UNKNOWN;

	/**
	 * Return reporting BussModule
	 * 
	 * @return reporting BussModule
	 */
	public BussModule getModule() {
		return(_module);
	}

	/**
	 * Define reporting BussModule
	 * 
	 * @param arg reporting BussModule
	 * @throws NullPointerException if null arg
	 */
	public void setModule(BussModule arg) {
		if (arg == null) {
			throw new NullPointerException("null module");
		}
		
		_module = arg;
	}

	/**
	 * audit action, essentially free form string
	 */
	private String _action = "unknown";

	/**
	 * Return audit action
	 * 
	 * @return audit action
	 */
	public String getAction() {
		return(_action);
	}

	/**
	 * Define audit action
	 * 
	 * @param arg audit action
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setAction(String arg) {
		if (arg == null) {
			throw new NullPointerException("null action");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty action");
		}
		
		_action = arg;
	}
	
	/**
	 * message identifier, taken from HP OVO message
	 */
	private String _message = "unknown";

	/**
	 * Return HP OVO message ID
	 * 
	 * @return HP OVO message ID
	 */
	public String getMessageId() {
		return(_message);
	}

	/**
	 * Define HP OVO message ID
	 * 
	 * @param arg HP OVO message ID
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setMessageId(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message ID");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty message ID");
		}
		
		_message = arg;
	}
	
	/**
	 * Hash code value for this instance, derived from action, module and message
	 * 
	 * @return hash code value for this instance
	 */
	public int hashCode() {
		return(_action.hashCode() * _module.hashCode() * _message.hashCode());
	}
	
	/**
	 * Return true if arg equals this instance.  
	 * Tests action, module, message.
	 * 
	 * @throws ClassCastException if arg cannot be cast
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}
		
		AuditMessage temp = (AuditMessage) arg;

		if (!temp.getAction().equals(_action)) {
			return(false);
		}
		
		if (!temp.getModule().equals(_module)) {
			return(false);
		}
		
		if (!temp.getMessageId().equals(_message)) {
			return(false);
		}
		
		return(true);
	}
	
	/**
	 * Return object state as a string
	 * 
	 * @return object state as a string
	 */
	public String toString() {
		return("AuditMessage:" + _module + ":" + _action + ":" + _message);
	}

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
