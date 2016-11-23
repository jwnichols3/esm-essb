<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.platform.shared.value.*" %>
<table border="0" width="100%">
    <tr>
        <td>
        <%
            SuppressionRuleAudit auditRecord = (SuppressionRuleAudit) session.getAttribute ( "RetrievedAuditRecord" );
            if ( null != auditRecord )
            {
             %>
            <table border="1">
                <tr>
                    <td><b>Suppression ID:</b></td>
                    <td><%= auditRecord.getSuppressId %>, Ver. <%= auditRecord.getAuditVersionNum() %>/td>
                </tr>
                <tr>
                    <td><b>Suppression ID:</b></td>
                    <td><%= auditRecord.getSuppressId %>, Ver. <%= auditRecord.getAuditVersionNum() %>/td>
                </tr>
            </table>
            <%
            }
            else
            {
         %>
            Could not find audit record
        <%
            }
         %>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
