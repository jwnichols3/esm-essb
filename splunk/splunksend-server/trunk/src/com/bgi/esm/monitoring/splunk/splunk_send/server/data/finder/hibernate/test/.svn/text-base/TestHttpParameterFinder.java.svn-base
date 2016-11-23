package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.HttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test.SampleHttpParameterCreator;

public class TestHttpParameterFinder extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHttpParameterFinder.class );

    public TestHttpParameterFinder ( String param )
    {
        super ( param );
    }

    public void testSelectAll()
    {
        _log.info ( "**************************************** testSelectAll()" );
        IHttpParameterFinder finder = new HttpParameterFinder();
        List <IHttpParameter> results = finder.selectAll();

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectAllPaginate()
    {
        _log.info ( "**************************************** testSelectAllPaginate()" );
        IHttpParameterFinder finder = new HttpParameterFinder();
        List <IHttpParameter> results = finder.selectAll ( 3, 1 );

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectCountAll()
    {
        _log.info ( "**************************************** testSelectCountAll()" );
        IHttpParameterFinder finder = new HttpParameterFinder();
        int num_results  = finder.selectCountAll();

        assertTrue ( "Invalid result for selectCountAll(): " + num_results, num_results >= 0 );
    }

    public void testEquals()
    {
        _log.info ( "**************************************** testEquals()" );
        IHttpParameterFinder finder = new HttpParameterFinder();
        IHttpParameter object1      = SampleHttpParameterCreator.createSampleObject ( finder );
        IHttpParameter object2      = SampleHttpParameterCreator.createSampleObject ( finder );
        IHttpParameter object3      = SampleHttpParameterCreator.createUpdateObject ( finder );

        assertEquals ( "Fails equality test",      object1, object2 );
        assertFalse  ( "Fails inequality test #1", object1.equals ( object3 ) );
        assertFalse  ( "Fails inequality test #2", object2.equals ( object3 ) );
    }

    public void testUpdate() throws UnknownHostException
    {
        _log.info ( "**************************************** testUpdate()" );
        IHttpParameterFinder finder = new HttpParameterFinder();
        IHttpParameter object       = SampleHttpParameterCreator.createSampleObject ( finder );

        assertFalse ( "Should not update a non-existent object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        assertTrue  ( "Could not insert test object", finder.insert ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        assertTrue  ( "Could not update test object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
    }

    public void testInsertSelectUpdate_ExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_ExistingRecord()" );
        IHttpParameterFinder finder  = new HttpParameterFinder();
        IHttpParameter object_insert = SampleHttpParameterCreator.createSampleObject ( finder );
        IHttpParameter object_update = SampleHttpParameterCreator.createUpdateObject ( finder );

        assertTrue ( "Failed to insert test object", finder.insert ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRowID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpParameter object_select = finder.select ( object_insert.getRowID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRowID ( object_insert.getRowID() );
        assertTrue ( "Could not update existing object", finder.update ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRowID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object update: " + object_update.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

    public void testInsertSelectUpdate_NonExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_NonExistingRecord()" );
        IHttpParameterFinder finder  = new HttpParameterFinder();
        IHttpParameter object_insert = SampleHttpParameterCreator.createSampleObject ( finder );
        IHttpParameter object_update = SampleHttpParameterCreator.createUpdateObject ( finder );

        assertFalse ( "Attempt to insert test object resulted in an update", finder.insertOrUpdate ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRowID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpParameter object_select = finder.select ( object_insert.getRowID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRowID ( object_insert.getRowID() );
        assertTrue ( "Attempt to update existing object resulted in an insert", finder.insertOrUpdate ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRowID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }
}