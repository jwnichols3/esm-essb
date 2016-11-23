package com.bgi.esm.portlets.testing.suppression;

import java.text.DecimalFormat;

import com.bgi.esm.portlets.testing.LiferayTestcase;

import com.meterware.httpunit.WebForm;

public class ThreadAddEntryForm extends Thread
{
    private boolean is_running = false;
    
    private String hostname    = null;
    private int id             = 0;
    private DecimalFormat df   = new DecimalFormat ( "0000" );

    public ThreadAddEntryForm ( String new_hostname, int new_id )
    {
    	super();
    	
    	hostname = new_hostname;
    	id       = new_id;
    }
    
    public void run ()
    {
        try
        {
        	is_running = true;
        		System.out.println ( "ThreadAddEntryForm, Step 01, id=" + id );
        		
        		LiferayTestcase testcase = new LiferayTestcase ( "threaded-test-" + df.format ( id ) );
	
		        testcase.setPageCaptureDirectory ( "c:\\test\\liferay-test\\add-entries" );
		    	testcase.setPageDelay ( 0 );
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 02, id=" + id );
	
		    	testcase.initialize ( hostname, "test-suppression", "esmtester" );
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 03, id=" + id );
	
		    	//  Go to the "General Guest" community
		    	testcase.goToCommunity ( "General Guest" );
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 04, id=" + id );
	
		    	//  Click on the "Alert Suppression" link to make sure that the Alert Suppression portlet is available
		    	testcase.clickLink ( "Alert Suppression" );
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 05, id=" + id );
	
		    	//  Click on the link to add an entry
		    	testcase.clickLink ( "appAddEntry");
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 06, id=" + id );
		    
		    	//  Populate the "Add Entry" form with test data
		    	WebForm add_entry_form = testcase.getFormWithName( "suppression_add_entry" );
		    		//  Choose the description
		    		add_entry_form.setParameter ( "description",   "This is a test entry submitted by HttpUnit/JUnit" );
		    		//  Choose the server
		    		add_entry_form.setCheckbox  ( "byNode", true );
		    		add_entry_form.setParameter ( "node",          "test_node" );
		    		//  Set the start time to NOW
		    		add_entry_form.setParameter ( "startChoice",   "now" );
		    		//  Set the end time to be 4 hours later
		    		add_entry_form.setParameter ( "endChoice",     "offset" );
		    		add_entry_form.setParameter ( "endChoiceNum",  "4" );
		    		add_entry_form.setParameter ( "endChoiceUnit", "3600" );
		    		//  Set the username
		    		add_entry_form.setParameter ( "username",      "test-suppression" );
		    	testcase.submitForm( add_entry_form );
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 07, id=" + id );
		    	
		    	testcase.logout();
		    	
		    	System.out.println ( "ThreadAddEntryForm, Step 08, id=" + id );
	    	is_running = false;
	    	
	    	
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
