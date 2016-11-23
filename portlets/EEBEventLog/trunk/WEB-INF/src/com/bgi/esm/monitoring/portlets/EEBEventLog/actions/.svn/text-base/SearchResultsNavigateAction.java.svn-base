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


public class SearchResultsNavigateAction extends DispatchAction
{
    final private static Logger _log          = Logger.getLogger ( SearchResultsNavigateAction.class );
    final private static SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );

    public ActionForward test ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchResultsNavigateAction::test() - success" );

        return mapping.findForward ( "success" );
    }

    public ActionForward pageUp ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchResultsNavigateAction::pageUp()" );

        try
        {
            WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
            HttpSession session        = request.getSession();
            String   SearchGroup       = (String)   session.getAttribute ( "EEBEventLog-Search-Group"       );
            String   SearchApplication = (String)   session.getAttribute ( "EEBEventLog-Search-Application" );
            Calendar SearchStartTime   = (Calendar) session.getAttribute ( "EEBEventLog-Search-StartTime"   );
            Calendar SearchEndTime     = (Calendar) session.getAttribute ( "EEBEventLog-Search-EndTime"     );
            Integer  SearchPageNumOld  = (Integer)  session.getAttribute ( "EEBEventLog-Search-PageNum"     );
            Integer  SearchPageNum     = new Integer ( SearchPageNumOld.intValue() + 1 );
            boolean valid_group        = (( null != SearchGroup ) && ( SearchGroup.trim().length() > 0 ));
            boolean valid_application  = (( null != SearchApplication ) && ( SearchApplication.trim().length() > 0 ));
            List list                  = null;

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "SearchResultsNavigateAtion::pageUp ( User=" );
                message.get().append ( request.getRemoteUser() );
                message.get().append ( ", SearchGroup=" );
                message.get().append ( SearchGroup );
                message.get().append ( ", SearchApplication=" );
                message.get().append ( SearchApplication );
                message.get().append ( ", SearchStartTime=" );
                message.get().append ( sdf.format ( SearchStartTime.getTime() ) );
                message.get().append ( ", SearchEndTime=" );
                message.get().append ( sdf.format ( SearchEndTime.getTime() ) );
                message.get().append ( ", PageNum=" );
                message.get().append ( SearchPageNum );
                message.get().append ( " ), " );

                _log.info ( message.get().toString() );
            }

            if ( valid_group && valid_application )
            {
                _log.info ( "Searching by group and application" );

                list = bef.get().findAllBetweenTimesByGroupApplicationPaginate ( SearchGroup, SearchApplication, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }
            else if ( valid_group )
            {
                _log.info ( "Searching by group only" );

                list = bef.get().findAllBetweenTimesByGroupPaginate ( SearchGroup, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }
            else if ( valid_application )
            {
                _log.info ( "Searching by application only" );

                list = bef.get().findAllBetweenTimesByApplicationPaginate ( SearchApplication, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }

            session.setAttribute ( "EEBEventLog-Search-Group",       SearchGroup       );
            session.setAttribute ( "EEBEventLog-Search-Application", SearchApplication );
            session.setAttribute ( "EEBEventLog-Search-StartTime",   SearchStartTime   );
            session.setAttribute ( "EEBEventLog-Search-EndTime",     SearchEndTime     );
            session.setAttribute ( "EEBEventLog-Search-PageNum",     SearchPageNum     );

            return mapping.findForward ( "success" );
        }
        catch ( BackEndFailure exception )
        {
            _log.info ( "SearchAction::search() - exception" );

            return mapping.findForward ( "exception" );
        }
    }

    public ActionForward pageDown ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
    {
        _log.info ( "SearchResultsNavigateAction::pageDown()" );

        try
        {
            WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
            HttpSession session        = request.getSession();
            String   SearchGroup       = (String)   session.getAttribute ( "EEBEventLog-Search-Group"       );
            String   SearchApplication = (String)   session.getAttribute ( "EEBEventLog-Search-Application" );
            Calendar SearchStartTime   = (Calendar) session.getAttribute ( "EEBEventLog-Search-StartTime"   );
            Calendar SearchEndTime     = (Calendar) session.getAttribute ( "EEBEventLog-Search-EndTime"     );
            Integer  SearchPageNumOld  = (Integer)  session.getAttribute ( "EEBEventLog-Search-PageNum"     );
            Integer  SearchPageNum     = null;
            boolean valid_group        = (( null != SearchGroup ) && ( SearchGroup.trim().length() > 0 ));
            boolean valid_application  = (( null != SearchApplication ) && ( SearchApplication.trim().length() > 0 ));
            List list                  = null;

            if ( SearchPageNumOld.intValue() > 1 )
            {
                SearchPageNum = new Integer ( SearchPageNumOld.intValue() - 1 );
            }
            else
            {
                SearchPageNum = SearchPageNumOld;
            }

            if ( _log.isInfoEnabled() )
            {
                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "SearchResultsNavigateAtion::pageDown ( User=" );
                message.get().append ( request.getRemoteUser() );
                message.get().append ( ", SearchGroup=" );
                message.get().append ( SearchGroup );
                message.get().append ( ", SearchApplication=" );
                message.get().append ( SearchApplication );
                message.get().append ( ", SearchStartTime=" );
                message.get().append ( sdf.format ( SearchStartTime.getTime() ) );
                message.get().append ( ", SearchEndTime=" );
                message.get().append ( sdf.format ( SearchEndTime.getTime() ) );
                message.get().append ( ", PageNum=" );
                message.get().append ( SearchPageNum );
                message.get().append ( " ), " );

                _log.info ( message.get().toString() );
            }

            if ( valid_group && valid_application )
            {
                _log.info ( "Searching by group and application" );

                list = bef.get().findAllBetweenTimesByGroupApplicationPaginate ( SearchGroup, SearchApplication, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }
            else if ( valid_group )
            {
                _log.info ( "Searching by group only" );

                list = bef.get().findAllBetweenTimesByGroupPaginate ( SearchGroup, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }
            else if ( valid_application )
            {
                _log.info ( "Searching by application only" );

                list = bef.get().findAllBetweenTimesByApplicationPaginate ( SearchApplication, SearchStartTime, SearchEndTime, 25, SearchPageNum.intValue() );

                if (( null != list ) && ( list.size() > 0 ))
                {
                    session.setAttribute ( "EEBEventLog-Search-Results", list );
                }
            }

            session.setAttribute ( "EEBEventLog-Search-Group",       SearchGroup       );
            session.setAttribute ( "EEBEventLog-Search-Application", SearchApplication );
            session.setAttribute ( "EEBEventLog-Search-StartTime",   SearchStartTime   );
            session.setAttribute ( "EEBEventLog-Search-EndTime",     SearchEndTime     );
            session.setAttribute ( "EEBEventLog-Search-PageNum",     SearchPageNum     );

            return mapping.findForward ( "success" );
        }
        catch ( BackEndFailure exception )
        {
            _log.info ( "SearchAction::search() - exception" );

            return mapping.findForward ( "exception" );
        }

    }
}
