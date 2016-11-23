package com.bgi.esm.monitoring.platform.regression;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Toolkit
{
    final private static Logger _log = Logger.getLogger ( Toolkit.class );

    public static void reconfigureLog4j()
    {
        File file = new File ( "logs" );
        file.mkdirs();

        //  Set the system property
        String logging_date_string = getLoggingDateString();
        System.out.println ( "Using logging date string: " + logging_date_string );
        System.setProperty ( "log4j.date_string", logging_date_string );

        //  Restart Log4j and have it reload its properties
        BasicConfigurator.resetConfiguration();
        PropertyConfigurator.configure( retrieveLoggingProperties() );
    }

    public static String getLoggingDateString()
    {
        Calendar calendar = Calendar.getInstance();
        DecimalFormat df2 = new DecimalFormat ( "00" );
        String hostname   = "";

        try
        {
            InetAddress addr  = InetAddress.getLocalHost();
            byte[] ip_address = addr.getAddress();

            hostname = addr.getHostName();

            if ( null != hostname )
            {
                hostname = hostname + "-";
            }
            else
            {
                hostname="";
            }
        }
        catch ( UnknownHostException exception )
        {
            _log.error ( "Could not determine hostname for localhost", exception );

            hostname="";
        }

        StringBuilder date_string = new StringBuilder();
        date_string.append ( hostname );
        date_string.append ( calendar.get ( Calendar.YEAR  ) );
        date_string.append ( df2.format ( calendar.get ( Calendar.MONTH ) ) );
        date_string.append ( df2.format ( calendar.get ( Calendar.DATE  ) ) );
        date_string.append ( "-" );
        date_string.append ( df2.format ( calendar.get ( Calendar.HOUR_OF_DAY ) ) );
        date_string.append ( df2.format ( calendar.get ( Calendar.MINUTE ) ) );
        date_string.append ( df2.format ( calendar.get ( Calendar.SECOND ) ) );

        return date_string.toString();
    }

    public static Properties retrieveLoggingProperties()
    {
        ClassLoader cl   = Toolkit.class.getClassLoader();
        InputStream is   = null;
        Properties props = null;
        try
        {
            is = cl.getResourceAsStream ( "log4j.properties" );
            props = new Properties();
            props.load ( is );

            return props;
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not retrieve log4j properties", exception );
        }

        return null;
    }

    public static byte[] retrieveResource ( String resource )
    {
        ClassLoader cl = Toolkit.class.getClassLoader();
        InputStream is = null;
        ByteBuffer bb  = null;

        try
        {
            is = cl.getResourceAsStream ( resource );
            bb = ByteBuffer.allocate ( is.available() );
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

            return bb.array();
        }
        catch ( IOException exception )
        {
            _log.error ( "Could not load resource: " + resource, exception );
        }

        return null;
    }
};
