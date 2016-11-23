package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;


import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;

public class SampleHttpParameterCreator
{
    public static IHttpParameter createSampleObject ( IHttpParameterFinder finder )
    {
        IHttpParameter object = finder.newInstance();
        object.setRequestID             ( "test string for 'request_id'" );
        object.setParameterName         ( "test string for 'parameter_name'" );
        object.setParameterPersistence  ( "test string for 'parameter_persistence'" );

        return object;
    }

    public static IHttpParameter createUpdateObject ( IHttpParameterFinder finder )
    {
        IHttpParameter object = finder.newInstance();
        object.setRequestID             ( "updated string for 'request_id'" );
        object.setParameterName         ( "updated string for 'parameter_name'" );
        object.setParameterPersistence  ( "updated string for 'parameter_persistence'" );

        return object;
    }
}