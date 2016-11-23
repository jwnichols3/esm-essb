package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.Audit;

/**
 * @author Dennis Lin (linden)
 * 
 */
public class AuditForMessageID 
{
    private static BackEndFacade bef = new BackEndFacade();
	/**
	 * 
	 * @throws Exception
	 */
	public void runClient ( String message_id ) throws Exception 
    {
        System.out.println ( "Retrieving all audit events for message ID: " + message_id );

        List list = bef.getAllAuditForMessageId ( message_id );
        Iterator i = list.iterator();

        while ( i.hasNext() )
        {
            System.out.println ( "\t" + i.next().toString() );
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception 
    {
		System.out.println("begin");

        AuditForMessageID a = new AuditForMessageID();

        a.runClient ( args[0] );
		
		System.out.println("end");
	}
}
