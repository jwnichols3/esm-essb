package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate;

import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.hibernate.HibernateUtil;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.hibernate.HttpCookie;

public class HttpCookieFinder implements IHttpCookieFinder
{
    /**
     *
     */
    //  XXX___SERIALVER__XXX
    final private static Logger _log           = Logger.getLogger ( HttpCookieFinder.class );
    protected String QuerySelectAll            = null;
    protected String QuerySelectCountAll       = null;

    public HttpCookieFinder()
    {
        createFinderQueries();
    }

    public IHttpCookie newInstance()
    {
        return new HttpCookie();
    }

    public void createFinderQueries()
    {
        QuerySelectAll        = "FROM HttpCookie x";
        QuerySelectCountAll   = "SELECT count(*) FROM HttpCookie";
    }

    @SuppressWarnings ( "unchecked" )
    public List <IHttpCookie> selectAll()
    {
        List <IHttpCookie> list = null;

        try
        {
            Query query     = null;
            Session session = HibernateUtil.getCurrentSession();
            Transaction tx  = session.beginTransaction();
                query = session.createQuery ( QuerySelectAll );
            list = query.list();
            tx.commit();

            return list;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAll() - " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }

    public int selectCountAll()
    {
        try
        {
            Session session = HibernateUtil.getCurrentSession();
            Transaction tx  = session.beginTransaction();
            int result = (int) ((Long) session.createQuery(QuerySelectCountAll).uniqueResult()).longValue();
            tx.commit();

            return result;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectCountAll() - " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return -1;
    }

    /**
     *  Returns all the results, paginated
     *
     *  @param page_size the number of results each page should have
     *  @param page_num  the 0-based page num
     *  @return the relevant results if successful, null otherwise
     */
    @SuppressWarnings ( "unchecked" )
    public List <IHttpCookie> selectAll ( int page_size, int page_num )
    {
        if (( page_size < 1 ) || ( page_num < 0 ))
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAll ( PageSize=" );
                message.get().append ( page_size );
                message.get().append ( ", PageNum=" );
                message.get().append ( page_num );
                message.get().append ( " ) - illegal values passed in" );
            throw new IllegalArgumentException ( message.get().toString() );
        }

        List <IHttpCookie> list = null;

        try
        {
            Query query     = null;
            Session session = HibernateUtil.getCurrentSession();
            Transaction tx  = session.beginTransaction();
                query = session.createQuery ( QuerySelectAll );
                query.setFirstResult ( (page_num-1) * page_size );
                query.setMaxResults  ( page_size );
                list = query.list();
            tx.commit();

            return list;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectAll( PageSize=" );
                message.get().append ( page_size );
                message.get().append ( ", PageNum=" );
                message.get().append ( page_num );
                message.get().append ( ") - " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }


    public IHttpCookie select ( String row_id )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::select ( RowID=" );
                message.get().append ( row_id );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        if ( null == row_id )
        {
            return null;
        }

        try
        {
            IHttpCookie object = null;
            Session session = HibernateUtil.getCurrentSession();
            Transaction tx  = session.beginTransaction();
                object = (IHttpCookie) session.get ( HttpCookie.class, row_id );
            tx.commit();

            return object;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::select ( RowID=" );
                message.get().append ( row_id );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }

    public boolean insert ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insert ( Object=" );
                message.get().append ( (null != object)? object.toString() : "null" );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        if ( null == object )
        {
            return false;
        }

        Session session = null;
        Transaction tx  = null;

        try
        {
            IHttpCookie data = null;

            if ( null != object.getRowID() )
            {
                data = select ( object.getRowID() );
            }

            if ( null != data )
            {
                if ( _log.isEnabledFor ( Level.WARN ) )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::insert ( Object=" );
                        message.get().append ( object.toString() );
                        message.get().append ( " ) - found existing object" );
                    _log.warn ( message.get().toString() );
                }

                return false;
            }

            data = newInstance();
            data.setValue ( object );
            data.setRowID ( object.getRowID() );
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( data );
            tx.commit();

            _log.info ( String.format ( "%s::insert ( OldRowID=%s, NewRowID=%s ) - 1", getClass().getName(), object.getRowID(), data.getRowID() ) );
            if ( null != data.getRowID() )
            {
                object.setRowID ( data.getRowID() );
            }
            _log.info ( String.format ( "%s::insert ( OldRowID=%s, NewRowID=%s ) - 2", getClass().getName(), object.getRowID(), data.getRowID() ) );

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insert ( RowID=" );
                message.get().append ( object.toString() );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        finally
        {
            //  If the transaction is active, we need to rollback
            if (( null != tx ) && ( tx.isActive() ))
            {
                if ( _log.isEnabledFor ( Level.WARN ) )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::insert (" );
                        if ( null != object )
                        {
                            message.get().append ( " " );
                            message.get().append ( object.toString() );
                            message.get().append ( " " );
                        }
                        message.get().append ( ") - rolling back transaction" );
                    _log.warn ( message.get().toString() );
                }

                if (( null != tx ) && ( tx.isActive() ))
                {
                    tx.rollback();
                }
            }
        }

        return false;
    }

    public boolean update ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( (null != object)? object.toString() : "null" );
                message.get().append ( object.toString() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        Session session = null;
        Transaction tx  = null;

        try
        {
            if (( null == object ) || ( null == object.getRowID() ))
            {
                return false;
            }

            IHttpCookie data = select ( object.getRowID() );
            if ( null == data )
            {
                if ( _log.isEnabledFor ( Level.WARN ) )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::update ( Object=" );
                        message.get().append ( object.toString() );
                        message.get().append ( " ) - could not find object" );
                    _log.warn ( message.get().toString() );
                }

                return false;
            }

            data.setValue ( object );
            session = HibernateUtil.getCurrentSession();
            tx  = session.beginTransaction();
                session.update ( data );
            tx.commit();

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::update ( RowID=" );
                message.get().append ( object.toString() );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );

            if (( null != tx ) && ( tx.isActive() ))
            {
                tx.rollback();
            }
        }

        return false;
    }

    public boolean lockForUpdate ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::lockForUpdate ( Object=" );
                message.get().append ( (null != object)? object.toString() : "null" );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        Session session = null;
        Transaction tx  = null;

        try
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();

            IHttpCookie data = (IHttpCookie) session.get ( HttpCookie.class, object.getRowID(), LockMode.UPGRADE );
            data.setValue ( object );
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.update ( data );
            tx.commit();

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::lockForUpdate ( RowID=" );
                message.get().append ( object.toString() );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );

            if (( null != tx ) && ( tx.isActive() ))
            {
                tx.rollback();
            }
        }

        return false;
    }

    public boolean insertOrUpdate ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertOrUpdate ( Object=" );
                if ( null != object )
                {
                    message.get().append ( object.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        if ( null == object )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertOrUpdate ( Object=null ) - can not insert or update a null object" );
            throw new IllegalArgumentException ( message.get().toString() );
        }

        IHttpCookie data = select ( object.getRowID() );
        try
        {
            if ( null == data )
            {
                insert ( object, hostname, username, remote_address, remote_port );
                return false;
            }
            else
            {
                data.setValue ( object );
                update ( data, hostname, username, remote_address, remote_port );
                return true;
            }
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insertOrUpdate (" );
                if ( null != object )
                {
                    message.get().append ( " " );
                    message.get().append ( object.toString() );
                    message.get().append ( " " );
                }
                message.get().append ( ") - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        throw new IllegalArgumentException ( "Does not work with argument=" + object.toString() );
    }

    public boolean delete ( IHttpCookie object, String username, String hostname, String remote_address, int remote_port )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::delete ( Object=" );
                message.get().append ( (null != object)? object.toString() : "null" );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        Session session = null;
        Transaction tx  = null;

        try
        {
            IHttpCookie data = select ( object.getRowID() );

            if ( null == data )
            {
                if ( _log.isEnabledFor ( Level.WARN ) )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::delete ( Object=" );
                        message.get().append ( object.toString() );
                        message.get().append ( " ) - could not find object" );
                    _log.warn ( message.get().toString() );
                }

                return false;
            }

            //  Perform the delete operation
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.delete ( data );

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::delete ( RowID=" );
                message.get().append ( object.toString() );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );

            if (( null != tx ) && ( tx.isActive() ))
            {
                tx.rollback();
            }
        }

        return false;
    }
}