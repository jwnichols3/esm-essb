package tools.testing.eeb.ovmessage;

@SuppressWarnings("serial")
public class OvMessageParseException extends Exception {

    public OvMessageParseException() {
        super();
    }

    public OvMessageParseException(String message) {
        super(message);
    }

    public OvMessageParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OvMessageParseException(Throwable cause) {
        super(cause);
    }
}
