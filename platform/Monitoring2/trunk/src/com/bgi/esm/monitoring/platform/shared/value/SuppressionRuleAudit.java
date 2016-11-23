package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * Value object for suppression rule.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (linden)
 */
public final class SuppressionRuleAudit implements Comparable, Serializable {

    /**
     * empty ctor
     */
    public SuppressionRuleAudit() {
        //empty
    }
    
    /**
     * ctor w/defined row key
     *
     * @param arg row key
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public SuppressionRuleAudit(String arg) {
        if (arg == null) {
            throw new NullPointerException("null rule key");
        }
        
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty rule key");
        }
        
        _key = arg;
    }

    public SuppressionRuleAudit ( SuppressionRule rule )
    {
        if ( rule == null )
        {
            throw new NullPointerException ( "tried to initiate audit record with a null object" );
        }

        setApplicationName    ( rule.getApplicationName()    );
        setNodeName           ( rule.getNodeName()           );
        setDatabaseServerName ( rule.getDatabaseServerName() );
        setMessage            ( rule.getMessage()            );
        setNotificationFlag   ( rule.getNotificationFlag()   );
        setNotifyMinutes      ( rule.getNotifyMinutes()      );
        setStartTime          ( rule.getStartTime()          );
        setNotificationTime   ( rule.getNotificationTime()   );
        setEndTime            ( rule.getEndTime()            );
        setMessage            ( rule.getMessage()            );
        setDescription        ( rule.getDescription()        );
        setRemoveOnReboot     ( rule.getRemoveOnReboot()     );
        setSuppressId         ( rule.getSuppressId()         );
        setOwner              ( rule.getOwner()              );
        setNotifyEmail        ( rule.getNotifyEmail()        );
    }

    private long _audit_version_num = 0L;

    public long getAuditVersionNum()
    {
        return _audit_version_num;
    }

    public void setAuditVersionNum ( long audit_version_num )
    {
        _audit_version_num = audit_version_num;
    }

    private String _audit_modified_by = null;

    public String getAuditModifiedBy()
    {
        return _audit_modified_by;
    }

    public void setAuditModifiedBy ( String audit_modified_by )
    {
        _audit_modified_by = audit_modified_by;
    }

    private java.sql.Timestamp _audit_timestamp = new java.sql.Timestamp ( System.currentTimeMillis() );

    public java.sql.Timestamp getAuditTimestamp()
    {
        return _audit_timestamp;
    }

    public void setAuditTimestamp ( java.sql.Timestamp audit_timestamp )
    {
        _audit_timestamp = audit_timestamp;
    }

    /**
     * Return rule key
     * 
     * @return rule key
     */
    public String getRuleKey() {
        return(_key);
    }

    public void setSuppressId ( long suppress_id ) {
        _suppress_id = suppress_id;
    }

    public long getSuppressId() {
        return _suppress_id;
    }

    /**
     * Return suppression notification time
     * 
     * @return suppression notification time
     */
    public Calendar getNotificationTime() {
        return(_notification_time);
    }

    /**
     * Define rule notification time
     * 
     * @param arg rule notification time
     * @throws NullPointerException if null arg
     */
    public void setNotificationTime(Calendar arg) {
        _notification_time = arg;
    }

    /**
     * Return suppression start time
     * 
     * @return suppression start time
     */
    public Calendar getStartTime() {
        return(_start_time);
    }

    /**
     * Define rule start time
     * 
     * @param arg rule start time
     * @throws NullPointerException if null arg
     */
    public void setStartTime(Calendar arg) {
        if (arg == null) {
            throw new NullPointerException("null start time");
        }
        
        _start_time = arg;
    }

    /**
     * Return suppression end time
     * 
     * @return suppression end time
     */
    public Calendar getEndTime() {      
        return(_end_time);
    }

    /**
     * Define rule end time
     * 
     * @param arg rule end time
     * @throws NullPointerException if null arg
     */
    public void setEndTime(Calendar arg) {
        if (arg == null) {
            throw new NullPointerException("null end time");
        }
        
        _end_time = arg;
    }

    /**
     * Return application name
     * 
     * @return application name
     */
    public String getApplicationName() {
        return(_application_name);
    }

    /**
     * Define application name
     * 
     * @param arg application name
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public void setApplicationName(String arg) {
        if (arg == null) {
            throw new NullPointerException("null app name");
        }
    
        /*    
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty app name");
        }
        //*/
        
        _application_name = arg;
    }

    /**
     * Return node name
     * 
     * @return node name
     */
    public String getNodeName() {
        return(_node_name);
    }

    /**
     * Define node name
     * 
     * @param arg node name
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public void setNodeName(String arg) {
        if (arg == null) {
            throw new NullPointerException("null node name");
        }
    
        /*    
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty node name");
        }
        //*/
        
        _node_name = arg;
    }

    /**
     * Return database server name
     * 
     * @return database server name
     */
    public String getDatabaseServerName() {
        return(_db_server);
    }

    /**
     * Define database server name
     * 
     * @param arg database server name
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public void setDatabaseServerName(String arg) {
        if (arg == null) {
            throw new NullPointerException("null DB name");
        }
       
        /* 
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty DB name");
        }
        //*/
        
        _db_server = arg;
    }

    /**
     * Return suppression message
     * 
     * @return suppression message
     */
    public String getMessage() {
        return(_message);
    }

    /**
     * Define suppression message
     * 
     * @param arg suppression message 
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public void setMessage(String arg) {
        if (arg == null) {
            throw new NullPointerException("null message");
        }
       
        /* 
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty message");
        }
        //*/
        
        _message = arg;
    }
    
    /**
     *  Defines the suppression description
     *
     *  @param arg suppression description
     *  @throws NullPointerException if null arg 
     *  @throws IllegalArgumentException if zero length arg 
     */
    public void setDescription ( String arg )
    {
        if (arg == null) {
            throw new NullPointerException("null message");
        }
        
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty message");
        }
    
        _description = arg;    
    }

    /**
     *  Returns the suppression description
     *
     *  @return the suppression description
     */
    public String getDescription()
    {
        return _description;
    }

    /**
     *  Defines the flag to remove suppression on reboot
     *
     *  @param arg whether or not to remove this suppression when the monitored system has been rebooted
     */
    public void setRemoveOnReboot ( boolean arg )
    {
        _remove_on_reboot = arg;
    }

    /**
     *  Returns the "remove on reboot" flag
     *
     *  @return the "remove on reboot" flag
     */
    public boolean getRemoveOnReboot()
    {
        return _remove_on_reboot;
    }

    /**
     *  Defines the deleted flag
     *
     *  @param arg whether or not this suppression is/was deleted before its expiration time
     */
    public void setDeletedFlag ( boolean arg )
    {
        _deleted_flag = arg;
    }

    /**
     *  Returns the deleted flag
     *
     *  @return whether or not this suppression is/was deleted before its expiration time
     */
    public boolean getDeletedFlag()
    {
        return _deleted_flag;
    }

    /**
     *  Defines the notification flag
     *
     *  @param arg whether or not a notification needs to be sent about this suppression
     */
    public void setNotificationFlag ( boolean arg )
    {
        _notify_flag = arg;
    }

    /**
     *  Returns the notification flag
     *
     *  @return whether or not to notify on the pending expiration of this suppression
     */
    public boolean getNotificationFlag()
    {
        return _notify_flag;
    }

    /**
     *  Defines the amount of time (in minutes) that the creator of the suppression rule
     *  should be notified about before expiration
     *
     *  @param minutes amount of warning time (in minutes) to give the creator of the suppression rule
     *  @throws IllegalArgumentException if nonpositive
     */
    public void setNotifyMinutes ( long minutes )
    {
        if ( minutes < 0 )
        {
            throw new IllegalArgumentException ( "Notification time must be non-negative" );
        }

        _notify_minutes = minutes;
    }

    /**
     *  Returns the amount of time (in minute) before 
     */
    public long getNotifyMinutes ()
    {
        return _notify_minutes;
    }

    /**
     *  Defines whether or not the expiration notification has been sent
     */
    public void setIsNotified ( boolean is_notified )
    {
        _is_notified = is_notified;
    }

    public boolean getIsNotified ()
    {
        return _is_notified;
    }

    public void setNotifyEmail ( String notify_email )
    {
        _notify_email = notify_email;
    }

    public String getNotifyEmail()
    {
        return _notify_email;
    }

    public void setOwner ( String owner )
    {
        _owner = owner;
    }

    public String getOwner()
    {
        return _owner;
    }

    /**
     * Return hash code value for this object, employs all fields.
     * 
     * @return hash code value for this object, employs all fields.
     */
    public int hashCode() {
        int result = _start_time.hashCode() * _end_time.hashCode();

        result *= _application_name.hashCode() * _node_name.hashCode();

        result *= _db_server.hashCode() * _message.hashCode();

        return(result);
    }

    /**
     * Return true if the Rules match. Compares all fields.
     * 
     * @param arg candidate to match
     * @return true if successful match
     */
    public boolean equals(Object arg) {
        if (arg == null) {
        return(false);
        }

        SuppressionRuleAudit temp = (SuppressionRuleAudit) arg;

        if (!_start_time.equals(temp.getStartTime())) {
            return(false);
        }

        if (!_end_time.equals(temp.getEndTime())) {
            return(false);
        }

        if (!_application_name.equals(temp.getApplicationName())) {
            return(false);
        }

        if (!_node_name.equals(temp.getNodeName())) {
            return(false);
        }

        if (!_db_server.equals(temp.getDatabaseServerName())) {
            return(false);
        }

        if (!_message.equals(temp.getMessage())) {
            return(false);
        }

        return(true);
    }

    /**
     * Return true if current time is within rule boundaries
     * 
     * @return true if current time is within rule boundaries
     */
    public boolean isActive() {
        if (( true == _deleted_flag ) || ( true == isExpired() ))
        {
            return(false);
        }

        return(isStarted());
    }

    /**
     * Returns true if the end time has already passed.
     * 
     * @return true if the end time has already passed.
     */
    public boolean isExpired() {
        return(_end_time.before(timeNow()));
    }

    /**
     * Returns true if the start time has already passed.
     * 
     * @return true if the start time has already passed.
     */
    public boolean isStarted() {
        return(_start_time.before(timeNow()));
    }
    
    /**
     * Return true if start time earlier than end time
     * 
     * @return true if start time is earlier than end time
     */
    public boolean isValidTime() {
        return(_start_time.before(_end_time));
    }

    /**
     * Compare this object w/the specified object.
     * 
     * @return -1 (less than), 0 (equals), 1 (greater than)
     * @throws ClassCastException if arg cannot be cast
     * @throws NullPointerException if null arg
     */
    public int compareTo(Object arg) {
        SuppressionRuleAudit temp = (SuppressionRuleAudit) arg;

        if (_start_time.after(temp.getStartTime())) {
            return(1);
        }

        if (_start_time.before(temp.getStartTime())) {
            return(-1);
        }

        return(0);
    }

    /**
     * Return object state as a String
     * 
     * @return object state as a String
     */
    public String toString() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        String temp1 = sdf.format(_start_time.getTime());
        String temp2 = sdf.format(_end_time.getTime());

        StringBuilder message = new StringBuilder();
            message.append ( "SuppressionRule ( key=" );
            message.append ( _key );
            message.append ( ", AuditVersionNum=" );
            message.append ( _audit_version_num );
            message.append ( ", StartDate=" );
            message.append ( temp1 );
            message.append ( ", EndDate=" );
            message.append ( temp2 );
            message.append ( ", AppName=" );
            message.append ( _application_name );
            message.append ( ", NodeName=" );
            message.append ( _node_name );
            message.append ( ", DBServer=" );
            message.append ( _db_server );
            message.append ( ", Message=" );
            message.append ( _message );
            message.append ( " )" );
        return message.toString();
    }

    /**
     * Return current time in GMT.
     */
    private Calendar timeNow() {
        return(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
    }

    /**
     * 
     */
    private String _key = DEFAULT_KEY;

    /**
     * 
     */
    private Calendar _start_time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    private Calendar _notification_time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    /**
     * 
     */
    private Calendar _end_time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    /**
     * 
     */
    private String _application_name = "apname";

    /**
     * 
     */
    private String _node_name = "nodename";

    /**
     * 
     */
    private String _db_server = "db";

    /**
     * 
     */
    private String _message = "message";

    /**
     *
     */
    private long _suppress_id = 0L;

    /**
     * 
     */
    private boolean _deleted_flag = false;

    /**
     * 
     */
    private boolean _notify_flag = false;

    /**
     *
     */
    private boolean _remove_on_reboot = false;

    /**
     *
     */
    private long _notify_minutes = 0L;

    /**
     *
     */
    private boolean _is_notified = false;

    /**
     *
     */
    private String _description = null;

    private String _owner = null;
    
    private String _notify_email = null;
    
    /**
     * Default row key value
     */
    public static final String DEFAULT_KEY = "bogus";

    /**
     * Serial version identifier. 
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID = 1L;
}
