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

import com.bgi.esm.monitoring.platform.shared.value.Spool;

/**
 * Manipulate spool entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class SpoolFacade {

    /**
     * ctor, initialize weblogic loggging
     */
    public SpoolFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( SpoolFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("SpoolFacade ctor/log failure");
        }
    }

    /**
     * Return all spool rows
     * 
     * @return all spool rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<Spool> results = new ArrayList<Spool>();

        try {
            SpoolEjbLocalHome home = SpoolEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                SpoolEjbLocal local = (SpoolEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("SpoolFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("SpoolFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified spool row
     * 
     * @param row to delete
     * @return true success
     */
    public boolean deleteRow(Spool arg) {
        _log.debug("delete:" + arg);

        SpoolEjbLocal local = findByKey(arg.getRowKey());

        if (local == null) {
            _log.error("SpoolFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            _log.error("SpoolFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a spool row
     * 
     * @param arg spool row
     * @returns true if success
     */
    public boolean addOrUpdateRow(Spool arg) {
        if (arg.getRowKey().equals(Spool.DEFAULT_KEY)) {
            _log.debug("insert:" + arg);

            SpoolEjbPK key = new SpoolEjbPK(SpoolEjbUtil.generateGUID(this));

            SpoolEjbLocalHome home = null;

            try {
                home = SpoolEjbUtil.getLocalHome();
                home.create(key, arg);
            } catch(CreateException ce) {
                String message = "SpoolFacade::addOrUpdateRow() - create exception:" + arg + ":";
                _log.error(message, ce);
                return(false);
            } catch(NamingException ne) {
                String message = "SpoolFacade::addOrUpdateRow() - naming exception:" + arg + ":";
                _log.error(message, ne);
                return(false);
            }
        } else {
            _log.debug("update:" + arg);

            SpoolEjbLocal local = findByKey(arg.getRowKey());

            if (local == null) {
                _log.error("SpoolFacade::addOrUpdateRow() - unknown key:" + arg.getRowKey());
                return(false);
            }

            local.setValue(arg);
        }

        return(true);
    }

    /**
     * Return selected spool row.
     * 
     * @param arg row key
     * @returns selected row or null if not found
     */
    public Spool selectRow(String arg) {
        _log.debug("select:" + arg);

        SpoolEjbLocal local = findByKey(arg);
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
    private SpoolEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }

        try {
            SpoolEjbLocalHome home = SpoolEjbUtil.getLocalHome();
            return (home.findByKey(target));
        } catch(FinderException fe) {
            _log.error("SpoolFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("SpoolFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    /**
     * Define logger
     */
    private Logger _log;
}
