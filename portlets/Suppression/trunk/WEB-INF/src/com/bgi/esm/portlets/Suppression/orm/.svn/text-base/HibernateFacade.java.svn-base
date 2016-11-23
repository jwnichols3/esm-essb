package com.bgi.esm.portlets.Suppression.orm;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.portlets.Suppression.orm.ExpirationNotification;
import com.bgi.esm.portlets.Suppression.orm.SuppressionRecord;
import com.bgi.esm.portlets.Suppression.HibernateUtil;

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

    public boolean insertOrUpdateSuppressionRecord ( SuppressionRecord object )
    {
        SuppressionRecord data = null;
        boolean was_update          = false;
        Session session             = null;
        Transaction tx              = null;

        data = selectSuppressionRecord ( object.getRowID() );

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

    public SuppressionRecord selectSuppressionRecord ( long RowID )
    {
        SuppressionRecord object = null;
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            object = (SuppressionRecord) session.get ( SuppressionRecord.class, RowID );
        tx.commit();

        return object;
    }

    public SuppressionRecord findSuppressionRecord ( long SuppressionID )
    {
        List list       = null;
        Query query     = null;
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            query       = session.createQuery ( "FROM com.bgi.esm.portlets.Suppression.orm.SuppressionRecord c WHERE c.SuppressionID= :SuppressionID" );
            query.setParameter ( "SuppressionID", SuppressionID );

            list = query.list();
        tx.commit();

        if ( list.size() == 1 )
        {
            return (SuppressionRecord) list.get ( 0 );
        }
        else
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Multiple suppressions found for Suppression ID #" );
            message.get().append ( SuppressionID );
            message.get().append ( " ) - returning first one - num results: " );
            message.get().append ( list.size() );

            _log.error( message.get().toString() );

            return null;
        }
    }

    public List <ExpirationNotification> getActiveNotifications ()
    {
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.portlets.Suppression.orm.ExpirationNotification c WHERE c.NotificationTime <= :CurrentTime AND c.NotificationTimestamp IS NULL" );
            query.setParameter ( "CurrentTime", Calendar.getInstance() );

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
            query = session.createQuery ( "FROM com.bgi.esm.portlets.Suppression.orm.ExpirationNotification c WHERE c.NotificationTime >= :StartTime AND c.NotificationTime <= :EndTime" );
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
            query = session.createQuery ( "FROM com.bgi.esm.portlets.Suppression.orm.ExpirationNotification c WHERE c.NotificationTime <= :SearchTime" );
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
            query = session.createQuery ( "FROM com.bgi.esm.portlets.Suppression.orm.ExpirationNotification c WHERE c.NotificationTime >= :SearchTime" );
            query.setParameter ( "SearchTime", search_time);

            list = query.list();
        tx.commit();

        return list;
    }

    /**
     * Compares if two java.util.Calendar objects match on year, month, date,
     * hour and minute
     * 
     * @param a
     *            the first of two Calendar objects to compare
     * @param b
     *            the second of two Calendar objects to compare
     * @return true if the two Calendar objects match, false otherwise
     */
    public static boolean compareCalendars(Calendar a, Calendar b) {
        if ((null == a) && (null == b)) {
            return true;
        } else if ((null != a) && (null != b)) {
            boolean return_result = true;
            return_result = return_result && (a.get(Calendar.YEAR) == b.get(Calendar.YEAR));
            return_result = return_result && (a.get(Calendar.MONTH) == b.get(Calendar.MONTH));
            return_result = return_result && (a.get(Calendar.DATE) == b.get(Calendar.DATE));
            return_result = return_result && (a.get(Calendar.HOUR) == b.get(Calendar.HOUR));
            return_result = return_result && (a.get(Calendar.MINUTE) == b.get(Calendar.MINUTE));

            return return_result;
        } else {
            return false;
        }
    }

    public static String formatDate ( Calendar calendar ) {
        if ( null == calendar ) {
            return "null";
        }
        else {
            return formatDate ( calendar.getTime() );
        }
    }

    public static String formatDate ( Date date ) {
        if ( null == date ) {
            return "null";
        }
        else {
            return sdf.format ( date );
        }
    }

    /**
     * Safe compare for two java.lang.Object (can be null) via their equal()
     * values
     * 
     * @param a
     *            the first string to compare
     * @param b
     *            the second string to compare
     * @return true if they match or are both null, false otherwise
     */
    public static boolean compareObjects(Object a, Object b) {
        if ((null == a) && (null == b)) {
            return true;
        } else if ((null != a) && (null != b)) {
            return a.equals(b);
        } else {
            return false;
        }
    }

    /**
     * Safe compare for two strings (can be null)
     * 
     * @param a
     *            the first string to compare
     * @param b
     *            the second string to compare
     * @return true if they match or are both null, false otherwise
     */
    public static boolean compareStrings(String a, String b) {
        if ((null == a) && (null == b)) {
            return true;
        } else if ((null != a) && (null != b)) {
            return a.equals(b);
        } else {
            return false;
        }
    }
};
