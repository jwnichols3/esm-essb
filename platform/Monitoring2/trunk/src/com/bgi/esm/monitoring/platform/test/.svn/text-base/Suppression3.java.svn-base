package com.bgi.esm.monitoring.platform.test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * Exercise suppression RMI features.
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Suppression3 {

	/**
	 * 
	 * @throws Exception
	 */
	public void runClient() throws Exception {
		SuppressionRule rule1 = new SuppressionRule();
		rule1.setStartTime(Calendar.getInstance());
			Thread.sleep(3000L);
		try {
		} catch(Exception exception) {
			//empty
		}
		rule1.setEndTime(Calendar.getInstance());
		rule1.setApplicationName       ( "application name" );
		rule1.setNodeName              ( "test_node"        );
		rule1.setDatabaseServerName    ( "db name"          );
		rule1.setMessage               ( "message"          );
        rule1.setGroupName             ( "group name"       );
        rule1.setDescription           ( "test description" );
        rule1.setNotifyMinutes         ( 239 );

        System.out.println ( "Rule key: " + rule1.getRuleKey() );

		BackEndFacade bef = new BackEndFacade();

		SuppressionRule rule2 = bef.addOrUpdateSuppressionRule(rule1);
		System.out.println ( "insert returns:" + rule2 );
        System.out.println ( "rule key: " + rule2.getRuleKey() );
        System.out.println ( "Suppression ID: " + rule2.getSuppressId() );

		SuppressionRule rule3 = bef.selectSuppressionRule(rule2.getRuleKey());
		System.out.println("select returns:" + rule3);
        System.out.println ( "Suppression ID: " + rule3.getSuppressId() );

		List list = bef.getAllSuppressionRules();
		System.out.println("list size:" + list.size());

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			SuppressionRule rule = (SuppressionRule) iterator.next();
			System.out.println(rule);
		}

        List notificationList = bef.getAllSuppressionNotifications();
        System.out.println ( "Notifications list size before wait: " + notificationList.size() );
        iterator = notificationList.iterator();
		while (iterator.hasNext()) {
			SuppressionRule rule = (SuppressionRule) iterator.next();
			System.out.println(rule);
		}

        try
        {
            Thread.sleep ( 61000 );
        }
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();
        }

        notificationList = bef.getAllSuppressionNotifications();
        System.out.println ( "Notifications list size after wait: " + notificationList.size() );
        iterator = notificationList.iterator();
		while (iterator.hasNext()) {
			SuppressionRule rule = (SuppressionRule) iterator.next();
			System.out.println(rule);
		}

		boolean flag = bef.deleteSuppressionRule(rule2);
		System.out.println("delete returns:" + flag);

        
        notificationList = bef.getAllSuppressionNotifications();
        System.out.println ( "Notifications list size after delete: " + notificationList.size() );
        iterator = notificationList.iterator();
		while (iterator.hasNext()) {
			SuppressionRule rule = (SuppressionRule) iterator.next();
			System.out.println(rule);
		}

	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		Suppression3 s1 = new Suppression3();
		s1.runClient();

		System.out.println("end");
	}
}
