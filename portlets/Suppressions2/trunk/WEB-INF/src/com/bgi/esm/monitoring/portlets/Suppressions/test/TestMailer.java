package com.bgi.esm.monitoring.portlets.Suppressions.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;

public class TestMailer extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestMailer.class );

    public TestMailer ( String param )
    {
        super ( param );
    }

    public void testMailer()
    {
        _log.info ( "**************************************** TestMailer::testMailer()" );
    }
};
