package com.bgi.esm.monitoring.platform.test.openview.orm;

import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.test.openview.orm.OpenviewMessage;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ActiveMessage extends OpenviewMessage
{
    final private static Logger _log = Logger.getLogger ( ActiveMessage.class );

    public ActiveMessage getValue()
    {
        ActiveMessage message = new ActiveMessage();
        message.setValue ( this );

        return message;
    }
};
