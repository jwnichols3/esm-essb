package com.bgi.esm.monitoring.platform.regression;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.regression.data.DataMap;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.HibernateUtil;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;

/**
 *  Takes data from one database and copies it into the embedded database for this application
 */
public class CreateEmbeddedDatabase
{
    final private static Logger _log = Logger.getLogger ( CreateEmbeddedDatabase.class );

    public static void main ( String args[] )
    {
        int num_records = (int) EventHibernateFacade.count();
        _log.info ( "Num events: " + num_records );
        for ( int counter = 1; counter <= num_records; counter++ )
        //for ( int counter = 1; counter <= 100; counter++ )
        {
            Event event = EventHibernateFacade.select ( counter );

            _log.info ( "saving event #" + counter );

            saveEvent ( event );
        }

        int num_datamap = countDataMapRecords();
        _log.info ( "Num data map rules: " + num_datamap );
        for ( int counter = 1; counter <= num_datamap; counter++ )
        {
            DataMap object = DataMap.select ( counter );

            _log.info ( "saving data map rule #" + counter );

            saveDataMap ( object );
        }

        _log.info ( "Num data map rules in Derby: " + countDataMapRecordsInDerby() );
    }

    private static void saveEvent ( Event object )
    {
        Session session = HibernateUtil.getDerbyCurrentSession();
        Transaction tx  = session.beginTransaction();
            session.save ( object );
        tx.commit();
    }

    private static void saveDataMap ( DataMap object )
    {
        Session session = HibernateUtil.getDerbyCurrentSession();
        Transaction tx  = session.beginTransaction();
            session.save ( object );
        tx.commit();
    }

    public static int countDataMapRecords()
    {
        Session session   = HibernateUtil.getCurrentSession();
        Transaction tx    = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from com.bgi.esm.monitoring.platform.regression.data.DataMap c").uniqueResult();
        tx.commit();

        return count.intValue();
    }

    public static int countDataMapRecordsInDerby()
    {
        Session session   = HibernateUtil.getDerbyCurrentSession();
        Transaction tx    = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from com.bgi.esm.monitoring.platform.regression.data.DataMap c").uniqueResult();
        tx.commit();

        return count.intValue();
    }
};
