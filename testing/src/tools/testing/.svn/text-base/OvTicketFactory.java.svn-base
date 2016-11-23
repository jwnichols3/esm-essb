package tools.testing;

import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;

public class OvTicketFactory {

	public static String makeOvTicket(TicketMessage ticketMessage) {
		final String XML_DECLARATION = "<?xml version='1.0' encoding='UTF-8' ?>\n";
		StringBuilder sb = new StringBuilder(XML_DECLARATION);
		sb.append("<ovMessage xmlns='http://openview.hp.com/xmlns/ico/message' version='1.0'>\n");
		sb.append("<messageEnvelope>\n");
		sb.append("<messageUUID>").append(ticketMessage.getMessageId()).append("</messageUUID>\n");
		sb.append("<timeStamp>\n");
		sb.append("<seconds>").append(ticketMessage.getTimeStamp().getTimeInMillis() / 1000).append("</seconds>\n");
		sb.append("<dateTime>").append(ticketMessage.getTimeStamp().toString()).append("</dateTime>\n");
		sb.append("</timeStamp>\n");
		sb.append("<severity>").append(ticketMessage.getSeverity()).append("</severity>\n");
		sb.append("<messageSource>").append(ticketMessage.getSourceNode()).append("</messageSource>\n");
		sb.append("<eventSource>").append(ticketMessage.getSourceNode()).append("</eventSource>\n");
		sb.append("<primaryMessageRepository>").append(ticketMessage.getSourceNode()).append("</primaryMessageRepository>\n");
		sb.append("<messageMetadataIdentifier />\n");
		sb.append("<managedEntityReference />\n");
		sb.append("</messageEnvelope>\n");
		sb.append("<messageBody>\n");
		sb.append("<owner />\n");
		sb.append("<numberOfDuplicates>0</numberOfDuplicates>\n");
		sb.append("<state>PENDING</state>\n");
		sb.append("<creatingCondition />\n");
		sb.append("<messageData application='OVO'>\n");
		sb.append("<data>\n");
		sb.append("<name>application</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getApplication()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>group</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getMessageGroup()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>object</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getObject()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>originalText</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getMessageText()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>receiveTimeSeconds</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getTimeStamp().getTimeInMillis() / 1000).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>serviceName</name>\n");
		sb.append("<value type='string' />\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>source</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getSourceNode()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>text</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getMessageText()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("<data>\n");
		sb.append("<name>type</name>\n");
		sb.append("<value type='string'>").append(ticketMessage.getSourceNodeType()).append("</value>\n");
		sb.append("</data>\n");
		sb.append("</messageData>\n");
		sb.append("<actionResult />\n");
		sb.append("<messageMetadataAnnotation />\n");
		sb.append("</messageBody>\n");
		sb.append("</ovMessage>\n");
		
		return sb.toString();
	}
}
