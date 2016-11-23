<%@ include file="/html/init.jsp"  %>
<%@ page import="com.bgi.esm.monitoring.portlets.Suppressions.forms.*" %>
<jsp:useBean id="entry_form" scope="request" class="com.bgi.esm.monitoring.portlets.Suppressions.forms.EditEntry" />
<%
    System.out.println ( "Edit entry.jsp" );
    Iterator i = param_map.keySet().iterator();
    String param = null;
    while ( i.hasNext() )
    {
        param = i.next().toString();
        System.out.print ( "\tParam ( " );
        System.out.print ( param );
        System.out.print ( " = " );
        System.out.print ( param_map.get( param ).toString() );
        System.out.print ( "\n" );
    }

    StringBuffer query  = null;
    int counter         = 0;
    int num_data        = 0;
    long current_time   = System.currentTimeMillis();
    Calendar calendar   = Calendar.getInstance();
    String data[]       = null;

    EditEntry data_form = entry_form;

    Toolkit.retrieveServerData ( renderRequest, data_form );

    String username     = request.getRemoteUser();

    TimeZone time_zone  = TimeZone.getDefault();
    //Cookie cookie       = Toolkit.getTimeZoneCookie ( request );
    int timezone_offset = Toolkit.computeTimeZoneOffset ( request );
    time_zone.setRawOffset ( timezone_offset );
    calendar.setTimeZone ( time_zone );

    //Connection con_sup = Toolkit.getSuppressionDatabaseConnection ( renderRequest );
    //Connection con_vpo = Toolkit.getDataSourceDatabaseConnection  ( renderRequest );

    //DatabaseMetaData dmd1 = con_sup.getMetaData();
    //DatabaseMetaData dmd2 = con_vpo.getMetaData();
 %>
<style type="text/css">
    div.suggestions {
        -moz-box-sizing:  border-box;
        box-sizing:       border-box;
        border:           1px solid black;
        position:         absolute;
        background-color: white;
        top:              200px;
        left:             500px;
        height:           200px; 
        overflow:         scroll;
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
<script language="javascript">
    var initialText    = "Type to search";

    /**
     * Removes leading and trailing spaces from a string.
     */
    function strTrim(s) 
    {
        return s.replace(/^\s+/,'').replace(/\s+$/,'');
    }

    function searchNodesFocus() 
    {
        var node_value = strTrim ( document.suppression_edit_entry.searchNodes.value );

        if ( node_value == initialText)
        {
            document.suppression_edit_entry.searchNodes.value = "";
        }
        else if (strTrim(document.suppression_edit_entry.searchNodes.value) == "") 
        {
            //document.suppression_edit_entry.searchNodes.value = initialText;
        }
    }

    function searchNodesOnblur()
    {
        var node_value = document.suppression_edit_entry.node.value;

        if ( node_value == "-" )
        {
            document.suppression_edit_entry.searchNodes.value = "";
        }
        else
        {
            document.suppression_edit_entry.searchNodes.value = document.suppression_edit_entry.node.value;
        }
    }

    function searchNodesOnkeyup() 
    {
        var mbrName = strTrim(document.suppression_edit_entry.searchNodes.value);

        if (mbrName == "") 
        {
            document.suppression_edit_entry.mrc.options[0].selected=true;
            document.suppression_edit_entry.searchNodes.value = initialText;
            return;
        }
        else if (mbrName.substring(0, initialText.length) == initialText) 
        {
            var justMbr = mbrName.substring(initialText.length, mbrName.length);
            document.suppression_edit_entry.searchNodes.value = justMbr;
            mbrName = justMbr.toUpperCase();
        }
        else {mbrName = mbrName.toUpperCase();}

        for (var i=0; i < document.suppression_edit_entry.node.options.length; i++) {

            var mName = strTrim(document.suppression_edit_entry.node.options[i].value.toUpperCase());

            // Check to see if any member name begins with the typed char(s).
            // If so, scroll to that name in the picklist.
            if (mName.indexOf(mbrName) == 0) 
            {
                document.suppression_edit_entry.byNode.checked=true;
                document.suppression_edit_entry.node.options[i].selected=true;
                return;
            }
        }
    }

    function searchAppsFocus() 
    {
        if (strTrim(document.suppression_edit_entry.searchApps.value) == initialText) 
        {
            document.suppression_edit_entry.searchApps.value = "";
        }
        else if (strTrim(document.suppression_edit_entry.searchApps.value) == "") 
        {
            //document.suppression_edit_entry.searchApps.value = initialText;
        }
    }

    function searchAppsOnblur()
    {
        var node_value = document.suppression_edit_entry.application.value;

        if ( node_value == "-" )
        {
            document.suppression_edit_entry.searchApps.value = "";
        }
        else
        {
            document.suppression_edit_entry.searchApps.value = node_value;
        }
    }

    function searchAppsOnkeyup() 
    {
        var mbrName = strTrim(document.suppression_edit_entry.searchApps.value);

        if (mbrName == "") 
        {
            document.suppression_edit_entry.application.options[0].selected=true;
            document.suppression_edit_entry.searchApps.value = initialText;
            return;
        }
        else if (mbrName.substring(0, initialText.length) == initialText) 
        {
            var justMbr = mbrName.substring(initialText.length, mbrName.length);
            document.suppression_edit_entry.searchApps.value = justMbr;
            mbrName = justMbr.toUpperCase();
        }
        else {mbrName = mbrName.toUpperCase();}

        for (var i=0; i < document.suppression_edit_entry.application.options.length; i++) {

            var mName = strTrim(document.suppression_edit_entry.application.options[i].value.toUpperCase());

            // Check to see if any member name begins with the typed char(s).
            // If so, scroll to that name in the picklist.
            if (mName.indexOf(mbrName) == 0) 
            {
                document.suppression_edit_entry.byApplication.checked =true;
                document.suppression_edit_entry.application.options[i].selected=true;
                return;
            }

        }

    }

    function searchDbServerFocus()
    {
        if (strTrim(document.suppression_edit_entry.searchDbServer.value) == initialText) 
        {
            document.suppression_edit_entry.searchDbServer.value = "";
        }
        else if (strTrim(document.suppression_edit_entry.searchDbServer.value) == "") 
        {
            //document.suppression_edit_entry.searchDbServer.value = initialText;
        }
    }

    function searchDbServerOnblur()
    {
        node_value = document.suppression_edit_entry.dbServer.value;

        if ( node_value == "-" )
        {
            document.suppression_edit_entry.searchDbServer.value = "";
        }
        else
        {
            document.suppression_edit_entry.searchDbServer.value = node_value;
        }
    }

    function searchDbServerOnkeyup() 
    {
        var mbrName = strTrim(document.suppression_edit_entry.searchDbServer.value);

        if (mbrName == "") 
        {
            document.suppression_edit_entry.mrc.options[0].selected=true;
            document.suppression_edit_entry.searchDbServer.value = initialText;
            return;
        }
        else if (mbrName.substring(0, initialText.length) == initialText) 
        {
            var justMbr = mbrName.substring(initialText.length, mbrName.length);
            document.suppression_edit_entry.searchDbServer.value = justMbr;
            mbrName = justMbr.toUpperCase();
        }
        else {mbrName = mbrName.toUpperCase();}

        for (var i=0; i < document.suppression_edit_entry.dbServer.options.length; i++) 
        {

            var mName = strTrim(document.suppression_edit_entry.node.options[i].value.toUpperCase());

            // Check to see if any member name begins with the typed char(s).
            // If so, scroll to that name in the picklist.
            if (mName.indexOf(mbrName) == 0) 
            {
                document.suppression_edit_entry.withDbServer.checked = true;
                document.suppression_edit_entry.withDbServerInstance.checked = true;
                document.suppression_edit_entry.dbServer.options[i].selected=true;
                return;
            }
        }
    }



    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //  Defining the autosuggest textbox control class
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    /**
     * An autosuggest textbox control.
     * @class
     * @scope public
     */
    function AutoSuggestControl(oTextbox /*:HTMLInputElement*/, 
                                oProvider /*:SuggestionProvider*/) 
    {
        /**
         * The currently selected suggestions.
         * @scope private
         */   
        this.cur /*:int*/ = -1;
    
        /**
         * The dropdown list layer.
         * @scope private
         */
        this.layer = null;
        
        /**
         * Suggestion provider for the autosuggest feature.
         * @scope private.
         */
        this.provider /*:SuggestionProvider*/ = oProvider;
        
        /**
         * The textbox to capture.
         * @scope private
         */
        this.textbox /*:HTMLInputElement*/ = oTextbox;
        
        //initialize the control
        this.init();
    }
    
    /**
     * Autosuggests one or more suggestions for what the user has typed.
     * If no suggestions are passed in, then no autosuggest occurs.
     * @scope private
     * @param aSuggestions An array of suggestion strings.
     * @param bTypeAhead If the control should provide a type ahead suggestion.
     */
    AutoSuggestControl.prototype.autosuggest = function (aSuggestions /*:Array*/,
                                                         bTypeAhead /*:boolean*/) 
    {
        //make sure there's at least one suggestion
        if (aSuggestions.length > 0) 
        {
            if (bTypeAhead) 
            {
               this.typeAhead(aSuggestions[0]);
            }
            
            this.showSuggestions(aSuggestions);
        } 
        else 
        {
            this.hideSuggestions();
        }
    };

    
    /**
     * Creates the dropdown layer to display multiple suggestions.
     * @scope private
     */
    AutoSuggestControl.prototype.createDropDown = function () 
    {
        var oThis = this;
    
        //create the layer and assign styles
        this.layer = document.createElement("div");
        this.layer.className = "suggestions";
        this.layer.style.visibility = "hidden";
        this.layer.style.width = this.textbox.offsetWidth;
        
        //when the user clicks on the a suggestion, get the text (innerHTML)
        //and place it into a textbox
        this.layer.onmousedown = 
        this.layer.onmouseup = 
        this.layer.onmouseover = function (oEvent) {
            oEvent = oEvent || window.event;
            oTarget = oEvent.target || oEvent.srcElement;
    
            if (oEvent.type == "mousedown") {
                oThis.textbox.value = oTarget.firstChild.nodeValue;
                oThis.hideSuggestions();
            } else if (oEvent.type == "mouseover") {
                oThis.highlightSuggestion(oTarget);
            } else {
                oThis.textbox.focus();
            }
        };
        
        document.body.appendChild(this.layer);
    };
    
    /**
     * Gets the left coordinate of the textbox.
     * @scope private
     * @return The left coordinate of the textbox in pixels.
     */
    AutoSuggestControl.prototype.getLeft = function () /*:int*/ 
    {
        var oNode = this.textbox;
        var iLeft = 0;
        
        while(oNode.tagName != "BODY") 
        {
            iLeft += oNode.offsetLeft;
            oNode = oNode.offsetParent;        
        }
        
        return iLeft;
    };
    
    /**
     * Gets the top coordinate of the textbox.
     * @scope private
     * @return The top coordinate of the textbox in pixels.
     */
    AutoSuggestControl.prototype.getTop = function () /*:int*/ 
    {
    
        var oNode = this.textbox;
        var iTop = 0;
        
        while(oNode.tagName != "BODY") {
            iTop += oNode.offsetTop;
            oNode = oNode.offsetParent;
        }
        
        return iTop;
    };
    
    /**
     * Handles three keydown events.
     * @scope private
     * @param oEvent The event object for the keydown event.
     */
    AutoSuggestControl.prototype.handleKeyDown = function (oEvent /*:Event*/) 
    {
        switch(oEvent.keyCode) 
        {
            case 38: //up arrow
                this.previousSuggestion();
                break;
            case 40: //down arrow 
                this.nextSuggestion();
                break;
            case 13: //enter
                this.hideSuggestions();
                break;
        }
    
    };

    AutoSuggestControl.prototype.onfocus = function()
    {
        this.provider.requestSuggestions ( this, false );
    }
    
    /**
     * Handles keyup events.
     * @scope private
     * @param oEvent The event object for the keyup event.
     */
    AutoSuggestControl.prototype.handleKeyUp = function (oEvent /*:Event*/) 
    {
    
        var iKeyCode = oEvent.keyCode;
    
        //for backspace (8) and delete (46), shows suggestions without typeahead
        if (iKeyCode == 8 || iKeyCode == 46) 
        {
            this.provider.requestSuggestions(this, false);
        } 
        //make sure not to interfere with non-character keys
        else if (iKeyCode < 32 || (iKeyCode >= 33 && iKeyCode < 46) || (iKeyCode >= 112 && iKeyCode <= 123)) 
        {
            //ignore
        } 
        else 
        {
            //request suggestions from the suggestion provider with typeahead
            this.provider.requestSuggestions(this, true);
        }
    };
    
    /**
     * Hides the suggestion dropdown.
     * @scope private
     */
    AutoSuggestControl.prototype.hideSuggestions = function () 
    {
        this.layer.style.visibility = "hidden";
    };
    
    /**
     * Highlights the given node in the suggestions dropdown.
     * @scope private
     * @param oSuggestionNode The node representing a suggestion in the dropdown.
     */
    AutoSuggestControl.prototype.highlightSuggestion = function (oSuggestionNode) 
    {
            
        for (var i=0; i < this.layer.childNodes.length; i++) {
            var oNode = this.layer.childNodes[i];
            if (oNode == oSuggestionNode) {
                oNode.className = "current"
            } else if (oNode.className == "current") {
                oNode.className = "";
            }
        }
    };
    
    /**
     * Initializes the textbox with event handlers for
     * auto suggest functionality.
     * @scope private
     */
    AutoSuggestControl.prototype.init = function () 
    {
        //save a reference to this object
        var oThis = this;

        this.textbox.onfocus = function (oEvent)
        {
            oThis.onfocus();
        }
        
        //assign the onkeyup event handler
        this.textbox.onkeyup = function (oEvent) 
        {
        
            //check for the proper location of the event object
            if (!oEvent) 
            {
                oEvent = window.event;
            }    
            
            //call the handleKeyUp() method with the event object
            oThis.handleKeyUp(oEvent);
        };

        //assign onkeydown event handler
        this.textbox.onkeydown = function (oEvent) 
        {
        
            //check for the proper location of the event object
            if (!oEvent) {
                oEvent = window.event;
            }    
            
            //call the handleKeyDown() method with the event object
            oThis.handleKeyDown(oEvent);
        };
        
        //assign onblur event handler (hides suggestions)    
        this.textbox.onblur = function () 
        {
            oThis.hideSuggestions();
        };
            
        //create the suggestions dropdown
        this.createDropDown();
    };
    
    /**
     * Highlights the next suggestion in the dropdown and
     * places the suggestion into the textbox.
     * @scope private
     */
    AutoSuggestControl.prototype.nextSuggestion = function () {
        var cSuggestionNodes = this.layer.childNodes;
    
        if (cSuggestionNodes.length > 0 && this.cur < cSuggestionNodes.length-1) {
            var oNode = cSuggestionNodes[++this.cur];
            this.highlightSuggestion(oNode);
            this.textbox.value = oNode.firstChild.nodeValue; 
        }
    };
    
    /**
     * Highlights the previous suggestion in the dropdown and
     * places the suggestion into the textbox.
     * @scope private
     */
    AutoSuggestControl.prototype.previousSuggestion = function () {
        var cSuggestionNodes = this.layer.childNodes;
    
        if (cSuggestionNodes.length > 0 && this.cur > 0) {
            var oNode = cSuggestionNodes[--this.cur];
            this.highlightSuggestion(oNode);
            this.textbox.value = oNode.firstChild.nodeValue;   
        }
    };
    
    /**
     * Selects a range of text in the textbox.
     * @scope public
     * @param iStart The start index (base 0) of the selection.
     * @param iLength The number of characters to select.
     */
    AutoSuggestControl.prototype.selectRange = function (iStart /*:int*/, iLength /*:int*/) {
    
        //use text ranges for Internet Explorer
        if (this.textbox.createTextRange) {
            var oRange = this.textbox.createTextRange(); 
            oRange.moveStart("character", iStart); 
            oRange.moveEnd("character", iLength - this.textbox.value.length);      
            oRange.select();
                
        //use setSelectionRange() for Mozilla
        } else if (this.textbox.setSelectionRange) {
            this.textbox.setSelectionRange(iStart, iLength);
        }     
    
        //set focus back to the textbox
        this.textbox.focus();      
    }; 
    
    /**
     * Builds the suggestion layer contents, moves it into position,
     * and displays the layer.
     * @scope private
     * @param aSuggestions An array of suggestions for the control.
     */
    AutoSuggestControl.prototype.showSuggestions = function (aSuggestions /*:Array*/) 
    {
        var oDiv = null;
        this.layer.innerHTML = "";  //clear contents of the layer
        
        for (var i=0; i < aSuggestions.length; i++) {
            oDiv = document.createElement("div");
            oDiv.appendChild(document.createTextNode(aSuggestions[i]));
            this.layer.appendChild(oDiv);
        }
        
        this.layer.style.left = this.getLeft() + "px";
        this.layer.style.top = (this.getTop()+this.textbox.offsetHeight) + "px";
        this.layer.style.visibility = "visible";
    };
    
    /**
     * Inserts a suggestion into the textbox, highlighting the 
     * suggested part of the text.
     * @scope private
     * @param sSuggestion The suggestion for the textbox.
     */
    AutoSuggestControl.prototype.typeAhead = function (sSuggestion /*:String*/) {
    
        //check for support of typeahead functionality
        if (this.textbox.createTextRange || this.textbox.setSelectionRange){
            var iLen = this.textbox.value.length; 
            this.textbox.value = sSuggestion; 
            this.selectRange(iLen, sSuggestion.length);
        }
    };

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //  Defining the auto-suggest controls
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    function ApplicationSuggestions()
    {
        this.applications =
        [
            <%
                data = data_form.getMessageGroups();
                num_data = data.length;
                for ( counter = 0; counter < num_data-1; counter++ )
                {
                    %>"<%= data[counter] %>",<%
                }
             %>
            "<%= data[counter] %>"
        ];
    }

    ApplicationSuggestions.prototype.requestSuggestions = function ( oAutoSuggestControl, bTypeAhead )
    {
        var aSuggestions  = [];
        var sTextboxValue = oAutoSuggestControl.textbox.value;
        if ( sTextboxValue.length >= 0 )
        {
            for ( var i = 0; i < this.applications.length; i++ )
            {
               if ( this.applications[i].indexOf ( sTextboxValue ) == 0 )
               {
                   aSuggestions.push(this.applications[i]);
               }
            }
        }

        oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
    }

    function ServerSuggestions()
    {
        this.servers =
        [
            <%
                data = data_form.getNodeNames();
                num_data = data.length;
                for ( counter = 0; counter < num_data-1; counter++ )
                {
                    %>"<%= data[counter] %>",<%
                }
             %>
            "<%= data[counter] %>"
        ];
    }

    ServerSuggestions.prototype.requestSuggestions = function ( oAutoSuggestControl, bTypeAhead )
    {
        var aSuggestions  = [];
        var sTextboxValue = oAutoSuggestControl.textbox.value;
        if ( sTextboxValue.length >= 0 )
        {
            for ( var i = 0; i < this.servers.length; i++ )
            {
               if ( this.servers[i].indexOf ( sTextboxValue ) == 0 )
               {
                   aSuggestions.push(this.servers[i]);
               }
            }
        }

        oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
    }

    function DBServerSuggestions()
    {
        this.db_servers =
        [
            <%
                data = data_form.getDataServers();
                num_data = data.length;
                for ( counter = 0; counter < num_data-1; counter++ )
                {
                    %>"<%= data[counter] %>",<%
                }
             %>
            "<%= data[counter] %>"
        ];
    }

    DBServerSuggestions.prototype.requestSuggestions = function ( oAutoSuggestControl, bTypeAhead )
    {
        var aSuggestions  = [];
        var sTextboxValue = oAutoSuggestControl.textbox.value;
        if ( sTextboxValue.length >= 0 )
        {
            for ( var i = 0; i < this.db_servers.length; i++ )
            {
               if ( this.db_servers[i].indexOf ( sTextboxValue ) == 0 )
               {
                   aSuggestions.push(this.db_servers[i]);
               }
            }
        }

        oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
    }


    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //  Disable autocomplete for all items.  This is to maintain compatibility with IE
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    function disableAllAutoComplete ( input_textbox )
    {
        input_elements = document.getElementsByTagName ( "input" );

        for ( counter = 0; input_elements[counter]; counter++ )
        {
            /*
            if ( input_elements[counter].classname && 
                  input_elements[counter].className.indexOf ( "disableAutoComplete" ) != -1 )
            {
                input_elements[counter].setAttribute ( "autocomplete", "off" );
            }
            //*/

            if ( input_elements.item(counter).name.toString() == "application" )
            {
                   textbox1 = input_elements.item(counter);
            }
            else if ( input_elements.item(counter).name.toString() == "node" )
            {
                textbox2 = input_elements.item(counter);
            }
            else if ( input_elements.item(counter).name.toString() == "dbServer" )
            {
                textbox3 = input_elements.item(counter);
            }
        }
    }

    function setAutoComplete1 ( input_textbox )
    {
        if ( null == suggestion_box01 )
        {
            input_textbox.setAttribute ( "autocomplete", "off" );
            suggestion_box01 = new AutoSuggestControl ( input_textbox,  new ApplicationSuggestions() );
        }
    }

    function setAutoComplete2 ()
    {
        var input_textbox = document.suppression_edit_entry.node;
        if ( null == suggestion_box02 )
        {
            input_textbox.setAttribute ( "autocomplete", "off" );
            suggestion_box02 = new AutoSuggestControl ( input_textbox,  new ServerSuggestions() );
        }
    }

    function setAutoComplete3 ( input_textbox )
    {
        if ( null == suggestion_box03 )
        {
            input_textbox.setAttribute ( "autocomplete", "off" );
            suggestion_box03 = new AutoSuggestControl ( input_textbox,  new DBServerSuggestions() );
        }
    }
  
    function changeApp()
    {
        var value = document.suppression_edit_entry.application.value;

        if (( value != '' ) && ( value != '-' ))
        {
            document.suppression_edit_entry.byApplication.checked = true;
        }
        else
        {
            document.suppression_edit_entry.byApplication.checked = false;
        }
    }

    function changeNode()
    {
        var value = document.suppression_edit_entry.node.value;

        if (( value != '' ) && ( value != '-' ))
        {
            document.suppression_edit_entry.byNode.checked = true;
        }
        else
        {
            document.suppression_edit_entry.byNode.checked = false;
        }
    }

    var input_elements;
    var textbox1;
    var textbox2;
    var textbox3;
    var suggestion_box01 = null;
    var suggestion_box02 = null;
    var suggestion_box03 = null;
</script>

    <table border="0" border="100%">
        <tr>
            <td>
                <jsp:include page="/html/nav.jsp"/>
            </td>
        </tr>
        <tr>
            <td>
                <html:link action="/view">Home</html:link>
                --&gt;
                <b>Edit an Existing Entry</b>
                <br />
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <font class="portlet-msg-error" style="font-size: x-small;">
                            <html:errors />
                            </font>
                        </td>
                    </tr>
                </table>
                <br />
                <br />
                <html:form action="/edit_entry_process" method="post" onsubmit="submitForm(this); return false;">
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.description"/> <a href="#" onclick="return displayHelpDescription();"><u>?</u></a></td>
                            <td><html:textarea name="suppression_edit_entry" property="description" cols="40" rows="6"/></td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_edit_entry" property="byApplication"><bean:message bundle="suppressions" key="form.fields.by_application"/> <a href="#" onclick="return displayHelpApplication();"><u>?</u></a></html:checkbox></td>
                            <td>
                                <html:select name="suppression_edit_entry" property="application" onchange="javascript:changeApp()" >
                                    <html:option value=''>-- Select Application --</html:option>
                                    <%
                                        data = data_form.getMessageGroups();
                                        num_data = data.length;
                                        for ( counter = 0; counter < num_data-1; counter++ )
                                        {
                                            %><html:option value='<%= data[counter]%>' ><%= data[counter] %></html:option><%
                                        }
                                     %>
                                </html:select>
                                <html:text name="suppression_edit_entry" property="searchApps" onblur="searchAppsBlur()" onfocus="searchAppsFocus()" onkeyup="searchAppsOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_edit_entry" property="byNode"><bean:message bundle="suppressions" key="form.fields.by_node"/> <a href="#" onclick="return displayHelpNode();"><u>?</u></a></html:checkbox></td>
                            <td>
                                <html:select name="suppression_edit_entry" property="node" onchange="javascript:changeNode()" >
                                    <html:option value='-'>-- Select Server --</html:option>
                                    <%
                                        data = data_form.getNodeNames();
                                        num_data = data.length;
                                        for ( counter = 0; counter < num_data; counter++ )
                                        {
                                            %><html:option value='<%= data[counter]%>' ><%= data[counter] %></html:option><%
                                        }
                                     %>
                                     <html:option value='test_node' >Test Node (for testing only)</html:option>
                                </html:select>
                                &nbsp;
                                <html:text name="suppression_edit_entry" property="searchNodes" onblur="searchNodesBlur()" onfocus="searchNodesFocus()" onkeyup="searchNodesOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_edit_entry" property="withDbServerInstance" onchange="if ( false == document.suppression_edit_entry.withDbServerInstance.checked) { document.suppression_edit_entry.withDbServer.checked=false; document.suppression_edit_entry.withDbServerMsg.checked=false; return true; }"><bean:message bundle="suppressions" key="form.fields.with_db_server_and_message"/></html:checkbox></td>
                            <td>
                                <html:checkbox name="suppression_edit_entry" property="withDbServer"><bean:message bundle="suppressions" key="form.fields.db_server"/> <a href="#" onclick="return displayHelpDatabases();"><u>?</u></a></html:checkbox>
                                <html:select name="suppression_edit_entry" property="dbServer" >
                                    <html:option value=''>-- Select Database --</html:option>
                                    <%
                                        data = data_form.getDataServers();
                                        num_data = data.length;
                                        for ( counter = 0; counter < num_data-1; counter++ )
                                        {
                                            %><html:option value='<%= data[counter]%>' ><%= data[counter] %></html:option><%
                                        }
                                     %>
                                </html:select>
                                <html:text name="suppression_edit_entry" property="searchDbServer" onblur="searchDbServerBlur()" onfocus="searchDbServerFocus()" onkeyup="searchDbServerOnkeyup()" value="Type to search"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:checkbox name="suppression_edit_entry" property="withDbServerMsg"><bean:message bundle="suppressions" key="form.fields.db_message"/> <a href="#" onclick="return displayHelpMessageText();"><u>?</u></a></html:checkbox></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:textarea name="suppression_edit_entry" property="dbServerMsg" cols="40" rows="6" onchange="document.suppression_edit_entry.withDbServerMsg.checked=true; document.suppression_edit_entry.withDbServerInstance.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <br />
                    <bean:message bundle="suppressions" key="form.message.start_suppression"/>
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.start_suppression.start_time"/> <a href="#" onclick="return displayHelpStartTime();"><u>?</u></a></td>
                            <td><html:radio name="suppression_edit_entry" property="startChoice" value="now"/></td>
                            <td><bean:message bundle="suppressions" key="form.fields.start_suppression.now"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_edit_entry" property="startChoice" value="later"/></td>
                            <td>
                                <html:select name="suppression_edit_entry" property="supStartDate" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_edit_entry" property="supStartMonth" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
                                    <html:option value="0" ><bean:message bundle="suppressions" key="form.suppression.time.month.01"/></html:option>
                                    <html:option value="1" ><bean:message bundle="suppressions" key="form.suppression.time.month.02"/></html:option>
                                    <html:option value="2" ><bean:message bundle="suppressions" key="form.suppression.time.month.03"/></html:option>
                                    <html:option value="3" ><bean:message bundle="suppressions" key="form.suppression.time.month.04"/></html:option>
                                    <html:option value="4" ><bean:message bundle="suppressions" key="form.suppression.time.month.05"/></html:option>
                                    <html:option value="5" ><bean:message bundle="suppressions" key="form.suppression.time.month.06"/></html:option>
                                    <html:option value="6" ><bean:message bundle="suppressions" key="form.suppression.time.month.07"/></html:option>
                                    <html:option value="7" ><bean:message bundle="suppressions" key="form.suppression.time.month.08"/></html:option>
                                    <html:option value="8" ><bean:message bundle="suppressions" key="form.suppression.time.month.09"/></html:option>
                                    <html:option value="9" ><bean:message bundle="suppressions" key="form.suppression.time.month.10"/></html:option>
                                    <html:option value="10"><bean:message bundle="suppressions" key="form.suppression.time.month.11"/></html:option>
                                    <html:option value="11"><bean:message bundle="suppressions" key="form.suppression.time.month.12"/></html:option>
                                </html:select>
                                <html:select name="suppression_edit_entry" property="supStartYear" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                </html:select>
                                <bean:message bundle="suppressions" key="form.suppression.time.date_time_preposition"/>
                                <html:select name="suppression_edit_entry" property="supStartHour" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_edit_entry" property="supStartMinute" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_edit_entry" property="supStartAmpm" onchange="document.suppression_edit_entry.startChoice[1].checked=true; return true;">
                                    <html:option value="AM">AM</html:option>
                                    <html:option value="PM">PM</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <html:checkbox name="suppression_edit_entry" property="removeOnReboot"><bean:message bundle="suppressions" key="form.message.remove_on_reboot"/> <a href="#" onclick="return displayHelpReboot();"><u>?</u></a></html:checkbox>
                    <br />
                    <br />
                    <bean:message bundle="suppressions" key="form.message.end_suppression"/>
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.end_suppression.end_time"/> <a href="#" onclick="return displayHelpEndTime();"><u>?</u></a></td>
                            <td><html:radio name="suppression_edit_entry" property="endChoice" value="offset"/></td>
                            <td>
                                <bean:message bundle="suppressions" key="form.fields.end_suppression.specified"/>
                                <html:text name="suppression_edit_entry" property="endChoiceNum"/>
                                <html:select name="suppression_edit_entry" property="endChoiceUnit">
                                    <html:option value="3600"><bean:message bundle="suppressions" key="form.suppression.time.unit.hours"/></html:option>
                                    <html:option value="60"><bean:message bundle="suppressions" key="form.suppression.time.unit.minutes"/></html:option>
                                    <html:option value="86400"><bean:message bundle="suppressions" key="form.suppression.time.unit.days"/></html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_edit_entry" property="endChoice" value="specified"/></td>
                            <td>
                                 <html:select name="suppression_edit_entry" property="supEndDate" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_edit_entry" property="supEndMonth" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
                                    <html:option value="0" ><bean:message bundle="suppressions" key="form.suppression.time.month.01"/></html:option>
                                    <html:option value="1" ><bean:message bundle="suppressions" key="form.suppression.time.month.02"/></html:option>
                                    <html:option value="2" ><bean:message bundle="suppressions" key="form.suppression.time.month.03"/></html:option>
                                    <html:option value="3" ><bean:message bundle="suppressions" key="form.suppression.time.month.04"/></html:option>
                                    <html:option value="4" ><bean:message bundle="suppressions" key="form.suppression.time.month.05"/></html:option>
                                    <html:option value="5" ><bean:message bundle="suppressions" key="form.suppression.time.month.06"/></html:option>
                                    <html:option value="6" ><bean:message bundle="suppressions" key="form.suppression.time.month.07"/></html:option>
                                    <html:option value="7" ><bean:message bundle="suppressions" key="form.suppression.time.month.08"/></html:option>
                                    <html:option value="8" ><bean:message bundle="suppressions" key="form.suppression.time.month.09"/></html:option>
                                    <html:option value="9" ><bean:message bundle="suppressions" key="form.suppression.time.month.10"/></html:option>
                                    <html:option value="10"><bean:message bundle="suppressions" key="form.suppression.time.month.11"/></html:option>
                                    <html:option value="11"><bean:message bundle="suppressions" key="form.suppression.time.month.12"/></html:option>
                                </html:select>
                                <html:select name="suppression_edit_entry" property="supEndYear" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                </html:select>
                                <bean:message bundle="suppressions" key="form.suppression.time.date_time_preposition"/>
                                <html:select name="suppression_edit_entry" property="supEndHour" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_edit_entry" property="supEndMinute" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_edit_entry" property="supEndAmpm" onchange="document.suppression_edit_entry.endChoice[1].checked=true; return true;">
                                    <html:option value="AM">AM</html:option>
                                    <html:option value="PM">PM</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <!--
                    <br />
                    <b>Advanced Options</b>
                    <table border="1">
                        <tr>
                            <td width="25%">
                                
                                    <b>Days of the Week</b>
                                    <br />
                                    <br />
                                    This suppression will only be active on the following days of the week within the time period specified above.
                                
                            </td>
                            <td>
                                <table border="0">
                                    <tr>
                                        <td>
                                            <html:checkbox name="suppression_edit_entry" property="onlySunday">Sunday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_edit_entry" property="onlyMonday">Monday</html:checkbox>
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_edit_entry" property="onlyTuesday">Tuesday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_edit_entry" property="onlyWednesday">Wednesday</html:checkbox>
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_edit_entry" property="onlyThursday">Thursday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_edit_entry" property="onlyFriday">Friday</html:checkbox>
                                            <br />
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_edit_entry" property="onlySaturday">Saturday</html:checkbox>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    -->
                    <br />
                    <html:checkbox name="suppression_edit_entry" property="notifyBeforeExpire"><bean:message bundle="suppressions" key="form.message.notify_before_expire"/> <a href="#" onclick="return displayHelpNotifyBeforeExpire();"><u>?</u></a></html:checkbox>
                    <br />
                    <table border="1">
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.notify_num_min_prior"/></td>
                            <td><html:text name="suppression_edit_entry" property="numMinutesPrior" onchange="document.suppression_edit_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                        <tr>
                            <td><bean:message bundle="suppressions" key="form.fields.notify_email"/></td>
                            <td><html:text name="suppression_edit_entry" property="email" onchange="document.suppression_edit_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                            <td><html:submit><bean:message bundle="suppressions" key="form.suppression.button.update_suppression"/></html:submit></td>
                            <td><html:cancel /></td>
                        </tr>
                    </table>
                    <html:hidden name="suppression_edit_entry" property="suppressId"/>
                    <html:hidden name="suppression_edit_entry" property="username" value='<%= ""+username %>'/>
                    <html:hidden name="suppression_edit_entry" property="timeZoneOffset" value='<%= Integer.toString ( timezone_offset ) %>'/>
                </html:form>
            </td>
        </tr>
    </table>

<script language="Javascript">
    function initStartEndTime()
    {
        <%
            String am_pm          = (calendar.get ( Calendar.HOUR_OF_DAY ) < 12 )? "AM" : "PM";
            int hour              = calendar.get ( Calendar.HOUR );
            String calendar_hour  = ( 0 != hour )? Integer.toString ( hour ) : "12";
         %>
        document.suppression_edit_entry.supStartDate.value   = "<%= calendar.get ( Calendar.DATE )   %>";
        document.suppression_edit_entry.supStartMonth.value  = "<%= calendar.get ( Calendar.MONTH )  %>";
        document.suppression_edit_entry.supStartYear.value   = "<%= calendar.get ( Calendar.YEAR )   %>";
        document.suppression_edit_entry.supStartHour.value   = "<%= calendar_hour                    %>";
        document.suppression_edit_entry.supStartMinute.value = "<%= calendar.get ( Calendar.MINUTE ) %>";
        document.suppression_edit_entry.supStartAmpm.value   = "<%= am_pm %>";

        document.suppression_edit_entry.supEndDate.value     = "<%= calendar.get ( Calendar.DATE )   %>";
        document.suppression_edit_entry.supEndMonth.value    = "<%= calendar.get ( Calendar.MONTH )  %>";
        document.suppression_edit_entry.supEndYear.value     = "<%= calendar.get ( Calendar.YEAR )   %>";
        document.suppression_edit_entry.supEndHour.value     = "<%= calendar_hour                    %>";
        document.suppression_edit_entry.supEndMinute.value   = "<%= calendar.get ( Calendar.MINUTE ) %>";
        document.suppression_edit_entry.supEndAmpm.value     = "<%= am_pm %>";
    }

    function displayHelpDescription()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.description"/>' );

        return false;
    }

    function displayHelpApplication()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.application"/>' );

        return false;
    }

    function displayHelpNode()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.node"/>' );

        return false;
    }

    function displayHelpDatabases()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.databases"/>' );

        return false;
    }

    function displayHelpMessageText()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.message_text"/>' );

        return false;
    }

    function displayHelpReboot()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.reboot"/>' );

        return false;
    }

    function displayHelpStartTime()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.start_time"/>' );

        return false;
    }

    function displayHelpEndTime()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.end_time"/>' );

        return false;
    }

    function displayHelpNotifyBeforeExpire()
    {
        alert ( '<bean:message bundle="help" key="suppressions.help.notify_before_expire"/>' );

        return false;
    }

    document.suppression_edit_entry.setAttribute ( "autocomplete", "off" );
    //initStartEndTime();
</script>
