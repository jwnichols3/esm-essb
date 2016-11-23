package com.bgi.esm.monitoring.platform.authentication.ldap;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.PartialResultException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import com.bgi.esm.monitoring.platform.authentication.User;
import com.bglobal.commons.security.Authenticator;
import com.bglobal.commons.security.AuthenticationException;
import com.bglobal.commons.security.ldap.LDAPAuthenticator;
import org.apache.log4j.Logger;

/**
 * @author Dennis S. Lin (linden)
 *
 * Manages an authentication attempt against LDAP
 */
public class LDAPConnection
{
    final private static Logger _log              = Logger.getLogger ( LDAPConnection.class );

    private static String BASE_DN                 = null;
    private static String INITIAL_CONTEXT_FACTORY = null;
    private static String PROVIDER_URL            = null;
    private static String SECURITY_PRINCIPAL      = null;
    private static String SECURITY_CREDENTIALS    = null;
    private static String SECURITY_AUTHENTICATION = null;
    private static String properties_file         = "ldap.properties";
    private static Properties properties          = null;
    private static Hashtable env                  = null;

    private InitialLdapContext context            = null;
    private SearchControls sc                     = null;
    private SearchResult sr                       = null;
    private NamingEnumeration ne                  = null;

    private long start_time                       = 0;
    private long end_time                         = 0;
    private long execution_time                   = 0;

    private String user_first_name                = null;
    private String user_middle_name               = null;
    private String user_last_name                 = null;
    private String user_email_address             = null;

    public static void main ( String args[] ) throws InterruptedException
    {
        LDAPConnection lc = null;;

        lc = new LDAPConnection();
        if ( lc.doesUserExist ( "linden", "unknown" ) )
        {
            System.out.println ( "User exists" );
            System.out.println ( "\tFirst name:    " + lc.getUserFirstName() );
            System.out.println ( "\tMiddle name:   " + lc.getUserMiddleName() );
            System.out.println ( "\tLast name:     " + lc.getUserLastName() );
            System.out.println ( "\tEmail address: " + lc.getUserEmailAddress() );
        }
        else
        {
            System.out.println ( "Failed to find user #1" );
        }

        Thread.sleep ( 1500 );

        lc = new LDAPConnection();
        if ( lc.doesUserExist ( "zzito", "unknown" ))
        {
            System.out.println ( "User exists" );
            System.out.println ( "\tFirst name:    " + lc.getUserFirstName() );
            System.out.println ( "\tMiddle name:   " + lc.getUserMiddleName() );
            System.out.println ( "\tLast name:     " + lc.getUserLastName() );
            System.out.println ( "\tEmail address: " + lc.getUserEmailAddress() );
        }
        else
        {
            System.out.println ( "Failed to find user #2" );
        }
    }

    public LDAPConnection()
    {
        loadProperties();
    }

    private static void loadProperties()
    {
        if ( null == properties )
        {
            ClassLoader cl = LDAPConnection.class.getClassLoader();
            try
            {
                InputStream is = cl.getResourceAsStream ( properties_file );

                if ( null == is )
                {
                    _log.error ( "Could not find properties file: " + properties_file );

                    throw new NullPointerException ( "Could not find properties file: " + properties_file );
                }

                properties = new Properties();
                properties.load ( is );

                BASE_DN                 = properties.getProperty ( "ldap.base_dn"                 );
                INITIAL_CONTEXT_FACTORY = properties.getProperty ( "ldap.initial_context_factory" );
                PROVIDER_URL            = properties.getProperty ( "ldap.provider_url"            );
                SECURITY_PRINCIPAL      = properties.getProperty ( "ldap.security_principal"      );
                SECURITY_CREDENTIALS    = properties.getProperty ( "ldap.security_credentials"    );
                SECURITY_AUTHENTICATION = properties.getProperty ( "ldap.security_authentication" );

                env = new Hashtable();
                env.put ( Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY );
                env.put ( Context.PROVIDER_URL,            PROVIDER_URL            );
                env.put ( Context.SECURITY_PRINCIPAL,      SECURITY_PRINCIPAL      );
                env.put ( Context.SECURITY_CREDENTIALS,    SECURITY_CREDENTIALS    );
                //env.put ( Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION );
            }
            catch ( IOException exception )
            {
                _log.error ( "Could not read LDAP connection properties", exception );
            }
        }
    }

    public static User retrieveUserInformation ( String username )
    {
        loadProperties();

        User user                  = null;
        Object o1                  = null;
        InitialLdapContext context = null;
        SearchControls sc          = null;
        SearchResult sr            = null;
        NamingEnumeration ne       = null;

        try
        {
            context        = new InitialLdapContext ( env, null );

            _log.debug ( "Successful LDAP login..." );
            sc             = new SearchControls();
            sc.setSearchScope ( SearchControls.SUBTREE_SCOPE );
            
            //  Search for a record
            ne             = context.search ( "dc=insidelive, dc=net", "(&(Objectcategory=Person)(ObjectClass=user)(samAccountname="+username+"))", sc );

            try
            {
                user = new User ( username );

                _log.debug ( "Found a result in LDAP!" );

                sr = (SearchResult) ne.next();

                Attributes a = sr.getAttributes();
                NamingEnumeration ne2 = a.getIDs();
                String attr_name = null;

                attr_name = "employeeID";
                o1 = a.get( attr_name );
                String employee_id         = ( null != o1 )? o1.toString() : null;
                employee_id = removeAttrDescriptor ( attr_name, employee_id );

                attr_name = "givenName";
                o1 = a.get( attr_name );
                String first_name          = ( null != o1 )? o1.toString() : null;
                first_name = removeAttrDescriptor ( attr_name, first_name );

                attr_name = "middleName";
                o1 = a.get( attr_name );
                String middle_name         = ( null != o1 )? o1.toString() : null;
                middle_name = removeAttrDescriptor ( attr_name, middle_name );

                attr_name = "sn";
                o1 = a.get( attr_name );
                String last_name           = ( null != o1 )? o1.toString() : null;
                last_name = removeAttrDescriptor ( attr_name, last_name );

                attr_name = "mail";
                o1 = a.get( attr_name );
                String email_address       = ( null != o1 )? o1.toString() : null;
                email_address = removeAttrDescriptor ( attr_name, email_address );

                attr_name = "telephoneNumber";
                o1 = a.get( attr_name );
                String phone_office        = ( null != o1 )? o1.toString() : null;
                phone_office = removeAttrDescriptor ( attr_name, phone_office );

                attr_name = "mobile";
                o1 = a.get( attr_name );
                String phone_mobile        = ( null != o1 )? o1.toString() : null;
                phone_mobile = removeAttrDescriptor ( attr_name, phone_mobile );

                attr_name = "uidNumber";
                o1 = a.get( attr_name );
                String uid_number          = ( null != o1 )? o1.toString() : null;
                uid_number = removeAttrDescriptor ( attr_name, uid_number );

                attr_name = "userPrincipalName";
                o1 = a.get( attr_name );
                String user_principal_name = ( null != o1 )? o1.toString() : null;
                user_principal_name = removeAttrDescriptor ( attr_name, user_principal_name );

                attr_name = "department";
                o1 = a.get( attr_name );
                String department          = ( null != o1 )? o1.toString() : null;
                department = removeAttrDescriptor ( attr_name, department );

                attr_name = "employeeType";
                o1 = a.get( attr_name );
                String corporateTitle      = ( null != o1 )? o1.toString() : null;
                corporateTitle = removeAttrDescriptor ( attr_name, corporateTitle );

                attr_name = "title";
                o1 = a.get( attr_name );
                String jobTitle            = ( null != o1 )? o1.toString() : null;
                jobTitle = removeAttrDescriptor ( attr_name, jobTitle );

                int index = ( null != uid_number )? uid_number.indexOf ( ":" ) : -1;
                if ( index >= 0 )
                {
                    uid_number = uid_number.substring ( index+1 ).trim();
                }

                user.setFirstName         ( first_name          );
                user.setMiddleName        ( middle_name         );
                user.setLastName          ( last_name           );
                user.setEmailAddress      ( email_address       );
                user.setEmployeeID        ( employee_id         );
                user.setPhoneNumberOffice ( phone_office        );
                user.setPhoneNumberMobile ( phone_mobile        );
                user.setUIDNumber         ( uid_number          );
                user.setUserPrincipalName ( user_principal_name );
                user.setDepartment        ( department          );

                o1 = a.get ( "memberOf" );
                if ( null != o1 )
                {
                    String memberships[] = o1.toString().split ( "CN=" );
                    //  First one is just the property name
                    for ( int counter = 1; counter < memberships.length; counter++ )
                    {
                        user.addMembership ( "CN=" + memberships[counter] );
                    }
                }

                return user;
            }
            catch ( PartialResultException e )
            {
                _log.error ( "Communication with the LDAP server ended abruptly", e );

                return null;
            }
        }
        catch ( CommunicationException e )
        {
            _log.error ( "Could not communicate with LDAP server", e );

            return null;
        }
        catch ( NamingException e )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Could not find user information for username=" );
            message.get().append ( username );

            _log.error ( message.get().toString(), e );

            return null;
        }
    }

    public boolean doesUserExist ( String username, String password )
    {
        int num_results    = 0;
        int counter        = 0;
        Object o1          = null;
        Object o2          = null;
        String cn          = null;
        String constructed = null;

        if ( _log.isDebugEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Testing if user " );
            message.get().append ( username );
            message.get().append ( " exists" );

            _log.debug ( message.get().toString() );
        }

        //  Attempt to login as user
        Authenticator anAuthenticator = new LDAPAuthenticator();
        try 
        {
            anAuthenticator.authenticate ( username, password );
        }
        catch (AuthenticationException ae) 
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "Could not authenticate ( Username, Password ) = ( " );
            message.get().append ( username );
            message.get().append ( ", **** )" );

            _log.error ( message.get().toString(), ae );

            return false;
        }
        catch (Throwable t) 
        {
            _log.error ( "Unknown exception encountered", t );

            return false;
        }


        //  Upon a successful login, retrieve user information
        try
        {
            start_time = System.currentTimeMillis();

            //  Test for LDAP response by attempting a login
            _log.debug ( "Testing LDAP availability..." );
            context        = new InitialLdapContext ( env, null );

            _log.debug ( "Successful LDAP login..." );
            sc             = new SearchControls();
            sc.setSearchScope ( SearchControls.SUBTREE_SCOPE );
            
            //  Search for a record
            ne             = context.search ( "dc=insidelive, dc=net", "(&(Objectcategory=Person)(ObjectClass=user)(samAccountname="+username+"))", sc );

            try
            {
                while ( ne.hasMore() )
                {
                    num_results++;

                    _log.debug ( "Found a result in LDAP!" );

                    sr = (SearchResult) ne.next();

                    Attributes a = sr.getAttributes();
                    NamingEnumeration ne2 = a.getIDs();

                    cn                 = a.get( "cn" ).toString().substring ( 4 );
                    o1                 = a.get( "middleName" );
                    user_first_name    = a.get( "givenName" ).toString().substring ( 11 );
                    user_middle_name   = (null != o1)? o1.toString().substring ( 12 ) : "";
                    user_last_name     = a.get( "sn" ).toString().substring ( 4 );
                    user_email_address = a.get ( "mail" ).toString();

                    String attrs_to_test[] = 
                    {
                        "mail",                     //  Email address
                        "facsysUserRoutingEMail",   //  
                        "userPrincipalName",        //  username@insidelive.net address
                        "department",               //  The XXX-YYYY region/MRC code of the user's department
                        "mobile",                   //  cell phone number
                        "telephoneNumber",          //  office phone number
                        "employeeID",               //  IGI
                        "displayName",              //  Outlook display name
                        "uidNumber",                //  Internal unique identifier used by ShareIT/LDAP
                        "memberOf"                  //  The groups that this user is a member of
                    };

                    if ( _log.isDebugEnabled() )
                    {
                        for ( counter = 0; counter < attrs_to_test.length; counter++ )
                        {
                            o1 = a.get ( attrs_to_test[counter] );

                            if ( null != o1 )
                            {
                                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                                message.get().append ( "\t" );
                                message.get().append ( attrs_to_test[counter] );
                                message.get().append ( ": " );
                                message.get().append ( o1.toString() );

                                _log.debug ( message.get().toString() );
                            }
                            else
                            {
                                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                                message.get().append ( "\t" );
                                message.get().append ( attrs_to_test[counter] );
                                message.get().append ( ": <null value>" );

                                _log.debug ( message.get().toString() );
                            }
                        }
                    }

                    if ( null != o1 )
                    {
                        //user_email_address = o1.toString().toLowerCase().substring ( attrs_to_test[2].length() + 2 );
                        user_email_address = user_email_address.toLowerCase().substring ( attrs_to_test[2].length() + 2 );

                        _log.info ( "SUCCESS: Found valid email address #2" );
                    }
                    else
                    {
                        _log.error ( "FAILURE: Could not find valid email address" );
                    }
                }
            }
            catch ( PartialResultException e )
            {
                _log.error ( "Communication with the LDAP server ended abruptly", e );
            }

            if ( null == cn )
            {
                // Failing on null CN value
                _log.error ( "Failing on bad CN value" );

                return false;
            }

            if ( num_results < 1 )
            {
                // Failing because user was not found
                _log.error ( "Failing because user was not found" );

                return false;
            }

            // Re-construct the CN attribute to search for the user
            cn = cn.replaceAll ( ",", "\\\\," );
            constructed = "CN="+cn+",OU=People,OU=Americas, dc=insidelive, dc=net";
            //constructed = "CN="+cn+", dc=insidelive, dc=net";

            execution_time = end_time - start_time;

            _log.info ( "Successful login" );
        }
        catch ( CommunicationException e )
        {
            _log.error ( "Could not communicate with LDAP server", e );

            return false;
        }
        catch ( NamingException e )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "LDAP connection bad #2 ( Constructed, Password ) = ( ");
            message.get().append ( constructed );
            message.get().append ( ", " );
            message.get().append ( password );
            message.get().append ( " )" );

            _log.error ( message.get().toString(), e );

            return false;
        }

        return ( num_results > 0 );
    }

    public String getUserFirstName()
    {
        return user_first_name;
    }

    public String getUserMiddleName()
    {
        return user_middle_name;
    }

    public String getUserLastName()
    {
        return user_last_name;
    }

    public String getUserEmailAddress()
    {
        return user_email_address;
    }

    public static Properties getProperties()
    {
        return properties;
    }

    /**
     *  Cleans out the ActiveDirectory attribute names (i.e. "sn: zzito" will be convered to "zzito" )
     *
     *  @param data the value to be checked
     *  @param attr_name name of attribute to be removed from data
     *
     *  @return the cleaned data value
     */
    private static String removeAttrDescriptor ( String attr_name, String data )
    {
        if (( null != data ) && ( data.indexOf ( attr_name ) == 0 ))
        {
            return data.substring ( attr_name.length() + 1 ).trim();
        }
        else
        {
            return data;
        }
    }

    /*
    private void LogToFile ( String message )
    {
        try
        {
            if ( false )
            {
                FileOutputStream outfile = new FileOutputStream ( "c:\\test\\ldap-log.out", true );
                    outfile.write ( (new Date()).toString().getBytes() );
                    outfile.write ( " - LDAPConnection - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
        }
        catch ( IOException exception )
        {
        }
    }
    //*/
};

