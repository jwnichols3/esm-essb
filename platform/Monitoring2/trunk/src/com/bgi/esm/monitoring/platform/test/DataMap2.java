package com.bgi.esm.monitoring.platform.test;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;

/**
 * Exercise data map rule
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMap2 {

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception {
	DataMapRule rule1 = new DataMapRule("group");
	rule1.setMethod("method");
	rule1.setAlarmpointGroup("ap group");
	rule1.setAlarmpointScript("ap script");
	rule1.setPeregrineCategory("peregrin cat");
	rule1.setPeregrineSubCategory("peregrin subcat");
	rule1.setPeregrineProduct("peregrin product");
	rule1.setPeregrineProblem("peregrin problem");
	rule1.setPeregrineAssignment("peregrin assignment");
	rule1.setPeregrineLocation("peregrin location");

	BackEndFacade bef = new BackEndFacade();

	DataMapRule rule2 = bef.addOrUpdateDataMapRule(rule1);
	System.out.println("insert returns:" + rule2);

	DataMapRule rule3 = bef.selectDataMapRule(rule2.getGroup());
	System.out.println("select returns:" + rule3);

	List list = bef.getAllDataMapRules();
	System.out.println("list size:" + list.size());

	Iterator iterator = list.iterator();
	while (iterator.hasNext()) {
	    DataMapRule rule = (DataMapRule) iterator.next();
	    System.out.println(rule);
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
	
	DataMap2 dm1 = new DataMap2();
	dm1.runClient();
	
	System.out.println("end");
    }
}
