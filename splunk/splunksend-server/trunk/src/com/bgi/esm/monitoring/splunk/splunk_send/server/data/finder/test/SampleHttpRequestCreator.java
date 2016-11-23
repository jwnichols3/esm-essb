package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.test;

import java.util.Calendar;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class SampleHttpRequestCreator
{
    public static IHttpRequest createSampleObject ( IHttpRequestFinder finder )
    {
        IHttpRequest object = finder.newInstance();
        object.setAuthType             ( "test string for 'auth_type'" );
        object.setContextPath          ( "test string for 'context_path'" );
        object.setMethod               ( "test string for 'method'" );
        object.setPathInfo             ( "test string for 'path_info'" );
        object.setPathInfoTranslated   ( "test string for 'path_info_translated'" );
        object.setRemoteUser           ( "test string for 'remote_user'" );
        object.setRequestedSessionID   ( "test string for 'requested_session_id'" );
        object.setRequestUri           ( "test string for 'request_uri'" );
        object.setRequestUrl           ( "test string for 'request_url'" );
        object.setServletPath          ( "test string for 'servlet_path'" );
        object.setUserPrincipal        ( "test string for 'user_principal'" );
        object.setCharacterEncoding    ( "test string for 'character_encoding'" );
        object.setContentType          ( "test string for 'content_type'" );
        object.setLocalAddress         ( "test string for 'local_address'" );
        object.setProtocol             ( "test string for 'protocol'" );
        object.setRemoteAddress        ( "test string for 'remote_address'" );
        object.setRemoteHost           ( "test string for 'remote_host'" );
        object.setScheme               ( "test string for 'scheme'" );
        object.setServerName           ( "test string for 'server_name'" );
        object.setRequestTime          ( Calendar.getInstance() );
        object.setProcessTime          ( Calendar.getInstance() );
        object.setContentLength        ( 5 );
        object.setLocalPort            ( 5 );
        object.setRemotePort           ( 5 );
        object.setServerPort           ( 5 );
        object.setIsProcessed          ( false );
        object.setWasSuccessful        ( 5 );
        object.setReturnCode           ( 5 );

        return object;
    }

    public static IHttpRequest createUpdateObject ( IHttpRequestFinder finder )
    {
        IHttpRequest object = finder.newInstance();
        object.setAuthType             ( "updated string for 'auth_type'" );
        object.setContextPath          ( "updated string for 'context_path'" );
        object.setMethod               ( "updated string for 'method'" );
        object.setPathInfo             ( "updated string for 'path_info'" );
        object.setPathInfoTranslated   ( "updated string for 'path_info_translated'" );
        object.setRemoteUser           ( "updated string for 'remote_user'" );
        object.setRequestedSessionID   ( "updated string for 'requested_session_id'" );
        object.setRequestUri           ( "updated string for 'request_uri'" );
        object.setRequestUrl           ( "updated string for 'request_url'" );
        object.setServletPath          ( "updated string for 'servlet_path'" );
        object.setUserPrincipal        ( "updated string for 'user_principal'" );
        object.setCharacterEncoding    ( "updated string for 'character_encoding'" );
        object.setContentType          ( "updated string for 'content_type'" );
        object.setLocalAddress         ( "updated string for 'local_address'" );
        object.setProtocol             ( "updated string for 'protocol'" );
        object.setRemoteAddress        ( "updated string for 'remote_address'" );
        object.setRemoteHost           ( "updated string for 'remote_host'" );
        object.setScheme               ( "updated string for 'scheme'" );
        object.setServerName           ( "updated string for 'server_name'" );
        object.setRequestTime          ( Calendar.getInstance() );
        object.setProcessTime          ( Calendar.getInstance() );
        object.setContentLength        ( 9 );
        object.setLocalPort            ( 9 );
        object.setRemotePort           ( 9 );
        object.setServerPort           ( 9 );
        object.setIsProcessed          ( true );
        object.setWasSuccessful        ( 9 );
        object.setReturnCode           ( 9 );

        return object;
    }
}