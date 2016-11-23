package com.bgi.esm.monitoring.platform.test.throttle;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.throttle.CommonTestCase;
import org.apache.log4j.Logger;

/**
 * Test modifying a data map rule through RMI
 * 
 * @author Dennis Lin (linden)
 */
public class TestThrottle extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestThrottle.class );
    private BackEndFacade bef = new BackEndFacade();

    public TestThrottle ( String param )
    {
        super ( param );
    }


	public void testAdd() throws BackEndFailure
    {
        _log.info ( "**************************************** testAdd()" );

        ThrottleRule rule = createThrottleRule();
	    ThrottleRule add  = bef.addOrUpdateThrottleRule(rule);

        assertNotNull ( "Could not add data map rule", add );
	}

    public void testAddThrottleAndRetrieve() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddThrottleAndRetrieve()" );

        ThrottleRule rule = createThrottleRule();
	    ThrottleRule add  = bef.addOrUpdateThrottleRule(rule);

        assertNotNull ( "Could not add data map rule", add );
    }

    public void testAddThrottleRetrieveUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddThrottleRetrieveUpdate()" );

        ThrottleRule rule = createThrottleRule();
	    ThrottleRule add  = bef.addOrUpdateThrottleRule(rule);

        assertNotNull ( "Could not add data map rule", add );

        add.setStormLevel ( 2 );
        ThrottleRule update = bef.addOrUpdateThrottleRule ( add );
    }
}
