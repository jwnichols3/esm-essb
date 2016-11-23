<%@ include file="/html/init.jsp"  %>
<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="java.text.*" %>
<%
    //User user = (User)request.getAttribute("USER");
    //response.setLocale ( user.getLocale() );
    //String test_string = (String) portletSession.getAttribute ( "test1", PortletSession.APPLICATION_SCOPE );
    //portletSession.setAttribute ( "test2", "Dennis really is great!", PortletSession.APPLICATION_SCOPE );
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
<div id="struts-suppression-view">
<script language="Javascript">
    var url_test_ajax;
    var url_test_ajax2 = "This is a test";
    var counter     = 0;
    var message     = "";
    var found       = false;
    var test1       = "test string";
    var test2       = "test string";
</script>
<html:link action="/test_ajax?text=testing_ajax_is_good" linkName="test_ajax" onblur="url_text_ajax=this.href" />
<br />
<table border="0" width="100%">
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <b>Home</b>
        </td>
    </tr>
</table>
<script language="javascript">
</script>
</div>
