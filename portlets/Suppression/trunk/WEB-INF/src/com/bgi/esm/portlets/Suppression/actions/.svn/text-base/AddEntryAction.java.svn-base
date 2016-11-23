package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.Date;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.ExtractFile;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;
/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class AddEntryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        _log.debug ( "com.bgi.esm.portlets.Suppression.actions.AddEntryAction::execute()" );
        
        _log.debug ( "Beginning execution" );
        
        AddEntry data_form = (AddEntry) form;
        
        _log.debug ( "Beginning validation" );
        
        ActionErrors errors = form.validate ( mapping, request );
       
        if ( _log.getLevel() == Level.DEBUG )
        {
            data_form.DumpToFile ( "c:\\test\\add_entry_form.out" );
        }
       
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
            //int time_offset = Toolkit.computeTimeZoneOffset ( request );
            int time_offset = Integer.parseInt ( data_form.getTimeZoneOffset() );

            _log.info ( "AddEntryAction::execute() - timezone offset=" + time_offset );
            //  Add the suppression
            int new_entry_num = Toolkit.addSuppression ( data_form, time_offset );
            HttpSession session = request.getSession();
            session.setAttribute ( "NewSuppressionID", new Integer ( new_entry_num ) );

            //  Retrieve the name of the extract file
            String filename = Toolkit.getExtractFileSuppressions ( request );

            if ( _log.isInfoEnabled() )
            {
                _log.info ( "Saving to extract file: " + filename );
            }

            //  Create and save the extarct file
            WeakReference <ExtractFile> ef  = new WeakReference <ExtractFile> ( new ExtractFile() );
            ef.get().toExtractFile ( filename );
            ef.clear();
            ef = null;
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
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        String user_agent  = request.getHeader ( "user-agent" );

        if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
        {
            _log.info ( "Perl agent detected" );

            return ( mapping.findForward ( "success-perl" ) );
        }
        else
        {
            return ( mapping.findForward ( "success" ) );
        }
    }
  
    private static final Logger _log = Logger.getLogger(AddEntryAction.class);
}


