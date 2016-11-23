package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;


import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IDataMapFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;

public class SampleDataMapCreator
{
    public static IDataMap createSampleObject ( IDataMapFinder finder )
    {
        IDataMap object = finder.newInstance();
        object.setApplicationName  ( "test string for 'application_name'" );
        object.setHostname         ( "test string for 'hostname'" );
        object.setTargetPath       ( "test string for 'target_path'" );

        return object;
    }

    public static IDataMap createUpdateObject ( IDataMapFinder finder )
    {
        IDataMap object = finder.newInstance();
        object.setApplicationName  ( "updated string for 'application_name'" );
        object.setHostname         ( "updated string for 'hostname'" );
        object.setTargetPath       ( "updated string for 'target_path'" );

        return object;
    }
}