package tools.testing.eeb.ovmessage;

@SuppressWarnings("serial")
public class FactoryConfigurationError extends Error {
    public FactoryConfigurationError() {
        super();
    }

    public FactoryConfigurationError(Exception e) {
        super(e);
    }

    public FactoryConfigurationError(Exception e, String msg) {
        super(msg, e);
    }

    public FactoryConfigurationError(String msg) {
        super(msg);
    }
}
