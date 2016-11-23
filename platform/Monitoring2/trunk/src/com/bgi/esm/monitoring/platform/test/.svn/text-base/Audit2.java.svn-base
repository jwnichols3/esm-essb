package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.Audit;
//import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * @author coleguy
 * 
 */
public class Audit2 {

	/**
	 * 
	 * @throws Exception
	 */
	public void runClient() throws Exception {
		Audit audit1 = new Audit();
//		audit1.setModule("module");
		audit1.setAction("action");
		audit1.setMessageId("messageId");

		BackEndFacade bef = new BackEndFacade();

		Audit audit2 = bef.addOrUpdateAudit(audit1);
		System.out.println("insert returns:" + audit2);

		Audit audit3 = bef.selectAudit(audit2.getRowKey());
		System.out.println("select returns:" + audit3);

		List list = bef.getAllAudit();
		System.out.println("list size:" + list.size());

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Audit temp = (Audit) iterator.next();
			System.out.println(temp);
		}

		// boolean flag = bef.deleteThrottleRule(rule2);
		// System.out.println("delete returns:" + flag);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");
		
		Audit2 a2 = new Audit2();
		a2.runClient();
		
		System.out.println("end");
	}
}
