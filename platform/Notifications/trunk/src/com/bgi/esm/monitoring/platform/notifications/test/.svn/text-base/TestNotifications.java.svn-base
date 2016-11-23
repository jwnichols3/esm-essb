package com.bgi.esm.monitoring.platform.notifications.test;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.notifications.NotificationBean;

public class TestNotifications extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestNotifications.class );

    public TestNotifications ( String param )
    {
        super ( param );
    }

    public void testSelectAllNotifications()
    {
        _log.info ( "**************************************** testSelectAllNotifications()" );

        List <NotificationBean> notifications = NotificationBean.getAllNotifications();

        assertNotNull ( notifications );

        Iterator <NotificationBean> i = notifications.iterator();
        int num_records = 0;

        while ( i.hasNext() )
        {
            num_records++;

            i.next();
        }

        _log.info ( "Number of notifications in database: " + num_records );
    }

    public void testBadSuppressionID()
    {
        _log.info ( "**************************************** testBadSuppressionID()" );

        NotificationBean nb = new NotificationBean();

        try
        {
            nb.setNotificationTarget ( "random" );

            nb.execute();

            fail ( "Notification went through despite having bad Suppression ID" );
        }
        catch ( IllegalArgumentException exception )
        {
            //  test passed
        }
    }

    public void testBadNotificationTarget()
    {
        _log.info ( "**************************************** testBadNotificationTarget()" );

        NotificationBean nb = new NotificationBean();

        try
        {
            nb.setSuppressionID ( System.currentTimeMillis() );

            nb.execute();

            fail ( "Notification went through despite having bad notification target" );
        }
        catch ( IllegalArgumentException exception )
        {
            //  test passed
        }
    }

    public void testBadNotificationTimestamp()
    {
        _log.info ( "**************************************** testBadNotificationTimestamp()" );

        NotificationBean nb = new NotificationBean();

        try
        {
            nb.setSuppressionID         ( System.currentTimeMillis() );
            nb.setNotificationTarget    ( "random" );
            nb.setNotificationTimestamp ( null );

            nb.execute();

            fail ( "Notification went through despite having bad notification timestamp" );
        }
        catch ( IllegalArgumentException exception )
        {
            //  test passed
        }
    }

    public void testAddNotification()
    {
        _log.info ( "**************************************** testAddNotification()" );

        NotificationBean nb = new NotificationBean();

        nb.setNotificationTarget ( "linden" );
        nb.setSuppressionID ( System.currentTimeMillis() );
        nb.execute();
    }
};
