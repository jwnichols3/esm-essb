package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import com.bgi.esm.monitoring.platform.shared.value.SuppressionRuleAudit;

/**
 * Entity bean supporting suppression rules
 * 
 * @ejb.bean 
 *   name="SuppressionRuleAuditEjb" 
 *   type="CMP" 
 *   cmp-version="2.x"
 *   reentrant="false" 
 *   view-type="local"
 *   local-jndi-name="${jndi.base}/SuppressionRuleAuditEjbLocalHome"
 *   description="suppression rule audit bean"
 * 
 * @ejb.persistence table-name="suppression_audit"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM SuppressionRuleAuditEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAuditVersions ( long suppress_id )" 
 *   query="SELECT OBJECT(x) FROM SuppressionRuleAuditEjb AS x WHERE x.suppressId = ?1" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="SuppressionRuleAuditLocal findByKey(java.lang.String key, long audit_version_num )"
 *   query="SELECT OBJECT(x) FROM SuppressionRuleAuditEjb AS x WHERE x.ruleKey = ?1 AND x.auditVersionNum=?2" 
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class SuppressionRuleAuditBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated rule key
	 * @param arg fresh suppression rule
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public SuppressionRuleAuditEjbPK ejbCreate(SuppressionRuleAudit arg) throws CreateException {
		setRuleKey(arg.getRuleKey());
		setValue(arg);

		return(null);
	}

	/**
	 * Update a database row.  Key will not be altered.
	 * 
	 * @param arg fresh row datum
	 * @throws IllegalArgumentException if invalid times
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public void setValue(SuppressionRuleAudit arg) {
		if (arg.isValidTime() == false) {
			throw new IllegalArgumentException("bad times");
		}
		
		long temp_time = arg.getStartTime().getTimeInMillis();
		setStartTime(new Timestamp(temp_time));

		temp_time = arg.getEndTime().getTimeInMillis();
		setEndTime(new Timestamp(temp_time));

        if ( arg.getNotifyMinutes() > 0 )
        {
		    temp_time = arg.getEndTime().getTimeInMillis();

            Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            timestamp.setTimeInMillis ( temp_time );

            int num_notify_minutes = (int) arg.getNotifyMinutes();

            timestamp.add ( Calendar.MINUTE, -num_notify_minutes );
            setNotificationTime ( new Timestamp ( timestamp.getTimeInMillis() ) );
        }

        setRuleKey          ( arg.getRuleKey()            );
        setAppName          ( arg.getApplicationName()    );
        setAuditVersionNum  ( arg.getAuditVersionNum()    );
        setAuditTimestamp   ( arg.getAuditTimestamp()     );
        setAuditModifiedBy  ( arg.getAuditModifiedBy()    );
        setNodeName         ( arg.getNodeName()           );
        setDbServer         ( arg.getDatabaseServerName() );
        setMessage          ( arg.getMessage()            );
        setDescription      ( arg.getDescription()        );
        setDeletedFlag      ( arg.getDeletedFlag()        );
        setNotificationFlag ( arg.getNotificationFlag()   );
        setNotifyMinutes    ( arg.getNotifyMinutes()      );
        setRemoveOnReboot   ( arg.getRemoveOnReboot()     );
        setSuppressId       ( arg.getSuppressId()         );
        setIsNotified       ( arg.getIsNotified()         );
        setOwner            ( arg.getOwner()              );
        setNotifyEmail      ( arg.getNotifyEmail()        );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public SuppressionRuleAudit getValue() {
		SuppressionRuleAudit result = new SuppressionRuleAudit(getRuleKey());

		Calendar temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getStartTime().getTime());
		result.setStartTime(temp_calendar);

		temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		temp_calendar.setTimeInMillis(getEndTime().getTime());
		result.setEndTime            ( temp_calendar );

		temp_calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        if ( null != getNotificationTime() )
        {
		    temp_calendar.setTimeInMillis(getNotificationTime().getTime());
		    result.setNotificationTime   ( temp_calendar );
        }

        result.setAuditVersionNum    ( getAuditVersionNum()  );
        result.setAuditTimestamp     ( getAuditTimestamp()   );
        result.setAuditModifiedBy    ( getAuditModifiedBy()  );
        result.setApplicationName    ( getAppName()          );
        result.setNodeName           ( getNodeName()         );
        result.setDatabaseServerName ( getDbServer()         );
        result.setMessage            ( getMessage()          );
        result.setDescription        ( getDescription()      );
        result.setDeletedFlag        ( getDeletedFlag()      );
        result.setNotificationFlag   ( getNotificationFlag() );
        result.setNotifyMinutes      ( getNotifyMinutes()    );
        result.setRemoveOnReboot     ( getRemoveOnReboot()   );
        result.setIsNotified         ( getIsNotified()       );
        result.setOwner              ( getOwner()            );
        result.setNotifyEmail        ( getNotifyEmail()      );

		return(result);
	}

	/**
	 * Define rule key.
	 * 
	 * @param arg row/rule key, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRuleKey(String arg);

	/**
	 * Return rule key.
	 * 
	 * @return row/rule key
	 * 
	 * @ejb.pk-field
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="row_id"
	 */
	public abstract String getRuleKey();

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
	 * Define rule start time
	 * 
	 * @param arg rule start time, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setStartTime(Timestamp arg);

	/**
	 * Return rule start time
	 * 
	 * @return rule start time
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="start_time"
	 */
	public abstract Timestamp getStartTime();

	/**
	 * Define rule end time
	 * 
	 * @param arg rule end time, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setEndTime(Timestamp arg);

	/**
	 * Return rule end time
	 * 
	 * @return rule end time
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="end_time"
	 */
	public abstract Timestamp getEndTime();

	/**
	 * Define rule notification time
	 * 
	 * @param arg rule notification time, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setNotificationTime(Timestamp arg);

	/**
	 * Return rule notification time
	 * 
	 * @return rule notification time
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="notification_time"
	 */
	public abstract Timestamp getNotificationTime();

	/**
	 * Define Alarmpoint name
	 * 
	 * @param alarm point name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setAppName(String arg);

	/**
	 * Return Alarmpoint name
	 * 
	 * @return Alarmpoint name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="app_name"
	 */
	public abstract String getAppName();

	/**
	 * Define node name
	 * 
	 * @param node name, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setNodeName(String arg);

	/**
	 * Return node name
	 * 
	 * @return node name
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="node_name"
	 */
	public abstract String getNodeName();

	/**
	 * Define database server
	 * 
	 * @param arg database server, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setDbServer(String arg);

	/**
	 * Return database server
	 * 
	 * @return database server
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="db_server"
	 */
	public abstract String getDbServer();

	/**
	 * Define message
	 * 
	 * @param arg message, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setMessage(String arg);

	/**
	 * Return message
	 * 
	 * @return message
	 * 
	 * @ejb.interface-method view-type="local"
	 * 
	 * @ejb.persistence column-name="message"
	 */
	public abstract String getMessage();

    /**
     * Define the description
     *
     * @param arg the description, cannot be null
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setDescription ( String arg );

    /**
     * Return the description
     *
     * @return description
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="description"
     */
    public abstract String getDescription();

    /**
     * Define the suppression ID
     *
     * @param arg the suppression ID
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setSuppressId ( long arg );

    /**
     * Return the deleted flag
     *
     * @return the deleted flag
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="suppress_id"
     */
    public abstract long getSuppressId();

    /**
     * Define the deleted flag
     *
     * @param arg the deleted flag
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setDeletedFlag ( boolean arg );

    /**
     * Return the deleted flag
     *
     * @return the deleted flag
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="deleted_flg"
     */
    public abstract boolean getDeletedFlag();

    /**
     * Define the notification flag
     *
     * @param arg the notification flag
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setNotificationFlag ( boolean arg );

    /**
     * Return the notification flag
     *
     * @return the notification flag
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="notify_flg"
     */
    public abstract boolean getNotificationFlag();

    /**
     * Define the amount of warning time (in minutes) to give the creator of the suppression
     * before the suppression expires
     *
     * @param minutes the amount of warning time (in minutes)
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setNotifyMinutes ( long minutes );

    /**
     * Return the amount of warning time (in minutes) to to give the creator of the suppression
     * before the suppression expires
     *
     * @return the notification flag
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="notify_minutes"
     */
    public abstract long getNotifyMinutes();

    /**
     * Define the remove-on-reboot flag
     *
     * @param arg the notification flag
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setRemoveOnReboot ( boolean arg );

    /**
     * Return the notification flag
     *
     * @return the notification flag
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="remove_on_reboot"
     */
    public abstract boolean getRemoveOnReboot();

    /**
     * Define whether or not this rule's expiration notification has been sent
     *
     * @param state the notification state
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setIsNotified ( boolean arg );

    /**
     * Return the expiration notification state
     *
     * @return the expiration notification state
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="is_notified" read-only="true"
     */
    public abstract boolean getIsNotified();

    /**
     * Define the email addresses to notify about the suppression
     *
     * @param arg the email addresses to notify about the suppression
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setNotifyEmail ( String arg );

    /**
     * Return the email addresses to notify about the suppression
     *
     * @return the email addresses to notify about the suppression
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="notify_email"
     */
    public abstract String getNotifyEmail();

    /**
     * Define owner/creator of this suppression
     *
     * @param arg the owner or creator of this suppression
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setOwner ( String arg );

    /**
     * Return the owner/creator of this suppression
     *
     * @return the owner/creator of this suppression
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="owner"
     */
    public abstract String getOwner();
}
