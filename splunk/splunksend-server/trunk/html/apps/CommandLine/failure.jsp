<%@ page import="java.util.List" %>
<%
    List <String> ErrorMessages = (List <String>) session.getAttribute ( "ERROR_MESSAGES" );

    for ( int counter = 0; counter < ErrorMessages.size(); counter++ )
    {
        %><%= ErrorMessages.get ( counter ) %><br><%
    }
 %>
