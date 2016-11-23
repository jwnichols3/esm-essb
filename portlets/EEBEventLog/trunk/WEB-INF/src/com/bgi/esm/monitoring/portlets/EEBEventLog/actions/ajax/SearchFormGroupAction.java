package com.bgi.esm.monitoring.portlets.EEBEventLog.actions.ajax;

import java.util.List;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;

public class SearchFormGroupAction extends Action
{
    final private static Logger _log = Logger.getLogger ( SearchFormGroupAction.class );

    public ActionForward execute ( ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
        _log.info ( "Retrieving renderRequest" );
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );
        if ( null != renderRequest )
        {
            _log.info ( "Retrieving PortletPreferences" );
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.store();
        }

        try
        {
            _log.info ( "Retrieving EEB data map rules" );
            BackEndFacade bef = new BackEndFacade();
            List rules = bef.getAllDataMapRules();
            HttpSession session = request.getSession();
            session.setAttribute ( "SearchForm-DataMapRules", rules );

            _log.info ( "Finished... success" );

            return mapping.findForward ( "success" );
        }
        catch ( BackEndFailure exception )
        {
            _log.info ( "Finished... failure" );

            return mapping.findForward ( "failure" );
        }
    }
};
