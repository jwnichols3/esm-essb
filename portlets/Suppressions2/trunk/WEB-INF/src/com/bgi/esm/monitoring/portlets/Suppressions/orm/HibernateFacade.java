package com.bgi.esm.monitoring.portlets.Suppressions;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;

public class HibernateFacade
{
    final private static Logger _log = Logger.getLogger ( HibernateFacade.class );
    final public static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );    

    public boolean insertOrUpdateNotification ( ExpirationNotification object )
    {
        ExpirationNotification data = null;
        boolean was_update          = false;
        Session session             = null;
        Transaction tx              = null;

        data = selectNotification ( object.getRowID() );
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

    public ExpirationNotification selectNotification ( long RowID )
    {
        ExpirationNotification object = null;
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            object = (ExpirationNotification) session.get ( ExpirationNotification.class, RowID );
        tx.commit();

        return object;
    }

    public List <ExpirationNotification> getActiveNotifications ()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification c WHERE c.NotificationTime >= :CurrentTime" );
            query.setParameter ( "CurrentTime", Calendar.getInstance(TimeZone.getTimeZone ( "GMT" )) );

            list = query.list();
        tx.commit();

        return list;
    }

    public List <ExpirationNotification> findNotificationsBetweenTimes ( Calendar start_time, Calendar end_time )
    {
        if (( null == start_time ) || ( null == end_time ))
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::findNotificationsBetweenTimes ( StartTime=" );
            message.get().append ( ( null != start_time )? sdf.format ( start_time ) : "null" );
            message.get().append ( ", EndTime=" );
            message.get().append ( ( null != end_time )? sdf.format ( end_time ) : "null" );
            message.get().append ( " )" );

            throw new IllegalArgumentException ( message.get().toString() );
        }

        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification c WHERE c.NotificationTime >= :StartTime AND c.NotificationTime <= :EndTime" );
            query.setParameter ( "StartTime", start_time );
            query.setParameter ( "EndTime",   end_time   );

            list = query.list();
        tx.commit();

        return list;
    }

    public List <ExpirationNotification> findNotificationsAfterTime ( Calendar search_time )
    {
        if ( null == search_time )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::findNotificationsAfterTime  - null date found" );

            throw new IllegalArgumentException ( message.get().toString() );
        }

        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification c WHERE c.NotificationTime <= :SearchTime" );
            query.setParameter ( "SearchTime", search_time);

            list = query.list();
        tx.commit();

        return list;
    }

    public List <ExpirationNotification> findNotificationsBeforeTime ( Calendar search_time )
    {
        if ( null == search_time )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::findNotificationsBeforeTime  - null date found" );

            throw new IllegalArgumentException ( message.get().toString() );
        }

        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification c WHERE c.NotificationTime >= :SearchTime" );
            query.setParameter ( "SearchTime", search_time);

            list = query.list();
        tx.commit();

        return list;
    }
};
