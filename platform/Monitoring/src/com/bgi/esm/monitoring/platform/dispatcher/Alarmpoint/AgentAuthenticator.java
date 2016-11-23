package com.bgi.esm.monitoring.platform.dispatcher.Alarmpoint;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

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

