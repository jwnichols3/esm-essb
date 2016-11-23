package com.bgi.esm.monitoring.platform.value;

/**
 * Command orderly shutdown. JMS subscribers consume this message and forward to
 * next module. After write to next module, module should perform orderly
 * shutdown.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class KillMessage extends AbstractMessage {

	/**
	 * Return KillMessage as a String
	 * 
	 * @return KillMessage as a String
	 */
	public String toString() {
		return("kill message");
	}

	/**
	 * Return KillMessage as a XML formatted String
	 * 
	 * @return KillMessage as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(LINE_SEPARATOR);

		sb.append("<EmsContainer>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <KillMessage/>");
		sb.append(LINE_SEPARATOR);

		sb.append("</EmsContainer>");
		sb.append(LINE_SEPARATOR);

		return(sb.toString());
	}

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
