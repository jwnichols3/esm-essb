/**
 * Testcase based on HttpUnit/JUnit to automatically submit forms at http://esm/vpo/vpo-event-list.cfm
 * 
 * @author  Dennis S. Lin
 * @company Barclays Global Investors
 * @dept    Enterprise System Management
 * @version $Revision: 1.1 $
 */
package com.bgi.esm.portlets.testing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;

import com.bgi.esm.portlets.testing.WebTestCase;

public class VPOTestcase extends WebTestCase
{
    protected String Hostname;
    
    /**
     *  The one and only main function
     */
    public static void main ( String args[] ) throws Exception
    {
        VPOTestcase testcase = new VPOTestcase( "console" );

        testcase.setPageCaptureDirectory ( "c:\\test\\liferay-test" );
    	testcase.setPageDelay ( 0 );

        testcase.initialize ( "http://esm" );

        testcase.CapturePage();

        testcase.getVPOEvents();
    }

    /**
     * Default constructor for JUnit compatibility
     *
     * @param the parameter for the default JUnit constructor
     */
    public VPOTestcase ( String param )
    {
        super ( param );
    }
    
    /**
     * @param hostname  The host to initially point to
     * @return true if able to point
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public boolean initialize ( String hostname ) 
        throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
        Hostname = hostname;

        //  Create the timestamp
        StringBuilder buffer = new StringBuilder();
        Calendar c = Calendar.getInstance();
        df = new DecimalFormat ( "00" );
        buffer.append ( df.format ( c.get( Calendar.YEAR ) ) );
        buffer.append ( "-" );
        buffer.append ( df.format ( c.get( Calendar.MONTH ) ) );
        buffer.append ( "-" );
        buffer.append ( df.format ( c.get( Calendar.DATE ) ) );
        buffer.append ( "-" );
        buffer.append ( df.format ( c.get( Calendar.HOUR_OF_DAY ) ) );
        buffer.append ( df.format ( c.get( Calendar.MINUTE ) ) );
        buffer.append ( df.format ( c.get( Calendar.SECOND ) ) );
        
        timestamp = buffer.toString();

        //  Initialize ommon HttpUnit parameters
        wc = new WebConversation();
        request = new GetMethodWebRequest ( Hostname + "/vpo/vpo-event-list.cfm" );
        response = wc.getResponse ( request );


        return true;
    }

    /**
     * Locate the relevant form, set its values, and submit it
     * 
     * @return true if the form was successfully submitted, false otherwise
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public boolean getVPOEvents() 
        throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	//  Locate the proper form to set and set the parameters
        WebForm form = response.getFormWithName ( "getevents" );
        form.setParameter ( "App", "gaa_asgus" );
        form.setCheckbox ( "severity", "1", true );
        form.setCheckbox ( "severity", "2", true );
        form.setCheckbox ( "severity", "4", true );
        //form.setParameter ( "node", "asg-sa-0x" );
        //form.setParameter ( "node", "-- select node --" );

        //  Submit the form and re-assign the local WebResponse object to point to the
        //  current response from the web server
        response = form.submit();

        //  Capture the HTML elements of the resulting page into XML file in the file capture directory
        CapturePage();
        
        
        return true;
    }
}
