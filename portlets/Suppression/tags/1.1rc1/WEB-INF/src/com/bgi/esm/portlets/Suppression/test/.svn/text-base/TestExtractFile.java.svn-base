package com.bgi.esm.portlets.Suppression.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.bgi.esm.portlets.Suppression.ExtractFile;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.test.CommonTestCase;

public class TestExtractFile extends CommonTestCase
{
    @SuppressWarnings("unused")	final private static long test_timestamp = 1144774800000L;

    public TestExtractFile ( String param )
    {
        super ( param );
    }

    public void testCreateObject() throws Exception
    {
        ExtractFile ef = new ExtractFile();

        assertNotNull ( ef );

        ef.toExtractFile ( "c:\\test\\extract.test" );
    }

    public void testFileFormat() throws Exception
    {
        Properties common_props = readCommonProperties();

        String filename = common_props.getProperty ( "suppression.suppressions.extract_file" );

        FileInputStream infile = new FileInputStream ( filename );
        byte file_contents[]   = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();

        StringTokenizer tokens = new StringTokenizer ( new String ( file_contents ), "\n" );
        String current_line    = null;

        Vector <String> start_time = new Vector <String> ();
        Vector <String> end_time   = new Vector <String> ();
        Vector <String> group_nm   = new Vector <String> ();
        Vector <String> node_nm    = new Vector <String> ();
        Vector <String> instance   = new Vector <String> ();
        Vector <String> message    = new Vector <String> ();
        int line_number            = 1;
        String splits[]            = null;

        while ( tokens.hasMoreTokens() )
        {
            current_line = tokens.nextToken();

            splits = current_line.split ( "=-=" );

            if ( splits.length != 6 )
            {
                throw new IOException ( "Extract file is in an invalid format at line #" + line_number + ", num_tokens=" + splits.length + ", line=" + current_line );
            }
            
            start_time.add ( splits[0] );
            end_time.add   ( splits[1] );
            group_nm.add   ( splits[2] );
            node_nm.add    ( splits[3] );
            instance.add   ( splits[4] );
            message.add    ( splits[5] );

            line_number++;
        }
    }

    /**
     * Tests for bug identified by Database Team and John Nichols on May 19, 2006
     *
     * The "message" field in the suppression can not contain any new line characters.
     */
    public void testForNewlinesInMessage() throws SQLException, ClassNotFoundException, IOException
    {
        String extract_filename = "c:/test/ExtractFile_newlines.test";
        ExtractFile ef          = new ExtractFile();
        long query_time         = ef.getQueryTime();
        ef.toExtractFile ( extract_filename );

        FileInputStream infile  = new FileInputStream ( extract_filename );
        byte file_contents[]    = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();

        StringTokenizer tokenizer = new StringTokenizer ( new String ( file_contents ), "\n" );

        ResultSet results = Toolkit.getCurrentAtTime ( query_time );
        int num_records   = 0;
        while ( results.next() )
        {
            num_records++;
        }

        assertTrue ( tokenizer.countTokens() == num_records );
    }

    /**
     * To test for bug identified by Unix Team on May 18, 2006
     *
     * The times in the extract file need to be UTC times.  Make sure that Java does not automatically convert the times taken from the database
     * into local times.
     */
    public void testForUTCTimes() throws SQLException, ClassNotFoundException, IOException
    {
        //  Create the extract file
        String extract_filename = "c:/test/ExtractFile.test";
        String extract_errors   = "c:/test/ExtractErrors.out";
        ExtractFile ef          = new ExtractFile();
        long query_time         = ef.getQueryTime();
        ef.toExtractFile ( extract_filename );

        //  Retrieve the same records from the database
        ResultSet results              = Toolkit.getCurrentAtTime ( query_time );
        Vector <Long> db_start_times   = new Vector <Long> ();
        Vector <Long> db_end_times     = new Vector <Long> ();
        Vector <Long> file_start_times = new Vector <Long> ();
        Vector <Long> file_end_times   = new Vector <Long> ();
        Vector <Integer> suppress_ids  = new Vector <Integer> ();
        Timestamp timestamp_start      = null;
        Timestamp timestamp_end        = null;

        while ( results.next() )
        {
            timestamp_start = results.getTimestamp ( 5 );
            timestamp_end   = results.getTimestamp ( 6 );

            suppress_ids.add ( new Integer ( results.getInt ( 1 ) ) );
            db_start_times.add ( new Long ( timestamp_start.getTime() / 1000 ) );
            db_end_times.add   ( new Long ( timestamp_end.getTime() / 1000   ) );
        }

        //  Parse the test file
        FileInputStream infile = new FileInputStream ( extract_filename );
        byte file_contents[]   = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();

        StringTokenizer lines  = new StringTokenizer ( new String ( file_contents ), "\n" );
        while ( lines.hasMoreTokens() )
        {
            String tokens[] = lines.nextToken().split ( ExtractFile.DELIMITER );

            // first token is start time in UTC / 1000
            file_start_times.add ( new Long ( tokens[0] ) );
            // second token is end time in UTC / 1000
            file_end_times.add   ( new Long ( tokens[1] ) );
        }
        
        //  Compare the timestamps
        Vector <Integer> mismatched_record_indices = new Vector <Integer> ();
        FileOutputStream outfile = new FileOutputStream ( extract_errors );
	        for ( int counter = 0; counter < db_start_times.size(); counter++ )
	        {
	            Long db_start   = db_start_times.elementAt   ( counter );
	            Long db_end     = db_end_times.elementAt     ( counter );
	            Long file_start = file_start_times.elementAt ( counter );
	            Long file_end   = file_end_times.elementAt   ( counter );
	
	            if (( !db_start.equals ( file_start )) || ( !db_end.equals ( file_end )))
	            {
	                mismatched_record_indices.add ( new Integer ( counter ) );
	                outfile.write ( suppress_ids.elementAt(counter).toString().getBytes() );
	                outfile.write ( ",".getBytes() );
	                outfile.write ( db_start.toString().getBytes() );
	                outfile.write ( ",".getBytes() );
	                outfile.write ( db_end.toString().getBytes() );
	                outfile.write ( ",".getBytes() );
	                outfile.write ( file_start.toString().getBytes() );
	                outfile.write ( ",".getBytes() );
	                outfile.write ( file_end.toString().getBytes() );
	                outfile.write ( "\n".getBytes() );
	            }
	        }
        outfile.close();        

        assertTrue ( mismatched_record_indices.size() == 0 );
    }
}
