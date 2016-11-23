package com.bgi.esm.monitoring.platform.shared.value;

/**
 * Throttle is inhibiting messages
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class StormMessage extends AbstractMessage {

	/**
	 * Throttle action, either spool or discard
	 */
	private ThrottleAction _action = ThrottleAction.PASS_THRU;

	/**
	 * Define associated throttle action
	 * 
	 * @param arg associated throttle action
	 */
	public void setAction(ThrottleAction arg) {
		_action = arg;
	}

	/**
	 * Return associated throttle action
	 * 
	 * @return associated throttle action
	 */
	public ThrottleAction getAction() {
		return(_action);
	}
	
	/**
	 * Spool key
	 */
	private String _spool_key = "bogus";
		
	/**
	 * Define spool key
	 * 
	 * @param arg spool key
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setSpoolKey(String arg) {
		if (arg == null) {
			throw new NullPointerException("null spool key");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty spool key");
		}
		
		_spool_key = arg;
	}

	/**
	 * Return spool key
	 * 
	 * @return spool key
	 */
	public String getSpoolKey() {
		return(_spool_key);
	}
	
	/**
	 * group, i.e. "accounts-barclaysglobal"
	 */
	private String _group = "group";

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public void setGroup(String arg) {
		if (arg == null) {
			throw new NullPointerException("null group");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty group");
		}

		_group = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getGroup() {
		return(_group);
	}

	/**
	 * Associated storm level
	 */
	private int _storm_level;

	/**
	 * Define storm level
	 * 
	 * @param arg storm level
	 */
	public void setStormLevel(int arg) {
		_storm_level = arg;
	}

	/**
	 * Define storm level
	 * 
	 * @param arg storm level
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 * @throws NumberFormatException if bad value
	 */
	public void setStormLevel(String arg) {
		if (arg == null) {
			throw new NullPointerException("null storm level");
		}

		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty storm level");
		}

		_storm_level = Integer.parseInt(arg);
	}

	/**
	 * Return storm level
	 * 
	 * @return storm level
	 */
	public int getStormLevel() {
		return(_storm_level);
	}

	/**
	 * Return StormMessage as a String
	 * 
	 * @return StormMessage as a String
	 */
	public String toString() {
        StringBuilder message = new StringBuilder ( "StormMessage ( SpoolKey, Group, Action, StormLevel ) = ( " );
        message.append ( _spool_key );
        message.append ( ", " );
        message.append ( _group );
        message.append ( ", " );
        message.append ( _action );
        message.append ( ", " );
        message.append ( _storm_level );
        message.append ( " )" );

		return message.toString();
	}

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
