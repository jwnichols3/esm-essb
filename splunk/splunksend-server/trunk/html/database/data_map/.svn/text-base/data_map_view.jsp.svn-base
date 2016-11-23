<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap" %>
<%
    List <IDataMap> records = (List <IDataMap>) session.getAttribute ( IDataMap.SESSION_BROWSE );
    boolean ShowColumn_RuleID           = true;
    boolean ShowColumn_ApplicationName  = true;
    boolean ShowColumn_Hostname         = true;
    boolean ShowColumn_TargetPath       = true;

    Map <String, String> params = BaseDispatchAction.retrieveHttpRequestParameters ( request );

    String strPageNum  = params.get ( "page_num" );
    String strPageSize = params.get ( "page_size" ); 
    int page_num       = 0;
    int page_size      = 25;

    try
    {
        if ( null != strPageNum  ) page_num  = Integer.parseInt ( strPageNum  );
        if ( null != strPageSize ) page_size = Integer.parseInt ( strPageSize );
    }
    catch ( RuntimeException exception )
    {
        page_num  = 0;
        page_size = 25;
    }

    if ( null != records )
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table width="100%" border="0">
                <tr>
                    <td>
                    <%
                        if ( page_num > 0 )
                        {
                             %><html:link action='<%= String.format ( "/data_map/view.do?page_size=%d&page_num=%d", page_size, page_num-1 ) %>'>Prev</html:link><%
                        }
                        else
                        {
                             %>&nbsp;<%
                        }
                     %>
                    </td>
                    <td><html:link action='<%= String.format ( "/data_map/view.do?page_size=%d&page_num=%d", page_size, page_num+1 ) %>'>Next</html:link></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/data_map/add">Add New Record</html:link>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <% if ( ShowColumn_RuleID )           { %><th><bean:message bundle="databases" key="database.data_map.rule_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_ApplicationName )  { %><th><bean:message bundle="databases" key="database.data_map.application_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_Hostname )         { %><th><bean:message bundle="databases" key="database.data_map.hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_TargetPath )       { %><th><bean:message bundle="databases" key="database.data_map.target_path.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IDataMap currentRecord = records.get ( counter );
                 %>
                <tr>
                    <td><html:link action='<%= "/data_map/edit?PrimaryKey=" + currentRecord.getRuleID() %>'>Edit</html:link></td>
                    <td><html:link action='<%= "/data_map/select?PrimaryKey=" + currentRecord.getRuleID() %>'>View</html:link></td>
                    <% if ( ShowColumn_RuleID )           { %><td><%= currentRecord.getRuleID()           %></td><% } %>
                    <% if ( ShowColumn_ApplicationName )  { %><td><%= currentRecord.getApplicationName()  %></td><% } %>
                    <% if ( ShowColumn_Hostname )         { %><td><%= currentRecord.getHostname()         %></td><% } %>
                    <% if ( ShowColumn_TargetPath )       { %><td><%= currentRecord.getTargetPath()       %></td><% } %>
                </tr>
        <%
            }
         %>
            </table>
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
            No results to display
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