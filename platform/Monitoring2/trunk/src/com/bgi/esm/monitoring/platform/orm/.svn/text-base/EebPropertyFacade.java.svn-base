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
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;

/**
 * Manipulate suppression rule entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class EebPropertyFacade {
    
    /**
     * ctor, initialize weblogic loggging
     */
    public EebPropertyFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( EebPropertyFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("EebPropertyFacade ctor/log failure");
        }
    }

    /**
     * Return all suppression rule rows, might be large result set
     * 
     * @return all suppression rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        if ( null != _log ) _log.debug("getAllRows");
        
        ArrayList<EebProperty> results = new ArrayList<EebProperty>();

        try {
            EebPropertyEjbLocalHome home = EebPropertyEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                EebPropertyEjbLocal local = (EebPropertyEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            if ( null != _log ) _log.error("EebPropertyFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            if ( null != _log ) _log.error("EebPropertyFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified suppression rule
     * 
     * @param property_name to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow( String property_name ) {
        if ( null != _log ) _log.debug("delete:" + property_name);
        
        if (property_name == null) {
            throw new NullPointerException("null property_name");
        }

        EebPropertyEjbLocal local = findByKey( property_name  );
        if (local == null) {
            if ( null != _log ) _log.error("EebPropertyFacade::deleteRow() - attempt to delete unknown row:" + property_name  + ":");
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            if ( null != _log ) _log.error("EebPropertyFacade::deleteRow() - remove exception:" + property_name + ":", re);
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
    public boolean addOrUpdateRow ( EebProperty arg ) {
        if ( null != _log ) _log.debug("add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException ("Tried to insert null EEBProperty value object into database");
        }
        
        // falling through imples update, ensure key already exists

        EebPropertyEjbLocal local = findByKey(arg.getPropertyName());
        if (local == null) {
            if ( null != _log ) _log.info ( "Inserting " + arg.toString() );
            return(insertRow(arg));
        }

        //  Update the audit tables
        local.setValue(arg);
        return(true);
    }

    /**
     * Insert a new row. Must generate key prior to insertion.
     * 
     * @param arg row to insert
     * @return true if success
     */
    private boolean insertRow(EebProperty arg) {
        EebPropertyEjbLocalHome home = null;

        try {
            home = EebPropertyEjbUtil.getLocalHome();
            home.create( arg );
        } catch(CreateException ce) {
            if ( null != _log ) _log.error("EebPropertyFacade::insertRow() - create exception:" + arg.toString(), ce );
            return(false);
        } catch(NamingException ne) {
            if ( null != _log ) _log.error("EebPropertyFacade::insertRow() - naming exception:" + arg.toString(), ne );
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
    public EebProperty selectRow(String arg) {
        if ( null != _log ) _log.debug("select:" + arg);

        EebPropertyEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }
    

    /**
     * Retrieve an individual EEB property
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private EebPropertyEjbLocal findByKey ( String property_name ) {
        if (property_name == null) {
            throw new NullPointerException("EebPropertyFacade::findByKey() - null property_name");
        }
        
        if (property_name.length() < 1) {
            throw new IllegalArgumentException("EebPropertyFacade::findByKey() - empty property_name");
        }
        
        try {
            EebPropertyEjbLocalHome home = EebPropertyEjbUtil.getLocalHome();
            return(home.findByKey(property_name));
        } catch(ObjectNotFoundException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "EebPropertyFacade::findByKey ( property_name=" );
                message.append ( property_name );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString() );
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "EebPropertyFacade::findByKey ( property_name=" );
                message.append ( property_name );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "EebPropertyFacade::findByKey ( property_name=" );
                message.append ( property_name );
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
