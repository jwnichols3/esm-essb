package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions;

import java.lang.ref.WeakReference;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject;


public class DataMapInitEditAction extends DataMapDispatchAction
{
    /*
     *
     */
    //  XXX___SERIALVER__XXX
    final private static Logger _log           = Logger.getLogger ( DataMapEditAction.class );


    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        Date timestamp = new Date();
        //  Log start
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::execute() - ( Username=" );
                message.get().append ( request.getRemoteUser() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - begin" );
            _log.info ( message.get().toString() );
        }

        //  Business logic
        ActionForward forward = init ( mapping, form, request, response );

        //  Log finish
        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::execute() - ( Username=" );
                message.get().append ( request.getRemoteUser() );
                message.get().append ( ", Timestamp=" );
                message.get().append ( CommonObject.sdf.format ( timestamp ) );
                message.get().append ( " ) - end" );
            _log.info ( message.get().toString() );
        }
        return forward;
    }
}
