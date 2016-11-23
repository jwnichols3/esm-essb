package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class HttpRequestForm extends ActionForm implements IHttpRequest
{
    /**
     *
     */
    final private static long serialVersionUID = -3122276309311362750L;
    final private static Logger _log           = Logger.getLogger ( HttpRequestForm.class );

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

    public IHttpRequest getValue()
    {
        try
        {
            Class <? extends IHttpRequest> c = this.getClass();
            IHttpRequest object = c.newInstance();
            object.setValue ( (IHttpRequest) this );

            return object;
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "IllegalAccessException when trying to instantiate class=" + this.getClass().getName(), exception );
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class=" + this.getClass().getName(), exception );
        }

        return null;
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


    /**
     *  Setter for helper variable 'request_time_helper_year
     */
    public void setRequestTimeHelperYear ( int year )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.YEAR, year );
    }

    /**
     *  Getter for helper variable 'request_time_helper_year
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperYear()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.YEAR ) : -1;
    }

    /**
     *  Setter for helper variable 'request_time_helper_month
     */
    public void setRequestTimeHelperMonth ( int month )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.MONTH, month );
    }

    /**
     *  Getter for helper variable 'request_time_helper_month
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperMonth()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.MONTH ) : -1;
    }

    /**
     *  Setter for helper variable 'request_time_helper_date
     */
    public void setRequestTimeHelperDate ( int date )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.DATE, date );
    }

    /**
     *  Getter for helper variable 'request_time_helper_date
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperDate()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.DATE ) : -1;
    }

    /**
     *  Setter for helper variable 'request_time_helper_hour
     */
    public void setRequestTimeHelperHour ( int hour )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.HOUR_OF_DAY, hour );
    }

    /**
     *  Getter for helper variable 'request_time_helper_hour
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperHour()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.HOUR_OF_DAY ) : -1;
    }

    /**
     *  Setter for helper variable 'request_time_helper_minute
     */
    public void setRequestTimeHelperMinute ( int minute )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.MINUTE, minute );
    }

    /**
     *  Getter for helper variable 'request_time_helper_minute
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperMinute()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.MINUTE ) : -1;
    }

    /**
     *  Setter for helper variable 'request_time_helper_second
     */
    public void setRequestTimeHelperSecond ( int second )
    {
        if ( null == RequestTime )         {
            RequestTime = Calendar.getInstance();
        }

        RequestTime.set ( Calendar.SECOND, second );
    }

    /**
     *  Getter for helper variable 'request_time_helper_second
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getRequestTimeHelperSecond()
    {
        return ( null != RequestTime )? RequestTime.get ( Calendar.SECOND ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_year
     */
    public void setProcessTimeHelperYear ( int year )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.YEAR, year );
    }

    /**
     *  Getter for helper variable 'process_time_helper_year
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperYear()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.YEAR ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_month
     */
    public void setProcessTimeHelperMonth ( int month )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.MONTH, month );
    }

    /**
     *  Getter for helper variable 'process_time_helper_month
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperMonth()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.MONTH ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_date
     */
    public void setProcessTimeHelperDate ( int date )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.DATE, date );
    }

    /**
     *  Getter for helper variable 'process_time_helper_date
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperDate()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.DATE ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_hour
     */
    public void setProcessTimeHelperHour ( int hour )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.HOUR_OF_DAY, hour );
    }

    /**
     *  Getter for helper variable 'process_time_helper_hour
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperHour()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.HOUR_OF_DAY ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_minute
     */
    public void setProcessTimeHelperMinute ( int minute )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.MINUTE, minute );
    }

    /**
     *  Getter for helper variable 'process_time_helper_minute
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperMinute()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.MINUTE ) : -1;
    }

    /**
     *  Setter for helper variable 'process_time_helper_second
     */
    public void setProcessTimeHelperSecond ( int second )
    {
        if ( null == ProcessTime )         {
            ProcessTime = Calendar.getInstance();
        }

        ProcessTime.set ( Calendar.SECOND, second );
    }

    /**
     *  Getter for helper variable 'process_time_helper_second
     *  @return the timestamp value if it exists, -1 otherwise
     */
    public int getProcessTimeHelperSecond()
    {
        return ( null != ProcessTime )? ProcessTime.get ( Calendar.SECOND ) : -1;
    }


    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.getMap();
        }
        else
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass() );
                message.get().append ( "::reset () - could not find PortletPreferences" );
            _log.warn ( message.get().toString() );
        }
    }
}