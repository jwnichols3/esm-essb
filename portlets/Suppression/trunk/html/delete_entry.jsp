<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%
    String username     = request.getRemoteUser();
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
    <b>View Entry</b>
	<br />
	<table border="1">
		<tr>
			<td>Node</td>
			<td><%= (null != data_form.getNode())? data_form.getNode() : "All nodes" %></td>
		</tr>
		<tr>
			<td>Application</td>
			<td><%= (null != data_form.getApplication())? data_form.getApplication() : "All Applications" %></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><%= data_form.getDescription() %></td>
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
	<br />
	Are you sure you want to delete this entry?
	<table border=="0" width="50%">
		<tr>
			<td>
				<html:link action='<%= "/confirm_delete?suppress_id=" + suppress_id %>'>Yes</html:link>
			</td>
			<td>
				<html:link action="/view">No</html:link>
			</td>
		</tr>
	</table>



