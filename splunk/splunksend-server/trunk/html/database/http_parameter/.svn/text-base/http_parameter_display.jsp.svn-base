<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter" %>
<%
    List <IHttpParameter> records = (List <IHttpParameter>) session.getAttribute ( IHttpParameter.SESSION_BROWSE );
    boolean ShowColumn_RowID                 = true;
    boolean ShowColumn_RequestID             = true;
    boolean ShowColumn_ParameterName         = true;
    boolean ShowColumn_ParameterPersistence  = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RowID )                 { %><th><bean:message bundle="databases" key="database.http_parameter.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )             { %><th><bean:message bundle="databases" key="database.http_parameter.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_ParameterName )         { %><th><bean:message bundle="databases" key="database.http_parameter.parameter_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_ParameterPersistence )  { %><th><bean:message bundle="databases" key="database.http_parameter.parameter_persistence.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpParameter currentRecord = (IHttpParameter) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RowID )                 { %><td><%= currentRecord.getRowID()                 %></td><% } %>
                    <% if ( ShowColumn_RequestID )             { %><td><%= currentRecord.getRequestID()             %></td><% } %>
                    <% if ( ShowColumn_ParameterName )         { %><td><%= currentRecord.getParameterName()         %></td><% } %>
                    <% if ( ShowColumn_ParameterPersistence )  { %><td><%= currentRecord.getParameterPersistence()  %></td><% } %>
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