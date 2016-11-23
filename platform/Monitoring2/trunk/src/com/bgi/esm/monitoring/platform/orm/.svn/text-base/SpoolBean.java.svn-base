package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Spool;

/**
 * Entity bean supporting spool activity
 * 
 * @ejb.bean 
 *   name="SpoolEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/SpoolEjbLocalHome"
 *   description="spool bean"
 * 
 * @ejb.persistence table-name="spool"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM SpoolEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="SpoolLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM SpoolEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SpoolBean implements EntityBean {

	/**
	 * Create a fresh database row
	 *
	 * @ejb.create-method
	 */
    public SpoolEjbPK ejbCreate(SpoolEjbPK key, Spool arg) throws CreateException {
		setRowKey(key.getRowKey());
		setValue(arg);

		return(null);
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(Spool arg) {
		long temp_time = arg.getTimeStamp().getTimeInMillis();
		setTimeStamp(new Timestamp(temp_time));

		setGroup(arg.getGroup());
		setSpoolKey(arg.getSpoolKey());
		setOvoKey(arg.getOvoKey());
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public Spool getValue() {
	    Spool result = new Spool(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimeStamp().getTime());
		result.setTimeStamp(temp_calendar);

		result.setGroup(getGroup());
		result.setSpoolKey(getSpoolKey());
		result.setOvoKey(getOvoKey());

		return(result);
	}

	/**
	 * Key (message group)
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRowKey(String arg);

	/**
	 * Key (message group)
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getRowKey();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setTimeStamp(Timestamp arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="time_stamp"
	 */
	public abstract Timestamp getTimeStamp();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setGroup(String arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="bgi_group"
	 */
	public abstract String getGroup();

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
	public abstract void setOvoKey(String arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ovo_key"
	 */
	public abstract String getOvoKey();
}
