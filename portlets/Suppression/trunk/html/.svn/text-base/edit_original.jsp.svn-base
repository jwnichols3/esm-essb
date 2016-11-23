<%@ include file="/html/init.jsp"  %>
<%
    //String test_string = (String) portletSession.getAttribute ( "test1", PortletSession.APPLICATION_SCOPE );
    //portletSession.setAttribute ( "test2", "Dennis really is great!", PortletSession.APPLICATION_SCOPE );
 %>
<html:form action="/edit_process" method="post" onsubmit="submitForm(this); return false;">
    <table border="0" width="100%">
        <tr>
            <td>
                    <b>Edit Portlet Properties</b>
                    <br />
                    <br />
                    <b>Suppression Database Information</b>
                    <table border="1">
                        <tr>
                            <td>Database type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="suppressionDatabaseType">
                                    <html:option value="Sybase">Sybase ASE</html:option>
                                    <html:option value="SQL Server">Microsoft SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="PostgreSQL">PostgreSQL 7.3 or later</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Database Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="suppressionDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>Database Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="suppressionDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>Database Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="suppressionDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>Database Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="suppressionDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>Database Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="suppressionDatabasePassword" /></td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <b>VPO Database Information</b>
                    <table border="1">
                        <tr>
                            <td>Database type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="vpoDatabaseType">
                                    <html:option value="Sybase">Sybase</html:option>
                                    <html:option value="SQL Server">SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Database Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>Database Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>Database Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>Database Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>Database Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabasePassword" /></td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <b>Inst Database Information</b>
                    <table border="1">
                        <tr>
                            <td>Database type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="instDatabaseType">
                                    <html:option value="Sybase">Sybase</html:option>
                                    <html:option value="SQL Server">SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Database Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>Database Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>Database Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>Database Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>Database Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabasePassword" /></td>
                        </tr>
                    </table>
                
            </td>
        </tr>
        <tr>
            <td colspan="3">
                    <html:submit>Update Database Information</html:submit>
            </td>
        </tr>
    </table>
</html:form>
