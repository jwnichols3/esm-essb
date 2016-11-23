package com.bgi.esm.monitoring.portlets.Suppressions.forms.test;

import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;

public class TestSuppressionAudit extends TestCase
{
    final private static Logger _log = Logger.getLogger ( SuppressionAudit.class );

    public TestSuppressionAudit ( String param ) 
    {
        super ( param );
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestSuppressionAudit::testRetrieveHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void testSelectPage()
    {
        _log.info ( "************************************************************ TestSuppressionAudit::testSelectPage()" );

        long num_records = Suppression.countRecords();

        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = Suppression.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        List <SuppressionAudit> list = SuppressionAudit.retrieveRecordsPage ( index, 0, 5 );

        assertNotNull ( "Could not paginate audit object list", list );

    }

    public void testSelect()
    {
        _log.info ( "************************************************************ TestSuppressionAudit::testSelect()" );

        long num_records = Suppression.countRecords();
        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = Suppression.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        _log.info ( "Attempting to retrieve index=" + index );

        SuppressionAudit audit = SuppressionAudit.select ( index, 1 );

        assertNotNull ( "Could not access audit entries", audit );
    }
}
