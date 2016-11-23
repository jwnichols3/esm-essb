<%@ include file="/html/init.jsp"  %>
<%@ page import="java.text.SimpleDateFormat " %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />
<%
	Calendar calendar            = Calendar.getInstance();
	TimeZone time_zone           = TimeZone.getDefault();
    int timezone_offset          = Toolkit.computeTimeZoneOffset ( request );
	time_zone.setRawOffset ( timezone_offset );
	
    String username                  = request.getRemoteUser();
    String struts_path               = "/current";
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
    long start_query_time            = System.currentTimeMillis();
    //HashMap <String, Vector> results = Toolkit.getAllCurrentSuppressions ( renderRequest );
    ResultSet results                = Toolkit.getAllCurrentSuppressions ( renderRequest, timezone_offset );
    long end_query_time              = System.currentTimeMillis();
    results.setFetchSize ( 500 );

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
                <b>All Current Entries</b>
                <br />
                <br />
                    <table id="suppressionEntriesTable" border="1" width="100%">
                        <%
                            java.util.Date debug_start = new java.util.Date();
							while ( results.next() )
                            {
                                if ( 0 == num_records % 25 )
                                {
                                     %>
                                <tr>
                                    <th>Suppress<br>ID</th>
                                    <th>Application</th>
                                    <th>Node</th>
                                    <th>
                                        DB Message
                                        <br />
                                        <br />
                                        DB Instance
                                    </th>
                                    <th>Description</th>
                                    <td nowrap>
                                        Start Time
                                        <br />
                                        End Time
                                    </th>
                                    <th>Owner</th>
                                    <th><center>Remove on Reboot?</center></th>
                                </tr>
                                    <%
                                }
                                suppress_id = results.getInt ( "suppress_id" );
                                group_nm    = Toolkit.checkDisplayString ( results.getString ( "group_nm" ) );
                                node_nm     = Toolkit.checkDisplayString ( results.getString ( "node_nm"  ) );
                                instance    = Toolkit.checkDisplayString ( results.getString ( "instance" ) );
                                message     = Toolkit.checkDisplayString ( results.getString ( "message"  ) );
                                instance    = Toolkit.checkDisplayString ( results.getString ( "instance" ) );
                                message     = Toolkit.checkDisplayString ( results.getString ( "message" ) );
                                suppress_desc_txt = Toolkit.checkDisplayString ( results.getString ( "suppress_desc_txt" ) );
								Timestamp ts_start = results.getTimestamp ( "start_utc_tms" );
								Timestamp ts_end   = results.getTimestamp ( "end_utc_tms" );
                                start_utc_tms = new java.util.Date ( ts_start.getTime() );
                                end_utc_tms   = new java.util.Date ( ts_end.getTime() );
                                creator = results.getString ( "create_nm" );
                                remove_on_reboot  = results.getInt ( "remove_on_reboot" );
                                reboot_text = (1 == remove_on_reboot)? "Yes" : "No"; 
                                %>
                                <tr>
                                    <%
                                    if ( null != request.getRemoteUser() )
                                    {
                                        %>
                                    <td><u><html:link styleId="suppressionEntryLink" action="<%= "/view_entry?suppress_id="+suppress_id %>"><%= suppress_id %></html:link></u></td>
                                        <%
                                    }
                                    else
                                    {
                                        %>
                                    <td><%= suppress_id %></u></td>
                                        <%
                                    }
                                     %>
                                    <td><div id='<%= "group" + suppress_id %>'><%= group_nm %></dof></td>
                                    <td><%= node_nm %></div></td>
                                    <td>
                                        <div id='<%= "message" + suppress_id %>'><%=  message %></div>
                                        <br />
                                        <br />
                                        <div id='<%= "instance" + suppress_id %>'><%= instance %></div>
                                    </td>
                                    <td><div id='<%= "description" + suppress_id %>'><%= suppress_desc_txt %></div></td>
                                    <td nowrap>
                                        <div id='<%= "startDate" + suppress_id %>'><%= df.format ( start_utc_tms ) %></div>
                                        <br />
                                        <div id='<%= "endDate" + suppress_id %>'><%= df.format ( end_utc_tms ) %></div>
                                    </td>
                                    <td><div id='<%= "creator" + suppress_id %>'><%= creator %></div></td>
                                    <td><div id='<%= "reboot" + suppress_id %>'><center><%= reboot_text %></center></div></td>
                                </tr>
                                <%
								num_records++;
                            }
                            java.util.Date debug_end = new java.util.Date();
                        %>
                    </table>
            </td>
        </tr>
    </table>

