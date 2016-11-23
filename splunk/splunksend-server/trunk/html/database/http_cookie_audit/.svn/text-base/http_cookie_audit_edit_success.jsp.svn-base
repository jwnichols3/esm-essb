<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookieAudit" %>
<%
    List <IHttpCookieAudit> records = (List <IHttpCookieAudit>) session.getAttribute ( IHttpCookieAudit.SESSION_BROWSE );
    boolean ShowColumn_RowID                = true;
    boolean ShowColumn_RequestID            = true;
    boolean ShowColumn_CookieComment        = true;
    boolean ShowColumn_Domain               = true;
    boolean ShowColumn_Path                 = true;
    boolean ShowColumn_CookieName           = true;
    boolean ShowColumn_CookiePersistence    = true;
    boolean ShowColumn_IsSecure             = true;
    boolean ShowColumn_MaxAge               = true;
    boolean ShowColumn_Version              = true;
    boolean ShowColumn_AuditRecordID        = true;
    boolean ShowColumn_AuditTimestamp       = true;
    boolean ShowColumn_AuditUsername        = true;
    boolean ShowColumn_AuditRemoteAddress   = true;
    boolean ShowColumn_AuditRemotePort      = true;
    boolean ShowColumn_AuditHostname        = true;
    boolean ShowColumn_AuditIsDeleted       = true;

    Map <String, String> params = BaseDispatchAction.retrieveHttpRequestParameters ( request );

    String strPageNum  = params.get ( "page_num" );
    String strPageSize = params.get ( "page_size" ); 
    int page_num       = 0;
    int page_size      = 25;

    try
    {
        if ( null != strPageNum  ) page_num  = Integer.parseInt ( strPageNum  );
        if ( null != strPageSize ) page_size = Integer.parseInt ( strPageSize );
    }
    catch ( RuntimeException exception )
    {
        page_num  = 0;
        page_size = 25;
    }

    if ( null != records )
    {
 %>
Successfully edited record
<br>
<br>
<table border="0" width="100%">
    <tr>
        <td>
            <table width="100%" border="0">
                <tr>
                    <td>
                    <%
                        if ( page_num > 0 )
                        {
                             %><html:link action='<%= String.format ( "/http_cookie_audit/view.do?page_size=%d&page_num=%d", page_size, page_num-1 ) %>'>Prev</html:link><%
                        }
                        else
                        {
                             %>&nbsp;<%
                        }
                     %>
                    </td>
                    <td><html:link action='<%= String.format ( "/http_cookie_audit/view.do?page_size=%d&page_num=%d", page_size, page_num+1 ) %>'>Next</html:link></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/http_cookie_audit/add">Add New Record</html:link>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <% if ( ShowColumn_RowID )                { %><th><bean:message bundle="databases" key="database.http_cookie_audit.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )            { %><th><bean:message bundle="databases" key="database.http_cookie_audit.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookieComment )        { %><th><bean:message bundle="databases" key="database.http_cookie_audit.cookie_comment.display_name" /></th><% } %>
                    <% if ( ShowColumn_Domain )               { %><th><bean:message bundle="databases" key="database.http_cookie_audit.domain.display_name" /></th><% } %>
                    <% if ( ShowColumn_Path )                 { %><th><bean:message bundle="databases" key="database.http_cookie_audit.path.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookieName )           { %><th><bean:message bundle="databases" key="database.http_cookie_audit.cookie_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookiePersistence )    { %><th><bean:message bundle="databases" key="database.http_cookie_audit.cookie_persistence.display_name" /></th><% } %>
                    <% if ( ShowColumn_IsSecure )             { %><th><bean:message bundle="databases" key="database.http_cookie_audit.is_secure.display_name" /></th><% } %>
                    <% if ( ShowColumn_MaxAge )               { %><th><bean:message bundle="databases" key="database.http_cookie_audit.max_age.display_name" /></th><% } %>
                    <% if ( ShowColumn_Version )              { %><th><bean:message bundle="databases" key="database.http_cookie_audit.version.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRecordID )        { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_record_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditTimestamp )       { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_timestamp.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditUsername )        { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_username.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )   { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditRemotePort )      { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditHostname )        { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_hostname.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )       { %><th><bean:message bundle="databases" key="database.http_cookie_audit.audit_is_deleted.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpCookieAudit currentRecord = records.get ( counter );
                 %>
                <tr>
                    <td><html:link action='<%= "/http_cookie_audit/edit?PrimaryKey=" + currentRecord.getAuditRecordID() %>'>Edit</html:link></td>
                    <td><html:link action='<%= "/http_cookie_audit/select?PrimaryKey=" + currentRecord.getAuditRecordID() %>'>View</html:link></td>
                    <% if ( ShowColumn_RowID )                { %><td><%= currentRecord.getRowID()                %></td><% } %>
                    <% if ( ShowColumn_RequestID )            { %><td><%= currentRecord.getRequestID()            %></td><% } %>
                    <% if ( ShowColumn_CookieComment )        { %><td><%= currentRecord.getCookieComment()        %></td><% } %>
                    <% if ( ShowColumn_Domain )               { %><td><%= currentRecord.getDomain()               %></td><% } %>
                    <% if ( ShowColumn_Path )                 { %><td><%= currentRecord.getPath()                 %></td><% } %>
                    <% if ( ShowColumn_CookieName )           { %><td><%= currentRecord.getCookieName()           %></td><% } %>
                    <% if ( ShowColumn_CookiePersistence )    { %><td><%= currentRecord.getCookiePersistence()    %></td><% } %>
                    <% if ( ShowColumn_IsSecure )             { %><td><%= currentRecord.getIsSecure()             %></td><% } %>
                    <% if ( ShowColumn_MaxAge )               { %><td><%= currentRecord.getMaxAge()               %></td><% } %>
                    <% if ( ShowColumn_Version )              { %><td><%= currentRecord.getVersion()              %></td><% } %>
                    <% if ( ShowColumn_AuditRecordID )        { %><td><%= currentRecord.getAuditRecordID()        %></td><% } %>
                    <% if ( ShowColumn_AuditTimestamp )                   { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getAuditTimestamp() )%></td><% } %>
                    <% if ( ShowColumn_AuditUsername )        { %><td><%= currentRecord.getAuditUsername()        %></td><% } %>
                    <% if ( ShowColumn_AuditRemoteAddress )   { %><td><%= currentRecord.getAuditRemoteAddress()   %></td><% } %>
                    <% if ( ShowColumn_AuditRemotePort )      { %><td><%= currentRecord.getAuditRemotePort()      %></td><% } %>
                    <% if ( ShowColumn_AuditHostname )        { %><td><%= currentRecord.getAuditHostname()        %></td><% } %>
                    <% if ( ShowColumn_AuditIsDeleted )       { %><td><%= currentRecord.getAuditIsDeleted()       %></td><% } %>
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