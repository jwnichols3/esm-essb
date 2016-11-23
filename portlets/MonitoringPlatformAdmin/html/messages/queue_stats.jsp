<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.*" %>
<%
    String queue_name = param_map.get ( "queue" );
 %>
<script language="javascript">
    function showStatus ( date, num_events, hour )
    {
        document.getElementById ( "image_map_text" ).innerHTML = "" + num_events + " events detected at " + date ;
    }
</script>
<table border="0" width="100%">
    <tr>
        <td>
            <b>Number of Active Events in the <%= queue_name %> Message Queue</b>
            <br>
            <br>
            <center>
                <img src="/MonitoringPlatformAdmin/html/messages/QueueActivityGraph.jsp" ismap usemap="#queue_map" border="0" alt="Please wait while I retrieve the message queue status...">
                <map name="queue_map">
                    <%= session.getAttribute( Constants.SESSION_NUM_EVENTS_24_HOURS_MAP ).toString() %>
                </map>
                <br>
                <br>
                <b>Comment: </b><p id="image_map_text"></p>
            </center>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            <jsp:include page="/html/messages/nav.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
