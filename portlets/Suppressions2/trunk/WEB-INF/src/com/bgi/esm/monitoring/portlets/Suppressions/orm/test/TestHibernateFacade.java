package com.bgi.esm.monitoring.portlets.Suppressions.orm.test;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateFacade;
import com.bgi.esm.monitoring.portlets.Suppressions.orm.ExpirationNotification;

public class TestHibernateFacade extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestHibernateFacade.class );

    public TestHibernateFacade ( String param )
    {
        super ( param );
    }

    public void testGetHibernateSession()
    {
    }

    public void testInsertSelectUpdateNotification()
    {
    }
}
