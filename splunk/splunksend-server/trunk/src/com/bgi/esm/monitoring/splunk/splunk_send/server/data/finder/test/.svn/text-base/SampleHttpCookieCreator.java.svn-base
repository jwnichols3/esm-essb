package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;


import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

public class SampleHttpCookieCreator
{
    public static IHttpCookie createSampleObject ( IHttpCookieFinder finder )
    {
        IHttpCookie object = finder.newInstance();
        object.setRequestID          ( "test string for 'request_id'" );
        object.setCookieComment      ( "test string for 'cookie_comment'" );
        object.setDomain             ( "test string for 'domain'" );
        object.setPath               ( "test string for 'path'" );
        object.setCookieName         ( "test string for 'cookie_name'" );
        object.setCookiePersistence  ( "test string for 'cookie_persistence'" );
        object.setIsSecure           ( false );
        object.setMaxAge             ( 5 );
        object.setVersion            ( 5 );

        return object;
    }

    public static IHttpCookie createUpdateObject ( IHttpCookieFinder finder )
    {
        IHttpCookie object = finder.newInstance();
        object.setRequestID          ( "updated string for 'request_id'" );
        object.setCookieComment      ( "updated string for 'cookie_comment'" );
        object.setDomain             ( "updated string for 'domain'" );
        object.setPath               ( "updated string for 'path'" );
        object.setCookieName         ( "updated string for 'cookie_name'" );
        object.setCookiePersistence  ( "updated string for 'cookie_persistence'" );
        object.setIsSecure           ( true );
        object.setMaxAge             ( 9 );
        object.setVersion            ( 9 );

        return object;
    }
}