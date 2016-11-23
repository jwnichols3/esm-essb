package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.DataMapPanel;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpAttributePanel;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpCookiePanel;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpHeaderPanel;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpParameterPanel;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpRequestPanel01;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpRequestPanel02;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.swing.HttpRequestPanel03;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;
public class GUISkeleton extends JPanel implements ActionListener
{
    final private static String application_title   = "Sample Application Title";

    @SuppressWarnings ( "unused" )
    final private static Logger _log                = Logger.getLogger ( GUISkeleton.class );

    /**
     *
     **/
    final private static long serialVersionUID = -1633342288567995225L;

    private static Properties ApplicationProperties = null;
    private static Properties VersionProperties     = null;
    private static Properties DatabaseProperties    = null;
    private static Properties EnvironmentProperties = null;
    private static String FileSeparator             = null;
    private static String HomePath                  = null;

    /**
     *  GUI Form panels
     */
    private DataMapPanel           dataMapPanel            = null;
    private HttpAttributePanel     httpAttributePanel      = null;
    private HttpCookiePanel        httpCookiePanel         = null;
    private HttpHeaderPanel        httpHeaderPanel         = null;
    private HttpParameterPanel     httpParameterPanel      = null;
    private HttpRequestPanel01     httpRequestPanel01      = null;
    private HttpRequestPanel02     httpRequestPanel02      = null;
    private HttpRequestPanel03     httpRequestPanel03      = null;
    public String getApplicationTitle()
    {
        return application_title;
    }

    public Properties getApplicationProperties()
    {
        return ApplicationProperties;
    }

    public Properties getDatabaseProperties()
    {
        return DatabaseProperties;
    }

    public Properties getVersionProperties()
    {
        return VersionProperties;
    }

    public String getHomePath()
    {
        return HomePath;
    }


    /**
     *  Placeholders for the active records
     */
    private IDataMap         ActiveRecordForDataMap         = null;
    private IHttpAttribute   ActiveRecordForHttpAttribute   = null;
    private IHttpCookie      ActiveRecordForHttpCookie      = null;
    private IHttpHeader      ActiveRecordForHttpHeader      = null;
    private IHttpParameter   ActiveRecordForHttpParameter   = null;
    private IHttpRequest     ActiveRecordForHttpRequest     = null;

    /**
     *  Constructor for this application
     */
    public GUISkeleton()
    {
        super ( new GridLayout ( 1, 1 ) );

        initialize();
    }

    /**
     *  Initializes the application
     */
    private void initialize()
    {
        //  Read application properties
        ApplicationProperties = Configuration.getInstance().getApplicationProperties();
        VersionProperties     = Configuration.getInstance().getVersionProperties();

        //  Initialize all the tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        dataMapPanel = new DataMapPanel();
        tabbedPane.addTab ( "DataMap", null, dataMapPanel, "Edit records in the 'data_map' table" );

        httpAttributePanel = new HttpAttributePanel();
        tabbedPane.addTab ( "HttpAttribute", null, httpAttributePanel, "Edit records in the 'http_attribute' table" );

        httpCookiePanel = new HttpCookiePanel();
        tabbedPane.addTab ( "HttpCookie", null, httpCookiePanel, "Edit records in the 'http_cookie' table" );

        httpHeaderPanel = new HttpHeaderPanel();
        tabbedPane.addTab ( "HttpHeader", null, httpHeaderPanel, "Edit records in the 'http_header' table" );

        httpParameterPanel = new HttpParameterPanel();
        tabbedPane.addTab ( "HttpParameter", null, httpParameterPanel, "Edit records in the 'http_parameter' table" );

        httpRequestPanel01 = new HttpRequestPanel01();
        tabbedPane.addTab ( "HttpRequest - Page 1", null, httpRequestPanel01, "Edit records in the 'http_request' table" );

        httpRequestPanel02 = new HttpRequestPanel02();
        tabbedPane.addTab ( "HttpRequest - Page 2", null, httpRequestPanel02, "Edit records in the 'http_request' table" );

        httpRequestPanel03 = new HttpRequestPanel03();
        tabbedPane.addTab ( "HttpRequest - Page 3", null, httpRequestPanel03, "Edit records in the 'http_request' table" );
        add ( tabbedPane );
    }

    public void actionPerformed ( ActionEvent event )
    {
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'DataMap
     */
    public boolean saveActiveRecordForDataMap()
    {
        return false;
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'HttpAttribute
     */
    public boolean saveActiveRecordForHttpAttribute()
    {
        return false;
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'HttpCookie
     */
    public boolean saveActiveRecordForHttpCookie()
    {
        return false;
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'HttpHeader
     */
    public boolean saveActiveRecordForHttpHeader()
    {
        return false;
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'HttpParameter
     */
    public boolean saveActiveRecordForHttpParameter()
    {
        return false;
    }

    /**
     *  Retrieve values from the forms and save the values into the active record for 'HttpRequest
     */
    public boolean saveActiveRecordForHttpRequest()
    {
        return false;
    }

    private static void retrieveHomePath()
    {
        EnvironmentProperties = new Properties();
        try
        {
            Runtime rt        = Runtime.getRuntime();
            Process p         = rt.exec ( "cmd.exe /c set" );
            BufferedReader br = new BufferedReader ( new InputStreamReader( p.getInputStream() ) );
            String line       = null;
            while ( ( line = br.readLine() ) != null )
            {
                int idx = line.indexOf( '=' );
                String key = line.substring( 0, idx );
                String value = line.substring( idx+1 );
                EnvironmentProperties.setProperty( key, value );
                // System.out.println( key + " = " + value );
            }

            System.out.println ( "Home drive: " + EnvironmentProperties.getProperty ( "HOMEDRIVE" ) );
            System.out.println ( "Home property: " + EnvironmentProperties.getProperty ( "HOMEPATH"  ) );

            FileSeparator      = System.getProperty ( "file.separator" );
            StringBuilder path = new StringBuilder ( EnvironmentProperties.getProperty ( "HOMEDRIVE" ) );
            path.append ( EnvironmentProperties.getProperty ( "HOMEPATH" ) );
            path.append ( FileSeparator );

            System.out.println ( "Home path: " + path.toString() );
            HomePath = path.toString();
        }
        catch ( IOException exception )
        {
            System.err.println ( "ERROR: Could not retrieve system environment variables" );
        }
    }

    private static void createAndShowGUI()
    {
        retrieveHomePath();

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Test Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUISkeleton();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setSize ( 1024, 768 );
        //frame.getContentPane().add(new PortletPropertiesEditor(), BorderLayout.CENTER);
        frame.getContentPane().add(newContentPane, BorderLayout.CENTER);

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main ( String args[] )
    {
        //  Schedule a job for the event-dispatching thread:
        //      creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater ( new Runnable ()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            }
        );
    }
    /**
     *  Getters for the active records
     */
    public IDataMap         getActiveRecordForDataMap()
    {
        return ActiveRecordForDataMap;
    }

    public IHttpAttribute   getActiveRecordForHttpAttribute()
    {
        return ActiveRecordForHttpAttribute;
    }

    public IHttpCookie      getActiveRecordForHttpCookie()
    {
        return ActiveRecordForHttpCookie;
    }

    public IHttpHeader      getActiveRecordForHttpHeader()
    {
        return ActiveRecordForHttpHeader;
    }

    public IHttpParameter   getActiveRecordForHttpParameter()
    {
        return ActiveRecordForHttpParameter;
    }

    public IHttpRequest     getActiveRecordForHttpRequest()
    {
        return ActiveRecordForHttpRequest;
    }

}
