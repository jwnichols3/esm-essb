package com.bgi.esm.portlets.Suppression.actions;

import java.io.FileOutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchHistorical extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm inForm, HttpServletRequest request, HttpServletResponse response) 
			throws Exception 
	{
        _log.info ( (new Date()).toString() );

		return ( mapping.findForward ( "success" ) );
	}

	@SuppressWarnings("unused")
	private void retrieveDataServers ( String server_type, String group )
	{
	}

    final private static Logger _log = Logger.getLogger ( SearchHistorical.class );
};
