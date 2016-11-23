<%@ include file="/html/init.jsp" %>
<tiles:useAttribute id="tilesPortletContent" name="portlet_content" classname="java.lang.String" ignore="true" />
    <table border="0" cellpadding="8" cellspacing="0" width="100%">
        <tr>
            <td>
                <jsp:include page="<%= \"/html\" + tilesPortletContent %>" flush="true" />
            </td>
        </tr>
        <tr>
            <td>
                <hr />
                <table border="0" width="75%">
                    <tr>
                        <td><html:link action="/view">Home</html:link></td>
                        <td><html:link action="/add_entry">Add Entry</html:link></td>
                        <td><html:link action="/current">Current</html:link></td>
                        <td><html:link action="/historical">Historical</html:link></td>
                        <td><html:link action="/help">Help</html:link></td>
                        <td><html:link action="/edit_entry?suppress_id=9950">Test Edit</html:link></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
