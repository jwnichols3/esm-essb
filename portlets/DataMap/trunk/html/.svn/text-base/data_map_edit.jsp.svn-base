<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMapAudit" %>
<%
    String strIndex = param_map.get ( "rule_id" );

    List <DataMapAudit> list = null; //DataMap.retrievePreviousVersions ( Long.parseLong ( param_map.get ( "rule_id" ) ) );
    Iterator <DataMapAudit> i = null; //list.iterator();
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
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_group.display_name" /></td>
                        <td><html:text name="data_map_form" property="bgiGroup" /></td>
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
                        <td><html:text name="data_map_form" property="apGroup" /></td>
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
                        <td><html:text name="data_map_form" property="perCat" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_cat', 'inline');" onMouseOut="setVisibility('instructions_per_cat','none');">Help</a></u>
                            <div id="instructions_per_cat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.display_name" /></td>
                        <td><html:text name="data_map_form" property="perSubcat" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_subcat', 'inline');" onMouseOut="setVisibility('instructions_per_subcat','none');">Help</a></u>
                            <div id="instructions_per_subcat" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.display_name" /></td>
                        <td><html:text name="data_map_form" property="perProduct" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_product', 'inline');" onMouseOut="setVisibility('instructions_per_product','none');">Help</a></u>
                            <div id="instructions_per_product" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.display_name" /></td>
                        <td><html:text name="data_map_form" property="perProblem" /></td>
                        <td>
                            <u><a href="#" onclick="return false;" onMouseOver="setVisibility('instructions_per_problem', 'inline');" onMouseOut="setVisibility('instructions_per_problem','none');">Help</a></u>
                            <div id="instructions_per_problem" style="position: absolute; width: 200px; background-color: white; padding: 5px; border: #000000 1px solid; display: none;" >
                                <bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.instructions" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="databases.test_datamap.data_map.per_assign.display_name" /></td>
                        <td><html:text name="data_map_form" property="perAssign" /></td>
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
            <br>
            <br>
            <b><bean:message bundle="databases" key="databases.records.audit_trail" /></b>
            <center>
                <table border="1">
                    <tr>
                        <th>&nbsp;</th>
                       <%
                            if ( show_col_audit_version_num )
                            {
                        %><th><bean:message bundle="databases" key="databases.records.audit_version_num" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_audit_timestamp )
                            {
                        %><th><bean:message bundle="databases" key="databases.records.audit_timestamp" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_audit_modified_by )
                            {
                        %><th><bean:message bundle="databases" key="databases.records.audit_modified_by" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_rule_id )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.rule_id.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_bgi_group )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_group.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_bgi_method )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.bgi_method.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_ap_group )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.ap_group.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_ap_script )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.ap_script.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_cat )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_cat.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_subcat )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_subcat.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_product )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_product.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_problem )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_problem.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_assign )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_assign.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_per_location )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_datamap.data_map.per_location.display_name" /></th><%
                            }
                        %>
                    </tr>
                    <%
                        while (( null != i ) && ( i.hasNext() ))
                        {
                            object = i.next();
                      %>
                    <tr>
                        <td><center><html:link action='<%= "/data_map_audit?method=audit&rule_id=" + object.getRuleId() + "&audit_version_num=" + object.getAuditVersionNum() %>'>View Audit</html:link></center></td>
                       <%
                            if ( show_col_audit_version_num )
                            {
                        %><td><center><%= object.getAuditVersionNum() %></center></td><%
                            }
                        %>
                       <%
                            if ( show_col_audit_timestamp )
                            {
                        %><td><center><%= Toolkit.sdf.format ( object.getAuditTimestamp().getTime() ) %></center></td><%
                            }
                        %>
                       <%
                            if ( show_col_audit_modified_by )
                            {
                        %><td><center><%= object.getAuditModifiedBy() %></center></td><%
                            }
                        %>
                        <%
                            if ( show_col_rule_id )
                            {
                                 %><td><center><%= object.getRuleId() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_bgi_group )
                            {
                                 %><td><center><%= object.getBgiGroup() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_bgi_method )
                            {
                                 %><td><center><%= object.getBgiMethod() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_ap_group )
                            {
                                 %><td><center><%= object.getApGroup() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_ap_script )
                            {
                                 %><td><center><%= object.getApScript() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_cat )
                            {
                                 %><td><center><%= object.getPerCat() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_subcat )
                            {
                                 %><td><center><%= object.getPerSubcat() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_product )
                            {
                                 %><td><center><%= object.getPerProduct() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_problem )
                            {
                                 %><td><center><%= object.getPerProblem() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_assign )
                            {
                                 %><td><center><%= object.getPerAssign() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_per_location )
                            {
                                 %><td><center><%= object.getPerLocation() %></center></td><%
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
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
