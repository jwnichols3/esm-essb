package com.bgi.esm.monitoring.platform.test.eeb_properties;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

public class CommonTestCase extends TestCase
{
    final private static Logger _log              = Logger.getLogger ( CommonTestCase.class );
    final protected String TEST_PROPERTY_NAME_01  = "test.property.01";
    final protected String TEST_PROPERTY_NAME_02  = "test.property.02";
    final protected String TEST_PROPERTY_VALUE_01 = "test-value-01";
    final protected String TEST_PROPERTY_VALUE_02 = "test-value-02";

    protected BackEndFacade bef                   = new BackEndFacade();

    public CommonTestCase ( String param )
    {
        super ( param );
    }
};
