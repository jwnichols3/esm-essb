<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.CommonObject" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpRequest" %>
<%
    List <IHttpRequest> records = (List <IHttpRequest>) session.getAttribute ( IHttpRequest.SESSION_BROWSE );
    boolean ShowColumn_RequestID            = true;
    boolean ShowColumn_AuthType             = true;
    boolean ShowColumn_ContextPath          = true;
    boolean ShowColumn_Method               = true;
    boolean ShowColumn_PathInfo             = true;
    boolean ShowColumn_PathInfoTranslated   = true;
    boolean ShowColumn_RemoteUser           = true;
    boolean ShowColumn_RequestedSessionID   = true;
    boolean ShowColumn_RequestUri           = true;
    boolean ShowColumn_RequestUrl           = true;
    boolean ShowColumn_ServletPath          = true;
    boolean ShowColumn_UserPrincipal        = true;
    boolean ShowColumn_CharacterEncoding    = true;
    boolean ShowColumn_ContentType          = true;
    boolean ShowColumn_LocalAddress         = true;
    boolean ShowColumn_Protocol             = true;
    boolean ShowColumn_RemoteAddress        = true;
    boolean ShowColumn_RemoteHost           = true;
    boolean ShowColumn_Scheme               = true;
    boolean ShowColumn_ServerName           = true;
    boolean ShowColumn_RequestTime          = true;
    boolean ShowColumn_ProcessTime          = true;
    boolean ShowColumn_ContentLength        = true;
    boolean ShowColumn_LocalPort            = true;
    boolean ShowColumn_RemotePort           = true;
    boolean ShowColumn_ServerPort           = true;
    boolean ShowColumn_IsProcessed          = true;
    boolean ShowColumn_WasSuccessful        = true;
    boolean ShowColumn_ReturnCode           = true;

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
Successfully added record
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
                             %><html:link action='<%= String.format ( "/http_request/view.do?page_size=%d&page_num=%d", page_size, page_num-1 ) %>'>Prev</html:link><%
                        }
                        else
                        {
                             %>&nbsp;<%
                        }
                     %>
                    </td>
                    <td><html:link action='<%= String.format ( "/http_request/view.do?page_size=%d&page_num=%d", page_size, page_num+1 ) %>'>Next</html:link></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/http_request/add">Add New Record</html:link>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <% if ( ShowColumn_RequestID )            { %><th><bean:message bundle="databases" key="database.http_request.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AuthType )             { %><th><bean:message bundle="databases" key="database.http_request.auth_type.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContextPath )          { %><th><bean:message bundle="databases" key="database.http_request.context_path.display_name" /></th><% } %>
                    <% if ( ShowColumn_Method )               { %><th><bean:message bundle="databases" key="database.http_request.method.display_name" /></th><% } %>
                    <% if ( ShowColumn_PathInfo )             { %><th><bean:message bundle="databases" key="database.http_request.path_info.display_name" /></th><% } %>
                    <% if ( ShowColumn_PathInfoTranslated )   { %><th><bean:message bundle="databases" key="database.http_request.path_info_translated.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteUser )           { %><th><bean:message bundle="databases" key="database.http_request.remote_user.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestedSessionID )   { %><th><bean:message bundle="databases" key="database.http_request.requested_session_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestUri )           { %><th><bean:message bundle="databases" key="database.http_request.request_uri.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestUrl )           { %><th><bean:message bundle="databases" key="database.http_request.request_url.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServletPath )          { %><th><bean:message bundle="databases" key="database.http_request.servlet_path.display_name" /></th><% } %>
                    <% if ( ShowColumn_UserPrincipal )        { %><th><bean:message bundle="databases" key="database.http_request.user_principal.display_name" /></th><% } %>
                    <% if ( ShowColumn_CharacterEncoding )    { %><th><bean:message bundle="databases" key="database.http_request.character_encoding.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContentType )          { %><th><bean:message bundle="databases" key="database.http_request.content_type.display_name" /></th><% } %>
                    <% if ( ShowColumn_LocalAddress )         { %><th><bean:message bundle="databases" key="database.http_request.local_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_Protocol )             { %><th><bean:message bundle="databases" key="database.http_request.protocol.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteAddress )        { %><th><bean:message bundle="databases" key="database.http_request.remote_address.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemoteHost )           { %><th><bean:message bundle="databases" key="database.http_request.remote_host.display_name" /></th><% } %>
                    <% if ( ShowColumn_Scheme )               { %><th><bean:message bundle="databases" key="database.http_request.scheme.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServerName )           { %><th><bean:message bundle="databases" key="database.http_request.server_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestTime )          { %><th><bean:message bundle="databases" key="database.http_request.request_time.display_name" /></th><% } %>
                    <% if ( ShowColumn_ProcessTime )          { %><th><bean:message bundle="databases" key="database.http_request.process_time.display_name" /></th><% } %>
                    <% if ( ShowColumn_ContentLength )        { %><th><bean:message bundle="databases" key="database.http_request.content_length.display_name" /></th><% } %>
                    <% if ( ShowColumn_LocalPort )            { %><th><bean:message bundle="databases" key="database.http_request.local_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_RemotePort )           { %><th><bean:message bundle="databases" key="database.http_request.remote_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_ServerPort )           { %><th><bean:message bundle="databases" key="database.http_request.server_port.display_name" /></th><% } %>
                    <% if ( ShowColumn_IsProcessed )          { %><th><bean:message bundle="databases" key="database.http_request.is_processed.display_name" /></th><% } %>
                    <% if ( ShowColumn_WasSuccessful )        { %><th><bean:message bundle="databases" key="database.http_request.was_successful.display_name" /></th><% } %>
                    <% if ( ShowColumn_ReturnCode )           { %><th><bean:message bundle="databases" key="database.http_request.return_code.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpRequest currentRecord = records.get ( counter );
                 %>
                <tr>
                    <td><html:link action='<%= "/http_request/edit?PrimaryKey=" + currentRecord.getRequestID() %>'>Edit</html:link></td>
                    <td><html:link action='<%= "/http_request/select?PrimaryKey=" + currentRecord.getRequestID() %>'>View</html:link></td>
                    <% if ( ShowColumn_RequestID )            { %><td><%= currentRecord.getRequestID()            %></td><% } %>
                    <% if ( ShowColumn_AuthType )             { %><td><%= currentRecord.getAuthType()             %></td><% } %>
                    <% if ( ShowColumn_ContextPath )          { %><td><%= currentRecord.getContextPath()          %></td><% } %>
                    <% if ( ShowColumn_Method )               { %><td><%= currentRecord.getMethod()               %></td><% } %>
                    <% if ( ShowColumn_PathInfo )             { %><td><%= currentRecord.getPathInfo()             %></td><% } %>
                    <% if ( ShowColumn_PathInfoTranslated )   { %><td><%= currentRecord.getPathInfoTranslated()   %></td><% } %>
                    <% if ( ShowColumn_RemoteUser )           { %><td><%= currentRecord.getRemoteUser()           %></td><% } %>
                    <% if ( ShowColumn_RequestedSessionID )   { %><td><%= currentRecord.getRequestedSessionID()   %></td><% } %>
                    <% if ( ShowColumn_RequestUri )           { %><td><%= currentRecord.getRequestUri()           %></td><% } %>
                    <% if ( ShowColumn_RequestUrl )           { %><td><%= currentRecord.getRequestUrl()           %></td><% } %>
                    <% if ( ShowColumn_ServletPath )          { %><td><%= currentRecord.getServletPath()          %></td><% } %>
                    <% if ( ShowColumn_UserPrincipal )        { %><td><%= currentRecord.getUserPrincipal()        %></td><% } %>
                    <% if ( ShowColumn_CharacterEncoding )    { %><td><%= currentRecord.getCharacterEncoding()    %></td><% } %>
                    <% if ( ShowColumn_ContentType )          { %><td><%= currentRecord.getContentType()          %></td><% } %>
                    <% if ( ShowColumn_LocalAddress )         { %><td><%= currentRecord.getLocalAddress()         %></td><% } %>
                    <% if ( ShowColumn_Protocol )             { %><td><%= currentRecord.getProtocol()             %></td><% } %>
                    <% if ( ShowColumn_RemoteAddress )        { %><td><%= currentRecord.getRemoteAddress()        %></td><% } %>
                    <% if ( ShowColumn_RemoteHost )           { %><td><%= currentRecord.getRemoteHost()           %></td><% } %>
                    <% if ( ShowColumn_Scheme )               { %><td><%= currentRecord.getScheme()               %></td><% } %>
                    <% if ( ShowColumn_ServerName )           { %><td><%= currentRecord.getServerName()           %></td><% } %>
                    <% if ( ShowColumn_RequestTime )                      { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getRequestTime() )%></td><% } %>
                    <% if ( ShowColumn_ProcessTime )                      { %><td><%= CommonObject.formatHTMLDate ( currentRecord.getProcessTime() )%></td><% } %>
                    <% if ( ShowColumn_ContentLength )        { %><td><%= currentRecord.getContentLength()        %></td><% } %>
                    <% if ( ShowColumn_LocalPort )            { %><td><%= currentRecord.getLocalPort()            %></td><% } %>
                    <% if ( ShowColumn_RemotePort )           { %><td><%= currentRecord.getRemotePort()           %></td><% } %>
                    <% if ( ShowColumn_ServerPort )           { %><td><%= currentRecord.getServerPort()           %></td><% } %>
                    <% if ( ShowColumn_IsProcessed )          { %><td><%= currentRecord.getIsProcessed()          %></td><% } %>
                    <% if ( ShowColumn_WasSuccessful )        { %><td><%= currentRecord.getWasSuccessful()        %></td><% } %>
                    <% if ( ShowColumn_ReturnCode )           { %><td><%= currentRecord.getReturnCode()           %></td><% } %>
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