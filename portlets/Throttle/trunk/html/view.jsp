<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";

    PortletPreferences prefs = renderRequest.getPreferences();
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <bean:message bundle="databases" key="databases.test_throttle.throttle.display_name.throttle_overview" />
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
