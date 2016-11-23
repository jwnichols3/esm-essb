package com.bgi.esm.monitoring.platform.test;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

/**
 * Exercise ping test
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class PingClient1 {

	/**
	 * 
	 * @throws Exception
	 */
	public void runClient() throws Exception {
		BackEndFacade bef = new BackEndFacade();
		boolean flag = bef.isPingTest(false);
		System.out.println("isPingTest(false) returns:" + flag);
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		PingClient1 pc1 = new PingClient1();
		pc1.runClient();

		System.out.println("end");
	}
}
