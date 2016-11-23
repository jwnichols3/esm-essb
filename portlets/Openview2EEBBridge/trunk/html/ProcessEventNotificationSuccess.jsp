<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
    BackEndFacade bef   = new BackEndFacade();
 %>
<script language="Javascript">
    function get_timezone() 
    {
        var tzoff, now;
    
        now   = new Date();
        tzoff = now.getTimezoneOffset();  // get offset in minutes
        tzoff = tzoff * -1;               // sign reversed by design
        tzoff = tzoff / 60;               // to hours
    }

    get_timezone();
</script>
<noscript>
    No Javascript detected
</noscript>
<table border="0" width="100%">
    <tr>
        <td>ProcessEventNotificationSuccess</td>
    </tr>
</table>
