package com.bgi.esm.monitoring.platform.value;

import java.io.Serializable;

/**
 * Abstract parent for XML message types.
 * 
 * @author coleguy
 */
public abstract class AbstractMessage implements Serializable, XmlIf {

	/**
	 * ctor
	 */
	public AbstractMessage() {
		LINE_SEPARATOR = System.getProperty("line.separator");
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public abstract String toString();

	/**
	 * Return object state as a XML formatted String
	 * 
	 * @return object state as a XML formatted String
	 */
	public abstract String toXml();

	/**
	 * Escape any special characters (such as ampersands). Also replaces any
	 * 'funny' characters w/spaces.
	 * 
	 * @param arg raw line to escape
	 * @return tweaked line w/escaped symbols.
	 */
	String escapeString(String arg) {
		StringBuffer sb = new StringBuffer();

		int length = arg.length();

		for (int ii = 0; ii < length; ii++) {
			char candidate = arg.charAt(ii);

			if (candidate == '&') {
				sb.append("&amp;");
			} else if (candidate == '<') {
				sb.append("&lt;");
			} else if (candidate == '>') {
				sb.append("&gt;");
			} else if (candidate == '\"') {
				sb.append("&quot;");
			} else if (candidate == '\'') {
				sb.append("&#039;");
			} else if (candidate == '\\') {
				sb.append("&#092;");
			} else if (candidate < ' ') {
				sb.append(" ");
			} else if (candidate > '~') {
				sb.append(" ");
			} else {
				sb.append(candidate);
			}
		}

		return (sb.toString());
	}
	
	/**
	 * Define line separator string.
	 */
	public final String LINE_SEPARATOR;
}
