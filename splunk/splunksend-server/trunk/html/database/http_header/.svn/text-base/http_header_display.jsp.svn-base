<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader" %>
<%
    List <IHttpHeader> records = (List <IHttpHeader>) session.getAttribute ( IHttpHeader.SESSION_BROWSE );
    boolean ShowColumn_RowID              = true;
    boolean ShowColumn_RequestID          = true;
    boolean ShowColumn_HeaderName         = true;
    boolean ShowColumn_HeaderPersistence  = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RowID )              { %><th><bean:message bundle="databases" key="database.http_header.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )          { %><th><bean:message bundle="databases" key="database.http_header.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_HeaderName )         { %><th><bean:message bundle="databases" key="database.http_header.header_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_HeaderPersistence )  { %><th><bean:message bundle="databases" key="database.http_header.header_persistence.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpHeader currentRecord = (IHttpHeader) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RowID )              { %><td><%= currentRecord.getRowID()              %></td><% } %>
                    <% if ( ShowColumn_RequestID )          { %><td><%= currentRecord.getRequestID()          %></td><% } %>
                    <% if ( ShowColumn_HeaderName )         { %><td><%= currentRecord.getHeaderName()         %></td><% } %>
                    <% if ( ShowColumn_HeaderPersistence )  { %><td><%= currentRecord.getHeaderPersistence()  %></td><% } %>
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