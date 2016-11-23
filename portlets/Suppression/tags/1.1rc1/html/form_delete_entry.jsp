<%@ page import="com.bgi.esm.portlets.Suppression.Toolkit" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%
    String suppress_id    = request.getParameter ( "suppressId" );

    try
    {
        Toolkit.deleteEntry ( Integer.parseInt ( suppress_id ) );
    }
    catch ( Exception exception )
    {
        exception.printStackTrace();
    }
 %>
Successfully delete suppression #<%= suppress_id %>
