package com.bgi.esm.monitoring.platform.test.datamap;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import com.bgi.esm.monitoring.platform.test.datamap.CommonTestCase;
import org.apache.log4j.Logger;

/**
 * Test modifying a data map rule through RMI
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestDataMap extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDataMap.class );
    private BackEndFacade bef = new BackEndFacade();

    public TestDataMap ( String param )
    {
        super ( param );
    }


	public void testAdd() throws BackEndFailure
    {
        _log.info ( "**************************************** testAdd()" );

        DataMapRule rule = createDataMapRule();
	    DataMapRule add  = bef.addOrUpdateDataMapRule(rule);

        assertNotNull ( "Could not add data map rule", add );
	}

    public void testAddDataMapAndRetrieve() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddDataMapAndRetrieve()" );

        DataMapRule rule     = createDataMapRule();
	    DataMapRule add      = bef.addOrUpdateDataMapRule(rule);

        String group         = rule.getGroup();

        DataMapRule retrieve = bef.selectDataMapRule ( group );

        assertNotNull ( "Could not find group that was just inserted: " + group, retrieve );
    }

    public void testAddDataMapRetrieveUpdate() throws BackEndFailure
    {
        _log.info ( "**************************************** testAddDataMapRetrieveUpdate()" );

        DataMapRule rule = createDataMapRule();
	    DataMapRule add  = bef.addOrUpdateDataMapRule(rule);

        String group     = rule.getGroup();

        assertNotNull ( "Could not add data map rule", add );

        add.setMethod ( METHOD_ALARMPOINT_ONLY );
        DataMapRule update = bef.addOrUpdateDataMapRule ( add );

        DataMapRule retrieve = bef.selectDataMapRule ( group );

        assertNotNull ( "Could not find group that was just inserted: " + group, retrieve );

        retrieve.setPeregrineCategory ( "Updated-Group" );

        DataMapRule updated  = bef.addOrUpdateDataMapRule  ( retrieve );

        assertNotNull ( "Could not update group that was just created:" + retrieve.getGroup() );

        assertEquals ( "Could not properly update group that just created: " + update.getGroup(), "Updated-Group", updated.getPeregrineCategory() );
    }
}
