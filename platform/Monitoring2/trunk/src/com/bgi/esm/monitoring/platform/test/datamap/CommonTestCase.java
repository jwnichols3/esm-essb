package com.bgi.esm.monitoring.platform.test.datamap;

import java.util.Calendar;
import java.util.Date;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;
import junit.framework.TestCase;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class CommonTestCase extends TestCase
{
    final public static String METHOD_TICKET             = "ticket";
    final public static String METHOD_ALARMPOINT_ONLY    = "alarmpoint_only";
    final public static String TEST_ALARMPOINT_GROUP     = "test-alarmpoint-group";
    final public static String TEST_ALARMPOINT_SCRIPT    = "BGI VPO";
    final public static String TEST_PEREGRINE_CATEGORY[] = 
    {
        "DEFAULT_CAT",
        "DATABASE",
        "FACILITIES",
        "MESSAGING", 
        "NETWORK",
        "SECURITY",
        "SERVERS",
        "SOFTWARE",
        "STORAGE"
    };

    final public static String TEST_PEREGRINE_SUBCATEGORY[] = 
    {
        "ACCOUNT ADMINISTRATION",
        "BACKUPS",
        "CAPACITY EXPANSION",
        "DATA PROTECTION",
        "DATABASE ADMINISTRATION"
    };

    final public static String TEST_PEREGRINE_PRODUCT[] = 
    {
        "APPLICATION RELATED",
        "ADVANTAGE",
        "ANTIVIRUS",
        "ACCOUNT ADMINISTRATION",
    };

    final public static String TEST_PEREGRINE_PROBLEM[] = 
    {
        "DEFAULT_PROBLEM",
        "ADVICE/GUIDANCE",
        "APPLICATION ACCESS PROVIDER",
        "CHANGE",
        "FAULT"
    };

    final public static String TEST_PEREGRINE_ASSIGNMENT[] = 
    {
        "DEFAULT_ASSIGNMENT",
        "SUPPORT-GCOM",
        "TECH TEAM_FTS"
    };

    final public static String TEST_PEREGRINE_LOCATION[] = 
    {
        "GLOBAL",
        "RAC1"
    };

    public CommonTestCase ( String param )
    {
        super ( param );
    }

    public static DataMapRule createTestDataMapRule()
    {
        DataMapRule rule = createDataMapRule();

        rule.setGroup ( "test-eeb" );

        return rule;
    }

    public static DataMapRule createDataMapRule()
    {
        DataMapRule rule = new DataMapRule();

        //  Create a rule with default values
        rule.setGroup                ( "test-user-" + System.currentTimeMillis() );
        rule.setMethod               ( METHOD_TICKET                 );
        rule.setAlarmpointGroup      ( TEST_ALARMPOINT_GROUP         );
        rule.setAlarmpointScript     ( TEST_ALARMPOINT_SCRIPT        );
	    rule.setPeregrineCategory    ( TEST_PEREGRINE_CATEGORY[0]    );
	    rule.setPeregrineSubCategory ( TEST_PEREGRINE_SUBCATEGORY[0] );
	    rule.setPeregrineProduct     ( TEST_PEREGRINE_PRODUCT[0]     );
	    rule.setPeregrineProblem     ( TEST_PEREGRINE_PROBLEM[0]     );
	    rule.setPeregrineAssignment  ( TEST_PEREGRINE_ASSIGNMENT[0]  );
	    rule.setPeregrineLocation    ( TEST_PEREGRINE_LOCATION[0]    );

        return rule;
    }
};
