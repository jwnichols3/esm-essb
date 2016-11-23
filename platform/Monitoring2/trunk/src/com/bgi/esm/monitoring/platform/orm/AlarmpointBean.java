package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;

/**
 * Entity bean supporting Alarmpoint entries
 * 
 * @ejb.bean 
 *   name="AlarmpointEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/AlarmpointEjbLocalHome"
 *   description="alarmpoint bean"
 * 
 * @ejb.persistence table-name="alarmpoint"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM AlarmpointEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="AlarmpointLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM AlarmpointEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class AlarmpointBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException is create problem
	 * 
	 * @ejb.create-method
	 */
	public AlarmpointEjbPK ejbCreate(AlarmpointEjbPK key, Alarmpoint arg) throws CreateException {
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
	public void setValue(Alarmpoint arg) {
		long temp_time = arg.getTimestamp().getTimeInMillis();
		setTimestamp  ( new Timestamp(temp_time));
        setMessageId  ( arg.getMessageId()   );
        setEventId    ( arg.getEventId()     );
        setAlarmpointMessage  ( arg.getAlarmpointMessage()   );
        setAlarmpointDevice   ( arg.getAlarmpointDevice()    );
        setNotificationTarget ( arg.getAlarmpointNotificationTarget() );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public Alarmpoint getValue() {
		Alarmpoint result = new Alarmpoint(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimestamp().getTime());

		result.setTimestamp  ( temp_calendar    );
        result.setMessageId  ( getMessageId()   );
        result.setEventId    ( getEventId()     );
        result.setAlarmpointDevice   ( getAlarmpointDevice()    );
        result.setAlarmpointMessage  ( getAlarmpointMessage()   );
        result.setAlarmpointNotificationTarget ( getNotificationTarget() );

		return(result);
	}

	/**
	 * Define row key.
	 *
	 * @param arg row key, cannot be null
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
	public abstract void setTimestamp(Timestamp arg);

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="time_stamp"
	 */
	public abstract Timestamp getTimestamp();

	/**
	 * Define Alarmpoint Event ID
	 * 
	 * @param arg buss module name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setEventId(String arg);

	/**
	 * Return buss module name
	 * 
	 * @return buss module name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_event_id"
	 */
	public abstract String getEventId();

	/**
	 * Define OVI message ID
	 * 
	 * @param arg OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessageId(String arg);

	/**
	 * Return OVI message ID
	 * 
	 * @return OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_id"
	 */
	public abstract String getMessageId();

	/**
	 * Define the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @param arg the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setIncidentID ( long arg);

	/**
	 * Return the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @return the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="incident_id"
	 */
	public abstract long getIncidentID();

	/**
	 * Define the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @param arg the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAlarmpointMessage ( String arg );

	/**
	 * Return the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @return the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_message"
	 */
	public abstract String getAlarmpointMessage();

	/**
	 * Define the device that Alarmpoint is going to notify about
	 * 
	 * @param arg the device, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAlarmpointDevice ( String arg );

	/**
	 * Return the iDevice that Alarmpoint is going to notify about
	 * 
	 * @return the device, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_device"
	 */
	public abstract String getAlarmpointDevice();

	/**
	 * Define the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @param arg the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setNotificationTarget ( String arg );

	/**
	 * Return the incident ID (used to correlate to a Service Center ticket)
	 * 
	 * @return the incident ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_notif_target"
	 */
	public abstract String getNotificationTarget();
}
