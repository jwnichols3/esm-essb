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
import com.bgi.esm.monitoring.platform.shared.value.Storm;

/**
 * Manipulate storm entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class StormFacade {

    /**
     * ctor, initialize weblogic loggging
     */
    public StormFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( StormFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("StormFacade ctor/log failure");
        }
    }

    /**
     * Return all storm rows
     * 
     * @return all storm rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<Storm> results = new ArrayList<Storm>();

        try 
        {
            StormEjbLocalHome home = StormEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) 
            {
                StormEjbLocal local = (StormEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } 
        catch ( FinderException exception ) 
        {
            _log.error("StormFacade::getAllRows() - finder exception", exception );
        } 
        catch ( NamingException exception ) 
        {
            _log.error("StormFacade::getAllRows() - naming exception", exception );
        }

        return (results);
    }

    public List getAllActiveRows()
    {
        ArrayList <Storm> results = new ArrayList <Storm> ();

        try
        {
            StormEjbLocalHome home = StormEjbUtil.getLocalHome();
            Iterator iterator = home.findAllInProgress().iterator();
            while (iterator.hasNext()) 
            {
                StormEjbLocal local = (StormEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        }
        catch(FinderException fe) 
        {
            _log.error("StormFacade::getAllActiveRows() - finder exception", fe);
        } 
        catch(NamingException ne) 
        {
            _log.error("StormFacade::getAllActiveRows() - naming exception", ne);
        }

        return results;
    }

    /**
     * Delete specified storm row
     * 
     * @param row to delete
     * @return true success
     */
    public boolean deleteRow(Storm arg) {
        _log.debug("delete:" + arg);

        StormEjbLocal local = findByKey(arg.getGroup());

        if (local == null) {
            _log.error("attempt to delete unknown row:" + arg.getGroup());
            return(false);
        }

        try 
        {
            local.remove();
        } 
        catch(RemoveException re) 
        {
            String message = "StormFacade::deleteRow() - remove exception:" + arg.getGroup() + ":";
            _log.error(message, re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a storm row
     * 
     * @param arg storm row
     * @returns true if success
     */
    public boolean addOrUpdateRow(Storm arg) {
        StormEjbLocal local = findByKey(arg.getGroup());

        if (local == null) {
            _log.debug("insert:" + arg);

            StormEjbLocalHome home = null;

            try {
                home = StormEjbUtil.getLocalHome();
                home.create(arg);
            } catch(CreateException ce) {
                _log.error("StormFacade::addOrUpdateRow() - create exception:" + arg + ":", ce);
                return(false);
            } catch (NamingException ne) {
                _log.error("StormFacade::addOrUpdateRow() - naming exception:" + arg + ":", ne);
                return(false);
            }
        } else {
            _log.debug("update:" + arg);

            local.setValue(arg);
        }

        return(true);
    }

    /**
     * Return selected storm rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     */
    public Storm selectRow(String arg) {
        _log.debug("select:" + arg);

        StormEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual storm rule
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private StormEjbLocal findByKey(String target) {
        try {
            StormEjbLocalHome home = StormEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(FinderException fe) {
            _log.error("StormFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("StormFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    /**
     * Define logger
     */
    private Logger _log;
}
