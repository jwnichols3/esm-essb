package com.bgi.esm.monitoring.platform.test.openview;

import org.hibernate.Session;
import com.bgi.esm.monitoring.platform.test.openview.CommonTestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestOpenview extends CommonTestCase
{
    public TestOpenview ( String param )
    {
        super ( param );
    }

    public void testHibernateSession()
    {
        Session session = getHibernateSession();

        assertNotNull ( "Could not obtain Hibernate session to the Openview database", session );
    }
}
