<%@ include file="/html/init.jsp" %>
<%@ page import="java.lang.ref.WeakReference" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression" %>
<%@ page import="com.bgi.esm.monitoring.platform.client.BackEndFacade"           %>
<%@ page import="com.bgi.esm.monitoring.platform.shared.value.SuppressionRule"   %>
<%
    int num_records_per_page = 20;
    String strPageNum = param_map.get ( "page_num" );
    int page_num      = 1;
    if ( null != strPageNum )
    {
        try
        {
            page_num = Integer.parseInt ( strPageNum );
        }
        catch ( Exception exception )
        {
            page_num = 1;
        }
    }

    WeakReference <BackEndFacade> bef  = new WeakReference <BackEndFacade> ( new BackEndFacade() );
    List <SuppressionRule> records     = bef.get().getAllSuppressionRulesPaginate ( num_records_per_page, page_num );

    if ( null != records )
    {
    Iterator <SuppressionRule> i   = records.iterator();
    SuppressionRule object         = null;

    //  Time zone formatting
	Calendar calendar            = Calendar.getInstance();
	TimeZone time_zone           = TimeZone.getDefault();
    int timezone_offset          = Toolkit.computeTimeZoneOffset ( request );
	time_zone.setRawOffset ( timezone_offset );

	SimpleDateFormat sdf         = new SimpleDateFormat ( "yyyy MMM dd HH:mm" );
	sdf.setTimeZone ( time_zone );

    boolean show_col_suppress_id      = true;
    boolean show_col_row_id           = false;
    boolean show_col_start_time       = true;
    boolean show_col_end_time         = true;
    boolean show_col_ap_name          = true;
    boolean show_col_node_name        = true;
    boolean show_col_group_name       = false;
    boolean show_col_db_server        = true;
    boolean show_col_notify_flg       = false;
    boolean show_col_notify_minutes   = false;
    boolean show_col_remove_on_reboot = true;
    boolean show_col_description      = true;
    boolean show_col_message          = true;

    String username                   = request.getRemoteUser();
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <html:link action="/add_entry"><bean:message bundle="databases" key="databases.records.add"/></html:link>
            <br>
            <br>
            <center>
                <table border="0" width="80%">
                    <tr>
                        <%
                        if ( page_num > 0 )
                        {
                         %><td><html:link action='<%= "/suppression_view?page_num=" + (page_num-1) %>'><bean:message bundle="databases" key="databases.records.prev"/></html:link></td><%
                        }
                        else
                        {
                         %><td>&nbsp;</td><%
                        }
                        if ( records.size() == num_records_per_page )
                        {
                         %><td><html:link action='<%= "/suppression_view?page_num=" + (page_num+1) %>'><bean:message bundle="databases" key="databases.records.next"/></html:link></td><%
                        }
                        else
                        {
                         %><td>&nbsp;</td><%
                        }
                         %>
                    </tr>
                </table>
                <table border="1">
                    <tr>
                        <th>&nbsp;</th>
                       <%
                            if ( show_col_row_id )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.row_id.display_name" /></th><%
                            }
                        %><%
                            if ( show_col_suppress_id )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.suppress_id.display_name" /></th><%
                            }
                         %>
                       <%
                            if ( show_col_ap_name )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.ap_name.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_node_name )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.node_name.display_name" /></th><%
                            }
                        %>
                           <th>
                               <bean:message bundle="databases" key="databases.test_suppressions.suppression.message.display_name" />
                               <br>
                               <br>
                               <bean:message bundle="databases" key="databases.test_suppressions.suppression.db_server.display_name" />
                           </th>
                       <%
                            if ( show_col_description )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.description.display_name" /></th><%
                            }
                        %>
                           <th nowrap>
                               <bean:message bundle="databases" key="databases.test_suppressions.suppression.start_time.display_name" />
                               <br>
                               <br>
                               <bean:message bundle="databases" key="databases.test_suppressions.suppression.end_time.display_name" />
                           </th>
                       <%
                            if ( show_col_group_name )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.group_name.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_notify_flg )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.notify_flg.display_name" /></th><%
                            }
                        %>
                       <%
                            if ( show_col_notify_minutes )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.notify_minutes.display_name" /></th><%
                            }
                        %>
                         <th>Owner</th>
                       <%
                            if ( show_col_remove_on_reboot )
                            {
                         %><th><bean:message bundle="databases" key="databases.test_suppressions.suppression.remove_on_reboot.display_name" /></th><%
                            }
                        %>
                    </tr>
                    <%
                        while ( i.hasNext() )
                        {
                            object = i.next();
                      %>
                    <tr>
                        <td><center>
                            <%
                                if (( null != username ) && ( username.equals ( object.getOwner() ) ))
                                {
                                %><html:link action='<%= "/suppression_edit?method=edit&suppress_id=" + object.getSuppressId() %>'><bean:message bundle="databases" key="databases.records.edit"/></html:link><%
                                }
                                else
                                {
                                %>&nbsp;<%
                                }

                             %>
                            </center>
                        </td>
                        <%
                            if ( show_col_suppress_id )
                            {
                                 %><td><center><%= object.getSuppressId() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_ap_name )
                            {
                                 %><td><center><%= object.getApplicationName() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_node_name )
                            {
                                 %><td><center><%= object.getNodeName() %></center></td><%
                            }
                         %>
                                 <td>
                                     <%= object.getMessage() %>
                                     <br>
                                     <br>
                                     <%= object.getDatabaseServerName() %>
                                 </td>
                        <%
                            if ( show_col_description )
                            {
                                 %><td><center><%= object.getDescription() %></center></td><%
                            }
                         %>
                                 <td nowrap>
                                     <%= sdf.format ( object.getStartTime().getTime() ) %>
                                     <br>
                                     <br>
                                     <%= sdf.format ( object.getEndTime().getTime() ) %>
                                 </td>
                        <%
                            if ( show_col_group_name )
                            {
                                 %><td><center><%= object.getGroupName() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_notify_flg )
                            {
                                 %><td><center><%= object.getNotificationFlag() %></center></td><%
                            }
                         %>
                        <%
                            if ( show_col_notify_minutes )
                            {
                                 %><td><center><%= object.getNotifyMinutes() %></center></td><%
                            }
                         %>
                                 <td><center><%= object.getOwner() %></center></td>
                        <%
                            if ( show_col_remove_on_reboot )
                            {
                                 %><td><center><%= object.getRemoveOnReboot() %></center></td><%
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
<%
    }
    else
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <html:link action="/add_entry">Add New Suppression</html:link>
            <br>
            <br>
            <center>No suppressions found.</center>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
<%
    }
 %>
