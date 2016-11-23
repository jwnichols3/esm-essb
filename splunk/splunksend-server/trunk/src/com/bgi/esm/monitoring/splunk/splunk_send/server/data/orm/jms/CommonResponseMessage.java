package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;


public abstract class CommonResponseMessage
{
    @SuppressWarnings ( "unused" )
    final private static Logger _log  = Logger.getLogger ( CommonResponseMessage.class );
    private Calendar ReceiveTimestamp = Calendar.getInstance();
    private String ResponseMessage    = null;

    public Calendar getReceiveTimestamp()
    {
        return ReceiveTimestamp;
    }

    public String toString()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( " ( ResponseTimestamp=" );
            message.get().append ( CommonObject.formatDate ( ReceiveTimestamp ) );
            message.get().append ( ", ReceiveMessage=" );
            message.get().append ( ResponseMessage );
            message.get().append ( " )" );
        return message.get().toString();
    }

    public void setResponseMessage ( String response_message )
    {
        ResponseMessage = response_message;
    }

    public String getResponseMessage()
    {
        return ResponseMessage;
    }
}