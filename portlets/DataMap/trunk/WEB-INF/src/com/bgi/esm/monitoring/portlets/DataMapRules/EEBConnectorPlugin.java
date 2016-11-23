package com.bgi.esm.monitoring.portlets.DataMapRules;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;

import com.bgi.esm.monitoring.platform.client.RmiProperties;

/**
 * Struts plugin. Used to obtain configuration information at runtime.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (linden)
 */
public class EEBConnectorPlugin implements PlugIn {

	/**
	 * Initialize plugin 
	 * 
	 * @param servlet
	 * @param config
	 * @throws ServletException
	 */
	public void init ( final ActionServlet servlet, final ModuleConfig config) throws ServletException 
    {
		// Establish runtime logging level by blasting out some test messages
		System.err.println ( "///EEBConnectorPlugin struts plugin start///" );
		_log.trace ( "//EEBConnectorPlugin struts trace level test message" );
		_log.debug ( "//EEBConnectorPlugin struts debug level test message" );
		_log.info  ( "//EEBConnectorPlugin struts info level test message"  );
		_log.warn  ( "//EEBConnectorPlugin struts warn level test message"  );
		_log.error ( "//EEBConnectorPlugin struts error level test message" );
		// _log.fatal("//fatal struts level test message");

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
			_log.error( "EEBConnectorPlugin not discovered");
		}
	}

	/**
	 * Discover JNDI properties to communicate w/ the Enterprise Event Bus
	 */
	@SuppressWarnings("unchecked")
	private void processProperties(final Map map) 
    {
		final Hashtable jndi = new Hashtable();

		final Hashtable ht = new Hashtable(map);
		for ( final Enumeration en = ht.keys(); en.hasMoreElements(); ) 
        {
			final String key = (String) en.nextElement();
			final String value = (String) ht.get(key);

			_log.info("EEBConnectorPlugin struts set:" + key + ":value:" + value);

			if ( key.startsWith("java.naming") ) 
            {
				jndi.put(key, value);
			} 
            else 
            {
				_log.error("unknown plugin property:" + key);
			}
		}

		RmiProperties.setJndi(jndi);
	}

	/**
	 * Servlet is exiting, tear down any persistent resources
	 */
	public void destroy() 
    {
		System.err.println("///EEBConnectorPlugin struts plugin stop///");
	}

	/**
	 * PlugIn target
	 */
	public static final String TARGET1 = "com.bgi.esm.monitoring.portlets.DataMapRules.EEBConnectorPlugin";

	/**
	 * Define logger
	 */
	private final Log _log = LogFactory.getLog ( EEBConnectorPlugin.class );
}

