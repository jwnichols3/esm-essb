package com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.actions;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

public class BlankAction extends Action
{
     /**
      *  Called when the page assigned to this action is served up.
      *
      *  @param mapping all the resulting mappings that are available from this point
      *  @param form the Struts HTML form with all the user-entered data
      *  @param request the HTTP request object
      *  @param response the HTTP response object
      *
      *  @return an ActionForward object that determines the next execution step to take      */
    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
            throws Exception
    {
        _log.debug ( "execute() - trying to retrieve renderRequest" );
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );
        
        if ( null != renderRequest )
        {
            _log.debug ( "execute() - Found renderRequest, trying to retrieve portlet preferences" );
            
            PortletPreferences prefs = renderRequest.getPreferences();
            
            _log.debug ( "execute() - Trying to save portlet preferences" );

            prefs.store();
            
            _log.debug ( "execute() - success!" );
        }

        return mapping.findForward ("success");
    }
    
    private static Logger _log = Logger.getLogger ( BlankAction.class );
}
