package com.bgi.esm.monitoring.platform.value;

import java.io.Serializable;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Universal date and time container.  
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @version $Id$
 */
public final class TimeHack implements Comparable, Serializable {
    
    /**
     * Default ctor uses current time.
     */
    public TimeHack() {
    	//empty
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeHack(Calendar arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeHack.ctor");
    	}
	
    	_value.clear();	
    	_value.setTimeInMillis(arg.getTimeInMillis());
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeHack(Timestamp arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeHack.ctor");
    	}
	
    	_value.clear();
    	_value.setTime(arg);
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeHack(TimeHack arg) {
    	if (arg == null) {
	    throw new NullPointerException("null TimeHack.ctor");
    	}
	
    	_value.clear();
    	_value.setTimeInMillis(arg.getCalendarValue().getTimeInMillis());
    }
    
    /**
     * ctor w/initial value
     *
     * @param arg value
     * @throws NullPointerException if null arg
     */
    public TimeHack(DateOnly date, TimeOnly time) {
    	if (date == null) {
	    throw new NullPointerException("null TimeHack.ctor date");
    	}
	
    	if (time == null) {
	    throw new NullPointerException("null TimeHack.ctor time");
    	}
	
    	_value.clear();
    	_value.setTimeInMillis(date.getCalendarValue().getTimeInMillis());
	
    	Calendar temp = time.getCalendarValue();
	
    	_value.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
    	_value.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
    	_value.set(Calendar.SECOND, temp.get(Calendar.SECOND));
    	_value.set(Calendar.MILLISECOND, temp.get(Calendar.MILLISECOND));
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
    public Timestamp getTimestampValue() {
    	return(new Timestamp(_value.getTimeInMillis()));
    }
    
    /**
     * Return value as DateOnly
     *
     * @return value as DateOnly
     */
    public DateOnly getDateOnlyValue() {
	return(new DateOnly(_value));
    }
    
    /**
     * Return value as TimeOnly
     *
     * @return value as TimeOnly
     */
    public TimeOnly getTimeOnlyValue() {
    	return(new TimeOnly(_value));
    }
    
    /**
     * Creates and returns a copy of this object
     * 
     * @return populated copy of this object
     */
    public Object clone() {
    	return(new TimeHack(_value));
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
	
    	TimeHack th = (TimeHack) arg;
	
    	return(_value.equals(th.getCalendarValue()));
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
            throw new NullPointerException("null TimeHack arg");
        }
	
    	TimeHack th = (TimeHack) arg;
	
    	if (_value.after(th.getCalendarValue())) {
	    return(1);
    	}
	
    	if (_value.before(th.getCalendarValue())) {
	    return(-1);
    	}
	
    	return(0);
    }
    
    /**
     * Return a formatted string representing the state of this instance.
     * Has the form YYYY/MM/DD HH:mm:ss
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
     * format employed by toString() method, YYYY/MM/DD HH:mm:ss
     */
    private static SimpleDateFormat _sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    /**
     * Serial version identifier.  
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;
}

/* 
 * Development Environment:
 *   Fedora Core 4
 *   Sun Java 1.5.0_06
 *
 * Maintenance History:
 *   $Log$
 */
