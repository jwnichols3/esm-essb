<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_cookie_audit.row_id.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForRowID() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForRowID().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="rowID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="rowID">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForRowID();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_comment.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForCookieComment() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForCookieComment().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="cookieComment" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="cookieComment">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForCookieComment();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.domain.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForDomain() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForDomain().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="domain" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="domain">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForDomain();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.path.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForPath() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForPath().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="path" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="path">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForPath();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_name.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForCookieName() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForCookieName().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="cookieName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="cookieName">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForCookieName();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForCookiePersistence() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForCookiePersistence().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="cookiePersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="cookiePersistence">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForCookiePersistence();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.is_secure.display_name" /></td>
               <td>
                   <html:select name="http_cookie_audit_form" property="isSecure">
                       <html:option value="true">Yes</html:option>
                       <html:option value="false">No</html:option>
                   </html:select>
               </td>
            </tr>
            <tr>
                <td><bean:message bundle="databases" key="database.http_cookie_audit.max_age.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForMaxAge() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForMaxAge().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="maxAge" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="maxAge">
                    <%
                        long default_values[] = AbstractHttpCookieAudit.getDefaultValuesForMaxAge();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.version.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForVersion() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForVersion().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="version" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="version">
                    <%
                        long default_values[] = AbstractHttpCookieAudit.getDefaultValuesForVersion();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_timestamp.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_cookie_audit_form" property="auditTimestampHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_cookie_audit_form" property="auditTimestampHelperMonth" >
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
                                <html:select name="http_cookie_audit_form" property="auditTimestampHelperDate" >
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
                                <html:select name="http_cookie_audit_form" property="auditTimestampHelperHour">
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
                                <html:select name="http_cookie_audit_form" property="auditTimestampHelperMinute">
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_username.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForAuditUsername() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForAuditUsername().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="auditUsername" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="auditUsername">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForAuditUsername();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_address.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForAuditRemoteAddress() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForAuditRemoteAddress().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="auditRemoteAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="auditRemoteAddress">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForAuditRemoteAddress();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_port.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForAuditRemotePort() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForAuditRemotePort().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="auditRemotePort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="auditRemotePort">
                    <%
                        long default_values[] = AbstractHttpCookieAudit.getDefaultValuesForAuditRemotePort();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_hostname.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForAuditHostname() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForAuditHostname().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="auditHostname" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="auditHostname">
                    <%
                        String default_values[] = AbstractHttpCookieAudit.getDefaultValuesForAuditHostname();
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
                <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_is_deleted.display_name" /></td>
                <%
                if (( null == AbstractHttpCookieAudit.getDefaultValuesForAuditIsDeleted() ) || ( 0 == AbstractHttpCookieAudit.getDefaultValuesForAuditIsDeleted().length ))
                {
                 %>
                <td><html:text name="http_cookie_audit_form" property="auditIsDeleted" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_cookie_audit_form" property="auditIsDeleted">
                    <%
                        boolean default_values[] = AbstractHttpCookieAudit.getDefaultValuesForAuditIsDeleted();
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
