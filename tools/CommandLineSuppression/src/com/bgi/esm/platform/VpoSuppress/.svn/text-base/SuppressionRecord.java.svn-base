package com.bgi.esm.platform.VpoSuppress;

import java.io.IOException;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.TestHarness;

public class SuppressionRecord
{
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss z" );
    final private static Logger _log = Logger.getLogger ( SuppressionRecord.class );
    private String suppress_desc_txt = null;
    private String node_nm           = null;
    private String group_nm          = null;
    private String create_nm         = null;
    private String instance          = null;
    private String message           = null;
    private String notify_flg        = null;
    private String deleted_flg       = null;
    private Date start_utc_tms       = null;
    private Date end_utc_tms         = null;
    private long timestamp_start     = 0;
    private long timestamp_end       = 0;
    private int notify_minutes       = 0;
    private int remove_on_reboot     = 0;
    private int suppress_id          = 0;

    public SuppressionRecord ( String suppression_id ) throws SQLException, ClassNotFoundException, IOException
    {
        initialize ( Integer.parseInt ( suppression_id ) );
    }

    public SuppressionRecord ( int suppression_id ) throws SQLException, ClassNotFoundException, IOException
    {
        initialize ( suppression_id );
    }

    private void initialize ( int suppression_id ) throws SQLException, ClassNotFoundException, IOException
    {
        suppress_id           = suppression_id;
        Connection con        = TestHarness.getDatabaseConnection();
        PreparedStatement ps  = con.prepareStatement ( "SELECT suppress_id, group_nm, node_nm, create_nm, start_utc_tms, end_utc_tms, notify_flg, notify_minutes, suppress_desc_txt, instance, message, deleted_flg FROM t_vpo_suppress where suppress_id=?" );
        ps.setInt ( 1, suppress_id );

        ResultSet results     = ps.executeQuery();
        if ( results.next() )
        {
            group_nm          = results.getString (  2 );
            node_nm           = results.getString (  3 );
            create_nm         = results.getString (  4 );
            long l_start_utc_tms     = results.getTimestamp (  5 ).getTime();
            long l_end_utc_tms       = results.getTimestamp (  6 ).getTime();
            notify_flg        = results.getString (  7 );
            notify_minutes    = results.getInt    (  8 );
            suppress_desc_txt = results.getString (  9 );
            instance          = results.getString ( 10 );
            message           = results.getString ( 11 );
            deleted_flg       = results.getString ( 12 );

            start_utc_tms     = new java.sql.Date ( l_start_utc_tms );
            end_utc_tms       = new java.sql.Date ( l_end_utc_tms );

            Calendar c        = Calendar.getInstance();
            c.setTime ( new Date ( l_end_utc_tms ) );
            //c.add ( Calendar.MONTH, -1 );
            end_utc_tms.setTime ( c.getTime().getTime() );

            _log.info ( "Init start: " + start_utc_tms.toString() );
            _log.info ( "Init end:   " + end_utc_tms.toString() );

            results.close();
            ps.close();
        }
        else
        {
            results.close();
            ps.close();

            throw new SQLException ( "Could not find Suppress ID#" + suppression_id );
        }
    }

    public String getApplication()
    {
        return group_nm;
    }

    public String getNode()
    {
        return node_nm;
    }

    public String getCreator()
    {
        return create_nm;
    }

    public long getStartTimestamp()
    {
        return start_utc_tms.getTime();
    }

    public String getStartTime()
    {
        return sdf.format ( (java.util.Date) start_utc_tms );
    }

    public java.util.Date getStartDate()
    {
        return start_utc_tms;
    }

    public long getEndTimestamp()
    {
        return end_utc_tms.getTime();
    }

    public String getEndTime()
    {
        return sdf.format ( (java.util.Date) end_utc_tms );
    }

    public java.util.Date getEndDate()
    {
        return end_utc_tms;
    }

    public String getDescription()
    {
        return suppress_desc_txt;
    }

    public String getInstance()
    {
        return instance;
    }

    public String getMessageText()
    {
        return message;
    }

    public long getNumSecondsLeft()
    {
        return (end_utc_tms.getTime() - start_utc_tms.getTime()) / 1000;
    }

    public double getNumMinutesLeft()
    {
        return (double) getNumSecondsLeft() / 60.0;
    }

    public boolean isDeleted()
    {
        return (( deleted_flg != null ) && ( deleted_flg.equals ( "y" ) ));
    }

    public double getNumHoursLeft()
    {
        return (double) getNumSecondsLeft() / 3600.0;
    }
};
