package com.bgi.esm.monitoring.platform.notifications;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SuppressionBean implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID         = 1174332732934L;
    private static final Logger _log                   = Logger.getLogger ( SuppressionBean.class );

    private String    RowId            = null;
    private long      SuppressId       = 0L;
    private long      NotifyMinutes    = 0;
    private Calendar  StartTime        = Calendar.getInstance();
    private Calendar  EndTime          = Calendar.getInstance();
    private String    ApName           = null;
    private String    NodeName         = null;
    private String    GroupName        = null;
    private String    DbServer         = null;
    private boolean   NotifyFlg        = false;
    private boolean   RemoveOnReboot   = false;
    private String    Description      = null;
    private String    Message          = null;


    public SuppressionBean()
    {
    }

    /**
     *  Setter method for the 'row_id' column
     *
     * @param row_id  The new value for the 'row_id' column
     */
    public void setRowId ( String row_id )
    {
        this.RowId = row_id;
    }

    /**
     *  Getter method for the 'row_id' column
     *  @return The new value for the 'row_id' column
     */
    public String getRowId ()
    {
        return RowId;
    }

    /**
     *  Setter method for the 'suppress_id' column
     *
     * @param suppress_id  The new value for the 'suppress_id' column
     */
    public void setSuppressId ( long suppress_id )
    {
        this.SuppressId = suppress_id;
    }

    /**
     *  Getter method for the 'suppress_id' column
     *  @return The new value for the 'suppress_id' column
     */
    public long getSuppressId ()
    {
        return SuppressId;
    }

    /**
     *  Setter method for the 'start_time' column
     *
     * @param start_time  The new value for the 'start_time' column
     */
    public void setStartTime ( Calendar start_time )
    {
        this.StartTime = start_time;
    }

    /**
     *  Getter method for the 'start_time' column
     *  @return The new value for the 'start_time' column
     */
    public Calendar getStartTime ()
    {
        return StartTime;
    }

    /**
     *  Setter method for the 'end_time' column
     *
     * @param end_time  The new value for the 'end_time' column
     */
    public void setEndTime ( Calendar end_time )
    {
        this.EndTime = end_time;
    }

    /**
     *  Getter method for the 'end_time' column
     *  @return The new value for the 'end_time' column
     */
    public Calendar getEndTime ()
    {
        return EndTime;
    }

    public long getEndTimestamp()
    {
        return EndTime.getTime().getTime();
    }

    /**
     *  Setter method for the 'ap_name' column
     *
     * @param ap_name  The new value for the 'ap_name' column
     */
    public void setApName ( String ap_name )
    {
        this.ApName = ap_name;
    }

    /**
     *  Getter method for the 'ap_name' column
     *  @return The new value for the 'ap_name' column
     */
    public String getApName ()
    {
        return ApName;
    }

    /**
     *  Setter method for the 'node_name' column
     *
     * @param node_name  The new value for the 'node_name' column
     */
    public void setNodeName ( String node_name )
    {
        this.NodeName = node_name;
    }

    /**
     *  Getter method for the 'node_name' column
     *  @return The new value for the 'node_name' column
     */
    public String getNodeName ()
    {
        return NodeName;
    }

    /**
     *  Setter method for the 'group_name' column
     *
     * @param group_name  The new value for the 'group_name' column
     */
    public void setGroupName ( String group_name )
    {
        this.GroupName = group_name;
    }

    /**
     *  Getter method for the 'group_name' column
     *  @return The new value for the 'group_name' column
     */
    public String getGroupName ()
    {
        return GroupName;
    }

    /**
     *  Setter method for the 'db_server' column
     *
     * @param db_server  The new value for the 'db_server' column
     */
    public void setDbServer ( String db_server )
    {
        this.DbServer = db_server;
    }

    /**
     *  Getter method for the 'db_server' column
     *  @return The new value for the 'db_server' column
     */
    public String getDbServer ()
    {
        return DbServer;
    }

    /**
     *  Setter method for the 'notify_flg' column
     *
     * @param notify_flg  The new value for the 'notify_flg' column
     */
    public void setNotifyFlg ( boolean notify_flg )
    {
        this.NotifyFlg = notify_flg;
    }

    /**
     *  Getter method for the 'notify_flg' column
     *  @return The new value for the 'notify_flg' column
     */
    public boolean getNotifyFlg ()
    {
        return NotifyFlg;
    }

    /**
     *  Setter method for the 'notify_minutes' column
     *
     * @param notify_minutes  The new value for the 'notify_minutes' column
     */
    public void setNotifyMinutes ( String notify_minutes )
    {
        if ( null == notify_minutes )
        {
            this.NotifyMinutes = 0L;
        }
        else
        {
            try
            {
                long num_minutes = Long.parseLong ( notify_minutes );

                this.NotifyMinutes = num_minutes;
            }
            catch ( NumberFormatException exception )
            {
                _log.warn ( "Defaulting to 0 minutes because bad argument detected: " + notify_minutes, exception );

                this.NotifyMinutes = 0L;
            }
        }
    }

    /**
     *  Setter method for the 'notify_minutes' column
     *
     * @param notify_minutes  The new value for the 'notify_minutes' column
     */
    public void setNotifyMinutes ( long notify_minutes )
    {
        this.NotifyMinutes = notify_minutes;
    }

    /**
     *  Getter method for the 'notify_minutes' column
     *  @return The new value for the 'notify_minutes' column
     */
    public long getNotifyMinutes ()
    {
        return NotifyMinutes;
    }

    /**
     *  Setter method for the 'remove_on_reboot' column
     *
     * @param remove_on_reboot  The new value for the 'remove_on_reboot' column
     */
    public void setRemoveOnReboot ( boolean remove_on_reboot )
    {
        this.RemoveOnReboot = remove_on_reboot;
    }

    /**
     *  Getter method for the 'remove_on_reboot' column
     *  @return The new value for the 'remove_on_reboot' column
     */
    public boolean getRemoveOnReboot ()
    {
        return RemoveOnReboot;
    }

    /**
     *  Setter method for the 'description' column
     *
     * @param description  The new value for the 'description' column
     */
    public void setDescription ( String description )
    {
        this.Description = description;
    }

    /**
     *  Getter method for the 'description' column
     *  @return The new value for the 'description' column
     */
    public String getDescription ()
    {
        return Description;
    }

    /**
     *  Setter method for the 'message' column
     *
     * @param message  The new value for the 'message' column
     */
    public void setMessage ( String message )
    {
        this.Message = message;
    }

    /**
     *  Getter method for the 'message' column
     *  @return The new value for the 'message' column
     */
    public String getMessage ()
    {
        return Message;
    }

    public static long countNumSuppressions()
    {
        Query query     = null;
        Session session = HibernateUtil.getSuppressionsCurrentSession();
        Transaction tx  = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from com.bgi.esm.monitoring.platform.notifications.SuppressionBean").uniqueResult();
        tx.commit();

        return count.longValue();
    }

    public static SuppressionBean getSuppression ( long suppression_id )
    {
        SuppressionBean object = null;
        Session session = HibernateUtil.getSuppressionsCurrentSession();
        Transaction tx  = session.beginTransaction();
            object = (SuppressionBean) session.get ( SuppressionBean.class, suppression_id );
        tx.commit();

        return object;
    }

    public static List <Integer> getCurrentSuppressions()
    {
        List <Integer> list = null;
        Iterator <Object> i = null;
        Calendar calendar   = Calendar.getInstance();
        Query query         = null;
        Session session     = HibernateUtil.getSuppressionsCurrentSession();
        Transaction tx      = session.beginTransaction();
            //query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.notifications.SuppressionBean c WHERE c.StartTime <= ? AND c.EndTime >= ?" );
            query = session.createSQLQuery ( "SELECT suppress_id FROM t_vpo_suppress WHERE end_utc_tms > :CurrentTime AND start_utc_tms < :CurrentTime" );
            query.setParameter ( "CurrentTime", calendar );
            //query = query.setDate ( 0, new java.util.Date() );
            //query = session.createQuery ( "FROM com.bgi.esm.monitoring.platform.notifications.SuppressionBean c WHERE c.EndTime > ?" );
            //query.setCalendar ( 0, calendar );
            //query.setCalendar ( 0, calendar.getTime().getTime() );
            //query.setCalendar ( 1, calendar );

            i = query.list().iterator();
        tx.commit();

        list = new Vector <Integer> ();
        while ( i.hasNext() )
        {
            list.add ( new Integer ( i.next().toString() ) );
        }

        return list;
    }
};
