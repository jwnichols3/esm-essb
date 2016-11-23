package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jms;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;


public class HttpRequestRequestMessage extends AbstractHttpRequest implements IHttpRequest, Serializable
{
    /*
     *
     */
    final private static long serialVersionUID = -1061357456388169091L;

    @SuppressWarnings ( "unused" )
    final private static Logger _log  = Logger.getLogger ( HttpRequestRequestMessage.class );
    private Calendar ReceiveTimestamp = Calendar.getInstance();

    public String toString()
    {
        String original_value = super.toString();
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( original_value );
            message.get().append ( " - Received at " );
            message.get().append ( CommonObject.formatDate ( ReceiveTimestamp ) );
        return message.get().toString();
    }
}