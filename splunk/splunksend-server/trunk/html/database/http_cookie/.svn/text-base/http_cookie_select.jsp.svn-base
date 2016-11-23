<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAudit" %>
<%
    IHttpCookie displayRecord = (IHttpCookie) session.getAttribute ( IHttpCookie.SESSION_SELECT );
    List <IHttpCookieAudit> auditRecords = (List <IHttpCookieAudit>) session.getAttribute ( IHttpCookieAudit.SESSION_BROWSE );
 %>
<table width="100%" border="0">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.request_id.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getRequestID() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.cookie_comment.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookieComment() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.domain.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getDomain() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.path.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getPath() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.cookie_name.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookieName() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.cookie_persistence.display_name" /></td>
                        <td><%= CommonObject.formatHTMLDisplayString ( displayRecord.getCookiePersistence() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.is_secure.display_name" /></td>
                        <td><%= Boolean.toString ( displayRecord.getIsSecure() ) %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.max_age.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getMaxAge() %></td>
                    </tr>
                    <tr>
                        <td><bean:message bundle="databases" key="database.http_cookie.version.display_name" /></td>
                        <td><%= CommonObject.formatdisplayRecord.getVersion() %></td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
<table width="100%" border="0">
    <tr>
        <td><jsp:include page="/html/database/http_cookie_audit/http_cookie_audit_display.jsp" /></td>
    </tr>
</table>
