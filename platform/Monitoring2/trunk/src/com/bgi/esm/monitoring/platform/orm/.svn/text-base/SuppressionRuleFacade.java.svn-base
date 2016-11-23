package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * Manipulate suppression rule entity beans
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class SuppressionRuleFacade {
    final protected SimpleDateFormat sdf = new SimpleDateFormat ( "MM/dd/yyyy HH:mm:ss" );
    
    /**
     * ctor, initialize weblogic loggging
     */
    public SuppressionRuleFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( SuppressionRuleFacade.class );
        }

        if ( null == _log ) {
            System.err.println("SuppressionRuleFacade ctor/log failure");
        }
    }

    /**
     * Return all suppression rule rows, might be large result set
     * 
     * @return all suppression rule rows, might be empty list (never null).
     */
    public List<SuppressionRule> getAllRows() {
        _log.debug("SuppressionRuleFacade::getAllRows() - entry");
        
        ArrayList<SuppressionRule> results = new ArrayList<SuppressionRule>();

        try {
            SuppressionRuleEjbLocalHome home = SuppressionRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                SuppressionRuleEjbLocal local = (SuppressionRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("SuppressionRuleFacade::getAllRows() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::getAllRows() - naming exception", ne);
        }

        return(results);
    }

    public List<SuppressionRule> findAllActiveSuppressions() {
        _log.debug("SuppressionRuleFacade::findAllActiveSuppressions() - entry");
        
        ArrayList<SuppressionRule> results = new ArrayList<SuppressionRule>();

        try {
            Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

            SuppressionRuleEjbLocalHome home = SuppressionRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findAllActiveSuppressionsAtTime (new Timestamp(timestamp.getTime().getTime())).iterator();
            while (iterator.hasNext()) {
                SuppressionRuleEjbLocal local = (SuppressionRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("SuppressionRuleFacade::findAllActiveSuppressions() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::findAllActiveSuppressions() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Return all suppression rule rows, might be large result set
     * 
     * @return all suppression rule rows, might be empty list (never null).
     */
    public List<SuppressionRule> getAllSuppressionNotifications() {
        _log.debug("SuppressionRuleFacade::getAllSuppressionNotifications() - entry");
        
        ArrayList<SuppressionRule> results = new ArrayList<SuppressionRule>();

        try {
            Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            _log.debug("SuppressionRuleFacade::getAllSuppressionNotifications() - timestamp: " + sdf.format ( timestamp.getTime() ) );

            SuppressionRuleEjbLocalHome home = SuppressionRuleEjbUtil.getLocalHome();
            Iterator iterator = home.findAllSuppressionNotifications(new Timestamp(timestamp.getTime().getTime())).iterator();
            while (iterator.hasNext()) {
                SuppressionRuleEjbLocal local = (SuppressionRuleEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error("SuppressionRuleFacade::getAllSuppressionNotifications() - finder exception", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::getAllSuppressionNotifications() - naming exception", ne);
        }

        return(results);
    }

    /**
     * Delete specified suppression rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(SuppressionRule arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("SuppressionRuleFacade::deleteRow() - null arg");
        }

        SuppressionRuleEjbLocal local = findByKey(arg.getRuleKey());
        if (local == null) {
            _log.error("SuppressionRuleFacade::deleteRow() - attempt to delete unknown row:" + arg.getRuleKey() + ":");
            return(false);
        }

        try
        {
            arg.setDeletedFlag ( true );

            return addOrUpdateRow ( arg, null );
        }
        catch ( NullPointerException npe )
        {
            _log.error ( "SuppressionRuleFacade::deleteRow() - Error encountered when trying to delete suppression #" + arg.getSuppressId(), npe );

            return false;
        }

        /*
        try {
            local.remove();
        } catch(RemoveException re) {
            _log.error("remove exception:" + arg.getRuleKey() + ":");
            return(false);
        }

        return(true);
        //*/
    }

    /**
     * Insert or update a suppression rule.
     * 
     * @param arg suppression rule
     * @return true if success
     * @throws NullPointerException if null arg
     * @throws IllegalArgumentException if invalid times
     */
    public boolean addOrUpdateRow(SuppressionRule arg, OrmFacade _orm) {
        _log.debug("SuppressionRuleFacade::addOrUpdateRow() - add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("null arg");
        }
        
        if (arg.isValidTime() == false) {
            _log.error("SuppressionRuleFacade::addOrUpdateRow() - invalid rule time");
            throw new IllegalArgumentException("invalid rule time");
        }

        if (arg.getRuleKey().equals(SuppressionRule.DEFAULT_KEY)) {
            String strTimezone = _orm.selectEebProperty( "server.time_zone" ).getPropertyValue();
            TimeZone tz        = TimeZone.getTimeZone ( strTimezone );
            int daylight_savings_offset = tz.getDSTSavings();
            int timezone_offset         = tz.getRawOffset();
            int offset                  = timezone_offset + daylight_savings_offset;
            arg.setSuppressId ( _orm.nextMonotonicSequenceForSuppression() );

            Calendar startTime = arg.getStartTime();
            Calendar endTime   = arg.getEndTime();
                startTime.add ( Calendar.MILLISECOND, -offset );
                endTime.add   ( Calendar.MILLISECOND, -offset );
            arg.setStartTime  ( startTime );
            arg.setEndTime    ( endTime );

            _log.debug("SuppressionRuleFacade::addOrUpdateRow() - saving:" + arg);

            return(insertRow(arg));
        }
        
        // falling through imples update, ensure key already exists

        SuppressionRuleEjbLocal local = findByKey(arg.getRuleKey());
        if (local == null) {
            _log.error("SuppressionRuleFacade::addOrUpdateRow() - unknown key" + arg);
            return(false);
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
    private boolean insertRow(SuppressionRule arg) {
        SuppressionRuleEjbPK key = new SuppressionRuleEjbPK(SuppressionRuleEjbUtil.generateGUID(this));

        SuppressionRuleEjbLocalHome home = null;

        try {
            home = SuppressionRuleEjbUtil.getLocalHome();
            home.create(key, arg);
        } catch(CreateException ce) {
            _log.error("SuppressionRuleFacade::insertRow() - create exception:" + arg + ":", ce);
            return(false);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::insertRow() - naming exception:" + arg + ":", ne);
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
    public SuppressionRule selectRow(String arg) {
        _log.debug("SuppressionRuleFacade::selectRow() - select:" + arg);

        SuppressionRuleEjbLocal local = findByKey(arg);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }
    
    /**
     * Return selected suppression rule referenced by suppression ID
     * 
     * @param suppress_id the suppression ID
     * @returns selected rule or null if not found
     */
    public SuppressionRule selectRowBySuppressId ( long suppress_id ) {
        _log.debug("SuppressionRuleFacade::selectRowBySuppressId() - select by suppress_id=" + suppress_id);

        SuppressionRuleEjbLocal local = findBySuppressId (suppress_id);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    /**
     * Retrieve an individual suppression rule
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private SuppressionRuleEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("null target");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("empty target");
        }
        
        try {
            SuppressionRuleEjbLocalHome home = SuppressionRuleEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch (FinderException fe) {
            _log.error("SuppressionRuleFacade::findByKey() - finder exception:" + target + ":", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::findByKey() - naming exception:" + target + ":", ne);
        }

        return(null);
    }
    
    
    /**
     * Retrieve an individual suppression rule referenced by their suppression ID
     * 
     * @param target key to select
     * @return rule or null if not found
     * @throws NullPointerException if null target
     * @throws IllegalArgumentException if empty target
     * @throws FinderException if select problem
     * @throws NamingException if JNDI problem
     */
    private SuppressionRuleEjbLocal findBySuppressId(long suppress_id ) {
        if (suppress_id < 1) {
            throw new IllegalArgumentException( "illegal suppression id: " + suppress_id );
        }
        
        try {
            SuppressionRuleEjbLocalHome home = SuppressionRuleEjbUtil.getLocalHome();
            return(home.findBySuppressId(suppress_id));
        } catch (FinderException fe) {
            _log.error("SuppressionRuleFacade::findBySuppressId() - finder exception for suppress_id=" + suppress_id + ":", fe);
        } catch(NamingException ne) {
            _log.error("SuppressionRuleFacade::findBySuppressId() - naming exception for suppress_id=" + suppress_id + ":", ne);
        }

        return(null);
    }

    /**
     * Define logger
     */
    private Logger _log;
}
