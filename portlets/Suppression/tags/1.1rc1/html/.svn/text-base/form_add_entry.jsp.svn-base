<%@ page import="com.bgi.esm.portlets.Suppression.Toolkit" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%
    System.out.println ( "Executing add entry request..." );
    String startChoice    = request.getParameter ( "startChoice"     );
    String supStartYear   = request.getParameter ( "supStartYear"    );
    String supStartMonth  = request.getParameter ( "supStartMonth"   );
    String supStartDate   = request.getParameter ( "supStartDate"    );
    String supStartHour   = request.getParameter ( "supStartHour"    );
    String supStartMinute = request.getParameter ( "supStartMinute"  );
    String supStartAmpm   = request.getParameter ( "supStartAmpm"    );
    String endChoice      = request.getParameter ( "endChoice"       );
    String supEndYear     = request.getParameter ( "supEndYear"      );
    String supEndMonth    = request.getParameter ( "supEndMonth"     );
    String supEndDate     = request.getParameter ( "supEndDate"      );
    String supEndHour     = request.getParameter ( "supEndHour"      );
    String supEndMinute   = request.getParameter ( "supEndMinute"    );
    String supEndAmpm     = request.getParameter ( "supEndAmpm"      );
    String group          = request.getParameter ( "group"           );
    String node           = request.getParameter ( "node"            );
    String description    = request.getParameter ( "description"     );
    String numMinPrior    = request.getParameter ( "numMinutesPrior" );
    String email          = request.getParameter ( "email"           );
    String dbServer       = request.getParameter ( "dbServer"        );
    String dbServerMsg    = request.getParameter ( "dbServerMsg"     );
    String username       = request.getParameter ( "username"        );

    System.out.println ( "\tStart Choice:  " + startChoice    );
    System.out.println ( "\tStart Year:    " + supStartYear   );
    System.out.println ( "\tStart Month:   " + supStartMonth  );
    System.out.println ( "\tStart Date:    " + supStartDate   );
    System.out.println ( "\tStart Hour:    " + supStartHour   );
    System.out.println ( "\tStart Minute:  " + supStartMinute );
    System.out.println ( "\tStart Ampm:    " + supStartAmpm   );
    System.out.println ( "\tEnd Choice:    " + endChoice      );
    System.out.println ( "\tEnd Year:      " + supEndYear     );
    System.out.println ( "\tEnd Month:     " + supEndMonth    );
    System.out.println ( "\tEnd Date:      " + supEndDate     );
    System.out.println ( "\tEnd Hour:      " + supEndHour     );
    System.out.println ( "\tEnd Minute:    " + supEndMinute   );
    System.out.println ( "\tEnd Ampm:      " + supEndAmpm     );
    System.out.println ( "\tGroup:         " + group          );
    System.out.println ( "\tNode:          " + node           );
    System.out.println ( "\tDescription:   " + description    );
    System.out.println ( "\tDB Server:     " + dbServer       );
    System.out.println ( "\tDB Server Msg: " + dbServerMsg    );
    System.out.println ( "\tUsername:      " + username       );
    //*/

    AddEntry data_form    = new AddEntry();
    data_form.setStartChoice     ( startChoice    );
    data_form.setSupStartYear    ( supStartYear   );
    data_form.setSupStartMonth   ( supStartMonth  );
    data_form.setSupStartDate    ( supStartDate   );
    data_form.setSupStartHour    ( supStartHour   );
    data_form.setSupStartMinute  ( supStartMinute );
    data_form.setSupStartAmpm    ( supStartAmpm   );

    data_form.setEndChoice       ( endChoice      );
    data_form.setSupEndYear      ( supEndYear     );
    data_form.setSupEndMonth     ( supEndMonth    );
    data_form.setSupEndDate      ( supEndDate     );
    data_form.setSupEndHour      ( supEndHour     );
    data_form.setSupEndMinute    ( supEndMinute   );
    data_form.setSupEndAmpm      ( supEndAmpm     );

    data_form.setApplication     ( group          );
    data_form.setNode            ( node           );
    data_form.setDescription     ( description    );
    data_form.setNumMinutesPrior ( numMinPrior    );
    data_form.setEmail           ( email          );
    data_form.setDbServer        ( dbServer       );
    data_form.setDbServerMsg     ( dbServerMsg    );

    data_form.setUsername        ( username       );

    ActionErrors errors = data_form.validate ( null, request );


    Integer suppress_id = null;

    try
    {
        suppress_id = new Integer ( Toolkit.addSuppression ( data_form, -7*1000*3600 ) );
    }
    catch ( Exception exception )
    {
        exception.printStackTrace();
    }
 %>
Successfully added suppression #<%= suppress_id.toString() %>
