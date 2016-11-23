<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAudit" %>
<%
    List <IHttpRequestAudit> records = (List <IHttpRequestAudit>) session.getAttribute ( IHttpRequestAudit.SESSION_BROWSE );
    boolean ShowColumn_RequestID            = true;
    boolean ShowColumn_AuthType             = true;
    boolean ShowColumn_ContextPath          = true;
    boolean ShowColumn_Method               = true;
    boolean ShowColumn_PathInfo             = true;
    boolean ShowColumn_PathInfoTranslated   = true;
    boolean ShowColumn_RemoteUser           = true;
    boolean ShowColumn_RequestedSessionID   = true;
    boolean ShowColumn_RequestUri           = true;
    boolean ShowColumn_RequestUrl           = true;
    boolean ShowColumn_ServletPath          = true;
    boolean ShowColumn_UserPrincipal        = true;
    boolean ShowColumn_CharacterEncoding    = true;
    boolean ShowColumn_ContentType          = true;
    boolean ShowColumn_LocalAddress         = true;
    boolean ShowColumn_Protocol             = true;
    boolean ShowColumn_RemoteAddress        = true;
    boolean ShowColumn_RemoteHost           = true;
    boolean ShowColumn_Scheme               = true;
    boolean ShowColumn_ServerName           = true;
    boolean ShowColumn_RequestTime          = true;
    boolean ShowColumn_ProcessTime          = true;
    boolean ShowColumn_ContentLength        = true;
    boolean ShowColumn_LocalPort            = true;
    boolean ShowColumn_RemotePort           = true;
    boolean ShowColumn_ServerPort           = true;
    boolean ShowColumn_IsProcessed          = true;
    boolean ShowColumn_WasSuccessful        = true;
    boolean ShowColumn_ReturnCode           = true;
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
                    <% if ( ShowColumn_RequestID )            { %><th><bean:message bundle="databases" key="database.http_request_audit.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuthType )             { %><th><bean:message bundle="databases" key="database.http_request_audit.auth_type.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContextPath )          { %><th><bean:message bundle="databases" key="database.http_request_audit.context_path.display_name" /></th><% } %>
                    <% if ( ShowColumn_Method )               { %><th><bean:message bundle="databases" key="database.http_request_audit.method.display_name" /></th><% } %>
                    <% if ( ShowColumn_PathInfo )             { %><th><bean:message bundle="databases" key="database.http_request_audit.path_info.display_name" /></th><% } %>
                    <% if ( ShowColumn_PathInfoTranslated )   { %><th><bean:message bundle="databases" key="database.http_request_audit.path_info_translated.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteUser )           { %><th><bean:message bundle="databases" key="database.http_request_audit.remote_user.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestedSessionID )   { %><th><bean:message bundle="databases" key="database.http_request_audit.requested_session_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestUri )           { %><th><bean:message bundle="databases" key="database.http_request_audit.request_uri.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestUrl )           { %><th><bean:message bundle="databases" key="database.http_request_audit.request_url.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServletPath )          { %><th><bean:message bundle="databases" key="database.http_request_audit.servlet_path.display_name" /></th><% } %>
                    <% if ( ShowColumn_UserPrincipal )        { %><th><bean:message bundle="databases" key="database.http_request_audit.user_principal.display_name" /></th><% } %>
                    <% if ( ShowColumn_CharacterEncoding )    { %><th><bean:message bundle="databases" key="database.http_request_audit.character_encoding.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContentType )          { %><th><bean:message bundle="databases" key="database.http_request_audit.content_type.display_name" /></th><% } %>
                    <% if ( ShowColumn_LocalAddress )         { %><th><bean:message bundle="databases" key="database.http_request_audit.local_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_Protocol )             { %><th><bean:message bundle="databases" key="database.http_request_audit.protocol.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteAddress )        { %><th><bean:message bundle="databases" key="database.http_request_audit.remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteHost )           { %><th><bean:message bundle="databases" key="database.http_request_audit.remote_host.display_name" /></th><% } %>
                    <% if ( ShowColumn_Scheme )               { %><th><bean:message bundle="databases" key="database.http_request_audit.scheme.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServerName )           { %><th><bean:message bundle="databases" key="database.http_request_audit.server_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestTime )          { %><th><bean:message bundle="databases" key="database.http_request_audit.request_time.display_name" /></th><% } %>
                    <% if ( ShowColumn_ProcessTime )          { %><th><bean:message bundle="databases" key="database.http_request_audit.process_time.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContentLength )        { %><th><bean:message bundle="databases" key="database.http_request_audit.content_length.display_name" /></th><% } %>
                    <% if ( ShowColumn_LocalPort )            { %><th><bean:message bundle="databases" key="database.http_request_audit.local_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemotePort )           { %><th><bean:message bundle="databases" key="database.http_request_audit.remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServerPort )           { %><th><bean:message bundle="databases" key="database.http_request_audit.server_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_IsProcessed )          { %><th><bean:message bundle="databases" key="database.http_request_audit.is_processed.display_name" /></th><% } %>
                    <% if ( ShowColumn_WasSuccessful )        { %><th><bean:message bundle="databases" key="database.http_request_audit.was_successful.display_name" /></th><% } %>
                    <% if ( ShowColumn_ReturnCode )           { %><th><bean:message bundle="databases" key="database.http_request_audit.return_code.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRecordID )        { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_record_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditTimestamp )       { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_timestamp.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditUsername )        { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_username.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )   { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemotePort )      { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditHostname )        { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )       { %><th><bean:message bundle="databases" key="database.http_request_audit.audit_is_deleted.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpRequestAudit currentRecord = (IHttpRequestAudit) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RequestID )            { %><td><%= currentRecord.getRequestID()            %></td><% } %>
                    <% if ( ShowColumn_AuthType )             { %><td><%= currentRecord.getAuthType()             %></td><% } %>
                    <% if ( ShowColumn_ContextPath )          { %><td><%= currentRecord.getContextPath()          %></td><% } %>
                    <% if ( ShowColumn_Method )               { %><td><%= currentRecord.getMethod()               %></td><% } %>
                    <% if ( ShowColumn_PathInfo )             { %><td><%= currentRecord.getPathInfo()             %></td><% } %>
                    <% if ( ShowColumn_PathInfoTranslated )   { %><td><%= currentRecord.getPathInfoTranslated()   %></td><% } %>
                    <% if ( ShowColumn_RemoteUser )           { %><td><%= currentRecord.getRemoteUser()           %></td><% } %>
                    <% if ( ShowColumn_RequestedSessionID )   { %><td><%= currentRecord.getRequestedSessionID()   %></td><% } %>
                    <% if ( ShowColumn_RequestUri )           { %><td><%= currentRecord.getRequestUri()           %></td><% } %>
                    <% if ( ShowColumn_RequestUrl )           { %><td><%= currentRecord.getRequestUrl()           %></td><% } %>
                    <% if ( ShowColumn_ServletPath )          { %><td><%= currentRecord.getServletPath()          %></td><% } %>
                    <% if ( ShowColumn_UserPrincipal )        { %><td><%= currentRecord.getUserPrincipal()        %></td><% } %>
                    <% if ( ShowColumn_CharacterEncoding )    { %><td><%= currentRecord.getCharacterEncoding()    %></td><% } %>
                    <% if ( ShowColumn_ContentType )          { %><td><%= currentRecord.getContentType()          %></td><% } %>
                    <% if ( ShowColumn_LocalAddress )         { %><td><%= currentRecord.getLocalAddress()         %></td><% } %>
                    <% if ( ShowColumn_Protocol )             { %><td><%= currentRecord.getProtocol()             %></td><% } %>
                    <% if ( ShowColumn_RemoteAddress )        { %><td><%= currentRecord.getRemoteAddress()        %></td><% } %>
                    <% if ( ShowColumn_RemoteHost )           { %><td><%= currentRecord.getRemoteHost()           %></td><% } %>
                    <% if ( ShowColumn_Scheme )               { %><td><%= currentRecord.getScheme()               %></td><% } %>
                    <% if ( ShowColumn_ServerName )           { %><td><%= currentRecord.getServerName()           %></td><% } %>
                    <% if ( ShowColumn_RequestTime )                      { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getRequestTime() )%></td><% } %>
                    <% if ( ShowColumn_ProcessTime )                      { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getProcessTime() )%></td><% } %>
                    <% if ( ShowColumn_ContentLength )        { %><td><%= currentRecord.getContentLength()        %></td><% } %>
                    <% if ( ShowColumn_LocalPort )            { %><td><%= currentRecord.getLocalPort()            %></td><% } %>
                    <% if ( ShowColumn_RemotePort )           { %><td><%= currentRecord.getRemotePort()           %></td><% } %>
                    <% if ( ShowColumn_ServerPort )           { %><td><%= currentRecord.getServerPort()           %></td><% } %>
                    <% if ( ShowColumn_IsProcessed )          { %><td><%= currentRecord.getIsProcessed()          %></td><% } %>
                    <% if ( ShowColumn_WasSuccessful )        { %><td><%= currentRecord.getWasSuccessful()        %></td><% } %>
                    <% if ( ShowColumn_ReturnCode )           { %><td><%= currentRecord.getReturnCode()           %></td><% } %>
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