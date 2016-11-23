<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap" %>
<%
    List <IDataMap> records = (List <IDataMap>) session.getAttribute ( IDataMap.SESSION_BROWSE );
    boolean ShowColumn_RuleID           = true;
    boolean ShowColumn_ApplicationName  = true;
    boolean ShowColumn_Hostname         = true;
    boolean ShowColumn_TargetPath       = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RuleID )           { %><th><bean:message bundle="databases" key="database.data_map.rule_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_ApplicationName )  { %><th><bean:message bundle="databases" key="database.data_map.application_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_Hostname )         { %><th><bean:message bundle="databases" key="database.data_map.hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_TargetPath )       { %><th><bean:message bundle="databases" key="database.data_map.target_path.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IDataMap currentRecord = (IDataMap) records.get ( counter );
                 %>
                <tr>
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