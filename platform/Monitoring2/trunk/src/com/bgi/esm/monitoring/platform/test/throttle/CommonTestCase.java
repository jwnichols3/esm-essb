package com.bgi.esm.monitoring.platform.test.throttle;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    public CommonTestCase ( String param )
    {
        super ( param );
    }

    protected ThrottleRule createThrottleRule()
    {
        ThrottleRule rule = new ThrottleRule();

        return rule;
    }
};
