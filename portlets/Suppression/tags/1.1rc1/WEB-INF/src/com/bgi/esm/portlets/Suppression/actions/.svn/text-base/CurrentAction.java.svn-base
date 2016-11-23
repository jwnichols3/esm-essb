package com.bgi.esm.portlets.Suppression.actions;

import java.util.HashMap;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.Toolkit;

/**
 * <a href="CurrentAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class CurrentAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        _log.debug ( "com.bgi.esm.portlets.Suppression.actions.CurrentAction::execute()" );

        PortletRequest portletRequest = (PortletRequest) request.getAttribute ( "javax.portlet.request" );

        if ( portletRequest instanceof RenderRequest )
        {
            RenderRequest renderRequest = (RenderRequest) portletRequest;

            int timezone_offset = Toolkit.computeTimeZoneOffset ( request );

            HashMap h = Toolkit.getCurrentSuppressionsHashmap ( renderRequest, timezone_offset );

            HttpSession session = request.getSession();

            session.setAttribute ( "results-start-time", new java.util.Date () );
            session.setAttribute ( "results-hashmap", h );
            session.setAttribute ( "results-end-time", new java.util.Date () );
        }

        return ( mapping.findForward ( "success" ) );
    }
  
    private static final Logger _log = Logger.getLogger (CurrentAction.class);
}
