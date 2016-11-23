<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit" %>
<%
    String strIndex = param_map.get ( "rule_id" );

    List <DataMapAudit> list = DataMap.retrievePreviousVersions ( Long.parseLong ( param_map.get ( "rule_id" ) ) );
    Iterator <DataMapAudit> i = list.iterator();
    DataMapAudit object = null;

    boolean show_col_rule_id      = false;
    boolean show_col_bgi_group    = true;
    boolean show_col_bgi_method   = true;
    boolean show_col_ap_group     = true;
    boolean show_col_ap_script    = true;
    boolean show_col_per_cat      = false;
    boolean show_col_per_subcat   = false;
    boolean show_col_per_product  = false;
    boolean show_col_per_problem  = false;
    boolean show_col_per_assign   = false;
    boolean show_col_per_location = false;
    boolean show_col_audit_version_num = true;
    boolean show_col_audit_modified_by = true;
    boolean show_col_audit_timestamp = true;
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
            <bean:message bundle="databases" key="databases.test_datamap.data_map.display_name.data_map_edit"/> 
        </td>
    </tr>
    <tr>
        <td>
            <center>
            <html:form action="data_map_edit_process.do?method=edit" method="post" onsubmit="submitForm ( this ); return false;">
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_version_num" /></td>
                        <td><html:text readonly="true" name="data_map_form_audit" property="auditVersionNum" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_timestamp" /></td>
                        <td><html:text readonly="true" name="data_map_form_audit" property="auditTimestamp" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.records.audit_modified_by" /></td>
                        <td><html:text readonly="true" name="data_map_form_audit" property="auditModifiedBy" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_group.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="bgiGroup" /></td>
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
                            <html:select disabled="true"  name="data_map_form_audit" property="bgiMethod">
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
                        <td><html:text readonly="true"  name="data_map_form_audit" property="apGroup" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_ap_group', 'inline');" onMouseOut="setVisibility('instructions_ap_group','none');">Help</a></u>
                            <div id="instructions_ap_group" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.ap_group.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.ap_script.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="apScript" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_ap_script', 'inline');" onMouseOut="setVisibility('instructions_ap_script','none');">Help</a></u>
                            <div id="instructions_ap_script" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.ap_script.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="perCat" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_cat', 'inline');" onMouseOut="setVisibility('instructions_per_cat','none');">Help</a></u>
                            <div id="instructions_per_cat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="perSubcat" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_subcat', 'inline');" onMouseOut="setVisibility('instructions_per_subcat','none');">Help</a></u>
                            <div id="instructions_per_subcat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="perProduct" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_product', 'inline');" onMouseOut="setVisibility('instructions_per_product','none');">Help</a></u>
                            <div id="instructions_per_product" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="perProblem" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_problem', 'inline');" onMouseOut="setVisibility('instructions_per_problem','none');">Help</a></u>
                            <div id="instructions_per_problem" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_assign.display_name" /></td>
                        <td><html:text readonly="true"  name="data_map_form_audit" property="perAssign" /></td>
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
                            <html:select disabled="true"  name="data_map_form_audit" property="perLocation">
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
                </table>
                <html:hidden name="data_map_form_audit" property="ruleId" value='<%= strIndex %>' />
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
