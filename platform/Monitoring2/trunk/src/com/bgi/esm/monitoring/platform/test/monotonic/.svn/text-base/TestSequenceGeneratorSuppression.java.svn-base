package com.bgi.esm.monitoring.platform.test.monotonic;

import java.util.Iterator;
import java.util.List;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.test.BaseClientScript;

/**
 * Exercise the generic monotonic sequence generator.
 * 
 * @author Dennis Lin (linden)
 */
public class TestSequenceGeneratorSuppression extends BaseClientScript
{

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception 
    {
        System.out.println ( "Retrieving next sequence value #1: " + bef.nextMonotonicSequenceForSuppression() );
        System.out.println ( "Retrieving next sequence value #2: " + bef.nextMonotonicSequenceForSuppression() );
        System.out.println ( "Retrieving next sequence value #3: " + bef.nextMonotonicSequenceForSuppression() );
    }
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception 
    {
	    System.out.println("begin");
	
	    TestSequenceGeneratorSuppression tsg = new TestSequenceGeneratorSuppression();
	    tsg.runClient();
	
	    System.out.println("end");
    }
}
