package com.bgi.esm.portlets.Suppression;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import com.bgi.esm.portlets.Suppression.Toolkit;

public class ExtractFile
{
    final private static Logger _log                     = Logger.getLogger ( ExtractFile.class );
    final public static String DELIMITER                 = " =-= ";
    private long query_time                              = 0L;

    private Connection con                               = null;

    private PreparedStatement ps_data_extract_log_select = null;
    private PreparedStatement ps_data_extract_log_insert = null;
    private PreparedStatement ps_data_extract_log_update = null;
    private PreparedStatement ps_data_change_log_select  = null;

    public ExtractFile() throws SQLException, IOException, ClassNotFoundException
    {
        query_time = System.currentTimeMillis();

        initialize();
    }

    public ExtractFile ( Date date ) throws SQLException, IOException, ClassNotFoundException
    {
        query_time = date.getTime();

        initialize();
    }

    public ExtractFile ( Timestamp timestamp ) throws SQLException, IOException, ClassNotFoundException
    {
        query_time = timestamp.getTime();

        initialize();
    }

    private void initialize () throws SQLException, IOException, ClassNotFoundException
    {
        con = Toolkit.getSuppressionDatabaseConnection();

        createPreparedStatements();
    }

    private void createPreparedStatements() throws SQLException
    {
        ps_data_extract_log_select = con.prepareStatement ( "SELECT last_update_tms FROM t_vpo_last_update WHERE process_nm=?" );
        ps_data_extract_log_insert = con.prepareStatement ( "INSERT INTO t_vpo_last_update ( process_nm, last_update_tms ) values ( ?, ? )" );
        ps_data_extract_log_update = con.prepareStatement ( "UPDATE t_vpo_last_update SET last_update_tms=? WHERE process_nm=?" );

        ps_data_change_log_select  = con.prepareStatement ( "SELECT last_update_tms FROM t_vpo_last_update WHERE process_nm=?" );
    }

    public ResultSet selectDataExtractLog () throws SQLException
    {
        ps_data_extract_log_select.setString ( 1, "data_extract" );

        return ps_data_extract_log_select.executeQuery();
    }

    public long retrieveQueryTime()
    {
        return query_time;
    }

    public int insertDataExtractLog ( long timestamp ) throws SQLException
    {
        ps_data_extract_log_insert.setString    ( 1, "data_extract" );
        ps_data_extract_log_insert.setTimestamp ( 2, new java.sql.Timestamp ( timestamp ) );

        return ps_data_extract_log_insert.executeUpdate();
    }

    public int updateDataExtractLog ( long timestamp ) throws SQLException
    {
        ps_data_extract_log_update.setTimestamp ( 1, new java.sql.Timestamp ( timestamp ) ); 
        ps_data_extract_log_update.setString    ( 2, "data_extract" );

        return ps_data_extract_log_update.executeUpdate();
    }

    public ResultSet selectDataChangeLog () throws SQLException
    {
        ps_data_change_log_select.setString ( 1, "data_change" );

        return ps_data_change_log_select.executeQuery();
    }

    public void toExtractFile ( String filename ) throws IOException, SQLException, ClassNotFoundException
    {
        //  Retrieve the suppressions from the database
        //Calendar calendar = Calendar.getInstance();
        //int offset        = calendar.getTimeZone().getOffset ( System.currentTimeMillis() );
        ResultSet results = Toolkit.getCurrentAtTime ( query_time );
        long start_time   = 0L;
        long end_time     = 0L;
        String group_nm   = null;  // application name
        String node_nm    = null;  // node name
        String instance   = null;  // db server instance
        String message    = null;  // db server message
       
        StringBuilder file_contents = new StringBuilder(); 
        while ( results.next() )
        {
            //start_time = (results.getTimestamp ( "start_utc_tms" ).getTime()+7*3600*1000) / 1000;
            //end_time   = (results.getTimestamp ( "end_utc_tms"   ).getTime()+7*3600*1000) / 1000;
            
            // The VPO server (rdcuxsrv125) was expecting local time instead of UTC.  Converting back.
            start_time = results.getTimestamp ( "start_utc_tms" ).getTime() / 1000;
            end_time   = results.getTimestamp ( "end_utc_tms"   ).getTime() / 1000;
            group_nm   = results.getString ( "group_nm" );
            node_nm    = results.getString ( "node_nm"  );
            instance   = results.getString ( "instance" );
            message    = results.getString ( "message"  );

            group_nm   = ((null != group_nm) && ( group_nm.trim().length() > 0 ))? group_nm.toLowerCase() : "-";
            node_nm    = ((null != node_nm)  && ( node_nm.trim().length()  > 0 ))?  node_nm.toLowerCase() : "-";
            instance   = ((null != instance) && ( instance.trim().length() > 0 ))? instance.toLowerCase() : "-";

            if ((null != message)  && ( message.trim().length()  > 0 ))
            {
                message = message.toLowerCase();

                message = message.replaceAll ( "\n", " " );
                message = message.replaceAll ( "\r", " " );
                /*
                StringBuilder new_message = new StringBuilder();
                StringTokenizer tokens    = new StringTokenizer ( message, "\n" );

                while ( tokens.hasMoreTokens() )
                {
                    new_message.append ( tokens.nextToken() );
                    new_message.append ( " " );
                }

                message = new_message.toString();
                //*/
            }
            else
            {
                message = "-";
            }

            file_contents.append ( start_time );
            file_contents.append ( DELIMITER );
            file_contents.append ( end_time );
            file_contents.append ( DELIMITER );
            file_contents.append ( group_nm );
            file_contents.append ( DELIMITER );
            file_contents.append ( node_nm );
            file_contents.append ( DELIMITER );
            file_contents.append ( instance );
            file_contents.append ( DELIMITER );
            file_contents.append ( message );
            file_contents.append ( "\n" );
        }

        /*
        FileOutputStream outfile = new FileOutputStream ( "extractfile_offset" );
            outfile.write ( "ExtractFile Offset: ".getBytes() );
            outfile.write ( Integer.toString ( offset ).getBytes() );
        outfile.close();
        //*/
        
        saveExtractFile ( filename, file_contents.toString() );

        //  Release strong references to objects to encourage garbage collection
        file_contents = null;
        //calendar      = null;
        results.close();
        results       = null;
    }

    private static synchronized void saveExtractFile ( String filename, String contents )
    {
        _log.info ( "Attempting to update extract file: " + filename );

        try
        {
            WeakReference <FileOutputStream> outfile = new WeakReference <FileOutputStream> ( new FileOutputStream ( filename ) );
        	    outfile.get().write ( contents.getBytes() );
            outfile.get().close();

            outfile = null;
        }
        catch ( IOException exception )
        {
            _log.error ( "Error when trying to update the extract file" );

            exception.printStackTrace();
        }

        _log.info ( "Finished updating extract file" );

        //  Release strong references to objects to encourage garbage collection
    }

    public long getQueryTime()
    {
        return query_time;
    }
}
