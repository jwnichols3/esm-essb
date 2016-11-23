package com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter;

import com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter.ServiceCenterPropertiesException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.lang.ref.WeakReference;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Properties to configure ServiceCenter.
 * 
 * Must contain the following properties:
 * 
 * <ul>
 * <li>service_center.server  # The name of the server that the web service resides on (e.g. peregrine-app-prod)</li>
 * <li>service_center.port    # The port number to which the web service is listening (e.g. 8080).</li>
 * <li>service_center.servlet # The servlet that provides the web service.  Must have a leading /.  E.g., /oaa/servlet/archway</li>
 * <li>service_center.action  # The servlet action for the web service (E.g., sc.query).</li>
 * </ul>
 * 
 * @author hannrus
 *
 */
public class ServiceCenterProperties
{
    private static final String DEFAULT_PROTOCOL  = "http";
    private static final String WEB_SERVICE_TABLE = "probsummary";

    private static final Logger LOG = Logger.getLogger ( ServiceCenterProperties.class );

    private URL        webServiceUrl;
    private Properties properties;    // Not sure it will be useful to keep this

    public ServiceCenterProperties ( File propertiesFile )
    {
        this ( getPropertiesFromFile(propertiesFile) );
    }

    public ServiceCenterProperties ( InputStream is )
    {
        this( getPropertiesFromStream(is) );
    }

    public ServiceCenterProperties ( Properties properties )
    {
        this.properties    = properties;
        this.webServiceUrl = getUrlFromProperties   ( properties );
    }

	private static Properties getPropertiesFromStream ( InputStream is )
    {
        Properties properties = new Properties();

        try
        {
            properties.load(is);
        }
        catch (IOException e)
        {
            throw new ServiceCenterPropertiesException("I/O error reading properties from stream.", e);
        }

        return properties;
    }

    private static Properties getPropertiesFromFile ( File propertiesFile )
    {
        InputStream is = null;

        try
        {
            is = new FileInputStream ( propertiesFile );
        }
        catch (FileNotFoundException e)
        {
            throw new ServiceCenterPropertiesException( "File not found: " + propertiesFile.toString(), e );
        }

        Properties properties = getPropertiesFromStream(is);

        try
        {
            is.close();
        }
        catch (IOException e)
        {
            LOG.error("IOException caught while trying to close the properties file " +
                "in ServiceCenterProperties.getPropertiesFromFile.");
        }

        return properties;
    }

    private static URL getUrlFromProperties ( Properties properties )
    {
        String server  = getTrimmedPropertyValue ( properties, "service_center.server"  );
        String port    = getTrimmedPropertyValue ( properties, "service_center.port"    );
        String servlet = getTrimmedPropertyValue ( properties, "service_center.servlet" );
        String action  = getTrimmedPropertyValue ( properties, "service_center.action"  );
        
        WeakReference <StringBuilder> buffer = new WeakReference <StringBuilder> ( new StringBuilder ( DEFAULT_PROTOCOL ) );

        buffer.get().append ( "://" );
        buffer.get().append ( server );

        if (( null != port ) && ( port.length() > 0 ))
        {
            buffer.get().append ( ":" );
            buffer.get().append ( port );
        }

        buffer.get().append ( servlet );

        buffer.get().append ( "?" );

        buffer.get().append ( action  );

        buffer.get().append ( "&_table=" );
        buffer.get().append ( WEB_SERVICE_TABLE );

        buffer.get().append ( "&number=" );

        URL url = null;

        try
        {
            url = new URL( buffer.get().toString() );
        }
        catch (MalformedURLException e)
        {
            throw new ServiceCenterPropertiesException("The properties file for ServiceCenter has invalid content.", e);
        }

        return url;
    }

    private static String getTrimmedPropertyValue(Properties properties, String key)
    {
    	String value = properties.getProperty(key);
    	
    	if (value != null)
    	{
    		value = value.trim();
    	}
    	
    	return value;
    }
    
   /**
     * Get the web service URL constructed from the properties.
     * 
     * @return the web service URL constructed from the properties.
     */
    public URL getWebServiceUrl()
    {
        return webServiceUrl;
    }

}
