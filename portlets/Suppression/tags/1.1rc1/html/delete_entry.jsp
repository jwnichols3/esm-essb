<%@ include file="/html/init.jsp" %>
<%@ page import="com.bgi.esm.portlets.Suppression.forms.AddEntry" %>
<%
    String username     = request.getRemoteUser();
	int suppress_id     = Integer.parseInt ( param_map.get( "suppress_id" ).toString() );

	AddEntry data_form  = Toolkit.retrieveEntry ( renderRequest, suppress_id );
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
			<td><%= Toolkit.getSupStartDateString ( data_form ) %></td>
		</tr>
		<tr>
			<td>End time</td>
			<td><%= Toolkit.getSupEndDateString ( data_form ) %></td>
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



