package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

/**
 * Value object for configurable EEB properties
 * 
 * @author Dennis Lin (linden)
 */
public final class EebProperty implements Serializable {

    final public static String PROPERTY_SERVICECENTER_MAX_NUM_UPDATES = "ServiceCenter.max_num_updates";
    final public static String PROPERTY_SERVICECENTER_ACTIVE = "ServiceCenter.active";
    final public static String PROPERTY_ALARMPOINT_ACTIVE    = "Alarmpoint.active";
    final public static String DEFAULT_KEY = "default";
    private String _property_name          = DEFAULT_KEY;
    private String _property_value         = "";

    public EebProperty ( String property_name )
    {
        _property_name = property_name;
    }

    public void setPropertyName ( String property_name )
    {
        _property_name = property_name;
    }

    public String getPropertyName ()
    {
        return _property_name;
    }

    public void setPropertyValue ( String property_value )
    {
        _property_value = property_value;
    }

    public String getPropertyValue()
    {
        return _property_value;
    }

	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
	 */
	public int hashCode() {
		int result = _property_name.hashCode() * _property_value.hashCode();

		return(result);
	}

	/**
	 * Return true if the Rules match. Compares all fields.
	 * 
	 * @param arg
	 *            candidate to match
	 * @return true if successful match
	 */
	public boolean equals ( Object arg ) 
    {
        EebProperty props = (EebProperty) arg;

		return (( getPropertyName().equals  ( props.getPropertyName()  ) ) && 
                ( getPropertyValue().equals ( props.getPropertyValue() ) ));
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
        StringBuilder message = new StringBuilder ( "EEBProperty ( PropertyName, PropertyValue ) = ( " );
        message.append ( _property_name );
        message.append ( ", " );
        message.append ( _property_value );
        message.append ( " )" );

        return message.toString();
	}
}
