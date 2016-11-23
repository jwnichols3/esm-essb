<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeader" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpHeaderAudit" %>
<%
    IHttpHeader displayRecord = (IHttpHeader) session.getAttribute ( IHttpHeader.SESSION_SELECT );
    List <IHttpHeaderAudit> auditRecords = (List <IHttpHeaderAudit>) session.getAttribute ( IHttpHeaderAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_header.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_header.header_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getHeaderName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_header.header_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getHeaderPersistence() ) %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/http_header_audit/http_header_audit_display.jsp" /></td>
    </tr>
</table>
