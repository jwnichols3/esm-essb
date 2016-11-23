package com.bgi.esm.monitoring.platform.test.openview.orm;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class OpenviewMessageText implements Serializable
{
    final private static Logger _log = Logger.getLogger ( OpenviewMessageText.class );

    protected String MessageNumber = null;
    protected String TextPart      = null;
    protected int OrderNumber      = 0;

    public OpenviewMessageText()
    {
    }

    public abstract OpenviewMessageText getValue();

    public void setValue ( OpenviewMessageText message_text )
    {
        if ( null != message_text )
        {
            setMessageNumber ( message_text.getMessageNumber() );
            setTextPart      ( message_text.getTextPart()      );
            setOrderNumber   ( message_text.getOrderNumber()   );
        }
        else
        {
            throw new IllegalArgumentException ( "Attempted to set OpenviewMessageText to null" );
        }
    }

    public void setMessageNumber ( String message_number )
    {
        MessageNumber = message_number;
    }

    public String getMessageNumber()
    {
        return MessageNumber;
    }
    
    public void setTextPart ( String text_part )
    {
        TextPart = text_part;
    }

    public String getTextPart()
    {
        return TextPart;
    }

    public void setOrderNumber ( int order_number )
    {
        OrderNumber = order_number;
    }

    public int getOrderNumber()
    {
        return OrderNumber;
    }
};
