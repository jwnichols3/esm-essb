package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.RawOvi;

/**
 * Exercise raw OVI RMI features.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class RawOvi2 {

	/**
	 * 
	 * @throws Exception
	 */
	public void runClient() throws Exception {
		RawOvi rod1 = new RawOvi();
//		rod1.setModule("source");
		rod1.setMessageId("id");
		rod1.setXmlPayload("xml payload");

		BackEndFacade bef = new BackEndFacade();

		RawOvi rod2 = bef.addOrUpdateRawOvi(rod1);
		System.out.println("insert returns:" + rod2);

		RawOvi rod3 = bef.selectRawOvi(rod2.getRowKey());
		System.out.println("select returns:" + rod3);

		List list = bef.getAllRawOvi();
		System.out.println("list size:" + list.size());

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			RawOvi rod = (RawOvi) iterator.next();
			System.out.println(rod);
		}

		// boolean flag = bef.deleteRawOvi(rod2);
		// System.out.println("delete returns:" + flag);
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		RawOvi2 ro2 = new RawOvi2();
		ro2.runClient();

		System.out.println("end");
	}
}
