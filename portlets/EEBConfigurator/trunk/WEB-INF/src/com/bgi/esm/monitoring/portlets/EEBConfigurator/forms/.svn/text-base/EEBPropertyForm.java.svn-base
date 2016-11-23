package com.bgi.esm.monitoring.portlets.EEBConfigurator.forms;

import java.util.HashMap;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.portlets.EEBConfigurator.Toolkit;

public class EEBPropertyForm extends ActionForm
{
    final private static Logger _log = Logger.getLogger ( EEBPropertyForm.class );
    private String PropertyValue     = null;

    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        HashMap <String, String> params = Toolkit.retrieveHttpRequestParameters ( request );
        String property_name = params.get ( "propertyName" );
        _log.info ( "Editing EEB property: " + property_name );

        if (( null == property_name ) || ( property_name.trim().length() < 1 ))
        {
            PropertyValue = "";

            return;
        }

        RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );

        PortletPreferences prefs = renderRequest.getPreferences();
        prefs.getMap();

        BackEndFacade bef    = new BackEndFacade();
        EebProperty property = null;
        
        try
        {
            property = bef.selectEebProperty ( property_name );
        }
        catch ( BackEndFailure exception )
        {
            _log.error ( exception );

            property = null;
        }

        if ( null != property )
        {
            PropertyValue = property.getPropertyValue();
        }
        else
        {
            PropertyValue = "";
        }
    }

    public void setPropertyValue ( String property_value )
    {
        PropertyValue = property_value;
    }

    public String getPropertyValue()
    {
        return PropertyValue;
    }
};
