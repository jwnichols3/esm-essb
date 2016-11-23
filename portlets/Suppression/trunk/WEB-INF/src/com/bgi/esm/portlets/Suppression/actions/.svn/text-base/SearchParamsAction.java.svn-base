package com.bgi.esm.portlets.Suppression.actions;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.SearchParams;

/**
 * <a href="SearchParamsAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class SearchParamsAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
      	throws Exception 
	{
		_log.debug ( "com.bgi.esm.portlets.Suppression.actions.SearchParamsAction::execute()" );
		
		System.out.println ( "SearchParamsAction::execute" );

		if ( null == form )
		{
            _log.error ( "null form detected" );
		}
		else
		{
            _log.debug ( "Form is OK" );
		}


		SearchParams data_form = (SearchParams) form;

		HttpSession session = request.getSession();
		session.setAttribute ( "description", data_form.getDescription() );
		session.setAttribute ( "application", data_form.getApplication() );
		session.setAttribute ( "msgGroups",   data_form.getMessageGroups() );
		session.setAttribute ( "node",        data_form.getNode() );
		session.setAttribute ( "dbServer",    data_form.getDbServer() );
		session.setAttribute ( "dbServerMsg", data_form.getDbServerMsg() );
		session.setAttribute ( "username",    data_form.getUsername() );

		Calendar calendar         = null;
		String temp_string        = null;
		String start_choice       = data_form.getStartChoice();
		String end_choice         = data_form.getEndChoice();
		java.util.Date start_date = null;
		java.util.Date end_date   = null;
		if ( start_choice.equals ( "beginning" ) )
		{
			start_date = new java.util.Date( 0 );
		}
		else
		{
			calendar = Calendar.getInstance();
			calendar.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupStartYear()   ) );
			calendar.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupStartMonth()  ) );
			calendar.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupStartDate()   ) );
			calendar.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupStartHour()   ) );
			calendar.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupStartMinute() ) );
			temp_string = data_form.getSupStartAmpm();
			calendar.set ( Calendar.AM_PM,  temp_string.equals ( "AM" ) ? Calendar.AM : Calendar.PM );

			start_date = calendar.getTime();
		}
		session.setAttribute ( "startDate", start_date );

		if ( end_choice.equals ( "now" ) )
		{
		}
		else
		{
		}

		if ( end_choice.equals ( "now" ) )
		{
			end_date = new java.util.Date();
		}
		else
		{
			calendar = Calendar.getInstance();
			calendar.set ( Calendar.YEAR,   Integer.parseInt ( data_form.getSupEndYear()   ) );
			calendar.set ( Calendar.MONTH,  Integer.parseInt ( data_form.getSupEndMonth()  ) );
			calendar.set ( Calendar.DATE,   Integer.parseInt ( data_form.getSupEndDate()   ) );
			calendar.set ( Calendar.HOUR,   Integer.parseInt ( data_form.getSupEndHour()   ) );
			calendar.set ( Calendar.MINUTE, Integer.parseInt ( data_form.getSupEndMinute() ) );
			temp_string = data_form.getSupEndAmpm();
			calendar.set ( Calendar.AM_PM,  temp_string.equals ( "AM" ) ? Calendar.AM : Calendar.PM );

			end_date    = calendar.getTime();
		}
		session.setAttribute ( "endDate", end_date );
        
        temp_string = data_form.getRemoveOnReboot();
        if ( temp_string.equals ( "remove-only" ) )
        {
            session.setAttribute ( "reboot", new Integer ( Toolkit.REMOVE_ON_REBOOT_TRUE_ONLY ));
        }
        else if ( temp_string.equals ( "no-remove" ) )
        {
            session.setAttribute ( "reboot", new Integer ( Toolkit.REMOVE_ON_REBOOT_FALSE_ONLY ));
        }
        else
        {
            session.setAttribute ( "reboot", new Integer ( Toolkit.REMOVE_ON_REBOOT_DOES_NOT_MATTER ));
        }
        
        temp_string = data_form.getIncludeDeleted();
        if ( temp_string.equals ( "deleted-only") )
        {
            session.setAttribute ( "is_deleted", new Integer ( Toolkit.IS_DELETED_TRUE_ONLY ) );
        }
        else if ( temp_string.equals ( "no-deleted") )
        {
            session.setAttribute ( "is_deleted", new Integer ( Toolkit.IS_DELETED_FALSE_ONLY ) );
        }
        else
        {
            session.setAttribute ( "is_deleted", new Integer ( Toolkit.IS_DELETED_DOES_NOT_MATTER ) );
        }
        
   		return(mapping.findForward("success"));
	}

    private static final Logger _log = Logger.getLogger (AddEntryAction.class);
}
