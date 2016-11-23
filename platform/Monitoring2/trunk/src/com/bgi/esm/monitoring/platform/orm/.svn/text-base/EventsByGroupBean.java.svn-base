package com.bgi.esm.monitoring.platform.orm;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;

import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;

/**
 * Entity bean supporting suppression rules
 * 
 * @ejb.bean 
 *   name="EventsByGroupEjb" 
 *   type="CMP" 
 *   cmp-version="2.x"
 *   reentrant="false" 
 *   view-type="local"
 *   local-jndi-name="${jndi.base}/EventsByGroupEjbLocalHome"
 *   description="suppression rule bean"
 * 
 * @ejb.persistence table-name="events_by_group"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAll()" 
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="EventsByGroupLocal findByKey(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.rowId = ?1" 
 *   unchecked="true"
 *
 * @ejb.finder
 *   signature="EventsByGroupLocal findByMessageId(java.lang.String key)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.messageId = ?1 ORDER BY x.timestamp ASC" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllBetweenTimesByGroup (java.lang.String group, java.sql.Timestamp start, java.sql.Timestamp end)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.groupName = ?1 AND x.timestamp between ?2 AND ?3 ORDER BY x.timestamp DESC" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllBetweenTimesByApplication (java.lang.String application, java.sql.Timestamp start, java.sql.Timestamp end)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.application= ?1 AND x.timestamp between ?2 AND ?3 ORDER BY x.timestamp DESC" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllBetweenTimesByGroupApplication (java.lang.String group, java.lang.String application, java.sql.Timestamp start, java.sql.Timestamp end)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.groupName = ?1 AND x.application=?2 AND x.timestamp between ?3 AND ?4 ORDER BY x.timestamp DESC" 
 *   unchecked="true"
 * 
 * @ejb.finder 
 *   signature="java.util.Collection findAllBetweenTimes (java.sql.Timestamp start, java.sql.Timestamp end)"
 *   query="SELECT OBJECT(x) FROM EventsByGroupEjb AS x WHERE x.timestamp between ?1 AND ?2 ORDER BY x.timestamp DESC" 
 *   unchecked="true"
 * 
 * @ejb.transaction type="Required"
 * 
 * @ejb.value-object match="*"
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class EventsByGroupBean implements EntityBean {

	/**
	 * Create a fresh database row
	 * 
	 * @param key generated rule key
	 * @param arg fresh suppression rule
	 * @throws CreateException if create problem
	 * 
	 * @ejb.create-method
	 */
	public EventsByGroupEjbPK ejbCreate(EventsByGroupEjbPK key, EventsByGroup arg) throws CreateException {
		setRowId(key.getRowId());
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
	public void setValue(EventsByGroup arg) {

        //  Need to set the row ID for the original value object
        arg.setRowId ( getRowId() );

        setGroupName       ( arg.getGroupName()       );
        setMessageId       ( arg.getMessageId()       );
        setTimestamp       ( new Timestamp ( arg.getTimestamp().getTimeInMillis() ) );
        setSourceNode      ( arg.getSourceNode()      );
        setSourceNodeType  ( arg.getSourceNodeType()  );
        setApplication     ( arg.getApplication()     );
        setObject          ( arg.getObject()          );
        setSeverity        ( arg.getSeverity()        );
        setMessageText     ( arg.getMessageText()     );
        setInstructionText ( arg.getInstructionText() );
        setResponderId     ( arg.getResponderId()     );
	}

	/**
	 * Return a database row.
	 * 
	 * @return row contents as value object
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public EventsByGroup getValue() {
		Calendar temp_calendar = Calendar.getInstance ( TimeZone.getTimeZone ( "GMT" ) );
        temp_calendar.setTimeInMillis ( getTimestamp().getTime() );

		EventsByGroup result = new EventsByGroup(getRowId());
        result.setGroupName       ( getGroupName()       );
        result.setMessageId       ( getMessageId()       );
        result.setTimestamp       ( temp_calendar        );
        result.setSourceNode      ( getSourceNode()      );
        result.setSourceNodeType  ( getSourceNodeType()  );
        result.setApplication     ( getApplication()     );
        result.setObject          ( getObject()          );
        result.setSeverity        ( getSeverity()        );
        result.setMessageText     ( getMessageText()     );
        result.setInstructionText ( getInstructionText() );
        result.setResponderId     ( getResponderId()     );

		return(result);
	}

	/**
	 * Define rule key.
	 * 
	 * @param arg row/rule key, cannot be null
	 * 
	 * @ejb.interface-method view-type="local"
	 */
	public abstract void setRowId(String arg);

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
	public abstract String getRowId();

    /**
     * Define the name of the BGI app group
     *
     * @param arg the name of the BGI app group
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setGroupName ( String arg );

    /**
     * Return the name of the BGI app group
     *
     * @return the name of the BGI app group
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="bgi_group"
     */
    public abstract String getGroupName();

    /**
     * Define the OVO message ID
     *
     * @param arg the OVO message ID
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setMessageId ( String arg );

    /**
     * Return the OVO message ID
     *
     * @return the OVO message ID
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="message_id"
     */
    public abstract String getMessageId();

    /**
     * Define the timestamp
     *
     * @param arg the timestamp
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setTimestamp ( Timestamp arg );

    /**
     * Return the timestamp
     *
     * @return the timestamp
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="time_stamp"
     */
    public abstract Timestamp getTimestamp();

    /**
     * Define the source node for this message
     *
     * @param arg the source node for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setSourceNode ( String arg );

    /**
     * Return the source node for this message
     *
     * @return the source node for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="source_node"
     */
    public abstract String getSourceNode();

    /**
     * Define the source node type for this message
     *
     * @param arg the source node type for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setSourceNodeType ( String arg );

    /**
     * Return the source node type for this message
     *
     * @return the source node type for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="source_node_type"
     */
    public abstract String getSourceNodeType();

    /**
     * Define the application for this message
     *
     * @param arg the application for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setApplication ( String arg );

    /**
     * Return the application for this message
     *
     * @return the application for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="application"
     */
    public abstract String getApplication();

    /**
     * Define the object for this message
     *
     * @param arg the object for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setObject ( String arg );

    /**
     * Return the object for this message
     *
     * @return the object for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="object"
     */
    public abstract String getObject();

    /**
     * Define the object for this message
     *
     * @param arg the object for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setSeverity ( String arg );

    /**
     * Return the object for this message
     *
     * @return the object for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="severity"
     */
    public abstract String getSeverity();

    /**
     * Define the message text for this message
     *
     * @param arg the message text for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setMessageText ( String arg );

    /**
     * Return the message text for this message
     *
     * @return the message text for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="message_text"
     */
    public abstract String getMessageText();

    /**
     * Define the instruction text for this message
     *
     * @param arg the instruction text for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setInstructionText ( String arg );

    /**
     * Return the instruction text for this message
     *
     * @return the instruction text for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="instruction_text"
     */
    public abstract String getInstructionText();

    /**
     * Define the responder ID for this message
     *
     * @param arg the responder ID for this message
     *
     * @ejb.interface-method view-type="local"
     */
    public abstract void setResponderId ( String arg );

    /**
     * Return the responder ID for this message
     *
     * @return the responder ID for this message
     *
     * @ejb.interface-method view-type="local"
     *
     * @ejb.persistence column-name="responder_id"
     */
    public abstract String getResponderId();
}
