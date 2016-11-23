package com.bgi.esm.portlets.Suppression.actions;

import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.portlets.Suppression.Toolkit;
import com.bgi.esm.portlets.Suppression.forms.AddEntry;

/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class AddEntryTest extends Action
{
	private void LogToFile ( String message )
	{
		try
		{
			FileOutputStream outfile = new FileOutputStream ( "c:\\test\\addentry.out", true );
				outfile.write ( (new Date()).toString().getBytes() );
				outfile.write ( " - ".getBytes() );
				outfile.write ( message.getBytes() );
				outfile.write ( "\n".getBytes() );
			outfile.close();
		}
		catch ( IOException exception )
		{
		}
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
      	throws Exception 
	{
		_log.debug ( "com.bgi.esm.portlets.Suppression.actions.AddEntryTest::execute()" );
		
		AddEntry data_form = (AddEntry) form;
        
        LogToFile ( "Beginning validation" );
        
        ActionErrors errors = form.validate ( mapping, request );
        
        LogToFile ( "Number of errors found: " + errors.size() );
        
        if ( errors.size() > 0 )
        {
            LogToFile ( "Failured execution" );
            return ( mapping.findForward ( "failure" ) );
        }
        
		//  Compute the offset
        int time_offset = Toolkit.computeTimeZoneOffset ( request );

        System.out.println ( "AddEntryTest::execute() - timezone offset=" + time_offset );
        //  Add the suppression
        Toolkit.addSuppression ( data_form, time_offset );

   		return ( mapping.findForward ( "success" ) );
	}
  
    private static final Logger _log = Logger.getLogger (AddEntryAction.class);
}
