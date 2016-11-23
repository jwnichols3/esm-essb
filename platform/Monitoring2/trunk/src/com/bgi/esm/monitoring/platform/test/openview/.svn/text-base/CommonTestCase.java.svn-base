package com.bgi.esm.monitoring.platform.test.openview;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.openview.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    final private static Logger _log              = Logger.getLogger ( CommonTestCase.class );

    protected BackEndFacade bef                   = new BackEndFacade();


    public CommonTestCase ( String param )
    {
        super ( param );
    }

    protected void logFunction ( String function_name )
    {
        if ( _log.isInfoEnabled() )
        {
            StringBuilder message = new StringBuilder();
                message.append ( "**************************************** " );
                message.append ( function_name );
            _log.info ( message.toString() );
        }
    }

    protected static Session getHibernateSession()
    {
        return HibernateUtil.getCurrentSession();
    }
};
