package com.bgi.esm.monitoring.platform.dispatcher.Alarmpoint;

import org.apache.log4j.Logger;

public class QueueDrainer
{
    final private static Logger _log = Logger.getLogger ( QueueDrainer.class );
    private static QueueDrainer ref  = null;

    //  Required for Singelton pattern
    private QueueDrainer()
    {
    }

    public static QueueDrainer getInstance()
    {
        if ( null == ref )
        {
            ref = new QueueDrainer();
        }

        return ref;
    }

    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException ( "This object is a Singleton" );
    }

    public void executeCommand()
    {
    }
};
