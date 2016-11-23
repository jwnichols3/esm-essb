<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpParameter" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.finder.IHttpParameterFinder" %>
<table border="0" width="100%">
    <tr>
        <td>
            <center>
                <html:form action="/http_parameter/add_process" method="post" enctype="multipart/form-data" onsubmit="submitForm(this); return false;">
                    <jsp:include page="http_parameter_form.jsp" />
                </html:form>
            </center>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
