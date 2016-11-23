package tools.testing.servicecenter;

import tools.testing.servicecenter.ticket.generated.Recordset;

import java.io.InputStream;
import java.io.IOException;

//import java.net.Authenticator;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.PasswordAuthentication;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

/**
 * Class to represent the Service Center.  Use it to request problem summaries 
 * for ticket numbers.
 * 
 * @author hannrus
 *
 */
public class ServiceCenter
{
    private static final String DEFAULT_PROPERTIES_FILE = "ServiceCenter.properties";

    // For authentication
    private static final String USERNAME = "zzito";
    private static final String PASSWORD = "zzito";

    private static final Logger LOG = Logger.getLogger ( ServiceCenter.class );

    private URL baseUrl;
    
    /**
     * Default constructor.  Reads configuration from ServiceCenter.properties file 
     * in the resource path.
     *
     */
    public ServiceCenter ()
    {
        this( getPropertiesFromResource(DEFAULT_PROPERTIES_FILE) );
        LOG.info ( "Retrieved properties from " + DEFAULT_PROPERTIES_FILE );
    }

    /**
     * Construct a ServiceCenter object from a properties object.
     * See ServiceCenterProperties for properties format.
     * 
     * @param properties
     */
    public ServiceCenter ( ServiceCenterProperties properties )
    {
        this( properties.getWebServiceUrl() );
    }

    /**
     * Construct a ServiceCenter object from a base URL and a schema.
     * 
     * @param baseUrl the URL which forms the basis for making requests to the Service Center.
     * For example: http://peregrine-app-prod:8080/oaa/servlet/archway?sc.query&_table=probsummary&number=
     */
    public ServiceCenter( URL baseUrl )
    {
        this.baseUrl = baseUrl;
        LOG.info ( "URL to make requests against: " + baseUrl );
    }

    private static ServiceCenterProperties getPropertiesFromResource ( String resourceFileName )
    {
        ClassLoader cl = ServiceCenter.class.getClassLoader();
        InputStream is = cl.getResourceAsStream( resourceFileName );
        ServiceCenterProperties properties = new ServiceCenterProperties(is);

        try
        {
            is.close();
        }
        catch (IOException e)
        {
            LOG.error("Caught IOException trying to close stream for properties resource " +
                "in ServiceCenter.getPropertiesFromResource.");
        }

        return properties;
    }

    public Recordset sendRequest(String ticketNumber) throws IOException, JAXBException
    {
    	return sendRequest(ticketNumber, null);
    }
    
    /**
     * Send a request to the Service Center and get a ticket back.
     * 
     * @param ticketNumber the ticket number, e.g. IM01006952
     * @return the response from the Service Center.  &lt;recordset&gt; is the root 
     * element of the XML that Service Center returns.
     * @throws IOException if there's a failure to connect to the Service Center
     * @throws JAXBException if there's a problem parsing the response from the Service Center
     */
    /*
    public Recordset sendRequest(String ticketNumber, Schema responseSchema) throws IOException, JAXBException
    {
        URL requestUrl = makeRequestUrl(ticketNumber);

        HttpURLConnection conn = makeConnection(requestUrl);

        InputStream in = conn.getInputStream();
        
        Recordset ticket = parseResponse( in, responseSchema );

        conn.disconnect();

        return ticket;
    }
    */
    
    public Recordset sendRequest(String ticketNumber, Schema responseSchema) throws IOException, JAXBException {
    	Recordset recordset = null;
    	
    	HttpClient client = new HttpClient();
    	
    	client.getState().setCredentials(
    			AuthScope.ANY,
    			new UsernamePasswordCredentials(USERNAME, PASSWORD)
    	);

    	GetMethod get = new GetMethod(baseUrl.toString().concat(ticketNumber));

    	get.setDoAuthentication(true);
    	
    	try {
    		int status = client.executeMethod(get);
    		
    		if (status == HttpStatus.SC_OK) {
    			InputStream in = get.getResponseBodyAsStream();
    			recordset = parseResponse(in, responseSchema);
    			in.close();
    		}
    	} finally {
    		get.releaseConnection();
    	}
    	
    	return recordset;
    }

    /*
    public Reader getReaderForRequest(String ticketNumber)
    {
    	URL requestUrl = makeRequestUrl(ticketNumber);
    	HttpURLConnection conn = makeConnection(requestUrl);
    	
    }
    */
    
    /*
    private URL makeRequestUrl(String ticketNumber)
    {
        URL requestUrl = null;

        try
        {
            requestUrl = new URL( baseUrl.toString().concat(ticketNumber) );
        }
        catch (MalformedURLException e)
        {
            // Shouldn't be an issue.  I don't see how the URL could be malformed
            // just by appending a ticket number to a URL that's already been
            // constructed.
            LOG.error("Caught a MalformedURLException while trying to construct " +
                "the request URL in ServiceCenter.makeRequestUrl.");
        }

        return requestUrl;
    }
    */
    
    /*
    private HttpURLConnection makeConnection(URL requestUrl) throws IOException
    {
        HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( USERNAME, PASSWORD.toCharArray() );
        } };

        Authenticator.setDefault(auth);  // Any way to get the current default so I can reset it when I'm done?

        conn.connect();

        return conn;
    }
    */
    
    private Recordset parseResponse(InputStream is, Schema responseSchema) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance( tools.testing.servicecenter.ticket.generated.ObjectFactory.class );
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(responseSchema); // null responseSchema means no validation
        return (Recordset) unmarshaller.unmarshal ( is );
    }
}
