<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width1       = "25%";
    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";

 %>
<html:form action="/edit/edit_db_prefs_process" method="post" onsubmit="submitForm(this); return false;">
    <table border="0" width="100%">
        <tr>
            <td>
                <center>
                    <font class="portlet-font" style="font-size: x-small;">
                        <b>Database Settings</b>
                        <br />
                        <table border="1">
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Type:</font></td>
                                <td>
                                    <html:select name="edit_db_preferences_form" property="databaseType">
                                        <html:option value="Generic ODBC">Generic ODBC</html:option>
                                        <html:option value="MySQL">MySQL</html:option>
                                        <html:option value="Oracle">Oracle</html:option>
                                        <html:option value="PostgreSQL">PostgreSQL</html:option>
                                        <html:option value="SQL Server">SQL Server</html:option>
                                        <html:option value="Sybase">Sybase</html:option>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Server:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databaseServer" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Name:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databaseName" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Port:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databasePort" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Username:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databaseUsername" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Password:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databasePassword" /></td>
                            </tr>
                        </table>
                        <br />
                        <html:submit>Update Database Properties</html:submit>
                    </font>
                </center>
            </td>
            <td>
                <center>
                    <font class="portlet-font" style="font-size: x-small;">
                        <b>Database Settings</b>
                        <br />
                        <table border="1">
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Type:</font></td>
                                <td>
                                    <html:select name="edit_db_preferences_form" property="backupDatabaseType">
                                        <html:option value="Generic ODBC">Generic ODBC</html:option>
                                        <html:option value="MySQL">MySQL</html:option>
                                        <html:option value="Oracle">Oracle</html:option>
                                        <html:option value="PostgreSQL">PostgreSQL</html:option>
                                        <html:option value="SQL Server">SQL Server</html:option>
                                        <html:option value="Sybase">Sybase</html:option>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Server:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="backupDatabaseServer" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Name:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="backupDatabaseName" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Port:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="backupDatabasePort" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Username:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="backupDatabaseUsername" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Password:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="backupDatabasePassword" /></td>
                            </tr>
                        </table>
                        <br />
                        <html:submit>Update Database Properties</html:submit>
                    </font>
                </center>
            </td>
            <td>
                <center>
                    <font class="portlet-font" style="font-size: x-small;">
                        <table border="1">
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Name:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databaseName" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Port:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databasePort" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Username:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databaseUsername" /></td>
                            </tr>
                            <tr>
                                <td><font class="portlet-font" style="font-size: x-small;">Database Password:</font></td>
                                <td><html:text name="edit_db_preferences_form" property="databasePassword" /></td>
                            </tr>
                        </table>
                        <br>
                        <html:submit>Update Database Properties</html:submit>
                    </font>
                </center>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <jsp:include page="/html/edit/nav.jsp"/>
            </td>
        </tr>
    </table>
</html:form>
