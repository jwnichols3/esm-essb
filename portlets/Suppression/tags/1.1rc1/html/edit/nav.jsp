<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width1       = "25%";
    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
 %>
<table border="0">
    <tr>
        <td>
            <html:link action="/edit/edit_database_settings">Edit Database Settings</html:link>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/edit/edit_settings">Edit Settings</html:link>
        </td>
    </tr>
</table>
