/**
 * AlertChannel.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * Channel for moving an alert from one thread to another.
 * 
 * @author hannrus
 */
public final class AlertChannel implements AlertProcessor
{
	private static final Logger  LOG = Logger.getLogger(AlertChannel.class);
	
	private static final long     DEFAULT_TIMEOUT = 30;
	private static final TimeUnit DEFAULT_UNIT    = TimeUnit.SECONDS;
	
	private final long     processTimeout;
	private final TimeUnit processTimeUnit;
	
	private SynchronousQueue<Alert> channel;

	/**
	 * Constructs an <code>AlertChannel</code> with a timeout of 30 seconds.
	 */
	public AlertChannel()
	{
		this(DEFAULT_TIMEOUT, DEFAULT_UNIT);
	}
	
	/**
	 * Constructs an <code>AlertChannel</code>.
	 * 
	 * @param processTimeout the amount of time to keep the channel open
	 * @param processTimeUnit the unit for the timeout value (e.g., seconds)
	 */
	public AlertChannel(long processTimeout, TimeUnit processTimeUnit)
	{
		this.processTimeout = processTimeout;
		this.processTimeUnit = processTimeUnit;
		channel = new SynchronousQueue<Alert>();
	}
	
	/**
	 * Push an alert into the <code>AlertChannel</code>.  If there is no other 
	 * thread waiting to receive the alert, the thread calling <code>offer</code> 
	 * is blocked until either another thread receives the alert, or until the 
	 * timeout is reached.
	 * 
	 * @param alert The alert to offer to the channel.
	 * @param timeout The amount of time to wait for another thread to receive the alert.
	 * @param unit The unit for the timeout value (e.g., seconds)
	 * @return true if the alert was pushed into the channel before the timeout was 
	 * reached, false otherwise
	 * @throws InterruptedException if the thread is interrupted.
	 */
	public boolean offer(Alert alert, long timeout, TimeUnit unit) throws InterruptedException
	{
		return channel.offer(alert, timeout, unit);
	}
	
	/**
	 * Take an alert from the <code>AlertChannel</code>.  If no other thread is 
	 * offering an alert through the <code>AlertChannel</code>, wait until either 
	 * another thread offers an alert, or until the timeout is reached.
	 * 
	 * @param timeout the amount of time to wait for an alert
	 * @param unit the unit for the timeout value
	 * @return an <code>Alert</code>, or null if the timeout is reached.
	 * @throws InterruptedException if the thread is interrupted.
	 */
	public Alert poll(long timeout, TimeUnit unit) throws InterruptedException
	{
		return channel.poll(timeout, unit);
	}
	
	/**
	 * Implementation of the AlertProcessor interface.  Calls offer to 
	 * push an alert into the channel, using the timeout specified in the 
	 * constructor.
	 * 
	 * @param alert the alert to push into the channel
	 */
	public void process(Alert alert)
	{
		try
		{
			offer(alert, processTimeout, processTimeUnit);
		}
		catch(InterruptedException e)
		{
			LOG.info("AlertChannel.process interrupted.");
		}
	}
}
