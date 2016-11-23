<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute" %>
<%
    List <IHttpAttribute> records = (List <IHttpAttribute>) session.getAttribute ( IHttpAttribute.SESSION_BROWSE );
    boolean ShowColumn_RowID                 = true;
    boolean ShowColumn_RequestID             = true;
    boolean ShowColumn_AttributeName         = true;
    boolean ShowColumn_AttributePersistence  = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RowID )                 { %><th><bean:message bundle="databases" key="database.http_attribute.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )             { %><th><bean:message bundle="databases" key="database.http_attribute.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AttributeName )         { %><th><bean:message bundle="databases" key="database.http_attribute.attribute_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_AttributePersistence )  { %><th><bean:message bundle="databases" key="database.http_attribute.attribute_persistence.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpAttribute currentRecord = (IHttpAttribute) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RowID )                 { %><td><%= currentRecord.getRowID()                 %></td><% } %>
                    <% if ( ShowColumn_RequestID )             { %><td><%= currentRecord.getRequestID()             %></td><% } %>
                    <% if ( ShowColumn_AttributeName )         { %><td><%= currentRecord.getAttributeName()         %></td><% } %>
                    <% if ( ShowColumn_AttributePersistence )  { %><td><%= currentRecord.getAttributePersistence()  %></td><% } %>
                </tr>
        <%
            }
         %>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
<%
    }
    else
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            No results to display
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
<%
    }
 %>