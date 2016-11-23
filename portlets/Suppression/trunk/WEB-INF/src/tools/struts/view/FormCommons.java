package tools.struts.view;

import tools.databases.Table;

public class FormCommons
{
    public static String createFormHeader ( Table table )
    {
        StringBuilder contents = new StringBuilder();
        contents.append ( "<%@ include file=\"/html/portlet/real_estate_manager/init.jsp\" %>\n" );
        contents.append ( "<%\n" );
        contents.append ( "    String username     = request.getRemoteUser();\n" );
        contents.append ( "    java.util.Date date = new java.util.Date();\n" );
        contents.append ( "\n" );
        contents.append ( "    String width1       = \"25%\";\n" );
        contents.append ( "    String width2       = \"55%\";\n" );
        contents.append ( "    String width3       = \"*\";\n" );
        contents.append ( "    String scrollbar    = \"12\";\n" );
        contents.append ( " %>\n" );

        return contents.toString();
    }

    public static String createFormFooter ( Table table )
    {
        StringBuilder contents = new StringBuilder();

        contents.append ( "<%\n" );
        contents.append ( " %>\n" );

        return contents.toString();
    }
};
