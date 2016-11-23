package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Value object for suppression rule.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (linden)
 */
public final class SuppressionRule implements Comparable, Serializable {
    /**
     * Serial version identifier. 
     * Be sure to update this when the class is updated.
     */
    public static final long serialVersionUID                = 1L;

    final public static int REMOVE_ON_REBOOT_DOES_NOT_MATTER = 0;
    final public static int REMOVE_ON_REBOOT_TRUE            = 1;
    final public static int REMOVE_ON_REBOOT_FALSE           = 2;

    final public static int WAS_DELETED_DOES_NOT_MATTER      = 0;
    final public static int WAS_DELETED_TRUE                 = 1;
    final public static int WAS_DELETED_FALSE                = 2;

    /**
     * Default row key value
     */
    public static final String DEFAULT_KEY = "bogus";

    private String _key                    = DEFAULT_KEY;
    private Calendar _start_time           = null;
    private Calendar _end_time             = null;
    private Calendar _notification_time    = null;
    private String _application_name       = "";
    private String _node_name              = "";
    private String _group_name             = "";
    private String _db_server              = "db";
    private String _message                = "";
    private String _description            = null;
    private String _notify_email           = "";
    private String _owner                  = "";
    private boolean _deleted_flag          = false;
    private boolean _notify_flag           = false;
    private boolean _remove_on_reboot      = false;
    private boolean _is_notified           = false;
    private long _suppress_id              = 0L;
    private long _notify_minutes           = 0L;
    
    /**
     * empty ctor
     */
    public SuppressionRule() 
    {
        initializeTime();
    }
    
    /**
     * ctor w/defined row key
     *
     * @param arg row key
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public SuppressionRule(String arg) {
        if (arg == null) {
            throw new NullPointerException("null rule key");
        }
        
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty rule key");
        }
        
        _key = arg;

        initializeTime();
    }

    /**
     *  Calld by the constructors to initialize the times of this suppression rule
     *  to start from the current system time and to last 4 hours.
     */
    private void initializeTime()
    {
        //_start_time = Calendar.getInstance ( TimeZone.getTimeZone ("GMT") );
        _start_time = Calendar.getInstance ();

        Date date   = new Date ( _start_time.getTime().getTime() + 1000*60*60*4 );

        // Default to a 4-hour suppression window
        //_end_time   = Calendar.getInstance( TimeZone.getTimeZone ("GMT" ) );
        _end_time   = Calendar.getInstance();
        _end_time.setTimeInMillis ( date.getTime() );
    }

    /**
     * Return rule key
     * 
     * @return rule key
     */
    public String getRuleKey() {
        return(_key);
    }

    public void setRuleKey ( String arg )
    {
        _key = arg;
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
     * Return the message group name
     *
     * @return message group name
     */
    public String getGroupName()
    {
        return _group_name;
    }

    /**
     * Define the message group name
     * 
     * @param arg the message group name
     * @throws NullPointerException if null arg 
     * @throws IllegalArgumentException if zero length arg 
     */
    public void setGroupName ( String arg )
    {
        if (arg == null) {
            throw new NullPointerException("null node name");
        }
       
        /* 
        if (arg.length() < 1) {
            throw new IllegalArgumentException("empty node name");
        }
        //*/

        _group_name = arg;
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
     *  Defines the suppression ID
     */
    public void setSuppressId ( long suppress_id )
    {
        _suppress_id = suppress_id;
    }

    public long getSuppressId ()
    {
        return _suppress_id;
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

    public void setOwner ( String owner )
    {
        _owner = owner;
    }

    public String getOwner()
    {
        return _owner;
    }

    public void setNotifyEmail ( String notify_email )
    {
        _notify_email = notify_email;
    }

    public String getNotifyEmail()
    {
        return _notify_email;
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

        SuppressionRule temp = (SuppressionRule) arg;

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
    public boolean isActive(int timezone_offset) {
        if (( true == _deleted_flag ) || ( true == isExpired(timezone_offset) ))
        {
            return(false);
        }

        return(isStarted(timezone_offset));
    }

    /**
     * Returns true if the end time has already passed.
     * 
     * @return true if the end time has already passed.
     */
    public boolean isExpired(int timezone_offset) {
        Calendar compareTime = Calendar.getInstance();
        compareTime.setTime ( _end_time.getTime() );
        compareTime.add ( Calendar.MILLISECOND, timezone_offset );

        return(compareTime.before(timeNow()));
    }

    /**
     * Returns true if the start time has already passed.
     * 
     * @return true if the start time has already passed.
     */
    public boolean isStarted(int timezone_offset) {
        Calendar compareTime = Calendar.getInstance();
        compareTime.setTime ( _start_time.getTime() );
        compareTime.add ( Calendar.MILLISECOND, timezone_offset );

        return(compareTime.before(timeNow()));
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
        SuppressionRule temp = (SuppressionRule) arg;

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
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        String temp1 = sdf.format(_start_time.getTime());
        String temp2 = sdf.format(_end_time.getTime());

        StringBuilder message = new StringBuilder();
            message.append ( "SuppressionRule ( key=" );
            message.append ( _key );
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

        //return("SuppressionRule:"+ _key + ":" + temp1 + " " + temp2 + " " + _application_name + " " + _node_name + " " + _db_server + " " + _message);
    }

    /**
     * Return current time in GMT.
     */
    private Calendar timeNow() {
        int daylight_savings_offset = TimeZone.getTimeZone( "America/Los_Angeles" ).getDSTSavings();
        int timezone_offset         = TimeZone.getTimeZone( "America/Los_Angeles" ).getRawOffset();
        Calendar instance = Calendar.getInstance();
        instance.add ( Calendar.MILLISECOND, -timezone_offset+daylight_savings_offset );

        return instance;
    }


    public static List <SuppressionRule> searchSuppressionRules ( List <SuppressionRule> list, String user, String description, String app_name, String node_name, String db_server, 
            String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  )
    {
        List <SuppressionRule> return_list  = new ArrayList <SuppressionRule> ();
        Iterator <SuppressionRule> iterator = list.iterator();
        SuppressionRule rule   = null;

        Pattern p_user         = ( null != user         )? Pattern.compile ( ".*" + user         + ".*" ) : null;
        Pattern p_description  = ( null != description  )? Pattern.compile ( ".*" + description  + ".*" ) : null;
        Pattern p_app_name     = ( null != app_name     )? Pattern.compile ( ".*" + app_name     + ".*" ) : null;
        Pattern p_node_name    = ( null != node_name    )? Pattern.compile ( ".*" + node_name    + ".*" ) : null;
        Pattern p_db_server    = ( null != db_server    )? Pattern.compile ( ".*" + db_server    + ".*" ) : null;
        Pattern p_message_text = ( null != message_text )? Pattern.compile ( ".*" + message_text + ".*" ) : null;

        while ( iterator.hasNext() )
        {
            rule = (SuppressionRule) iterator.next();

            boolean match = true;

            if (( true == match ) && ( null != user ))
            {
                match = p_user.matcher ( rule.getOwner() ).matches();
            }

            if (( true == match ) && ( null != description ))
            {
                match = p_description.matcher ( rule.getDescription() ).matches();
            }

            if (( true == match ) && ( null != app_name ))
            {
                match = p_app_name.matcher ( rule.getApplicationName() ).matches();
            }

            if (( true == match ) && ( null != node_name ))
            {
                match = p_node_name.matcher ( rule.getNodeName() ).matches();
            }

            if (( true == match ) && ( null != db_server ))
            {
                match = p_db_server.matcher ( rule.getDatabaseServerName() ).matches();
            }

            if (( true == match ) && ( null != message_text ))
            {
                match = p_message_text.matcher ( rule.getMessage() ).matches();
            }

            if (( true == match ) && ( null != start_time ))
            {
                match = start_time.before ( rule.getStartTime() );
            }

            if (( true == match ) && ( null != end_time ))
            {
                match = end_time.after ( rule.getEndTime() );
            }

            if (( true == match ) && ( SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER != remove_on_reboot ))
            {
                if ( remove_on_reboot == SuppressionRule.REMOVE_ON_REBOOT_TRUE )
                {
                    match = match && rule.getRemoveOnReboot();
                }
                else if ( remove_on_reboot == SuppressionRule.REMOVE_ON_REBOOT_FALSE )
                {
                    match = match && !rule.getRemoveOnReboot();
                }
            }

            if (( true == match ) && ( SuppressionRule.WAS_DELETED_DOES_NOT_MATTER != was_deleted ))
            {
                if ( was_deleted == SuppressionRule.WAS_DELETED_TRUE )
                {
                    match = match && rule.getDeletedFlag();
                }
                else if ( was_deleted == SuppressionRule.WAS_DELETED_FALSE )
                {
                    match = match && !rule.getDeletedFlag();
                }
            }

            if ( true == match )
            {
                return_list.add ( rule );
            }
        }

        return return_list;
    }
}
