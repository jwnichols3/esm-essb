package com.bgi.esm.monitoring.platform.regression;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bgi.esm.monitoring.platform.regression.data.DataMap;
import com.bgi.esm.monitoring.platform.regression.data.Event;
import com.bgi.esm.monitoring.platform.regression.hibernate.HibernateUtil;
import com.bgi.esm.monitoring.platform.regression.hibernate.EventHibernateFacade;

public class DumpEmbeddedDatabaseDataMap
{
    final private static Logger _log = Logger.getLogger ( DumpEmbeddedDatabaseDataMap.class );

    public static void main ( String args[] )
    {
        int num_datamap = countDataMapRecords();
        _log.info ( "Num data map rules: " + num_datamap );
        for ( int counter = 1; counter <= num_datamap; counter++ )
        {
            DataMap object = DataMap.select ( counter );

            System.out.print ( object.getBgiGroup() );
            System.out.print ( "," );
            System.out.print ( object.getBgiMethod() );
            System.out.print ( "," );
            System.out.print ( object.getApGroup() );
            System.out.print ( "," );
            System.out.print ( object.getApScript() );
            System.out.print ( "," );
            System.out.print ( object.getPerCat() );
            System.out.print ( "," );
            System.out.print ( object.getPerSubcat() );
            System.out.print ( "," );
            System.out.print ( object.getPerProduct() );
            System.out.print ( "," );
            System.out.print ( object.getPerProblem() );
            System.out.print ( "," );
            System.out.print ( object.getPerAssign() );
            System.out.print ( "," );
            System.out.print ( object.getPerLocation() );
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

