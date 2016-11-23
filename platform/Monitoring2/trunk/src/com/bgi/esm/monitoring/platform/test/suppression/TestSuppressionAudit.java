package com.bgi.esm.monitoring.platform.test.suppression;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.suppression.CommonTestCase;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

/**
 * Test adding a suppression through RMI
 * 
 * @author Dennis Lin (linden)
 */
public class TestSuppressionAudit extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestSuppressionAudit.class );
    private BackEndFacade bef        = new BackEndFacade();

    public TestSuppressionAudit ( String param )
    {
        super ( param );
    }

	public void testAddAndUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndUpdate()" );

        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );
        assertNotNull ( "Could not add test rule into database", result );

        Collection list1 = bef.getSuppressionRuleAuditVersions ( result.getSuppressId() );
        _log.info ( "Created suppression ID #" + result.getSuppressId() + " with key " + result.getRuleKey() );
        _log.info ( "Original suppression ID = " + rule.getSuppressId() );
        _log.info ( "Number of audit objects after creation: " + list1.size() );
        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + result.getSuppressId(), list1.size() == 1 );

        result.setDescription ( "Updated description" );
        SuppressionRule audit  = bef.addOrUpdateSuppressionRule ( result );
        _log.info ( "Attempting to update suppression ID #" + audit.getSuppressId() );
        assertNotNull ( "Could not update suppression #" + result.getSuppressId(), audit );

        Collection list2 = bef.getSuppressionRuleAuditVersions ( audit.getSuppressId() );
        _log.info ( "Retrieving information on audit for suppression ID #" + audit.getSuppressId() );
        _log.info ( "Number of audit objects after update: " + list2.size() );

        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + audit.getSuppressId(), list2.size() == 2 );
	}

	public void testAddAndUpdateMultipleLineDescription() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndUpdateMultipleLineDescription()" );

        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );
        assertNotNull ( "Could not add test rule into database", result );

        Collection list1 = bef.getSuppressionRuleAuditVersions ( result.getSuppressId() );
        _log.info ( "Created suppression ID #" + result.getSuppressId() + " with key " + result.getRuleKey() );
        _log.info ( "Original suppression ID = " + rule.getSuppressId() );
        _log.info ( "Number of audit objects after creation: " + list1.size() );
        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + result.getSuppressId(), list1.size() == 1 );

        result.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );
        SuppressionRule audit  = bef.addOrUpdateSuppressionRule ( result );
        _log.info ( "Attempting to update suppression ID #" + audit.getSuppressId() );
        assertNotNull ( "Could not update suppression #" + result.getSuppressId(), audit );

        Collection list2 = bef.getSuppressionRuleAuditVersions ( audit.getSuppressId() );
        _log.info ( "Retrieving information on audit for suppression ID #" + audit.getSuppressId() );
        _log.info ( "Number of audit objects after update: " + list2.size() );

        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + audit.getSuppressId(), list2.size() == 2 );
	}

    public void testAddAndDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddAndDelete()" );

        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );
        assertNotNull ( "Could not add test rule into database", result );
       
        Collection list1 = bef.getSuppressionRuleAuditVersions ( result.getSuppressId() );
        _log.info ( "Created suppression ID #" + result.getSuppressId() + " with key " + result.getRuleKey() );
        _log.info ( "Original suppression ID = " + rule.getSuppressId() );
        _log.info ( "Number of audit objects after creation: " + list1.size() );
        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + result.getSuppressId(), list1.size() == 1 );

        assertTrue ( "Could not delete suppression #" + result.getSuppressId(), bef.deleteSuppressionRule ( result ) );

        Collection list2 = bef.getSuppressionRuleAuditVersions ( result.getSuppressId() );

        _log.info ( "Retrieving information on audit for suppression ID #" + result.getSuppressId() );
        _log.info ( "Number of audit objects after delete: " + list2.size() );

        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + result.getSuppressId(), list2.size() == 2 );
    }

    public void testAddUpdateDelete() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddUpdateDelete()" );

        SuppressionRule rule   = createSuppressionRule();
        SuppressionRule result = bef.addOrUpdateSuppressionRule ( rule );
        assertNotNull ( "Could not add test rule into database", result );

        Collection list1 = bef.getSuppressionRuleAuditVersions ( result.getSuppressId() );
        _log.info ( "Created suppression ID #" + result.getSuppressId() + " with key " + result.getRuleKey() );
        _log.info ( "Original suppression ID = " + rule.getSuppressId() );
        _log.info ( "Number of audit objects after creation: " + list1.size() );
        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + result.getSuppressId(), list1.size() == 1 );

        result.setDescription ( BaseClientScript.TEST_DESCRIPTION_MULTI_LINE );
        SuppressionRule audit  = bef.addOrUpdateSuppressionRule ( result );
        _log.info ( "Attempting to update suppression ID #" + audit.getSuppressId() );
        assertNotNull ( "Could not update suppression #" + result.getSuppressId(), audit );

        Collection list2 = bef.getSuppressionRuleAuditVersions ( audit.getSuppressId() );
        _log.info ( "Retrieving information on audit for suppression ID #" + audit.getSuppressId() );
        _log.info ( "Number of audit objects after update: " + list2.size() );

        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + audit.getSuppressId(), list2.size() == 2 );

        assertTrue ( "Could not delete suppression #" + result.getSuppressId(), bef.deleteSuppressionRule ( audit ) );

        Collection list3 = bef.getSuppressionRuleAuditVersions ( audit.getSuppressId() );

        _log.info ( "Retrieving information on audit for suppression ID #" + audit.getSuppressId() );
        _log.info ( "Number of audit objects after delete: " + list3.size() );

        assertTrue ( "Auditing functionality for suppressions is not working correctly - suppression ID #" + audit.getSuppressId(), list3.size() == 3 );
    }
}
