package com.bgi.esm.monitoring.platform.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;

/**
 * Manipulate data map rule entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class DataMapRuleAuditFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public DataMapRuleAuditFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( DataMapRuleAuditFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("DataMapRuleAuditFacade ctor/log failure");
        }
    }

    /**
     * Return all data map rows
     * 
     * @return all data map rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<DataMapRuleAudit> results = new ArrayList<DataMapRuleAudit>();

        try {
            DataMapRuleAuditEjbLocalHome home = DataMapRuleAuditEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                DataMapRuleAuditEjbLocal local = (DataMapRuleAuditEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("DataMapRuleAuditFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("DataMapRuleAuditFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Retrieve all audit objects for a given suppression referenced by group name
     * 
     * @param group key to select
     * @return all previous audit versions or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    public List <DataMapRuleAudit> findAuditVersions ( String group )
    {
        try 
        {
            DataMapRuleAuditEjbLocalHome home = DataMapRuleAuditEjbUtil.getLocalHome();
            Iterator iterator = home.findAuditVersions ( group ).iterator();
            ArrayList<DataMapRuleAudit> results = new ArrayList<DataMapRuleAudit>();

            while ( iterator.hasNext() )
            {
                DataMapRuleAuditEjbLocal local = (DataMapRuleAuditEjbLocal) iterator.next();

                results.add ( local.getValue() );
            }

            return results;
        } catch (FinderException fe) {
            _log.error("DataMapRuleAuditFacade::findAuditVersions() - finder exception - data map rule for group=" + group + ":", fe);
        } catch(NamingException ne) {
            _log.error("DataMapRuleAuditFacade::findAuditVersions() - naming exception - data map rule for group=" + group + ":", ne);
        }

        return(null);
    }


    /**
     * Delete specified data map rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(DataMapRuleAudit arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        DataMapRuleAuditEjbLocal local = findByKey(arg.getGroup(), arg.getAuditVersionNum());
        if (local == null) {
            _log.error("DataMapRuleAuditFacade::deleteRow() - finder exception - attempt to delete unknown row:" + arg.getGroup());
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            String message = "DataMapRuleAuditFacade::deleteRow() - remove exception:" + arg.getGroup() + ":";
            _log.error(message, re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a data map rule.
     * 
     * @param arg data map rule
     * @returns true if success
     * @throws NullPointerException if null arg
     */
    public boolean addOrUpdateRow(DataMapRuleAudit arg) {
        _log.debug("add:" + arg);

        _log.info ( "DataMapRuleAuditFacade::addOrUpdateRow()" );
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        DataMapRuleAuditEjbLocal local = findByKey(arg.getGroup(), arg.getAuditVersionNum());
        if (local == null) {
            _log.debug("insert:" + arg.getGroup());

            DataMapRuleAuditEjbLocalHome home = null;

            try {
                home = DataMapRuleAuditEjbUtil.getLocalHome();
                home.create(arg);
            } catch(CreateException ce) {
                _log.error("DataMapRuleAuditFacade::addOrUpdateRow() - create exception:" + arg + ":", ce);
                return(false);
            } catch(NamingException ne) {
                _log.error("DataMapRuleAuditFacade::addOrUpdateRow() - naming exception:" + arg + ":", ne);
                return(false);
            }
        } else {
            _log.debug("update:" + arg);

            local.setValue(arg);
        }

        return(true);
    }

    /**
     * Return selected data map rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public DataMapRuleAudit selectRow(String arg, long audit_version_num) {
        _log.debug("select:" + arg);

        DataMapRuleAuditEjbLocal local = findByKey(arg, audit_version_num);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual data map rule
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private DataMapRuleAuditEjbLocal findByKey(String target, long audit_version_num ) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            DataMapRuleAuditEjbLocalHome home = DataMapRuleAuditEjbUtil.getLocalHome();
            return(home.findByKey(target, audit_version_num));
        } catch(FinderException fe) {
            _log.error("DataMapRuleAuditFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("DataMapRuleAuditFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }
    
    /**
     * Define logger
     */
    private Logger _log;
}
