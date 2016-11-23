<%@ taglib uri="http://portals.apache.org/bridges/struts/tags-portlet-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/portlet"       prefix="portlet" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld"      prefix="bean"    %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld"     prefix="logic"   %>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld"    prefix="nested"  %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld"     prefix="tiles"   %>

<%@ page import="java.io.*"  %>
<%@ page import="java.sql.*"  %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="javax.portlet.PortletRequest.*" %>
<%@ page import="javax.portlet.PortletSession.*" %>

<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.*" %>

<%@ page import="org.apache.log4j.*" %>
<%@ page import="org.hibernate.*" %>

<portlet:defineObjects />
<%
    //  To deploy in Tomcat/Struts, we must remove the <portlet:defineObjects /> call.
    //  <portlet:defineObjects />
    
    HashMap <String, String> param_map = Toolkit.retrieveHttpRequestParameters ( request );
 %>
