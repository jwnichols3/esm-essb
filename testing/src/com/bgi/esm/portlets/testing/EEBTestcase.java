package com.bgi.esm.portlets.testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import tools.testing.eeb.MessageSender;
import tools.testing.eeb.jms.Topic;
import tools.testing.eeb.ovmessage.OvMessage;
import tools.testing.servicecenter.ServiceCenter;
import tools.testing.servicecenter.ticket.generated.Recordset;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;

public class EEBTestcase extends TestCase {

	protected static final String NOTIFIER_TOPIC_NAME = "replatform.topic.notify";

	private static final String MATCH_ANYTHING = "-";

	private static final long POLLING_INTERVAL = 10000L;
	private static final long POLLING_LENGTH = 30000;
	
	private static final String TEST_MESSAGE_GROUP = "test-eeb";
	
	private BackEndFacade backEndFacade = new BackEndFacade();

	private ServiceCenter serviceCenter = new ServiceCenter();
	
	private Topic notifierTopic = null;
	
	private static final Logger LOG = Logger.getLogger ( EEBTestcase.class );
	
	public EEBTestcase(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		destroyAllSuppressionRules();
		destroyAllDataMapRules();
		destroyAllThrottleRules();
		destroyAllAuditData();
		destroyAllResponderData();
		
		notifierTopic = new Topic(NOTIFIER_TOPIC_NAME);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		notifierTopic.close();
		notifierTopic = null;
	}
	
	protected SuppressionRule makeSuppressionRule() {
		Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		endTime.add(Calendar.HOUR, 1);
		
		return makeSuppressionRule(startTime, endTime, "some description");
	}
	
	/**
	 * Make a suppression rule that matches anything within the specified time period.
	 * NOTE: SuppressionConsumerBean checks only application name, node name, database server name, and message.
	 * 
	 * @param startTime start time for suppression rule
	 * @param endTime end time for suppression rule
	 * @return
	 */
	protected SuppressionRule makeSuppressionRule(Calendar startTime, Calendar endTime, String description) {
		SuppressionRule rule = new SuppressionRule();
		
		rule.setStartTime(startTime);
		rule.setEndTime(endTime);
		
		rule.setApplicationName(MATCH_ANYTHING);
		
		rule.setNodeName(MATCH_ANYTHING);
		
		rule.setDatabaseServerName(MATCH_ANYTHING);
		
		rule.setMessage(MATCH_ANYTHING);
		
		rule.setDescription(description);
		
		return rule;
	}
	
	protected SuppressionRule makeSuppressionRule(
			Calendar startTime, 
			Calendar endTime, 
			String applicationName, 
			String nodeName, 
			String databaseServerName, 
			String message, 
			String description, 
			boolean removeOnReboot, 
			boolean deletedFlag, 
			boolean notificationFlag, 
			long notifyMinutes, 
			long suppressId, 
			boolean isNotified) {
		
    	SuppressionRule rule = new SuppressionRule();
    	
    	rule.setStartTime( startTime );
    	rule.setEndTime( endTime );
    	rule.setApplicationName(applicationName);
    	rule.setNodeName(nodeName);
    	rule.setGroupName(TEST_MESSAGE_GROUP);
    	rule.setDatabaseServerName(databaseServerName);
    	rule.setMessage(message);
    	rule.setDescription(description);
    	rule.setRemoveOnReboot(removeOnReboot);
    	rule.setDeletedFlag(deletedFlag);
    	rule.setNotificationFlag(notificationFlag);
    	rule.setNotifyMinutes(notifyMinutes);
    	rule.setSuppressId(suppressId);
    	rule.setIsNotified(isNotified);
    	
    	
    	return rule;
	}
	
	protected ThrottleRule makeThrottleRule(
			int stormLevel, 
			int duration, 
			int threshold, 
			ThrottleAction action, 
			boolean enableStormMessages) {
		
		ThrottleRule throttleRule = new ThrottleRule();
		
		throttleRule.setGroup(TEST_MESSAGE_GROUP);
		throttleRule.setStormLevel(stormLevel);
		throttleRule.setDuration(duration);
		throttleRule.setThreshold(threshold);
		throttleRule.setAction(action);
		throttleRule.setEnableStormMessages(enableStormMessages);
		
		return throttleRule;
	}

	protected void openThrottleWide() throws BackEndFailure {
		destroyAllThrottleRules();
		ThrottleRule tr = makeThrottleRule(0, 0, 0, ThrottleAction.PASS_THRU, false);
		addOrUpdateThrottleRule(tr);
	}
	
	protected void allowTestMessagesToPassThroughDataMap() throws BackEndFailure {
		DataMapRule dmr = makeDataMapRule();
		addOrUpdateDataMapRule(dmr);
	}
	
	protected DataMapRule makeDataMapRule() {
		DataMapRule dataMapRule = new DataMapRule(TEST_MESSAGE_GROUP);
		
		dataMapRule.setAlarmpointGroup("AlarmpointGroup");
		dataMapRule.setAlarmpointScript("AlarmpointScript");
		dataMapRule.setMethod("ticket");
		dataMapRule.setPeregrineAssignment("PeregrineAssignment");
		dataMapRule.setPeregrineCategory("PeregrineCategory");
		dataMapRule.setPeregrineLocation("PeregrineLocation");
		dataMapRule.setPeregrineProblem("PeregrineProblem");
		dataMapRule.setPeregrineProduct("PeregrineProduct");
		dataMapRule.setPeregrineSubCategory("PeregrineSubCategory");
		
		return dataMapRule;
	}

	protected Calendar makeCalendar(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date);
		return cal;
	}

	protected void addOrUpdateDataMapRule(DataMapRule dataMapRule) throws BackEndFailure {
		backEndFacade.addOrUpdateDataMapRule(dataMapRule);
	}
	
	protected void addOrUpdateSuppressionRule(SuppressionRule suppressionRule) throws BackEndFailure {
		backEndFacade.addOrUpdateSuppressionRule(suppressionRule);
	}
	
	protected void addOrUpdateThrottleRule(ThrottleRule throttleRule) throws BackEndFailure {
		backEndFacade.addOrUpdateThrottleRule(throttleRule);
	}
	
	protected void destroyAllSuppressionRules() throws BackEndFailure {
		List suppressionRules = backEndFacade.getAllSuppressionRules();
		for (Iterator i = suppressionRules.iterator(); i.hasNext(); ) {
			SuppressionRule sr = (SuppressionRule) i.next();
			backEndFacade.deleteSuppressionRule(sr);
		}
	}
	
	protected void destroyAllDataMapRules() throws BackEndFailure {
		List dataMapRules = backEndFacade.getAllDataMapRules();
		for (Iterator i = dataMapRules.iterator(); i.hasNext(); ) {
			DataMapRule dmr = (DataMapRule) i.next();
			backEndFacade.deleteDataMapRule(dmr);
		}
	}
	
	protected void destroyAllThrottleRules() throws BackEndFailure {
		List throttleRules = backEndFacade.getAllThrottleRules();
		for (Iterator i = throttleRules.iterator(); i.hasNext(); ) {
			ThrottleRule tr = (ThrottleRule) i.next();
			backEndFacade.deleteThrottleRule(tr);
		}
	}
	
	protected void destroyAllAuditData() throws BackEndFailure {
		List auditData = backEndFacade.getAllAudit();
		for (Iterator i = auditData.iterator(); i.hasNext(); ) {
			Audit auditDatum = (Audit) i.next();
			backEndFacade.deleteAudit(auditDatum);
		}
	}
	
	protected void destroyAllResponderData() throws BackEndFailure {
		List responderData = backEndFacade.getAllResponderIncidents();
		for (Iterator i = responderData.iterator(); i.hasNext(); ) {
			Responder responder = (Responder) i.next();
			backEndFacade.deleteResponder(responder);
		}
	}
	
	protected List<Audit> selectAuditDataByMessageId(UUID messageId) throws BackEndFailure {
		String messageIdString = messageId.toString();
		
		List allAuditData = backEndFacade.getAllAudit();
		
		List<Audit> selectedAuditData = new ArrayList<Audit>();
		
		for (Iterator i = allAuditData.iterator(); i.hasNext(); ) {
			Audit auditDatum = (Audit) i.next();
			if (auditDatum.getMessageId().equals(messageIdString)) {
				selectedAuditData.add(auditDatum);
			}
		}
		
		return selectedAuditData;
	}
	
	protected boolean messagePassedThrough(UUID messageId, BussModule module, String passThroughAction, String stopAction) throws BackEndFailure {
		
		String messageIdString = messageId.toString();
		
		boolean first = true;
		
		long startTime = System.currentTimeMillis();
		long endTime = 0L;
		
		try {
			do {
				if (first) {
					first = false;
				}
				else {
					Thread.sleep(POLLING_INTERVAL);
				}
				
				List allAuditData = backEndFacade.getAllAudit();
				endTime = System.currentTimeMillis();
				
				for (Iterator i = allAuditData.iterator(); i.hasNext(); ) {
					Audit auditDatum = (Audit) i.next();
					if (auditDatum.getMessageId().equals(messageIdString) && auditDatum.getModule().equals(module)) {
						if (auditDatum.getAction().equals(passThroughAction)) {
							return true;
						}
						
						if (auditDatum.getAction().equals(stopAction)) {
							return false;
						}
					}
				}
			} while (endTime - startTime < POLLING_LENGTH);
		} catch (InterruptedException e) {
			LOG.warn("Interrupted while checking if message " + messageIdString + 
					" passed through module " + module.toString() + '.', e);
		}
		
		return false;
	}
	
	protected boolean messageWasSuppressed(UUID messageId) throws BackEndFailure {
		return !messagePassedThrough(messageId, BussModule.SUPPRESSION, "downstream", "suppressed");
	}
	
	protected boolean messagePassedThroughDataMap(UUID messageId) throws BackEndFailure {
		return messagePassedThrough(messageId, BussModule.DATA_MAP, "downstream", "norule");
	}

	protected void assertMessageWasSuppressed(UUID messageId) throws BackEndFailure {
		assertTrue(messageWasSuppressed(messageId));
	}
	
	protected void assertMessageNotSuppressed(UUID messageId) throws BackEndFailure {
		assertFalse(messageWasSuppressed(messageId));
	}
	
	protected void assertMessagePassedThroughDataMap(UUID messageId) throws BackEndFailure {
		assertTrue(messagePassedThroughDataMap(messageId));
	}
	
	protected void assertMessageDidNotPassThroughDataMap(UUID messageId) throws BackEndFailure {
		assertFalse(messagePassedThroughDataMap(messageId));
	}
	
	protected void assertMessageNotThrottled(UUID messageId) throws BackEndFailure {
		assertTrue(messagePassedThrough(messageId, BussModule.THROTTLE, "PASS_THRU", "SPOOL"));
	}
	
	protected void assertMessageThrottled(UUID messageId) throws BackEndFailure {
		assertFalse(messagePassedThrough(messageId, BussModule.THROTTLE, "PASS_THRU", "SPOOL"));
	}
	
	protected void assertMessageDispatched(UUID messageId) throws BackEndFailure {
		assertTrue(messagePassedThrough(messageId, BussModule.DISPATCHER, "exit", ""));
	}
	
	protected Recordset getServiceCenterRecordset(UUID messageId) throws BackEndFailure {
		String messageIdString = messageId.toString();
		
		Responder responder = backEndFacade.selectResponderByMessageId(messageIdString);
		
		String serviceCenterTicketNumber = responder.getServiceCenterTicketNumber();
		
		LOG.debug("serviceCenterTicketNumber is " + serviceCenterTicketNumber);
		
		return getServiceCenterRecordset(serviceCenterTicketNumber);
	}
	
	protected Recordset getServiceCenterRecordset(String ticketNumber) throws BackEndFailure {
		Recordset recordset = null;
		
		try {
			recordset = serviceCenter.sendRequest(ticketNumber);
		} catch (IOException e) {
			LOG.error("IOException thrown by serviceCenter.sendRequest\n" + e.toString());
			throw new BackEndFailure(e.toString());
		} catch (JAXBException e) {
			LOG.error("JAXBException thrown by serviceCenter.sendRequest\n" + e.toString());
			throw new BackEndFailure(e.toString());
		}
		
		return recordset;
	}
	
	protected void assertServiceCenterTicketCreated(UUID messageId) throws BackEndFailure {
		Recordset recordset = getServiceCenterRecordset(messageId);
		assertNotNull(recordset);
		assertNotNull(recordset.getProbsummary());
	}
	
	protected int countSuppressionRules() throws BackEndFailure {
		return backEndFacade.getAllSuppressionRules().size();
	}
	
	protected OvMessage sendRandomMessage() throws JMSException {
		return MessageSender.sendRandomMessage(TEST_MESSAGE_GROUP, notifierTopic);
	}
	
	protected OvMessage[] sendRandomMessages(int numberOfMessages) throws JMSException {
		return MessageSender.sendRandomMessages(TEST_MESSAGE_GROUP, numberOfMessages, notifierTopic);
	}
	
	protected void sendMessage(OvMessage message) throws JMSException {
		MessageSender.sendMessage(message, notifierTopic);
	}
}
