package com.bgi.esm.monitoring.platform.test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.value.Responder;

/**
 * Exercise service center entity bean
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Responder4 {

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception {
    	BackEndFacade bef        = new BackEndFacade();
        Responder r1             = new Responder ();
        Calendar testTimestamp   = Calendar.getInstance();
        Calendar testTimestamp2  = Calendar.getInstance();
        testTimestamp2.setTimeInMillis ( testTimestamp.getTimeInMillis() - 10000 );

        Calendar searchTimestamp = Calendar.getInstance();
        searchTimestamp.setTimeInMillis ( testTimestamp.getTimeInMillis() - 5000 );

        String test_message_key  = "test-message-key-"  + testTimestamp.getTimeInMillis();

        r1.setMessageId                 ( "test-incident-id-"  + testTimestamp.getTimeInMillis() );
        r1.setAlarmpointEventID         ( "test-ap-event-id-"  + testTimestamp.getTimeInMillis() );
        r1.setServiceCenterTicketNumber ( "test-sc-ticket-"    + testTimestamp.getTimeInMillis() );
        r1.setMessageKey                ( test_message_key );
        r1.setTimestamp                 ( testTimestamp2 );

        r1 = bef.addOrUpdateResponder ( r1 );
        System.out.println ( "Responder #1 - RowID: " + r1.getRowKey() );

    	Responder r3  = bef.selectResponderByMessageKey ( test_message_key, searchTimestamp );
        if ( null == r3 )
        {
            System.out.println ( "CORRECT BEHAVIOR #1" );
        }
        else
        {
    	    System.out.println("select returns:" + r3.getRowKey() );
        }

        Responder r2  = new Responder ();

        r2.setMessageId                 ( "test-incident-id-"  + testTimestamp.getTimeInMillis() );
        r2.setAlarmpointEventID         ( "test-ap-event-id-"  + testTimestamp.getTimeInMillis() );
        r2.setServiceCenterTicketNumber ( "test-sc-ticket-"    + testTimestamp.getTimeInMillis() );
        r2.setMessageKey                ( test_message_key );
        r2.setTimestamp                 ( testTimestamp );

        r2 = bef.addOrUpdateResponder ( r2 );
        System.out.println ( "Responder #2 - RowID: " + r2.getRowKey() );

    	r3  = bef.selectResponderByMessageKey ( test_message_key, searchTimestamp );
    	System.out.println("select returns:" + r3.getRowKey() );

        if ( r3.getRowKey().equals ( r2.getRowKey() ) )
        {
            System.out.println ( "CORRECT BEHAVIOR #2" );
        }
        else
        {
    	    System.out.println("select returns:" + r3.getRowKey() );
        }
	}
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	    System.out.println("begin");
	
	    Responder4 sc = new Responder4();
	    sc.runClient();
	
	    System.out.println("end");
    }
}
