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
public class TestSequenceGeneratorDataMap extends BaseClientScript
{

    /**
     * 
     * @throws Exception
     */
    public void runClient() throws Exception 
    {
        System.out.println ( "Retrieving next sequence value #1: " + bef.nextMonotonicSequenceForDataMap() );
        System.out.println ( "Retrieving next sequence value #2: " + bef.nextMonotonicSequenceForDataMap() );
        System.out.println ( "Retrieving next sequence value #3: " + bef.nextMonotonicSequenceForDataMap() );
    }
    
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception 
    {
	    System.out.println("begin");
	
	    TestSequenceGeneratorDataMap tsg = new TestSequenceGeneratorDataMap();
	    tsg.runClient();
	
	    System.out.println("end");
    }
}
