<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit" %>
<%
	String strRowId = param_map.get ( "row_id" );
	String strIndex = param_map.get ( "rule_id" );

    List <ThrottleAudit> list  = Throttle.retrievePreviousVersions ( Long.parseLong ( param_map.get ( "rule_id" ) ) );
    
    Iterator <ThrottleAudit> i = list.iterator();
    ThrottleAudit object       = null;

    boolean show_col_rule_id      = false;
    boolean show_col_row_id       = false;
    boolean show_col_bgi_group    = true;
    boolean show_col_storm_level  = true;
    boolean show_col_duration     = true;
    boolean show_col_threshold    = true;
    boolean show_col_action       = true;
    boolean show_col_message_flag = false;
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <bean:message bundle="databases" key="databases.test_throttle.throttle.display_name.throttle_edit"/> 
        </td>
    </tr>
    <tr>
        <td>
            <html:form action="throttle_edit_process.do?method=edit" method="post" onsubmit="submitForm ( this ); return false;">
                <table border="1">
                    <%
                        if ( true == show_col_rule_id )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.rule_id.display_name" /></td>
                        <td><html:text name="throttle_form" property="ruleId" /></td>
                    </tr><%
                        }
                        if ( true == show_col_row_id )
                        {
                    %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.row_id.display_name" /></td>
                        <td><html:text name="throttle_form" property="rowId" /></td>
                    </tr><%
                        }
                        if ( true == show_col_bgi_group )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.display_name" /></td>
                        <td><html:text name="throttle_form" property="bgiGroup" readonly="true" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_bgi_group', 'inline');" onMouseOut="setVisibility('instructions_bgi_group','none');">Help</a></u>
                            <div id="instructions_bgi_group" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }
                        if ( true == show_col_storm_level )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.storm_level.display_name" /></td>
                        <td>
                            <html:select name="throttle_form" property="stormLevel">
                                <html:option value="0" >Low-level storm</html:option>
                                <html:option value="1" >Mid-level storm</html:option>
                                <html:option value="2" >High-level storm</html:option>
                            </html:select>
                        </td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_storm_level', 'inline');" onMouseOut="setVisibility('instructions_storm_level','none');">Help</a></u>
                            <div id="instructions_storm_level" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.storm_level.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }
                        if ( true == show_col_duration )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.duration.display_name" /></td>
                        <td><html:text name="throttle_form" property="duration" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_duration', 'inline');" onMouseOut="setVisibility('instructions_duration','none');">Help</a></u>
                            <div id="instructions_duration" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.duration.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }
                        if ( true == show_col_threshold )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.threshold.display_name" /></td>
                        <td><html:text name="throttle_form" property="threshold" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_threshold', 'inline');" onMouseOut="setVisibility('instructions_threshold','none');">Help</a></u>
                            <div id="instructions_threshold" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.threshold.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }
                        if ( true == show_col_action )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.action.display_name" /></td>
                        <td>
                            <html:select name="throttle_form" property="action">
                                <html:option value="DISCARD" >Discard Events</html:option>
                                <html:option value="PASS_THRU" >Allow Event to Pass Through</html:option>
                                <html:option value="SPOOL" >Spool Storm Events to Database</html:option>
                            </html:select>
                        </td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_action', 'inline');" onMouseOut="setVisibility('instructions_action','none');">Help</a></u>
                            <div id="instructions_action" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.action.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }
                        if ( true == show_col_message_flag )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.display_name" /></td>
                        <td><html:text name="throttle_form" property="messageFlag" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_message_flag', 'inline');" onMouseOut="setVisibility('instructions_message_flag','none');">Help</a></u>
                            <div id="instructions_message_flag" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }%>
                    <tr>
                       <td colspan="3"><html:submit>Save Entry</html:submit></td>
                    </tr>
                </table>
                <html:hidden name="throttle_form" property="rowId" value='<%= strRowId %>' />
                <html:hidden name="throttle_form" property="ruleId" value='<%= strIndex %>' />
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <br>
            <br>
            <b><bean:message bundle="databases" key="databases.records.audit_trail" /></b>
            <center>
                <table border="1">
                    <tr>
                        <th>&nbsp;</tn>
                        <th><bean:message bundle="databases" key="databases.records.audit_version_num" /></tn>
                        <th><bean:message bundle="databases" key="databases.records.audit_timestamp" /></tn>
                       <%
                            if ( show_col_rule_id )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.rule_id.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_row_id )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.row_id.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_bgi_group )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_storm_level )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.storm_level.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_duration )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.duration.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_threshold )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.threshold.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_action )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.action.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_message_flag )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.display_name" /></th><%
                            }
                        %>
                    </tr>
                    <%
                        while ( i.hasNext() )
                        {
                            object = i.next();
                      %>
                    <tr>
                        <td><center><html:link action='<%= "/throttle_audit?method=audit&rule_id=" + object.getRuleId() + "&audit_version_num=" + object.getAuditVersionNum() %>'>View Audit</html:link></center></td>
                        <td><center><%= object.getAuditVersionNum() %></center></td>
                        <td><center><%= Throttle.sdf.format ( object.getAuditTimestamp().getTime() ) %></center></td>
                        <%
                            if ( show_col_rule_id )
                            {
                                 %><td><center><%= object.getRuleId() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_row_id )
                            {
                                 %><td><center><%= object.getRowId() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_bgi_group )
                            {
                                 %><td><center><%= object.getBgiGroup() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_storm_level )
                            {
                                 %><td><center><%= object.getStormLevel() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_duration )
                            {
                                 %><td><center><%= object.getDuration() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_threshold )
                            {
                                 %><td><center><%= object.getThreshold() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_action )
                            {
                                 %><td><center><%= object.getAction() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_message_flag )
                            {
                                 %><td><center><%= object.getMessageFlag() %></center></td><%
                            }
                         %>
                    </tr>
                     <%
                        }
                     %>
                </table>
            </center>
        </td>
    </tr>
</table>
