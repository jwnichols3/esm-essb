package tools.testing.eeb.ovmessage.impl.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.util.Calendar;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import tools.testing.eeb.ovmessage.OvMessage;
import tools.testing.eeb.ovmessage.OvMessageBuilder;
import tools.testing.eeb.ovmessage.OvMessageParseException;

public class OvMessageBuilderJAXBImpl extends OvMessageBuilder {
    private static OvMessageBuilder instance = null;

    private OvMessageBuilderJAXBImpl() {
    }

    public static OvMessageBuilder getInstance() {
        return instance == null ? instance = new OvMessageBuilderJAXBImpl() : instance;
    }

    public OvMessage newOvMessage(String group) {
        OvMessage message = new OvMessageJAXBImpl();

        message.setMessageUUID(UUID.randomUUID());

        Calendar now = Calendar.getInstance();
        message.setTimeStamp(now);

        message.setSeverity(OvMessage.Severity.NORMAL);

        message.setMessageSource("caldte3pc42674.insidelive.net");

        message.setEventSource("caldte3pc42674");

        message.setState(OvMessage.State.PENDING);

        message.setNumberOfDuplicates(0);

        message.setData("application", "OvMessageBuilderJAXBImpl application");
        message.setData("group", group);
        message.setData("object", "OvMessageBuilderJAXBImpl object");
        message.setData("originalText", "OvMessageBuilderJAXBImpl originalText");
        message.setData("receiveTimeSeconds", now.getTimeInMillis() / 1000);
        message.setData("serviceName", "");
        message.setData("source", "OvMessageBuilderJAXBImpl source");
        message.setData("text", "OvMessageBuilderJAXBImpl text");
        message.setData("type", "");

        return message;
    }
    
    public OvMessage parse(InputStream in) throws OvMessageParseException {
        final String PACKAGE = "tools.testing.eeb.ovmessage.impl.jaxb.generated";

        tools.testing.eeb.ovmessage.impl.jaxb.generated.OvMessage message = null;

        try {
            JAXBContext jc = JAXBContext.newInstance(PACKAGE);

            Unmarshaller u = jc.createUnmarshaller();

            message = (tools.testing.eeb.ovmessage.impl.jaxb.generated.OvMessage) 
                u.unmarshal(in);
        }
        catch (JAXBException e) {
            throw new OvMessageParseException(e);
        }

        return new OvMessageJAXBImpl(message);
    }

    public OvMessage parse(File file) throws FileNotFoundException, OvMessageParseException {

        InputStream in = new FileInputStream(file);
        OvMessage message = null;

        try {
            message = parse(in);
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e) {
                // log IOException
                // If OvMessageParseException was thrown, it will
                // continue to propagate.
            }
        }

        return message;
    }
}
