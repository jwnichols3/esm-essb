<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit" %>
<%
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
    boolean show_col_message_flag = true;
 %>
<script language="JavaScript">
    function setVisibility ( id, visibility ) 
    {
        document.all[id].style.display = visibility;
    }
</script> 

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
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_version_num" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="auditVersionNum" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_timestamp" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="auditTimestamp" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_modified_by" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="auditModifiedBy" /></td>
                        <td>&nbsp;</td>
                    </tr>

                    <%
                        if ( true == show_col_rule_id )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.rule_id.display_name" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="ruleId" /></td>
                        <td>&nbsp;</td>
                    </tr><%
                        }
                        if ( true == show_col_row_id )
                        {
                    %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.row_id.display_name" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="rowId" /></td>
                        <td>&nbsp;</td>
                    </tr><%
                        }
                        if ( true == show_col_bgi_group )
                        {
                             %>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.display_name" /></td>
                        <td><html:text readonly="true" name="throttle_form_audit" property="bgiGroup" /></td>
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
                            <html:select disabled="true" name="throttle_form_audit" property="stormLevel">
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
                        <td><html:text readonly="true" name="throttle_form_audit" property="duration" /></td>
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
                        <td><html:text readonly="true" name="throttle_form_audit" property="threshold" /></td>
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
                            <html:select disabled="true" name="throttle_form_audit" property="action">
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
                        <td><html:text readonly="true" name="throttle_form_audit" property="messageFlag" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_message_flag', 'inline');" onMouseOut="setVisibility('instructions_message_flag','none');">Help</a></u>
                            <div id="instructions_message_flag" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.instructions" />
                            </div>
                        </td>
                    </tr><%
                        }%>
                </table>
                <html:hidden name="throttle_form_audit" property="ruleId" value='<%= strIndex %>' />
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
