package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;


import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpHeaderFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;

public class SampleHttpHeaderCreator
{
    public static IHttpHeader createSampleObject ( IHttpHeaderFinder finder )
    {
        IHttpHeader object = finder.newInstance();
        object.setRequestID          ( "test string for 'request_id'" );
        object.setHeaderName         ( "test string for 'header_name'" );
        object.setHeaderPersistence  ( "test string for 'header_persistence'" );

        return object;
    }

    public static IHttpHeader createUpdateObject ( IHttpHeaderFinder finder )
    {
        IHttpHeader object = finder.newInstance();
        object.setRequestID          ( "updated string for 'request_id'" );
        object.setHeaderName         ( "updated string for 'header_name'" );
        object.setHeaderPersistence  ( "updated string for 'header_persistence'" );

        return object;
    }
}