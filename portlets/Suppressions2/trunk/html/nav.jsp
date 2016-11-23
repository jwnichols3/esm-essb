<%@ include file="/html/init.jsp" %>
<%
 %>
<table border="1" width="100%">
    <tr>
        <td colspan>
            <html:link action="/view"><bean:message bundle="databases" key="databases.test_suppressions.home.display_name" /></html:link>
            <br>
            <br>
            <html:link action="/add_entry">Add New Suppression</html:link>
        </td>
        <td>
            <html:link action="/view_current?method=current">Current Suppressions</html:link>
            <br>
            <br>
            <html:link action="/view_current?method=myCurrent">My Current Suppressions</html:link>
        </td>
</table>
