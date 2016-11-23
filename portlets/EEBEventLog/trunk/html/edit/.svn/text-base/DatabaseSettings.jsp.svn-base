<%@ include file="/html/init.jsp" %>
<%
    Calendar calendar = Calendar.getInstance();
    int counter       = 0;
 %>
<table border="0" width="100%">
    <tr>
        <th colspan="2">Database Settings</th>
    </tr>
    <tr>
        <td>
            <html:form action="/edit/DatabaseSettingsProcess.do?method=save" method="post">
                <table border="0" width="100%">
                    <tr>
                        <td>Type: </td>
                        <td><html:text name="edit_database_settings" property="databaseType" /></td>
                    </tr>
                    <tr>
                        <td>Server: </td>
                        <td><html:text name="edit_database_settings" property="databaseServer" /></td>
                    </tr>
                    <tr>
                        <td>Name: </td>
                        <td><html:text name="edit_database_settings" property="databaseName" /></td>
                    </tr>
                    <tr>
                        <td>Port: </td>
                        <td><html:text name="edit_database_settings" property="databasePort" /></td>
                    </tr>
                    <tr>
                        <td>Username: </td>
                        <td><html:text name="edit_database_settings" property="databaseUsername" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><html:text name="edit_database_settings" property="databasePassword" /></td>
                    </tr>
                </table>
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/edit/nav.jsp" />
        </td>
    </tr>
</table>

