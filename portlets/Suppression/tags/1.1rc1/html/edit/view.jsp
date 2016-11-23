<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width1       = "25%";
    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
    String temp_string  = null;

 %>
<table border="0">
    <tr>
        <td>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/edit/nav.jsp" />
        </td>
    </tr>
</table>
