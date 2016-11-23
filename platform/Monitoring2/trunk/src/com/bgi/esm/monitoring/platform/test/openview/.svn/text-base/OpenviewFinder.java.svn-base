package com.bgi.esm.monitoring.platform.test.openview;

import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.test.openview.orm.ActiveMessage;
import com.bgi.esm.monitoring.platform.test.openview.orm.HistoricalMessage;
import com.bgi.esm.monitoring.platform.test.openview.orm.OpenviewMessage;
import com.bgi.esm.monitoring.platform.test.openview.orm.ActiveMessageText;
import com.bgi.esm.monitoring.platform.test.openview.orm.HistoricalMessageText;
import com.bgi.esm.monitoring.platform.test.openview.orm.OpenviewMessageText;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class OpenviewFinder
{
    public abstract ActiveMessage     findActiveMessageByMessageText     ( String message_text );
    public abstract ActiveMessage     findActiveMessageByMessageID       ( String message_id   );

    public abstract HistoricalMessage findHistoricalMessageByMessageText ( String message_text );
    public abstract HistoricalMessage findHistoricalMessageByMessageID   ( String message_id   );
};
