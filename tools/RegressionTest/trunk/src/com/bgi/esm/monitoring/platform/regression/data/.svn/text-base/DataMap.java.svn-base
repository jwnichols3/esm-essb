package com.bgi.esm.monitoring.platform.regression.data;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
//import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
//import org.apache.struts.action.ActionErrors;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.regression.hibernate.HibernateUtil;

public class DataMap //extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1173914320049L;
    private static final Logger _log                   = Logger.getLogger ( DataMap.class );


    private long      RuleId       = 0L;
    private String    BgiGroup     = "null";
    private String    BgiMethod    = "null";
    private String    ApGroup      = "null";
    private String    ApScript     = "null";
    private String    PerCat       = "null";
    private String    PerSubcat    = "null";
    private String    PerProduct   = "null";
    private String    PerProblem   = "null";
    private String    PerAssign    = "null";
    private String    PerLocation  = "null";


    public DataMap()
    {
        super ();
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

        //long num_previous_versions = DataMap.countPreviousVersions ( RuleId );

        //  Create the audit entry
        /*
        DataMapAudit audit = new DataMapAudit();

        session = HibernateUtil.getCurrentSession();
        tx      = session.beginTransaction();
            audit.setAuditTimestamp  ( Calendar.getInstance()    );
            audit.setAuditVersionNum ( 1 + num_previous_versions );
            audit.setAuditModifiedBy ( username                  );
                audit.setRuleId          ( object.getRuleId ()          );
                audit.setBgiGroup        ( object.getBgiGroup ()        );
                audit.setBgiMethod       ( object.getBgiMethod ()       );
                audit.setApGroup         ( object.getApGroup ()         );
                audit.setApScript        ( object.getApScript ()        );
                audit.setPerCat          ( object.getPerCat ()          );
                audit.setPerSubcat       ( object.getPerSubcat ()       );
                audit.setPerProduct      ( object.getPerProduct ()      );
                audit.setPerProblem      ( object.getPerProblem ()      );
                audit.setPerAssign       ( object.getPerAssign ()       );
                audit.setPerLocation     ( object.getPerLocation ()     );
            session.save ( audit );
        tx.commit();
        //*/

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
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.regression.data.DataMap c" );

            list = query.list();
        tx.commit();

        return list;
    }

    public static DataMap selectByGroupName ( String group_name )
    {
        List <DataMap> list = null;
        Session session = HibernateUtil.getCurrentSession();
        Query query     = null;
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.regression.data.DataMap c WHERE c.BgiGroup=:GroupName" );
            query = query.setParameter ( "GroupName", group_name );

            list = query.list();
        tx.commit();

        if ( list.size() >= 1 )
        {
            return list.get ( 0 );
        }
        else
        {
            _log.info ( "Could not find group=" + group_name + ", defaulting to ESM group" );
            return DataMap.select ( 146 );
        }
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
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.regression.data.DataMap" );
            query.setFirstResult ( page_num * num_results_per_page );
            query.setMaxResults ( num_results_per_page );

            list = query.list();
        tx.commit();

        return list;
    }
}
