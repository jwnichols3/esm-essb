package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;

/**
 * Entity bean supporting audit entries
 * 
 * @ejb.bean 
 *   name="AuditEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/AuditEjbLocalHome"
 *   description="audit bean"
 * 
 * @ejb.persistence table-name="audit"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM AuditEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="AuditLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM AuditEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findByMessageId (java.lang.String message_id)"
 *   query="SELECT OBJECT(x) FROM AuditEjb AS x WHERE x.messageId = ?1 ORDER BY x.timeStamp DESC"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class AuditBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException is create problem
	 * 
	 * @ejb.create-method
	 */
	public AuditEjbPK ejbCreate(AuditEjbPK key, Audit arg) throws CreateException {
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
	public void setValue(Audit arg) {
		long temp_time = arg.getTimeStamp().getTimeInMillis();
		setTimeStamp(new Timestamp(temp_time));

		setModule(arg.getModule().toString());
		setAction(arg.getAction());
		setMessageId(arg.getMessageId());
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public Audit getValue() {
		Audit result = new Audit(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimeStamp().getTime());
		result.setTimeStamp(temp_calendar);

		BussModule module = BussModule.getInstance(getModule());
		if (module == null) {
			result.setModule(BussModule.UNKNOWN);			
		} else {
			result.setModule(BussModule.getInstance(getModule()));	
		}
		
		result.setAction(getAction());
		result.setMessageId(getMessageId());

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
	 * @param arg buss module name, cannot be null
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
	 * Define audit action
	 * 
	 * @param arg audit action, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAction(String arg);

	/**
	 * Return audit action
	 * 
	 * @return audit action
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="action"
	 */
	public abstract String getAction();

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
}
