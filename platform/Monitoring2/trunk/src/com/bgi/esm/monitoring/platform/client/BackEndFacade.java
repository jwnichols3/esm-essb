package com.bgi.esm.monitoring.platform.client;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Hashtable;
import javax.naming.Context;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.RawOvi;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import com.bgi.esm.monitoring.platform.shared.value.Spool;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRuleAudit;

/**
 * Access WebLogic via RMI. 
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class BackEndFacade {

    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy MMM dd HH:mm:ss" );

    /**
     * ctor
     */
    public BackEndFacade() {
        _log.info("BackEndFacade creation");
    }

    /**
     * Hook for shutdown action. Nothing for WebLogic to do.
     */
    public void gracefulShutDown() {
        _log.info("graceful shutdown");
    }

    /**
     * Configure RMI for localhost.
     */
    public void loadLocalHost() {
        Hashtable<String, String> jndi = new Hashtable<String, String>();

        jndi.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        jndi.put(Context.PROVIDER_URL, "t3://calntalp202:7001");

        RmiProperties.setJndi(jndi);
    }

    /**
     * Test method, flip arg and return result.
     * 
     * @param arg flag to flip
     * @return flipped flag
     * @throws BackEndFailure if RMI failure
     */
    public boolean isPingTest(boolean arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.isPingTest(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::isPingTest ( arg ) = ( " );
            message.append ( Boolean.toString ( arg ) );
            message.append ( " )" );

            _log.error( message.toString(), exception );
            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// ServiceCenter ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    public List getAllServiceCenterTickets() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllServiceCenterTickets();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( ":::getAllServiceCenterTickets()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public ServiceCenter addOrUpdateServiceCenter ( ServiceCenter arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        System.out.println ( "BackEndFacade - Service Center Ticket Category: " + arg.getTicketCategory() );

        try
        {
            return sfer.addOrUpdateServiceCenter ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateServiceCenter() with ServiceCenter object - " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public ServiceCenter selectServiceCenterTicket ( String ticket_number ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectServiceCenterTicket ( ticket_number );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectServiceCenterTicket() - ticket_number=" );
            message.append ( ticket_number );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public ServiceCenter selectServiceCenterTicketByMessageKey ( String message_key ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectServiceCenterTicketByMessageKey ( message_key );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectServiceCenterTicket() - message_key=" );
            message.append ( message_key );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// ServiceCenterBuffer /////////////////////////////////////
    /////////////////////////////////////////////////////////////

    public List getAllServiceCenterTicketsInBuffer() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllServiceCenterTicketsInBuffer();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( ":::getAllServiceCenterTicketsInBuffer()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public ServiceCenter addOrUpdateServiceCenterBuffer ( ServiceCenter arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        System.out.println ( "BackEndFacade - Service Center Ticket Category: " + arg.getTicketCategory() );

        try
        {
            return sfer.addOrUpdateServiceCenterBuffer ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateServiceCenterBuffer() with ServiceCenter object - " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean deleteServiceCenterBuffer ( ServiceCenter arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        System.out.println ( "BackEndFacade - Service Center Ticket Category: " + arg.getTicketCategory() );

        try
        {
            return sfer.deleteServiceCenterBuffer ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteServiceCenterBuffer() with ServiceCenter object - " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /*
    public ServiceCenter findOldestServiceCenterTicketInBuffer() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.findOldestServiceCenterTicketInBuffer();
        }
        catch ( RemoteException exception )
        {
            _log.error ( "Could not retrieve oldest ticket from Service Center buffer", exception );
        }

        return null;
    }

    public ServiceCenter findNewestServiceCenterTicketInBuffer() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.findNewestServiceCenterTicketInBuffer();
        }
        catch ( RemoteException exception )
        {
            _log.error ( "Could not retrieve newest ticket from Service Center buffer", exception );
        }

        return null;
    }
    //*/


    /////////////////////////////////////////////////////////////
    /// Responder     ///////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public Responder findResponderByServiceCenterTicketNumber( String ticket_number ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.findResponderByServiceCenterTicketNumber( ticket_number );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findResponderByServiceCenterTicketNumber()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllResponderIncidents() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllResponderIncidents();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllResponderIncidents()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Delete specified responder row
     * 
     * @param row to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteResponder( Responder arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteResponder(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteResponder() with Responder object - " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Responder addOrUpdateResponder ( Responder arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        System.out.println ( "BackEndFacade - Responder Message ID: " + arg.getRowKey() );

        try
        {
            return sfer.addOrUpdateResponder ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateResponder() with Responder object - " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Responder selectResponder ( String row_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectResponder ( row_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectResponder() - row_id=" );
            message.append ( row_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Responder selectResponderByMessageId ( String message_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectResponderByMessageId ( message_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectResponderByMessageID() - message_id=" );
            message.append ( message_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Responder selectResponderByMessageKey ( String message_key, Calendar searchTimestamp ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectResponderByMessageKey ( message_key, searchTimestamp );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectResponderByMessageKey() - message_key=" );
            message.append ( message_key );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Responder selectResponderByMessageKey ( String message_key ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectResponderByMessageKey ( message_key );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectResponderByMessageKey() - message_key=" );
            message.append ( message_key );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /// Alarmpoint //////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllAlarmpointEvents() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllAlarmpointEvents();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllAlarmpointEvents()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Alarmpoint addOrUpdateAlarmpoint ( Alarmpoint arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.addOrUpdateAlarmpoint ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateAlarmpoint() with Alarmpoint object: " );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public Alarmpoint selectAlarmpointEventByEventID ( String event_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectAlarmpointEventByEventID ( event_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectAlarmpointEventByEventID() with event_id=" );
            message.append ( event_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /// Audit ///////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all audit datum
     * 
     * @return all audit datum, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllAudit() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllAudit());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllAudit()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllAuditForMessageId ( String message_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllAuditForMessageId ( message_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllAuditForMessageId() with message_id=" );
            message.append ( message_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Delete specified audit row
     * 
     * @param row to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteAudit(Audit arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteAudit(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteAudit() with Audit object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update audit row. Returns updated value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg audit datum
     * @returns updated row or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public Audit addOrUpdateAudit(Audit arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateAudit(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateAudit() with Audit object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected audit datum
     * 
     * @param arg audit key
     * @returns selected row or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public Audit selectAudit(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectAudit(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectAudit() with row key=" );
            message.append ( arg );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// DataMap /////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all data map rows
     * 
     * @return all data map rows, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllDataMapRules() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllDataMapRules());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllDataMapRules()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllDataMapRulesPaginate ( int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try 
        {
            list = sfer.getAllDataMapRulesPaginate ( num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllDataMapRulesPaginate() - ( num_per_page, page_num ) = ( " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getDataMapRuleAuditVersions ( String group ) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getDataMapRuleAuditVersions ( group ));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getDataMapRuleAuditVersions() with group=" );
            message.append ( group );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /**
     * Delete specified data map rule
     * 
     * @param rule to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteDataMapRule(DataMapRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteDataMapRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteDataMapRule() with DataMapRule object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update a data map rule. Returns fresh value object.
     * 
     * @param arg data map rule
     * @returns updated data map rule or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public DataMapRule addOrUpdateDataMapRule(DataMapRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateDataMapRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateDataMapRule() with DataMapRule object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected data map rule.
     * 
     * @param arg rule key (message group)
     * @returns selected rule or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public DataMapRule selectDataMapRule(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectDataMapRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /// Events By Group /////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllEventsByGroup() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllEventsByGroup();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllEventsByGroup()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List findAllEventsByGroupBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.findAllEventsByGroupBetweenTimesByGroup ( group, start_time, end_time );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllEventsByGroupBetweenTimesByGroup ( group, start_time, end_time ) = ( " );
            message.append ( group );
            message.append ( ", " );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", " );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( " )" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List findAllEventsByGroupBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllEventsByGroupBetweenTimesByGroupPaginate ( group, start_time, end_time, num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllEventsByGroupBetweenTimesByGroupPaginate ( group, start_time, end_time, num_per_page, page_num ) = ( " );
            message.append ( group );
            message.append ( ", " );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", " );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( ", " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List findAllEventsByGroupsBetweenTimes ( Calendar start_time, Calendar end_time ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllEventsByGroupsBetweenTimes ( start_time, end_time );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllEventsByGroupBetweenTimesByGroupPaginate ( start_time, end_time ) = ( " );
            message.append ( sdf.format ( start_time ) );
            message.append ( ", " );
            message.append ( sdf.format ( end_time ) );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List findAllEventsByGroupsBetweenTimesPaginate ( Calendar start_time, Calendar end_time, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num ) = ( " );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", " );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( ", " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }


            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List findAllBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByGroup ( group, start_time, end_time );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByGroup ( group=" );
            message.append ( group );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time ) );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public List findAllBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByGroupPaginate ( group, start_time, end_time, num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByGroupPaginate ( group=" );
            message.append ( group );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( ", num_per_page=" );
            message.append ( num_per_page );
            message.append ( ", page_num=" );
            message.append ( page_num );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public List findAllBetweenTimesByApplication ( String application, Calendar start_time, Calendar end_time ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByApplication ( application, start_time, end_time );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByApplication ( application=" );
            message.append ( application );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public List findAllBetweenTimesByApplicationPaginate ( String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByApplicationPaginate ( application, start_time, end_time, num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByApplication ( application=" );
            message.append ( application );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public List findAllBetweenTimesByGroupApplication ( String group, String application, Calendar start_time, Calendar end_time ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByGroupApplication ( group, application, start_time, end_time );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByGroupApplication ( group=" );
            message.append ( group );
            message.append ( ", application=" );
            message.append ( application );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public List findAllBetweenTimesByGroupApplicationPaginate ( String group, String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try
        {
            list = sfer.findAllBetweenTimesByGroupApplicationPaginate ( group, application, start_time, end_time, num_per_page, page_num );

            return list;
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::findAllBetweenTimesByGroupApplication ( group=" );
            message.append ( group );
            message.append ( ", application=" );
            message.append ( application );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( " )" );

            throw new BackEndFailure ( message.toString() );
        }
    }

    public EventsByGroup selectEventsByGroup ( String row_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectEventsByGroup ( row_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectEventsByGroup() with row_id=" );
            message.append ( row_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public EventsByGroup selectEventsByGroupByMessageId ( String message_id ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectEventsByGroupByMessageId ( message_id );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectEventsByGroup() with message_id=" );
            message.append ( message_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    public EventsByGroup addOrUpdateEventsByGroup ( EventsByGroup arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.addOrUpdateEventsByGroup ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateEventsByGroup() with EventsByGroup object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean deleteEventsByGroup ( EventsByGroup arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.deleteEventsByGroup ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteEventsByGroup() with EventsByGroup object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /// EEB Property ////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllEebProperties() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getAllEebProperties();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllEebProperties()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public EebProperty addOrUpdateEebProperty ( EebProperty arg ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.addOrUpdateEebProperty ( arg );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateEebProperty () with EebProperty object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public EebProperty selectEebProperty ( String property_name ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.selectEebProperty ( property_name );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append( "::selectEebProperty() with property_name=" );
            message.append ( property_name );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean deleteEebProperty ( String property_name ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.deleteEebProperty ( property_name );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteEebProperty() with property_name=" );
            message.append ( property_name );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public void setEebPropertyServiceCenterActive ( boolean is_active ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            sfer.setEebPropertyServiceCenterActive ( is_active );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::setEebPropertyServiceCenterActive() with is_active=" );
            message.append ( Boolean.toString ( is_active ) );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean getEebPropertyServiceCenterActive() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try
        {
            return sfer.getEebPropertyServiceCenterActive ();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getEebPropertyServiceCenterActive()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public void setEebPropertyAlarmpointActive ( boolean is_active ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = null;

        try
        {
            sfer = getRemote();

            sfer.setEebPropertyAlarmpointActive ( is_active );
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::setEebPropertyAlarmpointActive() with is_active=" );
            message.append ( Boolean.toString ( is_active ) );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean getEebPropertyAlarmpointActive() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = null;

        try
        {
            sfer = getRemote();

            return sfer.getEebPropertyAlarmpointActive ();
        }
        catch ( RemoteException exception )
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getEebPropertyAlarmpointActive()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }
 

    /////////////////////////////////////////////////////////////
    /// Raw OVI /////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all raw OVI datum
     * 
     * @return all raw OVI datum, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllRawOvi() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllRawOvi());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllRawOvi()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Delete specified OVI datum
     * 
     * @param row to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteRawOvi(RawOvi arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteRawOvi(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteRawOvi() with RawOvi object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update raw OVI datum. Returns updated value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg raw OVI datum
     * @returns updated row or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public RawOvi addOrUpdateRawOvi(RawOvi arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateRawOvi(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateRawOvi() with RawOvi object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected raw OVI datum
     * 
     * @param arg raw OVI key
     * @returns selected row or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public RawOvi selectRawOvi(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectRawOvi(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectRawOvi() with row key=" );
            message.append ( arg );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// Suppression /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /**
     * Return all suppression rows
     * 
     * @return all suppression rows, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllSuppressionRules() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllSuppressionRules());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllSuppressionRules()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllSuppressionNotifications() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllSuppressionNotifications());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllSuppressionNotifications()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    public List getAllSuppressionRulesPaginate ( int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try 
        {
            list = sfer.getAllSuppressionRulesPaginate ( num_per_page, page_num );

            return list;
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllSuppressionRulesPaginate ( num_per_page, page_num ) = ( " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllActiveSuppressionRules() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.getAllActiveSuppressionRules() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllActiveSuppressionRules()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllActiveSuppressionRulesPaginate ( int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try 
        {
            list = sfer.getAllActiveSuppressionRulesPaginate ( num_per_page, page_num );

            return list;
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllActiveSuppressionRulesPaginate ( num_per_page, page_num ) = ( " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllActiveSuppressionRulesForUser ( String user ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return( sfer.getAllActiveSuppressionRulesForUser ( user ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllActiveSuppressionRulesForUser() with user=" );
            message.append ( user );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getAllActiveSuppressionRulesForUserPaginate ( String user, int num_per_page, int page_num ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        List list = null;

        try 
        {
            list = sfer.getAllActiveSuppressionRulesForUserPaginate ( user, num_per_page, page_num );

            return list;
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllActiveSuppressionRulesForUserPaginate ( user, num_per_page, page_num ) = ( " );
            message.append ( user );
            message.append ( ", " );
            message.append ( num_per_page );
            message.append ( ", " );
            message.append ( page_num );
            message.append ( " )" );

            if ( null != list )
            {
                message.append ( " returned num results: " );
                message.append ( list.size() );
            }

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    public List searchActiveSuppressionRules 
            ( String user, String description, String app_name, String node_name, String db_server, 
              String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return( sfer.searchActiveSuppressionRules ( user, description, app_name, node_name, db_server, message_text, start_time, end_time, remove_on_reboot, was_deleted ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::searchActiveSuppressionRules ( user=" );
            message.append ( user );
            message.append ( ", description=" );
            message.append ( description );
            message.append ( ", app_name=" );
            message.append ( app_name );
            message.append ( ", node_name=" );
            message.append ( node_name );
            message.append ( ", db_server=" );
            message.append ( db_server );
            message.append ( ", message_text=" );
            message.append ( message_text );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( ", remove_on_reboot=" );
            message.append ( remove_on_reboot );
            message.append ( ", was_deleted=" );
            message.append ( was_deleted );
            message.append ( " )" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List searchHistoricalSuppressionRules ( String user, String description, String app_name, String node_name, String db_server, 
            String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return( sfer.searchHistoricalSuppressionRules ( user, description, app_name, node_name, db_server, message_text, start_time, end_time, remove_on_reboot, was_deleted ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::searchHistoricalSuppressionRules ( user=" );
            message.append ( user );
            message.append ( ", description=" );
            message.append ( description );
            message.append ( ", app_name=" );
            message.append ( app_name );
            message.append ( ", node_name=" );
            message.append ( node_name );
            message.append ( ", db_server=" );
            message.append ( db_server );
            message.append ( ", message_text=" );
            message.append ( message_text );
            message.append ( ", start_time=" );
            message.append ( sdf.format ( start_time.getTime() ) );
            message.append ( ", end_time=" );
            message.append ( sdf.format ( end_time.getTime() ) );
            message.append ( ", remove_on_reboot=" );
            message.append ( remove_on_reboot );
            message.append ( ", was_deleted=" );
            message.append ( was_deleted );
            message.append ( " )" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public List getSuppressionRuleAuditVersions ( long suppress_id ) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getSuppressionRuleAuditVersions ( suppress_id ));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getSuppressionRuleAuditVersions ( suppress_id ) = ( " );
            message.append ( suppress_id );
            message.append ( " )" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /**
     * Delete specified suppression rule
     * 
     * @param rule to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteSuppressionRule(SuppressionRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteSuppressionRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteSuppressionRule() with SuppressionRule object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update a suppression rule. Returns updated value object.
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg suppression rule
     * @returns updated suppression rule or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public SuppressionRule addOrUpdateSuppressionRule(SuppressionRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateSuppressionRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateSuppressionRule() with SuppressionRule object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected suppression rule.
     * 
     * @param arg rule key
     * @returns selected rule or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public SuppressionRule selectSuppressionRule(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectSuppressionRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectSuppressionRule() with row_key=" );
            message.append ( arg );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected suppression rule.
     * 
     * @param arg rule key
     * @returns selected rule or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public SuppressionRule selectSuppressionRuleBySuppressId ( long suppress_id ) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectSuppressionRuleBySuppressId ( suppress_id ));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectSupperssionRuleBySuppressId ( suppress_id ) = ( " );
            message.append ( suppress_id );
            message.append ( " )" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// Throttle (Rules) ////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all throttle rows
     * 
     * @return all throttle rows, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllThrottleRules() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllThrottleRules());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllThrottleRules()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Delete specified throttle rule
     * 
     * @param rule to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteThrottleRule(ThrottleRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteThrottleRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteThrottleRule() with ThrottleRule object=" );
            message.append ( arg.toString() );
            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update a throttle rule. Returns updated value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg throttle rule
     * @returns updated throttle rule or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public ThrottleRule addOrUpdateThrottleRule(ThrottleRule arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateThrottleRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateThrottleRule() with ThrottleRule object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected throttle rule.
     * 
     * @param arg rule key
     * @returns selected rule or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public ThrottleRule selectThrottleRule(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectThrottleRule(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectThrottleRule() with row_key=" );
            message.append ( arg );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }
    
    /////////////////////////////////////////////////////////////
    /// Throttle (Spool) ////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return all spool rows
     * 
     * @return all spool rows, might be empty list (never null).
     * @throws BackEndFailure if RMI failure
     */
    public List getAllSpoolRows() throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.getAllSpoolRows());
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getAllSpoolRows()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }
    
    /**
     * Delete specified spool row
     * 
     * @param spool to delete
     * @return true success
     * @throws BackEndFailure if RMI failure
     */
    public boolean deleteSpoolRow(Spool arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.deleteSpoolRow(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::deleteSpoolRow() with Spool object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Insert or update a spool row. Returns fresh value object. 
     * Clients need the update because I might change the key, etc.
     * 
     * @param arg spool row
     * @returns updated spool row or null if problem
     * @throws BackEndFailure if RMI failure
     */
    public Spool addOrUpdateSpoolRow(Spool arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.addOrUpdateSpoolRow(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::addOrUpdateSpoolRow() where Spool object=" );
            message.append ( arg.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /**
     * Return selected spool row.
     * 
     * @param arg key (message group)
     * @returns selected spool row or null if not found
     * @throws BackEndFailure if RMI failure
     */
    public Spool selectSpoolRow(String arg) throws BackEndFailure {
        SessionFacadeEjbRemote sfer = getRemote();

        try {
            return(sfer.selectSpoolRow(arg));
        } catch( RemoteException exception ) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::selectSpoolRow() where row_key = " );
            message.append ( arg );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /// Storm ///////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public List getAllActiveStorms () throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.getAllActiveStorms() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::nextMonotonicSequenceValue()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    public int nextMonotonicSequenceValue() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.nextMonotonicSequenceValue() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::nextMonotonicSequenceValue()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public int nextMonotonicSequenceForDataMap() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.nextMonotonicSequenceForDataMap() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::nextMonotonicSequenceForDataMap()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public int nextMonotonicSequenceForSuppression() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.nextMonotonicSequenceForSuppression() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::nextMonotonicSequenceForSuppression()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public int nextMonotonicSequenceForThrottle() throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.nextMonotonicSequenceForThrottle() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::nextMonotonicSequenceForThrottle()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean sendSuppressionDrainMessage() throws BackEndFailure
    {
        _log.debug("Entering sendSuppressionDrainMessage");
        
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            _log.debug("Exiting sendSuppressionDrainMessage");
            return ( sfer.sendSuppressionDrainMessage() );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::sendSuppressionDrainMessage()" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean sendSuppressionDrainMessage ( SuppressionDrainMessage suppression_drain_message ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.sendSuppressionDrainMessage ( suppression_drain_message ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::sendSuppressionDrainMessage() with SuppressionDrainMessage object=" );
            message.append ( suppression_drain_message.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    public boolean sendSuppressionMessage ( SuppressionMessage suppression_message ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.sendSuppressionMessage ( suppression_message ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::sendSuppressionMessage() with SuppressionMessage object=" );
            message.append ( suppression_message.toString() );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public boolean acknowledgeOpenviewEvent ( String message_id, String annotation_text ) throws BackEndFailure
    {
        SessionFacadeEjbRemote sfer = getRemote();

        try 
        {
            return ( sfer.acknowledgeOpenviewEvent ( message_id, annotation_text ) );
        } 
        catch( RemoteException exception ) 
        {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::acknowledgeOpenviewEvent() with Openview EventID=" );
            message.append ( message_id );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }


    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////

    /**
     * Return remote interface to WebLogic
     * 
     * @return remote interface to WebLogic
     * @throws BackEndFailure if RMI failure
     */
    private SessionFacadeEjbRemote getRemote() throws BackEndFailure {
        try {
            SessionFacadeEjbHomeRemote sfehr = SessionFacadeEjbUtil.getHome(RmiProperties.getJndiHashtable());
            return(sfehr.create());
        } catch(Exception exception) {
            StringBuilder message = new StringBuilder ( BackEndFailure.RMI_FAILURE );
            message.append ( "::getRemote() - Unable to get remote interface to application" );

            _log.error(message.toString(), exception);

            throw new BackEndFailure( message.toString() );
        }
    }
    
    /**
     * Define logger
     */
    private Logger _log = Logger.getLogger(BackEndFacade.class);
}
