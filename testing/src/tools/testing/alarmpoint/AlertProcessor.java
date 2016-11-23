/**
 * AlertProcessor.java
 * @author hannrus
 */
package tools.testing.alarmpoint;

/**
 * The <code>AlertReceiverServer</code> is constructed with one of these.
 * It tells the <code>AlertReceiverServer</code> what to do with the alerts 
 * it receives.
 * 
 * @author hannrus
 */
public interface AlertProcessor
{
	void process(Alert alert);
}
