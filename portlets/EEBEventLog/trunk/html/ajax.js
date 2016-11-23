    function getAJAXObject()
    {
        var AJAX_req = false;

        //  branch for native XMLHttpRequest object
        if ( window.XMLHttpRequest )
        {
            try
            {
                AJAX_req = new XMLHttpRequest();
            }
            catch ( e )
            {
                AJAX_req = false;
            }
        }
        else if ( window.ActiveXObject )
        {
            try
            {
                AJAX_req = new ActiveXObject ( "Msxml2.XMLHTTP" );
            }
            catch ( e )
            {
                try
                {
                    AJAX_req = new ActiveXObject ( "Microsoft.XMLHTTP" );
                }
                catch ( e )
                {
                    AJAX_req = false;
                }
            }
        }

        return AJAX_req;
    }

    function processReqChange ( request, page_element )
    {
        if ( !request )
        {
            alert ( "No XmlHTTP object found" );
        }

        var ready_state = request.readyState;

        if ( 1 == ready_state )
        {
            //  Loading
            page_element.innerHTML = "Loading...";
        }
        else if ( 2 == ready_state )
        {
            //  Loaded
            page_element.innerHTML = "Loading...";
        }
        else if ( 3 == ready_state )
        {
            //  Interactive 
            page_element.innerHTML = "Loading...";
        }
        else if ( 4 == ready_state )
        {
            if ( request.status == 200 )
            {
                //  No errors
                var response_text = request.responseText;

                page_element.innerHTML = response_text;
            }
            else
            {
                alert ( "There was a problem retrieving the XML data:\n" + request.statusText );
            }
        }
    }

    function loadXMLDoc ( url, page_element )
    {
        var AJAX_req = getAJAXObject();

        if ( AJAX_req )
        {
            page_element.innerHTML = "Loading...";
            AJAX_req.onreadystatechange = function()
                    {
                        processReqChange ( AJAX_req, page_element );
                    };
            AJAX_req.open ( "GET", url, true );
            AJAX_req.send ( null );

            if ( AJAX_req.readState == 4 )
            {
                //  only if "OK"
                if ( AJAX_req.status == 200 )
                {
                    //  No errors
                    return AJAX_req.responseText;
                }
                else
                {
                    alert ( "There was a problem retrieving the XML data:\\n" + AJAX_req.statusText );
                }
            }
        }
        else
        {
            alert ( "Null request object" );

            return null;
        }
    }
