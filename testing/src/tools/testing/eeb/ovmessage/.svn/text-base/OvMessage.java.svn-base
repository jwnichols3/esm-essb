package tools.testing.eeb.ovmessage;

import java.util.Calendar;
import java.util.UUID;

public interface OvMessage {

    enum Severity {
        WARNING("Warning"),
        NORMAL("Normal"),
        MAJOR("Major"),
        MINOR("Minor");

        private final String value;

        Severity(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static Severity fromValue(String v) {
            for (Severity c: Severity.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
    }

    enum State {
        PENDING("PENDING"),
        ACKNOWLEDGED("ACKNOWLEDGED");

        private final String value;

        State(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static State fromValue(String v) {
            for (State c: State.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
    }

    UUID getMessageUUID();
    void setMessageUUID(UUID messageUUID);

    Calendar getTimeStamp();
    void setTimeStamp(Calendar timeStamp);

    Severity getSeverity();
    void setSeverity(Severity severity);

    String getMessageSource();
    void setMessageSource(String messageSource);

    String getEventSource();
    void setEventSource(String eventSource);

    String getPrimaryMessageRepository();
    void setPrimaryMessageRepository(String primaryMessageRepository);

    int getNumberOfDuplicates();
    void setNumberOfDuplicates(int numberOfDuplicates);

    State getState();
    void setState(State state);

    void setData(String name, String content);
    void setData(String name, Long content);

    Object getValue(String name);

    Object removeData(String name);
}
