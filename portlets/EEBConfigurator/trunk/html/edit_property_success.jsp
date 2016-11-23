<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
    BackEndFacade bef   = new BackEndFacade();
    List properties     = bef.getAllEebProperties();
 %>
<table border="1" width="100%">
    <tr>
        <td>
            EEB Configurator
            <br>
            <br>
            Property successfully updated.
            <br>
            <br>
            <%
                if ( null != properties )
                {
                    Iterator i = properties.iterator();

             %>
            <table border="1">
                <tr>
                    <th>&nbsp;</th>
                    <th>Property Name</th>
                    <th>Property Value</th>
                    <th>Description</th>
                </tr>
                <%
                    while ( i.hasNext() )
                    {
                        EebProperty property = (EebProperty) i.next();
                        String property_key  = property.getPropertyName() + ".description";
                         %>
                <tr>
                    <td><html:link action='<%= "/edit_property.do?propertyName=" + property.getPropertyName() %>'>Edit</html:link></td>
                    <td><%= property.getPropertyName() %></td>
                    <td><%= property.getPropertyValue() %></td>
                    <td>&nbsp;</td>
                </tr>
                        <%
                    }
                 %>
            </table>
            <%
                }
                else
                {
             %><center><b>No EEB properties to configure</b></center><%
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
