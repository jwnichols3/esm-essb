<%@ include file="/html/init.jsp" %>
        <table border="1">
            <tr>
                <td><bean:message bundle="databases" key="database.http_attribute_audit.row_id.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForRowID() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForRowID().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="rowID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="rowID">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForRowID();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.request_id.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForRequestID() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForRequestID().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="requestID" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="requestID">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForRequestID();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.attribute_name.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAttributeName() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAttributeName().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="attributeName" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="attributeName">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAttributeName();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.attribute_persistence.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAttributePersistence() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAttributePersistence().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="attributePersistence" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="attributePersistence">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAttributePersistence();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_timestamp.display_name" /></td>
                <td>
                    <table border="0">
                        <tr>
                            <td><html:text name="http_attribute_audit_form" property="auditTimestampHelperYear" /></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="http_attribute_audit_form" property="auditTimestampHelperMonth" >
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
                                <html:select name="http_attribute_audit_form" property="auditTimestampHelperDate" >
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
                                <html:select name="http_attribute_audit_form" property="auditTimestampHelperHour">
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
                                <html:select name="http_attribute_audit_form" property="auditTimestampHelperMinute">
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_username.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAuditUsername() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAuditUsername().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="auditUsername" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="auditUsername">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAuditUsername();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_remote_address.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAuditRemoteAddress() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAuditRemoteAddress().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="auditRemoteAddress" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="auditRemoteAddress">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAuditRemoteAddress();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_remote_port.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAuditRemotePort() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAuditRemotePort().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="auditRemotePort" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="auditRemotePort">
                    <%
                        long default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAuditRemotePort();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_hostname.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAuditHostname() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAuditHostname().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="auditHostname" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="auditHostname">
                    <%
                        String default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAuditHostname();
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
                <td><bean:message bundle="databases" key="database.http_attribute_audit.audit_is_deleted.display_name" /></td>
                <%
                if (( null == AbstractHttpAttributeAudit.getDefaultValuesForAuditIsDeleted() ) || ( 0 == AbstractHttpAttributeAudit.getDefaultValuesForAuditIsDeleted().length ))
                {
                 %>
                <td><html:text name="http_attribute_audit_form" property="auditIsDeleted" /></td>
                <%
                }
                else
                {
                 %>
                <td>
                    <html:select name="http_attribute_audit_form" property="auditIsDeleted">
                    <%
                        boolean default_values[] = AbstractHttpAttributeAudit.getDefaultValuesForAuditIsDeleted();
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
