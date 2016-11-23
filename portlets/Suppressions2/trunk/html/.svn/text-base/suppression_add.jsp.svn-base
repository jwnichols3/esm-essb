<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.forms.SuppressionAudit" %>
<%
    String strIndex = param_map.get ( "suppress_id" );
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <html:form action="/suppression_add_process.do?method=add" method="post" onsubmit="submitForm ( this ); return false;">
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.row_id.display_name" /></td>
                        <td><html:text name="suppression_form" property="rowId" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.start_time.display_name" /></td>
                        <td>
                            <table border="0">
                                <tr>
                                    <td>
                                        <html:select name="suppression_form" property="startTimeYear">
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="startTimeMonth">
                                            <html:option value="0" ><bean:message bundle="suppressions" key="form.suppression.time.month.01"/></html:option>
                                            <html:option value="1" ><bean:message bundle="suppressions" key="form.suppression.time.month.02"/></html:option>
                                            <html:option value="2" ><bean:message bundle="suppressions" key="form.suppression.time.month.03"/></html:option>
                                            <html:option value="3" ><bean:message bundle="suppressions" key="form.suppression.time.month.04"/></html:option>
                                            <html:option value="4" ><bean:message bundle="suppressions" key="form.suppression.time.month.05"/></html:option>
                                            <html:option value="5" ><bean:message bundle="suppressions" key="form.suppression.time.month.06"/></html:option>
                                            <html:option value="6" ><bean:message bundle="suppressions" key="form.suppression.time.month.07"/></html:option>
                                            <html:option value="7" ><bean:message bundle="suppressions" key="form.suppression.time.month.08"/></html:option>
                                            <html:option value="8" ><bean:message bundle="suppressions" key="form.suppression.time.month.09"/></html:option>
                                            <html:option value="9" ><bean:message bundle="suppressions" key="form.suppression.time.month.10"/></html:option>
                                            <html:option value="10"><bean:message bundle="suppressions" key="form.suppression.time.month.11"/></html:option>
                                            <html:option value="11"><bean:message bundle="suppressions" key="form.suppression.time.month.12"/></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="startTimeHour">
                                            <html:option value="1" >1</html:option>
                                            <html:option value="2" >2</html:option>
                                            <html:option value="3" >3</html:option>
                                            <html:option value="4" >4</html:option>
                                            <html:option value="5" >5</html:option>
                                            <html:option value="6" >6</html:option>
                                            <html:option value="7" >7</html:option>
                                            <html:option value="8" >8</html:option>
                                            <html:option value="9" >9</html:option>
                                            <html:option value="10">10</html:option>
                                            <html:option value="11">11</html:option>
                                            <html:option value="12">12</html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="startTimeMinute">
                                            <html:option value="0" >00</html:option>
                                            <html:option value="1" >01</html:option>
                                            <html:option value="2" >02</html:option>
                                            <html:option value="3" >03</html:option>
                                            <html:option value="4" >04</html:option>
                                            <html:option value="5" >05</html:option>
                                            <html:option value="6" >06</html:option>
                                            <html:option value="7" >07</html:option>
                                            <html:option value="8" >08</html:option>
                                            <html:option value="9" >09</html:option>
                                        <%
                                            for ( counter = 10; counter < 60; counter++ )
                                            {
                                            %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                            }
                                        %>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="startTimeAmPm">
                                            <html:option value='<%= "" + Calendar.AM %>'>AM</html:option>
                                            <html:option value='<%= "" + Calendar.PM %>'>PM</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.end_time.display_name" /></td>
                        <td>
                            <table border="0">
                                <tr>
                                    <td>
                                        <html:select name="suppression_form" property="endTimeYear">
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="endTimeMonth">
                                            <html:option value="0" ><bean:message bundle="suppressions" key="form.suppression.time.month.01"/></html:option>
                                            <html:option value="1" ><bean:message bundle="suppressions" key="form.suppression.time.month.02"/></html:option>
                                            <html:option value="2" ><bean:message bundle="suppressions" key="form.suppression.time.month.03"/></html:option>
                                            <html:option value="3" ><bean:message bundle="suppressions" key="form.suppression.time.month.04"/></html:option>
                                            <html:option value="4" ><bean:message bundle="suppressions" key="form.suppression.time.month.05"/></html:option>
                                            <html:option value="5" ><bean:message bundle="suppressions" key="form.suppression.time.month.06"/></html:option>
                                            <html:option value="6" ><bean:message bundle="suppressions" key="form.suppression.time.month.07"/></html:option>
                                            <html:option value="7" ><bean:message bundle="suppressions" key="form.suppression.time.month.08"/></html:option>
                                            <html:option value="8" ><bean:message bundle="suppressions" key="form.suppression.time.month.09"/></html:option>
                                            <html:option value="9" ><bean:message bundle="suppressions" key="form.suppression.time.month.10"/></html:option>
                                            <html:option value="10"><bean:message bundle="suppressions" key="form.suppression.time.month.11"/></html:option>
                                            <html:option value="11"><bean:message bundle="suppressions" key="form.suppression.time.month.12"/></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="endTimeHour">
                                            <html:option value="1" >1</html:option>
                                            <html:option value="2" >2</html:option>
                                            <html:option value="3" >3</html:option>
                                            <html:option value="4" >4</html:option>
                                            <html:option value="5" >5</html:option>
                                            <html:option value="6" >6</html:option>
                                            <html:option value="7" >7</html:option>
                                            <html:option value="8" >8</html:option>
                                            <html:option value="9" >9</html:option>
                                            <html:option value="10">10</html:option>
                                            <html:option value="11">11</html:option>
                                            <html:option value="12">12</html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="endTimeMinute">
                                            <html:option value="0" >00</html:option>
                                            <html:option value="1" >01</html:option>
                                            <html:option value="2" >02</html:option>
                                            <html:option value="3" >03</html:option>
                                            <html:option value="4" >04</html:option>
                                            <html:option value="5" >05</html:option>
                                            <html:option value="6" >06</html:option>
                                            <html:option value="7" >07</html:option>
                                            <html:option value="8" >08</html:option>
                                            <html:option value="9" >09</html:option>
                                        <%
                                            for ( counter = 10; counter < 60; counter++ )
                                            {
                                            %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                            }
                                        %>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="suppression_form" property="endTimeAmPm">
                                            <html:option value='<%= "" + Calendar.AM %>'>AM</html:option>
                                            <html:option value='<%= "" + Calendar.PM %>'>PM</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.ap_name.display_name" /></td>
                        <td><html:text name="suppression_form" property="appName" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.node_name.display_name" /></td>
                        <td><html:text name="suppression_form" property="nodeName" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.group_name.display_name" /></td>
                        <td><html:text name="suppression_form" property="groupName" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.db_server.display_name" /></td>
                        <td><html:text name="suppression_form" property="dbServer" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.notify_flg.display_name" /></td>
                        <td><html:text name="suppression_form" property="notifyFlg" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.notify_minutes.display_name" /></td>
                        <td><html:text name="suppression_form" property="notifyMinutes" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.remove_on_reboot.display_name" /></td>
                        <td><html:text name="suppression_form" property="removeOnReboot" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.description.display_name" /></td>
                        <td><html:text name="suppression_form" property="description" />
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_suppressions.suppression.message.display_name" /></td>
                        <td><html:text name="suppression_form" property="message" />
                    </tr>
                    <tr>
                       <td colspan="2"><html:submit>Save Entry</html:submit></td>
                    </tr>
                </table>
                <html:hidden name="suppression_form" property="suppressId" value='<%= strIndex %>' />
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
        </td>
    </tr>
</table>
