package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions;

//import weblogic.logging.log4j.Log4jLoggingHelper;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

public abstract class BaseHttpCookieSessionBean
{
    private static Logger _log = null;

    private @Resource SessionContext sessionContext = null;

    static
    {
        //  Setup log4j for both Weblogic and non-Weblogic
        Exception exception = null;
        try
        {
            //_log = Log4jLoggingHelper.getLog4jServerLogger();
        }
        catch ( Exception ex )
        {
            _log = null;
            exception = ex;
        }

        if ( null == _log )
        {
            _log = Logger.getLogger ( BaseHttpCookieSessionBean.class );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( BaseHttpCookieSessionBean.class );
            message.get().append ( "staticBlock() - Could not initialize Weblogic Log4j.  Defaulting to Apache Log4j" );
            if ( null != exception )
            {
                message.get().append ( ": " );
                message.get().append ( exception.getMessage() );
                _log.info ( message.get().toString(), exception );
            }
            else
            {
                _log.info ( message.get().toString() );
            }
        }
    }

    protected List <IHttpCookie> selectAll()
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        List <IHttpCookie> results = finder.selectAll();

        return results;
    }

    protected List <IHttpCookie> selectAll ( int page_size, int page_num )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        List <IHttpCookie> results = finder.selectAll ( page_size, page_num );

        return results;
    }

    protected IHttpCookie insert ( IHttpCookie object )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        String username       = sessionContext.getCallerPrincipal().getName();
        String remoteHostname = null;
        String remoteAddress  = null;
        int remotePort        = -1;

        boolean result = finder.insert ( object, username, remoteHostname, remoteAddress, remotePort );

        return ( result )? object : null;
    }

    protected IHttpCookie update ( IHttpCookie object )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        String username       = sessionContext.getCallerPrincipal().getName();
        String remoteHostname = null;
        String remoteAddress  = null;
        int remotePort        = -1;

        boolean result = finder.update ( object, username, remoteHostname, remoteAddress, remotePort );

        return ( result )? object : null;
    }

    protected IHttpCookie insertOrUpdate ( IHttpCookie object )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        String username       = sessionContext.getCallerPrincipal().getName();
        String remoteHostname = null;
        String remoteAddress  = null;
        int remotePort        = -1;

        finder.insertOrUpdate ( object, username, remoteHostname, remoteAddress, remotePort );

        return object;
    }

    protected boolean delete ( IHttpCookie object )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        String username       = sessionContext.getCallerPrincipal().getName();
        String remoteHostname = null;
        String remoteAddress  = null;
        int remotePort        = -1;

        return finder.delete ( object, username, remoteHostname, remoteAddress, remotePort );
    }

    protected IHttpCookie select ( String row_id )
    {
        IHttpCookieFinder finder = Configuration.getInstance().getHttpCookieFinder();

        return finder.select ( row_id );
    }
}