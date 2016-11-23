package com.bgi.esm.monitoring.portlets.EEBEventLog.actions.edit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

public class DatabaseSettingsAction extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( DatabaseSettingsAction.class );

    public ActionForward init ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {


        return mapping.findForward ( "success" );
    }

    public ActionForward save ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        return mapping.findForward ( "success" );
    }
}

