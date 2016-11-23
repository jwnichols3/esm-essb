package com.bgi.esm.portlets.testing.alarmpoint;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.Button;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebTable;

import com.bgi.esm.portlets.testing.AlarmpointTestcase;

public class TestUserManagement extends AlarmpointTestcase
{
    final private static Logger _log      = Logger.getLogger ( TestUserManagement.class );

    private String web_login_first_name   = null;
    private String web_login_last_name    = null;
    private String web_login_username     = null;
    private String web_login_email        = null;
    private String web_login_phone_work   = null;
    private String web_login_phone_mobile = null;

    public TestUserManagement ( String param ) 
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

    public void testAddNewUser() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Start the test case
        initialize();

        int timestamp = (int) (System.currentTimeMillis() / 60000L);

        addNewUser ( "test_user_" + timestamp, "Testfirstname", "Testlastname" );
    }

    public void testRetrieveUser() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Start the test case
        initialize();

        //  Navigate to the user's info page
        searchForUsername ( web_login_username );
    }
}
