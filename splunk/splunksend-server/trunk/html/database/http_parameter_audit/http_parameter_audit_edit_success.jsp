<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%
    List <IHttpParameterAudit> records = (List <IHttpParameterAudit>) session.getAttribute ( IHttpParameterAudit.SESSION_BROWSE );
    boolean ShowColumn_RowID                 = true;
    boolean ShowColumn_RequestID             = true;
    boolean ShowColumn_ParameterName         = true;
    boolean ShowColumn_ParameterPersistence  = true;
    boolean ShowColumn_AuditRecordID         = true;
    boolean ShowColumn_AuditTimestamp        = true;
    boolean ShowColumn_AuditUsername         = true;
    boolean ShowColumn_AuditRemoteAddress    = true;
    boolean ShowColumn_AuditRemotePort       = true;
    boolean ShowColumn_AuditHostname         = true;
    boolean ShowColumn_AuditIsDeleted        = true;

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
Successfully edited record
<br>
<br>
<table border="0" width="100%">
    <tr>
        <td>
            <table width="100%" border="0">
                <tr>
                    <td>
                    <%
                        if ( page_num > 0 )
                        {
                             %><html:link action='<%= String.format ( "/http_parameter_audit/view.do?page_size=%d&page_num=%d", page_size, page_num-1 ) %>'>Prev</html:link><%
                        }
                        else
                        {
                             %>&nbsp;<%
                        }
                     %>
                    </td>
                    <td><html:link action='<%= String.format ( "/http_parameter_audit/view.do?page_size=%d&page_num=%d", page_size, page_num+1 ) %>'>Next</html:link></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/http_parameter_audit/add">Add New Record</html:link>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <% if ( ShowColumn_RowID )                 { %><th><bean:message bundle="databases" key="database.http_parameter_audit.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )             { %><th><bean:message bundle="databases" key="database.http_parameter_audit.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_ParameterName )         { %><th><bean:message bundle="databases" key="database.http_parameter_audit.parameter_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_ParameterPersistence )  { %><th><bean:message bundle="databases" key="database.http_parameter_audit.parameter_persistence.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRecordID )         { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_record_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditTimestamp )        { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_timestamp.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditUsername )         { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_username.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )    { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemotePort )       { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditHostname )         { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )        { %><th><bean:message bundle="databases" key="database.http_parameter_audit.audit_is_deleted.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpParameterAudit currentRecord = records.get ( counter );
                 %>
                <tr>
                    <td><html:link action='<%= "/http_parameter_audit/edit?PrimaryKey=" + currentRecord.getAuditRecordID() %>'>Edit</html:link></td>
                    <td><html:link action='<%= "/http_parameter_audit/select?PrimaryKey=" + currentRecord.getAuditRecordID() %>'>View</html:link></td>
                    <% if ( ShowColumn_RowID )                 { %><td><%= currentRecord.getRowID()                 %></td><% } %>
                    <% if ( ShowColumn_RequestID )             { %><td><%= currentRecord.getRequestID()             %></td><% } %>
                    <% if ( ShowColumn_ParameterName )         { %><td><%= currentRecord.getParameterName()         %></td><% } %>
                    <% if ( ShowColumn_ParameterPersistence )  { %><td><%= currentRecord.getParameterPersistence()  %></td><% } %>
                    <% if ( ShowColumn_AuditRecordID )         { %><td><%= currentRecord.getAuditRecordID()         %></td><% } %>
                    <% if ( ShowColumn_AuditTimestamp )                    { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getAuditTimestamp() )%></td><% } %>
                    <% if ( ShowColumn_AuditUsername )         { %><td><%= currentRecord.getAuditUsername()         %></td><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )    { %><td><%= currentRecord.getAuditRemoteAddress()    %></td><% } %>
                    <% if ( ShowColumn_AuditRemotePort )       { %><td><%= currentRecord.getAuditRemotePort()       %></td><% } %>
                    <% if ( ShowColumn_AuditHostname )         { %><td><%= currentRecord.getAuditHostname()         %></td><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )        { %><td><%= currentRecord.getAuditIsDeleted()        %></td><% } %>
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