<%@ include file="/html/init.jsp"  %>
<%
    String username = request.getRemoteUser();

    if ( username != null )
    {
 %>
<script name="javascript">
    var navigation_add_entry   = "";
    var navigation_home        = "";
    var navigation_current_my  = "";
    var navigation_current_all = "";
    var navigation_history_my  = "";
    var navigation_history_all = "";

    function navigateToAllCurrent()
    {
        var navigation_link = document.getElementById ( "appCurrentEntriesAll" );

        navigation_current_all = navigation_link.href;

        self.location = navigation_current_all;
    }

    function navigateToMyCurrent()
    {
        var navigation_link = document.getElementById ( "appCurrentEntriesMine" );

        navigation_current_my = navigation_link.href;

        self.location = navigation_current_my;
    }

    function navigateToMyHistory()
    {
        var navigation_link = document.getElementById ( "appHistoryMine" );

        navigation_history_my = navigation_link.href;

        self.location = navigation_history_my;
    }

    function navigateToHome()
    {
        var navigation_link = document.getElementById ( "appSuppressionHome" );

        navigation_home = navigation_link.href;

        self.location = navigation_home;
    }

    function navigateToAddEntry()
    {
        var navigation_link = document.getElementById ( "appAddEntry" );

        navigation_add_entry = navigation_link.href;

        self.location = navigation_add_entry;
    }
</script>
   <html:link action="/view"             linkName="appSuppressionHome"    styleId="appSuppressionHome"    onblur="navigation_suppression_home=this.href" />
   <html:link action="/add_entry"        linkName="appAddEntry"           styleId="appAddEntry"           onblur="navigation_add_entry=this.href" />
   <html:link action="/add_entry_simple" linkName="appAddEntry"           styleId="appAddEntrySimple"     onblur="navigation_add_entry=this.href" />
   <html:link action="/edit"             linkName="portletSettings"       styleId="portletSettings"/>
   <html:link action="/current"          linkName="appCurrentEntriesAll"  styleId="appCurrentEntriesAll"  onblur="navigation_current_all=this.href" />
   <html:link action="/my_current"       linkName="appCurrentEntriesMine" styleId="appCurrentEntriesMine" onblur="navigation_current_my=this.href"  />
   <html:link action="/my_history"       linkName="appHistoryMine"        styleId="appHistoryMine"        onblur="navigation_history_my=this.href"  />
        <table width="100%" border="1">
            <tr>
                <td rowspan="2">
                    
                        <center><input type="reset" name="Reset" value='<bean:message bundle="suppressions" key="navigation.home"/>' onclick="navigateToHome()" /></center>
                    
                </td>
                <td rowspan="2">
                    
                        <center><input type="reset" name="Reset" value='<bean:message bundle="suppressions" key="navigation.add_entry"/>' onclick="navigateToAddEntry()" /></center>
                    
                    <br>
                    <br>
                    <html:link action="/add_entry_simple" linkName="appAddEntry"           styleId="appAddEntrySimple"     onblur="navigation_add_entry=this.href" ></html:link>
                </td>
                <td>
                    <table border="0" width="100%">
                        <tr>
                            <td><center><input type="reset" name="Reset" value='<bean:message bundle="suppressions" key="navigation.current_entries"/>' onclick="navigateToAllCurrent();"/></center></td>
                            <td><center><input type="reset" name="Reset" value='<bean:message bundle="suppressions" key="navigation.my_entries"/>' onclick="navigateToMyCurrent();"/></center></td>
                        </tr>
                    </table>
                </td>
                <td>
                    <center><input type="reset" name="Reset" value='<bean:message bundle="suppressions" key="navigation.my_historical"/>' onclick="navigateToMyHistory();"/></center>
                </td>
            </tr>
            <tr>
                <td>
                    Search Current Entries
                    <br />
                    <html:form action="/search_current_process" method="post">
                    <table width="100%" border="0">
                        <tr>
                            <td><bean:message bundle="suppressions" key="navigation.search_current.app_contains"/></td>
                            <td><html:text name="suppression_search_params" property="application"/></td>
                        </tr>
                        <tr>
                            <td><bean:message bundle="suppressions" key="navigation.search_current.system_contains"/></td>
                            <td><html:text name="suppression_search_params" property="node"/></td>
                        </tr>
                        <tr>
                            <td><html:submit>Search</html:submit></td>
                            <td><html:link action="/search_current" linkName="appHistorySearchCurrent"><u><bean:message bundle="suppressions" key="navigation.search_current.advanced_search"/></u></html:link></td>
                        </tr>
                    </table>
                    </html:form>
                </td>
                <td>
                    Search Historical Entries
                    <br />
                    <html:form action="/search_historical_process" method="post" onsubmit="submitForm(this); return false;">
                    <table width="100%" border="0">
                        <tr>
                            <td><bean:message bundle="suppressions" key="navigation.search_historical.app_contains"/></td>
                            <td>
                                <html:text name="suppression_search_params" property="application" />
                            </td>
                        </tr>
                        <tr>
                            <td><bean:message bundle="suppressions" key="navigation.search_historical.system_contains"/></td>
                            <td>
                                <html:text name="suppression_search_params" property="node" />
                            </td>
                        </tr>
                        <tr>
                            <td><html:submit>Search</html:submit></td>
                            <td><html:link action="/search_historical" linkName="appHistorySearchAll"><u><bean:message bundle="suppressions" key="navigation.search_historical.advanced_search"/></u></html:link></td>
                        </tr>
                    </table>
                    </html:form>
                </td>
            </tr>
        </table>
<%
    }
    else
    {
 %>
        <table border="1" width="100%">
            <tr>
                <td><html:link action="/view" linkName="appHome"><bean:message bundle="suppressions" key="navigation.home"/></html:link></td>
                <td>
                    <html:link action="/current?some_param=some_value" linkName="appCurrentEntriesAll"><bean:message bundle="suppressions" key="navigation.current_entries"/></html:link>
                </td>
                <td>
                    <html:link action="/search_historical" linkName="appHistorySearchAll"><bean:message bundle="suppressions" key="navigation.search_all_historical"/></html:link>
                    <br />
                    <html:link action="/search_current" linkName="appHistorySearchCurrent"><bean:message bundle="suppressions" key="navigation.search_all_current"/></html:link>
                </td>
            </tr>
        </table>
<%
    }
 %>
