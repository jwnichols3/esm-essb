package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleAction;

/**
 * Entity bean monotonic sequence generation.
 * Please do not use this obvious hack for high volume applications.
 * 
 * @ejb.bean 
 *   name="MonotonicDataMapEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/MonotonicDataMapEjbLocalHome"
 *   description="monotonic sequence bean"
 * 
 * @ejb.persistence table-name="monotonic_datamap"
 * 
 * @ejb.finder 
 *   signature="MonotonicLocal findSequence()"
 *   query="SELECT OBJECT(x) FROM MonotonicDataMapEjb AS x WHERE x.key = 1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class MonotonicDataMapBean implements EntityBean {

	/**
	 * @ejb.create-method
	 */
	public MonotonicDataMapEjbPK ejbCreate(int key, int value) throws CreateException {
		setKey(key);
		setValue(value);

		return(null);
	}

	/**
	 * Key (message group)
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setKey(Integer arg);

	/**
	 * Key (message group)
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract Integer getKey();

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setValue(java.lang.Integer arg);

	/**
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="current_value"
	 */
	public abstract java.lang.Integer getValue();
}
