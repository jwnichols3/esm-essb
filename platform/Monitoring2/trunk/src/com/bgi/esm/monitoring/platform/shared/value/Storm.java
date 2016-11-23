package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for storm level.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class Storm implements Serializable {

	private String         _spool_key      = "bogus";
	private int            _storm_level    = 0;                //  storm level
	private ThrottleAction _action         = null;
	private String         _group          = null;             //  group, i.e. "accounts-barclaysglobal"
	private Calendar _reminder_message     = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 */
	public void setGroup(String arg) 
    {
		_group = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getGroup() 
    {
		return(_group);
	}


	public void setStormLevel(int arg) 
    {
		_storm_level = arg;
	}

	public void setStormLevel(String arg) 
    {
		_storm_level = Integer.parseInt(arg);
	}

	public int getStormLevel() 
    {
		return(_storm_level);
	}

	public void setAction(ThrottleAction arg) 
    {
		_action = arg;
	}

	public void setAction(String arg) 
    {
		_action = ThrottleAction.getInstance(arg);
	}

	public ThrottleAction getAction() 
    {
		return(_action);
	}

	public void setSpoolKey(String arg) 
    {
		_spool_key = arg;
	}

	public String getSpoolKey() 
    {
		return(_spool_key);
	}

	public void setReminderTimeStamp(Calendar arg) 
    {
		_reminder_message = arg;
	}

	public Calendar getReminderTimeStamp() 
    {
		return(_reminder_message);
	}

	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
	 */
	public int hashCode() 
    {
		int result = _group.hashCode() * _action.hashCode() * _storm_level;

		return(result);
	}

	/**
	 * Return true if the Rules match. Compares all fields.
	 * 
	 * @param arg
	 *            candidate to match
	 * @return true if successful match
	 */
	public boolean equals(DataMapRule arg) 
    {
		// if (!_group.equals(arg.getGroup())) {
		// return(false);
		// }

		// if (!_method.equals(arg.getMethod())) {
		// return(false);
		// }

		// if (!_ap_script.equals(arg.getAlarmPointScript())) {
		// return(false);
		// }

		// if (!_p_assignment.equals(arg.getPeregrineAssignment())) {
		// return(false);
		// }

		// if (!_p_location.equals(arg.getPeregrineLocation())) {
		// return(false);
		// }

		return(true);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() 
    {
        StringBuilder message = new StringBuilder();
            message.append ( "Storm ( key=" );
            message.append ( _spool_key );
            message.append ( ", group=" );
            message.append ( _group );
            message.append ( ", action=" );
            message.append ( _action.toString() );
            message.append ( ", stormLevel=" );
            message.append ( _storm_level );
            message.append ( " )" );
		return message.toString();
	}
}
