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
 *  Obtains a count of the number of records in both tables of the embedded database.
 */
public class TestEmbeddedDatabase
{
    final private static Logger _log = Logger.getLogger ( TestEmbeddedDatabase.class );

    public static void main ( String args[] )
    {
        int num_records = (int) EventHibernateFacade.count();
        _log.info ( "Number of records in Event table: " + num_records );

        num_records = (int) countDataMapRecords();
        _log.info ( "Number of records in DataMap table: " + num_records );
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
