package com.bgi.esm.monitoring.platform.filters.Archway;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ArchwayFilter implements Filter
{
    final private static Logger _log = Logger.getLogger ( ArchwayFilter.class );

    private ServletContext context = null;

    /**
     *  Iniitialize the filter
     *  @param filterConfig the filter configuration
     *  @throws ServletException if an error occurs
     */
    public void init ( FilterConfig filterConfig ) throws ServletException
    {
        context = filterConfig.getServletContext();

        String configUrlParam = filterConfig.getInitParameter ( "configUrl" );

        if ( null == configUrlParam )
        {
        }

        try
        {
        }
        catch ( Exception e )
        {
            throw new ServletException ( e );
        }
    }

    /**
     *  Buffers the entire request into a local database
     */
    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException, ServletException
    {
        if ( response.isCommitted() )
        {
            //  The response has been had its status code written and its headers added.
            //  There is nothing for this filter to do.
        }
        else if ( request instanceof HttpServletRequest )
        {
            //  Processing HTTP requests/responses.  Will write to database.
            HttpServletRequest  hreq = (HttpServletRequest) request;
            HttpServletResponse hres = (HttpServletResponse) response;

            if ( _log.isInfoEnabled() )
            {
                Enumeration params  = hreq.getParameterNames();
                Enumeration attribs = hreq.getAttributeNames();
                Cookie cookies[]    = hreq.getCookies();

                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Received Request: "   );
                message.get().append ( hreq.getRequestURL()   );
                message.get().append ( "\n    Server:       " );
                message.get().append ( hreq.getServerName()   );
                message.get().append ( "\n    Port:         " );
                message.get().append ( hreq.getServerPort()   );
                message.get().append ( "\n    Query String: " );
                message.get().append ( hreq.getQueryString()  );
                message.get().append ( "\n    Parameters:\n"  );
                while ( params.hasMoreElements() )
                {
                    String param_name = params.nextElement().toString();

                    message.get().append ( "        " );
                    message.get().append ( param_name );
                    message.get().append ( "=" );
                    message.get().append ( hreq.getParameter ( param_name ) );
                    message.get().append ( "\n" );
                }

                message.get().append ( "    Attributes:\n" );
                while ( attribs.hasMoreElements() )
                {
                    String attrib_name = attribs.nextElement().toString();

                    message.get().append ( "        " );
                    message.get().append ( attrib_name );
                    message.get().append ( "=" );
                    message.get().append ( hreq.getAttribute ( attrib_name ) );
                    message.get().append ( "\n" );
                }

                message.get().append ( "    Cookies:\n" );
                for ( int counter = 0; counter < cookies.length; counter++ )
                {
                    Cookie cookie = cookies[counter];
                    message.get().append ( "        " );
                    message.get().append ( cookie.getName() );
                    message.get().append ( "=" );
                    message.get().append ( cookie.getValue() );
                    message.get().append ( "\n" );
                }

                _log.info ( message.get().toString() );
            }
        }
        else
        {
            //  Unknown response...
        }
    }

    /**
     *  Destroy this filter
     */
    public void destroy()
    {
        //  Memory management: Release the reference to the ServletContext
        context = null;
    }
};
