package com.bgi.esm.portlets.testing.suppression;

import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;

public class TestEditEntryForm extends SuppressionTestcase
{
	final private static Logger _log = Logger.getLogger ( TestEditEntryForm.class );
	
    public TestEditEntryForm ( String param )
    {
        super ( param );
    }
    
    /**
     * Test adding a suppression entry with against a test node and with default entries
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    public void testEditEntryDefaultTimes() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestEditEntryForm::testEditEntry()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to add an entry
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "appAddEntrySimple") );
    	
        /////////////////////////////////////////////////////////////////////////////////
    	//  Populate the "Add Entry" form with test data
        /////////////////////////////////////////////////////////////////////////////////
    	response.getForms();
    	WebForm add_entry_form = response.getFormWithName( "suppression_add_entry" );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",   "This is a test entry submitted by HttpUnit/JUnit - TestEditEntryForm::testEditEntry()" );
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
    		add_entry_form.setParameter ( "username",      TEST_USERNAME );    		
    	response = add_entry_form.submit();    	
    	CapturePage();

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Edit the entry
        /////////////////////////////////////////////////////////////////////////////////
        WebLink link = response.getLinkWith( Integer.toString ( suppress_id ) );
        link.click();
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Logout
        /////////////////////////////////////////////////////////////////////////////////
    	logout();
    }
    //*/
    
    /**
     * Test adding a suppression entry with against a test node and with default entries
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    public void testEditEntryDefaultStartSpecifiedEnd() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestEditEntryForm::testEditEntryDefaultStartSpecifiedEnd()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to add an entry
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "appAddEntrySimple") );
    	
        /////////////////////////////////////////////////////////////////////////////////
    	//  Populate the "Add Entry" form with test data
        /////////////////////////////////////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        // Add 10 minutes
        calendar.add ( Calendar.MINUTE, 10 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName( "suppression_add_entry" );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",   "This is a test entry submitted by HttpUnit/JUnit - TestEditEntryForm::testEditEntryDefaultStartSpecifiedEnd()" );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",          "test_node" );
    		//  Set the start time to NOW
    		add_entry_form.setParameter ( "startChoice",   "now" );
    		//  Set the end time to be 4 hours later
    		add_entry_form.setParameter ( "endChoice",     "specified" );
            add_entry_form.setParameter ( "supEndYear",    Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
            add_entry_form.setParameter ( "supEndMonth",   Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
            add_entry_form.setParameter ( "supEndDate",    Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
            add_entry_form.setParameter ( "supEndHour",    Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
            add_entry_form.setParameter ( "supEndMinute",  Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
            add_entry_form.setParameter ( "supEndAmpm",    am_pm );
    		//  Set the username
    		add_entry_form.setParameter ( "username",      TEST_USERNAME );
    	response = add_entry_form.submit();    	
    	CapturePage();

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );


        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Logout
        /////////////////////////////////////////////////////////////////////////////////
    	logout();
    }
    
    
    /**
     * Test adding a suppression entry with against a test node and with default entries
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    public void testEditEntrySpecifiedStartSpecifiedEnd() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestEditEntryForm::testEditEntrySpecifiedStartSpecifiedEnd()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to add an entry
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "appAddEntrySimple") );
    	
        /////////////////////////////////////////////////////////////////////////////////
    	//  Populate the "Add Entry" form with test data
        /////////////////////////////////////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        calendar.add ( Calendar.MINUTE, 60 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName(  "suppression_add_entry" );
    		System.out.println ( "******************** Form offset detected: " + add_entry_form.getParameterValue( "timeZoneOffset" ) );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",    "This is a test entry submitted by HttpUnit/JUnit - TestEditEntryForm::testEditEntrySpecifiedStartSpecifiedEnd()" );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",           "test_node" );
    		//  Set the start time to NOW
    		add_entry_form.setParameter ( "startChoice",    "later" );
    		add_entry_form.setParameter ( "supStartYear",   Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
            add_entry_form.setParameter ( "supStartMonth",  Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
            add_entry_form.setParameter ( "supStartDate",   Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
            add_entry_form.setParameter ( "supStartHour",   Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
            add_entry_form.setParameter ( "supStartMinute", Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
            add_entry_form.setParameter ( "supStartAmpm",   am_pm );
        

    		//  Set the end time to be 10 minutes later
    		add_entry_form.setParameter ( "endChoice",     "specified" );
            calendar.add ( Calendar.MINUTE, 10 );
            am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
            add_entry_form.setParameter ( "supEndYear",    Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
            add_entry_form.setParameter ( "supEndMonth",   Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
            add_entry_form.setParameter ( "supEndDate",    Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
            add_entry_form.setParameter ( "supEndHour",    Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
            add_entry_form.setParameter ( "supEndMinute",  Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
            add_entry_form.setParameter ( "supEndAmpm",    am_pm );
    		//  Set the username
    		add_entry_form.setParameter ( "username",      TEST_USERNAME );
    	response = add_entry_form.submit();    	
    	CapturePage();

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );

        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Logout
        /////////////////////////////////////////////////////////////////////////////////
    	logout();
    }
    
    /**
     * Test adding a suppression entry with against a test node and with a specified start time and the default offset
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    public void testEditEntrySpecifiedStartOffsetEnd() throws SAXException, ParserConfigurationException, 
    	TransformerException, IOException
	{
    	/////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestEditEntryForm::testEditEntrySpecifiedStartOffsetEnd()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to add an entry
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "appAddEntrySimple") );
    	
        /////////////////////////////////////////////////////////////////////////////////
    	//  Populate the "Add Entry" form with test data
        /////////////////////////////////////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        calendar.add ( Calendar.MINUTE, 60 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName(  "suppression_add_entry" );
    		System.out.println ( "******************** Form offset detected: " + add_entry_form.getParameterValue( "timeZoneOffset" ) );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",    "This is a test entry submitted by HttpUnit/JUnit - TestEditEntryForm::testEditEntrySpecifiedStartOffsetEnd()" );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",           "test_node" );
    		//  Set the start time to NOW
    		add_entry_form.setParameter ( "startChoice",    "later" );
    		add_entry_form.setParameter ( "supStartYear",   Integer.toString ( calendar.get ( Calendar.YEAR   ) ) );
            add_entry_form.setParameter ( "supStartMonth",  Integer.toString ( calendar.get ( Calendar.MONTH  ) ) );
            add_entry_form.setParameter ( "supStartDate",   Integer.toString ( calendar.get ( Calendar.DATE   ) ) );
            add_entry_form.setParameter ( "supStartHour",   Integer.toString ( calendar.get ( Calendar.HOUR   ) ) );
            add_entry_form.setParameter ( "supStartMinute", Integer.toString ( calendar.get ( Calendar.MINUTE ) ) );
            add_entry_form.setParameter ( "supStartAmpm",   am_pm );
        

    		//  Set the end time to be 10 minutes later
    		add_entry_form.setParameter ( "endChoice",     "offset" );
    		add_entry_form.setParameter ( "endChoiceNum",  "4" );
    		add_entry_form.setParameter ( "endChoiceUnit", "3600" );
    		
    		//  Set the username
    		add_entry_form.setParameter ( "username",      TEST_USERNAME );
    	response = add_entry_form.submit();    	
    	CapturePage();

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );

        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
            	
        /////////////////////////////////////////////////////////////////////////////////
        //  Logout
        /////////////////////////////////////////////////////////////////////////////////
    	logout();
	}
};
