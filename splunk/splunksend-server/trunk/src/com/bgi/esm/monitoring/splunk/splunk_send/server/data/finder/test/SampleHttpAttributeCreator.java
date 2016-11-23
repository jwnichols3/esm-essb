package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;


import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpAttributeFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

public class SampleHttpAttributeCreator
{
    public static IHttpAttribute createSampleObject ( IHttpAttributeFinder finder )
    {
        IHttpAttribute object = finder.newInstance();
        object.setRequestID             ( "test string for 'request_id'" );
        object.setAttributeName         ( "test string for 'attribute_name'" );
        object.setAttributePersistence  ( "test string for 'attribute_persistence'" );

        return object;
    }

    public static IHttpAttribute createUpdateObject ( IHttpAttributeFinder finder )
    {
        IHttpAttribute object = finder.newInstance();
        object.setRequestID             ( "updated string for 'request_id'" );
        object.setAttributeName         ( "updated string for 'attribute_name'" );
        object.setAttributePersistence  ( "updated string for 'attribute_persistence'" );

        return object;
    }
}