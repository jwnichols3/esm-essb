package com.bgi.esm.monitoring.platform.buss_module.dispatcher;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Notification;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import com.bgi.esm.monitoring.platform.shared.value.StormMessage;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import com.bglobal.gss.peregrine.configuration.PeregrineConfigurationSingleton;
import com.bglobal.gss.peregrine.persistence.AbstractFinder;
import com.bglobal.gss.peregrine.persistence.Configuration;
import com.bglobal.gss.peregrine.persistence.ICreateIncidentRequest;
import com.bglobal.gss.peregrine.service.AbstractIncidentService;
import com.bglobal.gss.peregrine.service.AbstractInteractionService;
import com.bglobal.gss.peregrine.service.ServiceCenterFacade;
import com.bglobal.gss.peregrine.service.mediator.IncidentServiceMediator;
import com.bglobal.gss.peregrine.service.mediator.TicketServiceMediator;
import com.bglobal.gss.peregrine.webservice.IncidentResponse;
import com.bglobal.gxml.ticket.CreateIncidentRequest;
import com.bglobal.gxml.ticket.CreateIncidentResponse;
import com.bglobal.gxml.ticket.RetrieveTicketRequest;
import com.bglobal.gxml.ticket.RetrieveTicketResponse;
import com.bglobal.gxml.ticket.UpdateIncidentRequest;
import com.bglobal.gxml.ticket.UpdateIncidentResponse;
import com.bglobal.gxml.ticket.TicketInfo;
import com.bglobal.gxml.ticket.TicketResponse;
import com.peregrine.webservice.sample.ServiceUtility;

/**
 * Dispatch an OVO event to Alarm Point/Service Center.
 *
 * @ejb.bean
 *   name="DispatcherConsumerEjb"
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable"
 *   description="dispatcher queue consumer"
 *
 * @weblogic.message-driven destination-jndi-name="replatform.queue.dispatcher"
 *
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class DispatcherConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    private static final String logExternalFileName = "c:\\test\\JMS-Dispatcher.out";

    /**
     * ctor
     */
    public DispatcherConsumerBean() {
        super(BussModule.DISPATCHER);

        try {
            _log = Log4jLoggingHelper.getLog4jServerLogger();
        } catch(Exception exception) {
            System.err.println("DispatcherConsumerBean ctor failure");
            _log = Logger.getLogger ( DispatcherConsumerBean.class );
        }

        _log.debug("dispatcher consumer ctor");
    }

    /**
     * Invoked when messages arrive via queue
     *
     * @param arg fresh message
     */
    public void onMessage(Message arg)
    {
        _log.debug ( "DispatcherConsumerBean::onMessage() - fresh message has arrived" );

        ObjectMessage om = (ObjectMessage) arg;

        try
        {
            // JMS ObjectMessages contain a Java serialized object.  Now we extract that payload.
            Serializable object = om.getObject();

            // I expect we should always have an object, but it's good to check.
            if (object == null)
            {
                _log.error ( "skipping null message" );
            }
            else if ( object instanceof DispatcherMessage ) // This is the typical case.
            {
                DispatcherMessage dm         = (DispatcherMessage) object;
                TicketMessage ticketMessage  = dm.getTicketMessage();
                String message_key           = ticketMessage.getMessageKey();

                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::onMessage() - retrieved ticket message key - " );
                        message.append ( message_key );
                    _log.debug ( message.toString() );
                }

                ServiceCenter sc             = null;
                Alarmpoint ap                = new Alarmpoint();
                Responder responder          = null;
                ServiceCenterTicket scTicket = null;
                String scTicketNumber        = null;
                String ticket_message        = dm.getTicketMessage().getMessageText();
                String message_id            = ticketMessage.getMessageId();
                String source_node           = ticketMessage.getSourceNode();
                EventsByGroup openview_event = _orm.selectEventsByGroupByMessageId ( message_id );

                if ( null == openview_event )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::onMessage() - could not find Openview event ( MessageID=" );
                        message.append ( message_id );
                        message.append ( " ) in the Enterprise Event Bus" );
                    throw new IllegalStateException ( message.toString() );
                }

                //  Put entries into the audit table
                logEntryMessage ( ticketMessage.getMessageId() );
                DataMapRule rule = dm.getDataMapRule();
                if ( null == rule )
                {
                    _log.error ( "Could not find data map rule for Openview Message ID=" + message_id + ", defaulting to ESM" );

                    rule = _orm.selectDataMapRule( "esm" );
                }
                logThrottleMessage ( rule );

                //  Processing the custom message attributes
                //    Taken from vpo_server/alternate-notification.pl
                //        $email_from  =  "alarmpoint\@barclaysglobal.com";
                //        $email_dl    =  "bgiglobalwindowssystems\@barclaysglobal.com";
                //        $email_cc    =  "";
                //
                //      For SEV=17
                //        $email_from    = $email_from;
                //        $email_to      = $email_dl;
                //        $email_cc      = $email_cc;
                //        $email_subject = "$node : $message_text";
                //        $email_body    = "NetIQ severity: ${netiq_sev}\n\n${long_message}\n\n${netiq_specifics}";
                //
                //      For SEV=22
                //        $email_from    = 'alarmpoint@barclaysglobal.com';
                //        $email_to      = 'appmgradmin@barclaysglobal.com';
                //        $email_cc      = '';
                //        $email_subject = "$node : $message_text";
                //        $email_body    = "NetIQ severity: ${netiq_sev}\n\n${long_message}\n\n${netiq_specifics}";

                Map message_attributes = ticketMessage.getAttributes();
                int num_message_attrs  = ( null != message_attributes )? message_attributes.size() : 0;
                if ( num_message_attrs > 0 )
                {
                    if ( _log.isInfoEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "Found custom message attributes:\n" );
                        Iterator attributes = message_attributes.keySet().iterator();

                        while ( attributes.hasNext() )
                        {
                            String attr_name = attributes.next().toString();
                            message.append ( "    " );
                            message.append ( attr_name );
                            message.append ( "=" );
                            message.append ( message_attributes.get( attr_name ).toString() );
                            message.append ( "\n" );
                        }

                        _log.info ( message.toString() );
                        LogToFile ( logExternalFileName, message.toString() );
                    }

                    String netiq_severity = ticketMessage.getAttribute ( "netiq_severity" );
                    String netiq_jobid    = ticketMessage.getAttribute ( "netiq_jobid"    );
                    String netiq_eventid  = ticketMessage.getAttribute ( "netiq_eventid"  );
                    if ( null != netiq_severity )
                    {
                        String netiq_long_message = ticketMessage.getAttribute ( "z_netiq_longmsg" );
                        if ( netiq_severity.equals ( "17" ) )
                        {
                            StringBuilder netiq_message = new StringBuilder();
                            netiq_message.append ( "NetIQ Severity: " );
                            netiq_message.append ( netiq_severity );
                            netiq_message.append ( "\n\n" );
                            if (( null != netiq_long_message ) && ( !netiq_long_message.trim().equals ( "null" )))
                            {
                                netiq_message.append ( netiq_long_message );
                            }
                            netiq_message.append ( "\n\nNetIQ JobID=" );
                            netiq_message.append ( netiq_jobid );
                            netiq_message.append ( "\n\nNetIQ EventID=" );
                            netiq_message.append ( netiq_eventid );

                            ticket_message = netiq_message.toString();
                        }
                        else if ( netiq_severity.equals ( "22" ) )
                        {
                            StringBuilder netiq_message = new StringBuilder();
                            netiq_message.append ( "NetIQ Severity: " );
                            netiq_message.append ( netiq_severity );
                            netiq_message.append ( "\n\n" );
                            if (( null != netiq_long_message ) && ( !netiq_long_message.trim().equals ( "null" )))
                            {
                                netiq_message.append ( netiq_long_message );
                            }
                            netiq_message.append ( "\n\nNetIQ JobID=" );
                            netiq_message.append ( netiq_jobid );
                            netiq_message.append ( "\n\nNetIQ EventID=" );
                            netiq_message.append ( netiq_eventid );

                            ticket_message = netiq_message.toString();
                        }
                    }
                }

                //  Storing the responder
                //Responder searchResponder = searchResponder ( message_id, message_key, rule );
                Responder searchResponder = _orm.selectResponderByMessageKey ( message_key );
                LogToFile ( logExternalFileName, "Creating responder with message key: " + message_key );

                //  Splitting the Alarmpoint alert message into lines
                //  and only sending the first line
                //  April 29, 2009 - requested by Kris Maksymowicz
                String apMessageLines[] = ticket_message.split ( "\n" );

                //  Save the alert in the "alarmpoint" table so that the "AP_Drain" bus module can attempt to re-create it at
                //  a later date when Alarmpoint comes back up
                String alarmpoint_target = rule.getAlarmpointGroup();
                String alarmpoint_device  = ticketMessage.getSourceNode();
                ap.setMessageId         ( message_id );
                ap.setAlarmpointDevice  ( alarmpoint_device );
                ap.setAlarmpointMessage ( apMessageLines[0] );
                ap.setAlarmpointNotificationTarget ( alarmpoint_target  );

                //  If the alert is to go straight to Alarmpoint, then we process on the spot
                if ( rule.getMethod().equals ( "alarmpoint_only" ) )
                {
                    ap.setAlarmpointMessage ( ticket_message );
                    //createAlarmpointAlert ( responder, ap, rule, openview_event.getSeverity() );

                    //  If responder exists, check to make sure that the requisite time has passed before
                    //  creating a new Alarmpoint notification
                    if ( null != searchResponder )
                    {
                        int delay_in_seconds = 3600;  // Default to 3600 seconds, i.e. 1 hr
                        EebProperty property = _orm.selectEebProperty ( "suppress.duplicate.Alarmpoint" );
                        if ( null != property )
                        {
                            try
                            {
                                delay_in_seconds = Integer.parseInt ( property.getPropertyValue() );
                            }
                            catch ( NumberFormatException exception )
                            {
                                _log.error ( "Invalid value for EEBProperty ( Key=suppress.duplicate.Alarmpoint ) - " + property.getPropertyValue(), exception );
                            }
                        }

                        Calendar timestamp = searchResponder.getTimestamp();
                        int difference     = (int) (( System.currentTimeMillis() - timestamp.getTime().getTime() ) / 1000 );

                        //  Haven't waited long enough, update duplicate counter only
                        if ( difference < delay_in_seconds )
                        {
                            int duplicateCount = ((int)searchResponder.getDuplicateCount() ) + 1;
                            searchResponder.setDuplicateCount ( duplicateCount );
                            searchResponder.setServiceCenterTicketNumber ( "alarmpoint_only" );
                            searchResponder = _orm.addOrUpdateRow ( searchResponder );

                            openview_event.setResponderId ( searchResponder.getRowKey() );
                            _orm.addOrUpdateEventsByGroup ( openview_event );

                            logMessage ( message_id, "Duplicate Event #" + duplicateCount );
                        }
                        else
                        {
                            //  Update existing responder with new timestamp
                            responder = new Responder();
                            responder.setMessageId ( message_id );
                            responder.setMessageKey ( message_key );
                            responder.setGroup ( rule.getGroup() );
                            responder.setAlarmpointNotificationTarget ( rule.getAlarmpointGroup() );
                            responder.setServiceCenterTicketMessage   ( ticket_message );
                            responder = _orm.addOrUpdateRow ( responder );

                            openview_event.setResponderId ( responder.getRowKey() );
                            _orm.addOrUpdateEventsByGroup ( openview_event );

                            //  Create new alarmpoint alert
                            Alarmpoint result_ap = _orm.addOrUpdateAlarmpoint ( ap );
                        }
                    }
                    else
                    {
                        //  Create new responder
                        responder = new Responder();
                        responder.setMessageId ( message_id );
                        responder.setMessageKey ( message_key );
                        responder.setGroup ( rule.getGroup() );
                        responder.setAlarmpointNotificationTarget ( rule.getAlarmpointGroup() );
                        responder.setServiceCenterTicketMessage   ( ticket_message );
                        responder = _orm.addOrUpdateRow ( responder );

                        openview_event.setResponderId ( responder.getRowKey() );
                        _orm.addOrUpdateEventsByGroup ( openview_event );

                        //  Create new alarmpoint alert
                        Alarmpoint result_ap = _orm.addOrUpdateAlarmpoint ( ap );
                    }
                }
                else if ( rule.getMethod().equals ( "ticket" ) )
                {
                    //  Sanity check for ticket_message
                    if ( null == ticket_message )
                    {
                        ticket_message = "";
                    }

                    ServiceCenter result_sc = null;

                    if ( _orm.getEebPropertyServiceCenterActive() )
                    {
                        //  Put into the Service Center Buffer queue for processing
                        sc    = new ServiceCenter ();   //  Creates a new Service Center object with the default key so
                                                        //  the ORM facade will know to insert it into the database
                            sc.setMessageId          ( message_id );
                            sc.setTicketOpenedBy     ( "VPO" );
                            sc.setTicketAssignment   ( rule.getGroup() );
                            sc.setTicketMessage      ( ticket_message );
                            sc.setTicketCategory     ( rule.getPeregrineCategory() );
                            sc.setTicketSubCategory  ( rule.getPeregrineSubCategory() );
                            sc.setTicketProblem      ( rule.getPeregrineProblem() );
                            sc.setTicketProduct      ( rule.getPeregrineProduct() );
                            sc.setTicketSeverityCode ( ticketMessage.getSeverity() );
                            sc.setMessageKey         ( message_key );

                        if ( null == source_node )
                        {
                            source_node = "";
                        }

                        if ( null == searchResponder )
                        {
                            //  This is a new ticket + notification
                            processNewTicketAndNotification ( openview_event, rule, responder, ap, ticketMessage, message_id, message_key, source_node, ticket_message );
                        }
                        else
                        {
                            //  If the existing ticket is already closed, then create a new ticket
                            boolean wasTicketClosedOrExpired = checkForClosedOrExpiredTicket ( searchResponder );

                            if ( true == wasTicketClosedOrExpired )
                            {
                                if ( _log.isInfoEnabled() )
                                {
                                    StringBuilder message = new StringBuilder();
                                        message.append ( getClass().getName() );
                                        message.append ( "::onMessage() - Found expired responder entry ( ResponderID=" );
                                        message.append ( searchResponder.getRowKey() );
                                        message.append ( ", OpenviewEventID=" );
                                        message.append ( searchResponder.getMessageId() );
                                        message.append ( ", TicketNumber=" );
                                        message.append ( searchResponder.getServiceCenterTicketNumber() );
                                        message.append ( ", AlarmpointEventID=" );
                                        message.append ( searchResponder.getAlarmpointEventID() );
                                        message.append ( " )\n" );
                                    _log.info ( message.toString() );
                                }

                                processNewTicketAndNotification ( openview_event, rule, responder, ap, ticketMessage, message_id, message_key, source_node, ticket_message );
                            }
                            else
                            {
                                if ( _log.isInfoEnabled() )
                                {
                                    StringBuilder message = new StringBuilder();
                                        message.append ( "Possibly updating ticket...DuplicateCount=" );
                                        message.append ( searchResponder.getDuplicateCount() );
                                        message.append ( ", MaxNumUpdates=" );
                                        message.append ( _orm.getEebPropertyServiceCenterMaxNumUpdates() );
                                    _log.info ( message.toString() );
                                }

                                if ( searchResponder.getDuplicateCount() < _orm.getEebPropertyServiceCenterMaxNumUpdates() )
                                {
                                    //  Processing existing ticket/notification that does not exceed update limit
                                    processExistingTicketAndNotification ( openview_event, searchResponder, message_id, message_key, scTicketNumber, ticket_message );
                                    logMessage ( message_id, "Duplicate Event #" + searchResponder.getDuplicateCount() );
                                }
                                else   //  Too many updates to the same message key
                                {
                                    if ( _log.isEnabledFor ( Level.WARN ) )
                                    {
                                        StringBuilder message = new StringBuilder();
                                            message.append ( "DispatcherConsumerBean::onMessage() - too many updates for MessageKey=" );
                                            message.append ( message_key );
                                            message.append ( ".  A new ticket will be created for this message key." );
                                        _log.warn ( message.toString() );
                                    }

                                    //  Create a new responder row
                                    responder = new Responder();
                                    responder.setRowKey ( Responder.DEFAULT_KEY );
                                    responder.setDuplicateCount ( 0 );
                                    responder.resetTimestamp();
                                    _orm.addOrUpdateRow ( responder );

                                    if ( openview_event.getSeverity().equals ( "critical" ) ||
                                         openview_event.getSeverity().equals ( "major"    ) ||
                                         openview_event.getSeverity().equals ( "minor"    ) ||
                                         openview_event.getSeverity().equals ( "warning"  ) )
                                    {
                                        createServiceCenterTicket ( openview_event, rule, message_id, ticket_message, message_key, ticketMessage );
                                    }
                                }
                            }
                        }

                    }  // If Service Center ticketing is active
                    else
                    {
                        _log.info ( "Ticket processing - ServiceCenter module has been administratively disabled #1" );

                        responder = new Responder();
                        responder.setRowKey ( Responder.DEFAULT_KEY );
                        responder.setDuplicateCount ( 0 );
                        responder.resetTimestamp();
                        responder.setMessageKey ( message_key );
                        responder.setServiceCenterTicketNumber ( "alarmpoint_only" );
                        responder.setMessageId ( message_id );
                        responder.setMessageKey ( message_key );
                        responder.setGroup ( rule.getGroup() );
                        responder.setAlarmpointNotificationTarget ( rule.getAlarmpointGroup() );
                        responder.setServiceCenterTicketMessage   ( ticket_message );
                        _orm.addOrUpdateRow ( responder );

                        Alarmpoint result_ap = _orm.addOrUpdateAlarmpoint ( ap );
                    }
                } //  If this is a "ticket" method
                else
                {
                    _log.info ( "Ticket processing - ServiceCenter module has been administratively disabled #2" );
                }

                updateResponder ( responder, message_id );

                //  Own the event
                ownOpenviewMessage ( openview_event.getMessageId(), "eeb", "HYPertext01" );

                //acknowledgeOpenviewMessage ( message_id, "[ESM-EnterpriseEventBus::Dispatcher] The EEB has processed this event" );
                //writeToOpenView( dm.getTicketMessage(), "EEB has processed this event" );
                logDownStreamMessage ( ticketMessage.getMessageId() );
            }
        }
        catch ( Exception exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::onMessage() - Error encountered: " );
                message.append ( exception );
            _log.error ( message.toString(), exception );
        }
    }

    private void saveServiceCenterTicket ( DataMapRule rule, String ticket_owner, String ticket_message, String message_id )
    {
        _log.warn ( "The ESM-Enterprise Event Bus has experienced problems connecting to ServiceCenter.  This ticket will be buffered and created when Service Center comes back up." );

        ServiceCenter scParameters = new ServiceCenter();
        scParameters.setTicketAssignment    ( rule.getPeregrineAssignment() );
        scParameters.setTicketAssigneeName  ( ticket_owner                  );
        scParameters.setTicketMessage       ( ticket_message                );
        scParameters.setMessageId           ( message_id                    );
        /*
        scParameters.setTicketCategory      ( ServiceCenter.DEFAULT_VALUE   );  //  category
        scParameters.setTicketOpenTime      ( ServiceCenter.DEFAULT_VALUE   );  //  open.time
        scParameters.setTicketOpenedBy      ( ServiceCenter.DEFAULT_VALUE   );  //  opened.by
        scParameters.setTicketPriorityCode  ( ServiceCenter.DEFAULT_VALUE   );  //  priority.code
        scParameters.setTicketSeverityCode  ( ServiceCenter.DEFAULT_VALUE   );  //  severity.code
        scParameters.setTicketUpdateTime    ( ServiceCenter.DEFAULT_VALUE   );  //  update.time
        scParameters.setTicketAssignment    ( ServiceCenter.DEFAULT_VALUE   );  //  assignment
        scParameters.setTicketAlertTime     ( ServiceCenter.DEFAULT_VALUE   );  //  alert.time
        scParameters.setTicketStatus        ( ServiceCenter.DEFAULT_VALUE   );  //  status
        scParameters.setTicketCloseTime     ( ServiceCenter.DEFAULT_VALUE   );  //  close.time
        scParameters.setTicketClosedBy      ( ServiceCenter.DEFAULT_VALUE   );  //  closed.by
        scParameters.setTicketFlag          ( ServiceCenter.DEFAULT_VALUE   );  //  flag
        scParameters.setTicketAssigneeName  ( ServiceCenter.DEFAULT_VALUE   );  //  assignee.name
        scParameters.setTicketRespondTime   ( ServiceCenter.DEFAULT_VALUE   );  //  respond.time
        scParameters.setTicketContactName   ( ServiceCenter.DEFAULT_VALUE   );  //  contact.name  -- IGI
        scParameters.setTicketActor         ( ServiceCenter.DEFAULT_VALUE   );  //  actor
        scParameters.setTicketFormat        ( ServiceCenter.DEFAULT_VALUE   );  //  format
        scParameters.setTicketDeadlineGroup ( ServiceCenter.DEFAULT_VALUE   );  //  deadline.group
        scParameters.setTicketDeadlineAlert ( ServiceCenter.DEFAULT_VALUE   );  //  deadline.alert
        //*/

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

    private void logThrottleMessage ( DataMapRule rule )
    {
        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder ( "Received throttle message\n" );
            message.append ( "\tGroup:       " + rule.getGroup()            );
            message.append ( "\n\tMethod:    " + rule.getMethod()           );
            message.append ( "\n\tAP Group:  " + rule.getAlarmpointGroup()  );
            message.append ( "\n\tAP Script: " + rule.getAlarmpointScript() );
            message.append ( "\n" );

            _log.debug ( message.toString() );
        }
    }

    private void logTicketMessage ( TicketMessage ticketMessage )
    {
        StringBuilder log_message = null;

        if ( _log.isDebugEnabled() )
        {
            log_message = new StringBuilder();
            log_message.append ( "Processing message_id=" );
            log_message.append ( ticketMessage.getMessageId() );
            log_message.append ( " ( suppressCount, severity  ) = ( " );
            log_message.append ( ticketMessage.getSuppressCount() );
            log_message.append ( ", " );
            log_message.append ( ticketMessage.getSeverity() );
            log_message.append ( " )" );
        }

        if ( null != log_message )
        {
            LogToFile ( logExternalFileName, log_message.toString() );
        }
    }

    private void logServiceCenterTicketMessage ( ServiceCenterTicket serviceCenterTicket, DispatcherMessage dm )
    {
        StringBuilder ticket_message = new StringBuilder ( dm.getTicketMessage().getMessageText() );
            ticket_message.append ( "  ServiceCenter service URL: http://helpmeqa/getservices/view_specific.cfm?number=" );
            ticket_message.append ( serviceCenterTicket.getTicketNumber() );
            ticket_message.append ( " -  HelpME Ticket No. " );
            ticket_message.append ( serviceCenterTicket.getTicketNumber() );
        LogToFile ( logExternalFileName, ticket_message.toString() );
    }

    private void logNewEvent ( DataMapRule rule, DispatcherMessage dm )
    {
        StringBuilder message = new StringBuilder ( "Processing new event ( PeregrineAssignment, MessageText ) = ( " );
            message.append ( rule.getPeregrineAssignment() );
            message.append ( ", " );
            message.append ( dm.getTicketMessage().getMessageText() );
            message.append ( " )" );
        LogToFile ( logExternalFileName, message.toString() );
    }

    	
	public void logResponse ( TicketResponse tikRes, TicketInfo response ) {
        _log.info ( "Create Incident Response message: " + tikRes.getMessage() );
        /*
        if ( _log.isInfoEnabled() ) {
        	StringBuilder message = new StringBuilder ( "" );
        	if (tikRes.getReturncode().intValue() != 1000) {
	        	message.append ( "Incident Response with CallID=" );
	        	message.append ( response.getId() );
	        	message.append ( "\n    getTicketType:..............." );
	            message.append ( response.getTickettype());
	            message.append ( "\n    getContact:..............." );
	            message.append ( response.getContact());
	            message.append ( "\n    getDescription:..............." );
	            message.append ( response.getDescription());
	            message.append ( "\n    getTicketStatus:..............." );
	            message.append ( response.getTicketstatus());
	            message.append ( "\n    getContactFullName:..............." );
	            message.append ( response.getContactname());
	            message.append ( "\n    getOwner:..............." );
	            message.append ( response.getOwner());
	            message.append ( "\n    getOpenDate:..............." );
	            message.append ( response.getOpeneddate());
	            message.append ( "\n    getSubcategory:..............." );
	            message.append ( response.getSubcategory());
	            message.append ( "\n    getProductType:..............." );
	            message.append ( response.getProducttype());
	            message.append ( "\n    getProblemType:..............." );
	            message.append ( response.getProblemtype());
	            message.append ( "\n    getRecipientName:..............." );
	            message.append ( response.getRecipientname());
	            message.append ( "\n    getOpenedBy:..............." );
	            message.append ( response.getOpenedby());
	            message.append ( "\n    getUpdateBy:..............." );
	            message.append ( response.getUpdatedby());
	            message.append ( "\n    getResolution:..............." );
	            message.append ( response.getResolution());
	            message.append ( "\n    getClosedBy:..............." );
	            message.append ( response.getClosedby());
	            message.append ( "\n    getRecipientPhone:..............." );
	            message.append ( response.getRecipientphone());
	            message.append ( "\n    getRecipientdeskno:..............." );
	            message.append ( response.getRecipientdeskno());
	            message.append ( "\n    getRecipientName:..............." );
	            message.append ( response.getRecipientname());
	            message.append ( "\n    getContactPhone:..............." );
	            message.append ( response.getContactphone());
	            message.append ( "\n    getImpact:..............." );
	            message.append ( response.getImpact());
	            message.append ( "\n    getResolutionCatTier3:..............." );
	            message.append ( response.getClosecode());
	            message.append ( "\n    getCategory:..............." );
	            message.append ( response.getCategory());
	            message.append ( "\n    getUrgency:..............." );
	            message.append ( response.getSeverity());
	            message.append ( "\n    getJournalUpdates:..............." );
	            List<String> journalupdates = response.getJournalupdates();
	            StringBuffer strBuffer = new StringBuffer();
	            for (String str : journalupdates) {
	            	strBuffer.append(str);
	            	strBuffer.append(AbstractIncidentService.TEXT_DELIMITER);
	            }
	            message.append (strBuffer.toString() );
	            message.append ( "\n    getSource:..............." );
	            message.append ( response.getSource());
	            message.append ( "\n    getClosedDate:..............." );
	            message.append ( response.getCloseddate());
        	}
            message.append ( "\n    Return Code:............" );
            message.append ( tikRes.getReturncode()         );
            message.append ( "\n    Messages:............" );
            message.append ( tikRes.getMessages()         );
            message.append ( "\n    Message:............" );
            message.append ( tikRes.getMessage()         );
            _log.info(message.toString());
        }
        //*/
    }


    public void logResponse(IncidentResponse response) {
        _log.info ( "Category:              " + response.getCategory());
        _log.info ( "Subcategory:           " + response.getSubcategory());
        _log.info ( "Product Type:          " + response.getProductType());
        _log.info ( "Problem Type:          " + response.getProblemType());
        _log.info ( "Opened By:             " + response.getOpenedBy());
        _log.info ( "Ext App Key:           " + response.getExtAppKey());
        _log.info ( "Status:                " + response.getStatus());
        _log.info ( "ReturnCode:            " + response.getReturnCode());
        _log.info ( "ID:                    " + response.getId());
        _log.info ( "Message:               " + response.getMessage());
        _log.info ( "Contact Name:          " + response.getContactName());
        _log.info ( "Brief Description:     " + response.getBriefDescription());
        _log.info ( "Description:           " + response.getDescription());
        _log.info ( "Ticket Status:         " + response.getTicketStatus());
        _log.info ( "Contact First Name:    " + response.getContactFirstName());
        _log.info ( "Contact Last Name:     " + response.getContactLastName());
        _log.info ( "Owner:                 " + response.getOwner());
        _log.info ( "Open date:             " + response.getOpenDate());
        _log.info ( "Timezone String:       " + response.getTimezoneString());
        _log.info ( "Updated By:            " + response.getUpdateBy());
        _log.info ( "Resolution:            " + response.getResolution());
        _log.info ( "Closed By:             " + response.getClosedBy());
        _log.info ( "Alert Status:          " + response.getAlertStatus());
        _log.info ( "Assignment Group:      " + response.getAssignmentGroup());
        _log.info ( "Impact:                " + response.getImpact());
        _log.info ( "Resolution Cat Tier 3: " + response.getResolutionCatTier3());
        _log.info ( "Urgency:               " + response.getUrgency());
        _log.info ( "Source:                " + response.getSource());
        _log.info ( "Closed Date:           " + response.getClosedDate());
        _log.info ( "Messages:              " + response.getMessages());
        //_log.info ( "Assignee:              " + response.getAssignee());
        _log.info ( "Open Date:             " + response.getOpenDate());
        _log.info ( "Journal Updates:" );
        /*
        StringType[] journalUpdates = response.getJournalUpdates();
        if (journalUpdates != null) {
            for (int i = 0; i < journalUpdates.length; i++)
                _log.info(journalUpdates[i].toString());
        }
        //*/
    }

    private boolean updateServiceCenterTicket ( Responder responder, String scTicketNumber, String update_message, String message_key )
    {
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::onMessage() - updating ticket ( MessageKey=" );
                message.append ( message_key );
                message.append ( ", ResponderID=" );
                message.append ( responder.getRowKey() );
            _log.warn ( message.toString() );
        }

        try
        {
            String receipt_id = responder.getSCAsyncRequestReceipt();
            AbstractFinder receiptFinder = Configuration.getSingleton().getFinder();
            ICreateIncidentRequest createIncidentRequest = null;

            if ( null != receipt_id )
            {
                receiptFinder.selectCreateIncidentRequest ( receipt_id );
                if ( null != createIncidentRequest )
                {
                    responder.setServiceCenterTicketNumber ( createIncidentRequest.getIncidentID() );
                    _orm.addOrUpdateRow ( responder );
                }
            }

            UpdateIncidentRequest _soaReq = new UpdateIncidentRequest();
    		    _soaReq.setIncidentid  ( scTicketNumber );
    		    _soaReq.setSynchronous ( false );
                if (( null != update_message ) && ( update_message.trim().length() > 5 ))
                {
    		        _soaReq.setJournalupdates ( update_message + ", Duplicate #" + responder.getDuplicateCount() );
                }
                else
                {
                    _soaReq.setJournalupdates ( "Duplicate event detected, Duplicate #" + responder.getDuplicateCount() );
                }
    		    IncidentServiceMediator svc = new IncidentServiceMediator();
    		UpdateIncidentResponse updateTikResponse = svc.updateIncident(_soaReq);

    		logResponse(updateTikResponse, updateTikResponse.getTicketinfo());
            logMessage ( responder.getMessageId(), receipt_id, BussModule.SC_GATEWAY_UPDATE );

            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::onMessage() - updated ticket ( MessageKey=" );
                    message.append ( message_key );
                    message.append ( ", IncidentID=" );
                    message.append ( scTicketNumber );
                    message.append ( " )" );
                _log.warn ( message.toString() );
            }

            return true;
        }
        catch ( Exception exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::updateServiceCenterTicket ( TicketID=" );
                message.append ( scTicketNumber );
                message.append ( ", UpdateMessage=" );
                message.append ( update_message );
                message.append ( " ) - exception: " );
                message.append ( exception.getMessage() );
            _log.error ( message.toString(), exception );
        }

        return false;
    }

    private boolean createServiceCenterTicket ( EventsByGroup openview_event, DataMapRule dataMapRule, String message_id, String ticket_message, String message_key, TicketMessage ticketMessage )
    {
        //  This is the first time that this message key has been encountered.  Maybe this is a new monitor?
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::onMessage() - new message key detected: " );
                message.append ( message_key );
                message.append ( ", connecting to Service Center at:" );
                message.append ( PeregrineConfigurationSingleton.getInstance().getServiceCenterLocation() );
            _log.warn ( message.toString() );
        }

        try
        {
            Responder responder = new Responder();
            String source_node = ticketMessage.getSourceNode();
            if ( null == source_node ) source_node = "";

            String lines[] = ticket_message.split ( "\n" );

            CreateIncidentRequest _soaReq = new CreateIncidentRequest();
                _soaReq.setSynchronous( false );
                _soaReq.setHostname     ( source_node );
                _soaReq.setCategory     ( dataMapRule.getPeregrineCategory()    );
                _soaReq.setSubcategory  ( dataMapRule.getPeregrineSubCategory() );
                _soaReq.setProducttype  ( dataMapRule.getPeregrineProduct()     );
                _soaReq.setProblemtype  ( dataMapRule.getPeregrineProblem()     );
                _soaReq.setDescription  ( ticket_message      );
                _soaReq.setContact      ( PeregrineConfigurationSingleton.getInstance().getServiceCenterAccount() );
                _soaReq.setBriefdescription ( lines[0] );
                if ( false == dataMapRule.getAllowPassthruSeverity() )
                {
                    _soaReq.setSeverity     ( "4" );
                    _soaReq.setImpact       ( "4" );
                }
                else
                {
                    String strSeverity = ticketMessage.getSeverity();

                    if ( null == strSeverity  )
                    {
                        _soaReq.setSeverity     ( "4" );
                        _soaReq.setImpact       ( "4" );
                    }
                    else
                    {
                        if ( strSeverity.equals ( "critical" ) )
                        {
                            strSeverity = "1";
                        }
                        else if ( strSeverity.equals ( "major" ) )
                        {
                            strSeverity = "2";
                        }
                        else if ( strSeverity.equals ( "minor" ) )
                        {
                            strSeverity = "3";
                        }
                        else if ( strSeverity.equals ( "warning" ) )
                        {
                            strSeverity = "4";
                        }
                        else if ( strSeverity.equals ( "normal" ) )
                        {
                            strSeverity = "5";
                        }
                        else
                        {
                            if ( _log.isEnabledFor ( Level.WARN ) )
                            {
                                StringBuilder message = new StringBuilder();
                                    message.append ( getClass().getName() );
                                    message.append ( "::onMessage() - created ticket ( MessageKey=" );
                                    message.append ( message_key );
                                    message.append ( ", MessageID=" );
                                    message.append ( message_id );
                                    message.append ( " ) - unknown event severity: " );
                                    message.append ( strSeverity );
                                _log.warn ( message.toString() );
                            }

                            strSeverity = "4";
                        }
                    }

                    _soaReq.setSeverity     ( strSeverity );
                    _soaReq.setImpact       ( strSeverity );
                }
                _soaReq.setAssignmentgroup( dataMapRule.getPeregrineAssignment() );
                _soaReq.setExtappkey      ( message_id );
            IncidentServiceMediator svc = new IncidentServiceMediator();
            CreateIncidentResponse response = svc.createIncident(_soaReq);

            if ( null != response )
            {
                logResponse ( response, response.getTicketinfo() );

                if ( _log.isEnabledFor ( Level.WARN ) )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::onMessage() - created ticket ( MessageKey=" );
                        message.append ( message_key );
                        message.append ( ", MessageID=" );
                        message.append ( message_id );
                        message.append ( " )" );
                    _log.warn ( message.toString() );
                }

                StringTokenizer tokenizer = new StringTokenizer ( response.getMessage(), "=" );
                String request_type = tokenizer.nextToken();
                String receipt_id   = tokenizer.nextToken();

                logMessage ( message_id, receipt_id, BussModule.SC_GATEWAY_CREATE );

                responder.setSCAsyncRequestReceipt ( receipt_id );
                responder.setMessageId ( message_id );
                responder.setMessageKey ( message_key );
                responder.setGroup ( dataMapRule.getGroup() );
                responder.setAlarmpointNotificationTarget ( dataMapRule.getAlarmpointGroup() );
                responder.setServiceCenterTicketMessage   ( ticket_message );
                responder = _orm.addOrUpdateRow ( responder );

                openview_event.setResponderId ( responder.getRowKey() );
                _orm.addOrUpdateEventsByGroup ( openview_event );

                return true;
            }
            else
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::onMessage() - attempted to create ticket ( MessageKey=" );
                    message.append ( message_key );
                    message.append ( ", MessageID=" );
                    message.append ( message_id );
                    message.append ( " ) - did not get a response back from SCGateway (GSS)" );
                _log.error ( message.toString() );

                return false;
            }
        }
        catch ( Exception exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::createServiceCenterTicket ( MessageID=" );
                message.append ( message_id );
                message.append ( ", TicketMessage=" );
                message.append ( ticket_message );
                message.append ( " ) - exception: " );
                message.append ( exception.getMessage() );
            _log.error ( message.toString(), exception );

            return false;
        }
    }

    private boolean checkForClosedOrExpiredTicket ( Responder responder )
    {
        //  Check for expired ticket
        int delay_in_seconds = 3600;  // Default to 3600 seconds, i.e. 1 hr
        EebProperty property = _orm.selectEebProperty ( "suppress.duplicate.ServiceCenter" );
        if ( null != property )
        {
            try
            {
                delay_in_seconds = Integer.parseInt ( property.getPropertyValue() );
            }
            catch ( NumberFormatException exception )
            {
                _log.error ( "Invalid value for EEBProperty ( Key=suppress.duplicate.ServiceCenter ) - " + property.getPropertyValue(), exception );
            }
        }

        Calendar timestamp = responder.getTimestamp();
        int difference     = (int) (( System.currentTimeMillis() - timestamp.getTime().getTime() ) / 1000 );

        //  Haven't waited long enough, update duplicate counter only
        if ( difference > delay_in_seconds )
        {
            return true;
        }

        //  Check for closed ticket
        if (( null  != responder.getServiceCenterTicketNumber() ) &&
            ( false == responder.getServiceCenterTicketNumber().equals ( "IM-Default" ) ))
        {
            RetrieveTicketRequest _soaReq = new RetrieveTicketRequest();
                _soaReq.setTicketid ( responder.getServiceCenterTicketNumber() );
                TicketServiceMediator svc = new TicketServiceMediator();
            RetrieveTicketResponse response = svc.retrieveTicket (_soaReq);

            String TicketStatus = response.getTicketinfo().getTicketstatus();

            if ( _log.isInfoEnabled() )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( getClass().getName() );
                    message.append ( "::onMessage() - found existing Ticket ( " );
                    message.append ( responder.getServiceCenterTicketNumber() );
                    message.append ( " with status: " );
                    message.append ( TicketStatus );
                    message.append ( ".  Making request to " );
                    message.append ( PeregrineConfigurationSingleton.getInstance().getServiceCenterLocation() );
                _log.info ( message.toString() );
            }

            if (( null != TicketStatus ) &&
                ( ( TicketStatus.equals ( "Closed" ) ) ||
                  ( TicketStatus.equals ( "Resolved" ) ) ) )
            {
                return true;
            }
        }

        return false;
    }
    
    private void processNewTicketAndNotification ( EventsByGroup openview_event, DataMapRule rule, Responder responder, Alarmpoint ap, TicketMessage ticketMessage, String message_id, String message_key, String source_node, String ticket_message )
    {
        if ( openview_event.getSeverity().equals ( "critical" ) ||
             openview_event.getSeverity().equals ( "major"    ) ||
             openview_event.getSeverity().equals ( "warning"  ) ||
             openview_event.getSeverity().equals ( "minor"    ) )
        {
            StringTokenizer tokenizer = new StringTokenizer ( source_node, "." );
            String TruncatedSourceNode = tokenizer.nextToken();

            createServiceCenterTicket ( openview_event, rule, message_id, String.format ( "%s: %s", TruncatedSourceNode, ticket_message), message_key, ticketMessage );
            //createServiceCenterTicket ( rule, responder, message_id, ticket_message, message_key );
        }

        if ( _orm.getEebPropertyAlarmpointActive() )
        {
            if ( openview_event.getSeverity().equals ( "critical" ) ||
                 openview_event.getSeverity().equals ( "major"    ))
            {
                Alarmpoint result_ap = _orm.addOrUpdateAlarmpoint ( ap );
            }
        }
    }

    private void processExistingTicketAndNotification ( EventsByGroup openview_event, Responder responder, String message_id, String message_key, String scTicketNumber, String ticket_message )
    {
        //  Increment the duplicate counter for the responder
        responder.incrementDuplicateCount();
        Responder save_result = _orm.addOrUpdateRow ( responder );

        openview_event.setResponderId ( responder.getRowKey() );
        _orm.addOrUpdateEventsByGroup ( openview_event );

        //  Retrieve the corresponding ticket from the Responder object and look for the ticket in Archway
        scTicketNumber = responder.getServiceCenterTicketNumber();
        //scTicketNumber = "IM70680";

        if (( null == scTicketNumber ) || ( true == scTicketNumber.equals ( ServiceCenter.UNKNOWN_TICKET_NUM )))
        {
            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                StringBuilder message = new StringBuilder();
                    message.append ( "DispatcherConsumerBean::onMessage() - skipping ticket update for MessageKey=" );
                    message.append ( message_key );
                    message.append ( " because Service Center ticket has not yet been created.  Responder ( RowKey=" );
                    message.append ( responder.getRowKey() );
                    message.append ( " now has a duplicate count of " );
                    message.append ( responder.getDuplicateCount() );
                _log.warn ( message.toString() );
            }
        }
        else
        {
            if ( openview_event.getSeverity().equals ( "critical" ) ||
                 openview_event.getSeverity().equals ( "major"    ) ||
                 openview_event.getSeverity().equals ( "warning"  ) ||
                 openview_event.getSeverity().equals ( "minor"    ) )
            {
                updateServiceCenterTicket ( responder, scTicketNumber, ticket_message, message_key );

                annotateOpenviewMessage ( message_id, "[ESM-EnterpriseEventBus::Dispatcher] Updated Service Center Ticket #" + scTicketNumber );
            }
        }
    }

    private void updateResponder ( Responder responder, String message_id )
    {
        if ( null != responder )
        {
            LogToFile ( logExternalFileName, "Update events by group #4 with responder.rowKey=" + responder.getRowKey() );
            long debug_timestamp = System.currentTimeMillis();
            LogToFile ( logExternalFileName, "Exception debug 01 - " + debug_timestamp );
            Responder save_result = _orm.addOrUpdateRow ( responder );
            LogToFile ( logExternalFileName, "Exception debug 02 - " + debug_timestamp );
            if ( null == save_result )
            {
                LogToFile  ( logExternalFileName, "Could not add or update Responder: =" + responder.getRowKey() );
                _log.error ( "Could not add or update Responder: =" + responder.getRowKey() );
            }
            LogToFile ( logExternalFileName, "Searching for event with message_id=" + message_id );
            EventsByGroup event = _orm.selectEventsByGroupByMessageId ( message_id );
            if ( null != event )
            {
                event.setResponderId ( responder.getRowKey() );
                LogToFile ( logExternalFileName, "Update events by group #4 with event.responderId=" + event.getResponderId() + " and row_id=" + event.getRowId() );
                _orm.addOrUpdateEventsByGroup ( event );
            }
        }
        else
        {
            LogToFile ( logExternalFileName, "Update events.. null responder detected" );
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
