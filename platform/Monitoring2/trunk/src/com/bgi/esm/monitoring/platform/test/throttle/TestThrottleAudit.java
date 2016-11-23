package com.bgi.esm.monitoring.platform.test.throttle;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.throttle.CommonTestCase;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * Test the audit functionalities of the data map entity bean through RMI.
 * 
 * @author Dennis Lin (linden)
 */
public class TestThrottleAudit extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestThrottleAudit.class );
    private BackEndFacade bef        = new BackEndFacade();

    public TestThrottleAudit ( String param )
    {
        super ( param );
    }

	public void testAddAndUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndUpdate()" );

        ThrottleRule rule   = createThrottleRule();
	}

    public void testAddAndDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndDelete()" );

        ThrottleRule rule   = createThrottleRule();
    }

    public void testAddUpdateDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddUpdateDelete()" );

        ThrottleRule rule   = createThrottleRule();
    }
}
