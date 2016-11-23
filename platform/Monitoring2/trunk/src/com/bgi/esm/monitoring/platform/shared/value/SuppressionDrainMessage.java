package com.bgi.esm.monitoring.platform.shared.value;

/**
 * @author Dennis Lin (linden)
 *
 */
public class SuppressionDrainMessage extends AbstractMessage {

    private String message = "suppression drain message";

    /**
     *  Set the message string
     */
    public void setMessageString ( String new_message )
    {
        message = new_message;
    }

	/**
	 * Return SuppressionDrainMessage as a String
	 * 
	 * @return SuppressionDrainMessage as a String
	 */
	public String toString() {
		return message;
	}

	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;
}
