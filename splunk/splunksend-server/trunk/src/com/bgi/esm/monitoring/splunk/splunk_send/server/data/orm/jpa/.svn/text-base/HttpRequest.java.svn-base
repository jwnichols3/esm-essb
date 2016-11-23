package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

@Entity
@Table ( name="http_request" )
public class HttpRequest extends AbstractHttpRequest implements IHttpRequest
{
    /**
     *
     */
    final private static long serialVersionUID = -6312376588788144177L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( HttpRequest.class );

    @Id
    @Column ( name="request_id" )
    public String getRequestID()
    {
        return super.getRequestID();
    }

    @Column ( name="auth_type" )
    public String getAuthType()
    {
        return super.getAuthType();
    }

    @Column ( name="context_path" )
    public String getContextPath()
    {
        return super.getContextPath();
    }

    @Column ( name="method" )
    public String getMethod()
    {
        return super.getMethod();
    }

    @Column ( name="path_info" )
    public String getPathInfo()
    {
        return super.getPathInfo();
    }

    @Column ( name="path_info_translated" )
    public String getPathInfoTranslated()
    {
        return super.getPathInfoTranslated();
    }

    @Column ( name="remote_user" )
    public String getRemoteUser()
    {
        return super.getRemoteUser();
    }

    @Column ( name="requested_session_id" )
    public String getRequestedSessionID()
    {
        return super.getRequestedSessionID();
    }

    @Column ( name="request_uri" )
    public String getRequestUri()
    {
        return super.getRequestUri();
    }

    @Column ( name="request_url" )
    public String getRequestUrl()
    {
        return super.getRequestUrl();
    }

    @Column ( name="servlet_path" )
    public String getServletPath()
    {
        return super.getServletPath();
    }

    @Column ( name="user_principal" )
    public String getUserPrincipal()
    {
        return super.getUserPrincipal();
    }

    @Column ( name="character_encoding" )
    public String getCharacterEncoding()
    {
        return super.getCharacterEncoding();
    }

    @Column ( name="content_type" )
    public String getContentType()
    {
        return super.getContentType();
    }

    @Column ( name="local_address" )
    public String getLocalAddress()
    {
        return super.getLocalAddress();
    }

    @Column ( name="protocol" )
    public String getProtocol()
    {
        return super.getProtocol();
    }

    @Column ( name="remote_address" )
    public String getRemoteAddress()
    {
        return super.getRemoteAddress();
    }

    @Column ( name="remote_host" )
    public String getRemoteHost()
    {
        return super.getRemoteHost();
    }

    @Column ( name="scheme" )
    public String getScheme()
    {
        return super.getScheme();
    }

    @Column ( name="server_name" )
    public String getServerName()
    {
        return super.getServerName();
    }

    @Column ( name="request_time" )
    @Temporal ( TemporalType.TIMESTAMP )
    public Calendar getRequestTime()
    {
        return super.getRequestTime();
    }

    @Column ( name="process_time" )
    @Temporal ( TemporalType.TIMESTAMP )
    public Calendar getProcessTime()
    {
        return super.getProcessTime();
    }

    @Column ( name="content_length" )
    public long getContentLength()
    {
        return super.getContentLength();
    }

    @Column ( name="local_port" )
    public long getLocalPort()
    {
        return super.getLocalPort();
    }

    @Column ( name="remote_port" )
    public long getRemotePort()
    {
        return super.getRemotePort();
    }

    @Column ( name="server_port" )
    public long getServerPort()
    {
        return super.getServerPort();
    }

    @Column ( name="is_processed" )
    public boolean getIsProcessed()
    {
        return super.getIsProcessed();
    }

    @Column ( name="was_successful" )
    public long getWasSuccessful()
    {
        return super.getWasSuccessful();
    }

    @Column ( name="return_code" )
    public long getReturnCode()
    {
        return super.getReturnCode();
    }
}