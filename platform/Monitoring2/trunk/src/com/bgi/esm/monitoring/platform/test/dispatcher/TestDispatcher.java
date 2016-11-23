package com.bgi.esm.monitoring.platform.test.dispatcher;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.test.dispatcher.CommonTestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestDispatcher extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDispatcher.class );

    public TestDispatcher ( String param )
    {
        super ( param );
    }

    public void testDispatcher() throws JMSException
    {
        DispatcherMessage dm = new DispatcherMessage();
        dm.setTicketMessage ( createTestTicketMessage() );
        dm.setDataMapRule   ( createTestDataMapRule()   );

        ObjectMessage om = _queueSession.createObjectMessage ( dm );

        _queueSender.send ( om );
    }

    public void testDispatcherUpdate() throws JMSException, InterruptedException
    {
        long timestamp = System.currentTimeMillis();
        _log.info ( "Timestamp: " + timestamp );

        DispatcherMessage dm1 = new DispatcherMessage();
        dm1.setTicketMessage ( createTestOriginalTicketMessage ( timestamp ) );
        dm1.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om1     = _queueSession.createObjectMessage ( dm1 );
        _queueSender.send ( om1 );

        Thread.currentThread().sleep ( 15000 );

        DispatcherMessage dm2 = new DispatcherMessage();
        dm2.setTicketMessage ( createTestDuplicateTicketMessage ( timestamp, 1 ) );
        dm2.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om2     = _queueSession.createObjectMessage ( dm2 );
        _queueSender.send ( om2 );
    }

    /*
    public void testDispatcherUpdate_DuplicateCountOutOfSync() throws JMSException, InterruptedException
    {
        long timestamp = System.currentTimeMillis();
        _log.info ( "Timestamp: " + timestamp );

        DispatcherMessage dm1 = new DispatcherMessage();
        dm1.setTicketMessage ( createTestOriginalTicketMessage ( timestamp ) );
        dm1.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om1     = _queueSession.createObjectMessage ( dm1 );
        _queueSender.send ( om1 );

        Thread.currentThread().sleep ( 15000 );

        DispatcherMessage dm2 = new DispatcherMessage();
        dm2.setTicketMessage ( createTestDuplicateTicketMessage ( timestamp, 10 ) );
        dm2.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om2     = _queueSession.createObjectMessage ( dm2 );
        _queueSender.send ( om2 );

        Thread.currentThread().sleep ( 15000 );

        DispatcherMessage dm3 = new DispatcherMessage();
        dm3.setTicketMessage ( createTestDuplicateTicketMessage ( timestamp, 5 ) );
        dm3.setDataMapRule   ( createTestDataMapRule()   );
        ObjectMessage om3     = _queueSession.createObjectMessage ( dm3 );
        _queueSender.send ( om3 );
    }
    //*/
}
