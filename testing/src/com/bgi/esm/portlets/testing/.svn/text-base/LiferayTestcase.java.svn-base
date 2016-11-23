package com.bgi.esm.portlets.testing;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.meterware.httpunit.ClientProperties;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;

import com.bgi.esm.portlets.testing.WebTestCase;

/**
 *  Provides the common functionality to test portlets under Liferay Enterprise Portal. 
 */
public class LiferayTestcase extends WebTestCase
{
    final private static Logger _log = Logger.getLogger ( LiferayTestcase.class );

    //  Connection parameters to Liferay
    protected String Hostname                        = null;
    protected String Username                        = null;
    protected String Password                        = null;
    protected String Community                       = null;
    
    //  Intermediate results parameters
    protected HashMap <String, Integer> communities  = null;
    protected String community_names[]               = null;
    protected String portlets_on_page[]              = null;
    protected Date start_time                        = null;
     

    public static void main ( String args[] ) throws Exception
    {
        DecimalFormat df = new DecimalFormat ( "00" );
        //for ( int counter = 0; counter < 10; counter++ )
        for ( int counter = 0; counter < 2; counter++ )
        {
            sampleTestCase ( "Liferay" + df.format ( counter ) );
        }
    }

    public static void sampleTestCase( String testcase_name ) throws Exception
    {
        LiferayTestcase testcase  = new LiferayTestcase ( testcase_name );
        testcase.initialize ( "localhost", "Desktop", "linden", "testdsl0502-05" );
        String portlets_on_page[] = null;

        _log.info ( "Num portlets: " + testcase.getPortletNodes().size() );
        portlets_on_page = testcase.getPortletsOnPage();
        if ( _log.isInfoEnabled() )
        {
            for ( int counter = 0; counter < portlets_on_page.length; counter++ )
            {
            	_log.info ( "\t" + portlets_on_page[counter] );
            }
        }
        if ( testcase.isPortletOnPage( "Ping Monitor Status" ) )
        {
            _log.info ( "Found Ping Monitor Portlet" );
        }
        String community_names[] = testcase.getCommunities();
        _log.info ( "Num communities: " + community_names.length );
        if ( _log.isInfoEnabled() )
        {
            for ( int counter = 0; counter < community_names.length; counter++ )
            {
            	_log.info ( "\t" + community_names[counter] );
            }
        }
        testcase.goToCommunity  ( "Desktop" );
        _log.info ( "Num portlets: " + testcase.getPortletNodes().size() );
        portlets_on_page = testcase.getPortletsOnPage();
        if ( _log.isInfoEnabled() )
        {
            for ( int counter = 0; counter < portlets_on_page.length; counter++ )
            {
            	_log.info ( "\t" + portlets_on_page[counter] );
            }
        }
        if ( testcase.isPortletOnPage( "Ping Monitor Status" ) )
        {
        	_log.info ( "Found Ping Monitor Portlet" );
        }
        testcase.logout();
    }
    
    /**
     * Default constructor for tests based on unit.framework.TestCase 
     * 
     * @param param
     */
    public LiferayTestcase ( String new_test_id )
    {
    	super ( new_test_id );
    	
    	HttpUnitOptions.setScriptingEnabled ( false );

        if ( null == PageCaptureDirectory )
        {
            PageCaptureDirectory = "c:\\test\\httpunit";
        }
    }
    
    /**
     * Retrieves the names of all the communities available to this user
     * 
     * @return a String array of all the communities available to this user
     * @throws SAXException
     */
    public String[] getCommunities () throws SAXException
    {
        if ( null == communities )
        {
            Vector <Node> community_nodes = new Vector <Node> ();
            Vector <String> names         = new Vector <String> ();

            communities = new HashMap <String, Integer> ();

            traverseTree ( response.getDOM(), community_nodes, "name", "my_communities_sel" );

            //  There should only be one of this type
            if ( community_nodes.size() > 0 )
            {
                Node node = community_nodes.elementAt ( 0 );
                Node attr = null;
                Node name = null;

                community_nodes = findDOMChildren ( node, "option" );
        
                for ( int counter = 0; counter < community_nodes.size(); counter++ )
                {
                    node = community_nodes.elementAt ( counter );
                    attr = getAttribute ( node, "value" );
                    name = node.getChildNodes().item(0);
                
                    names.add ( name.getNodeValue() );

                    communities.put ( name.getNodeValue(), new Integer ( attr.getNodeValue() ) );
                }
            }
            
            Object o[]      = names.toArray();
            community_names = new String[o.length];
            
            for ( int counter = 0; counter < o.length; counter++ )
            {
            	community_names[counter] = o[counter].toString();
            }
        }

        return community_names;
    }

    /**
     * Helper function to retrieve the portlet name from a DOM branch that has been identified as a portlet
     * @param current_node
     * @return
     * @throws SAXException
     */
    private String getPortletName ( Node current_node ) throws SAXException
    {
    	//  The node we are interested in is nested 2 levels deeper than the current node.  Each of the levels leading
    	//  up to the node we want is composed of a <div /> tag with a single descendant
    	NodeList children   = current_node.getChildNodes();
        Node child          = null;
        Node portlet_name   = null;
        String return_value = null;
        int counter         = 0;
        
        for ( counter = 0; counter < children.getLength(); counter++ )
        {
        	child = children.item( counter );
        	
        	if ( child.getNodeName().equalsIgnoreCase( "DIV" ) )
        	{
        		portlet_name = child;
        		break;
        	}
        }
        
        if ( null != portlet_name )
        {
        	while ( null != portlet_name.getFirstChild() )
        	{
        		portlet_name = portlet_name.getFirstChild();
        	}
        	
        	return_value = portlet_name.getNodeValue().substring ( 1 );
        	return_value = return_value.substring ( 0, return_value.length()-1 );
        }
    	
    	return return_value;
    }

    /**
     * Recursively traverse down the tree looking for <div /> tags that have "class" attribute with the value of
     * "portlet-container".  A <div class="portlet-container"> tag signifies the parent node of a portlet's content
     * in the DOM hierarchy
     * 
     * @return the Node objects representing the portlets
     * @throws SAXException
     */
    public Vector <Node> getPortletNodes () throws SAXException
    {
        Vector <Node> portlet_nodes  = new Vector <Node> ();

        traverseTree ( response.getDOM(), portlet_nodes, "class", "portlet-title" );
        
        return portlet_nodes;
    }


    /**
     * Returns an array of java.lang.String objects representing the names of all the portlets displayed on the current page
     * 
     * @return an array of java.lang.String objects representing the names of all the portlets displayed on the current page
     * @throws SAXException
     */
    public String[] getPortletsOnPage () throws SAXException
    {
    	Vector <Node> portlet_nodes = getPortletNodes();
    	int counter                 = 0;
    	portlets_on_page            = new String[portlet_nodes.size()];
    	
    	for ( counter = 0; counter < portlet_nodes.size(); counter++ )
    	{
    		portlets_on_page[counter] = getPortletName ( portlet_nodes.elementAt ( counter ) );
    	}
    	
    	return portlets_on_page;
    }
    
    /**
     * Navigates the testcase to a specified community
     * 
     * @param community_name the name of the community to navigate to
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean goToCommunity ( String community_name ) throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	getCommunities();
    	
        Integer community_id = communities.get ( community_name );

        if ( null == community_id )
        {
            _log.error ( "*** goToCommunity() - could not find community ID" );
        	
        	debugDumpPage();
        	
            return false;
        }
        else
        { 
            //  Simulate a user choosing the community
        	HttpUnitOptions.setScriptingEnabled ( false );
	            request   = new GetMethodWebRequest ( Hostname + "/c/portal/group_forward?group_id=" + community_id.intValue() );
	            response  = wc.getResponse ( request );
            HttpUnitOptions.setScriptingEnabled ( false );
            
            CapturePage();

            //  Extract the URL to navigate to from the resulting Javascript
            String response_text = response.getText();
            int index     = response_text.indexOf   ( "self.location = '" );
            response_text = response_text.substring ( index + "self.location = '".length() );
            index         = response_text.indexOf   ( "</script>" );
            response_text = response_text.substring ( 0, index ).trim();
            response_text = response_text.substring ( 0, response_text.length()-2 );

            //  Go to the resulting page
        	HttpUnitOptions.setScriptingEnabled ( false );
            	request   = new GetMethodWebRequest ( Hostname + response_text );
            	response  = wc.getResponse ( request );
        	HttpUnitOptions.setScriptingEnabled ( false );
            
            CapturePage();
            
            return true;
        }
    }

    /**
     * Tests whether or not a DOM node has a specified attribute
     * 
     * @param parent_node the DOM node to test
     * @param attribute_name the attribute to test for
     * @return true if the DOM node has the attribute, false otherwise
     * @throws SAXException 
     */
    @SuppressWarnings("unused")
	private boolean hasAttribute ( Node parent_node, String attribute_name ) throws SAXException
    {
    	NamedNodeMap attributes = parent_node.getAttributes();

        if ( null == attributes )
        {
        	debugDumpPage();
        	
        	return false;
    	}

        Node attribute = attributes.getNamedItem ( attribute_name );

        return ( null != attribute );
    }
    
    /**
     * Sets up the testcase with the necessary parameters from a Properties object
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     *
     */
    public boolean initialize ( Properties properties )
    	throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	Hostname = properties.getProperty ( "liferay.testcase.hostname", "localhost:80" );
    	Username = properties.getProperty ( "liferay.testcase.username", "test@liferay.com" );
    	Password = properties.getProperty ( "liferay.testcase.password", "test" );
    	
    	return initialize ( Hostname, Username, Password );
    }
    
    /**
     * Sets up the testcase with the necessary parameters
     * 
     * @param hostname  the hostname in "hostname:port_number" format.  The "http://" header is important
     * @param username  the username to login with
     * @param password  the password to login with
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean initialize ( String hostname, String username, String password )
    	throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	//  Disable stopping on unknown Javascript errors

        //  Initialize parameters
        Username         = username;
        Password         = password;
        Hostname         = "http://" + hostname;
        start_time       = new Date();
        
        //  Create the timestamp
        initTimestamp();
        
        //  Set the counter format
        df = new DecimalFormat ( "0000" );
        
        //  Create a Webconversation with the webserver
        wc         = new WebConversation();
        request    = new GetMethodWebRequest ( Hostname + "/web/guest/home" );
        HttpUnitOptions.setScriptingEnabled ( false );
        	response   = wc.getResponse ( request );
        HttpUnitOptions.setScriptingEnabled ( false );
        
        ClientProperties cp = wc.getClientProperties();
        cp.setAcceptGzip( true );
        
        CapturePage();

        return login();
    }

    /**
     * Sets up the testcase with the necessary parameters
     * 
     * @param hostname  the hostname in "http://hostname:port_number" format.  The "http://" header is important
     * @param community the initial community to start off in
     * @param username  the username to login with
     * @param password  the password to login with
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean initialize ( String hostname, String community, String username, String password )
        throws MalformedURLException, IOException, SAXException, ParserConfigurationException, TransformerException
    {
    	Community        = community;
    	
    	assertTrue ( initialize ( hostname, username, password ) );
    	
    	if ( null != Community )
    	{
    		return goToCommunity ( Community );
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /**
     * Returns whether or not a portlet by the specified name exists on the current page
     * 
     * @param portlet_name
     * @return
     * @throws SAXException
     */
    public boolean isPortletOnPage ( String portlet_name ) throws SAXException
    {
    	String portlets_on_page[] = getPortletsOnPage();
    	boolean return_value      = false;
    	int num_portlets          = portlets_on_page.length;
    	
    	for ( int counter = 0; counter < num_portlets; counter++ )
    	{
    		if ( portlets_on_page[counter].equals ( portlet_name ) )
    		{
    			return_value = true;
    			break;
    		}
    	}
    	
    	return return_value;
    }
    
    public boolean login2() throws SAXException, IOException
    {
    	debugDumpPage();
    	return false;
    }
    
    /**
     * If the testcase has not logged into Liferay, then login
     * 
     * @return true if successful login, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean login() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
        WebForm forms[]       = response.getForms();
        WebForm form          = null;
        String login_params[] = null;

        for ( int d = 0; d < forms.length; d++ )
        {
            _log.info ( "Processing form: " + forms[d].getName() );
            login_params = retrieveLoginFormFields ( forms[d] );
            if ( null != login_params )
            {
                form = forms[d];
                break;
            }
        }

        if ( null == form )
        {
            _log.error ( "Could not find login form" );

            return false;
        }

        //  Search for the "login" field name
        //  Search for the "password" field name
        //  Search for the "cmd" field name
        boolean is_default_login = true;

        if ( login_params[2].indexOf ( "my_account_cmd" ) >= 0 )
        {
        	_log.info ( "Detected my_account_cmd");
            is_default_login = false;
        }
        
        HttpUnitOptions.setScriptingEnabled ( false );
	        request = form.getRequest();
	            request.setParameter ( login_params[2], (is_default_login)? "update" : "auth" );
	            request.setParameter ( login_params[0], Username );
	            request.setParameter ( login_params[1], Password );
	        response = wc.getResponse ( request );
        HttpUnitOptions.setScriptingEnabled ( false );
        
        CapturePage(); // 0002

        if ( !is_default_login )
        {
        	CapturePage();

            //  This is now the "Processing Login" page.  There is a hidden form that is hidden in the Javascript of the page.
            //  We need to retrieve the hidden form
            HttpUnitOptions.setScriptingEnabled ( false );
                request   = new PostMethodWebRequest ( Hostname + "/c/portal/protected" );
                response  = wc.getResponse ( request );
            HttpUnitOptions.setScriptingEnabled ( false );
        
            CapturePage();

            //  The hidden form from the "Processing Login" page is the servlet container login mechanism.  The values should
            //  already be set, so we just need to submit the form
            HttpUnitOptions.setScriptingEnabled ( false );
                form      = response.getFormWithName ( "fm" );
                if ( null == form )
                {
                    _log.error ( "Login failed" );
                    CapturePage();
                    return false;
                }
            HttpUnitOptions.setScriptingEnabled ( false );
            HttpUnitOptions.setScriptingEnabled ( false );
                response  = form.submit();
            HttpUnitOptions.setScriptingEnabled ( false );
        
            CapturePage();
        }
        else
        {
            _log.info ( "Using default login!" );
            
            form      = response.getFormWithName ( "fm" );
            if ( null == form )
            {
                _log.error ( "Login failed because the form could not be found (default)" );
                debugDumpPage();
                return false;
            }
            
            HttpUnitOptions.setScriptingEnabled ( false );
	            response  = form.submit();
            HttpUnitOptions.setScriptingEnabled ( false );
            
            CapturePage(); // 0003
        }

        _log.info ( "Login successful" );

        return true;
    }

    
    /**
     * If the testcase has not logged out of Liferay, then logout
     *
     * @return true if successful logout, false otherwise
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException 
     * @throws ParserConfigurationException 
     */
    public boolean logout() throws SAXException, IOException, ParserConfigurationException, TransformerException
    {
    	WebLink sign_out = response.getLinkWith( "Sign Out" );
    	
    	if ( null != sign_out )
    	{
    	
    		HttpUnitOptions.setScriptingEnabled ( false );
        		response = sign_out.click();
        	HttpUnitOptions.setScriptingEnabled ( false );
    		
    		CapturePage();

            return true;
    	}
    	else
    	{
            _log.error ( "Could log sign out" );

    		debugDumpPage();
    		
            return false;
    	}
    }
    
    /**
     * Helper function to retrieve the dynamic fields of the login form
     * 
     * @param form the com.meterware.httpunit.WebForm object 
     * @return
     */
    private String[] retrieveLoginFormFields ( WebForm form )
    {
        String param_names[]   = form.getParameterNames();
        String return_values[] = new String[3];
        String param           = null;
        int num_params         = param_names.length;
        int counter            = 0;

        boolean found_username = false;
        boolean found_password = false;
        boolean found_command  = false;

        for ( counter = 0; counter < num_params; counter++ )
        {
            param = param_names[counter];
            
            if ( param.indexOf ( "_login" ) > 0 )
            {
                found_username = true;
                return_values[0] = param;
            }
            else if ( param.indexOf ( "_password" ) > 0 )
            {
                found_password = true;
                return_values[1] = param;
            }
            else if ( param.indexOf ( "_cmd" ) > 0 )
            {
                found_command = true;
                return_values[2] = param;
            }
        }


        if ( found_username && found_password && found_command )
        {
            return return_values;
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the children of the parent DOM node that match the attribute name and attribute value,
     * i.e. attribute_name="attribute_value"
     * 
     * @param parent_node the parent DOM node
     * @param nodes the Vector object to hold all the DOM children that match 
     * @param attribute_name the attribute name to match
     * @param attribute_value the attribute value to match
     * @return the children of the parent DOM node that match the attribute name and attribute value
     */
    private Vector <Node> traverseTree ( Node parent_node, Vector <Node> nodes, String attribute_name, String attribute_value )
    {
        //  Depth-first recursion
        NodeList nodelist = parent_node.getChildNodes();
        Node current_node = null;
        int nodelist_size = nodelist.getLength();
        
        for ( int counter = 0; counter < nodelist_size; counter++ )
        {
            current_node = nodelist.item ( counter );

            if ( hasAttribute ( current_node, attribute_name, attribute_value ) )
            {
                nodes.add ( current_node );
            }
            else
            {
                traverseTree ( current_node, nodes, attribute_name, attribute_value );
            }
        }

        return nodes;
    }
};
