import com.bglobal.commons.security.Authenticator;
import com.bglobal.commons.security.AuthenticationException;
import com.bglobal.commons.security.ldap.LDAPAuthenticator;

public class test_ldap
{
	public static void main ( String args[] ) throws Exception
	{
		Authenticator anAuthenticator = new LDAPAuthenticator();

		try 
		{
			anAuthenticator.authenticate("zzito","HYPertext01");

    		/// If we get here then the authentication worked....
			System.out.println ( "Authentication successful!" );
		}
		catch (AuthenticationException ae) 
		{
			ae.printStackTrace();
		}
		catch (Throwable t) 
		{
		}
	}
};
