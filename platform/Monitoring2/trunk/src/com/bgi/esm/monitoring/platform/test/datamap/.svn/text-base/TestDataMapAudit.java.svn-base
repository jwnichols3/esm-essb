package com.bgi.esm.monitoring.platform.test.datamap;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.datamap.CommonTestCase;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * Test the audit functionalities of the data map entity bean through RMI.
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestDataMapAudit extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDataMapAudit.class );
    private BackEndFacade bef        = new BackEndFacade();

    public TestDataMapAudit ( String param )
    {
        super ( param );
    }

	public void testAddAndUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndUpdate()" );

        DataMapRule rule   = createDataMapRule();
	}

    public void testAddAndDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndDelete()" );

        DataMapRule rule   = createDataMapRule();
    }

    public void testAddUpdateDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddUpdateDelete()" );

        DataMapRule rule   = createDataMapRule();
    }
}
