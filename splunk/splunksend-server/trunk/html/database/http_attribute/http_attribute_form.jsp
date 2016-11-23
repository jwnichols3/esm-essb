<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_attribute.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpAttribute.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpAttribute.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_attribute_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpAttribute.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_attribute.attribute_name.display_name" /></td>
                <%
                if (( null == AbstractHttpAttribute.getDefaultValuesForAttributeName() ) || ( 0 == AbstractHttpAttribute.getDefaultValuesForAttributeName().length ))
                {
                 %>
                <td><html:text name="http_attribute_form" property="attributeName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_form" property="attributeName">
                    <%
                        String default_values[] = AbstractHttpAttribute.getDefaultValuesForAttributeName();
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
                <td><bean:message bundle="databases" key="database.http_attribute.attribute_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpAttribute.getDefaultValuesForAttributePersistence() ) || ( 0 == AbstractHttpAttribute.getDefaultValuesForAttributePersistence().length ))
                {
                 %>
                <td><html:text name="http_attribute_form" property="attributePersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_form" property="attributePersistence">
                    <%
                        String default_values[] = AbstractHttpAttribute.getDefaultValuesForAttributePersistence();
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
