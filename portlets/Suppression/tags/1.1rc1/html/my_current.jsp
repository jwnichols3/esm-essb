<%@ include file="/html/init.jsp"  %>
<%@ page import="java.text.SimpleDateFormat " %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />
<%
    String username                  = request.getRemoteUser();
    String struts_path               = "/my_current";
    boolean show_suppress_id         = true;
    boolean show_group_name          = true;
    boolean show_node_name           = true;
    boolean show_db_instance         = true;
    boolean show_db_message          = true;
    boolean show_description         = true;
    boolean show_start_time          = true;
    boolean show_end_time            = true;
    boolean show_suppression_creator = false;
    boolean show_remove_on_reboot    = true;
	String sort_type                 = param_map.get ( "sort" );
	String sort_string1              = null;
	String sort_string2              = null;
    ResultSet results                = Toolkit.getCurrentSuppressions ( renderRequest, username, sort_type );

    String group_nm              = null;
    String node_nm               = null;
    String instance              = null;
    String message               = null;
    String suppress_desc_txt     = null;
    String creator               = null;
    java.util.Date start_utc_tms = null;
    java.util.Date end_utc_tms   = null;
    String reboot_text           = null;
    int remove_on_reboot         = 0;
    int suppress_id              = 0;

    String width1                = "5%";
    String width2                = "10%";
    String width3                = "10%";
    String width4                = "25%";
    String width5                = "25%";
    String width6                = "*";
    String scrollbar             = "12";

    int num_records              = 0;
	
	Calendar calendar            = Calendar.getInstance();
	TimeZone time_zone           = TimeZone.getDefault();
	time_zone.setRawOffset ( Toolkit.computeTimeZoneOffset ( request ) );

    PortletURL render_url        = renderResponse.createRenderURL();
	SimpleDateFormat df          = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
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
                <b>Current Entries</b> for <%= username %>
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
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>" styleId="suppressionEntryLink"><%= suppress_id %></html:link></td><%
                                }
                                if ( show_group_name )
                                {
                                    group_nm = results.getString ( "group_nm" );
                                    if (( null == group_nm ) || ( group_nm.length() < 1 ) || ( group_nm.equals ( "-" )))
                                    {
                                        group_nm = "<center>-- All applications --</center>";
                                    }
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= group_nm %></html:link></td><%
                                }
                                if ( show_node_name )
                                {
                                    node_nm = results.getString ( "node_nm" );
                                    if (( null == node_nm ) || ( node_nm.length() < 1 ) || ( node_nm.equals ( "-" )))
                                    {
                                        node_nm = "<center>-- All nodes --</center>";
                                    }
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= node_nm %></html:link></td><%
                                }
                                if ( show_db_instance && show_db_message )
                                {
                                    instance = results.getString ( "instance" );
                                    message  = results.getString ( "message" );

                                    if (( null == instance ) || ( instance.length() < 1 ) || ( instance.equals ( "-" )))
                                    {
                                        instance = "<center>-- All database instances --</center>";
                                    }
                                     %>
                                    <td>
                                        <html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= (null != message)? message : "&nbsp;"  %></html:link>
                                        <br />
                                        <br />
                                        <html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= (null != instance)? instance : "&nbsp;" %></html:link>
                                    </td>
                                    <%
                                }
                                else if ( show_db_instance )
                                {
                                    instance = results.getString ( "instance" );
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= (null != instance)? instance: "&nbsp;" %></html:link></td><%
                                }
                                else if ( show_db_message )
                                {
                                    message  = results.getString ( "message" );
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= (null != message)? message : "&nbsp;" %></html:link></td><%
                                }
                                if ( show_description )
                                {
                                    suppress_desc_txt = results.getString ( "suppress_desc_txt" );
                                    %><td><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= suppress_desc_txt %></html:link></td><%
                                }
                    
                                if ( show_start_time && show_end_time )
                                {
									Timestamp ts_start = results.getTimestamp ( "start_utc_tms" );
									Timestamp ts_end   = results.getTimestamp ( "end_utc_tms" );
                                    start_utc_tms = new java.util.Date ( ts_start.getTime() );
                                    end_utc_tms   = new java.util.Date ( ts_end.getTime() );
                                     %>
                                    <td nowrap>
                                        <html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= df.format ( start_utc_tms ) %></html:link>
                                        <br />
                                        <html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= df.format ( end_utc_tms ) %></html:link>
                                    </td>
                                    <%
                                }
                                else if ( show_start_time )
                                {
                                    start_utc_tms = results.getDate ( "start_utc_tms" );
                                    %><td nowrap><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= df.format ( start_utc_tms ) %></html:link></td><%
                                }
                                else if ( show_end_time )
                                {
                                    end_utc_tms   = results.getDate ( "end_utc_tms" );
                                    %><td nowrap><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= df.format ( end_utc_tms ) %></html:link></td><%
                                }

                                if ( show_suppression_creator )
                                {
                                    creator = results.getString ( "create_nm" );
                                    %><td nowrap><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= creator %></html:link></td><%
                                }
                                if ( show_remove_on_reboot )
                                {
                                    remove_on_reboot  = results.getInt ( "remove_on_reboot" );
                                    reboot_text = (1 == remove_on_reboot)? "Yes" : "No"; 
                                    %><td nowrap><html:link action="<%= "/view_entry?suppress_id="+suppress_id %>"><center><%= reboot_text %></center></html:link></td><%
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

