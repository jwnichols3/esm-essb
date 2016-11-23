package com.bgi.esm.monitoring.portlets.EEBConfigurator.actions;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.portlets.EEBConfigurator.Toolkit;
import com.bgi.esm.monitoring.portlets.EEBConfigurator.forms.EEBPropertyForm;

public class EditPropertyAction extends Action
{
    final private Logger _log = Logger.getLogger ( EditPropertyAction.class );

    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
        throws Exception
    {
        HashMap <String, String> params = Toolkit.retrieveHttpRequestParameters ( request );

        String property_name = params.get ( "propertyName" );

        _log.info ( "Editing EEB property: " + property_name );

        BackEndFacade bef         = new BackEndFacade();
        //EebProperty property = bef.selectEebProperty ( params.get ( "propertyName" ) );
        HttpSession session       = request.getSession();
        EEBPropertyForm data_form = (EEBPropertyForm) form;
        EebProperty property      = (EebProperty) session.getAttribute ( Toolkit.SESSION_PROPERTY );
        property.setPropertyValue ( data_form.getPropertyValue() );
        bef.addOrUpdateEebProperty ( property );

        return mapping.findForward ( "success" );
    }
};
