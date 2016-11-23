package com.bgi.esm.monitoring.portlets.Throttle.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;

public class ThrottleAudit extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1175099742854L;
    private static final Logger _log                   = Logger.getLogger ( ThrottleAudit.class );


    private long      AuditId         = 0L;
    private long      AuditVersionNum = 0L;
    private Calendar  AuditTimestamp  = Calendar.getInstance();
    private String    AuditModifiedBy = null;
    private long      RuleId       = 0L;
    private String    RowId        = null;
    private String    BgiGroup     = null;
    private long      StormLevel   = 0;
    private long      Duration     = 0;
    private long      Threshold    = 0;
    private String    Action       = null;
    private char      MessageFlag  = 0;


    public ThrottleAudit()
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
        _log.debug ( "ThrottleAudit::reset()" );
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
        _log.debug ( "ThrottleAudit::validate()" );
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
     *  Setter method for the 'rule_id' column
     *
     * @param rule_id  The new value for the 'rule_id' column
     */
    public void setRuleId ( long rule_id )
    {
        this.RuleId = rule_id;
    }

    /**
     *  Getter method for the 'rule_id' column
     *  @return The new value for the 'rule_id' column
     */
    public long getRuleId ()
    {
        return RuleId;
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
     *  Setter method for the 'bgi_group' column
     *
     * @param bgi_group  The new value for the 'bgi_group' column
     */
    public void setBgiGroup ( String bgi_group )
    {
        this.BgiGroup = bgi_group;
    }

    /**
     *  Getter method for the 'bgi_group' column
     *  @return The new value for the 'bgi_group' column
     */
    public String getBgiGroup ()
    {
        return BgiGroup;
    }

    /**
     *  Setter method for the 'storm_level' column
     *
     * @param storm_level  The new value for the 'storm_level' column
     */
    public void setStormLevel ( long storm_level )
    {
        this.StormLevel = storm_level;
    }

    /**
     *  Getter method for the 'storm_level' column
     *  @return The new value for the 'storm_level' column
     */
    public long getStormLevel ()
    {
        return StormLevel;
    }

    /**
     *  Setter method for the 'duration' column
     *
     * @param duration  The new value for the 'duration' column
     */
    public void setDuration ( long duration )
    {
        this.Duration = duration;
    }

    /**
     *  Getter method for the 'duration' column
     *  @return The new value for the 'duration' column
     */
    public long getDuration ()
    {
        return Duration;
    }

    /**
     *  Setter method for the 'threshold' column
     *
     * @param threshold  The new value for the 'threshold' column
     */
    public void setThreshold ( long threshold )
    {
        this.Threshold = threshold;
    }

    /**
     *  Getter method for the 'threshold' column
     *  @return The new value for the 'threshold' column
     */
    public long getThreshold ()
    {
        return Threshold;
    }

    /**
     *  Setter method for the 'action' column
     *
     * @param action  The new value for the 'action' column
     */
    public void setAction ( String action )
    {
        this.Action = action;
    }

    /**
     *  Getter method for the 'action' column
     *  @return The new value for the 'action' column
     */
    public String getAction ()
    {
        return Action;
    }

    /**
     *  Setter method for the 'message_flag' column
     *
     * @param message_flag  The new value for the 'message_flag' column
     */
    public void setMessageFlag ( char message_flag )
    {
        this.MessageFlag = message_flag;
    }

    /**
     *  Getter method for the 'message_flag' column
     *  @return The new value for the 'message_flag' column
     */
    public char getMessageFlag ()
    {
        return MessageFlag;
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
                message.get().append ( "Updating ThrottleAudit ( RuleId, AuditVersionNum ) = ( " );
                message.get().append ( RuleId );
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
                message.get().append ( "Inserting audit entry for ThrottleAudit ( RuleId ) = ( " );
                message.get().append ( RuleId );
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
    public static ThrottleAudit select ( long rule_id, long audit_version_num )
    {
        ThrottleAudit object = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit c WHERE c.RuleId = :RuleId AND c.AuditVersionNum = :AuditVersionNum" );
            query = query.setParameter ( "RuleId", rule_id );
            query = query.setParameter ( "AuditVersionNum", audit_version_num );
            object = (ThrottleAudit) query.uniqueResult();
        tx.commit();

        return object;
    }

    /**
     *  Returns the a "paged" list of audit objects
     *
     *  @return list of all audit objects in the database that belong to a "page"
     */
    public static List <ThrottleAudit> retrieveRecordsPage ( long RuleId, int page_num, int num_results_per_page )
    {
        List <ThrottleAudit> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit c WHERE c.RuleId = :RuleId ORDER BY AuditVersionNum DESC" );
            query.setParameter   ( "RuleId", RuleId );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults  ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }
}
