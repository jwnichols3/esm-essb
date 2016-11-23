package com.bgi.esm.monitoring.platform.buss_module.dispatcher.Alarmpoint;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class AgentAuthenticator extends Authenticator
{
    private String username;
    private String password;
                 
    public AgentAuthenticator (String username, String password)
    {
       this.username = username;
       this.password = password;
    }
   
    protected PasswordAuthentication getPasswordAuthentication()
    {
       return new PasswordAuthentication ( username,password.toCharArray());
    }
};

