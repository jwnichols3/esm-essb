package com.bgi.esm.portlets.testing.suppression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;

public class TestExtractFile extends SuppressionTestcase
{
    final private static Logger _log     = Logger.getLogger ( TestExtractFile.class );

    public TestExtractFile ( String param )
    {
        super ( param );
    }

    /**
     * Logins to the portal and tries to get the extract file settings
     */
    public void testExtractFileSettingsAreNotNull() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testExtractFileSettingsAreNotNull()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );
    }
    
    public void testExtractFileDoesNotContainNegativeTestSuppression() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testExtractFileSettingsAreNotNull()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );

        assertFalse ( "Error in suppression file: " + extract_file_suppressions, 
            doesExtractFileHaveSuppressionWithString ( "XXX___SHOULD_NOT_HAVE_THIS_DESCRIPTION___XXX" , extract_file_suppressions ) );
    }

    /**
     * Test adding a suppression entry with against a test node and with default entries
     * 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    public void testAddEntryDefaultTimesCreatesExtractFile() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testAddEntryDefaultTimesCreatesExtractFile()" );

    	
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
        String suppress_desc     = "This is a test entry submitted by HttpUnit/JUnit - TestExtractFile::testAddEntryDefaultTimesCreatesExtractFile() - timestamp=" + System.currentTimeMillis();
        String extract_file_node = "test_node_" + System.currentTimeMillis();
    	response.getForms();
    	WebForm add_entry_form = response.getFormWithName( "suppression_add_entry" );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",   suppress_desc );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",          extract_file_node );
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

        System.out.println ( "Form description value: " + add_entry_form.getParameterValue ( "description" ) );

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );
        System.out.println ( "Suppression ID for testAddEntryDefaultTimes() - " + suppress_id );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        System.out.println ( "Listing suppressions found: " );
        for ( int counter = 0; counter < current_suppression_ids.size(); counter++ )
        {
            System.out.println ( "\t" + current_suppression_ids.elementAt(counter).toString() );
        }
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
        portletSetup();

    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test for existence of record in suppression file
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( "Could not find suppression with description: " + suppress_desc, 
            doesExtractFileHaveSuppressionWithString ( extract_file_node, extract_file_suppressions ) );
    	
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
    public void testAddEntryDefaultStartSpecifiedEndCreatesExtractFile() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testAddEntryDefaultStartSpecifiedEndCreatesExtractFile()" );

    	
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
        String suppress_desc = "This is a test entry submitted by HttpUnit/JUnit - TestExtractFile::testAddEntryDefaultStartSpecifiedEndCreatesExtractFile() - timestamp=" + System.currentTimeMillis();
        String extract_file_node = "test_node_" + System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        // Add 10 minutes
        calendar.add ( Calendar.MINUTE, 10 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName( "suppression_add_entry" );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",   suppress_desc );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",          extract_file_node );
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

        System.out.println ( "Form description value: " + add_entry_form.getParameterValue ( "description" ) );

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );
        System.out.println ( "Suppression ID for testAddEntryDefaultStartSpecifiedEnd() - " + suppress_id );


        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        System.out.println ( "Listing suppressions found: " );
        for ( int counter = 0; counter < current_suppression_ids.size(); counter++ )
        {
            System.out.println ( "\t" + current_suppression_ids.elementAt(counter).toString() );
        }
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
        portletSetup();

    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test for existence of record in suppression file
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( "Could not find suppression with description: " + suppress_desc, 
            doesExtractFileHaveSuppressionWithString ( extract_file_node, extract_file_suppressions ) );
    	
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
    public void testAddEntrySpecifiedStartSpecifiedEndCreatesExtractFile() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testAddEntrySpecifiedStartSpecifiedEndCreatesExtractFile()" );

    	
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
        String suppress_desc = "This is a test entry submitted by HttpUnit/JUnit - TestExtractFile::testAddEntrySpecifiedStartSpecifiedEndCreatesExtractFile() - timestamp=" + System.currentTimeMillis();
        String extract_file_node = "test_node_" + System.currentTimeMillis();
        Calendar calendar    = Calendar.getInstance();
        calendar.add ( Calendar.MINUTE, 60 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName(  "suppression_add_entry" );
    		System.out.println ( "******************** Form offset detected: " + add_entry_form.getParameterValue( "timeZoneOffset" ) );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",    "This is a test entry submitted by HttpUnit/JUnit - TestExtractFile::testAddEntrySpecifiedStartSpecifiedEndCreatesExtractFile()" );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",           extract_file_node );
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

        System.out.println ( "Form description value: " + add_entry_form.getParameterValue ( "description" ) );

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );
        System.out.println ( "Suppression ID for testAddEntrySpecifiedStartSpecifiedEnd() - " + suppress_id );

        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        System.out.println ( "Listing suppressions found: " );
        for ( int counter = 0; counter < current_suppression_ids.size(); counter++ )
        {
            System.out.println ( "\t" + current_suppression_ids.elementAt(counter).toString() );
        }
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
        portletSetup();

    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );
    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Test for existence of record in suppression file
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( "Could not find suppression with description: " + suppress_desc, 
            doesExtractFileHaveSuppressionWithString ( extract_file_node, extract_file_suppressions ) );
    	
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
    public void testAddEntrySpecifiedStartOffsetEndCreatesExtractFile() throws SAXException, ParserConfigurationException, 
    	TransformerException, IOException
	{
    	/////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        HTMLElement page_element = null;
        String temp_string       = null;
    	setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestExtractFile::testAddEntrySpecifiedStartOffsetEnd()" );

    	
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
        String suppress_desc = "This is a test entry submitted by HttpUnit/JUnit - TestExtractFile::testAddEntrySpecifiedStartOffsetEndCreatesExtractFile() - timestamp=" + System.currentTimeMillis();
        String extract_file_node = "test_node_" + System.currentTimeMillis();
        Calendar calendar    = Calendar.getInstance();
        calendar.add ( Calendar.MINUTE, 60 );
        String am_pm = (calendar.get ( Calendar.HOUR_OF_DAY ) >= 12 )? "PM" : "AM";
        response.getForms();
    	WebForm add_entry_form = response.getFormWithName(  "suppression_add_entry" );
    		System.out.println ( "******************** Form offset detected: " + add_entry_form.getParameterValue( "timeZoneOffset" ) );
    		assertNotNull ( add_entry_form );
    		//  Choose the description
    		add_entry_form.setParameter ( "description",    extract_file_node );
    		//  Choose the server
    		add_entry_form.setCheckbox  ( "byNode", true );
    		add_entry_form.setParameter ( "node",           extract_file_node );
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

        System.out.println ( "Form description value: " + add_entry_form.getParameterValue ( "description" ) );

        /////////////////////////////////////////////////////////////////////////////////
        //  This should be the entry success page.  Retrieve the suppression ID
        /////////////////////////////////////////////////////////////////////////////////
        page_element = response.getElementWithID ( "addEntryMessage" );
        assertNotNull ( page_element );

        //  Text should be in the form of "Add Entry Success for Suppression #xxxx"
        temp_string  = page_element.getText();
        temp_string  = temp_string.substring ( temp_string.indexOf( "#" )+1 ).trim();
        int suppress_id = Integer.parseInt ( temp_string );
        System.out.println ( "Suppression ID for testAddEntrySpecifiedStartOffsetEnd() - " + suppress_id );

        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in all current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> current_suppression_ids = navigateToAllCurrentSuppressions();
        System.out.println ( "Listing suppressions found: " );
        for ( int counter = 0; counter < current_suppression_ids.size(); counter++ )
        {
            System.out.println ( "\t" + current_suppression_ids.elementAt(counter).toString() );
        }
        assertTrue ( current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Verify that the suppression exists in my current suppressions
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> my_current_suppression_ids = navigateToMyCurrentSuppressions();
        assertTrue ( my_current_suppression_ids.contains ( new Integer ( suppress_id ) ) );
            	
        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to get to the portlet settings page
        /////////////////////////////////////////////////////////////////////////////////
        portletSetup();

    	assertTrue ( clickLink ( "portletSettings") );

        /////////////////////////////////////////////////////////////////////////////////
        //  Retrieve the extract file locations
        /////////////////////////////////////////////////////////////////////////////////
        WebForm portlet_prefs = response.getFormWithName(  "suppression_portlet_prefs" );
        String extract_file_suppressions = portlet_prefs.getParameterValue ( "extractFileForSuppressions" );
        String extract_file_vpo          = portlet_prefs.getParameterValue ( "extractFileForVpo" );
        String extract_file_inst         = portlet_prefs.getParameterValue ( "extractFileForInst" );

        /////////////////////////////////////////////////////////////////////////////////
        //  Test to make sure that the values are correct
        /////////////////////////////////////////////////////////////////////////////////
        StringBuilder error_message = new StringBuilder();
        boolean valid_extract_file_suppressions = true; 
        boolean valid_extract_file_vpo          = true; 
        boolean valid_extract_file_inst         = true; 

        if (( null == extract_file_suppressions ) || ( extract_file_suppressions.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for suppressions extract file\n" );
            valid_extract_file_suppressions = false; 
        }
        if (( null == extract_file_vpo ) || ( extract_file_vpo.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for vpo extract file\n" );
            valid_extract_file_vpo = false; 
        }
        if (( null == extract_file_inst ) || ( extract_file_inst.trim().length() == 0 ))
        {
            error_message.append ( "Invalid location for inst extract file\n" );
            valid_extract_file_inst = false; 
        }

        assertTrue ( error_message.toString(), valid_extract_file_suppressions && valid_extract_file_vpo && valid_extract_file_inst );
        System.out.println ( "Retrieved extraction file locations: " );
        System.out.println ( "\tSuppress: " + extract_file_suppressions );
        System.out.println ( "\tVPO:      " + extract_file_vpo          );
        System.out.println ( "\tInst:     " + extract_file_inst         );
    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Test for existence of record in suppression file
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( "Could not find suppression with description: " + suppress_desc, 
            doesExtractFileHaveSuppressionWithString ( extract_file_node, extract_file_suppressions ) );
    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Logout
        /////////////////////////////////////////////////////////////////////////////////
    	logout();
	}

    private boolean doesExtractFileHaveSuppressionWithString ( String string, String extract_file_suppressions ) throws IOException
    {
        FileInputStream infile    = new FileInputStream ( extract_file_suppressions );
        byte file_contents[]      = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();
        
        StringTokenizer lines     = new StringTokenizer ( new String ( file_contents ), "\n" );
        boolean found_suppression = false;
        String current_line       = null;
        while ( lines.hasMoreTokens() )
        {
            current_line = lines.nextToken();

            if ( current_line.indexOf ( string ) > 0 )
            {
                found_suppression = true;
                break;
            }
        }

        return found_suppression;
    }
}
