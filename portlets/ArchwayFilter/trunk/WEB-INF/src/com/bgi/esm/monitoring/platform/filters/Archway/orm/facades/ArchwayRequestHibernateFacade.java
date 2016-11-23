package com.bgi.esm.monitoring.platform.filters.Archway.orm.facades;

import java.util.Calendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.HibernateUtil;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.facades.AbstractArchwayRequestFacade;
import com.bgi.esm.monitoring.platform.filters.Archway.orm.beans.ArchwayRequestBean;

public class ArchwayRequestHibernateFacade extends AbstractArchwayRequestFacade
{
    final private static Logger _log = Logger.getLogger ( ArchwayRequestHibernateFacade.class );

    public List selectAllRequests()
    {
        String query_str = "FROM com.bgi.esm.monitoring.platform.filters.Archway.orm.beans.ArchwayRequestBean c";
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( query_str );

            list = query.list();
        tx.commit();

        return list;
    }

    public ArchwayRequestBean select ( long request_id )
    {
        ArchwayRequestBean object = null;
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            object = (ArchwayRequestBean) session.get ( ArchwayRequestBean.class, request_id );
        tx.commit();

        return object;
    }

    public boolean insertOrUpdateRow ( ArchwayRequestBean object )
    {
        ArchwayRequestBean data = null;
        boolean was_update      = false;
        Session session         = null;
        Transaction tx          = null;

        data = select ( object.getRequestID() );

        if ( null != data )
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                data.setValue ( object );
                session.update ( data );
            tx.commit();

            was_update = true;
        }
        else
        {
            session = HibernateUtil.getCurrentSession();
            tx      = session.beginTransaction();
                session.save ( object );
            tx.commit();

            was_update = false;
        }

        return was_update;
    }

    public List findRequestsBetweenTimes ( Calendar start_time, Calendar end_time )
    {
        String query_str = "FROM com.bgi.esm.monitoring.platform.filters.Archway.orm.beans.ArchwayRequestBean c WHERE RequestTime >=:StartTime AND RequestTime <=:EndTime";
        List list        = null;
        Query query      = null;
        Session session  = HibernateUtil.getCurrentSession();
        Transaction tx   = session.beginTransaction();
            query = session.createQuery ( query_str );
            query = query.setParameter ( "StartTime", start_time );
            query = query.setParameter ( "EndTime",   end_time   );

            list = query.list();
        tx.commit();

        return list;
   }
};
