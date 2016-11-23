package com.bgi.esm.monitoring.platform.buss_module.sc_drain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;

/**
 * Service Center Drain
 * 
 * @ejb.bean 
 *   name="ScDrainConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="dispatcher queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.sc_drain"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ScDrainConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-ScDrain.out";
    final private static SimpleDateFormat sdf       = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    // The lifetime of a buffered ticket request in milliseconds
    final private static long ticket_lifetime       = 2*24*60*60*1000; // days * hours * minutes * seconds * milliseconds

    /**
     * ctor
     */
    public ScDrainConsumerBean() {
        super(BussModule.SC_DRAIN);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("ScDrainConsumerBean ctor failure");
            _log = Logger.getLogger ( ScDrainConsumerBean.class );
        }
        
        _log.debug("sc_drain consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage(Message arg) 
    {
        long message_timestamp = System.currentTimeMillis();
        try
        {
            _log.debug( "Attempting to re-create Service Center tickets" );

            logEntryMessage ( Long.toString ( message_timestamp ) );

            LogToFile ( externalLogFileName, "Attempting to re-create Service Center tickets" );

            List list              = _orm.getAllServiceCenterTickets();
            Iterator i             = list.iterator();
            long current_timestamp = System.currentTimeMillis();
            int num_records        = 0;
            int num_expired        = 0;

            ////////////////////////////////////////////////////////////////////////////////
            //  Attempting to re-create Service Center tickets
            ////////////////////////////////////////////////////////////////////////////////
            while ( i.hasNext() )
            {
                num_records++;

                ServiceCenter sc = (ServiceCenter) i.next();

                if ( null == sc )
                {
                    continue;
                }

                LogToFile ( externalLogFileName, "Attempting to re-create ticket for Message ID: " + sc.getMessageId() );

                //  Attempt to create the ticket.   If successful, remove from the "service_center" field.
                ServiceCenterTicket sct = null;
                String message_id       = sc.getMessageId();
                Responder responder     = _orm.selectResponderByMessageId ( message_id );

                try
                {
                    //sct        = ServiceCenterTicket.createNewTicket ( sc.getTicketAssignment(), sc.getTicketAssigneeName(), sc.getTicketMessage(), sc.getTicketCategory(), sc.getTicketSubCategory(), sc.getTicketProblem(), sc.getTicketProduct() );
                    sct        = ServiceCenterTicket.createNewTicket ( "VPO", sc.getTicketAssignment(), sc.getTicketMessage(), sc.getTicketCategory(), sc.getTicketSubCategory(), sc.getTicketProblem(), sc.getTicketProduct() );
                    message_id = sc.getMessageId(); 
                }
                catch ( Exception exception )
                {
                    _log.error ( "Error encountered when trying to create Service Center ticket", exception );

                    LogToFile ( externalLogFileName, "Error encountered when trying to create Service Center ticket" );

                    sct = null;
                }
        
                if ( null != sct )
                {
                    //  Ticket creation was successful, so now we log the ticket number in responder
                    if ( null == responder )
                    {
                        StringBuilder message = new StringBuilder ();
                        message.append ( "Message ID#" );
                        message.append ( message_id );
                        message.append ( " did not complete processing for ServiceCenter AND Alarmpoint" );

                        _log.warn ( message.toString() );

                        responder = new Responder();
                        responder.setMessageId ( message_id );

                        logMessage( message_id, Responder.SC_AUDIT_ACTION_FAILURE );

                        continue;
                    }
                    responder.setServiceCenterTicketNumber ( sct.getTicketNumber() );

                    //  Log entry in responder
                    _orm.addOrUpdateRow ( responder );

                    //  Remove request from Service Center recovery queue
                    _orm.deleteServiceCenter ( sc );

                    logMessage( message_id, "SC Ticket: " + sct.getTicketNumber() );
                    logMessage( message_id, Responder.SC_AUDIT_ACTION_SUCCESS );

                    continue;
                }
                else
                {
                    StringBuilder message = new StringBuilder ( "Could not recreate Service Center ticket for message ID: " );
                    message.append ( sc.getMessageId() );
                    message.append ( " - attempted at " );
                    message.append ( sdf.format ( new Date() ) );

                    LogToFile ( externalLogFileName, message.toString() );

                    _log.error ( message.toString() );

                    logMessage( sc.getMessageId(), Responder.SC_AUDIT_ACTION_FAILURE );
                }

                ////////////////////////////////////////////////////////////////////////////////
                //  Remove the Service Center ticket from the ticket buffer if 
                //  it's been alive for a certain amount of time
                ////////////////////////////////////////////////////////////////////////////////
                long creation_time = sc.getTimestamp().getTime().getTime();

                //  If the ticket is too old
                if ( current_timestamp - creation_time > ticket_lifetime )
                {
                    num_expired++;

                    logMessage ( sc.getMessageId(), Responder.SC_AUDIT_ACTION_EXPIRED );

                    _orm.deleteServiceCenter ( sc );

                    logMessage( message_id, "SC Ticket: " + sct.getTicketNumber() );
                    responder.setServiceCenterTicketNumber ( responder.SC_AUDIT_ACTION_EXPIRED );

                    _orm.addOrUpdateRow ( responder );
                }
                else if ( sc.getNumAttempts() > 5 )
                {
                    num_expired++;

                    logMessage ( sc.getMessageId(), Responder.SC_AUDIT_ACTION_DELETED );

                    _orm.deleteServiceCenter ( sc );

                    logMessage( message_id, "SC Ticket: " + sct.getTicketNumber() );
                    responder.setServiceCenterTicketNumber ( responder.SC_AUDIT_ACTION_DELETED );

                    _orm.addOrUpdateRow ( responder );
                }
            }

            LogToFile ( externalLogFileName, "Number of Service Center tickets queued:  " + num_records );
            LogToFile ( externalLogFileName, "Number of Service Center tickets expired: " + num_expired );

            logExitMessage  ( Long.toString ( message_timestamp ) );
        }
        catch ( Exception exception )
        {
            _log.error ( "SC Drain Consumer Bean - exception detected: " + exception.getMessage(), exception );
        }
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
