package com.bgi.esm.portlets.testing.suppression;

import java.util.Calendar;

import tools.testing.eeb.ovmessage.OvMessage;

import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.portlets.testing.EEBTestcase;

public class TestOneSuppressionRule extends EEBTestcase {

	public TestOneSuppressionRule(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMessageMeetsRuleCriteria() throws Exception {
		SuppressionRule suppressionRule = makeSuppressionRule( makeCalendar(1971, Calendar.APRIL, 28),
				makeCalendar(2010, Calendar.JANUARY, 1), "desc" );

		addOrUpdateSuppressionRule(suppressionRule);
		
		OvMessage message = sendRandomMessage();
		
		assertTrue(messageWasSuppressed(message.getMessageUUID()));
	}
	
	public void testInvalidSuppressionRule() throws Exception {
		SuppressionRule suppressionRule = makeSuppressionRule( makeCalendar(2010, Calendar.JANUARY, 1), 
				makeCalendar(1971, Calendar.APRIL, 28), "desc" ); // start date follows end date
		
		addOrUpdateSuppressionRule(suppressionRule);
		
		OvMessage message = sendRandomMessage();

		assertFalse(messageWasSuppressed(message.getMessageUUID()));
	}
	
	public void testMessageDoesNotMeetRuleCriteria() throws Exception {
		SuppressionRule suppressionRule = makeSuppressionRule( makeCalendar(1971, Calendar.JANUARY, 1), 
				makeCalendar(1971, Calendar.APRIL, 28), "desc" ); // rule starts and ends before message under test
		
		addOrUpdateSuppressionRule(suppressionRule);
		
		OvMessage message = sendRandomMessage();		
		
		assertFalse(messageWasSuppressed(message.getMessageUUID()));
	}
}
