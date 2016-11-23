package com.bgi.esm.portlets.testing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.meterware.httpunit.Button;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebImage;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

import junit.framework.TestCase;

public class WebTestCase extends TestCase
{
	//  Logging parameters
    @SuppressWarnings("unused")
	private static Logger _log                       = Logger.getLogger ( LiferayTestcase.class.getName() );
    private static String PageCaptureDirectory       = null;  // root directory to store all web page captures
    protected int step_counter                       = 1;     // page/step counter to track all web page captures
    protected static DocumentBuilderFactory factory  = null;
	protected static DocumentBuilder builder         = null;
	protected static TransformerFactory xformFactory = null;
	protected static Transformer idTransform         = null;
	
	protected boolean capture                        = true;
    protected DecimalFormat df                       = null;
    
    //  Run-specific parameters
    protected static String timestamp                = null;
    protected String test_id                         = null;
    protected int page_delay                         = 5000;

    //  Common HttpUnit objects used in the testcase
    protected WebConversation wc                     = null;
    protected WebRequest request                     = null;
    protected WebResponse response                   = null;
    protected Vector <Date> timestamps               = null;
    
    
	/**
	 * Default constructor for JUnit compatibility
	 * 
	 * @param new_test_id
	 */
	public WebTestCase ( String new_test_id )
	{
		super ( new_test_id );
		
		df = new DecimalFormat ( "0000" );
		
		if ( null == timestamps )
		{
			timestamps = new Vector <Date> ();
		}
		
		test_id = new_test_id;
		
		timestamps.add ( new Date() );
	}
	
	/**
	 * Creates the timestamp used to mark and categorize all the states of a capture session
     *
     * @return the timestamp string
	 */
	private static boolean called = false;
	protected String initTimestamp()
	{
		if ( false == called )
		{
			called = true;
			//  Create the timestamp
	        StringBuilder buffer = new StringBuilder();
	        Calendar c = Calendar.getInstance();
	        df = new DecimalFormat ( "00" );
	        buffer.append ( df.format ( c.get( Calendar.YEAR ) ) );
	        buffer.append ( "-" );
	        buffer.append ( df.format ( c.get( Calendar.MONTH )+1 ) );
	        buffer.append ( "-" );
	        buffer.append ( df.format ( c.get( Calendar.DATE ) ) );
	        buffer.append ( "-" );
	        buffer.append ( df.format ( c.get( Calendar.HOUR_OF_DAY ) ) );
	        buffer.append ( df.format ( c.get( Calendar.MINUTE ) ) );
	        buffer.append ( df.format ( c.get( Calendar.SECOND ) ) );
	        
	        timestamp = buffer.toString();
		}

        return timestamp;
	}

    /**
     * Sets the timestamp used to mark and categorize all the states of a capture session
     *
     * @param new_timestamp the timestamp to use
     */
    protected void setTimestamp ( String new_timestamp )
    {
        timestamp = new_timestamp;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize()
	{
		timestamps.add ( new Date() );
		
		int counter          = 0;
		int num_timestamps   = timestamps.size();
		
		Date last_timestamp  = timestamps.elementAt( 0 );
		Date current_time    = null;
		StringBuilder output = new StringBuilder();
		
		for ( counter = 1; counter < num_timestamps; counter++ )
		{
			current_time = timestamps.elementAt( counter );
			
			output.append ( current_time.getTime() - last_timestamp.getTime() );
			output.append ( " milliseconds\n" );
			
			last_timestamp = current_time;
		}
		
		StringBuilder page_root = new StringBuilder ();
		if (( null != PageCaptureDirectory ) && ( PageCaptureDirectory.length() > 0 ))
		{
			page_root.append ( PageCaptureDirectory );
			page_root.append ( "/" );
			page_root.append ( timestamp );
			page_root.append ( "/" );
			page_root.append ( test_id );
			
			//  Create the directoy if necessary
			File directory = new File ( page_root.toString() );
			if ( !directory.exists() )
			{
				directory.mkdirs();
			}
			
			page_root.append( "/execution_times.out" );
			
			System.out.println ( "Saving execution times: " + page_root.toString() );
			
			LogToFile ( page_root.toString(), output.toString() );
		}
	}
	
	/**
     * Debugging function to log messages to a file
     * 
     * @param filename
     * @param message
     */
    @SuppressWarnings("unused")
	protected static void LogToFile ( String filename, String message )
    {
        try
        {
            FileOutputStream outfile = new FileOutputStream ( filename );
                outfile.write ( message.getBytes() );
            outfile.close();
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
    }
    
    /**
     * Saves the webpage on to the file system 
     * @throws SAXException 
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    protected void CapturePage() throws IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	timestamps.add( new Date() );
    	
    	if ( true == capture )
    	{
	    	StringBuilder page_root = new StringBuilder ();
    		if (( null != PageCaptureDirectory ) && ( PageCaptureDirectory.length() > 0 ))
    		{
    			if ( null == timestamp )
    			{
    				initTimestamp();
    			}
    			
    			page_root.append ( PageCaptureDirectory );
    			page_root.append ( "/" );
    			page_root.append ( timestamp );
    			page_root.append ( "/" );
    			page_root.append ( test_id );
   			
    			//  Create the directoy if necessary
    			File directory = new File ( page_root.toString() );
    			if ( !directory.exists() )
    			{
    				directory.mkdirs();
    			}
    		}
    		
    		page_root.append ( "/" );
    		
    		StringBuilder capture_page = new StringBuilder ( page_root.toString() );
    		capture_page.append ( "Page" );
    		capture_page.append ( df.format ( step_counter ) );
    		capture_page.append ( ".html" );
    		
    		StringBuilder capture_attr = new StringBuilder ( page_root.toString() ); 
    		capture_attr.append ( "Attr" );
    		capture_attr.append ( df.format ( step_counter ) );
    		capture_attr.append ( ".xml" );
    		
    		step_counter++;
    		
    		System.out.println ( "Capturing to page: " + capture_page.toString() );
    		LogToFile ( capture_page.toString(), response.getText() );
    		
    		debugDumpXML ( capture_attr.toString() );
    	}

        try
        {
            Thread.sleep ( page_delay );
        }
        catch ( InterruptedException exception )
        {
        }
    }
    
    /**
     * Clicks a link on the page and captures the resulting page (debug mode)
     * 
     * @param text The name of the link to click
     * @return true if the link is successfully clicked and navigated, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean clickLink ( String text ) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
    	WebLink search_link = response.getLinkWith( text );
    	
    	if ( null == search_link )
    	{
            System.out.println ( "Searching for link with ID of: " + text );
    		search_link = response.getLinkWithID( text );
    	}
    	
    	if ( null == search_link )
    	{
            System.out.println ( "Searching for image link with called: " + text );
    		search_link = response.getLinkWithImageText( text );
    	}
    	
    	if ( null == search_link )
    	{
            System.out.println ( "Searching for link with name of: " + text );
    		search_link = response.getLinkWithName( text );
    	}
    	
    	if ( null == search_link )
    	{
    		return false;
    	}
    	else
    	{
    		return clickLink ( search_link );
    	}
    }
    
    /**
     * Clicks a link on the page and captures the resulting page (debug mode)
     * 
     * @param search_link The link to click
     * @return true if the link is successfully clicked and navigated, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean clickLink ( WebLink search_link ) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
    	if ( null == search_link )
    	{
    		debugDumpPage();
    		
    		return false;
    	}

		HttpUnitOptions.setScriptingEnabled ( false );
			response = search_link.click();
		HttpUnitOptions.setScriptingEnabled ( false );

        /*
        try
        {
            Thread.sleep ( 5000 );
        }
        catch ( InterruptedException exception )
        {
            exception.printStackTrace();
        }
        //*/
	
    	CapturePage();
    	
    	return true;
    }
    
    /**
     * Submits a form and captures the resulting page (debug mode)
     * 
     * @param form_name The name of the form to submit
     * @return true if the form is successfully submitted and the next page is reached, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean submitForm ( String form_name ) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
    	WebForm form = response.getFormWithName( form_name );
    	
    	return submitForm ( form );
    }
    
    /**
     * Submits a form and captures the resulting page (debug mode)
     * 
     * @param form the form to submit
     * @return true if the form is successfully submitted and the next page is reached, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean submitForm ( WebForm form ) throws SAXException, IOException, ParserConfigurationException, TransformerException
    {	
    	if ( null == form )
    	{
    		debugDumpPage();
    		
    		return false;
    	}
    	
    	HttpUnitOptions.setScriptingEnabled ( false );
    		response = form.submit();
     	HttpUnitOptions.setScriptingEnabled ( false );
    	
    	CapturePage();
    	
    	return true;
    }
    
    /**
     * Retrieves the WebForm object of a form that is in the current WebResponse state.
     * 
     * @param name the name of the form to return 
     * @return the WebForm object
     * @throws SAXException
     */
    public WebForm getFormWithName ( String name ) throws SAXException
    {
    	return response.getFormWithName ( name );
    }
    
    /**
     * Debug function that prints the state of the current com.meterware.httpunit.WebResponse object to stdout
     * 
     * @throws SAXException
     */
    @SuppressWarnings("unused")
	public void debugDumpPage() throws SAXException
    {
    	System.out.println ( dumpPageAttributes() );
    }
    
    public void debugDumpPage ( String filename ) throws SAXException
    {
    	LogToFile ( filename, dumpPageAttributes() );
    }
    
    public void debugDumpXML ( String filename ) throws SAXException, ParserConfigurationException, TransformerException
    {
    	LogToFile ( filename, debugDumpXMLHelper() );
    }
    
    public void debugDumpXML () throws SAXException, ParserConfigurationException, TransformerException
    {
    	System.out.println ( debugDumpXMLHelper() );
    }
    
    private void appendAttribute ( Element node, String attr_name, String attr_value )
    {
    	if (( null != attr_value ) && ( attr_value.trim().length() > 0 ))
		{
    		node.setAttribute ( attr_name, attr_value );
		}
    }
    
    private String debugDumpXMLHelper () throws SAXException, ParserConfigurationException, TransformerException
    {
    	WebImage images[]   = response.getImages();
    	WebForm forms[]     = response.getForms();
    	WebLink links[]     = response.getLinks();
    	WebTable tables[]   = response.getTables();
    	int counter         = 0;
    	
    	if ( null == builder )
    	{
    		factory      = DocumentBuilderFactory.newInstance();
    		builder      = factory.newDocumentBuilder();
    		
    		xformFactory = TransformerFactory.newInstance();
    		idTransform  = xformFactory.newTransformer();
    	}
    	String cookie_names[] = wc.getCookieNames();
    	
    	Document document     = builder.newDocument();
    	Element new_node      = null;
    	Element root          = (Element) document.createElement ( "web-page-state" );
    	document.appendChild ( root );
    	
    	Element node_cookies  = (Element) document.createElement ( "cookies" );
    	Element node_images   = (Element) document.createElement ( "images" );
    	Element node_forms    = (Element) document.createElement ( "forms"  );
    	Element node_links    = (Element) document.createElement ( "links"  );
    	Element node_tables   = (Element) document.createElement ( "tables" );
    	root.appendChild ( node_images );
    	root.appendChild ( node_forms  );
    	root.appendChild ( node_links  );
    	root.appendChild ( node_tables );
    	
    	for ( counter = 0; counter < cookie_names.length; counter++ )
    	{
    		new_node = document.createElement ( "cookie" );
    		node_cookies.appendChild ( new_node );
    		
    		appendAttribute ( new_node, "name",  cookie_names[counter] );
    		appendAttribute ( new_node, "value", wc.getCookieValue ( cookie_names[counter] ) );
    	}
    	
    	for ( counter = 0; counter < images.length; counter++ )
    	{
    		new_node = document.createElement ( "image" );
    		node_images.appendChild ( new_node );
    		
    		appendAttribute ( new_node, "name",     images[counter].getName()    );
    		appendAttribute ( new_node, "id",       images[counter].getID()      );
    		appendAttribute ( new_node, "text",     images[counter].getText()    );
    		appendAttribute ( new_node, "title",    images[counter].getTitle()   );
    		appendAttribute ( new_node, "alt-text", images[counter].getAltText() );
    		appendAttribute ( new_node, "source",   images[counter].getSource()   );
    	}
    	
    	for ( counter = 0; counter < forms.length; counter++ )
    	{
    		new_node = document.createElement ( "form" );
    		node_forms.appendChild ( new_node );
    		
    		appendAttribute ( new_node, "name",   forms[counter].getName()   );
    		appendAttribute ( new_node, "action", forms[counter].getAction() );
    		appendAttribute ( new_node, "id",     forms[counter].getID()     );
    		appendAttribute ( new_node, "method", forms[counter].getMethod() );
    		appendAttribute ( new_node, "target", forms[counter].getTarget() );
    		appendAttribute ( new_node, "title",  forms[counter].getTitle()   );
    		
    		String param_names[] = forms[counter].getParameterNames();
    		Element param_node   = null;
    		for ( int c = 0; c < param_names.length; c++ )
    		{
    			param_node = document.createElement ( "input-param" );
    			new_node.appendChild ( param_node );
    			
    			appendAttribute ( param_node, "name", param_names[c] );
    			appendAttribute ( param_node, "value", forms[counter].getParameterValue( param_names[c] ) );
    			
    			if ( forms[counter].isTextParameter( param_names[c] ) )
    			{
    				appendAttribute ( param_node, "is-text", "true" );
    			}
    			else
    			{
    				appendAttribute ( param_node, "is-text", "false" );
    			}
    		}
    	}
    	
    	for ( counter = 0; counter < links.length; counter++ )
    	{
    		new_node = document.createElement ( "link" );
    		node_links.appendChild ( new_node );
    		
    		appendAttribute ( new_node, "text",   links[counter].getText()   );
    		appendAttribute ( new_node, "name",   links[counter].getName()   );
    		appendAttribute ( new_node, "id",     links[counter].getID()     );
    		appendAttribute ( new_node, "target", links[counter].getTarget() );
    		appendAttribute ( new_node, "title",  links[counter].getTitle()  );
    	}
    	
    	for ( counter = 0; counter < tables.length; counter++ )
    	{
            new_node = document.createElement ( "table" );
            node_tables.appendChild ( new_node );
    		appendAttribute ( new_node, "name",    tables[counter].getName()     );
    		appendAttribute ( new_node, "id",      tables[counter].getID()       );
    		appendAttribute ( new_node, "class",   tables[counter].getClassName());
            appendAttribute ( new_node, "numRows", Integer.toString ( tables[counter].getRowCount() ) );
            appendAttribute ( new_node, "numCols", Integer.toString ( tables[counter].getColumnCount() ) );

            /*
            for ( int rows = 0; rows < tables[counter].getRowCount(); rows++ )
            {
                for ( int cols = 0; cols < tables[counter].getColumnCount(); cols++ )
                {
                    Element table_cell = document.createElement ( "table-cell" );
                    new_node.appendChild ( table_cell );
                    appendAttribute ( table_cell, "rowNum", Integer.toString ( rows ) );
                    appendAttribute ( table_cell, "colNum", Integer.toString ( cols ) );
                    appendAttribute ( table_cell, "text",   tables[counter].getTableCell ( rows, cols ).asText() );
                }
            }
            //*/
    	}
    	
    	DOMSource input            = new DOMSource ( document );
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	StreamResult output        = new StreamResult ( baos );
    	idTransform.transform ( input, output );
    	
    	String result              = baos.toString();
    	result = result.replaceAll ( "<web-page-state",   "\n<web-page-state"     );
    	result = result.replaceAll ( "</web-page-state>", "\n</web-page-state>"   );
    	result = result.replaceAll ( "<images/>",         "\n\t<images/>"         );
    	result = result.replaceAll ( "<image/>",          "\n\t\t<image/>"        );
    	result = result.replaceAll ( "<images",           "\n\t<images"           );
    	result = result.replaceAll ( "</images",          "\n\t</images"          );
    	result = result.replaceAll ( "<image ",           "\n\t\t<image "         );
    	
    	result = result.replaceAll ( "<forms/>",          "\t<forms/>"            );
    	result = result.replaceAll ( "<forms",            "\n\t<form"             );
    	result = result.replaceAll ( "</form>",           "\n\t\t</form>"         );
    	result = result.replaceAll ( "</forms",           "\n\t</forms"           );
    	result = result.replaceAll ( "<form ",            "\n\t\t<form "          );
    	result = result.replaceAll ( "<input-param ",     "\n\t\t\t<input-param " );
    	
    	result = result.replaceAll ( "<cookies/>",        "\t<cookies/>"          );
    	result = result.replaceAll ( "<cookies",          "\n\t<cookies"          );
    	result = result.replaceAll ( "</cookies",         "\n\t</cookies"         );
    	result = result.replaceAll ( "<cookie ",          "\n\t\t<cookie "        );
    	
    	result = result.replaceAll ( "<links/>",          "\t<links/>"            );
    	result = result.replaceAll ( "<links",            "\n\t<links"            );
    	result = result.replaceAll ( "</links",           "\n\t</links"           );
    	result = result.replaceAll ( "<link ",            "\n\t\t<link "          );
    	
    	
    	result = result.replaceAll ( "<tables/>",        "\t<tables/>"            );
    	result = result.replaceAll ( "<tables",          "\n\t<tables"            );
    	result = result.replaceAll ( "</tables",         "\n\t</tables"           );
    	result = result.replaceAll ( "<table ",          "\n\t\t<table "          );
    	result = result.replaceAll ( "</table>",         "\n\t\t</table>"         );
    	result = result.replaceAll ( "<table-cell ",     "\n\t\t\t<table-cell "   );
    	result = result.replaceAll ( "</table-cell>",    "\n\t\t\t</table-cell>"  );
    	
    	return result;
    }
    
    public String dumpPageAttributes() throws SAXException
    {
    	if ( null == response ) return null;
    	
    	StringBuilder buffer = new StringBuilder();
    	
        buffer.append ( "**********************************************************************" );
        buffer.append ( "\n*    " + response.getURL() );
        buffer.append ( "\n**********************************************************************\n" );

        int counter          = 0;
        WebForm forms[]      = response.getForms();
        String param_names[] = null;
        SubmitButton submit_buttons[] = null;
        Button buttons[]     = null;

        for ( counter = 0; counter < forms.length; counter++ )
        {
            System.out.println ( "Form name: " + forms[counter].getName() );

            param_names = forms[counter].getParameterNames();

            for ( int c = 0; c < param_names.length; c++ )
            {
            	buffer.append ( "\tParam: " );
            	buffer.append ( param_names[c] );
            	buffer.append ( "\t" );
            	buffer.append ( (forms[counter].isTextParameter(param_names[c]) ? "text" : "non-text" ) );
            	buffer.append ( "\t" );
            	buffer.append ( forms[counter].getParameterValue(param_names[c]) );
            	buffer.append ( "\n" );
            }

            submit_buttons = forms[counter].getSubmitButtons();
            if ( null != submit_buttons )
            {
                for ( int c = 0; c < submit_buttons.length; c++ )
                {
                	buffer.append ( "\tSubmit button: ( " );
                	buffer.append ( submit_buttons[c].getName() );
                	buffer.append ( ", " );
                	buffer.append ( submit_buttons[c].toString() );
                	buffer.append ( " )\n" );
                }
            }

            buttons = forms[counter].getButtons();
            if ( null != buttons )
            {
                for ( int c = 0; c < buttons.length; c++ )
                {
                	buffer.append ( "\tButton: ( " );
                	buffer.append ( buttons[c].getName() );
                	buffer.append ( ", " );
                	buffer.append ( buttons[c].toString() );
                	buffer.append ( " )\n" );
                }
            }
        }

        WebLink links[]      = response.getLinks();
        for ( counter = 0; counter < links.length; counter++ )
        {
        	buffer.append ( "Link text: " + links[counter].getText() + ", " + links[counter].getName() + ", " + links[counter].getID() );
        	buffer.append ( "\n" );
        }

        WebImage images[]    = response.getImages();
        if ( null != images )
        {
            for ( counter = 0; counter < images.length; counter++ )
            {
            	buffer.append ( "Image text: " + images[counter].getText() );
            	buffer.append ( ", " );
            	buffer.append ( images[counter].getName() );
            	buffer.append ( ", " );
            	buffer.append ( images[counter].getLink() );
            	buffer.append ( ", " );
            	buffer.append ( images[counter].getID() );
            	buffer.append ( "\n" );
            }
        }
        
        return buffer.toString();
    }
    
    /**
     * Debugging function to list all the attributes of a node
     * 
     * @param parent_node the node to view
     */
    @SuppressWarnings("unused")
	private void dumpAttributes ( Node parent_node )
    {
        NamedNodeMap attributes = parent_node.getAttributes();
        Node current_node       = null;
        int num_attributes      = attributes.getLength();
        String value            = null;

        System.out.println ( "\t\tNum attributes: " + num_attributes );
        for ( int c = 0; c < num_attributes; c++ )
        {
            current_node = attributes.item ( c );
            value = current_node.getNodeValue();

            System.out.print ( "\t\t( " );
            System.out.print ( current_node.getNodeName() );
            System.out.print ( ", " );
            System.out.print ( (null != value)? value.trim() : "null" );
            System.out.print ( " )\n" );
        }
    }
    
    /**
     * Debugging function to retrieve all the children names of
     * 
     * @param parent_node the node to retrieve from 
     */
    @SuppressWarnings("unused")
	private void dumpChildren ( Node parent_node )
    {
        NodeList nodelist = parent_node.getChildNodes();
        Node current_node = null;
        int nodelist_size = nodelist.getLength();
        String value      = null;

        System.out.println ( "Children of node: " + parent_node.getNodeName() );
        for ( int c = 0; c < nodelist_size; c++ )
        {
            current_node = nodelist.item ( c );
            value = current_node.getNodeValue();

            System.out.print ( "\t( " );
            System.out.print ( current_node.getNodeName() );
            System.out.print ( ", " );
            System.out.print ( (null != value)? value.trim() : "null" );
            System.out.print ( " )\n" );
        }
    }

    /**
     * Retrieves all the immediate DOM children of a specified node name
     * 
     * @param parent_node the parent node to investigate
     * @param node_name the specified node name
     * @return a Vector<Node> object of the matching DOM children
     */
    protected Vector<Node> findDOMChildren ( Node parent_node, String node_name )
    {
        NodeList nodelist = parent_node.getChildNodes();
        Node current_node = null;
        Vector<Node> return_value = new Vector <Node> ();
        int nodelist_size = nodelist.getLength();

        for ( int c = 0; c < nodelist_size; c++ )
        {
            current_node = nodelist.item ( c );

            if ( current_node.getNodeName().equalsIgnoreCase ( node_name ) )
            {
                return_value.add ( current_node );
            }
        }

        return return_value;
    }

    /**
     * Retrieves the org.w3c.dom.Node object representing the specified object for a Node
     * 
     * @param current_node the node to investigate
     * @param attribute_name the name of the attribute to retrieve
     * @return the org.w3c.dom.Node representing the attribute
     */
    protected Node getAttribute ( Node current_node, String attribute_name )
    {
        return current_node.getAttributes().getNamedItem ( attribute_name );
    }
    
    /**
     * Retrieves the directory where all web page captures will be stored
     *  
     * @return the directory where all web page captures will be stored
     */
    public String getPageCaptureDirectory()
    {
    	return PageCaptureDirectory;
    }
    
    public int getPageDelay()
    {
    	return page_delay;
    }
    
    public void setPageDelay ( int new_delay )
    {
    	page_delay = new_delay;
    }

    /**
     *  Tests whether or not a DOM node has a specified attribute of the specified value
     *  
     * @param parent_node the DOM node to test
     * @param attribute_name the attribute to test for
     * @param attribute_value the value to test for
     * @return true if the DOM node has the attribute with the specified value, false otherwise
     */
    protected boolean hasAttribute ( Node parent_node, String attribute_name, String attribute_value )
    {
        NamedNodeMap attributes = parent_node.getAttributes();

        if ( null == attributes ) return false;
        
        Node attribute = attributes.getNamedItem ( attribute_name );

        if ( null == attribute ) return false;

        String value = attribute.getNodeValue();

        return ((( null != value ) && ( value.equals ( attribute_value ) )) || ( attribute_value == value ));
    }
    
    /**
     * Sets the directory where all web page captures will be stored
     *  
     * @param directory the directory where all web page captures will be stored
     */
    public void setPageCaptureDirectory ( String directory )
    {
    	if ( null != directory )
    	{
    		PageCaptureDirectory = directory.trim();
    	}
    }
    
    /**
     * Returns all the links of a specified ID
     * 
     * @param id the ID to match
     * @return an array of com.meterware.httpunit.WebLink objects that represent all links on 
     *         the current page that have the specified ID
     * @throws SAXException
     */
    public WebLink[] getLinksWithID ( String id ) throws SAXException
    {
    	WebLink all_links[] = response.getLinks();

    	Vector <WebLink> links = new Vector <WebLink> ();
    	
    	for ( int counter = 0; counter < all_links.length; counter++ )
    	{
    		if ( all_links[counter].getID().equals ( id ) )
    		{
    			links.add ( all_links[counter] );
    		}
    	}

        all_links = new WebLink[links.size()];
    	
    	return links.toArray ( all_links );
    }
    
    public boolean verifyPage ( String capture_filename )
    {
    	return false;
    }
    
    public boolean verifyHasFormOfName ( String name ) throws SAXException
    {
    	WebForm form = response.getFormWithName( name );
    	
    	return ( form != null );
    }
    
    public boolean verifyHasLinkWithName ( String name ) throws SAXException
    {
    	WebLink link = response.getLinkWithName( name );
    	
    	return (null != link );
    }
    
    public boolean verifyHasLinkWithText ( String text ) throws SAXException
    {
    	WebLink link = response.getLinkWith( text );
    	
    	return ( link != link );
    }
};
