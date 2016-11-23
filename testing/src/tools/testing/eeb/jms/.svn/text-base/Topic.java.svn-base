package tools.testing.eeb.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Topic {

    private TopicConnection connection;
    private TopicPublisher publisher;
    private TopicSession session;
    
    public Topic (String topicName) throws NamingException, JMSException {
        Context context = new InitialContext();
        javax.jms.Topic topic = (javax.jms.Topic) context.lookup(topicName);
        TopicConnectionFactory factory = 
        	(TopicConnectionFactory) context.lookup("weblogic.jms.ConnectionFactory");

        connection = factory.createTopicConnection();
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        publisher = session.createPublisher(topic);
    }

    public void write(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        publisher.publish(textMessage);
    }
    
    public void close() throws JMSException {
    	connection.close();
    }
}
