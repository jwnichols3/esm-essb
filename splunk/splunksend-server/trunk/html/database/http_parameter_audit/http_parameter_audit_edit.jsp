<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameterAudit" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterAuditFinder" %>
<%
    Map <String, String> param_map = BaseDispatchAction.retrieveHttpRequestParameters ( request );
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <center>
                <html:form action="/http_parameter_audit/edit_process" method="post" enctype="multipart/form-data" onsubmit="submitForm(this); return false;">
                    <jsp:include page="http_parameter_audit_form.jsp" />
                    <html:hidden name="http_parameter_audit_form" property="auditRecordID" value='<%= param_map.get ( "PrimaryKey" ) %>' />
                </html:form>
</center>
            </center>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
