package tools.testing.eeb.ovmessage.impl.jaxb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

public class OvMessageDatatypeConverter {
    public static UUID parseMessageUUID(String value) {
        return UUID.fromString(value);
    }

    public static String printMessageUUID(UUID value) {
        return value.toString();
    }

    public static Calendar parseDateTime(String value) {
        return DatatypeConverter.parseDateTime(value);
    }

    public static String printDateTime(Calendar value) {
        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return formatter.format(value.getTime());

    }
}
