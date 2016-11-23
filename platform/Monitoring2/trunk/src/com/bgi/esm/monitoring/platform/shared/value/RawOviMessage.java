package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Event Mine Message (RAW_OVI)
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class RawOviMessage extends AbstractMessage {

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
	 * Raw OVI XML 
	 */
	private String _xml_payload = "bogus";

	/**
	 * Return HP OVO raw XML
	 * 
	 * @return HP OVO raw XML
	 */
	public String getXmlPayload() {
		return(_xml_payload);
	}

	/**
	 * Define HP OVO raw XML
	 * 
	 * @param arg HP OVO raw XML
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setXmlPayload(String arg) {
		if (arg == null) {
			throw new NullPointerException("null xml payload");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty xml payload");
		}
		
		_xml_payload = arg;
	}
	
	/**
	 * Hash code value for this instance, derived from module and message
	 * 
	 * @return hash code value for this instance
	 */
	public int hashCode() {
		return(_module.hashCode() * _message.hashCode());
	}
	
	/**
	 * Return true if arg equals this instance.  
	 * Tests module and message.
	 * 
	 * @throws ClassCastException if arg cannot be cast
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}
		
		RawOviMessage temp = (RawOviMessage) arg;
		
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
		return("RawOviMessage:" + _module + ":" + _message);
	}

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
