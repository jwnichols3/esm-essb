package com.bgi.esm.portlets.Suppression.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.portlets.Suppression.Mailer;

public class TestMailer extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestMailer.class );

    final private static String MAILSERVER_US = "calnte2k044.insidelive.net";
    final private static String MAILSERVER_UK = "ldnnte2k030.insidelive.net";
    final private static String MAILSERVER_AU = "sydnte2k032.insidelive.net";
    final private static String MAILSERVER_JP = "tokyomailserver01.insidelive.net";


    public TestMailer ( String param )
    {
        super ( param );
    }

    private int sendTestMessage() throws Exception
    {
        return Mailer.postMail ( new String[] { "linden@barclaysglobal.com" }, "Test Email", "Test message 1\r\nTest message 2", "bgiitesm@barclaysglobal.com" );
    }

    public void testMailer()
    {
        _log.info ( "**************************************** TestMailer::testMailer()" );

        Mailer.startDefaultServer();

        assertNotNull ( "Could not start test SMTP Server", Mailer.getSmtpServer() );

        Mailer.stopDefaultServer();
    }

    public void testSendEmailFromUS() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendEmailFromUS()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUK ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesAU ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesJP ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from US", Mailer.SUCCESS_CODE_US == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendEmailFromUK_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendEmailFromUK_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesAU ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesJP ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from UK: " + status_code , Mailer.SUCCESS_CODE_UK == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendEmailFromAU_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendEmailFromAU_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesJP ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from Australia: " + status_code, Mailer.SUCCESS_CODE_AU == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendEmailFromJP_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendEmailFromJP_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesJP ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from Japan: " + status_code , Mailer.SUCCESS_CODE_JP == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendEmail_DefaultFailover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendEmail_DefaultFailover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesJP ( "bogus_server", "12345" );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from default server: " + status_code, Mailer.SUCCESS_CODE_DEFAULT == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendRealEmailFromUS() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendRealEmailFromUS()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( MAILSERVER_US, null );
        Mailer.setMailerPropertiesUK ( MAILSERVER_UK, null );
        Mailer.setMailerPropertiesAU ( MAILSERVER_AU, null );
        Mailer.setMailerPropertiesJP ( MAILSERVER_JP, null );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from US", Mailer.SUCCESS_CODE_US == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendRealEmailFromUK_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendRealEmailFromUK_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( MAILSERVER_UK, null );
        Mailer.setMailerPropertiesAU ( MAILSERVER_AU, null );
        Mailer.setMailerPropertiesJP ( MAILSERVER_JP, null );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from UK: " + status_code , Mailer.SUCCESS_CODE_UK == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendRealEmailFromAU_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendRealEmailFromAU_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( MAILSERVER_AU, null );
        Mailer.setMailerPropertiesJP ( MAILSERVER_JP, null );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from Australia: " + status_code, Mailer.SUCCESS_CODE_AU == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendRealEmailFromJP_Failover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendRealEmailFromJP_Failover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesJP ( MAILSERVER_JP, null );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from Japan: " + status_code , Mailer.SUCCESS_CODE_JP == status_code );

        Mailer.stopDefaultServer();
    }

    public void testSendRealEmail_DefaultFailover() throws Exception
    {
        _log.info ( "**************************************** TestMailer::testSendRealEmail_DefaultFailover()" );

        Mailer.startDefaultServer();
        Mailer.initializeMailerProperties ( "localhost", Integer.toString ( Mailer.TEST_SMTP_PORT ) );
        Mailer.setMailerPropertiesUS ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesUK ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesAU ( "bogus_server", "12345" );
        Mailer.setMailerPropertiesJP ( "bogus_server", "12345" );

        int status_code = sendTestMessage();

        if ( _log.isInfoEnabled() )
        {
            if ( Mailer.SUCCESS_CODE_US      == status_code ) _log.info ( "Mailer - Success from US" );
            if ( Mailer.SUCCESS_CODE_UK      == status_code ) _log.info ( "Mailer - Success from UK" );
            if ( Mailer.SUCCESS_CODE_AU      == status_code ) _log.info ( "Mailer - Success from AU" );
            if ( Mailer.SUCCESS_CODE_JP      == status_code ) _log.info ( "Mailer - Success from JP" );
            if ( Mailer.SUCCESS_CODE_DEFAULT == status_code ) _log.info ( "Mailer - Success from Default" );
            if ( Mailer.FAILURE_CODE         == status_code ) _log.info ( "Mailer - Failure" );
        }

        assertTrue ( "Could not send test message from default server: " + status_code, Mailer.SUCCESS_CODE_DEFAULT == status_code );

        Mailer.stopDefaultServer();
    }
};
