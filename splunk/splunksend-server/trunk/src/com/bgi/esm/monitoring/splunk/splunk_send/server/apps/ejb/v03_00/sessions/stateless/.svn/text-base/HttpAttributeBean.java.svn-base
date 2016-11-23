package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.stateless;

import java.lang.ref.WeakReference;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.BaseHttpAttributeSessionBean;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.local.IHttpAttributeLocal;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote.IHttpAttributeRemote;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;

@Stateless ( name="HttpAttributeStateless", mappedName="ejb/HttpAttributeStateless" )
public class HttpAttributeBean extends BaseHttpAttributeSessionBean implements IHttpAttributeLocal, IHttpAttributeRemote
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
            _log = Logger.getLogger ( HttpAttributeBean.class );

            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( HttpAttributeBean.class );
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

    public List <IHttpAttribute> selectAllLocal()
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

    public List <IHttpAttribute> selectAllRemote()
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

    public List <IHttpAttribute> selectAllRemote ( int page_size, int page_num )
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

    public List <IHttpAttribute> selectAllLocal ( int page_size, int page_num )
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

    public IHttpAttribute insertLocal ( IHttpAttribute object )
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

    public IHttpAttribute insertRemote ( IHttpAttribute object )
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

    public IHttpAttribute updateLocal ( IHttpAttribute object )
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

    public IHttpAttribute updateRemote ( IHttpAttribute object )
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

    public IHttpAttribute insertOrUpdateLocal ( IHttpAttribute object )
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

    public IHttpAttribute insertOrUpdateRemote ( IHttpAttribute object )
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

    public boolean deleteLocal ( IHttpAttribute object )
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

    public boolean deleteRemote ( IHttpAttribute object )
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
    public IHttpAttribute selectLocal ( String row_id )
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
    public IHttpAttribute selectRemote ( String row_id )
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