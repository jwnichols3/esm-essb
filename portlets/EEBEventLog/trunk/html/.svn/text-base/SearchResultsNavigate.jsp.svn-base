<%@ include file="/html/init.jsp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List events = (List) session.getAttribute ( "EEBEventLog-Search-Results" );
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <center>
                <table border="1">
                    <tr>
                        <td><html:link action="/SearchResultsNavigate?method=pageDown">Previous Page</html:link></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><html:link action="/SearchResultsNavigate?method=pageUp">Next Page</html:link></td>
                    </tr>
                    <tr>
                        <th>Group</th>
                        <th>Application</th>
                        <th>Timestamp</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        for ( int counter = 0; counter < events.size(); counter++ )
                        {
                            EventsByGroup event = (EventsByGroup) events.get ( counter );
                     %>
                    <tr>
                        <td><%= event.getGroupName() %></td>
                        <td><%= event.getApplication() %></td>
                        <td><%= Toolkit.sdf.format ( event.getTimestamp().getTime() ) %></td>
                        <td><html:link action='<%= "/TraceOVOMessage?message_id=" + event.getMessageId() %>'>View</html:link></td>
                    </tr>
                    <%
                        }
                     %>
                </table>
            </center>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
