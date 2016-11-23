<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.Toolkit" %>
<%
    String suppress_id    = request.getParameter ( "suppressId" );

    try
    {
        Toolkit.deleteEntry ( Integer.parseInt ( suppress_id ) );
 %>Successfully delete suppression #<%= suppress_id %><%
    }
    catch ( Exception exception )
    {
 %>Could not delete suppression #<%= suppress_id %>
<br>
<br>
<b>Error: </b><%= exception.getMessage() %>
<%
        exception.printStackTrace();
    }
 %>

