package com.bgi.esm.monitoring.platform.notifications;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.notifications.HibernateUtil;

public class NotificationBean implements Serializable
{
    private static Logger _log = Logger.getLogger ( NotificationBean.class );

    private long NotificationID            = 0L;
    private long SuppressionID             = 0L;
    private String NotificationTarget      = null;
    private Calendar NotificationTimestamp = Calendar.getInstance();

    public NotificationBean()
    {
    }

    public long getNotificationID()
    {
        return NotificationID;
    }

    public void setNotificationID ( long notification_id )
    {
        NotificationID = notification_id;
    }

    public long getSuppressionID()
    {
        return SuppressionID;
    }

    public void setSuppressionID ( long suppression_id )
    {
        SuppressionID = suppression_id;
    }

    public String getNotificationTarget()
    {
        return NotificationTarget;
    }

    public void setNotificationTarget ( String notification_target )
    {
        NotificationTarget = notification_target;
    }

    public Calendar getNotificationTimestamp()
    {
        return NotificationTimestamp;
    }

    public void setNotificationTimestamp ( Calendar timestamp )
    {
        NotificationTimestamp = timestamp;
    }

    public boolean execute()
    {
        boolean bad_alert = false;
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );

        if ( SuppressionID < 1 )
        {
            message.get().append ( "Bad Suppression ID: "  );
            message.get().append ( SuppressionID );

            bad_alert = true;
        }

        if ( null == NotificationTarget )
        {
            if ( true == bad_alert )
            {
                message.get().append ( ", " );
            }

            message.get().append ( "Null notification target detected" );

            bad_alert = true;
        }

        if ( null == NotificationTimestamp )
        {
            if ( true == bad_alert )
            {
                message.get().append ( ", " );
            }

            message.get().append ( "Null timestamp detected" );

            bad_alert = true;
        }

        if ( true == bad_alert )
        {
            throw new IllegalArgumentException ( message.get().toString() );
        }

        Session session = HibernateUtil.getNotificationsCurrentSession();
        Serializable id = null;
        Transaction tx  = session.beginTransaction();
            id = session.save ( this );
        tx.commit();

        return ( null != id );
    }

    public static List <NotificationBean> getAllNotifications()
    {
        List <NotificationBean> list = null;
        Query query     = null;
        Session session = HibernateUtil.getNotificationsCurrentSession();
        Transaction tx  = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.notifications.NotificationBean" );
            list  = query.list();
        tx.commit();

        return list;
    }
}
