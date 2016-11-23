package com.bgi.esm.monitoring.portlets.Throttle.forms.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle;

public class TestThrottle extends TestCase
{
    final private static Logger _log          = Logger.getLogger ( TestThrottle.class );
    final private static String test_username = "test_username";

    public TestThrottle ( String param ) 
    {
        super ( param );

        _log.info ( "Test suite for TestThrottle has been initialized" );
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestThrottle::testHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void SKIPtestCountRecords()
    {
        _log.info ( "************************************************************ TestThrottle::testCountRecords()" );

        Throttle.countRecords();
    }

    public void SKIPtestSelectAll()
    {
        _log.info ( "************************************************************ TestThrottle::testSelectAll()" );

        List <Throttle> list = Throttle.selectAll();

        assertNotNull ( "Could not paginate select results", list );
    }

    public void SKIPtestSelectResultsPage()
    {
        _log.info ( "************************************************************ TestThrottle::testSelectResultsPage()" );

        List <Throttle> list = Throttle.retrieveRecordsPage ( 0, 5 );

        assertNotNull ( "Could not paginate select results", list );
    }

    public void testInsertUpdateObject()
    {
        _log.info ( "************************************************************ TestThrottle::testInsertUpdateObject()" );

        long num_records = Throttle.countRecords();

        //  Create sample object to populate database with
        Throttle object = new Throttle ();
//        object.setRowId        ( "test string for 'row_id'" );
        object.setBgiGroup     ( "test string for 'bgi_group'" );
        object.setStormLevel   ( 0 );
        object.setDuration     ( 0 );
        object.setThreshold    ( 0 );
        object.setAction       ( "test string for 'action'" );
        object.setMessageFlag  ( 'a' );

        assertFalse ( "Object already exists in test database", object.insertOrUpdate ( test_username ) );

        long index = object.getRuleId ();
       
        
        _log.info ( "Object now has index=" + index );
        _log.info ( "Object now has Row Id=" + object.getRowId() );

        long prev_versions1 = Throttle.countPreviousVersions ( index );

        //  Update fields in object to test updating
//        object.setRowId        ( "updated string for 'row_id'" );
        object.setRuleId	   (index);
        object.setBgiGroup     ( "updated string for 'bgi_group'" );
        object.setStormLevel   ( 1 );
        object.setDuration     ( 1 );
        object.setThreshold    ( 1 );
        object.setAction       ( "updated string for 'action'" );
        object.setMessageFlag  ( 'A' );


        assertTrue ( "Object could not be found in database", object.insertOrUpdate( test_username ) );
        long prev_versions2 = Throttle.countPreviousVersions ( index );


        //  Testing audit functionality
        _log.info ( "Number of previous versions before update: " + prev_versions1 );
        _log.info ( "Number of previous versions after update:  " + prev_versions2 );
        assertTrue ( "Audit functionality is not working", prev_versions2 == prev_versions1 + 1 );
    }
}
