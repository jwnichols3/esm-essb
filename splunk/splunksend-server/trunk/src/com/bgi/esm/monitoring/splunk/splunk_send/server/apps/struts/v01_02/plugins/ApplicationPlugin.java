package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.plugins;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;

public class ApplicationPlugin implements PlugIn
{
    final private static Logger _log         = Logger.getLogger ( ApplicationPlugin.class );
    private static InitialContext ctx        = null;
    private static Properties JNDIProperties = null;
    private static boolean EnterpriseMode    = false;

    /**
      * Initialize plugin
      *
      * @param servlet
      * @param config
      * @throws ServletException
      */
    public void init ( final ActionServlet servlet, final ModuleConfig config ) throws ServletException
    {
        parseConfiguration ( config.findPlugInConfigs() );
    }

    private void parseConfiguration ( final PlugInConfig[] args )
    {
        final String class_name = getClass().getName();
        boolean flag = false;

        for ( PlugInConfig element : args )
        {
            if ( class_name.equals(element.getClassName()) )
            {
                flag = false;
                processProperties ( element.getProperties() );
            }
        }

        if ( flag )
        {
            _log.error ( String.format ( "%s not discovered", class_name ) );
        }
    }

    @SuppressWarnings ( "unchecked" )
    private void processProperties ( Map map )
    {
        if ( null == JNDIProperties )
        {
            synchronized ( ApplicationPlugin.class )
            {
                if ( null == JNDIProperties )
                {
                     JNDIProperties = new Properties ();

                     Iterator <String> keys = (Iterator <String>) map.keySet().iterator();
                     while ( keys.hasNext() )
                     {
                         String key = keys.next();

                         if ( key.equals ( "enterprise.mode" ) )
                         {
                             EnterpriseMode = Boolean.parseBoolean ( map.get( key ).toString() );
                         }
                         else
                         {
                             JNDIProperties.put ( key, map.get ( key ) );
                         }
                     }
                }
            }
        }
    }

    public void destroy()
    {
        System.err.println ( String.format ( "%s::destroy() - plugin stopped", getClass().getName() ) );
    }

    public static InitialContext getContext()
    {
        if ( null == ctx )
        {
            synchronized ( ApplicationPlugin.class )
            {
                if ( null == ctx )
                {
                    try
                    {
                        ctx = new InitialContext ( JNDIProperties );
                    }
                    catch ( NamingException exception )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( ApplicationPlugin.class.getName() );
                            message.get().append ( "::getContext() - naming error: " );
                            message.get().append ( exception.getMessage() );
                        _log.error ( message.get().toString(), exception );
                    }
                }
            }
        }

        return ctx;
    }

    public static Properties getJNDIProperties()
    {
        return JNDIProperties;
    }

    public static boolean getEnterpriseMode()
    {
        return EnterpriseMode;
    }
}