package com.bgi.esm.portlets.testing.suppression;

import java.util.Calendar;

import tools.testing.eeb.ovmessage.OvMessage;

import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.portlets.testing.EEBTestcase;

public class TestTwoOverlappingSuppressionRules extends EEBTestcase {

	private SuppressionRule rule1;
	private SuppressionRule rule2;
	
	public TestTwoOverlappingSuppressionRules(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		rule1 = makeSuppressionRule();

		rule2 = makeSuppressionRule();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMessageWithOneApplicableRule() throws Exception {
		rule1.getStartTime().add(Calendar.MONTH, -3);
		rule1.getEndTime().add(Calendar.DAY_OF_MONTH, -7);
		
		rule2.getStartTime().add(Calendar.MONTH, -2);
		rule2.getEndTime().add(Calendar.MONTH, 2);
		
		addOrUpdateSuppressionRule(rule1);
		addOrUpdateSuppressionRule(rule2);
		
		assertEquals(countSuppressionRules(), 2);
		
		OvMessage message = sendRandomMessage();

		assertTrue(messageWasSuppressed(message.getMessageUUID()));
	}
	
	public void testMessageWithTwoApplicableRules() throws Exception {
		rule1.getStartTime().add(Calendar.MONTH, -3);
		rule1.getEndTime().add(Calendar.DAY_OF_MONTH, 7);
		rule2.getStartTime().add(Calendar.MONTH, -2);
		rule2.getEndTime().add(Calendar.MONTH, 2);
		
		addOrUpdateSuppressionRule(rule1);
		addOrUpdateSuppressionRule(rule2);
		
		assertTrue(countSuppressionRules() == 2);
		
		OvMessage message = sendRandomMessage();

		assertTrue(messageWasSuppressed(message.getMessageUUID()));
	}
}
