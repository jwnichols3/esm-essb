package com.bgi.esm.monitoring.platform.test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.QueueSender;
import javax.jms.Session;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;

import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.TicketMessage;
import com.bgi.esm.monitoring.platform.shared.utility.AbstractQueue;

public class PopulateTestData
{
    private BackEndFacade bef           = null;
    private QueueConnection _connection = null;
    private QueueSender     _sender     = null;
    private QueueSession    _session    = null;
    private String          _queueName  = null;

    public PopulateTestData()
    {
        bef = new BackEndFacade();
    }

    public void runClient() throws BackEndFailure
    {
        checkDataMapRule();
    }

    private void checkDataMapRule() throws BackEndFailure
    {
    	DataMapRule rule1 = new DataMapRule ("test-esm-ticket" );
    	rule1.setMethod               ( "ticket"             );
    	rule1.setAlarmpointGroup      ( "Dennis Test Group"  );
    	rule1.setAlarmpointScript     ( "BGI VPO"            );
    	rule1.setPeregrineCategory    ( "servers"            );
    	rule1.setPeregrineSubCategory ( "enterprise systems management");
    	rule1.setPeregrineProduct     ( "vpo"                );
    	rule1.setPeregrineProblem     ( "fault"              );
    	rule1.setPeregrineAssignment  ( "default assignment" );
    	rule1.setPeregrineLocation    ( "global"             );

    	DataMapRule rule2 = new DataMapRule ("test-esm-alarmpoint-only" );
    	rule2.setMethod               ( "alarmpoint_only"    );
    	rule2.setAlarmpointGroup      ( "Dennis Test Group"  );
    	rule2.setAlarmpointScript     ( "BGI VPO"            );
    	rule2.setPeregrineCategory    ( "servers"            );
    	rule2.setPeregrineSubCategory ( "enterprise systems management");
    	rule2.setPeregrineProduct     ( "vpo"                );
    	rule2.setPeregrineProblem     ( "fault"              );
    	rule2.setPeregrineAssignment  ( "default assignment" );
    	rule2.setPeregrineLocation    ( "global"             );

        boolean found_rule1 = false;
        boolean found_rule2 = false;
    	List list = bef.getAllDataMapRules();
    	System.out.println ("Number of existing data map rules:" + list.size());

        Iterator iterator = list.iterator();
        while ( iterator.hasNext() )
        {
            DataMapRule rule = (DataMapRule) iterator.next();

            if ( rule.getGroup().equals ( "test-esm-ticket" ) )
            {
                found_rule1 = true;
            }

            if ( rule.getGroup().equals ( "test-esm-alarmpoint-only" ) )
            {
                found_rule2 = true;
            }
        }

        if ( false == found_rule1 )
        {
            System.out.println ( "Could not find test rule 'test-esm-ticket'.  Creating...." );

            DataMapRule rule = bef.addOrUpdateDataMapRule ( rule1 );

            if ( null != rule )
            {
                System.out.println ( "Test rule 'test-esm-ticket' successfully added" );
            }
        }

        if ( false == found_rule2 )
        {
            System.out.println ( "Could not find test rule 'test-esm-alarmpoint-only'.  Creating...." );

            DataMapRule rule = bef.addOrUpdateDataMapRule ( rule2 );

            if ( null != rule )
            {
                System.out.println ( "Test rule 'test-esm-alarmpoint-only' successfully added" );
            }
        }
    }

    public static void main ( String args[] ) throws Exception
    {
        System.out.println("begin");

        PopulateTestData ptd = new PopulateTestData ();
        ptd.runClient();

        System.out.println("end");
    }

};
