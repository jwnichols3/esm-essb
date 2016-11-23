package com.bgi.esm.portlets.testing.eeb;

import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.bgi.esm.portlets.testing.EEBTestcase;

import tools.testing.eeb.ovmessage.OvMessage;
import tools.testing.servicecenter.ticket.generated.Action;
import tools.testing.servicecenter.ticket.generated.ProblemSummary;
import tools.testing.servicecenter.ticket.generated.Recordset;

public class TestEndToEnd extends EEBTestcase {

	private static final Logger LOG = Logger.getLogger ( TestEndToEnd.class );
	
	public TestEndToEnd(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();

		allowTestMessagesToPassThroughDataMap();
		openThrottleWide();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMessageThroughput() throws Exception {
		OvMessage message = sendRandomMessage();
		
		UUID messageId = message.getMessageUUID();
		
		assertNotNull(messageId);
		
		assertMessageNotSuppressed(messageId);
		assertMessagePassedThroughDataMap(messageId);
		assertMessageNotThrottled(messageId);
		assertMessageDispatched(messageId);
		
		assertServiceCenterTicketCreated(messageId);
	}
	
	public void testStormThroughput() throws Exception {
		final int NUMBER_OF_MESSAGES = 500;
		
		OvMessage[] messages = sendRandomMessages(NUMBER_OF_MESSAGES);
		
		assertEquals(messages.length, NUMBER_OF_MESSAGES);
		
		Thread.sleep(12000);
		
		for (int i = 0; i < messages.length; ++i) {
			UUID messageId = messages[i].getMessageUUID();
			LOG.debug("Ticket " + (i + 1) + " (" + messageId + ") is being checked.");
			assertServiceCenterTicketCreated(messageId);
			LOG.debug("Ticket " + (i + 1) + " (" + messageId + ") checks out okay.");
			messages[i] = null;
			Thread.sleep(5000);
		}
	}
	
	private String toString(Recordset recordset) {
        StringWriter textSink = new StringWriter();

        try {
            final String CONTEXT_PATH = "tools.testing.servicecenter.ticket.generated";

            JAXBContext jc = JAXBContext.newInstance(CONTEXT_PATH);
            Marshaller m = jc.createMarshaller();
            m.setProperty("jaxb.formatted.output", true);

            m.marshal(recordset, textSink);
        }
        catch (JAXBException e) {
            LOG.error("Could not marshal Recordset.");
        }

        return textSink.toString();
	}
	
	public void testTicketUpdate() throws Exception {
		OvMessage message = sendRandomMessage();
		
		UUID messageId = message.getMessageUUID();
		
		Thread.sleep(12000);
		
		Recordset recordset = getServiceCenterRecordset(messageId);
		assertNotNull(recordset);
		
		ProblemSummary probSummary = recordset.getProbsummary();
		assertNotNull(probSummary);
		
		LOG.debug(toString(recordset));
		
		message.setNumberOfDuplicates(1);
		message.setData("text", "Updated text.");
		
		sendMessage(message);
		
		Thread.sleep(30000);
		
		String ticketNumber = probSummary.getNumber();
		
		recordset = getServiceCenterRecordset(ticketNumber);
		probSummary = recordset.getProbsummary();

		Action updateAction  = probSummary.getUpdateAction();
		
		assertNotNull(updateAction);
		
		List updateEntryList = updateAction.getEntryList();
		
		assertNotNull(updateEntryList);
		assertTrue(updateEntryList.size() >= 2);
		
		String entry = (String) updateEntryList.get(1);
		LOG.debug("Entry is: " + entry);
		
		final Pattern p = Pattern.compile("Ticket updated via HelpME Ticket #" + 
				ticketNumber + 
				" - \\d+ duplicate events detected\\) by ESM Monitoring.");
		
		Matcher m = p.matcher(entry);
		
		assertTrue(m.matches());
		
		LOG.debug(toString(recordset));
	}
}
