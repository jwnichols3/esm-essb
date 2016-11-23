package com.bgi.esm.monitoring.portlets.EEBEventLog.actions.edit;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BlankAction extends Action
{
    final private static Logger _log = Logger.getLogger ( BlankAction.class );

    public ActionForward execute ( ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );
        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.store();
        }

        return mapping.findForward ( "success" );
    }
};
