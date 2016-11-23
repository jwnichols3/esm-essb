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
public class Suppression4 {

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
        System.out.println ( "Timezone: " + bef.selectEebProperty( "server.time_zone" ).getPropertyValue() );

        List suppressionRules = bef.getAllActiveSuppressionRules();
        for ( int counter = 0; counter < suppressionRules.size(); counter++)
        {
            System.out.print ( "Rule #" );
            System.out.print ( counter );
            System.out.print ( ": " );
            System.out.print ( suppressionRules.get(counter).toString() );
            System.out.print ( "\n" );
        }
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("begin");

		Suppression4 s1 = new Suppression4();
		s1.runClient();

		System.out.println("end");
	}
}
