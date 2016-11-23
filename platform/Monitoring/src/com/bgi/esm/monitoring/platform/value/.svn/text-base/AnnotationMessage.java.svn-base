package com.bgi.esm.monitoring.platform.value;

/**
 * Add a new annotation to an OVO message.
 * 
 * @author coleguy
 */
public class AnnotationMessage extends AbstractMessage {
	
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
	 * Annotation text
	 */
	private String _text;
	
	/**
	 * Return annotation text
	 * 
	 * @return annotation text
	 */
	public String getText() {
		return(_text);
	}
	
	/**
	 * Define annotation text
	 * 
	 * @param arg annotation text
	 */
	public void setText(String arg) {
		_text = arg;
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return(_message_id + ":" + _text);
	}

	/**
	 * Return object state as a XML formatted String
	 * 
	 * @return object state as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(LINE_SEPARATOR);		
		sb.append("<ovMessageModifyRequest xmlns=\"http://openview.hp.com/xmlns/ico/message\" xmlns:ovit=\"http://openview.hp.com/xmlns/ico/types\" version=\"1.0\">");
		sb.append(LINE_SEPARATOR);
		sb.append("  <messageUUID>");
		sb.append(LINE_SEPARATOR);
		sb.append("    " + _message_id);
		sb.append(LINE_SEPARATOR);
		sb.append("  </messageUUID>");
		sb.append(LINE_SEPARATOR);
		sb.append("  <action>");
		sb.append(LINE_SEPARATOR);
		sb.append("    <data>");
		sb.append(LINE_SEPARATOR);
		sb.append("      <name>annotation</name>");
		sb.append(LINE_SEPARATOR);
		sb.append("      <value type=\"string\">" + _text + "</value>");
		sb.append(LINE_SEPARATOR);
		sb.append("    </data>");
		sb.append(LINE_SEPARATOR);
		sb.append("  </action>");
		sb.append(LINE_SEPARATOR);
		sb.append("</ovMessageModifyRequest>");
		sb.append(LINE_SEPARATOR);
				
		return(sb.toString());
	}

    /**
     * Serial version identifier.  
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;
}
