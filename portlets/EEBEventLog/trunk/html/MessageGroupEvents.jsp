<%@ include file="/html/init.jsp" %>
<%
    int num_minutes                   = Integer.parseInt ( param_map.get ( "num_minutes" ) );
    int num_per_page                  = Integer.parseInt ( param_map.get ( "num_per_page" ) );
    int page_num                      = Integer.parseInt ( param_map.get ( "page" ) );
    String group_name                 = param_map.get ( "group" ); 
    Calendar start_time               = Calendar.getInstance();
    Calendar end_time                 = Calendar.getInstance();
    start_time.add ( Calendar.MINUTE, -num_minutes );
    WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> ( new BackEndFacade() );
    List list                         = bef.get().findAllEventsByGroupBetweenTimesByGroupPaginate ( group_name, start_time, end_time, num_per_page, page_num );

    int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
	TimeZone time_zone  = TimeZone.getDefault();
	time_zone.setRawOffset ( timezone_offset );

	SimpleDateFormat df = new SimpleDateFormat ( "yyyy MMM dd HH:mm:ss" );
	df.setTimeZone ( time_zone );

    if ( null != list )
    {
        Iterator i          = list.iterator();
     %>
    <table border="0" width="100%">
        <tr>
            <td>
                <center>
                <b>All Events in the last <%= num_minutes %> minutes.</b>
                <table border="1">
                    <tr>
                        <th nowrap>Time</th>
                        <th>Group</th>
                        <th>Source Node</th>
                        <th>Message Text</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        while ( i.hasNext() )
                        {
                            EventsByGroup e = (EventsByGroup) i.next();
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
