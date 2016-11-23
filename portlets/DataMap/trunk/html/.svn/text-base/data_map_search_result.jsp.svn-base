<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.DataMapRules.forms.DataMap" %>
<%
    String strPageNum = param_map.get ( "page_num" );
	int num_records_per_page = 20;
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
    
    List <DataMap> records = (List) session.getAttribute("dataMapList");
    String group_name = (String) session.getAttribute("group_name");
    long num_records = DataMap.countRecords(group_name);
    Iterator <DataMap> i   = records.iterator();
    DataMap object = null;

    boolean show_col_rule_id = false;
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

    session.removeAttribute("dataMapList");
    
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <bean:message bundle="databases" key="databases.test_datamap.data_map.display_name.data_map_view"/> 
            <br>
            <br>
            <html:link action="/data_map_add"><u><bean:message bundle="databases" key="databases.records.add"/></u></html:link>
            <br>
            <br>
            <center>
                <table border="0" width="80%">
                    <tr>
                    	
                    	<td>Search results of : "<strong><%= group_name %></strong>"</td>
                    
	                    <%
                        if ( page_num > 0 )
                        {
                         %><td><html:link action='<%= "/data_map_search_process.do?method=search&page_num=" + (page_num-1)%>'><bean:message bundle="databases" key="databases.records.prev"/></html:link></td><%
                        }
                        else
                        {
                         %><td>&nbsp;</td><%
                        }
                        if ( (page_num+1)*num_records_per_page < num_records  )
                        {
                         %><td><html:link action='<%= "/data_map_search_process.do?method=search&page_num=" + (page_num+1) %>'><bean:message bundle="databases" key="databases.records.next"/></html:link></td><%
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
                        while ( i.hasNext() )
                        {
                            object = i.next();
                      %>
                    <tr>
                        <td><center><html:link action='<%= "/data_map_edit?method=init&rule_id=" + object.getRuleId() %>'><u><bean:message bundle="databases" key="databases.records.edit"/></u></html:link></center></td>
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
