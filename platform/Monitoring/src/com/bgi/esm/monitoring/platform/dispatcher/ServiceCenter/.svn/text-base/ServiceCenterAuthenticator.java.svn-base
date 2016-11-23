package com.bgi.esm.monitoring.platform.dispatcher;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ServiceCenterAuthenticator extends Authenticator
{
    private String username;
    private String password;
                 
    public ServiceCenterAuthenticator (String username,String password)
    {
       this.username = username;
       this.password = password;
    }
   
    protected PasswordAuthentication getPasswordAuthentication()
    {
       return new PasswordAuthentication ( username,password.toCharArray());
    }
};
