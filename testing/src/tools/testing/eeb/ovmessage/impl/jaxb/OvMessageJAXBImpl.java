package tools.testing.eeb.ovmessage.impl.jaxb;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import tools.testing.eeb.ovmessage.impl.jaxb.generated.Data;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.MessageData;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.MessageEnvelope;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.MessageBody;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.ObjectFactory;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.OvMessage;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.TimeStamp;
import tools.testing.eeb.ovmessage.impl.jaxb.generated.Value;

public class OvMessageJAXBImpl implements tools.testing.eeb.ovmessage.OvMessage {
    private static final String VERSION = "1.0";
    private static final String MESSAGE_DATA_APPLICATION = "OVO";

    private static final OvMessageNamespacePrefixMapper NAMESPACE_PREFIX_MAPPER = 
        new OvMessageNamespacePrefixMapper();

    private ObjectFactory   factory;
    private OvMessage       message;
    private MessageEnvelope envelope;
    private MessageBody     body;
    private List<Data>      dataList;

    public OvMessageJAXBImpl() {
        factory = new ObjectFactory();

        message = factory.createOvMessage();
        message.setVersion(VERSION);

        envelope = factory.createMessageEnvelope();
        message.setMessageEnvelope(envelope);

        body = factory.createMessageBody();
        message.setMessageBody(body);
    }

    public OvMessageJAXBImpl(OvMessage message) {
        this.message = message;
        envelope = message.getMessageEnvelope();
        body = message.getMessageBody();
    }

    public UUID getMessageUUID() {
        return envelope.getMessageUUID();
    }

    public void setMessageUUID(UUID messageUUID) {
        envelope.setMessageUUID(messageUUID);
    }

    public Calendar getTimeStamp() {
        return envelope.getTimeStamp().getDateTime();
    }

    public void setTimeStamp(Calendar inCal) {
        Calendar cal = (Calendar) inCal.clone();
        TimeStamp timeStamp = factory.createTimeStamp();
        timeStamp.setDateTime(cal);
        timeStamp.setSeconds(cal.getTimeInMillis() / 1000);
        envelope.setTimeStamp(timeStamp);
    }

    public tools.testing.eeb.ovmessage.OvMessage.Severity getSeverity() {
        return tools.testing.eeb.ovmessage.OvMessage.Severity.fromValue(envelope.getSeverity().value());
    }

    public void setSeverity(tools.testing.eeb.ovmessage.OvMessage.Severity severity) {
        envelope.setSeverity(tools.testing.eeb.ovmessage.impl.jaxb.generated.Severity.fromValue(severity.value()));
    }

    public String getMessageSource() {
        return envelope.getMessageSource();
    }

    public void setMessageSource(String messageSource) {
        envelope.setMessageSource(messageSource);
    }

    public String getEventSource() {
        return envelope.getEventSource();
    }

    public void setEventSource(String eventSource) {
        envelope.setEventSource(eventSource);
    }

    public String getPrimaryMessageRepository() {
        return envelope.getPrimaryMessageRepository();
    }

    public void setPrimaryMessageRepository(String primaryMessageRepository) {
        envelope.setPrimaryMessageRepository(primaryMessageRepository);
    }

    public int getNumberOfDuplicates() {
        return body.getNumberOfDuplicates();
    }

    public void setNumberOfDuplicates(int numberOfDuplicates) {
        body.setNumberOfDuplicates(numberOfDuplicates);
    }

    public tools.testing.eeb.ovmessage.OvMessage.State getState() {
        return tools.testing.eeb.ovmessage.OvMessage.State.fromValue(body.getState().value());
    }

    public void setState(tools.testing.eeb.ovmessage.OvMessage.State state) {
        body.setState(tools.testing.eeb.ovmessage.impl.jaxb.generated.State.fromValue(state.value()));
    }

    private void makeMessageDataContainer() {
        MessageData messageData = factory.createMessageData();
        messageData.setApplication(MESSAGE_DATA_APPLICATION);
        body.setMessageData(messageData);
        dataList = messageData.getDataList(); // This call causes the list to be created.
    }

    private void destroyMessageDataContainer() {
        dataList = null;
        body.setMessageData(null);
    }

    private Value makeValue(String type, String content) {
        Value value = factory.createValue();
        value.setType(type);
        value.setContent(content);
        return value;
    }


    private Data findDataItem(String name) {
        if (dataList == null) {
            return null;
        }

        for (Data dataItem : dataList) {
            if (dataItem.getName().equals(name)) {
                return dataItem;
            }
        }

        return null;
    }

    private Value findValue(String name) {
        if (dataList == null) {
            return null;
        }

        for (Data dataItem : dataList) {
            if (dataItem.getName().equals(name)) {
                return dataItem.getValue();
            }
        }

        return null;
    }

	private boolean hasDataItem(String name) {
        return findDataItem(name) != null;
    }

	private Data makeDataItem(String name, Value value) {
        Data dataItem = factory.createData();
        dataItem.setName(name);
        dataItem.setValue(value);
        return dataItem;
    }

    private void setData(String name, String contentType, String content) {
        assert name != null && contentType != null && content != null;

        Value value = makeValue(contentType, content);

        Data dataItem = null;

        if (dataList == null) {
            makeMessageDataContainer();
        }
        else {
            dataItem = findDataItem(name);
        }

        if (dataItem == null) {
            dataItem = factory.createData();
            dataItem.setName(name);
            dataList.add(dataItem);
        }

        dataItem.setValue(value);
    }

    public void setData(String name, String content) {
        final String CONTENT_TYPE = "string";
        setData(name, CONTENT_TYPE, content);
    }

    public void setData(String name, Long content) {
        final String CONTENT_TYPE = "long";
        setData(name, CONTENT_TYPE, content.toString());
    }

    private Object convertValueContentToJavaObject(Value value) {
        assert value != null;

        if ( value.getType().equals("long") ) {
            return Long.valueOf(value.getContent());
        }

        return value.getContent();
    }

    public Object getValue(String name) {
        Value value = findValue(name);
        return value == null ? null : convertValueContentToJavaObject(value);
    }

    
    public Object removeData(String name) {
        Value value = null;
        Iterator<Data> i = dataList.iterator();

        while (value == null && i.hasNext() ) {
            Data dataItem = i.next();

            if ( dataItem.getName().equals(name) ) {

                value = dataItem.getValue();
                i.remove();

                if (dataList.isEmpty()) {
                    destroyMessageDataContainer();
                }
            }
        }

        return value == null ? null : convertValueContentToJavaObject(value);
    }

    public String toString() {
        StringWriter textSink = new StringWriter();

        try {
            final String CONTEXT_PATH = "tools.testing.eeb.ovmessage.impl.jaxb.generated";
            final String NAMESPACE_PREFIX_MAPPER_KEY = "com.sun.xml.bind.namespacePrefixMapper";

            JAXBContext jc = JAXBContext.newInstance(CONTEXT_PATH);
            Marshaller m = jc.createMarshaller();

            m.setProperty(NAMESPACE_PREFIX_MAPPER_KEY, NAMESPACE_PREFIX_MAPPER);

            m.marshal(message, textSink);
        }
        catch (JAXBException e) {
            // log exception
        }

        return textSink.toString();
    }

/*
    public void setText(String string) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("tools.testing.ovmessage");
        Unmarshaller u = jc.createUnmarshaller();
        OvMessage newMesage = (OvMessage) u.unmarshal( new StreamSource( new StringReader( string ) ) );

        envelope = newMessage.getMessageEnvelope();
        body = newMessage.getMessageBody();
        message = newMessage;
    }
*/
}
