package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.hibernate;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    final private static Logger _log                   = Logger.getLogger ( HibernateUtil.class );
    final private static String hibernate_config_file  = "hibernate.cfg.xml";
    final private static String application_props_file = "application.properties";
    private static SessionFactory sessionFactory       = null;
    private static Properties application_props        = null;


    //////////////////////////////////////////////////////////////////////
    //  Initialize this singleton class
    //////////////////////////////////////////////////////////////////////
    static 
    {
        configureHibernate();

        //configureApplicationProperties();
    }

    public static synchronized boolean configureHibernate()
    {
        //  Configuring the Hibernate Session Factory
        try 
        {
            _log.info ( "Creating Session Factory..." );

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure( hibernate_config_file ).buildSessionFactory();
        } 
        catch ( Throwable ex ) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", ex);

            throw new ExceptionInInitializerError(ex);
        }

        return true;
    }

    public static synchronized boolean configureApplicationProperties()
    {
        //  Loading Application Properties
        _log.info ( "******************** configureApplicationProperties() - attempting to load properties file: " + application_props_file );

        try
        {
            ClassLoader cl    = HibernateUtil.class.getClassLoader();
            InputStream is    = cl.getResourceAsStream ( application_props_file );
            application_props = new Properties();
            application_props.load ( is );
        }
        catch ( IOException exception )
        {
             StringBuilder message = new StringBuilder ( "Could not load application properties file: " );
             message.append ( application_props_file );

             _log.fatal ( message.toString() );

             return false;
        }
        catch ( Throwable ex )
        {
             _log.fatal ( "Could not load application properties file: " + application_props_file , ex );

             return false;
        }

        return true;
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static Session getCurrentSession()
    {
        return getSessionFactory().getCurrentSession();
    }

    public static Session getNewSession()
    {
        return getSessionFactory().openSession();
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
