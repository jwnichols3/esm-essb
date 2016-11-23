package com.bgi.esm.portlets.Suppression.actions;

import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GetDataServers extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm inForm, HttpServletRequest request, HttpServletResponse response) 
			throws Exception 
	{
		FileOutputStream outfile = new FileOutputStream ( "c:\\test\\ajax_get_servers" );
			outfile.write ( "Successful!\n".getBytes() );
		outfile.close();
		return null;
	}

	@SuppressWarnings("unused")
	private void retrieveDataServers ( String server_type, String group )
	{
	}
};
