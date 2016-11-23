package com.bgi.esm.portlets.testing.suppression;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.bgi.esm.portlets.testing.LiferayTestcase;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;


public class SuppressionTestcase extends LiferayTestcase
{
    final private static Logger _log = Logger.getLogger ( SuppressionTestcase.class );

    protected static Properties test_properties = null;
    protected static String TEST_USERNAME       = null;
    protected static String TEST_PASSWORD       = null;
    protected static String hostname            = null;

    public SuppressionTestcase ( String param )
    {
        super ( param );

        //  Read the parameters from the source file
        if ( null == test_properties )
        {
            try
            {
                test_properties = new Properties();

                Class c        = this.getClass();
                ClassLoader cl = c.getClassLoader();
                InputStream is = cl.getResourceAsStream ( "test-suppressions.properties" );
                test_properties.load ( is );

                TEST_USERNAME  = test_properties.getProperty ( "suppressions.test.username" );
                TEST_PASSWORD  = test_properties.getProperty ( "suppressions.test.password" );
                hostname       = test_properties.getProperty ( "suppressions.test.hostname" );
            }

            catch ( IOException exception )
            {
                exception.printStackTrace();
            }
        }

        _log.info ( "Test Username:  " + TEST_USERNAME );
        _log.info ( "Test Password:  " + TEST_PASSWORD );
        _log.info ( "Test Localhost: " + hostname );
    }

    public void portletSetup()  throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Go to the "General Guest" community
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( goToCommunity ( "General Guest" ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Click on the "Alert Suppression" link to make sure that the Alert 
        //  Suppression portlet is available
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( isPortletOnPage ( "Alert Suppressions Portlet" ) );
    }

    public Vector <Integer> navigateToAllCurrentSuppressions() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Click on the "Alert Suppression" link to make sure that the Alert 
        //  Suppression portlet is available
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( isPortletOnPage ( "Alert Suppressions Portlet" ) );
        
        /////////////////////////////////////////////////////////////////////////////////
        //  Click on the link to view all current entries
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( clickLink ( "appCurrentEntriesAll") );
        WebLink suppression_entries[] = getLinksWithID ( "suppressionEntryLink" );
        Vector <Integer> suppression_ids = new Vector <Integer> ();
        for ( int counter = 0; counter < suppression_entries.length; counter++ )
        {
            if ( suppression_entries[counter].getText() != null )
            {
                suppression_ids.add ( new Integer ( suppression_entries[counter].getText() ) );
            }
        }

        return suppression_ids;
    }

    public Vector <Integer> navigateToMyCurrentSuppressions() throws SAXException, ParserConfigurationException, 
           TransformerException, IOException
    {
        /////////////////////////////////////////////////////////////////////////////////
        //  Click on the link to view all current entries
        /////////////////////////////////////////////////////////////////////////////////
        assertTrue ( clickLink ( "appCurrentEntriesMine") );
        WebLink suppression_entries[] = getLinksWithID ( "suppressionEntryLink" );
        Vector <Integer> suppression_ids = new Vector <Integer> ();
        for ( int counter = 0; counter < suppression_entries.length; counter++ )
        {
            if ( suppression_entries[counter].getText() != null )
            {
                suppression_ids.add ( new Integer ( suppression_entries[counter].getText() ) );
            }
        }

        return suppression_ids;
    }
}
