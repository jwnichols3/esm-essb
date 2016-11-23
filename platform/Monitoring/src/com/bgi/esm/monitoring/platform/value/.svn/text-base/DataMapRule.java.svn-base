package com.bgi.esm.monitoring.platform.value;

/**
 * Value object for data map rule. 
 * Data map rules come from a configuration file and specify event actions 
 * based upon OVO message group.
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 */
public class DataMapRule extends AbstractMessage {

	/**
	 * Define message group
	 * 
	 * @param arg message group
	 */
	public void setGroup(String arg) {
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

	/**
	 * Define service method
	 * 
	 * @param arg service method
	 */
	public void setMethod(String arg) {
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
	 */
	public void setAlarmPointGroup(String arg) {
		_ap_group = arg;
	}

	/**
	 * Retrieve alarm point group
	 * 
	 * @return alarm point group
	 */
	public String getAlarmPointGroup() {
		return(_ap_group);
	}

	/**
	 * Define alarm point script
	 * 
	 * @param arg alarm point script
	 */
	public void setAlarmPointScript(String arg) {
		_ap_script = arg;
	}

	/**
	 * Return alarm point script
	 * 
	 * @return alarm point script
	 */
	public String getAlarmPointScript() {
		return(_ap_script);
	}

	/**
	 * Define peregrine category
	 * 
	 * @param arg peregrine category
	 */
	public void setPeregrineCategory(String arg) {
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
	 */
	public void setPeregrineSubCategory(String arg) {
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
	 */
	public void setPeregrineProduct(String arg) {
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
	 */
	public void setPeregrineProblem(String arg) {
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
	 */
	public void setPeregrineAssignment(String arg) {
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
	 */
	public void setPeregrineLocation(String arg) {
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
	public boolean equals(DataMapRule arg) {
		if (!_group.equals(arg.getGroup())) {
			return(false);
		}

		if (!_method.equals(arg.getMethod())) {
			return(false);
		}

		if (!_ap_script.equals(arg.getAlarmPointScript())) {
			return(false);
		}

		if (!_p_assignment.equals(arg.getPeregrineAssignment())) {
			return(false);
		}

		if (!_p_location.equals(arg.getPeregrineLocation())) {
			return(false);
		}

		return(true);
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() {
		return(_group + ":" + _method + ":" + _ap_group + ":" + _ap_script + ":" + _p_category + ":" + _p_subcategory + ":" + _p_product + ":" + _p_problem + ":" + _p_assignment + ":" + _p_location);
	}

	/**
	 * Return DataMapRule as a XML formatted String
	 * 
	 * @return DataMapRule as a XML formatted String
	 */
	public String toXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<DataMap>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <group>" + _group + "</group>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <method>" + _method + "</method>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <alarm_point_group>" + _ap_group + "</alarm_point_group>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <alarm_point_script>" + _ap_script + "</alarm_point_script>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_category>" + _p_category + "</peregrine_category>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_subcategory>" + _p_subcategory + "</peregrine_subcategory>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_product>" + _p_product + "</peregrine_product>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_problem>" + _p_problem + "</peregrine_problem>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_assignment>" + _p_assignment + "</peregrine_assignment>");
		sb.append(LINE_SEPARATOR);

		sb.append("  <peregrine_location>" + _p_location + "</peregrine_location>");
		sb.append(LINE_SEPARATOR);

		sb.append("</DataMap>");
		sb.append(LINE_SEPARATOR);

		return(sb.toString());
	}

	/**
	 * group, i.e. "accounts-barclaysglobal"
	 */
	private String _group;

	/**
	 * method, i.e. "ticket"
	 */
	private String _method;

	/**
	 * alarm point group, i.e. "SUPPORT-accounts-barclaysglobal"
	 */
	private String _ap_group;

	/**
	 * alarm point script, i.e. "BGI VPO"
	 */
	private String _ap_script;

	/**
	 * peregrine category, i.e. "SOFTWARE"
	 */
	private String _p_category;

	/**
	 * peregrine subcategory, i.e. "SOFTWARE"
	 */
	private String _p_subcategory;

	/**
	 * peregrine product, i.e. "ACCOUNTS.BARCLAYSGLOBAL.COM"
	 */
	private String _p_product;

	/**
	 * peregrine problem, i.e. "FAULT"
	 */
	private String _p_problem;

	/**
	 * peregrine assignment group, i.e. "5000"
	 */
	private String _p_assignment;

	/**
	 * peregrine location, i.e. "GLOBAL"
	 */
	private String _p_location;
}

/*
 * Development Environment: 
 *   Fedora 4 
 *   Sun Java Developers Kit 1.5.0_06
 */