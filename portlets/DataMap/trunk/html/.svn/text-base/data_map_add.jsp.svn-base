<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit" %>
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
            <bean:message bundle="databases" key="databases.test_datamap.data_map.display_name.data_map_add"/> 
        </td>
    </tr>
    <tr>
        <td>
            <center>
            <html:form action="data_map_add_process.do?method=add" method="post" onsubmit="submitForm ( this ); return false;">
                <table border="1" width="80%">
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_group.display_name" /></td>
                        <td><html:text name="data_map_form" property="bgiGroup" size="40" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_bgi_group', 'inline');" onMouseOut="setVisibility('instructions_bgi_group','none');">Help</a></u>
                            <div id="instructions_bgi_group" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_group.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_method.display_name" /></td>
                        <td>
                            <html:select name="data_map_form" property="bgiMethod">
                                <html:option value="ticket" >Ticket + Notification</html:option>
                                <html:option value="alarmpoint_only" >Notification Only</html:option>
                            </html:select>
                        </td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_bgi_method', 'inline');" onMouseOut="setVisibility('instructions_bgi_method','none');">Help</a></u>
                            <div id="instructions_bgi_method" style="position: absolute; width: 400px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_method.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.ap_group.display_name" /></td>
                        <td><html:text name="data_map_form" property="apGroup" size="40" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_ap_group', 'inline');" onMouseOut="setVisibility('instructions_ap_group','none');">Help</a></u>
                            <div id="instructions_ap_group" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.ap_group.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.ap_script.display_name" /></td>
                        <td>
                            <html:select name="data_map_form" property="apScript">
                                <html:option value="BGI Broadcast" >BGI Broadcast</html:option>
                                <html:option value="BGI COMFORT" >BGI COMFORT</html:option>
                                <html:option value="BGI Equilend" >BGI Equilend</html:option>
                                <html:option value="BGI NetIQ" >BGI NetIQ</html:option>
                                <html:option value="BGI On-Call" >BGI On-Call</html:option>
                                <html:option value="BGI ORBIS" >BGI ORBIS</html:option>
                                <html:option value="BGI ORBIS ESCALATE" >BGI ORBIS ESCALATE</html:option>
                                <html:option value="BGI UKTEST" >BGI UKTEST</html:option>
                                <html:option value="BGI VPO" >BGI VPO</html:option>
                                <html:option value="callin" >callin</html:option>
                                <html:option value="callout" >callout</html:option>
                                <html:option value="default" >default</html:option>
                                <html:option value="HP-OVO-Unix" >HP-OVO-Unix</html:option>
                                <html:option value="messaging" >messaging</html:option>
                                <html:option value="on-call" >on-call</html:option>
                                <html:option value="Peregrine" >Peregrine</html:option>
                                <html:option value="Peregrine ServiceCenter" >Peregrine ServiceCenter</html:option>
                                <html:option value="ping" >ping</html:option>
                                <html:option value="scenario" >scenario</html:option>
                                <html:option value="voicerecordings" >voice recordings</html:option>
                            </html:select>
                        </td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_ap_script', 'inline');" onMouseOut="setVisibility('instructions_ap_script','none');">Help</a></u>
                            <div id="instructions_ap_script" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.ap_script.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.display_name" /></td>
                        <td><html:text name="data_map_form" property="perCat" size="40"value="MONITORING EVENT"/></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_cat', 'inline');" onMouseOut="setVisibility('instructions_per_cat','none');">Help</a></u>
                            <div id="instructions_per_cat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.display_name" /></td>
                        <td><html:text name="data_map_form" property="perSubcat" size="40"value="INCIDENT" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_subcat', 'inline');" onMouseOut="setVisibility('instructions_per_subcat','none');">Help</a></u>
                            <div id="instructions_per_subcat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.display_name" /></td>
                        <td><html:text name="data_map_form" property="perProduct" size="40" value="SYSTEM MONITORING"/></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_product', 'inline');" onMouseOut="setVisibility('instructions_per_product','none');">Help</a></u>
                            <div id="instructions_per_product" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.display_name" /></td>
                        <td><html:text name="data_map_form" property="perProblem" size="40" value="SYSTEM ALARM" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_problem', 'inline');" onMouseOut="setVisibility('instructions_per_problem','none');">Help</a></u>
                            <div id="instructions_per_problem" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_assign.display_name" /></td>
                        <td><html:text name="data_map_form" property="perAssign" size="40" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_assign', 'inline');" onMouseOut="setVisibility('instructions_per_assign','none');">Help</a></u>
                            <div id="instructions_per_assign" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_assign.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_location.display_name" /></td>
                        <td>
                            <html:select name="data_map_form" property="perLocation">
                                <html:option value="GLOBAL" >Global</html:option>
                                <html:option value="RAC1" >Rancho Cordova</html:option>
                            </html:select>
                        </td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_location', 'inline');" onMouseOut="setVisibility('instructions_per_location','none');">Help</a></u>
                            <div id="instructions_per_location" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_location.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                       <td colspan="3"><html:submit>Save Entry</html:submit></td>
                    </tr>
                </table>
                <html:hidden name="data_map_form" property="ruleId" value='<%= strIndex %>' />
            </html:form>
            </center>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
