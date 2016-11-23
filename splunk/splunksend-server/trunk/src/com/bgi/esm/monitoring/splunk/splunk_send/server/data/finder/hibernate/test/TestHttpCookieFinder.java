package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.HttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test.SampleHttpCookieCreator;

public class TestHttpCookieFinder extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHttpCookieFinder.class );

    public TestHttpCookieFinder ( String param )
    {
        super ( param );
    }

    public void testSelectAll()
    {
        _log.info ( "**************************************** testSelectAll()" );
        IHttpCookieFinder finder = new HttpCookieFinder();
        List <IHttpCookie> results = finder.selectAll();

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectAllPaginate()
    {
        _log.info ( "**************************************** testSelectAllPaginate()" );
        IHttpCookieFinder finder = new HttpCookieFinder();
        List <IHttpCookie> results = finder.selectAll ( 3, 1 );

        assertNotNull ( "Could not obtain results list", results );
    }

    public void testSelectCountAll()
    {
        _log.info ( "**************************************** testSelectCountAll()" );
        IHttpCookieFinder finder = new HttpCookieFinder();
        int num_results  = finder.selectCountAll();

        assertTrue ( "Invalid result for selectCountAll(): " + num_results, num_results >= 0 );
    }

    public void testEquals()
    {
        _log.info ( "**************************************** testEquals()" );
        IHttpCookieFinder finder = new HttpCookieFinder();
        IHttpCookie object1      = SampleHttpCookieCreator.createSampleObject ( finder );
        IHttpCookie object2      = SampleHttpCookieCreator.createSampleObject ( finder );
        IHttpCookie object3      = SampleHttpCookieCreator.createUpdateObject ( finder );

        assertEquals ( "Fails equality test",      object1, object2 );
        assertFalse  ( "Fails inequality test #1", object1.equals ( object3 ) );
        assertFalse  ( "Fails inequality test #2", object2.equals ( object3 ) );
    }

    public void testUpdate() throws UnknownHostException
    {
        _log.info ( "**************************************** testUpdate()" );
        IHttpCookieFinder finder = new HttpCookieFinder();
        IHttpCookie object       = SampleHttpCookieCreator.createSampleObject ( finder );

        assertFalse ( "Should not update a non-existent object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );

        assertTrue  ( "Could not insert test object", finder.insert ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        assertTrue  ( "Could not update test object", finder.update ( object, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
    }

    public void testInsertSelectUpdate_ExistingRecord() throws UnknownHostException
    {
        _log.info ( "**************************************** testInsertSelectUpdate_ExistingRecord()" );
        IHttpCookieFinder finder  = new HttpCookieFinder();
        IHttpCookie object_insert = SampleHttpCookieCreator.createSampleObject ( finder );
        IHttpCookie object_update = SampleHttpCookieCreator.createUpdateObject ( finder );

        assertTrue ( "Failed to insert test object", finder.insert ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRowID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpCookie object_select = finder.select ( object_insert.getRowID() );
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
        IHttpCookieFinder finder  = new HttpCookieFinder();
        IHttpCookie object_insert = SampleHttpCookieCreator.createSampleObject ( finder );
        IHttpCookie object_update = SampleHttpCookieCreator.createUpdateObject ( finder );

        assertFalse ( "Attempt to insert test object resulted in an update", finder.insertOrUpdate ( object_insert, "TestUser", InetAddress.getLocalHost().getHostName(), InetAddress.getLocalHost().getHostAddress(), -1 ) );
        _log.info ( "Object ID: " + object_insert.getRowID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpCookie object_select = finder.select ( object_insert.getRowID() );
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