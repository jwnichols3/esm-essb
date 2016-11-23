package com.bgi.esm.monitoring.platform.test.openview;

import com.bgi.esm.monitoring.platform.test.openview.CommonTestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestOpenviewInterconnect extends CommonTestCase
{
    public TestOpenviewInterconnect ( String param )
    {
        super ( param );
    }

    public void testRetrieveEventStatus()
    {
        logFunction ( "testRetrieveEventStatus()" );

        //  Simulate an event coming from Openview (so we know the exact MessageID key)
    }

    public void testAcknowledgeEvent()
    {
        logFunction ( "testAcknowledgeEvent()" );
    }
};
