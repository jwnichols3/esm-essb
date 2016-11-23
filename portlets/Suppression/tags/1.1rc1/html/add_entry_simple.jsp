<%@ include file="/html/init.jsp"  %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />
<%
    StringBuffer query  = null;
    int counter         = 0;
    int num_data        = 0;
    long current_time   = System.currentTimeMillis();
    Calendar calendar   = Calendar.getInstance();
    String data[]       = null;

    Toolkit.retrieveServerData ( renderRequest, data_form );

    String username     = request.getRemoteUser();

    TimeZone time_zone  = TimeZone.getDefault();
    //Cookie cookie       = Toolkit.getTimeZoneCookie ( request );
    int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
    time_zone.setRawOffset ( timezone_offset );
    calendar.setTimeZone ( time_zone );
 %>

    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                General Help for Add Entry
            </td>
        </tr>
        <tr>
            <td>
                <html:link action="/view">Home</html:link>
                --&gt;
                <b>Add a New Entry</b>
                <br />
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <html:errors />
                        </td>
                    </tr>
                </table>
                <br />
                <br />
                <html:form action="/add_entry_process" method="post" onsubmit="submitForm(this); return false;">
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.description" /> <a href="#" onclick="return displayHelpDescription()"><u>?</u></a></td>
                            <td><html:textarea name="suppression_add_entry" property="description" cols="40" rows="6"/></td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="byApplication"><bean:message bundle="suppressions" key="form.fields.by_application"/> <a href="#" onclick="return displayHelpApplication()"><u>?</u></a></html:checkbox></td>
                            <td>
                                <html:text name="suppression_add_entry" property="application" onchange="javascript:changeApp()" />
                                <html:text name="suppression_add_entry" property="searchApps" onfocus="javascript:searchAppsFocus()" onblur="searchAppsOnblur()" onkeyup="searchAppsOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="byNode"><bean:message bundle="suppressions" key="form.fields.by_node"/> <a href="#" onclick="return displayHelpNode()"><u>?</u></a></html:checkbox></td>
                            <td>
                                <html:text name="suppression_add_entry" property="node" onchange="javascript:changeNode()" />
                                &nbsp;
                                <html:text name="suppression_add_entry" property="searchNodes" onfocus="javascript:searchNodesFocus()" onblur="searchNodesOnblur()" onkeyup="searchNodesOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="withDbServerInstance" onchange="if ( false == document.suppression_add_entry.withDbServerInstance.checked) { document.suppression_add_entry.withDbServer.checked=false; document.suppression_add_entry.withDbServerMsg.checked=false; return true; }"><bean:message bundle="suppressions" key="form.fields.with_db_server_and_message"/></html:checkbox></td>
                            <td>
                                <html:checkbox name="suppression_add_entry" property="withDbServer"><bean:message bundle="suppressions" key="form.fields.db_server"/> <a href="#" onclick="return displayHelpDatabases()"><u>?</u></a></html:checkbox>
                                <html:text name="suppression_add_entry" property="dbServer" />
                                <html:text name="suppression_add_entry" property="searchDbServer" onfocus="javascript:searchDbServerFocus()" onblur="searchDbServerOnblur()" onkeyup="searchDbServerOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:checkbox name="suppression_add_entry" property="withDbServerMsg"><bean:message bundle="suppressions" key="form.fields.db_message"/> <a href="#" onclick="return displayHelpMessageText()"><u>?</u></a></html:checkbox></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:textarea name="suppression_add_entry" property="dbServerMsg" cols="40" rows="6" onchange="document.suppression_add_entry.withDbServerMsg.checked=true; document.suppression_add_entry.withDbServerInstance.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <br />
                    <bean:message bundle="suppressions" key="form.message.start_suppression"/>
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.start_suppression.start_time"/> <a href="#" onclick="return displayHelpStartTime()"><u>?</u></a></td>
                            <td><html:text name="suppression_add_entry" property="startChoice" value="now"/></td>
                            <td><bean:message bundle="suppressions" key="form.fields.start_suppression.now"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td></td>
                            <td>
                                <html:text name="suppression_add_entry" property="supStartDate" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supStartMonth" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supStartYear" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                                <bean:message bundle="suppressions" key="form.suppression.time.date_time_preposition"/>
                                <html:text name="suppression_add_entry" property="supStartHour" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supStartMinute" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supStartAmpm" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;"/>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <html:checkbox name="suppression_add_entry" property="removeOnReboot"><bean:message bundle="suppressions" key="form.message.remove_on_reboot"/> <a href="#" onclick="return displayHelpReboot()"><u>?</u></a></html:checkbox>
                    <br />
                    <br />
                    <bean:message bundle="suppressions" key="form.message.end_suppression"/>
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.end_suppression.end_time"/> <a href="#" onclick="return displayHelpEndTime()"><u>?</u></a></td>
                            <td><html:text name="suppression_add_entry" property="endChoice" value="offset"/></td>
                            <td>
                                <bean:message bundle="suppressions" key="form.fields.end_suppression.specified"/>
                                <html:text name="suppression_add_entry" property="endChoiceNum"/>
                                <html:text name="suppression_add_entry" property="endChoiceUnit"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td></td>
                            <td>
                                <html:text name="suppression_add_entry" property="supEndDate" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supEndMonth" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supEndYear" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                                <bean:message bundle="suppressions" key="form.suppression.time.date_time_preposition"/>
                                <html:text name="suppression_add_entry" property="supEndHour" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supEndMinute" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                                <html:text name="suppression_add_entry" property="supEndAmpm" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;"/>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <html:checkbox name="suppression_add_entry" property="notifyBeforeExpire"><bean:message bundle="suppressions" key="form.message.notify_before_expire"/>  <a href="#" onclick="return displayHelpNotifyBeforeExpire()"><u>?</u></a></html:checkbox>
                    <br />
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.notify_num_min_prior"/></td>
                            <td><html:text name="suppression_add_entry" property="numMinutesPrior" onchange="document.suppression_add_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.notify_email"/></td>
                            <td><html:text name="suppression_add_entry" property="email" onchange="document.suppression_add_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                            <td><html:submit><bean:message bundle="suppressions" key="form.suppression.button.create_suppression"/></html:submit></td>
                            <td><html:cancel /></td>
                        </tr>
                    </table>
                    <html:hidden name="suppression_add_entry" property="username" value='<%= ""+username %>'/>
                    <html:hidden name="suppression_add_entry" property="timeZoneOffset" value='<%= Integer.toString ( timezone_offset ) %>'/>
                </html:form>
            </td>
        </tr>
    </table>

