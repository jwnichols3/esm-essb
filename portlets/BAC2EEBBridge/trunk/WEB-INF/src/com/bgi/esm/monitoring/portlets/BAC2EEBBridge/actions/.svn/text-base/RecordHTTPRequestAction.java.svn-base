package com.bgi.esm.monitoring.portlets.BAC2EEBBridge.actions;

import java.util.Enumeration;
import java.util.Vector;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpCookie;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpFinder;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpParameter;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpRequest;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpAttribute;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.AbstractHttpHeader;
import com.bgi.esm.monitoring.portlets.BAC2EEBBridge.persistence.hibernate.HibernateHttpFinder;


public class RecordHTTPRequestAction extends Action
{
     /**
      *  Called when the page assigned to this action is served up.
      *
      *  @param mapping all the resulting mappings that are available from this point
      *  @param form the Struts HTML form with all the user-entered data
      *  @param request the HTTP request object
      *  @param response the HTTP response object
      *
      *  @return an ActionForward object that determines the next execution step to take      
      */
    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
            throws Exception
    {
        try
        {
            //  Save the request
            AbstractHttpRequest savedRequest = HibernateHttpFinder.createRequest();
            savedRequest.setValue ( request );
            HibernateHttpFinder.insertOrUpdateRequest ( savedRequest );

            _log.info ( "Saved request: " + savedRequest.getRowID() );

            //  Process the headers
            Enumeration headerNames = request.getHeaderNames();
            while ( headerNames.hasMoreElements() )
            {
                String header_name = headerNames.nextElement().toString();
                AbstractHttpHeader header = HibernateHttpFinder.createRequestHeader();
                    header.setRequestID   ( savedRequest.getRowID() );
                    header.setHeaderName  ( header_name );
                    header.setHeaderValue ( request.getHeader ( header_name ) );
                HibernateHttpFinder.insertOrUpdateRequestHeader ( header );

                _log.info ( String.format ( "--- Saving Header ( %s, %s )", header_name, header.getHeaderValue() ) );
            }
    
            
            //  Process the parameters
            Enumeration paramNames = request.getParameterNames();
            while ( paramNames.hasMoreElements() )
            {
                String param_name = paramNames.nextElement().toString();
                AbstractHttpParameter parameter = HibernateHttpFinder.createRequestParameter();
                    parameter.setRequestID      ( savedRequest.getRowID() );
                    parameter.setParameterName  ( param_name );
                    parameter.setParameterValue ( request.getParameter ( param_name ) );
                HibernateHttpFinder.insertOrUpdateRequestParameter ( parameter );

                _log.info ( String.format ( "--- Saving Parameter ( %s, %s )", param_name, parameter.getParameterValue() ) );
            }
            
            //  Process the attributes
            Enumeration attrNames = request.getParameterNames();
            while ( attrNames.hasMoreElements() )
            {
                String attr_name  = attrNames.nextElement().toString();
                Object attr_value = request.getAttribute ( attr_name );
                AbstractHttpAttribute attribute = HibernateHttpFinder.createRequestAttribute();
                    attribute.setRequestID ( savedRequest.getRowID() );
                    attribute.setAttributeName  ( attr_name );
                    attribute.setAttributeValue ( ( null != attr_value )? attr_value.toString() : null );
                HibernateHttpFinder.insertOrUpdateRequestAttribute ( attribute );

                _log.info ( String.format ( "--- Saving Attribute ( %s, %s )", attr_name, attribute.getAttributeValue() ) );
            }
            
            //  Process the cookies 
            Cookie cookies[] = request.getCookies();
            for ( int counter = 0; counter < cookies.length; counter++ )
            {
                AbstractHttpCookie cookie = HibernateHttpFinder.createCookie();
                    cookie.setValue     ( cookies[counter]        );
                    cookie.setRequestID ( savedRequest.getRowID() );
                HibernateHttpFinder.insertOrUpdateCookie ( cookie );

                _log.info ( String.format ( "--- Saving Cookie ( %s, %s )", cookie.getCookieName(), cookie.getCookieValue() ) );
            }

            return mapping.findForward ( "success" );
        }
        catch ( Exception exception )
        {
            _log.error ( exception.getMessage(), exception );

            return mapping.findForward ( "false" );
        }
    }
    
    private static Logger _log = Logger.getLogger ( RecordHTTPRequestAction.class );
}

