package com.bgi.esm.monitoring.platform.buss_module.dispatcher;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import weblogic.logging.LoggerNotAvailableException;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class ServiceCenterAuthenticator extends Authenticator
{
	private static Logger _log = null;
	
    private String username;
    private String password;
    
    static
    {
    	try
    	{
    		_log = Log4jLoggingHelper.getLog4jServerLogger();
    	}
    	//catch (LoggerNotAvailableException e)
    	catch ( Throwable th )
    	{
    		_log = Logger.getLogger ( ServiceCenterAuthenticator.class );
    	}
    }
    
    
    public ServiceCenterAuthenticator (String username,String password)
    {
       this.username = username;
       this.password = password;
    }
   
    public PasswordAuthentication getPasswordAuthentication()
    {
       _log.debug("Entering ServiceCenterAuthenticator.getPasswordAuthentication.");
       _log.debug("username: " + username + "\npassword: " + password);
       _log.debug("Exiting ServiceCenterAuthenticator.getPasswordAuthentication.");
       return new PasswordAuthentication ( username,password.toCharArray());

    }

    public String getRequestInfo()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append ( "ServiceCenterAuthenticator information:\n" );
        buffer.append ( "    Host:     " + getRequestingHost()      );
        buffer.append ( "\n" );
        buffer.append ( "    Port:     " + getRequestingPort()      );
        buffer.append ( "\n" );
        buffer.append ( "    Protocol: " + getRequestingProtocol()  );
        buffer.append ( "\n" );
        buffer.append ( "    Requestor Type: " + getRequestorType() );
        buffer.append ( "\n" );

        return buffer.toString();
    }
};
