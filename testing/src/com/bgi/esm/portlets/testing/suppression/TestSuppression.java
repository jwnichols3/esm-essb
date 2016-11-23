/**
 * 
 */
package com.bgi.esm.portlets.testing.suppression;

import java.util.Calendar;

import junit.framework.TestCase;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.utility.SuppressionQueue;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

/**
 * @author hannrus
 *
 */
public class TestSuppression extends TestCase {

	public void setUp() throws Exception {
	}
	
	public void tearDown() throws Exception {
	}
	
    public void testWriteSuppressionMessageToQueue() throws Exception {
    	SuppressionMessage sm = new SuppressionMessage();

    	SuppressionQueue sq = new SuppressionQueue("replatform.queue.suppression");
    	boolean flag = sq.queueWriter(sm);
    	assertTrue(flag);
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testAddSuppressionRule() throws Exception {
    	Calendar startTime = Calendar.getInstance();
    	Calendar endTime = (Calendar) startTime.clone();
    	endTime.add(Calendar.DAY_OF_YEAR, 1);
    	
    	SuppressionRule rule1 = new SuppressionRule();
    	rule1.setStartTime(startTime);
    	rule1.setEndTime(endTime);
    	rule1.setApplicationName("some application name");
    	rule1.setNodeName("node name");
    	rule1.setDatabaseServerName("db name");
    	rule1.setMessage("message");
    	rule1.setDescription("description");

    	BackEndFacade bef = new BackEndFacade();

    	SuppressionRule rule2 = bef.addOrUpdateSuppressionRule(rule1);
    	
    	assertNotNull(rule2);
    }
}
