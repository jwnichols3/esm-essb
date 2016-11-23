<%@ include file="/html/init.jsp"  %>
<%
    Integer suppress_id = (Integer) session.getAttribute ( "NewSuppressionID" );
 %>
    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                    Function add was successful
            </td>
        </tr>
    </table>
