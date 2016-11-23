<%@ include file="/html/init.jsp" %>
<%
    WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
    String message_id    = param_map.get ( "message_id" );
    List audit_list      = bef.get().getAllAuditForMessageId ( message_id );

    String sc_ticket_num = null;
    Responder responder  = bef.get().selectResponderByMessageId ( message_id );

    if ( null != responder )
    {
        sc_ticket_num = responder.getServiceCenterTicketNumber();
    }
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <b>Events for OVO Message ID#<%= message_id %></b>
            <br><%= (( null != sc_ticket_num ) && ( false == sc_ticket_num.equals ( "IM-Default" )))? ("HelpME Ticket #" + sc_ticket_num): "" %>
            <%
                if (( null != sc_ticket_num ) && ( false == sc_ticket_num.equals ( "IM-Default" ) ))
                {
                 %>
             - <a href="http://helpmeqa/getservices/view_specific.cfm?number=<%= sc_ticket_num %>" target="_blank">Open window to HelpME</a>
                <%
                }
             %>
            <br>
            <%
                if ( null != audit_list )
                {
             %>
            <center>
                <table border="1" width="80%">
                    <tr>
                        <th>Timestamp</th>
                        <th>Processed By</th>
                        <th>Action</th>
                    </tr>
            <%
                    Iterator i = audit_list.iterator();
                    while ( i.hasNext() )
                    {
                        Audit a = (Audit) i.next();
             %>
                    <tr>
                        <td><%= Toolkit.sdf.format ( a.getTimeStamp().getTime() ) %></td>
                        <td><%= a.getModule() %></td>
                        <td><%= a.getAction() %></td>
                    </tr>
            <%
                    }
             %>
                </table>
            </center>
            <%
                }
                else
                {
            %><center>The EEB has not processed this event</center><%
                }
             %>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>

