package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.RawOvi;

/**
 * Entity bean supporting data map rules
 * 
 * @ejb.bean 
 *   name="RawOviEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/RawOviEjbLocalHome"
 *   description="raw OVI bean"
 * 
 * @ejb.persistence table-name="raw_ovi"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM RawOviEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="RawOviLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM RawOviEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class RawOviBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public RawOviEjbPK ejbCreate(RawOviEjbPK key, RawOvi arg) throws CreateException {
		setRowKey(key.getRowKey());
		setValue(arg);

		return(null);
	}

	/**
	 * Update a database row.  Key will not be altered.
	 * 
	 * @param arg fresh row datum
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(RawOvi arg) {
		long temp_time = arg.getTimeStamp().getTimeInMillis();
		setTimeStamp(new Timestamp(temp_time));

		setModule(arg.getModule().toString());
		setMessageId(arg.getMessageId());
		setXml(arg.getXmlPayload());
	}

	/**
	 * Return a database row
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public RawOvi getValue() {
		RawOvi result = new RawOvi(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimeStamp().getTime());
		result.setTimeStamp(temp_calendar);

		result.setModule(BussModule.getInstance(getModule()));
		result.setMessageId(getMessageId());
		result.setXmlPayload(getXml());

		return(result);
	}

	/**
	 * Define row key.
	 * 
	 * @param row key, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRowKey(String arg);

	/**
	 * Return row key.
	 * 
	 * @return row key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getRowKey();

	/**
	 * Define time stamp
	 * 
	 * @param arg time stamp, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setTimeStamp(Timestamp arg);

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="time_stamp"
	 */
	public abstract Timestamp getTimeStamp();

	/**
	 * Define buss module name
	 * 
	 * @param arg module name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setModule(String arg);

	/**
	 * Return buss module name
	 * 
	 * @return buss module name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="module"
	 */
	public abstract String getModule();

	/**
	 * Define OVI message ID
	 *
	 * @param arg OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessageId(String arg);

	/**
	 * Return OIV message ID
	 * 
	 * @return OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_id"
	 */
	public abstract String getMessageId();

	/**
	 * Define raw OVI XML message
	 * 
	 * @param arg raw OVI XML message, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setXml(String arg);

	/**
	 * Return raw OVI XML message
	 * 
	 * @return raw OVI XML message
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="xml"
	 */
	public abstract String getXml();
}
