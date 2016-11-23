<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width1       = "25%";
    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
    String temp_string  = null;

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    //  Customize the following table to store your portlet settings
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
 %>
<table border="0">
    <tr>
        <td>
            <html:form action="/edit/edit_settings_process" method="post" onsubmit="submitForm(this); return false;">
                <font class="portlet-font" style="font-size: x-small;">
                    <b>Portlet Settings</b>
                    <br />
                    <table border="1">
                        <tr>
                            <td><font class="portlet-font" style="font-size: x-small;">Test String field:</font></td>
                            <td>
                                <html:text name="edit_settings_form" property="testString" size="30" />
                            </td>
                        </tr>
                    </table>
                </font>
                <html:submit>Update Subject</html:submit>
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/edit/nav.jsp"/>
        </td>
    </tr>
</table>
