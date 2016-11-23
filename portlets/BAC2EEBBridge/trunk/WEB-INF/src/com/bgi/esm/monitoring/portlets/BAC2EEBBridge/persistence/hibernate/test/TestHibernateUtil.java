package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.hibernate.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.hibernate.HibernateUtil;

public class TestHibernateUtil extends CommonBaseTestCase 
{
	private static Logger _log = Logger.getLogger ( TestHibernateUtil.class );

	public TestHibernateUtil(String param) 
    {
		super(param);
	}

	public void testInitializeHibernateUtil() 
    {
		logFunction("testInitializeHibernateUtil");

		Session session = HibernateUtil.getCurrentSession();

		assertNotNull("Could not obtain Hibernate session", session);
	}
}
