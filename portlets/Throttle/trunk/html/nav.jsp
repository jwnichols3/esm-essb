<%@ include file="/html/init.jsp" %>
<%
 %>
<table border="1" width="100%">
    <tr>
        <td><html:link action="/view"><bean:message bundle="databases" key="databases.test_throttle.home.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="/throttle_view"><bean:message bundle="databases" key="databases.test_throttle.throttle.display_name" /></html:link></td>
    </tr>
</table>
