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
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;

/**
 * Manipulate Service Center entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class ServiceCenterFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public ServiceCenterFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( ServiceCenterFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("ServiceCenterFacade ctor/log failure");
        }
    }
    
    /**
     * Return all service_center rule rows, might be a very large result set
     * 
     * @return all service_center rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<ServiceCenter> results = new ArrayList<ServiceCenter>();

        try {
            ServiceCenterEjbLocalHome home = ServiceCenterEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                ServiceCenterEjbLocal local = (ServiceCenterEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("ServiceCenterFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified service_center row
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(ServiceCenter arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        ServiceCenterEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("ServiceCenterFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("ServiceCenterFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a service_center row
     * 
     * @param arg service_center row
     * @return true if success
     * @throws NullPointerException if null arg
     */
    public boolean addOrUpdateRow(ServiceCenter arg) {
        _log.debug("add:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.getRowKey().equals(ServiceCenter.DEFAULT_KEY)) {
            return(insertRow(arg));
        }

        // falling through implies update, test for spoof key
        
        ServiceCenterEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("ServiceCenterFacade::addOrUpdateRow() - unknown key" + arg);
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
    private boolean insertRow(ServiceCenter arg) {
        ServiceCenterEjbPK key = new ServiceCenterEjbPK(ServiceCenterEjbUtil.generateGUID(this));

        ServiceCenterEjbLocalHome home = null;

        System.out.println ( "ServiceCenterFacade - Service Center Ticket Category: " + arg.getTicketCategory() );


        try {
            home = ServiceCenterEjbUtil.getLocalHome();
            home.create(key, arg);       
        } catch(CreateException ce) {
            _log.error("ServiceCenterFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("ServiceCenterFacade::insertRow() - naming exception:" + arg + ":", ne);
            return(false);
        }

        return(true);
    }

    /**
     * Return selected service_center row
     * 
     * @param arg row key
     * @return selected row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     */
    public ServiceCenter selectRow(String arg) {
        _log.debug("select:" + arg);

        ServiceCenterEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual row
     * 
     * @param target key to select
     * @return row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    public ServiceCenterEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            ServiceCenterEjbLocalHome home = ServiceCenterEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(FinderException fe) {
            _log.error("ServiceCenterFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    public ServiceCenter findByTicketNumber (String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }

        if ( target.equals ( "IM-Default" ) ) {
            _log.info ( "ServiceCenterFacade::findByTicketNumber() - Query for 'IM-Default'" );
            return null;
        }

        if ( target.equals ( "alarmpoint_only" ) ) {
            _log.info ( "ServiceCenterFacade::findByTicketNumber() - Query for 'alarmpoint_only'" );
            return null;
        }
        
        try {
            ServiceCenterEjbLocalHome home = ServiceCenterEjbUtil.getLocalHome();
            ServiceCenterEjbLocal record = home.findByTicketNumber(target);

            return ( null != record )? record.getValue() : null;
        } catch(FinderException fe) {
            _log.error("ServiceCenterFacade::findByTicketNumber() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterFacade::findByTicketNumber() - naming exception:" + target + ":", ne);
        }

        return(null);
    }



    /**
     * Retrieve an individual row
     * 
     * @param target key to select
     * @return row or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    public ServiceCenter findByMessageKey(String key) {
        if (key == null) {
            throw new NullPointerException("null key");
        }
        
        if (key.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            ServiceCenterEjbLocalHome home = ServiceCenterEjbUtil.getLocalHome();
            ServiceCenterEjbLocal record = (ServiceCenterEjbLocal) home.findByMessageKey(key).iterator().next();

            return ( null != record )? record.getValue() : null;
        } catch(FinderException fe) {
            _log.error("ServiceCenterFacade::findByMessageKey() - finder exception:" + key + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterFacade::findByMessageKey() - naming exception:" + key + ":", ne);
        }

        return(null);
    }

    
    /**
     * Define logger
     */
    private Logger _log;
}
