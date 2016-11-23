package com.bgi.esm.portlets.testing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractTopic;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;

import junit.framework.TestCase;

public class SuppressionTestcase extends TestCase {

	protected static final String NOTIFIER_TOPIC_NAME = "replatform.topic.notify";
	protected static final String NOTIFIER_INPUT_FILENAME = "C:\\dump3.raw";
	protected static final String MESSAGE_ID = "4ba06760-7a59-71db-1598-4534661e0000";
	private static final String MATCH_ANYTHING = "-";

	private TopicConnection connection;
	private TopicPublisher publisher;
	private TopicSession session;
	
	private BackEndFacade backEndFacade = new BackEndFacade();

	public SuppressionTestcase(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		destroyAllSuppressionRules();
		destroyAllDataMapRules();
		destroyAllAuditData();
		
		Context context = new InitialContext();
		
		Topic topic = (Topic) context.lookup(NOTIFIER_TOPIC_NAME);
		TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup(AbstractTopic.TOPIC_FACTORY);
		
		connection = factory.createTopicConnection();
		
		session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		publisher = session.createPublisher(topic);

	}

	protected SuppressionRule makeSuppressionRule(Calendar startTime, Calendar endTime) {
     	return makeSuppressionRule(startTime, endTime, MATCH_ANYTHING, MATCH_ANYTHING, MATCH_ANYTHING, MATCH_ANYTHING);
	}
	
	protected SuppressionRule makeSuppressionRule(Calendar startTime, Calendar endTime, String applicationName, 
			String nodeName, String databaseServerName, String message) {
		
    	SuppressionRule rule = new SuppressionRule();
    	
    	rule.setStartTime( startTime );
    	rule.setEndTime( endTime );
    	rule.setApplicationName(applicationName);
    	rule.setNodeName(nodeName);
    	rule.setDatabaseServerName(databaseServerName);
    	rule.setMessage(message);
    	
    	return rule;
	}
	
	protected void writeToTopic(String messageText) throws JMSException {
		TextMessage message = session.createTextMessage();
		
		message.setText(messageText);
		
		publisher.publish(message);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
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
	
	protected void destroyAllAuditData() throws BackEndFailure {
		List auditData = backEndFacade.getAllAudit();
		for (Iterator i = auditData.iterator(); i.hasNext(); ) {
			Audit auditDatum = (Audit) i.next();
			backEndFacade.deleteAudit(auditDatum);
		}
	}
	
	protected List<Audit> selectAuditDataByMessageId(String messageId) throws BackEndFailure {
		List allAuditData = backEndFacade.getAllAudit();
		
		List<Audit> selectedAuditData = new ArrayList<Audit>();
		
		for (Iterator i = allAuditData.iterator(); i.hasNext(); ) {
			Audit auditDatum = (Audit) i.next();
			if (auditDatum.getMessageId().equals(messageId)) {
				selectedAuditData.add(auditDatum);
			}
		}
		
		return selectedAuditData;
	}
	
	protected boolean messageWasSuppressed(String messageId) throws BackEndFailure {
		boolean result = false;
		
		List allAuditData = backEndFacade.getAllAudit();
		
		for (Iterator i = allAuditData.iterator(); !result && i.hasNext(); ) {
			Audit auditDatum = (Audit) i.next();
			result = auditDatum.getMessageId().equals(messageId) && 
				auditDatum.getAction().equals("suppressed");
		}
		
		return result;
	}
}
