package com.bgi.esm.monitoring.platform.test.events_by_group;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    final protected static Logger _log = Logger.getLogger ( CommonTestCase.class );
    protected BackEndFacade bef        = new BackEndFacade();
    protected String DEFAULT_GROUP     = "test-eeb";

    public CommonTestCase ( String param )
    {
        super ( param );
    }

    protected EventsByGroup createEventsByGroup ( String message_id )
    {
        EventsByGroup ebg = new EventsByGroup ();
        ebg.setGroupName ( DEFAULT_GROUP );
        ebg.setMessageId ( message_id    );

        return ebg;
    }

    protected static void logFunction ( String function_name )
    {
        _log.info ( "**************************************** " + function_name );
    }
};
