package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.stateless;

import java.lang.ref.WeakReference;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.BaseHttpRequestSessionBean;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.local.IHttpRequestLocal;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote.IHttpRequestRemote;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

@Stateless ( name="HttpRequestStateless", mappedName="ejb/HttpRequestStateless" )
public class HttpRequestBean extends BaseHttpRequestSessionBean implements IHttpRequestLocal, IHttpRequestRemote
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
            _log = Logger.getLogger ( HttpRequestBean.class );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( HttpRequestBean.class );
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

    public List <IHttpRequest> selectAllLocal()
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

    public List <IHttpRequest> selectAllRemote()
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAllRemote ()" );
            _log.info ( message.get().toString() );
        }

        return selectAll();
    }

    public List <IHttpRequest> selectAllRemote ( int page_size, int page_num )
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

    public List <IHttpRequest> selectAllLocal ( int page_size, int page_num )
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

    public IHttpRequest insertLocal ( IHttpRequest object )
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

    public IHttpRequest insertRemote ( IHttpRequest object )
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

    public IHttpRequest updateLocal ( IHttpRequest object )
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

    public IHttpRequest updateRemote ( IHttpRequest object )
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

    public IHttpRequest insertOrUpdateLocal ( IHttpRequest object )
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

    public IHttpRequest insertOrUpdateRemote ( IHttpRequest object )
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

    public boolean deleteLocal ( IHttpRequest object )
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

    public boolean deleteRemote ( IHttpRequest object )
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
    public IHttpRequest selectLocal ( String row_id )
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
    public IHttpRequest selectRemote ( String row_id )
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