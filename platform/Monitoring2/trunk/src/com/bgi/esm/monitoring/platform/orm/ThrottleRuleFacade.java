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

import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;

/**
 * Manipulate throttle rule entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ThrottleRuleFacade {

    /**
     * ctor, initialize weblogic logging
     */
    public ThrottleRuleFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( ThrottleRuleFacade.class );
        }

        if ( null == _log ) {
            System.err.println("ThrottleRuleFacade ctor failure");
        }
    }
    
    /**
     * Return all throttle rule rows
     * 
     * @return all throttle rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        _log.debug("ThrottleRuleFacade::getAllRows() - entry");
        
        ArrayList<ThrottleRule> results = new ArrayList<ThrottleRule>();

        try {
            ThrottleRuleEjbLocalHome home = ThrottleRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                ThrottleRuleEjbLocal local = (ThrottleRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("ThrottleRuleFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("ThrottleRuleFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified throttle rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(ThrottleRule arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        ThrottleRuleEjbLocal local = findByKey(arg.getRuleKey());
        if (local == null) {
            _log.error("ThrottleRuleFacade::deleteRow() - attempt to delete unknown row:" + arg.getRuleKey());
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            String message = "ThrottleRuleFacade::deleteRow() - remove exception:" + arg.getRuleKey() + ":";
            _log.error(message, re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a throttle rule.
     * 
     * @param arg throttle rule
     * @return true if success
     * @throws NullPointerException if null arg
     */
    public boolean addOrUpdateRow(ThrottleRule arg) {
        _log.debug("add:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.getRuleKey().equals(ThrottleRule.DEFAULT_KEY)) {
            return(insertRow(arg));
        }

        ThrottleRuleEjbLocal local = findByKey(arg.getRuleKey());
        if (local == null) {
            _log.error("ThrottleRuleFacade::addOrUpdateRow() - unknown key" + arg);
            return(false);
        }

        // fall through for update
        
        local.setValue(arg);
        return(true);
    }

    /**
     * Insert a new row. Must generate key prior to insertion.
     * 
     * @param arg row to insert
     * @return true if success
     */
    private boolean insertRow(ThrottleRule arg) {
        ThrottleRuleEjbPK key = new ThrottleRuleEjbPK(ThrottleRuleEjbUtil.generateGUID(this));

        ThrottleRuleEjbLocalHome home = null;

        /*
        if ( arg.getRuleId() < 0 )
        {
            arg.setRuleId ( (new MonotonicThrottleFacade()).getSequenceValue() );
        }
        //*/

        try {
            home = ThrottleRuleEjbUtil.getLocalHome();
            home.create(key, arg);
        } catch(CreateException ce) {
            String message = "ThrottleRuleFacade::insertRow() - create exception:" + arg + ":";
            _log.error(message, ce);
            return(false);
        } catch(NamingException ne) {
            String message = "ThrottleRuleFacade::insertRow() - naming exception:" + arg + ":";
            _log.error(message, ne);
            return(false);
        }

        return(true);
    }

    /**
     * Return selected throttle rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public ThrottleRule selectRow(String arg) {
        _log.debug("select:" + arg);

        ThrottleRuleEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Return throttle rules by group
     * 
     * @return throttle rules by group, might be empty list (never null).
     */
    public List<ThrottleRule> selectByGroup(String arg) {
        ArrayList<ThrottleRule> results = new ArrayList<ThrottleRule>();

        try {
            ThrottleRuleEjbLocalHome home = ThrottleRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findByGroup(arg).iterator();
            while (iterator.hasNext()) {
                ThrottleRuleEjbLocal local = (ThrottleRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("ThrottleRuleFacade::selectByGroup() - finder exception");
        } catch(NamingException ne) {
                _log.error("ThrottleRuleFacade::selectByGroup() - naming exception");

        }

        return(results);
    }

    /**
     * Retrieve an individual throttle rule
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private ThrottleRuleEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null target");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty target");
        }
        
        try {
            ThrottleRuleEjbLocalHome home = ThrottleRuleEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(FinderException fe) {
            _log.error("ThrottleRuleFacade::findByKey() - finder exception:" + target + ":");
        } catch(NamingException ne) {
            _log.error("ThrottleRuleFacade::findByKey() - naming exception:" + target + ":");
        }

        return(null);
    }

    /**
     * Define logger
     */
    private Logger _log;
}
