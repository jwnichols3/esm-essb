package com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.actions;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.Constants;
import com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.Toolkit;

public class MessageQueueQueryAction extends DispatchAction
{
     /**
      *  Called when the page assigned to this action is served up.
      *
      *  @param mapping all the resulting mappings that are available from this point
      *  @param form the Struts HTML form with all the user-entered data
      *  @param request the HTTP request object
      *  @param response the HTTP response object
      *
      *  @return an ActionForward object that determines the next execution step to take
      */
    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
            throws Exception
    {
        //log.debug ( "execute() - trying to retrieve renderRequest" );
        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        HashMap <Date, Integer> num_events = null;
        Vector <Date> events_dates         = new Vector <Date> ();
        String queue_name                  = param_map.get ( "queue" );
        HttpSession session                = request.getSession();

        if ( _log.isInfoEnabled() )
        {
            WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
            message.get().append ( "MessageQueueQueryAction - queue name: " );
            message.get().append ( queue_name );

            _log.info ( message.get().toString() );
        }

        //  Retrieve the number of events in the past 24 hours
        num_events = Toolkit.getNumEventsInPast24Hours ( queue_name );
        session.setAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS, num_events );

        //  Retrieve the min/max number of events in the past hours
        Iterator <Date> keys = num_events.keySet().iterator();
        int max_events = Integer.MIN_VALUE;
        int min_events = Integer.MAX_VALUE;
        while ( keys.hasNext() )
        {
            Date key       = keys.next();
            Integer events = num_events.get ( key );

            if ( events.intValue() > max_events )
            {
                max_events = events.intValue();
            }
            if ( events.intValue() < min_events )
            {
                min_events = events.intValue();
            }

            events_dates.add ( key );
        }

        Collections.sort ( events_dates );
        session.setAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_DATES, events_dates             );
        session.setAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_MIN, new Integer ( min_events ) );
        session.setAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_MAX, new Integer ( max_events ) );

        //  Compute the image map
        StringBuilder image_map = new StringBuilder();
        int graph_height        = Constants.QueueActivityGraph_GraphHeight;
        int hour_width          = Constants.QueueActivityGraph_HourWidth;
        int num_hours           = Constants.QueueActivityGraph_NumHours;
        int image_left_border   = Constants.QueueActivityGraph_ImageLeftBorder;
        int image_bottom_border = Constants.QueueActivityGraph_ImageBottomBorder;
        for ( int counter = 0; counter < num_hours+1; counter++ )
        {
            Date key       = events_dates.elementAt ( counter );
            Integer events = num_events.get ( key );
            int start_x = image_left_border + counter * hour_width;
            image_map.append ( "        <area shape=\"rect\" href=\"#\" onmouseover=\"showStatus ( '" );
            image_map.append ( key.toString() );
            image_map.append ( "', " );
            image_map.append ( events.toString() );
            image_map.append ( ", " );
            image_map.append ( counter );
            image_map.append ( " );\" coords=\"" );
            image_map.append ( start_x );
            image_map.append ( ", 0, " );
            image_map.append ( start_x+hour_width );
            image_map.append ( ", " );
            image_map.append ( graph_height );
            image_map.append ( "\">\n" );
        }
        session.setAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_MAP, image_map.toString() );

        return mapping.findForward ( "success" );
    }

    private static Logger _log = Logger.getLogger ( MessageQueueQueryAction.class );
}
