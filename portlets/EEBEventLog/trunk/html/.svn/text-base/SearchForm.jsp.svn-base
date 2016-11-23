<%@ include file="/html/init.jsp" %>
<%
    Calendar calendar   = Calendar.getInstance();
    int counter         = 0;
%>
<script language="javascript" type="text/javascript" src="/EEBEventLog/html/ajax.js"></script>
<table border="0" width="100%">
    <tr>
        <td>
            <html:form action="/SearchFormProcess.do?method=search" method="post">
                <table border="0" width="100%">
                    <tr>
                        <td>Message Group Name: </td>
                        <td colspan="3">
                            <div id="searchFormGroup">Loading...</div>
                        </td>
                    </tr>
                    <tr>
                        <td>Application: </td>
                        <td colspan="3"><html:text name="search_form" property="searchApplication" /></td>
                    </tr>
                    <tr>
                        <td>Events After: </td>
                        <td>
                            <table border="0">
                                <tr>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartYear"     >
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)-1) %>'><%= calendar.get(Calendar.YEAR)-1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartMonth"    >
                                            <html:option value="0" ><bean:message bundle="SearchForm" key="time.month.01"/></html:option>
                                            <html:option value="1" ><bean:message bundle="SearchForm" key="time.month.02"/></html:option>
                                            <html:option value="2" ><bean:message bundle="SearchForm" key="time.month.03"/></html:option>
                                            <html:option value="3" ><bean:message bundle="SearchForm" key="time.month.04"/></html:option>
                                            <html:option value="4" ><bean:message bundle="SearchForm" key="time.month.05"/></html:option>
                                            <html:option value="5" ><bean:message bundle="SearchForm" key="time.month.06"/></html:option>
                                            <html:option value="6" ><bean:message bundle="SearchForm" key="time.month.07"/></html:option>
                                            <html:option value="7" ><bean:message bundle="SearchForm" key="time.month.08"/></html:option>
                                            <html:option value="8" ><bean:message bundle="SearchForm" key="time.month.09"/></html:option>
                                            <html:option value="9" ><bean:message bundle="SearchForm" key="time.month.10"/></html:option>
                                            <html:option value="10"><bean:message bundle="SearchForm" key="time.month.11"/></html:option>
                                            <html:option value="11"><bean:message bundle="SearchForm" key="time.month.12"/></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartDate"    >
                                        <%
                                            for ( counter = 1; counter <= 31; counter++ )
                                            {
                                            %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                            }
                                        %>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartHour"    >
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
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartMinute"  >
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
                                        </html:select >
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeStartAmPm"   >
                                            <html:option value="AM" >AM</html:option>
                                            <html:option value="PM" >PM</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>Events Before: </td>
                        <td>
                            <table border="0">
                                <tr>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndYear"     >
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)-1) %>'><%= calendar.get(Calendar.YEAR)-1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                            <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndMonth"    >
                                            <html:option value="0" ><bean:message bundle="SearchForm" key="time.month.01"/></html:option>
                                            <html:option value="1" ><bean:message bundle="SearchForm" key="time.month.02"/></html:option>
                                            <html:option value="2" ><bean:message bundle="SearchForm" key="time.month.03"/></html:option>
                                            <html:option value="3" ><bean:message bundle="SearchForm" key="time.month.04"/></html:option>
                                            <html:option value="4" ><bean:message bundle="SearchForm" key="time.month.05"/></html:option>
                                            <html:option value="5" ><bean:message bundle="SearchForm" key="time.month.06"/></html:option>
                                            <html:option value="6" ><bean:message bundle="SearchForm" key="time.month.07"/></html:option>
                                            <html:option value="7" ><bean:message bundle="SearchForm" key="time.month.08"/></html:option>
                                            <html:option value="8" ><bean:message bundle="SearchForm" key="time.month.09"/></html:option>
                                            <html:option value="9" ><bean:message bundle="SearchForm" key="time.month.10"/></html:option>
                                            <html:option value="10"><bean:message bundle="SearchForm" key="time.month.11"/></html:option>
                                            <html:option value="11"><bean:message bundle="SearchForm" key="time.month.12"/></html:option>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndDate"     >
                                        <%
                                            for ( counter = 1; counter <= 31; counter++ )
                                            {
                                            %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                            }
                                        %>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndHour"     >
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
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndMinute"   >
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
                                        </html:select >
                                    </td>
                                    <td>
                                        <html:select name="search_form" property="searchTimeEndAmPm"   >
                                            <html:option value="AM" >AM</html:option>
                                            <html:option value="PM" >PM</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4"><center><html:submit>Search Events</html:submit></center></td>
                    </tr>
                </table>
            </html:form>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="/html/nav.jsp" />
        </td>
    </tr>
</table>
<script language="javascript">
    var searchFormNode  = document.getElementById ( "searchFormNode"  );
    var searchFormGroup = document.getElementById ( "searchFormGroup" );

    loadXMLDoc ( "/EEBEventLog/AJAXSearchFormGroup.do", searchFormGroup );
</script>
