package com.bgi.esm.monitoring.portlets.DataMapRules.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.portlets.DataMapRules.DataMapGenerator;

public class TestGenerateDataMapFiles extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestGenerateDataMapFiles.class );

    public TestGenerateDataMapFiles ( String param )
    {
        super ( param );
    }

    public void testGenerateOpenviewDataMap()
    {
        DataMapGenerator.generateOpenviewDataMap ( "OpenviewDataMap.pl" );
    }

    public void testGenerateServiceCenterDataMap()
    {
        DataMapGenerator.generateServiceCenterDataMap ( "ServiceCenterDataMap.pl" );
    }
};
