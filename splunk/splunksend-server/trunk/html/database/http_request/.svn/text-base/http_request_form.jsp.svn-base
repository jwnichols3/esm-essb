<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_request.auth_type.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForAuthType() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForAuthType().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="authType" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="authType">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForAuthType();
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
                <td><bean:message bundle="databases" key="database.http_request.context_path.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForContextPath() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForContextPath().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="contextPath" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="contextPath">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForContextPath();
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
                <td><bean:message bundle="databases" key="database.http_request.method.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForMethod() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForMethod().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="method" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="method">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForMethod();
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
                <td><bean:message bundle="databases" key="database.http_request.path_info.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForPathInfo() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForPathInfo().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="pathInfo" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="pathInfo">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForPathInfo();
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
                <td><bean:message bundle="databases" key="database.http_request.path_info_translated.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForPathInfoTranslated() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForPathInfoTranslated().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="pathInfoTranslated" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="pathInfoTranslated">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForPathInfoTranslated();
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
                <td><bean:message bundle="databases" key="database.http_request.remote_user.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRemoteUser() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRemoteUser().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="remoteUser" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="remoteUser">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRemoteUser();
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
                <td><bean:message bundle="databases" key="database.http_request.requested_session_id.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRequestedSessionID() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRequestedSessionID().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="requestedSessionID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="requestedSessionID">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRequestedSessionID();
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
                <td><bean:message bundle="databases" key="database.http_request.request_uri.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRequestUri() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRequestUri().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="requestUri" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="requestUri">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRequestUri();
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
                <td><bean:message bundle="databases" key="database.http_request.request_url.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRequestUrl() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRequestUrl().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="requestUrl" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="requestUrl">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRequestUrl();
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
                <td><bean:message bundle="databases" key="database.http_request.servlet_path.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForServletPath() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForServletPath().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="servletPath" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="servletPath">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForServletPath();
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
                <td><bean:message bundle="databases" key="database.http_request.user_principal.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForUserPrincipal() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForUserPrincipal().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="userPrincipal" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="userPrincipal">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForUserPrincipal();
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
                <td><bean:message bundle="databases" key="database.http_request.character_encoding.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForCharacterEncoding() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForCharacterEncoding().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="characterEncoding" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="characterEncoding">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForCharacterEncoding();
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
                <td><bean:message bundle="databases" key="database.http_request.content_type.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForContentType() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForContentType().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="contentType" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="contentType">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForContentType();
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
                <td><bean:message bundle="databases" key="database.http_request.local_address.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForLocalAddress() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForLocalAddress().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="localAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="localAddress">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForLocalAddress();
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
                <td><bean:message bundle="databases" key="database.http_request.protocol.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForProtocol() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForProtocol().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="protocol" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="protocol">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForProtocol();
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
                <td><bean:message bundle="databases" key="database.http_request.remote_address.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRemoteAddress() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRemoteAddress().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="remoteAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="remoteAddress">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRemoteAddress();
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
                <td><bean:message bundle="databases" key="database.http_request.remote_host.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRemoteHost() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRemoteHost().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="remoteHost" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="remoteHost">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForRemoteHost();
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
                <td><bean:message bundle="databases" key="database.http_request.scheme.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForScheme() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForScheme().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="scheme" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="scheme">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForScheme();
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
                <td><bean:message bundle="databases" key="database.http_request.server_name.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForServerName() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForServerName().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="serverName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="serverName">
                    <%
                        String default_values[] = AbstractHttpRequest.getDefaultValuesForServerName();
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
                <td><bean:message bundle="databases" key="database.http_request.request_time.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_request_form" property="requestTimeHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_form" property="requestTimeHelperMonth" >
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
                                <html:select name="http_request_form" property="requestTimeHelperDate" >
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
                                <html:select name="http_request_form" property="requestTimeHelperHour">
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
                                <html:select name="http_request_form" property="requestTimeHelperMinute">
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
                <td><bean:message bundle="databases" key="database.http_request.process_time.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_request_form" property="processTimeHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_request_form" property="processTimeHelperMonth" >
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
                                <html:select name="http_request_form" property="processTimeHelperDate" >
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
                                <html:select name="http_request_form" property="processTimeHelperHour">
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
                                <html:select name="http_request_form" property="processTimeHelperMinute">
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
                <td><bean:message bundle="databases" key="database.http_request.content_length.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForContentLength() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForContentLength().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="contentLength" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="contentLength">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForContentLength();
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
                <td><bean:message bundle="databases" key="database.http_request.local_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForLocalPort() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForLocalPort().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="localPort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="localPort">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForLocalPort();
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
                <td><bean:message bundle="databases" key="database.http_request.remote_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForRemotePort() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForRemotePort().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="remotePort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="remotePort">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForRemotePort();
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
                <td><bean:message bundle="databases" key="database.http_request.server_port.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForServerPort() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForServerPort().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="serverPort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="serverPort">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForServerPort();
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
                <td><bean:message bundle="databases" key="database.http_request.is_processed.display_name" /></td>
               <td>
                   <html:select name="http_request_form" property="isProcessed">
                       <html:option value="true">Yes</html:option>
                       <html:option value="false">No</html:option>
                   </html:select>
               </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_request.was_successful.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForWasSuccessful() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForWasSuccessful().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="wasSuccessful" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="wasSuccessful">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForWasSuccessful();
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
                <td><bean:message bundle="databases" key="database.http_request.return_code.display_name" /></td>
                <%
                if (( null == AbstractHttpRequest.getDefaultValuesForReturnCode() ) || ( 0 == AbstractHttpRequest.getDefaultValuesForReturnCode().length ))
                {
                 %>
                <td><html:text name="http_request_form" property="returnCode" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_request_form" property="returnCode">
                    <%
                        long default_values[] = AbstractHttpRequest.getDefaultValuesForReturnCode();
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
