package com.bgi.esm.monitoring.platform.value;

/**
 * Message to EventMine module.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class EventMineMessage extends AbstractMessage {
	
	/**
	 * Source topic
	 */
	private String _source;

	/**
	 * Return XML source topic
	 * 
	 * @return XML source topic
	 */
	public String getSource() {
		return(_source);
	}

	/**
	 * Define XML source topic
	 * 
	 * @param arg source topic
	 */
	public void setSource(String arg) {
		_source = arg;
	}

	/**
	 * XML payload
	 */
	private String _xml;
	
	/**
	 * Return raw XML payload
	 * 
	 * @return raw XML payload
	 */
	public String getPayload() {
		System.out.println("payload:" + _xml + ":");
		
		if (_xml == null) {
			return("bogus");
		}
		
		if (_xml.length() < 1) {
			return("bogus2");
		}
		
		return(_xml);
	}

	/**
	 * Define raw XML payload
	 * 
	 * @param arg raw XML payload
	 */
	public void setPayload(String arg) {
		if (arg.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
			//
			// must strip off the magic cookie or Digester chokes
			// 
			int ndx = arg.indexOf(">") + 1;	
			_xml = arg.substring(ndx);
		} else {
			_xml = arg;
		}
	}
	
	public void addPayload(String arg) {
		System.out.println("add:" + arg);
	}
	
	/**
	 * Return EventMineMessage as a String
	 * 
	 * @return EventMineMessage as a String
	 */
	public String toString() {
		return("event mine message:" + _source);
	}

	/**
	 * Return EventMineMessage as a XML formatted String
	 * 
	 * @return KillMessage as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(LINE_SEPARATOR);

		sb.append("<EmsContainer>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <EventMineMessage>");
		sb.append(LINE_SEPARATOR);
		
		sb.append("    <source>" + _source + "</source>");
		sb.append(LINE_SEPARATOR);
		
		sb.append("    <payload>");
		sb.append(LINE_SEPARATOR);
		sb.append(escapeString(_xml));
		sb.append(LINE_SEPARATOR);
		sb.append("    </payload>");
		sb.append(LINE_SEPARATOR);

		sb.append("  </EventMineMessage>");
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