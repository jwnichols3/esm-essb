package com.bgi.esm.monitoring.portlets.Suppressions.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.suppression.CommonTestCase;
import org.apache.log4j.Logger;

/**
 * Test adding a suppression through RMI
 * 
 * @author Dennis Lin (linden)
 */
public class TestRMIClient extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestRMIClient.class );
    private BackEndFacade bef = new BackEndFacade();

    public TestRMIClient ( String param )
    {
        super ( param );
    }


	public void testAdd() throws BackEndFailure
    {
        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );

        assertNotNull ( "Could not add test rule into database", result );
        assertTrue ( "Unable to obtain a valid suppression ID", result.getSuppressId() > 0 );
	}

    public void testAddMultiLineDescription() throws BackEndFailure
    {
        SuppressionRule rule   = createSuppressionRule();
        rule.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );

        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );

        assertNotNull ( "Could not add test rule into database", result );
        assertTrue ( "Unable to obtain a valid suppression ID", result.getSuppressId() > 0 );
    }

    public void testAddSuppressionAndRetrieve() throws BackEndFailure
    {
        SuppressionRule rule     = createSuppressionRule();
        rule.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );
        SuppressionRule result   = bef.addOrUpdateSuppressionRule ( rule );

        _log.info ( "Retrieving suppression ID #" + result.getSuppressId() );

        SuppressionRule retrieve = bef.selectSuppressionRuleBySuppressId ( result.getSuppressId() );

        assertNotNull ( "Could not retrieve suppression by suppress_id for suppression #" + result.getSuppressId(), retrieve );
    }

    public void testAddSuppressionRetrieveUpdate() throws BackEndFailure
    {
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
        Calendar start_time  = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));
        Calendar end_time    = Calendar.getInstance(TimeZone.getTimeZone ( "GMT" ));

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

    public void testSelectAllRules() throws BackEndFailure
    {
        List <SuppressionRule> records = bef.getAllSuppressionRules ();

        assertNotNull ( "Could not retrieve records", records );
    }

    public void testSelectAllRulesPaginate() throws BackEndFailure
    {
        int num_records_per_page = 20;
        int page_num             = 1;

        List <SuppressionRule> records = bef.getAllSuppressionRulesPaginate ( num_records_per_page, page_num );

        assertNotNull ( "Could not retrieve records", records );
    }
}

