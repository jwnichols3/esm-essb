package com.bgi.esm.monitoring.portlets.Suppressions.forms.test;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression;

public class TestSuppression extends TestCase
{
    final private static Logger _log          = Logger.getLogger ( TestSuppression.class );
    final private static String test_username = "test_username";

    public TestSuppression ( String param ) 
    {
        super ( param );

        _log.info ( "Test suite for TestSuppression has been initialized" );
    }

    private Suppression createTestSuppression()
    {
        Calendar start_time = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        Calendar end_time   = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        end_time.add ( Calendar.HOUR, 4 );

        Suppression object = new Suppression ();
        object.setRowId            ( "test string for 'row_id' - " + System.currentTimeMillis() );
        object.setStartTime        ( start_time );
        object.setEndTime          ( end_time   );
        object.setAppName          ( "test string for 'app_name'" );
        object.setNodeName         ( "test string for 'node_name'" );
        object.setGroupName        ( "test string for 'group_name'" );
        object.setDbServer         ( "test string for 'db_server'" );
        object.setNotifyFlg        ( 0 );
        object.setNotifyMinutes    ( 0 );
        object.setRemoveOnReboot   ( 0 );
        object.setDescription      ( "test string for 'description'" );
        object.setMessage          ( "test string for 'message'" );
        object.setOwner            ( test_username );

        return object;
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestSuppression::testHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void testCountRecords()
    {
        _log.info ( "************************************************************ TestSuppression::testCountRecords()" );

        Suppression.countRecords();
    }

    public void testSelectAll()
    {
        _log.info ( "************************************************************ TestSuppression::testSelectAll()" );

        List <Suppression> list = Suppression.selectAll();

        assertNotNull ( "Could not paginate select results", list );
    }

    public void testSelectResultsPage()
    {
        _log.info ( "************************************************************ TestSuppression::testSelectResultsPage()" );

        List <Suppression> list = Suppression.retrieveRecordsPage ( 0, 5 );

        assertNotNull ( "Could not paginate select results", list );
    }

    public void testSelectSpecificSuppression()
    {
        _log.info ( "************************************************************ TestSuppression::testSelectSpecificSuppression()" );

        Suppression object = Suppression.select ( 1L );

        assertNotNull ( "Could not find suppression ( SuppressID=1 )", object );
    }

    public void testInsertUpdateObject()
    {
        _log.info ( "************************************************************ TestSuppression::testInsertUpdateObject()" );

        long num_records = Suppression.countRecords();

        //  Create sample object to populate database with
        Suppression object = createTestSuppression();

        assertFalse ( "Object already exists in test database", object.insertOrUpdate ( test_username ) );

        long index = object.getSuppressId ();
        _log.info ( "Object now has RowID=" + object.getRowId() );
        _log.info ( "Object now has index=" + index );

        long prev_versions1 = Suppression.countPreviousVersions ( index );

        //  Update fields in object to test updating
        //object.setRowId            ( "updated string for 'row_id'" );
        object.setStartTime        ( Calendar.getInstance(TimeZone.getTimeZone ( "GMT" )) );
        object.setEndTime          ( Calendar.getInstance(TimeZone.getTimeZone ( "GMT" )) );
        object.setAppName          ( "updated string for 'ap_name'" );
        object.setNodeName         ( "updated string for 'node_name'" );
        object.setGroupName        ( "updated string for 'group_name'" );
        object.setDbServer         ( "updated string for 'db_server'" );
        object.setNotifyFlg        ( 1 );
        object.setNotifyMinutes    ( 1 );
        object.setRemoveOnReboot   ( 1 );
        object.setDescription      ( "updated string for 'description'" );
        object.setMessage          ( "updated string for 'message'" );

        assertTrue ( "Object could not be found in database", object.insertOrUpdate( test_username ) );
        long prev_versions2 = Suppression.countPreviousVersions ( index );


        //  Testing audit functionality
        _log.info ( "Number of previous versions before update: " + prev_versions1 );
        _log.info ( "Number of previous versions after update:  " + prev_versions2 );
        assertTrue ( "Audit functionality is not working", prev_versions2 == prev_versions1 + 1 );
    }

    public void testSearchForActiveSuppression()
    {
        _log.info ( "************************************************************ TestSuppression::testSearchForActiveSuppression()" );

        Suppression object = createTestSuppression();

        assertFalse ( "Object already exists in the database", object.insertOrUpdate ( test_username ) );

        _log.info ( "New suppression has RowID=" + object.getRowId() );

        List <Suppression> all_suppressions = Suppression.selectAll();
        for ( int counter = 0; counter < all_suppressions.size(); counter++ )
        {
            Suppression suppression = all_suppressions.get ( counter );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Found an active suppression: ( RowID=" );
            message.get().append ( suppression.getRowId() );
            message.get().append ( ", StartTime=" );
            message.get().append ( Toolkit.sdf.format ( suppression.getStartTime().getTime() ) );
            message.get().append ( ", EndTime=" );
            message.get().append ( Toolkit.sdf.format ( suppression.getEndTime().getTime() ) );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        List <Suppression> active_suppressions = Suppression.selectAllActive(0);
        boolean found_suppression = false;
        for ( int counter = 0; counter < active_suppressions.size(); counter++ )
        {
            Suppression suppression = active_suppressions.get ( counter );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Found an active suppression: ( RowID=" );
            message.get().append ( suppression.getRowId() );
            message.get().append ( ", StartTime=" );
            message.get().append ( Toolkit.sdf.format ( suppression.getStartTime().getTime() ) );
            message.get().append ( ", EndTime=" );
            message.get().append ( Toolkit.sdf.format ( suppression.getEndTime().getTime() ) );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );

            if ( suppression.getRowId().equals ( object.getRowId() ) )
            {
                found_suppression = true;

                break;
            }
        }

        assertTrue ( "Could not find newly inserted active suppression", found_suppression );
    }
}
