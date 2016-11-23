package com.bgi.esm.monitoring.portlets.Suppressions.forms;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;

public class Suppression extends ActionForm implements SuppressionDate
{
    /**
     *
     */
    private static final long serialVersionUID         = 1174332732933L;
    private static final Logger _log                   = Logger.getLogger ( Suppression.class );
    private static final SimpleDateFormat sdf          = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
    protected BackEndFacade bef        = new BackEndFacade();

    public static String SESSION_KEY_SEARCH_RESULTS    = "ESM-EEB-Suppression-Search-Results";

    private String    RowId            = "";
    private long      SuppressId       = 0L;
    private Calendar  StartTime        = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
    private Calendar  EndTime          = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
    private String    AppName          = "";
    private String    NodeName         = "";
    private String    GroupName        = "";
    private String    DbServer         = "";
    private long      NotifyFlg        = 0;
    private long      NotifyMinutes    = 0;
    private long      RemoveOnReboot   = 0;
    private String    Description      = "";
    private String    Message          = "";
    private String    Owner            = "";
    private String    NotifyEmail      = "";
    private String    EndChoiceNum     = null;
    private String    EndChoiceUnit    = null;
    private String    EndChoice        = null;
    private String    StartChoice      = null;

    private int       StartTimeYear    = 0;
    private int       StartTimeMonth   = 0;
    private int       StartTimeDate    = 0;
    private int       StartTimeHour    = 0;
    private int       StartTimeMinute  = 0;
    private int       StartTimeSecond  = 0;
    private int       StartTimeAmPm    = 0;
    private int       EndTimeYear      = 0;
    private int       EndTimeMonth     = 0;
    private int       EndTimeDate      = 0;
    private int       EndTimeHour      = 0;
    private int       EndTimeMinute    = 0;
    private int       EndTimeSecond    = 0;
    private int       EndTimeAmPm      = 0;

    private SuppressionRule suppressionRule = null;

    public Suppression()
    {
        super ();
    }

    public void setSupEndAmpm ( String am_pm )
    {
        String compareString = am_pm.toLowerCase();
        if ( compareString.equals ( "am" ) )
        {
            EndTimeAmPm    = 0;
        }
        else if ( compareString.equals ( "pm" ) )
        {
            EndTimeAmPm    = 1;
        }
        else
        {
            throw new IllegalArgumentException ( "Expected AM/PM, but got: " + am_pm );
        }
    }

    public String getSupEndAmpm()
    {
        return null;
    }

    public void setSupEndMinute ( String end_minute )
    {
        EndTimeMinute = Integer.parseInt ( end_minute );
    }

    public String getSupEndMinute()
    {
        return Integer.toString ( EndTimeMinute );
    }

    public void setSupEndHour ( String end_hour )
    {
        EndTimeHour = Integer.parseInt ( end_hour );
    }

    public String getSupEndHour()
    {
        return Integer.toString ( EndTimeHour );
    }

    public void setSupEndDate ( String end_date )
    {
        EndTimeDate = Integer.parseInt ( end_date );
    }

    public String getSupEndDate()
    {
        return Integer.toString ( EndTimeDate );
    }

    public void setSupEndMonth ( String end_month )
    {
        EndTimeMonth = Integer.parseInt ( end_month );
    }

    public String getSupEndMonth()
    {
        return Integer.toString ( EndTimeMonth );
    }

    public void setSupEndYear ( String end_year )
    {
        EndTimeYear = Integer.parseInt ( end_year );
    }

    public String getSupEndYear()
    {
        return Integer.toString ( EndTimeYear );
    }
    
    public void setSupStartAmpm ( String am_pm )
    {
        String compareString = am_pm.toLowerCase();
        if ( compareString.equals ( "am" ) )
        {
            StartTimeAmPm    = 0;
        }
        else if ( compareString.equals ( "pm" ) )
        {
            StartTimeAmPm    = 1;
        }
        else
        {
            throw new IllegalArgumentException ( "Expected AM/PM, but got: " + am_pm );
        }
    }

    public String getSupStartAmpm()
    {
        return null;
    }

    public void setSupStartMinute ( String start_minute )
    {
        StartTimeMinute = Integer.parseInt ( start_minute );
    }

    public String getSupStartMinute()
    {
        return Integer.toString ( StartTimeMinute );
    }

    public void setSupStartHour ( String start_hour )
    {
        StartTimeHour = Integer.parseInt ( start_hour );
    }

    public String getSupStartHour()
    {
        return Integer.toString ( StartTimeHour );
    }

    public void setSupStartDate ( String start_date )
    {
        StartTimeDate = Integer.parseInt ( start_date );
    }

    public String getSupStartDate()
    {
        return Integer.toString ( StartTimeDate );
    }

    public void setSupStartMonth ( String start_month )
    {
        StartTimeMonth = Integer.parseInt ( start_month );
    }

    public String getSupStartMonth()
    {
        return Integer.toString ( StartTimeMonth );
    }

    public void setSupStartYear ( String start_year )
    {
        StartTimeYear = Integer.parseInt ( start_year );
    }

    public String getSupStartYear()
    {
        return Integer.toString ( StartTimeYear );
    }


    /*
    public void setValue ( SuppressionRule rule )
    {
        suppressionRule = rule;

        setRowId            ( rule.getRowId()              );
        setAppName          ( rule.getApplicationName()    );
        setNodeName         ( rule.getNodeName()           );
        setDbServer         ( rule.getDatabaseServerName() );
        setMessage          ( rule.getMessage()            );
        setDescription      ( rule.getDescription()        );
        setDeletedFlag      ( rule.getDeletedFlag()        );
        setNotificationFlag ( rule.getNotificationFlag()   );
        setNotifyMinutes    ( rule.getNotifyMinutes()      );
        setRemoveOnReboot   ( rule.getRemoveOnReboot()     );
        setGroupName        ( rule.getGroupName()          );
        setSuppressId       ( rule.getSuppressId()         );
        setIsNotified       ( rule.getIsNotified()         );
        setOwner            ( rule.getOwner()              );
        setNotifyEmail      ( rule.getNotifyEmail()        );
    }
    //*/

    /*
    protected SuppressionRule createSuppressionRule()
    {
        if ( null == suppressionRule )
        {
        }
    }
    //*/

    /**
     *  Reset bean properties to their default state, as needed. This method is called 
     *  before the properties are repopulated by the controller
     *
     *  @param mapping The mapping used to select this instance
     *  @param request The servlet request we are processing
     */
    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "Suppression::reset()" );

        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        String index = param_map.get ( "suppress_id" );

        if ( null == index )
        {
            _log.info ( "reset() - Null index found..." );

            return;
        }

        _log.info ( "Initializing object with index=" + index );

        //Suppression object = Suppression.select ( Long.parseLong ( index ) );
        
        SuppressionRule suppressionRule = null;
        try
        {
            suppressionRule = bef.selectSuppressionRuleBySuppressId ( Long.parseLong ( index ) );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( "Exception: " + exception.getMessage(), exception );
        }

        if ( null != suppressionRule )
        {
            setRowId           ( suppressionRule.getRuleKey()            );
            setSuppressId      ( suppressionRule.getSuppressId()         );
            setStartTime       ( suppressionRule.getStartTime()          );
            setEndTime         ( suppressionRule.getEndTime()            );
            setAppName         ( suppressionRule.getApplicationName()    );
            setNodeName        ( suppressionRule.getNodeName()           );
            setGroupName       ( suppressionRule.getGroupName()          );
            setDbServer        ( suppressionRule.getDatabaseServerName() );
            setNotifyFlg       ( suppressionRule.getNotificationFlag()   );
            setNotifyMinutes   ( suppressionRule.getNotifyMinutes()      );
            setRemoveOnReboot  ( suppressionRule.getRemoveOnReboot()     );
            setDescription     ( suppressionRule.getDescription()        );
            setMessage         ( suppressionRule.getMessage()            );
            setOwner           ( suppressionRule.getOwner()              );
            setNotifyEmail     ( suppressionRule.getNotifyEmail()        );

            //Toolkit.setSupStartDate ( this, suppressionRule.getStartTime().getTime() );
            //Toolkit.setSupEndDate   ( this, suppressionRule.getEndTime().getTime()   );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::reset ( StartTime=" );
            message.get().append ( Toolkit.sdf.format ( suppressionRule.getStartTime().getTime() ) );
            message.get().append ( ", EndTime=" );
            message.get().append ( Toolkit.sdf.format ( suppressionRule.getEndTime().getTime() ) );
            message.get().append ( " )" );
            _log.warn ( message.get().toString() );

            LogToFile ( "c:\\test\\Suppression.reset.out", message.get().toString() );

            /*
            setAppName          ( arg.getApplicationName()    );
            setNodeName         ( arg.getNodeName()           );
            setDbServer         ( arg.getDatabaseServerName() );
            setMessage          ( arg.getMessage()            );
            setDescription      ( arg.getDescription()        );
            setDeletedFlag      ( arg.getDeletedFlag()        );
            setNotificationFlag ( arg.getNotificationFlag()   );
            setNotifyMinutes    ( arg.getNotifyMinutes()      );
            setRemoveOnReboot   ( arg.getRemoveOnReboot()     );
            setGroupName        ( arg.getGroupName()          );
            setSuppressId       ( arg.getSuppressId()         );
            setIsNotified       ( arg.getIsNotified()         );
            setOwner            ( arg.getOwner()              );
            setNotifyEmail      ( arg.getNotifyEmail()        );
            //*/
        }
        else
        {
            _log.error ( "Could not find object in database with suppress_id=" + index );
        }
    }

    /**
     *  Validate the properties that have been set for this HTTP request, and return an ActionErrors 
     *  object that encapsulates any validation errors that have been found.
     *
     *  @param mapping The mapping used to select this instance
     *  @param request The servlet request we are processing
     *  @return null if no errors are found,an ActionErrors object with recorded error messages otherwise
     */
    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "Suppression::validate()" );
        ActionErrors errors = new ActionErrors();

        Date suppressionStartDate = Toolkit.getSupStartDate ( this );
        Date suppressionEndDate   = Toolkit.getSupEndDate ( this );
        Date currentDate          = new Date();

        //  Check for valid start time

        //  Check for valid end time
        if ( currentDate.after ( suppressionEndDate ) )
        {
            errors.add ( "end-date-before-current-time", new ActionMessage ( "Please choose an end time that is after the current time", false ) );
        }

        if ( suppressionEndDate.before ( suppressionStartDate ))
        {
            errors.add ( "end-date-before-start-date", new ActionMessage ( "Please choose an end time that is after the start time", false ) );
        }

        //  Check for valid description
        if (( null == Description ) || ( Description.length() < 4 ))
        {
            errors.add ( "description", new ActionMessage ( "The description is too short", false ) );
        }
        
        //  Check for valid node/app combination
        if (( null == NodeName ) && ( null == AppName ))
        {
            errors.add ( "null-app-null-node", new ActionMessage ( "Please specify either an application name or a node name", false ) );
        }

        //  Check for valid DB server/message combination

        //  Check for email notification

        return errors;
    }

    /**
     *  Setter method for the 'row_id' column
     *
     * @param row_id  The new value for the 'row_id' column
     */
    public void setRowId ( String row_id )
    {
        this.RowId = row_id;
    }

    /**
     *  Getter method for the 'row_id' column
     *  @return The new value for the 'row_id' column
     */
    public String getRowId ()
    {
        return RowId;
    }

    /**
     *  Setter method for the 'suppress_id' column
     *
     * @param suppress_id  The new value for the 'suppress_id' column
     */
    public void setSuppressId ( long suppress_id )
    {
        this.SuppressId = suppress_id;
    }

    /**
     *  Getter method for the 'suppress_id' column
     *  @return The new value for the 'suppress_id' column
     */
    public long getSuppressId ()
    {
        return SuppressId;
    }

    /**
     *  Setter method for the 'start_time' column
     *
     * @param start_time  The new value for the 'start_time' column
     */
    public void setStartTime ( Calendar start_time )
    {
        this.StartTime = start_time;

        StartTimeYear    = StartTime.get ( Calendar.YEAR   );
        StartTimeMonth   = StartTime.get ( Calendar.MONTH  );
        StartTimeHour    = StartTime.get ( Calendar.HOUR   );
        StartTimeMinute  = StartTime.get ( Calendar.MINUTE );
        StartTimeSecond  = StartTime.get ( Calendar.SECOND );
        StartTimeAmPm    = StartTime.get ( Calendar.AM_PM  );
    }

    /**
     *  Getter method for the 'start_time' column
     *  @return The new value for the 'start_time' column
     */
    public Calendar getStartTime ()
    {
        StartTime.set ( Calendar.YEAR,   StartTimeYear   );
        StartTime.set ( Calendar.MONTH,  StartTimeMonth  );
        StartTime.set ( Calendar.HOUR,   StartTimeHour   );
        StartTime.set ( Calendar.MINUTE, StartTimeMinute );
        StartTime.set ( Calendar.SECOND, StartTimeSecond );
        StartTime.set ( Calendar.AM_PM,  StartTimeAmPm   );

        return StartTime;
    }

    /**
     *  Setter method for the 'end_time' column
     *
     * @param end_time  The new value for the 'end_time' column
     */
    public void setEndTime ( Calendar end_time )
    {
        this.EndTime = end_time;

        EndTimeYear    = EndTime.get ( Calendar.YEAR   );
        EndTimeMonth   = EndTime.get ( Calendar.MONTH  );
        EndTimeHour    = EndTime.get ( Calendar.HOUR   );
        EndTimeMinute  = EndTime.get ( Calendar.MINUTE );
        EndTimeSecond  = EndTime.get ( Calendar.SECOND );
        EndTimeAmPm    = EndTime.get ( Calendar.AM_PM  );
    }

    /**
     *  Getter method for the 'end_time' column
     *  @return The new value for the 'end_time' column
     */
    public Calendar getEndTime ()
    {
        EndTime.set ( Calendar.YEAR,   EndTimeYear   );
        EndTime.set ( Calendar.MONTH,  EndTimeMonth  );
        EndTime.set ( Calendar.HOUR,   EndTimeHour   );
        EndTime.set ( Calendar.MINUTE, EndTimeMinute );
        EndTime.set ( Calendar.SECOND, EndTimeSecond );
        EndTime.set ( Calendar.AM_PM,  EndTimeAmPm   );

        return EndTime;
    }

    public void setStartTimeYear ( int year )
    {
        StartTimeYear = year;
    }

    public int getStartTimeYear()
    {
        return StartTimeYear;
    }

    public void setStartTimeMonth ( int month )
    {
        StartTimeMonth = month;
    }

    public int getStartTimeMonth()
    {
        return StartTimeMonth;
    }

    public void setStartTimeDate ( int date )
    {
        StartTimeDate = date;
    }

    public int getStartTimeDate()
    {
        return StartTimeDate;
    }

    public void setStartTimeHour ( int hour )
    {
        StartTimeHour = hour;
    }

    public int getStartTimeHour()
    {
        return StartTimeHour;
    }

    public void setStartTimeMinute ( int minute )
    {
        StartTimeMinute = minute;
    }

    public int getStartTimeMinute()
    {
        return StartTimeMinute;
    }

    public void setStartTimeSecond ( int second )
    {
        StartTimeSecond = second;
    }

    public int getStartTimeSecond()
    {
        return StartTimeSecond;
    }

    public void setStartTimeAmPm ( int am_pm )
    {
        StartTimeAmPm = am_pm;
    }

    public int getStartTimeAmPm()
    {
        return StartTimeAmPm;
    }

    public void setEndTimeYear ( int year )
    {
        EndTimeYear = year;
    }

    public int getEndTimeYear()
    {
        return EndTimeYear;
    }

    public void setEndTimeMonth ( int month )
    {
        EndTimeMonth = month;
    }

    public int getEndTimeMonth()
    {
        return EndTimeMonth;
    }

    public void setEndTimeDate ( int date )
    {
        EndTimeDate = date;
    }

    public int getEndTimeDate()
    {
        return EndTimeDate;
    }

    public void setEndTimeHour ( int hour )
    {
        EndTimeHour = hour;
    }

    public int getEndTimeHour()
    {
        return EndTimeHour;
    }

    public void setEndTimeMinute ( int minute )
    {
        EndTimeMinute = minute;
    }

    public int getEndTimeMinute()
    {
        return EndTimeMinute;
    }

    public void setEndTimeSecond ( int second )
    {
        EndTimeSecond = second;
    }

    public int getEndTimeSecond()
    {
        return EndTimeSecond;
    }

    public void setEndTimeAmPm ( int am_pm )
    {
        EndTimeAmPm = am_pm;
    }

    public int getEndTimeAmPm()
    {
        return EndTimeAmPm;
    }

    /**
     *  Setter method for the 'app_name' column
     *
     * @param app_name  The new value for the 'app_name' column
     */
    public void setAppName ( String app_name )
    {
        this.AppName = app_name;
    }

    /**
     *  Getter method for the 'app_name' column
     *  @return The new value for the 'app_name' column
     */
    public String getAppName ()
    {
        return AppName;
    }

    /**
     *  Setter method for the 'node_name' column
     *
     * @param node_name  The new value for the 'node_name' column
     */
    public void setNodeName ( String node_name )
    {
        this.NodeName = node_name;
    }

    /**
     *  Getter method for the 'node_name' column
     *  @return The new value for the 'node_name' column
     */
    public String getNodeName ()
    {
        return NodeName;
    }

    /**
     *  Setter method for the 'group_name' column
     *
     * @param group_name  The new value for the 'group_name' column
     */
    public void setGroupName ( String group_name )
    {
        this.GroupName = group_name;
    }

    /**
     *  Getter method for the 'group_name' column
     *  @return The new value for the 'group_name' column
     */
    public String getGroupName ()
    {
        return GroupName;
    }

    /**
     *  Setter method for the 'db_server' column
     *
     * @param db_server  The new value for the 'db_server' column
     */
    public void setDbServer ( String db_server )
    {
        this.DbServer = db_server;
    }

    /**
     *  Getter method for the 'db_server' column
     *  @return The new value for the 'db_server' column
     */
    public String getDbServer ()
    {
        return DbServer;
    }

    /**
     *  Setter method for the 'notify_flg' column
     *
     * @param notify_flg  The new value for the 'notify_flg' column
     */
    public void setNotifyFlg ( long notify_flg )
    {
        this.NotifyFlg = notify_flg;
    }

    public void setNotifyFlg ( boolean value )
    {
        this.NotifyFlg = ( value ) ? 1 : 0;
    }

    /**
     *  Getter method for the 'notify_flg' column
     *  @return The new value for the 'notify_flg' column
     */
    public long getNotifyFlg ()
    {
        return NotifyFlg;
    }

    /**
     *  Setter method for the 'notify_minutes' column
     *
     * @param notify_minutes  The new value for the 'notify_minutes' column
     */
    public void setNotifyMinutes ( long notify_minutes )
    {
        this.NotifyMinutes = notify_minutes;
    }

    /**
     *  Getter method for the 'notify_minutes' column
     *  @return The new value for the 'notify_minutes' column
     */
    public long getNotifyMinutes ()
    {
        return NotifyMinutes;
    }

    /**
     *  Setter method for the 'remove_on_reboot' column
     *
     * @param remove_on_reboot  The new value for the 'remove_on_reboot' column
     */
    public void setRemoveOnReboot ( long remove_on_reboot )
    {
        this.RemoveOnReboot = remove_on_reboot;
    }

    public void setRemoveOnReboot ( boolean value )
    {
        this.RemoveOnReboot = ( value ) ? 1 : 0 ;
    }

    /**
     *  Getter method for the 'remove_on_reboot' column
     *  @return The new value for the 'remove_on_reboot' column
     */
    public long getRemoveOnReboot ()
    {
        return RemoveOnReboot;
    }

    /**
     *  Setter method for the 'description' column
     *
     * @param description  The new value for the 'description' column
     */
    public void setDescription ( String description )
    {
        this.Description = description;
    }

    /**
     *  Getter method for the 'description' column
     *  @return The new value for the 'description' column
     */
    public String getDescription ()
    {
        return Description;
    }

    /**
     *  Setter method for the 'message' column
     *
     * @param message  The new value for the 'message' column
     */
    public void setMessage ( String message )
    {
        this.Message = message;
    }

    /**
     *  Getter method for the 'message' column
     *  @return The new value for the 'message' column
     */
    public String getMessage ()
    {
        if (( null != Message ) && ( Message.trim().length() > 0 ))
        {
            return(Message);
        }
        else
        {
            return "-";
        }
    }

    public void setOwner ( String owner )
    {
        Owner = owner;
    }

    public String getOwner()
    {
        return Owner;
    }

    public void setNotifyEmail ( String notify_email )
    {
        NotifyEmail = notify_email;
    }

    public String getNotifyEmail()
    {
        return NotifyEmail;
    }

    public void setEndChoiceNum ( String end_choice_num )
    {
        EndChoiceNum = end_choice_num;
    }

    public String getEndChoiceNum ()
    {
        return EndChoiceNum;
    }

    public void setEndChoiceUnit ( String end_choice_unit )
    {
        EndChoiceUnit = end_choice_unit;
    }

    public String getEndChoiceUnit()
    {
        return EndChoiceUnit;
    }

    public void setEndChoice ( String end_choice )
    {
        EndChoice = end_choice;
    }

    public String getEndChoice()
    {
        return EndChoice;
    }

    public void setStartChoice ( String start_choice )
    {
        StartChoice = start_choice;
    }

    public String getStartChoice()
    {
        return StartChoice;
    }


    /**
     *  Returns the number of existing records
     *
     *  @return the number of existing records
     */
    public static long countRecords()
    {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            long num_records = ((Number) session.createSQLQuery( "select count(*) from suppression" ).uniqueResult() ).longValue();
        tx.commit();

        return num_records;
    }

    /**
     *  Returns the total number of changes to this record
     *
     *  @return the total number of changes to this record
     */
    public static long countPreviousVersions ( long SuppressId )
    {
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "SELECT count(*) FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit c WHERE c.SuppressId = :SuppressId" );
            query.setParameter ( "SuppressId", SuppressId );

            long num_records = ((Number) query.uniqueResult()).longValue();
        tx.commit();

        return num_records;
    }

    /**
     *  Attempt to update/insert this object into the database.
     *
     *  @return true if updated, false otherwise
     */
    /*
    public boolean insertOrUpdate ( String username ) throws BackEndFailure
    {
    }
    //*/

    public boolean insertOrUpdate ( String username )
    {
        boolean was_update = false;
        Session session    = HibernateUtil.getCurrentSession();
        Transaction tx     = null;
        Suppression object = null;
       
        if ( SuppressId  != 0 )
        {
            object = (Suppression) Suppression.select ( SuppressId );
        }

        if ( null != object )
        {
            //  Hibernate 3.2 requires that only one instance of the object be present (does row-level locking for us).
            //  Must use this object when updating/saving

            //  Update existing record in database
            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Updating Suppression ( SuppressId ) = " );
                message.get().append ( SuppressId );

                _log.info ( message.get().toString() );
            }

            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                //  Update the current object
                object.setRowId            ( getRowId ()            );
                object.setSuppressId       ( getSuppressId ()       );
                object.setStartTime        ( getStartTime ()        );
                object.setEndTime          ( getEndTime ()          );
                object.setAppName          ( getAppName ()          );
                object.setNodeName         ( getNodeName ()         );
                object.setGroupName        ( getGroupName ()        );
                object.setDbServer         ( getDbServer ()         );
                object.setNotifyFlg        ( getNotifyFlg ()        );
                object.setNotifyMinutes    ( getNotifyMinutes ()    );
                object.setRemoveOnReboot   ( getRemoveOnReboot ()   );
                object.setDescription      ( getDescription ()      );
                object.setMessage          ( getMessage ()          );
                object.setOwner            ( getOwner()             );
                object.setNotifyEmail      ( getNotifyEmail()       );
                session.update ( object );
            tx.commit();

            //  Create the audit entry
            SuppressionAudit audit = null;
        
            audit = new SuppressionAudit();
            long num_previous_versions = Suppression.countPreviousVersions ( SuppressId );

            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                audit.setAuditTimestamp  ( Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ))    );
                audit.setAuditVersionNum ( 1 + num_previous_versions );
                audit.setAuditModifiedBy ( username                  );
                audit.setRowId            ( object.getRowId ()            );
                audit.setSuppressId       ( object.getSuppressId ()       );
                audit.setStartTime        ( object.getStartTime ()        );
                audit.setEndTime          ( object.getEndTime ()          );
                audit.setAppName          ( object.getAppName ()          );
                audit.setNodeName         ( object.getNodeName ()         );
                audit.setGroupName        ( object.getGroupName ()        );
                audit.setDbServer         ( object.getDbServer ()         );
                audit.setNotifyFlg        ( object.getNotifyFlg ()        );
                audit.setNotifyMinutes    ( object.getNotifyMinutes ()    );
                audit.setRemoveOnReboot   ( object.getRemoveOnReboot ()   );
                audit.setDescription      ( object.getDescription ()      );
                audit.setMessage          ( object.getMessage ()          );
                audit.setOwner            ( object.getOwner()             );
                audit.setNotifyEmail      ( object.getNotifyEmail()       );
                session.save ( audit );
            tx.commit();

            was_update = true;
        }
        else
        {
            _log.info ( "Inserting new Suppression object into database" );

            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( this );
            tx.commit();

            was_update = false;
        }

        return was_update;
    }

    /**
     *  Select a particular instance of a Suppression by its index
     *
     *  @return the instance of the object if available, null otherwise
     */
    public static Suppression select ( long suppress_id )
    {
        List <Suppression> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression c WHERE c.SuppressId = :SuppressId" );
            query = query.setParameter ( "SuppressId", suppress_id );

            list = query.list();
        tx.commit();

        if ( list.size() == 1 )
        {
            return (Suppression) list.get ( 0 );
        }
        else if ( list.size() == 0 )
        {
            return null;
        }
        else
        {
            throw new IllegalStateException ( "Multiple suppressions ( num=" + list.size() + " ) for suppress_id=" + suppress_id );
        }
    }

    /**
     *  Retrieves a list of objects (in descending order by time of creation) of all previous
     *  versions of this object
     *
     *  @return list of objects representing all previous versions of this object in descending
     *          order by time of creation
     */
    public static List <SuppressionAudit> retrievePreviousVersions ( long suppress_id )
    {
        List <SuppressionAudit> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit c WHERE c.SuppressId = :SuppressId ORDER BY AuditVersionNum DESC" );
            query = query.setParameter ( "SuppressId", suppress_id );

            list = query.list();
        tx.commit();

        return list;
    }

    /**
     *  Retrieves all objects in the database
     *
     *  @return list of all objects in the database
     */
    public static List <Suppression> selectAll ()
    {
        List <Suppression> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression c" );

            list = query.list();
        tx.commit();

        return list;
    }

    /**
     *  Retrieves all active suppressions
     *
     *  @return list of all active suppressions in the database
     */
    public static List <Suppression> selectAllActive( int offset )
    {
        Calendar timestamp      = Calendar.getInstance();
        timestamp.add ( Calendar.MILLISECOND, offset );
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( Suppression.class.getName() );
            message.get().append ( "::selectAllActive( offset=" );
            message.get().append ( offset );
            message.get().append ( " ) - search timestamp is " );
            message.get().append ( sdf.format ( timestamp.getTime() ) );

            _log.info ( message.get().toString() );
        }
        List <Suppression> list = null;
        Session session         = HibernateUtil.getCurrentSession();
        Query query             = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression c WHERE c.StartTime < :CurrentTime AND c.EndTime > :CurrentTime" );
            query = query.setParameter ( "CurrentTime", timestamp );

            list = query.list();
        tx.commit();

        return list;
    }

    public static List <Suppression> selectAllActiveForUser ( int offset, String username )
    {
        Calendar timestamp = Calendar.getInstance();
        timestamp.add ( Calendar.MILLISECOND, offset );
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( Suppression.class.getName() );
            message.get().append ( "::selectAllActiveForUser( offset=" );
            message.get().append ( offset );
            message.get().append ( ", username=" );
            message.get().append ( username );
            message.get().append ( " ) - search timestamp is " );
            message.get().append ( sdf.format ( timestamp.getTime() ) );

            _log.info ( message.get().toString() );
        }
        List <Suppression> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression c WHERE c.StartTime < :CurrentTime AND c.EndTime > :CurrentTime AND c.Owner=:Owner ORDER BY c.SuppressId DESC" );
            query = query.setParameter ( "CurrentTime", timestamp );
            query = query.setParameter ( "Owner",       username  );

            list = query.list();
        tx.commit();

        return list;
    }

    /**
     *  Retrieves "pages" of records of sepcified sizes
     *
     *  @return list of all objects in the database that belong to a "page"
     */
    public static List <Suppression> retrieveRecordsPage ( int page_num, int num_results_per_page )
    {
        List <Suppression> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression" );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }

    /**
     *  Retrieves the audit entries for this object
     *
     *  @return a List of audit entries 
     */
    public List <SuppressionAudit> retrievePreviousVersions()
    {
        return Suppression.retrievePreviousVersions ( SuppressId );
    }

    private static void LogToFile ( String filename, String text )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( sdf.format ( new Date() ).getBytes() );
                outfile.write ( " - ".getBytes() );
                outfile.write ( text.getBytes() );
                outfile.write ( "\n".getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            _log.error ( exception );
        }

    }
}
