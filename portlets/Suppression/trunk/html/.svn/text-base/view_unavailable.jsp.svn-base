<%@ include file="/html/init.jsp"  %>
<%@page import="com.liferay.portal.model.User"%>
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
        </td>
    </tr>
    <tr>
        <td>
            <center>
                Suppression Portlet is currently unavailable
                <br>
                <br>
                Estimated Time of Fix: 5:00 PM on March 30, 2006 (PST)
            </center>
        </td>
    </tr>
</table>
<script language="javascript">
</script>
</div>
<%
    Iterator i = param_map.keySet().iterator();
    StringBuilder buffer = new StringBuilder();
    String key = null;
    String value = null;

    buffer.append ( "Parameters:\n");
    while ( i.hasNext() )
    {
        key   = i.next().toString();
        value = param_map.get( key ).toString();

        buffer.append ( "\t( " );
        buffer.append ( key );
        buffer.append ( ", " );
        buffer.append ( value );
        buffer.append ( " )\n" );
    }

    buffer.append ( "\n" );
    buffer.append ( "Attributes:\n" );

    Enumeration e = request.getAttributeNames();
    while ( e.hasMoreElements() )
    {
        key = e.nextElement().toString();
        value = request.getAttribute( key ).toString();

        buffer.append ( "\t( " );
        buffer.append ( key );
        buffer.append ( ", " );
        buffer.append ( value );
        buffer.append ( " )\n" );
    }

    FileOutputStream outfile = new FileOutputStream ( "c:\\test\\portlet_attrs.dump" );
        outfile.write ( buffer.toString().getBytes() );
    outfile.close();
 %>
