package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;

/**
 * Manipulate suppression rule entity beans
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public final class EventsByGroupFacade {

    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );

    /**
     * ctor, initialize weblogic loggging
     */
    public EventsByGroupFacade() {
        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            _log = Logger.getLogger ( EventsByGroupFacade.class );
        }
        
        if ( null == _log ) {
            System.err.println("EventsByGroupFacade ctor/log failure");
        }
    }

    /**
     * Return all suppression rule rows, might be large result set
     * 
     * @return all suppression rule rows, might be empty list (never null).
     */
    public List getAllRows() {
        _log.debug("getAllRows");
        
        ArrayList<EventsByGroup> results = new ArrayList<EventsByGroup>();

        try {
            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            Iterator iterator = home.findAll().iterator();
            while (iterator.hasNext()) {
                EventsByGroupEjbLocal local = (EventsByGroupEjbLocal) iterator.next();
                results.add(local.getValue());
            }
        } catch(FinderException fe) {
            _log.error( "EventByGroupFacade - could not retrieve entire list of events" );
        } catch(NamingException ne) {
            _log.error( "EventByGroupFacade - naming exception encountered when retrieving entire list of events");
        }

        return(results);
    }

    public List findAllBetweenTimesByGroup ( String group, Timestamp start, Timestamp end )
    {
        _log.debug("findAllBetweenTimesByGroup");
        
        ArrayList<EventsByGroup> results = new ArrayList<EventsByGroup>();

        try
        {
            if ( _log.isInfoEnabled() )
            {
                Date date_start = new Date ( start.getTime() );
                Date date_end   = new Date ( end.getTime()   );

                StringBuilder message = new StringBuilder();
                    message.append ( "EventsByGroupFacade::findAllBetweenTimesByGroup ( group=" );
                    message.append ( group );
                    message.append ( ", StartTime=" );
                    message.append ( sdf.format ( date_start ) );
                    message.append ( ", EndTime=" );
                    message.append ( sdf.format ( date_end ) );
                    message.append ( " )" );
                _log.info ( message.toString() );
            }

            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            Iterator iterator = home.findAllBetweenTimesByGroup( group, start, end ).iterator();
            while (iterator.hasNext()) 
            {
                EventsByGroupEjbLocal local = (EventsByGroupEjbLocal) iterator.next();
                results.add(local.getValue());
            }

        }
        catch ( FinderException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - could not retrieve entire list of events for keys " );
                message.append ( " ( group, start, end ) = ( " );
                message.append ( group );
                message.append ( ", " );
                message.append ( start.toString() );
                message.append ( ", " );
                message.append ( end.toString() );
                message.append ( " )" );
            _log.error( message.toString(), exception );
        }
        catch ( NamingException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - naming exception encountered when retrieving entire list of events for keys ");
            message.append ( " ( group, start, end ) = ( " );
            message.append ( group );
            message.append ( ", " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }

        return results;
    }


    public List findAllBetweenTimesByApplication ( String application, Timestamp start, Timestamp end )
    {
        _log.debug("findAllBetweenTimesByApplication");
        
        ArrayList<EventsByGroup> results = new ArrayList<EventsByGroup>();

        try
        {
            if ( _log.isInfoEnabled() )
            {
                Date date_start = new Date ( start.getTime() );
                Date date_end   = new Date ( end.getTime()   );

                StringBuilder message = new StringBuilder();
                    message.append ( "EventsByGroupFacade::findAllBetweenTimesByApplication ( application=" );
                    message.append ( application );
                    message.append ( ", StartTime=" );
                    message.append ( sdf.format ( date_start ) );
                    message.append ( ", EndTime=" );
                    message.append ( sdf.format ( date_end ) );
                    message.append ( " )" );
                _log.info ( message.toString() );
            }

            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            Iterator iterator = home.findAllBetweenTimesByApplication ( application, start, end ).iterator();
            while (iterator.hasNext()) 
            {
                EventsByGroupEjbLocal local = (EventsByGroupEjbLocal) iterator.next();
                results.add(local.getValue());
            }

        }
        catch ( FinderException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - could not retrieve entire list of events for keys " );
            message.append ( " ( application, start, end ) = ( " );
            message.append ( application );
            message.append ( ", " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }
        catch ( NamingException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - naming exception encountered when retrieving entire list of events for keys ");
            message.append ( " ( application, start, end ) = ( " );
            message.append ( application );
            message.append ( ", " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }

        return results;
    }

    public List findAllBetweenTimesByGroupApplication ( String group, String application, Timestamp start, Timestamp end )
    {
        _log.debug("findAllBetweenTimesByGroupApplication");
        
        ArrayList<EventsByGroup> results = new ArrayList<EventsByGroup>();

        try
        {
            if ( _log.isInfoEnabled() )
            {
                Date date_start = new Date ( start.getTime() );
                Date date_end   = new Date ( end.getTime()   );

                StringBuilder message = new StringBuilder();
                    message.append ( "EventsByGroupFacade::findAllBetweenTimesByGroupApplication ( group=" );
                    message.append ( group );
                    message.append ( ", application=" );
                    message.append ( application );
                    message.append ( ", StartTime=" );
                    message.append ( sdf.format ( date_start ) );
                    message.append ( ", EndTime=" );
                    message.append ( sdf.format ( date_end ) );
                    message.append ( " )" );
                _log.info ( message.toString() );
            }

            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            Iterator iterator = home.findAllBetweenTimesByGroupApplication ( group, application, start, end ).iterator();
            while (iterator.hasNext()) 
            {
                EventsByGroupEjbLocal local = (EventsByGroupEjbLocal) iterator.next();
                results.add(local.getValue());
            }

        }
        catch ( FinderException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - could not retrieve entire list of events for keys " );
            message.append ( " ( group, application, start, end ) = ( " );
            message.append ( group );
            message.append ( ", " );
            message.append ( application );
            message.append ( ", " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }
        catch ( NamingException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - naming exception encountered when retrieving entire list of events for keys ");
            message.append ( " ( application, start, end ) = ( " );
            message.append ( application );
            message.append ( ", " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }

        return results;
    }


    public List findAllBetweenTimes ( Timestamp start, Timestamp end )
    {
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "EventsByGroupFacade::findAllBetweenTimes ( Start=" );
                message.append ( sdf.format ( start.getTime() ) );
                message.append ( ", End=" );
                message.append ( sdf.format ( end.getTime() ) );
                message.append ( " )" );
            _log.debug( message.toString() );
        }
        
        ArrayList<EventsByGroup> results = new ArrayList<EventsByGroup>();

        try
        {
            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            Iterator iterator = home.findAllBetweenTimes( start, end ).iterator();
            while (iterator.hasNext()) 
            {
                EventsByGroupEjbLocal local = (EventsByGroupEjbLocal) iterator.next();
                results.add(local.getValue());
            }

        }
        catch ( FinderException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - could not retrieve entire list of events for keys " );
            message.append ( " ( start, end ) = ( " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }
        catch ( NamingException exception )
        {
            StringBuilder message = new StringBuilder ( "EventByGroupFacade - naming exception encountered when retrieving entire list of events for keys ");
            message.append ( " ( start, end ) = ( " );
            message.append ( start.toString() );
            message.append ( ", " );
            message.append ( end.toString() );
            message.append ( " )" );

            _log.error( message.toString(), exception );
        }

        return results;
    }

    /**
     * Delete specified suppression rule
     * 
     * @param rule to delete
     * @return true success
     * @throws NullPointerException if null arg
     */
    public boolean deleteRow(EventsByGroup arg) {
        _log.debug("delete:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("EventsByGroupFacade::deleteRow() - null arg");
        }

        EventsByGroupEjbLocal local = findByKey(arg.getRowId());
        if (local == null) {
            _log.error("EventsByGroupFacade::deleteRow() - attempt to delete unknown row:" + arg.getRowId() );
            return(false);
        }

        try {
            local.remove();
        } catch(RemoveException re) {
            _log.error("EventsByGroupFacade::deleteRow() - remove exception:" + arg.getRowId() );
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
    public boolean addOrUpdateRow(EventsByGroup arg )
    {
        _log.debug("add:" + arg);
        
        if (arg == null) {
            throw new NullPointerException("EventsByGroupFacade::addOrUpdateRow() - null arg");
        }
        
        if (arg.getRowId().equals(EventsByGroup.DEFAULT_KEY)) {
            return(insertRow(arg));
        }
        
        // falling through imples update, ensure key already exists

        EventsByGroupEjbLocal local = findByKey(arg.getRowId());
        if (local == null) {
            _log.error("EventsByGroupFacade::addOrUpdateRow() - unknown key" + arg);
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
    private boolean insertRow(EventsByGroup arg) {
        EventsByGroupEjbPK key = new EventsByGroupEjbPK(EventsByGroupEjbUtil.generateGUID(this));

        EventsByGroupEjbLocalHome home = null;

        try {
            home = EventsByGroupEjbUtil.getLocalHome();
            home.create(key, arg);
        } catch(CreateException ce) {

            _log.error("EventsByGroupFacade - could not create row:" + arg );
            return(false);
        } catch(NamingException ne) {
            _log.error("EventsByGroupFacade - naming exception:" + arg );
            return(false);
        }

        return(true);
    }

    /**
     * Return selected event/group object
     * 
     * @param arg row_id
     * @returns selected rule or null if not found
     */
    public EventsByGroup selectRow(String row_id) {
        _log.debug("select:" + row_id);

        EventsByGroupEjbLocal local = findByKey(row_id);
        if (local == null) {
            return(null);
        }

        return(local.getValue());
    }

    public EventsByGroup selectRowMessageId ( String message_id ) {
        _log.debug("select:" + message_id );

        EventsByGroupEjbLocal local = findByMessageId(message_id );
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
    private EventsByGroupEjbLocal findByKey(String target) {
        if (target == null) {
            throw new NullPointerException("EventsByGroupFacade::findByKey() - null target");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("EventsByGroupFacade::findByKey() - empty target");
        }
        
        try {
            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            return(home.findByKey(target));
        } catch (FinderException fe) {
            _log.error("EventsByGroupFacade - Could not find row where target=" + target + ":");
        } catch(NamingException ne) {
            _log.error("EventsByGroupFacade - naming exception:" + target );
        }

        return(null);
    }

    private EventsByGroupEjbLocal findByMessageId(String target) {
        if (target == null) {
            throw new NullPointerException("EventsByGroupFacade::findByMessageId() - null target");
        }
        
        if (target.length() < 1) {
            throw new IllegalArgumentException("EventsByGroupFacade::findByMessageId() - empty target");
        }
        
        try {
            EventsByGroupEjbLocalHome home = EventsByGroupEjbUtil.getLocalHome();
            return(home.findByMessageId(target));
        } catch (FinderException fe) {
            _log.error("EventsByGroupFacade - Could not find row where message_id=" + target + ":");
        } catch(NamingException ne) {
            _log.error("EventsByGroupFacade - naming exception:" + target );
        }

        return(null);
    }


    /**
     * Define logger
     */
    private Logger _log;
}
