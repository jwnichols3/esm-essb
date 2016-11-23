package com.bgi.esm.monitoring.platform.test.dispatcher;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.test.dispatcher.CommonTestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestResponder extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestResponder.class );

    public TestResponder ( String param )
    {
        super ( param );
    }

    public void testFindResponderByMessageId() throws BackEndFailure
    {
        Responder r1 = bef.selectResponderByMessageId ( "b4f8c9c2-1bf9-71dc-18f1-453434340000" );

        assertNotNull ( "Could not find responder object with specified message key", r1 );

        _log.info ( "Responder - RowKey=" + r1.getRowKey() );
    }

    public void testFindResponderByMessageKey() throws BackEndFailure
    {
        Responder r1  = new Responder ();
        long timestamp = System.currentTimeMillis();
        String test_message_key = "test-message-key-" + timestamp;
        r1.setMessageId                 ( "test-incident-id-"  + timestamp );
        r1.setAlarmpointEventID         ( "test-ap-event-id-"  + timestamp );
        r1.setServiceCenterTicketNumber ( "test-sc-ticket-"    + timestamp );
        r1.setMessageKey                ( test_message_key );
        r1.setLockedTimestamp           ( timestamp );

        System.out.println ( "Message ID: " + r1.getMessageId() );

    	BackEndFacade bef = new BackEndFacade();
    	Responder r2  = bef.addOrUpdateResponder(r1);

        Responder responder = bef.selectResponderByMessageKey ( test_message_key );

        _log.info ( "Locked timestamp: " + responder.getLockedTimestamp() );

        assertNotNull ( "Could not find responder object with message key", responder );
    }

    /*
    public void testFindResponderByMessageKey2() throws BackEndFailure
    {
        BackEndFacade bef = new BackEndFacade();
        Responder responder = bef.selectResponderByMessageKey ( "test-eeb:Sun SPARC (HTTPS):ovpa_datacollection_mon:OVPA:major" );

        assertNotNull ( "Could not find responder object with message key", responder );
    }
    //*/

    public void testFindResponderWithNonExistentMessageKey() throws BackEndFailure
    {
        Responder r1  = new Responder ();
        long timestamp = System.currentTimeMillis();
        r1.setMessageId                 ( "test-incident-id-"  + timestamp );
        r1.setAlarmpointEventID         ( "test-ap-event-id-"  + timestamp );
        r1.setServiceCenterTicketNumber ( "test-sc-ticket-"    + timestamp );
        r1.setMessageKey                ( "test-message-key"   + timestamp );

        System.out.println ( "Message ID: " + r1.getMessageId() );

    	BackEndFacade bef = new BackEndFacade();
    	Responder r2  = bef.addOrUpdateResponder(r1);

        //Responder r3 = bef.findBym
    }

    public void testFindResponderByTicketNumber() throws BackEndFailure
    {
        String ticket_number = "IM01621476";
        Responder responder = bef.findResponderByServiceCenterTicketNumber ( ticket_number );

        assertNotNull ( "Could not find responder by ticket number: " + ticket_number, responder );

        _log.info ( "Responder ticket number: " + responder.getServiceCenterTicketNumber() );
    }
}
