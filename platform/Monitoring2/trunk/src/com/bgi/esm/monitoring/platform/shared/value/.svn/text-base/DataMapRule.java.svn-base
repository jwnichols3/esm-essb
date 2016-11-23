package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;

/**
 * Value object for data map rule.
 * 
 * Data map rules specify event actions based upon OVO message group.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public final class DataMapRule implements Serializable {
	
	/**
	 * empty ctor
	 */
	public DataMapRule() {
		//empty
	}
	
	/**
	 * ctor w/defined key (message group)
	 * 
	 * @param arg message group
	 * @throws NullPointerException if null arg
	 * @throws IllegalArgumentException if zero length arg
	 */
	public DataMapRule(String arg) {
		if (arg == null) {
			throw new NullPointerException("null message group");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty message group");
		}
		
		_group = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getGroup() {
		return(_group);
	}

    public void setGroup ( String group )
    {
        if ( null == group )
        {
            throw new NullPointerException ( "tried to initialize a datamap rule with a null group" );
        }

        if ( group.length() < 2 )
        {
            throw new IllegalArgumentException ( "group name was less than 2 characters long" );
        }

        _group = group;
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

    public String getPeregrineQueue()
    {
        return _p_queue;
    }

    public void setPeregrineQueue ( String queue_name )
    {
        /*
        if ( null == queue_name )
        {
            throw new NullPointerException ( "Null peregrine queue name" );
        }

        if ( queue_name.length() < 1 )
        {
            throw new IllegalArgumentException ( "Empty peregrine queue name" );
        }
        //*/

        _p_queue = queue_name;
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
		
		DataMapRule temp = (DataMapRule) arg;
		
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

        if (_allow_passthru_severity != temp.getAllowPassthruSeverity() ) {
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
            message.append ( ", AllowPassthruSeverity=" );
            message.append ( Boolean.toString ( _allow_passthru_severity ) );
            message.append ( " )" );
		return message.toString();
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
     *  As defined by GSS/Service Center people
     */
    private String _p_queue   = "ENTERPRISE SYSTEMS MANAGEMENT";

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
