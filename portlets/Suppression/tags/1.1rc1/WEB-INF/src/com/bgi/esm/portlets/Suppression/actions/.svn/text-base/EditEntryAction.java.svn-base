package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bgi.esm.portlets.Suppression.ExtractFile;
import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.EditEntry;

/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class EditEntryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception 
    {
        _log.debug ( "com.bgi.esm.portlets.Suppression.actions.EditEntryAction::execute()" );
        
        EditEntry data_form = (EditEntry) form;

        if ( _log.getLevel() == Level.DEBUG )
        {
            data_form.DumpToFile ( "c:\\test\\edit_entry.out" );
        }

        data_form.validate ( mapping, request );
        
        Toolkit.updateEntry ( request, data_form );

        //  Retrieve the name of the extract file
        String filename = Toolkit.getExtractFileSuppressions ( request );

        //  Create and save the extarct file
        WeakReference <ExtractFile> ef  = new WeakReference <ExtractFile> ( new ExtractFile() );
        ef.get().toExtractFile ( filename );
        ef.clear();
        ef = null;
       

        _log.info ( "finish" );

        String user_agent  = request.getHeader ( "user-agent" );

        if ( user_agent.indexOf ( "libwww-perl" ) >= 0 )
        {
            System.out.println ( "Perl agent detected" );
            return ( mapping.findForward ( "success-perl" ) );
        }
        else
        {
            return ( mapping.findForward ( "success" ) );
        }
    }
  
    private static final Logger _log = Logger.getLogger(EditEntryAction.class);
}

