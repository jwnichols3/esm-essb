package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.stateful;

import java.lang.ref.WeakReference;
import java.util.List;
import javax.ejb.Stateful;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.BaseHttpCookieSessionBean;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.local.IHttpCookieLocal;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote.IHttpCookieRemote;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;

@Stateful ( name="HttpCookieStateful", mappedName="ejb/HttpCookieStateful" )
public class HttpCookieBean extends BaseHttpCookieSessionBean implements IHttpCookieLocal, IHttpCookieRemote
{
    private static Logger _log = null;

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
            _log = Logger.getLogger ( HttpCookieBean.class );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( HttpCookieBean.class );
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

    public List <IHttpCookie> selectAllLocal()
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAllLocal ()" );
            _log.info ( message.get().toString() );
        }

        return selectAll();
    }

    public List <IHttpCookie> selectAllRemote()
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAllRemote ()" );
            _log.info ( message.get().toString() );
        }

        return selectAll( );
    }

    public List <IHttpCookie> selectAllRemote ( int page_size, int page_num )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAllRemote ( PageNum=" );
                message.get().append ( page_num );
                message.get().append ( ", PageSize=" );
                message.get().append ( page_size );
                message.get().append ( ")" );
            _log.info ( message.get().toString() );
        }

        return selectAll ( page_size, page_num );
    }

    public List <IHttpCookie> selectAllLocal ( int page_size, int page_num )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAllLocal ( PageNum=" );
                message.get().append ( page_num );
                message.get().append ( ", PageSize=" );
                message.get().append ( page_size );
                message.get().append ( ")" );
            _log.info ( message.get().toString() );
        }

        return selectAll ( page_size, page_num );
    }

    public IHttpCookie insertLocal ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertLocal ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return insert ( object );
    }

    public IHttpCookie insertRemote ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertRemote ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return insert ( object );
    }

    public IHttpCookie updateLocal ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::updateLocal ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return update ( object );
    }

    public IHttpCookie updateRemote ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::updateRemote ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return update ( object );
    }

    public IHttpCookie insertOrUpdateLocal ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertOrUpdateLocal ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return insertOrUpdate ( object );
    }

    public IHttpCookie insertOrUpdateRemote ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertOrUpdateRemote ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return insertOrUpdate ( object );
    }

    public boolean deleteLocal ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::deleteLocal ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return delete ( object );
    }

    public boolean deleteRemote ( IHttpCookie object )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::deleteRemote ( object=" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return delete ( object );
    }

    /**
     *  Selects the specified object
     *
     *  @param object the id of the object to select
     *  @return the object that was if found, null otherwise
     */
    public IHttpCookie selectLocal ( String row_id )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectLocal ( RowID=" );
                message.get().append ( row_id );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return select ( row_id );
    }

    /**
     *  Selects the specified object
     *
     *  @param object the id of the object to select
     *  @return the object that was if found, null otherwise
     */
    public IHttpCookie selectRemote ( String row_id )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectRemote ( RowID=" );
                message.get().append ( row_id );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        return select ( row_id );
    }
}