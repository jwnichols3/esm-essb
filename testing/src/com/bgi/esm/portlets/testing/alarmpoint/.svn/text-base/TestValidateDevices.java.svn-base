package com.bgi.esm.portlets.testing.alarmpoint;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import tools.testing.alarmpoint.Alert;
import tools.testing.alarmpoint.AlertChannel;
import tools.testing.alarmpoint.AlertReceiverServer;

import com.bgi.esm.portlets.testing.AlarmpointTestcase;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebTable;

public class TestValidateDevices extends AlarmpointTestcase
{
    private static final Logger _log = Logger.getLogger ( TestValidateDevices.class );

    private static final int ALERT_RECEIVER_SERVER_PORT = 9001;
    private static final int ALERT_CHANNEL_POLL_TIMEOUT = 120;  // seconds
    
    private static AlertReceiverServer alertReceiverServer;
    private static AlertChannel        alertChannel;
    
    private String web_login_first_name   = null;
    private String web_login_last_name    = null;
    private String web_login_username     = null;
    private String web_login_email        = null;
    private String web_login_phone_work   = null;
    private String web_login_phone_mobile = null;

    public TestValidateDevices ( String param ) 
    {
        super ( param );

        try
        {
            initialize();
        }
        catch ( SAXException exception )
        {
            _log.info ( "Error in constructor - SAXException", exception );
        }
        catch ( TransformerException exception )
        {
            _log.info ( "Error in constructor - TransformerException", exception );
        }
        catch ( ParserConfigurationException exception )
        {
            _log.info ( "Error in constructor - ParserConfigurationException", exception );
        }
        catch ( IOException exception )
        {
            _log.info ( "Error in constructor - IOException", exception );
        }

        web_login_first_name   = properties.getProperty ( "alarmpoint.testcase.web_login.first_name"   );
        web_login_last_name    = properties.getProperty ( "alarmpoint.testcase.web_login.last_name"    );
        web_login_username     = properties.getProperty ( "alarmpoint.testcase.web_login.username"     );
        web_login_email        = properties.getProperty ( "alarmpoint.testcase.web_login.email"        );
        web_login_phone_work   = properties.getProperty ( "alarmpoint.testcase.web_login.phone_work"   );
        web_login_phone_mobile = properties.getProperty ( "alarmpoint.testcase.web_login.phone_mobile" );
    }

    public static void startAlertReceiverServer()
    {
    	alertChannel = new AlertChannel();
    	alertReceiverServer = 
    		new AlertReceiverServer(ALERT_RECEIVER_SERVER_PORT, alertChannel);

     	new Thread(alertReceiverServer).start();
    }
    
    public static void shutDownAlertReceiverServer()
    {
    	alertReceiverServer.shutDown();
    	alertReceiverServer = null;
    	alertChannel = null;
    }
    
    public void testRetrieveUserDevices() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Start the test case
        initialize();

        //  Navigate to the user's info page
        searchForUsername ( web_login_username );

        //  Go to the link that will list the user devices
        clickLink ( "User Devices" );

        //  Process the table that contains the user devices
        WebTable table = searchForTable ( "list-table" );
        int num_rows   = table.getRowCount();
        int num_cols   = table.getColumnCount();
        _log.info ( "Found list table with " + num_rows + " rows and " + num_cols + " columns" );

        //  First row is the title row
        Vector <String> columns = new Vector <String> ();
        for ( int counter = 0; counter < num_cols; counter++ )
        {
            columns.add ( table.getTableCell( 0, counter ).getText() );
        }


        //  Stats
        int num_devices          = num_rows - 1;     //  The top row is the title row, so the number of devices = num_rows - 1
        int index_device_type    = columns.indexOf ( "Type"    );  //  Type of device (Email, Voice, etc.)
        int index_device_name    = columns.indexOf ( "Name"    );  //  Name of the device (BGI Mail, Mobile Phone, Work Phone, etc.)
        int index_device_details = columns.indexOf ( "Details" );  //  email address, phone number
        int index_device_valid   = columns.indexOf ( "Valid"   );  //  whether or not the device has been validated (Pending, Untested, Valid)
        int index_device_status  = columns.indexOf ( "Status"  );  //  Whether or not the device is 'Active' or not

        assertTrue ( index_device_type    >= 0 );
        assertTrue ( index_device_name    >= 0 );
        assertTrue ( index_device_details >= 0 );
        assertTrue ( index_device_valid   >= 0 );
        assertTrue ( index_device_status  >= 0 );
        assertTrue ( num_devices > 0 );
    }
    
    private WebLink getValidateDeviceLink (String targetDetailsCellText) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
    	WebTable table = searchForTable ( "list-table" );

    	TableCell targetCell = null;
    	
    	int rowCount = table.getRowCount();
    	for (int row = 1; row < rowCount && targetCell == null; ++row)
    	{
    		String detailsCellText = table.getCellAsText(row, 4);
    		if ( detailsCellText.equals(targetDetailsCellText) )
    		{
    			targetCell = table.getTableCell(row, 5);
    		}
    	}
    	
    	assertNotNull(targetCell);
    	
    	WebLink[] links = targetCell.getLinks();
    	
    	assertEquals(1, links.length);

    	return links[0];
    }
    
    private String extractValidationCode(Alert alert)
    {
    	String body = alert.getBody();
    	
    	final Pattern p = Pattern.compile("(?s).*The validation code for your Device, .*, is (\\d*)\\..*");
    	Matcher m = p.matcher(body);
    	
    	assertTrue(m.matches());
    	
    	return m.group(1);
    }
    
    private String extractDeviceId(WebLink validateDeviceLink)
    {
     	final Pattern p = Pattern.compile("ValidateDevice\\.do;jsessionid=.*\\?deviceId=(\\d+);personId=\\d+");
     	Matcher m = p.matcher(validateDeviceLink.getURLString());
     	assertTrue(m.matches());
     	return m.group(1);
    }
    
    private void enterValidationCode(String deviceId, String validationCode) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
     	WebForm validationForm = response.getFormWithName("DevicesPendingValidationform");
    	
     	validationForm.setCheckbox("action" + deviceId, true);
     	
     	validationForm.setParameter("validationcode" + deviceId, validationCode);
     	
     	SubmitButton submitButton = validationForm.getSubmitButton("validateDevices");
     	
     	submitForm(validationForm, submitButton);
    }
    
    private void confirmDeviceValidated(String deviceId) throws SAXException
    {
     	WebForm validatedDevicesForm = response.getFormWithName("ValidatedDevicesform");
     	
     	assertNotNull("Device " + deviceId + " not validated.", validatedDevicesForm);
     	
     	assertTrue("Device " + deviceId + " not validated.", 
     			validatedDevicesForm.hasParameterNamed("action" + deviceId));
    }
    
    public void doValidateDevice(String deviceDetail) throws IOException, SAXException, ParserConfigurationException, TransformerException, InterruptedException
    {
    	// Start the test case.
    	initialize();
    	
    	startAlertReceiverServer();
    	
    	// Navigate to the user's web info page
    	searchForUsername( web_login_username );
    	
    	// Go to the link that will list the user devices
    	clickLink( "User Devices" );
    	
     	WebLink validateDeviceLink = getValidateDeviceLink(deviceDetail);
    	
     	// Should not be hard-coded, but I can't seem to get it from the document.
     	final String basePath = "/alarmpoint";
     	
     	// Click on link to transmit alert with validation code
     	navigateTo(basePath + '/' + validateDeviceLink.getURLString());
    	
     	// Retrieve alert from the alert channel.  The alert has the 
     	// validation code.
     	Alert alert = alertChannel.poll(ALERT_CHANNEL_POLL_TIMEOUT, TimeUnit.SECONDS);
 
     	shutDownAlertReceiverServer();
     	
     	CapturePage();
       	
     	assertNotNull("alert is null.", alert);

     	String validationCode = extractValidationCode(alert);
     	_log.debug("validation code: " + validationCode);
     	
     	String deviceId = extractDeviceId(validateDeviceLink);
     	_log.debug("deviceId: " + deviceId);
     	
     	enterValidationCode(deviceId, validationCode);
     	
     	confirmDeviceValidated(deviceId);
    }
    
    public void testValidateVirtualPager() throws IOException, SAXException, ParserConfigurationException, TransformerException, InterruptedException
    {
    	doValidateDevice("Virtual Pager");
    }
    
    public void testValidateVirtualEmail() throws IOException, SAXException, ParserConfigurationException, TransformerException, InterruptedException
    {
    	doValidateDevice("Virtual Email");
    }
    
    public void testValidateVirtualTextPhone() throws IOException, SAXException, ParserConfigurationException, TransformerException, InterruptedException
    {
    	doValidateDevice("Virtual Text Phone");
    }
}
