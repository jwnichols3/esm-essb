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

import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;

/**
 * Manipulate Service Center entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class ServiceCenterBufferFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public ServiceCenterBufferFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( ServiceCenterBufferFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("ServiceCenterBufferFacade ctor/log failure");
        }
    }

    public long countRecords()
    {
        try
        {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();

            return home.findAll().size();
        } 
        catch(FinderException fe) 
        {
            _log.error("ServiceCenterBufferFacade::getAllRows() - finder exception", fe);
        } 
        catch(NamingException ne) 
        {
            _log.error("ServiceCenterBufferFacade::getAllRows() - naming exception", ne);
        }

        return -1;
    }
    
    /**
     * Return all service_center rule rows, might be a very large result set
     * 
     * @return all service_center rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<ServiceCenter> results = new ArrayList<ServiceCenter>();

        try {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                ServiceCenterBufferEjbLocal local = (ServiceCenterBufferEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("ServiceCenterBufferFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterBufferFacade::getAllRows() - naming exception", ne);
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

        ServiceCenterBufferEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("ServiceCenterBufferFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("ServiceCenterBufferFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
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
        
        ServiceCenterBufferEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("ServiceCenterBufferFacade::addOrUpdateRow() - unknown key: " + arg);
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
        ServiceCenterBufferEjbPK key = new ServiceCenterBufferEjbPK(ServiceCenterBufferEjbUtil.generateGUID(this));

        ServiceCenterBufferEjbLocalHome home = null;

        System.out.println ( "ServiceCenterBufferFacade - Service Center Ticket Category: " + arg.getTicketCategory() );


        try {
            home = ServiceCenterBufferEjbUtil.getLocalHome();
            home.create(key, arg);       
        } catch(CreateException ce) {
            _log.error("ServiceCenterBufferFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("ServiceCenterBufferFacade::insertRow() - naming exception:" + arg + ":", ne);
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

        ServiceCenterBufferEjbLocal local = findByKey(arg);
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
    public ServiceCenterBufferEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();

            ServiceCenterBufferEjbLocal bean = null;
            
            try
            {
                bean = home.findByKey ( target );
            }
            catch ( ObjectNotFoundException exceptino )
            {
                bean = null;
            }

            return bean;
        } catch(FinderException fe) {
            _log.error("ServiceCenterBufferFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterBufferFacade::findByKey() - naming exception:" + target + ":", ne);
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
        
        try {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();
            ServiceCenterBufferEjbLocal record = home.findByTicketNumber(target);

            return ( null != record )? record.getValue() : null;
        } catch(FinderException fe) {
            _log.error("ServiceCenterBufferFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterBufferFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    /**
     *  Retrieves the oldest row
     */
    /*
    public ServiceCenter findOldest()
    {
        try
        {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();
            ServiceCenterBufferEjbLocal record = (ServiceCenterBufferEjbLocal) home.findOldest();

            return ( null != record )? record.getValue() : null;
        }
        catch ( FinderException exception )
        {
            _log.error ( "ServiceCenterBufferFacade::findOldest() - finder exception", exception );
        }
        catch ( NamingException exception )
        {
            _log.error ( "ServiceCenterBufferFacade::findOldest() - naming exception", exception );
        }

        return(null);
    }
    //*/

    /**
     *  Retrieves the newest row
     */
    /*
    public ServiceCenter findNewest()
    {
        try
        {
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();
            ServiceCenterBufferEjbLocal record = (ServiceCenterBufferEjbLocal) home.findNewest();

            return ( null != record )? record.getValue() : null;
        }
        catch ( FinderException exception )
        {
            _log.error ( "ServiceCenterBufferFacade::findNewest() - finder exception", exception );
        }
        catch ( NamingException exception )
        {
            _log.error ( "ServiceCenterBufferFacade::findNewest() - naming exception", exception );
        }

        return(null);
    }
    //*/


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
            ServiceCenterBufferEjbLocalHome home = ServiceCenterBufferEjbUtil.getLocalHome();
            ServiceCenterBufferEjbLocal record = (ServiceCenterBufferEjbLocal) home.findByMessageKey(key).iterator().next();

            return ( null != record )? record.getValue() : null;
        } catch(FinderException fe) {
            _log.error("ServiceCenterBufferFacade::findByMessageKey() - finder exception:" + key + ":", fe);
        } catch(NamingException ne) {
            _log.error("ServiceCenterBufferFacade::findByMessageKey() - naming exception:" + key + ":", ne);
        }

        return(null);
    }

    
    /**
     * Define logger
     */
    private Logger _log;
}

