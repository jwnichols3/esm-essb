package com.bgi.esm.monitoring.portlets.Suppressions;

import java.util.Iterator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class Mailer
{
    final private static Logger _log               = Logger.getLogger ( Mailer.class );
    final private static int TEST_SMTP_PORT        = 55250;

    private static SimpleSmtpServer smtpServer     = null;
    private static Properties mailer_properties    = null;
    private static Properties mailer_properties_us = null;
    private static Properties mailer_properties_uk = null;
    private static Properties mailer_properties_au = null;
    private static Properties mailer_properties_jp = null;

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

    public static void postMail( String recipients[], String subject, String message, String from) throws MessagingException
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

            mailer_properties.put ( "mail.smtp.host", hostname );
            
            if ( null != server_port )
            {
                mailer_properties.put ( "mail.smtp.port", server_port );
            }
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
        mailer_properties_uk = checkProperties ( mailer_properties_uk, hostname, server_port );
    }

    public static void setMailerPropertiesAU ( String hostname )
    {
        mailer_properties_au = checkProperties ( mailer_properties_au, hostname, null );
    }

    public static void setMailerPropertiesJP ( String hostname, String server_port )
    {
        mailer_properties_au = checkProperties ( mailer_properties_au, hostname, server_port );
    }

    public static void setMailerPropertiesJP ( String hostname )
    {
        mailer_properties_au = checkProperties ( mailer_properties_au, hostname, null );
    }
}
