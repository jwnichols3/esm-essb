package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IDataMapFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpAttributeFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpHeaderFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class TestConfiguration extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestConfiguration.class );

    public TestConfiguration ( String param )
    {
        super ( param );
    }

    public void testFinder_DataMap()
    {
        _log.info ( "**************************************** testFinder_DataMap()" );
        IDataMapFinder finder = Configuration.getInstance().getDataMapFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testFinder_HttpAttribute()
    {
        _log.info ( "**************************************** testFinder_HttpAttribute()" );
        IHttpAttributeFinder finder = Configuration.getInstance().getHttpAttributeFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testFinder_HttpCookie()
    {
        _log.info ( "**************************************** testFinder_HttpCookie()" );
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testFinder_HttpHeader()
    {
        _log.info ( "**************************************** testFinder_HttpHeader()" );
        IHttpHeaderFinder finder = Configuration.getInstance().getHttpHeaderFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testFinder_HttpParameter()
    {
        _log.info ( "**************************************** testFinder_HttpParameter()" );
        IHttpParameterFinder finder = Configuration.getInstance().getHttpParameterFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testFinder_HttpRequest()
    {
        _log.info ( "**************************************** testFinder_HttpRequest()" );
        IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();

        assertNotNull ( "Could not instantiate finder", finder );
    }

    public void testDTO_DataMap()
    {
        _log.info ( "**************************************** testDTO_DataMap()" );
        IDataMap object = Configuration.getInstance().newDataMap();

        assertNotNull ( "Could not instantiate object", object );
    }

    public void testDTO_HttpAttribute()
    {
        _log.info ( "**************************************** testDTO_HttpAttribute()" );
        IHttpAttribute object = Configuration.getInstance().newHttpAttribute();

        assertNotNull ( "Could not instantiate object", object );
    }

    public void testDTO_HttpCookie()
    {
        _log.info ( "**************************************** testDTO_HttpCookie()" );
        IHttpCookie object = Configuration.getInstance().newHttpCookie();

        assertNotNull ( "Could not instantiate object", object );
    }

    public void testDTO_HttpHeader()
    {
        _log.info ( "**************************************** testDTO_HttpHeader()" );
        IHttpHeader object = Configuration.getInstance().newHttpHeader();

        assertNotNull ( "Could not instantiate object", object );
    }

    public void testDTO_HttpParameter()
    {
        _log.info ( "**************************************** testDTO_HttpParameter()" );
        IHttpParameter object = Configuration.getInstance().newHttpParameter();

        assertNotNull ( "Could not instantiate object", object );
    }

    public void testDTO_HttpRequest()
    {
        _log.info ( "**************************************** testDTO_HttpRequest()" );
        IHttpRequest object = Configuration.getInstance().newHttpRequest();

        assertNotNull ( "Could not instantiate object", object );
    }

}