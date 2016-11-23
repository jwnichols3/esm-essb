<%@ taglib uri="http://portals.apache.org/bridges/struts/tags-portlet-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"      prefix="bean"    %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"     prefix="logic"   %>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld"    prefix="nested"  %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"     prefix="tiles"   %>
<%@ taglib uri="http://java.sun.com/portlet"       prefix="portlet" %>

<%@ page import="java.io.*"   %>
<%@ page import="java.sql.*"  %>
<%@ page import="java.util.*" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="javax.portlet.PortletRequest.*" %>
<%@ page import="javax.portlet.PortletSession.*" %>

<portlet:defineObjects />
<%
	HashMap <String, String> param_map = null;

	if ( request.getParameter ( "_spage" ) != null ) // Liferay parameter handling
	{
		String parameter = request.getParameter ( "_spage" );
		parameter = parameter.substring ( parameter.indexOf ( "?" )+1 );

		// Parse out the tokens
		StringTokenizer tokenizer = new StringTokenizer ( parameter, "&" );
		String key                = null;
		String value              = null;
		int index                 = 0;
		param_map                 = new HashMap <String, String> ();

		while ( tokenizer.hasMoreTokens() )
		{
			parameter = tokenizer.nextToken();
			index     = parameter.indexOf ( "=" );
			if ( index >= 0 )
			{
				key       = parameter.substring ( 0, index );
				value     = parameter.substring ( index+1 );

				param_map.put ( key, value );
			}
		}
	}
	else // Struts parameter handling
	{
		Enumeration e = request.getParameterNames();
		String es     = null;
		param_map     = new HashMap <String, String>();
		while ( e.hasMoreElements() )
		{
			es = e.nextElement().toString();
			param_map.put ( es, request.getParameter ( es ) );
		}
	}

    PortletPreferences prefs = renderRequest.getPreferences();
 %>
