package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.Responder;

/**
 * Entity bean supporting Responder entries
 * 
 * @ejb.bean 
 *   name="ResponderEjb" 
 *   type="CMP" 
 *   cmp-version="2.x" 
 *   reentrant="false"
 *   view-type="local" 
 *   local-jndi-name="${jndi.base}/ResponderEjbLocalHome"
 *   description="alarmpoint bean"
 * 
 * @ejb.persistence table-name="responder"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="ResponderLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x WHERE x.rowKey = ?1"
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllByServiceCenterTicketNumber(java.lang.String ticket_number)"
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x WHERE x.serviceCenterTicketNumber = ?1"
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="ResponderLocal findByServiceCenterTicketNumber(java.lang.String ticket_number)"
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x WHERE x.serviceCenterTicketNumber = ?1"
 *   unchecked="true"
 *
 * @ejb.finder 
 *   signature="ResponderLocal findByMessageIdLocal (java.lang.String messageId)"
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x WHERE x.messageId= ?1"
 *   unchecked="true"
 *
 * @ejb.finder 
 *   signature="java.util.Collection findByMessageKeyLocal (java.lang.String message_key, java.sql.Timestamp start)"
 *   query="SELECT OBJECT(x) FROM ResponderEjb AS x WHERE x.messageKey LIKE ?1 AND x.timestamp > ?2 ORDER BY x.timestamp DESC"
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class ResponderBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated row key
	 * @param arg fresh row datum
	 * @throws CreateException is create problem
	 * 
	 * @ejb.create-method
	 */
	public ResponderEjbPK ejbCreate(ResponderEjbPK key, Responder arg) throws CreateException {
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
	public void setValue(Responder arg) 
    {
        setMessageId                    ( arg.getMessageId()                    );
		setServiceCenterTicketNumber    ( arg.getServiceCenterTicketNumber()    );
		setServiceCenterTicketOwner     ( arg.getServiceCenterTicketOwner()     );
        setServiceCenterTicketMessage   ( arg.getServiceCenterTicketMessage()   );
        setSCAsyncRequestReceipt        ( arg.getSCAsyncRequestReceipt()        );
        setAlarmpointEventID            ( arg.getAlarmpointEventID()            );
        setAlarmpointNotificationTarget ( arg.getAlarmpointNotificationTarget() );
        setAlarmpointMessage            ( arg.getAlarmpointMessage()            );
        setDuplicateCount               ( arg.getDuplicateCount()               );
        setMessageKey                   ( arg.getMessageKey()                   );
        setLockedTimestamp              ( arg.getLockedTimestamp()              );
		long temp_time = arg.getTimestamp().getTimeInMillis();
		setTimestamp                    ( new Timestamp(temp_time)              );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public Responder getValue() 
    {
		Responder result = new Responder(getRowKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getTimestamp().getTime());
        result.setTimestamp                    ( temp_calendar                     );
        result.setMessageId                    ( getMessageId()                    );
		result.setServiceCenterTicketNumber    ( getServiceCenterTicketNumber()    );
        result.setServiceCenterTicketOwner     ( getServiceCenterTicketOwner()     );
        result.setServiceCenterTicketMessage   ( getServiceCenterTicketMessage()   );
        result.setSCAsyncRequestReceipt        ( getSCAsyncRequestReceipt()        );
        result.setAlarmpointEventID            ( getAlarmpointEventID()            );
        result.setAlarmpointNotificationTarget ( getAlarmpointNotificationTarget() );
        result.setAlarmpointMessage            ( getAlarmpointMessage()            );
        result.setDuplicateCount               ( getDuplicateCount()               );
        result.setMessageKey                   ( getMessageKey()                   );
        result.setLockedTimestamp              ( getLockedTimestamp()              );

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
	 * Define Alarmpoint Event ID
	 * 
	 * @param arg Alarmpoint event ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAlarmpointEventID (String ap_event_id );

	/**
	 * Return Alarmpoint Event ID
	 * 
	 * @return the Alarmpoint event ID, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="ap_event_id"
	 */
	public abstract String getAlarmpointEventID();

    /**
     * Define the Alarmpoint Notification Target
     *
     * @param notification_target the group or person in Alarmpoint to notify
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setAlarmpointNotificationTarget ( String notification_target );

    /**
     * Return the Alarmpoint Notification Target
     *
     * @return the group or person in Alarmpoint to notify
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="ap_notif_target"
     */
    public abstract String getAlarmpointNotificationTarget();

    /**
     *  Define the message for the Alarmpoint notification
     *
     *  @param message the message to send to the notification receiver
     *
     *  @ejb.interface-method view-type="local"
     */
    public abstract void setAlarmpointMessage( String message );

    /**
     *  Return the message for the Alarmpoint notification
     *
     *  @return the emssage to send to the notification receiver
     *
     *  @ejb.interface-method view-type="local"
     *
     *  @ejb.persistence column-name="ap_message"
     */
    public abstract String getAlarmpointMessage();

	/**
	 * Define the Service Center ticket number
	 * 
	 * @param arg the Service Center ticket number, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setServiceCenterTicketNumber(String sc_ticket_num);

	/**
	 * Return the Service Center ticket number
	 * 
	 * @return the Service Center ticket number, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="sc_ticket_num"
	 */
	public abstract String getServiceCenterTicketNumber();

    /**
     * Define the Service Center message to be logged
     *
     * @param message the Service Center message to be logged
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setServiceCenterTicketMessage ( String sc_ticket_message );

    /**
	 * Return the Service Center message
	 * 
	 * @return the Service Center message, can not be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="sc_ticket_message"
     */
    public abstract String getServiceCenterTicketMessage();

    /**
     * Define the Service Center owner
     *
     * @param message the Service Center tocket owner to be logged
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setServiceCenterTicketOwner ( String sc_ticket_owner );

    /**
	 * Return the Service Center owner 
	 * 
	 * @return the Service Center ticket owner, can not be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="sc_ticket_owner"
     */
    public abstract String getServiceCenterTicketOwner();

    /**
     * Define the Service Center owner
     *
     * @param message the Service Center tocket owner to be logged
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setSCAsyncRequestReceipt ( String request_receipt );

    /**
	 * Return the Service Center owner 
	 * 
	 * @return the Service Center ticket owner, can not be null
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="sc_request_receipt"
     */
    public abstract String getSCAsyncRequestReceipt();

	/**
	 * Define the Openview message ID
	 * 
	 * @param arg the message ID
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessageId(String message_id);

	/**
	 * Return the Openview message ID 
	 * 
	 * @return the Openview message ID ( cannot be null )
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_id"
	 */
	public abstract String getMessageId();

	/**
	 * Define the duplicate count
	 * 
	 * @param duplicate_count the duplicate count
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setDuplicateCount ( long duplicate_count );

	/**
	 * Return the duplicate count
	 * 
	 * @return the duplicate count 
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="duplicate_count"
	 */
	public abstract long getDuplicateCount();

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
	 * Define the Openview message key
	 * 
	 * @param arg the message key
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessageKey(String message_id);

	/**
	 * Return the Openview message key 
	 * 
	 * @return the Openview message key ( cannot be null )
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message_key"
	 */
	public abstract String getMessageKey();

	/**
	 * Define the timestamp for when this record was locked
	 * 
	 * @param locked_timestamp the timestamp for when this record was locked
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setLockedTimestamp ( long locked_timestamp );

	/**
	 * Return the timestamp for which this record was locked
	 * 
	 * @return the timestmap for when this record was locked
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="locked_timestamp"
	 */
	public abstract long getLockedTimestamp();
}
