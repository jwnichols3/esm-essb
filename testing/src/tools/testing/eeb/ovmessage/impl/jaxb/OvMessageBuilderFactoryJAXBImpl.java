package tools.testing.eeb.ovmessage.impl.jaxb;

import tools.testing.eeb.ovmessage.OvMessageBuilder;
import tools.testing.eeb.ovmessage.OvMessageBuilderFactory;

public class OvMessageBuilderFactoryJAXBImpl extends OvMessageBuilderFactory {
    public OvMessageBuilder newOvMessageBuilder() {
        return OvMessageBuilderJAXBImpl.getInstance();
    }
}
