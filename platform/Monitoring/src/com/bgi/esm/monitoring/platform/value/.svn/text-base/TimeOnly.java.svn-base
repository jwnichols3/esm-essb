package com.bgi.esm.monitoring.platform.value;

import java.io.Serializable;

import java.sql.Time;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Time only, no date.
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public final class TimeOnly implements Comparable, Serializable {
    
    /**
     * Default ctor uses current time.
     */
    public TimeOnly() { 
    	Calendar temp = Calendar.getInstance();
    	setTime(temp.getTimeInMillis());
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeOnly(Calendar arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeOnly.ctor");
    	}
	
    	setTime(arg.getTimeInMillis());
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeOnly(Time arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeOnly.ctor");
    	}
	
    	setTime(arg.getTime());
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeOnly(TimeOnly arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeOnly.ctor");
    	}
    	
    	setTime(arg.getCalendarValue().getTimeInMillis());
    }
    
    /**
     * Define the time value.  
     *
     * @param hours 0 to 23
     * @param minutes 0 to 59
     * @param seconds 0 to 59
     *
     * @throws IllegalArgumentException out of range argument
     */
    public TimeOnly(int hours, int minutes, int seconds) {
    	if ((hours < MIN_HOUR) || (hours > MAX_HOUR)) {
	    throw new IllegalArgumentException("bad hour");
    	}
	
    	if ((minutes < MIN_MINUTE) || (minutes > MAX_MINUTE)) {
	    throw new IllegalArgumentException("bad minute");
    	}
	
    	if ((seconds < MIN_SECOND) || (seconds > MAX_SECOND)) {
	    throw new IllegalArgumentException("bad second");
    	}
	
       	Calendar temp = Calendar.getInstance();
	
    	temp.set(Calendar.HOUR_OF_DAY, hours);
    	temp.set(Calendar.MINUTE, minutes);
    	temp.set(Calendar.SECOND, seconds);
	
    	setTime(temp.getTimeInMillis());
    }

    /**
     * Define the time value
     *
     * @param arg time as 12:34:56
     *
     * @throws IllegalArgumentException out of range argument
     */
    public TimeOnly(String arg) {
	String[] argz = arg.split(":");

 	if (argz.length != 3) {
	    throw new IllegalArgumentException("bad time");
 	}

	int hours = Integer.parseInt(argz[0]);
	int minutes = Integer.parseInt(argz[1]);
	int seconds = Integer.parseInt(argz[2]);

    	if ((hours < MIN_HOUR) || (hours > MAX_HOUR)) {
	    throw new IllegalArgumentException("bad hour");
    	}
	
    	if ((minutes < MIN_MINUTE) || (minutes > MAX_MINUTE)) {
	    throw new IllegalArgumentException("bad minute");
    	}
	
    	if ((seconds < MIN_SECOND) || (seconds > MAX_SECOND)) {
	    throw new IllegalArgumentException("bad second");
    	}
	
       	Calendar temp = Calendar.getInstance();
	
    	temp.set(Calendar.HOUR_OF_DAY, hours);
    	temp.set(Calendar.MINUTE, minutes);
    	temp.set(Calendar.SECOND, seconds);
	
    	setTime(temp.getTimeInMillis());
    }
    
    /**
     * Set time in milliseconds
     * 
     * @param arg time in milliseconds
     */
    private void setTime(long arg) {
    	Calendar temp = Calendar.getInstance();
    	temp.setTimeInMillis(arg);
    	
    	_value.clear();
    	
    	_value.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
       	_value.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
       	_value.set(Calendar.SECOND, temp.get(Calendar.SECOND));
      	_value.set(Calendar.MILLISECOND, 0);
	
    	_value.set(Calendar.YEAR, 0);
    	_value.set(Calendar.MONTH, 0);
    	_value.set(Calendar.DAY_OF_MONTH, 0);   	
    }
    
    /**
     * Return value in milliseconds
     *
     * @return value in milliseconds
     */
    public long getMilliSecondsValue() {
	return(_value.getTimeInMillis());
    }
    
    /**
     * Return value as calendar
     *
     * @return value as calendar
     */
    public Calendar getCalendarValue() {
	return(_value);
    }
    
    /**
     * Return value as date
     *
     * @return value as date
     */
    public Date getDateValue() {
	return(new Date(_value.getTimeInMillis()));
    }
    
    /**
     * Return value as time stamp
     *
     * @return value as time stamp
     */
    public Time getTimeValue() {
    	return(new Time(_value.getTimeInMillis()));
    }
    
    /**
     * Creates and returns a copy of this object
     * 
     * @return populated copy of this object
     */
    public Object clone() {
    	return(new TimeOnly(_value));
    }
    
    /**
     * Return hash code value for this object
     *
     * @return hash code value for this object
     */
    public int hashCode() {
    	return(29 * _value.hashCode());
    }
    
    /**
     * Return true if the supplied arguments match the same
     * time as this instance.
     *
     * @param hours 0 to 23
     * @param minutes 0 to 59
     * @param seconds 0 to 59
     * @return true, if the specified time matches this instance.
     */
    public boolean equals(int hours, int minutes, int seconds) {
        if (hours != _value.get(Calendar.HOUR_OF_DAY)) {
            return(false);
        }
        
        if (minutes != _value.get(Calendar.MINUTE)) {
            return(false);
        }
        
        if (seconds != _value.get(Calendar.SECOND)) {
            return(false);
        }
        
        return(true);
    }
    
    /**
     * Return true if values match
     *
     * @param arg test candidate
     * @return true if values match
     * @throws ClassCastException if arg cannot be cast
     */
    public boolean equals(Object arg) {
    	if (arg == null) {
	    return(false);
    	}
	
    	Calendar temp = ((TimeOnly) arg).getCalendarValue();
	
    	int hours = temp.get(Calendar.HOUR_OF_DAY);
        int minutes = temp.get(Calendar.MINUTE);
        int seconds = temp.get(Calendar.SECOND);
	
        return(equals(hours, minutes, seconds));
    }
    
    /**
     * Compare this object w/the specified object.
     *
     * @return -1 (less than), 0 (equals), 1 (greater than)
     * @throws ClassCastException if arg cannot be cast
     * @throws NullPointerException if null arg
     */
    public int compareTo(Object arg) {
        if (arg == null) {
            throw new NullPointerException("null TimeOnly arg");
        }
        
    	TimeOnly to = (TimeOnly) arg;
	
    	if (_value.after(to.getCalendarValue())) {
	    return(1);
    	}
	
    	if (_value.before(to.getCalendarValue())) {
	    return(-1);
    	}
	
    	return(0);
    }
    
    /**
     * Return a formatted string representing the state of this instance.
     * Has the form HH:mm:ss
     *
     * @return a formatted string representing the state of this instance.
     */
    public String toString() {
    	return(_sdf.format(_value.getTime()));
    }
    
    /**
     * free form note regarding this row
     */
    private final Calendar _value = Calendar.getInstance();
    
    /**
     * format employed by toString() method HH:mm:ss
     */
    private static SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm:ss");
    
    /**
     * Serial version identifier.  
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;
    
    /**
     * Smallest legal hour
     */
    public static final int MIN_HOUR = 0;
    
    /**
     * Largest legal hour
     */
    public static final int MAX_HOUR = 23;
    
    /**
     * Smallest legal minute
     */
    public static final int MIN_MINUTE = 0;
    
    /**
     * Largest legal minute
     */
    public static final int MAX_MINUTE = 59;
    
    /**
     * Smallest legal second
     */
    public static final int MIN_SECOND = 0;
    
    /**
     * Largest legal second
     */
    public static final int MAX_SECOND = 59;
    
    /**
     * Smallest legal millisecond
     */
    public static final int MIN_MILLISECOND = 0;
    
    /**
     * Largest legal millisecond
     */
    public static final int MAX_MILLISECOND = 99;
}

/*
 * Development Environment:
 *   Fedora Core 4
 *   Sun Java 1.5.0_05
 *
 * Maintenance History:
 *   $Log$
 */
