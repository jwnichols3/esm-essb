<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_cookie.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_cookie.cookie_comment.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForCookieComment() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForCookieComment().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="cookieComment" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="cookieComment">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForCookieComment();
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
                <td><bean:message bundle="databases" key="database.http_cookie.domain.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForDomain() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForDomain().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="domain" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="domain">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForDomain();
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
                <td><bean:message bundle="databases" key="database.http_cookie.path.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForPath() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForPath().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="path" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="path">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForPath();
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
                <td><bean:message bundle="databases" key="database.http_cookie.cookie_name.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForCookieName() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForCookieName().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="cookieName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="cookieName">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForCookieName();
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
                <td><bean:message bundle="databases" key="database.http_cookie.cookie_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForCookiePersistence() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForCookiePersistence().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="cookiePersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="cookiePersistence">
                    <%
                        String default_values[] = AbstractHttpCookie.getDefaultValuesForCookiePersistence();
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
                <td><bean:message bundle="databases" key="database.http_cookie.is_secure.display_name" /></td>
               <td>
                   <html:select name="http_cookie_form" property="isSecure">
                       <html:option value="true">Yes</html:option>
                       <html:option value="false">No</html:option>
                   </html:select>
               </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_cookie.max_age.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForMaxAge() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForMaxAge().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="maxAge" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="maxAge">
                    <%
                        long default_values[] = AbstractHttpCookie.getDefaultValuesForMaxAge();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = Long.toString ( default_values[counter] );
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
                <td><bean:message bundle="databases" key="database.http_cookie.version.display_name" /></td>
                <%
                if (( null == AbstractHttpCookie.getDefaultValuesForVersion() ) || ( 0 == AbstractHttpCookie.getDefaultValuesForVersion().length ))
                {
                 %>
                <td><html:text name="http_cookie_form" property="version" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_form" property="version">
                    <%
                        long default_values[] = AbstractHttpCookie.getDefaultValuesForVersion();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = Long.toString ( default_values[counter] );
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
