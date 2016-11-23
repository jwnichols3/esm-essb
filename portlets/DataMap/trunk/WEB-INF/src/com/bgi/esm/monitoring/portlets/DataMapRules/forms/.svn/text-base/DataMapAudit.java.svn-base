package com.bgi.esm.monitoring.portlets.DataMapRules.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.portlets.DataMapRules.HibernateUtil;
import com.bgi.esm.monitoring.portlets.DataMapRules.Toolkit;

public class DataMapAudit extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1173914320065L;
    private static final Logger _log                   = Logger.getLogger ( DataMapAudit.class );


    private long      AuditId         = 0L;
    private long      AuditVersionNum = 0L;
    private Calendar  AuditTimestamp  = Calendar.getInstance();
    private String    AuditModifiedBy = null;
    private long      RuleId       = 0L;
    private String    BgiGroup     = null;
    private String    BgiMethod    = null;
    private String    ApGroup      = null;
    private String    ApScript     = null;
    private String    PerCat       = null;
    private String    PerSubcat    = null;
    private String    PerProduct   = null;
    private String    PerProblem   = null;
    private String    PerAssign    = null;
    private String    PerLocation  = null;


    public DataMapAudit()
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
        _log.debug ( "DataMapAudit::reset()" );

        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        String index   = param_map.get ( "rule_id" );
        String version = param_map.get ( "audit_version_num" );

        if ( null == index )
        {
            _log.info ( "reset() - Null index found..." );

            return;
        }

        DataMapAudit object = DataMapAudit.select ( Long.parseLong ( index ), Long.parseLong ( version ) );

        setAuditVersionNum ( Long.parseLong ( version )  );
        setAuditTimestamp  ( object.getAuditTimestamp()  );
        setAuditModifiedBy ( object.getAuditModifiedBy() );
        setRuleId          ( object.getRuleId()          );
        setBgiGroup        ( object.getBgiGroup()        );
        setBgiMethod       ( object.getBgiMethod()       );
        setApGroup         ( object.getApGroup()         );
        setApScript        ( object.getApScript()        );
        setPerCat          ( object.getPerCat()          );
        setPerSubcat       ( object.getPerSubcat()       );
        setPerProduct      ( object.getPerProduct()      );
        setPerProblem      ( object.getPerProblem()      );
        setPerAssign       ( object.getPerAssign()       );
        setPerLocation     ( object.getPerLocation()     );
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
        _log.debug ( "DataMapAudit::validate()" );
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
     *  Setter method for the 'bgi_method' column
     *
     * @param bgi_method  The new value for the 'bgi_method' column
     */
    public void setBgiMethod ( String bgi_method )
    {
        this.BgiMethod = bgi_method;
    }

    /**
     *  Getter method for the 'bgi_method' column
     *  @return The new value for the 'bgi_method' column
     */
    public String getBgiMethod ()
    {
        return BgiMethod;
    }

    /**
     *  Setter method for the 'ap_group' column
     *
     * @param ap_group  The new value for the 'ap_group' column
     */
    public void setApGroup ( String ap_group )
    {
        this.ApGroup = ap_group;
    }

    /**
     *  Getter method for the 'ap_group' column
     *  @return The new value for the 'ap_group' column
     */
    public String getApGroup ()
    {
        return ApGroup;
    }

    /**
     *  Setter method for the 'ap_script' column
     *
     * @param ap_script  The new value for the 'ap_script' column
     */
    public void setApScript ( String ap_script )
    {
        this.ApScript = ap_script;
    }

    /**
     *  Getter method for the 'ap_script' column
     *  @return The new value for the 'ap_script' column
     */
    public String getApScript ()
    {
        return ApScript;
    }

    /**
     *  Setter method for the 'per_cat' column
     *
     * @param per_cat  The new value for the 'per_cat' column
     */
    public void setPerCat ( String per_cat )
    {
        this.PerCat = per_cat;
    }

    /**
     *  Getter method for the 'per_cat' column
     *  @return The new value for the 'per_cat' column
     */
    public String getPerCat ()
    {
        return PerCat;
    }

    /**
     *  Setter method for the 'per_subcat' column
     *
     * @param per_subcat  The new value for the 'per_subcat' column
     */
    public void setPerSubcat ( String per_subcat )
    {
        this.PerSubcat = per_subcat;
    }

    /**
     *  Getter method for the 'per_subcat' column
     *  @return The new value for the 'per_subcat' column
     */
    public String getPerSubcat ()
    {
        return PerSubcat;
    }

    /**
     *  Setter method for the 'per_product' column
     *
     * @param per_product  The new value for the 'per_product' column
     */
    public void setPerProduct ( String per_product )
    {
        this.PerProduct = per_product;
    }

    /**
     *  Getter method for the 'per_product' column
     *  @return The new value for the 'per_product' column
     */
    public String getPerProduct ()
    {
        return PerProduct;
    }

    /**
     *  Setter method for the 'per_problem' column
     *
     * @param per_problem  The new value for the 'per_problem' column
     */
    public void setPerProblem ( String per_problem )
    {
        this.PerProblem = per_problem;
    }

    /**
     *  Getter method for the 'per_problem' column
     *  @return The new value for the 'per_problem' column
     */
    public String getPerProblem ()
    {
        return PerProblem;
    }

    /**
     *  Setter method for the 'per_assign' column
     *
     * @param per_assign  The new value for the 'per_assign' column
     */
    public void setPerAssign ( String per_assign )
    {
        this.PerAssign = per_assign;
    }

    /**
     *  Getter method for the 'per_assign' column
     *  @return The new value for the 'per_assign' column
     */
    public String getPerAssign ()
    {
        return PerAssign;
    }

    /**
     *  Setter method for the 'per_location' column
     *
     * @param per_location  The new value for the 'per_location' column
     */
    public void setPerLocation ( String per_location )
    {
        this.PerLocation = per_location;
    }

    /**
     *  Getter method for the 'per_location' column
     *  @return The new value for the 'per_location' column
     */
    public String getPerLocation ()
    {
        return PerLocation;
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
                message.get().append ( "Updating DataMapAudit ( RuleId, AuditVersionNum ) = ( " );
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
                message.get().append ( "Inserting audit entry for DataMapAudit ( RuleId ) = ( " );
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
    public static DataMapAudit select ( long rule_id, long audit_version_num )
    {
        DataMapAudit object = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit c WHERE c.RuleId = :RuleId AND c.AuditVersionNum = :AuditVersionNum" );
            query = query.setParameter ( "RuleId", rule_id );
            query = query.setParameter ( "AuditVersionNum", audit_version_num );
            object = (DataMapAudit) query.uniqueResult();
        tx.commit();

        return object;
    }

    /**
     *  Returns the a "paged" list of audit objects
     *
     *  @return list of all audit objects in the database that belong to a "page"
     */
    public static List <DataMapAudit> retrieveRecordsPage ( long RuleId, int page_num, int num_results_per_page )
    {
        List <DataMapAudit> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit c WHERE c.RuleId = :RuleId ORDER BY AuditVersionNum DESC" );
            query.setParameter   ( "RuleId", RuleId );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults  ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }
}
