<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle" %>
<%
    int num_records_per_page = 20;
    String strPageNum = param_map.get ( "page_num" );
    int page_num      = 0;
    if ( null != strPageNum )
    {
        try
        {
            page_num = Integer.parseInt ( strPageNum );
        }
        catch ( Exception exception )
        {
            page_num = 0;
        }
    }

    long num_records = Throttle.countRecords();
    List <Throttle> records = Throttle.retrieveRecordsPage ( page_num, num_records_per_page );
    Iterator <Throttle> i   = records.iterator();
    Throttle object = null;

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
            <bean:message bundle="databases" key="databases.test_throttle.throttle.display_name.throttle_view"/> 
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/throttle_add"><bean:message bundle="databases" key="databases.records.add"/></html:link>
            <br>
            <br>
            <center>
                <table border="0" width="80%">
                    <tr>
                        <%
                        if ( page_num > 0 )
                        {
                         %><td><html:link action='<%= "/throttle_view?page_num=" + (page_num-1) %>'><bean:message bundle="databases" key="databases.records.prev"/></html:link></td><%
                        }
                        else
                        {
                         %><td>&nbsp;</td><%
                        }
                        if ( (page_num+1)*num_records_per_page < num_records  )
                        {
                         %><td><html:link action='<%= "/throttle_view?page_num=" + (page_num+1) %>'><bean:message bundle="databases" key="databases.records.next"/></html:link></td><%
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
                        <td><center><html:link action='<%= "/throttle_edit?method=init&rule_id=1&row_id=" + object.getRowId() %>'><bean:message bundle="databases" key="databases.records.edit"/></html:link></center></td>
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
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
