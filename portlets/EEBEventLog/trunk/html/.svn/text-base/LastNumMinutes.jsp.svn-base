<%@ include file="/html/init.jsp" %>
<%
    int num_minutes     = Integer.parseInt ( param_map.get ( "num_minutes" ) );
    int num_per_page    = Integer.parseInt ( param_map.get ( "num_per_page" ) );
    int page_num        = Integer.parseInt ( param_map.get ( "page" ) );

    //  Time Zones
    Calendar start_time = Calendar.getInstance( TimeZone.getTimeZone ("GMT") );
    Calendar end_time   = Calendar.getInstance( TimeZone.getTimeZone ("GMT") );
    start_time.add ( Calendar.MINUTE, -num_minutes );

    int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
	TimeZone time_zone  = TimeZone.getDefault();
	time_zone.setRawOffset ( timezone_offset );

    start_time.setTimeZone ( time_zone );
    end_time.setTimeZone   ( time_zone );

    //  Formatter
	//SimpleDateFormat df = new SimpleDateFormat ( "yyyy MMM dd HH:mm:ss" );
	SimpleDateFormat df = new SimpleDateFormat ( "yyyy MMM dd HH:mm" );
	df.setTimeZone ( time_zone );

    //  Retrieving data from back end
    WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
    List list           = bef.get().findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

    if ( null != list )
    {
     %>
    <table border="0" width="100%">
        <tr>
            <td>
                <center>
                <b>All Events in the last <%= num_minutes %> minutes
                <br>
                <br>
                Start Time=<%= df.format ( start_time.getTime() ) %>
                <br>
                End Time=<%= df.format ( end_time.getTime() ) %>
                <br>
                <br>
                <table border="1">
                    <tr> 
                        <th nowrap>Time</th>
                        <th>Group</th>
                        <th>Source Node</th>
                        <th>Message Text</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        //for ( int counter = list.size() - 1; counter >= 0; counter-- )
                        for ( int counter = 0; counter < list.size(); counter++ )
                        {
                            EventsByGroup e = (EventsByGroup) list.get ( counter );
                             %>
                    <tr>
                        <td nowrap><%= df.format ( e.getTimestamp().getTime() )%></td>
                        <td><%= e.getGroupName() %></td>
                        <td><%= e.getSourceNode() %></td>
                        <td><%= e.getMessageText() %></td>
                        <td><html:link action='<%= "/TraceOVOMessage?message_id=" + e.getMessageId() %>'>View</html:link></td>
                    </tr>
                            <%
                        }
                     %>
                </table>
                </center>
            </td>
        </tr>
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
    </table>
    <%
    }
    else
    {
        %>
    <table border="0" width="100%">
        <tr>
            <td>
                <center><b>There were no events in the last <%= num_minutes %> minutes.</b></center>
            </td>
        </tr>
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
    </table>
        <%
    }
 %>


