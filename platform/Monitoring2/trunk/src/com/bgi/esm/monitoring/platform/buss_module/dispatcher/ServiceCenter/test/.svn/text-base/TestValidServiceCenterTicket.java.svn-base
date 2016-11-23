package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenter;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ticket.Recordset;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ticket.ProblemSummary;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.XMLAssert;
import org.xml.sax.SAXException;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestValidServiceCenterTicket extends TestCase
{
    // Valid tickets
    final private static Logger _log              = Logger.getLogger ( TestValidServiceCenterTicket.class );
    final private static String SAMPLE_DATA_DIR   = "data";
    //final private static String TICKET_NUMBER_1   = SAMPLE_DATA_DIR + "/" + "IM01006952";
    //final private static String TICKET_NUMBER_2   = SAMPLE_DATA_DIR + "/" + "IM01002948";
    final private static String TICKET_NUMBER_1   = "IM01006952";
    final private static String TICKET_NUMBER_2   = "IM01002948";
    
    final private static String SCHEMA_PATH       = "xml/ServiceCenterTicket.xsd";
    
    private ServiceCenter serviceCenter;
    private ClassLoader classLoader;
    private Schema ticketSchema;
    
    public TestValidServiceCenterTicket ( String param ) throws IOException, SAXException
    {
        super ( param );
        
        serviceCenter = new ServiceCenter();
        
        classLoader = TestValidServiceCenterTicket.class.getClassLoader();
        
		InputStream is = classLoader.getResourceAsStream(SCHEMA_PATH);
		Source source = new StreamSource(is);
		SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
		ticketSchema = sf.newSchema(source);
		is.close();
    }

    public void testInstantiateTicket1() throws IOException
    {
    	try
    	{
    		Recordset ticket = serviceCenter.sendRequest ( TICKET_NUMBER_1 );
    		assertNotNull ( ticket );
    		assertNotNull ( ticket.getProbsummary() );
    	}
    	catch (JAXBException e)
    	{
    		fail("Caught JAXBException in testInstantiateTicket1.  This most " +
    				"likely means the ticket is incorrectly formatted.");
    	}
    }
    
    public void testInstantiateTicket2() throws IOException
    {
    	try
    	{
    		Recordset ticket = serviceCenter.sendRequest( TICKET_NUMBER_2 );
    		assertNotNull ( ticket );
    		assertNotNull ( ticket.getProbsummary() );
    	}
    	catch (JAXBException e)
    	{
    		fail("Caught JAXBException in testInstantiateTicket1.  This most " +
    				"likely means the ticket is incorrectly formatted.");
    	}
    }
    
    public void testValidateTicket1() throws IOException
    {
    	try
    	{
    		Recordset ticket = serviceCenter.sendRequest( TICKET_NUMBER_1, ticketSchema );
    		assertNotNull ( ticket );
    		assertNotNull ( ticket.getProbsummary() );
    	}
    	catch (JAXBException e)
    	{
    		fail("Caught JAXBException in testValidateTicket1.  This most " +
    				"likely means the ticket is incorrectly formatted.");
    	}
    }
    
    public void testValidateTicket2() throws IOException
    {
    	try
    	{
    		Recordset ticket = serviceCenter.sendRequest( TICKET_NUMBER_2, ticketSchema );
    		assertNotNull ( ticket );
    		assertNotNull ( ticket.getProbsummary() );
    	}
    	catch (JAXBException e)
    	{
    		fail("Caught JAXBException in testValidateTicket2.  This most " +
    				"likely means the ticket is incorrectly formatted.");
    	}
    }

    public void testTicket1Content() throws IOException, ParserConfigurationException
    {
        String resource_name = SAMPLE_DATA_DIR + "/" + TICKET_NUMBER_1 + ".stream";
    	Reader expected = new InputStreamReader(classLoader.getResourceAsStream( resource_name ) );
    	Reader actual = serviceCenter.getReaderForRequest( TICKET_NUMBER_1 );
    	
    	try
    	{
    		XMLAssert.assertXMLEqual(expected, actual);
    	}
    	catch (SAXException e)
    	{
    		fail("Caught SAXException in testTicket1Content.  This most " + 
    			"likely means the ticket is incorrect.");
    	}
    	finally
    	{
    		expected.close();
    		actual.close();
    	}
     }
    
    public void testTicket2Content() throws IOException, ParserConfigurationException
    {
        String resource_name = SAMPLE_DATA_DIR + "/" + TICKET_NUMBER_2 + ".stream";
    	Reader expected = new InputStreamReader(classLoader.getResourceAsStream( resource_name ));
    	Reader actual = serviceCenter.getReaderForRequest( TICKET_NUMBER_2 );
    	
    	try
    	{
    		XMLAssert.assertXMLEqual(expected, actual);
    	}
    	catch (SAXException e)
    	{
    		fail("Caught SAXException in testTicket2Content.  This most " + 
    			"likely means the ticket is incorrect.");
    	}
    	finally
    	{
    		expected.close();
    		actual.close();
    	}
     }

};
