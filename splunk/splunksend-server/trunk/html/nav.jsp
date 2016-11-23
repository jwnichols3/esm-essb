<%@ include file="/html/init.jsp" %>
<table border="1">
    <tr>
        <td><html:link action="/view"><bean:message bundle="databases" key="home.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="data_map/view"><bean:message bundle="databases" key="database.data_map.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="http_attribute/view"><bean:message bundle="databases" key="database.http_attribute.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="http_cookie/view"><bean:message bundle="databases" key="database.http_cookie.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="http_header/view"><bean:message bundle="databases" key="database.http_header.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="http_parameter/view"><bean:message bundle="databases" key="database.http_parameter.display_name" /></html:link></td>
    </tr>
    <tr>
        <td><html:link action="http_request/view"><bean:message bundle="databases" key="database.http_request.display_name" /></html:link></td>
    </tr>
</table>
