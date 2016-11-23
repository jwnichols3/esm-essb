package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Acknowlege a OVO event
 * 
 * @author coleguy
 */
public class AcknowledgeMessage extends AbstractMessage {

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
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return(_message_id);
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
		sb.append("    <modifyState>");
		sb.append(LINE_SEPARATOR);
		sb.append("      Acknowledge");
		sb.append(LINE_SEPARATOR);
		sb.append("    </modifyState>");
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
