<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMap" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IDataMapAudit" %>
<%
    IDataMap displayRecord = (IDataMap) session.getAttribute ( IDataMap.SESSION_SELECT );
    List <IDataMapAudit> auditRecords = (List <IDataMapAudit>) session.getAttribute ( IDataMapAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map.application_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getApplicationName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map.hostname.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getHostname() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.data_map.target_path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getTargetPath() ) %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/data_map_audit/data_map_audit_display.jsp" /></td>
    </tr>
</table>
