package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;

/**
 * Manipulate data map rule entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class DataMapRuleFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public DataMapRuleFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( DataMapRuleFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("DataMapRuleFacade ctor/log failure");
        }
    }

    /**
     * Return all data map rows
     * 
     * @return all data map rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<DataMapRule> results = new ArrayList<DataMapRule>();

        try {
            DataMapRuleEjbLocalHome home = DataMapRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                DataMapRuleEjbLocal local = (DataMapRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "DataMapRuleFacade::getAllRows() - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "DataMapRuleFacade::getAllRows() - naming exception: " );
                message.append ( ne.getMessage() );
            _log.error( message.toString(), ne);
        }

        return(results);
    }

    /**
     * Delete specified data map rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(DataMapRule arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        DataMapRuleEjbLocal local = findByKey(arg.getGroup());
        if (local == null) {
            _log.error("DataMapRuleFacade::deleteRow() - attempt to delete unknown row:" + arg.getGroup());
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            String message = "DataMapRuleFacade::deleteRow() - remove exception:" + arg.getGroup() + ":";
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
    public boolean addOrUpdateRow(DataMapRule arg) {
        _log.debug("add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        DataMapRuleEjbLocal local      = findByKey(arg.getGroup());
        DataMapRuleAuditEjbLocal audit = null;
        if (local == null) {
            _log.debug("insert:" + arg.getGroup());

            DataMapRuleEjbLocalHome home = null;

            try {
                home = DataMapRuleEjbUtil.getLocalHome();
                home.create(arg);
            } catch(CreateException ce) {
                _log.error("DataMapRuleFacade::addOrUpdateRow() - create exception:" + arg + ":", ce);
                return(false);
            } catch(NamingException ne) {
                _log.error("DataMapRuleFacade::addOrUpdateRow() - naming exception:" + arg + ":", ne);
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
    public DataMapRule selectRow(String arg) {
        _log.debug("select:" + arg);

        DataMapRuleEjbLocal local = findByKey(arg);
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
    private DataMapRuleEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            DataMapRuleEjbLocalHome home = DataMapRuleEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(ObjectNotFoundException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "DataMapRuleFacade::findByKey ( target=" );
                message.append ( target );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString() );
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "DataMapRuleFacade::findByKey ( target=" );
                message.append ( target );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "DataMapRuleFacade::findByKey ( target=" );
                message.append ( target );
                message.append ( " ) - naming exception: " );
                message.append ( ne.getMessage() );
            _log.error( message.toString(), ne);
        }

        return(null);
    }
    
    /**
     * Define logger
     */
    private Logger _log;
}
