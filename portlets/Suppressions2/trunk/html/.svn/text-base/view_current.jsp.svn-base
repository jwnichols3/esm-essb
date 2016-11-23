<%@ include file="/html/init.jsp"  %>
<%@ page import="java.text.SimpleDateFormat " %>
<%@ page import="java.util.List" %>
<%@ page import="com.bgi.esm.monitoring.platform.client.BackEndFacade"           %>
<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.forms.Suppression" %>
<%
	Calendar calendar            = Calendar.getInstance();
	TimeZone time_zone           = TimeZone.getDefault();
    int timezone_offset          = Toolkit.computeTimeZoneOffset ( request );
    int browser_timezone_offset  = Toolkit.computeTimeZoneOffset ( request );
	time_zone.setRawOffset ( timezone_offset );

    BackEndFacade bef  = new BackEndFacade();
    String strTimezone = bef.selectEebProperty( "server.time_zone" ).getPropertyValue();
    TimeZone tz_server = TimeZone.getTimeZone ( strTimezone );
    int tz_daylight_savings_offset = tz_server.getDSTSavings();
    int tz_timezone_offset         = tz_server.getRawOffset();
    int tz_offset                  = tz_timezone_offset + tz_daylight_savings_offset;

    timezone_offset -= tz_offset;

	SimpleDateFormat df          = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
	//df.setTimeZone ( time_zone );

    String username              = request.getRemoteUser();

    List <Suppression> resultList = (List <Suppression>) session.getAttribute ( Suppression.SESSION_KEY_SEARCH_RESULTS );
%>
    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <b>All Current Entries</b>
                <br />
                <br />
                Browser Timezone offset: <%= browser_timezone_offset %>
                <br />
                <br />
                <table id="suppressionEntriesTable" border="1" width="100%">
                    <tr>
                        <th>Suppress<br>ID</th>
                        <th>Application</th>
                        <th>Node</th>
                        <th>DB Message<br>DB Instance</th>
                        <th>Description</th>
                        <th>Start Time<br>End Time</th>
                        <th>Owner</th>
                        <th>Remove on<br>Reboot?</th>
                    </tr>
                    <%
                        for ( int counter = 0; counter < resultList.size(); counter++ )
                        {
                            Suppression suppression = (Suppression) resultList.get ( counter );
                            Calendar startTime = suppression.getStartTime();
                            Calendar endTime   = suppression.getEndTime();

                            startTime.add ( Calendar.MILLISECOND, timezone_offset );
                            endTime.add   ( Calendar.MILLISECOND, timezone_offset );
                             %>
                    <tr>
                        <td>
                            <center>
                                <b><%= suppression.getSuppressId() %></b>
                            <%
                                if (( null != username ) && ( username.equals ( suppression.getOwner() ) ))
                                {
                                    %><br><html:link action='<%= "/suppression_edit?method=edit&suppress_id=" + suppression.getSuppressId() %>'><bean:message bundle="databases" key="databases.records.edit"/></html:link><%
                                }
                             %>
                            </center>
                        </td>
                        <td><%= suppression.getAppName() %></td>
                        <td><%= suppression.getNodeName() %></td>
                        <td><%= suppression.getDbServer() %><hr><%= suppression.getMessage() %></td>
                        <td><%= suppression.getDescription() %></td>
                        <td><%= df.format ( startTime.getTime() ) %><hr><%= df.format ( endTime.getTime() ) %></td>
                        <td><%= suppression.getOwner() %></td>
                        <td><%= ( 1L == suppression.getRemoveOnReboot() )? "true" : "false" %></td>
                    </tr>
                            <%
                        }
                     %>
                </table>
            </td>
        </tr>
    </table>
