<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_request_audit.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.auth_type.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuthType() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuthType().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="authType" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="authType">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuthType();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.context_path.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForContextPath() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForContextPath().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="contextPath" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="contextPath">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForContextPath();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.method.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForMethod() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForMethod().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="method" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="method">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForMethod();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.path_info.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForPathInfo() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForPathInfo().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="pathInfo" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="pathInfo">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForPathInfo();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.path_info_translated.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForPathInfoTranslated() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForPathInfoTranslated().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="pathInfoTranslated" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="pathInfoTranslated">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForPathInfoTranslated();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.remote_user.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRemoteUser() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRemoteUser().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="remoteUser" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="remoteUser">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRemoteUser();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.requested_session_id.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRequestedSessionID() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRequestedSessionID().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="requestedSessionID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="requestedSessionID">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRequestedSessionID();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.request_uri.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRequestUri() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRequestUri().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="requestUri" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="requestUri">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRequestUri();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.request_url.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRequestUrl() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRequestUrl().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="requestUrl" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="requestUrl">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRequestUrl();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.servlet_path.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForServletPath() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForServletPath().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="servletPath" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="servletPath">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForServletPath();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.user_principal.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForUserPrincipal() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForUserPrincipal().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="userPrincipal" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="userPrincipal">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForUserPrincipal();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.character_encoding.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForCharacterEncoding() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForCharacterEncoding().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="characterEncoding" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="characterEncoding">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForCharacterEncoding();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.content_type.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForContentType() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForContentType().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="contentType" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="contentType">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForContentType();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.local_address.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForLocalAddress() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForLocalAddress().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="localAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="localAddress">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForLocalAddress();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.protocol.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForProtocol() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForProtocol().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="protocol" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="protocol">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForProtocol();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.remote_address.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRemoteAddress() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRemoteAddress().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="remoteAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="remoteAddress">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRemoteAddress();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.remote_host.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRemoteHost() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRemoteHost().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="remoteHost" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="remoteHost">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRemoteHost();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.scheme.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForScheme() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForScheme().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="scheme" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="scheme">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForScheme();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.server_name.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForServerName() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForServerName().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="serverName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="serverName">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForServerName();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.request_time.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_request_audit_form" property="requestTimeHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="requestTimeHelperMonth" >
                                    <html:option value="0"><bean:message bundle="databases" key="databases.common.month.01.display_name" /></html:option>
                                    <html:option value="1"><bean:message bundle="databases" key="databases.common.month.02.display_name" /></html:option>
                                    <html:option value="2"><bean:message bundle="databases" key="databases.common.month.03.display_name" /></html:option>
                                    <html:option value="3"><bean:message bundle="databases" key="databases.common.month.04.display_name" /></html:option>
                                    <html:option value="4"><bean:message bundle="databases" key="databases.common.month.05.display_name" /></html:option>
                                    <html:option value="5"><bean:message bundle="databases" key="databases.common.month.06.display_name" /></html:option>
                                    <html:option value="6"><bean:message bundle="databases" key="databases.common.month.07.display_name" /></html:option>
                                    <html:option value="7"><bean:message bundle="databases" key="databases.common.month.08.display_name" /></html:option>
                                    <html:option value="8"><bean:message bundle="databases" key="databases.common.month.09.display_name" /></html:option>
                                    <html:option value="9"><bean:message bundle="databases" key="databases.common.month.10.display_name" /></html:option>
                                    <html:option value="10"><bean:message bundle="databases" key="databases.common.month.11.display_name" /></html:option>
                                    <html:option value="11"><bean:message bundle="databases" key="databases.common.month.12.display_name" /></html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="requestTimeHelperDate" >
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="requestTimeHelperHour">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="requestTimeHelperMinute">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                    <html:option value="32">32</html:option>
                                    <html:option value="33">33</html:option>
                                    <html:option value="34">34</html:option>
                                    <html:option value="35">35</html:option>
                                    <html:option value="36">36</html:option>
                                    <html:option value="37">37</html:option>
                                    <html:option value="38">38</html:option>
                                    <html:option value="39">39</html:option>
                                    <html:option value="40">40</html:option>
                                    <html:option value="41">41</html:option>
                                    <html:option value="42">42</html:option>
                                    <html:option value="43">43</html:option>
                                    <html:option value="44">44</html:option>
                                    <html:option value="45">45</html:option>
                                    <html:option value="46">46</html:option>
                                    <html:option value="47">47</html:option>
                                    <html:option value="48">48</html:option>
                                    <html:option value="49">49</html:option>
                                    <html:option value="50">50</html:option>
                                    <html:option value="51">51</html:option>
                                    <html:option value="52">52</html:option>
                                    <html:option value="53">53</html:option>
                                    <html:option value="54">54</html:option>
                                    <html:option value="55">55</html:option>
                                    <html:option value="56">56</html:option>
                                    <html:option value="57">57</html:option>
                                    <html:option value="58">58</html:option>
                                    <html:option value="59">59</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_request_audit.process_time.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_request_audit_form" property="processTimeHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="processTimeHelperMonth" >
                                    <html:option value="0"><bean:message bundle="databases" key="databases.common.month.01.display_name" /></html:option>
                                    <html:option value="1"><bean:message bundle="databases" key="databases.common.month.02.display_name" /></html:option>
                                    <html:option value="2"><bean:message bundle="databases" key="databases.common.month.03.display_name" /></html:option>
                                    <html:option value="3"><bean:message bundle="databases" key="databases.common.month.04.display_name" /></html:option>
                                    <html:option value="4"><bean:message bundle="databases" key="databases.common.month.05.display_name" /></html:option>
                                    <html:option value="5"><bean:message bundle="databases" key="databases.common.month.06.display_name" /></html:option>
                                    <html:option value="6"><bean:message bundle="databases" key="databases.common.month.07.display_name" /></html:option>
                                    <html:option value="7"><bean:message bundle="databases" key="databases.common.month.08.display_name" /></html:option>
                                    <html:option value="8"><bean:message bundle="databases" key="databases.common.month.09.display_name" /></html:option>
                                    <html:option value="9"><bean:message bundle="databases" key="databases.common.month.10.display_name" /></html:option>
                                    <html:option value="10"><bean:message bundle="databases" key="databases.common.month.11.display_name" /></html:option>
                                    <html:option value="11"><bean:message bundle="databases" key="databases.common.month.12.display_name" /></html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="processTimeHelperDate" >
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="processTimeHelperHour">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="processTimeHelperMinute">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                    <html:option value="32">32</html:option>
                                    <html:option value="33">33</html:option>
                                    <html:option value="34">34</html:option>
                                    <html:option value="35">35</html:option>
                                    <html:option value="36">36</html:option>
                                    <html:option value="37">37</html:option>
                                    <html:option value="38">38</html:option>
                                    <html:option value="39">39</html:option>
                                    <html:option value="40">40</html:option>
                                    <html:option value="41">41</html:option>
                                    <html:option value="42">42</html:option>
                                    <html:option value="43">43</html:option>
                                    <html:option value="44">44</html:option>
                                    <html:option value="45">45</html:option>
                                    <html:option value="46">46</html:option>
                                    <html:option value="47">47</html:option>
                                    <html:option value="48">48</html:option>
                                    <html:option value="49">49</html:option>
                                    <html:option value="50">50</html:option>
                                    <html:option value="51">51</html:option>
                                    <html:option value="52">52</html:option>
                                    <html:option value="53">53</html:option>
                                    <html:option value="54">54</html:option>
                                    <html:option value="55">55</html:option>
                                    <html:option value="56">56</html:option>
                                    <html:option value="57">57</html:option>
                                    <html:option value="58">58</html:option>
                                    <html:option value="59">59</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_request_audit.content_length.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForContentLength() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForContentLength().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="contentLength" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="contentLength">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForContentLength();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.local_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForLocalPort() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForLocalPort().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="localPort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="localPort">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForLocalPort();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.remote_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForRemotePort() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForRemotePort().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="remotePort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="remotePort">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForRemotePort();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.server_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForServerPort() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForServerPort().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="serverPort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="serverPort">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForServerPort();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.is_processed.display_name" /></td>
               <td>
                   <html:select name="http_request_audit_form" property="isProcessed">
                       <html:option value="true">Yes</html:option>
                       <html:option value="false">No</html:option>
                   </html:select>
               </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_request_audit.was_successful.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForWasSuccessful() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForWasSuccessful().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="wasSuccessful" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="wasSuccessful">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForWasSuccessful();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.return_code.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForReturnCode() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForReturnCode().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="returnCode" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="returnCode">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForReturnCode();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_timestamp.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_request_audit_form" property="auditTimestampHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="auditTimestampHelperMonth" >
                                    <html:option value="0"><bean:message bundle="databases" key="databases.common.month.01.display_name" /></html:option>
                                    <html:option value="1"><bean:message bundle="databases" key="databases.common.month.02.display_name" /></html:option>
                                    <html:option value="2"><bean:message bundle="databases" key="databases.common.month.03.display_name" /></html:option>
                                    <html:option value="3"><bean:message bundle="databases" key="databases.common.month.04.display_name" /></html:option>
                                    <html:option value="4"><bean:message bundle="databases" key="databases.common.month.05.display_name" /></html:option>
                                    <html:option value="5"><bean:message bundle="databases" key="databases.common.month.06.display_name" /></html:option>
                                    <html:option value="6"><bean:message bundle="databases" key="databases.common.month.07.display_name" /></html:option>
                                    <html:option value="7"><bean:message bundle="databases" key="databases.common.month.08.display_name" /></html:option>
                                    <html:option value="8"><bean:message bundle="databases" key="databases.common.month.09.display_name" /></html:option>
                                    <html:option value="9"><bean:message bundle="databases" key="databases.common.month.10.display_name" /></html:option>
                                    <html:option value="10"><bean:message bundle="databases" key="databases.common.month.11.display_name" /></html:option>
                                    <html:option value="11"><bean:message bundle="databases" key="databases.common.month.12.display_name" /></html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="auditTimestampHelperDate" >
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="http_request_audit_form" property="auditTimestampHelperHour">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_audit_form" property="auditTimestampHelperMinute">
                                    <html:option value="0">0</html:option>
                                    <html:option value="1">1</html:option>
                                    <html:option value="2">2</html:option>
                                    <html:option value="3">3</html:option>
                                    <html:option value="4">4</html:option>
                                    <html:option value="5">5</html:option>
                                    <html:option value="6">6</html:option>
                                    <html:option value="7">7</html:option>
                                    <html:option value="8">8</html:option>
                                    <html:option value="9">9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                    <html:option value="13">13</html:option>
                                    <html:option value="14">14</html:option>
                                    <html:option value="15">15</html:option>
                                    <html:option value="16">16</html:option>
                                    <html:option value="17">17</html:option>
                                    <html:option value="18">18</html:option>
                                    <html:option value="19">19</html:option>
                                    <html:option value="20">20</html:option>
                                    <html:option value="21">21</html:option>
                                    <html:option value="22">22</html:option>
                                    <html:option value="23">23</html:option>
                                    <html:option value="24">24</html:option>
                                    <html:option value="25">25</html:option>
                                    <html:option value="26">26</html:option>
                                    <html:option value="27">27</html:option>
                                    <html:option value="28">28</html:option>
                                    <html:option value="29">29</html:option>
                                    <html:option value="30">30</html:option>
                                    <html:option value="31">31</html:option>
                                    <html:option value="32">32</html:option>
                                    <html:option value="33">33</html:option>
                                    <html:option value="34">34</html:option>
                                    <html:option value="35">35</html:option>
                                    <html:option value="36">36</html:option>
                                    <html:option value="37">37</html:option>
                                    <html:option value="38">38</html:option>
                                    <html:option value="39">39</html:option>
                                    <html:option value="40">40</html:option>
                                    <html:option value="41">41</html:option>
                                    <html:option value="42">42</html:option>
                                    <html:option value="43">43</html:option>
                                    <html:option value="44">44</html:option>
                                    <html:option value="45">45</html:option>
                                    <html:option value="46">46</html:option>
                                    <html:option value="47">47</html:option>
                                    <html:option value="48">48</html:option>
                                    <html:option value="49">49</html:option>
                                    <html:option value="50">50</html:option>
                                    <html:option value="51">51</html:option>
                                    <html:option value="52">52</html:option>
                                    <html:option value="53">53</html:option>
                                    <html:option value="54">54</html:option>
                                    <html:option value="55">55</html:option>
                                    <html:option value="56">56</html:option>
                                    <html:option value="57">57</html:option>
                                    <html:option value="58">58</html:option>
                                    <html:option value="59">59</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_username.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuditUsername() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuditUsername().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="auditUsername" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="auditUsername">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuditUsername();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_remote_address.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuditRemoteAddress() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuditRemoteAddress().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="auditRemoteAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="auditRemoteAddress">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuditRemoteAddress();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_remote_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuditRemotePort() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuditRemotePort().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="auditRemotePort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="auditRemotePort">
                    <%
                        long default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuditRemotePort();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_hostname.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuditHostname() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuditHostname().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="auditHostname" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="auditHostname">
                    <%
                        String default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuditHostname();
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
                <td><bean:message bundle="databases" key="database.http_request_audit.audit_is_deleted.display_name" /></td>
                <%
                if (( null == AbstractHttpRequestAudit.getDefaultValuesForAuditIsDeleted() ) || ( 0 == AbstractHttpRequestAudit.getDefaultValuesForAuditIsDeleted().length ))
                {
                 %>
                <td><html:text name="http_request_audit_form" property="auditIsDeleted" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_audit_form" property="auditIsDeleted">
                    <%
                        boolean default_values[] = AbstractHttpRequestAudit.getDefaultValuesForAuditIsDeleted();
                        for ( int counter = 0; counter < default_values.length; counter++ )
                        {
                             String default_value = null ( default_values[counter] );
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
