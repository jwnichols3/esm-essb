package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;

/**
 * Exercise throttle RMI features.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class Throttle2 {

	/**
	 * 
	 * @throws Exception
	 */
	public void runClient() throws Exception {
		ThrottleRule rule1 = new ThrottleRule();
		rule1.setGroup("group");
		rule1.setStormLevel(666);
		rule1.setDuration(123L);
		rule1.setThreshold(100);
		rule1.setAction(ThrottleAction.PASS_THRU);

		BackEndFacade bef = new BackEndFacade();

		ThrottleRule rule2 = bef.addOrUpdateThrottleRule(rule1);
		System.out.println("insert returns:" + rule2);

		ThrottleRule rule3 = bef.selectThrottleRule(rule2.getRuleKey());
		System.out.println("select returns:" + rule3);

		List list = bef.getAllThrottleRules();
		System.out.println("list size:" + list.size());

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			ThrottleRule rule = (ThrottleRule) iterator.next();
			System.out.println(rule);
		}

		// boolean flag = bef.deleteThrottleRule(rule2);
		// System.out.println("delete returns:" + flag);
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		Throttle2 t1 = new Throttle2();
		t1.runClient();

		System.out.println("end");
	}
}
