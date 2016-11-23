package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ProcessData
{
    public static void main ( String args[] ) throws ClassNotFoundException, SQLException
    {
        Class.forName ( "com.mysql.jdbc.Driver" );
        Connection con              = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/testdump_dest", "root", "apogee" );
        Statement stmt              = con.createStatement();
        PreparedStatement ps_insert = con.prepareStatement ( "INSERT INTO EventsPeregrineAlarmpoint ( message_number, anno_text_id, timestamp, PeregrineCategory, PeregrineSubcategory, PeregrineProductType, PeregrineProblemType ) VALUES ( ?, ?, ?, ?, ?, ?, ? )" );
        PreparedStatement ps_exist  = con.prepareStatement ( "SELECT timestamp FROM EventsPeregrineAlarmpoint WHERE message_number=? AND anno_text_id=?" );
        ResultSet results           = stmt.executeQuery ( "SELECT a1.message_number, a2.anno_text_id, a3.time, a2.text_part FROM testdump_dest.opc_hist_annotation AS a1, testdump_dest.opc_hist_anno_text AS a2, testdump_dest.opc_hist_annotation AS a3 WHERE a1.anno_text_id=a2.anno_text_id AND a2.anno_text_id=a3.anno_text_id AND a2.text_part LIKE \"Peregrine Ticket%\"" );
        String annotation           = null;
        String p_category           = null;
        String p_subcategory        = null;
        String p_product_type       = null;
        String p_problem_type       = null;
        String message_number       = null;
        String anno_text_id         = null;
        int index                   = 0;
        int total_new_records       = 0;
        int num_new_records         = 0;
        long date                   = 0;

        while ( results.next() )
        {
            message_number = results.getString ( 1 );
            anno_text_id   = results.getString ( 2 );
            date           = results.getLong   ( 3 );
            annotation     = results.getString ( 4 ).replaceAll( "\n", "" ).replaceAll( "\r", "" );

            //  Remove up to the first colon
            index          = annotation.indexOf ( ":" );
            annotation     = annotation.substring ( index+1 );
            index          = annotation.indexOf ( "Peregrine" );
            p_category     = annotation.substring ( 0, index ).trim();
            if ( index >= 0 )
            {
                p_category = annotation.substring ( 0, index ).trim();
            }
            else
            {
                p_category = null;
            }

            annotation     = annotation.substring ( index );
            index          = annotation.indexOf ( ":" );
            annotation     = annotation.substring ( index+1 );
            index          = annotation.indexOf ( "Peregrine" );
            p_subcategory  = annotation.substring ( 0, index ).trim();
            if ( index >= 0 )
            {
                p_subcategory = annotation.substring ( 0, index ).trim();
            }
            else
            {
                p_subcategory = null;
            }

            annotation     = annotation.substring ( index );
            index          = annotation.indexOf ( ":" );
            annotation     = annotation.substring ( index+1 );
            index          = annotation.indexOf ( "Peregrine" );
            if ( index >= 0 )
            {
                p_product_type = annotation.substring ( 0, index ).trim();
            }
            else
            {
                p_product_type = null;
            }

            if ( index >= 0 )
            {
                annotation     = annotation.substring ( index );
                index          = annotation.indexOf ( ":" );
                annotation     = annotation.substring ( index+1 );
                index          = annotation.indexOf ( "Peregrine" );
                if ( index >= 0 )
                {
                    p_problem_type = annotation.substring ( 0, index ).trim();
                }
                else
                {
                    p_problem_type = null;
                }
            }
            else
            {
                p_problem_type = null;
            }

            //Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            //calendar.setTimeInMillis ( date.getTime() );
            java.util.Date out_date = new Date ( date * 1000L );

            System.out.print ( java.text.DateFormat.getDateInstance().format(out_date) );
            System.out.print ( ", " );
            System.out.print ( p_category     );
            System.out.print ( ", " );
            System.out.print ( p_subcategory  );
            System.out.print ( ", " );
            System.out.print ( p_product_type );
            System.out.print ( ", " );
            System.out.print ( p_problem_type );
            System.out.print ( "\n" );

            ps_exist  = con.prepareStatement ( "SELECT timestamp FROM EventsPeregrineAlarmpoint WHERE message_number=? AND anno_text_id=?" );
            ps_exist.setString ( 1, message_number );
            ps_exist.setString ( 2, anno_text_id   );

            ResultSet test_exist = ps_exist.executeQuery();

            if ( test_exist.next() )
            {
                System.out.println ( "Record exists for message_number=" + message_number + ", anno_text_id=" + anno_text_id );
            }
            else
            {
                ps_insert.setString ( 1, message_number );
                ps_insert.setString ( 2, anno_text_id   );
                ps_insert.setLong   ( 3, date * 1000L   );
                ps_insert.setString ( 4, p_category     );
                ps_insert.setString ( 5, p_subcategory  );
                ps_insert.setString ( 6, p_product_type );
                ps_insert.setString ( 7, p_problem_type );

                num_new_records    = ps_insert.executeUpdate();
                System.out.println ( "Num records added: " + num_new_records );

                total_new_records += num_new_records;
                System.out.println ( "Total records added: " + num_new_records );
            }
        }

        System.out.println ( "********************************************************" );
        System.out.println ( "***** BEGIN Linking records" );
        System.out.println ( "********************************************************" );

        PreparedStatement ps_search_unlinked = con.prepareStatement ( "SELECT message_number, anno_text_id, PeregrineCategory FROM EventsPeregrineAlarmpoint WHERE AlarmpointEvents IS NULL" );
        PreparedStatement ps_search_links    = con.prepareStatement ( "SELECT ID FROM testdump_dest.LogNotificationHistory WHERE id IN (SELECT ID FROM testdump_dest.LogNotificationHistory WHERE IncidentID IS NOT NULL AND IncidentID LIKE 'IM%' AND IncidentID=?)" );
        PreparedStatement ps_search_update   = con.prepareStatement ( "UPDATE EventsPeregrineAlarmpoint SET NumAlarmpointEvents=?, AlarmpointEvents=? WHERE message_number=? AND anno_text_id=?" );
        String peregrine_ticket_num          = null;
        ResultSet results_alarmpoint         = null;
        StringBuilder event_list             = null;
        String events                        = null;
        int num_alarmpoint_events            = 0;

        results = ps_search_unlinked.executeQuery();
        while ( results.next() )
        {
            peregrine_ticket_num  = results.getString ( 3 );
            event_list            = new StringBuilder();
            num_alarmpoint_events = 0;

            ps_search_links.setString ( 1, peregrine_ticket_num );
            results_alarmpoint = ps_search_links.executeQuery();

            while ( results_alarmpoint.next() )
            {
                event_list.append ( results_alarmpoint.getLong ( 1 ) );
                event_list.append ( ", " );

                num_alarmpoint_events++;
            }

            events = event_list.toString();

            ps_search_update.setInt    ( 1, num_alarmpoint_events );
            if ( events.length() > 1 )
            {
                ps_search_update.setString ( 2, events.substring ( 0, events.length()-2 ) );
                events = events.substring ( 0, events.length()-2 );
            }
            else
            {
                ps_search_update.setString ( 2, events );
            }
            ps_search_update.setString ( 3, results.getString ( 1 ) );
            ps_search_update.setString ( 4, results.getString ( 2 ) );
            ps_search_update.executeUpdate();

            System.out.println ( "Num alarmpoint events linked for ( message_number, anno_text_id ) = ( " + results.getString ( 1 ) + ", " + results.getString ( 2 ) + " ): " + num_alarmpoint_events );
            System.out.println ( "\tEvent list: " + events );
        }
    }
};
