<%@ include file="/html/init.jsp"  %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />

<portlet:defineObjects />
<%
    StringBuffer query  = null;
    int counter         = 0;
    int num_data        = 0;
    long current_time   = System.currentTimeMillis();
    Calendar calendar   = Calendar.getInstance();
    String data[]       = null;

    Toolkit.retrieveServerData ( data_form );

    String username     = request.getRemoteUser();
 %>
<style type="text/css">
    div.suggestions {
        -moz-box-sizing:  border-box;
        box-sizing:       border-box;
        border:           1px solid black;
        position:         absolute;
        background-color: white;
        top:              200px;
        left:             200px;
        height:           200px; 
        overflow:         auto;
        z-index:          99;
        text-align:       left;
    }
    div.suggestions.div {
        cursor:  default;
        padding: 0px 3px;
    }
    div.suggestions.div.current {
        background-color: #3366cc;
        color:            white;
    }
</style>

    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <html:link action="/view">Home</html:link>
                --&gt;
                <b>Search My History</b>
                <br />
            
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <html:errors />
                        </td>
                    </tr>
                </table>
                <br />
                <br />
                <html:form action="/search_my_history_process" method="post" focus="firstName" onsubmit="submitForm(this); return false;">
                    Search for records where... 
                    <table border="1">
                        <tr>
                            <td>... the &ldquo;Description&rdquo; field contains text </td>
                            <td><html:text name="suppression_search_params" property="description"/></td>
                        </tr>
                        <tr>
                            <td>... and affected the Application</td>
                            <td>
                                <html:select name="suppression_search_params" property="application">
                                <html:option value="all">-- all applications --</html:option>
                                <%
                                    data = data_form.getMessageGroups();
                                    num_data = data.length;
                                    for ( counter = 0; counter < num_data; counter++ )
                                    {
                                %><html:option value='<%= data[counter] %>'><%= data[counter] %></html:option><%
                                    }
                                 %>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>... and applied to the following Node Group</td>
                            <td>
                                <html:select name="suppression_search_params" property="node">
                                <html:option value="all">-- all nodes --</html:option>
                                <%
                                    data = data_form.getNodeNames();
                                    num_data = data.length;
                                    for ( counter = 0; counter < num_data; counter++ )
                                    {
                                %><html:option value='<%= data[counter] %>'><%= data[counter] %></html:option><%
                                    }
                                 %>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>... and applied to the following Database</td>
                            <td>
                                <html:select name="suppression_search_params" property="dbServer">
                                <html:option value="all">-- all DB servers --</html:option>
                                <%
                                    data = data_form.getDataServers();
                                    num_data = data.length;
                                    for ( counter = 0; counter < num_data; counter++ )
                                    {
                                %><html:option value='<%= data[counter] %>'><%= data[counter] %></html:option><%
                                    }
                                 %>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>...the following text in the &ldquo;Message Text&rdquo;</td>
                            <td><html:text name="suppression_search_params" property="dbServerMsg"/></td>
                        </tr>
                    </table>
                    <br />
                    <table border="1">
                        <tr>
                            <td>created since</td>
                            <td><html:radio name="suppression_search_params" property="startChoice" value="beginning"/></td>
                            <td>the beginning of time</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_search_params" property="startChoice" value="specified"/></td>
                            <td>
                                <html:select name="suppression_search_params" property="supStartDate">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_search_params" property="supStartMonth">
                                    <html:option value="0" >January</html:option>
                                    <html:option value="1" >February</html:option>
                                    <html:option value="2" >March</html:option>
                                    <html:option value="3" >April</html:option>
                                    <html:option value="4" >May</html:option>
                                    <html:option value="5" >June</html:option>
                                    <html:option value="6" >July</html:option>
                                    <html:option value="7" >August</html:option>
                                    <html:option value="8" >September</html:option>
                                    <html:option value="9">October</html:option>
                                    <html:option value="10">November</html:option>
                                    <html:option value="11">December</html:option>
                                </html:select>
                                <html:select name="suppression_search_params" property="supStartYear">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                </html:select>
                                at
                                <html:select name="suppression_search_params" property="supStartHour">
                                    <html:option value="1" >1</html:option>
                                    <html:option value="2" >2</html:option>
                                    <html:option value="3" >3</html:option>
                                    <html:option value="4" >4</html:option>
                                    <html:option value="5" >5</html:option>
                                    <html:option value="6" >6</html:option>
                                    <html:option value="7" >7</html:option>
                                    <html:option value="8" >8</html:option>
                                    <html:option value="9" >9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                </html:select>
                                <html:select name="suppression_search_params" property="supStartMinute">
                                    <html:option value="0" >00</html:option>
                                    <html:option value="1" >01</html:option>
                                    <html:option value="2" >02</html:option>
                                    <html:option value="3" >03</html:option>
                                    <html:option value="4" >04</html:option>
                                    <html:option value="5" >05</html:option>
                                    <html:option value="6" >06</html:option>
                                    <html:option value="7" >07</html:option>
                                    <html:option value="8" >08</html:option>
                                    <html:option value="9" >09</html:option>
                                <%
                                    for ( counter = 10; counter < 60; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_search_params" property="supStartAmpm">
                                    <html:option value="AM">AM</html:option>
                                    <html:option value="PM">PM</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <table border="1">
                        <tr>
                            <td>until</td>
                            <td><html:radio name="suppression_search_params" property="endChoice" value="now"/></td>
                            <td>
                                now
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_search_params" property="endChoice" value="specified"/></td>
                            <td>
                                 <html:select name="suppression_search_params" property="supEndDate">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_search_params" property="supEndMonth">
                                    <html:option value="0" >January</html:option>
                                    <html:option value="1" >February</html:option>
                                    <html:option value="2" >March</html:option>
                                    <html:option value="3" >April</html:option>
                                    <html:option value="4" >May</html:option>
                                    <html:option value="5" >June</html:option>
                                    <html:option value="6" >July</html:option>
                                    <html:option value="7" >August</html:option>
                                    <html:option value="8" >September</html:option>
                                    <html:option value="9">October</html:option>
                                    <html:option value="10">November</html:option>
                                    <html:option value="11">December</html:option>
                                </html:select>
                                <html:select name="suppression_search_params" property="supEndYear">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                </html:select>
                                at
                                <html:select name="suppression_search_params" property="supEndHour">
                                    <html:option value="1" >1</html:option>
                                    <html:option value="2" >2</html:option>
                                    <html:option value="3" >3</html:option>
                                    <html:option value="4" >4</html:option>
                                    <html:option value="5" >5</html:option>
                                    <html:option value="6" >6</html:option>
                                    <html:option value="7" >7</html:option>
                                    <html:option value="8" >8</html:option>
                                    <html:option value="9" >9</html:option>
                                    <html:option value="10">10</html:option>
                                    <html:option value="11">11</html:option>
                                    <html:option value="12">12</html:option>
                                </html:select>
                                <html:select name="suppression_search_params" property="supEndMinute">
                                    <html:option value="0" >00</html:option>
                                    <html:option value="1" >01</html:option>
                                    <html:option value="2" >02</html:option>
                                    <html:option value="3" >03</html:option>
                                    <html:option value="4" >04</html:option>
                                    <html:option value="5" >05</html:option>
                                    <html:option value="6" >06</html:option>
                                    <html:option value="7" >07</html:option>
                                    <html:option value="8" >08</html:option>
                                    <html:option value="9" >09</html:option>
                                <%
                                    for ( counter = 10; counter < 60; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_search_params" property="supEndAmpm">
                                    <html:option value="AM">AM</html:option>
                                    <html:option value="PM">PM</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
					<br />
					<br />
					What about ... 
					<table border="0">
						<tr>
							<td>... suppressions that are removed on reboot?</td>
							<td>
                                <html:select name="suppression_search_params" property="removeOnReboot">
                                    <html:option value="either">Does not matter</html:option>
                                    <html:option value="remove-only">With this option</html:option>
                                    <html:option value="no-remove">Without this option</html:option>
                                </html:select>
							</td>
						</tr>
						<tr>
							<td>... deleted suppressions?</td>
							<td>
                                <html:select name="suppression_search_params" property="includeDeleted">
                                    <html:option value="either">Does not matter</html:option>
                                    <html:option value="deleted-only">Deleted suppressions only</html:option>
                                    <html:option value="no-deleted">No deleted suppressions</html:option>
                                </html:select>
							</td>
						</tr>
					</table>
                    <br />
                    <table border="0">
                        <tr>
                            <td><html:submit>Search</html:submit></td>
                            <td><html:cancel /></td>
                        </tr>
                    </table>
                    <html:hidden name="suppression_search_params" property="username" value='<%= ""+username %>'/>
                </html:form>
            </td>
        </td>
    </table>



