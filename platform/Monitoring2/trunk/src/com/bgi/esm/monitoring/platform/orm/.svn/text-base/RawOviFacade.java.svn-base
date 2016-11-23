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

import com.bgi.esm.monitoring.platform.shared.value.RawOvi;

/**
 * Manipulate RAW_OVI entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class RawOviFacade {
    
    /**
     * ctor, initialize weblogic loggging
     */
    public RawOviFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( RawOviFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("RawOviFacade ctor/log failure");
        }
    }
    
    /**
     * Return all raw OVI rows.  Might be a very large result set.
     * 
     * @return all raw OVI rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<RawOvi> results = new ArrayList<RawOvi>();

        try {
            RawOviEjbLocalHome home = RawOviEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                RawOviEjbLocal local = (RawOviEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch (FinderException fe) {
            _log.error("RawOviFacade::getAllRows() - finder exception", fe);
        } catch (NamingException ne) {
            _log.error("RawOviFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified raw OVI
     * 
     * @param row to delete
     * @return true success
     */
    public boolean deleteRow(RawOvi arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        RawOviEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("RawOviFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("RawOviFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a RAW_OVI row
     * 
     * @param arg raw OVI row
     * @return true if success
     */
    public boolean addOrUpdateRow(RawOvi arg) {
        _log.debug("add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.getRowKey().equals(RawOvi.DEFAULT_KEY)) {
            return(insertRow(arg));
        }
        
        // falling through implies update, test for spoof key

        RawOviEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("RawOviFacade::addOrUpdateRow() - unknown key:" + arg.toString());
            return(false);
        }

        local.setValue(arg);
        
        return(true);
    }

    /**
     * Insert a new row. Must generate key prior to insertion.
     * 
     * @param arg row to insert
     * @return true if success
     */
    private boolean insertRow(RawOvi arg) {
        RawOviEjbPK key = new RawOviEjbPK(RawOviEjbUtil.generateGUID(this));

        RawOviEjbLocalHome home = null;

        try {
            home = RawOviEjbUtil.getLocalHome();
            home.create(key, arg);
        } catch(CreateException ce) {
            _log.error("RawOviFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("RawOviFacade::insertRow() - naming exception:" + arg + ":", ne);
            return(false);
        }

        return(true);
    }

    /**
     * Return selected raw OVI row
     * 
     * @param arg row key (message group)
     * @return selected row or null if not found
     */
    public RawOvi selectRow(String arg) {
        _log.debug("select:" + arg);

        RawOviEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual rawOvi row
     * 
     * @param target key to select
     * @return row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private RawOviEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            RawOviEjbLocalHome home = RawOviEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(FinderException fe) {
            _log.error ( "RawOviFacade::findByKey() - Could not find RawOvi record with target=" + target );
        } catch(NamingException ne) {
            _log.error ( "RawOviFacade::findByKey() - Naming exception detected with target=" + target );
        }

        return(null);
    }

    /**
     * Define logger
     */
    private Logger _log;
}
