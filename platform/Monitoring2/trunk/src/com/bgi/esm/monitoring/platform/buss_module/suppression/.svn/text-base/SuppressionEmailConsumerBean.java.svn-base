package com.bgi.esm.monitoring.platform.buss_module.suppression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Vector;
import javax.ejb.MessageDrivenBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.buss_module.AbstractBussModule;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * @ejb.bean 
 *   name="SuppressionEmailConsumerEjb" 
 *   type="MDB"
 *   acknowledge-mode="Auto-acknowledge"
 *   destination-type="javax.jms.Queue"
 *   subscription-durability="NonDurable" 
 *   description="dispatcher queue consumer"
 * 
 * @weblogic.message-driven destination-jndi-name="replatform.queue.suppression_email"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SuppressionEmailConsumerBean extends AbstractBussModule implements MessageDrivenBean, MessageListener {

    final private static String externalLogFileName = "c:\\test\\JMS-Suppression-Email.out";

	/**
	 * ctor
	 */
	public SuppressionEmailConsumerBean () {
		super(BussModule.SUPPRESSION_EMAIL);

		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("SuppressionEmailConsumerBean ctor failure");
            _log = Logger.getLogger ( SuppressionEmailConsumerBean.class );
            _log.error ( "SuppressionEmailConsumerBean ctor failure", exception );
		}
		
		_log.debug("suppression_email consumer ctor");
	}

	/**
	 * Invoked when messages arrive via queue
	 * 
	 * @param arg fresh message
	 */
	public void onMessage(javax.jms.Message arg) {
        long message_timestamp = System.currentTimeMillis();

        logEntryMessage  ( Long.toString ( message_timestamp ) );

        if ( _log.isDebugEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( getClass().getName() );
                message.append ( "::onMessage() - fresh message noted" );
            _log.debug ( message.toString() );
        }

        _orm.selectEebProperty ( "mail.server.us" );
        _orm.selectEebProperty ( "mail.server.uk" );
        _orm.selectEebProperty ( "mail.server.au" );
        _orm.selectEebProperty ( "mail.server.jp" );

        //  Get the current time stamp
        Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long timestamp_ms  = timestamp.getTime().getTime();

        //  Find all active suppressions
        List <SuppressionRule> suppressionNotifications = _orm.getAllSuppressionNotifications();

        TimeZone tz_server = TimeZone.getTimeZone ( _orm.selectEebProperty( "server.time_zone" ).getPropertyValue() );

        for ( int counter = 0; counter < suppressionNotifications.size(); counter++ )
        {
            SuppressionRule suppressionRecord = suppressionNotifications.get ( counter );

            if ( _log.isInfoEnabled() )
            {
                StringBuilder test_message = new StringBuilder();
                    test_message.append ( getClass().getName() );
                    test_message.append ( "::onMessage() - testing supperssion #" );
                    test_message.append ( suppressionRecord.getSuppressId() );
                    test_message.append ( ", Timestamp=" );
                    test_message.append ( sdf.format ( timestamp.getTime() ) );
                    test_message.append ( ", EndTime=" );
                    test_message.append ( sdf.format ( suppressionRecord.getNotificationTime().getTime() ) );
                _log.info ( test_message.toString() );
            }

            if ( suppressionRecord.getEndTime().before ( timestamp ) )
            {
                StringBuilder expired = new StringBuilder();
                    expired.append ( "Suppression #" );
                    expired.append ( suppressionRecord.getSuppressId() );
                    expired.append ( " has expired on " );
                    expired.append ( sdf.format ( suppressionRecord.getEndTime().getTime() ) );
                    expired.append ( ", therefore no notification will be given." );
                _log.debug ( expired.toString() );

                suppressionRecord.setIsNotified ( true );
                _orm.addOrUpdateSuppressionRule ( suppressionRecord );

                continue;
            }

            try
            {
                //  Construct the email
                String recipients[] = suppressionRecord.getNotifyEmail().split ( "," );

                //String recipients[] = new String[] { "linden@barclaysglobal.com" };

                int offset_SFO         = tz_SFO.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar startTime_SFO = Calendar.getInstance();
                startTime_SFO.setTime ( suppressionRecord.getStartTime().getTime() );
                startTime_SFO.add     ( Calendar.MILLISECOND, offset_SFO );

                int offset_LDN         = tz_LDN.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar startTime_LDN = Calendar.getInstance();
                startTime_LDN.setTime ( suppressionRecord.getStartTime().getTime() );
                startTime_LDN.add     ( Calendar.MILLISECOND, offset_LDN );

                int offset_TKY         = tz_TKY.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar startTime_TKY = Calendar.getInstance();
                startTime_TKY.setTime ( suppressionRecord.getStartTime().getTime() );
                startTime_TKY.add     ( Calendar.MILLISECOND, offset_TKY );

                int offset_SYD         = tz_SYD.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar startTime_SYD = Calendar.getInstance();
                startTime_SYD.setTime ( suppressionRecord.getStartTime().getTime() );
                startTime_SYD.add     ( Calendar.MILLISECOND, offset_SYD );

                offset_SFO         = tz_SFO.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar endTime_SFO = Calendar.getInstance();
                endTime_SFO.setTime ( suppressionRecord.getEndTime().getTime() );
                endTime_SFO.add     ( Calendar.MILLISECOND, offset_SFO );

                offset_LDN         = tz_LDN.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar endTime_LDN = Calendar.getInstance();
                endTime_LDN.setTime ( suppressionRecord.getEndTime().getTime() );
                endTime_LDN.add     ( Calendar.MILLISECOND, offset_LDN );

                offset_TKY         = tz_TKY.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar endTime_TKY = Calendar.getInstance();
                endTime_TKY.setTime ( suppressionRecord.getEndTime().getTime() );
                endTime_TKY.add     ( Calendar.MILLISECOND, offset_TKY );

                offset_SYD         = tz_SYD.getOffset ( timestamp_ms ) - tz_server.getOffset ( timestamp_ms );
                Calendar endTime_SYD = Calendar.getInstance();
                endTime_SYD.setTime ( suppressionRecord.getEndTime().getTime() );
                endTime_SYD.add     ( Calendar.MILLISECOND, offset_SYD );


                StringBuilder emailMessage = new StringBuilder();
                emailMessage.append ( "This is a friendly notification to let you know that Suppression #" );
                emailMessage.append ( suppressionRecord.getSuppressId() );
                emailMessage.append ( " is about to expire.\n\n" );
                emailMessage.append ( "Suppression Details:" );
                emailMessage.append ( "\n    - Application:       "  );
                if ( null != suppressionRecord.getApplicationName()  ) emailMessage.append ( suppressionRecord.getApplicationName() );
                emailMessage.append ( "\n    - Node:              "  );  
                if ( null != suppressionRecord.getNodeName()         ) emailMessage.append ( suppressionRecord.getNodeName() );
                emailMessage.append ( "\n    - Description:       "  );
                if ( null != suppressionRecord.getDescription()      ) emailMessage.append ( suppressionRecord.getDescription() );
                emailMessage.append ( "\n    - Database Instance: "  );
                if ( null != suppressionRecord.getDatabaseServerName() ) emailMessage.append ( suppressionRecord.getDatabaseServerName() );
                emailMessage.append ( "\n    - Database Message:  "  );
                if ( null != suppressionRecord.getMessage()          ) emailMessage.append ( suppressionRecord.getMessage() );
                emailMessage.append ( "\n    - Start Time:" );
                emailMessage.append ( "\n        SFO: " );
                emailMessage.append ( sdf.format ( startTime_SFO.getTime() ) );
                emailMessage.append ( "\n        LDN: " );
                emailMessage.append ( sdf.format ( startTime_LDN.getTime() ) );
                emailMessage.append ( "\n        TKY: " );
                emailMessage.append ( sdf.format ( startTime_TKY.getTime() ) );
                emailMessage.append ( "\n        SYD: " );
                emailMessage.append ( sdf.format ( startTime_SYD.getTime() ) );
                emailMessage.append ( "\n    - End Time:" );
                emailMessage.append ( "\n        SFO: " );
                emailMessage.append ( sdf.format ( endTime_SFO.getTime() ) );
                emailMessage.append ( "\n        LDN: " );
                emailMessage.append ( sdf.format ( endTime_LDN.getTime() ) );
                emailMessage.append ( "\n        TKY: " );
                emailMessage.append ( sdf.format ( endTime_TKY.getTime() ) );
                emailMessage.append ( "\n        SYD: " );
                emailMessage.append ( sdf.format ( endTime_SYD.getTime() ) );
                /*
                emailMessage.append ( "\n    - Start Time:        " );
                emailMessage.append ( sdf.format ( suppressionRecord.getStartTime().getTime() ) );
                emailMessage.append ( "\n    - End Time:          " );
                emailMessage.append ( sdf.format ( suppressionRecord.getEndTime().getTime() ) );
                //*/
                emailMessage.append ( "\n\nESM Team" );

                StringBuilder emailSubject = new StringBuilder();
                emailSubject.append ( "BGI IT ESM - Suppression #" );
                emailSubject.append ( suppressionRecord.getSuppressId() );
                emailSubject.append ( " is will expire in " );
                emailSubject.append ( suppressionRecord.getNotifyMinutes() );
                emailSubject.append ( " minutes" );
        
                //  Fire off the email
                postMail ( recipients, emailSubject.toString(), emailMessage.toString(), "bgiitesm@barclaysglobal.com" );
        
                //  Save the notification time
                Calendar notificationTimestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                suppressionRecord.setIsNotified ( true );
                _orm.addOrUpdateSuppressionRule ( suppressionRecord );
            }
            catch ( Exception exception )
            {
                StringBuilder errorMessage = new StringBuilder();
                    errorMessage.append ( "Could not process email notification for Suppression #" );
                    errorMessage.append ( suppressionRecord.getSuppressId() );
                    errorMessage.append ( " at " );
                    errorMessage.append ( sdf.format ( new java.util.Date() ) );
                    errorMessage.append ( " - " );
                    errorMessage.append ( exception.getMessage() );
                _log.error ( errorMessage.toString(), exception );
            }
        }

        Calendar end_timestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        logExitMessage  ( Long.toString ( message_timestamp ) );
	}

    public void postMail( String recipients[], String subject, String message, String from)
    {
        try
        {
            boolean debug = false;

            //Set the host smtp address
            String mail_host = _orm.selectEebProperty ( "mail.server.smtp.host" ).getPropertyValue();
            String mail_port = _orm.selectEebProperty ( "mail.server.smtp.port" ).getPropertyValue();

            if ( _log.isDebugEnabled() )
            {
                StringBuilder mail_server = new StringBuilder();
                    mail_server.append ( getClass().getName() );
                    mail_server.append ( "::postMail() - Mail Server: ( " );
                    mail_server.append ( mail_host );
                    mail_server.append ( ", " );
                    mail_server.append ( mail_port );
                    mail_server.append ( " )" );
                _log.debug ( mail_server.toString() );
            }

            Properties props = new Properties();
            props.put ( "mail.transport.protocol", "smtp" );
            props.put ( "mail.debug", "true"   );
            props.put ( "mail.smtp.host", mail_host );
            props.put ( "mail.smtp.port", mail_port );
            props.put ( "mail.host", mail_host );
            props.put ( "mail.port", mail_port );

            // create some properties and get the default Session
            //Session session = Session.getDefaultInstance(props, null);
            Session session = Session.getInstance(props, null);
            session.setDebug(debug);

            // create a message
            javax.mail.Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            Vector <String> validRecipients = new Vector <String> ();
            for (int i = 0; i < recipients.length; i++)
            {
                String filteredRecipient = recipients[i].replaceAll ( "'", "" ).trim();
                _log.info ( "Processing recipient: " + filteredRecipient );

                if ( filteredRecipient.length() > 0 )
                {
                    validRecipients.add ( filteredRecipient );
                    //AddressTo[i] = new InternetAddress ( filteredRecipient );
                }
            }

            if ( validRecipients.size() == 0 )
            {
                _log.error ( "No valid recipients found" );

                return;
            }

            InternetAddress[] addressTo = new InternetAddress[validRecipients.size()];
            for ( int counter = 0; counter < validRecipients.size(); counter++ )
            {
                addressTo[counter] = new InternetAddress ( validRecipients.get ( counter ) );
            }

            msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);

            // Optional : You can also set your custom headers in the Email if you Want
            //msg.addHeader("MyHeaderName", "myHeaderValue");

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
        }
        catch ( MessagingException exception )
        {
            _log.error ( "Could not process email notification: " + exception.getMessage(), exception );
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

    private static TimeZone tz_SFO = TimeZone.getTimeZone ( "America/Los_Angeles" );
    private static TimeZone tz_LDN = TimeZone.getTimeZone ( "Europe/London" );
    private static TimeZone tz_TKY = TimeZone.getTimeZone ( "Asia/Tokyo" );
    private static TimeZone tz_SYD = TimeZone.getTimeZone ( "Australia/Sydney" );
}
/**
mail.store.protocol  	    Protocol for retrieving email. Example: mail.store.protocol=imap  	imap
mail.transport.protocol 	Protocol for sending email. Example: mail.transport.protocol=smtp 	smtp
mail.host 	                The name of the mail host machine. Example: mail.host=mailserver 	Local machine
mail.user 	                Name of the default user for retrieving email. 
                            Example: mail.user=postmaster 	
                            (Default: Value of the user.name Java system property. )
mail.protocol.host 	        Mail host for a specific protocol. For example, you can set 
                            mail.SMTP.host and mail.IMAP.host to different machine names. 
                            Examples: mail.smtp.host=mail.mydom.com mail.imap.host=localhost 	
                            (Default: Value of the mail.host property.)
mail.protocol.user 	        Protocol-specific default user name for logging into a mailer server. 
                            Examples: mail.smtp.user=weblogic mail.imap.user=appuser 	
                            (Default: Value of the mail.user property.)
mail.from 	                The default return address. Examples: mail.from=master@mydom.com 	username@host
mail.debug 	                Set to True to enable JavaMail debug output. 	                    False
 *
 */ 
