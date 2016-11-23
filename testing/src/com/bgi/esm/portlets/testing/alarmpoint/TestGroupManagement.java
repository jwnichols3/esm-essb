package com.bgi.esm.portlets.testing.alarmpoint;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class TestGroupManagement extends AlarmpointTestcase
{
    final private static Logger _log      = Logger.getLogger ( TestGroupManagement.class );

    private String web_login_first_name   = null;
    private String web_login_last_name    = null;
    private String web_login_username     = null;
    private String web_login_email        = null;
    private String web_login_phone_work   = null;
    private String web_login_phone_mobile = null;

    public TestGroupManagement ( String param ) 
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

    final private static String group_default_team_default_members[] = { "linden", "nichj" };


    public void doNottestAddNewGroup() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Start the test case
        initialize();

        //  Create a group with default team members
        addNewGroup_People ( group_default_team_default_members );
    }

    public void doNottestAddNewGroupAndDelete() throws IOException, SAXException, ParserConfigurationException, 
           TransformerException, SQLException, ClassNotFoundException
    {
        //  Start the test case
        initialize();

        //  Create a group with default team members
        String new_group = addNewGroup_People ( group_default_team_default_members );

        //  Delete the test group
        assertTrue ( deleteGroupWithName ( new_group ) );
    }

    public void doNottestAddNewGroupChangeGroupByDeletingUser() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        //  Start the test case
        initialize();

        //  Create a group with default team members
        String new_group = addNewGroup_People ( group_default_team_default_members );

        //  View the information of the group just created
        viewGroupWithName ( new_group );

        clickLink ( "Teams in Group" );

        clickLink ( new_group + " - Default Team" );

        WebForm form               = null;
        SubmitButton submit_button = null;

        form = getFormWithName ( "coverageDetailForm" );
        submit_button = form.getSubmitButton ( "listTableDetails.removeSelectedMembers" );
        form.setCheckbox ( "action0", true );
        submitForm ( form, submit_button );
    }

    public void testAddNewGroupDeleteTeam() throws IOException, SAXException, ParserConfigurationException, TransformerException,
            SQLException, ClassNotFoundException
    {
        WebForm form = null;
        SubmitButton submit_button = null;
        //  Start the test case
        initialize();

        //  Create a group with default team members
        String new_group = addNewGroup_People ( group_default_team_default_members );

        //  View the information of the group just created
        viewGroupWithName ( new_group );

        // View teams for group just created
        clickLink( "Teams in Group" );
        
        Connection con = getDatabaseConnection();
        PreparedStatement ps_retrieve_recipient_id = con.prepareStatement ( "SELECT recipient_id FROM alarmpoint3.recipients WHERE (target_name=? AND recipient_cat='team') ORDER BY recipient_id" );
        ps_retrieve_recipient_id.setString ( 1, new_group );

        ResultSet results = ps_retrieve_recipient_id.executeQuery();
        assertTrue ( results.next() );

        int recipient_id = results.getInt ( 1 );

        _log.info ( "Found recipient id: " + recipient_id );

        form = getFormWithName ( "TeamsinthisGroupform" ); // ( "coverageDetailForm" );
        submit_button = form.getSubmitButton ( "removeButton" );
        form.setCheckbox ( "action" + recipient_id, true );
        submitForm ( form, submit_button );
    }

    public void testAddNewGroupSecondRotation() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
        WebForm form = null;
        SubmitButton submit_button = null;
        //  Start the test case
        initialize();

        //  Create a group with default team members
        String new_group = addNewGroup_People ( group_default_team_default_members );

        //  View the information of the group just created
        viewGroupWithName ( new_group );
    }
}
