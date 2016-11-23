package com.bgi.esm.monitoring.platform.filters.Archway.orm.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.HibernateUtil;

public class TestHibernateUtil extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHibernateUtil.class );

    public TestHibernateUtil ( String param )
    {
        super ( param );
    }

    public void testRetrieveSessionFactory()
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        assertNotNull ( "Could not retrieve session factory", sessionFactory );
    }

    public void testRetrieveCurrentSession()
    {
        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not retrieve a usable Hibernate session", session );
    }
}
