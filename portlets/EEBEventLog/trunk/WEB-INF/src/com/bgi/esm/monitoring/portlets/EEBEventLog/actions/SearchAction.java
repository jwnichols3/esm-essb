package com.bgi.esm.monitoring.portlets.EEBEventLog.actions;

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
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.portlets.EEBEventLog.forms.SearchForm;


public class SearchAction extends DispatchAction
{
    final private static Logger _log          = Logger.getLogger ( SearchAction.class );
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );

    public ActionForward test ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchAction::test() - success" );

        return mapping.findForward ( "success" );
    }

    public ActionForward search ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        SearchForm data_form     = (SearchForm) form;
        String SearchGroup       = data_form.getSearchGroup();
        String SearchApplication = data_form.getSearchApplication();
        Calendar SearchStartTime = data_form.getSearchStartTime();
        Calendar SearchEndTime   = data_form.getSearchEndTime();
        HttpSession session      = request.getSession();

        try
        {
            WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
            List list                         = null;
            boolean valid_group               = (( null != SearchGroup ) && ( SearchGroup.trim().length() > 0 ));
            boolean valid_application         = (( null != SearchApplication ) && ( SearchApplication.trim().length() > 0 ));

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "SearchAction::search() - ( SearchGroup=" );
                message.get().append ( SearchGroup );
                message.get().append ( ", SearchApplication=" );
                message.get().append ( SearchApplication );
                message.get().append ( ", SearchStartTime=" );
                message.get().append ( sdf.format ( SearchStartTime.getTime() ) );
                message.get().append ( ", SearchEndTime=" );
                message.get().append ( sdf.format ( SearchEndTime.getTime() ) );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }

            if ( valid_group && valid_application )
            {
                _log.info ( "Searching by group and application" );

                list = bef.get().findAllBetweenTimesByGroupApplicationPaginate ( SearchGroup, SearchApplication, SearchStartTime, SearchEndTime, 25, 1 );
            }
            else if ( valid_group )
            {
                _log.info ( "Searching by group only" );

                list = bef.get().findAllBetweenTimesByGroupPaginate ( SearchGroup, SearchStartTime, SearchEndTime, 25, 1 );
            }
            else if ( valid_application )
            {
                _log.info ( "Searching by application only" );

                list = bef.get().findAllBetweenTimesByApplicationPaginate ( SearchApplication, SearchStartTime, SearchEndTime, 25, 1 );
            }

            session.setAttribute ( "EEBEventLog-Search-Group",       SearchGroup       );
            session.setAttribute ( "EEBEventLog-Search-Application", SearchApplication );
            session.setAttribute ( "EEBEventLog-Search-StartTime",   SearchStartTime   );
            session.setAttribute ( "EEBEventLog-Search-EndTime",     SearchEndTime     );
            session.setAttribute ( "EEBEventLog-Search-Results",     list              );
            session.setAttribute ( "EEBEventLog-Search-PageNum",     new Integer ( 1 ) );

            if ( null != list )
            {
                session.setAttribute ( "EEBEventLog-Search-PageNum",     new Integer ( 1 ) );

                _log.info ( "SearchAction::search() - success" );

                return mapping.findForward ( "success" );
            }
            else
            {
                _log.info ( "SearchAction::search() - unknown" );

                return mapping.findForward ( "unknown" );
            }
        }
        catch ( BackEndFailure exception )
        {
            _log.info ( "SearchAction::search() - exception" );

            return mapping.findForward ( "exception" );
        }
    }
}
