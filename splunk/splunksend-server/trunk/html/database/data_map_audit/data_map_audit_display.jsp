<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAudit" %>
<%
    List <IDataMapAudit> records = (List <IDataMapAudit>) session.getAttribute ( IDataMapAudit.SESSION_BROWSE );
    boolean ShowColumn_RuleID               = true;
    boolean ShowColumn_ApplicationName      = true;
    boolean ShowColumn_Hostname             = true;
    boolean ShowColumn_TargetPath           = true;
    boolean ShowColumn_AuditRecordID        = true;
    boolean ShowColumn_AuditTimestamp       = true;
    boolean ShowColumn_AuditUsername        = true;
    boolean ShowColumn_AuditRemoteAddress   = true;
    boolean ShowColumn_AuditRemotePort      = true;
    boolean ShowColumn_AuditHostname        = true;
    boolean ShowColumn_AuditIsDeleted       = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RuleID )               { %><th><bean:message bundle="databases" key="database.data_map_audit.rule_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_ApplicationName )      { %><th><bean:message bundle="databases" key="database.data_map_audit.application_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_Hostname )             { %><th><bean:message bundle="databases" key="database.data_map_audit.hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_TargetPath )           { %><th><bean:message bundle="databases" key="database.data_map_audit.target_path.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRecordID )        { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_record_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditTimestamp )       { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_timestamp.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditUsername )        { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_username.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )   { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemotePort )      { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditHostname )        { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )       { %><th><bean:message bundle="databases" key="database.data_map_audit.audit_is_deleted.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IDataMapAudit currentRecord = (IDataMapAudit) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RuleID )               { %><td><%= currentRecord.getRuleID()               %></td><% } %>
                    <% if ( ShowColumn_ApplicationName )      { %><td><%= currentRecord.getApplicationName()      %></td><% } %>
                    <% if ( ShowColumn_Hostname )             { %><td><%= currentRecord.getHostname()             %></td><% } %>
                    <% if ( ShowColumn_TargetPath )           { %><td><%= currentRecord.getTargetPath()           %></td><% } %>
                    <% if ( ShowColumn_AuditRecordID )        { %><td><%= currentRecord.getAuditRecordID()        %></td><% } %>
                    <% if ( ShowColumn_AuditTimestamp )                   { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getAuditTimestamp() )%></td><% } %>
                    <% if ( ShowColumn_AuditUsername )        { %><td><%= currentRecord.getAuditUsername()        %></td><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )   { %><td><%= currentRecord.getAuditRemoteAddress()   %></td><% } %>
                    <% if ( ShowColumn_AuditRemotePort )      { %><td><%= currentRecord.getAuditRemotePort()      %></td><% } %>
                    <% if ( ShowColumn_AuditHostname )        { %><td><%= currentRecord.getAuditHostname()        %></td><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )       { %><td><%= currentRecord.getAuditIsDeleted()       %></td><% } %>
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