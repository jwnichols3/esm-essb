<%@ include file="/html/init.jsp"  %>
<%@ page import="java.text.SimpleDateFormat " %>
<%
    String username                  = request.getRemoteUser();
    String description               = (String) session.getAttribute ( "description" );
    String app_name                  = (String) session.getAttribute ( "application" );
	String search_username           = (String) session.getAttribute ( "username"    );
    String node                      = (String) session.getAttribute ( "node"        );
    String dbServer                  = (String) session.getAttribute ( "dbServer"    );
    String dbServerMsg               = (String) session.getAttribute ( "dbServerMsg" );
    String msgGroups[]               = (String[]) session.getAttribute ( "msgGroups" );
    java.util.Date startDate         = (java.util.Date) session.getAttribute ( "startDate" );
    java.util.Date endDate           = (java.util.Date) session.getAttribute ( "endDate" );
	Integer status_reboot            = (Integer) session.getAttribute ( "reboot"     );
	Integer status_deleted           = (Integer) session.getAttribute ( "is_deleted" );

	StringBuilder debug = new StringBuilder();
	debug.append ( "Description=" + description );
	debug.append ( "\n" );
	debug.append ( "Application=" + application );
	debug.append ( "\n" );
	debug.append ( "Node=" + node );
	debug.append ( "\n" );
	debug.append ( "dbServer=" + dbServer );
	debug.append ( "\n" );
	debug.append ( "dbServerMsg=" + dbServerMsg );
	debug.append ( "\n" );
	debug.append ( "startDate=" + startDate.toString() );
	debug.append ( "\n" );
	debug.append ( "endDate=" + endDate.toString() );
	debug.append ( "\n" );

	session.setAttribute ( "description", description     );
	session.setAttribute ( "application", app_name        );
	session.setAttribute ( "username",    search_username );
	session.setAttribute ( "node",        node            );
	session.setAttribute ( "dbServer",    dbServer        );
	session.setAttribute ( "dbServermsg", dbServerMsg     );
	session.setAttribute ( "msgGroups",   msgGroups       );
	session.setAttribute ( "startDate",   startDate       );
	session.setAttribute ( "endDate",     endDate         );
	session.setAttribute ( "reboot",      status_reboot   );
	session.setAttribute ( "is_deleted",  status_deleted  );

    int suppress_id                  = 0;
    String struts_path               = "/search_historical_again";
    boolean show_suppress_id         = true;
    boolean show_group_name          = true;
    boolean show_node_name           = true;
    boolean show_db_instance         = true;
    boolean show_db_message          = true;
    boolean show_description         = true;
    boolean show_start_time          = true;
    boolean show_end_time            = true;
    boolean show_suppression_creator = true;
    boolean show_remove_on_reboot    = true;
	String sort_type                 = param_map.get ( "sort" );
	String sort_string1              = null;
	String sort_string2              = null;
    ResultSet results                = Toolkit.retrieveHistory ( renderRequest, description, app_name, node, dbServer, dbServerMsg, startDate, endDate, status_reboot.intValue(), status_deleted.intValue(), search_username, sort_type );

    String group_nm                  = null;
    String node_nm                   = null;
    String instance                  = null;
    String message                   = null;
    String suppress_desc_txt         = null;
    String creator                   = null;
    java.util.Date start_utc_tms     = null;
    java.util.Date end_utc_tms       = null;
    String reboot_text               = null;
    int remove_on_reboot             = 0;

    String width1                    = "5%";
    String width2                    = "10%";
    String width3                    = "10%";
    String width4                    = "25%";
    String width5                    = "25%";
    String width6                    = "*";
    String scrollbar                 = "12";

    int num_records                  = 0;
	
	Calendar calendar                = Calendar.getInstance();
	TimeZone time_zone               = TimeZone.getDefault();
	time_zone.setRawOffset ( Toolkit.computeTimeZoneOffset ( request ) );

    PortletURL render_url            = renderResponse.createRenderURL();
	SimpleDateFormat df              = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
	df.setTimeZone ( time_zone );
    //<div style="overflow: auto; width: 100%; height: 600; border-right: 1px gray solid; border-left: 1px gray solid; border-bottom: 1px gray solid; border-top: 1px gray solid; padding:0px; margin: 0px">
%>

    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <b>Searching All Historical Entries</b>
                <br />
                <br />
                
                    <table id="suppressionEntriesTable" border="1" width="100%">
                        <%
							while ( results.next() )
                            {
                                if ( 0 == num_records % 25  )
                                {
                                    %>
                                    <tr>
                                    <%
                                        if ( show_suppress_id )
                                        {
											sort_string1 = "suppress_id";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
							
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">ID</html:link></th><%
                                        }
                                        if ( show_group_name )
                                        {
											sort_string1 = "group_nm";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
															
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">App</html:link></th><%
                                        }
                                        if ( show_node_name )
                                        {
											sort_string1 = "node_nm";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
															
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Node</html:link></th><%
                                        }
                                        if ( show_db_instance && show_db_message )
                                        {
											sort_string2 = "instance";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string2 )))
											{
												sort_string2 = sort_string2 + " DESC";
											}
															
                                            %><th>Message Text /<br><html:link action="<%= struts_path + "?sort=" + sort_string2 %>">Database</html:link></th><%
                                        }
                                        else if ( show_db_instance )
                                        {
											sort_string1 = "instance";
											if (( null != sort_type ) && ( sort_type.equals ( sort_string2 )))
											{
												sort_string2 = sort_string2 + " DESC";
											}
															
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Database</html:link></th><%
                                        }
                                        else if ( show_db_message )
                                        {
                                            %><th>Message Text</th><%
                                        }
                                
                                        if ( show_description )
                                        {
                                            %><th>Description</th><%
                                        }
                                
                                        if ( show_start_time && show_end_time )
                                        {
											sort_string1 = "start_utc_tms";
											sort_string2 = "end_utc_tms";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
											if (( null == sort_type ) || ( sort_type.equals ( sort_string2 )))
											{
												sort_string2 = sort_string2 + " DESC";
											}
                                            %><th nowrap><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Start Time</html:link> /<br /><html:link action="<%= struts_path + "?sort=" + sort_string2 %>">End Time</html:link></th><%
                                        }
                                        else if ( show_start_time )
                                        {
											sort_string1 = "start_utc_tms";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
                                            %><th nowrap><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Start Time</html:link></th><%
                                        }
                                        else if ( show_end_time )
                                        {
											sort_string1 = "end_utc_tms";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
                                            %><th nowrap><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">End Time</html:link></th><%
                                        }
                                
                                        if ( show_suppression_creator ) 
                                        {
											sort_string1 = "create_nm";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Creator</html:link></th><%
                                        }
                                        if ( show_remove_on_reboot )
                                        {
											sort_string1 = "remove_on_reboot";
											if (( null == sort_type ) || ( sort_type.equals ( sort_string1 )))
											{
												sort_string1 = sort_string1 + " DESC";
											}
                                            %><th><html:link action="<%= struts_path + "?sort=" + sort_string1 %>">Remove on Reboot?</html:link></th><%
                                        }
                                    %>
                                    </tr>
                                    <%
                                }

                                 %>
                                <tr>
                                <%
                                if ( show_suppress_id )
                                {
                                    suppress_id =  results.getInt ( "suppress_id" );

                                    if ( request.getRemoteUser() != null )
                                    {
                                        %><td><html:link styleId="suppressionEntryLink" action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= suppress_id %></html:link></td><%
                                    }
                                    else
                                    {
                                        %><td><%= suppress_id %></td><%
                                    }
                                }
                                if ( show_group_name )
                                {
                                    group_nm = results.getString ( "group_nm" );
                                    if (( null == group_nm ) || ( group_nm.length() < 1 ) || ( group_nm.equals ( "-" )))
                                    {
                                        group_nm = "<center>-- All Applications --</center>";
                                    }
                                    %><td><%= group_nm %></td><%
                                }
                                if ( show_node_name )
                                {
                                    node_nm = results.getString ( "node_nm" );
                                    if (( null == node_nm ) || ( node_nm.length() < 1 ) || ( node_nm.equals ( "-" )))
                                    {
                                        node_nm = "<center>-- All Nodes --</center>";
                                    }
                                    %><td><%= node_nm %></td><%
                                }
                                if ( show_db_instance && show_db_message )
                                {
                                    instance = results.getString ( "instance" );
                                    message  = results.getString ( "message" );

                                    if (( null == instance ) || ( instance.length() < 1 ) || ( instance.equals ( "-" )))
                                    {
                                        instance = "<center>-- All Databases --</center>";
                                    }
                                     %>
                                    <td>
                                        <%= (null != message)? message : "&nbsp;"  %>
                                        <br />
                                        <br />
                                        <%= (null != instance)? instance : "&nbsp;" %>
                                    </td>
                                    <%
                                }
                                else if ( show_db_instance )
                                {
                                    instance = results.getString ( "instance" );
                                    %><td><%= (null != instance)? instance: "&nbsp;" %></td><%
                                }
                                else if ( show_db_message )
                                {
                                    message  = results.getString ( "message" );
                                    %><td><%= (null != message)? message : "&nbsp;" %></td><%
                                }
                                if ( show_description )
                                {
                                    suppress_desc_txt = results.getString ( "suppress_desc_txt" );
                                    %><td><%= suppress_desc_txt %></td><%
                                }
                    
                                if ( show_start_time && show_end_time )
                                {
									Timestamp ts_start = results.getTimestamp ( "start_utc_tms" );
									Timestamp ts_end   = results.getTimestamp ( "end_utc_tms" );
                                    start_utc_tms = new java.util.Date ( ts_start.getTime() );
                                    end_utc_tms   = new java.util.Date ( ts_end.getTime() );
                                     %>
                                    <td nowrap>
                                        <%= df.format ( start_utc_tms ) %>
                                        <br />
                                        <%= df.format ( end_utc_tms ) %>
                                    </td>
                                    <%
                                }
                                else if ( show_start_time )
                                {
                                    start_utc_tms = results.getDate ( "start_utc_tms" );
                                    %><td nowrap><%= df.format ( start_utc_tms ) %></td><%
                                }
                                else if ( show_end_time )
                                {
                                    end_utc_tms   = results.getDate ( "end_utc_tms" );
                                    %><td nowrap><%= df.format ( end_utc_tms ) %></td><%
                                }

                                if ( show_suppression_creator )
                                {
                                    creator = results.getString ( "create_nm" );
                                    %><td><%= creator %></td><%
                                }
                                if ( show_remove_on_reboot )
                                {
                                    remove_on_reboot  = results.getInt ( "remove_on_reboot" );
                                    reboot_text = (1 == remove_on_reboot)? "Yes" : "No"; 
                                    %><td><center><%= reboot_text %></center></td><%
                                }
                                 %>
                                </tr>
                                <%

								num_records++;
                            }
                        %>
                    </table>
            </td>
        </tr>
    </table>
