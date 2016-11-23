package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.Responder;

/**
 * Exercise service center entity bean
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Responder2 {

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception {
        Responder r1  = new Responder ();
        long timestamp = System.currentTimeMillis();
        r1.setMessageId                 ( "test-incident-id-"  + timestamp );
        r1.setAlarmpointEventID         ( "test-ap-event-id-"  + timestamp );
        r1.setServiceCenterTicketNumber ( "test-sc-ticket-"    + timestamp );

        System.out.println ( "Message ID: " + r1.getMessageId() );

    	BackEndFacade bef = new BackEndFacade();

    	Responder r2  = bef.addOrUpdateResponder(r1);
    	System.out.println("insert returns:" + r2.getMessageId() );

    	Responder r3  = bef.selectResponderByMessageId ( r2.getMessageId() );
    	System.out.println("select returns:" + r3.getMessageId() );

    	List list = bef.getAllResponderIncidents ();
    	System.out.println("list size:" + list.size());

	Iterator iterator = list.iterator();
	while (iterator.hasNext()) {
	    Responder r = (Responder) iterator.next();
	    System.out.println(r);
	}

//	boolean flag = bef.deleteDataMapRule(rule3);
//	System.out.println("delete returns:" + flag);
    }
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	System.out.println("begin");
	
	Responder2 sc = new Responder2();
	sc.runClient();
	
	System.out.println("end");
    }
}
