<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAuditAudit" %>
<%
    IHttpRequestAudit displayRecord = (IHttpRequestAudit) session.getAttribute ( IHttpRequestAudit.SESSION_SELECT );
    List <IHttpRequestAuditAudit> auditRecords = (List <IHttpRequestAuditAudit>) session.getAttribute ( IHttpRequestAuditAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.auth_type.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuthType() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.context_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getContextPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.method.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getMethod() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.path_info.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPathInfo() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.path_info_translated.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPathInfoTranslated() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.remote_user.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteUser() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.requested_session_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestedSessionID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.request_uri.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestUri() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.request_url.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestUrl() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.servlet_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getServletPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.user_principal.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getUserPrincipal() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.character_encoding.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCharacterEncoding() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.content_type.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getContentType() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.local_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getLocalAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.protocol.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getProtocol() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.remote_host.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteHost() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.scheme.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getScheme() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.server_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getServerName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.request_time.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getRequestTime() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.process_time.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getProcessTime() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.content_length.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getContentLength() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.local_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getLocalPort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.server_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getServerPort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.is_processed.display_name" /></td>
                        <td><%= Boolean.toString ( displayRecord.getIsProcessed() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.was_successful.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getWasSuccessful() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.return_code.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getReturnCode() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_timestamp.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getAuditTimestamp() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_username.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditUsername() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request_audit.audit_is_deleted.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditIsDeleted() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
