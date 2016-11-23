package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.HttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test.SampleHttpRequestCreator;

public class TestHttpRequestFinder extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHttpRequestFinder.class );

    public TestHttpRequestFinder ( String param )
    {
        super ( param );
    }

    public void testSelectAll()
    {
        _log.info ( "**************************************** testSelectAll()" );
        IHttpRequestFinder finder = new HttpRequestFinder();
        List <IHttpRequest> results = finder.selectAll();

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectAllPaginate()
    {
        _log.info ( "**************************************** testSelectAllPaginate()" );
        IHttpRequestFinder finder = new HttpRequestFinder();
        List <IHttpRequest> results = finder.selectAll ( 3, 1 );

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectCountAll()
    {
        _log.info ( "**************************************** testSelectCountAll()" );
        IHttpRequestFinder finder = new HttpRequestFinder();
        int num_results  = finder.selectCountAll();

        assertTrue ( "Invalid result for selectCountAll(): " + num_results, num_results >= 0 );
    }

    public void testEquals()
    {
        _log.info ( "**************************************** testEquals()" );
        IHttpRequestFinder finder = new HttpRequestFinder();
        IHttpRequest object1      = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object2      = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object3      = SampleHttpRequestCreator.createUpdateObject ( finder );

        assertEquals ( "Fails equality test",      object1, object2 );
        assertFalse  ( "Fails inequality test #1", object1.equals ( object3 ) );
        assertFalse  ( "Fails inequality test #2", object2.equals ( object3 ) );
    }

    public void testUpdate() throws UnknownHostException
    {
        _log.info ( "**************************************** testUpdate()" );
        IHttpRequestFinder finder = new HttpRequestFinder();
        IHttpRequest object       = SampleHttpRequestCreator.createSampleObject ( finder );

        assertFalse ( "Should not update a non-existent object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        assertTrue  ( "Could not insert test object", finder.insert ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        assertTrue  ( "Could not update test object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
    }

    public void testInsertSelectUpdate_ExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_ExistingRecord()" );
        IHttpRequestFinder finder  = new HttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );

        assertTrue ( "Failed to insert test object", finder.insert ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpRequest object_select = finder.select ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( object_insert.getRequestID() );
        assertTrue ( "Could not update existing object", finder.update ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object update: " + object_update.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

    public void testInsertSelectUpdate_NonExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_NonExistingRecord()" );
        IHttpRequestFinder finder  = new HttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );

        assertFalse ( "Attempt to insert test object resulted in an update", finder.insertOrUpdate ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpRequest object_select = finder.select ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( object_insert.getRequestID() );
        assertTrue ( "Attempt to update existing object resulted in an insert", finder.insertOrUpdate ( object_select, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        object_select = finder.select ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }
}