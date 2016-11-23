package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions;

import java.lang.ref.WeakReference;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.plugins.ApplicationPlugin;

public class BaseDispatchAction extends DispatchAction
{
    final private static Logger _log        = Logger.getLogger ( BaseDispatchAction.class );
    private static boolean IsEnterpriseMode = false;
    private static InitialContext ctx       = null;

    /**
     *  Override the default 'dispatchMethod' definition to catch all exceptions and forward to a default method
     *  called "defaultMethod"
     */
    protected ActionForward dispatchMethod ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String name )
    {
        try
        {
            return super.dispatchMethod ( mapping, form, request, response, name );
        }
        catch ( Exception exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass() );
                message.get().append ( "::dispatchMethod() - defaulting to the 'textbook_action' ActionForward" );
            _log.warn ( message.get().toString(), exception );

            return defaultMethod ( mapping, form, request, response );
        }
    }

    protected ActionForward defaultMethod ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        if ( _log.isEnabledFor ( Level.WARN ) )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass() );
                message.get().append ( "::defaultMethod() - default instance called" );
            _log.warn ( message.get().toString() );
        }

        return mapping.findForward ( "success" );
    }

    /**
     * Create a portal-independent, common HashMap <String, String> object for the developer to retrieve
     * HTTP request parameters from.
     *
     * @param request the HttpServletRequest object to extract parameters from
     */
    @SuppressWarnings ( "unchecked" )
    final public static Map <String, String> retrieveHttpRequestParameters ( HttpServletRequest request )
    {
        Map <String, String> param_map = new HashMap <String, String> ();

        //  Liferay parameter handling
        String liferayParameters = request.getParameter ( "_spage" );
        if ( null == liferayParameters ) liferayParameters = request.getParameter ( "_spageview" );

        if ( liferayParameters != null )
        {
            liferayParameters = liferayParameters.substring ( liferayParameters.indexOf ( "?" )+1 );

            // Parse out the tokens
            StringTokenizer tokenizer = new StringTokenizer ( liferayParameters, "&" );
            String key                = null;
            String value              = null;
            int index                 = 0;
            param_map                 = new HashMap <String, String> ();

            while ( tokenizer.hasMoreTokens() )
            {
                liferayParameters = tokenizer.nextToken();
                index     = liferayParameters.indexOf ( "=" );
                if ( index >= 0 )
                {
                    key   = liferayParameters.substring ( 0, index );
                    value = liferayParameters.substring ( index+1 );

                    param_map.put ( key, value );
                }
            }
        }

        Enumeration e = request.getParameterNames();
        String es     = null;
        param_map     = new HashMap <String, String>();
        while ( e.hasMoreElements() )
        {
            es = e.nextElement().toString();
            param_map.put ( es, request.getParameter ( es ) );
        }

        if ( null != request.getQueryString() )
        {
            StringTokenizer tokenizer = new StringTokenizer ( request.getQueryString(), "&" );
            while ( tokenizer.hasMoreTokens() )
            {
                String str[] = tokenizer.nextToken().split ( "=" );
                param_map.put ( str[0], str[1] );
            }
        }

        return param_map;
    }

    protected static boolean isEnterpriseMode()
    {
        return ApplicationPlugin.getEnterpriseMode();
    }

    public static Context getContext()
    {
        if ( null == ctx )
        {
            synchronized ( ApplicationPlugin.class )
            {
                if ( null == ctx )
                {
                    Properties JNDIProperties = ApplicationPlugin.getJNDIProperties();
                    try
                    {
                        ctx = new InitialContext ( JNDIProperties );
                    }
                    catch ( NamingException exception )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( ApplicationPlugin.class.getName() );
                            message.get().append ( "::getContext() could not obtain connection to app server:" );
                            message.get().append ( (null != JNDIProperties)? JNDIProperties.toString() : "null" );
                        _log.error ( message.get().toString(), exception );
                    }
                }
            }
        }

        return ctx;
    }

    /**
     *  Given an HttpServletRequest, return the PortletPreferences if it exists
     *
     *  @param request the HTTPServletRequest object to inspect
     *  @return the PortletPreferences object in the request, if it exists
     */
    final public static PortletPreferences getPortletPreferences ( HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );
        if ( null != renderRequest )
        {
            PortletPreferences portletPrefs = renderRequest.getPreferences();

            return portletPrefs;
        }
        else
        {
            return null;
        }
    }

    public boolean getIsEnterpriseMode()
    {
        return IsEnterpriseMode;
    }
}
