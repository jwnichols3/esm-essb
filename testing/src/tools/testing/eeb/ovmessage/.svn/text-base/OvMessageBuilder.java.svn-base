package tools.testing.eeb.ovmessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class OvMessageBuilder {
    public abstract OvMessage newOvMessage(String group);
    public abstract OvMessage parse(InputStream in) throws OvMessageParseException;
    public abstract OvMessage parse(File file) throws FileNotFoundException, OvMessageParseException;
}
