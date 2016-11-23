package com.bgi.esm.monitoring.portlets.Throttle.forms;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.portlets.Throttle.HibernateUtil;
import com.bgi.esm.monitoring.portlets.Throttle.Toolkit;

public class Throttle extends ActionForm {
	public static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd MMM yyyy - HH:mm");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1175099742854L;
	private static final Logger _log = Logger.getLogger(Throttle.class);
	private static BackEndFacade bef = new BackEndFacade();

	private long RuleId = 0L;
	private String RowId = "";
	private String BgiGroup = null;
	private long StormLevel = 0;
	private long Duration = 0;
	private long Threshold = 0;
	private String Action = null;
	private char MessageFlag = 0;

	public Throttle() {
		super();
	}

	/**
	 * Reset bean properties to their default state, as needed. This method is
	 * called before the properties are repopulated by the controller
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		_log.debug("Throttle::reset()");

		HashMap<String, String> param_map = Toolkit
				.retrieveHttpRequestParameters(request);
		String index = param_map.get("rule_id");
		String rowId = param_map.get("row_id");

		if (null == index) {
			_log.info("reset() - Null index found...");

			return;
		}

//		Throttle object = Throttle.select(Long.parseLong(index));
		Throttle object = Throttle.select(rowId);

		_log.info("Initializing object with index=" + index);

		setRuleId(object.getRuleId());
		setRowId(object.getRowId());
		setBgiGroup(object.getBgiGroup());
		setStormLevel(object.getStormLevel());
		setDuration(object.getDuration());
		setThreshold(object.getThreshold());
		setAction(object.getAction());
		setMessageFlag(object.getMessageFlag());
	}

	/**
	 * Validate the properties that have been set for this HTTP request, and
	 * return an ActionErrors object that encapsulates any validation errors
	 * that have been found.
	 * 
	 * @param mapping
	 *            The mapping used to select this instance
	 * @param request
	 *            The servlet request we are processing
	 * @return null if no errors are found,an ActionErrors object with recorded
	 *         error messages otherwise
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		_log.debug("Throttle::validate()");
		ActionErrors errors = new ActionErrors();

		return errors;
	}

	/**
	 * Setter method for the 'rule_id' column
	 * 
	 * @param rule_id
	 *            The new value for the 'rule_id' column
	 */
	public void setRuleId(long rule_id) {
		this.RuleId = rule_id;
	}

	/**
	 * Getter method for the 'rule_id' column
	 * 
	 * @return The new value for the 'rule_id' column
	 */
	public long getRuleId() {
		return RuleId;
	}

	/**
	 * Setter method for the 'row_id' column
	 * 
	 * @param row_id
	 *            The new value for the 'row_id' column
	 */
	public void setRowId(String row_id) {
		this.RowId = row_id;
	}

	/**
	 * Getter method for the 'row_id' column
	 * 
	 * @return The new value for the 'row_id' column
	 */
	public String getRowId() {
		return RowId;
	}

	/**
	 * Setter method for the 'bgi_group' column
	 * 
	 * @param bgi_group
	 *            The new value for the 'bgi_group' column
	 */
	public void setBgiGroup(String bgi_group) {
		this.BgiGroup = bgi_group;
	}

	/**
	 * Getter method for the 'bgi_group' column
	 * 
	 * @return The new value for the 'bgi_group' column
	 */
	public String getBgiGroup() {
		return BgiGroup;
	}

	/**
	 * Setter method for the 'storm_level' column
	 * 
	 * @param storm_level
	 *            The new value for the 'storm_level' column
	 */
	public void setStormLevel(long storm_level) {
		this.StormLevel = storm_level;
	}

	/**
	 * Getter method for the 'storm_level' column
	 * 
	 * @return The new value for the 'storm_level' column
	 */
	public long getStormLevel() {
		return StormLevel;
	}

	/**
	 * Setter method for the 'duration' column
	 * 
	 * @param duration
	 *            The new value for the 'duration' column
	 */
	public void setDuration(long duration) {
		this.Duration = duration;
	}

	/**
	 * Getter method for the 'duration' column
	 * 
	 * @return The new value for the 'duration' column
	 */
	public long getDuration() {
		return Duration;
	}

	/**
	 * Setter method for the 'threshold' column
	 * 
	 * @param threshold
	 *            The new value for the 'threshold' column
	 */
	public void setThreshold(long threshold) {
		this.Threshold = threshold;
	}

	/**
	 * Getter method for the 'threshold' column
	 * 
	 * @return The new value for the 'threshold' column
	 */
	public long getThreshold() {
		return Threshold;
	}

	/**
	 * Setter method for the 'action' column
	 * 
	 * @param action
	 *            The new value for the 'action' column
	 */
	public void setAction(String action) {
		this.Action = action;
	}

	/**
	 * Getter method for the 'action' column
	 * 
	 * @return The new value for the 'action' column
	 */
	public String getAction() {
		return Action;
	}

	/**
	 * Setter method for the 'message_flag' column
	 * 
	 * @param message_flag
	 *            The new value for the 'message_flag' column
	 */
	public void setMessageFlag(char message_flag) {
		this.MessageFlag = message_flag;
	}

	/**
	 * Getter method for the 'message_flag' column
	 * 
	 * @return The new value for the 'message_flag' column
	 */
	public char getMessageFlag() {
		return MessageFlag;
	}

	/**
	 * Returns the number of existing records
	 * 
	 * @return the number of existing records
	 */
	public static long countRecords() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		long num_records = ((Number) session.createSQLQuery(
				"select count(*) from throttle").uniqueResult()).longValue();
		tx.commit();

		return num_records;
	}

	/**
	 * Returns the number of last RuleId
	 * 
	 * @return the number of last RuleId
	 */
	private static long getLastRuleId() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		long lastRuleId = ((Number) session.createSQLQuery(
				"select max(rule_id) from throttle").uniqueResult())
				.longValue();
		tx.commit();
		_log.info("Last Rule Id: " + lastRuleId);
		return lastRuleId;
	}

	/**
	 * Returns the total number of changes to this record
	 * 
	 * @return the total number of changes to this record
	 */
	public static long countPreviousVersions(long RuleId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = null;
		Transaction tx = session.beginTransaction();
		query = session
				.createQuery("SELECT count(*) FROM com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit c WHERE c.RuleId = :RuleId");
		query.setParameter("RuleId", RuleId);

		long num_records = ((Number) query.uniqueResult()).longValue();
		tx.commit();

		return num_records;
	}

	/**
	 * Attempt to update/insert this object into the database.
	 * 
	 * @return true if updated, false otherwise
	 */
	public boolean insertOrUpdate(String username) {
		boolean was_update = false;
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Throttle object = (Throttle) session.get(Throttle.class, RowId);
		tx.commit();

		if (null != object) {
			// Hibernate 3.2 requires that only one instance of the object be
			// present (does row-level locking for us).
			// Must use this object when updating/saving

			// Update existing record in database
			if (_log.isInfoEnabled()) {
				WeakReference<StringBuilder> message = new WeakReference<StringBuilder>(
						new StringBuilder());
				message.get().append("Updating Throttle ( RuleId ) = ");
				message.get().append(RuleId);
				message.get().append(" for RetrievedObject ( RowID=");
				message.get().append(object.getRowId());
				message.get().append(", RuleID=");
				message.get().append(object.getRuleId());
				message.get().append(" )");

				_log.info(message.get().toString());
			}

			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// Update the current object
			object.setRuleId(getRuleId());
			object.setBgiGroup(getBgiGroup());
			object.setStormLevel(getStormLevel());
			object.setDuration(getDuration());
			object.setThreshold(getThreshold());
			object.setAction(getAction());
			object.setMessageFlag(getMessageFlag());
			session.update(object);
			tx.commit();

			long num_previous_versions = Throttle.countPreviousVersions(RuleId);

			// Create the audit entry
			// ThrottleAudit audit = new ThrottleAudit();
			//
			// session = HibernateUtil.getCurrentSession();
			// tx = session.beginTransaction();
			//            
			// audit.setAuditTimestamp ( Calendar.getInstance() );
			// audit.setAuditVersionNum ( 1 + num_previous_versions );
			// audit.setAuditModifiedBy ( username );
			// audit.setRuleId ( object.getRuleId () );
			// audit.setRowId ( object.getRowId () );
			// audit.setBgiGroup ( object.getBgiGroup () );
			// audit.setStormLevel ( object.getStormLevel () );
			// audit.setDuration ( object.getDuration () );
			// audit.setThreshold ( object.getThreshold () );
			// audit.setAction ( object.getAction () );
			// audit.setMessageFlag ( object.getMessageFlag () );
			// session.save ( audit );
			// tx.commit();

			was_update = true;
		} else {
			_log.info("Inserting new Throttle object into database");

			long ruleId = getLastRuleId() + 1;

			setRuleId(ruleId);

			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			session.save(this);

			tx.commit();

			was_update = false;
		}

		return was_update;
	}

	/**
	 * Select a particular instance of a Throttle by its index
	 * 
	 * @return the instance of the object if available, null otherwise
	 */
	public static Throttle select(long rule_id) {
		Throttle object = null;
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		object = (Throttle) session.get(Throttle.class, rule_id);
		tx.commit();

		return object;
	}
	
	/**
	 * Select a particular instance of a Throttle by its RowId
	 * 
	 * @return the instance of the object if available, null otherwise
	 */
	public static Throttle select(String rowId) {
		Throttle object = null;
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		object = (Throttle) session.get(Throttle.class, rowId);
		tx.commit();

		return object;
	}

	/**
	 * Retrieves a list of objects (in descending order by time of creation) of
	 * all previous versions of this object
	 * 
	 * @return list of objects representing all previous versions of this object
	 *         in descending order by time of creation
	 */
	public static List<ThrottleAudit> retrievePreviousVersions(long rule_id) {
		List<ThrottleAudit> list = null;
		Session session = HibernateUtil.getCurrentSession();
		Query query = null;
		Transaction tx = session.beginTransaction();
		query = session
				.createQuery("FROM com.bgi.esm.monitoring.portlets.Throttle.forms.ThrottleAudit c WHERE c.RuleId = :RuleId ORDER BY AuditVersionNum DESC");
		query = query.setParameter("RuleId", rule_id);

		list = query.list();
		tx.commit();

		return list;
	}

	/**
	 * Retrieves all objects in the database
	 * 
	 * @return list of all objects in the database
	 */
	public static List<Throttle> selectAll() {
		List<Throttle> list = null;
		Session session = HibernateUtil.getCurrentSession();
		Query query = null;
		Transaction tx = session.beginTransaction();
		query = session
				.createQuery("FROM com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle c order by c.BgiGroup");

		list = query.list();
		tx.commit();

		return list;
	}

	/**
	 * Retrieves "pages" of records of sepcified sizes
	 * 
	 * @return list of all objects in the database that belong to a "page"
	 */
	public static List<Throttle> retrieveRecordsPage(int page_num,
			int num_results_per_page) {
		List<Throttle> list = null;
		Session session = HibernateUtil.getCurrentSession();
		Query query = null;
		Transaction tx = session.beginTransaction();
		query = session
				.createQuery("FROM com.bgi.esm.monitoring.portlets.Throttle.forms.Throttle c order by c.BgiGroup");
		query.setFirstResult(page_num * num_results_per_page);
		query.setMaxResults(num_results_per_page);

		list = query.list();
		tx.commit();

		return list;
	}

	/**
	 * Retrieves the audit entries for this object
	 * 
	 * @return a List of audit entries
	 */
	public List<ThrottleAudit> retrievePreviousVersions() {
		return Throttle.retrievePreviousVersions(RuleId);
	}
}
