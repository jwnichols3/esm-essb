<%@ include file="/html/init.jsp" %>
<%
    String username     = request.getRemoteUser();
    java.util.Date date = new java.util.Date();


    String width2       = "55%";
    String width3       = "*";
    String scrollbar    = "12";
    //BackEndFacade bef   = new BackEndFacade();
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
    <center><font color="#ff0000"><b><bean:message bundle="messages" key="message.error.NoJavascript.title"/></b></center>
    <br>
    <br>
    <bean:message bundle="messages" key="message.error.NoJavascript.message"/>
    <br>
    <br>
    <bean:message bundle="messages" key="message.error.NoJavascript.warning"/>
</noscript>
<table border="0" width="100%">
    <tr>
        <td>
            <bean:message bundle="messages" key="message.introduction.title"/>
            <br>
            <br>
            <bean:message bundle="messages" key="message.introduction.description"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
