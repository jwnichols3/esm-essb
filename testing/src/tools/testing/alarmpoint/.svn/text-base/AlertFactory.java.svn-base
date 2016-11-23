/**
 * AlertFactory.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Singleton class containing convenience methods for constructing 
 * <code>Alert</code> objects.
 * 
 * @author hannrus
 */
public class AlertFactory
{
	private static final Logger  LOG = Logger.getLogger(AlertFactory.class);

	private static AlertFactory instance = null;
	
	/**
	 * Get the instance of the alert factory. 
	 * @return the alert factory
	 */
	public static AlertFactory getInstance()
	{
		return instance == null ? instance = new AlertFactory() : instance;
	}
	
	private AlertFactory()
	{
	}
	
	// The number of zeros between the "initial bytes" and the beginning of the 
	// message seems to be variable.
	private void seekStartOfHeading(DataInput di) throws IOException, InvalidAlertException
	{
		final byte START_OF_HEADING = 1;
		
		byte bite = 0;

		try
		{
			while ( (bite = di.readByte()) == 0 )
				;
		}
		catch (EOFException e)
		{
			LOG.error("Premature end to input stream.");
			throw new InvalidAlertException();
		}
		catch (IOException e)
		{
			LOG.error("Throwing IOException from seekStartOfHeading.");
			throw e;
		}
		
		if (bite != START_OF_HEADING)
		{
			LOG.error("Did not find start of heading.");
			throw new InvalidAlertException();
		}
	}

	// The number of zeros between the "final bytes" and the "end of text" 
	// value seems to be variable.
	private void seekEndOfText(DataInput di)
	{
		final byte END_OF_TEXT = 3;
		
		byte bite = 0;
		
		try
		{
			while ( ( bite = di.readByte() ) == 0 )
				;
		}
		catch (EOFException e)
		{
			LOG.info("Premature end to input stream.");
		}
		catch (IOException e)
		{
			LOG.info("IOException caught while seeking end of text.");
		}
		
		if ( bite != END_OF_TEXT )
		{
			LOG.info("Did not find end of text.");
		}
	}
	
	// Reads a string from the input stream.  Converts bytes to two-byte chars.
	private String readString(DataInput in, int length) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < length; ++i)
		{
			sb.append( (char) in.readByte() );
		}
		
		return sb.toString();
	}
	
	// Reads a string from the input stream and converts it to a date,
	// using the specified date format.
	private Date readDate(DataInput in, int length, DateFormat dateFormat) throws IOException, InvalidAlertException
	{
		try
		{
			return dateFormat.parse( readString(in, length) );
		}
		catch (ParseException e)
		{
			throw new InvalidAlertException(e);
		}
	}
	
	// The username and device are transmitted as part of the same string--e.g.,
	// hannrus|Virtual Device 2
	private String[] readUserNameAndDevice(DataInput in, int length) throws IOException
	{
		final String DELIMITER = "\\|";
		return readString(in, length).split(DELIMITER);
	}
	
	// 1, 2, or 3.  See Alert.DeviceType.  Note that this method makes 
	// assumptions about the order of the values in the Alert.DeviceType 
	// enum.
	private Alert.DeviceType readDeviceType(DataInput in) throws IOException
	{
		final Alert.DeviceType[] DEVICE_TYPES = Alert.DeviceType.values();
		int deviceType = in.readInt();
		return DEVICE_TYPES[deviceType - 1];
	}
	
	// Only virtual pagers seem to have "choices."
	private String[] readChoices(DataInput in, int numberOfChoices) throws IOException
	{
		String[] choices = new String[numberOfChoices];
		
		for (int i = 0; i < numberOfChoices; ++i)
		{
			short length = in.readShort();
			choices[i] = readString(in, length);
		}
		
		return choices;
	}
	
	/**
	 * Make an alert from an input stream.  Parses the stream transmitted to the 
	 * AlarmPoint Virtual Device Manager.
	 * 
	 * @param in the input stream
	 * @return an <code>Alert</code>
	 * @throws IOException if there's a problem reading the stream
	 * @throws InvalidAlertException if a valid <code>Alert</code> can't be 
	 * constructed from the data in the stream.
	 */
	public Alert makeAlert(InputStream in) throws IOException, InvalidAlertException
	{
		final long START_OR_END_TRANSMISSION = 0x7a295bdf3cbea5a9L;

		DataInputStream din = new DataInputStream(in);
		
		if ( din.readLong() != START_OR_END_TRANSMISSION )
		{
			LOG.error("Unexpected value at beginning of input stream.");
			throw new InvalidAlertException();
		}
		
		seekStartOfHeading(din);
		
		long notificationId = din.readLong();
		LOG.debug("notificationId: " + notificationId);
		
		InetAddress senderAddress = InetAddress.getByName( readString( din, din.readShort() ) );
		LOG.debug("senderAddress: " + senderAddress.toString());
		
		// Don't know what this part is
		final int NUMBER_OF_BYTES_TO_SKIP = 4;
		int numberOfSkippedBytes = din.skipBytes(NUMBER_OF_BYTES_TO_SKIP);
		if ( !(numberOfSkippedBytes == NUMBER_OF_BYTES_TO_SKIP) )
		{
			LOG.error("Alert is invalid because not enough bytes were skipped.");
			throw new InvalidAlertException();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		Date date = readDate(din, din.readShort(), dateFormat);
		LOG.debug("date: " + date.toString());
		
		String[] userNameAndDevice = readUserNameAndDevice( din, din.readInt() );
		if (userNameAndDevice.length != 2)
		{
			throw new InvalidAlertException();
		}
		LOG.debug("user name: " + userNameAndDevice[0]);
		LOG.debug("device: " + userNameAndDevice[1]);
		
		Alert.DeviceType deviceType = readDeviceType(din);
		LOG.debug("deviceType: " + deviceType.toString());
		
		String subject = readString( din, din.readShort() );
		LOG.debug("subject: " + subject);
		
		String body = readString( din, din.readInt() );
		LOG.debug("body: " + body);
		
		String[] choices = readChoices( din, din.readInt() );
		LOG.debug("choices.length: " + choices.length);
		
		if ( din.readLong() != START_OR_END_TRANSMISSION )
		{
			LOG.error("Unexpected value at the end of the input stream.");
			throw new InvalidAlertException();
		}
		
		seekEndOfText(din);
		
		return new Alert(notificationId, senderAddress, date, userNameAndDevice[0], 
				userNameAndDevice[1], deviceType, subject, body, choices);
	}
	
	/**
	 * Construct an <code>Alert</code>.  Somewhat more convenient than 
	 * using the <code>Alert</code> constructor in some cases.
	 * @param notificationId
	 * @param senderAddress
	 * @param dateTime
	 * @param userName
	 * @param device
	 * @param deviceType
	 * @param subject
	 * @param body
	 * @param choices
	 * @return
	 */
	public Alert makeAlert(
			int notificationId,
			String senderAddress,
			long dateTime,
			String userName,
			String device,
			Alert.DeviceType deviceType,
			String subject,
			String body,
			String ... choices)
	{
		Alert alert = null;
		
		try
		{
			alert = new Alert(
					notificationId,
					InetAddress.getByName(senderAddress),
					new Date(dateTime),
					userName,
					device,
					deviceType,
					subject,
					body,
					choices
			);
		}
		catch (UnknownHostException e)
		{
			LOG.error("Unknown host exception caught while initializing Alert.  " +
					"This should never happen.");
		}
		
		return alert;
	}
}
