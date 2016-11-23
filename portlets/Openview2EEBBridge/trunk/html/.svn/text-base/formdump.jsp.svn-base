<%@ page import="java.io.*,java.util.*" %>
<%
    String temp_string    = null;
    Enumeration e         = request.getParameterNames();
    StringBuilder message = new StringBuilder();

    message.append ( "<table border='1'>\n" );
    while ( e.hasMoreElements() )
    {
        temp_string = e.nextElement().toString();

        message.append ( "    <tr>\n" );
        message.append ( "        <td>" );
        message.append ( temp_string );
        message.append ( "</td>\n" );
        message.append ( "        <td>" );
        message.append ( request.getParameter ( temp_string ) );
        message.append ( "</td>\n" );
        message.append ( "    </tr>\n" );
    }

    message.append ( "</table>\n" );


    FileOutputStream outfile = new FileOutputStream ( "c:\\test\\formdump.out" );
        outfile.write ( message.toString().getBytes() );
    outfile.close();
 %>
