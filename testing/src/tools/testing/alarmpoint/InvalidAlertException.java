/**
 * InvalidAlertException.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

/**
 * Exception thrown by <code>AlertFactory</code> if the 
 * alert received from an input stream can't be parsed into a 
 * valid <code>Alert</code>.
 * 
 * @author hannrus
 *
 */
@SuppressWarnings("serial")
public class InvalidAlertException extends Exception
{
	public InvalidAlertException()
	{
		super();
	}
	
	public InvalidAlertException(Exception e)
	{
		super(e);
	}
	
	public InvalidAlertException(String s)
	{
		super(s);
	}
}
