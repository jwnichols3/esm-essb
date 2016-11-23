import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

public class RetrievePeopleInfo
{
    public static void main ( String args[] ) throws SQLException, ClassNotFoundException, IOException
    {
        int port = 14110;
        Properties props = new Properties();
        props.put("user", "shareit_read");
        props.put("password",  "relay8");
        
        Vector <String> values = null;
                
        Class.forName ( "com.sybase.jdbc2.jdbc.SybDriver" );
        String db_connection_string = "jdbc:sybase:Tds:rdcuxsrv038:"+port+"/shareit_db";
        Connection con              = DriverManager.getConnection (db_connection_string,  props);
        Statement stmt              = null;
        //CallableStatement cstmt1    = con.prepareCall("exec p_get_app_people_info");
        CallableStatement cstmt1    = con.prepareCall("exec p_get_people");
        ResultSet results           = cstmt1.executeQuery();

        queryDump ( results, "dump.txt" );
    }

    private static void queryDump ( ResultSet results, String filename ) throws SQLException, IOException
    {
        ResultSetMetaData md = results.getMetaData();

        int record_counter = 0;
        int counter = 0;
        int num_columns = md.getColumnCount();
        String temp_string = null;

        FileOutputStream outfile = new FileOutputStream(filename);
        for (counter = 1; counter <= num_columns; counter++)
        {
            outfile.write(md.getColumnName(counter).getBytes());
            // if ( counter+1 != num_columns )
            if (true)
            {
                outfile.write("|".getBytes());
            }
        }
        outfile.write("\n".getBytes());
        while (results.next())
        {
            if (record_counter % 500 == 0)
            {
                System.out.println("\tProcessing record #" + record_counter);
            }
            for (counter = 1; counter <= num_columns; counter++)
            {
                temp_string = results.getString(counter);
                temp_string = (null != temp_string) ? temp_string : "";

                outfile.write(temp_string.getBytes());
                // if ( counter+1 != num_columns )
                if (true)
                {
                    outfile.write("|".getBytes());
                }
            }
            outfile.write("\n".getBytes());
            record_counter++;
        }
        System.out.println("num records: " + record_counter);
        outfile.close();
    }
}
