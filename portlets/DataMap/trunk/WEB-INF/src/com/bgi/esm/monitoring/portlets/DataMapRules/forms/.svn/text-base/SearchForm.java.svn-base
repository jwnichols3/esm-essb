package com.bgi.esm.monitoring.portlets.DataMapRules.forms;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.portlets.DataMapRules.Toolkit;

public class SearchForm extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID         = 1173914320149L;
    private static final Logger _log                   = Logger.getLogger ( SearchForm.class );

    private String GroupName;

    public SearchForm()
    {
        super ();
    }

    /**
     *  Reset bean properties to their default state, as needed. This method is called 
     *  before the properties are repopulated by the controller
     *
     *  @param mapping The mapping used to select this instance
     *  @param request The servlet request we are processing
     */
    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "SearchForm::reset()" );

        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        String index = param_map.get ( "rule_id" );

        if ( null == index )
        {
            _log.info ( "reset() - Null index found..." );

            return;
        }

        _log.info ( "Initializing object with index=" + index );

    }

    /**
     *  Validate the properties that have been set for this HTTP request, and return an ActionErrors 
     *  object that encapsulates any validation errors that have been found.
     *
     *  @param mapping The mapping used to select this instance
     *  @param request The servlet request we are processing
     *  @return null if no errors are found,an ActionErrors object with recorded error messages otherwise
     */
    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "SearchForm::validate()" );
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void setGroupName ( String group_name )
    {
        GroupName = group_name;
    }

    public String getGroupName()
    {
        return GroupName;
    }
}
