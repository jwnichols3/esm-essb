package tools;

import java.sql.*;

public class ConvertToMySQL
{
    public static void main ( String args[] ) throws Exception
    {
        Class.forName ( "net.sourceforge.jtds.jdbc.Driver" );
        Connection con_source = DriverManager.getConnection ( "jdbc:jtds:sqlserver://calntalp202:1433/enterprise_event_bus", "monitoring", "HYPertext01" );
        Statement stmt = con_source.createStatement ();
        ResultSet results = stmt.executeQuery (  "SELECT * FROM events_by_group" );

        Class.forName ( "com.mysql.jdbc.Driver" );
        Connection con_dest = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/vpo_stress_test", "root", "apogee" );
        PreparedStatement insert = con_dest.prepareStatement ( "INSERT INTO events_by_group ( row_id, responder_id, bgi_group, message_id, time_stamp, source_node, source_node_type, application, object, severity, message_key, message_text, instruction_text ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

        while ( results.next() )
        {
            insert.setString    (  1, results.getString ( "row_id" ) );
            insert.setString    (  2, results.getString ( "responder_id" ) );
            insert.setString    (  3, results.getString ( "bgi_group" ) );
            insert.setString    (  4, results.getString ( "message_id" ) );
            insert.setTimestamp (  5, results.getTimestamp ( "time_stamp" ) );
            insert.setString    (  6, results.getString ( "source_node" ) );
            insert.setString    (  7, results.getString ( "source_node_type" ) );
            insert.setString    (  8, results.getString ( "application" ) );
            insert.setString    (  9, results.getString ( "object" ) );
            insert.setString    ( 10, results.getString ( "severity" ) );
            insert.setString    ( 11, results.getString ( "message_key" ) );
            insert.setString    ( 12, results.getString ( "message_text" ) );
            insert.setString    ( 13, results.getString ( "instruction_text" ) );

            insert.execute();
        }
    }
}
