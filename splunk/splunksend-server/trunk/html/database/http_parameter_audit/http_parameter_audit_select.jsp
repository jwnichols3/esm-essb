<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAuditAudit" %>
<%
    IHttpParameterAudit displayRecord = (IHttpParameterAudit) session.getAttribute ( IHttpParameterAudit.SESSION_SELECT );
    List <IHttpParameterAuditAudit> auditRecords = (List <IHttpParameterAuditAudit>) session.getAttribute ( IHttpParameterAuditAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.row_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRowID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.parameter_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getParameterName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.parameter_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getParameterPersistence() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_timestamp.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getAuditTimestamp() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_username.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditUsername() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter_audit.audit_is_deleted.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditIsDeleted() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
