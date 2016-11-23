package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.client;

import java.lang.ref.WeakReference;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import junit.framework.TestCase;

public abstract class BaseTestClient extends TestCase
{
    final private static Logger _log  = Logger.getLogger ( BaseTestClient.class );
    private static InitialContext ctx = null;

    protected void enumerateContextHelper ( Context ctx, int tabs ) throws NamingException
    {
        StringBuilder spacing = new StringBuilder();
        for ( int counter = 0; counter < tabs; counter++ )
        {
            spacing.append ( "    " );
        }

        try
        {
            NamingEnumeration <?> en = ctx.list ( "" );
            while(en.hasMore())
            {
                NameClassPair entry = (NameClassPair) en.next();
                _log.info ( String.format ( "%s( %s=%s )", spacing.toString(), entry.getName(), entry.getClassName() ) );

                try
                {
                    Object object = ctx.lookup ( entry.getName() );
                    if ( object instanceof Context )
                    {
                        enumerateContextHelper ( (Context) object, tabs+1 );
                    }
                }
                catch ( Exception exception )
                {
                    _log.error ( exception );
                }
                catch ( Throwable exception )
                {
                    _log.error ( exception );
                }
            }
        }
        catch ( Exception exception )
        {
            _log.error ( exception );
        }
    }

    protected InitialContext getContext()
    {
        if ( null == ctx )
        {
            synchronized ( BaseTestClient.class )
            {
                try
                {
                    ctx  = new InitialContext ( createConnectionProperties() );
                }
                catch ( Exception exception )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( BaseTestClient.class.getName() );
                        message.get().append ( "::staticBlock() - could not obtain JNDI connection to application server: " );
                        message.get().append ( exception.getMessage() );
                    _log.error ( message.get().toString(), exception );
                }
            }
        }

        return ctx;
    }

    protected static Properties createConnectionProperties()
    {
        Properties props = new Properties();

        //  JBoss Properties
        //props.setProperty ( "java.naming.factory.initial",  "org.jnp.interfaces.NamingContextFactory" );
        //props.setProperty ( "java.naming.provider.url",     "localhost:1099" );
        //props.setProperty ( "java.naming.factory.url.pkgs", "org.jboss.naming" );

        //  Weblogic Properties
        //props.setProperty ( "java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory" );
        //props.setProperty ( "java.naming.provider.url",    "t3://localhost:7001" );

        //  Glassfish Properties
        props.setProperty ( "java.naming.factory.initial",  "com.sun.enterprise.naming.SerialInitContextFactory" );
        props.setProperty ( "org.omg.CORBA.ORBInitialHost", "localhost"); //  Server location
        props.setProperty ( "org.omg.CORBA.ORBInitialPort", "3700");      //  default is 3700

        return props;
    }
}