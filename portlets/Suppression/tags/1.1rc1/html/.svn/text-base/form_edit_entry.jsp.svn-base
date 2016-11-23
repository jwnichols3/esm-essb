<%@ page import="com.bgi.esm.portlets.Suppression.Toolkit" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%
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
    String suppress_id    = request.getParameter ( "suppressId"      );

    AddEntry data_form   = new AddEntry();
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

    data_form.setSuppressId      ( suppress_id    );

    Toolkit.updateEntry ( data_form );
 %>
Successfully edited suppression #<%= suppress_id %>
