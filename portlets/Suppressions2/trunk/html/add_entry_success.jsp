<%@ include file="/html/init.jsp"  %>
<%
    Long suppress_id = (Long) session.getAttribute ( "NewSuppressionID" );
 %>
    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <div id="addEntryMessage">
                    <b>Suppression #<%= suppress_id.toString() %> successfully created</b>
                    <br>
                    <br>
                </div>
            </td>
        </tr>
    </table>
