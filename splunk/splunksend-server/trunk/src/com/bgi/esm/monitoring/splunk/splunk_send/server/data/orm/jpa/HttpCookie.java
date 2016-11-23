package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

@Entity
@Table ( name="http_cookie" )
public class HttpCookie extends AbstractHttpCookie implements IHttpCookie
{
    /**
     *
     */
    final private static long serialVersionUID = 3142080299900920421L;
    @SuppressWarnings ( "unused" )
    final private static Logger _log           = Logger.getLogger ( HttpCookie.class );

    @Id
    @Column ( name="row_id" )
    public String getRowID()
    {
        return super.getRowID();
    }

    @Column ( name="request_id" )
    public String getRequestID()
    {
        return super.getRequestID();
    }

    @Column ( name="cookie_comment" )
    public String getCookieComment()
    {
        return super.getCookieComment();
    }

    @Column ( name="domain" )
    public String getDomain()
    {
        return super.getDomain();
    }

    @Column ( name="path" )
    public String getPath()
    {
        return super.getPath();
    }

    @Column ( name="cookie_name" )
    public String getCookieName()
    {
        return super.getCookieName();
    }

    @Column ( name="cookie_persistence" )
    public String getCookiePersistence()
    {
        return super.getCookiePersistence();
    }

    @Column ( name="is_secure" )
    public boolean getIsSecure()
    {
        return super.getIsSecure();
    }

    @Column ( name="max_age" )
    public long getMaxAge()
    {
        return super.getMaxAge();
    }

    @Column ( name="version" )
    public long getVersion()
    {
        return super.getVersion();
    }
}