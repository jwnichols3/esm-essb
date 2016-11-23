package com.bgi.esm.monitoring.portlets.Throttle;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *  Manages the Hibernate session.  Follows the singleton pattern.
 *
 *  @author Dennis Lin (linden)
 */
public class HibernateUtil 
{
    private static final Logger _log = Logger.getLogger ( HibernateUtil.class );

    private static final SessionFactory sessionFactory;

    static 
    {
        try 
        {
            _log.info ( "Creating Session Factory..." );

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } 
        catch ( Throwable exception ) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", exception );

            throw new ExceptionInInitializerError ( exception );
        }
    }

    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }

    public static Session getCurrentSession()
    {
        return getSessionFactory().getCurrentSession();
    }

    public static void shutdown()
    {
        _log.info ( "Shutdown session factory" );
        sessionFactory.close();
    }

    public void finalize()
    {
        _log.info ( "Finalizing session factory" );
        sessionFactory.close();
    }
}
