<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAuditAudit" %>
<%
    IDataMapAudit displayRecord = (IDataMapAudit) session.getAttribute ( IDataMapAudit.SESSION_SELECT );
    List <IDataMapAuditAudit> auditRecords = (List <IDataMapAuditAudit>) session.getAttribute ( IDataMapAuditAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.rule_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRuleID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.application_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getApplicationName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.target_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getTargetPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_timestamp.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDate ( displayRecord.getAuditTimestamp() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_username.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditUsername() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_remote_address.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditRemoteAddress() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_remote_port.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditRemotePort() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAuditHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map_audit.audit_is_deleted.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getAuditIsDeleted() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
