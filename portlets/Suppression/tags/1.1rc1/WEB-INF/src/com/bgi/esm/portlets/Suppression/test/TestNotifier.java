package com.bgi.esm.portlets.Suppression.test;

import java.util.Calendar;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.portlets.Suppression.Notifier;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.orm.ExpirationNotification;
import com.bgi.esm.portlets.Suppression.orm.HibernateFacade;
import com.bgi.esm.portlets.Suppression.orm.SuppressionRecord;

public class TestNotifier extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestNotifier.class );

    public TestNotifier ( String param )
    {
        super ( param );
    }

    public void testStartNotifier() throws Exception
    {
        _log.info ( "**************************************** TestNotifier::testStartNotifier()" );

        Thread testThread = new Thread ( new Notifier() );
        testThread.start();

        Thread.sleep ( 75000 );

        testThread.stop();
    }

    public void testEmailNotification() throws Exception
    {
        _log.info ( "**************************************** TestNotifier::testEmailNotification()" );

        HibernateFacade hibernateFacade = Toolkit.getHibernateFacade();

        //  Create a notification that is due in 45 seconds
        Calendar startTimeStamp   = Calendar.getInstance();
        Calendar notificationTime = Calendar.getInstance();
        notificationTime.add ( Calendar.SECOND, 45 );

        _log.info ( "Test case start: " + HibernateFacade.sdf.format ( startTimeStamp.getTime() ) );
        _log.info ( "Test case notfication time: " + HibernateFacade.sdf.format ( notificationTime.getTime() ) );

        int SuppressionID = (int) (System.currentTimeMillis() / 60000);
        ExpirationNotification notification = new ExpirationNotification();
        notification.setNotificationEmails ( "linden@barclaysglobal.com,dennis.s.lin@barclaysglobal.com" );
        notification.setSuppressionID      ( SuppressionID    );
        notification.setNotificationTime   ( notificationTime );
        hibernateFacade.insertOrUpdateNotification ( notification );

        SuppressionRecord suppressionRecord = new SuppressionRecord();
        suppressionRecord.setSuppressionID ( SuppressionID );
        suppressionRecord.setNodeName      ( "test_node" );
        suppressionRecord.setGroupName     ( "test_group" );
        suppressionRecord.setStartTime     ( Calendar.getInstance() );
        suppressionRecord.setEndTime       ( Calendar.getInstance() );
        suppressionRecord.setDescription   ( "test description" );
        suppressionRecord.setCreatorName   ( "test-user-" + SuppressionID );
        suppressionRecord.setMessage       ( "test-message" );
        hibernateFacade.insertOrUpdateSuppressionRecord ( suppressionRecord );

        _log.info ( "Notification ID: " + notification.getRowID() );


        //  Start the notifier and give it enough time to fire
        Thread testThread = new Thread ( new Notifier() );
        testThread.start();
        System.out.println ( "Thread started at " + System.currentTimeMillis() );
        Thread.currentThread().sleep ( 120000 );

        ExpirationNotification testNotification = hibernateFacade.selectNotification ( notification.getRowID() );
        assertNotNull ( "Could not find test notification #" + notification.getRowID(), testNotification );
        assertNotNull ( "Notification failed", testNotification.getNotificationTimestamp() );

        testThread.stop();
        System.out.println ( "Thread stopped at " + System.currentTimeMillis() );
    }
};
