<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttributeAudit" %>
<%
    IHttpAttribute displayRecord = (IHttpAttribute) session.getAttribute ( IHttpAttribute.SESSION_SELECT );
    List <IHttpAttributeAudit> auditRecords = (List <IHttpAttributeAudit>) session.getAttribute ( IHttpAttributeAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_attribute.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_attribute.attribute_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAttributeName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_attribute.attribute_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getAttributePersistence() ) %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/http_attribute_audit/http_attribute_audit_display.jsp" /></td>
    </tr>
</table>
