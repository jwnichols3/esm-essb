<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%
    IHttpParameter displayRecord = (IHttpParameter) session.getAttribute ( IHttpParameter.SESSION_SELECT );
    List <IHttpParameterAudit> auditRecords = (List <IHttpParameterAudit>) session.getAttribute ( IHttpParameterAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter.parameter_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getParameterName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_parameter.parameter_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getParameterPersistence() ) %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/http_parameter_audit/http_parameter_audit_display.jsp" /></td>
    </tr>
</table>
