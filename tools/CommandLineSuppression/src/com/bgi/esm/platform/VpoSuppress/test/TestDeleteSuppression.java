package com.bgi.esm.platform.VpoSuppress.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.SuppressionRecord;
import com.bgi.esm.platform.VpoSuppress.TestHarness;
import com.bgi.esm.platform.VpoSuppress.test.CommonTestCase;

public class TestDeleteSuppression extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDeleteSuppression.class );

    public TestDeleteSuppression ( String param )
    {
        super ( param );
    }

    public void testCreateCommand() throws IOException
    {
        String command = TestHarness.createExecuteCommandDelete ( "0001", default_portal, default_creator );

        _log.info ( "Default add command: " + command );
    }


    public void testExecuteAdd_DefaultCommand_Delete() throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
        _log.info ( " ********** testExecuteAdd_DefaultCommand_Delete()" );

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

        String command_delete     = TestHarness.createExecuteCommandDelete ( Integer.toString ( suppress_id ), default_portal, default_creator );
        Properties output_delete  = TestHarness.LogCommand ( command_delete );
        assertNotNull ( output_delete );
        String output_string_del  = output_delete.getProperty ( "stdout" );

        SuppressionRecord sr_del  = new SuppressionRecord ( suppress_id );
        _log.info ( "Was suppression deleted? " + ( sr_del.isDeleted() ? "true" : "false" ) );
        assertTrue ( "Unable to delete suppression", sr_del.isDeleted() );
    }


    public void testCreateDefaultSuppression_ChangeToOneHourSuppression_Delete() throws IOException, InterruptedException, ClassNotFoundException, SQLException
    {
        _log.info ( "testCreateDefaultSuppression_ChangeToOneHourSuppression_Delete()" );

        Date end_date_add         = createDateDelta ( 0, 0, 0, 4, 0, 0, 0 );
        Date end_date_edit        = createDateDelta ( 0, 0, 0, 1, 0, 0, 0 );
        String command_add        = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, null, null, default_creator, default_email_address, 0 );
        Properties output_add     = TestHarness.LogCommand ( command_add );
        assertNotNull ( output_add );
        String output_string_add  = output_add.getProperty ( "stdout" );
        int suppress_id           = Integer.parseInt ( output_string_add );
        SuppressionRecord sr_add  = new SuppressionRecord ( suppress_id );

        _log.info  ( "Expected end date after add:  " + end_date_add.toString() );
        _log.info  ( "Retrieved end date after add: " + sr_add.getEndTime() );
        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date_add, sr_add.getEndDate() ) );

        String command_edit       = TestHarness.createExecuteCommandUpdate ( Integer.toString( suppress_id ), default_portal, default_application,
                default_node, default_instance, default_description, null, end_date_edit, default_creator );
        Properties output_edit    = TestHarness.LogCommand ( command_edit );
        assertNotNull ( output_edit );
        String output_string_edit = output_edit.getProperty ( "stdout" );
        SuppressionRecord sr_edit = new SuppressionRecord ( suppress_id );

        _log.info  ( "Expected end date after edit:  " + end_date_edit.toString() );
        _log.info  ( "Retrieved end date after edit: " + sr_edit.getEndTime() );
        assertTrue ( "Suppression times did not match", compareLocalDateToGMT ( end_date_edit, sr_edit.getEndDate() ) );

        String command_delete     = TestHarness.createExecuteCommandDelete ( Integer.toString ( suppress_id ), default_portal, default_creator );
        Properties output_delete  = TestHarness.LogCommand ( command_delete );
        assertNotNull ( output_delete );
        String output_string_del  = output_delete.getProperty ( "stdout" );

        SuppressionRecord sr_del  = new SuppressionRecord ( suppress_id );
        _log.info ( "Was suppression deleted? " + ( sr_del.isDeleted() ? "true" : "false" ) );
        assertTrue ( "Unable to delete suppression", sr_del.isDeleted() );
    }


    public void testCreateDefaultSuppression_ChangeApplication_Delete () throws IOException, InterruptedException, ClassNotFoundException, SQLException
    {
        _log.info ( "testCreateDefaultSuppression_ChangeToOneHourSuppression_Delete()" );

        String command_add        = TestHarness.createExecuteCommandAdd ( default_application, default_portal,
                default_node, default_instance, default_message_text, 
                default_description, null, null, default_creator, default_email_address, 0 );
        Properties output_add     = TestHarness.LogCommand ( command_add );
        assertNotNull ( output_add );
        String output_string_add  = output_add.getProperty ( "stdout" );
        int suppress_id           = Integer.parseInt ( output_string_add );
        SuppressionRecord sr_add  = new SuppressionRecord ( suppress_id );

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Suppression ID: " + suppress_id );
            _log.info ( "Start application:     " + default_application );
            _log.info ( "Retrieved application: " + sr_add.getApplication() );
        }
        assertEquals ( "Application name was not properly saved", default_application, sr_add.getApplication() );

        String new_application    = "TEST_APPNAME_CHANGE";
        String command_edit       = TestHarness.createExecuteCommandUpdate ( Integer.toString( suppress_id ), default_portal, new_application,
                default_node, default_instance, default_description, null, null, default_creator );
        Properties output_edit    = TestHarness.LogCommand ( command_edit );
        assertNotNull ( output_edit );
        String output_string_edit = output_edit.getProperty ( "stdout" );
        SuppressionRecord sr_edit = new SuppressionRecord ( suppress_id );

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "End application:       " + new_application );
            _log.info ( "Retrieved application: " + sr_edit.getApplication() );
        }
        assertEquals ( "Application name was not saved property when suppression was edited", new_application, sr_edit.getApplication() );

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Expected end time:  " + sr_add.getEndTime()  );
            _log.info ( "Retrieved end time: " + sr_edit.getEndTime() );
            _log.info ( "Expected end timestamp:  " + sr_add.getEndTimestamp()  );
            _log.info ( "Retrieved end timestamp: " + sr_edit.getEndTimestamp() );
        }
        assertTrue ( "End date was changed when suppression was updated", Math.abs ( sr_add.getEndTimestamp() - sr_edit.getEndTimestamp() ) < 60000 );

        String command_delete     = TestHarness.createExecuteCommandDelete ( Integer.toString ( suppress_id ), default_portal, default_creator );
        Properties output_delete  = TestHarness.LogCommand ( command_delete );
        assertNotNull ( output_delete );
        String output_string_del  = output_delete.getProperty ( "stdout" );

        SuppressionRecord sr_del  = new SuppressionRecord ( suppress_id );
        _log.info ( "Was suppression deleted? " + ( sr_del.isDeleted() ? "true" : "false" ) );
        assertTrue ( "Unable to delete suppression", sr_del.isDeleted() );
    }
};
