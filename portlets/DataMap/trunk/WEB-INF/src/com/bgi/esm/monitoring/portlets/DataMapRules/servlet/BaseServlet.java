package com.bgi.esm.monitoring.portlets.DataMapRules.servlet;

import java.lang.ref.WeakReference;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public abstract class BaseServlet extends HttpServlet
{
    final private static Logger _log         = Logger.getLogger ( BaseServlet.class );

    protected ServletContext servletContext  = null;

    protected static String[] formatter_args = null;

    public void init ( ServletConfig config ) throws ServletException
    {
        if ( null == servletContext )
        {
            servletContext = config.getServletContext();

            _log.info ( "Initialized servlet context with ServletContextName=" + servletContext.getServletContextName() );
        }

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::init( ServletConfig ) - servlet started" );

            Enumeration e = config.getInitParameterNames();
            if ( e.hasMoreElements() )
            {
                message.get().append ( " ( " );
                while ( e.hasMoreElements() )
                {
                    String parameter = e.nextElement().toString();
                    String value     = config.getInitParameter ( parameter );

                    message.get().append ( parameter );
                    message.get().append ( "=" );
                    message.get().append ( value );

                    if ( e.hasMoreElements() )
                    {
                        message.get().append ( ", " );
                    }
                }
                message.get().append ( " )" );
            }

            _log.info ( message.get().toString() );
        }

    }

    public void init () throws ServletException
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::init() - servlet started" );

            _log.info ( message.get().toString() );
        }
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     */
    final protected HashMap <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
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

    public String getServletInfo()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
        message.get().append ( getClass().getName() );
        message.get().append ( "::getServletInfo() - please override to display useful information" );

        return message.get().toString();
    }


    public void destroy()
    {
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::init() - servlet destroyed" );

            _log.info ( message.get().toString() );
        }
    }
}
