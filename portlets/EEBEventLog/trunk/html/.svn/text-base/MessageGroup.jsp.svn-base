<%@ include file="/html/init.jsp" %>
<%
    int num_per_page    = Integer.parseInt ( param_map.get ( "num_per_page" ) );
    int page_num        = Integer.parseInt ( param_map.get ( "page_num" ) );
    WeakReference <BackEndFacade> bef = new WeakReference <BackEndFacade> (new BackEndFacade() );
    List list           = bef.get().getAllDataMapRulesPaginate ( num_per_page, page_num );
 %>
<table border="0" width="100%">
    <tr>
        <td>
            <center>
                <a href="#" onclick="blah(); return false;"<b>Search for Events</b></a>
                <br>
                <br>
                <div id="EEBEventLogSearchForm" style="display:none;"><jsp:include page="/html/SearchForm.jsp"/></div>
            </center>
        </td>
    </tr>
    <tr>
        <td>
            Data Map Groups Available:
            <br>
            <br>
            <%
                if ( null != list )
                {
                    String link_prev    = "&nbsp;";
                    String link_next    = "&nbsp;";

                    if ( list.size() < num_per_page )
                    {
                        StringBuilder link = new StringBuilder();
                        link.append ( "/MessageGroup?num_per_page=" );
                        link.append ( num_per_page );
                        link.append ( "&page_num=" );
                        link.append ( page_num + 1 );
                        link_next = link.toString();
                    }
             %>
            <center>
            <table border"0" width-"100%">
                <tr>
                    <%
                        if ( page_num > 1 )
                        {
                            StringBuilder link = new StringBuilder();
                            link.append ( "/MessageGroup?num_per_page=" );
                            link.append ( num_per_page );
                            link.append ( "&page_num=" );
                            link.append ( page_num - 1 );
                            link_prev = link.toString();
                    %><td><html:link action='<%= link_prev %>'>Prev</html:link></td><%
                        }
                        else
                        {
                    %><td>&nbsp;</td><%
                        }
                     %>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <%
                        if ( list.size() == num_per_page )
                        {
                            StringBuilder link = new StringBuilder();
                            link.append ( "/MessageGroup?num_per_page=" );
                            link.append ( num_per_page );
                            link.append ( "&page_num=" );
                            link.append ( page_num + 1 );
                            link_next = link.toString();
                    %><td><html:link action='<%= link_next %>'>Next</html:link></td><%
                        }
                        else
                        {
                    %><td>&nbsp;</td><%
                        }
                     %>
                </tr>
            </table>
            <table border"1">
                <tr>
                    <th>Group Name</th>
                    <th>Notification Type</th>
                    <th>Service Center Queue</th>
                    <th>Alarmpoint Group</th>
                </tr>
                <%
                    Iterator i = list.iterator();
                    while ( i.hasNext() )
                    {
                        DataMapRule rule = (DataMapRule) i.next();

                        StringBuilder link_action = new StringBuilder ( "/MessageGroupEvents?group=" );
                        link_action.append ( rule.getGroup() );
                        link_action.append ( "&num_minutes=30&num_per_page=25&page=1" );
                 %>
                <tr>
                    <td><html:link action='<%= link_action.toString() %>'><%= rule.getGroup() %></html:link></td>
                    <td><%= rule.getMethod() %></td>
                    <td><%= rule.getPeregrineCategory() %></td>
                    <td><%= rule.getAlarmpointGroup() %></td>
                </tr>
                <%
                    }
                 %>
            </table>
            </center>
            <%
                }
                else
                {
             %>
                <center><b>No Data Map Rules Available</b></center>
            <%
                }
             %>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp"/>
        </td>
    </tr>
</table>
