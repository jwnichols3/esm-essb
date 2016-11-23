package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 
 * @author Dennis Lin (linden)
 */
public final class ServiceCenter implements Comparable, Serializable {

	/**
	 * empty ctor
	 */
	public ServiceCenter() {
	}

	public ServiceCenter(String arg) {
		if (arg == null) {
			throw new NullPointerException("null row key");
		}
		
		if (arg.length() < 1) {
			throw new IllegalArgumentException("empty row key");
		}
		
		_key = arg;
	}


	private String _key = DEFAULT_KEY;

	public String getRowKey() {
		return (_key);
	}

	public void setRowKey(String arg) {
		_key = arg;
	}

	/**
	 * Service Center ticket number 
	 */
	private String _ticketNum = UNKNOWN_TICKET_NUM;

	/**
	 * Define message group
	 * 
	 * @param arg Service Center ticket number
	 */
	public void setTicketNum(String arg) {
		_ticketNum = arg;
	}

	/**
	 * Return message group
	 * 
	 * @return message group
	 */
	public String getTicketNum() {
		return(_ticketNum);
	}

    /**
     *  Openview Interconnect message ID
     */
    private String _messageId;

    /**
     *
     * @param arg Openview messageID
     */
    public void setMessageId ( String arg ) {
        _messageId = arg;
    }

    /**
     * @return Openview message ID
     */
    public String getMessageId() {
        return _messageId;
    }

	private Calendar _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	/**
	 * Return time stamp
	 * 
	 * @return time stamp
	 */
	public Calendar getTimestamp() {
		return(_time_stamp);
	}

	public void setTimestamp(Calendar arg) {
		_time_stamp = arg;
	}

    final public static String UNKNOWN_TICKET_NUM  = "unknown-ticket-num";
    final public static String INACTIVE_TICKET_NUM = "service-center-inactive";
    final public static String DEFAULT_VALUE  = "default-bogus";
    private String ticket_category            = ServiceCenter.DEFAULT_VALUE;  //  category
    private String ticket_subcategory         = ServiceCenter.DEFAULT_VALUE;  //  subcategory
    private String ticket_open_time           = ServiceCenter.DEFAULT_VALUE;  //  open.time
    private String ticket_opened_by           = ServiceCenter.DEFAULT_VALUE;  //  opened.by
    private String ticket_priority_code       = ServiceCenter.DEFAULT_VALUE;  //  priority.code
    private String ticket_severity_code       = ServiceCenter.DEFAULT_VALUE;  //  severity.code
    private String ticket_update_time         = ServiceCenter.DEFAULT_VALUE;  //  update.time
    private String ticket_assignment          = ServiceCenter.DEFAULT_VALUE;  //  assignment
    private String ticket_alert_time          = ServiceCenter.DEFAULT_VALUE;  //  alert.time
    private String ticket_status              = ServiceCenter.DEFAULT_VALUE;  //  status
    private String ticket_close_time          = ServiceCenter.DEFAULT_VALUE;  //  close.time
    private String ticket_closed_by           = ServiceCenter.DEFAULT_VALUE;  //  closed.by
    private String ticket_flag                = ServiceCenter.DEFAULT_VALUE;  //  flag
    private String ticket_assignee_name       = ServiceCenter.DEFAULT_VALUE;  //  assignee.name
    private String ticket_respond_time        = ServiceCenter.DEFAULT_VALUE;  //  respond.time
    private String ticket_contact_name        = ServiceCenter.DEFAULT_VALUE;  //  contact.name  -- IGI
    private String ticket_actor               = ServiceCenter.DEFAULT_VALUE;  //  actor
    private String ticket_format              = ServiceCenter.DEFAULT_VALUE;  //  format
    private String ticket_deadline_group      = ServiceCenter.DEFAULT_VALUE;  //  deadline.group
    private String ticket_deadline_alert      = ServiceCenter.DEFAULT_VALUE;  //  deadline.alert
    private String ticket_problem             = ServiceCenter.DEFAULT_VALUE;
    private String ticket_product             = ServiceCenter.DEFAULT_VALUE;
    private String ticket_location            = ServiceCenter.DEFAULT_VALUE;
    private String ticket_message             = "";
    private String message_key                = "";
    private long num_attempts                 = 0;

    public void setTicketCategory ( String category )
    {
        if ( null != category )
        {
            ticket_category = category;
        }
    }

    public String getTicketCategory()
    {
        return ticket_category;
    }

    public void setTicketSubCategory ( String sub_category )
    {
        ticket_subcategory = sub_category;
    }

    public String getTicketSubCategory ()
    {
        return ticket_subcategory;
    }

    public void setTicketLocation ( String location )
    {
        ticket_location = location;
    }

    public String getTicketLocation()
    {
        return ticket_location;
    }

    public void setTicketAssignment ( String assignment )
    {
        ticket_assignment = assignment;
    }

    public String getTicketAssignment ()
    {
        return ticket_assignment;
    }

    public void setTicketProduct ( String product )
    {
        ticket_product = product;
    }

    public String getTicketProduct()
    {
        return ticket_product;
    }

    public void setTicketProblem ( String problem )
    {
        ticket_problem = problem;
    }

    public String getTicketProblem()
    {
        return ticket_problem;
    }

    public void setTicketOpenTime ( String open_time )
    {
        ticket_open_time = open_time;
    }

    public String getTicketOpenTime()
    {
        return ticket_open_time;
    }

    public void setTicketOpenedBy ( String opened_by )
    {
        ticket_opened_by = opened_by;
    }

    public String getTicketOpenedBy()
    {
        return ticket_opened_by;
    }

    public void setTicketPriorityCode ( String priority_code )
    {
        ticket_priority_code = priority_code;
    }

    public String getTicketPriorityCode()
    {
        return ticket_priority_code;
    }

    public void setTicketSeverityCode ( String severity_code )
    {
        ticket_severity_code = severity_code;
    }

    public String getTicketSeverityCode()
    {
        return ticket_severity_code;
    }

    public void setTicketUpdateTime ( String update_time )
    {
        ticket_update_time = update_time;
    }

    public String getTicketUpdateTime()
    {
        return ticket_update_time;
    }

    public void setTicketAlertTime ( String alert_time )
    {
        ticket_alert_time = alert_time;
    }

    public String getTicketAlertTime()
    {
        return ticket_alert_time;
    }

    public void setTicketStatus ( String status )
    {
        ticket_status = status;
    }

    public String getTicketStatus()
    {
        return ticket_status;
    }

    public void setTicketCloseTime ( String close_time )
    {
        ticket_close_time = close_time;
    }

    public String getTicketCloseTime()
    {
        return ticket_close_time;
    }

    public void setTicketClosedBy ( String closed_by )
    {
        ticket_closed_by = closed_by;
    }

    public String getTicketClosedBy()
    {
        return ticket_closed_by;
    }

    public void setTicketFlag ( String flag )
    {
        ticket_flag = flag;
    }

    public String getTicketFlag()
    {
        return ticket_flag;
    }

    public void setTicketAssigneeName ( String assignee_name )
    {
        ticket_assignee_name = assignee_name;
    }

    public String getTicketAssigneeName()
    {
        return ticket_assignee_name;
    }

    public void setTicketRespondTime ( String respond_time )
    {
        ticket_respond_time = respond_time;
    }

    public String getTicketRespondTime()
    {
        return ticket_respond_time;
    }

    public void setTicketContactName ( String contact_name )
    {
        ticket_contact_name = contact_name;
    }

    public String getTicketContactName()
    {
        return ticket_contact_name;
    }

    public void setTicketActor ( String actor )
    {
        ticket_actor = actor;
    }

    public String getTicketActor()
    {
        return ticket_actor;
    }

    public void setTicketFormat ( String format ) 
    {
        ticket_format = format;
    }

    public String getTicketFormat()
    {
        return ticket_format;
    }

    public void setTicketDeadlineGroup ( String deadline_group )
    {
        ticket_deadline_group = deadline_group;
    }

    public String getTicketDeadlineGroup()
    {
        return ticket_deadline_group;
    }

    public void setTicketDeadlineAlert ( String deadline_alert )
    {
        ticket_deadline_alert = deadline_alert;
    }

    public String getTicketDeadlineAlert()
    {
        return ticket_deadline_alert;
    }

    public void setTicketMessage ( String message )
    {
        ticket_message = message;
    }

    public String getTicketMessage()
    {
        return ticket_message;
    }

    public void setNumAttempts ( long number_of_attempts )
    {
        num_attempts = number_of_attempts;
    }

    public long getNumAttempts()
    {
        return num_attempts;
    }

    public long incrementNumAttempts()
    {
        num_attempts = num_attempts + 1;

        return num_attempts;
    }

    public void setMessageKey ( String key )
    {
        message_key = key;
    }

    public String getMessageKey()
    {
        return message_key;
    }


	/**
	 * Return hash code value for this object, based on key.
	 * 
	 * @return hash code value for this object, based on key.
	 */
	public int hashCode() {
		return(_key.hashCode());
	}

	/**
	 * Return true if the key matches
	 * 
	 * @param arg if key matches
	 * @return true if successful match
	 */
	public boolean equals(Object arg) {
		if (arg == null) {
			return(false);
		}

		ServiceCenter temp = (ServiceCenter) arg;

		return(temp.getRowKey().equals(_key));
	}

	/**
	 * Support for Comparable.compareTo
	 * 
	 * @param arg item to compare
	 * @return -1 if less, 1 if greater or zero if equal
	 * @throws NullPointerException if null arg
	 */
	public int compareTo(Object arg) {
		if (arg == null) {
			throw new NullPointerException("null arg");
		}

		ServiceCenter temp = (ServiceCenter) arg;

		return(_time_stamp.compareTo(temp.getTimestamp()));
	}

	/**
	 * Return object state as a String
	 * 
	 * @return object state as a String
	 */
	public String toString() 
    {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String temp1 = sdf.format(_time_stamp.getTime());

        StringBuilder message = new StringBuilder();
            message.append ( "ServiceCenter ( TicketNum=" );
            message.append ( _ticketNum );
            message.append ( ", OpenviewMessageID=" );
            message.append ( _messageId );
            message.append ( ", Category=" );
            message.append ( ticket_category );
            message.append ( ", SubCategory=" );
            message.append ( ticket_subcategory );
            message.append ( ", Severity=" );
            message.append ( ticket_severity_code );
            message.append ( ", Assignment=" );
            message.append ( ticket_assignment );
            message.append ( ", Timestamp=" );
            message.append ( temp1 );
            message.append ( ", Product=" );
            message.append ( ticket_product );
            message.append ( ", Problem=" );
            message.append ( ticket_problem );
            message.append ( ", Location=" );
            message.append ( ticket_location );
            message.append ( ", MessageKey=" );
            message.append ( message_key );
            message.append ( " )" );
        return message.toString();
	}

    public String toDebugString()
    {
        StringBuilder message = new StringBuilder ( "Details for Ticket #" );
        message.append ( _ticketNum );
        message.append ( "\n" );
        message.append ( "\tTicket owner:          " + getTicketAssigneeName()   );
        message.append ( "\n" );
        message.append ( "\tTicket message:        " + getTicketMessage()       );
        message.append ( "\n" );
        message.append ( "\tMessage ID:            " + getMessageId()           );
        message.append ( "\n" );
        message.append ( "\tTicket category:       " + getTicketCategory()      );
        message.append ( "\n" );
        message.append ( "\tTicket open time:      " + getTicketOpenTime()      );
        message.append ( "\n" );
        message.append ( "\tTicket opened by:      " + getTicketOpenedBy()      );
        message.append ( "\n" );
        message.append ( "\tTicket priority code:  " + getTicketPriorityCode()  );
        message.append ( "\n" );
        message.append ( "\tTicket severity code:  " + getTicketSeverityCode()  );
        message.append ( "\n" );
        message.append ( "\tTicket update time:    " + getTicketUpdateTime()    );
        message.append ( "\n" );
        message.append ( "\tTicket assignment:     " + getTicketAssignment()    );
        message.append ( "\n" );
        message.append ( "\tTicket alert time:     " + getTicketAlertTime()     );
        message.append ( "\n" );
        message.append ( "\tTicket status:         " + getTicketStatus()        );
        message.append ( "\n" );
        message.append ( "\tTicket close time:     " + getTicketCloseTime()     );
        message.append ( "\n" );
        message.append ( "\tTicket closed by:      " + getTicketClosedBy()      );
        message.append ( "\n" );
        message.append ( "\tTicket flag:           " + getTicketFlag()          );
        message.append ( "\n" );
        message.append ( "\tTicket assignee name:  " + getTicketAssigneeName()  );
        message.append ( "\n" );
        message.append ( "\tTicket respond time:   " + getTicketRespondTime()   );
        message.append ( "\n" );
        message.append ( "\tTicket contact name:   " + getTicketContactName()   );
        message.append ( "\n" );
        message.append ( "\tTicket actor:          " + getTicketActor()         );
        message.append ( "\n" );
        message.append ( "\tTicket format:         " + getTicketFormat()        );
        message.append ( "\n" );
        message.append ( "\tTicket deadline group: " + getTicketDeadlineGroup() );
        message.append ( "\n" );
        message.append ( "\tTicket deadline alert: " + getTicketDeadlineAlert() );
        message.append ( "\n" );
        message.append ( "\tNum creation attempts: " + getNumAttempts()         );
        message.append ( "\n" );
        message.append ( "\tMessage Key:           " + getMessageKey()         );
      
        return message.toString();  
    }

	/**
	 * Default row key value
	 */
	public static final String DEFAULT_KEY = "bogus";
}
