<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAuditAudit" %>
<%
    IHttpCookieAudit displayRecord = (IHttpCookieAudit) session.getAttribute ( IHttpCookieAudit.SESSION_SELECT );
    List <IHttpCookieAuditAudit> auditRecords = (List <IHttpCookieAuditAudit>) session.getAttribute ( IHttpCookieAuditAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.row_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRowID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_comment.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookieComment() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.domain.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getDomain() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookieName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.cookie_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookiePersistence() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.is_secure.display_name" /></td>
                        <td><%= Boolean.toString ( displayRecord.getIsSecure() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.max_age.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getMaxAge() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.version.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getVersion() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_timestamp.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getAuditTimestamp() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_username.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditUsername() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie_audit.audit_is_deleted.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditIsDeleted() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
