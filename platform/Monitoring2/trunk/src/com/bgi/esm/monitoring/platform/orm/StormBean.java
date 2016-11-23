package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;

/**
 * Entity bean supporting storm levels
 * 
 * @ejb.bean 
 *   name="StormEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/StormEjbLocalHome"
 *   description="storm bean"
 * 
 * @ejb.persistence table-name="storm"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM StormEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllInProgress()" 
 *   query="SELECT OBJECT(x) FROM StormEjb AS x WHERE x.stormLevel > 0 ORDER BY x.stormLevel DESC, x.spoolReminder DESC"
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="StormLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM StormEjb AS x WHERE x.key = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class StormBean implements EntityBean {

	/**
	 * @ejb.create-method
	 */
	public StormEjbPK ejbCreate(Storm arg) throws CreateException {
		setKey(arg.getGroup());
		setValue(arg);

		return(null);
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(Storm arg) {
		setStormLevel(new Integer(arg.getStormLevel()));
		setAction(arg.getAction().toString());
		setSpoolKey(arg.getSpoolKey());

		long temp_time = arg.getReminderTimeStamp().getTimeInMillis();
		setSpoolReminder(new Timestamp(temp_time));
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public Storm getValue() {
		Storm result = new Storm();

		result.setGroup(getKey());
		result.setStormLevel(getStormLevel().intValue());
		result.setAction(ThrottleAction.getInstance(getAction()));
		result.setSpoolKey(getSpoolKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getSpoolReminder().getTime());
		result.setReminderTimeStamp(temp_calendar);

		return(result);
	}

	/**
	 * Key (message group)
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setKey(String arg);

	/**
	 * Key (message group)
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="bgi_group"
	 */
	public abstract String getKey();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setStormLevel(java.lang.Integer arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="storm_level"
	 */
	public abstract java.lang.Integer getStormLevel();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAction(String arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="action"
	 */
	public abstract String getAction();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setSpoolKey(String arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="spool_key"
	 */
	public abstract String getSpoolKey();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setSpoolReminder(Timestamp arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="spool_reminder"
	 */
	public abstract Timestamp getSpoolReminder();
}
