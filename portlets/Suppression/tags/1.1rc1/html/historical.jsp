<%@ include file="/html/init.jsp" %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />
<%
    String username     = request.getRemoteUser();
    int suppress_id     = 0;

    Statement stmt      = Toolkit.getSuppressionDatabaseStatement( renderRequest );
    ResultSet results   = Toolkit.getAllHistoricalSuppressions( renderRequest );
    String width1       = "10%";
    String width2       = "10%";
    String width3       = "10%";
    String width4       = "25%";
    String width5       = "25%";
    String width6       = "10%";
    String width7       = "*";
    String scrollbar    = "12";
%>


    <table border="0" width="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <b>Historical Entries</b> for <%= username %>
                <br />
                <br />
                <div style="overflow: auto; width: 100%; height: 300; border-right: 1px gray solid; border-left: 1px gray solid; border-bottom: 1px gray solid; border-top: 1px gray solid; padding:0px; margin: 0px">
                    <table border="1" width="100%">
                        <col width="<%= width1 %>"><col width="<%= width2 %>"><col width="<%= width3 %>"><col width="<%= width4 %>"><col width="<%= width5 %>"><col width="<%= width6 %>"><col width="<%= width7 %>">
                        <%
                            while ( results.next() )
                            {
                                suppress_id = results.getInt ( 1 );
                            %>
                            <tr>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= (null != results.getString ( "group_nm" ))? results.getString ( "group_nm" ) : "All Applications" %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= (null != results.getString ( "node_nm" ))? results.getString ( "node_nm" ) : "All Nodes"  %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= (null != results.getString ( "instance" ))? results.getString ( "instance" ) : "<center>-</center>" %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= (null != results.getString ( "message" ))? results.getString ( "message" ) : "<center>-</center>" %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= (null != results.getString ( "suppress_desc_txt" ))? results.getString ( "suppress_desc_txt" ) : "<center>-</center>" %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= results.getString ( "start_utc_tms" ) %></html:link></td>
                                <td><html:link action='<%= "/view_entry?suppress_id="+suppress_id %>'><%= results.getString ( "end_utc_tms" ) %></html:link></td>
                            </tr>
                            <%
                            }
                         %>
                    </table>
                </div>
				<br />
            </td>
        </tr>
    </table>
</font>
<%
    stmt.close();
 %>
