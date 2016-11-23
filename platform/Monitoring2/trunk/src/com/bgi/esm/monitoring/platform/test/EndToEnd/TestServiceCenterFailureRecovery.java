package com.bgi.esm.monitoring.platform.test.EndToEnd;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import org.apache.log4j.Logger;

/**
 *  Collection of tools for an end-to-end test.
 *
 *  @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestServiceCenterFailureRecovery extends TestCaseBaseline
{
    final private static Logger _log = Logger.getLogger ( TestServiceCenterFailureRecovery.class );

    private static String ServiceCenterHostnameProperty = "ServiceCenter.hostname";
    private static String ServiceCenterHostnameValue    = null;
    private static String ServiceCenterHostnameOriginal = null;
    private static String invalid_hostname              = "bogus-service-center-host";

    public TestServiceCenterFailureRecovery ( String param )
    {
        super ( param );

        try
        {
            ServiceCenterHostnameOriginal = bef.selectEebProperty( ServiceCenterHostnameProperty ).getPropertyValue();
        }
        catch ( BackEndFailure exception )
        {
            _log.fatal ( "Could not retrieve original Service Center hostname property" );
        }
    }

    private boolean setInvalidServiceCenterHostname()
    {
        try
        {
            EebProperty e = new EebProperty ( ServiceCenterHostnameProperty );
            e.setPropertyValue ( invalid_hostname );

            EebProperty updated = bef.addOrUpdateEebProperty ( e );

            return (( null != updated ) && ( updated.getPropertyValue().equals ( invalid_hostname )));
        }
        catch ( BackEndFailure exception )
        {
            _log.fatal ( "Could not set Service Center hostname to invalid value" );

            return false;
        }
    }

    private boolean restoreServiceCenterHostname()
    {
        try
        {
            EebProperty e = new EebProperty ( ServiceCenterHostnameProperty );
            e.setPropertyValue ( ServiceCenterHostnameOriginal );

            EebProperty updated = bef.addOrUpdateEebProperty ( e );

            return (( null != updated ) && ( updated.getPropertyValue().equals ( ServiceCenterHostnameOriginal )));
        }
        catch ( BackEndFailure exception )
        {
            _log.fatal ( "Could not set Service Center hostname to original value" );

            return false;
        }
    }

    public void testSetInvalidServiceCenterHostname() throws BackEndFailure
    {
        _log.info ( "**************************************** testSetInvalidServiceCenterHostname()" );

        _log.info ( "Beginning Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        assertTrue ( "Could not set Service Center hostname to invalid value", setInvalidServiceCenterHostname() );
        assertEquals ( "Could not set Service Center hostname to invalid value", invalid_hostname, 
                bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        _log.info ( "Current Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        restoreServiceCenterHostname();
    }

    public void testRestoreServiceCenterHostname() throws BackEndFailure
    {
        _log.info ( "**************************************** testRestoreServiceCenterHostname()" );

        _log.info ( "Beginning Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        assertTrue ( "Could not restore Service Center hostname to valid value", restoreServiceCenterHostname() );
        assertEquals ( "Could not set Service Center hostname to invalid value", ServiceCenterHostnameOriginal, 
                bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        _log.info ( "Current Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        restoreServiceCenterHostname();
    }

    public void testServiceCenterFailureRecovery() throws BackEndFailure, InterruptedException, JMSException
    {
        _log.info ( "**************************************** testServiceCenterFailureRecovery()" );

        //  To simulate Service Center failing, we set the current Service Center hostname
        //  to an invalid hostname
        assertTrue ( "Could not set Service Center hostname to invalid value", setInvalidServiceCenterHostname() );
        _log.info ( "Current Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        //  Wait a little bit to give the EEB time for processing
        Thread.currentThread().sleep ( 15000 );

        //  Create and send a sample test message
        Calendar start_time   = Calendar.getInstance();
        long timestamp        = start_time.getTime().getTime();
        String ovo_message_id = "test-message-" + timestamp;
        String message_string = createOriginalOVOTestMessage ( start_time, ovo_message_id );

        TextMessage message   = _topicSession.createTextMessage();
        message.setText ( message_string );
        _topicPublisher.publish ( message );

        //  Wait a little bit to give the EEB time for processing
        Thread.currentThread().sleep ( 30000 );

        //  Make sure that the Service Center ticket is waiting to be created
        List audit_list1      = bef.getAllAuditForMessageId ( ovo_message_id );
        dumpAuditList ( "First audit list", audit_list1 );

        assertNotNull ( "Could not retrieve audit list for sending messages", audit_list1 );
        assertTrue    ( "Empty/incomplete audit list retrieved", audit_list1.size() > 10 ); 

        //  Not we restore the Service Center hostname and ask the EEB to process
        assertTrue ( "Could not restore Service Center hostname to original value", restoreServiceCenterHostname() );
        _log.info ( "Current Service Center hostname: " + bef.selectEebProperty ( ServiceCenterHostnameProperty ).getPropertyValue() );

        startSCDrain();

        //  Wait a little bit to give the EEB time for processing
        Thread.currentThread().sleep ( 10000 );

        List audit_list2      = bef.getAllAuditForMessageId ( ovo_message_id );
        dumpAuditList ( "Second audit list", audit_list2 );

        assertNotNull ( "Could not retrieve audit list after restoring Service Center", audit_list2 );
        assertTrue    ( "Empty/incomplete audit list retrieved", audit_list2.size() > 10 ); 

        assertTrue ( "Service Center recovery is not working", audit_list2.size() > audit_list1.size() );

        restoreServiceCenterHostname();
    }
}
