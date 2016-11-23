package com.bgi.esm.monitoring.platform.notifications;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    final private static Logger _log                          = Logger.getLogger ( HibernateUtil.class );
    private static SessionFactory suppressionsSessionFactory  = null;
    private static SessionFactory notificationsSessionFactory = null;
    static 
    {
        try 
        {
            _log.info ( "Creating Session Factory..." );

            // Create the SessionFactory from hibernate.cfg.xml
            suppressionsSessionFactory = new Configuration().configure( "hibernate.suppressions.cfg.xml" ).buildSessionFactory();

            notificationsSessionFactory = new Configuration().configure( "hibernate.notifications.cfg.xml" ).buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getNotificationsSessionFactory()
    {
        return notificationsSessionFactory;
    }

    public static SessionFactory getSuppressionsSessionFactory()
    {
        return suppressionsSessionFactory;
    }

    public static Session getNotificationsCurrentSession()
    {
        return getNotificationsSessionFactory().getCurrentSession();
    }

    public static Session getSuppressionsCurrentSession()
    {
        return getSuppressionsSessionFactory().getCurrentSession();
    }

    public static void shutdown()
    {
        _log.warn ( "Shutdown session factory for suppressions database..." );
        suppressionsSessionFactory.close();
    }

    public void finalize()
    {
        _log.warn ( "Finalizing session factory for suppressions database..." );
        suppressionsSessionFactory.close();
    }
}

