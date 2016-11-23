package com.bgi.esm.monitoring.portlets.DataMapRules.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
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
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit;

public class DataMap extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1173914320049L;
    private static final Logger _log                   = Logger.getLogger ( DataMap.class );


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


    public DataMap()
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
        _log.debug ( "DataMap::reset()" );

        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        String index = param_map.get ( "rule_id" );

        if ( null == index )
        {
            _log.info ( "reset() - Null index found..." );

            return;
        }

        DataMap object = DataMap.select ( Long.parseLong ( index ) );

        _log.info ( "Initializing object with index=" + index );

        setRuleId         ( object.getRuleId()         );
        setBgiGroup       ( object.getBgiGroup()       );
        setBgiMethod      ( object.getBgiMethod()      );
        setApGroup        ( object.getApGroup()        );
        setApScript       ( object.getApScript()       );
        setPerCat         ( object.getPerCat()         );
        setPerSubcat      ( object.getPerSubcat()      );
        setPerProduct     ( object.getPerProduct()     );
        setPerProblem     ( object.getPerProblem()     );
        setPerAssign      ( object.getPerAssign()      );
        setPerLocation    ( object.getPerLocation()    );
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
        _log.debug ( "DataMap::validate()" );
        ActionErrors errors = new ActionErrors();

        return errors;
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
     *  Returns the number of existing records
     *
     *  @return the number of existing records
     */
    public static long countRecords()
    {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            long num_records = ((Number) session.createSQLQuery( "select count(*) from data_map" ).uniqueResult() ).longValue();
        tx.commit();

        return num_records;
    }
    
    /**
     *  Returns the number of records based on the group_name
     *
     *  @return the number of records satisfied in the query
     */
    public static long countRecords(String group_name)
    {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            long num_records = ((Number) session.createSQLQuery( "select count(*) from data_map WHERE bgi_group LIKE '%" + group_name + "%'" ).uniqueResult() ).longValue();
        tx.commit();

        return num_records;
    }


    /**
     *  Returns the total number of changes to this record
     *
     *  @return the total number of changes to this record
     */
    public static long countPreviousVersions ( long RuleId )
    {
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "SELECT count(*) FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit c WHERE c.RuleId = :RuleId" );
            query.setParameter ( "RuleId", RuleId );

            long num_records = ((Number) query.uniqueResult()).longValue();
        tx.commit();

        return num_records;
    }

    /**
     *  Attempt to update/insert this object into the database.
     *
     *  @return true if updated, false otherwise
     */
    public boolean insertOrUpdate ( String username )
    {
        boolean was_update = false;
        Session session    = HibernateUtil.getCurrentSession();
        Transaction tx     = session.beginTransaction();
        DataMap object = (DataMap) session.get ( DataMap.class, RuleId );
        tx.commit();

        long num_previous_versions = DataMap.countPreviousVersions ( RuleId );

            //  Create the audit entry
            DataMapAudit audit = new DataMapAudit();
        if ( null != object )
        {
            //  Hibernate 3.2 requires that only one instance of the object be present (does row-level locking for us).
            //  Must use this object when updating/saving

            //  Update existing record in database
            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Updating DataMap ( RuleId ) = " );
                message.get().append ( RuleId );

                _log.info ( message.get().toString() );
            }

            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                //  Update the current object
                object.setRuleId          ( getRuleId ()          );
                object.setBgiGroup        ( getBgiGroup ()        );
                object.setBgiMethod       ( getBgiMethod ()       );
                object.setApGroup         ( getApGroup ()         );
                object.setApScript        ( getApScript ()        );
                object.setPerCat          ( getPerCat ()          );
                object.setPerSubcat       ( getPerSubcat ()       );
                object.setPerProduct      ( getPerProduct ()      );
                object.setPerProblem      ( getPerProblem ()      );
                object.setPerAssign       ( getPerAssign ()       );
                object.setPerLocation     ( getPerLocation ()     );
                session.update ( object );
            tx.commit();

            was_update = true;
        }
        else
        {
            _log.info ( "Inserting new DataMap object into database" );

            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( this );
            tx.commit();

            was_update = false;
        }

        return was_update;
    }

    /**
     *  Select a particular instance of a DataMap by its index
     *
     *  @return the instance of the object if available, null otherwise
     */
    public static DataMap select ( long rule_id )
    {
        DataMap object = null;
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            object = (DataMap) session.get ( DataMap.class, rule_id );
        tx.commit();

        return object;
    }

    /**
     *  Retrieves a list of objects (in descending order by time of creation) of all previous
     *  versions of this object
     *
     *  @return list of objects representing all previous versions of this object in descending
     *          order by time of creation
     */
    public static List <DataMapAudit> retrievePreviousVersions ( long rule_id )
    {
        List <DataMapAudit> list = null;
        /*
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit c WHERE c.RuleId = :RuleId ORDER BY AuditVersionNum DESC" );
            query = query.setParameter ( "RuleId", rule_id );

            list = query.list();
        tx.commit();
        //*/

        return list;
    }

    /**
     *  Retrieves all objects in the database
     *
     *  @return list of all objects in the database
     */
    public static List <DataMap> selectAll ()
    {
        List <DataMap> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap D ORDER BY D.BgiGroup" );
            
            list = query.list();
        tx.commit();

        return list;
    }

    /**
     *  Retrieves "pages" of records of sepcified sizes
     *
     *  @return list of all objects in the database that belong to a "page"
     */
    public static List <DataMap> retrieveRecordsPage ( int page_num, int num_results_per_page )
    {
        List <DataMap> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap D ORDER BY D.BgiGroup" );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }
    
    /**
     *  Retrieves "pages" of records satisfied by the search query
     *
     *  @return list of all objects in the database that satisfied by the search query
     */
    public static List <DataMap> searchRecordsByGroup ( String group_name, int page_num, int num_results_per_page )
    {
        List <DataMap> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap D " +
            		"WHERE D.BgiGroup LIKE '%" + group_name + "%' " + 
            		"ORDER BY D.BgiGroup" );
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
    public List <DataMapAudit> retrievePreviousVersions()
    {
        return DataMap.retrievePreviousVersions ( RuleId );
    }
}
