<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_parameter.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpParameter.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpParameter.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_parameter_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_parameter_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpParameter.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_parameter.parameter_name.display_name" /></td>
                <%
                if (( null == AbstractHttpParameter.getDefaultValuesForParameterName() ) || ( 0 == AbstractHttpParameter.getDefaultValuesForParameterName().length ))
                {
                 %>
                <td><html:text name="http_parameter_form" property="parameterName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_parameter_form" property="parameterName">
                    <%
                        String default_values[] = AbstractHttpParameter.getDefaultValuesForParameterName();
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
                <td><bean:message bundle="databases" key="database.http_parameter.parameter_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpParameter.getDefaultValuesForParameterPersistence() ) || ( 0 == AbstractHttpParameter.getDefaultValuesForParameterPersistence().length ))
                {
                 %>
                <td><html:text name="http_parameter_form" property="parameterPersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_parameter_form" property="parameterPersistence">
                    <%
                        String default_values[] = AbstractHttpParameter.getDefaultValuesForParameterPersistence();
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
