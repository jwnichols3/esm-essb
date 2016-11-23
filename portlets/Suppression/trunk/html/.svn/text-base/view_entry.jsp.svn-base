<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%
    String username     = request.getRemoteUser();
    Object o            = (Object) param_map.get ( "suppress_id" );
    FileOutputStream outfile = new FileOutputStream ( "c:\\test\\viewentry" );
        outfile.write ( param_map.toString().getBytes() );
        /*
        outfile.write ( "class=".getBytes() );
        outfile.write ( o.getClass().getName().getBytes() );
        outfile.write ( "\n".getBytes() );
        outfile.write ( "value=".getBytes() );
        outfile.write ( o.toString().getBytes() );
        outfile.write ( "\n".getBytes() );
        //*/
    outfile.close();
    int suppress_id     = Integer.parseInt ( param_map.get( "suppress_id" ).toString() );

    TimeZone time_zone  = TimeZone.getDefault();
    int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
    time_zone.setRawOffset ( timezone_offset );

    AddEntry data_form  = Toolkit.retrieveEntry ( suppress_id );
    java.util.Date start_time = new java.util.Date ( data_form.getStartTimestamp().getTime() + timezone_offset );
    java.util.Date end_time   = new java.util.Date ( data_form.getEndTimestamp().getTime() + timezone_offset );
    java.text.DateFormat df   = java.text.DateFormat.getInstance();
 %>

    <html:link action="/view">Home</html:link>
    --&gt;
    <b>Delete Entry</b>
    <br />
    <table border="0">
        <tr>
            <td>
                <table border="1">
                    <tr>
                        <td>Node</td>
                        <td><%= (null != data_form.getNode())? data_form.getNode() : "All nodes" %></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><%= data_form.getDescription() %></td>
                    </tr>
                    <tr>
                        <td>Application</td>
                        <td><%= (null != data_form.getApplication())? data_form.getApplication() : "All Applications" %></td>
                    </tr>
                    <tr>
                        <td>Node</td>
                        <td><%= (null != data_form.getNode())? data_form.getNode() : "All Nodes" %></td>
                    </tr>
                    <tr>
                        <td>Database Server</td>
                        <td><%= (null != data_form.getDbServer())? data_form.getDbServer() : "All DbServers" %></td>
                    </tr>
                    <tr>
                        <td>Database Server Message</td>
                        <td><%= (null != data_form.getDbServerMsg())? data_form.getDbServerMsg() : "All DbServerMsgs" %></td>
                    </tr>
                    <tr>
                        <td>Time to Notify Before Expiration</td>
                        <td><%= (null != data_form.getNumMinutesPrior())? data_form.getNumMinutesPrior() : "No notification specified" %></td>
                    </tr>
                    <tr>
                        <td>Start time</td>
                        <td><%= df.format ( start_time ) %></td>
                    </tr>
                    <tr>
                        <td>End time</td>
                        <td><%= df.format ( end_time ) %></td>
                    </tr>
                </table>
                <br />
                <html:link action='<%= "/edit_entry?suppress_id=" + suppress_id %>'>Edit this entry</html:link>
                <br />
                <html:link action='<%= "/delete_entry?suppress_id=" + suppress_id %>'>Delete this entry</html:link>
            </td>
        </tr>
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
	</table>

