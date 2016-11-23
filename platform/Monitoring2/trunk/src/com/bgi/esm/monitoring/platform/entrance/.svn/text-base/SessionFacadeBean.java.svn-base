package com.bgi.esm.monitoring.platform.entrance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ejb.SessionBean;
import org.apache.log4j.Logger;
import weblogic.logging.log4j.Log4jLoggingHelper;
import com.bgi.esm.monitoring.platform.orm.OrmFacade;
import com.bgi.esm.monitoring.platform.shared.utility.JmsFacade;
import com.bgi.esm.monitoring.platform.shared.value.AcknowledgeMessage;
import com.bgi.esm.monitoring.platform.shared.value.AnnotationMessage;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;
import com.bgi.esm.monitoring.platform.shared.value.Audit;
import com.bgi.esm.monitoring.platform.shared.value.BussModule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRule;
import com.bgi.esm.monitoring.platform.shared.value.DataMapRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.EebProperty;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.shared.value.DispatcherMessage;
import com.bgi.esm.monitoring.platform.shared.value.RawOvi;
import com.bgi.esm.monitoring.platform.shared.value.Responder;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import com.bgi.esm.monitoring.platform.shared.value.Spool;
import com.bgi.esm.monitoring.platform.shared.value.Storm;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionDrainMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionMessage;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRule;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRule;
import com.bgi.esm.monitoring.platform.shared.value.ThrottleRuleAudit;

/**
 * Dedicated entry point into application server for RMI clients.
 * 
 * @ejb.bean 
 *   name="SessionFacadeEjb" 
 *   type="Stateless"
 *   jndi-name="${jndi.base}/SessionFacadeRemote"
 *   local-jndi-name="${jndi.base}/SessionFacadeLocalHome"
 *   description="rmi gateway"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.transaction-type type="Container"
 * 
 * @author G.S. Cole (guycole at gmail dot com)
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SessionFacadeBean implements SessionBean {
	
	/**
	 * ctor, initialize weblogic logging
	 */
	public SessionFacadeBean() {
		try {
			_log = Log4jLoggingHelper.getLog4jServerLogger();
		} catch(Exception exception) {
			System.err.println("SessionFacadeBean ctor failure");
            _log = Logger.getLogger ( SessionFacadeBean.class );
		}
	}

	/**
	 * Connectivity test method, flip arg and return result.
	 * 
	 * @param arg flag to flip
	 * @return flipped flag
	 * 
	 * @ejb.interface-method
	 */
	public boolean isPingTest(boolean arg) {
		_log.info("isPingTest() active");
		return(!arg);
	}

	/////////////////////////////////////////////////////////////
	/// ServiceCenter ///////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/**
	 * Return all service center datum
	 * 
	 * @return all service center datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllServiceCenterTickets() {
        return ( _orm.getAllServiceCenterTickets() );
    }

	/**
	 * Adds or updates a row in Service Center
	 *
     * @param Service Center datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public ServiceCenter addOrUpdateServiceCenter(ServiceCenter arg) {
		return(_orm.addOrUpdateServiceCenter(arg));
	}

	/**
	 * Return a particular Service Center ticket
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public ServiceCenter selectServiceCenterTicket ( String ticket_number )
    {
        return ( _orm.selectServiceCenterTicket ( ticket_number ) );
    }

	/**
	 * Return a particular Service Center ticket
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public ServiceCenter selectServiceCenterTicketByMessageKey ( String message_key )
    {
        return ( _orm.selectTicketByMessageKey ( message_key ) );
    }

	/////////////////////////////////////////////////////////////
	/// ServiceCenterBuffer /////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/**
	 * Return all service center datum
	 * 
	 * @return all service center datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllServiceCenterTicketsInBuffer() {
        return ( _orm.getAllServiceCenterTicketsInBuffer() );
    }

	/**
	 * Adds or updates a row in Service Center
	 *
     * @param Service Center datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public ServiceCenter addOrUpdateServiceCenterBuffer(ServiceCenter arg) {
		return(_orm.addOrUpdateServiceCenterBuffer(arg));
	}

	/**
	 * Adds or updates a row in Service Center Buffer
	 *
     * @param Service Center datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
    public boolean deleteServiceCenterBuffer(ServiceCenter arg )
    {
        return _orm.deleteServiceCenterBuffer ( arg );
    }

	/**
	 * Finds the oldest Service Center ticket in the buffer
	 * 
	 */
    /*
    public ServiceCenter findOldestServiceCenterTicketInBuffer()
    {
        return _orm.findOldestServiceCenterTicketInBuffer();
    }
    //*/

	/**
	 * Finds the newest Service Center ticket in the buffer
	 * 
	 */
    /*
    public ServiceCenter findNewestServiceCenterTicketInBuffer()
    {
        return _orm.findNewestServiceCenterTicketInBuffer();
    }
    //*/


	/////////////////////////////////////////////////////////////
	/// Responder ///////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/**
	 * Return all service center datum
	 * 
	 * @return all service center datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllResponderIncidents () {
        return ( _orm.getAllResponderIncidents() );
    }

	/**
	 * Delete specified responder datum
	 * 
	 * @param row to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteResponder(Responder arg) {
		return(_orm.deleteResponder(arg));
	}

    
	/**
	 * Adds or updates a row in Service Center
	 *
     * @param Service Center datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public Responder addOrUpdateResponder ( Responder arg) {
		return(_orm.addOrUpdateRow (arg));
	}

    /**
     * @ejb.interface-method
     */
    public Responder findResponderByServiceCenterTicketNumber ( String ticket_number )
    {
        return _orm.findResponderByServiceCenterTicketNumber ( ticket_number );
    }

	/**
	 * Return a particular Service Center ticket
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public Responder selectResponder ( String row_id )
    {
        return ( _orm.selectResponder ( row_id ) );
    }

	/**
	 * Return a particular Responder record by Incident ID
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public Responder selectResponderByMessageId ( String message_id )
    {
        return ( _orm.selectResponderByMessageId ( message_id ) );
    }

	/**
	 * Return a particular Responder record by message key
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public Responder selectResponderByMessageKey ( String message_key, Calendar timestamp )
    {
        return ( _orm.selectResponderByMessageKey ( message_key, timestamp ) );
    }

	/**
	 * Return a particular Responder record by message key
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public Responder selectResponderByMessageKey ( String message_key )
    {
        return ( _orm.selectResponderByMessageKey ( message_key ) );
    }


	/////////////////////////////////////////////////////////////
	/// Alarmpoint //////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/**
	 * Return all service center datum
	 * 
	 * @return all service center datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllAlarmpointEvents() {
        return ( _orm.getAllAlarmpointEvents() );
    }

	/**
	 * Adds or updates a row in Service Center
	 *
     * @param Service Center datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public Alarmpoint addOrUpdateAlarmpoint(Alarmpoint arg) {
		return(_orm.addOrUpdateAlarmpoint(arg));
	}

	/**
	 * Return a particular Service Center ticket
	 * 
	 * @return a particular Service Center ticket information
	 * 
	 * @ejb.interface-method
	 */
    public Alarmpoint selectAlarmpointEventByEventID ( String ticket_number )
    {
        return ( _orm.selectAlarmpointEventByEventID ( ticket_number ) );
    }


	/////////////////////////////////////////////////////////////
	/// Audit ///////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all audit datum
	 * 
	 * @return all audit datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllAudit() {
		return(_orm.getAllAudit());
	}

    /**
	 * 
	 * @ejb.interface-method
	 */
    public List getAllAuditForMessageId ( String message_id )
    {
        return _orm.getAllAuditForMessageId ( message_id );
    }

	/**
	 * Delete specified audit datum
	 * 
	 * @param row to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteAudit(Audit arg) {
		return(_orm.deleteAudit(arg));
	}

	/**
	 * Insert or update audit datum. Returns updated value object. 
	 * Clients need the update because I might change the key, etc.
	 * 
	 * @param arg audit datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public Audit addOrUpdateAudit(Audit arg) {
		return(_orm.addOrUpdateAudit(arg));
	}

	/**
	 * Return selected audit datum
	 * 
	 * @param arg audit key
	 * @return selected row or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public Audit selectAudit(String arg) {
		return(_orm.selectAudit(arg));
	}

	/////////////////////////////////////////////////////////////
	/// DataMap /////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all data map rows
	 * 
	 * @return all data map rows, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllDataMapRules() {
		return(_orm.getAllDataMapRules());
	}

    /**
	 * 
	 * @ejb.interface-method
	 */
    public List getAllDataMapRulesPaginate ( int num_per_page, int page_num )
    {
        return _orm.getAllDataMapRulesPaginate ( num_per_page, page_num );
    }

	/**
	 * Delete specified data map rule
	 * 
	 * @param rule to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteDataMapRule(DataMapRule arg) {
		return(_orm.deleteDataMapRule(arg));
	}

	/**
	 * Insert or update a data map rule. Returns fresh value object.
	 * 
	 * @param arg data map rule
	 * @return updated data map rule or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public DataMapRule addOrUpdateDataMapRule(DataMapRule arg) {
		return(_orm.addOrUpdateDataMapRule(arg));
	}

	/**
	 * Return selected data map rule.
	 * 
	 * @param arg rule key (message group)
	 * @return selected rule or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public DataMapRule selectDataMapRule(String arg) {
		return(_orm.selectDataMapRule(arg));
	}


	/**
	 * Return all audit versions of the data map rule referenceed by Service Center group name
	 * 
	 * @param group the suppression ID
	 * @return a collection of audit objects or null if not found
	 * 
	 * @ejb.interface-method
	 */
    public List<DataMapRuleAudit> getDataMapRuleAuditVersions ( String group ) {
        return ( _orm.getDataMapRuleAuditVersions ( group ) );
    }


	/////////////////////////////////////////////////////////////
	/// Events By Group /////////////////////////////////////////
	/////////////////////////////////////////////////////////////
    /**
     *
     * @ejb.interface-method
     */
    public List getAllEventsByGroup()
    {
        return _orm.getAllEventsByGroup();
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllEventsByGroupBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time )
    {
        return _orm.findAllEventsByGroupBetweenTimesByGroup ( group, start_time, end_time );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllEventsByGroupBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        return _orm.findAllEventsByGroupBetweenTimesByGroupPaginate ( group, start_time, end_time, num_per_page, page_num );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllEventsByGroupsBetweenTimes ( Calendar start_time, Calendar end_time )
    {
        return _orm.findAllEventsByGroupsBetweenTimes ( start_time, end_time );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllEventsByGroupsBetweenTimesPaginate ( Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        return _orm.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );
    }

    /**
     *
     * @ejb.interface-method
     */
    public EventsByGroup selectEventsByGroup ( String row_id )
    {
        return _orm.selectEventsByGroup ( row_id );
    }

    /**
     *
     * @ejb.interface-method
     */
    public EventsByGroup selectEventsByGroupByMessageId ( String message_id )
    {
        return _orm.selectEventsByGroupByMessageId ( message_id );
    }

    /**
     *
     * @ejb.interface-method
     */
    public EventsByGroup addOrUpdateEventsByGroup ( EventsByGroup arg )
    {
        return _orm.addOrUpdateEventsByGroup ( arg );
    }

    /**
     *
     * @ejb.interface-method
     */
    public boolean deleteEventsByGroup ( EventsByGroup arg )
    {
        return _orm.deleteEventsByGroup ( arg );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByGroup ( String group, Calendar start_time, Calendar end_time )
    {
        return _orm.findAllBetweenTimesByGroup ( group, start_time, end_time );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByGroupPaginate ( String group, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        return _orm.findAllBetweenTimesByGroupPaginate ( group, start_time, end_time, num_per_page, page_num );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByApplication ( String application, Calendar start_time, Calendar end_time )
    {
        return _orm.findAllBetweenTimesByApplication ( application, start_time, end_time );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByApplicationPaginate ( String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        return _orm.findAllBetweenTimesByApplicationPaginate ( application, start_time, end_time, num_per_page, page_num );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByGroupApplication ( String group, String application, Calendar start_time, Calendar end_time )
    {
        return _orm.findAllBetweenTimesByGroupApplication ( group, application, start_time, end_time );
    }

    /**
     *
     * @ejb.interface-method
     */
    public List findAllBetweenTimesByGroupApplicationPaginate ( String group, String application, Calendar start_time, Calendar end_time, int num_per_page, int page_num )
    {
        return _orm.findAllBetweenTimesByGroupApplicationPaginate ( group, application, start_time, end_time, num_per_page, page_num );
    }

	/////////////////////////////////////////////////////////////
	/// EEB Property ////////////////////////////////////////////
	/////////////////////////////////////////////////////////////

    /**
     * Retrieves a list of all the EEB properties
     *
     * @return a list of all EEB properties
     *
     * @ejb.interface-method
     */
    public List getAllEebProperties()
    {
        return _orm.getAllEebProperties();
    }

    /**
     * Add or update an EEB property
     *
     * @param arg the property to add or update
	 * @return updated data map rule or null if problem
	 * 
	 * @ejb.interface-method
     */
    public EebProperty addOrUpdateEebProperty ( EebProperty arg )
    {
        return _orm.addOrUpdateEebProperty ( arg );
    }

    /**
     * Retrieve an EEB property
     *
     * @param property_name the name of the EEB property to retrieve
     * @return a value object representing the desired EEB property
	 * 
	 * @ejb.interface-method
     */
    public EebProperty selectEebProperty ( String property_name )
    {
        return _orm.selectEebProperty ( property_name );
    }

    /**
     * Delete an EEB property
     *
     * @param arg the name of the property to delete
     *
     * @ejb.interface-method
     */
    public boolean deleteEebProperty ( String property_name )
    {
        return _orm.deleteEebProperty ( property_name );
    }

    /**
     * @ejb.interface-method
     */
    public void setEebPropertyServiceCenterActive ( boolean is_active )
    {
        _orm.setEebPropertyServiceCenterActive ( is_active );
    }

    /**
     * @ejb.interface-method
     */
    public boolean getEebPropertyServiceCenterActive()
    {
        return _orm.getEebPropertyServiceCenterActive();
    }

    /**
     * @ejb.interface-method
     */
    public void setEebPropertyAlarmpointActive ( boolean is_active )
    {
        _orm.setEebPropertyAlarmpointActive ( is_active );
    }

    /**
     * @ejb.interface-method
     */
    public boolean getEebPropertyAlarmpointActive()
    {
        return _orm.getEebPropertyAlarmpointActive();
    }


	/////////////////////////////////////////////////////////////
	/// Raw OVI /////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all raw OVI datum
	 * 
	 * @return all raw OVI datum, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllRawOvi() {
		return(_orm.getAllRawOvi());
	}

	/**
	 * Delete specified OVI datum
	 * 
	 * @param row to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteRawOvi(RawOvi arg) {
		return(_orm.deleteRawOvi(arg));
	}

	/**
	 * Insert or update raw OVI datum. Returns updated value object. 
	 * Clients need the update because I might change the key, etc.
	 * 
	 * @param arg raw OVI datum
	 * @return updated row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public RawOvi addOrUpdateRawOvi(RawOvi arg) {
		return(_orm.addOrUpdateRawOvi(arg));
	}

	/**
	 * Return selected raw OVI datum
	 * 
	 * @param arg raw OVI key
	 * @return selected row or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public RawOvi selectRawOvi(String arg) {
		return(_orm.selectRawOvi(arg));
	}

	/////////////////////////////////////////////////////////////
	/// Suppression /////////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all suppression rows
	 * 
	 * @return all suppression rows, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllSuppressionRules() {
		return(_orm.getAllSuppressionRules());
	}

	/**
	 * Return all suppression notifications
	 * 
	 * @return all suppression notifications, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllSuppressionNotifications() {
		return(_orm.getAllSuppressionNotifications());
	}

	/**
	 * Return all suppression rows
	 * 
	 * @return all suppression rows, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllSuppressionRulesPaginate( int num_per_page, int page_num ) 
    {
		return(_orm.getAllSuppressionRulesPaginate ( num_per_page, page_num ));
	}

	/**
	 * Return all active suppressions
	 * 
	 * @return all active suppressions, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllActiveSuppressionRules()
    {
        return _orm.getAllActiveSuppressionRules();
    }

	/**
	 * Return all active suppressions paginated
	 * 
	 * @return all active suppressions, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllActiveSuppressionRulesPaginate ( int num_per_page, int page_num )
    {
        return _orm.getAllActiveSuppressionRulesPaginate ( num_per_page, page_num );
    }

	/**
	 * Return all active suppressions for a user
	 * 
	 * @return all active suppressions, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllActiveSuppressionRulesForUser ( String user )
    {
        return _orm.getAllActiveSuppressionRulesForUser ( user );
    }

	/**
	 * Return all active suppressions for a user (paginated)
	 * 
	 * @return all active suppressions, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
    public List getAllActiveSuppressionRulesForUserPaginate ( String user, int num_per_page, int page_num )
    {
        return _orm.getAllActiveSuppressionRulesForUserPaginate ( user, num_per_page, page_num );
    }


	/**
	 * Returns the search results for a list of all active suppressions
	 * 
	 * @return all active suppressions that fit the search criteria
	 * 
	 * @ejb.interface-method
	 */
    public List searchActiveSuppressionRules 
            ( String user, String description, String app_name, String node_name, String db_server, 
              String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  )
    {
        return _orm.searchActiveSuppressionRules ( user, description, app_name, node_name, db_server, message_text, start_time, end_time, remove_on_reboot, was_deleted );
    }

	/**
	 * Returns the search results for a list of all historiacl suppressions
	 * 
	 * @return all historical suppressions that fit the search criteria
	 * 
	 * @ejb.interface-method
	 */
    public List searchHistoricalSuppressionRules ( String user, String description, String app_name, String node_name, String db_server, 
            String message_text, Calendar start_time, Calendar end_time, int remove_on_reboot, int was_deleted  )
    {
        return _orm.searchHistoricalSuppressionRules ( user, description, app_name, node_name, db_server, message_text, start_time, end_time, remove_on_reboot, was_deleted );
    }


	/**
	 * Delete specified suppression rule
	 * 
	 * @param rule to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteSuppressionRule(SuppressionRule arg) {
		return(_orm.deleteSuppressionRule(arg));
	}

	/**
	 * Insert or update a suppression rule. Returns updated value object.
	 * Clients need the update because I might change the key, etc.
	 * 
	 * @param arg suppression rule
	 * @return updated suppression rule or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public SuppressionRule addOrUpdateSuppressionRule(SuppressionRule arg) {
		return(_orm.addOrUpdateSuppressionRule(arg));
	}

	/**
	 * Return selected suppression rule.
	 * 
	 * @param arg rule key
	 * @return selected rule or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public SuppressionRule selectSuppressionRule(String arg) {
		return(_orm.selectSuppressionRule(arg));
	}

	/**
	 * Return selected suppression rule referenced by Suppression ID
	 * 
	 * @param suppress_id the suppression ID
	 * @return selected rule or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public SuppressionRule selectSuppressionRuleBySuppressId ( long suppress_id ) {
		return(_orm.selectSuppressionRuleBySuppressId ( suppress_id ));
	}

	/**
	 * Return all the audit versions of a suppression rule
	 * 
	 * @param suppress_id the suppression ID
	 * @return a collection of audit objects or null if not found
	 * 
	 * @ejb.interface-method
	 */
    public List<SuppressionRuleAudit> getSuppressionRuleAuditVersions ( long suppress_id ) {
        return ( _orm.getSuppressionRuleAuditVersions ( suppress_id ) );
    }

	/////////////////////////////////////////////////////////////
	/// Throttle (Rules) ////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all throttle rows
	 * 
	 * @return all throttle rows, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllThrottleRules() {
		return(_orm.getAllThrottleRules());
	}

	/**
	 * Delete specified throttle rule
	 * 
	 * @param rule to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteThrottleRule(ThrottleRule arg) {
		return(_orm.deleteThrottleRule(arg));
	}

	/**
	 * Insert or update a throttle rule. Returns updated value object. 
	 * Clients need the update because I might change the key, etc.
	 * 
	 * @param arg throttle rule
	 * @return updated throttle rule or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public ThrottleRule addOrUpdateThrottleRule(ThrottleRule arg) {
		return(_orm.addOrUpdateThrottleRule(arg));
	}

	/**
	 * Return selected throttle rule.
	 * 
	 * @param arg rule key
	 * @return selected rule or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public ThrottleRule selectThrottleRule(String arg) {
		return(_orm.selectThrottleRule(arg));
	}
	
	/////////////////////////////////////////////////////////////
	/// Throttle (Spool) ////////////////////////////////////////
	/////////////////////////////////////////////////////////////

	/**
	 * Return all spool rows
	 * 
	 * @return all spool rows, might be empty list (never null).
	 * 
	 * @ejb.interface-method
	 */
	public List getAllSpoolRows() {
		return(_orm.getAllSpoolRows());
	}

	/**
	 * Delete specified spool row
	 * 
	 * @param spool to delete
	 * @return true success
	 * 
	 * @ejb.interface-method
	 */
	public boolean deleteSpoolRow(Spool arg) {
		return(_orm.deleteSpoolRow(arg));
	}

	/**
	 * Insert or update a spool row. Returns fresh value object. 
	 * Clients need the update because I might change the key, etc.
	 * 
	 * @param arg spool row
	 * @return updated spool row or null if problem
	 * 
	 * @ejb.interface-method
	 */
	public Spool addOrUpdateSpoolRow(Spool arg) {
		return(_orm.addOrUpdateSpoolRow(arg));
	}

	/**
	 * Return selected spool row.
	 * 
	 * @param arg key (message group)
	 * @return selected spool row or null if not found
	 * 
	 * @ejb.interface-method
	 */
	public Spool selectSpoolRow(String arg) {
		return(_orm.selectSpoolRow(arg));
	}
	
	/////////////////////////////////////////////////////////////
	/// Storm ///////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
    
    /**
     * Select all active storm rows
     *
     * @ejb.interface-method
     */
    public List getAllActiveStorms()
    {
        return _orm.getAllActiveStorms();
    }


	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/**
	 * Return the next sequence value in the monotonic sequence
     * generator.
	 * 
	 * @return the next sequence value in the monotonic sequence generator.
	 * 
	 * @ejb.interface-method
	 */
    public int nextMonotonicSequenceValue() 
    {
        return _orm.nextMonotonicSequence();
    }

	/**
	 * Return the next sequence value in the monotonic sequence
     * generator.
	 * 
	 * @return the next sequence value in the monotonic sequence generator data map rules.
	 * 
	 * @ejb.interface-method
	 */
    public int nextMonotonicSequenceForDataMap() 
    {
        return _orm.nextMonotonicSequenceForDataMap();
    }

	/**
	 * Return the next sequence value in the monotonic sequence
     * generator.
	 * 
	 * @return the next sequence value in the monotonic sequence generator suppression rules.
	 * 
	 * @ejb.interface-method
	 */
    public int nextMonotonicSequenceForSuppression() 
    {
        return _orm.nextMonotonicSequenceForSuppression();
    }

	/**
	 * Return the next sequence value in the monotonic sequence
     * generator.
	 * 
	 * @return the next sequence value in the monotonic sequence generator for throttle rules.
	 * 
	 * @ejb.interface-method
	 */
    public int nextMonotonicSequenceForThrottle() 
    {
        return _orm.nextMonotonicSequenceForThrottle();
    }

	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
    /**
     *  Injecting a suppression message into the bus
     *
     *  @return whether or a message was successfully injected into the system
	 * 
	 *  @ejb.interface-method
     */
    public boolean sendSuppressionDrainMessage()
    {
    	_log.debug("Entering/exiting sendSuppressionDrainMessage");
    	// return true;  // 2007-04-19 RH
        return sendSuppressionDrainMessage ( new SuppressionDrainMessage() );
    }

    /**
     *  Injecting a suppression message into the bus
     *
     *  @return whether or a message was successfully injected into the system
	 * 
	 *  @ejb.interface-method
     */
    public boolean sendSuppressionDrainMessage ( SuppressionDrainMessage message )
    {
        try 
        {
            JmsFacade jf = new JmsFacade(BussModule.SUPPRESSION_DRAIN);
            jf.queueWriter( message );

            return true;
        } 
        catch(Exception exception) 
        {
            _log.error ( "Could not send suppression drain message to JMS bus", exception );

            return false;
        }
    }

    /**
     *  Injecting a JMS dispatcher message
     *
     *  @return whether or a message was successfully injected into the system
	 * 
	 *  @ejb.interface-method
     */
    public boolean sendDispacherMessage( DispatcherMessage message )
    {
        try 
        {
            JmsFacade jf = new JmsFacade(BussModule.DISPATCHER);
            jf.queueWriter( message );

            return true;
        } 
        catch(Exception exception) 
        {
            _log.error ( "Could not send suppression drain message to JMS bus", exception );

            return false;
        }
    }

    /**
     *  Injecting a JMS message into the suppression module
     *
     *  @return whether or a message was successfully injected into the system
	 * 
	 *  @ejb.interface-method
     */
    public boolean sendSuppressionMessage ( SuppressionMessage message )
    {
        try 
        {
            JmsFacade jf = new JmsFacade(BussModule.SUPPRESSION);
            jf.queueWriter( message );

            return true;
        } 
        catch(Exception exception) 
        {
            _log.error ( "Could not send suppression message to JMS bus", exception );

            return false;
        }
    }

	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
    /**
     *  Asking the EEB to acknowledge an Openview event
     *
     *  @return whether or a message was successfully injected into the system
	 * 
	 *  @ejb.interface-method
     */
    public boolean acknowledgeOpenviewEvent ( String message_id, String annotation_text )
    {
		AnnotationMessage annotation = new AnnotationMessage();
		annotation.setMessageId ( message_id      );
		annotation.setText      ( annotation_text );

		AcknowledgeMessage acknowledge = new AcknowledgeMessage();
		acknowledge.setMessageId ( message_id );

		JmsFacade jms = null;
        StringBuilder message = null;

        try 
        {
            _log.info ( "Connecting to JmsFacade: " + BussModule.MODIFIER );
            LogToFile ( "c:\\test\\jms-openview.out", "Connecting to JmsFacade: " + BussModule.MODIFIER );
			jms = new JmsFacade(BussModule.MODIFIER);

            if ( _log.isInfoEnabled() )
            {
                message = new StringBuilder();
                message.append ( "Attempting to send AnnotationMessage for messageId=" );
                message.append ( message_id );
                message.append ( " - " );
                message.append ( annotation.toString() );

                _log.info ( message.toString() );
                LogToFile ( "c:\\test\\jms-openview.out", message.toString() );
            }
			jms.topicWriter(annotation.toXml());

            if ( _log.isInfoEnabled() )
            {
                message = new StringBuilder();
                message.append ( "Attempting to send AcknowledgeMessage for messageId=" );
                message.append ( message_id );
                message.append ( " - " );
                message.append ( acknowledge.toString() );

                _log.info ( message.toString() );
                LogToFile ( "c:\\test\\jms-openview.out", message.toString() );
            }
			jms.topicWriter(acknowledge.toXml());

            _log.info ( "Annotate/Acknowledge successful for messageId=" + message_id );
            LogToFile ( "c:\\test\\jms-openview.out", "Annotate/Acknowledge successful for messageId=" + message_id );
        } 
        catch ( Exception exception )
        {
            _log.error ( "Could not acknowledge Openview EventID=" + message_id, exception );

            return false;
        }
        finally
        {
            if ( null != jms )
            {
                jms.shutDown();
            }
        }

        return true;
    }

	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
    
    public final void LogToFile ( String filename, String message )
    {
        if ( _log.isDebugEnabled() )
        {
            try
            {
                synchronized ( this )
                {
                FileOutputStream outfile = new FileOutputStream ( filename, true );
                    outfile.write ( (new java.util.Date()).toString().getBytes() );
                    outfile.write ( " - ".getBytes() );
                    outfile.write ( message.getBytes() );
                    outfile.write ( "\n".getBytes() );
                outfile.close();
                }
            }
            catch ( IOException exception )
            {
                _log.fatal ( "Could not log message to filename: " + filename );
            }
        }
    }


	/**
	 * Handle to ORM dispatcher
	 */
	private final OrmFacade _orm = new OrmFacade();
	
	/**
	 * Define logger
	 */
	private Logger _log;
}
