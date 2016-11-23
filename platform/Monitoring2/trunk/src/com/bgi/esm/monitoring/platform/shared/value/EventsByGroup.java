package com.bgi.esm.monitoring.platform.shared.value;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

public class EventsByGroup implements Serializable
{
    final public static String DEFAULT_KEY = "DEFAULT-KEY";
    private String _row_id                 = DEFAULT_KEY;
    private String _group_name             = "";
    private String _message_id             = "";
    private String _source_node            = "";
    private String _source_node_type       = "";
    private String _application            = "";
    private String _object                 = "";
    private String _severity               = "";
    private String _message_text           = "";
    private String _instruction_text       = "";
    private String _responder_id           = "";
    private int    _suppress_count         = 0;
    private Calendar _timestamp = Calendar.getInstance ( TimeZone.getTimeZone ("GMT") );

    public EventsByGroup()
    {
    }

    public EventsByGroup ( String row_id )
    {
        _row_id = row_id;
    }

    public void setRowId ( String row_id )
    {
        _row_id = row_id;
    }

    public String getRowId()
    {
        return _row_id;
    }

    public void setGroupName ( String group_name )
    {
        _group_name = group_name;
    }

    public String getGroupName()
    {
        return _group_name;
    }

    public void setMessageId ( String message_id )
    {
        _message_id = message_id;
    }

    public String getMessageId()
    {
        return _message_id;
    }

    public void setTimestamp ( Calendar timestamp )
    {
        _timestamp = timestamp;
    }

    public Calendar getTimestamp ()
    {
        return _timestamp;
    }

    public void setSourceNode ( String source_node )
    {
        if ( null != source_node )
        {
            _source_node = source_node;
        }
    }

    public String getSourceNode()
    {
        return _source_node;
    }

    public void setSourceNodeType ( String source_node_type )
    {
        if ( null != source_node_type )
        {
            _source_node_type = source_node_type;
        }
    }

    public String getSourceNodeType()
    {
        return _source_node_type;
    }

    public void setApplication ( String application )
    {
        if ( null != application )
        {
            _application = application;
        }
    }

    public String getApplication()
    {
        return _application;
    }

    public void setObject ( String object )
    {
        if ( null != object )
        {
            _object = object;
        }
    }

    public String getObject()
    {
        return _object;
    }

    public void setSeverity ( String severity )
    {
        _severity = severity;
    }

    public String getSeverity ()
    {
        return _severity;
    }

    public void setMessageText ( String message_text )
    {
        if ( null != message_text )
        {
            _message_text = message_text;
        }
    }

    public String getMessageText()
    {
        return _message_text;
    }

    public void setInstructionText ( String instruction_text )
    {
        if ( null != instruction_text )
        {
            _instruction_text = instruction_text;
        }
    }

    public String getInstructionText ()
    {
        return _instruction_text;
    }

    public void setSuppressCount ( int suppress_count )
    {
        _suppress_count = suppress_count;
    }

    public int getSuppressCount()
    {
        return _suppress_count;
    }
    
    public void setResponderId ( String responder_id )
    {
        _responder_id = responder_id;
    }

    public String getResponderId()
    {
        return _responder_id;
    }
}
