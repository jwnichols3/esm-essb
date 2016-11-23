package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.Spool;

/**
 * @author coleguy
 *
 */
public class Spool2 {

	public void runClient() throws Exception {
		Spool spool1 = new Spool();
		spool1.setGroup("group");
		spool1.setOvoKey("ovokey");
		spool1.setSpoolKey("spoolkey");

		BackEndFacade bef = new BackEndFacade();

		Spool spool2 = bef.addOrUpdateSpoolRow(spool1);
		System.out.println("insert returns:" + spool2);

		Spool spool3 = bef.selectSpoolRow(spool2.getRowKey());
		System.out.println("select returns:" + spool3);

		List list = bef.getAllSpoolRows();
		System.out.println("list size:" + list.size());

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Spool temp = (Spool) iterator.next();
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
		
		Spool2 s2 = new Spool2();
		s2.runClient();
		
		System.out.println("end");
	}
}
