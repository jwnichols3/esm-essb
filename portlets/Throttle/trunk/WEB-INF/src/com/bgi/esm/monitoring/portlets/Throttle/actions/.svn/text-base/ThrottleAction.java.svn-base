package com.bgi.esm.monitoring.portlets.Throttle.actions;

import java.lang.ref.WeakReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Throttle.Toolkit;
import com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle;
import com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;

public class ThrottleAction extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( ThrottleAction.class );

    /**
     *
     */
    public ActionForward init ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "ThrottleAction::init()" );

        Throttle data_form = (Throttle) form;
        data_form.reset ( mapping, request );

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward add ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "ThrottleAction::add()" );
        
        String username = request.getRemoteUser();
        Throttle data_form = (Throttle) form;
        data_form.insertOrUpdate ( username );

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward edit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        Throttle data_form = (Throttle) form;

        String username = request.getRemoteUser();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference ( new StringBuilder() );
            message.get().append ( "ThrottleAction::edit() - ( rule_id, username ) = ( " );
            message.get().append ( data_form.getRuleId() );
            message.get().append ( ", " );
            message.get().append ( username );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        data_form.insertOrUpdate ( username );

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward audit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "ThrottleAction::audit()" );

        ThrottleAudit data_form = (ThrottleAudit) form;
        data_form.reset ( mapping, request );

        return mapping.findForward ( "success" );
    }
}
