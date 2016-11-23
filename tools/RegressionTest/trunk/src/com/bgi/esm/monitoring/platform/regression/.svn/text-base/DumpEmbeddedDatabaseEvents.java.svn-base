package com.bgi.esm.monitoring.platform.regression;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.regression.data.DataMap;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.HibernateUtil;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;

public class DumpEmbeddedDatabaseEvents
{
    final private static Logger _log = Logger.getLogger ( DumpEmbeddedDatabaseEvents.class );

    public static void main ( String args[] )
    {
        int num_records = (int) EventHibernateFacade.count();
        _log.info ( "Num events: " + num_records );
        for ( int counter = 1; counter <= num_records; counter++ )
        //for ( int counter = 1; counter <= 100; counter++ )
        {
            Event event = EventHibernateFacade.select ( counter );

            System.out.print ( event.getMessageID() );
            System.out.print ( "," );
            System.out.print ( event.getNodeName() );
            System.out.print ( "," );
            System.out.print ( event.getNodeType() );
            System.out.print ( "," );
            System.out.print ( event.getNodeTime() );
            System.out.print ( "," );
            System.out.print ( event.getNodeDate() );
            System.out.print ( "," );
            System.out.print ( event.getServerDate() );
            System.out.print ( "," );
            System.out.print ( event.getServerTime() );
            System.out.print ( "," );
            System.out.print ( event.getApplication() );
            System.out.print ( "," );
            System.out.print ( event.getMessageGroup() );
            System.out.print ( "," );
            System.out.print ( event.getSeverity() );
            System.out.print ( "," );
            System.out.print ( event.getObject() );
            System.out.print ( "," );
            System.out.print ( event.getOperators() );
            System.out.print ( "," );
            System.out.print ( event.getMessageText() );
            System.out.print ( "," );
            System.out.print ( event.getInstructions() );
            System.out.print ( "," );
            System.out.print ( event.getMessageAttributes() );
            System.out.print ( "\n" );
        }
    }

    public static int countDataMapRecords()
    {
        Session session   = HibernateUtil.getCurrentSession();
        Transaction tx    = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from com.bgi.esm.monitoring.platform.regression.data.DataMap c").uniqueResult();
        tx.commit();

        return count.intValue();
    }
};

