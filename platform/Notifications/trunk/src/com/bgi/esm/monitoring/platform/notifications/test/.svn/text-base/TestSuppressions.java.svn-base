package com.bgi.esm.monitoring.platform.notifications.test;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.notifications.SuppressionBean;

public class TestSuppressions extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestSuppressions.class );

    public TestSuppressions ( String param )
    {
        super ( param );
    }

    public void testCountNumSuppressions()
    {
        _log.info ( "**************************************** testGetAllSuppressions()" );

        long num_records = SuppressionBean.countNumSuppressions();

        assertTrue ( "Suppressions database is empty", num_records > 0L );

        _log.info ( "Number of suppressions: " + num_records );
    }

    public void testGetSuppression()
    {
        _log.info ( "**************************************** testGetSuppression()" );

        SuppressionBean suppression = SuppressionBean.getSuppression ( 2000 );

        assertNotNull ( suppression );

        _log.info ( "Suppression description: " + suppression.getDescription() );
        _log.info ( "Suppression start time:  " + suppression.getStartTime() );
        _log.info ( "Suppression end time:    " + suppression.getEndTime() );
    }

    public void testGetCurrentSuppressions()
    {
        _log.info ( "**************************************** testGetCurrentSuppression()" );

        List <Integer> list  = SuppressionBean.getCurrentSuppressions();
        Iterator <Integer> i = list.iterator();
        int num_records = 0;

        while ( i.hasNext() )
        {
            num_records++;

            _log.info ( "Suppression ID: " + i.next().toString() );
        }

        assertTrue ( "Suppressions database does not have any current suppressions", num_records > 0 );

        _log.info ( "Number of current records: " + num_records );
    }
}
