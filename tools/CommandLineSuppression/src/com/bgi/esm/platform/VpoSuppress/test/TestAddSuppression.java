package com.bgi.esm.platform.VpoSuppress.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.SuppressionRecord;
import com.bgi.esm.platform.VpoSuppress.TestHarness;
import com.bgi.esm.platform.VpoSuppress.test.CommonTestCase;

public class TestAddSuppression extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestAddSuppression.class );

    public TestAddSuppression ( String param )
    {
        super ( param );
    }

    public void testCreateCommand() throws IOException
    {
        String command = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, null, getDefaultEndDate(), default_creator, default_email_address, 0 );

        _log.info ( "Default add command: " + command );
    }

    public void testExecuteAdd_DefaultCommand() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_DefaultCommand()" );

        Date end_date        = getDefaultEndDate();
        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, null, end_date, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        assertNotNull ( output );

        _log.info ( "Expected  end date: " + end_date.toString() );

        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( "Error creating suppression", output_string );
        int suppress_id      = Integer.parseInt ( output_string );
        SuppressionRecord sr = new SuppressionRecord ( suppress_id );

        _log.info ( "Retrieved end time: " + sr.getEndTime() );

        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date, sr.getEndDate() ) );
    }

    public void testExecuteAdd_OneHourSuppression() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_OneHourSuppression()" );

        Date end_date        = createDateDelta ( 0, 0, 0, 1, 0, 0, 0 );

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, null, end_date, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        assertNotNull ( output );

        _log.info ( "Expected  end date: " + end_date.toString() );

        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( "Error creating suppression", output_string );
        int suppress_id      = Integer.parseInt ( output_string );
        SuppressionRecord sr = new SuppressionRecord ( suppress_id );

        _log.info ( "Retrieved end time: " + sr.getEndTime() );

        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date, sr.getEndDate() ) );
    }

    public void testExecuteAdd_SpecifiedStartTimeDefaultEndTime() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_SpecifiedStartTimeDefaultEndTime()" );

        Calendar calendar1   = Calendar.getInstance();
        Date start_date      = createDateDelta ( 0, 0, 1, 0, 0, 0, 0 );
        calendar1.setTime ( start_date );
        calendar1.set ( Calendar.HOUR_OF_DAY, 23 );
        calendar1.set ( Calendar.MINUTE,      0  );
        calendar1.set ( Calendar.SECOND,      0  );
        calendar1.set ( Calendar.MILLISECOND, 0  );
        start_date = calendar1.getTime();

        Calendar calendar2   = Calendar.getInstance();
        calendar2.setTime ( start_date );
        calendar2.set ( Calendar.HOUR_OF_DAY, 23 );
        calendar2.set ( Calendar.MINUTE,      0  );
        calendar2.set ( Calendar.SECOND,      0  );
        calendar2.set ( Calendar.MILLISECOND, 0  );
        calendar2.add ( Calendar.HOUR_OF_DAY, 4 );
        Date end_date        = calendar2.getTime();

        _log.info ( "Expected start date: " + start_date.toString() );
        _log.info ( "Expected end date:   " + end_date.toString() );

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, start_date, null, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        assertNotNull ( output );

        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( "Error creating suppression", output_string );
        int suppress_id      = Integer.parseInt ( output_string );
        SuppressionRecord sr = new SuppressionRecord ( suppress_id );

        _log.info ( "Retrieved end time: " + sr.getEndTime() );

        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date, sr.getEndDate() ) );
    }

    /**
     *  This was added to catch a parsing error in Perl when the start hour is a single digit.
     */
    public void testExecuteAdd_SpecifiedStartDefaultEndTimePassesNoon() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_SpecifiedStartDefaultEndTimePassesNoon()" );

        Calendar calendar1   = Calendar.getInstance();
        Date start_date      = createDateDelta ( 0, 0, 1, 0, 0, 0, 0 );
        calendar1.setTime ( start_date );
        calendar1.set ( Calendar.HOUR_OF_DAY, 11 );
        calendar1.set ( Calendar.MINUTE,      0  );
        calendar1.set ( Calendar.SECOND,      0  );
        calendar1.set ( Calendar.MILLISECOND, 0  );
        start_date = calendar1.getTime();

        Calendar calendar2   = Calendar.getInstance();
        calendar2.setTime ( start_date );
        calendar2.set ( Calendar.HOUR_OF_DAY, 11 );
        calendar2.set ( Calendar.MINUTE,      0  );
        calendar2.set ( Calendar.SECOND,      0  );
        calendar2.set ( Calendar.MILLISECOND, 0  );
        calendar2.add ( Calendar.HOUR_OF_DAY, 4 );
        Date end_date        = calendar2.getTime();

        _log.info ( "Expected start date: " + start_date.toString() );
        _log.info ( "Expected end date:   " + end_date.toString() );

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, start_date, null, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        assertNotNull ( output );

        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( "Error creating suppression", output_string );
        int suppress_id      = Integer.parseInt ( output_string );
        SuppressionRecord sr = new SuppressionRecord ( suppress_id );

        _log.info ( "Retrieved end time: " + sr.getEndTime() );

        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date, sr.getEndDate() ) );
    }


    /**
     *  This was added to catch a parsing error in Perl that happened when the default suppression end time 
     *  passed midnight, the end date of the suppression was the same date as the start date.
     */
    public void testExecuteAdd_SpecifiedStartDefaultEndTimePassesMidnight() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_SpecifiedStartDefaultEndTimePassesMidnight()" );

        Calendar calendar1   = Calendar.getInstance();
        Date start_date      = createDateDelta ( 0, 0, 1, 0, 0, 0, 0 );
        calendar1.setTime ( start_date );
        calendar1.set ( Calendar.HOUR_OF_DAY, 23 );
        calendar1.set ( Calendar.MINUTE,      0  );
        calendar1.set ( Calendar.SECOND,      0  );
        calendar1.set ( Calendar.MILLISECOND, 0  );
        start_date = calendar1.getTime();

        Calendar calendar2   = Calendar.getInstance();
        calendar2.setTime ( start_date );
        calendar2.set ( Calendar.HOUR_OF_DAY, 23 );
        calendar2.set ( Calendar.MINUTE,      0  );
        calendar2.set ( Calendar.SECOND,      0  );
        calendar2.set ( Calendar.MILLISECOND, 0  );
        calendar2.add ( Calendar.HOUR_OF_DAY, 4 );
        Date end_date        = calendar2.getTime();

        _log.info ( "Expected start date: " + start_date.toString() );
        _log.info ( "Expected end date:   " + end_date.toString() );

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, start_date, null, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        assertNotNull ( output );

        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( "Error creating suppression", output_string );
        int suppress_id      = Integer.parseInt ( output_string );
        SuppressionRecord sr = new SuppressionRecord ( suppress_id );

        _log.info ( "Retrieved end time: " + sr.getEndTime() );

        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date, sr.getEndDate() ) );
    }

    public void testExecuteAdd_DefaultStartDate_InvalidEndDate() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_DefaultStartDate_InvalidEndDate()" );

        Date start_date      = createDateDelta ( 0, 0,  1, 0, 0, 0, 0 );
        Date end_date        = createDateDelta ( 0, 0, -1, 0, 0, 0, 0 );

        _log.info ( "Expected start date: " + start_date.toString() );
        _log.info ( "Expected end date:   " + end_date.toString() );

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, start_date, end_date, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( output_string );
        assertEquals ( "Suppression record created with invalid end date", 0, output_string.length() );
    }

    public void testExecuteAdd_SpecifiedStartDate_InvalidEndDate() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_SpecifiedStartDate_InvalidEndDate()" );

        Calendar calendar   = Calendar.getInstance();
        Date start_date     = createDateDelta ( 0, 0, 1, 0, 0, 0, 0 );
        calendar.setTime ( start_date );
        calendar.set ( Calendar.HOUR_OF_DAY, 23 );
        calendar.set ( Calendar.MINUTE,      0  );
        calendar.set ( Calendar.SECOND,      0  );
        calendar.set ( Calendar.MILLISECOND, 0  );
        start_date = new Date ( calendar.getTime().getTime() );

        calendar.add ( Calendar.DATE, -2 );
        Date end_date       = calendar.getTime();

        String command       = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, start_date, end_date, default_creator, default_email_address, 0 );

        Properties output    = TestHarness.LogCommand ( command );
        String output_string = output.getProperty ( "stdout" );
        assertNotNull ( output_string );
        assertEquals ( "Suppression record created with invalid end date", 0, output_string.length() );
    }
};
