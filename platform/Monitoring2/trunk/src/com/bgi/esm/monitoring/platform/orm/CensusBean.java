package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import com.bgi.esm.monitoring.platform.shared.value.Census;

/**
 * Entity bean supporting message census
 * 
 * @ejb.bean 
 *   name="CensusEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/CensusEjbLocalHome"
 *   description="census bean"
 * 
 * @ejb.persistence table-name="census"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM CensusEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="CensusLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM CensusEjb AS x WHERE x.key = ?1"
 *   unchecked="true"
 *
 * @ejb.finder 
 *   signature="java.util.Collection findByGroup(java.lang.String group)"
 *   query="SELECT OBJECT(x) FROM CensusEjb AS x WHERE x.group = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class CensusBean implements EntityBean {

	/**
	 * @ejb.create-method
	 */
	public CensusEjbPK ejbCreate(Census arg) throws CreateException {
		setKey(arg.getRowKey());
		setValue(arg);

		return(null);
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(Census arg) {
		setGroup(arg.getGroup());
		setTimeStamp(new Timestamp(arg.getTimeStamp().getTimeInMillis()));
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public Census getValue() {
		Census result = new Census();

		result.setRowKey(getKey());

		result.setGroup(getGroup());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimeStamp().getTime());

		result.setTimeStamp(temp_calendar);

		return(result);
	}

	/**
	 * Key
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setKey(String arg);

	/**
	 * Key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getKey();

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
	public abstract void setTimeStamp(Timestamp arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="time_stamp"
	 */
	public abstract Timestamp getTimeStamp();
}
