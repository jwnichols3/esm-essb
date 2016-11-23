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
import com.bgi.esm.monitoring.platform.shared.value.Audit;

/**
 * Manipulate audit entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, userid=linden)
 */
public final class AuditFacade {
    
    /**
     * ctor, initialize weblogic monitoring
     */
    public AuditFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( AuditFacade.class );
        }

        if ( null == _log ) {
            System.err.println("AuditFacade ctor/log failure");
        }
    }
    
    /**
     * Return all audit rule rows, might be a very large result set
     * 
     * @return all audit rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<Audit> results = new ArrayList<Audit>();

        try {
            AuditEjbLocalHome home = AuditEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                AuditEjbLocal local = (AuditEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("AuditFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("AuditFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    public List getAllRowsForMessageId ( String message_id )
    {
        ArrayList <Audit> results = new ArrayList <Audit> ();

        try 
        {
            AuditEjbLocalHome home = AuditEjbUtil.getLocalHome();
            Iterator iterator = home.findByMessageId( message_id ).iterator();
            while (iterator.hasNext()) 
            {
                AuditEjbLocal local = (AuditEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(ObjectNotFoundException exception) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "AuditFacade::getAllRowsForMessageId ( message_id=" );
                    message.append ( message_id );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } 
        catch(FinderException fe) 
        {
            _log.error("AuditFacade::getAllRowsForMessageId() - finder exception", fe);
        } 
        catch(NamingException ne) 
        {
            _log.error("AuditFacade::getAllRowsForMessageId() - naming exception");
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
    public boolean deleteRow(Audit arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        AuditEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("AuditFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("AuditFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
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
    public boolean addOrUpdateRow(Audit arg) {
        _log.debug("add:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.getRowKey().equals(Audit.DEFAULT_KEY)) {
            return(insertRow(arg));
        }

        // falling through implies update, test for spoof key
        
        AuditEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("AuditFacade::addOrUpdateRow() - unknown key" + arg);
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
    private boolean insertRow(Audit arg) {
        AuditEjbPK key = new AuditEjbPK(AuditEjbUtil.generateGUID(this));

        AuditEjbLocalHome home = null;

        try {
            home = AuditEjbUtil.getLocalHome();
            home.create(key, arg);       
        } catch(CreateException ce) {
            _log.error("AuditFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("AuditFacade::insertRow() - naming exception:" + arg + ":", ne);
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
    public Audit selectRow(String arg) {
        _log.debug("select:" + arg);

        AuditEjbLocal local = findByKey(arg);
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
    private AuditEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }
        
        try {
            AuditEjbLocalHome home = AuditEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(ObjectNotFoundException exception) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "AuditFacade::findByKey ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            _log.error("AuditFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("AuditFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }
    
    /**
     * Define logger
     */
    private Logger _log;
}
