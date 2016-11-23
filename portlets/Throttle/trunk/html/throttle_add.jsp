<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit" %>
<%
    String strIndex = param_map.get ( "rule_id" );
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
            <bean:message bundle="databases" key="databases.test_throttle.throttle.display_name.throttle_add"/> 
        </td>
    </tr>
    <tr>
        <td>
            <html:form action="throttle_add_process.do?method=add" method="post" onsubmit="submitForm ( this ); return false;">
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.display_name" /></td>
                        <td><html:text name="throttle_form" property="bgiGroup" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_bgi_group', 'inline');" onMouseOut="setVisibility('instructions_bgi_group','none');">Help</a></u>
                            <div id="instructions_bgi_group" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.bgi_group.instructions" />
                            </div>
                        </td>
                    </tr>
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
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.duration.display_name" /></td>
                        <td><html:text name="throttle_form" property="duration" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_duration', 'inline');" onMouseOut="setVisibility('instructions_duration','none');">Help</a></u>
                            <div id="instructions_duration" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.duration.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.threshold.display_name" /></td>
                        <td><html:text name="throttle_form" property="threshold" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_threshold', 'inline');" onMouseOut="setVisibility('instructions_threshold','none');">Help</a></u>
                            <div id="instructions_threshold" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.threshold.instructions" />
                            </div>
                        </td>
                    </tr>
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
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.display_name" /></td>
                        <td><html:text name="throttle_form" property="messageFlag" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_message_flag', 'inline');" onMouseOut="setVisibility('instructions_message_flag','none');">Help</a></u>
                            <div id="instructions_message_flag" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_throttle.throttle.message_flag.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                       <td colspan="3"><html:submit>Save Entry</html:submit></td>
                    </tr>
                </table>
                <html:hidden name="throttle_form" property="ruleId" value='<%= strIndex %>' />
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
        </td>
    </tr>
</table>
