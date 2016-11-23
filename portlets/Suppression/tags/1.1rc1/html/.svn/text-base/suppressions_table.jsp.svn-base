<%@ include file="/html/init.jsp"  %>
<%@ page import="java.text.SimpleDateFormat " %>
<%
    String username              = request.getRemoteUser();
	ResultSet results            = Toolkit.getAllCurrentSuppressions( renderRequest, 0 );

    String width1                = "5%";
    String width2                = "10%";
    String width3                = "10%";
    String width4                = "23%";
    String width5                = "22%";
    String width6                = "10%";
    String width7                = "15%";
    String width8                = "*";

	String group_nm              = null;
    String node_nm               = null;
    String instance              = null;
    String message               = null;
    String suppress_desc_txt     = null;
    java.util.Date start_utc_tms = null;
    java.util.Date end_utc_tms   = null;
    String reboot_text           = null;
	String temp_string           = null;
    int remove_on_reboot         = 0;
    int suppress_id              = 0;
	int num_records              = 0;
	SimpleDateFormat df          = new SimpleDateFormat ( "yyyy-MM-dd hh:mm" );

 %>
        <table border="1" width="100%">
        	<col width="<%= width1 %>"><col width="<%= width2 %>"><col width="<%= width3 %>"><col width="<%= width4 %>"><col width="<%= width5 %>"><col width="<%= width6 %>"><col width="<%= width7 %>"><col width="<%= width8 %>">
<%
        while ( results.next() )
        {
            suppress_id       = results.getInt    ( 1 );
            group_nm          = results.getString ( "group_nm" );
            node_nm           = results.getString ( "node_nm" );
            instance          = results.getString ( "instance" );
            message           = results.getString ( "message" );
            suppress_desc_txt = results.getString ( "suppress_desc_txt" );
            start_utc_tms     = results.getDate   ( "start_utc_tms" );
            end_utc_tms       = results.getDate   ( "end_utc_tms" );

            group_nm          = ((null != group_nm) && ( !group_nm.equals ( "-- all applications --" )))? group_nm : "All Applications";
            node_nm           = ((null != node_nm ) && ( !node_nm.equals  ( "-- all nodes --" )))? node_nm  : "All Nodes";
            instance          = (null != instance)? instance : "&nbsp;"; 
            message           = (null != message )? message  : "&nbsp;";
            suppress_desc_txt = results.getString ( "suppress_desc_txt" );
            remove_on_reboot  = results.getInt ( "remove_on_reboot" );
            reboot_text       = (1 == remove_on_reboot)? "Yes" : "No";
            
            if ( 0 == num_records % 25 )
            {
				%>
                <tr>
                    <th>ID</th>
                    <th>App</th>
                    <th>Node</th>
                    <th>Message Text /<br>Database</th>
                    <th>Description</th>
                    <th>Remove on<br>Reboot?</th>
                    <th>Start /<br>End </th>
                    <th></th>
                    <th></th>
                </tr>
				<%
            }
            
            if ( null != username )
            {
				%>
                 <tr>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><center><%= suppress_id %></center></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= group_nm          %></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><center><%= node_nm   %></center></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'>Database:<br><%= instance          %><br><br>Message:<br><%= message %></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= suppress_desc_txt %></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><center><%= reboot_text       %></center></html:link></td>
                     <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= df.format ( start_utc_tms ) %><br><%= df.format ( end_utc_tms )%></html:link></td>
                 </tr>
				 <%
            }
            else
            {
				%>
                 <tr>
                     <td><center><%= suppress_id %></center></td>
                     <td><%= group_nm          %></td>
                     <td><center><%= node_nm   %></center></td>
                     <td>Database:<br><%= instance %><br><br>Message:<br><%= message %></td>
                     <td><%= suppress_desc_txt %></td>
                     <td><center><%= reboot_text       %></center></td>
                     <td><%= df.format ( start_utc_tms ) %><br><%= df.format ( end_utc_tms ) %></td>
                 </tr>
				<%
            }
            
            num_records++;
        }
		%>
        <tr>
            <th>ID</th>
            <th>App</th>
            <th>Node</th>
            <th>Message Text /<br>Database</th>
            <th>Description</th>
            <th>Remove on<br>Reboot?</th>
            <th>Start /<br> End </th>
            <th></th>
            <th></th>
        </tr>
    </table>
