package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.util.Calendar;


public interface IHttpRequest
{
    //  HTTP Session Attribute Names
    final public static String SESSION_BROWSE  = "HttpRequest-BrowseRecords";
    final public static String SESSION_SELECT  = "HttpRequest-SelectRecord";
    final public static String SESSION_MAP     = "HttpRequest-MapByPrimaryKey";

    public void setValue ( IHttpRequest object );
    public String toXML();

    public void setRequestID ( String request_id );
    public String getRequestID();

    public void setAuthType ( String auth_type );
    public String getAuthType();

    public void setContextPath ( String context_path );
    public String getContextPath();

    public void setMethod ( String method );
    public String getMethod();

    public void setPathInfo ( String path_info );
    public String getPathInfo();

    public void setPathInfoTranslated ( String path_info_translated );
    public String getPathInfoTranslated();

    public void setRemoteUser ( String remote_user );
    public String getRemoteUser();

    public void setRequestedSessionID ( String requested_session_id );
    public String getRequestedSessionID();

    public void setRequestUri ( String request_uri );
    public String getRequestUri();

    public void setRequestUrl ( String request_url );
    public String getRequestUrl();

    public void setServletPath ( String servlet_path );
    public String getServletPath();

    public void setUserPrincipal ( String user_principal );
    public String getUserPrincipal();

    public void setCharacterEncoding ( String character_encoding );
    public String getCharacterEncoding();

    public void setContentType ( String content_type );
    public String getContentType();

    public void setLocalAddress ( String local_address );
    public String getLocalAddress();

    public void setProtocol ( String protocol );
    public String getProtocol();

    public void setRemoteAddress ( String remote_address );
    public String getRemoteAddress();

    public void setRemoteHost ( String remote_host );
    public String getRemoteHost();

    public void setScheme ( String scheme );
    public String getScheme();

    public void setServerName ( String server_name );
    public String getServerName();

    public void setRequestTime ( Calendar request_time );
    public Calendar getRequestTime();

    public void setProcessTime ( Calendar process_time );
    public Calendar getProcessTime();

    public void setContentLength ( String content_length );
    public void setContentLength ( long content_length );
    public long getContentLength();

    public void setLocalPort ( String local_port );
    public void setLocalPort ( long local_port );
    public long getLocalPort();

    public void setRemotePort ( String remote_port );
    public void setRemotePort ( long remote_port );
    public long getRemotePort();

    public void setServerPort ( String server_port );
    public void setServerPort ( long server_port );
    public long getServerPort();

    public void setIsProcessed ( String is_processed );
    public void setIsProcessed ( int is_processed );
    public void setIsProcessed ( boolean is_processed );
    public boolean getIsProcessed();

    public void setWasSuccessful ( String was_successful );
    public void setWasSuccessful ( long was_successful );
    public long getWasSuccessful();

    public void setReturnCode ( String return_code );
    public void setReturnCode ( long return_code );
    public long getReturnCode();
}
