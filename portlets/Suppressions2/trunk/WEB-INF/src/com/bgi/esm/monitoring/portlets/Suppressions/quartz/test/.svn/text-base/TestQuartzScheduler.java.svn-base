package com.bgi.esm.monitoring.portlets.Suppressions.quartz.test;

import org.apache.log4j.Logger;
import junit.framework.TestCase;

public class TestQuartzScheduler extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestQuartzScheduler.class );

    public TestQuartzScheduler ( String param )
    { 
        super ( param );
    }

    private static void logFunction ( String function_name )
    {
        StringBuilder message = new StringBuilder ( "**************************************** " );
        message.append ( function_name );
        message.append ( "()" );

        _log.info ( function_name );
    }

    /*
    public void testCreateScheduler()
    {
        logFunction ( "testCreateScheduler" );

        StdSchedulerFactory.getDefaultScheduler();
        Scheduler scheduler = example.scheduleJob(scheduler);

        assertNotNull ( "Could not instantiate scheduler", scheduler );

        //  Start the Scheduler running
        scheduler.start();
        assertTrue ( "Could not start scheduler", scheduler.isStarted() );

        //  Shut down the Scheduler
        scheduler.shutdown();
        assertTrue ( "Could not shut down scheduler", scheduler.isShutdown() );
    }
    //*/
    
    public void testInstantiateJob()
    {
        logFunction ( "testInstantiateJob" );
    }

    public void testAddJob()
    {
        logFunction ( "testAddJob" );
    }

    public void testAddJob_Persist()
    {
        logFunction ( "testAddJob_Persist" );
    }

    public void testAddJobWaitForTrigger()
    {
        logFunction ( "testAddJobWaitForTrigger" );
    }

    public void testAddJobWaitForTrigger_Recover()
    {
        logFunction ( "testAddJobWaitForTrigger_Recover" );
    }

    public void testAddJobWaitForTrigger_NewScheduler()
    {
        logFunction ( "testAddJobWaitForTrigger_NewScheduler" );
    }
};
