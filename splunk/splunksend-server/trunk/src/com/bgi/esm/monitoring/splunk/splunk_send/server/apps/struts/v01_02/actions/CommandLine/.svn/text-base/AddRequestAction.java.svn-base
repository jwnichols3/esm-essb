package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.CommandLine;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpAttributeFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpCookieFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpHeaderFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.transactions.SaveHttpRequestToLocalDisk;

public class AddRequestAction extends BaseDispatchAction
{
    final private static Logger _log = Logger.getLogger ( AddRequestAction.class );

    @SuppressWarnings("unchecked")
    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        try
        {
            List <String> ErrorMessages = SaveHttpRequestToLocalDisk.saveHttpRequest ( request );

            if ( null == ErrorMessages )
            {
                return mapping.findForward ( "success" );
            }
            else
            {
                for ( int counter = 0; counter < ErrorMessages.size(); counter++ )
                {
                    _log.error ( "Error: " + ErrorMessages.get ( counter ) );
                }

                HttpSession session = request.getSession();
                session.setAttribute ( "ERROR_MESSAGES", ErrorMessages );
            }
        }
        catch ( RuntimeException exception )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::execute() - error encountered: " );
                message.get().append ( exception.getMessage() );
            _log.error ( message.get().toString(), exception );
        }

        return mapping.findForward ( "failure" );
    }
}
