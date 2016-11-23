<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";

    PortletPreferences prefs = renderRequest.getPreferences();
 %>
<script language="Javascript">
    function get_timezone() 
    {
        var tzoff, now;
    
        now   = new Date();
        tzoff = now.getTimezoneOffset();  // get offset in minutes
        tzoff = tzoff * -1;               // sign reversed by design
        tzoff = tzoff / 60;               // to hours

        document.cookie = "<%= Toolkit.TIMEZONE_COOKIE_NAME %>=" + tzoff;// + "; expires=0;";
    }

    get_timezone();
</script>
<noscript>
    <center><font color="#ff0000"><b>Javascript is DISABLED!</b></center>
    <br>
    <br>
    The browser uses Javascript and cookies to determine your time zone.  
</noscript>

<table border="0" width="100%">
    <tr>
        <td>
            &nbsp;
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
