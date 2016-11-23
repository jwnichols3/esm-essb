package com.bgi.esm.monitoring.portlets.EEBEventLog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class Toolkit
{
    final private static int    DEFAULT_INT         = 0;
    final private static long   DEFAULT_LONG        = 0;
    final private static double DEFAULT_DOUBLE      = 0.0;
    final private static float  DEFAULT_FLOAT       = 0.0f;

    final public static String TIMEZONE_COOKIE_NAME = "eeb-event-log-timezone-offset";

    final private static Logger _log                = Logger.getLogger( Toolkit.class );
    final public static SimpleDateFormat sdf        = new SimpleDateFormat ( "yyyy-MMM-dd, HH:mm:ss" );

    /**
     * Sets the size of the portlet window according to javax.portlet.WindowState.  This is to be called in the 
     * execute() method of the org.apache.struts.action.Action class.
     *
     * @param request the HttpServletRequest of the current page
     * @param window_state the new size of the window
     */
    public static void setPortletWindowSize ( HttpServletRequest request, WindowState window_state )
        throws WindowStateException
    {
        PortletResponse portletResponse = (PortletResponse) request.getAttribute ( "javax.portlet.response" );

        if ( portletResponse instanceof ActionResponse )
        {
            _log.info ( "MaximizePortletWindowAction::execute() - detected ActionResponse object" );
            ActionResponse actionResponse = (ActionResponse) portletResponse;

            actionResponse.setWindowState ( window_state );
        }
        else
        {
            _log.info ( "Toolkit::setPortletWindowSize() - could not set the portlet window size" );
        }
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     */
    public static HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        HashMap <String, String> param_map = new HashMap <String, String> ();

        if ( request.getParameter ( "_spage" ) != null ) // Liferay parameter handling
        {
            String parameter = request.getParameter ( "_spage" );
            parameter = parameter.substring ( parameter.indexOf ( "?" )+1 );

            // Parse out the tokens
            StringTokenizer tokenizer = new StringTokenizer ( parameter, "&" );
            String key                = null;
            String value              = null;
            int index                 = 0;
            param_map                 = new HashMap <String, String> ();

            while ( tokenizer.hasMoreTokens() )
            {
                parameter = tokenizer.nextToken();
                index     = parameter.indexOf ( "=" );
                if ( index >= 0 )
                {
                    key       = parameter.substring ( 0, index );
                    value     = parameter.substring ( index+1 );

                    param_map.put ( key, value );
                }
            }
        }
        else // Struts parameter handling
        {
            Enumeration e = request.getParameterNames();
            String es     = null;
            param_map     = new HashMap <String, String>();
            while ( e.hasMoreElements() )
            {
                es = e.nextElement().toString();
                param_map.put ( es, request.getParameter ( es ) );
            }
        }
        return param_map;
    }

    public static void dumpHttpRequestParameters ( String filename, HttpServletRequest request ) throws IOException
    {
        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );

        StringBuilder buffer = new StringBuilder();
        String temp_string   = null;
        Iterator <String> i  = param_map.keySet().iterator();
        while ( i.hasNext() )
        {
            temp_string = i.next();
            buffer.append ( temp_string );
            buffer.append ( " = " );
            buffer.append ( param_map.get ( temp_string ) );
            buffer.append ( "\n" );
        }

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( buffer.toString().getBytes() );
        outfile.close();
    }

    /**
     * Parses a String object as an int, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an int, returning DEFAULT_INT if there are a NumberFormatException is thrown
     */
    public static int parseInt ( String s )
    {
        try
        {
            return Integer.parseInt ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to int: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_INT;
        }
    }

    /**
     * Parses a String object as an long, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an long, returning DEFAULT_LONG if there are a NumberFormatException is thrown
     */
    public static long parseLong ( String s )
    {
        try
        {
            return Long.parseLong ( s );
        }
        catch ( NumberFormatException exception )
        {   
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to long: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_LONG;
        }
    }

    /**
     * Parses a String object as an double, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an double, returning DEFAULT_DOUBLE if there are a NumberFormatException is thrown
     */
    public static double parseDouble ( String s )
    {
        try
        {
            return Double.parseDouble ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to double: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_DOUBLE;
        }
    }

    /**
     * Parses a String object as an float, returning 0 if there are a NumberFormatException is thrown
     *
     * @param s the String object to parse
     * @return a String object as an float, returning DEFAULT_FLOAT if there are a NumberFormatException is thrown
     */
    public static float parseFloat ( String s )
    {
        try
        {
            return Float.parseFloat ( s );
        }
        catch ( NumberFormatException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder( "Could not parse to float: ") );
            message.get().append( s );
            
            _log.error ( message.get().toString() );
            
            message = null;
            
            exception.printStackTrace();
            return DEFAULT_FLOAT;
        }
    }

    /**
     * Dumps all the HttpServletRequest attributes and parameters to a file
     */
    @SuppressWarnings("unchecked")
    public static void dumpHttpServletRequest ( String filename, HttpServletRequest request ) throws IOException
    {
        FileOutputStream outfile = new FileOutputStream ( filename );
        Enumeration <String> e   = request.getAttributeNames();
        String property          = null;
        
        outfile.write ( "************* Attributes\n".getBytes() );
        while ( e.hasMoreElements() )
        {
            property = e.nextElement();
            
            outfile.write ( property.getBytes() );
            outfile.write ( " - ".getBytes() );
            outfile.write ( request.getAttribute( property ).toString().getBytes() );
            outfile.write ( "\n".getBytes() );
        }
        
        outfile.write ( "************* Parameters\n".getBytes() );
        e = request.getParameterNames();
        while ( e.hasMoreElements() )
        {
            property = e.nextElement();
            
            outfile.write ( property.getBytes() );
            outfile.write ( " - ".getBytes() );
            outfile.write ( request.getParameter( property ).toString().getBytes() );
            outfile.write ( "\n".getBytes() );
        }
        
        outfile.close();
    }
    
    /**
     * Retrieves the PortletRequest object from the HTTP session
     * 
     * @return the PortletRequest object from the HTTP session
     */
    public static PortletRequest getPortletRequest ( HttpServletRequest request )
    {
        HttpSession session = request.getSession();
        
        return (PortletRequest) session.getAttribute ( "javax.portlet.request" );
    }
    
    public static PortletPreferences getPortletPreferences ( HttpServletRequest request )
    {
        HttpSession session           = request.getSession();
        PortletRequest portletRequest = (PortletRequest) session.getAttribute ( "javax.portlet.request" );
        
        return getPortletPreferences ( portletRequest );
    }
    
    public static PortletPreferences getPortletPreferences ( PortletRequest request )
    {
        return request.getPreferences();
    }

    /**
     * Retrieves the value of the timezone cookie the browser sets to notify the application of the user's timezone
     * 
     * @param request the HttpServletRequest from the application
     * @return the timezone cookie
     */
    public static Cookie getTimeZoneCookie ( HttpServletRequest request )
    {
        Cookie cookies[]       = request.getCookies();
        Cookie timezone_cookie = null;
        int counter            = 0;

        for ( counter = 0; counter < cookies.length; counter++ )
        {
            if ( cookies[counter].getName().equals ( TIMEZONE_COOKIE_NAME ) )
            {
                timezone_cookie = cookies[counter];
    
                break;
            }
        }

        return timezone_cookie;
    }

    /**
     * Computes the value of the timezone cookie
     * 
     * @param request the HttpServletReqeust from the application
     * @return the timezone offset in milliseconds
     */
    public static int computeTimeZoneOffset ( HttpServletRequest request )
    {
        Cookie timezone_cookie = Toolkit.getTimeZoneCookie ( request );

        if ( null == timezone_cookie )
        {
            return 0;
        }
        else
        {
            return Integer.parseInt ( timezone_cookie.getValue() ) * 3600000;
        }
    }
}
