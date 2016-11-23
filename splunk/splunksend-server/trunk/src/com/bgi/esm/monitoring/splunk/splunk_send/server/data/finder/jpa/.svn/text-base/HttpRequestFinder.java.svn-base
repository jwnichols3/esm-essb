package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.jpa;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jpa.HttpRequest;

public class HttpRequestFinder implements IHttpRequestFinder
{
    /**
     *
     */
    //  XXX___SERIALVER__XXX
    final private static Logger _log           = Logger.getLogger ( HttpRequestFinder.class );
    protected String QuerySelectAll            = null;
    protected String QuerySelectCountAll       = null;

    public HttpRequestFinder()
    {
        createFinderQueries();
    }

    public IHttpRequest newInstance()
    {
        return new HttpRequest();
    }

    public void createFinderQueries()
    {
        QuerySelectAll        = "SELECT x FROM HttpRequest x ORDER BY x.requestTime, x.processTime DESC";
        QuerySelectCountAll   = "SELECT count(x) FROM HttpRequest x";
    }

    @SuppressWarnings ( "unchecked" )
    public List <IHttpRequest> selectAll()
    {
        EntityManager em     = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List <IHttpRequest> list = null;
        Query query = em.createQuery ( QuerySelectAll );

        try
        {
            tx.begin();
                list = query.getResultList();
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
        EntityManager em     = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Query query = em.createQuery ( QuerySelectCountAll );

        try
        {
            tx.begin();
                query.getResultList();
            tx.commit();

            int result = ((Long) query.getSingleResult()).intValue();
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
    public List <IHttpRequest> selectAll ( int page_size, int page_num )
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

        EntityManager em     = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List <IHttpRequest> list = null;
        Query query = em.createQuery ( QuerySelectAll );
        query.setFirstResult ( page_num * page_size );
        query.setMaxResults  ( page_size );

        try
        {
            tx.begin();
                list = query.getResultList();
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


    public IHttpRequest select ( String row_id )
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::select ( RequestID=" );
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
            EntityManager em     = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            IHttpRequest object = null;
                tx.begin();
                    object = (IHttpRequest) em.find ( HttpRequest.class, row_id );
                tx.commit();
            em.close();

            return object;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::select ( RequestID=" );
                message.get().append ( row_id );
                message.get().append ( " ) - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }

    public boolean insert ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port )
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

        EntityManager em     = null;
        EntityTransaction tx = null;

        try
        {
            em = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
            IHttpRequest data = null;

            if ( null != object.getRequestID() )
            {
                data = select ( object.getRequestID() );
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

            //  Create the RowID
            if ( null == object.getRequestID() )
            {
                UUID uuid = UUID.randomUUID();

                object.setRequestID                ( uuid.toString() );
            }

            data = newInstance();
            data.setValue ( object );
            data.setRequestID ( object.getRequestID() );
                tx = em.getTransaction();
                tx.begin();
                    em.persist ( data );
                tx.commit();
            em.close();

            _log.info ( String.format ( "%s::insert ( OldRowID=%s, NewRowID=%s ) - 1", getClass().getName(), object.getRequestID(), data.getRequestID() ) );
            if ( null != data.getRequestID() )
            {
                object.setRequestID ( data.getRequestID() );
            }
            _log.info ( String.format ( "%s::insert ( OldRowID=%s, NewRowID=%s ) - 2", getClass().getName(), object.getRequestID(), data.getRequestID() ) );

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::insert ( RequestID=" );
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

                tx.rollback();
            }
        }

        return false;
    }

    public boolean update ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port )
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

        EntityManager em     = null;
        EntityTransaction tx = null;

        try
        {
            if (( null == object ) || ( null == object.getRequestID() ))
            {
                return false;
            }

            em = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
            tx = em.getTransaction();
            IHttpRequest data = select ( object.getRequestID() );
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
                tx.begin();
                    em.merge ( data );
                tx.commit();
            em.close();

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::update ( RequestID=" );
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
                        message.get().append ( "::update (" );
                        if ( null != object )
                        {
                            message.get().append ( " " );
                            message.get().append ( object.toString() );
                            message.get().append ( " " );
                        }
                        message.get().append ( ") - rolling back transaction" );
                    _log.warn ( message.get().toString() );
                }

                tx.rollback();
            }
        }

        return false;
    }

    public boolean lockForUpdate ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port )
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

        EntityManager em     = null;
        EntityTransaction tx = null;

        try
        {
            em = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
            IHttpRequest data = (IHttpRequest) em.getReference ( HttpRequest.class, object.getRequestID() );
            data.setValue ( object );
            tx = em.getTransaction();
            tx.begin();
                em.merge ( data );
            tx.commit();

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::lockForUpdate ( RequestID=" );
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
                        message.get().append ( "::lockForUpdate (" );
                        if ( null != object )
                        {
                            message.get().append ( " " );
                            message.get().append ( object.toString() );
                            message.get().append ( " " );
                        }
                        message.get().append ( ") - rolling back transaction" );
                    _log.warn ( message.get().toString() );
                }

                tx.rollback();
            }
        }

        return false;
    }

    public boolean insertOrUpdate ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port )
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

        IHttpRequest data = select ( object.getRequestID() );
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

    public boolean delete ( IHttpRequest object, String username, String hostname, String remote_address, int remote_port )
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

        EntityManager em     = null;
        EntityTransaction tx = null;

        try
        {
            em = JPAFinderUtilities.getEntityManagerFactory().createEntityManager();
            tx = em.getTransaction();
            IHttpRequest data = select ( object.getRequestID() );

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
            em.remove ( data );

            return true;
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::delete ( RequestID=" );
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
                        message.get().append ( "::delete (" );
                        if ( null != object )
                        {
                            message.get().append ( " " );
                            message.get().append ( object.toString() );
                            message.get().append ( " " );
                        }
                        message.get().append ( ") - rolling back transaction" );
                    _log.warn ( message.get().toString() );
                }

                tx.rollback();
            }
        }

        return false;
    }
}