package com.bgi.esm.monitoring.platform.regression.hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.HibernateUtil;

public class EventHibernateFacade
{
    public static long count()
    {
        Session session   = HibernateUtil.getCurrentSession();
        Transaction tx    = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from com.bgi.esm.monitoring.platform.regression.data.Event c").uniqueResult();
        tx.commit();

        return count.intValue();
    }

    public static Event select ( long row_id )
    {
        Event object = null;
        Session session   = HibernateUtil.getCurrentSession();
        Transaction tx    = session.beginTransaction();
            object = (Event) session.get ( Event.class, row_id );
        tx.commit();

        return object;
    }

    public static Event findByMessageID ( String message_id )
    {
        List <Event> list = null;
        Session session   = HibernateUtil.getCurrentSession();
        Query query       = null;
        Transaction tx    = session.beginTransaction();
            query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.regression.data.Event c WHERE c.MessageID=:MessageID" );
            query.setParameter ( "MessageID", message_id );

            list = query.list();
        tx.commit();

        if ( list.size() == 1 )
        {
            return list.get ( 0 );
        }
        else
        {
            return null;
        }
    }

    public static boolean insertOrUpdate ( Event event )
    {
        boolean was_update = false;
        Event object       = EventHibernateFacade.findByMessageID ( event.getMessageID() );
        Session session    = null;
        Transaction tx     = null;
        //Event object    = (Event) session.get ( Event.class, event.getRowID() );
        //tx.commit();

        if ( null != object )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                object.setMessageID         ( event.getMessageID()         );
                object.setNodeName          ( event.getNodeName()          );
                object.setNodeType          ( event.getNodeType()          );
                object.setNodeTime          ( event.getNodeTime()          );
                object.setNodeDate          ( event.getNodeDate()          );
                object.setServerDate        ( event.getServerDate()        );
                object.setServerTime        ( event.getServerTime()        );
                object.setApplication       ( event.getApplication()       );
                object.setMessageGroup      ( event.getMessageGroup()      );
                object.setObject            ( event.getObject()            );
                object.setSeverity          ( event.getSeverity()          );
                object.setOperators         ( event.getOperators()         );
                object.setMessageText       ( event.getMessageText()       );
                object.setInstructions      ( event.getInstructions()      );
                object.setMessageAttributes ( event.getMessageAttributes() );
                session.update ( object );
            tx.commit();

            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( event );
            tx.commit();

            was_update = false;
        }

        return was_update;
    }
};
