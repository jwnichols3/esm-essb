/**
 * AlertsRepository.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author hannrus
 *
 */
public final class AlertsRepository implements AlertProcessor
{
	private Map<Long, Alert> alerts = new Hashtable<Long, Alert>();
	
	public void add(Alert alert)
	{
		alerts.put(alert.getNotificationId(), alert);
	}
	
	public Alert get(long notificationId)
	{
		return alerts.get(notificationId);
	}
	
	public Alert remove(long notificationId)
	{
		return alerts.remove(notificationId);
	}
	
	public boolean contains(long notificationId)
	{
		return alerts.containsKey(notificationId);
	}
	
	/* (non-Javadoc)
	 * @see tools.testing.alarmpoint.AlertProcessor#process(tools.testing.alarmpoint.Alert)
	 */
	public void process(Alert alert)
	{
		add(alert);
	}
}
