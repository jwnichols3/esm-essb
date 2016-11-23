package com.bgi.esm.monitoring.platform.orm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.logging.log4j.Log4jLoggingHelper;

import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;

/**
 * Manipulate suppression rule entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class SuppressionRuleAuditFacade {
    
    /**
     * ctor, initialize weblogic loggging
     */
    public SuppressionRuleAuditFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( SuppressionRuleAuditFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("SuppressionRuleAuditFacade ctor/log failure");
        }
    }

    /**
     * Return all suppression rule rows, might be large result set
     * 
     * @return all suppression rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        _log.debug("SuppressionRuleAuditFacade::getAllRows() - entry");
        
        ArrayList<SuppressionRuleAudit> results = new ArrayList<SuppressionRuleAudit>();

        try {
            SuppressionRuleAuditEjbLocalHome home = SuppressionRuleAuditEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                SuppressionRuleAuditEjbLocal local = (SuppressionRuleAuditEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("SuppressionRuleAuditFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleAuditFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Retrieve all audit objects for a given suppression referenced by suppression ID
     * 
     * @param suppress_id key to select
     * @return all previous audit versions or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    public List<SuppressionRuleAudit> findAuditVersions ( long suppress_id ) {
        try {
            SuppressionRuleAuditEjbLocalHome home = SuppressionRuleAuditEjbUtil.getLocalHome();
            Iterator iterator = home.findAuditVersions ( suppress_id ).iterator();
            ArrayList<SuppressionRuleAudit> results = new ArrayList<SuppressionRuleAudit>();

            while ( iterator.hasNext() )
            {
                SuppressionRuleAuditEjbLocal local = (SuppressionRuleAuditEjbLocal) iterator.next();

                results.add ( local.getValue() );
            }

            return results;
        } catch (FinderException fe) {
            _log.error("SuppressionRuleAuditFacade::findAuditVersions() - finder exception - suppress_id=" + suppress_id + ":", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleAuditFacade::findAuditVersions() - naming exception - suppress_id=" + suppress_id + ":", ne);
        }

        return(null);
    }

    /**
     * Delete specified suppression rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(SuppressionRuleAudit arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        SuppressionRuleAuditEjbLocal local = findByKey(arg.getRuleKey(),arg.getAuditVersionNum());
        if (local == null) {
            _log.error("SuppressionRuleAuditFacade::deleteRow() - attempt to delete unknown row:" + arg.getRuleKey() + ":");
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            _log.error("SuppressionRuleAuditFacade::deleteRow() - remove exception:" + arg.getRuleKey() + ":", re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a suppression rule.
     * 
     * @param arg suppression rule
     * @return true if success
     * @throws NullPointerException if null arg
     * @throws IllegalArgumentException if invalid times
     */
    public boolean addOrUpdateRow(SuppressionRuleAudit arg) {
        _log.debug("add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.isValidTime() == false) {
            _log.error("SuppressionRuleAuditFacade::addOrUpdateRow() - invalid rule time");
            throw new IllegalArgumentException("invalid rule time");
        }

        if (arg.getRuleKey().equals(SuppressionRuleAudit.DEFAULT_KEY)) {
            return(insertRow(arg));
        }
        
        // falling through imples update, ensure key already exists

        SuppressionRuleAuditEjbLocal local = findByKey(arg.getRuleKey(), arg.getAuditVersionNum());
        if (local == null) {
            _log.error("SuppressionRuleAuditFacade::addOrUpdateRow() - unknown key" + arg);
            return(false);
        }

        local.setValue(arg);
        return(true);
    }

    /**
     *  Insert a new row (created from a SuppressionRule object)
     *
     *  @param arg row to insert
     *  @return true if success
     */


    /**
     * Insert a new row. Must generate key prior to insertion.
     * 
     * @param arg row to insert
     * @return true if success
     */
    private boolean insertRow(SuppressionRuleAudit arg) {
        SuppressionRuleAuditEjbPK key = new SuppressionRuleAuditEjbPK(SuppressionRuleAuditEjbUtil.generateGUID(arg), arg.getAuditVersionNum() );

        SuppressionRuleAuditEjbLocalHome home = null;

        try {
            home = SuppressionRuleAuditEjbUtil.getLocalHome();
            //home.create(key, arg);
            home.create(arg);
        } catch(CreateException ce) {
            _log.error("SuppressionRuleAuditFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleAuditFacade::insertRow() - naming exception:" + arg + ":", ne);
            return(false);
        }

        return(true);
    }

    /**
     * Return selected suppression rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public SuppressionRuleAudit selectRow(String arg, long audit_version_num) {
        _log.debug("SuppressionRuleAuditFacade::selectRow() - arg=" + arg + ", audit_version_num=" + audit_version_num );

        SuppressionRuleAuditEjbLocal local = findByKey(arg, audit_version_num);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual suppression rule
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private SuppressionRuleAuditEjbLocal findByKey ( String target, long audit_version_num ) {
        if (target == null) {
            throw new NullPointerException("null target");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty target");
        }
        
        try {
            SuppressionRuleAuditEjbLocalHome home = SuppressionRuleAuditEjbUtil.getLocalHome();
            return(home.findByKey(target, audit_version_num));
        } catch (FinderException fe) {
            _log.error("SuppressionRuleAuditFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleAuditFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }
    
    /**
     * Define logger
     */
    private Logger _log;
}
