package com.bgi.esm.monitoring.platform.authentication;

import java.util.Vector;
import org.apache.log4j.Logger;


public class User
{
    final private static Logger _log    = Logger.getLogger ( User.class );

    private String Username             = null;
    private String EmailAddress         = null;
    private String UserPrincipalName    = null;
    private String PhoneNumberMobile    = null;
    private String PhoneNumberOffice    = null;
    private String EmployeeID           = null;
    private String FirstName            = null;
    private String MiddleName           = null;
    private String LastName             = null;
    private String Department           = null;
    private String EmployeeType         = null;
    private String Title                = null;
    private int UIDNumber               = 0;
    private Vector <String> memberships = null;

    public User ( String username )
    {
        memberships = new Vector <String> ();

        Username    = username;
    }

    public void setEmailAddress ( String email_address )
    {
        EmailAddress = email_address;
    }

    public String getEmailAddress ()
    {
        return EmailAddress;
    }

    public void setUserPrincipalName ( String user_principal_name )
    {
        UserPrincipalName = user_principal_name;
    }

    public String getUserPrincipalName ()
    {
        return UserPrincipalName;
    }

    public void setPhoneNumberMobile ( String phone_number_mobile )
    {
        PhoneNumberMobile = phone_number_mobile;
    }

    public String getPhoneNumberMobile ( String phone_number_mobile )
    {
        return PhoneNumberMobile;
    }

    public void setPhoneNumberOffice ( String phone_number_office )
    {
        PhoneNumberOffice = phone_number_office;
    }

    public String getPhoneNumberOffice ()
    {
        return PhoneNumberOffice;
    }

    public void setEmployeeID ( String employee_id )
    {
        EmployeeID = employee_id;
    }

    public String getEmployeeID ()
    {
        return EmployeeID;
    }

    public void setUIDNumber ( String uid_number )
    {
        UIDNumber = ( null != uid_number )? Integer.parseInt ( uid_number ) : -1;
    }

    public int getUIDNumber ()
    {
        return UIDNumber;
    }

    public void addMembership ( String membership )
    {
        memberships.add ( membership );
    }

    public boolean isMemberOf ( String membership )
    {
        return memberships.contains ( membership );
    }

    public Vector <String> getMemberships()
    {
        return memberships;
    }

    public void setFirstName ( String first_name )
    {
        FirstName = first_name;
    }

    public String getFirstName ()
    {
        return FirstName;
    }

    public void setMiddleName ( String middle_name )
    {
        MiddleName = middle_name;
    }

    public String getMiddleName ()
    {
        return MiddleName;
    }

    public void setLastName ( String last_name )
    {
        LastName = last_name;
    }

    public String getLastName ()
    {
        return LastName;
    }

    public void setDepartment ( String department )
    {
        Department = department;
    }

    public String getDepartment()
    {
        return Department;
    }

    public String getUsername()
    {
        return Username;
    }

    public String getEmployeeType()
    {
        return EmployeeType;
    }

    public void setEmployeeType ( String employee_type )
    {
        EmployeeType = employee_type;
    }

    public String getJobTitle()
    {
        return Title;
    }

    public void setJobTitle ( String job_title )
    {
        Title = job_title;
    }

    public void LogObject()
    {
        if ( _log.isInfoEnabled() )
        {
            _log.info ( "User information:  " + Username          );
            _log.info ( "\tUID Number:      " + UIDNumber         );
            _log.info ( "\tFirst name:      " + FirstName         );
            _log.info ( "\tMiddle name:     " + MiddleName        );
            _log.info ( "\tLast name:       " + LastName          );
            _log.info ( "\tEmployee ID:     " + EmployeeID        );
            _log.info ( "\tDepartment:      " + Department        );
            _log.info ( "\tEmail Address:   " + EmailAddress      );
            _log.info ( "\tPrincipal Name:  " + UserPrincipalName );
            _log.info ( "\tOffice Number:   " + PhoneNumberOffice );
            _log.info ( "\tMobile Number:   " + PhoneNumberMobile );
            _log.info ( "\tJob Title:       " + Title             );
            _log.info ( "\tCorporate Title: " + EmployeeType      );

            int num_memberships = memberships.size();
            _log.info ( "\tMemberships: " + num_memberships );
            for ( int counter = 0; counter < num_memberships; counter++ )
            {
                _log.info ( "\t\t" + memberships.elementAt ( counter ) );
            }
        }

        //*
            System.out.println ( "User information: " + Username          );
            System.out.println ( "\tUID Number:     " + UIDNumber         );
            System.out.println ( "\tFirst name:     " + FirstName         );
            System.out.println ( "\tMiddle name:    " + MiddleName        );
            System.out.println ( "\tLast name:      " + LastName          );
            System.out.println ( "\tEmployee ID:    " + EmployeeID        );
            System.out.println ( "\tDepartment:     " + Department        );
            System.out.println ( "\tEmail Address:  " + EmailAddress      );
            System.out.println ( "\tPrincipal Name: " + UserPrincipalName );
            System.out.println ( "\tOffice Number:  " + PhoneNumberOffice );
            System.out.println ( "\tMobile Number:  " + PhoneNumberMobile );

            int num_memberships = memberships.size();
            System.out.println ( "\tMemberships: " + num_memberships );
            for ( int counter = 0; counter < num_memberships; counter++ )
            {
                System.out.println ( "\t\t" + memberships.elementAt ( counter ) );
            }
        //*/
    }

};
