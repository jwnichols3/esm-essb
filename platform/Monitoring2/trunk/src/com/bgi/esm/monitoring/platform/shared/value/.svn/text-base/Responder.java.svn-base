package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Value object for census item, used to calculate storm levels. 
 * This class is for application internal use only, it is not 
 * shared w/RMI clients.
 * 
 * @author Dennis Lin (linden)
 */
public final class Responder implements Serializable 
{
    public static final String DEFAULT_KEY             = "default-bogus";
    public static final String SC_AUDIT_ACTION_EXPIRED = "SC-create-ticket-expired";
    public static final String SC_AUDIT_ACTION_SUCCESS = "SC-create-ticket-success";
    public static final String SC_AUDIT_ACTION_FAILURE = "SC-create-ticket-failure";
    public static final String SC_AUDIT_ACTION_DELETED = "SC-create-ticket-deleted";
    public static final String AP_AUDIT_ACTION_SUCCESS = "AP-send-notification-success";
    public static final String AP_AUDIT_ACTION_EXPIRED = "AP-send-notification-expired";
    public static final String AP_AUDIT_ACTION_FAILURE = "AP-send-notification-failure";
    public static final String AP_AUDIT_ACTION_DELETED = "AP-send-notification-deleted";

    private String   message_key          = "";
	private String   _key                 = DEFAULT_KEY;                      //  Default row key
    private String   _message_id          = null;                             //  Openview Message ID
    private String   _group               = "esm";                            //  Notification target group
	private Calendar _time_stamp          = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    private long     _locked_timestamp    = 0;
    private long     _duplicate_count     = 0;                                //  Number of duplicate events
    private String   _ap_notif_target     = null;                             //  Alarmpoint notification target
    private String   _ap_event_id         = "AP-Default";                     //  Alarmpoint Event ID
    private String   _ap_message          = "Default Alarmpoint message";     //  Alarmpoint message
    private String   _sc_ticket_message   = null;                             //  Service Center ticket message
	private String   _sc_ticket_num       = "IM-Default";                     //  Service Center Ticket number
    private String   _sc_ticket_owner     = "ESM";                            //  Default owner should be the ESM team
    private String   _sc_async_receipt    = null;                             //  Request receipt for making an asynchronous request
                                                                                           //  to GSS's Service Center Gateway

	/**
	 * empty ctor
	 */
	public Responder() 
    {
		//empty
	}

	public Responder(String arg) 
    {
		if (arg == null) 
        {
			throw new NullPointerException("null row key");
		}
		
		if (arg.length() < 1) 
        {
			throw new IllegalArgumentException("empty row key");
		}
		
		_key = arg;
	}

	public String getRowKey() 
    {
		return (_key);
	}

	public void setRowKey(String arg) 
    {
		_key = arg;
	}

	/**
	 * Define the Service Center ticket number
	 * 
	 * @param arg the Service Center ticket number
	 */
	public void setServiceCenterTicketNumber(String arg) 
    {
		_sc_ticket_num = arg;
	}

	/**
	 * Return the Service Center ticket number
	 * 
	 * @return the Service Center ticket number
	 */
	public String getServiceCenterTicketNumber() 
    {
		return(_sc_ticket_num );
	}

    public void setAlarmpointMessage ( String ap_message )
    {
        _ap_message = ap_message;
    }

    public String getAlarmpointMessage ()
    {
        return _ap_message;
    }

    public void setAlarmpointEventID ( String ap_event_id )
    {
        _ap_event_id = ap_event_id;
    }

    public String getAlarmpointEventID()
    {
        return _ap_event_id;
    }

    public void setMessageId ( String message_id )
    {
        _message_id = message_id;
    }

    public String getMessageId ()
    {
        return _message_id;
    }

    public void setServiceCenterTicketOwner ( String ticket_owner )
    {
        _sc_ticket_owner = ticket_owner;
    }

    public String getServiceCenterTicketOwner()
    {
        return _sc_ticket_owner;
    }

    public String getSCAsyncRequestReceipt ()
    {
        return _sc_async_receipt;
    }

    public void setSCAsyncRequestReceipt (  String request_receipt )
    {
        _sc_async_receipt = request_receipt;
    }

    public void setServiceCenterTicketMessage ( String ticket_message )
    {
        _sc_ticket_message = ticket_message;
    }

    public String getServiceCenterTicketMessage ()
    {
        return _sc_ticket_message;
    }

    public void setAlarmpointNotificationTarget ( String notification_target )
    {
        _ap_notif_target = notification_target;
    }

    public String getAlarmpointNotificationTarget ()
    {
        return _ap_notif_target;
    }

    public void setGroup ( String group )
    {
        if (( null == group ) || ( group.trim().length() == 0 ))
        {
            group = "esm";
        }
        else
        {
            _group = group;
        }
    }

    public String getGroup()
    {
        return _group;
    }

    public void setDuplicateCount ( long duplicate_count )
    {
        _duplicate_count = duplicate_count;
    }

    public long getDuplicateCount ()
    {
        return _duplicate_count;
    }

    public long incrementDuplicateCount()
    {
        _duplicate_count = _duplicate_count + 1;

        return _duplicate_count;
    }

    public void setLockedTimestamp ( long locked_timestamp )
    {
        _locked_timestamp = locked_timestamp;
    }

    public long getLockedTimestamp()
    {
        return _locked_timestamp;
    }

    public void lock()
    {
        _locked_timestamp = System.currentTimeMillis();
    }

    public void unlock()
    {
        _locked_timestamp = 0L;
    }

    public boolean isLocked()
    {
        return _locked_timestamp > 0;
    }

	public Calendar getTimestamp() 
    {
		return(_time_stamp);
	}

	public void setTimestamp ( Calendar arg ) 
    {
		_time_stamp = arg;
	}

    public void setMessageKey ( String key )
    {
        message_key = key;
    }

    public String getMessageKey()
    {
        return message_key;
    }

    public void resetTimestamp()
    {
	    _time_stamp = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    }

    public String toString()
    {
        StringBuilder message = new StringBuilder();
            message.append ( "Responder ( key=" );
            message.append ( _key );
            message.append ( ", group=" );
            message.append ( _group );
            message.append ( ", OpenviewID=" );
            message.append ( _message_id );
            message.append ( ", messageKey=" );
            message.append ( message_key );
            message.append ( ", duplicateCount=" );
            message.append ( _duplicate_count );
            message.append ( ", SCTicketOwner=" );
            message.append ( _sc_ticket_owner );
            message.append ( ", SCTicketNum=" );
            message.append ( _sc_ticket_num );
            message.append ( ", APEventID=" );
            message.append ( _ap_event_id );
            message.append ( ", AsyncReceipt=" );
            message.append ( _sc_async_receipt );
            message.append ( " )" );
        return message.toString();
    }
}
