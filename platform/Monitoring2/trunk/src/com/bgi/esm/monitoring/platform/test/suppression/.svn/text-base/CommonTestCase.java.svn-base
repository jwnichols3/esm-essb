package com.bgi.esm.monitoring.platform.test.suppression;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    protected BackEndFacade bef = new BackEndFacade();

    public CommonTestCase ( String param )
    {
        super ( param );
    }

    protected SuppressionRule createSuppressionRule()
    {
        SuppressionRule rule = new SuppressionRule();
        rule.setApplicationName       ( BaseClientScript.TEST_APPLICATION );
        rule.setNodeName              ( BaseClientScript.TEST_NODE        );
        rule.setDatabaseServerName    ( BaseClientScript.TEST_DATASERVER  );
        rule.setMessage               ( BaseClientScript.TEST_MESSAGE     );
        rule.setDescription           ( BaseClientScript.TEST_DESCRIPTION );
        rule.setOwner                 ( BaseClientScript.TEST_USER        );

        //  The rule should have a default suppression time of 4 hours

        return rule;
    }

    protected SuppressionRule createSuppressionRule ( Calendar start_time, Calendar end_time )
    {
        SuppressionRule rule = createSuppressionRule();
        rule.setStartTime ( start_time );
        rule.setEndTime   ( end_time   );

        return rule;
    }

    protected SuppressionRule createSuppressionRule ( Date start_time, Date end_time )
    {
        SuppressionRule rule = createSuppressionRule();
        Calendar start       = Calendar.getInstance();
        Calendar end         = Calendar.getInstance();

        start.setTimeInMillis ( start_time.getTime() );
        end.setTimeInMillis   ( end_time.getTime()   );

        return rule;
    }

    protected boolean sendDrainMessage() throws BackEndFailure
    {
    	_log.debug("Entering sendDrainMessage");
        return bef.sendSuppressionDrainMessage();
    }

    protected boolean sendDrainMessage ( SuppressionDrainMessage message ) throws BackEndFailure
    {
        return bef.sendSuppressionDrainMessage ( message );
    }

    final private static Logger _log = Logger.getLogger ( CommonTestCase.class );
};
