package com.bgi.esm.monitoring.platform.test.suppression;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.suppression.CommonTestCase;
import org.apache.log4j.Logger;

/**
 * Test adding a suppression through RMI
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestSuppression extends CommonTestCase
{
    final private static Logger _log   = Logger.getLogger ( TestSuppression.class );
    final private SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    private int getTimezoneOffset()
    {
        return TimeZone.getTimeZone( "America/Los_Angeles" ).getRawOffset();
    }

    public TestSuppression ( String param )
    {
        super ( param );
    }

    public void testSuppressionTimeframe_StartingNow() throws InterruptedException
    {
        _log.info ( "**************************************** testSuppressionTimeframe_StartingNow()" );

        SuppressionRule rule = createSuppressionRule();

        Thread.currentThread().sleep ( 5000 );

        _log.info ( "Start time: " + sdf.format ( rule.getStartTime().getTime() ) );
        _log.info ( "End time:   " + sdf.format ( rule.getEndTime().getTime()   ) );

        assertTrue ( "Suppression rule created with default time parameters is not immediately active", rule.isStarted(getTimezoneOffset()) );
    }

    public void testSuppressionTimeframe_StartingInFuture()
    {
        _log.info ( "**************************************** testSuppressionTimeframe_StartingInFuture()" );

        Calendar start_time  = Calendar.getInstance();
        Calendar end_time    = Calendar.getInstance();
        long current_time    = System.currentTimeMillis();

        start_time.setTimeInMillis ( current_time + 3600 * 5 * 1000 );

        SuppressionRule rule = createSuppressionRule();
        rule.setStartTime ( start_time );

        assertFalse ( "Suppression starting in the future is immediately active", rule.isStarted(getTimezoneOffset()) );
    }

	public void testAdd() throws BackEndFailure
    {
        _log.info ( "**************************************** testAdd()" );

        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );

        assertNotNull ( "Could not add test rule into database", result );
        assertTrue ( "Unable to obtain a valid suppression ID", result.getSuppressId() > 0 );
	}

    public void testAddMultiLineDescription() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddMultiLineDescription()" );

        SuppressionRule rule   = createSuppressionRule();
        rule.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );

        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );

        assertNotNull ( "Could not add test rule into database", result );
        assertTrue ( "Unable to obtain a valid suppression ID", result.getSuppressId() > 0 );
    }

    public void testAddSuppressionAndRetrieve() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddSuppressionAndRetrieve()" );

        SuppressionRule rule     = createSuppressionRule();
        rule.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );
        SuppressionRule result   = bef.addOrUpdateSuppressionRule ( rule );

        _log.info ( "Retrieving suppression ID #" + result.getSuppressId() );

        SuppressionRule retrieve = bef.selectSuppressionRuleBySuppressId ( result.getSuppressId() );

        assertNotNull ( "Could not retrieve suppression by suppress_id for suppression #" + result.getSuppressId(), retrieve );
    }

    public void testAddSuppressionRetrieveUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddSuppressionRetrieveUpdate()" );

        SuppressionRule rule     = createSuppressionRule();
        rule.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );
        SuppressionRule result   = bef.addOrUpdateSuppressionRule ( rule );

        _log.info ( "Retrieving suppression ID #" + result.getSuppressId() );

        SuppressionRule retrieve = bef.selectSuppressionRuleBySuppressId ( result.getSuppressId() );

        assertNotNull ( "Could not retrieve suppression by suppress_id for suppression #" + result.getSuppressId(), retrieve );

        retrieve.setDescription ( "Updated!" );

        SuppressionRule update   = bef.addOrUpdateSuppressionRule ( retrieve );
        assertEquals ( "Could not update suppression #" + retrieve.getSuppressId(), "Updated!", update.getDescription() );
    }

    public void testAddSuppressionInFutureDefaultLength() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddSuppressionInFutureDefaultLength()" );

        Calendar start_time  = Calendar.getInstance();
        Calendar end_time    = Calendar.getInstance();

        //  Set the start time to be the start of the 2nd hour from now.
        start_time.add ( Calendar.HOUR,   2 );
        start_time.set ( Calendar.MINUTE, 0 );

        //  Set the start time to be the start of the 2nd hour from now.
        end_time.setTimeInMillis ( start_time.getTime().getTime() );
        end_time.add ( Calendar.HOUR, 4 );

        SuppressionRule rule = createSuppressionRule ();

        //  Add this rule
        bef.addOrUpdateSuppressionRule ( rule );
    }

    public void testSendDrainMessage() throws BackEndFailure
    {
        _log.info ( "**************************************** testSendDrainMessage()" );

        assertTrue ( "Could not send test drain message", sendDrainMessage() );
    }

    public void testSendDrainMessageSpecified() throws BackEndFailure
    {
        _log.info ( "**************************************** testSendDrainMessageSpecified()" );

        SuppressionDrainMessage message = new SuppressionDrainMessage ();
        message.setMessageString ( "suppression drain message from unit tests" );

        assertTrue ( "Could not send test drain message", sendDrainMessage ( message ) );
    }

    public void testActiveSuppression()
    {
        _log.info ( "**************************************** testActiveSuppression()" );

        Calendar start_time  = Calendar.getInstance();
        Calendar end_time    = Calendar.getInstance();

        start_time.add ( Calendar.HOUR, -1 );
        end_time.add   ( Calendar.HOUR,  1 );

        SuppressionRule rule = createSuppressionRule();
        rule.setStartTime ( start_time );
        rule.setEndTime   ( end_time   );

        assertTrue ( "Suppression rule should be active", rule.isActive(getTimezoneOffset()) );
    }

    public void testInactiveSuppression()
    {
        _log.info ( "**************************************** testInactiveSuppression()" );

        Calendar start_time  = Calendar.getInstance();
        Calendar end_time    = Calendar.getInstance();

        start_time.add ( Calendar.HOUR, -8 );
        end_time.add   ( Calendar.HOUR, -4 );

        SuppressionRule rule = createSuppressionRule();
        rule.setStartTime ( start_time );
        rule.setEndTime   ( end_time   );

        assertFalse ( "Suppression should not be active", rule.isActive(getTimezoneOffset()) );
    }


    public void testSuppressionNotification() throws BackEndFailure, InterruptedException
    {
        _log.info ( "**************************************** testSuppressionNotification()" );

        Calendar end_time    = Calendar.getInstance();
        end_time.add ( Calendar.MINUTE, 5 );

        SuppressionRule rule = createSuppressionRule();
        rule.setNotificationFlag ( true );
        rule.setNotifyMinutes    ( 5 );

        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );

        assertNotNull ( "Could not add test rule into database", result );
        assertTrue ( "Unable to obtain a valid suppression ID", result.getSuppressId() > 0 );
        assertTrue ( "Could not send test drain message", sendDrainMessage() );

        _log.info ( "Testing suppression notification for suppression ID #" + result.getSuppressId() );

        Thread.currentThread().sleep ( 7500 );

        SuppressionRule retrieve = bef.selectSuppressionRuleBySuppressId ( result.getSuppressId() );

        assertTrue ( "Suppression notification was not sent", retrieve.getIsNotified() );
    }

    public void testSearchAgainstNullFilters()
    {
        _log.info ( "**************************************** testSearchAgainstNullFilters()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }

    public void testSearchAgainstMatchingUsername()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingUsername()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, BaseClientScript.TEST_USER, null, null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }

    public void testSearchAgainstInvalidUsername()
    {
        _log.info ( "**************************************** testSearchAgainstInvalidUsername()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, "blah", null, null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }

    public void testSearchAgainstMatchingDescription()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingDescription()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, BaseClientScript.TEST_DESCRIPTION, null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }


    public void testSearchAgainstInvalidDescription()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingDescription()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, "blah", null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }

    public void testSearchAgainstMatchingApplicationName()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingApplicationName()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, BaseClientScript.TEST_APPLICATION, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }


    public void testSearchAgainstInvalidApplicationName()
    {
        _log.info ( "**************************************** testSearchAgainstInvalidApplicationName()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, "blah", null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }


    public void testSearchAgainstMatchingNodeName()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingNodeName()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, BaseClientScript.TEST_NODE, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }


    public void testSearchAgainstInvalidNodeName()
    {
        _log.info ( "**************************************** testSearchAgainstInvalidNodeName()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, "blah", null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }

    public void testSearchAgainstMatchingDatabaseServer()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingDatabaseServer()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, BaseClientScript.TEST_DATASERVER, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }

    public void testSearchAgainstInvalidDatabaseServer()
    {
        _log.info ( "**************************************** testSearchAgainstInvalidDatabaseServer()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, "blah", 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }

    public void testSearchAgainstMatchingMessageText()
    {
        _log.info ( "**************************************** testSearchAgainstMatchingMessageText()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, null, 
                BaseClientScript.TEST_MESSAGE, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }

    public void testSearchAgainstInvalidMessageText()
    {
        _log.info ( "**************************************** testSearchAgainstInvalidMessageText()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, null, 
                "blah", null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 0 );
    }

    public void testSearchAgainstValidStartTime()
    {
        _log.info ( "**************************************** testSearchAgainstNullFilters()" );

        //  Create the sample list
        ArrayList <SuppressionRule> list = new ArrayList <SuppressionRule> ();
        list.add ( createSuppressionRule() );

        //  Search based on all null parameters
        List results = SuppressionRule.searchSuppressionRules ( list, null, null, null, null, null, 
                null, null, null, SuppressionRule.REMOVE_ON_REBOOT_DOES_NOT_MATTER, SuppressionRule.WAS_DELETED_DOES_NOT_MATTER );

        assertTrue ( "Search did not work", results.size() == 1 );
    }
}
