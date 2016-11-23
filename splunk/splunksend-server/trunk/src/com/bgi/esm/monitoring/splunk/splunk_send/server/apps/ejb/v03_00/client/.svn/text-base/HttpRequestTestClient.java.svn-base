package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.client;

import javax.naming.Context;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote.IHttpRequestRemote;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test.SampleHttpRequestCreator;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class HttpRequestTestClient extends BaseTestClient
{
    final private static Logger _log = Logger.getLogger ( HttpRequestTestClient.class );

    private IHttpRequestRemote bean = null;

    public void testEnumerateContext() throws NamingException
    {
        _log.info ( "**************************************** testEnumerateContext()" );
        Context context = getContext();
        assertNotNull ( "Could not obtain JNDI connection", context );
        enumerateContextHelper ( context, 0 );
    }

    public void testStateless_InsertSelectUpdateDelete() throws NamingException
    {
        _log.info ( "**************************************** testStateless_InsertSelectUpdateDelete()" );
        assertNotNull ( "Could not obtain connection to application server", getContext() );

        bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
        IHttpRequestFinder finder  = Configuration.getInstance().getHttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );

        object_insert = bean.insertRemote ( object_insert );
        assertNotNull ( "Failed to insert test object", object_insert );
        _log.info ( "Object ID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpRequest object_select = bean.selectRemote ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( object_insert.getRequestID() );
        object_select = bean.updateRemote ( object_select );
        assertNotNull ( "Could not update existing object", object_select );

        object_select = bean.selectRemote ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object insert: " + object_insert.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

    public void testStateless_InsertOrUpdate() throws NamingException
    {
        _log.info ( "**************************************** testStateless_InsertOrUpdate()" );
        assertNotNull ( "Could not obtain connection to application server", getContext() );

        bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
        IHttpRequestFinder finder  = Configuration.getInstance().getHttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );
        IHttpRequest result        = null;

        result = bean.insertOrUpdateRemote ( object_insert );
        assertNotNull ( "Attempt to insert test object failed", result );
        _log.info ( "NewRowID: " + result.getRequestID() );
        _log.info ( "OldRowID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );
        assertFalse ( "Insert resulted in an update", result.getRequestID().equals ( object_insert.getRequestID() ) );

        IHttpRequest object_select = bean.selectRemote ( result.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( result.getRequestID() );
        result = bean.insertOrUpdateRemote ( object_select );
        assertNotNull ( "Attempt to update existing object failed ", result );

        _log.info ( "NewRowID: " + result.getRequestID() );
        _log.info ( "OldRowID: " + object_select.getRequestID() );
        assertEquals ( "Update resulted in an insert", object_select.getRequestID(), result.getRequestID() );
        object_select = bean.selectRemote ( result.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object result: " + result.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }


    public void testStateful_InsertSelectUpdateDelete() throws NamingException
    {
        _log.info ( "**************************************** testStateful_InsertSelectUpdateDelete()" );
        assertNotNull ( "Could not obtain connection to application server", getContext() );

        bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateful" );
        IHttpRequestFinder finder  = Configuration.getInstance().getHttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );

        object_insert = bean.insertRemote ( object_insert );
        assertNotNull ( "Failed to insert test object", object_insert );
        _log.info ( "Object ID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );

        IHttpRequest object_select = bean.selectRemote ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( object_insert.getRequestID() );
        object_select = bean.updateRemote ( object_select );
        assertNotNull ( "Could not update existing object", object_select );

        object_select = bean.selectRemote ( object_insert.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object insert: " + object_insert.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

    public void testStateful_InsertOrUpdate() throws NamingException
    {
        _log.info ( "**************************************** testStateful_InsertSelectUpdateDelete()" );
        assertNotNull ( "Could not obtain connection to application server", getContext() );

        bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateful" );
        IHttpRequestFinder finder  = Configuration.getInstance().getHttpRequestFinder();
        IHttpRequest object_insert = SampleHttpRequestCreator.createSampleObject ( finder );
        IHttpRequest object_update = SampleHttpRequestCreator.createUpdateObject ( finder );
        IHttpRequest result        = null;

        result = bean.insertOrUpdateRemote ( object_insert );
        assertNotNull ( "Attempt to insert test object failed", result );
        _log.info ( "NewRowID: " + result.getRequestID() );
        _log.info ( "OldRowID: " + object_insert.getRequestID() );
        _log.info ( "Object:    " + object_insert.toString() );
        assertFalse ( "Insert resulted in an update", result.getRequestID().equals ( object_insert.getRequestID() ) );

        IHttpRequest object_select = bean.selectRemote ( result.getRequestID() );
        assertNotNull ( "Could not find test object in database - #1", object_select );
        assertEquals  ( "Selected object and inserted object are not the same", object_insert, object_select );

        object_select.setValue ( object_update );
        object_select.setRequestID ( result.getRequestID() );
        result = bean.insertOrUpdateRemote ( object_select );
        assertNotNull ( "Attempt to update existing object failed ", result );

        _log.info ( "NewRowID: " + result.getRequestID() );
        _log.info ( "OldRowID: " + object_select.getRequestID() );
        assertEquals ( "Update resulted in an insert", object_select.getRequestID(), result.getRequestID() );
        object_select = bean.selectRemote ( result.getRequestID() );
        assertNotNull ( "Could not find test object in database - #2", object_select );
        _log.info ( "Object select: " + object_select.toString() );
        _log.info ( "Object result: " + result.toString() );
        assertEquals ( "Updated test object was not properly persisted", object_select, object_update );
    }

}