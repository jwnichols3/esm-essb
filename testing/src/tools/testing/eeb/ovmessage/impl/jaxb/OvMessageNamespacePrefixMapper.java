package tools.testing.eeb.ovmessage.impl.jaxb;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class OvMessageNamespacePrefixMapper extends NamespacePrefixMapper {
    private static final String OV_MESSAGE_NAMESPACE_URI = "http://openview.hp.com/xmlns/ico/message";

    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {

        if (namespaceUri.equals(OV_MESSAGE_NAMESPACE_URI) && !requirePrefix) {
            return "";
        }

        return suggestion;
    }
}
