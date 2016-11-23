<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_header.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpHeader.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpHeader.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_header_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_header_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpHeader.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_header.header_name.display_name" /></td>
                <%
                if (( null == AbstractHttpHeader.getDefaultValuesForHeaderName() ) || ( 0 == AbstractHttpHeader.getDefaultValuesForHeaderName().length ))
                {
                 %>
                <td><html:text name="http_header_form" property="headerName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_header_form" property="headerName">
                    <%
                        String default_values[] = AbstractHttpHeader.getDefaultValuesForHeaderName();
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
                <td><bean:message bundle="databases" key="database.http_header.header_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpHeader.getDefaultValuesForHeaderPersistence() ) || ( 0 == AbstractHttpHeader.getDefaultValuesForHeaderPersistence().length ))
                {
                 %>
                <td><html:text name="http_header_form" property="headerPersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_header_form" property="headerPersistence">
                    <%
                        String default_values[] = AbstractHttpHeader.getDefaultValuesForHeaderPersistence();
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
