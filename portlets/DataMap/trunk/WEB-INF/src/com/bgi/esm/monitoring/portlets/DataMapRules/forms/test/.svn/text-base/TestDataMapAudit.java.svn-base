package com.bgi.esm.monitoring.portlets.DataMapRules.forms.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap;
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit;
import com.bgi.esm.monitoring.portlets.DataMapRules.HibernateUtil;

public class TestDataMapAudit extends TestCase
{
    final private static Logger _log = Logger.getLogger ( DataMapAudit.class );

    public TestDataMapAudit ( String param ) 
    {
        super ( param );
    }

    public void testRetrieveHibernateSession()
    {
        _log.info ( "************************************************************ TestDataMapAudit::testRetrieveHibernateSession()" );

        Session session = HibernateUtil.getCurrentSession();

        assertNotNull ( "Could not obtain valid Hibernate session", session );
    }

    public void testSelectPage()
    {
        _log.info ( "************************************************************ TestDataMapAudit::testSelectPage()" );

        long num_records = DataMap.countRecords();

        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = DataMap.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        List <DataMapAudit> list = DataMapAudit.retrieveRecordsPage ( index, 0, 5 );

        assertNotNull ( "Could not paginate audit object list", list );

    }

    public void testSelect()
    {
        _log.info ( "************************************************************ TestDataMapAudit::testSelect()" );

        long num_records = DataMap.countRecords();
        long index       = 0;
        for ( long counter = 1; counter <= num_records; counter++ )
        {
            long num_previous_versions = DataMap.countPreviousVersions ( counter );
            if ( num_previous_versions > 0 )
            {
                index = counter;
                break;
            }
        }

        assertTrue ( "Audit functionality may not be working", index > 0 );

        _log.info ( "Attempting to retrieve index=" + index );

        DataMapAudit audit = DataMapAudit.select ( index, 1 );

        assertNotNull ( "Could not access audit entries", audit );
    }
}
