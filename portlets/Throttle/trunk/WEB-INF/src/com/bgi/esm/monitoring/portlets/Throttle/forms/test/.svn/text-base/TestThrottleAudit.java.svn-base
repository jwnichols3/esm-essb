package com.bgi.esm.monitoring.portlets.Throttle.forms.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle;
import com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;

public class TestThrottleAudit extends TestCase
{
    final private static Logger _log = Logger.getLogger ( ThrottleAudit.class );

    public TestThrottleAudit ( String param ) 
    {
        super ( param );
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestThrottleAudit::testRetrieveHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void testSelectPage()
    {
        _log.info ( "************************************************************ TestThrottleAudit::testSelectPage()" );

        long num_records = Throttle.countRecords();

        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = Throttle.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        List <ThrottleAudit> list = ThrottleAudit.retrieveRecordsPage ( index, 0, 5 );

        assertNotNull ( "Could not paginate audit object list", list );

    }

    public void testSelect()
    {
        _log.info ( "************************************************************ TestThrottleAudit::testSelect()" );

        long num_records = Throttle.countRecords();
        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = Throttle.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        _log.info ( "Attempting to retrieve index=" + index );

        ThrottleAudit audit = ThrottleAudit.select ( index, 1 );

        assertNotNull ( "Could not access audit entries", audit );
    }
}
