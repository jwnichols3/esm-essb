package com.bgi.esm.monitoring.platform.notifications.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.notifications.HibernateUtil;

public class TestHibernateUtil extends TestCase
{
    final private static Logger _log             = Logger.getLogger ( TestHibernateUtil.class );

    public TestHibernateUtil ( String param )
    {
        super ( param );

        _log.info ( "Started TestHibernateUtil test suite" );
    }

    public void testHibernateSessionForSuppressions()
    {
        Session session = HibernateUtil.getSuppressionsCurrentSession();

        assertNotNull ( "Could not retrieve valid Hibernate session for suppressions", session );
    }

    public void testHibernateSessionForNotifications()
    {
        Session session = HibernateUtil.getNotificationsCurrentSession();

        assertNotNull ( "Could not retrieve valid Hibernate session for notifiactions", session );
    }
}

