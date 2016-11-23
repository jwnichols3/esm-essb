package com.bgi.esm.monitoring.portlets.Suppressions.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;

public class SuppressionAudit extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1174332732933L;
    private static final Logger _log                   = Logger.getLogger ( SuppressionAudit.class );


    private long      AuditId          = 0L;
    private long      AuditVersionNum  = 0L;
    private Calendar  AuditTimestamp   = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
    private String    AuditModifiedBy  = null;
    private String    RowId            = null;
    private long      SuppressId       = 0L;
    private Calendar  StartTime        = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
    private Calendar  EndTime          = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
    private String    AppName          = null;
    private String    NodeName         = null;
    private String    GroupName        = null;
    private String    DbServer         = null;
    private long      NotifyFlg        = 0;
    private long      NotifyMinutes    = 0;
    private long      RemoveOnReboot   = 0;
    private String    Description      = null;
    private String    Message          = null;
    private String    Owner            = null;
    private String    NotifyEmail      = null;


    public SuppressionAudit()
    {
        super ();
    }

    /**
     *  Reset bean properties to their default state, as needed. This method is called 
     *  before the properties are repopulated by the controller
     *
     *  @param mapping The mapping used to select this instance
     *  @param request The servlet request we are processing
     */
    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "SuppressionAudit::reset()" );
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
        _log.debug ( "SuppressionAudit::validate()" );
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    /**
     *  Setter method for the 'AuditId' column
     *
     * @param AuditId  The new value for the 'AuditId' column
     */
    public void setAuditId ( long AuditId )
    {
        this.AuditId = AuditId;
    }

    /**
     *  Getter method for the 'AuditId' column
     *  @return The new value for the 'AuditId' column
     */
    public long getAuditId ()
    {
        return AuditId;
    }

    /**
     *  Setter method for the 'AuditVersionNum' column
     *
     * @param AuditVersionNum  The new value for the 'AuditVersionNum' column
     */
    public void setAuditVersionNum ( long AuditVersionNum )
    {
        this.AuditVersionNum = AuditVersionNum;
    }

    /**
     *  Getter method for the 'AuditVersionNum' column
     *  @return The new value for the 'AuditVersionNum' column
     */
    public long getAuditVersionNum ()
    {
        return AuditVersionNum;
    }

    /**
     *  Setter method for the 'AuditTimestamp' column
     *
     * @param AuditTimestamp  The new value for the 'AuditTimestamp' column
     */
    public void setAuditTimestamp ( Calendar AuditTimestamp )
    {
        this.AuditTimestamp = AuditTimestamp;
    }

    /**
     *  Getter method for the 'AuditTimestamp' column
     *  @return The new value for the 'AuditTimestamp' column
     */
    public Calendar getAuditTimestamp ()
    {
        return AuditTimestamp;
    }

    /**
     *  Setter method for the 'AuditModifiedBy' column
     *
     * @param AuditModifiedBy  The new value for the 'AuditModifiedBy' column
     */
    public void setAuditModifiedBy ( String AuditModifiedBy )
    {
        this.AuditModifiedBy = AuditModifiedBy;
    }

    /**
     *  Getter method for the 'AuditModifiedBy' column
     *  @return The new value for the 'AuditModifiedBy' column
     */
    public String getAuditModifiedBy ()
    {
        return AuditModifiedBy;
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
    }

    /**
     *  Getter method for the 'start_time' column
     *  @return The new value for the 'start_time' column
     */
    public Calendar getStartTime ()
    {
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
    }

    /**
     *  Getter method for the 'end_time' column
     *  @return The new value for the 'end_time' column
     */
    public Calendar getEndTime ()
    {
        return EndTime;
    }

    /**
     *  Setter method for the 'ap_name' column
     *
     * @param ap_name  The new value for the 'ap_name' column
     */
    public void setAppName ( String ap_name )
    {
        this.AppName = ap_name;
    }

    /**
     *  Getter method for the 'ap_name' column
     *  @return The new value for the 'ap_name' column
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
        return Message;
    }

    public void setOwner ( String owner )
    {
        this.Owner = owner;
    }

    public String getOwner()
    {
        return Owner;
    }

    public void setNotifyEmail ( String owner )
    {
        this.NotifyEmail = owner;
    }

    public String getNotifyEmail()
    {
        return NotifyEmail;
    }

    /**
     *  Attempt to update/insert this object into the database.
     *
     *  @return true if updated, false otherwise
     */
    public boolean insertOrUpdate( String username )
    {
        boolean was_updated = false;

        Session session     = HibernateUtil.getCurrentSession();
        Transaction tx      = session.beginTransaction();

        if ( session.contains ( this ) )
        {
            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Updating SuppressionAudit ( SuppressId, AuditVersionNum ) = ( " );
                message.get().append ( SuppressId );
                message.get().append ( ", " );
                message.get().append ( AuditVersionNum );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }

            session.update ( this );

            was_updated = true;
        }
        else
        {
            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Inserting audit entry for SuppressionAudit ( SuppressId ) = ( " );
                message.get().append ( SuppressId );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }

            session.save ( this );

            was_updated = false;
        }

        return was_updated;
    }

    /**
     *  Attempt to retrieve an audit entry.
     *
     *  @return the audit entry if it exists, null otherwise
     */
    public static SuppressionAudit select ( long suppress_id, long audit_version_num )
    {
        SuppressionAudit object = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit c WHERE c.SuppressId = :SuppressId AND c.AuditVersionNum = :AuditVersionNum" );
            query = query.setParameter ( "SuppressId", suppress_id );
            query = query.setParameter ( "AuditVersionNum", audit_version_num );
            object = (SuppressionAudit) query.uniqueResult();
        tx.commit();

        return object;
    }

    /**
     *  Returns the a "paged" list of audit objects
     *
     *  @return list of all audit objects in the database that belong to a "page"
     */
    public static List <SuppressionAudit> retrieveRecordsPage ( long SuppressId, int page_num, int num_results_per_page )
    {
        List <SuppressionAudit> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit c WHERE c.SuppressId = :SuppressId ORDER BY AuditVersionNum DESC" );
            query.setParameter   ( "SuppressId", SuppressId );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults  ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }
}
