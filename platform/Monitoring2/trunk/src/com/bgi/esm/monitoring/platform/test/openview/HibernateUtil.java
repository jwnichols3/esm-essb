package com.bgi.esm.monitoring.platform.test.openview;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class HibernateUtil
{
    final private static Logger _log             = Logger.getLogger ( HibernateUtil.class );
    private static SessionFactory sessionFactory = null;
    static 
    {
        try 
        {
            _log.info ( "Creating Session Factory..." );

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure( "com/bgi/esm/monitoring/platform/test/openview/hibernate.cfg.xml" ).buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
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
        _log.warn ( "Shutdown session factory" );
        sessionFactory.close();
    }

    public void finalize()
    {
        _log.warn ( "Finalizing session factory" );
        sessionFactory.close();
    }
}
