<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.apps.struts.v01_02.actions.BaseDispatchAction" %>
<%@ page import="com.bgi.esm.monitoring.splunk.splunk_send.server.data.orm.IHttpAttribute" %>
<%
    List <IHttpAttribute> records = (List <IHttpAttribute>) session.getAttribute ( IHttpAttribute.SESSION_BROWSE );
    boolean ShowColumn_RowID                 = true;
    boolean ShowColumn_RequestID             = true;
    boolean ShowColumn_AttributeName         = true;
    boolean ShowColumn_AttributePersistence  = true;

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
                             %><html:link action='<%= String.format ( "/http_attribute/view.do?page_size=%d&page_num=%d", page_size, page_num-1 ) %>'>Prev</html:link><%
                        }
                        else
                        {
                             %>&nbsp;<%
                        }
                     %>
                    </td>
                    <td><html:link action='<%= String.format ( "/http_attribute/view.do?page_size=%d&page_num=%d", page_size, page_num+1 ) %>'>Next</html:link></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <html:link action="/http_attribute/add">Add New Record</html:link>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <% if ( ShowColumn_RowID )                 { %><th><bean:message bundle="databases" key="database.http_attribute.row_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_RequestID )             { %><th><bean:message bundle="databases" key="database.http_attribute.request_id.display_name" /></th><% } %>
                    <% if ( ShowColumn_AttributeName )         { %><th><bean:message bundle="databases" key="database.http_attribute.attribute_name.display_name" /></th><% } %>
                    <% if ( ShowColumn_AttributePersistence )  { %><th><bean:message bundle="databases" key="database.http_attribute.attribute_persistence.display_name" /></th><% } %>
                </tr>
                <%
                    for ( int counter = 0; counter < records.size(); counter++ )
                    {
                        IHttpAttribute currentRecord = records.get ( counter );
                 %>
                <tr>
                    <td><html:link action='<%= "/http_attribute/edit?PrimaryKey=" + currentRecord.getRowID() %>'>Edit</html:link></td>
                    <td><html:link action='<%= "/http_attribute/select?PrimaryKey=" + currentRecord.getRowID() %>'>View</html:link></td>
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