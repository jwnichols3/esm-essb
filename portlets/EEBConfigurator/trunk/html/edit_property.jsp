<%@ include file="/html/init.jsp" %>
<%
    EebProperty property = (EebProperty) session.getAttribute ( Toolkit.SESSION_PROPERTY );
    String username      = request.getRemoteUser();
 %>
<table border="1" width="100%">
    <tr>
        <td>
            Editing Property: <%= property.getPropertyName() %>
            <br>
            <br>
            <html:form action="/edit_property_process" method="post">
                <table border="0">
                    <tr>
                        <td>New Value: </td>
                        <td><html:text name="property_form" property="propertyValue" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:submit>Update Property</html:submit></td>
                    </tr>
                </table>
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
