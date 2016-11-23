package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

/**
 * Abstract message parent.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public abstract class AbstractMessage implements Serializable {

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
	 * Define line separator string.
	 */
	public final String LINE_SEPARATOR;

	/**
	 * Escape any special characters (such as ampersands). 
	 * Also replaces any strange characters w/spaces.
	 * 
	 * @param arg raw line to escape
	 * @return tweaked line w/escaped symbols.
	 */
	protected String escapeString(String arg) {
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

		return(sb.toString());
	}
}
