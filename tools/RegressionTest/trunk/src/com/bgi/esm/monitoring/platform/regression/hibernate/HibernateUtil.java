package com.bgi.esm.monitoring.platform.regression.hibernate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

public class HibernateUtil
{
    final private static Logger _log                   = Logger.getLogger ( HibernateUtil.class );
    final private static String hibernate_config_file  = "hibernate.cfg.xml";
    final private static String application_props_file = "application.properties";
    private static Configuration configuration         = null;
    private static Document HibernateConfigDocument    = null;
    private static SessionFactory sessionFactory       = null;
    private static Properties application_props        = null;
    private static Configuration derbyConfiguration    = null;
    private static SessionFactory derbySessionFactory  = null;


    //////////////////////////////////////////////////////////////////////
    //  Initialize this singleton class
    //////////////////////////////////////////////////////////////////////
    static 
    {
        retrieveConfigurationFile();
        configureHibernate();
        configureHibernateDerby();

        //configureApplicationProperties();
    }

    private static void retrieveConfigurationFile()
    {
        try
        {
            ClassLoader cl                 = HibernateUtil.class.getClassLoader();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder        = factory.newDocumentBuilder();
            ByteArrayInputStream bais      = new ByteArrayInputStream ( retrieveResource ( hibernate_config_file ) );
            HibernateConfigDocument        = builder.parse ( bais );
        }
        catch ( Exception exception )
        {
            _log.fatal ( "Could not parse Hibernate configuration file: " + hibernate_config_file, exception );
        }
    }

    private static byte[] retrieveResource ( String location )
    {
        ClassLoader cl = HibernateUtil.class.getClassLoader();
        InputStream is = null;
        ByteBuffer bb  = null;
        try
        {
            is = cl.getResourceAsStream ( location );
            URL u = HibernateUtil.class.getResource ( location );
            if ( null != u )
            {
                _log.info ( "Retrieving resource from URL: " + u.toString() );
            }
            bb = ByteBuffer.allocate ( is.available() );
            byte contents[]    = new byte[1024];
            int num_bytes_read = 0;
            int total_read     = 0;
            do
            {
                num_bytes_read = is.read ( contents );

                if ( num_bytes_read > 0 )
                {
                    bb.put ( contents, 0, num_bytes_read );

                    total_read += num_bytes_read;
                }
            }
            while ( num_bytes_read >= 0 );
            is.close();

            return bb.array();
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not retrieve resource: " + location, exception );
        }

        return null;
    }

    public static boolean configureHibernate()
    {
        //  Configuring the Hibernate Session Factory
        try 
        {
            _log.info ( "Creating Session Factory..." );

            //  Retrieve the necessary resources
            byte resource1[] = retrieveResource ( "com/bglobal/gss/peregrine/persistence/hibernate/ServiceCenterIncidentRequests.hbm.xml" );
            //byte resource2[] = retrieveResource ( "com/bgi/esm/monitoring/platform/regression/data/DataMap.hbm.xml" );

            // Create the SessionFactory from hibernate.cfg.xml
            configuration = new Configuration().configure( HibernateConfigDocument );
            //configuration = new Configuration().configure( hibernate_config_file );
            configuration = configuration.addXML ( new String ( resource1 ) );
            //configuration = configuration.addXML ( new String ( resource2 ) );

            sessionFactory = configuration.buildSessionFactory();
        } 
        catch ( Throwable ex ) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", ex);

            throw new ExceptionInInitializerError(ex);
        }

        return true;
    }

    public static boolean configureHibernateDerby()
    {
        //  Configuring the Hibernate Session Factory
        try 
        {
            _log.info ( "Creating Session Factory for Derby..." );

            //  Retrieve the necessary resources
            byte resource1[] = retrieveResource ( "com/bgi/esm/monitoring/platform/regression/data/Event.derby.hbm.xml" );
            byte resource2[] = retrieveResource ( "com/bgi/esm/monitoring/platform/regression/data/DataMap.derby.hbm.xml" );

            // Create the SessionFactory from hibernate.cfg.xml
            derbyConfiguration = new Configuration().configure( "hibernate.derby.cfg.xml" );
            derbyConfiguration = derbyConfiguration.addXML ( new String ( resource1 ) );
            derbyConfiguration = derbyConfiguration.addXML ( new String ( resource2 ) );

            derbySessionFactory = derbyConfiguration.buildSessionFactory();
        } 
        catch ( Throwable ex ) 
        {
            // Make sure you log the exception, as it might be swallowed
            _log.fatal ("Initial SessionFactory creation failed.", ex);

            throw new ExceptionInInitializerError(ex);
        }

        return true;
    }

    public static boolean configureApplicationProperties()
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

        //  Initialization logic

        return true;
    }

    public static SessionFactory getDerbySessionFactory()
    {
        return derbySessionFactory;
    }

    public static Session getDerbyCurrentSession()
    {
        return getDerbySessionFactory().getCurrentSession();
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

