package com.bgi.esm.portlets.testing;

import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import com.meterware.httpunit.ClientProperties;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebTable;
import com.bgi.esm.portlets.testing.WebTestCase;

/**
 *  Embodies all the common functionality of user tasks in the Alarmpoint web GUI
 */
public class AlarmpointTestcase extends WebTestCase
{
    final private static Logger _log       = Logger.getLogger ( AlarmpointTestcase.class );
    protected static Properties properties = null;
    protected static DecimalFormat df      = null;
    private static Connection con          = null;
    protected static String driver_name    = null;
    protected static String connection_url = null;
    protected static String db_username    = null;
    protected static String db_password    = null;
    protected String Username              = null;
    protected String Password              = null;
    protected String Hostname              = null;
    protected String Portnum               = null;
    protected Date start_time              = null;

    public static void main ( String args[] ) throws Exception
    {
        AlarmpointTestcase testcase = new AlarmpointTestcase ( "sample_testcase" );
        testcase.initialize();
        testcase.goToProfileHome();
        testcase.goToAlertsHome();
        testcase.goToUsersHome();
        testcase.goToGroupsHome();
        testcase.goToReportsHome();
        testcase.goToReportsHome();
        testcase.goToMessagingHome();
        testcase.goToAdminHome();
        testcase.goToDeveloperHome();
    }

    /**
     * Default constructor for tests based on unit.framework.TestCase 
     * 
     * @param param
     */
    public AlarmpointTestcase ( String param )
    {
    	super ( param );
    	
    	HttpUnitOptions.setScriptingEnabled ( false );
    	
    	timestamp = param;
    }

    /**
     *  Read the necessary properties from the default properties file
     */
    public boolean initialize() throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
        if ( null == properties )
        {
            _log.info ( "Initialized common AlarmpointTestcase properties" );

            properties     = new Properties(); 
            Class c        = AlarmpointTestcase.class;
            ClassLoader cl = c.getClassLoader();
            InputStream is = cl.getResourceAsStream ( "alarmpoint.properties" );
            properties.load ( is );           
        }

        return initialize ( properties );
    }

    /**
     * Sets up the testcase with the necessary parameters from a Properties object
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     *
     */
    public boolean initialize ( Properties new_properties )
    	throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
        if ( null == properties )
        {
            properties = new_properties;
        }

    	Hostname = properties.getProperty ( "alarmpoint.testcase.hostname", "localhost" );
    	Username = properties.getProperty ( "alarmpoint.testcase.username", "test@alarmpoint.com" );
    	Password = properties.getProperty ( "alarmpoint.testcase.password", "test" );
        Portnum  = properties.getProperty ( "alarmpoint.testcase.port_num", "8888" );
        driver_name    = properties.getProperty ( "alarmpoint.database.driver", "net.sourceforge.jtds.jdbc.Driver" );
        connection_url = properties.getProperty ( "alarmpoint.database.connection_url" );
        db_username    = properties.getProperty ( "alarmpoint.database.username" );
        db_password    = properties.getProperty ( "alarmpoint.database.password" );

        assertNotNull ( connection_url );
    	
    	return initialize ( Hostname, Username, Password );
    }

    /**
     *  Retrieves a database connection to the alarmpoint database
     */
    public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException
    {
        if ( null == con )
        {
            Class.forName ( driver_name );
            con = DriverManager.getConnection ( connection_url, db_username, db_password );
        }

        return con;
    }

    /**
     *  Logs into to an instance of the Alarmpoint Web GUI
     *
     *  @param hostname the name fo the host to login to
     *  @param username Alarmpoint web GUI username
     *  @param password Alarmpoint web GUI password
     *
     *  @return true if successful login, false otherwise
     */
    public boolean initialize ( String hostname, String username, String password )
    	throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	//  Disable stopping on unknown Javascript errors

        //  Initialize parameters
        Username         = username;
        Password         = password;
        Hostname         = "http://" + hostname;
        start_time       = new Date();
        
        //  Create the timestamp
        initTimestamp();
        
        //  Set the counter format
        df = new DecimalFormat ( "0000" );
        
        //  Create a Webconversation with the webserver
        wc         = new WebConversation();
        request    = new GetMethodWebRequest ( Hostname + ":" + Portnum + "/alarmpoint" );
        HttpUnitOptions.setScriptingEnabled ( false );
        	response   = wc.getResponse ( request );
        HttpUnitOptions.setScriptingEnabled ( false );
        
        ClientProperties cp = wc.getClientProperties();
        cp.setAcceptGzip( true );
        
        CapturePage();

        return login();
    }

    /**
     *  Causes the HttpUnit user agent to navigate to the specified URL
     *
     *  @param url URL to navigate to
     */
    public boolean navigateTo ( String url ) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        request = new GetMethodWebRequest ( Hostname + ":" + Portnum + url );

        HttpUnitOptions.setScriptingEnabled ( false );
        	response   = wc.getResponse ( request );
        HttpUnitOptions.setScriptingEnabled ( false );

        return true;
    }

    /**
     * If the testcase has not logged into Liferay, then login
     * 
     * @return true if successful login, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean login() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        WebForm forms[]       = response.getForms();
        WebForm form          = null;
        String login_params[] = null;

        //  Look for a form called "signInForm"
        form     = response.getFormWithName ( "signInForm" );
        if ( null == form )
        {
            _log.error ( "Could not find login form" );

            return false;
        }

        //  Attempt to logni
        HttpUnitOptions.setScriptingEnabled ( false );
	        request  = form.getRequest();
	            request.setParameter ( "username", Username );
	            request.setParameter ( "password", Password );
	        response = wc.getResponse ( request );
        HttpUnitOptions.setScriptingEnabled ( false );

        CapturePage();  // 0002

        //  If we see the same form, then we know the login failed
        form = response.getFormWithName ( "signInForm" );
        if ( null == form )
        {
            _log.info ( "Login successful" );

            return true;
        }
        else
        {
            _log.error ( "Login failed" );

            return false;
        }
    }

    /**
     *  Navigate to the "Profile" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToProfileHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Profile" );
    }

    /**
     *  Navigate to the "Alerts" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToAlertsHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Alerts" );
    }

    /**
     *  Navigate to the "Users" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToUsersHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Users" );
    }

    /**
     *  Navigate to the "Groups" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToGroupsHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Groups" );
    }

    /**
     *  Navigate to the "Reports" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToReportsHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Reports" );
    }

    /**
     *  Navigate to the "Messaging" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToMessagingHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Messaging" );
    }

    /**
     *  Navigate to the "Admin" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToAdminHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Admin" );
    }

    /**
     *  Navigate to the "Developer" section
     *
     *  @return true if succesfully navigated, false otherwise
     */
    public boolean goToDeveloperHome() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return clickLink ( "Developer" );
    }

    /**
     *  Add a new user to Alarmpoint
     */
    public String addNewUser()
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return addNewUser ( null, null, null );
    }

    /**
     *  Add new alarmpoint user
     */
    public String addNewUser ( String user_id, String first_name, String last_name ) 
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        goToUsersHome();

        clickLink ( "Add User" );

        WebForm form = getFormWithName ( "personDetailsForm" );

        //  We want the "Full Access User" role
        String options[]  = form.getOptions      ( "unselectedRoles" );
        String opt_vals[] = form.getOptionValues ( "unselectedRoles" );
        int num_options   = options.length;
        int found         = -1;

        for ( int counter = 0; counter < num_options; counter++ )
        {
            if ( options[counter].equals ( "Full Access User" ) )
            {
                found = counter;
                break;
            }
        }
        String unselected_values[] = new String[options.length-1];
        int unwanted_counter = 0;
        for ( int counter = 0; counter < options.length-1; counter++ )
        {
            if ( counter != found )
            {
                unselected_values[unwanted_counter++] = opt_vals[counter];
            }
        }
        form.setParameter ( "unselectedRoles", new String[] { opt_vals[found] } );

        if ( found < 0 )
        {
            _log.error ( "Could not find the desired user role" );

            return null;
        }

        /*
        String selected[] = form.getParameterValues ( "selectedRoles" );
        _log.info ( "Num roles selected: " + selected.length );
        for ( int counter = 0; counter < selected.length; counter++ )
        {
            _log.info ( "selected role: " + selected[counter] );
        }
        String unselected[] = form.getParameterValues ( "unselectedRoles" );
        _log.info ( "Num roles unselected: " + unselected.length );
        for ( int counter = 0; counter < unselected.length; counter++ )
        {
            _log.info ( "unselected role: " + unselected[counter] );
        }
        //*/

        //  We want the Rancho site value
        options     = form.getOptions      ( "site" );
        opt_vals    = form.getOptionValues ( "site" );
        num_options = options.length;
        found       = -1;
        _log.info ( "Num options: " + num_options );
        for ( int counter = 0; counter < num_options; counter++ )
        {
            _log.info ( "Option: ( " + options[counter] + ", " + opt_vals[counter] + " )" );
            if ( options[counter].equals ( "Rancho Cordova" ) )
            {
                found = counter;
                break;
            }
        }
        assertTrue ( found >= 0 );
        String site_value = opt_vals[found];

        String web_login = null;

        if ( null == user_id )
        {

            //  Set the timestamp for this test case
            int timestamp = (int) (System.currentTimeMillis() / 60000L);
            web_login = "test_user_" + timestamp;
            _log.info ( "No user_id defined.  Using default userid: " + web_login );
        }
        else
        {
            web_login = user_id;
        }

        String web_firstname = null;
        if ( null == first_name )
        {
            web_firstname = "Testfirstname";
        }
        else
        {
            web_firstname = first_name;
        }

        String web_lastname = null;
        if ( null == last_name )
        {
            web_lastname = "Testlastname";
        }
        else
        {
            web_lastname = last_name;
        }

        //  Fill out the form
        form.setParameter ( "userCode",  web_login     );
        form.setParameter ( "firstName", web_firstname );
        form.setParameter ( "lastName",  web_lastname  );

        SubmitButton submit_button = form.getSubmitButton ( "addButton" );
        submitForm ( form, submit_button );

        submit_button = form.getSubmitButton ( "saveButton" );
        submitForm ( form, submit_button );

        form        = getFormWithName      ( "webLoginDetailsForm" );
        options     = form.getOptions      ( "activeDirectoryDomain" );
        opt_vals    = form.getOptionValues ( "activeDirectoryDomain" );
        num_options = options.length;
        found       = -1;

        for ( int counter = 0; counter < num_options; counter++ )
        {
            if ( options[counter].indexOf ( "insidelive.net" ) >= 0 )
            {
                found = counter;
            }
        }
        assertTrue ( found >= 0 );
        form.setParameter ( "activeDirectoryDomain", opt_vals[found]          );
        form.setParameter ( "credentialTypes",       "NATIVE"                 );
        form.setParameter ( "webLogin",              "test_user_" + timestamp );
        form.setParameter ( "webPassword",           "test_password"          );
        form.setParameter ( "verifyWebPassword",     "test_password"          );
        form.setCheckbox  ( "useNTStyle",            true                     );

        submit_button = form.getSubmitButton ( "saveButton" );
        submitForm ( form, submit_button );

        return web_login;
    }

    /**
     *  Navigates to the information page of the specified username (NT login)
     */
    public void searchForUsername ( String username ) throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Go to the "Users" page and search for the test username
        goToUsersHome();

        _log.info ( "Filling out search form" );
        WebForm user_search_form = getFormWithName ( "genericSearchPersonsForm" );
            //  Default form values
            user_search_form.setParameter ( "resultSize",                        "10"           );
            user_search_form.setParameter ( "basicSearchGroup.searchTypeBasic",  "BEGINS_WITH"  );
            user_search_form.setParameter ( "basicSearchGroup.searchFieldBasic", "WEB_LOGIN"    );
            user_search_form.setParameter ( "isAdvancedGroupDisplayed",          "false"        );
            //user_search_form.setParameter ( "requestedFormAction",               "Site"         );
            user_search_form.setParameter ( "basicSearchGroup.searchTextBasic",  username       );

            //  Populate search field with username
            String names[] = user_search_form.getParameterNames();
            for ( int counter = 0; counter < names.length; counter++ )
            {
                _log.info ( "Form attrib: " + names[counter] );
            }

            SubmitButton submit_button = user_search_form.getSubmitButton ( "search" );
        _log.info ( "Submitted search form" );
        submitForm ( user_search_form, submit_button );

        //  The following block of code circumvents an error that HttpUnit will encounter if you click on the 
        //  link with the username
        WebLink link = searchForLink ( username );
        assertNotNull ( link );
        _log.info ( "Found user link: " + link.getURLString() );
        navigateTo ( link.getURLString() );
        CapturePage();
    }
    public String addNewGroup_People ( Object members[] )
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        //  Set the timestamp for this test case
        int timestamp = (int) (System.currentTimeMillis() / 60000L);
        _log.info ( "Testcase timestamp: " + timestamp );
        String name_of_group = "Test Group " + timestamp;

        return addNewGroup_People ( name_of_group, members );
    }

    public String addNewGroup_People ( String group_name, Object members[] )
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        return addNewGroup_People ( group_name, "Default Team", members );
    }

    /**
     *  Given an instance of the table taken from the group's page, it will check to see
     *  if a given team is part of a group.
     *
     *  NOTE:  No data validation is performed on the table
     *
     *  @param table the WebTable instance representing the table of teams
     *  @param group_name the name of the group
     *  @param team_name the name of the team
     *  @return true if the team is part of the group, false otherwise
     */
    private static boolean doesTableHaveTeam ( WebTable table, String group_name, String team_name )
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        //  Check to see if the person exists
        if ( null != table )
        {
            int num_rows = table.getRowCount();
            int num_cols = table.getColumnCount();

            if ( num_rows > 0 )
            {
                for ( int counter = 1; counter < num_rows; counter++ )
                {
                    String cell = table.getTableCell ( counter, 1 ).getText();
                    _log.info ( "\tFound team: " + cell );
                    if ( cell.equals ( team_name ) )
                    {
                        return true;
                    }
                }

                return false;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private static boolean doesTableHaveMember ( WebTable table, String group_name, String team_name, String member_name ) throws Exception
    {
        //  Check to see if the person exists
        if ( null != table )
        {
            WeakReference <StringBuilder> message1 = new WeakReference <StringBuilder> ( new StringBuilder() );
            message1.get().append ( "( Group, Team ) = ( " );
            message1.get().append ( group_name );
            message1.get().append ( ", " );
            message1.get().append ( team_name );
            message1.get().append ( " ) already exists" );
            _log.info ( message1.get().toString() );

            int num_rows = table.getRowCount();
            int num_cols = table.getColumnCount();
            boolean found_user = false;

            if ( num_rows > 0 )
            {
                for ( int r = 1; r < num_rows; r++ )
                {
                    String cell = table.getTableCell ( r, 4 ).getText();

                    if ( cell.indexOf ( member_name ) > 0 )
                    {
                        String temp_string = cell.substring ( cell.indexOf ( member_name ) );
                        temp_string = temp_string.substring ( 0, member_name.length() );
                        if ( temp_string.equals ( member_name ) )
                        {
                            found_user = true;

                            break;
                        }
                    }
                }

                if ( true == found_user )
                {
                    WeakReference <StringBuilder> message2 = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message2.get().append ( "Existing ( Group, Team ) = ( " );
                    message2.get().append ( group_name );
                    message2.get().append ( ", " );
                    message2.get().append ( team_name );
                    message2.get().append ( " ) already has member=" );
                    message2.get().append ( member_name );
                    _log.info ( message2.get().toString() );
                }
            }

            return found_user;
        }
        else
        {
            return false;
        }
    }


    /**
     *  Adds the specified usernames (NT logins) to an the default team in a default
     *  Alarmpoint group  (testXXXXX format, where XXXXX is the timestamp)
     *
     *  @param members a String array of the usernames (NT logins) to add into a group
     */
    public String addNewGroup_People ( String group_name, String team_name, Object members[] )
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        _log.info ( "Adding Group=" + group_name + ",team=" + team_name );

        if (( null == team_name ) || ( team_name.equals ( "(fixed)" )))
        {
            team_name = "Default Team";
        }

        SubmitButton submit_button = null;
        WebForm form               = null;
        WebLink link               = null;
        String options[]           = null;
        String opt_vals[]          = null;
        int found                  = 0;
        if (( null == members ) || ( members.length < 1 ))
        {
            _log.error ( "Must specify at least 1 member to add when creating a new group" );
        }

        goToGroupsHome();

        WebTable table = searchForTable ( "list-table" );
        boolean found_group = false;
        if ( null != table )
        {
            int num_rows = table.getRowCount();
            int num_cols = table.getColumnCount();
            if ( num_rows > 1 )
            {
                _log.info ( "Found Groups Table!" );
                _log.info ( "\tNum rows: " + num_rows );
                _log.info ( "\tNum cols: " + num_cols );
                for ( int r = 1; r < num_rows; r++ )
                {
                    String table_group_name = table.getTableCell( r, 1 ).getText();
                    _log.info ( "Found group: ( " + r + ", 1 ) = " + table_group_name );

                    if ( table_group_name.equals ( group_name ) )
                    {
                        found_group = true;
                        break;
                    }
                }
            }
        }

        if ( true == found_group )
        {
            _log.info ( "Found the group: " + group_name );

            clickLink ( group_name );

            clickLink ( "Teams in Group" );

            clickLink ( "Add New" );

            form = getFormWithName ( "setTeamForCoverageForm" );
                form.setParameter ( "name",        team_name );
                form.setParameter ( "description", ""        );
            submit_button = form.getSubmitButton ( "continueButton" );
            submitForm ( form, submit_button );

            link = searchForLink ( "Add Person(s)" );

            assertNotNull ( link );
            _log.info ( "Found 'Add Person(s)' link: " + link.getURLString() );
            navigateTo ( link.getURLString() );
            CapturePage();

            //  A form will pop up in a different page, but HttpUnit can still capture it.
            //  We will loop through the list of members to add
            for ( int counter = 0; counter < members.length; counter++ )
            {
                form = getFormWithName ( "genericSearchPersonsForm" );
                form.setParameter ( "basicSearchGroup.searchTextBasic", members[counter].toString() );
                submit_button = form.getSubmitButton ( "search" );
                submitForm ( form, submit_button );

                form = getFormWithName ( "genericSearchPersonsForm" );
                String param_names[] = form.getParameterNames();
                for ( int c = 0; c < param_names.length; c++ )
                {
                    if ( param_names[c].indexOf ( "action_left." ) >= 0 )
                    {
                        _log.info ( "Set checkbox: " + param_names[c] );
                        form.setCheckbox ( param_names[c], true );
                    }
                }
                submit_button = form.getSubmitButton ( "addButton" );
                submitForm ( form, submit_button );
            }

            //  Save the team members
            _log.info ( "Clicking on save button" );
            form = getFormWithName ( "genericSearchPersonsForm" );
            submit_button = form.getSubmitButton ( "saveButton" );
            submitForm ( form, submit_button );

            //  Save the escalation path
            form = getFormWithName ( "TeamMembersList" );
            //submit_button = form.getSubmitButton ( "saveButton" );
            submit_button = form.getSubmitButton ( "saveDetails" );
            submitForm ( form, submit_button );

            //  Save the role as "Full Access User"
            form = getFormWithName ( "avaliableAdminGroupsScheduleMemebersForm" );
            options  = form.getOptions      ( "availableAdminGroups" );
            opt_vals = form.getOptionValues ( "availableAdminGroups" );
            found = -1;
            for ( int counter = 0; counter < options.length; counter++ )
            {
                if ( options[counter].indexOf ( "Full Access User" ) >= 0 )
                {
                    found = counter;
                    break;
                }
            }
            assertTrue ( found >= 0 );
            form.setParameter ( "availableAdminGroups",       opt_vals[found] );

            submit_button = form.getSubmitButton ( "addButton" );
            submitForm ( form, submit_button );

            form = getFormWithName ( "avaliableAdminGroupsScheduleMemebersForm" );
            submit_button = form.getSubmitButton ( "finishButton" );
            submitForm ( form, submit_button );


            return group_name;
        }
        else
        {
            _log.info ( "Creating new group: " + group_name );

            clickLink ( "Add New" );

            //  Fill out the form with new group information
            form       = getFormWithName      ( "coverageDetailForm" );
            options    = form.getOptions      ( "allDetails.timezoneList" );
            opt_vals   = form.getOptionValues ( "allDetails.timezoneList" );

            //  Find the US/Pacific timezone
            for ( int counter = 0; counter < options.length; counter++ )
            {
                if ( options[counter].indexOf ( "US/Pacific" ) >= 0 )
                {
                    found = counter;
                    break;
                }
            }
            assertTrue ( found >= 0 );

                form.setParameter ( "allDetails.name",               group_name      ); 
                form.setParameter ( "allDetails.status",             "true"          );
                form.setParameter ( "allDetails.allowDuplicates",    "true"          );
                form.setParameter ( "allDetails.scheduleType",       "use_default"   );
                form.setParameter ( "allDetails.summary",            "Test user created by test suite" );
                form.setParameter ( "allDetails.timezoneList",       opt_vals[found] );
                form.setParameter ( "allDetails.useEmergencyDevice", "true"          );  //  This is really the "Use Default Devices" checkbox
            submit_button = form.getSubmitButton ( "saveButton" );
            if ( null == submit_button )
            {
                _log.info ( "Could not find SubmitButton ( saveButton ) ... looking for SubmitButton ( continueButton )" );
                submit_button = form.getSubmitButton ( "continueButton" );
            }
            if ( null == submit_button )
            {
                throw new NullPointerException ( "Could not find a proper SubmitButton to submit form" );
            }
            submitForm ( form, submit_button );

            //  Create the default team
            form = getFormWithName ( "scheduleSetTeamForm" );
            if ( null == form )
            {
                //  The group already exists, so we go through the "Find Groups" method
                clickLink ( "Find Groups" );

                form = getFormWithName ( "genericSearchGroupsForm" );
                    form.setParameter ( "searchDropDowns.0.searchText", group_name );
                submit_button = form.getSubmitButton ( "search" );

                assertNotNull ( "Could not find SubmitButton ( seach )", submit_button );
                submitForm ( form, submit_button );

                link = searchForLink ( group_name );
                assertNotNull ( link );
                _log.info ( "Found group link: " + link.getURLString() );
                navigateTo ( link.getURLString() );
                CapturePage();

                link = searchForLink ( "Team Members" );
                if ( null == link )
                {
                    clickLink ( "Teams in Group" );

                    table = searchForTable ( "list-table" );
                    int num_rows = table.getRowCount();
                    int num_cols = table.getColumnCount();

                    link = searchForLink ( team_name );
                    if ( null == link )
                    {
                        boolean does_user_exist = doesTableHaveTeam ( table, group_name, team_name );

                        _log.info ( "Team exist? " + ( does_user_exist ? "yes" : "no" ) );
                    }
                    _log.info ( "Found team link: " + link.getURLString() );
                    navigateTo ( link.getURLString() );
                    CapturePage();

                }
                assertNotNull ( link );
                _log.info ( "Found 'Team Members' link: " + link.getURLString() );
                navigateTo ( link.getURLString() );
                CapturePage();
            }
            else
            {
                assertNotNull ( form );
                    form.setParameter  ( "name",                team_name );
                    //form.setParameter ( "copyFromTeam",        "NONE" );
                    //form.setParameter ( "createTeamSelection", "1"    );
                    form.setParameter  ( "description",         ""      );
                    form.setCheckbox   ( "roundRobin",          false   );
                    //form.setParameter ( "reuse",               ""     );
                submit_button = form.getSubmitButton ( "continueButton" );
                submitForm ( form, submit_button );
            }

            //  Click on the "Add Person(s)" link
            link = searchForLink ( "Add Person(s)" );
            assertNotNull ( link );
            _log.info ( "Found 'Add Person(s)' link: " + link.getURLString() );
            navigateTo ( link.getURLString() );
            CapturePage();

            //  A form will pop up in a different page, but HttpUnit can still capture it.
            //  We will loop through the list of members to add
            for ( int counter = 0; counter < members.length; counter++ )
            {
                String member_name = members[counter].toString().trim();

                if ( member_name.length() < 1 )
                {
                    continue;
                }

                _log.info ( "Adding member: " + members[counter].toString() );
                form = getFormWithName ( "genericSearchPersonsForm" );
                form.setParameter ( "basicSearchGroup.searchTextBasic", members[counter].toString() );
                submit_button = form.getSubmitButton ( "search" );
                submitForm ( form, submit_button );

                form = getFormWithName ( "genericSearchPersonsForm" );
                String param_names[] = form.getParameterNames();
                for ( int c = 0; c < param_names.length; c++ )
                {
                    if ( param_names[c].indexOf ( "action_left." ) >= 0 )
                    {
                        _log.info ( "Set checkbox: " + param_names[c] );
                        form.setCheckbox ( param_names[c], true );
                    }
                }
                submit_button = form.getSubmitButton ( "addButton" );
                submitForm ( form, submit_button );
            }

            //  Save the team members
            _log.info ( "Clicking on save button" );
            form = getFormWithName ( "genericSearchPersonsForm" );
            submit_button = form.getSubmitButton ( "saveButton" );
            submitForm ( form, submit_button );

            //  Save the escalation path
            form = getFormWithName ( "TeamMembersList" );
            submit_button = form.getSubmitButton ( "saveButton" );
            submitForm ( form, submit_button );

            //  Save the role as "Full Access User"
            form = getFormWithName ( "avaliableAdminGroupsScheduleMemebersForm" );
            options  = form.getOptions      ( "availableAdminGroups" );
            opt_vals = form.getOptionValues ( "availableAdminGroups" );
            found = -1;
            for ( int counter = 0; counter < options.length; counter++ )
            {
                if ( options[counter].indexOf ( "Full Access User" ) >= 0 )
                {
                    found = counter;
                    break;
                }
            }
            assertTrue ( found >= 0 );
            form.setParameter ( "availableAdminGroups",       opt_vals[found] );

            submit_button = form.getSubmitButton ( "addButton" );
            submitForm ( form, submit_button );

            form = getFormWithName ( "avaliableAdminGroupsScheduleMemebersForm" );
            submit_button = form.getSubmitButton ( "finishButton" );
            submitForm ( form, submit_button );

            return group_name;
        }
    }

    /**
     *  Navigates through the Alarmpoint web GUI and delete the specified group
     *
     *  @param group_name the name of the group to view
     *  @param SAXException
     *  @param IOException
     *  @param ParserConfigurationException
     *  @param TransformerException
     */
    public boolean viewGroupWithName ( String group_name )
        throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        //  Create a group with default team members
        goToGroupsHome();

        // Flip through pages until group link is found
        WebLink groupLink = null;
        boolean stillPagesToLookAt = false;
        
        do
        {
        	groupLink = searchForLink( group_name );
        	stillPagesToLookAt = ( groupLink == null ) ? clickLink(">") : false;
        }
        while (stillPagesToLookAt);
        
        //  Click on the link that will take you to the group information
        return (groupLink == null) ? false : clickLink ( groupLink );
    }

    /**
     *  Navigates through the Alarmpoint web GUI and delete the specified group
     *
     *  @param group_name the name of the group to delete
     *  @throws SAXException
     *  @throws IOException
     *  @throws ParserConfigurationException
     *  @throws TransformerException
     *  @throws SQLException
     *  @throws ClassNotFoundException
     */
    public boolean deleteGroupWithName ( String group_name )
        throws SAXException, IOException, ParserConfigurationException, TransformerException, SQLException, ClassNotFoundException
    {
        WebForm form               = null;
        SubmitButton submit_button = null;

        //  Create a group with default team members
        goToGroupsHome();

        Connection con = getDatabaseConnection();
        PreparedStatement ps_retrieve_recipient_id = con.prepareStatement ( "SELECT recipient_id FROM alarmpoint3.recipients WHERE (target_name=? AND recipient_cat='group') ORDER BY recipient_id" );
        ps_retrieve_recipient_id.setString ( 1, group_name );
        ResultSet results = ps_retrieve_recipient_id.executeQuery();

        assertTrue ( "Could not find group='" + group_name + "'", results.next() );

        int recipient_id = results.getInt ( 1 );

        if ( _log.isInfoEnabled() )
        {
            _log.info ( "Found recipient ID for '" + group_name + "' = " + recipient_id );
        }

        form          = getFormWithName ( "CoveragesList" );
        submit_button = form.getSubmitButton ( "removeButton" );
        form.setCheckbox ( "action" + recipient_id, true );
        submitForm ( form, submit_button );

        return true;
    }
}
