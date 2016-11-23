package com.bgi.esm.monitoring.portlets.Suppressions;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;


public class QuartzPlugin implements PlugIn
{
    final private static Logger _log                       = Logger.getLogger ( QuartzPlugin.class );

	/**
	 * PlugIn target
	 */
	final public static String TARGET1                     = "com.bgi.esm.monitoring.portlets.Suppressions.QuartzPlugin";

    /**
     *  Quartz-related variables
     */
    final private static SchedulerFactory schedulerFactory = null;
    final private static Scheduler scheduler               = null;
    private static boolean initialized                     = false;
    private static HashMap <String, String> properties     = null;

	/**
	 * Initialize plugin 
	 * 
	 * @param servlet
	 * @param config
	 * @throws ServletException
	 */
	public void init ( final ActionServlet servlet, final ModuleConfig config ) throws ServletException 
    {
		// Establish runtime logging level by blasting out some test messages
		System.err.println ( "///QuartzPlugin struts plugin start///" );
		_log.debug ( "//QuartzPlugin struts debug level test message" );
		_log.info  ( "//QuartzPlugin struts info level test message"  );
		_log.warn  ( "//QuartzPlugin struts warn level test message"  );
		_log.error ( "//QuartzPlugin struts error level test message" );
		// _log.fatal("//fatal struts level test message");

        if ( false == initialized )
        {
            properties = new HashMap <String, String> ();
            initialized = true;
        }
        else
        {
            _log.error ( "Attempted to re-initialize the Quartz Scheduler/SchedulerFactory objects!" );
        }
        
        discoverFrameWorkDatum(config.findPlugInConfigs());
	}

	/**
	 * Iterate for each plug in to discover my plugin and then provision it.
	 * 
	 *  @param argz array of plugin configurations
	 */
	private void discoverFrameWorkDatum ( final PlugInConfig[] argz ) 
    {
		boolean flag = true;
		
		for ( PlugInConfig element : argz ) 
        {
			if ( TARGET1.equals(element.getClassName()) ) 
            {
				flag = false;
				processProperties ( element.getProperties() );
			}
		}
		
		if ( flag ) 
        {
			_log.error( "QuartzPlugin not discovered");
		}
	}

	/**
	 * Discover JNDI properties to communicate w/ the Enterprise Event Bus
	 */
	@SuppressWarnings("unchecked")
	private void processProperties(final Map map) 
    {
        Iterator i = map.keySet().iterator();
        while ( i.hasNext() )
        {
			final String key = (String) i.next();
			final String value = (String) map.get(key);

            properties.put ( key, value );

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "QuartzPlugin: Loaded property ( key=" );
                message.get().append ( key );
                message.get().append ( ", value=" );
                message.get().append ( value );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }
        }
    }

	/**
	 * Servlet is exiting, tear down any persistent resources
	 */
	public void destroy() 
    {
		System.err.println ( "///QuartzPlugin struts plugin stop///" );
	}
};
