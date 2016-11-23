package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.value.Responder;

/**
 * Manipulate responder entity beans
 *
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class ResponderFacade {

    /**
     * ctor, initialize weblogic monitoring
     */
    public ResponderFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( Responder.class );
        }

        if ( null == _log ) {
            System.err.println("ResponderFacade ctor/log failure");
        }
    }

    /**
     * Return all responder rule rows, might be a very large result set
     *
     * @return all responder rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        ArrayList<Responder> results = new ArrayList<Responder>();

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                ResponderEjbLocal local = (ResponderEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(ObjectNotFoundException exception) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::getAllRows() - finder exception: " );
                    message.append ( exception.getMessage() );
                _log.info ( message.toString(), exception );
            }
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::getAllRows() - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::getAllRows() - naming exception: " );
                message.append ( ne.getMessage() );
            _log.error( message.toString(), ne);
        }

        return(results);
    }

    /**
     * Delete specified responder row
     *
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(Responder arg) {
        _log.debug("delete:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        ResponderEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            _log.error("ResponderFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowKey());
            return(false);
        }

        try {
            local.remove();
        } catch (RemoveException re) {
            _log.error("ResponderFacade::deleteRow() - remove exception:" + arg.getRowKey() + ":", re);
            return(false);
        }

        return(true);
    }

    /**
     * Insert or update a responder row
     *
     * @param arg responder row
     * @return true if success
     * @throws NullPointerException if null arg
     */
    public boolean addOrUpdateRow(Responder arg) {
        _log.debug("add:" + arg);

        if (arg == null) {
            throw new NullPointerException("null arg");
        }

        // If row with key can't be found, insert
        ResponderEjbLocal local = findByKey(arg.getRowKey());
        if (local == null) {
            return(insertRow(arg));
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
    private boolean insertRow(Responder arg) {
        String key_string  = ResponderEjbUtil.generateGUID(this);
        ResponderEjbPK key = new ResponderEjbPK( key_string );

        ResponderEjbLocalHome home = null;

        try {
            home = ResponderEjbUtil.getLocalHome();
            home.create(key, arg);

            arg.setRowKey ( key_string );
        } catch(CreateException ce) {
            _log.error("ResponderFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("ResponderFacade::insertRow() - naming exception:" + arg + ":", ne);
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
    public Responder selectRow(String arg) {
        _log.debug("select:" + arg);

        ResponderEjbLocal local = findByKey(arg);
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
    private ResponderEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null key");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty key");
        }

        if ( target.equals ( Responder.DEFAULT_KEY ) )
        {
            _log.error ( "ResponderFacade::findByKey() - Searching for Responder object with default key... returning null" );

            return null;
        }

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch(ObjectNotFoundException exception) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::findByKey ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::findByKey ( target=" );
                message.append ( target );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::findByKey ( target=" );
                message.append ( target );
                message.append ( " ) - naming exception: " );
                message.append ( ne.getMessage() );
            _log.error( message.toString(), ne);
        }

        return(null);
    }

    public List findAllByTicketNumber (String target) {
        if (target == null) {
            throw new NullPointerException("null ticket number");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty ticket number");
        }

        ArrayList<Responder> results = new ArrayList<Responder>();

        if ( target.equals ( "IM-Default" ) || target.equals ( "alarmpoint_only" ) ) {
            _log.info ( String.format ( "ResponderFacade::findByTicketNumber ( target=%s ) - skipping and returning empty list...", target ) );
            return results;
        }

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            Iterator iterator = home.findAllByServiceCenterTicketNumber(target).iterator();
            while (iterator.hasNext()) {
                ResponderEjbLocal local = (ResponderEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::findByTicketNumber ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::findByTicketNumber ( target=" );
                message.append ( target );
                message.append ( " ) - finder exception: " );
                message.append ( fe.getMessage() );
            _log.error( message.toString(), fe);
        } catch(NamingException ne) {
            StringBuilder message = new StringBuilder();
                message.append ( "ResponderFacade::findByTicketNumber ( target=" );
                message.append ( target );
                message.append ( " ) - naming exception: " );
                message.append ( ne.getMessage() );
            _log.error( message.toString(), ne);
        }

        return results;
    }
    private ResponderEjbLocal findByTicketNumber (String target) {
        if (target == null) {
            throw new NullPointerException("null message id");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty message id");
        }

        if ( target.equals ( "IM-Default" ) || target.equals ( "alarmpoint_only" ) ) {
            _log.info ( String.format ( "ResponderFacade::findByTicketNumber called for ( TicketID=%s )", target ) );
            return null;
        }

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            return(home.findByServiceCenterTicketNumber(target));
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::findByticketNumber ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            _log.error("ResponderFacade::findByServiceCenterTicketNumber() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ResponderFacade::findByServiceCenterTicketNumber() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    private ResponderEjbLocal findByMessageIdLocal (String target) {
        if (target == null) {
            throw new NullPointerException("null message id");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty message id");
        }

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            return(home.findByMessageIdLocal(target));
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::findBymessageIdLocal ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            _log.error("ResponderFacade::findByMessageIdLocal() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("ResponderFacade::findByMessageIdLocal() - naming exception:" + target + ":", ne);
        }

        return(null);
    }

    public Responder findByMessageKeyLocal (String target) {
        Calendar searchTimestamp   = Calendar.getInstance();
        searchTimestamp.add ( Calendar.HOUR_OF_DAY, -6 );

        Timestamp timestamp        = new Timestamp ( searchTimestamp.getTimeInMillis() );

        return findByMessageKeyLocal ( target, timestamp );
    }

    public Responder findByMessageKeyLocal (String target, Timestamp timestamp) {
        if (target == null) {
            throw new NullPointerException("null message key");
        }

        if (target.length() < 1) {
            throw new IllegalArgumentException("empty message key");
        }

        try {
            ResponderEjbLocalHome home = ResponderEjbUtil.getLocalHome();
            Iterator iterator          = home.findByMessageKeyLocal( target, timestamp ).iterator();
            if ( false == iterator.hasNext() )
            {
                return null;
            }
            ResponderEjbLocal record   = (ResponderEjbLocal) iterator.next();

            return ( null != record )? record.getValue() : null;
        } catch(ObjectNotFoundException exception ) {
            if ( _log.isInfoEnabled() ) {
                StringBuilder message = new StringBuilder();
                    message.append ( "ResponderFacade::findBymessageKeyLocal ( target=" );
                    message.append ( target );
                    message.append ( " ) - no records found" );
                _log.info ( message.toString() );
            }
        } catch(FinderException fe) {
            _log.error("ResponderFacade::findByMessageKeyLocal() - finder exception:" + target + ":", fe );
        } catch(NamingException ne) {
            _log.error("ResponderFacade::findByMessageKeyLocal() - naming exception:" + target + ":", ne );
        }

        return(null);
    }

    public Responder findByServiceCenterTicketNumber ( String ticket_number )
    {
        ResponderEjbLocal local = findByTicketNumber ( ticket_number );

        if ( null != local )
        {
            return local.getValue();
        }
        else
        {
            return null;
        }
    }

    public Responder findByMessageId ( String message_id )
    {
        ResponderEjbLocal local = findByMessageIdLocal ( message_id );

        if ( null != local )
        {
            return local.getValue();
        }
        else
        {
            return null;
        }
    }


    /**
     * Define logger
     */
    private Logger _log;
}
