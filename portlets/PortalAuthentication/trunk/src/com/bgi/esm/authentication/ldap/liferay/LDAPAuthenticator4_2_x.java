package com.bgi.esm.authentication.ldap.liferay; 
import com.bgi.esm.authentication.ldap.LDAPConnection;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

//  Liferay 4.1.1 libraries
//import com.liferay.portal.security.auth.Authenticator;
//import com.liferay.portal.security.auth.AuthException;
//import com.liferay.portal.model.Contact;
//import com.liferay.portal.model.User;
//import com.liferay.portal.service.UserLocalServiceUtil;

//  Liferay 4.2.x libraries
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

public class LDAPAuthenticator4_2_x implements Authenticator
{
    final private static Logger _log = Logger.getLogger ( LDAPAuthenticator4_2_x.class );

    private static void LogToFile ( String message )
    {
        try
        {
            if ( true )
            {
                FileOutputStream outfile = new FileOutputStream ( "c:\\test\\ldap-log.out", true );
                    outfile.write ( (new Date()).toString().getBytes() );
                    outfile.write ( " - Liferay - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
            }
        }
        catch ( IOException exception )
        {
            _log.info ( "Could not log to output file", exception );
        }
    }

    public int authenticateByEmailAddress( String companyId, String emailAddress, String password, Map headerMap, Map parameterMap ) throws AuthException 
    {
        LogToFile ( "authenticateByEmailAddress - Email Address: " + emailAddress );
        return FAILURE;
    }

    public int authenticateByUserId( String companyId, String user_id, String password, Map headerMap, Map parameterMap ) throws AuthException
    {
        LogToFile ( "authenticateByUserID - UserID: " + user_id );
        LDAPConnection ldap = new LDAPConnection();
        Contact contact     = null;
        User user           = null;

        //  Liferay 4.1.1
        int birthdayMonth = Calendar.JANUARY;
        int birthdayDay = 1;
        int birthdayYear = 1970;
        String prefixId = null;
        String suffixId = null;

        if (( user_id.indexOf ( "test-" ) >= 0 ) || ( user_id.equals ( "liferay.com.1" ) ) || ( user_id.equals ( "test@liferay.com" ) ))
        {
            try 
            {
                _log.info ( "\tSearching Liferay Database for test user: " + user_id );
                user = UserLocalServiceUtil.getUserById ( user_id );

                return SUCCESS;
                //return ( user.getPassword().equals ( password ) )? SUCCESS : FAILURE;
            }
            catch ( NoSuchUserException exception )
            {
                _log.info ( "\tCould not find user: " + user_id );
                return FAILURE;
            }
            catch ( SystemException exception )
            {
                _log.info ( "\tSystem error detected when searching for test user" );
                return FAILURE;
            }
            catch ( PortalException exception )
            {
                _log.info ( "\tPortal error detected when searching for test user" );
                return FAILURE;
            }
        }

        try
        {
            _log.info ( "\tSearching LDAP" );
            if ( ldap.doesUserExist ( user_id, password ) )
            {
                _log.info ( "\tFound user in LDAP" );
                boolean autoUserId    = false;
                String userId         = user_id;
                boolean autoPassword  = false;
                String password1      = password;
                String password2      = password;
                boolean passwordReset = false;
                String firstName      = ldap.getUserFirstName();
                String middleName     = ldap.getUserMiddleName();
                String lastName       = ldap.getUserLastName();
                String nickName       = "";
                String emailAddress   = ldap.getUserEmailAddress();
                boolean male          = true;
                Date birthday         = new Date();

                Locale locale         = Locale.US;

                //  Now that we know that the user exists in LDAP, we need to update his/her profile
                //  in Liferay
                try 
                {
                    _log.info ( "\tSearching Liferay Database" );
                    user    = UserLocalServiceUtil.getUserById ( user_id );
                    contact = user.getContact();
                    _log.info ( "SUCCESS #1-1: Found user in Liferay database" );

                    //  Liferay 4.x
                    user = UserLocalServiceUtil.updateUser ( 
                        userId, password1, emailAddress, user.getLanguageId(),
                        user.getTimeZoneId(), user.getGreeting(), user.getResolution(), user.getComments(),
                        user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getNickName(),
                        "", "", user.getMale(),
                        //user.getPrefixId(), user.getSuffixId(), user.getMale(),
                        birthdayMonth, birthdayDay, birthdayYear,
                        //user.getBirthdayMonth(), user.getBirthdayDay(), user.getBirthdayYear(),
                        contact.getSmsSn(), contact.getAimSn(), contact.getIcqSn(), contact.getJabberSn(), contact.getMsnSn(), contact.getSkypeSn(), contact.getYmSn(),
                        contact.getJobTitle(), user.getOrganization().getOrganizationId(), user.getLocation().getOrganizationId() );


                    _log.info ( "SUCCESS #1-2: Updated Liferay user database" );
                        
                    return SUCCESS;
                }
                catch ( NoSuchUserException exception )
                {
                    try
                    {
                        _log.info ( "\tCreate User profile in Liferay for " + userId );
                        //  Liferay 3.6.1
                        /*
                        user = UserLocalServiceUtil.addUser(
                            companyId, autoUserId, userId, autoPassword, password1,
                            password2, passwordReset, firstName, middleName,
                            lastName, nickName, male, birthday, emailAddress, locale);
                        //*/

                        user = UserLocalServiceUtil.addUser( "LDAP",
                            companyId, autoUserId, userId, autoPassword, password1,
                            password2, passwordReset, emailAddress, locale, firstName, 
                            middleName, lastName, nickName, prefixId, suffixId, male, birthdayMonth, 
                            birthdayDay, birthdayYear, "", "", "", false );

                        String array_users[] = { user_id };

                        //  Make sure that the user has the "Guest" and "User" roles
                        _log.info ( "setting Guest role" );
                        UserLocalServiceUtil.setRoleUsers ( "Guest", array_users );
                        _log.info ( "setting User role" );
                        UserLocalServiceUtil.setRoleUsers ( "User",  array_users );

                        //  Make sure that the user does NOT have the "Power User" or "Administrator" roles
                        _log.info ( "removing Power User role" );
                        UserLocalServiceUtil.unsetRoleUsers ( "Power User",    array_users );
                        _log.info ( "removing Administrator role" );
                        UserLocalServiceUtil.unsetRoleUsers ( "Administrator", array_users );

                        //  Disable the "Terms of Use" screen
                        UserLocalServiceUtil.updateAgreedToTermsOfUse ( userId, true );

                            //birthdayDay, birthdayYear, null, null, null, false );
                    }
                    catch ( Exception e )
                    {
                        _log.error ( "FAILURE: Unknown exception: " +  e.getMessage() );
                        _log.error ( "Unknown exception", e );
                        throw new AuthException ( e );
                    }
                }

                if ( null == user )
                {
                    _log.info ( "FAILURE: Null user" );
                    return FAILURE;
                }

                _log.info ( "SUCCESS: replicated user in Liferay database" );
                return SUCCESS;
            }
            else
            {
                _log.info ( "FAILURE: User does not exist in Liferay or LDAP" );
                return FAILURE;
            }
        }
        catch ( Exception exception )
        {
            throw new AuthException ( exception );
        }
    }
}
