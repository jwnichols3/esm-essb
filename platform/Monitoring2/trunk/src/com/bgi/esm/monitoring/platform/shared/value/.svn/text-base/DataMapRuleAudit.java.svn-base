package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Value object for data map rule.
 * 
 * Data map rules specify event actions based upon OVO message group.
 * 
 * @author Dennis Lin (linden)
 */
public final class DataMapRuleAudit implements Serializable {
	
	/**
	 * empty ctor
	 */
	public DataMapRuleAudit() {
		//empty
	}
	
	/**
	 * ctor w/defined key (message group)
	 * 
	 * @param arg message group
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public DataMapRuleAudit(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message group");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty message group");
		}
		
		_group = arg;
	}

    public DataMapRuleAudit ( DataMapRule rule )
    {
		if ( rule == null) {
			throw new NullPointerException( "tried to initialize a DataMapRuleAudit object with a null DataMapRule object" );
		}

        _group = rule.getGroup();
        setMethod                ( rule.getMethod()                );
        setAlarmpointGroup       ( rule.getAlarmpointGroup()       );
        setAlarmpointScript      ( rule.getAlarmpointScript()      );
        setPeregrineQueue        ( rule.getPeregrineQueue()        );
        setPeregrineCategory     ( rule.getPeregrineCategory()     );
        setPeregrineSubCategory  ( rule.getPeregrineSubCategory()  );
        setPeregrineProduct      ( rule.getPeregrineProduct()      );
        setPeregrineProblem      ( rule.getPeregrineProblem()      );
        setPeregrineAssignment   ( rule.getPeregrineAssignment()   );
        setPeregrineLocation     ( rule.getPeregrineLocation()     );
        setAllowPassthruSeverity ( rule.getAllowPassthruSeverity() );
    }

    private long _audit_version_num = 0L;

    public long getAuditVersionNum()
    {
        return _audit_version_num;
    }

    public void setAuditVersionNum ( long audit_version_num )
    {
        _audit_version_num = audit_version_num;
    }

    private String _audit_modified_by = null;

    public String getAuditModifiedBy()
    {
        return _audit_modified_by;
    }

    public void setAuditModifiedBy ( String audit_modified_by )
    {
        _audit_modified_by = audit_modified_by;
    }

    private java.sql.Timestamp _audit_timestamp = new java.sql.Timestamp ( System.currentTimeMillis() );

    public java.sql.Timestamp getAuditTimestamp()
    {
        return _audit_timestamp;
    }

    public void setAuditTimestamp ( java.sql.Timestamp audit_timestamp )
    {
        _audit_timestamp = audit_timestamp;
    }

    /**
     *
     */
    public void setGroup ( String group )
    {
        if ( null == group )
        {
            throw new NullPointerException ( "Tried setting a DataMapRuleAudit object to null" );
        }

        _group = group;
    }

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getGroup() {
		return(_group);
	}

	/**
	 * Define service method
	 * 
	 * @param arg service method
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setMethod(String arg) {
		if (arg == null) {
			throw new NullPointerException("null method");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty method");
		}
		
		_method = arg;
	}

	/**
	 * Retrieve service method
	 * 
	 * @return service method
	 */
	public String getMethod() {
		return(_method);
	}

	/**
	 * Define alarm point group
	 * 
	 * @param arg alarm point group
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setAlarmpointGroup(String arg) {
		if (arg == null) {
			throw new NullPointerException("null AP group");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty AP group");
		}
		
		_ap_group = arg;
	}

	/**
	 * Retrieve alarm point group
	 * 
	 * @return alarm point group
	 */
	public String getAlarmpointGroup() {
		return(_ap_group);
	}

	/**
	 * Define alarm point script
	 * 
	 * @param arg alarm point script
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setAlarmpointScript(String arg) {
		if (arg == null) {
			throw new NullPointerException("null AP script");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty AP script");
		}
		
		_ap_script = arg;
	}

	/**
	 * Return alarm point script
	 * 
	 * @return alarm point script
	 */
	public String getAlarmpointScript() {
		return(_ap_script);
	}

    public void setPeregrineQueue ( String queue_name )
    {
        if ( null == queue_name )
        {
            throw new NullPointerException ( "null peregrine queue" );
        }

        if ( queue_name.length() < 1 )
        {
            throw new IllegalArgumentException ( "empty peregrine queue name" );
        }

        _p_queue = queue_name;
    }

    public String getPeregrineQueue()
    {
        return _p_queue;
    }

	/**
	 * Define peregrine category
	 * 
	 * @param arg peregrine category
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineCategory(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine category");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine category");
		}
		
		_p_category = arg;
	}

	/**
	 * Return peregrine category
	 * 
	 * @return peregrine category
	 */
	public String getPeregrineCategory() {
		return(_p_category);
	}

	/**
	 * Define peregrine subcategory
	 * 
	 * @param arg peregrine subcategory
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineSubCategory(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine subcategory");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine subcategory");
		}
		
		_p_subcategory = arg;
	}

	/**
	 * Return peregrine subcategory
	 * 
	 * @return peregrine subcategory
	 */
	public String getPeregrineSubCategory() {
		return(_p_subcategory);
	}

	/**
	 * Define peregrine product
	 * 
	 * @param arg peregrine product
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineProduct(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine product");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine product");
		}
		
		_p_product = arg;
	}

	/**
	 * Return peregrine product
	 * 
	 * @return peregrine product
	 */
	public String getPeregrineProduct() {
		return(_p_product);
	}

	/**
	 * Define peregrine problem
	 * 
	 * @param arg peregrine problem
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineProblem(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine problem");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine problem");
		}
			
		_p_problem = arg;
	}

	/**
	 * Return peregrine problem
	 * 
	 * @return peregrine problem
	 */
	public String getPeregrineProblem() {
		return(_p_problem);
	}

	/**
	 * Define peregrine assignment
	 * 
	 * @param arg peregrine assignment
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineAssignment(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine assignment");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine assignment");
		}
					
		_p_assignment = arg;
	}

	/**
	 * Return peregrine assignment
	 * 
	 * @return peregrine assignment
	 */
	public String getPeregrineAssignment() {
		return(_p_assignment);
	}

	/**
	 * Define peregrine location
	 * 
	 * @param arg peregine location
	 * @throws NullPointerException if null arg 
	 * @throws IllegalArgumentException if zero length arg 
	 */
	public void setPeregrineLocation(String arg) {
		if (arg == null) {
			throw new NullPointerException("null peregrine location");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty peregrine location");
		}		
		
		_p_location = arg;
	}

	/**
	 * Return peregrine location
	 * 
	 * @return peregrine location
	 */
	public String getPeregrineLocation() {
		return(_p_location);
	}
    
    public void setAllowPassthruSeverity ( boolean allow_passthrough_severity ) {
        _allow_passthru_severity = allow_passthrough_severity;
    }

    public boolean getAllowPassthruSeverity() {
        return _allow_passthru_severity;
    }

	/**
	 * Return hash code value for this object, employs all fields.
	 * 
	 * @return hash code value for this object, employs all fields.
	 */
	public int hashCode() {
		int result = _group.hashCode() * _method.hashCode();

		result *= _ap_script.hashCode();

		result *= _p_assignment.hashCode() * _p_location.hashCode();

		return(result);
	}

	/**
	 * Return true if the Rules match. Compares all fields.
	 * 
	 * @param arg candidate to match
	 * @return true if successful match
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}
		
		DataMapRuleAudit temp = (DataMapRuleAudit) arg;
		
		if (!_group.equals(temp.getGroup())) {
			return(false);
		}

		if (!_method.equals(temp.getMethod())) {
			return(false);
		}

		if (!_ap_script.equals(temp.getAlarmpointScript())) {
			return(false);
		}

		if (!_p_assignment.equals(temp.getPeregrineAssignment())) {
			return(false);
		}

		if (!_p_location.equals(temp.getPeregrineLocation())) {
			return(false);
		}

		return(true);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() 
    {
        StringBuilder message = new StringBuilder();
            message.append ( "DataMapRule ( group=" );
            message.append ( _group );
            message.append ( ", VersionNum=" );
            message.append ( _audit_version_num );
            message.append ( ", method=" );
            message.append ( _method );
            message.append ( ", APGroup=" );
            message.append ( _ap_group );
            message.append ( ", APScript=" );
            message.append ( _ap_script );
            message.append ( ", PeregrineCategory=" );
            message.append ( _p_category );
            message.append ( ", PeregrineSubCategory=" );
            message.append ( _p_subcategory );
            message.append ( ", Product=" );
            message.append ( _p_product );
            message.append ( ", Problem=" );
            message.append ( _p_problem );
            message.append ( ", Assignment=" );
            message.append ( _p_assignment );
            message.append ( ", Location=" );
            message.append ( _p_location );
            message.append ( " )" );
        return message.toString();

		//return("DataMapRuleAudit:" + _group + ":" + _method + ":" + _ap_group + ":" + _ap_script + ":" + _p_category + ":" + _p_subcategory + ":" + _p_product + ":" + _p_problem + ":" + _p_assignment + ":" + _p_location);
	}
	
	/**
	 * Serial version identifier. 
	 * Be sure to update this when the class is updated.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * group, i.e. "accounts-barclaysglobal"
	 */
	private String _group = DEFAULT_KEY;

	/**
	 * method, i.e. "ticket"
	 */
	private String _method = "bogus";

	/**
	 * alarm point group, i.e. "SUPPORT-accounts-barclaysglobal"
	 */
	private String _ap_group = "bogus";

	/**
	 * alarm point script, i.e. "BGI VPO"
	 */
	private String _ap_script = "bogus";

    /**
     *  Service Center queue, as defined by GSS
     */
    private String _p_queue = "bogus";

	/**
	 * peregrine category, i.e. "SOFTWARE"
	 */
	private String _p_category = "bogus";

	/**
	 * peregrine subcategory, i.e. "SOFTWARE"
	 */
	private String _p_subcategory = "bogus";

	/**
	 * peregrine product, i.e. "ACCOUNTS.BARCLAYSGLOBAL.COM"
	 */
	private String _p_product = "bogus";

	/**
	 * peregrine problem, i.e. "FAULT"
	 */
	private String _p_problem = "bogus";

	/**
	 * peregrine assignment group, i.e. "5000"
	 */
	private String _p_assignment = "bogus";

	/**
	 * peregrine location, i.e. "GLOBAL"
	 */
	private String _p_location = "bogus";

    private boolean _allow_passthru_severity = false;

	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";
}
