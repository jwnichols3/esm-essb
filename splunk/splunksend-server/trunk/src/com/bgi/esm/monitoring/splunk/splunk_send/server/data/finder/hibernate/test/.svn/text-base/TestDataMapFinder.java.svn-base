package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.DataMapFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IDataMapFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test.SampleDataMapCreator;

public class TestDataMapFinder extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestDataMapFinder.class );

    public TestDataMapFinder ( String param )
    {
        super ( param );
    }

    public void testSelectAll()
    {
        _log.info ( "**************************************** testSelectAll()" );
        IDataMapFinder finder = new DataMapFinder();
        List <IDataMap> results = finder.selectAll();

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectAllPaginate()
    {
        _log.info ( "**************************************** testSelectAllPaginate()" );
        IDataMapFinder finder = new DataMapFinder();
        List <IDataMap> results = finder.selectAll ( 3, 1 );

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectCountAll()
    {
        _log.info ( "**************************************** testSelectCountAll()" );
        IDataMapFinder finder = new DataMapFinder();
        int num_results  = finder.selectCountAll();

        assertTrue ( "Invalid result for selectCountAll(): " + num_results, num_results >= 0 );
    }

    public void testEquals()
    {
        _log.info ( "**************************************** testEquals()" );
        IDataMapFinder finder = new DataMapFinder();
        IDataMap object1      = SampleDataMapCreator.createSampleObject ( finder );
        IDataMap object2      = SampleDataMapCreator.createSampleObject ( finder );
        IDataMap object3      = SampleDataMapCreator.createUpdateObject ( finder );

        assertEquals ( "Fails equality test",      object1, object2 );
        assertFalse  ( "Fails inequality test #1", object1.equals ( object3 ) );
        assertFalse  ( "Fails inequality test #2", object2.equals ( object3 ) );
    }

    public void testUpdate() throws UnknownHostException
    {
        _log.info ( "**************************************** testUpdate()" );
        IDataMapFinder finder = new DataMapFinder();
        IDataMap object       = SampleDataMapCreator.createSampleObject ( finder );

        assertFalse ( "Should not update a non-existent object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        assertTrue  ( "Could not insert test object", finder.insert ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        assertTrue  ( "Could not update test object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
    }

    public void testInsertSelectUpdate_ExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_ExistingRecord()" );
        IDataMapFinder finder  = new DataMapFinder();
        IDataMap object_insert = SampleDataMapCreator.createSampleObject ( finder );
        IDataMap object_update = SampleDataMapCreator.createUpdateObject ( finder );

        assertTrue ( "Failed to insert test object", finder.insert ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRuleID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IDataMap object_select = finder.select ( object_insert.getRuleID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRuleID ( object_insert.getRuleID() );
        assertTrue ( "Could not update existing object", finder.update ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRuleID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object update: " + object_update.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

    public void testInsertSelectUpdate_NonExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_NonExistingRecord()" );
        IDataMapFinder finder  = new DataMapFinder();
        IDataMap object_insert = SampleDataMapCreator.createSampleObject ( finder );
        IDataMap object_update = SampleDataMapCreator.createUpdateObject ( finder );

        assertFalse ( "Attempt to insert test object resulted in an update", finder.insertOrUpdate ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRuleID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IDataMap object_select = finder.select ( object_insert.getRuleID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRuleID ( object_insert.getRuleID() );
        assertTrue ( "Attempt to update existing object resulted in an insert", finder.insertOrUpdate ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRuleID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }
}