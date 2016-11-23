package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.EebProperty;

/**
 * Entity bean supporting suppression rules
 * 
 * @ejb.bean 
 *   name="EebPropertyEjb" 
 *   type="CMP" 
 *   cmp-version="2.x"
 *   reentrant="false" 
 *   view-type="local"
 *   local-jndi-name="${jndi.base}/EebPropertyEjbLocalHome"
 *   description="EEB property bean"
 * 
 * @ejb.persistence table-name="eeb_properties"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM EebPropertyEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="EebPropertyLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM EebPropertyEjb AS x WHERE x.propertyName= ?1" 
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class EebPropertyBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated rule key
	 * @param arg new EEB property
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public EebPropertyEjbPK ejbCreate( EebProperty arg) throws CreateException {
		setPropertyName(arg.getPropertyName());
		setValue(arg);

		return(null);
	}

	/**
	 * Update a database row.  Key will not be altered.
	 * 
	 * @param arg fresh row datum
	 * @throws IllegalArgumentException if invalid times
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(EebProperty arg) {
        if ( null == arg )
        {
            throw new NullPointerException ( "Tried to set EEB Property Bean with a null value object" );
        }

        setPropertyValue ( arg.getPropertyValue() );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public EebProperty getValue() {

		EebProperty result = new EebProperty ( getPropertyName() );
        result.setPropertyValue ( getPropertyValue() );

		return(result);
	}

	/**
	 * Define property name.
	 * 
	 * @param arg row/property name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPropertyName(String arg);

	/**
	 * Return property name.
	 * 
	 * @return row/property name
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="property_name"
	 */
	public abstract String getPropertyName();

	/**
	 * Define property value.
	 * 
	 * @param arg row/property value, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPropertyValue(String arg);

	/**
	 * Return property value.
	 * 
	 * @return row/property value
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="property_value"
	 */
	public abstract String getPropertyValue();
}
