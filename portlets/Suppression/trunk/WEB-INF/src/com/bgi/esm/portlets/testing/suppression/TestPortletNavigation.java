package com.bgi.esm.portlets.testing.suppression;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.suppression.SuppressionTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;

public class TestPortletNavigation extends SuppressionTestcase
{
    final private static Logger _log     = Logger.getLogger ( TestPortletNavigation.class );

    public TestPortletNavigation ( String param )
    {
        super ( param );
    }
    
    /**
     * Tests to see if the cookie that determines user's timezone has been
     * correctly set
     * 
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     * @throws SAXException 
     * @throws IOException 
     * @throws MalformedURLException
     */
    public void testRetrieveTimezoneOffset() throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	/////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestAddEntryForm::testAddEntrySpecifiedStartSpecifiedEnd()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to add an entry
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( clickLink ( "appAddEntrySimple") );
    	response.getForms();
    	WebForm add_entry_form = response.getFormWithName(  "suppression_add_entry" );
    	assertNotNull ( add_entry_form );

    	//  Retrieve the timezone offset from the form
    	String timezone_offset = add_entry_form.getParameterValue( "timeZoneOffset" );
    	
    	//  Now compute the server's timezone offset
    	Calendar calendar      = Calendar.getInstance();
    	long calendar_offset   = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
    	
    	System.out.println ( "Testing timezone offsets: " );
    	System.out.println ( "\tUser:   " + timezone_offset );
    	System.out.println ( "\tSystem: " + calendar_offset );
    	assertEquals ( timezone_offset, Long.toString ( calendar_offset ) );
    }
    
    /**
     * Test retrieving the list of all current entries
     */
    public void testRetrieveAllCurrentEntries() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestPortletNavigation::testAddEntry()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();    	

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to view all current entries
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> suppression_ids = navigateToAllCurrentSuppressions();
        assertNotNull ( suppression_ids );
        
        HTMLElement page_element = null;
        
        Integer suppression_id = suppression_ids.elementAt ( 0 );
        System.out.println ( "Retrieve suppression ID information for ID #" + suppression_id.toString() );
        
        page_element = response.getElementWithID ( "description"+suppression_id.toString() );
        System.out.println ( "\tDescription: " + page_element.getText() );
        page_element = response.getElementWithID ( "startDate"+suppression_id.toString() );
        System.out.println ( "\tStart date:  " + page_element.getText() );
        page_element = response.getElementWithID ( "endDate"+suppression_id.toString() );
        System.out.println ( "\tEnd date:    " + page_element.getText() );
    }

    /**
     * Test retrieving the list of the test user's current entries
     */
    public void testRetrieveMyCurrentEntries() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Initialize test parameters
        /////////////////////////////////////////////////////////////////////////////////
        setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	setPageDelay ( 0 );
        
        _log.info ( "Starting test case: TestPortletNavigation::testAddEntry()" );

    	
        /////////////////////////////////////////////////////////////////////////////////
        //  Login
        /////////////////////////////////////////////////////////////////////////////////
    	assertTrue ( initialize ( hostname, TEST_USERNAME, TEST_PASSWORD ) );
    	
        portletSetup();    	

        /////////////////////////////////////////////////////////////////////////////////
    	//  Click on the link to view all current entries
        /////////////////////////////////////////////////////////////////////////////////
        Vector <Integer> suppression_ids = navigateToMyCurrentSuppressions();
        assertNotNull ( suppression_ids );
    }    
};
