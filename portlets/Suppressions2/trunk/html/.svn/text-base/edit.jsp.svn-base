<%@ include file="/html/init.jsp"  %>
<%
    //String test_string = (String) portletSession.getAttribute ( "test1", PortletSession.APPLICATION_SCOPE );
    //portletSession.setAttribute ( "test2", "Dennis really is great!", PortletSession.APPLICATION_SCOPE );
 %>
<html:form action="/edit_process" method="post" onsubmit="submitForm(this); return false;">
    <table border="0" width="100%">
        <tr>
            <td colspan="3">
                <font class="portlet-font" style="font-size: medium;">
                    <b>Edit Portlet Properties</b>
                </font>
            </td>
        </tr>
        <tr>
            <td valign="top">
                
                    <b>VPO Database Information</b>
                    <table border="1">
                        <tr>
                            <td>DB type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="vpoDatabaseType">
                                    <html:option value="Sybase">Sybase ASE</html:option>
                                    <html:option value="SQL Server">Microsoft SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="PostgreSQL">PostgreSQL 7.3 or later</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>DB Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>DB Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>DB Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>DB Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>DB Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoDatabasePassword" /></td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <b>Inst Database Information</b>
                    <table border="1">
                        <tr>
                            <td>DB type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="instDatabaseType">
                                    <html:option value="Sybase">Sybase ASE</html:option>
                                    <html:option value="SQL Server">Microsoft SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="PostgreSQL">PostgreSQL 7.3 or later</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>DB Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>DB Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>DB Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>DB Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>DB Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instDatabasePassword" /></td>
                        </tr>
                    </table>
                
            </td>
            <td valign="top">
                
                    <b>VPO Database Information</b>
                    <table border="1">
                        <tr>
                            <td>DB type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="vpoBackupDatabaseType">
                                    <html:option value="Sybase">Sybase ASE</html:option>
                                    <html:option value="SQL Server">Microsoft SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="PostgreSQL">PostgreSQL 7.3 or later</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>DB Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoBackupDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>DB Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoBackupDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>DB Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoBackupDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>DB Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoBackupDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>DB Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="vpoBackupDatabasePassword" /></td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <b>Inst Database Information</b>
                    <table border="1">
                        <tr>
                            <td>DB type:</td>
                            <td>
                                <html:select name="suppression_portlet_prefs" property="instBackupDatabaseType">
                                    <html:option value="Sybase">Sybase ASE</html:option>
                                    <html:option value="SQL Server">Microsoft SQL Server</html:option>
                                    <html:option value="Oracle">Oracle</html:option>
                                    <html:option value="PostgreSQL">PostgreSQL 7.3 or later</html:option>
                                    <html:option value="MySQL">MySQL</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>DB Server:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instBackupDatabaseServer" /></td>
                        </tr>
                        <tr>
                            <td>DB Name:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instBackupDatabaseName" /></td>
                        </tr>
                        <tr>
                            <td>DB Port:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instBackupDatabasePort" /></td>
                        </tr>
                        <tr>
                            <td>DB Username:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instBackupDatabaseUsername" /></td>
                        </tr>
                        <tr>
                            <td>DB Password:</td>
                            <td><html:text name="suppression_portlet_prefs" property="instBackupDatabasePassword" /></td>
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
