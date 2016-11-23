<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.platform.shared.value.*" %>
<select name="searchFormNode">
<%
    List data_map_rules = (List) session.getAttribute ( "SearchForm-DataMapRules" );
    int num_rules       = data_map_rules.size();

    for ( int counter = 0; counter < num_rules; counter++ )
    {
        DataMapRule rule = (DataMapRule) data_map_rules.get ( counter );
 %><option value='<%= rule.getGroup() %>'><%= rule.getGroup() %></option><%
    }
 %>
</select>
