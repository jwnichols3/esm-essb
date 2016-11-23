package com.bgi.esm.monitoring.platform.orm;

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
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;

/**
 * Manipulate audit entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class AlarmpointFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public AlarmpointFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( AlarmpointFacade.class );
        }

        if ( null == _log ) {
            System.err.println("AlarmpointFacade ctor/log failure");
        }
    }
    
    /**
     * Return all audit rule rows, might be a very large result set
     * 
     * @return all audit rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<Alarmpoint> results = new ArrayList<Alarmpoint>();

        try {
            AlarmpointEjbLocalHome home = AlarmpointEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                AlarmpointEjbLocal local = (AlarmpointEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("AlarmpointFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("AlarmpointFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified audit row
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(Alarmpoint arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        AlarmpointEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("AlarmpointFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("AlarmpointFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":");
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a audit row
     * 
     * @param arg audit row
     * @return true if success
     * @throws NullPointerException if null arg
     */
    public boolean addOrUpdateRow(Alarmpoint arg) {
        _log.debug("add:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.getRowKey().equals(Alarmpoint.DEFAULT_KEY)) {
            return(insertRow(arg));
        }

        // falling through implies update, test for spoof key
        
        AlarmpointEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("AlarmpointFacade::addOrUpdateRow() - unknown key" + arg);
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
    private boolean insertRow(Alarmpoint arg) {
        AlarmpointEjbPK key = new AlarmpointEjbPK(AlarmpointEjbUtil.generateGUID(this));

        AlarmpointEjbLocalHome home = null;

        try {
            home = AlarmpointEjbUtil.getLocalHome();
            home.create(key, arg);       
        } catch(CreateException ce) {
            _log.error("AlarmpointFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("AlarmpointFacade::insertRow() - naming exception:" + arg + ":", ne);
            return(false);
        }

        return(true);
    }

    /**
     * Return selected audit row
     * 
     * @param arg row key
     * @return selected row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     */
    public Alarmpoint selectRow(String arg) {
        _log.debug("select:" + arg);

        AlarmpointEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual audit row
     * 
     * @param target key to select
     * @return row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private AlarmpointEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            AlarmpointEjbLocalHome home = AlarmpointEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(ObjectNotFoundException exception) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "AlarmpointFacade::findBykey ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            _log.error("AlarmpointFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("AlarmpointFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }
    
    /**
     * Define logger
     */
    private Logger _log;
}
