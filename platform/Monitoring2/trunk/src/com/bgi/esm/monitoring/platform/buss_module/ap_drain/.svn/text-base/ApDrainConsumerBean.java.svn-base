package com.bgi.esm.monitoring.platform.buss_module.ap_drain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.Calendar;
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
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint.Notification;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bglobal.gss.peregrine.persistence.AbstractFinder;
import com.bglobal.gss.peregrine.persistence.Configuration;
import com.bglobal.gss.peregrine.persistence.ICreateIncidentRequest;
import com.bglobal.gss.peregrine.service.mediator.IncidentServiceMediator;
import com.bglobal.gxml.ticket.UpdateIncidentRequest;
import com.bglobal.gxml.ticket.UpdateIncidentResponse;

/**
 * Alarm Point Drain
 * 
 * @ejb.bean 
 *   name="ApDrainConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="dispatcher queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.ap_drain"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ApDrainConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-APDrain.out";
	/**
	 * ctor
	 */
	public ApDrainConsumerBean() {
		super(BussModule.AP_DRAIN);

		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("ApDrainConsumerBean ctor failure");
            _log = Logger.getLogger ( ApDrainConsumerBean.class );
            _log.error ( "ApDrainConsumerBean ctor failure", exception );
		}
		
		_log.debug("ap_drain consumer ctor");
	}

	/**
	 * Invoked when messages arrive via queue
	 * 
	 * @param arg fresh message
	 */
	public void onMessage(Message arg) {
        long message_timestamp = System.currentTimeMillis();

        logEntryMessage  ( Long.toString ( message_timestamp ) );

		_log.debug("fresh message noted");

        if ( _orm.getEebPropertyAlarmpointActive() )
        {
            LogToFile ( "c:\\test\\JMS-APDrain-AlarmpointActive", "Alarmpoint active" );

            try
            {
                for ( int counter = 0; counter < 5; counter++ )
                {
                    reconcileAgainstJavaClient();

                    reconcileAgainstJavaClient ( "peregrine" );
                }

                EebProperty property = _orm.selectEebProperty ( "Alarmpoint.delay_seconds" );
                if ( null == property )
                {
                    recoverFailedAlerts ( 90 );
                }
                else
                {
                    try
                    {
                        recoverFailedAlerts ( Integer.parseInt ( property.getPropertyValue() ) );
                    }
                    catch ( NumberFormatException exception )
                    {
                        _log.error ( exception );

                        recoverFailedAlerts ( 90 );
                    }
                }
            }
            catch ( Exception exception )
            {
                _log.error ( "Error when processing queued Alarmpoint alert", exception );
            }
        }
        else
        {
            LogToFile ( "c:\\test\\JMS-APDrain-AlarmpointActive", "Alarmpoint inactive" );
            try
            {
                _log.info ( "Alarmpoint alerting has been deactivated.  Bypassing for JMS Message: " + arg.getJMSMessageID() );

            }
            catch ( JMSException exception )
            {
                _log.info ( "Alarmpoint alerting has been deactivated.  Bypassing alerting" );
            }
        }


        logExitMessage  ( Long.toString ( message_timestamp ) );
	}

    final private static String APJC_XMLNODE_INCIDENT_ID         = "<incident_id>";
    final private static int APJC_XMLNODE_INCIDENT_ID_STRLENGTH  = APJC_XMLNODE_INCIDENT_ID.length();

    final private static String APJC_XMLNODE_EVENT_ID            = "<event_id>";
    final private static int APJC_XMLNODE_EVENT_ID_STRLENGTH     = APJC_XMLNODE_EVENT_ID.length();

    final private static String APJC_XMLNODE_REQUEST_TEXT        = "<request_text>";
    final private static int APJC_XMLNODE_REQUEST_TEXT_STRLENGTH = APJC_XMLNODE_REQUEST_TEXT.length();

    final private static String APJC_XMLNODE_FIRST_NAME          = "<first_name>";
    final private static int APJC_XMLNODE_FIRST_NAME_STRLENGTH   = APJC_XMLNODE_FIRST_NAME.length();

    final private static String APJC_XMLNODE_LAST_NAME           = "<last_name>";
    final private static int APJC_XMLNODE_LAST_NAME_STRLENGTH    = APJC_XMLNODE_LAST_NAME.length();

    final private static String APJC_XMLNODE_WEBLOGIN            = "<weblogin>";
    final private static int APJC_XMLNODE_WEBLOGIN_STRLENGTH     = APJC_XMLNODE_WEBLOGIN.length();

    private void reconcileAgainstJavaClient ()
    {
        String apjc_client_name  = _orm.selectEebProperty ( "Alarmpoint.JavaClient.CommandLine.client"    ).getPropertyValue();

        reconcileAgainstJavaClient ( apjc_client_name );
    }

    private void reconcileAgainstJavaClient ( String apjc_client_name )
    {
        String apjc_command_line = _orm.selectEebProperty ( "Alarmpoint.JavaClient.CommandLine.full_path" ).getPropertyValue();

        StringBuilder command_line = new StringBuilder();
        command_line.append ( apjc_command_line );
        command_line.append ( " --get-response " );
        command_line.append ( apjc_client_name );

        String apjc_response = retrieveAPJCResponse ( command_line.toString() );

        _log.debug ( "Retrieved APJC response: " + apjc_response );

        while (
                ( null != apjc_response ) && 
                (
                  ( apjc_response.indexOf ( "<method>OK</method>" ) > 50 ) // ||
                  //( apjc_response.indexOf ( "<method>Request</method>" ) > 50 )
                )
              )
        {
            /*
            try
            {
                FileOutputStream outfile = new FileOutputStream ( "c:/test/AlarmpointDrain." + System.currentTimeMillis() + ".xml" );
                    outfile.write ( apjc_response.getBytes() );
                outfile.close();
            }
            catch ( Exception exception )
            {
                _log.error ( exception );
            }
            //*/

            String incident_id     = retrieveAPJCIncidentID  ( apjc_response.toString() );
            String event_id        = retrieveAPJCEventID     ( apjc_response.toString() );
            String request_text    = retrieveAPJCRequestText ( apjc_response.toString() );
            String user_name_first = retrieveAPJCFirstName   ( apjc_response.toString() );
            String user_name_last  = retrieveAPJCLastName    ( apjc_response.toString() );
            String user_weblogin   = retrieveAPJCWeblogin    ( apjc_response.toString() );

            if ( _log.isDebugEnabled() )
            {
                _log.debug ( String.format ( "Extracted ( IncidentID=%s, EventID=%s, RequestText=%s, UserNameFirst=%s, UserNameLast=%s, WebLogin=%s )", incident_id, event_id, request_text, user_name_first, user_name_last, user_weblogin ) );
            }

            if ( null != incident_id )
            {
                StringBuilder alarmpointAnnotation = new StringBuilder();

                alarmpointAnnotation.append ( "Alarmpoint Notification Information:\n" );
                alarmpointAnnotation.append ( "    Incident ID:     " );
                alarmpointAnnotation.append ( incident_id );
                alarmpointAnnotation.append ( "\n" );

                if (( null != user_name_first ) && ( null != user_name_last ))
                {
                    alarmpointAnnotation.append ( "    User Notified:   " );
                    alarmpointAnnotation.append ( user_name_first );
                    alarmpointAnnotation.append ( " "  );
                    alarmpointAnnotation.append ( user_name_last );
                    alarmpointAnnotation.append ( "\n" );
                }

                if ( null != event_id )
                {
                    alarmpointAnnotation.append ( "    Event ID:        " );
                    alarmpointAnnotation.append ( event_id );
                    alarmpointAnnotation.append ( "\n" );
                }

                if ( null != request_text )
                {
                    alarmpointAnnotation.append ( "    Request Text:    " );
                    alarmpointAnnotation.append ( request_text );
                    alarmpointAnnotation.append ( "\n" );
                }

                if ( _log.isDebugEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                    message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" );
                    message.append ( incident_id );
                    message.append ( ", EventID=" );
                    message.append ( event_id );
                    message.append ( ", RequestText=" );
                    message.append ( request_text );
                    message.append ( " )" );

                    _log.debug ( message );
                }

                if ( !incident_id.equals ( "alarmpoint_only" ) )
                {
                    Responder responder = null;
                    try
                    {
                        responder = _orm.findResponderByServiceCenterTicketNumber ( incident_id );
                    }
                    catch ( Exception exception )
                    {
                        StringBuilder message = new StringBuilder();
                            message.append ( getClass().getName() );
                            message.append ( "::reconcileAgainstJavaClient ( APJCClientName=" );
                            message.append ( apjc_client_name );
                            message.append ( ", IncidentID=" );
                            message.append ( incident_id );
                            message.append ( " ) - search for responder by Service Center ticket number failed" );
                        _log.error ( message.toString(), exception );
                    }

                    if ( null != responder )
                    {
                        annotateOpenviewMessage ( responder.getMessageId(), alarmpointAnnotation.toString() );
                        logMessage ( responder.getMessageId(), "AP Event ID #" + event_id );

                        if ( _log.isDebugEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                            message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" ); message.append ( incident_id ); message.append ( ", EventID=" ); message.append ( event_id ); message.append ( ", RequestText=" ); message.append ( request_text );
                            message.append ( " ) - DEBUG 01" );

                            _log.debug ( message );
                        }
                    }
                    else
                    {
                        responder = _orm.selectResponderByMessageId ( incident_id );
                        if ( null != responder )
                        {
                            annotateOpenviewMessage ( incident_id, alarmpointAnnotation.toString() );
                            logMessage ( incident_id, "AP Event ID #" + event_id );
                        }

                        if ( _log.isDebugEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                            message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" ); message.append ( incident_id ); message.append ( ", EventID=" ); message.append ( event_id ); message.append ( ", RequestText=" ); message.append ( request_text );
                            message.append ( " ) - DEBUG 02" );

                            _log.debug ( message );
                        }
                    }

                    if ( null != responder )
                    {
                        responder.setAlarmpointEventID ( event_id );
                        _orm.addOrUpdateRow ( responder );

                        if ( _log.isDebugEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                            message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" ); message.append ( incident_id ); message.append ( ", EventID=" ); message.append ( event_id ); message.append ( ", RequestText=" ); message.append ( request_text );
                            message.append ( " ) - DEBUG 03" );

                            _log.debug ( message );
                        }
                    }
                }
                else
                {
                    if ( _log.isDebugEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                        message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" ); message.append ( incident_id ); message.append ( ", EventID=" ); message.append ( event_id ); message.append ( ", RequestText=" ); message.append ( request_text );
                        message.append ( " ) - DEBUG 04" );

                        _log.debug ( message );
                    }

                    if ( null != incident_id )
                    {
                        annotateOpenviewMessage ( incident_id, alarmpointAnnotation.toString() );

                        if ( _log.isDebugEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                            message.append ( "ApDrainConsumerBean - Retrieved Alarmpoint Event info ( IncidentID=" ); message.append ( incident_id ); message.append ( ", EventID=" ); message.append ( event_id ); message.append ( ", RequestText=" ); message.append ( request_text );
                            message.append ( " ) - DEBUG 05" );

                            _log.debug ( message );
                        }
                    }
                }
            }
            else
            {
                _log.error ( "Invalid Alarmpoint Java Client response detected: " + apjc_response );
            }
        
            apjc_response = retrieveAPJCResponse ( command_line.toString() );
        }
    }

    private String retrieveAPJCResponse ( String command )
    {
        try
        {
            _log.debug ( "Executing APJC command: " + command );

            Runtime rt = Runtime.getRuntime();

            Process p = rt.exec ( command );
            //p.waitFor();

            InputStream in = p.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte contents[] = new byte[1024];
            int num_read = in.read ( contents );
            while ( num_read >= 0 )
            {
                baos.write ( contents, 0, num_read );

                num_read = in.read ( contents );
            }

            return (new String ( baos.toByteArray() ) ).trim();
        }
        catch ( IOException exception )
        {
            _log.error ( "Encountered IOException when retrieving APJC response: " + exception.getMessage(), exception );

            return null;
        }
        catch ( Exception exception )
        {
            _log.error ( "Encountered Exception when retrieving APJC response: " + exception.getMessage(), exception );

            return null;
        }
        /*
        catch ( InterruptedException exception )
        {
            _log.error ( "Encountered InterruptedException when retrieving APJC response" );

            return null;
        }
        //*/
    }

    private static String retrieveAPJCFirstName ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<first_name>"  ) + APJC_XMLNODE_FIRST_NAME_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</first_name>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCFirstName ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }

    private static String retrieveAPJCLastName ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<last_name>"  ) + APJC_XMLNODE_LAST_NAME_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</last_name>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCLastName ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }

    private static String retrieveAPJCWeblogin ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<weblogin>"  ) + APJC_XMLNODE_FIRST_NAME_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</weblogin>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCWeblogin ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }
    private static String retrieveAPJCRequestText ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<request_text>"  ) + APJC_XMLNODE_REQUEST_TEXT_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</request_text>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCRequestText ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }

    private static String retrieveAPJCEventID ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<event_id>"  ) + APJC_XMLNODE_EVENT_ID_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</event_id>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCEventID ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }

    private static String retrieveAPJCIncidentID ( String apjc_response )
    {
        try
        {
            int index_start = apjc_response.indexOf ( "<incident_id>"  ) + APJC_XMLNODE_INCIDENT_ID_STRLENGTH;
            int index_end   = apjc_response.indexOf ( "</incident_id>" );

            return apjc_response.substring ( index_start, index_end );
        }
        catch ( StringIndexOutOfBoundsException exception )
        {
            StringBuilder message = new StringBuilder();
                message.append ( ApDrainConsumerBean.class.getName() );
                message.append ( "::retrieveAPJCIncidentID ( APJCResponse=" );
                message.append ( apjc_response );
                message.append ( " ) - error: " );
                message.append ( exception.getMessage() );
            _log.info ( message.toString(), exception );

            return null;
        }
    }

    private void recoverFailedAlerts ( int delay_seconds )
    {
        List list  = _orm.getAllAlarmpointEvents();
        Iterator i = list.iterator();
        int num_records = 0;

        Calendar timestamp = Calendar.getInstance();
        long current_timestamp = timestamp.getTime().getTime();
        timestamp.add ( Calendar.SECOND, -delay_seconds );

        if ( _log.isInfoEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::recoverFailedAlerts ( DelaySeconds=" );
                message.append ( delay_seconds );
                message.append ( " ) - current timestamp=" );
                message.append ( current_timestamp );
                message.append ( ", compare timestamp=" );
                message.append ( timestamp.getTime().getTime() );
            _log.info ( message.toString() );
        }

        while ( i.hasNext() )
        {
            num_records++;

            Alarmpoint ap = (Alarmpoint) i.next();

            if ( timestamp.before ( ap.getTimestamp() ) )
            {
                if ( _log.isInfoEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::recoverFailedAlerts ( DelaySeconds=" );
                        message.append ( delay_seconds );
                        message.append ( " ) - current timestamp=" );
                        message.append ( current_timestamp );
                        message.append ( ", skipping Alarmpoint=" );
                        message.append ( ap.toString() );
                    _log.info ( message.toString() );
                }

                continue;
            }

            String alarmpoint_device = ap.getAlarmpointDevice();
            if (( null == alarmpoint_device ) || ( alarmpoint_device.trim().length() < 4 ))
            {
                alarmpoint_device = "unknown device";
            }

            Notification notification = null;
            DataMapRule rule           = null;
            EventsByGroup event = _orm.selectEventsByGroupByMessageId ( ap.getMessageId() );
            if ( null == event )
            {
                Responder responder = _orm.findResponderByServiceCenterTicketNumber ( ap.getMessageId() );
                if ( null != responder )
                {
                    event = _orm.selectEventsByGroupByMessageId ( responder.getMessageId() );
                }
            }

            Responder responder = null;
            if ( null != event )
            {
                rule    = _orm.selectDataMapRule ( event.getGroupName() );
                if ( null == rule )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::recoverFailedAlerts() - could not find data map rule for group name '" );
                        message.append ( event.getGroupName() );
                        message.append ( "'" );
                    _log.error ( message.toString() );
                    continue;
                }

                if ( _log.isInfoEnabled() )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::recoverFailedAlerts() - recovering failed Alarmpoint alert for OpenviewEvent ( MessageID=" );
                        message.append ( event.getMessageId() );
                        message.append ( " )" );
                    _log.info ( message.toString() );
                }

                String responder_id = event.getResponderId();
                if ( null != responder_id )
                {
                    responder = _orm.selectResponder ( event.getResponderId() );
                }
                if ( null == responder )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::recoverFailedAlerts() - no responder found for OpenviewEvent ( MessageID=" );
                        message.append ( event.getMessageId() );
                        message.append ( " ) - deleting notification" );
                    _log.error ( message.toString() );

                    _orm.deleteAlarmpoint ( ap );

                    continue;
                }

                if (( null != responder.getServiceCenterTicketNumber() ) &&
                    ( responder.getServiceCenterTicketNumber().equals ( "alarmpoint_only" )))
                {
                    //ap.setMessageId ( event.getMessageId() );
                    ap.setMessageId ( "" );
                    //notification = new Notification ( ap.getAlarmpointNotificationTarget(), alarmpoint_device, ap.getAlarmpointMessage(), event.getMessageId(), rule.getAlarmpointScript(), event.getSeverity() );
                    notification = new Notification ( ap.getAlarmpointNotificationTarget(), alarmpoint_device, ap.getAlarmpointMessage(), "", rule.getAlarmpointScript(), event.getSeverity() );

                    _orm.addOrUpdateAlarmpoint ( ap );
                }
                else if (( null  != responder.getServiceCenterTicketNumber() ) &&
                         ( false == responder.getServiceCenterTicketNumber().equals ( "IM-Default" ) ))
                {
                    notification = new Notification ( ap.getAlarmpointNotificationTarget(), alarmpoint_device, ap.getAlarmpointMessage(), responder.getServiceCenterTicketNumber(), rule.getAlarmpointScript(), event.getSeverity() );
                    ap.setMessageId( responder.getServiceCenterTicketNumber() );

                    _orm.addOrUpdateAlarmpoint ( ap );
                }
                else if ( null != responder.getSCAsyncRequestReceipt() )
                {
                    String receipt_id = responder.getSCAsyncRequestReceipt();
                    AbstractFinder receiptFinder = Configuration.getSingleton().getFinder();
                    ICreateIncidentRequest createIncidentRequest = receiptFinder.selectCreateIncidentRequest ( receipt_id );
                    if (( null != createIncidentRequest ) && ( null != createIncidentRequest.getIncidentID() ))
                    {
                        responder.setServiceCenterTicketNumber ( createIncidentRequest.getIncidentID() );
                        _orm.addOrUpdateRow ( responder );

                        StringBuilder update_message = new StringBuilder();
                        update_message.append ( "This ticket is for hostname '" );
                        update_message.append ( event.getSourceNode() );
                        update_message.append ( "'" );

                        IncidentServiceMediator svc = new IncidentServiceMediator();
                        UpdateIncidentRequest _soaReq = new UpdateIncidentRequest();
                		    _soaReq.setIncidentid  ( createIncidentRequest.getIncidentID() );
                		    _soaReq.setSynchronous ( false );
                		    _soaReq.setJournalupdates ( update_message.toString() );
                		UpdateIncidentResponse updateTikResponse = svc.updateIncident(_soaReq);
                    }

                    String ap_notification_target  = ( null != ap )? ap.getAlarmpointNotificationTarget() : null;
                    String ap_notification_message = ( null != ap )? ap.getAlarmpointMessage() : null;
                    String create_incident_id      = ( null != createIncidentRequest )? createIncidentRequest.getIncidentID() : null;
                    String alarmpoint_script       = ( null != rule )? rule.getAlarmpointScript() : null;
                    String event_severity          = ( null != event )? event.getSeverity() : null;

                    notification = new Notification ( ap_notification_target,
                        alarmpoint_device,
                        ap_notification_message,
                        create_incident_id,
                        alarmpoint_script,
                        event_severity );
                }
            }

            if (( null == notification ) && ( null != ap ) && ( null != event ))
            {
                 notification = new Notification ( ap.getAlarmpointNotificationTarget(),
                     alarmpoint_device,
                     ap.getAlarmpointMessage(),
                     //ap.getMessageId(),
                     "",
                     rule.getAlarmpointScript(),
                     event.getSeverity() ); 
            }

            if ( null != notification ) 
            {
                try
                {
                    if ( _log.isInfoEnabled() )
                    {
                        StringBuilder message = new StringBuilder();
                            message.append ( getClass().getName() );
                            message.append ( "::recoverFailedAlerts() - attempting to send notification " );
                            message.append ( notification.toString() );
                            message.append ( ", Alarmpoint=" );
                            message.append ( ( null != ap )? ap.toString() : "null" );
                            message.append ( ", Timestamp=" );
                            message.append ( ( null != timestamp )? sdf.format ( timestamp.getTime() ) : "null" );
                            message.append ( ", AP-Timestamp=" );
                            message.append ( ( null != timestamp )? sdf.format ( ap.getTimestamp().getTime() ) : "null" );
                        _log.info ( message.toString() );
                    }

                    //if ( null != notification.getMessageId() )
                    if (( null != responder ) && ( null != responder.getMessageId() ))
                    {
                        //logMessage ( notification.getMessageId(), "Send alert to Alarmpoint - #1" );
                        logMessage ( responder.getMessageId(), "Send alert to Alarmpoint - #1" );
                    }

                    if ( notification.sendAlert() == true )
                    {
                        logMessage ( notification.getMessageId(), "Alarmpoint received alert" );
                        if ( _log.isInfoEnabled() )
                        {
                            StringBuilder message = new StringBuilder();
                                message.append ( getClass().getName() );
                                message.append ( "::recoverFailedAlerts() - notification " );
                                message.append ( notification.toString() );
                                message.append ( " sent at " );
                                message.append ( sdf.format ( new Date() ) );
                            _log.info ( message.toString() );
                        }
                        //  The notification was successful, so we can remove it from the buffered queue
                        _orm.deleteAlarmpoint ( ap );
                    }
                }
                catch ( RuntimeException exception )
                {
                    StringBuilder message = new StringBuilder();
                        message.append ( getClass().getName() );
                        message.append ( "::recoverFailedAlerts() - Could not connect to the Alarmpoint Java Client: " );
                        message.append ( exception.getMessage() );
                    _log.error ( message.toString(), exception );
                }
            }
        }

        LogToFile ( externalLogFileName, "Number of AP events in queue: " + num_records );
    }

	/**
	 * Handle to ORM dispatcher
	 */
	private final OrmFacade _orm = new OrmFacade();

	/**
	 * Define logger
	 */
	private static Logger _log;
}
