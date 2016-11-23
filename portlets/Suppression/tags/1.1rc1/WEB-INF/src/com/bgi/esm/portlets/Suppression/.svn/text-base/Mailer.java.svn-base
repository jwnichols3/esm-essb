package com.bgi.esm.portlets.Suppression;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.bgi.esm.portlets.Suppression.orm.HibernateFacade;

public class Mailer
{
    final private static Logger _log               = Logger.getLogger ( Mailer.class );
    final public static int TEST_SMTP_PORT         = 55250;

    private static SimpleSmtpServer smtpServer     = null;
    private static Properties mailer_properties    = null;
    private static Properties mailer_properties_us = null;
    private static Properties mailer_properties_uk = null;
    private static Properties mailer_properties_au = null;
    private static Properties mailer_properties_jp = null;

    final public static int FAILURE_CODE           = 0;
    final public static int SUCCESS_CODE_US        = 1;
    final public static int SUCCESS_CODE_UK        = 2;
    final public static int SUCCESS_CODE_AU        = 3;
    final public static int SUCCESS_CODE_JP        = 4;
    final public static int SUCCESS_CODE_DEFAULT   = 5;

    public static void main ( String args[] ) throws Exception
    {
        smtpServer = SimpleSmtpServer.start ( TEST_SMTP_PORT );
        for ( int counter = 0; counter < 20; counter++ )
        {
            System.out.println ( "Iteration #" + counter );
    
            System.out.println ( "Test 01-1" );
            postMail ( new String[] { "linden@barclaysglobal.com" }, "Test Email", "Test message 1\r\nTest message 2", "bgiitesm@barclaysglobal.com" );
            System.out.println ( "Test 01-2" );
    
            System.out.println ( "Test 01-3" );
        }

        System.out.println ( "Num email received: " + smtpServer.getReceivedEmailSize() );
        Iterator <SmtpMessage> receivedMessages = smtpServer.getReceivedEmail();
        while ( receivedMessages.hasNext() )
        {
            SmtpMessage message = receivedMessages.next();
            System.out.println ( "Received email: " + message.getBody() );
        }

        smtpServer.stop();
    }

    private static void logPostMailAttempt ( String subject, Date mailTimestamp, String server_location )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> logMessage = new WeakReference <StringBuilder> ( new StringBuilder() );
            logMessage.get().append ( "Mailer::postMail() - attempting to send email from " );
            logMessage.get().append ( server_location );
            logMessage.get().append ( " server ( Subject='" );
            logMessage.get().append ( subject );
            logMessage.get().append ( "', Time=" );
            logMessage.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
            logMessage.get().append ( "' )" );

            _log.info ( logMessage.get().toString() );
        }
    }

    public static int postMail ( String recipients[], String subject, String message, String from ) throws MessagingException
    {
        boolean debug       = false;
        boolean send_result = false;
        Date mailTimestamp  = new Date();

        logPostMailAttempt ( subject, mailTimestamp, "US" );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_us, debug );
        if ( true == send_result ) return SUCCESS_CODE_US;
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
            error_message.get().append ( "Failed to send email from US mail server at " );
            error_message.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
            error_message.get().append ( " -- retrying with UK mail server" );

            _log.warn ( error_message.get().toString() );
        }

        logPostMailAttempt ( subject, mailTimestamp, "UK" );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_us, debug );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_uk, debug );
        if ( true == send_result ) return SUCCESS_CODE_UK;
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
            error_message.get().append ( "Failed to send email from UK mail server at " );
            error_message.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
            error_message.get().append ( " -- retrying with AU mail server" );

            _log.warn ( error_message.get().toString() );
        }

        logPostMailAttempt ( subject, mailTimestamp, "AU" );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_us, debug );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_au, debug );
        if ( true == send_result ) return SUCCESS_CODE_AU;
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
            error_message.get().append ( "Failed to send email from AU mail server at " );
            error_message.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
            error_message.get().append ( " -- retrying with JP mail server" );

            _log.warn ( error_message.get().toString() );
        }

        logPostMailAttempt ( subject, mailTimestamp, "JP" );
        send_result = sendMail ( recipients, subject, message, from, mailer_properties_jp, debug );
        if ( true == send_result ) return SUCCESS_CODE_JP;
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
            error_message.get().append ( "Failed to send email from JP mail server at " );
            error_message.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
            error_message.get().append ( " -- retrying with mail server on localhost" );

            _log.warn ( error_message.get().toString() );
        }

        send_result = sendMail ( recipients, subject, message, from, mailer_properties, debug );
        if ( true == send_result ) return SUCCESS_CODE_DEFAULT;

        //  Email sending has failed.
        WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
        error_message.get().append ( "Failed to send email from all email servers at " );
        error_message.get().append ( HibernateFacade.sdf.format ( mailTimestamp ) );
        error_message.get().append ( " -- retrying with mail server on localhost" );

        _log.error ( error_message.get().toString() );

        return FAILURE_CODE;
    }

    private static boolean sendMail ( String[] recipients, String subject, String message, String from, Properties mailer_props, boolean debug )
    {
        if ( null != mailer_props )
        {
            try
            {
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> logMessage = new WeakReference <StringBuilder> ( new StringBuilder() );
                    logMessage.get().append ( Mailer.class.getName() );
                    logMessage.get().append ( "::sendMail() - attempting to send email with ( host=" );
                    logMessage.get().append ( mailer_props.getProperty ( "mail.smtp.host" ) );
                    logMessage.get().append ( ", port-" );
                    logMessage.get().append ( mailer_props.getProperty ( "mail.smtp.port" ) );
                    logMessage.get().append ( " )" );

                    _log.info ( logMessage.get().toString() );
                }

                Session session = Session.getInstance ( mailer_props, null );
                session.setDebug ( debug );

                // create a message
                Message msg = new MimeMessage(session);

                // set the from and to address
                InternetAddress addressFrom = new InternetAddress(from);
                msg.setFrom(addressFrom);

                InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
                for (int i = 0; i < recipients.length; i++)
                {
                    addressTo[i] = new InternetAddress(recipients[i]);
                }
                msg.setRecipients(Message.RecipientType.TO, addressTo);
   

                // Optional : You can also set your custom headers in the Email if you Want
                msg.addHeader("MyHeaderName", "myHeaderValue");

                // Setting the Subject and Content Type
                msg.setSubject(subject);
                msg.setContent(message, "text/plain");
                Transport.send(msg);

                return true;
            }
            catch ( MessagingException exception )
            {
                WeakReference <StringBuilder> error_message = new WeakReference <StringBuilder> ( new StringBuilder() );
                error_message.get().append ( Mailer.class.getName() );
                error_message.get().append ( "::sendMail() - failed to send with properties ( hostname=" );
                error_message.get().append ( mailer_props.getProperty ( "mail.smtp.host" ) );
                error_message.get().append ( ", port=" );
                error_message.get().append ( mailer_props.getProperty ( "mail.smtp.port" ) );
                error_message.get().append ( " ) - System ( host=" );
                error_message.get().append ( System.getProperty ( "mail.smtp.host" ) );
                error_message.get().append ( ", port=" );
                error_message.get().append ( System.getProperty ( "mail.smtp.port" ) );
                error_message.get().append ( " ) - " );
                error_message.get().append ( exception.getMessage() ); 

                _log.error ( error_message.get().toString() );

                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public static void postMail2 ( String recipients[], String subject, String message, String from ) throws MessagingException
    {
       boolean debug = false;

       //Set the host smtp address
       Properties props = new Properties();
       props.put ( "mail.smtp.host", "localhost");  //  US
       props.put ( "mail.smtp.port", Integer.toString ( TEST_SMTP_PORT ) );
       //props.put("mail.smtp.host", "calnte2k044.insidelive.net");  //  US on port 25
       //props.put("mail.smtp.host", "tokyomailserver01.insidelive.net");  //  Tokyo on port 25
       //props.put("mail.smtp.host", "sydnte2k032.insidelive.net");  //  Sydney on port 25
       //props.put("mail.smtp.host", "ldnnte2k030.insidelive.net");  //  London on port 25

       // create some properties and get the default Session
       Session session = Session.getDefaultInstance(props, null);
       session.setDebug(debug);

       // create a message
       Message msg = new MimeMessage(session);

       // set the from and to address
       InternetAddress addressFrom = new InternetAddress(from);
       msg.setFrom(addressFrom);

       InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
       for (int i = 0; i < recipients.length; i++)
       {
           addressTo[i] = new InternetAddress(recipients[i]);
       }
       msg.setRecipients(Message.RecipientType.TO, addressTo);
   

       // Optional : You can also set your custom headers in the Email if you Want
       msg.addHeader("MyHeaderName", "myHeaderValue");

       // Setting the Subject and Content Type
       msg.setSubject(subject);
       msg.setContent(message, "text/plain");
       Transport.send(msg);
    }

    public static void initializeMailerProperties ( String hostname, String server_port )
    {
        if ( null == mailer_properties )
        {
            mailer_properties = new Properties();
        }

        if ( null != hostname )
        {
            mailer_properties.put ( "mail.smtp.port", hostname );
        }
        else
        {
            mailer_properties.put ( "mail.smtp.port", "localhost" );
        }

        if ( null != server_port )
        {
            mailer_properties.put ( "mail.smtp.port", server_port );
        }
        else
        {
            mailer_properties.put ( "mail.smtp.port", Integer.toString ( TEST_SMTP_PORT ) );
        }
    }

    private static Properties checkProperties ( Properties mailer_props, String hostname, String server_port )
    {
        if ( null == mailer_props )
        {
            mailer_props = new Properties();
        }

        if ( null != hostname )
        {
            mailer_props.put ( "mail.smtp.host", hostname );
        }

        if ( null != server_port )
        {
            mailer_props.put ( "mail.smtp.port", server_port );
        }
        else
        {
            mailer_props.remove ( "mail.smtp.port" );
        }

        return mailer_props;
    }

    public static void setMailerPropertiesUS ( String hostname )
    {
        mailer_properties_us = checkProperties ( mailer_properties_us, hostname, null );
    }

    public static void setMailerPropertiesUS ( String hostname, String server_port )
    {
        mailer_properties_us = checkProperties ( mailer_properties_us, hostname, server_port );
    }

    public static void setMailerPropertiesUK ( String hostname )
    {
        mailer_properties_uk = checkProperties ( mailer_properties_uk, hostname, null );
    }

    public static void setMailerPropertiesUK ( String hostname, String server_port )
    {
        mailer_properties_uk = checkProperties ( mailer_properties_uk, hostname, server_port );
    }

    public static void setMailerPropertiesAU ( String hostname, String server_port )
    {
        mailer_properties_au = checkProperties ( mailer_properties_au, hostname, server_port );
    }

    public static void setMailerPropertiesAU ( String hostname )
    {
        mailer_properties_au = checkProperties ( mailer_properties_au, hostname, null );
    }

    public static void setMailerPropertiesJP ( String hostname, String server_port )
    {
        mailer_properties_jp = checkProperties ( mailer_properties_jp, hostname, server_port );
    }

    public static void setMailerPropertiesJP ( String hostname )
    {
        mailer_properties_jp = checkProperties ( mailer_properties_jp, hostname, null );
    }

    public static void resetMailer()
    {
        _log.warn ( "Clearing all mailer properties" );

        mailer_properties    = null;
        mailer_properties_us = null;
        mailer_properties_uk = null;
        mailer_properties_au = null;
        mailer_properties_jp = null;

        stopDefaultServer();
    }

    public static void startDefaultServer()
    {
        stopDefaultServer();

        smtpServer = SimpleSmtpServer.start ( TEST_SMTP_PORT );
    }

    public static void stopDefaultServer()
    {
        if ( null != smtpServer )
        {
            smtpServer.stop();

            smtpServer = null;
        }
    }

    public static SimpleSmtpServer getSmtpServer()
    {
        return smtpServer;
    }
}
