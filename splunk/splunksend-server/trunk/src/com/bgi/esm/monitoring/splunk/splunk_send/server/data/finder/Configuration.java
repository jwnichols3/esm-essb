package com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder;

import java.lang.ref.WeakReference;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IDataMapFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpAttributeFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpHeaderFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;

public class Configuration
{
    final private static Logger _log           = Logger.getLogger ( Configuration.class );
    private static Configuration configuration = null;
    private Properties ApplicationProperties   = null;
    private Properties VersionProperties       = null;
    private Properties DatabaseProperties      = null;
    private Properties ORMProperties           = null;
    private String Implementation              = null;
    final private String ORMPropertiesFile     = "orm.properties";

    //  Finder classes
    private Class <?> FinderClassDataMap        = null;
    private Class <?> FinderClassHttpAttribute  = null;
    private Class <?> FinderClassHttpCookie     = null;
    private Class <?> FinderClassHttpHeader     = null;
    private Class <?> FinderClassHttpParameter  = null;
    private Class <?> FinderClassHttpRequest    = null;

    //  DTO classes
    private Class <?> DTOClassDataMap           = null;
    private Class <?> DTOClassHttpAttribute     = null;
    private Class <?> DTOClassHttpCookie        = null;
    private Class <?> DTOClassHttpHeader        = null;
    private Class <?> DTOClassHttpParameter     = null;
    private Class <?> DTOClassHttpRequest       = null;

    private Configuration()
    {
        //  Defeat instantiation

        initialize();
    }

    public static Configuration getInstance()
    {
        if ( null == configuration )
        {
            synchronized ( Configuration.class )
            {
                if ( null == configuration )
                {
                    _log.info ( "Begin initialization..." );
                        configuration = new Configuration();
                    _log.info ( "Finished initialization..." );
                }
            }
        }

        return configuration;
    }

    private void initialize()
    {
        //  Read properties
        try
        {
            ORMProperties = new Properties();
            ByteArrayInputStream bais = new ByteArrayInputStream ( readFile ( ORMPropertiesFile ) );
            ORMProperties.load ( bais );

            Implementation = ORMProperties.getProperty ( "orm.implementation" );

            //  Loading the DTO Classes
            DTOClassDataMap           = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.data_map", Implementation ) ) );
            DTOClassHttpAttribute     = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.http_attribute", Implementation ) ) );
            DTOClassHttpCookie        = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.http_cookie", Implementation ) ) );
            DTOClassHttpHeader        = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.http_header", Implementation ) ) );
            DTOClassHttpParameter     = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.http_parameter", Implementation ) ) );
            DTOClassHttpRequest       = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.data.%s.http_request", Implementation ) ) );

            //  Loading the Finder Classes
            FinderClassDataMap        = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.data_map", Implementation ) ) );
            FinderClassHttpAttribute  = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.http_attribute", Implementation ) ) );
            FinderClassHttpCookie     = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.http_cookie", Implementation ) ) );
            FinderClassHttpHeader     = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.http_header", Implementation ) ) );
            FinderClassHttpParameter  = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.http_parameter", Implementation ) ) );
            FinderClassHttpRequest    = Class.forName ( ORMProperties.getProperty ( String.format ( "orm.finder.%s.http_request", Implementation ) ) );

        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::initialize() - error when initializing from properties file: " );
                message.get().append ( ORMPropertiesFile );
                message.get().append ( " - " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
        catch ( ClassNotFoundException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::initialize() - when attempting to initialize DTO/Finder classes: " );
                message.get().append ( exception.getMessage() );
            _log.fatal ( message.get().toString(), exception );
        }
    }

    public IDataMapFinder getDataMapFinder()
    {
        try
        {
            IDataMapFinder finder = (IDataMapFinder) FinderClassDataMap.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getDataMapFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getDataMapFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpAttributeFinder getHttpAttributeFinder()
    {
        try
        {
            IHttpAttributeFinder finder = (IHttpAttributeFinder) FinderClassHttpAttribute.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpAttributeFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpAttributeFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpCookieFinder getHttpCookieFinder()
    {
        try
        {
            IHttpCookieFinder finder = (IHttpCookieFinder) FinderClassHttpCookie.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpCookieFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpCookieFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpHeaderFinder getHttpHeaderFinder()
    {
        try
        {
            IHttpHeaderFinder finder = (IHttpHeaderFinder) FinderClassHttpHeader.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpHeaderFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpHeaderFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpParameterFinder getHttpParameterFinder()
    {
        try
        {
            IHttpParameterFinder finder = (IHttpParameterFinder) FinderClassHttpParameter.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpParameterFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpParameterFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpRequestFinder getHttpRequestFinder()
    {
        try
        {
            IHttpRequestFinder finder = (IHttpRequestFinder) FinderClassHttpRequest.newInstance();

            return finder;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpRequestFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::getHttpRequestFinder() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IDataMap newDataMap()
    {
        try
        {
            IDataMap object = (IDataMap) DTOClassDataMap.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newDataMap() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newDataMap() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpAttribute newHttpAttribute()
    {
        try
        {
            IHttpAttribute object = (IHttpAttribute) DTOClassHttpAttribute.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpAttribute() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpAttribute() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpCookie newHttpCookie()
    {
        try
        {
            IHttpCookie object = (IHttpCookie) DTOClassHttpCookie.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpCookie() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpCookie() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpHeader newHttpHeader()
    {
        try
        {
            IHttpHeader object = (IHttpHeader) DTOClassHttpHeader.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpHeader() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpHeader() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpParameter newHttpParameter()
    {
        try
        {
            IHttpParameter object = (IHttpParameter) DTOClassHttpParameter.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpParameter() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpParameter() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }
    public IHttpRequest newHttpRequest()
    {
        try
        {
            IHttpRequest object = (IHttpRequest) DTOClassHttpRequest.newInstance();

            return object;
        }
        catch ( InstantiationException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpRequest() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( IllegalAccessException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( Configuration.class.getName() );
                message.get().append ( "::newHttpRequest() - Could not instantiate finder instance: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }

    public String getImplementation()
    {
        return Implementation;
    }

    public static byte[] readFile ( String filename )
    {
        try
        {
            ClassLoader cl = Configuration.class.getClassLoader();
            InputStream is = cl.getResourceAsStream ( filename );
            ByteBuffer bb  = ByteBuffer.allocate ( is.available() );
            byte contents[]    = new byte[1024];
            int num_bytes_read = 0;
            int total_read     = 0;
            do
            {
                num_bytes_read = is.read ( contents );

                if ( num_bytes_read > 0 )
                {
                    bb.put ( contents, 0, num_bytes_read );
                    total_read += num_bytes_read;
                }
            }
            while ( num_bytes_read >= 0 );
            is.close();

            //  Memory management
            contents = null;

            return bb.array();
        }
        catch ( Exception exception )
        {
            _log.warn ( "Could not find the following file in the classpath: " + filename );
        }

        try
        {
            FileInputStream infile = new FileInputStream ( filename );
            byte file_contents[] = new byte[infile.available()];
            infile.read ( file_contents );
            infile.close();

            return file_contents;
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not find the following file in the file system: " + filename, exception );
        }

        return null;
    }

    @SuppressWarnings ( "unchecked" )
    public Properties getApplicationProperties()
    {
        if ( null == ApplicationProperties )
        {
            String username       = System.getProperty ( "user.name" );
            String hostname1      = System.getProperty ( "env.COMPUTERNAME" );
            String hostname2      = System.getProperty ( "env.HOSTNAME" );
            String hostname       = (( null == hostname1 ) || ( hostname1.trim().length() == 0 ))? hostname2 : hostname1;
            Properties props1     = CommonObject.loadProperties ( "application.properties" );
            Properties props2     = CommonObject.loadProperties ( String.format ( "application.%s.properties", hostname ) );
            Properties props3     = CommonObject.loadProperties ( String.format ( "application.%s.%s.properties", hostname, username ) );
            Enumeration e         = null;
            ApplicationProperties = new Properties();

            e = props1.propertyNames();
            while ( e.hasMoreElements() )
            {
                String property = e.nextElement().toString();
                ApplicationProperties.setProperty ( property, props1.getProperty ( property ) );
            }

            if ( null != props2 )
            {
                e = props2.propertyNames();
                while ( e.hasMoreElements() )
                {
                    String property = e.nextElement().toString();
                    ApplicationProperties.setProperty ( property, props2.getProperty ( property ) );
                }
            }

            if ( null != props3 )
            {
                e = props3.propertyNames();
                while ( e.hasMoreElements() )
                {
                    String property = e.nextElement().toString();
                    ApplicationProperties.setProperty ( property, props3.getProperty ( property ) );
                }
            }
        }

        return ApplicationProperties;
    }

    public Properties getVersionProperties()
    {
        if ( null == VersionProperties )
        {
            VersionProperties = CommonObject.loadProperties ( "version.properties" );
        }

        return VersionProperties;
    }

    @SuppressWarnings ( "unchecked" )
    public Properties getDatabaseProperties()
    {
        if ( null == DatabaseProperties )
        {
            String language    = System.getProperty ( "user.language" );
            Properties props1  = CommonObject.loadProperties ( "content/databases.properties" );
            Properties props2  = CommonObject.loadProperties ( String.format ( "content/databases_%s.properties", language ) );
            Enumeration e      = null;
            DatabaseProperties = new Properties();

            e = props1.propertyNames();
            while ( e.hasMoreElements() )
            {
                String property = e.nextElement().toString();
                DatabaseProperties.setProperty ( property, props1.getProperty ( property ) );
            }

            e = props2.propertyNames();
            while ( e.hasMoreElements() )
            {
                String property = e.nextElement().toString();
                DatabaseProperties.setProperty ( property, props2.getProperty ( property ) );
            }
        }

        return DatabaseProperties;
    }
}