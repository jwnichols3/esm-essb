package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Date;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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
import com.bgi.esm.portlets.Suppression.forms.AddEntry;

/**
 * <a href="BatchAddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class BatchAddEntryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        _log.debug ( "com.bgi.esm.portlets.Suppression.actions.BatchAddEntryAction::execute()" );
        
        _log.debug ( "Beginning execution" );
        
        AddEntry data_form = (AddEntry) form;
        
        _log.debug ( "Beginning validation" );
        
        ActionErrors errors = form.validate ( mapping, request );
        
        _log.debug ( "Number of errors found: " + errors.size() );
        
        if ( errors.size() > 0 )
        {
            HttpSession session = request.getSession();
            session.setAttribute ( "org.apache.struts.action.ERROR", errors );

            _log.error ( "Failed execution because errors were detected: " + errors.size() );

            return ( mapping.findForward ( "failure" ) );
        }
        
        try
        {
            //  Compute the offset
            int time_offset = Toolkit.computeTimeZoneOffset ( request );

            _log.info ( "BatchAddEntryAction::execute() - timezone offset=" + time_offset );
            //  Add the suppression

            Toolkit.addSuppression ( data_form, time_offset );
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return ( mapping.findForward ( "success" ) );
    }
  
    private static final Logger _log = Logger.getLogger (BatchAddEntryAction.class);
}
