package com.bgi.esm.portlets.testing.suppression;

import tools.testing.eeb.ovmessage.OvMessage;

import com.bgi.esm.portlets.testing.EEBTestcase;

public class TestNoSuppressionRules extends EEBTestcase {

	public TestNoSuppressionRules(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMessageNotSuppressed() throws Exception {
		OvMessage message = sendRandomMessage();
		
		assertFalse(messageWasSuppressed(message.getMessageUUID()));
	}
}
