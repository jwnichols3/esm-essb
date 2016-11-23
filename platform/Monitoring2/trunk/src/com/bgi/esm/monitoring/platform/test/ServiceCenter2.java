package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;

/**
 * Exercise service center entity bean
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenter2 {

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception {
        ServiceCenter sc1  = new ServiceCenter ();
        sc1.setTicketNum          ( "TestIM-" + System.currentTimeMillis() );
        sc1.setMessageId          ( "OVITestMessageId-" + System.currentTimeMillis() );
        sc1.setTicketOpenedBy     ( "test-user" );
        sc1.setTicketCategory     ( "test" );
        sc1.setTicketPriorityCode ( "test-priority" );
        sc1.setTicketSeverityCode ( "test-severity" );
        sc1.setTicketAssignment   ( "test-assignment" );
        sc1.setTicketMessage      ( "test-message" );

        System.out.println ( "Ticket category: " + sc1.getTicketCategory() );

    	BackEndFacade bef = new BackEndFacade();

    	ServiceCenter sc2  = bef.addOrUpdateServiceCenter(sc1);
    	System.out.println("insert returns:" + sc2 );

    	ServiceCenter sc3  = bef.selectServiceCenterTicket ( sc2.getRowKey() );
    	System.out.println("select returns:" + sc3);

    	List list = bef.getAllServiceCenterTickets();
    	System.out.println("list size:" + list.size());

	Iterator iterator = list.iterator();
	while (iterator.hasNext()) {
	    ServiceCenter sc = (ServiceCenter) iterator.next();
	    System.out.println(sc);
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
	
	ServiceCenter2 sc = new ServiceCenter2();
	sc.runClient();
	
	System.out.println("end");
    }
}
