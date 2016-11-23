package com.bgi.esm.monitoring.portlets.EEBEventLog.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchForm extends ActionForm
{
    final private static Logger _log       = Logger.getLogger ( SearchForm.class );

    protected String SearchGroup           = null;
    protected String SearchTimeStartYear   = null;
    protected String SearchTimeStartMonth  = null;
    protected String SearchTimeStartDate   = null;
    protected String SearchTimeStartMinute = null;
    protected String SearchTimeStartHour   = null;
    protected String SearchTimeStartAmPm   = null;
    protected String SearchTimeEndYear     = null;
    protected String SearchTimeEndMonth    = null;
    protected String SearchTimeEndDate     = null;
    protected String SearchTimeEndMinute   = null;
    protected String SearchTimeEndHour     = null;
    protected String SearchTimeEndAmPm     = null;
    protected String SearchActions         = null;
    protected String SearchSeverity        = null;
    protected String SearchApplication     = null;
    protected String SearchMessageText     = null;
    protected String SearchMessageID       = null;
    protected String SearchNode            = null;

    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            prefs.getMap();
        }

        //  Default to 2 days before
        Calendar calendar   = Calendar.getInstance();
        Calendar start_date = Calendar.getInstance();
        start_date.setTimeInMillis ( calendar.getTimeInMillis() );
        start_date.add ( Calendar.DATE, -2 );

        setSearchTimeStartYear   ( Integer.toString ( start_date.get ( Calendar.YEAR   ) ) );
        setSearchTimeStartMonth  ( Integer.toString ( start_date.get ( Calendar.MONTH  ) ) );
        setSearchTimeStartDate   ( Integer.toString ( start_date.get ( Calendar.DATE   ) ) );
        setSearchTimeStartHour   ( Integer.toString ( start_date.get ( Calendar.HOUR   ) ) );
        setSearchTimeStartMinute ( Integer.toString ( start_date.get ( Calendar.MINUTE ) ) );
        setSearchTimeEndYear     ( Integer.toString ( calendar.get   ( Calendar.YEAR   ) ) );
        setSearchTimeEndMonth    ( Integer.toString ( calendar.get   ( Calendar.MONTH  ) ) );
        setSearchTimeEndDate     ( Integer.toString ( calendar.get   ( Calendar.DATE   ) ) );
        setSearchTimeEndHour     ( Integer.toString ( calendar.get   ( Calendar.HOUR   ) ) );
        setSearchTimeEndMinute   ( Integer.toString ( calendar.get   ( Calendar.MINUTE ) ) );
    }

    public void setSearchGroup ( String search_group )
    {
        SearchGroup = search_group;
    }

    public String getSearchGroup()
    {
        return SearchGroup;
    }

    public void setSearchTimeStartYear ( String search_time_start_year )
    {
        SearchTimeStartYear = search_time_start_year;
    }

    public String getSearchTimeStartYear()
    {
        return SearchTimeStartYear;
    }

    public void setSearchTimeStartMonth ( String search_time_start_month )
    {
        SearchTimeStartMonth = search_time_start_month;
    }

    public String getSearchTimeStartMonth()
    {
        return SearchTimeStartMonth;
    }

    public void setSearchTimeStartDate ( String search_time_start_date )
    {
        SearchTimeStartDate = search_time_start_date;
    }

    public String getSearchTimeStartDate()
    {
        return SearchTimeStartDate;
    }

    public void setSearchTimeStartHour ( String search_time_start_hour )
    {
        SearchTimeStartHour = search_time_start_hour;
    }

    public String getSearchTimeStartHour()
    {
        return SearchTimeStartHour;
    }

    public void setSearchTimeStartMinute ( String search_time_start_minute )
    {
        SearchTimeStartMinute = search_time_start_minute;
    }

    public String getSearchTimeStartMinute()
    {
        return SearchTimeStartMinute;
    }

    public void setSearchTimeStartAmPm ( String search_time_start_am_pm )
    {
        SearchTimeStartAmPm = search_time_start_am_pm;
    }

    public String getSearchTimeStartAmPm()
    {
        return SearchTimeStartAmPm;
    }

    public void setSearchTimeEndYear ( String search_time_end_year )
    {
        SearchTimeEndYear = search_time_end_year;
    }

    public String getSearchTimeEndYear()
    {
        return SearchTimeEndYear;
    }

    public void setSearchTimeEndMonth ( String search_time_end_month )
    {
        SearchTimeEndMonth = search_time_end_month;
    }

    public String getSearchTimeEndMonth()
    {
        return SearchTimeEndMonth;
    }

    public void setSearchTimeEndDate ( String search_time_end_date )
    {
        SearchTimeEndDate = search_time_end_date;
    }

    public String getSearchTimeEndDate()
    {
        return SearchTimeEndDate;
    }

    public void setSearchTimeEndHour ( String search_time_end_hour )
    {
        SearchTimeEndHour = search_time_end_hour;
    }

    public String getSearchTimeEndHour()
    {
        return SearchTimeEndHour;
    }

    public void setSearchTimeEndMinute ( String search_time_end_minute )
    {
        SearchTimeEndMinute = search_time_end_minute;
    }

    public String getSearchTimeEndMinute()
    {
        return SearchTimeEndMinute;
    }

    public void setSearchTimeEndAmPm ( String search_time_end_am_pm )
    {
        SearchTimeEndAmPm = search_time_end_am_pm;
    }

    public String getSearchTimeEndAmPm()
    {
        return SearchTimeEndAmPm;
    }

    public void searchActions ( String search_actions )
    {
        SearchActions = search_actions;
    }

    public String getSearchActions()
    {
        return SearchActions;
    }

    public void setSearchSeverity ( String search_severity )
    {
        SearchSeverity = search_severity;
    }

    public String getSearchSeverity()
    {
        return SearchSeverity;
    }

    public void setSearchApplication ( String search_application )
    {
        SearchApplication = search_application;
    }

    public String getSearchApplication()
    {
        return SearchApplication;
    }

    public void setSearchMessageText ( String search_message_text )
    {
        SearchMessageText = search_message_text;
    }

    public String getSearchMessageText()
    {
        return SearchMessageText;
    }

    public void setSearchMessageID ( String search_message_id )
    {
        SearchMessageID = search_message_id;
    }

    public String getSearchMessageID()
    {
        return SearchMessageID;
    }

    public void setSearchNode ( String search_node )
    {
        SearchNode = search_node;
    }

    public String getSearchNode()
    {
        return SearchNode;
    }

    public Calendar getSearchStartTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set ( Calendar.YEAR,   Integer.parseInt ( SearchTimeStartYear   ) );
        calendar.set ( Calendar.MONTH,  Integer.parseInt ( SearchTimeStartMonth  ) );
        calendar.set ( Calendar.DATE,   Integer.parseInt ( SearchTimeStartDate   ) );
        calendar.set ( Calendar.MINUTE, Integer.parseInt ( SearchTimeStartMinute ) );
        calendar.set ( Calendar.HOUR,   Integer.parseInt ( SearchTimeStartHour   ) );
        if ( SearchTimeStartAmPm.equals ( "AM" ) )
        {
            calendar.set ( Calendar.AM_PM, Calendar.AM );
            return calendar;
        }
        else if ( SearchTimeStartAmPm.equals ( "PM" ) )
        {
            calendar.set ( Calendar.AM_PM, Calendar.PM );
            return calendar;
        }
        else
        {
            throw new IllegalArgumentException ( "Invalid value for AM/PM setting in start date: " + SearchTimeStartAmPm );
        }
    }

    public Calendar getSearchEndTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set ( Calendar.YEAR,   Integer.parseInt ( SearchTimeEndYear   ) );
        calendar.set ( Calendar.MONTH,  Integer.parseInt ( SearchTimeEndMonth  ) );
        calendar.set ( Calendar.DATE,   Integer.parseInt ( SearchTimeEndDate   ) );
        calendar.set ( Calendar.MINUTE, Integer.parseInt ( SearchTimeEndMinute ) );
        calendar.set ( Calendar.HOUR,   Integer.parseInt ( SearchTimeEndHour   ) );

        if ( SearchTimeEndAmPm.equals ( "AM" ) )
        {
            calendar.set ( Calendar.AM_PM, Calendar.AM );
            return calendar;
        }
        else if ( SearchTimeEndAmPm.equals ( "PM" ) )
        {
            calendar.set ( Calendar.AM_PM, Calendar.PM );
            return calendar;
        }
        else
        {
            throw new IllegalArgumentException ( "Invalid value for AM/PM setting in end date: " + SearchTimeEndAmPm );
        }

    }
};
