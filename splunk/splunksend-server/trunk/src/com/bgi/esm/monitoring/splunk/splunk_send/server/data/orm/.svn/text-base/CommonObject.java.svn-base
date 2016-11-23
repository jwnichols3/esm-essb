package com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.Logger;

public abstract class CommonObject implements Serializable
{
    /**
     *
     */
    final private static long serialVersionUID = 1995628586981758748L;
    final public static DecimalFormat df2    = new DecimalFormat ( "00" );
    final public static DecimalFormat df4    = new DecimalFormat ( "0000" );
    final public static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );

    final private static Logger _log         = Logger.getLogger ( CommonObject.class );

    /**
     * Prepares a string to be displayed or assigned to a Javascript variable enclosed by double quotes
     *
     * @param string the String to convert
     * @return the formatted string with double quotes and newlines escaped
     */
    public static String parseJavascriptString ( Object string )
    {
        if ( null == string )
        {
            return "";
        }

        String contents = string.toString();
        contents = contents.replaceAll ( "\r", "\\r" );
        contents = contents.replaceAll ( "\n", "\\n" );
        contents = contents.replaceAll ( "\"", "\\\\\"" );

        return contents;
    }

    public static String createJavascriptString ( String input )
    {
        if ( null == input )
        {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer ( input, "'" );
        while ( tokenizer.hasMoreTokens() )
        {
            buffer.append ( tokenizer.nextToken().trim() );
            if ( tokenizer.hasMoreTokens() )
            {
                buffer.append ( "\'" );
            }
        }

        tokenizer = new StringTokenizer ( buffer.toString(), "\n" );
        buffer = new StringBuilder();
        while ( tokenizer.hasMoreTokens() )
        {
            buffer.append ( tokenizer.nextToken().trim() );
            if ( tokenizer.hasMoreTokens() )
            {
                buffer.append ( "\n" );
            }
        }

        String original_string = buffer.toString();
        tokenizer = new StringTokenizer ( buffer.toString(), "\"" );
        buffer = new StringBuilder();
        boolean left = true;
        if ( tokenizer.countTokens() > 1 )
        {
            while ( tokenizer.hasMoreTokens() )
            {
                buffer.append ( tokenizer.nextToken().trim() );
                if ( left )
                {
                    buffer.append ( " &ldquo;" );
                }
                else
                {
                    buffer.append ( "&rdquo; " );
                }
                left = !left;
                if (( !tokenizer.hasMoreTokens() ) && ( false == left ))
                {
                    buffer.append ( "&rdquo; " );
                }
            }
        }
        else
        {
            return original_string;
        }

        return buffer.toString();
    }

    public static String formatDate ( Calendar time )
    {
        if ( null != time )
        {
            return sdf.format ( time.getTime() );
        }

        return null;
    }

    public static String formatDate ( Date date )
    {
        if ( null != date )
        {
            return sdf.format ( date );
        }

        return null;
    }


    public static String formatHTMLDate ( Calendar time )
    {
        if ( null != time )
        {
            return sdf.format ( time.getTime() );
        }

        return "&nbsp;";
    }

    public static String formatHTMLDate ( Date date )
    {
        if ( null != date )
        {
            return sdf.format ( date );
        }

        return "&nbsp;";
    }

    /**
     *  Compares if two java.util.Calendar objects match on year, month, date, hour and minute
     *
     *  @param a the first of two Calendar objects to compare
     *  @param b the second of two Calendar objects to compare
     *  @return true if the two Calendar objects match, false otherwise
     */
    public boolean compareCalendars ( Calendar a, Calendar b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            boolean return_result = true;
            return_result = return_result && ( a.get ( Calendar.YEAR   ) == b.get ( Calendar.YEAR   ) );
            return_result = return_result && ( a.get ( Calendar.MONTH  ) == b.get ( Calendar.MONTH  ) );
            return_result = return_result && ( a.get ( Calendar.DATE   ) == b.get ( Calendar.DATE   ) );
            return_result = return_result && ( a.get ( Calendar.HOUR   ) == b.get ( Calendar.HOUR   ) );
            return_result = return_result && ( a.get ( Calendar.MINUTE ) == b.get ( Calendar.MINUTE ) );

            return return_result;
        }
        else
        {
            return false;
        }
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( int a, int b )
    {
        return ( a == b );
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( long a, long b )
    {
        return ( a == b );
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( float a, float b )
    {
        return ( a == b );
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( double a, double b )
    {
        return (( a == b ) || ( Math.abs ( a - b ) < 0.00001 ));
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( char a, char b )
    {
        return ( a == b );
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( byte a, byte b )
    {
        return ( a == b );
    }

    /**
     *  Function compare primivites (overridden to catch type errors at compile-time)
     *
     *  @param a the first primitive to compare
     *  @param b the second primitive to compare
     *  @return true if they match, false otherwise
     */
    public boolean comparePrimitives ( boolean a, boolean b )
    {
        return ( a == b );
    }

    /**
     *  Safe compare for two java.lang.Object (can be null) via their equal() values
     *
     *  @param a the first string to compare
     *  @param b the second string to compare
     *  @return true if they match or are both null, false otherwise
     */
    public boolean compareObjects ( Object a, Object b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            return a.equals ( b );
        }
        else
        {
            return false;
        }
    }

    /**
     *  Safe compare for two strings (can be null)
     *
     *  @param a the first string to compare
     *  @param b the second string to compare
     *  @return true if they match or are both null, false otherwise
     */
    public boolean compareStrings ( String a, String b )
    {
        if (( null == a ) && ( null == b ))
        {
            return true;
        }
        else if (( null != a ) && ( null != b ))
        {
            return a.equals ( b );
        }
        else
        {
            return false;
        }
    }

    protected void checkArgumentForNullObject ( Object object )
    {
        if ( null == object )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Attempted to set " );
                message.get().append ( toString() );
                message.get().append ( " to null" );
            throw new IllegalArgumentException ( message.get().toString() );
        }
    }

    protected void throwExceptionForArgumentOfIncorrectType ( Object object )
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( this.getClass().getName() );
            message.get().append ( "::setValue() - Attempted to set value to an object of type " );
            message.get().append ( object.getClass().getName() );
        throw new IllegalArgumentException ( message.get().toString() );
    }

    public static Properties loadProperties ( String resource )
    {
        ClassLoader cl   = CommonObject.class.getClassLoader();
        InputStream is   = null;
        ByteBuffer bb    = null;
        Properties props = null;

        //  Attempting to retrieve from within the classpath
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

            ByteArrayInputStream bais = new ByteArrayInputStream ( bb.array() );
            props = new Properties();
            props.load ( bais );

            return props;
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( CommonObject.class.getName() );
                message.get().append ( "::loadProperties ( Resource=" );
                message.get().append ( resource );
                message.get().append ( " ) - IOException encountered when reading from classpath: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( CommonObject.class.getName() );
                message.get().append ( "::loadProperties ( Resource=" );
                message.get().append ( resource );
                message.get().append ( " ) - RuntimeException encountered when reading from classpath: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        //  Attempting to retrieve from the file system
        try
        {
            FileInputStream infile = new FileInputStream ( resource );
            props = new Properties();
            props.load ( infile );

            return props;
        }
        catch ( IOException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( CommonObject.class.getName() );
                message.get().append ( "::loadProperties ( Resource=" );
                message.get().append ( resource );
                message.get().append ( " ) - IOException encountered when reading from file system: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( CommonObject.class.getName() );
                message.get().append ( "::loadProperties ( Resource=" );
                message.get().append ( resource );
                message.get().append ( " ) - RuntimeException encountered when reading from file system: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return null;
    }

    public static byte[] readFile ( String filename )
    {
        try
        {
            ClassLoader cl = CommonObject.class.getClassLoader();
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
    protected static String[] retrieveStringDefaultValues ( Properties properties, String propertyPrefix )
    {
        List <String> propertyKeys = new Vector <String> ();
        String returnValues[]      = null;
        Enumeration e              = properties.propertyNames();

        while ( e.hasMoreElements() )
        {
            String propertyName = e.nextElement().toString();

            if ( propertyName.indexOf ( propertyPrefix ) == 0 )
            {
                propertyKeys.add ( propertyName );
            }
        }

        //  Sort the keys
        Collections.sort ( propertyKeys );

        //  Construct the return object
        returnValues = new String[propertyKeys.size()];
        for ( int counter = 0; counter < returnValues.length; counter++ )
        {
            returnValues[counter] = properties.getProperty ( propertyKeys.get ( counter ) );
        }

        return returnValues;
    }

    @SuppressWarnings ( "unchecked" )
    protected static int[] retrieveIntDefaultValues ( Properties properties, String propertyPrefix )
    {
        List <String> propertyKeys = new Vector <String> ();
        int returnValues[]         = null;
        Enumeration e              = properties.propertyNames();

        while ( e.hasMoreElements() )
        {
            String propertyName = e.nextElement().toString();

            if ( propertyName.indexOf ( propertyPrefix ) == 0 )
            {
                String propertyValue = properties.getProperty ( propertyName );
                try
                {
                    Integer.parseInt ( propertyValue );
                    propertyKeys.add ( propertyName  );
                }
                catch ( NumberFormatException exception )
                {
                    _log.error ( String.format ( "%s::retrieveIntDefaultValues() - invalid value for key='%s': %s", CommonObject.class.getName(), propertyName, propertyValue ) );
                }
            }
        }

        //  Sort the keys
        Collections.sort ( propertyKeys );

        //  Construct the return object
        returnValues = new int[propertyKeys.size()];
        for ( int counter = 0; counter < returnValues.length; counter++ )
        {
            returnValues[counter] = Integer.parseInt ( properties.getProperty ( propertyKeys.get ( counter ) ) );
        }

        return returnValues;
    }

    @SuppressWarnings ( "unchecked" )
    protected static long[] retrieveLongDefaultValues ( Properties properties, String propertyPrefix )
    {
        List <String> propertyKeys = new Vector <String> ();
        long returnValues[]        = null;
        Enumeration e              = properties.propertyNames();

        while ( e.hasMoreElements() )
        {
            String propertyName = e.nextElement().toString();

            if ( propertyName.indexOf ( propertyPrefix ) == 0 )
            {
                String propertyValue = properties.getProperty ( propertyName );
                try
                {
                    Long.parseLong ( propertyValue );
                    propertyKeys.add ( propertyName  );
                }
                catch ( NumberFormatException exception )
                {
                    _log.error ( String.format ( "%s::retrieveLongDefaultValues() - invalid value for key='%s': %s", CommonObject.class.getName(), propertyName, propertyValue ) );
                }
            }
        }

        //  Sort the keys
        Collections.sort ( propertyKeys );

        //  Construct the return object
        returnValues = new long[propertyKeys.size()];
        for ( int counter = 0; counter < returnValues.length; counter++ )
        {
            returnValues[counter] = Long.parseLong ( properties.getProperty( propertyKeys.get ( counter ) ) );
        }

        return returnValues;
    }

    @SuppressWarnings ( "unchecked" )
    protected static float[] retrieveFloatDefaultValues ( Properties properties, String propertyPrefix )
    {
        List <String> propertyKeys = new Vector <String> ();
        float returnValues[]        = null;
        Enumeration e              = properties.propertyNames();

        while ( e.hasMoreElements() )
        {
            String propertyName = e.nextElement().toString();

            if ( propertyName.indexOf ( propertyPrefix ) == 0 )
            {
                String propertyValue = properties.getProperty ( propertyName );
                try
                {
                    Float.parseFloat ( propertyValue );
                    propertyKeys.add ( propertyName  );
                }
                catch ( NumberFormatException exception )
                {
                    _log.error ( String.format ( "%s::retrieveFloatDefaultValues() - invalid value for key='%s': %s", CommonObject.class.getName(), propertyName, propertyValue ) );
                }
            }
        }

        //  Sort the keys
        Collections.sort ( propertyKeys );

        //  Construct the return object
        returnValues = new float[propertyKeys.size()];
        for ( int counter = 0; counter < returnValues.length; counter++ )
        {
            returnValues[counter] = Float.parseFloat ( properties.getProperty( propertyKeys.get ( counter ) ) );
        }

        return returnValues;
    }

    @SuppressWarnings ( "unchecked" )
    protected static double[] retrieveDoubleDefaultValues ( Properties properties, String propertyPrefix )
    {
        List <String> propertyKeys = new Vector <String> ();
        double returnValues[]        = null;
        Enumeration e              = properties.propertyNames();

        while ( e.hasMoreElements() )
        {
            String propertyName = e.nextElement().toString();

            if ( propertyName.indexOf ( propertyPrefix ) == 0 )
            {
                String propertyValue = properties.getProperty ( propertyName );
                try
                {
                    Double.parseDouble ( propertyValue );
                    propertyKeys.add ( propertyName  );
                }
                catch ( NumberFormatException exception )
                {
                    _log.error ( String.format ( "%s::retrieveDoubleDefaultValues() - invalid value for key='%s': %s", CommonObject.class.getName(), propertyName, propertyValue ) );
                }
            }
        }

        //  Sort the keys
        Collections.sort ( propertyKeys );

        //  Construct the return object
        returnValues = new double[propertyKeys.size()];
        for ( int counter = 0; counter < returnValues.length; counter++ )
        {
            returnValues[counter] = Double.parseDouble ( properties.getProperty( propertyKeys.get ( counter ) ) );
        }

        return returnValues;
    }

    public static String formatHTMLDisplayString ( String string )
    {
        if (( null == string ) || ( string.trim().length() == 0 ))
        {
            return "&nbsp;";
        }

        return string.replaceAll ( "\n", "<br>" );
    }
}