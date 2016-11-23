package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.ExtractFile;
import com.bgi.esm.portlets.Suppression.Toolkit;

/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class DeleteEntryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception 
    {
        _log.info ( "execute()" );

        String user_agent = request.getHeader ( "user-agent" );

        HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
        String suppress_id_string          = null;
        ActionForward forward              = null;

        // Attempt to delete the entry
        try
        {
            if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
            {
                _log.info ( "Perl agent detected" );

                suppress_id_string = request.getParameter ( "suppress_id" );

                forward            = mapping.findForward ( "success-perl" );
            }
            else
            {
                suppress_id_string = param_map.get ( "suppress_id" );

                forward            = mapping.findForward ( "success" );
            }

            int suppress_id = Integer.parseInt ( suppress_id_string );

            Toolkit.deleteEntry ( suppress_id );

            //  Retrieve the name of the extract file
            String filename = Toolkit.getExtractFileSuppressions ( request );

            //  Create and save the extarct file
            WeakReference <ExtractFile> ef  = new WeakReference <ExtractFile> ( new ExtractFile() );
            ef.get().toExtractFile ( filename );
            ef.clear();
            ef = null;  // releasing strong reference to object to encourage garbage collection

            return forward;
        }
        catch ( NumberFormatException exception )
        {
            _log.error ( "NumberFormatException on suppress_id: " + suppress_id_string );

            return ( mapping.findForward ( "failure" ) );
        }
    }
  
    private static final Logger _log = Logger.getLogger ( DeleteEntryAction.class );
}

