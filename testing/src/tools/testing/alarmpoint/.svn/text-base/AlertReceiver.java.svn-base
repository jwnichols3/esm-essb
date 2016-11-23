/**
 * AlertReceiver.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * The <code>AlertReceiverServer</code> creates an <code>AlertReceiver</code> 
 * for each alert socket connection it makes and hands off the socket 
 * connection to the <code>AlertReceiver</code> for processing.
 * 
 * @author hannrus
 *
 */
final class AlertReceiver implements Runnable
{
	private static final Logger  LOG = Logger.getLogger(AlertReceiver.class);
	
	private final Socket         SOCKET;
	private final AlertProcessor PROCESSOR;

	/**
	 * Construct an <code>AlertReceiver</code>.
	 * @param socket the socket connection to process
	 * @param processor what to do to the input stream in the socket.
	 */
	public AlertReceiver(Socket socket, AlertProcessor processor)
	{
		SOCKET    = socket;
		PROCESSOR = processor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		InputStream in = null;
		
		try
		{
			in = SOCKET.getInputStream();
			
			try
			{
				Alert alert = AlertFactory.getInstance().makeAlert(in);
				PROCESSOR.process(alert);
			}
			catch(IOException e)
			{
				LOG.error("IOException caught while reading from socket input stream.");
				StackTraceElement[] stackTrace = e.getStackTrace();
				for (int i = 0; i < stackTrace.length; ++i)
				{
					LOG.debug(stackTrace[i].toString());
				}
			}
			catch(InvalidAlertException e)
			{
				LOG.error("Received invalid alert.");
			}
		}
		catch(IOException e)
		{
			LOG.error("IOException caught while trying to get an input stream " + 
					"from the socket.");
		}
		finally
		{
			try
			{
				if (in != null)
				{	
					in.close();
				}
				
				SOCKET.close();
			}
			catch (IOException e)
			{
				LOG.error("IOException caught while trying to close input stream or socket.");
			}
		}
	}
}
