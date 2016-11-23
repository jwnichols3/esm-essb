package com.bgi.esm.portlets.Suppression;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.orm.ExpirationNotification;
import com.bgi.esm.portlets.Suppression.orm.HibernateFacade;
import com.bgi.esm.portlets.Suppression.orm.SuppressionRecord;

public class Notifier implements Runnable
{
    final private static Logger _log = Logger.getLogger ( Notifier.class );

    private boolean IsRunning = true;

    public void run()
    {
        while ( true == IsRunning )
        {
            //  Get the current time stamp
            Calendar timestamp = Calendar.getInstance();

            //  Find all active suppressions
            HibernateFacade hibernateFacade = Toolkit.getHibernateFacade();
            List <ExpirationNotification> activeNotifications = hibernateFacade.getActiveNotifications();

            //  Logging
            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::run() - firing at " );
                message.get().append ( HibernateFacade.sdf.format ( timestamp.getTime() ) );
                message.get().append ( " - found num active notifications: " );
                message.get().append ( activeNotifications.size() );

                _log.info ( message.get().toString() );
            }

            for ( int counter = 0; counter < activeNotifications.size(); counter++ )
            {
                ExpirationNotification notification = activeNotifications.get ( counter );

                if ( notification.getNotificationTime().before ( timestamp ) )
                {
                    try
                    {
                        //  Construct the email
                        String recipients[] = notification.getNotificationEmails().split ( "," );
                        //String recipients[] = new String[] { "linden@barclaysglobal.com" };

                        _log.info ( "Searching for Suppression ID #" + notification.getSuppressionID() );
                        SuppressionRecord suppressionRecord = hibernateFacade.findSuppressionRecord ( notification.getSuppressionID() );
                        if ( null == suppressionRecord )
                        {
                            _log.error ( "Could not find Suppression ID #" + notification.getSuppressionID() );
                        }

                        StringBuilder emailMessage = new StringBuilder();
                        emailMessage.append ( "This is a friendly notification to let you know that Suppression #" );
                        emailMessage.append ( notification.getSuppressionID() );
                        emailMessage.append ( " is about to expire.\n\n" );
                        emailMessage.append ( "Suppression Details:" );
                        emailMessage.append ( "\n    - Application:       "  );
                        if ( null != suppressionRecord.getGroupName()        ) emailMessage.append ( suppressionRecord.getGroupName() );
                        emailMessage.append ( "\n    - Node:              "  );  
                        if ( null != suppressionRecord.getNodeName()         ) emailMessage.append ( suppressionRecord.getNodeName() );
                        emailMessage.append ( "\n    - Description:       "  );
                        if ( null != suppressionRecord.getDescription()      ) emailMessage.append ( suppressionRecord.getDescription() );
                        emailMessage.append ( "\n    - Database Instance: "  );
                        if ( null != suppressionRecord.getDatabaseInstance() ) emailMessage.append ( suppressionRecord.getDatabaseInstance() );
                        emailMessage.append ( "\n    - Database Message:  "  );
                        if ( null != suppressionRecord.getMessage()          ) emailMessage.append ( suppressionRecord.getMessage() );
                        emailMessage.append ( "\n    - Start Time:        " );
                        emailMessage.append ( HibernateFacade.sdf.format ( suppressionRecord.getStartTime().getTime() ) );
                        emailMessage.append ( "\n    - End Time:          " );
                        emailMessage.append ( HibernateFacade.sdf.format ( suppressionRecord.getEndTime().getTime() ) );
                        emailMessage.append ( "\n\nESM Team" );

                        StringBuilder emailSubject = new StringBuilder();
                        emailSubject.append ( "BGI IT ESM - Suppression #" );
                        emailSubject.append ( notification.getSuppressionID() );
                        emailSubject.append ( " is about to expire" );
                
                        //  Fire off the email
                        Mailer.postMail ( recipients, emailSubject.toString(), emailMessage.toString(), "bgiitesm@barclaysglobal.com" );
                
                        //  Save the notification time
                        Calendar notificationTimestamp = Calendar.getInstance();
                        notification.setNotificationTimestamp ( notificationTimestamp );
                        hibernateFacade.insertOrUpdateNotification ( notification );

                        _log.info ( "Notification email sent for Notification #" + notification.getRowID() );

                        //  Memory management
                        WeakReference <StringBuilder> trash1 = new WeakReference <StringBuilder> ( emailMessage );
                        WeakReference <StringBuilder> trash2 = new WeakReference <StringBuilder> ( emailSubject );
                        emailMessage = null;
                        emailSubject = null;
                    }
                    catch ( Exception exception )
                    {
                        WeakReference <StringBuilder> errorMessage = new WeakReference <StringBuilder> ( new StringBuilder() );
                        errorMessage.get().append ( "Could not process Notification #" );
                        errorMessage.get().append ( notification.getRowID() );
                        errorMessage.get().append ( " for Suppression #" );
                        errorMessage.get().append ( notification.getSuppressionID() );
                        errorMessage.get().append ( " at " );
                        errorMessage.get().append ( hibernateFacade.sdf.format ( new java.util.Date() ) );
                        errorMessage.get().append ( " - " );
                        errorMessage.get().append ( exception.getMessage() );

                        _log.error ( errorMessage.get().toString(), exception );
                    }
                }
            }

            Calendar end_timestamp = Calendar.getInstance();

            try
            {
                Thread.sleep ( 60000 );  // Sleep for a minute
            }
            catch ( InterruptedException exception )
            {
                _log.error ( exception );
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
};
