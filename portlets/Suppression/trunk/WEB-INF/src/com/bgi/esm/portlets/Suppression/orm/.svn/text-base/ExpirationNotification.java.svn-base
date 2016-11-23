package com.bgi.esm.portlets.Suppression.orm;

import java.util.Calendar;
import org.apache.log4j.Logger;

public class ExpirationNotification
{
    final private static Logger _log = Logger.getLogger ( ExpirationNotification.class );

    protected long RowID                     = 0L;
    protected long SuppressionID             = 0L;
    protected String NotificationEmails      = null;
    protected Calendar NotificationTime      = null;
    protected Calendar NotificationTimestamp = null;

    public ExpirationNotification()
    {
    }

    public void setValue ( ExpirationNotification notification )
    {
        setSuppressionID         ( notification.getSuppressionID()         );
        setNotificationEmails    ( notification.getNotificationEmails()    );
        setNotificationTime      ( notification.getNotificationTime()      );
        setNotificationTimestamp ( notification.getNotificationTimestamp() );
    }

    public ExpirationNotification getValue()
    {
        ExpirationNotification object = new ExpirationNotification();
        object.setValue ( this );

        return object;
    }

    public void setRowID ( long row_id )
    {
        RowID = row_id;
    }

    public long getRowID()
    {
        return RowID;
    }

    public void setSuppressionID ( long suppression_id )
    {
        SuppressionID = suppression_id;
    }

    public long getSuppressionID()
    {
        return SuppressionID;
    }

    public void setNotificationEmails ( String notification_emails )
    {
        NotificationEmails = notification_emails;
    }

    public String getNotificationEmails()
    {
        return NotificationEmails;
    }

    public void setNotificationTime ( Calendar notification_time )
    {
        NotificationTime = notification_time;
    }

    public Calendar getNotificationTime ()
    {
        return NotificationTime;
    }

    public void setNotificationTimestamp ( Calendar notification_timestamp )
    {
        NotificationTimestamp = notification_timestamp;
    }

    public Calendar getNotificationTimestamp()
    {
        return NotificationTimestamp;
    }
}
