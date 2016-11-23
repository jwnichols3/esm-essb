package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Properties;
import org.apache.log4j.Logger;


public abstract class AbstractHttpRequest extends CommonObject implements Serializable, IHttpRequest
{
    /*
     *
     */
    final private static long serialVersionUID = 7447283277959682964L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( AbstractHttpRequest.class );

    private String    RequestID            = null;
    private String    AuthType             = null;
    private String    ContextPath          = null;
    private String    Method               = null;
    private String    PathInfo             = null;
    private String    PathInfoTranslated   = null;
    private String    RemoteUser           = null;
    private String    RequestedSessionID   = null;
    private String    RequestUri           = null;
    private String    RequestUrl           = null;
    private String    ServletPath          = null;
    private String    UserPrincipal        = null;
    private String    CharacterEncoding    = null;
    private String    ContentType          = null;
    private String    LocalAddress         = null;
    private String    Protocol             = null;
    private String    RemoteAddress        = null;
    private String    RemoteHost           = null;
    private String    Scheme               = null;
    private String    ServerName           = null;
    private Calendar  RequestTime          = Calendar.getInstance();
    private Calendar  ProcessTime          = Calendar.getInstance();
    private long      ContentLength        = 0;
    private long      LocalPort            = 0;
    private long      RemotePort           = 0;
    private long      ServerPort           = 0;
    private boolean   IsProcessed          = false;
    private long      WasSuccessful        = 0;
    private long      ReturnCode           = 0;

    //  Default values
    private static String DefaultValuesForAuthType[]              = null;
    private static String DefaultValuesForContextPath[]           = null;
    private static String DefaultValuesForMethod[]                = null;
    private static String DefaultValuesForPathInfo[]              = null;
    private static String DefaultValuesForPathInfoTranslated[]    = null;
    private static String DefaultValuesForRemoteUser[]            = null;
    private static String DefaultValuesForRequestedSessionID[]    = null;
    private static String DefaultValuesForRequestUri[]            = null;
    private static String DefaultValuesForRequestUrl[]            = null;
    private static String DefaultValuesForServletPath[]           = null;
    private static String DefaultValuesForUserPrincipal[]         = null;
    private static String DefaultValuesForCharacterEncoding[]     = null;
    private static String DefaultValuesForContentType[]           = null;
    private static String DefaultValuesForLocalAddress[]          = null;
    private static String DefaultValuesForProtocol[]              = null;
    private static String DefaultValuesForRemoteAddress[]         = null;
    private static String DefaultValuesForRemoteHost[]            = null;
    private static String DefaultValuesForScheme[]                = null;
    private static String DefaultValuesForServerName[]            = null;
    private static long DefaultValuesForContentLength[]         = null;
    private static long DefaultValuesForLocalPort[]             = null;
    private static long DefaultValuesForRemotePort[]            = null;
    private static long DefaultValuesForServerPort[]            = null;
    private static long DefaultValuesForWasSuccessful[]         = null;
    private static long DefaultValuesForReturnCode[]            = null;

    static
    {
        initializeDefaultValues();
    }

    private static void initializeDefaultValues()
    {
        Properties properties = loadProperties ( "content/defaults/http_request.properties" );
        if ( null == properties )
        {
            return;
        }

        DefaultValuesForAuthType                = retrieveStringDefaultValues ( properties, "database.http_request.auth_type."               );
        DefaultValuesForContextPath             = retrieveStringDefaultValues ( properties, "database.http_request.context_path."            );
        DefaultValuesForMethod                  = retrieveStringDefaultValues ( properties, "database.http_request.method."                  );
        DefaultValuesForPathInfo                = retrieveStringDefaultValues ( properties, "database.http_request.path_info."               );
        DefaultValuesForPathInfoTranslated      = retrieveStringDefaultValues ( properties, "database.http_request.path_info_translated."    );
        DefaultValuesForRemoteUser              = retrieveStringDefaultValues ( properties, "database.http_request.remote_user."             );
        DefaultValuesForRequestedSessionID      = retrieveStringDefaultValues ( properties, "database.http_request.requested_session_id."    );
        DefaultValuesForRequestUri              = retrieveStringDefaultValues ( properties, "database.http_request.request_uri."             );
        DefaultValuesForRequestUrl              = retrieveStringDefaultValues ( properties, "database.http_request.request_url."             );
        DefaultValuesForServletPath             = retrieveStringDefaultValues ( properties, "database.http_request.servlet_path."            );
        DefaultValuesForUserPrincipal           = retrieveStringDefaultValues ( properties, "database.http_request.user_principal."          );
        DefaultValuesForCharacterEncoding       = retrieveStringDefaultValues ( properties, "database.http_request.character_encoding."      );
        DefaultValuesForContentType             = retrieveStringDefaultValues ( properties, "database.http_request.content_type."            );
        DefaultValuesForLocalAddress            = retrieveStringDefaultValues ( properties, "database.http_request.local_address."           );
        DefaultValuesForProtocol                = retrieveStringDefaultValues ( properties, "database.http_request.protocol."                );
        DefaultValuesForRemoteAddress           = retrieveStringDefaultValues ( properties, "database.http_request.remote_address."          );
        DefaultValuesForRemoteHost              = retrieveStringDefaultValues ( properties, "database.http_request.remote_host."             );
        DefaultValuesForScheme                  = retrieveStringDefaultValues ( properties, "database.http_request.scheme."                  );
        DefaultValuesForServerName              = retrieveStringDefaultValues ( properties, "database.http_request.server_name."             );
        DefaultValuesForContentLength           = retrieveLongDefaultValues ( properties, "database.http_request.content_length."          );
        DefaultValuesForLocalPort               = retrieveLongDefaultValues ( properties, "database.http_request.local_port."              );
        DefaultValuesForRemotePort              = retrieveLongDefaultValues ( properties, "database.http_request.remote_port."             );
        DefaultValuesForServerPort              = retrieveLongDefaultValues ( properties, "database.http_request.server_port."             );
        DefaultValuesForWasSuccessful           = retrieveLongDefaultValues ( properties, "database.http_request.was_successful."          );
        DefaultValuesForReturnCode              = retrieveLongDefaultValues ( properties, "database.http_request.return_code."             );
    }

    public void setValue ( IHttpRequest object )
    {
        setAuthType             ( object.getAuthType()             );
        setContextPath          ( object.getContextPath()          );
        setMethod               ( object.getMethod()               );
        setPathInfo             ( object.getPathInfo()             );
        setPathInfoTranslated   ( object.getPathInfoTranslated()   );
        setRemoteUser           ( object.getRemoteUser()           );
        setRequestedSessionID   ( object.getRequestedSessionID()   );
        setRequestUri           ( object.getRequestUri()           );
        setRequestUrl           ( object.getRequestUrl()           );
        setServletPath          ( object.getServletPath()          );
        setUserPrincipal        ( object.getUserPrincipal()        );
        setCharacterEncoding    ( object.getCharacterEncoding()    );
        setContentType          ( object.getContentType()          );
        setLocalAddress         ( object.getLocalAddress()         );
        setProtocol             ( object.getProtocol()             );
        setRemoteAddress        ( object.getRemoteAddress()        );
        setRemoteHost           ( object.getRemoteHost()           );
        setScheme               ( object.getScheme()               );
        setServerName           ( object.getServerName()           );
        setRequestTime          ( object.getRequestTime()          );
        setProcessTime          ( object.getProcessTime()          );
        setContentLength        ( object.getContentLength()        );
        setLocalPort            ( object.getLocalPort()            );
        setRemotePort           ( object.getRemotePort()           );
        setServerPort           ( object.getServerPort()           );
        setIsProcessed          ( object.getIsProcessed()          );
        setWasSuccessful        ( object.getWasSuccessful()        );
        setReturnCode           ( object.getReturnCode()           );
    }

    final public static String[] getDefaultValuesForAuthType()
    {
        return DefaultValuesForAuthType;
    }

    final public static String[] getDefaultValuesForContextPath()
    {
        return DefaultValuesForContextPath;
    }

    final public static String[] getDefaultValuesForMethod()
    {
        return DefaultValuesForMethod;
    }

    final public static String[] getDefaultValuesForPathInfo()
    {
        return DefaultValuesForPathInfo;
    }

    final public static String[] getDefaultValuesForPathInfoTranslated()
    {
        return DefaultValuesForPathInfoTranslated;
    }

    final public static String[] getDefaultValuesForRemoteUser()
    {
        return DefaultValuesForRemoteUser;
    }

    final public static String[] getDefaultValuesForRequestedSessionID()
    {
        return DefaultValuesForRequestedSessionID;
    }

    final public static String[] getDefaultValuesForRequestUri()
    {
        return DefaultValuesForRequestUri;
    }

    final public static String[] getDefaultValuesForRequestUrl()
    {
        return DefaultValuesForRequestUrl;
    }

    final public static String[] getDefaultValuesForServletPath()
    {
        return DefaultValuesForServletPath;
    }

    final public static String[] getDefaultValuesForUserPrincipal()
    {
        return DefaultValuesForUserPrincipal;
    }

    final public static String[] getDefaultValuesForCharacterEncoding()
    {
        return DefaultValuesForCharacterEncoding;
    }

    final public static String[] getDefaultValuesForContentType()
    {
        return DefaultValuesForContentType;
    }

    final public static String[] getDefaultValuesForLocalAddress()
    {
        return DefaultValuesForLocalAddress;
    }

    final public static String[] getDefaultValuesForProtocol()
    {
        return DefaultValuesForProtocol;
    }

    final public static String[] getDefaultValuesForRemoteAddress()
    {
        return DefaultValuesForRemoteAddress;
    }

    final public static String[] getDefaultValuesForRemoteHost()
    {
        return DefaultValuesForRemoteHost;
    }

    final public static String[] getDefaultValuesForScheme()
    {
        return DefaultValuesForScheme;
    }

    final public static String[] getDefaultValuesForServerName()
    {
        return DefaultValuesForServerName;
    }

    final public static long[] getDefaultValuesForContentLength()
    {
        return DefaultValuesForContentLength;
    }

    final public static long[] getDefaultValuesForLocalPort()
    {
        return DefaultValuesForLocalPort;
    }

    final public static long[] getDefaultValuesForRemotePort()
    {
        return DefaultValuesForRemotePort;
    }

    final public static long[] getDefaultValuesForServerPort()
    {
        return DefaultValuesForServerPort;
    }

    final public static long[] getDefaultValuesForWasSuccessful()
    {
        return DefaultValuesForWasSuccessful;
    }

    final public static long[] getDefaultValuesForReturnCode()
    {
        return DefaultValuesForReturnCode;
    }

    public boolean equals ( Object object )
    {
        if ( object instanceof IHttpRequest )
        {
            boolean match_result = true;
            IHttpRequest data = (IHttpRequest) object;
                match_result = match_result && compareStrings   ( AuthType,             data.getAuthType()              );
                match_result = match_result && compareStrings   ( ContextPath,          data.getContextPath()           );
                match_result = match_result && compareStrings   ( Method,               data.getMethod()                );
                match_result = match_result && compareStrings   ( PathInfo,             data.getPathInfo()              );
                match_result = match_result && compareStrings   ( PathInfoTranslated,   data.getPathInfoTranslated()    );
                match_result = match_result && compareStrings   ( RemoteUser,           data.getRemoteUser()            );
                match_result = match_result && compareStrings   ( RequestedSessionID,   data.getRequestedSessionID()    );
                match_result = match_result && compareStrings   ( RequestUri,           data.getRequestUri()            );
                match_result = match_result && compareStrings   ( RequestUrl,           data.getRequestUrl()            );
                match_result = match_result && compareStrings   ( ServletPath,          data.getServletPath()           );
                match_result = match_result && compareStrings   ( UserPrincipal,        data.getUserPrincipal()         );
                match_result = match_result && compareStrings   ( CharacterEncoding,    data.getCharacterEncoding()     );
                match_result = match_result && compareStrings   ( ContentType,          data.getContentType()           );
                match_result = match_result && compareStrings   ( LocalAddress,         data.getLocalAddress()          );
                match_result = match_result && compareStrings   ( Protocol,             data.getProtocol()              );
                match_result = match_result && compareStrings   ( RemoteAddress,        data.getRemoteAddress()         );
                match_result = match_result && compareStrings   ( RemoteHost,           data.getRemoteHost()            );
                match_result = match_result && compareStrings   ( Scheme,               data.getScheme()                );
                match_result = match_result && compareStrings   ( ServerName,           data.getServerName()            );
                match_result = match_result && compareCalendars ( RequestTime,          data.getRequestTime()           );
                match_result = match_result && compareCalendars ( ProcessTime,          data.getProcessTime()           );
                match_result = match_result && ( ContentLength        == data.getContentLength()        );
                match_result = match_result && ( LocalPort            == data.getLocalPort()            );
                match_result = match_result && ( RemotePort           == data.getRemotePort()           );
                match_result = match_result && ( ServerPort           == data.getServerPort()           );
                match_result = match_result && ( IsProcessed          == data.getIsProcessed()          );
                match_result = match_result && ( WasSuccessful        == data.getWasSuccessful()        );
                match_result = match_result && ( ReturnCode           == data.getReturnCode()           );
            return match_result;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( " ( " );
            message.get().append ( "RequestID=" );
            message.get().append ( getRequestID() );
            message.get().append ( ", AuthType=" );
            message.get().append ( getAuthType() );
            message.get().append ( ", ContextPath=" );
            message.get().append ( getContextPath() );
            message.get().append ( ", Method=" );
            message.get().append ( getMethod() );
            message.get().append ( ", PathInfo=" );
            message.get().append ( getPathInfo() );
            message.get().append ( ", PathInfoTranslated=" );
            message.get().append ( getPathInfoTranslated() );
            message.get().append ( ", RemoteUser=" );
            message.get().append ( getRemoteUser() );
            message.get().append ( ", RequestedSessionID=" );
            message.get().append ( getRequestedSessionID() );
            message.get().append ( ", RequestUri=" );
            message.get().append ( getRequestUri() );
            message.get().append ( ", RequestUrl=" );
            message.get().append ( getRequestUrl() );
            message.get().append ( ", ServletPath=" );
            message.get().append ( getServletPath() );
            message.get().append ( ", UserPrincipal=" );
            message.get().append ( getUserPrincipal() );
            message.get().append ( ", CharacterEncoding=" );
            message.get().append ( getCharacterEncoding() );
            message.get().append ( ", ContentType=" );
            message.get().append ( getContentType() );
            message.get().append ( ", LocalAddress=" );
            message.get().append ( getLocalAddress() );
            message.get().append ( ", Protocol=" );
            message.get().append ( getProtocol() );
            message.get().append ( ", RemoteAddress=" );
            message.get().append ( getRemoteAddress() );
            message.get().append ( ", RemoteHost=" );
            message.get().append ( getRemoteHost() );
            message.get().append ( ", Scheme=" );
            message.get().append ( getScheme() );
            message.get().append ( ", ServerName=" );
            message.get().append ( getServerName() );
            message.get().append ( ", RequestTime=" );
            message.get().append ( CommonObject.formatDate ( getRequestTime() ) );
            message.get().append ( ", ProcessTime=" );
            message.get().append ( CommonObject.formatDate ( getProcessTime() ) );
            message.get().append ( ", ContentLength=" );
            message.get().append ( getContentLength() );
            message.get().append ( ", LocalPort=" );
            message.get().append ( getLocalPort() );
            message.get().append ( ", RemotePort=" );
            message.get().append ( getRemotePort() );
            message.get().append ( ", ServerPort=" );
            message.get().append ( getServerPort() );
            message.get().append ( ", IsProcessed=" );
            message.get().append ( getIsProcessed() );
            message.get().append ( ", WasSuccessful=" );
            message.get().append ( getWasSuccessful() );
            message.get().append ( ", ReturnCode=" );
            message.get().append ( getReturnCode() );
            message.get().append ( " )" );
        return message.get().toString();
    }

    public String toXML()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "<item classname=\"" );
            message.get().append ( getClass().getName() );
            message.get().append ( "\">\n" );
            message.get().append ( "    <request_id><![CDATA[" );
            message.get().append ( getRequestID() );
            message.get().append ( "]]></request_id>\n" );
            message.get().append ( "    <auth_type><![CDATA[" );
            message.get().append ( getAuthType() );
            message.get().append ( "]]></auth_type>\n" );
            message.get().append ( "    <context_path><![CDATA[" );
            message.get().append ( getContextPath() );
            message.get().append ( "]]></context_path>\n" );
            message.get().append ( "    <method><![CDATA[" );
            message.get().append ( getMethod() );
            message.get().append ( "]]></method>\n" );
            message.get().append ( "    <path_info><![CDATA[" );
            message.get().append ( getPathInfo() );
            message.get().append ( "]]></path_info>\n" );
            message.get().append ( "    <path_info_translated><![CDATA[" );
            message.get().append ( getPathInfoTranslated() );
            message.get().append ( "]]></path_info_translated>\n" );
            message.get().append ( "    <remote_user><![CDATA[" );
            message.get().append ( getRemoteUser() );
            message.get().append ( "]]></remote_user>\n" );
            message.get().append ( "    <requested_session_id><![CDATA[" );
            message.get().append ( getRequestedSessionID() );
            message.get().append ( "]]></requested_session_id>\n" );
            message.get().append ( "    <request_uri><![CDATA[" );
            message.get().append ( getRequestUri() );
            message.get().append ( "]]></request_uri>\n" );
            message.get().append ( "    <request_url><![CDATA[" );
            message.get().append ( getRequestUrl() );
            message.get().append ( "]]></request_url>\n" );
            message.get().append ( "    <servlet_path><![CDATA[" );
            message.get().append ( getServletPath() );
            message.get().append ( "]]></servlet_path>\n" );
            message.get().append ( "    <user_principal><![CDATA[" );
            message.get().append ( getUserPrincipal() );
            message.get().append ( "]]></user_principal>\n" );
            message.get().append ( "    <character_encoding><![CDATA[" );
            message.get().append ( getCharacterEncoding() );
            message.get().append ( "]]></character_encoding>\n" );
            message.get().append ( "    <content_type><![CDATA[" );
            message.get().append ( getContentType() );
            message.get().append ( "]]></content_type>\n" );
            message.get().append ( "    <local_address><![CDATA[" );
            message.get().append ( getLocalAddress() );
            message.get().append ( "]]></local_address>\n" );
            message.get().append ( "    <protocol><![CDATA[" );
            message.get().append ( getProtocol() );
            message.get().append ( "]]></protocol>\n" );
            message.get().append ( "    <remote_address><![CDATA[" );
            message.get().append ( getRemoteAddress() );
            message.get().append ( "]]></remote_address>\n" );
            message.get().append ( "    <remote_host><![CDATA[" );
            message.get().append ( getRemoteHost() );
            message.get().append ( "]]></remote_host>\n" );
            message.get().append ( "    <scheme><![CDATA[" );
            message.get().append ( getScheme() );
            message.get().append ( "]]></scheme>\n" );
            message.get().append ( "    <server_name><![CDATA[" );
            message.get().append ( getServerName() );
            message.get().append ( "]]></server_name>\n" );
            message.get().append ( "    <request_time><![CDATA[" );
            message.get().append ( CommonObject.formatDate ( getRequestTime() ) );
            message.get().append ( "]]></request_time>\n" );
            message.get().append ( "    <process_time><![CDATA[" );
            message.get().append ( CommonObject.formatDate ( getProcessTime() ) );
            message.get().append ( "]]></process_time>\n" );
            message.get().append ( "    <content_length><![CDATA[" );
            message.get().append ( getContentLength() );
            message.get().append ( "]]></content_length>\n" );
            message.get().append ( "    <local_port><![CDATA[" );
            message.get().append ( getLocalPort() );
            message.get().append ( "]]></local_port>\n" );
            message.get().append ( "    <remote_port><![CDATA[" );
            message.get().append ( getRemotePort() );
            message.get().append ( "]]></remote_port>\n" );
            message.get().append ( "    <server_port><![CDATA[" );
            message.get().append ( getServerPort() );
            message.get().append ( "]]></server_port>\n" );
            message.get().append ( "    <is_processed><![CDATA[" );
            message.get().append ( getIsProcessed() );
            message.get().append ( "]]></is_processed>\n" );
            message.get().append ( "    <was_successful><![CDATA[" );
            message.get().append ( getWasSuccessful() );
            message.get().append ( "]]></was_successful>\n" );
            message.get().append ( "    <return_code><![CDATA[" );
            message.get().append ( getReturnCode() );
            message.get().append ( "]]></return_code>\n" );
            message.get().append ( "</item>" );
        return message.get().toString();
    }

    /**
     *  Setter method for the 'request_id' column
     *
     *  @param request_id  The new value for the 'request_id' column
     */
    public void setRequestID ( String request_id )
    {
        this.RequestID = request_id;
    }

    /**
     *  Getter method for the 'request_id' column
     *  @return The new value for the 'request_id' column
     */
    public String getRequestID ()
    {
        return RequestID;
    }

    /**
     *  Setter method for the 'auth_type' column
     *
     *  @param auth_type  The new value for the 'auth_type' column
     */
    public void setAuthType ( String auth_type )
    {
        this.AuthType = auth_type;
    }

    /**
     *  Getter method for the 'auth_type' column
     *  @return The new value for the 'auth_type' column
     */
    public String getAuthType ()
    {
        return AuthType;
    }

    /**
     *  Setter method for the 'context_path' column
     *
     *  @param context_path  The new value for the 'context_path' column
     */
    public void setContextPath ( String context_path )
    {
        this.ContextPath = context_path;
    }

    /**
     *  Getter method for the 'context_path' column
     *  @return The new value for the 'context_path' column
     */
    public String getContextPath ()
    {
        return ContextPath;
    }

    /**
     *  Setter method for the 'method' column
     *
     *  @param method  The new value for the 'method' column
     */
    public void setMethod ( String method )
    {
        this.Method = method;
    }

    /**
     *  Getter method for the 'method' column
     *  @return The new value for the 'method' column
     */
    public String getMethod ()
    {
        return Method;
    }

    /**
     *  Setter method for the 'path_info' column
     *
     *  @param path_info  The new value for the 'path_info' column
     */
    public void setPathInfo ( String path_info )
    {
        this.PathInfo = path_info;
    }

    /**
     *  Getter method for the 'path_info' column
     *  @return The new value for the 'path_info' column
     */
    public String getPathInfo ()
    {
        return PathInfo;
    }

    /**
     *  Setter method for the 'path_info_translated' column
     *
     *  @param path_info_translated  The new value for the 'path_info_translated' column
     */
    public void setPathInfoTranslated ( String path_info_translated )
    {
        this.PathInfoTranslated = path_info_translated;
    }

    /**
     *  Getter method for the 'path_info_translated' column
     *  @return The new value for the 'path_info_translated' column
     */
    public String getPathInfoTranslated ()
    {
        return PathInfoTranslated;
    }

    /**
     *  Setter method for the 'remote_user' column
     *
     *  @param remote_user  The new value for the 'remote_user' column
     */
    public void setRemoteUser ( String remote_user )
    {
        this.RemoteUser = remote_user;
    }

    /**
     *  Getter method for the 'remote_user' column
     *  @return The new value for the 'remote_user' column
     */
    public String getRemoteUser ()
    {
        return RemoteUser;
    }

    /**
     *  Setter method for the 'requested_session_id' column
     *
     *  @param requested_session_id  The new value for the 'requested_session_id' column
     */
    public void setRequestedSessionID ( String requested_session_id )
    {
        this.RequestedSessionID = requested_session_id;
    }

    /**
     *  Getter method for the 'requested_session_id' column
     *  @return The new value for the 'requested_session_id' column
     */
    public String getRequestedSessionID ()
    {
        return RequestedSessionID;
    }

    /**
     *  Setter method for the 'request_uri' column
     *
     *  @param request_uri  The new value for the 'request_uri' column
     */
    public void setRequestUri ( String request_uri )
    {
        this.RequestUri = request_uri;
    }

    /**
     *  Getter method for the 'request_uri' column
     *  @return The new value for the 'request_uri' column
     */
    public String getRequestUri ()
    {
        return RequestUri;
    }

    /**
     *  Setter method for the 'request_url' column
     *
     *  @param request_url  The new value for the 'request_url' column
     */
    public void setRequestUrl ( String request_url )
    {
        this.RequestUrl = request_url;
    }

    /**
     *  Getter method for the 'request_url' column
     *  @return The new value for the 'request_url' column
     */
    public String getRequestUrl ()
    {
        return RequestUrl;
    }

    /**
     *  Setter method for the 'servlet_path' column
     *
     *  @param servlet_path  The new value for the 'servlet_path' column
     */
    public void setServletPath ( String servlet_path )
    {
        this.ServletPath = servlet_path;
    }

    /**
     *  Getter method for the 'servlet_path' column
     *  @return The new value for the 'servlet_path' column
     */
    public String getServletPath ()
    {
        return ServletPath;
    }

    /**
     *  Setter method for the 'user_principal' column
     *
     *  @param user_principal  The new value for the 'user_principal' column
     */
    public void setUserPrincipal ( String user_principal )
    {
        this.UserPrincipal = user_principal;
    }

    /**
     *  Getter method for the 'user_principal' column
     *  @return The new value for the 'user_principal' column
     */
    public String getUserPrincipal ()
    {
        return UserPrincipal;
    }

    /**
     *  Setter method for the 'character_encoding' column
     *
     *  @param character_encoding  The new value for the 'character_encoding' column
     */
    public void setCharacterEncoding ( String character_encoding )
    {
        this.CharacterEncoding = character_encoding;
    }

    /**
     *  Getter method for the 'character_encoding' column
     *  @return The new value for the 'character_encoding' column
     */
    public String getCharacterEncoding ()
    {
        return CharacterEncoding;
    }

    /**
     *  Setter method for the 'content_type' column
     *
     *  @param content_type  The new value for the 'content_type' column
     */
    public void setContentType ( String content_type )
    {
        this.ContentType = content_type;
    }

    /**
     *  Getter method for the 'content_type' column
     *  @return The new value for the 'content_type' column
     */
    public String getContentType ()
    {
        return ContentType;
    }

    /**
     *  Setter method for the 'local_address' column
     *
     *  @param local_address  The new value for the 'local_address' column
     */
    public void setLocalAddress ( String local_address )
    {
        this.LocalAddress = local_address;
    }

    /**
     *  Getter method for the 'local_address' column
     *  @return The new value for the 'local_address' column
     */
    public String getLocalAddress ()
    {
        return LocalAddress;
    }

    /**
     *  Setter method for the 'protocol' column
     *
     *  @param protocol  The new value for the 'protocol' column
     */
    public void setProtocol ( String protocol )
    {
        this.Protocol = protocol;
    }

    /**
     *  Getter method for the 'protocol' column
     *  @return The new value for the 'protocol' column
     */
    public String getProtocol ()
    {
        return Protocol;
    }

    /**
     *  Setter method for the 'remote_address' column
     *
     *  @param remote_address  The new value for the 'remote_address' column
     */
    public void setRemoteAddress ( String remote_address )
    {
        this.RemoteAddress = remote_address;
    }

    /**
     *  Getter method for the 'remote_address' column
     *  @return The new value for the 'remote_address' column
     */
    public String getRemoteAddress ()
    {
        return RemoteAddress;
    }

    /**
     *  Setter method for the 'remote_host' column
     *
     *  @param remote_host  The new value for the 'remote_host' column
     */
    public void setRemoteHost ( String remote_host )
    {
        this.RemoteHost = remote_host;
    }

    /**
     *  Getter method for the 'remote_host' column
     *  @return The new value for the 'remote_host' column
     */
    public String getRemoteHost ()
    {
        return RemoteHost;
    }

    /**
     *  Setter method for the 'scheme' column
     *
     *  @param scheme  The new value for the 'scheme' column
     */
    public void setScheme ( String scheme )
    {
        this.Scheme = scheme;
    }

    /**
     *  Getter method for the 'scheme' column
     *  @return The new value for the 'scheme' column
     */
    public String getScheme ()
    {
        return Scheme;
    }

    /**
     *  Setter method for the 'server_name' column
     *
     *  @param server_name  The new value for the 'server_name' column
     */
    public void setServerName ( String server_name )
    {
        this.ServerName = server_name;
    }

    /**
     *  Getter method for the 'server_name' column
     *  @return The new value for the 'server_name' column
     */
    public String getServerName ()
    {
        return ServerName;
    }

    /**
     *  Setter method for the 'request_time' column
     *
     *  @param request_time  The new value for the 'request_time' column
     */
    public void setRequestTime ( Calendar request_time )
    {
        this.RequestTime = request_time;
    }

    /**
     *  Getter method for the 'request_time' column
     *  @return The new value for the 'request_time' column
     */
    public Calendar getRequestTime ()
    {
        return RequestTime;
    }

    /**
     *  Setter method for the 'process_time' column
     *
     *  @param process_time  The new value for the 'process_time' column
     */
    public void setProcessTime ( Calendar process_time )
    {
        this.ProcessTime = process_time;
    }

    /**
     *  Getter method for the 'process_time' column
     *  @return The new value for the 'process_time' column
     */
    public Calendar getProcessTime ()
    {
        return ProcessTime;
    }

    /**
     *  Setter method for the 'content_length' column
     *
     *  @param content_length  The new value for the 'content_length' column
     */
    public void setContentLength ( String content_length )
    {
        this.ContentLength = Long .parseLong  ( content_length );
    }

    /**
     *  Setter method for the 'content_length' column
     *
     *  @param content_length  The new value for the 'content_length' column
     */
    public void setContentLength ( long content_length )
    {
        this.ContentLength = content_length;
    }

    /**
     *  Getter method for the 'content_length' column
     *  @return The new value for the 'content_length' column
     */
    public long getContentLength ()
    {
        return ContentLength;
    }

    /**
     *  Setter method for the 'local_port' column
     *
     *  @param local_port  The new value for the 'local_port' column
     */
    public void setLocalPort ( String local_port )
    {
        this.LocalPort = Long .parseLong  ( local_port );
    }

    /**
     *  Setter method for the 'local_port' column
     *
     *  @param local_port  The new value for the 'local_port' column
     */
    public void setLocalPort ( long local_port )
    {
        this.LocalPort = local_port;
    }

    /**
     *  Getter method for the 'local_port' column
     *  @return The new value for the 'local_port' column
     */
    public long getLocalPort ()
    {
        return LocalPort;
    }

    /**
     *  Setter method for the 'remote_port' column
     *
     *  @param remote_port  The new value for the 'remote_port' column
     */
    public void setRemotePort ( String remote_port )
    {
        this.RemotePort = Long .parseLong  ( remote_port );
    }

    /**
     *  Setter method for the 'remote_port' column
     *
     *  @param remote_port  The new value for the 'remote_port' column
     */
    public void setRemotePort ( long remote_port )
    {
        this.RemotePort = remote_port;
    }

    /**
     *  Getter method for the 'remote_port' column
     *  @return The new value for the 'remote_port' column
     */
    public long getRemotePort ()
    {
        return RemotePort;
    }

    /**
     *  Setter method for the 'server_port' column
     *
     *  @param server_port  The new value for the 'server_port' column
     */
    public void setServerPort ( String server_port )
    {
        this.ServerPort = Long .parseLong  ( server_port );
    }

    /**
     *  Setter method for the 'server_port' column
     *
     *  @param server_port  The new value for the 'server_port' column
     */
    public void setServerPort ( long server_port )
    {
        this.ServerPort = server_port;
    }

    /**
     *  Getter method for the 'server_port' column
     *  @return The new value for the 'server_port' column
     */
    public long getServerPort ()
    {
        return ServerPort;
    }

    /**
     *  Setter method for the 'is_processed' column
     *
     *  @param is_processed  The new value for the 'is_processed' column
     */
    public void setIsProcessed ( String is_processed )
    {
        this.IsProcessed = Boolean.parseBoolean ( is_processed );
    }

    /**
     *  Setter method for the 'is_processed' column
     *
     *  @param is_processed  The new value for the 'is_processed' column
     */
    public void setIsProcessed ( int is_processed )
    {
        this.IsProcessed = ( 1 == is_processed );
    }

    /**
     *  Setter method for the 'is_processed' column
     *
     *  @param is_processed  The new value for the 'is_processed' column
     */
    public void setIsProcessed ( boolean is_processed )
    {
        this.IsProcessed = is_processed;
    }

    /**
     *  Getter method for the 'is_processed' column
     *  @return The new value for the 'is_processed' column
     */
    public boolean getIsProcessed ()
    {
        return IsProcessed;
    }

    /**
     *  Setter method for the 'was_successful' column
     *
     *  @param was_successful  The new value for the 'was_successful' column
     */
    public void setWasSuccessful ( String was_successful )
    {
        this.WasSuccessful = Long .parseLong  ( was_successful );
    }

    /**
     *  Setter method for the 'was_successful' column
     *
     *  @param was_successful  The new value for the 'was_successful' column
     */
    public void setWasSuccessful ( long was_successful )
    {
        this.WasSuccessful = was_successful;
    }

    /**
     *  Getter method for the 'was_successful' column
     *  @return The new value for the 'was_successful' column
     */
    public long getWasSuccessful ()
    {
        return WasSuccessful;
    }

    /**
     *  Setter method for the 'return_code' column
     *
     *  @param return_code  The new value for the 'return_code' column
     */
    public void setReturnCode ( String return_code )
    {
        this.ReturnCode = Long .parseLong  ( return_code );
    }

    /**
     *  Setter method for the 'return_code' column
     *
     *  @param return_code  The new value for the 'return_code' column
     */
    public void setReturnCode ( long return_code )
    {
        this.ReturnCode = return_code;
    }

    /**
     *  Getter method for the 'return_code' column
     *  @return The new value for the 'return_code' column
     */
    public long getReturnCode ()
    {
        return ReturnCode;
    }

}