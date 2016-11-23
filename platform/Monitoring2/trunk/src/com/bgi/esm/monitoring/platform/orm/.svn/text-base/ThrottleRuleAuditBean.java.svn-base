package com.bgi.esm.monitoring.platform.orm;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.ThrottleRuleAudit;

/**
 * Entity bean supporting throttle rules
 * 
 * @ejb.bean 
 *   name="ThrottleRuleAuditEjb" 
 *   type="CMP" 
 *   cmp-version="2.x"
 *   reentrant="false" 
 *   view-type="local"
 *   local-jndi-name="${jndi.base}/ThrottleRuleAuditEjbLocalHome"
 *   description="throttle rule bean"
 * 
 * @ejb.persistence table-name="throttle_audit"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM ThrottleRuleAuditEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="ThrottleRuleAuditLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM ThrottleRuleAuditEjb AS x WHERE x.ruleKey = ?1" 
 *   unchecked="true"
 *
 * @ejb.finder 
 *   signature="java.util.Collection findByGroup(java.lang.String group)"
 *   query="SELECT OBJECT(x) FROM ThrottleRuleAuditEjb AS x WHERE x.group = ?1"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ThrottleRuleAuditBean implements EntityBean {

	/**
	 * Create a fresh database rule/row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public ThrottleRuleAuditEjbPK ejbCreate(ThrottleRuleAuditEjbPK key, ThrottleRuleAudit arg) throws CreateException {
		setRuleKey(key.getRuleKey());
		setValue(arg);

		return(null);
	}

	/**
	 * Update a database rule/row.  Key will not be altered.
	 * 
	 * @param arg fresh row datum
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(ThrottleRuleAudit arg) {
		setGroup(arg.getGroup());
		setStormLevel(new Integer(arg.getStormLevel()));
		setDuration(new Long(arg.getDuration()));
		setThreshold(new Integer(arg.getThreshold()));
		setAction(arg.getAction().toString());
		
		if (arg.isEnableStormMessages()) {
			setEnableStormMessages('t');
		} else {
			setEnableStormMessages('f');
		}
	}

	/**
	 * Return a database rule/row.  
	 * 
	 * @return row contents as a value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public ThrottleRuleAudit getValue() {
		ThrottleRuleAudit result = new ThrottleRuleAudit(getRuleKey());

		result.setGroup(getGroup());
		result.setStormLevel(getStormLevel().intValue());
		result.setDuration(getDuration().longValue());
		result.setThreshold(getThreshold().intValue());
		result.setAction(getAction());
		
		if (getEnableStormMessages() == 'f') {
			result.setEnableStormMessages(false);
		}

		return(result);
	}

	/**
	 * Define rule key.
	 * 
	 * @param arg rule key, cannot be null.
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRuleKey(String arg);

	/**
	 * Return rule key.
	 * 
	 * @return rule key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getRuleKey();

	/**
	 * Define OVO group
	 * 
	 * @param arg OVO group, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setGroup(String arg);

	/**
	 * Return OVO group
	 * 
	 * @return OVO group
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="bgi_group"
	 */
	public abstract String getGroup();

	/**
	 * Define the audit version num.
	 * 
	 * @param arg audit version num
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAuditVersionNum(long arg);

	/**
	 * Return the audit version num
	 * 
	 * @return the audit version num
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="audit_version_num"
	 */
	public abstract long getAuditVersionNum();

	/**
	 * Define the audit timestamp.
	 * 
	 * @param arg rule key (message group), cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAuditTimestamp(java.sql.Timestamp arg);

	/**
	 * Return the audit timestamp
	 * 
	 * @return the audit timestamp
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="audit_timestamp"
	 */
	public abstract java.sql.Timestamp getAuditTimestamp();

	/**
	 * Define the person who made the change
	 * 
	 * @param arg the person who made this change, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAuditModifiedBy( String arg );

	/**
	 * Return the person who made this change
	 * 
	 * @return the person who made this change
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="audit_modified_by"
	 */
	public abstract String getAuditModifiedBy();

	/**
	 * Define storm level
	 * 
	 * @param arg define storm level, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setStormLevel(java.lang.Integer arg);

	/**
	 * Return storm level
	 * 
	 * @return storm level
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="storm_level"
	 */
	public abstract java.lang.Integer getStormLevel();

	/**
	 * Define duration in seconds
	 * 
	 * @param arg duration in seconds, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setDuration(java.lang.Long arg);

	/**
	 * Return duration in seconds
	 * 
	 * @return duration in seconds
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="duration"
	 */
	public abstract java.lang.Long getDuration();

	/**
	 * Define threshold (message population)
	 * 
	 * @param arg threshold (message population), cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setThreshold(java.lang.Integer arg);

	/**
	 * Return threshold (message population)
	 * 
	 * @return threshold (message population)
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="threshold"
	 */
	public abstract java.lang.Integer getThreshold();

	/**
	 * Define throttle action
	 * 
	 * @param arg throttle action, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAction(String arg);

	/**
	 * Return throttle action
	 * 
	 * @return throttle action
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="action"
	 */
	public abstract String getAction();
	
	/**
	 * Define "in progress" message flag
	 * 
	 * @param arg true, enable "in progress" messages
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setEnableStormMessages(char arg);

	/**
	 * Return "in progress" message flag
	 * 
	 * @return "in progress" message flag
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_flag"
	 */
	public abstract char getEnableStormMessages();
}
