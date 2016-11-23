package com.bgi.esm.portlets.testing.suppression;

import java.text.DecimalFormat;

import com.bgi.esm.portlets.testing.LiferayTestcase;

public class ThreadViewCurrentEntriesMine extends Thread
{
    private boolean is_running = false;
    
    private String hostname    = null;
    private int id             = 0;
    private DecimalFormat df   = new DecimalFormat ( "0000" );
    
    public ThreadViewCurrentEntriesMine ( String new_hostname, int new_id )
    {
    	super();
    	
    	hostname = new_hostname;
    	id = new_id;
    }

    public void run ()
    {
        try
        {
        	is_running = true;
	            LiferayTestcase testcase = new LiferayTestcase ( "threaded-test-" + df.format ( id ) );
	            
	            System.out.println ( "ThreadViewCurrentEntriesMine, Step 01, id=" + id );
	
	            testcase.setPageCaptureDirectory ( "c:\\test\\liferay-test\\view-current" );
	    	    testcase.setPageDelay ( 0 );
	    	
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 02, id=" + id );
	    	    
	    	    testcase.initialize ( hostname, "test-suppression", "esmtester" );
	    	    
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 03, id=" + id );
	    	
	    	    //  Go to the "General Guest" community
	    	    testcase.goToCommunity ( "General Guest" );
	    	    
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 04, id=" + id );
	    	
	    	    //  Click on the "Alert Suppression" link to make sure that the Alert Suppression portlet is available
	    	    testcase.clickLink ( "Alert Suppression" );
	    	    
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 05, id=" + id );
	    	
	    	    //  Click on the link to add an entry
	    	    testcase.clickLink ( "appCurrentEntriesMine");
	    	    
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 06, id=" + id );
	    	
	    	    testcase.logout();
	    	    
	    	    System.out.println ( "ThreadViewCurrentEntriesMine, Step 07, id=" + id );
        	is_running = true;
        }
        catch ( Exception exception )
        {
        }
    }
    
    public boolean isRunning()
    {
    	return is_running;
    }
}
