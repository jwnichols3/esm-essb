package com.bgi.esm.monitoring.portlets.DataMapRules.actions;

import java.lang.ref.WeakReference;
import java.util.List;

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
import com.bgi.esm.monitoring.portlets.DataMapRules.HibernateUtil;
import com.bgi.esm.monitoring.portlets.DataMapRules.Toolkit;
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap;
import com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit;
import com.bgi.esm.monitoring.portlets.DataMapRules.HibernateUtil;

public class DataMapAction extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( DataMapAction.class );

    private static BackEndFacade bef = new BackEndFacade();

    /**
     *
     */
    public ActionForward init ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "DataMapAction::init()" );

        DataMap data_form = (DataMap) form;
        data_form.reset ( mapping, request );

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward add ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "DataMapAction::add()" );

        int new_key =  0;
        
        try
        {
            new_key = bef.nextMonotonicSequenceForDataMap();
        }
        catch ( Exception exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( getClass().getName() );
            message.get().append ( "::add() - backend failure: " );
            message.get().append ( exception.getMessage() );

            _log.error ( message.get().toString(), exception );
        }

        DataMap data_form = (DataMap) form;
        data_form.setRuleId ( new_key );

        Session session = HibernateUtil.getCurrentSession();
        Transaction tx  = session.beginTransaction();
            session.save ( data_form );
        tx.commit();

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward edit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        DataMap data_form = (DataMap) form;

        String username = request.getRemoteUser();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference ( new StringBuilder() );
            message.get().append ( "DataMapAction::edit() - ( rule_id, username ) = ( " );
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
   public ActionForward search ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
   {
       HttpSession session = request.getSession(true);
       
       String group_name = request.getParameter("bgiGroup");
       
       if (group_name == null) {
    	   group_name = (String) session.getAttribute("group_name");
       }
       
       int num_records_per_page = 20;
       String strPageNum = request.getParameter("page_num");
       int page_num      = 0;
       if ( null != strPageNum )
       {
           try
           {
               page_num = Integer.parseInt ( strPageNum );
           }
           catch ( Exception exception )
           {
               page_num = 0;
           }
       }
       
       List <DataMap> dataMapList = DataMap.searchRecordsByGroup( group_name, page_num, num_records_per_page );
       session.setAttribute("dataMapList", dataMapList);
       session.setAttribute("group_name", group_name);

       return mapping.findForward ( "success" );
   }

    /**
     *
     */
    public ActionForward audit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "DataMapAction::audit()" );

        DataMapAudit data_form = (DataMapAudit) form;
        data_form.reset ( mapping, request );

        return mapping.findForward ( "success" );
    }
}
