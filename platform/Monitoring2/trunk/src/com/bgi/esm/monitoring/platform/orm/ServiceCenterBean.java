package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;


/**
 * Entity bean supporting ServiceCenter  
 * 
 * @ejb.bean 
 *   name="ServiceCenterEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/ServiceCenterEjbLocalHome"
 *   description="servicecenter bean"
 * 
 * @ejb.persistence table-name="service_center"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM ServiceCenterEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="ServiceCenterLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM ServiceCenterEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 *
 * @ejb.finder 
 *   signature="ServiceCenter findByTicketNumber(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM ServiceCenterEjb AS x WHERE x.ticketNum = ?1"
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findByMessageKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM ServiceCenterEjb AS x WHERE x.messageKey LIKE ?1 ORDER BY x.timestamp DESC"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ServiceCenterBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException is create problem
	 * 
	 * @ejb.create-method
	 */
	public ServiceCenterEjbPK ejbCreate(ServiceCenterEjbPK key, ServiceCenter arg) throws CreateException {
		setRowKey(key.getRowKey());
		setValue(arg);
		
		return(null);
	}

	/**
	 * Update a database row.  Key will not be altered.
	 * 
	 * @param arg fresh row datum
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(ServiceCenter arg) {
		long temp_time = arg.getTimestamp().getTimeInMillis();
		setTimestamp           ( new Timestamp(temp_time)     );
        setMessageId           ( arg.getMessageId()           );
        setTicketNum           ( arg.getTicketNum()           );
        setTicketCategory      ( arg.getTicketCategory()      );
        setTicketSubCategory   ( arg.getTicketSubCategory()   );
        setTicketOpenTime      ( arg.getTicketOpenTime()      );
        setTicketOpenedBy      ( arg.getTicketOpenedBy()      );
        setTicketPriorityCode  ( arg.getTicketPriorityCode()  );
        setTicketSeverityCode  ( arg.getTicketSeverityCode()  );
        setTicketUpdateTime    ( arg.getTicketUpdateTime()    );
        setTicketAssignment    ( arg.getTicketAssignment()    );
        setTicketAlertTime     ( arg.getTicketAlertTime()     );
        setTicketStatus        ( arg.getTicketStatus()        );
        setTicketCloseTime     ( arg.getTicketCloseTime()     );
        setTicketClosedBy      ( arg.getTicketClosedBy()      );
        setTicketFlag          ( arg.getTicketFlag()          );
        setTicketAssigneeName  ( arg.getTicketAssigneeName()  );
        setTicketRespondTime   ( arg.getTicketRespondTime()   );
        setTicketContactName   ( arg.getTicketContactName()   );
        setTicketActor         ( arg.getTicketActor()         );
        setTicketFormat        ( arg.getTicketFormat()        );
        setTicketDeadlineGroup ( arg.getTicketDeadlineGroup() );
        setTicketDeadlineAlert ( arg.getTicketDeadlineAlert() );
        setTicketProblem       ( arg.getTicketProblem()       );
        setTicketProduct       ( arg.getTicketProduct()       );
        setTicketLocation      ( arg.getTicketLocation()      );
        setTicketMessage       ( arg.getTicketMessage()       );
        setNumAttempts         ( arg.getNumAttempts()         );
        setMessageKey          ( arg.getMessageKey()          );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public ServiceCenter getValue() {
		ServiceCenter result = new ServiceCenter(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimestamp().getTime());

		result.setTimestamp           ( temp_calendar            );
        result.setMessageId           ( getMessageId()           );
        result.setTicketNum           ( getTicketNum()           );
        result.setTicketCategory      ( getTicketCategory()      );
        result.setTicketSubCategory   ( getTicketSubCategory()   );
        result.setTicketPriorityCode  ( getTicketPriorityCode()  );
        result.setTicketSeverityCode  ( getTicketSeverityCode()  );
        result.setTicketUpdateTime    ( getTicketUpdateTime()    );
        result.setTicketAssignment    ( getTicketAssignment()    );
        result.setTicketAlertTime     ( getTicketAlertTime()     );
        result.setTicketStatus        ( getTicketStatus()        );
        result.setTicketOpenTime      ( getTicketOpenTime()      );
        result.setTicketCloseTime     ( getTicketCloseTime()     );
        result.setTicketOpenedBy      ( getTicketOpenedBy()      );
        result.setTicketClosedBy      ( getTicketClosedBy()      );
        result.setTicketFlag          ( getTicketFlag()          );
        result.setTicketAssigneeName  ( getTicketAssigneeName()  );
        result.setTicketContactName   ( getTicketContactName()   );
        result.setTicketActor         ( getTicketActor()         );
        result.setTicketFormat        ( getTicketFormat()        );
        result.setTicketRespondTime   ( getTicketRespondTime()   );
        result.setTicketDeadlineGroup ( getTicketDeadlineGroup() );
        result.setTicketDeadlineAlert ( getTicketDeadlineAlert() );
        result.setTicketProblem       ( getTicketProblem()       );
        result.setTicketProduct       ( getTicketProduct()       );
        result.setTicketAssignment    ( getTicketAssignment()    );
        result.setTicketLocation      ( getTicketLocation()      );
        result.setTicketMessage       ( getTicketMessage()       );
        result.setNumAttempts         ( getNumAttempts()         );
        result.setMessageKey          ( getMessageKey()          );

		return(result);
	}

	/**
	 * Define row key.
	 *
	 * @param arg row key, cannot be null
	 *
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRowKey(String arg);

	/**
	 * Return row key.
	 * 
	 * @return row key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getRowKey();

	/**
	 * Define time stamp
	 * 
	 * @param arg time stamp, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setTimestamp(Timestamp arg);

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="time_stamp"
	 */
	public abstract Timestamp getTimestamp();

	/**
	 * Define Service Center ticket number
	 * 
	 * @param arg buss module name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setTicketNum(String arg);

	/**
	 * Return buss module name
	 * 
	 * @return buss module name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_num"
	 */
	public abstract String getTicketNum();

	/**
	 * Define OVI message ID
	 * 
	 * @param arg OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessageId(String arg);

	/**
	 * Return OVI message ID
	 * 
	 * @return OVI message ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_id"
	 */
	public abstract String getMessageId();

    /**
     * Define the ticket category
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_category"
     */
    public abstract String getTicketCategory();

    /**
     * Return the ticket category
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_category"
     */
    public abstract void setTicketCategory ( String ticket_category );

    /**
     * Define the time ticket was created/opened
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_open_time"
     */
    public abstract String getTicketOpenTime();

    /**
     * Return the time the ticket was created/opened
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_open_time"
     */
    public abstract void setTicketOpenTime ( String ticket_open_time );

    /**
     * Define the priority code of the ticket
     *
     * @return ticket priority code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_priority_code"
     */
    public abstract String getTicketPriorityCode();

    /**
     * Return the priority code of the ticket
     *
     * @return ticket priority code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_priority_code"
     */
    public abstract void setTicketPriorityCode ( String ticket_priority_code );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_severity_code"
     */
    public abstract String getTicketSeverityCode();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_severity_code"
     */
    public abstract void setTicketSeverityCode ( String ticket_severity_code );

    /**
     * Define the time ticket was created/updateed
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_update_time"
     */
    public abstract String getTicketUpdateTime();

    /**
     * Return the time the ticket was created/updateed
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_update_time"
     */
    public abstract void setTicketUpdateTime ( String ticket_update_time );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_assignment"
     */
    public abstract String getTicketAssignment();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_assignment"
     */
    public abstract void setTicketAssignment ( String ticket_assignment );

    /**
     * Define the time ticket was created/alerted
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_alert_time"
     */
    public abstract String getTicketAlertTime();

    /**
     * Return the time the ticket was created/alerted
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_alert_time"
     */
    public abstract void setTicketAlertTime ( String ticket_alert_time );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_status"
     */
    public abstract String getTicketStatus();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_status"
     */
    public abstract void setTicketStatus ( String ticket_status );

    /**
     * Define the time ticket was created/closeed
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_close_time"
     */
    public abstract String getTicketCloseTime();

    /**
     * Return the time the ticket was created/closeed
     *
     * @return ticket category, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_close_time"
     */
    public abstract void setTicketCloseTime ( String ticket_close_time );

    /**
     * Return the Windows username of the person who opened the ticket
     *
     * @return the Windows username of the person who opened the ticket
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_opened_by"
     */
    public abstract String getTicketOpenedBy();

    /**
     * Define the Windows username of the persion who opened the ticket
     *
     * @param ticket_opened_by the Windows username of the persion who opened the ticket, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_opened_by"
     */
    public abstract void setTicketOpenedBy ( String ticket_opened_by );

    /**
     * Returns the Windows username of the person who closed the ticket
     *
     * @return the Windows username of the person who closed the ticket
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_closed_by"
     */
    public abstract String getTicketClosedBy();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_closed_by"
     */
    public abstract void setTicketClosedBy ( String ticket_closed_by );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_flag"
     */
    public abstract String getTicketFlag();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_flag"
     */
    public abstract void setTicketFlag ( String ticket_flag );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_assignee_name"
     */
    public abstract String getTicketAssigneeName();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_assignee_name"
     */
    public abstract void setTicketAssigneeName ( String ticket_assignee_name );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_contact_name"
     */
    public abstract String getTicketContactName();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_contact_name"
     */
    public abstract void setTicketContactName ( String ticket_contact_name );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_actor"
     */
    public abstract String getTicketActor();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_actor"
     */
    public abstract void setTicketActor ( String ticket_actor );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_format"
     */
    public abstract String getTicketFormat();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_format"
     */
    public abstract void setTicketFormat ( String ticket_format );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_respond_time"
     */
    public abstract String getTicketRespondTime();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_respond_time"
     */
    public abstract void setTicketRespondTime ( String ticket_respond_time );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_deadline_group"
     */
    public abstract String getTicketDeadlineGroup();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_deadline_group"
     */
    public abstract void setTicketDeadlineGroup ( String ticket_deadline_group );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_deadline_alert"
     */
    public abstract String getTicketDeadlineAlert();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_deadline_alert"
     */
    public abstract void setTicketDeadlineAlert ( String ticket_deadline_alert );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_product"
     */
    public abstract String getTicketProduct();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_product"
     */
    public abstract void setTicketProduct ( String ticket_deadline_alert );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_problem"
     */
    public abstract String getTicketProblem();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_problem"
     */
    public abstract void setTicketProblem ( String ticket_problem );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_sub_category"
     */
    public abstract String getTicketSubCategory();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_sub_category"
     */
    public abstract void setTicketSubCategory ( String ticket_sub_category );

    /**
     * Define the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_location"
     */
    public abstract String getTicketLocation();

    /**
     * Return the severity code of the ticket
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_location"
     */
    public abstract void setTicketLocation ( String ticket_location );

    /**
     * Define the incident ID (used to correlate with Alarmpoint Events)
     *
     * @return ticket incident id, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="incident_id"
     */
    public abstract long getTicketIncidentID ();

    /**
     * Return the incident ID (used to correlate with Alarmpoint Events)
     *
     * @return ticket severity code, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="incident_id"
     */
    public abstract void setTicketIncidentID ( long incident_id );

    /**
     * Define the ticket message
     *
     * @return the ticket message, can not be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_message"
     */
    public abstract String getTicketMessage();

    /**
     * Return the ticket message
     *
     * @param message the ticket message, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ticket_message"
     */
    public abstract void setTicketMessage ( String message );

    /**
     * Define the number of ticket creation attempts
     *
     * @return the ticket message, can not be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="num_attempts"
     */
    public abstract long getNumAttempts();

    /**
     * Return the number of ticket creation attempts
     *
     * @param message the ticket message, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="num_attempts"
     */
    public abstract void setNumAttempts ( long number_of_attempts );

    /**
     * Define the message key
     *
     * @return the message key, can not be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_key"
     */
    public abstract String getMessageKey();

    /**
     * Return the message key
     *
     * @param message the message key, cannot be null
     *
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_key"
     */
    public abstract void setMessageKey ( String message_key );

}
