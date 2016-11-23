<%@ include file="/html/init.jsp" %>
<%
 %>
<table border="1" width="100%">
    <tr>
        <td><html:link action="/view"><bean:message bundle="databases" key="databases.test_datamap.home.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="/data_map_view"><bean:message bundle="databases" key="databases.test_datamap.data_map.display_name" /></html:link></td>
    </tr>
</table>
