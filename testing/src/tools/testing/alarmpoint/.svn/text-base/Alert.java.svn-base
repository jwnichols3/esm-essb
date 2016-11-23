/**
 * Alert.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;

/**
 * A container for information received in an alert.
 * @author hannrus
 */
public final class Alert
{
	public enum DeviceType { EMAIL, PAGER, TEXT_PHONE }
	
	private long notificationId;
	private InetAddress senderAddress;
	private Date dateTime;
	private String userName;
	private String device;
	private DeviceType deviceType;
	private String subject;
	private String body;
	private String[] choices;
	
	public Alert(
			long notificationId, 
			InetAddress senderAddress, 
			Date dateTime, 
			String userName, 
			String device, 
			DeviceType deviceType, 
			String subject, 
			String body, 
			String[] choices)
	{
		this.notificationId = notificationId;
		this.senderAddress = senderAddress;
		this.dateTime = dateTime;
		this.userName = userName;
		this.device = device;
		this.deviceType = deviceType;
		this.subject = subject;
		this.body = body;
		this.choices = choices;
	}
	
	public long getNotificationId()
	{
		return notificationId;
	}
	
	public void setNotificationId(long notificationId)
	{
		this.notificationId = notificationId;
	}
	
	public InetAddress getSenderAddress()
	{
		return senderAddress;
	}
	
	public void setSenderAddress(InetAddress senderAddress)
	{
		this.senderAddress = senderAddress;
	}
	
	public Date getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(Date dateTime)
	{
		this.dateTime = dateTime;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getDevice()
	{
		return device;
	}
	
	public void setDevice(String device)
	{
		this.device = device;
	}
	
	public DeviceType getDeviceType()
	{
		return deviceType;
	}
	
	public void setDeviceType(DeviceType deviceType)
	{
		this.deviceType = deviceType;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getBody()
	{
		return body;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public String[] getChoices()
	{
		return choices;
	}
	
	public void setChoices(String[] choices)
	{
		this.choices = choices;
	}
	
	// Recipe for equals taken from <cite>Effective Java</cite>, chapter 3.
	public boolean equals(Object otherObject)
	{
		if (this == otherObject)
			return true;
		
		if ( !(otherObject instanceof Alert) )
			return false;
		
		Alert other = (Alert) otherObject;
		
		return 
			notificationId == other.notificationId &&
			senderAddress.equals(other.senderAddress) &&
			dateTime.equals(other.dateTime) &&
			userName.equals(other.userName) &&
			device.equals(other.device) &&
			deviceType.equals(other.deviceType) &&
			subject.equals(other.subject) && 
			body.equals(other.body) &&
			Arrays.equals(choices, other.choices);
	}
	
	// Recipe for hashCode taken from <cite>Effective Java</cite>, chapter 3.
	public int hashCode()
	{
		final int VALUE_1 = 17;
		final int VALUE_2 = 37;
		final int SHIFT_VALUE = 32;
		
		int result = VALUE_1;
		
		result = VALUE_2 * result + 
			( (int) (notificationId ^ (notificationId >>> SHIFT_VALUE)) );
		result = VALUE_2 * result + senderAddress.hashCode();
		result = VALUE_2 * result + dateTime.hashCode();
		result = VALUE_2 * result + userName.hashCode();
		result = VALUE_2 * result + device.hashCode();
		result = VALUE_2 * result + deviceType.hashCode();
		result = VALUE_2 * result + subject.hashCode();
		result = VALUE_2 * result + body.hashCode();
		
		for (int i = 0; i < choices.length; ++i)
		{
			result = VALUE_2 * result + choices[i].hashCode();
		}
		
		return result;
	}
}
