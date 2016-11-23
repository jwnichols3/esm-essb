<%@ include file="/html/init.jsp"  %>
<jsp:useBean id="data_form" scope="request" class="com.bgi.esm.portlets.Suppression.forms.AddEntry" />
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
<div id="struts-suppression-div">
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
<script language="javascript">
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
	//  Disable autocomplete for all items.  This is to maintain compatibility with IE
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
	function disableAllAutoComplete()
	{
		if ( document.getElementsByTagName )
		{
			var inputElements = document.getElementsByTagName ( "input" );
			var counter = 0;

			for ( counter = 0; inputElements[counter]; counter++ )
			{
				if ( inputElements[counter].classname && 
				 	 inputElements[counter].className.indexOf ( "disableAutoComplete" ) != -1 )
				{
					inputElements[counter].setAttribute ( "autocomplete", "off" );
				}
			}
		}
	}

	disableAllAutoComplete();

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

    var current_div           = document.getElementById ( "struts-suppression-div" );
    var div_links             = current_div.getElementsByTagName ( "a" );
    var input_elements        = current_div.getElementsByTagName ( "input" );
    var counter               = 0;
    var ajax_retrieve_servers;
    var textbox1;
    var textbox2;
    var textbox3;
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
                <b>Add a New Entry</b>
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
                <html:form action="/add_entry_process"  >
                    <table border="1">
                        <tr>
                            <td>Description</td>
                            <td><html:textarea name="suppression_add_entry" property="description" cols="40" rows="6"/></td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="byApplication">By Application</html:checkbox></td>
                            <td>
                                <html:text name="suppression_add_entry" property="application"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="byNode">By Node</html:checkbox></td>
                            <td>
                                <html:text name="suppression_add_entry" property="node"/>
                            </td>
                        </tr>
                        <tr>
                            <td><html:checkbox name="suppression_add_entry" property="withDbServerInstance" onchange="if ( false == document.suppression_add_entry.withDbServerInstance.checked) { document.suppression_add_entry.withDbServer.checked=false; document.suppression_add_entry.withDbServerMsg.checked=false; return true; }">With</html:checkbox></td>
                            <td>
                                <html:checkbox name="suppression_add_entry" property="withDbServer">DB Server</html:checkbox>
                                <html:text name="suppression_add_entry" property="dbServer"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:checkbox name="suppression_add_entry" property="withDbServerMsg">Message Text</html:checkbox></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:textarea name="suppression_add_entry" property="dbServerMsg" cols="40" rows="6" onchange="document.suppression_add_entry.withDbServerMsg.checked=true; document.suppression_add_entry.withDbServerInstance.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <br />
                    <table border="1">
                        <tr>
                            <td>Start Time</td>
                            <td><html:radio name="suppression_add_entry" property="startChoice" value="now"/></td>
                            <td>Now</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_add_entry" property="startChoice" value="later"/></td>
                            <td>
                                <html:select name="suppression_add_entry" property="supStartDate" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_add_entry" property="supStartMonth" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
                                    <html:option value="0" >January</html:option>
                                    <html:option value="1" >February</html:option>
                                    <html:option value="2" >March</html:option>
                                    <html:option value="3" >April</html:option>
                                    <html:option value="4" >May</html:option>
                                    <html:option value="5" >June</html:option>
                                    <html:option value="6" >July</html:option>
                                    <html:option value="7" >August</html:option>
                                    <html:option value="8" >September</html:option>
                                    <html:option value="9" >October</html:option>
                                    <html:option value="10">November</html:option>
                                    <html:option value="11">December</html:option>
                                </html:select>
                                <html:select name="suppression_add_entry" property="supStartYear" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+2) %>'><%= calendar.get(Calendar.YEAR)+2 %></html:option>
                                </html:select>
                                at
                                <html:select name="suppression_add_entry" property="supStartHour" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_add_entry" property="supStartMinute" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_add_entry" property="supStartAmpm" onchange="document.suppression_add_entry.startChoice[1].checked=true; return true;">
                                    <html:option value="AM">AM</html:option>
                                    <html:option value="PM">PM</html:option>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <html:checkbox name="suppression_add_entry" property="removeOnReboot">Please remove this suppression when this machine reboots</html:checkbox>
                    <br />
                    <br />
                    <table border="1">
                        <tr>
                            <td>End Time</td>
                            <td><html:radio name="suppression_add_entry" property="endChoice" value="offset"/></td>
                            <td>
                                In
                                <html:text name="suppression_add_entry" property="endChoiceNum"/>
                                <html:select name="suppression_add_entry" property="endChoiceUnit">
                                    <html:option value="3600">hours</html:option>
                                    <html:option value="60">minutes</html:option>
                                    <html:option value="86400">days</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><html:radio name="suppression_add_entry" property="endChoice" value="specified"/></td>
                            <td>
                                 <html:select name="suppression_add_entry" property="supEndDate" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
                                <%
                                    for ( counter = 1; counter <= 31; counter++ )
                                    {
                                    %><html:option value='<%= ""+counter %>' ><%= counter %></html:option><%
                                    }
                                %>
                                </html:select>
                                <html:select name="suppression_add_entry" property="supEndMonth" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_add_entry" property="supEndYear" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+0) %>'><%= calendar.get(Calendar.YEAR)+0 %></html:option>
                                    <html:option value='<%= ""+(calendar.get(Calendar.YEAR)+1) %>'><%= calendar.get(Calendar.YEAR)+1 %></html:option>
                                </html:select>
                                at
                                <html:select name="suppression_add_entry" property="supEndHour" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_add_entry" property="supEndMinute" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
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
                                <html:select name="suppression_add_entry" property="supEndAmpm" onchange="document.suppression_add_entry.endChoice[1].checked=true; return true;">
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
                                            <html:checkbox name="suppression_add_entry" property="onlySunday">Sunday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_add_entry" property="onlyMonday">Monday</html:checkbox>
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_add_entry" property="onlyTuesday">Tuesday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_add_entry" property="onlyWednesday">Wednesday</html:checkbox>
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_add_entry" property="onlyThursday">Thursday</html:checkbox>
                                            <br />
                                            <html:checkbox name="suppression_add_entry" property="onlyFriday">Friday</html:checkbox>
                                            <br />
                                        </td>
                                        <td>
                                            <html:checkbox name="suppression_add_entry" property="onlySaturday">Saturday</html:checkbox>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    -->
                    <br />
                    <html:checkbox name="suppression_add_entry" property="notifyBeforeExpire">Notify when this timeframe is about to expire</html:checkbox>
                    <br />
                    <table border="1">
                        <tr>
                            <td>Minutes prior</td>
                            <td><html:text name="suppression_add_entry" property="numMinutesPrior" onchange="document.suppression_add_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                        <tr>
                            <td>E-mail</td>
                            <td><html:text name="suppression_add_entry" property="email" onchange="document.suppression_add_entry.notifyBeforeExpire.checked=true; return true;"/></td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                            <td><html:submit>Create Notification</html:submit></td>
                            <td><html:cancel /></td>
                        </tr>
                    </table>
                    <html:hidden name="suppression_add_entry" property="username" value='<%= ""+username %>'/>
                </html:form>
            </td>
        </tr>
    </table>

<script language="javascript">
    for ( counter = 0; counter < input_elements.length; counter++ )
    {
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

    for ( counter = 0; counter < div_links.length; counter++ )
    {
        if ( div_links.item(counter).name.toString() == "ajax_get_servers" )
        {
            ajax_get_servers = div_links.item(counter).href;
        }
    }

	var suggestion_box01 = new AutoSuggestControl ( textbox1,  new ApplicationSuggestions() );
	var suggestion_box02 = new AutoSuggestControl ( textbox2,  new ServerSuggestions() );
	var suggestion_box03 = new AutoSuggestControl ( textbox3,  new DBServerSuggestions() );
</script>
</div>
