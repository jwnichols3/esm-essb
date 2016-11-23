package com.bgi.esm.monitoring.platform.regression;

import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.regression.RegressionTest;
import com.bgi.esm.monitoring.platform.regression.RegressionTestExponential;
import com.bgi.esm.monitoring.platform.regression.RegressionTestExponentialOnly; 
import com.bgi.esm.monitoring.platform.regression.RegressionTestMT;
import com.bgi.esm.monitoring.platform.regression.RegressionTestMTExponential;
import com.bgi.esm.monitoring.platform.regression.TestEmbeddedDatabase;

/**
 *  Used by the executable jar to choose the execution mode.
 */
public class MainClass
{
    final private static Logger _log         = Logger.getLogger ( MainClass.class );
    final private static String VERSION      = "0.3";
    final private static String VERSION_DATE = "August 6, 2007";

    public static void main ( String args[] )
    {
        if ( args.length > 0 )
        {
            String mode = args[0];

            _log.info ( "Operating in mode: " + mode );

            if ( mode.equals ( "eeb-st" ) )
            {
                RegressionTest.main ( null );
            }
            else if ( mode.equals ( "eeb-mt" ) )
            {
                RegressionTestMT.main ( null );
            }
            else if ( mode.equals ( "eeb-st-users" ) )
            {
                RegressionTestExponential.main ( null );
            }
            else if ( mode.equals ( "eeb-mt-users" ) )
            {
                RegressionTestMTExponential.main ( null );
            }
            else if ( mode.equals ( "test-embedded-db" ) )
            {
                TestEmbeddedDatabase.main ( null );
            }
            else if ( mode.equals ( "users-only" ) )
            {
                RegressionTestExponentialOnly.main ( args );
            }
            else if ( mode.equals ( "dump-embedded-db-events" ) )
            {
                DumpEmbeddedDatabaseEvents.main ( null );
            }
            else if ( mode.equals ( "dump-embedded-db-datamap" ) )
            {
                DumpEmbeddedDatabaseDataMap.main ( null );
            }
            else if ( mode.equals ( "version" ) )
            {
                System.out.print ( "EEB - SC Archway Regression/Stress Test Tool\n" );
                System.out.print ( "Version " );
                System.out.print ( VERSION );
                System.out.print ( ", Created on " );
                System.out.print ( VERSION_DATE );
                System.out.print ( "\n" );
                System.out.print ( "Created by:  Dennis Lin (linden)\n" );
                System.out.print ( "             Enterprise Systems Management\n" );
                System.out.print ( "             415-908-7706\n" );
            }
            else
            {
                printUsage ( args );
            }
        }
        else
        {
            printUsage ( args );
        }
    }

    private static void printUsage ( String args[] )
    {
        System.out.println ( "Please run with one of the following choies" );
        System.out.println ( "    eeb-st          Simulating the EEB in single-threaded mode"      );
        System.out.println ( "    eeb-mt          Simulating the EEB in multi-threaded mode"      );
        System.out.println ( "    eeb-st-users    Simulating the EEB in single-threaded mode with app users" );
        System.out.println ( "    eeb-mt-users    Simulating the EEB in multi-threaded mode with app users" );
        System.out.println ( "    users-only      Simulating only the application users" );
        System.out.println ( "    version         Prints out the version of this tool" );

        for ( int counter = 0; counter < args.length; counter++ )
        {
            System.out.println ( "    Arg " + counter + ": " + args[counter] );
        }
    }
}
