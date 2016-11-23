/**
 * AlertReceiverServer.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ServerSocketFactory;

import org.apache.log4j.Logger;


/**
 * Waits for a socket connection request from AlarmPoint.  Accepts the 
 * connection, then launches a new thread to handle processing of the 
 * socket.
 * 
 * @author hannrus
 *
 */
public final class AlertReceiverServer implements Runnable
{
	private static final Logger  LOG = Logger.getLogger(AlertReceiverServer.class);
	
	private final int            PORT;
	private final AlertProcessor PROCESSOR;
	
	private volatile boolean shouldListen;
	
	/**
	 * Constructs an <code>AlertReceiverServer</code>.
	 * @param port the port to listen on for socket connection requests
	 * @param processor what to do to the input stream in each socket connection
	 */
	public AlertReceiverServer(int port, AlertProcessor processor)
	{
		PORT         = port;
		PROCESSOR    = processor;
		shouldListen = true;
	}
	
	/**
	 * Start the <code>AlertReceiverServer</code> by passing it to a 
	 * new thread.  The thread will call <code>run</code>.
	 */
	public void run()
	{
		try
		{
			LOG.info("AlertReceiverServer starting up.");
			
			ServerSocket serverSocket = 
				ServerSocketFactory.getDefault().createServerSocket(PORT);
			
			try
			{
				shouldListen = true;
				
				while (shouldListen)
				{
					Socket socket = serverSocket.accept();
					
					if (shouldListen)
					{
						AlertReceiver receiver = new AlertReceiver(socket, PROCESSOR);
						new Thread(receiver).start();
					}
				}
			}
			catch (IOException e)
			{
				LOG.error("Caught IOException while accepting connections.");
			}
			finally
			{
				try
				{
					serverSocket.close();
				}
				catch(IOException e)
				{
					LOG.error("Could not close server socket.");
				}
				
				LOG.info("AlertReceiverServer shutting down.");
			}
			
		}
		catch (IOException e)
		{
			LOG.error("Could not listen on port " + PORT);
		}
	}
	
	/**
	 * Shut down the <code>AlertReceiverServer</code>.
	 */
	public synchronized void shutDown()
	{
		if (shouldListen)
		{
			shouldListen = false;
			
			try
			{
				Socket socket = new Socket("localhost", PORT);
				
				if ( !socket.isClosed() )
				{
					socket.close();
				}
			}
			catch (UnknownHostException e)
			{
				LOG.error("localhost not a recognized host.  This should not happen.");
			}
			catch (IOException e)
			{
				LOG.error("Caught IOException while shutting down server socket.");
			}
		}
	}
}
