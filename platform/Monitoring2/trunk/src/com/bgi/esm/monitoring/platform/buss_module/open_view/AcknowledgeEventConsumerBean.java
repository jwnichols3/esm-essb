package com.bgi.esm.monitoring.platform.buss_module.open_view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bglobal.gss.peregrine.persistence.AbstractFinder;
import com.bglobal.gss.peregrine.persistence.Configuration;
import com.bglobal.gss.peregrine.persistence.ICreateIncidentRequest;
import com.bglobal.gss.peregrine.service.mediator.IncidentServiceMediator;
import com.bglobal.gxml.ticket.UpdateIncidentRequest;
import com.bglobal.gxml.ticket.UpdateIncidentResponse;


/**
 * @ejb.bean 
 *   name="OpenviewAcknowledgeEventConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="dispatcher queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.openview_acknowledge"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class AcknowledgeEventConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-Openview-AcknowledgeEvent.out";
    /**
     * ctor
     */
    public AcknowledgeEventConsumerBean() {
        super(BussModule.OPENVIEW_ACKNOWLEDGE);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("AcknowledgeEventConsumerBean ctor failure");
            _log = Logger.getLogger ( AcknowledgeEventConsumerBean.class );
            _log.error ( "AcknowledgeEventConsumerBean ctor failure", exception );
        }
        
        _log.debug("openview_acknowledge consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     * 
     * @param arg fresh message
     */
    public void onMessage(Message arg) {
        long message_timestamp = System.currentTimeMillis();

        logEntryMessage  ( Long.toString ( message_timestamp ) );

        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::onMessage() - fresh message noted" );
            _log.debug ( message.toString() );
        }

        List unfinishedResponders = _orm.findAllResponderByServiceCenterTicketNumber ( "IM-Default" );

        for ( int counter = 0; counter < unfinishedResponders.size(); counter++ )
        {
            Responder responder = (Responder) unfinishedResponders.get ( counter );

            if ( _log.isDebugEnabled() )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::onMessage() - Started processing Responder ( RowID=" );
                    message.append ( responder.getRowKey() );
                    message.append ( ", TicketNumber=" );
                    message.append ( responder.getServiceCenterTicketNumber() );
                    message.append ( " )" );
                _log.debug ( message.toString() ); 
            }

            if ( null != responder.getSCAsyncRequestReceipt() )
            {
                String ticket_number = findTicketNumberForReceipt ( responder.getSCAsyncRequestReceipt() );

                if ( null != ticket_number )
                {
                    responder.setServiceCenterTicketNumber ( ticket_number );
                    logMessage( responder.getMessageId(), ticket_number, BussModule.SC_GATEWAY_CREATE );
                    _orm.addOrUpdateRow ( responder );
                    annotateOpenviewMessage ( responder.getMessageId(), "[ESM-EnterpriseEventBus::AcknowledgeEvent] Created Service Center Ticket #" + ticket_number );

                    EventsByGroup event = _orm.selectEventsByGroupByMessageId ( responder.getMessageId() );

                    StringBuilder update_message = new StringBuilder();
                    update_message.append ( "This ticket is for hostname '" );
                    update_message.append ( event.getSourceNode() );
                    update_message.append ( "'" );

                    IncidentServiceMediator svc = new IncidentServiceMediator();
                    UpdateIncidentRequest _soaReq = new UpdateIncidentRequest();
                    _soaReq.setIncidentid  ( ticket_number );
                    _soaReq.setSynchronous ( false );
                    _soaReq.setJournalupdates ( update_message.toString() );
                    UpdateIncidentResponse updateTikResponse = svc.updateIncident(_soaReq);
                }
            }

            if ( _log.isDebugEnabled() )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::onMessage() - Finished processing Responder ( RowID=" );
                    message.append ( responder.getRowKey() );
                    message.append ( ", TicketNber=" );
                    message.append ( responder.getServiceCenterTicketNumber() );
                    message.append ( " )" );
                _log.debug ( message.toString() ); 
            }

        }

        logExitMessage  ( Long.toString ( message_timestamp ) );
    }

    private String findTicketNumberForReceipt ( String receipt_id )
    {
        AbstractFinder receiptFinder = Configuration.getSingleton().getFinder();
        ICreateIncidentRequest createIncidentRequest = receiptFinder.selectCreateIncidentRequest ( receipt_id );
        if (( null != createIncidentRequest ) && ( null != createIncidentRequest.getIncidentID() ))
        {
            return createIncidentRequest.getIncidentID();
        }
        else
        {
            return null;
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
