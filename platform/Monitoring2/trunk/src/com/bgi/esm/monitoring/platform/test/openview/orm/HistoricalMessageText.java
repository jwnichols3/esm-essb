package com.bgi.esm.monitoring.platform.test.openview.orm;

import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.test.openview.orm.OpenviewMessageText;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class HistoricalMessageText extends OpenviewMessageText
{
    final private static Logger _log = Logger.getLogger ( HistoricalMessageText.class );

    public OpenviewMessageText getValue()
    {
        HistoricalMessageText message_text = new HistoricalMessageText();
        message_text.setValue ( this );

        return message_text;
    }
};
