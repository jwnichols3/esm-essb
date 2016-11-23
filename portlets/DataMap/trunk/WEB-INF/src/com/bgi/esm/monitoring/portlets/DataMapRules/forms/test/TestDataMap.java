package com.bgi.esm.monitoring.portlets.DataMapRules.forms.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.DataMapRules.HibernateUtil;
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap;

public class TestDataMap extends TestCase
{
    final private static Logger _log          = Logger.getLogger ( TestDataMap.class );
    final private static String test_username = "test_username";

    public TestDataMap ( String param ) 
    {
        super ( param );

        _log.info ( "Test suite for TestDataMap has been initialized" );
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestDataMap::testHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void testCountRecords()
    {
        _log.info ( "************************************************************ TestDataMap::testCountRecords()" );

        DataMap.countRecords();
    }

    public void testSelectAll()
    {
        _log.info ( "************************************************************ TestDataMap::testSelectAll()" );

        List <DataMap> list = DataMap.selectAll();

        assertNotNull ( "Could not paginate select results", list );
    }

    public void testSelectResultsPage()
    {
        _log.info ( "************************************************************ TestDataMap::testSelectResultsPage()" );

        List <DataMap> list = DataMap.retrieveRecordsPage ( 0, 5 );

        assertNotNull ( "Could not paginate select results", list );
    }

    public void testInsertUpdateObject()
    {
        _log.info ( "************************************************************ TestDataMap::testInsertUpdateObject()" );

        long num_records = DataMap.countRecords();

        //  Create sample object to populate database with
        DataMap object = new DataMap ();
        object.setBgiGroup     ( "test string for 'bgi_group'" );
        object.setBgiMethod    ( "test string for 'bgi_method'" );
        object.setApGroup      ( "test string for 'ap_group'" );
        object.setApScript     ( "test string for 'ap_script'" );
        object.setPerCat       ( "test string for 'per_cat'" );
        object.setPerSubcat    ( "test string for 'per_subcat'" );
        object.setPerProduct   ( "test string for 'per_product'" );
        object.setPerProblem   ( "test string for 'per_problem'" );
        object.setPerAssign    ( "test string for 'per_assign'" );
        object.setPerLocation  ( "test string for 'per_location'" );

        assertFalse ( "Object already exists in test database", object.insertOrUpdate ( test_username ) );

        long index = object.getRuleId ();
        _log.info ( "Object now has index=" + index );

        long prev_versions1 = DataMap.countPreviousVersions ( index );

        //  Update fields in object to test updating
        object.setBgiGroup     ( "updated string for 'bgi_group'" );
        object.setBgiMethod    ( "updated string for 'bgi_method'" );
        object.setApGroup      ( "updated string for 'ap_group'" );
        object.setApScript     ( "updated string for 'ap_script'" );
        object.setPerCat       ( "updated string for 'per_cat'" );
        object.setPerSubcat    ( "updated string for 'per_subcat'" );
        object.setPerProduct   ( "updated string for 'per_product'" );
        object.setPerProblem   ( "updated string for 'per_problem'" );
        object.setPerAssign    ( "updated string for 'per_assign'" );
        object.setPerLocation  ( "updated string for 'per_location'" );


        assertTrue ( "Object could not be found in database", object.insertOrUpdate( test_username ) );
        long prev_versions2 = DataMap.countPreviousVersions ( index );


        //  Testing audit functionality
        _log.info ( "Number of previous versions before update: " + prev_versions1 );
        _log.info ( "Number of previous versions after update:  " + prev_versions2 );
        assertTrue ( "Audit functionality is not working", prev_versions2 == prev_versions1 + 1 );
    }
}
