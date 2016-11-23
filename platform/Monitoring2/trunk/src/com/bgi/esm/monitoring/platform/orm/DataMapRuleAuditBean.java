package com.bgi.esm.monitoring.platform.orm;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;

/**
 * Entity bean supporting data map rules
 * 
 * @ejb.bean 
 *   name="DataMapRuleAuditEjb" 
 *   type="CMP" 
 *   cmp-version="2.x"
 *   reentrant="false" 
 *   view-type="local"
 *   local-jndi-name="${jndi.base}/DataMapRuleAuditEjbLocalHome"
 *   description="data map rule audit bean"
 * 
 * @ejb.persistence table-name="data_map_audit"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM DataMapRuleAuditEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAuditVersions ( java.lang.String group )" 
 *   query="SELECT OBJECT(x) FROM DataMapRuleAuditEjb AS x WHERE x.ruleKey= ?1" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="DataMapRuleAuditLocal findByKey(java.lang.String key, long audit_version_num )"
 *   query="SELECT OBJECT(x) FROM DataMapRuleAuditEjb AS x WHERE x.ruleKey = ?1 AND x.auditVersionNum = ?2" 
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class DataMapRuleAuditBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key message group
	 * @param arg fresh row datum
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public DataMapRuleAuditEjbPK ejbCreate(DataMapRuleAudit arg) throws CreateException {
		setRuleKey(arg.getGroup());
		setValue(arg);

		return(null);
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(DataMapRuleAudit arg) 
    {
        setRuleKey         ( arg.getGroup()                );
        setAuditVersionNum ( arg.getAuditVersionNum()      );
        setAuditTimestamp  ( arg.getAuditTimestamp()       );
        setAuditModifiedBy ( arg.getAuditModifiedBy()      );
		setBgiMethod       ( arg.getMethod()               );
		setApGroup         ( arg.getAlarmpointGroup()      );
		setApScript        ( arg.getAlarmpointScript()     );
        setPerQueue        ( arg.getPeregrineQueue()       );
		setPerCat          ( arg.getPeregrineCategory()    );
		setPerSubCat       ( arg.getPeregrineSubCategory() );
		setPerProduct      ( arg.getPeregrineProduct()     );
		setPerProblem      ( arg.getPeregrineProblem()     );
		setPerAssign       ( arg.getPeregrineAssignment()  );
		setPerLocation     ( arg.getPeregrineLocation()    );
        setAllowPassthruSeverity ( arg.getAllowPassthruSeverity() );
	}

	/**
	 * @ejb.interface-method view-type="local"
	 */
	public DataMapRuleAudit getValue() {
		DataMapRuleAudit result = new DataMapRuleAudit(getRuleKey());

        result.setGroup                ( getRuleKey()         );
        result.setAuditVersionNum      ( getAuditVersionNum() );
        result.setAuditTimestamp       ( getAuditTimestamp()  );
        result.setAuditModifiedBy      ( getAuditModifiedBy() );
		result.setMethod               ( getBgiMethod()       );
		result.setAlarmpointGroup      ( getApGroup()         );
		result.setAlarmpointScript     ( getApScript()        );
        result.setPeregrineQueue       ( getPerQueue()        );
		result.setPeregrineCategory    ( getPerCat()          );
		result.setPeregrineSubCategory ( getPerSubCat()       );
		result.setPeregrineProduct     ( getPerProduct()      );
		result.setPeregrineProblem     ( getPerProblem()      );
		result.setPeregrineAssignment  ( getPerAssign()       );
		result.setPeregrineLocation    ( getPerLocation()     );
        result.setAllowPassthruSeverity ( getAllowPassthruSeverity() );

		return(result);
	}

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
	 * @ejb.pk-field
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
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="audit_modified_by"
	 */
	public abstract String getAuditModifiedBy();

	/**
	 * Define rule key.
	 * 
	 * @param arg rule key (message group), cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRuleKey(String arg);

	/**
	 * Return rule key (message group)
	 * 
	 * @return rule key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="bgi_group"
	 */
	public abstract String getRuleKey();

	/**
	 * Define BGI method name
	 * 
	 * @param arg method name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setBgiMethod(String arg);

	/**
	 * Return BGI method name
	 * 
	 * @return BGI method name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="bgi_method"
	 */
	public abstract String getBgiMethod();

	/**
	 * Define AP group name
	 * 
	 * @param arg AP group name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setApGroup(String arg);

	/**
	 * Return AP group name
	 * 
	 * @return AP group name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_group"
	 */
	public abstract String getApGroup();

	/**
	 * Define AP script name
	 * 
	 * @param arg AP script name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setApScript(String arg);

	/**
	 * Return AP script name
	 * 
	 * @return AP script name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_script"
	 */
	public abstract String getApScript();

	/**
	 * Define Peregrine queue
	 * 
	 * @param arg peregrine queue, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerQueue(String arg);

	/**
	 * Return Peregrine queue
	 * 
	 * @return peregrine queue
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_queue"
	 */
	public abstract String getPerQueue();

	/**
	 * Define Peregrine category
	 * 
	 * @param arg peregrine category, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerCat(String arg);

	/**
	 * Return Peregrine category
	 * 
	 * @return peregrine category
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_cat"
	 */
	public abstract String getPerCat();

	/**
	 * Define Peregrine subcategory
	 * 
	 * @param arg peregrine subcategory, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerSubCat(String arg);

	/**
	 * Return Peregrine subcategory
	 * 
	 * @return peregrine subcategory
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_subcat"
	 */
	public abstract String getPerSubCat();

	/**
	 * Define Peregrine product
	 * 
	 * @param arg peregrine product, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerProduct(String arg);

	/**
	 * Return Peregrine product
	 * 
	 * @return peregrine product
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_product"
	 */
	public abstract String getPerProduct();

	/**
	 * Define Peregrine product
	 * 
	 * @param peregrine product, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerProblem(String arg);

	/**
	 * Return Peregrine problem
	 * 
	 * @return peregrine problem
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_problem"
	 */
	public abstract String getPerProblem();

	/**
	 * Define Peregrine assignment
	 * 
	 * @param arg peregrine assignment, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerAssign(String arg);

	/**
	 * Return Peregrine assignment
	 * 
	 * @return peregrine assignemnt
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_assign"
	 */
	public abstract String getPerAssign();

	/**
	 * Define Peregrine location
	 * 
	 * @param arg peregrine location
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setPerLocation(String arg);

	/**
	 * Return Peregrine location
	 * 
	 * @return peregrin location
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="per_location"
	 */
	public abstract String getPerLocation();

	/**
	 * Define flag to allow Openview severity to pass-through to the Service Center ticket
	 * 
	 * @param arg flag to allow Openview severity to pass-through to the Service Center ticket
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAllowPassthruSeverity(boolean arg);

	/**
	 * Return flag to allow Openview severity to pass-through to the Service Center ticket
	 * 
	 * @return flag to allow Openview severity to pass-through to the Service Center ticket
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="allow_passthru_severity"
	 */
	public abstract boolean getAllowPassthruSeverity();
}
