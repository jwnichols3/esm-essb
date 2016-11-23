package com.bgi.esm.monitoring.portlets.Suppressions.actions;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.TimeZone;
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
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression;
import com.bgi.esm.monitoring.portlets.Suppressions.HibernateUtil;

public class SearchAction extends DispatchAction
{
    final private static Logger _log = Logger.getLogger ( SearchAction.class );

    private int computeTimezoneOffset ( HttpServletRequest request ) throws Exception
    {
        BackEndFacade bef  = new BackEndFacade();
        String strTimezone = bef.selectEebProperty( "server.time_zone" ).getPropertyValue();
        TimeZone tz_server = TimeZone.getTimeZone ( strTimezone );
        int tz_daylight_savings_offset = tz_server.getDSTSavings();
        int tz_timezone_offset         = tz_server.getRawOffset();
        int tz_offset                  = tz_timezone_offset + tz_daylight_savings_offset;
        int browser_offset             = Toolkit.computeTimeZoneOffset ( request );
        //int offset                     = browser_offset - tz_offset;
        int offset                     = tz_offset;

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "SearchAction::computeTimezoneOffset ( Server=" );
            message.get().append ( tz_offset );
            message.get().append ( ", Browser=" );
            message.get().append ( browser_offset );
            message.get().append ( ", TotalOffset=" );
            message.get().append ( offset );
            message.get().append ( " )" );

            _log.info ( message.get().toString() );
        }

        return offset;
    }

    /**
     *
     */
    public ActionForward current ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchAction::current()" );

        try
        {
            List <Suppression> resultList = Suppression.selectAllActive( computeTimezoneOffset ( request ) );
            HttpSession session = request.getSession();
            session.setAttribute ( Suppression.SESSION_KEY_SEARCH_RESULTS, resultList );

            return mapping.findForward ( "success" );
        }
        catch ( Exception exception )
        {
            _log.error ( "Exception: " + exception.getMessage(), exception );

            return mapping.findForward ( "failure" );
        }
    }

    public ActionForward myCurrent ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchAction::myCurrent()" );

        if ( null == request.getRemoteUser() )
        {
            return mapping.findForward ( "not-logged-in" );
        }

        try
        {
            List <Suppression> resultList = Suppression.selectAllActiveForUser ( computeTimezoneOffset ( request ), request.getRemoteUser() );
            HttpSession session = request.getSession();
            session.setAttribute ( Suppression.SESSION_KEY_SEARCH_RESULTS, resultList );

            return mapping.findForward ( "success" );
        }
        catch ( Exception exception )
        {
            _log.error ( "Exception: " + exception.getMessage(), exception );

            return mapping.findForward ( "failure" );
        }
    }

    public ActionForward genericSearch ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchAction::genericSearch()" );

        try
        {
            List <Suppression> resultList = Suppression.selectAllActiveForUser ( computeTimezoneOffset ( request ), request.getRemoteUser() );
            HttpSession session = request.getSession();
            session.setAttribute ( Suppression.SESSION_KEY_SEARCH_RESULTS, resultList );

            return mapping.findForward ( "success" );
        }
        catch ( Exception exception )
        {
            _log.error ( "Exception: " + exception.getMessage(), exception );

            return mapping.findForward ( "failure" );
        }
    }
}
