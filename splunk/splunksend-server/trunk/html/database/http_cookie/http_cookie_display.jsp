<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpCookie" %>
<%
    List <IHttpCookie> records = (List <IHttpCookie>) session.getAttribute ( IHttpCookie.SESSION_BROWSE );
    boolean ShowColumn_RowID              = true;
    boolean ShowColumn_RequestID          = true;
    boolean ShowColumn_CookieComment      = true;
    boolean ShowColumn_Domain             = true;
    boolean ShowColumn_Path               = true;
    boolean ShowColumn_CookieName         = true;
    boolean ShowColumn_CookiePersistence  = true;
    boolean ShowColumn_IsSecure           = true;
    boolean ShowColumn_MaxAge             = true;
    boolean ShowColumn_Version            = true;

    if (( null != records ) && ( records.size() > 0 ))
    {
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <% if ( ShowColumn_RowID )              { %><th><bean:message bundle="databases" key="database.http_cookie.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )          { %><th><bean:message bundle="databases" key="database.http_cookie.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookieComment )      { %><th><bean:message bundle="databases" key="database.http_cookie.cookie_comment.display_name" /></th><% } %>
                    <% if ( ShowColumn_Domain )             { %><th><bean:message bundle="databases" key="database.http_cookie.domain.display_name" /></th><% } %>
                    <% if ( ShowColumn_Path )               { %><th><bean:message bundle="databases" key="database.http_cookie.path.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookieName )         { %><th><bean:message bundle="databases" key="database.http_cookie.cookie_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_CookiePersistence )  { %><th><bean:message bundle="databases" key="database.http_cookie.cookie_persistence.display_name" /></th><% } %>
                    <% if ( ShowColumn_IsSecure )           { %><th><bean:message bundle="databases" key="database.http_cookie.is_secure.display_name" /></th><% } %>
                    <% if ( ShowColumn_MaxAge )             { %><th><bean:message bundle="databases" key="database.http_cookie.max_age.display_name" /></th><% } %>
                    <% if ( ShowColumn_Version )            { %><th><bean:message bundle="databases" key="database.http_cookie.version.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpCookie currentRecord = (IHttpCookie) records.get ( counter );
                 %>
                <tr>
                    <% if ( ShowColumn_RowID )              { %><td><%= currentRecord.getRowID()              %></td><% } %>
                    <% if ( ShowColumn_RequestID )          { %><td><%= currentRecord.getRequestID()          %></td><% } %>
                    <% if ( ShowColumn_CookieComment )      { %><td><%= currentRecord.getCookieComment()      %></td><% } %>
                    <% if ( ShowColumn_Domain )             { %><td><%= currentRecord.getDomain()             %></td><% } %>
                    <% if ( ShowColumn_Path )               { %><td><%= currentRecord.getPath()               %></td><% } %>
                    <% if ( ShowColumn_CookieName )         { %><td><%= currentRecord.getCookieName()         %></td><% } %>
                    <% if ( ShowColumn_CookiePersistence )  { %><td><%= currentRecord.getCookiePersistence()  %></td><% } %>
                    <% if ( ShowColumn_IsSecure )           { %><td><%= currentRecord.getIsSecure()           %></td><% } %>
                    <% if ( ShowColumn_MaxAge )             { %><td><%= currentRecord.getMaxAge()             %></td><% } %>
                    <% if ( ShowColumn_Version )            { %><td><%= currentRecord.getVersion()            %></td><% } %>
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