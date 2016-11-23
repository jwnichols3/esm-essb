package com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.forms.HttpRequestForm;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.Configuration;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpRequestFinder;
import com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest;
import com.bgi.esm.monitoring.splunk.splunk_send.server.apps.ejb.v03_00.sessions.remote.IHttpRequestRemote;

public class HttpRequestDispatchAction extends BaseDispatchAction
{
    final private static Logger _log = Logger.getLogger ( HttpRequestDispatchAction.class );

    /**
     *  Resets the form
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    public ActionForward init ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        Map <String, String> param_map = retrieveHttpRequestParameters ( request );
        String username                = request.getRemoteUser();
        String remoteHostname          = request.getRemoteHost();
        String remoteAddress           = request.getRemoteAddr();
        int remotePort                 = request.getRemotePort();
        String PrimaryKey              = param_map.get ( "PrimaryKey" );

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::init() - ( Form=" );
                if ( null != data_form )
                {
                    message.get().append ( data_form.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( ", Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( ", PrimaryKey=" );
                message.get().append ( PrimaryKey );
                message.get().append ( ", RemoteHostname=" );
                message.get().append ( remoteHostname );
                message.get().append ( ", RemoteAddress=" );
                message.get().append ( remoteAddress );
                message.get().append ( ", RemotePort=" );
                message.get().append ( remotePort );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        boolean result = false;

        //  Retrieve the record
        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            IHttpRequest            object_select = finder.select ( PrimaryKey );

            if ( null != object_select )
            {
                data_form.setValue ( object_select );
                result = true;
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::init() - ( Form=" );
                        if ( null != data_form )
                        {
                            message.get().append ( data_form.toString() );
                        }
                        else
                        {
                            message.get().append ( "null" );
                        }
                        message.get().append ( ", Username=" );
                        message.get().append ( username );
                        message.get().append ( ", IsEnterpriseMode=" );
                        message.get().append ( isEnterpriseMode() );
                        message.get().append ( ", PrimaryKey=" );
                        message.get().append ( PrimaryKey );
                        message.get().append ( " ) - form initialized" );
                    _log.info ( message.get().toString() );
                }
            }
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                IHttpRequest object_select = bean.selectRemote ( PrimaryKey );
                if ( null != object_select )
                {
                    data_form.setValue ( object_select );
                    result = true;
                    if ( _log.isInfoEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( getClass().getName() );
                            message.get().append ( "::init() - ( Form=" );
                            if ( null != data_form )
                            {
                                message.get().append ( data_form.toString() );
                            }
                            else
                            {
                                message.get().append ( "null" );
                            }
                            message.get().append ( ", Username=" );
                            message.get().append ( username );
                            message.get().append ( ", IsEnterpriseMode=" );
                            message.get().append ( isEnterpriseMode() );
                            message.get().append ( ", PrimaryKey=" );
                            message.get().append ( PrimaryKey );
                            message.get().append ( " ) - form initialized" );
                        _log.info ( message.get().toString() );
                    }
                }
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::init ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             return mapping.findForward ( "success" );
        }
        else
        {
            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::init() - ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( ", Username=" );
                    message.get().append ( username );
                    message.get().append ( ", IsEnterpriseMode=" );
                    message.get().append ( isEnterpriseMode() );
                    message.get().append ( " ) - could not update" );
                 _log.warn ( message.get().toString() );
            }

             return mapping.findForward ( "failure" );
        }
    }

    /**
     *  Saves the information from the form
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    public ActionForward add ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        String username       = request.getRemoteUser();
        String remoteHostname = request.getRemoteHost();
        String remoteAddress  = request.getRemoteAddr();
        int remotePort        = request.getRemotePort();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::add() - ( Form=" );
                if ( null != data_form )
                {
                    message.get().append ( data_form.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( ", Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        boolean result = false;

        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            result = finder.insert ( data_form, username, remoteHostname, remoteAddress, remotePort );
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
                IHttpRequest object_insert = finder.newInstance();
                object_insert.setValue ( data_form );
                bean.insertRemote ( object_insert );

                if ( null == object_insert )
                {
                    if ( _log.isEnabledFor ( Level.WARN ) )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( getClass().getName() );
                            message.get().append ( "::add( Form=" );
                            if ( null != data_form )
                            {
                                message.get().append ( data_form.toString() );
                            }
                            else
                            {
                                message.get().append ( "null" );
                            }
                            message.get().append ( " ) - null returned after insert" );
                        _log.warn ( message.get().toString() );
                    }
                }

                result = true;
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::add( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             //  Prepare records view page
             return browse ( mapping, form, request, response );
        }
        else
        {
             return mapping.findForward ( "failure" );
        }
    }

    /**
     *  Updates an existing record with the information found in the form
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    public ActionForward edit ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        String username       = request.getRemoteUser();
        String remoteHostname = request.getRemoteHost();
        String remoteAddress  = request.getRemoteAddr();
        int remotePort        = request.getRemotePort();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::edit() - ( Form=" );
                if ( null != data_form )
                {
                    message.get().append ( data_form.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( ", Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        boolean result = false;

        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            IHttpRequest            object_select = finder.select ( data_form.getRequestID() );

            if ( null != object_select )
            {
                object_select.setValue ( data_form );
                result = finder.update ( object_select, username, remoteHostname, remoteAddress, remotePort );
            }
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                IHttpRequest object_select = bean.selectRemote ( data_form.getRequestID() );
                if ( null != object_select )
                {
                    object_select.setValue ( data_form );
                    bean.updateRemote ( object_select );

                    result = true;
                }
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::edit ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             //  Prepare records view page
             return browse ( mapping, form, request, response );
        }
        else
        {
            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( "::edit() - ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( ", Username=" );
                    message.get().append ( username );
                    message.get().append ( ", IsEnterpriseMode=" );
                    message.get().append ( isEnterpriseMode() );
                    message.get().append ( " ) - could not update" );
                 _log.warn ( message.get().toString() );
            }

             return mapping.findForward ( "failure" );
        }
    }

    /**
     *  Saves the information from the form
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    public ActionForward delete ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        String username       = request.getRemoteUser();
        String remoteHostname = request.getRemoteHost();
        String remoteAddress  = request.getRemoteAddr();
        int remotePort        = request.getRemotePort();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::delete() - ( Form=" );
                if ( null != data_form )
                {
                    message.get().append ( data_form.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( ", Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        boolean result = false;

        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            result = finder.delete ( data_form, username, remoteHostname, remoteAddress, remotePort );
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                result = bean.deleteRemote ( data_form );
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::delete( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             return mapping.findForward ( "success" );
        }
        else
        {
             return mapping.findForward ( "failure" );
        }
    }

    /**
     *  Allows the browsing of existing records in the persistence layer
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    @SuppressWarnings ( "unchecked" )
    public ActionForward browse ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        String username       = request.getRemoteUser();
        String remoteHostname = request.getRemoteHost();
        String remoteAddress  = request.getRemoteAddr();
        int remotePort        = request.getRemotePort();

        boolean result              = false;
        HttpSession session         = request.getSession();
        Map <String, String> params = retrieveHttpRequestParameters ( request );
        List <IHttpRequest> browseRecords = (List <IHttpRequest>) session.getAttribute ( IHttpRequest.SESSION_BROWSE );
        int PageNum        = 0;
        int PageSize       = 0;
        String strPageNum  = params.get ( "page_num"  );
        String strPageSize = params.get ( "page_size" );

        try
        {
            PageNum = Integer.parseInt ( strPageNum );
        }
        catch ( Exception exception )
        {
            PageNum = 0;
        }

        try
        {
            PageSize = Integer.parseInt ( strPageSize );
        }
        catch ( Exception exception )
        {
            PageSize = 25;
        }

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::browse() - ( Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( ", PageNum=" );
                message.get().append ( PageNum );
                message.get().append ( ", PageSize=" );
                message.get().append ( PageSize );
                message.get().append ( ", RemoteHostname=" );
                message.get().append ( remoteHostname );
                message.get().append ( ", RemoteAddress=" );
                message.get().append ( remoteAddress );
                message.get().append ( ", RemotePort=" );
                message.get().append ( remotePort );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            browseRecords = finder.selectAll ( PageSize, PageNum );
            session.setAttribute ( IHttpRequest.SESSION_BROWSE, browseRecords );

            result = ( null != browseRecords );
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                browseRecords = bean.selectAllRemote ( PageSize, PageNum );
                session.setAttribute ( IHttpRequest.SESSION_BROWSE, browseRecords );

                result = ( null != browseRecords );
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::browse ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             _log.info ( "Num records found: " + browseRecords.size() );
             return mapping.findForward ( "success" );
        }
        else
        {
            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::browse() - ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( ", Username=" );
                    message.get().append ( username );
                    message.get().append ( ", IsEnterpriseMode=" );
                    message.get().append ( isEnterpriseMode() );
                    message.get().append ( " ) - could not update" );
                 _log.warn ( message.get().toString() );
            }

            return mapping.findForward ( "failure" );
        }
    }

    /**
     *  Allows the viewing of a single record and all its audit records
     *
     *  @param mapping The ActionMapping used to select this instance
     *  @param form The optional ActionForm bean for this request (if any)
     *  @param request The HTTP request we are processing
     *  @param response The HTTP response we are creating
     **/
    public ActionForward selectRecord ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        HttpRequestForm data_form = (HttpRequestForm) form;
        Map <String, String> param_map = retrieveHttpRequestParameters ( request );
        String username                = request.getRemoteUser();
        String remoteHostname          = request.getRemoteHost();
        String remoteAddress           = request.getRemoteAddr();
        int remotePort                 = request.getRemotePort();
        String PrimaryKey              = param_map.get ( "PrimaryKey" );
        HttpSession session            = request.getSession();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( getClass().getName() );
                message.get().append ( "::selectRecord() - ( Form=" );
                if ( null != data_form )
                {
                    message.get().append ( data_form.toString() );
                }
                else
                {
                    message.get().append ( "null" );
                }
                message.get().append ( ", Username=" );
                message.get().append ( username );
                message.get().append ( ", IsEnterpriseMode=" );
                message.get().append ( isEnterpriseMode() );
                message.get().append ( ", PrimaryKey=" );
                message.get().append ( PrimaryKey );
                message.get().append ( ", RemoteHostname=" );
                message.get().append ( remoteHostname );
                message.get().append ( ", RemoteAddress=" );
                message.get().append ( remoteAddress );
                message.get().append ( ", RemotePort=" );
                message.get().append ( remotePort );
                message.get().append ( " )" );
            _log.info ( message.get().toString() );
        }

        boolean result = false;

        //  Retrieve the record
        if ( false == isEnterpriseMode() )
        {
            IHttpRequestFinder finder = Configuration.getInstance().getHttpRequestFinder();
            IHttpRequest            object_select = finder.select ( PrimaryKey );

            if ( null != object_select )
            {
                session.setAttribute ( IHttpRequest.SESSION_SELECT, object_select );
                result = true;
                if ( _log.isInfoEnabled() )
                {
                    WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                        message.get().append ( getClass().getName() );
                        message.get().append ( "::selectRecord() - ( Form=" );
                        if ( null != data_form )
                        {
                            message.get().append ( data_form.toString() );
                        }
                        else
                        {
                            message.get().append ( "null" );
                        }
                        message.get().append ( ", Username=" );
                        message.get().append ( username );
                        message.get().append ( ", IsEnterpriseMode=" );
                        message.get().append ( isEnterpriseMode() );
                        message.get().append ( ", PrimaryKey=" );
                        message.get().append ( PrimaryKey );
                        message.get().append ( ", RemoteHostname=" );
                        message.get().append ( remoteHostname );
                        message.get().append ( ", RemoteAddress=" );
                        message.get().append ( remoteAddress );
                        message.get().append ( ", RemotePort=" );
                        message.get().append ( remotePort );
                        message.get().append ( " ) - selectRecord" );
                    _log.info ( message.get().toString() );
                }
            }
        }
        else
        {
            try
            {
                IHttpRequestRemote bean = (IHttpRequestRemote) getContext().lookup ( "ejb/HttpRequestStateless" );
                IHttpRequest object_select = bean.selectRemote ( PrimaryKey );
                if ( null != object_select )
                {
                    session.setAttribute ( IHttpRequest.SESSION_SELECT, object_select );
                    result = true;

                    if ( _log.isInfoEnabled() )
                    {
                        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                            message.get().append ( getClass().getName() );
                            message.get().append ( "::selectRecord() - ( Form=" );
                            if ( null != data_form )
                            {
                                message.get().append ( data_form.toString() );
                            }
                            else
                            {
                                message.get().append ( "null" );
                            }
                            message.get().append ( ", Username=" );
                            message.get().append ( username );
                            message.get().append ( ", IsEnterpriseMode=" );
                            message.get().append ( isEnterpriseMode() );
                            message.get().append ( ", PrimaryKey=" );
                            message.get().append ( PrimaryKey );
                            message.get().append ( ", RemoteHostname=" );
                            message.get().append ( remoteHostname );
                            message.get().append ( ", RemoteAddress=" );
                            message.get().append ( remoteAddress );
                            message.get().append ( ", RemotePort=" );
                            message.get().append ( remotePort );
                            message.get().append ( " ) - selectRecord" );
                        _log.info ( message.get().toString() );
                    }
                }
            }
            catch ( NamingException exception )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::selectRecord ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( " ) - could not connect to app server: " );
                    message.get().append ( exception.getMessage() );
                _log.error ( message.get().toString(), exception );

                result = false;
            }
        }

        if ( result )
        {
             return mapping.findForward ( "success" );
        }
        else
        {
            if ( _log.isEnabledFor ( Level.WARN ) )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                    message.get().append ( getClass().getName() );
                    message.get().append ( "::selectRecord() - ( Form=" );
                    if ( null != data_form )
                    {
                        message.get().append ( data_form.toString() );
                    }
                    else
                    {
                        message.get().append ( "null" );
                    }
                    message.get().append ( ", Username=" );
                    message.get().append ( username );
                    message.get().append ( ", IsEnterpriseMode=" );
                    message.get().append ( isEnterpriseMode() );
                    message.get().append ( ", RemoteHostname=" );
                    message.get().append ( remoteHostname );
                    message.get().append ( ", RemoteAddress=" );
                    message.get().append ( remoteAddress );
                    message.get().append ( ", RemotePort=" );
                    message.get().append ( remotePort );
                    message.get().append ( " ) - could not update" );
                 _log.warn ( message.get().toString() );
            }

             return mapping.findForward ( "failure" );
        }
    }
}
