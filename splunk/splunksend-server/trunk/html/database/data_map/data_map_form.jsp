<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.data_map.application_name.display_name" /></td>
                <%
                if (( null == AbstractDataMap.getDefaultValuesForApplicationName() ) || ( 0 == AbstractDataMap.getDefaultValuesForApplicationName().length ))
                {
                 %>
                <td><html:text name="data_map_form" property="applicationName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="data_map_form" property="applicationName">
                    <%
                        String default_values[] = AbstractDataMap.getDefaultValuesForApplicationName();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = default_values[counter];
                         %><html:option value='<%= default_value %>'><%= default_value %></html:option><%
                        }
                    %>
                    </html:select>
                </td>
                <%
                }
                 %>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.data_map.hostname.display_name" /></td>
                <%
                if (( null == AbstractDataMap.getDefaultValuesForHostname() ) || ( 0 == AbstractDataMap.getDefaultValuesForHostname().length ))
                {
                 %>
                <td><html:text name="data_map_form" property="hostname" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="data_map_form" property="hostname">
                    <%
                        String default_values[] = AbstractDataMap.getDefaultValuesForHostname();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = default_values[counter];
                         %><html:option value='<%= default_value %>'><%= default_value %></html:option><%
                        }
                    %>
                    </html:select>
                </td>
                <%
                }
                 %>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.data_map.target_path.display_name" /></td>
                <%
                if (( null == AbstractDataMap.getDefaultValuesForTargetPath() ) || ( 0 == AbstractDataMap.getDefaultValuesForTargetPath().length ))
                {
                 %>
                <td><html:text name="data_map_form" property="targetPath" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="data_map_form" property="targetPath">
                    <%
                        String default_values[] = AbstractDataMap.getDefaultValuesForTargetPath();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = default_values[counter];
                         %><html:option value='<%= default_value %>'><%= default_value %></html:option><%
                        }
                    %>
                    </html:select>
                </td>
                <%
                }
                 %>
            </tr>
            <tr>
                <td colspan="2"><center><html:submit>Submit Entry</html:submit></center></td>
            </tr>
        </table>
