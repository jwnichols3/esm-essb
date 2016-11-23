package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.hibernate;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpCookie;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpFinder;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpParameter;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpRequest;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpAttribute;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpHeader;

public class HibernateHttpFinder extends AbstractHttpFinder
{
    final private static Logger _log = Logger.getLogger ( HibernateHttpFinder.class );

    ///////////////////////////////////////////////////////////////////////////
    //  HTTP Requests
    ///////////////////////////////////////////////////////////////////////////
    protected static String QueryRequests_SelectAll = String.format ( "FROM %s c", HibernateHttpRequest.class.getName() );

    public static List selectAllRequests()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( QueryRequests_SelectAll );

            list = query.list();
        tx.commit();

        return list;
    }

    public static AbstractHttpRequest createRequest()
    {
        Class c = HibernateHttpRequest.class;
        try
        {
            return (AbstractHttpRequest) c.newInstance();
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class: " + c.getName(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "Illegal Access Exception encountered when trying to instantiate: " + c.getName(), exception );
        }

        return null;
    }

    public static AbstractHttpRequest selectRequest ( String row_id )
    {
        AbstractHttpRequest object = null;
        Session session            = HibernateUtil.getCurrentSession();
        Transaction tx             = session.beginTransaction();
            object                 = (AbstractHttpRequest) session.get ( HibernateHttpRequest.class, row_id );
        tx.commit();

        return object;
    }

    public static boolean insertOrUpdateRequest ( AbstractHttpRequest object )
    {
        AbstractHttpRequest data = null;
        boolean was_update       = false;
        Session session          = null;
        Transaction tx           = null;

        if ( null != object.getRowID() )
        {
            data = selectRequest ( object.getRowID() );
        }

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();
            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();
            was_update = false;
        }

        return was_update;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  HTTP Request Parameters
    ///////////////////////////////////////////////////////////////////////////
    protected static String QueryParameters_SelectAll = String.format ( "FROM %s c", HibernateHttpParameter.class.getName() );

    public static List selectAllRequestParameters()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( QueryParameters_SelectAll );

            list = query.list();
        tx.commit();

        return list;
    }

    public static AbstractHttpParameter createRequestParameter()
    {
        Class c = HibernateHttpParameter.class;
        try
        {
            return (AbstractHttpParameter) c.newInstance();
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class: " + c.getName(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "Illegal Access Exception encountered when trying to instantiate: " + c.getName(), exception );
        }

        return null;
    }

    public static AbstractHttpParameter selectRequestParameter ( String row_id )
    {
        AbstractHttpParameter object = null;
        Session session              = HibernateUtil.getCurrentSession();
        Transaction tx               = session.beginTransaction();
            object                   = (AbstractHttpParameter) session.get ( HibernateHttpParameter.class, row_id );
        tx.commit();

        return object;
    }

    public static boolean insertOrUpdateRequestParameter ( AbstractHttpParameter object )
    {
        AbstractHttpParameter data = null;
        boolean was_update         = false;
        Session session            = null;
        Transaction tx             = null;

        if ( null != object.getRowID() )
        {
            data = selectRequestParameter ( object.getRowID() );
        }

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();
            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();
            was_update = false;
        }

        return was_update;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  HTTP Request Attributes
    ///////////////////////////////////////////////////////////////////////////
    protected static String QueryAttributes_SelectAll = String.format ( "FROM %s c", HibernateHttpAttribute.class.getName() );
    
    public static List selectAllRequestAttributes()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( QueryAttributes_SelectAll );

            list = query.list();
        tx.commit();

        return list;
    }

    public static AbstractHttpAttribute createRequestAttribute()
    {
        Class c = HibernateHttpAttribute.class;
        try
        {
            return (AbstractHttpAttribute) c.newInstance();
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class: " + c.getName(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "Illegal Access Exception encountered when trying to instantiate: " + c.getName(), exception );
        }

        return null;
    }

    public static AbstractHttpAttribute selectRequestAttribute ( String row_id )
    {
        AbstractHttpAttribute object = null;
        Session session              = HibernateUtil.getCurrentSession();
        Transaction tx               = session.beginTransaction();
            object                   = (AbstractHttpAttribute) session.get ( HibernateHttpAttribute.class, row_id );
        tx.commit();

        return object;
    }

    public static boolean insertOrUpdateRequestAttribute ( AbstractHttpAttribute object)
    {
        AbstractHttpAttribute data = null;
        boolean was_update         = false;
        Session session            = null;
        Transaction tx             = null;

        if ( null != object.getRowID() )
        {
            data = selectRequestAttribute ( object.getRowID() );
        }

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();
            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();
            was_update = false;
        }

        return was_update;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  HTTP Request Headers
    ///////////////////////////////////////////////////////////////////////////
    protected static String QueryHeaders_SelectAll = String.format ( "FROM %s c", HibernateHttpHeader.class.getName() );
    
    public static List selectAllRequestHeaders()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( QueryHeaders_SelectAll );

            list = query.list();
        tx.commit();

        return list;
    }

    public static AbstractHttpHeader createRequestHeader()
    {
        Class c = HibernateHttpHeader.class;
        try
        {
            return (AbstractHttpHeader) c.newInstance();
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class: " + c.getName(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "Illegal Access Exception encountered when trying to instantiate: " + c.getName(), exception );
        }

        return null;
    }

    public static AbstractHttpHeader selectRequestHeader ( String row_id )
    {
        AbstractHttpHeader object = null;
        Session session           = HibernateUtil.getCurrentSession();
        Transaction tx            = session.beginTransaction();
            object                = (AbstractHttpHeader) session.get ( HibernateHttpHeader.class, row_id );
        tx.commit();

        return object;
    }

    public static boolean insertOrUpdateRequestHeader ( AbstractHttpHeader object )
    {
        AbstractHttpHeader data = null;
        boolean was_update      = false;
        Session session         = null;
        Transaction tx          = null;

        if ( null != object.getRowID() )
        {
            data = selectRequestHeader ( object.getRowID() );
        }

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();
            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();
            was_update = false;
        }

        return was_update;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  HTTP Cookies
    ///////////////////////////////////////////////////////////////////////////
    protected static String QueryCookies_SelectAll = String.format ( "FROM %s c", HibernateHttpCookie.class.getName() );
    
    public static List selectAllCookies()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( QueryCookies_SelectAll );

            list = query.list();
        tx.commit();

        return list;
    }

    public static AbstractHttpCookie createCookie()
    {
        Class c = HibernateHttpCookie.class;
        try
        {
            return (AbstractHttpCookie) c.newInstance();
        }
        catch ( InstantiationException exception )
        {
            _log.error ( "Could not instantiate class: " + c.getName(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            _log.error ( "Illegal Access Exception encountered when trying to instantiate: " + c.getName(), exception );
        }

        return null;
    }

    public static AbstractHttpCookie selectCookie ( String row_id )
    {
        AbstractHttpCookie object = null;
        Session session           = HibernateUtil.getCurrentSession();
        Transaction tx            = session.beginTransaction();
            object                = (AbstractHttpCookie) session.get ( HibernateHttpCookie.class, row_id );
        tx.commit();

        return object;
    }

    public static boolean insertOrUpdateCookie ( AbstractHttpCookie object )
    {
        AbstractHttpCookie data = null;
        boolean was_update      = false;
        Session session         = null;
        Transaction tx          = null;
        
        if ( null != object.getRowID() )
        {
            data = selectCookie ( object.getRowID() );
        }

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();
            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();
            was_update = false;
        }

        return was_update;
    }
};
