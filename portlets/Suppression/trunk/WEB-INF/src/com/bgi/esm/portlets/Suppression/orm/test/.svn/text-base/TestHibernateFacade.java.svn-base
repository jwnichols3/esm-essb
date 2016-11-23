package com.bgi.esm.portlets.Suppression.orm.test;

import java.util.Calendar;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.portlets.Suppression.HibernateUtil;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.orm.ExpirationNotification;
import com.bgi.esm.portlets.Suppression.orm.HibernateFacade;
import com.bgi.esm.portlets.Suppression.orm.SuppressionRecord;

public class TestHibernateFacade extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHibernateFacade.class );

    public TestHibernateFacade ( String param )
    {
        super ( param );
    }

    public void testGetHibernateSession()
    {
        _log.info ( "\n\n\n**************************************** testGetHibernateSession()" );
        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not retrieve Hibernate session", session );
    }

    public void testInsertSelectUpdateNotification()
    {
        _log.info ( "\n\n\n**************************************** testInsertSelectUpdateNotification()" );
        int SuppressionID  = (int) (System.currentTimeMillis() / 60000);
        Calendar timestamp = Calendar.getInstance();

        ExpirationNotification testNotification = new ExpirationNotification();
        testNotification.setSuppressionID ( SuppressionID );
        testNotification.setNotificationEmails ( "linden@barclaysglobal.com,dennis.s.lin@barclaysglobal.com" );
        testNotification.setNotificationTime  ( timestamp );

        HibernateFacade hibernateFacade = Toolkit.getHibernateFacade();
        assertFalse ( "Notification already exists", hibernateFacade.insertOrUpdateNotification ( testNotification ) );

        _log.info ( "Notification ID: " + testNotification.getRowID() );

        ExpirationNotification selected = hibernateFacade.selectNotification ( testNotification.getRowID() );
        assertNotNull ( "Could not find notification ID #" + testNotification.getRowID(), selected );

        Calendar timestamp2 = Calendar.getInstance();
        selected.setNotificationTimestamp ( timestamp2 );
        selected.setRowID ( testNotification.getRowID() );

        assertTrue ( "Could not update notification ID #" + testNotification.getRowID(), hibernateFacade.insertOrUpdateNotification ( selected ) );

        ExpirationNotification updated = hibernateFacade.selectNotification ( testNotification.getRowID() );

        assertTrue ( "Could not update notification time", updated.getNotificationTime().equals ( timestamp ) );
        assertTrue ( "Could not update notification timestamp", updated.getNotificationTimestamp().equals ( timestamp2 ) );
    }

    public void testInsertSelectUpdateSuppressionRecord()
    {
        _log.info ( "\n\n\n**************************************** testInsertSelectUpdateSuppressionRecord()" );
        int SuppressionID  = (int) (System.currentTimeMillis() / 10000);
        Calendar timestamp = Calendar.getInstance();

        SuppressionRecord testSuppressionRecord = new SuppressionRecord();
        testSuppressionRecord.setSuppressionID ( SuppressionID );
        testSuppressionRecord.setNodeName      ( "test_node" );
        testSuppressionRecord.setGroupName     ( "test_group" );
        testSuppressionRecord.setStartTime     ( Calendar.getInstance() );
        testSuppressionRecord.setEndTime       ( Calendar.getInstance() );
        testSuppressionRecord.setDescription   ( "test description" );
        testSuppressionRecord.setCreatorName   ( "test-user-" + SuppressionID );
        testSuppressionRecord.setMessage       ( "test-message" );

        HibernateFacade hibernateFacade = Toolkit.getHibernateFacade();
        assertFalse ( "SuppressionRecord already exists: " + testSuppressionRecord.getSuppressionID(), hibernateFacade.insertOrUpdateSuppressionRecord ( testSuppressionRecord ) );

        _log.info ( "Row ID: " + testSuppressionRecord.getRowID () );
        _log.info ( "SuppressionRecord ID: " + testSuppressionRecord.getSuppressionID() );

        SuppressionRecord selected = hibernateFacade.findSuppressionRecord ( testSuppressionRecord.getSuppressionID() );
        assertNotNull ( "Could not find notification ID #" + testSuppressionRecord.getSuppressionID(), selected );
        selected.setRowID ( testSuppressionRecord.getRowID() );
        selected.setMessage ( "Updated message" );

        assertTrue ( "Could not update notification ID #" + testSuppressionRecord.getSuppressionID(), hibernateFacade.insertOrUpdateSuppressionRecord ( selected ) );

        SuppressionRecord updated = hibernateFacade.findSuppressionRecord ( testSuppressionRecord.getSuppressionID() );
        updated.setNodeName      ( "updated_node" );
        updated.setGroupName     ( "updated_group" );
        updated.setStartTime     ( Calendar.getInstance() );
        updated.setEndTime       ( Calendar.getInstance() );
        updated.setDescription   ( "updated description" );
        updated.setCreatorName   ( "updated-user-" + SuppressionID );
        updated.setMessage       ( "updated-message" );

        _log.info ( "Updating Suppression #" + updated.getSuppressionID() );

        assertTrue ( "SuppressionRecord already exists", hibernateFacade.insertOrUpdateSuppressionRecord ( updated ) );
    }


    public void testSaveRetrieveSuppressionRecord()
    {
        _log.info ( "\n\n\n**************************************** testSaveRetrieveSuppressionRecord()" );
        int SuppressionID  = (int) (System.currentTimeMillis() / 10000);
        SuppressionRecord suppressionRecord = new SuppressionRecord();
        suppressionRecord.setSuppressionID ( SuppressionID );
        suppressionRecord.setNodeName      ( "test_node" );
        suppressionRecord.setGroupName     ( "test_group" );
        suppressionRecord.setStartTime     ( Calendar.getInstance() );
        suppressionRecord.setEndTime       ( Calendar.getInstance() );
        suppressionRecord.setDescription   ( "test description" );
        suppressionRecord.setCreatorName   ( "test-user-" + SuppressionID );
        suppressionRecord.setMessage       ( "test-message" );

        HibernateFacade hibernateFacade = Toolkit.getHibernateFacade();
        assertFalse ( "Suppression already exists: " + SuppressionID, hibernateFacade.insertOrUpdateSuppressionRecord ( suppressionRecord ) );

        _log.info ( "Resulting Suppression ID: " + suppressionRecord.getSuppressionID() );

        assertEquals ( "Suppression IDs are different", suppressionRecord.getSuppressionID(), SuppressionID );
    }
}
