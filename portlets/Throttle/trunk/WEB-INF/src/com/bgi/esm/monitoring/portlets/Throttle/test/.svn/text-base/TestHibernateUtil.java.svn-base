package com.bgi.esm.monitoring.portlets.Throttle.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;

public class TestHibernateUtil extends TestCase
{
    final private static Logger _log             = Logger.getLogger ( TestHibernateUtil.class );

    public TestHibernateUtil ( String param )
    {
        super ( param );

        _log.info ( "Started TestHibernateUtil test suite" );
    }

    public void testHibernateSession()
    {
        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not retrieve valid Hibernate session", session );
    }
}
