<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequestAudit" %>
<%
    IHttpRequest displayRecord = (IHttpRequest) session.getAttribute ( IHttpRequest.SESSION_SELECT );
    List <IHttpRequestAudit> auditRecords = (List <IHttpRequestAudit>) session.getAttribute ( IHttpRequestAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.auth_type.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuthType() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.context_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getContextPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.method.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getMethod() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.path_info.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPathInfo() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.path_info_translated.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPathInfoTranslated() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.remote_user.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteUser() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.requested_session_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestedSessionID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.request_uri.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestUri() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.request_url.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestUrl() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.servlet_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getServletPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.user_principal.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getUserPrincipal() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.character_encoding.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCharacterEncoding() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.content_type.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getContentType() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.local_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getLocalAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.protocol.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getProtocol() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.remote_host.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRemoteHost() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.scheme.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getScheme() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.server_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getServerName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.request_time.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getRequestTime() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.process_time.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getProcessTime() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.content_length.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getContentLength() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.local_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getLocalPort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.server_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getServerPort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.is_processed.display_name" /></td>
                        <td><%= Boolean.toString ( displayRecord.getIsProcessed() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.was_successful.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getWasSuccessful() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_request.return_code.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getReturnCode() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/http_request_audit/http_request_audit_display.jsp" /></td>
    </tr>
</table>
