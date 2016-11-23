package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.jms;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.AbstractHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;


public class HttpCookieRequestMessage extends AbstractHttpCookie implements IHttpCookie, Serializable
{
    /*
     *
     */
    final private static long serialVersionUID = 3313128988735600007L;

    @SuppressWarnings ( "unused" )
    final private static Logger _log  = Logger.getLogger ( HttpCookieRequestMessage.class );
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