package com.bgi.esm.monitoring.portlets.Suppressions.actions;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.EditEntry;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;

public class SuppressionAction extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( SuppressionAction.class );
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
    final private static BackEndFacade bef    = new BackEndFacade();

    /**
     *
     */
    public ActionForward init ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SuppressionAction::init()" );

        Suppression data_form = (Suppression) form;
        data_form.reset ( mapping, request );

        return mapping.findForward ( "success" );
    }

    /**
     *
     */
    public ActionForward add ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SuppressionAction::add()" );

        Suppression data_form = (Suppression) form;
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "SuppressionAction::add() - ( StartTime=" );
            message.get().append ( sdf.format ( data_form.getStartTime().getTime() ) );
            message.get().append ( ", EndTime=" );
            message.get().append ( sdf.format ( data_form.getEndTime().getTime() ) );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        String username = request.getRemoteUser();

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
        throws BackEndFailure
    {
        _log.info ( "SuppressionAction::edit()" );

        Suppression data_form = (Suppression) form;
        //EditEntry data_form = (EditEntry) form;
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "SuppressionAction::edit() - ( StartTime=" );
            message.get().append ( sdf.format ( data_form.getStartTime().getTime() ) );
            message.get().append ( ", EndTime=" );
            message.get().append ( sdf.format ( data_form.getEndTime().getTime() ) );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        String username = request.getRemoteUser();

        //*
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference ( new StringBuilder() );
            message.get().append ( "SuppressionAction::edit() - ( suppress_id, username ) = ( " );
            message.get().append ( data_form.getSuppressId() );
            message.get().append ( ", " );
            message.get().append ( username );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        //data_form.insertOrUpdate ( username );
        SuppressionRule suppressionRule = bef.selectSuppressionRuleBySuppressId ( data_form.getSuppressId() );
        suppressionRule.setApplicationName    ( data_form.getAppName()        );
        suppressionRule.setNodeName           ( data_form.getNodeName()       );
        suppressionRule.setGroupName          ( data_form.getGroupName()      );
        suppressionRule.setStartTime          ( data_form.getStartTime()      );
        suppressionRule.setEndTime            ( data_form.getEndTime()        );
        suppressionRule.setDatabaseServerName ( data_form.getDbServer()       );
        suppressionRule.setMessage            ( data_form.getMessage()        );
        suppressionRule.setDescription        ( data_form.getDescription()    );
        suppressionRule.setDeletedFlag        ( false                         );
        suppressionRule.setNotificationFlag   ( data_form.getNotifyFlg() == 1 );
        suppressionRule.setNotifyMinutes      ( data_form.getNotifyMinutes()  );
        suppressionRule.setRemoveOnReboot     ( data_form.getRemoveOnReboot() == 1 );
        suppressionRule.setNotifyEmail        ( data_form.getNotifyEmail()    );

        SuppressionRule result = bef.addOrUpdateSuppressionRule ( suppressionRule );

        if ( null != result )
        {
            return mapping.findForward ( "success" );
        }
        else
        {
            return mapping.findForward ( "failure" );
        }
    }

    public ActionForward audit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
        throws BackEndFailure
    {
        String strSuppressID  = request.getParameter ( "suppress_id"       );
        String strAuditVerNum = request.getParameter ( "audit_version_num" );

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "SuppressionAction::audit ( SuppressID=" );
            message.get().append ( strSuppressID );
            message.get().append ( ", AuditVersionNum=" );
            message.get().append ( strAuditVerNum );

            _log.info ( message.get().toString() );
        }

        long suppress_id       = Long.parseLong ( strSuppressID  );
        long audit_version_num = Long.parseLong ( strAuditVerNum );

        List auditRecords = (List) bef.getSuppressionRuleAuditVersions ( suppress_id );

        if (( null == auditRecords ) || ( auditRecords.size() == 0 ))
        {
            return mapping.findForward ( "failure" );
        }

        boolean found_record = false;
        SuppressionRuleAudit auditRecord = null;
        for ( int counter = 0; counter < auditRecords.size(); counter++ )
        {
            auditRecord = (SuppressionRuleAudit) auditRecords.get ( counter );

            if ( auditRecord.getAuditVersionNum() == audit_version_num )
            {
                found_record = true;

                break;
            }
        }

        if ( found_record )
        {
            HttpSession session = request.getSession();
            session.setAttribute ( "RetrievedAuditRecord", auditRecord );

            return mapping.findForward ( "success" );
        }
        else
        {
            return mapping.findForward ( "failure" );
        }
    }
}
