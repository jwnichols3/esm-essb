package com.bgi.esm.monitoring.portlets.Openview2EEBBridge.forms;

import java.lang.ref.WeakReference;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class NotifierEventForm extends ActionForm
{
    final private static Logger _log = Logger.getLogger ( NotifierEventForm.class );

    private String MessageID         = null;
    private String NodeName          = null;
    private String NodeType          = null;
    private String CreationDate      = null;
    private String CreationTime      = null;
    private String ReceiveDate       = null;
    private String ReceiveTime       = null;
    private String Application       = null;
    private String MessageGroup      = null;
    private String Object            = null;
    private String Severity          = null;
    private String Operators         = null;
    private String Message           = null;
    private String Instruction       = null;
    private String MessageAttributes = null;
    private String SuppressCount     = null;
    private String OVOInterface      = null;

    public NotifierEventForm()
    {
        super();
    }

    public void reset ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "NotifierEvent::reset()" );
    }

    public ActionErrors validate ( ActionMapping mapping, HttpServletRequest request )
    {
        _log.debug ( "NotifierEvent::validate()" );
        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public void setMessageID ( String message_id )
    {
        MessageID = message_id;
    }

    public String getMessageID()
    {
        return MessageID;
    }

    public void setNodeName ( String node_name )
    {
        NodeName = node_name;
    }

    public String getNodeName()
    {
        return NodeName;
    }

    public void setNodeType ( String node_type )
    {
        NodeType = node_type;
    }

    public String getNodeType()
    {
        return NodeType;
    }

    public void setCreationDate ( String creation_date )
    {
        CreationDate = creation_date;
    }

    public String getCreationDate()
    {
        return CreationDate;
    }

    public void setCreationTime ( String creation_time )
    {
        CreationTime = creation_time;
    }

    public String getCreationTime()
    {
        return CreationTime;
    }

    public void setReceiveDate ( String receive_date )
    {
        ReceiveDate = receive_date;
    }

    public String getReceiveDate()
    {
        return ReceiveDate;
    }

    public void setReceiveTime ( String receive_time )
    {
        ReceiveTime = receive_time;
    }

    public String getReceiveTime()
    {
        return ReceiveTime;
    }

    public void setApplication ( String application )
    {
        Application = application;
    }

    public String getApplication ()
    {
        return Application;
    }

    public void setMessageGroup ( String application_group )
    {
        MessageGroup = application_group;
    }

    public String getMessageGroup()
    {
        return MessageGroup;
    }

    public void setObject ( String object )
    {
        Object = object;
    }

    public String getObject ()
    {
        return Object;
    }

    public void setSeverity ( String severity )
    {
        Severity = severity;
    }

    public String getSeverity ()
    {
        return Severity;
    }

    public void setOperators ( String operators )
    {
        Operators = operators;
    }

    public String getOperators ()
    {
        return Operators;
    }

    public void setMessage ( String message )
    {
        Message = message;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setInstruction ( String instruction )
    {
        Instruction = instruction;
    }

    public String getInstruction ()
    {
        return Instruction;
    }

    public void setMessageAttributes ( String message_attributes )
    {
        MessageAttributes = message_attributes;
    }

    public String getMessageAttributes()
    {
        return MessageAttributes;
    }

    public void setSuppressCount ( String suppression_count )
    {
        SuppressCount = suppression_count;
    }

    public String getSuppressCount()
    {
        return SuppressCount;
    }

    public void setOVOInterface ( String ovo_interface )
    {
        OVOInterface = ovo_interface;
    }

    public String getOVOInterface()
    {
        return OVOInterface;
    }

    public String toString()
    {
        WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
        message.get().append ( "Notifier Event from Openview ( Messageid=" );
        message.get().append ( getMessageID() );
        message.get().append ( ", SourceNode=" );
        message.get().append ( getNodeName() );
        message.get().append ( ", SourceNodeType=" );
        message.get().append ( getNodeType() );
        message.get().append ( ", CreationTime=" );
        message.get().append ( getCreationTime() );
        message.get().append ( ", CreationDate=" );
        message.get().append ( getCreationDate() );
        message.get().append ( ", ReceiveTime=" );
        message.get().append ( getReceiveTime() );
        message.get().append ( ", ReceiveDate=" );
        message.get().append ( getReceiveDate() );
        message.get().append ( ", Application=" );
        message.get().append ( getApplication() );
        message.get().append ( ", Object=" );
        message.get().append ( getObject() );
        message.get().append ( ", Severity=" );
        message.get().append ( getSeverity() );
        message.get().append ( ", Operators=" );
        message.get().append ( getOperators() );
        message.get().append ( ", Message=" );
        message.get().append ( getMessage() );
        message.get().append ( ", Instruction=" );
        message.get().append ( getInstruction() );
        message.get().append ( ", MessageAttributes=" );
        message.get().append ( getMessageAttributes() );
        message.get().append ( ", SuppressCount=" );
        message.get().append ( getSuppressCount() );
        message.get().append ( " )\n" );

        return message.get().toString();
    }
};
