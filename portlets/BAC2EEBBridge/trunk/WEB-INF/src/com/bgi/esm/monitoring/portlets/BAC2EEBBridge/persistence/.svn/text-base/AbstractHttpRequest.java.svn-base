package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence;

import java.io.Serializable;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public abstract class AbstractHttpRequest implements Serializable
{
    final private static Logger _log    = Logger.getLogger ( AbstractHttpRequest.class );

    protected String RowID              = null;
    protected String AuthType           = null;
    protected String ContextPath        = null;
    protected String Method             = null;
    protected String PathInfo           = null;
    protected String PathInfoTranslated = null;
    protected String RemoteUser         = null;
    protected String RequestedSessionID = null;
    protected String RequestURI         = null;
    protected String RequestURL         = null;
    protected String ServletPath        = null;
    protected String UserPrincipal      = null;
    protected String CharacterEncoding  = null;
    protected String ContentType        = null;
    protected String LocalAddress       = null;
    protected String Protocol           = null;
    protected String RemoteAddress      = null;
    protected String RemoteHost         = null;
    protected String Scheme             = null;
    protected String ServerName         = null;

    protected Calendar RequestTime      = null;

    protected int ContentLength         = 0;
    protected int LocalPort             = 0;
    protected int RemotePort            = 0;
    protected int ServerPort            = 0;

    public AbstractHttpRequest()
    {
    }

    public void setValue ( HttpServletRequest request )
    {
        setAuthType           ( request.getAuthType()              );
        setContextPath        ( request.getContextPath()           );
        setMethod             ( request.getMethod()                );
        setPathInfo           ( request.getPathInfo()              );
        setPathInfoTranslated ( request.getPathTranslated()        );
        setRemoteUser         ( request.getRemoteUser()            );
        setRequestedSessionID ( request.getRequestedSessionId()    );
        setRequestURI         ( request.getRequestURI()            );
        setRequestURL         ( request.getRequestURL().toString() );
        setServletPath        ( request.getServletPath()           );
        if ( null != request.getUserPrincipal() )
        {
            setUserPrincipal      ( request.getUserPrincipal().toString() );
        }
        setCharacterEncoding  ( request.getCharacterEncoding()     );
        setContentType        ( request.getContentType()           );
        setLocalAddress       ( request.getLocalAddr()             );
        setProtocol           ( request.getProtocol()              );
        setRemoteAddress      ( request.getRemoteAddr()            );
        setRemoteHost         ( request.getRemoteHost()            );
        setScheme             ( request.getScheme()                );
        setServerName         ( request.getServerName()            );
        setContentLength      ( request.getContentLength()         );
        setLocalPort          ( request.getLocalPort()             );
        setRemotePort         ( request.getRemotePort()            );
        setServerPort         ( request.getServerPort()            );
        setRequestTime        ( Calendar.getInstance()             );
    }

    public void setValue ( AbstractHttpRequest request )
    {
        setAuthType           ( request.getAuthType()           );
        setContextPath        ( request.getContextPath()        );
        setMethod             ( request.getMethod()             );
        setPathInfo           ( request.getPathInfo()           );
        setPathInfoTranslated ( request.getPathInfoTranslated() );
        setRemoteUser         ( request.getRemoteUser()         );
        setRequestedSessionID ( request.getRequestedSessionID() );
        setRequestURI         ( request.getRequestURI()         );
        setRequestURL         ( request.getRequestURL()         );
        setServletPath        ( request.getServletPath()        );
        setUserPrincipal      ( request.getUserPrincipal()      );
        setCharacterEncoding  ( request.getCharacterEncoding()  );
        setContentType        ( request.getContentType()        );
        setLocalAddress       ( request.getLocalAddress()       );
        setProtocol           ( request.getProtocol()           );
        setRemoteAddress      ( request.getRemoteAddress()      );
        setRemoteHost         ( request.getRemoteHost()         );
        setScheme             ( request.getScheme()             );
        setServerName         ( request.getServerName()         );
        setContentLength      ( request.getContentLength()      );
        setRequestTime        ( request.getRequestTime()        );
        setLocalPort          ( request.getLocalPort()          );
        setRemotePort         ( request.getRemotePort()         );
        setServerPort         ( request.getServerPort()         );
    }

    public AbstractHttpRequest getValue()
    {
        try 
        {
            Class c = this.getClass();
            AbstractHttpRequest object = (AbstractHttpRequest) c.newInstance();
            object.setValue ( this );

            return object;
        } 
        catch ( IllegalAccessException exception ) 
        {
            _log.error ( exception.getMessage(), exception );

            return null;
        } 
        catch ( InstantiationException exception ) 
        {
            _log.error ( exception.getMessage(), exception );

            return null;
        }
    }

    public void setRowID ( String row_id )
    {
        RowID = row_id;
    }
    
    public String getRowID()
    {
        return RowID;
    }

    public void setAuthType ( String auth_type )
    {
        AuthType = auth_type;
    }

    public String getAuthType()
    {
        return AuthType;
    }

    public void setContextPath ( String context_path )
    {
        ContextPath = context_path;
    }

    public String getContextPath()
    { 
        return ContextPath;
    }

    public void setMethod ( String method )
    {
        Method = method;
    }

    public String getMethod()
    {
        return Method;
    }

    public void setPathInfo ( String path_info )
    {
        PathInfo = path_info;
    }

    public String getPathInfo()
    {
        return PathInfo;
    }

    public void setPathInfoTranslated ( String path_info_translated )
    {
        PathInfoTranslated = path_info_translated;
    }

    public String getPathInfoTranslated()
    {
        return PathInfoTranslated;
    }

    public void setRemoteUser ( String remote_user )
    {
        RemoteUser = remote_user;
    }

    public String getRemoteUser()
    {
        return RemoteUser;
    }

    public void setRequestedSessionID ( String requested_session_id )
    {
        RequestedSessionID = requested_session_id;
    }

    public String getRequestedSessionID()
    {
        return RequestedSessionID;
    }

    public void setRequestURI ( String request_uri )
    {
        RequestURI = request_uri;
    }

    public String getRequestURI()
    {
        return RequestURI;
    }

    public void setRequestURL ( String request_url )
    {
        RequestURL = request_url;
    }

    public String getRequestURL()
    {
        return RequestURL;
    }

    public void setServletPath ( String servlet_path )
    {
        ServletPath = servlet_path;
    }

    public String getServletPath()
    {
        return ServletPath;
    }

    public void setUserPrincipal ( String user_principal )
    {
        UserPrincipal = user_principal;
    }

    public String getUserPrincipal()
    {
        return UserPrincipal;
    }

    public void setCharacterEncoding ( String character_encoding )
    {
        CharacterEncoding = character_encoding;
    }

    public String getCharacterEncoding()
    {
        return CharacterEncoding;
    }

    public void setContentType ( String content_type )
    {
        ContentType = content_type;
    }

    public String getContentType()
    {
        return ContentType;
    }

    public void setLocalAddress ( String local_address )
    {
        LocalAddress = local_address;
    }

    public String getLocalAddress()
    {
        return LocalAddress;
    }

    public void setProtocol ( String protocol )
    {
        Protocol = protocol;
    }

    public String getProtocol()
    {
        return Protocol;
    }

    public void setRemoteAddress ( String remote_address )
    {
        RemoteAddress = remote_address;
    }

    public String getRemoteAddress()
    {
        return RemoteAddress;
    }

    public void setRemoteHost ( String remote_host )
    {
        RemoteHost = remote_host;
    }

    public String getRemoteHost()
    {
        return RemoteHost;
    }

    public void setScheme ( String scheme )
    {
        Scheme = scheme;
    }

    public String getScheme()
    {
        return Scheme;
    }

    public void setServerName ( String server_name )
    {
        ServerName = server_name;
    }

    public String getServerName()
    {
        return ServerName;
    }

    public void setContentLength ( int content_length )
    {
        ContentLength = content_length;
    }

    public int getContentLength()
    {
        return ContentLength;
    }

    public void setLocalPort ( int local_port )
    {
        LocalPort = local_port;
    }

    public int getLocalPort()
    {
        return LocalPort;
    }

    public void setRemotePort ( int remote_port )
    {
        RemotePort = remote_port;
    }

    public int getRemotePort()
    {
        return RemotePort;
    }

    public void setServerPort ( int server_port )
    {
        ServerPort = server_port;
    }

    public int getServerPort()
    {
        return ServerPort;
    }

    public void setRequestTime ( Calendar request_time )
    {
        RequestTime = request_time;
    }

    public Calendar getRequestTime()
    {
        return RequestTime;
    }
};
