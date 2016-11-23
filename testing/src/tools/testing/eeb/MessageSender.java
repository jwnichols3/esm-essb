package tools.testing.eeb;

import javax.jms.JMSException;

import tools.testing.eeb.jms.Topic;
import tools.testing.eeb.ovmessage.OvMessage;
import tools.testing.eeb.ovmessage.OvMessageBuilder;
import tools.testing.eeb.ovmessage.OvMessageBuilderFactory;

public class MessageSender {

    private static final OvMessageBuilder DEFAULT_BUILDER = OvMessageBuilderFactory.newInstance().newOvMessageBuilder();

    /*
    public static UUID[] sendRandomMessages(String group, int quantity, Topic topic, OvMessageBuilder builder) throws JMSException {
    	UUID[] messageIds = new UUID[quantity];
    	
        for (int i = 0; i < quantity; ++i) {
            OvMessage message = builder.newOvMessage(group);
            topic.write(message.toString());
            messageIds[i] = message.getMessageUUID();
        }
        
        return messageIds;
    }
*/
    public static OvMessage[] sendRandomMessages(String group, int quantity, Topic topic, OvMessageBuilder builder) throws JMSException {
    	OvMessage[] messages = new OvMessage[quantity];
    	
    	for (int i = 0; i < quantity; ++i) {
    		messages[i] = builder.newOvMessage(group);
    		sendMessage(messages[i], topic);
    	}
    	
    	return messages;
    }
    
    public static OvMessage[] sendRandomMessages(String group, int quantity, Topic topic) throws JMSException {
        return sendRandomMessages(group, quantity, topic, DEFAULT_BUILDER);
    }
    
    public static OvMessage sendRandomMessage(String group, Topic topic, OvMessageBuilder builder) throws JMSException {
    	OvMessage[] messages = sendRandomMessages(group, 1, topic, builder);
    	return messages != null ? messages[0] : null;
    }
    
    public static OvMessage sendRandomMessage(String group, Topic topic) throws JMSException {
    	return sendRandomMessage(group, topic, DEFAULT_BUILDER);
    }
    
    public static void sendMessage(OvMessage message, Topic topic) throws JMSException {
    	topic.write(message.toString());
    }
}
