package com.bgi.esm.monitoring.platform.buss_module.sc_buffer;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.MessageDrivenBean;
import javax.ejb.ObjectNotFoundException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import com.bglobal.gss.peregrine.persistence.AbstractFinder;
import com.bglobal.gss.peregrine.persistence.Configuration;
import com.bglobal.gss.peregrine.persistence.ICreateIncidentRequest;

/**
 * Service Center Buffer
 * 
 * @ejb.bean 
 *   name="ScBufferConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="dispatcher queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.sc_buffer"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ScBufferConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-ScBuffer.out";
    final private static String logExternalFileName = "c:\\test\\JMS-ScBuffer.out";
    final private static SimpleDateFormat sdf       = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    // The lifetime of a buffered ticket request in milliseconds
    final private static long ticket_lifetime       = 2*24*60*60*1000; // days * hours * minutes * seconds * milliseconds

    /**
     * ctor
     */
    public ScBufferConsumerBean() {
        super(BussModule.SC_BUFFER);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("ScBufferConsumerBean ctor failure");
            _log = Logger.getLogger ( ScBufferConsumerBean.class );
        }
        
        _log.debug("sc_buffer consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage ( Message arg )
    {
        long message_timestamp = System.currentTimeMillis();

        ObjectMessage om = (ObjectMessage) arg;

        try
        {
            logEntryMessage ( Long.toString ( message_timestamp ) );

            Serializable object = om.getObject();

            if ( null == object )
            {
                _log.error ( "Skipping null message" );
            }
            else
            {
                //  Check the responder records for tickets numbers that are "IM-Default"
                LogToFile ( externalLogFileName, "New message detected: " + message_timestamp );

                List unprocessedResponders = _orm.findAllResponderByServiceCenterTicketNumber ( "IM-Default" );
                _log.info ( "-- Attempting to retrieve ticket numbers for num responders: " + unprocessedResponders.size() );

                AbstractFinder receiptFinder = Configuration.getSingleton().getFinder();

                for ( int counter = 0; counter < unprocessedResponders.size(); counter++ )
                {
                    Responder responder = (Responder) unprocessedResponders.get ( counter );
                    String receipt_id = responder.getSCAsyncRequestReceipt();
                    if ( null == receipt_id ) continue;

                    ICreateIncidentRequest createIncidentRequest = receiptFinder.selectCreateIncidentRequest ( receipt_id );
                    if ( null != createIncidentRequest )
                    {
                        responder.setServiceCenterTicketNumber ( createIncidentRequest.getIncidentID() );
                        _orm.addOrUpdateRow ( responder );

                        annotateOpenviewMessage ( responder.getMessageId(), "[ESM-EnterpriseEventBus::ServiceCenterBuffer] Created Service Center Ticket #" + createIncidentRequest.getIncidentID() );

                        if ( _log.isInfoEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                                message.append ( getClass().getName() );
                                message.append ( "::onMessage() - updated Responder ( RowKey=" );
                                message.append ( responder.getRowKey() );
                                message.append ( " ) to SCTicketNumber=" );
                                message.append ( responder.getServiceCenterTicketNumber() );
                            _log.info ( message.toString() );
                        }
                    }
                }
            }
        }
        catch ( Exception exception )
        {
            LogToFile ( externalLogFileName, "SC Buffer Consumer Bean - exception detected: " + exception.getMessage() );
            _log.error ( "SC Buffer Consumer Bean - exception detected: " + exception.getMessage(), exception );
        }
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage2 ( Message arg )
    {
        long message_timestamp = System.currentTimeMillis();

        ObjectMessage om = (ObjectMessage) arg;

        try
        {
            logEntryMessage ( Long.toString ( message_timestamp ) );

            Serializable object = om.getObject();

            if ( null == object )
            {
                _log.error ( "Skipping null message" );
            }
            //else if ( object instanceof DispatcherMessage ) 
            else
            {
                //  Check the Service Center Buffer
                LogToFile ( externalLogFileName, "New message detected: " + message_timestamp );
                List list = _orm.getAllServiceCenterTicketsInBuffer();
                if ( false == doesServiceCenterBufferHaveTickets ( list ) ) { return; }
                
                //long num_sc_tickets = _orm.getNumServiceCenterTicketsInBuffer();
                //if ( false == doesServiceCenterBufferHaveTickets ( num_sc_tickets ) ) { return; }
                //List list = null;

                //  The Service Center requests will returned in FIFO fashion.  Retrieve the first one for processing.
                ServiceCenter sc             = (ServiceCenter) list.get ( 0 );
                String message_key           = sc.getMessageKey();
                String scTicketNumber        = null;
                ServiceCenterTicket scTicket = null;
                String ticket_owner          = "VPO";
                Responder responder          = null;
              
                //  Look for the next available unlocked Responder object
                for ( int counter = 0; counter < list.size(); counter++ )
                { 
                    sc          = (ServiceCenter) list.get ( counter );
                    message_key = sc.getMessageKey();
                    responder   = findResponderByMessageKey ( message_key, sc );   // Returns most recent responder record
                                                                                   // for the given message key
                    if ( null != responder )
                    {
                        break;
                    } 
                }

                //  A valid responder object should have been created by the Dispatcher module.
                //  If a valid responder does not exist, then we do not need to process any further.
                //  A null responder object will also mean that no responder object is currently availble for processing.
                if ( null == responder ) 
                {
                    LogToFile ( externalLogFileName, "Could not find responder object for message key: " + message_key );

                    return; 
                } 

                //  Now we have the Service Center request and the corresponding responder object.  We can now check
                //  to see if another dispatcher process is already processing a request for the same message key.  If
                //  another request exists, then we move this request to the back of the line of processing.
                /*
                if ( true == responder.isLocked() )
                {
                    LogToFile ( externalLogFileName, "Responder object for message_key=" + message_key + " is locked" );

                    //  Move the Service Center buffer request to the back of the line
                    sc.setTimestamp ( Calendar.getInstance(TimeZone.getTimeZone("GMT")) );
                    _orm.addOrUpdateServiceCenterBuffer ( sc );
                }
                else
                //*/
                if ( false )
                {
                    //  Otherwise, we need to lock this responder before we start processing
                    responder = lockResponder ( responder );

                    //  Not we retrieve the Service Center ticket to see if it is closed.  If the ticket is closed,
                    //  then we open a new ticket.  Otherwise, we update
                    scTicketNumber = responder.getServiceCenterTicketNumber();

                    if ( false == isValidServiceCenterTicketNumber ( scTicketNumber, responder ) )
                    {
                        LogToFile ( externalLogFileName, "Invalid SC Ticket Number; Creating a new ticket for message_key=" + message_key );
                        //  The last ticket created was an invalid ticket.  Therefore, we can safely create a new ticket
                        responder = createTicket ( sc, responder );
                    }
                    else
                    {
                        LogToFile ( externalLogFileName, "Valid SC Ticket Number - ( message_key=" + message_key + ", ticket_number=" + scTicketNumber + " )" );
                        //  A previous ticket exists.  We can now retrieve it and check the status
                        try
                        {
                            scTicket       = ServiceCenterTicket.retrieveTicket ( scTicketNumber );
                        }
                        catch ( Exception exception )
                        {
                            LogToFile ( externalLogFileName, "Could not retrieve ticket_number=" + scTicketNumber );

                            scTicket = null;
                        }

                        //  If the ticket is closed, then we can safely create a new ticket
                        if (( null == scTicket) || ( false == scTicket.isTicketOpen() ))
                        {
                            LogToFile ( externalLogFileName, "No active ticket for message_key=" + message_key + "... creating new ticket" );

                            responder = createTicket ( sc, responder );

                            LogToFile ( externalLogFileName, "Previous ticket was closed... new ticket created: " + responder.getServiceCenterTicketNumber() );
                        }
                        else
                        {
                            LogToFile ( externalLogFileName, "Ticket " + responder.getServiceCenterTicketNumber() + " has status=" + scTicket.getStatus() );
                            //  Check to see how many times this ticket has been updated.  If it is too many times, then
                            //  we need to close down the existing ticket and open up a new one.
                            if ( responder.getDuplicateCount() >= 10 )
                            {
                                LogToFile ( externalLogFileName, "Closing down previous ticket..." + responder.getServiceCenterTicketNumber() );
                                //  Create a new responder and new ticket
                                Responder new_responder = new Responder();
                                new_responder.setAlarmpointMessage            ( responder.getAlarmpointMessage()            );
                                new_responder.setAlarmpointEventID            ( responder.getAlarmpointEventID()            );
                                new_responder.setMessageId                    ( responder.getMessageId()                    );
                                new_responder.setServiceCenterTicketOwner     ( responder.getServiceCenterTicketOwner()     );
                                new_responder.setServiceCenterTicketMessage   ( responder.getServiceCenterTicketMessage()   );
                                new_responder.setAlarmpointNotificationTarget ( responder.getAlarmpointNotificationTarget() );
                                new_responder.setMessageKey                   ( responder.getMessageKey()                   );
                                new_responder.setDuplicateCount ( 0 );

                                LogToFile ( externalLogFileName, "Debug 01" );
                                /*
                                Responder saved_responder = _orm.addOrUpdateRow ( new_responder );
                                saved_responder = lockResponder   ( saved_responder );
                                    saved_responder = createTicket ( sc, saved_responder );
                                saved_responder = unlockResponder ( saved_responder );
                                //*/
                                try
                                {
                                    new_responder = _orm.addOrUpdateRow ( new_responder );
                                    new_responder = lockResponder   ( new_responder );
                                        new_responder = createTicket ( sc, new_responder );
                                    new_responder = unlockResponder ( new_responder );
                                }
                                catch ( Exception exception )
                                {
                                    StringBuilder message = new StringBuilder();
                                        message.append ( "Could not find NewResponder ( RowKey=" );
                                        message.append ( new_responder.getRowKey() );
                                        message.append ( " ), and OldResponder ( RowKey=" );
                                        message.append ( responder.getRowKey() );
                                        message.append ( " )" );
                                    _log.error ( message.toString(), exception );
                                }
                                
                                //  Close down the current ticket
                                StringBuilder close_message = new StringBuilder ( "Too many updates -- closing this ticket.  Please refer to ticket #" );
                                //close_message.append ( saved_responder.getServiceCenterTicketNumber() );
                                close_message.append ( new_responder.getServiceCenterTicketNumber() );

                                //ServiceCenterTicket.updateExistingTicket ( scTicketNumber, "ESM Monitoring", close_message.toString() );

                                //  Close down the ticket
                                ServiceCenterTicket.closeExistingTicket ( scTicketNumber, close_message.toString() );
                            }
                            else
                            {
                                LogToFile ( externalLogFileName, "Updating ticket: " + responder.getServiceCenterTicketNumber() );
                                responder.incrementDuplicateCount();

                                // Simply update the current ticket
                                responder = updateTicket ( sc, responder );
                            }
                        }
                    }

                    //  Send the message to Openview acknowledging the event in the event browser
                    LogToFile ( externalLogFileName, "Acknowledging Openview Message ID: " + sc.getMessageId() );
                    acknowledgeOpenviewMessage ( sc.getMessageId(), "[ESM-EnterpriseEventBus] Notification and ticketing has been completed." );
                    
                    //  Now we unlock the responder to signify that it is now open for further processing
                    responder = unlockResponder ( responder );
                }
            }
        }
        catch ( Exception exception )
        {
            LogToFile ( externalLogFileName, "SC Buffer Consumer Bean - exception detected: " + exception.getMessage() );
            _log.error ( "SC Buffer Consumer Bean - exception detected: " + exception.getMessage(), exception );
        }
    }

    public void onMessage3 ( Message arg ) 
    {
        long message_timestamp = System.currentTimeMillis();

        ObjectMessage om = (ObjectMessage) arg;

        try
        {
            logEntryMessage ( Long.toString ( message_timestamp ) );

            Serializable object = om.getObject();

            if ( null == object )
            {
                _log.error ( "Skipping null message" );
            }
            //else if ( object instanceof DispatcherMessage ) 
            else
            {
                String log_timestamp = "Timestamp (" + message_timestamp + ") - ";
                LogToFile ( externalLogFileName, log_timestamp + "New message detected: " + message_timestamp );

                List list = _orm.getAllServiceCenterTicketsInBuffer();
                //DispatcherMessage dm = (DispatcherMessage) object;
                //TicketMessage ticketMessage = dm.getTicketMessage();


                //  If there are not tickets in the Service Center buffer, then we don't have to do anything
                if ( list.size() == 0 )
                {
                    LogToFile ( externalLogFileName, log_timestamp + "No tickets in the Service Center buffer." );
                    _log.info ( "No tickets in the Service Center buffer." );

                    return;
                }

                //  Kick out a warning message if the number of tickets in the Service Center buffer exceeds a
                //  certain level
                if ( list.size() > 10 )
                {
                    LogToFile ( externalLogFileName, log_timestamp + "Large number of tickets in the Service Center Buffer detected: " + list.size() );
                    _log.warn ( "Large number of tickets in the Service Center Buffer detected: " + list.size() );
                }
                else
                {
                    LogToFile ( externalLogFileName, log_timestamp + "Number of tickets detected in the Service Center Buffer: " + list.size() );
                }

                //  The Service Center requests will returned in FIFO fashion.  Retrieve the first one for processing.
                ServiceCenter sc             = (ServiceCenter) list.get ( 0 );
                String message_key           = sc.getMessageKey();
                String scTicketNumber        = null;
                ServiceCenterTicket scTicket = null;
                String ticket_owner          = "VPO";

                //  Look for the most recent Responder object.  If it
                Responder responder = null;
              
                //  If the message key is valid, then process
                if (( null != message_key ) && ( message_key.length() > 3 ))
                {
                    responder = _orm.selectResponderByMessageKey ( message_key );
                }
                //  Otherwise, create the default message key
                else
                {
                    _log.error ( "Message found with invalid message key in Service Center Buffer ( RowKey=" + sc.getRowKey() + " ).  Deleting..." );

                    LogToFile ( externalLogFileName, log_timestamp + "Message found with invalid message key in Service Center Buffer ( RowKey=" + sc.getRowKey() + " ).  Deleting..." );

                    _orm.deleteServiceCenterBuffer ( sc );

                    return;
                }

                if ( null == responder )
                {
                    //  This is the first time that this message key has been encountered.  Maybe this is a new monitor?
                    StringBuilder message = new StringBuilder(log_timestamp);
                        message.append ( "ScBufferConsumerBean::onMessage() - new message key detected: " );
                        message.append ( message_key );
                    _log.warn ( message.toString() );
                    LogToFile ( externalLogFileName, message.toString() );

                    //  Save the responder object
                    responder = new Responder();
                    responder.setMessageId  ( sc.getMessageId() );
                    responder.setServiceCenterTicketNumber ( ServiceCenter.UNKNOWN_TICKET_NUM ); 
                    //responder.setAlarmpointNotificationTarget ( null );
                    responder.setAlarmpointMessage ( sc.getTicketMessage() );
                    responder.setMessageKey ( message_key );
                    responder.lock();

                    LogToFile ( externalLogFileName, log_timestamp + "Debug 02" );
                    Responder r = _orm.addOrUpdateRow ( responder );
                    if ( null == r )
                    {
                        message = new StringBuilder();
                        message.append ( "Could not save Responder with message key: " );
                        message.append ( message_key );
                        message.append ( ", next event with this message key will generate a duplicate alert" );

                        _log.warn ( message.toString() );
                        LogToFile ( externalLogFileName, log_timestamp + message.toString() );
                    }

                    //  Create the ticket
                    r = createTicket ( sc, r );

                    r.unlock();
                    LogToFile ( externalLogFileName, log_timestamp + "Debug 03" );
                    r = _orm.addOrUpdateRow ( r );
                }
                else if ( false == responder.isLocked() )
                {
                    //  Lock the row
                    responder.lock();
                    LogToFile ( externalLogFileName, log_timestamp + "Debug 04" );
                    responder = _orm.addOrUpdateRow ( responder );

                    LogToFile ( externalLogFileName, log_timestamp + "Found existing responder with RowKey=" + responder.getRowKey() );
                    responder.setServiceCenterTicketOwner   ( ticket_owner   );
                    responder.setServiceCenterTicketMessage ( sc.getTicketMessage() );
                    //  Retrieve the corresponding ticket from the Responder object and look for the ticket in Archway
                    scTicketNumber = responder.getServiceCenterTicketNumber();

                    //  A responder entry exists, but there is no Service Center ticket number.  This might be the result of
                    //  a reconciliation attempt by the EEB, as Alarmpoint may have already created the record.  In this case,
                    //  create the Service Center ticket and update the Responder object accordingly
                    if (( null == scTicketNumber ) || ( scTicketNumber.equals ( ServiceCenter.UNKNOWN_TICKET_NUM ) ))
                    {
                        LogToFile ( externalLogFileName, log_timestamp + "Responder ( RowKey=" + responder.getRowKey() + " ) does not have a ticket number yet" );
                        responder = createTicket ( sc, responder );
                    }
                    else if ( scTicketNumber.indexOf ( "test-" ) == 0 )
                    {
                        _log.warn ( "Found test ticket... deleting RowKey=" + responder.getRowKey() );

                        _orm.deleteResponder ( responder );
                        _orm.deleteServiceCenterBuffer ( sc );
                    }
                    else 
                    {
                        LogToFile ( externalLogFileName, log_timestamp + "Responder ( RowKey=" + responder.getRowKey() + " ) already has an existing ticket number: " + scTicketNumber );
                        scTicket       = ServiceCenterTicket.retrieveTicket ( scTicketNumber );
                        //scTicket = new ServiceCenterTicket ( scTicketNumber );

                        // Unable to find the ticket when provided a ticket number.  An error has occured.
                        if ( null == scTicket )
                        {
                            StringBuilder message = new StringBuilder();
                                message.append ( "Could not find Service Center Ticket #" );
                                message.append ( scTicketNumber );
                                message.append ( "... attempting to re-create ticket" );
                            _log.error ( message.toString() );
                            LogToFile ( externalLogFileName, log_timestamp + message.toString() );

                            responder = createTicket ( sc, responder );
                        }
                        //  By now, we know if the ticket exists or not.  If the ticket is open, then we update its contents.
                        //  If the ticket
                        else if ( scTicket.isTicketOpen() )
                        {
                            long num_duplicates = responder.incrementDuplicateCount();
                            LogToFile ( externalLogFileName, log_timestamp + "Responder ( RowKey=" + responder.getRowKey() + " ) has an existing open ticket... updating with " + num_duplicates + " duplicates" );

                            if ( num_duplicates <= 10 )
                            {
                                responder.incrementDuplicateCount();
                                responder = updateTicket ( sc, responder );
                            }
                            else
                            {
                                if ( _log.isInfoEnabled() )
                                {
                                    StringBuilder message = new StringBuilder();
                                        message.append ( "Ticket #" );
                                        message.append ( scTicket );
                                        message.append ( " will be closed because it already has 10 updates. " );
                                    _log.info ( message.toString() );
                                }

                                //  Open a new ticket for the same message key
                                Responder new_responder = new Responder();
                                new_responder.setMessageId  ( sc.getMessageId() );
                                new_responder.setServiceCenterTicketNumber ( ServiceCenter.UNKNOWN_TICKET_NUM ); 
                                //responder.setAlarmpointNotificationTarget ( null );
                                new_responder.setAlarmpointMessage ( sc.getTicketMessage() );
                                new_responder.setMessageKey ( message_key );
                                new_responder.lock();

                                LogToFile ( externalLogFileName, log_timestamp + "Debug 05" );
                                Responder insert_new_responder = _orm.addOrUpdateRow ( new_responder );
                                if ( null == insert_new_responder )
                                {
                                    StringBuilder message = new StringBuilder();
                                        message.append ( "Could not save Responder with message key: " );
                                        message.append ( message_key );
                                        message.append ( ", next event with this message key will generate a duplicate alert" );
                                    _log.warn ( message.toString() );
                                    LogToFile ( externalLogFileName, log_timestamp + message.toString() );
                                }

                                //  Create the ticket
                                insert_new_responder = createTicket ( sc, insert_new_responder );
                                LogToFile ( externalLogFileName, log_timestamp + "New responder has row ( RowKey=" + insert_new_responder.getRowKey() + ", TicketNumber=" + insert_new_responder.getServiceCenterTicketNumber() );
            
                                //  Update responder to point to the new ticket and unlock
                                insert_new_responder.unlock();
                                LogToFile ( externalLogFileName, log_timestamp + "Debug 06" );
                                insert_new_responder = _orm.addOrUpdateRow ( insert_new_responder );
                                
                                //  Update the current ticket with reference to new ticket
                                StringBuilder close_message = new StringBuilder ( "Too many updates -- closing this ticket.  Please refer to ticket #" );
                                close_message.append ( insert_new_responder.getServiceCenterTicketNumber() );

                                //ServiceCenterTicket.updateExistingTicket ( scTicketNumber, "ESM Monitoring", close_message.toString() );

                                //  Close down the ticket
                                ServiceCenterTicket.closeExistingTicket ( scTicketNumber, close_message.toString() );
                            }
                        }
                        else
                        {
                            LogToFile ( externalLogFileName, log_timestamp + "Responder ( RowKey=" + responder.getRowKey() + " ) does not have an open ticket... creating new ticket" );

                            //  Create a new responder so the old record is preserved.
                            Responder responder2 = new Responder();
		                    responder2.setServiceCenterTicketNumber    ( responder.getServiceCenterTicketNumber()    );
		                    responder2.setServiceCenterTicketOwner     ( responder.getServiceCenterTicketOwner()     );
                            responder2.setServiceCenterTicketMessage   ( responder.getServiceCenterTicketMessage()   );
                            responder2.setAlarmpointEventID            ( responder.getAlarmpointEventID()            );
                            responder2.setAlarmpointNotificationTarget ( responder.getAlarmpointNotificationTarget() );
                            responder2.setAlarmpointMessage            ( responder.getAlarmpointMessage()            );
                            responder2.setDuplicateCount               ( responder.getDuplicateCount()               );
                            responder2.setMessageKey                   ( responder.getMessageKey()                   );
                            responder2.lock();

                            LogToFile ( externalLogFileName, log_timestamp + "Debug 07" );
                            responder2 = _orm.addOrUpdateRow ( responder2 );

                            responder2 = createTicket ( sc, responder2 );

                            responder2.unlock();
                            LogToFile ( externalLogFileName, log_timestamp + "Debug 08" );
                            responder2 = _orm.addOrUpdateRow ( responder2 );
                        }
                    }
                }
                else
                {
                    sc.setTimestamp ( Calendar.getInstance(TimeZone.getTimeZone("GMT")) );

                    _orm.addOrUpdateServiceCenterBuffer ( sc );
                }
            }

            logExitMessage  ( Long.toString ( message_timestamp ) );
        }
        catch ( Exception exception )
        {
            LogToFile ( externalLogFileName, "SC Buffer Consumer Bean - exception detected: " + exception.getMessage() );
            _log.error ( "SC Buffer Consumer Bean - exception detected: " + exception.getMessage(), exception );
        }
    }

    private void saveServiceCenterTicket ( ServiceCenter scParameters )
    {
        try
        { 
            ServiceCenter updated = _orm.addOrUpdateServiceCenter ( scParameters );

            if ( null == updated )
            {
                LogToFile ( logExternalFileName, "Unable to save information to create the Service Center ticket at a later time" );
            }
            else
            {
                LogToFile ( logExternalFileName, "Successfully saved information to create the Service Center at a later time" );
            }
        }
        catch ( Exception exception )
        {
            _log.error ( "Exception encountered when trying to save information to create Service Center Ticket at a later time:\n" + exception.getMessage(), exception );
        }

    }

    private Responder createTicket ( ServiceCenter sc, Responder responder )
    {
        if ( true == _orm.getEebPropertyServiceCenterActive() )
        {
            ServiceCenterTicket serviceCenterTicket = ServiceCenterTicket.createNewTicket 
                ( "VPO", null, sc.getTicketMessage(), sc.getTicketCategory(), sc.getTicketSubCategory(), sc.getTicketProblem(), sc.getTicketProduct() );

            if ( null == serviceCenterTicket )
            {
                //  Log the request in the recovery buffer
                //  Save the ticket in the "service_center" table, which will be read by the SC_Drain bus module
                //  in the future to re-create the ticket when Service Center is back up
                saveServiceCenterTicket ( sc );

                LogToFile ( logExternalFileName, "Logging Service Center Ticket create request into queue" );

                responder.setServiceCenterTicketNumber ( ServiceCenter.UNKNOWN_TICKET_NUM ); // This will be set by the Service Center drain
            }
            else
            {
                LogToFile ( logExternalFileName, "Created SC Ticket #" + serviceCenterTicket.getTicketNumber() );
                responder.setServiceCenterTicketNumber ( serviceCenterTicket.getTicketNumber() );
            }
        }
        else
        {
            responder.setServiceCenterTicketNumber ( ServiceCenter.INACTIVE_TICKET_NUM );
        }
        
        logMessage ( sc.getMessageId(), "create-ticket: " + responder.getServiceCenterTicketNumber() );

        //  Save the current processing status
        LogToFile ( externalLogFileName, "Debug 09" );
        Responder return_value = _orm.addOrUpdateRow ( responder );

        //  Delete the buffered request
        _orm.deleteServiceCenterBuffer ( sc );

        return return_value;
    }

    private Responder updateTicket ( ServiceCenter sc, Responder responder )
    {
        String scTicketNumber    = responder.getServiceCenterTicketNumber();
        String ticket_updated_by = "ESM Monitoring";
        long suppressCount       = responder.getDuplicateCount();

        StringBuilder message    = new StringBuilder();
        message.append ( "HelpME Ticket #" );
        message.append ( scTicketNumber );
        message.append ( " - " );
        message.append ( suppressCount );
        message.append ( " duplicate events detected" );

        if ( true == _orm.getEebPropertyAlarmpointActive() )
        {
            try
            {
                ServiceCenterTicket.updateExistingTicket ( scTicketNumber, ticket_updated_by,  message.toString() );
            }
            catch ( IOException exception )
            {
                LogToFile ( logExternalFileName, "Logging Service Center Ticket update request into queue" );
                saveServiceCenterTicket ( sc );
            }
        }
        else
        {
        }

        logMessage ( sc.getMessageId(), "update-ticket: " + message.toString() );

        //  Request processed
        _orm.deleteServiceCenterBuffer ( sc );

        LogToFile ( externalLogFileName, "Debug 10 - Duplicate count: " + responder.getDuplicateCount() );
        return _orm.addOrUpdateRow ( responder );
    }

    private Responder lockResponder ( Responder responder )
    {
        responder.lock();
        LogToFile ( externalLogFileName, "Debug 11" );
        return _orm.addOrUpdateRow ( responder );
    }

    private Responder unlockResponder ( Responder responder )
    {
        responder.unlock();
        LogToFile ( externalLogFileName, "Debug 12" );
        return _orm.addOrUpdateRow ( responder );
    }

    public boolean doesServiceCenterBufferHaveTickets ( List list )
    {
        return doesServiceCenterBufferHaveTickets ( list.size() );
    }

    public boolean doesServiceCenterBufferHaveTickets ( long num_records )
    {
        //  If there are not tickets in the Service Center buffer, then we don't have to do anything
        if ( num_records == 0 )
        {
            LogToFile ( externalLogFileName, "No tickets in the Service Center buffer." );
            _log.info ( "No tickets in the Service Center buffer." );

            return false;
        }

        //  Kick out a warning message if the number of tickets in the Service Center buffer exceeds a
        //  certain level
        if ( num_records > 10 )
        {
            LogToFile ( externalLogFileName, "Large number of tickets in the Service Center Buffer detected: " + num_records );
            _log.warn ( "Large number of tickets in the Service Center Buffer detected: " + num_records );
        }
        else
        {
            LogToFile ( externalLogFileName, "Number of tickets detected in the Service Center Buffer: " + num_records );
        }

        return true;
    }

    public Responder findResponderByMessageKey ( String message_key, ServiceCenter sc )
    {
        Responder responder = null;

        //  If the message key is valid, then process
        if (( null != message_key ) && ( message_key.length() > 3 ))
        {
            responder = _orm.selectResponderByMessageKey ( message_key );

            if (( null != responder ) && ( responder.isLocked() ))
            {
                return null;
            }

            //  If no responder is found, create!
            if ( null == responder )
            {
                LogToFile ( externalLogFileName, "Creating new responder object for message_key=" + message_key );
                responder = new Responder();
                responder.setAlarmpointMessage            ( responder.getAlarmpointMessage()            );
                responder.setAlarmpointEventID            ( responder.getAlarmpointEventID()            );
                responder.setMessageId                    ( sc.getMessageId()          );
                responder.setServiceCenterTicketOwner     ( sc.getTicketAssigneeName() );
                responder.setServiceCenterTicketMessage   ( sc.getTicketMessage()      );
                responder.setMessageKey                   ( message_key                );
                responder.setGroup                        ( sc.getTicketAssignment()   );
                responder.setDuplicateCount ( 0 );
            }
            else
            {
                LogToFile ( externalLogFileName, "Existing responder found for message_key=" + message_key + ", RowID=" + responder.getRowKey() );
            }

            LogToFile ( externalLogFileName, "Debug 13a - " + responder.getRowKey() );
            responder.lock();
            responder = _orm.addOrUpdateRow ( responder );
            LogToFile ( externalLogFileName, "Debug 13b - " + responder.getRowKey() );
        }
        //  Otherwise, create delete his Service Center Buffer request
        else
        {
            _log.error ( "Message found with invalid message key in Service Center Buffer ( RowKey=" + sc.getRowKey() + " ).  Deleting..." );

            LogToFile ( externalLogFileName, "Message found with invalid message key in Service Center Buffer ( RowKey=" + sc.getRowKey() + " ).  Deleting..." );

            _orm.deleteServiceCenterBuffer ( sc );

            responder = null;
        }

        return responder;
    }

    public boolean isValidServiceCenterTicketNumber ( String scTicketNumber, Responder responder )
    {
        if (( null == scTicketNumber ) || ( scTicketNumber.equals ( ServiceCenter.UNKNOWN_TICKET_NUM ) ) || ( scTicketNumber.equals ( "IM-Default" )))
        {
            LogToFile ( externalLogFileName, "Responder ( RowKey=" + responder.getRowKey() + " ) does not have a ticket number yet" );

            return false;
        }
        else if ( scTicketNumber.indexOf ( "test-" ) == 0 )
        {
            _log.warn ( "Found test ticket... deleting RowKey=" + responder.getRowKey() );

            //_orm.deleteResponder ( responder );
            //_orm.deleteServiceCenterBuffer ( sc );

            return false;
        }

        return true;
    }

    /**
     * Handle to ORM dispatcher
     */
    private final OrmFacade _orm = new OrmFacade();
    
    /**
     * Define logger
     */
    private Logger _log;
}

