package com.bgi.esm.authentication.ldap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
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

import com.bglobal.commons.security.Authenticator;
import com.bglobal.commons.security.AuthenticationException;
import com.bglobal.commons.security.ldap.LDAPAuthenticator;

public class LDAPConnection
{
    public static final String LDAP_NAME               = "dc=insidelive, dc=net";
    public static final String PROVIDER_URL            = "ldap://dir.insidelive.net:389";
    public static final String SECURITY_PRINCIPAL      = "cn=zzReadLDAP,ou=ServiceAccounts,ou=Global,dc=insidelive,dc=net";
    public static final String SECURITY_CREDENTIALS    = "zzreadldap";
    public static final String SECURITY_AUTHENTICATION = "zzreadldap";


    private InitialLdapContext context = null;
    private SearchControls sc          = null;
    private SearchResult sr            = null;
    private NamingEnumeration ne       = null;
    private Hashtable env = new Hashtable();

    private long start_time            = 0;
    private long end_time              = 0;
    private long execution_time        = 0;

    private String user_first_name     = null;
    private String user_middle_name    = null;
    private String user_last_name      = null;
    private String user_email_address  = null;

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
        env.put ( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put ( Context.PROVIDER_URL,            PROVIDER_URL );
        //env.put ( Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION );
        env.put ( Context.SECURITY_PRINCIPAL,      SECURITY_PRINCIPAL );
        env.put ( Context.SECURITY_CREDENTIALS,    SECURITY_CREDENTIALS );
    }

    public boolean doesUserExist ( String username, String password )
    {
        int num_results    = 0;
        int counter        = 0;
        Object o1          = null;
        Object o2          = null;
        String cn          = null;
        String constructed = null;

        LogToFile ( "Testing if user " + username + " exists" );

        //  Attempt to login as user
        Authenticator anAuthenticator = new LDAPAuthenticator();
        try 
        {
            anAuthenticator.authenticate ( username, password );
        }
        catch (AuthenticationException ae) 
        {
            return false;
        }
        catch (Throwable t) 
        {
            return false;
        }


        //  Upon a successful login, retrieve user information
        try
        {
            start_time = System.currentTimeMillis();

            //  Test for LDAP response by attempting a login
            LogToFile ( "Testing LDAP availability..." );
            context        = new InitialLdapContext ( env, null );
            LogToFile ( "Successful LDAP login..." );
            sc             = new SearchControls();
            sc.setSearchScope ( SearchControls.SUBTREE_SCOPE );
            
            //  Search for a record
            ne             = context.search ( "dc=insidelive, dc=net", "(&(Objectcategory=Person)(ObjectClass=user)(samAccountname="+username+"))", sc );

            try
            {
                while ( ne.hasMore() )
                {
                    num_results++;

                    LogToFile ( "Found a result in LDAP!" );

                    sr = (SearchResult) ne.next();

                    Attributes a = sr.getAttributes();
                    NamingEnumeration ne2 = a.getIDs();

                    cn                 = a.get( "cn" ).toString().substring ( 4 );
                    user_first_name    = a.get( "givenName" ).toString().substring ( 11 );
                    o1                 = a.get( "middleName" );
                    user_middle_name   = (null != o1)? o1.toString().substring ( 12 ) : "";
                    user_last_name     = a.get( "sn" ).toString().substring ( 4 );

                    String attrs_to_test[] = 
                    {
                        "mail",
                        "facsysUserRoutingEMail",
                        "userPrincipalName"
                    };

                    for ( counter = 0; counter < attrs_to_test.length; counter++ )
                    {
                        o1 = a.get ( attrs_to_test[counter] );

                        if ( null != o1 )
                        {
                            break;
                        }
                    }

                    if ( null != o1 )
                    {
                        user_email_address = o1.toString().toLowerCase().substring ( attrs_to_test[counter].length() + 2 );
                        LogToFile ( "SUCCESS: Found valid email address #2" );
                    }
                    else
                    {
                        LogToFile ( "FAILURE: Could not find valid email address" );
                    }
                }
            }
            catch ( PartialResultException e )
            {
            }

            if ( null == cn )
            {
                // Failing on null CN value
                LogToFile ( "Failing on bad CN value" );
                return false;
            }

            if ( num_results < 1 )
            {
                // Failing because user was not found
                LogToFile ( "Failing because user was not found" );
                return false;
            }

            // Re-construct the CN attribute to search for the user
            cn = cn.replaceAll ( ",", "\\\\," );
            constructed = "CN="+cn+",OU=People,OU=Americas, dc=insidelive, dc=net";
            //constructed = "CN="+cn+", dc=insidelive, dc=net";

            execution_time = end_time - start_time;

            LogToFile ( "SUCCESS!" );
        }
        catch ( CommunicationException e )
        {
            LogToFile ( "LDAP connection bad #1..." );
            return false;
        }
        catch (NamingException e)
        {
            LogToFile ( "LDAP connection bad #2..." );
            LogToFile ( "\tConstructed=" + constructed );
            LogToFile ( "\tPassword=" + password );
            e.printStackTrace();
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
};
