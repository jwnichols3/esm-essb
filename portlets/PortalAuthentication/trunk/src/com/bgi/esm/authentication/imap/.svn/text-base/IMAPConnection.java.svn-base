package com.bgi.esm.authentication.imap;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class IMAPConnection
{
	public static void main ( String args[] ) throws MessagingException
	{
		IMAPConnection i = new IMAPConnection();
	}

	public IMAPConnection() throws MessagingException
	{
		Properties props = System.getProperties();
		props.put ( "mail.smtp.host", "calnte2k035.insidelive.net" );

		Session session = Session.getDefaultInstance ( props, null );

		Store store = session.getStore ( "imap" );
		store.connect ( "calnte2k035.insidelive.net", "linden", "dsl0502barclaysglobal" );
	}
};
