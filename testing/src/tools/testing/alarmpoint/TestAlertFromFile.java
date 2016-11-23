/**
 * TestAlertFromFile.java
 * @hannrus
 */
package tools.testing.alarmpoint;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.apache.log4j.Logger;


/**
 * Tests the <code>Alert</code> and alert-receiving classes by starting 
 * an <code>AlertReceiverServer</code> and sending it alerts from binary 
 * files.
 * 
 * @author hannrus
 *
 */
public class TestAlertFromFile extends TestCase
{
	private static final Logger  LOG = Logger.getLogger(TestAlertFromFile.class);

	private static final ClassLoader classLoader = TestAlertFromFile.class.getClassLoader();
	
	private static final String HOST = "localhost";
	private static final int    PORT = 9001;
	
	private static final long POLLING_TIMEOUT = 30; // 30 seconds

	private AlertChannel channel;
	private AlertReceiverServer server;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		// repository = new AlertsRepository();
		channel  = new AlertChannel();
		server = new AlertReceiverServer(PORT, channel);
		LOG.info("Starting AlertReceiverServer");
		new Thread(server).start();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
		LOG.info("Shutting down AlertReceiverServer");
		server.shutDown();
		server = null;
		channel = null;
	}

	private void safeClose (Closeable c, String errorMessage)
	{
		try
		{
			c.close();
		}
		catch (IOException e)
		{
			LOG.error("IOException thrown on close: " + errorMessage);
		}
	}
	
	private void safeClose (Socket socket)
	{
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			LOG.error("IOException thrown on attempt to close socket.");
		}
	}

	private Alert makeExpectedEmailAlert()
	{
		return AlertFactory.getInstance().makeAlert(
				200249,                                // notificationId
				"69.52.12.60",                         // senderAddress
				1162431506000L,                        // dateTime (Wed Nov 01 17:37:06 PST 2006)
				"hannrus",                             // userName
				"Virtual Device 2",                    // device
				Alert.DeviceType.EMAIL,                // deviceType
				"Device Test Validation Code",         // subject
				"The validation code for your " +      // body
				"Device, Russell Hanneken - " +
				"Virtual Device 2, is 7391.\n\n" +
				"To validate your Device, do " + 
				"one of the following:\n" +
				" - Respond to the message " +
				"with VALIDATE.\n" +
				" - Enter the validation code " + 
				"into the AlarmPoint Web User " +
				"Interface.\n\n" +
				"If you would like to reply to " +
				"this e-mail, simply reply with " + 
				"the word \"RESPONSE\" followed " +
				"by your choice in the subject " +
				"line. Your response choices " +
				"are \"Validate\"\n\n" +
				"Please make sure to include " +
				"the original message in your " +
				"reply. The following is used " +
				"by the server to identify the " +
				"email 200249."
		);
	}

	private Alert makeExpectedPagerAlert()
	{
		return AlertFactory.getInstance().makeAlert(
				200245,                                // notificationId
				"69.52.12.60",                         // senderAddress
				1162431426000L,                        // dateTime (Wed Nov 01 17:37:06 PST 2006)
				"hannrus",                             // userName
				"Virtual Device",                      // device
				Alert.DeviceType.PAGER,                // deviceType
				"Device Test Validation Code",         // subject
				"The validation code for your " +      // body
				"Device, Russell Hanneken - " +
				"Virtual Device, is 9434.\n\n" +
				"To validate your Device, do " + 
				"one of the following:\n" +
				" - Respond to the message " +
				"with VALIDATE.\n" +
				" - Enter the validation code " + 
				"into the AlarmPoint Web User " +
				"Interface.",
				"Validate"                             // choices
		);
	}
	
	private Alert makeExpectedTextPhoneAlert()
	{
		return AlertFactory.getInstance().makeAlert(
				200253,                                    // notificationId
				"69.52.12.60",                             // senderAddress
				1162431536000L,                            // dateTime
				"hannrus",                                 // userName
				"Virtual Device 3",                        // device
				Alert.DeviceType.TEXT_PHONE,               // deviceType
				"Device Test Validation Code",             // subject
				"<200253>\n" +                             // body
				"The validation code for your " +
				"Device, Russell Hanneken - " +
				"Virtual Device 3, is 5897.\n\n" +
				"To validate your Device, do one " +
				"of the following:\n" +
				" - Respond to the message with " +
				"VALIDATE.\n" +
				" - Enter the validation code " +
				"into the AlarmPoint Web User " +
				"Interface.\n" +
				"Reply: \"RES\" followed by a " +
				"choice\n" +
				"Choices: \"Validate\"\n"
		);
	}
	
	private void generateAlert(InputStream in, String host, int port) throws IOException
	{
		Socket socket = new Socket(host, port);
		
		OutputStream socketOut = null;
		try
		{
			socketOut = socket.getOutputStream();
		}
		catch (IOException e)
		{
			LOG.error("IOException thrown when trying to get output stream from socket.");
			safeClose(socket);
			throw e;
		}
		
		int bite = 0;

		try
		{
			while ( ( bite = in.read() ) != -1 )
			{
				socketOut.write(bite);
			}
		}
		catch (IOException e)
		{
			LOG.error("IOException thrown while reading/writing.");
			throw e;
		}
		finally
		{
			safeClose(socketOut, "Closing socket's output stream.");
			safeClose(socket);
		}
	}
	
	private InputStream getStreamFromFile(String inputFileName) throws FileNotFoundException
	{
		BufferedInputStream fileIn = 
			new BufferedInputStream( classLoader.getResourceAsStream( inputFileName ) );
		
		if (fileIn == null)
		{
			LOG.error("Input file not found.");
			throw new FileNotFoundException( inputFileName );
		}
		
		return fileIn;
	}
	
	private void doAlertTest(String inputFileName, String host, int port, Alert expectedAlert) throws FileNotFoundException, IOException, InterruptedException
	{
		LOG.info("Starting doAlertTest");

		InputStream fileIn = getStreamFromFile(inputFileName);
		
		generateAlert(fileIn, HOST, PORT);
		
		safeClose(fileIn, "Closing input stream for file.");
		
		Alert alert = channel.poll(POLLING_TIMEOUT, TimeUnit.SECONDS);
		
		assertNotNull(alert);
		
		// I'm checking each attribute individually for ease of debugging.
		assertEquals( expectedAlert.getNotificationId(), alert.getNotificationId() );
		assertEquals( expectedAlert.getSenderAddress(), alert.getSenderAddress() );
		assertEquals( expectedAlert.getDateTime(), alert.getDateTime() );
		assertEquals( expectedAlert.getUserName(), alert.getUserName() );
		assertEquals( expectedAlert.getDevice(), alert.getDevice() );
		assertEquals( expectedAlert.getDeviceType(), alert.getDeviceType() );
		assertEquals( expectedAlert.getSubject(), alert.getSubject() );
		assertEquals( expectedAlert.getBody(), alert.getBody() );
		assertTrue( Arrays.equals(expectedAlert.getChoices(), alert.getChoices()) );
		
		// Now use the Alert.equals method.
		assertEquals(expectedAlert, alert);
		
		LOG.info("Ending doAlertTest");
	}
	
	public void testPagerAlert() throws FileNotFoundException, IOException, InterruptedException
	{
		doAlertTest("pager.bin", HOST, PORT, makeExpectedPagerAlert());
	}
	
	public void testEmailAlert() throws FileNotFoundException, IOException, InterruptedException
	{
		doAlertTest("email.bin", HOST, PORT, makeExpectedEmailAlert());
	}

	public void testTextPhoneAlert() throws FileNotFoundException, IOException, InterruptedException
	{
		doAlertTest("textphone.bin", HOST, PORT, makeExpectedTextPhoneAlert());
	}
}
