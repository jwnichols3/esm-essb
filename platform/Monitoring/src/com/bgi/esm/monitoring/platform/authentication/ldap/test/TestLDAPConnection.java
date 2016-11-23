package com.bgi.esm.monitoring.platform.authentication.ldap.test;

import java.util.Properties;
import com.bgi.esm.monitoring.platform.authentication.ldap.LDAPConnection;
import com.bgi.esm.monitoring.platform.authentication.User;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

public class TestLDAPConnection extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestLDAPConnection.class );

    public TestLDAPConnection ( String param )
    {
        super ( param );
    }

    /**
     *  Tests loading the LDAP properties
     */
    public void testLoadProperties()
    {
        LDAPConnection connection = new LDAPConnection();
        
        assertNotNull ( connection.getProperties() );
    }

    /**
     *  Tests validating a valid user.  Do NOT write a test case for an invalid login
     *  as this has the possibility of locking out the common LDAP account used by 
     *  many other people in BGI.
     */
    public void testValidUser()
    {
        LDAPConnection connection = new LDAPConnection();
        assertNotNull ( connection.getProperties() );

        assertTrue ( connection.doesUserExist ( "zzito", "HYPertext01" ) );
    }

    public void testRetrieveInformationForSystemAccount()
    {
        User user = LDAPConnection.retrieveUserInformation ( "zzito" );

        assertNotNull ( user );

        user.LogObject();
    }

    public void testRetrieveInformationForUser()
    {
        Properties sys_props = System.getProperties();
        String username      = sys_props.getProperty ( "user.name" );

        _log.info ( "Retrieing information from LDAP for username=" + username );

        User user = LDAPConnection.retrieveUserInformation ( username );

        assertNotNull ( "Could not retrieve information for current user: " + username, user );

        user.LogObject();
    }

    public void testRetrieveInvalidUser()
    {
        User user = LDAPConnection.retrieveUserInformation ( "some_invalid_user" );

        assertTrue ( null == user );
    }
};