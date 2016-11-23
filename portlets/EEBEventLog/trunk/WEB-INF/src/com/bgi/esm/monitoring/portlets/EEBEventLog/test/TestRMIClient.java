package com.bgi.esm.monitoring.portlets.EEBEventLog.test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.portlets.EEBEventLog.Toolkit;

import org.apache.log4j.Logger;
import junit.framework.TestCase;

public class TestRMIClient extends TestCase
{
    final private static Logger _log       = Logger.getLogger ( TestRMIClient.class );
    final private static BackEndFacade bef = new BackEndFacade();


    public TestRMIClient ( String param )
    {
        super ( param );
    }

    public void testRetrieveAudit() throws BackEndFailure
    {
        List list = bef.getAllAudit();

        _log.info ( "Retrieved list of audit entries: " + list.size() );
    }

    public void testRetrieveEventsByGroupLastHour() throws BackEndFailure
    {
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();

        start_time.add ( Calendar.HOUR, -1 );

        List list = bef.findAllEventsByGroupsBetweenTimes ( start_time, end_time );

        _log.info ( "Retrieved list of events within the last hour: " + list.size() );
    }

}
