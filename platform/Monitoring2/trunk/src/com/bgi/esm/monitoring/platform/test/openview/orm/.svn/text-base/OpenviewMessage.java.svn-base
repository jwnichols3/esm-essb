package com.bgi.esm.monitoring.platform.test.openview.orm;

import java.io.Serializable;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public abstract class OpenviewMessage implements Serializable
{
    final private static Logger _log                        = Logger.getLogger ( OpenviewMessage.class );
    protected String    MessageNumber                       = null;
    protected String    ConditionID                         = null;
    protected String    InstructionID                       = null;
    protected String    NodeID                              = null;
    protected String    MessageGenNodeID                    = null;
    protected int       NetworkType                         = 0;
    protected int       LogOnlyFlag                         = 0;
    protected int       UnmatchedFlag                       = 0;
    protected int       NotificationFlag                    = 0;
    protected int       TroubleTicketFlag                   = 0;
    protected int       AcknowledgeAfterTroubleTicketFlag   = 0;
    protected int       MessageGroupMiscFlag                = 0;
    protected long      MessageSourceType                   = 0;
    protected long      CreationTime                        = 0;
    protected long      ReceivingTime                       = 0;              
    protected Calendar  LocalCreationTime                   = null;
    protected Calendar  LocalReceivingTime                  = null;
    protected int       Severity                            = 0;
    protected int       AutoStatus                          = 0;
    protected int       AutoAnnotationFlag                  = 0;
    protected int       AutoAcknowledgeFlag                 = 0;
    protected String    AutoNodeID                          = null;
    protected int       OperationsInitStatus                = 0;
    protected String    OperationsInitNodeID                = null;
    protected int       OperationsInitAnnotationFlag        = 0;
    protected int       OperationsInitAcknowledgeFlag       = 0;
    protected int       EscalateFlag                        = 0;
    protected int       AssignFlag                          = 0;
    protected int       InstructionType                     = 0;
    protected int       Forward                             = 0;
    protected int       ReadOnly                            = 0;
    protected String    OriginalMessageID                   = null;
    protected int       BufferFlag                          = 0;
    protected long      UnbufferTime                        = 0;
    protected Calendar  LocalUnbufferTime                   = null;
    protected long      TimeDiff                            = 0;
    protected Calendar  LocalAgtCreationTime                = null;
    protected int       AcknowledgeFlag                     = 0;
    protected long      AcknowledgeTime                     = 0;
    protected Calendar  LocalAcknowledgeTime                = null;
    protected String    AcknowledgeUser                     = null;
    protected String    MessageSourceName                   = null;
    protected String    Application                         = null;
    protected String    MessageGroup                        = null;
    protected String    Object                              = null;
    protected String    NotifyServices                      = null;
    protected String    AutoCall                            = null;
    protected String    OperationsInitCall                  = null;
    protected String    MessageType                         = null;
    protected String    ResolvedInstructionsParameter       = null;
    protected String    ServiceName                         = null;
    protected String    MessageKey                          = null;
    protected long      DuplicateCount                      = 0;
    protected long      LastTimeReceived                    = 0;
    protected Calendar  LocalLastTimeReceived               = null;
    protected int       CMAFlag                             = 0;

    public OpenviewMessage()
    {
    }

    public abstract OpenviewMessage getValue();

    public void setValue ( OpenviewMessage message )
    {
        if ( null != message )
        {
            setMessageNumber                     ( message.getMessageNumber()                     );
            setConditionID                       ( message.getConditionID()                       );
            setInstructionID                     ( message.getInstructionID()                     );
            setNodeID                            ( message.getNodeID()                            );
            setMessageGenNodeID                  ( message.getMessageGenNodeID()                  );
            setNetworkType                       ( message.getNetworkType()                       );
            setLogOnlyFlag                       ( message.getLogOnlyFlag()                       );
            setUnmatchedFlag                     ( message.getUnmatchedFlag()                     );
            setNotificationFlag                  ( message.getNotificationFlag()                  );
            setTroubleTicketFlag                 ( message.getTroubleTicketFlag()                 );
            setAcknowledgeAfterTroubleTicketFlag ( message.getAcknowledgeAfterTroubleTicketFlag() );
            setMessageGroupMiscFlag              ( message.getMessageGroupMiscFlag()              );
            setMessageSourceType                 ( message.getMessageSourceType()                 );
            setCreationTime                      ( message.getCreationTime()                      );
            setReceivingTime                     ( message.getReceivingTime()                     );              
            setLocalCreationTime                 ( message.getLocalCreationTime()                 );
            setLocalReceivingTime                ( message.getLocalReceivingTime()                );
            setSeverity                          ( message.getSeverity()                          );
            setAutoStatus                        ( message.getAutoStatus()                        );
            setAutoAnnotationFlag                ( message.getAutoAnnotationFlag()                );
            setAutoAcknowledgeFlag               ( message.getAutoAcknowledgeFlag()               );
            setAutoNodeID                        ( message.getAutoNodeID()                        );
            setOperationsInitStatus              ( message.getOperationsInitStatus()              );
            setOperationsInitNodeID              ( message.getOperationsInitNodeID()              );
            setOperationsInitAnnotationFlag      ( message.getOperationsInitAnnotationFlag()      );
            setOperationsInitAcknowledgeFlag     ( message.getOperationsInitAcknowledgeFlag()     );
            setEscalateFlag                      ( message.getEscalateFlag()                      );
            setAssignFlag                        ( message.getAssignFlag()                        );
            setInstructionType                   ( message.getInstructionType()                   );
            setForward                           ( message.getForward()                           );
            setReadOnly                          ( message.getReadOnly()                          );
            setOriginalMessageID                 ( message.getOriginalMessageID()                 );
            setBufferFlag                        ( message.getBufferFlag()                        );
            setUnbufferTime                      ( message.getUnbufferTime()                      );
            setLocalUnbufferTime                 ( message.getLocalUnbufferTime()                 );
            setTimeDiff                          ( message.getTimeDiff()                          );
            setLocalAgtCreationTime              ( message.getLocalAgtCreationTime()              );
            setAcknowledgeFlag                   ( message.getAcknowledgeFlag()                   );
            setAcknowledgeTime                   ( message.getAcknowledgeTime()                   );
            setLocalAcknowledgeTime              ( message.getLocalAcknowledgeTime()              );
            setAcknowledgeUser                   ( message.getAcknowledgeUser()                   );
            setMessageSourceName                 ( message.getMessageSourceName()                 );
            setApplication                       ( message.getApplication()                       );
            setMessageGroup                      ( message.getMessageGroup()                      );
            setObject                            ( message.getObject()                            ); 
            setNotifyServices                    ( message.getNotifyServices()                    );
            setAutoCall                          ( message.getAutoCall()                          );
            setOperationsInitCall                ( message.getOperationsInitCall()                );
            setMessageType                       ( message.getMessageType()                       );
            setResolvedInstructionsParameter     ( message.getResolvedInstructionsParameter()     );
            setServiceName                       ( message.getServiceName()                       );
            setMessageKey                        ( message.getMessageKey()                        );
            setDuplicateCount                    ( message.getDuplicateCount()                    );
            setLastTimeReceived                  ( message.getLastTimeReceived()                  );
            setLocalLastTimeReceived             ( message.getLocalLastTimeReceived()             );
            setCMAFlag                           ( message.getCMAFlag()                           );
        }
        else
        {
            throw new IllegalArgumentException ( "Attempted to set OpenviewMessage object to null" );
        }
    }

    protected void setMessageNumber ( String message_number )
    {
        MessageNumber = message_number;
    }

    protected String getMessageNumber()
    {
        return MessageNumber;
    }

    protected void setConditionID ( String condition_id )
    {
        ConditionID = condition_id;
    }

    protected String getConditionID()
    {
        return ConditionID;
    }

    protected void setInstructionID ( String instruction_id )
    {
        InstructionID = instruction_id;
    }

    protected String getInstructionID()
    {
        return InstructionID;
    }

    protected void setNodeID ( String node_id )
    {
        NodeID = node_id;
    }

    protected String getNodeID()
    {
        return NodeID;
    }

    protected void setMessageGenNodeID ( String message_gen_node_id )
    {
        MessageGenNodeID = message_gen_node_id;
    }

    protected String getMessageGenNodeID()
    {
        return MessageGenNodeID;
    }

    protected void setNetworkType ( int network_type )
    {
        NetworkType = network_type;
    }

    protected int getNetworkType()
    {
        return NetworkType;
    }

    protected void setLogOnlyFlag ( int log_only_flag )
    {
        LogOnlyFlag = log_only_flag;
    }

    protected int getLogOnlyFlag()
    {
        return LogOnlyFlag;
    }

    protected void setUnmatchedFlag ( int unmatched_flag )
    {
        UnmatchedFlag = unmatched_flag;
    }

    protected int getUnmatchedFlag()
    {
        return UnmatchedFlag;
    }

    protected void setMessageSourceType ( long message_source_type )
    {
        MessageSourceType = message_source_type;
    }

    protected long getMessageSourceType()
    {
        return MessageSourceType;
    }

    protected void setNotificationFlag ( int notification_flag )
    {
        NotificationFlag = notification_flag;
    }

    protected int getNotificationFlag()
    {
        return NotificationFlag;
    }

    protected void setTroubleTicketFlag ( int trouble_ticket_flag )
    {
        TroubleTicketFlag = trouble_ticket_flag;
    }

    protected int getTroubleTicketFlag()
    {
        return TroubleTicketFlag;
    }

    protected void setAcknowledgeAfterTroubleTicketFlag ( int acknowledge_after_trouble_ticket_flag )
    {
        AcknowledgeAfterTroubleTicketFlag = acknowledge_after_trouble_ticket_flag;
    }

    protected int getAcknowledgeAfterTroubleTicketFlag()
    {
        return AcknowledgeAfterTroubleTicketFlag;
    }

    protected void setMessageGroupMiscFlag ( int message_group_misc_flag )
    {
        MessageGroupMiscFlag = message_group_misc_flag;
    }

    protected int getMessageGroupMiscFlag()
    {
        return MessageGroupMiscFlag;
    }

    protected void setCreationTime ( long creation_time )
    {
        CreationTime = creation_time;
    }

    protected long getCreationTime()
    {
        return CreationTime;
    }

    protected void setLocalCreationTime ( Calendar local_creation_time )
    {
        LocalCreationTime = local_creation_time;
    }

    protected Calendar getLocalCreationTime()
    {
        return LocalCreationTime;
    }

    protected void setReceivingTime ( long receiving_time )
    {
        ReceivingTime = receiving_time;
    }

    protected long getReceivingTime()
    {
        return ReceivingTime;
    }

    protected void setLocalReceivingTime ( Calendar local_receiving_time )
    {
        LocalReceivingTime = local_receiving_time;
    }

    protected Calendar getLocalReceivingTime()
    {
        return LocalReceivingTime;
    }

    protected void setSeverity ( int severity )
    {
        Severity = severity;
    }

    protected int getSeverity()
    {
        return Severity;
    }

    protected void setAutoStatus ( int auto_status )
    {
        AutoStatus = auto_status;
    }

    protected int getAutoStatus()
    {
        return AutoStatus;
    }

    protected void setAutoAnnotationFlag ( int auto_annotation_flag )
    {
        AutoAnnotationFlag = auto_annotation_flag;
    }

    protected int getAutoAnnotationFlag()
    {
        return AutoAnnotationFlag;
    }

    protected void setAutoAcknowledgeFlag ( int auto_acknowledge_flag )
    {
        AutoAcknowledgeFlag = auto_acknowledge_flag;
    }

    protected int getAutoAcknowledgeFlag()
    {
        return AutoAcknowledgeFlag;
    }

    protected void setAutoNodeID ( String auto_node_id )
    {
        AutoNodeID = auto_node_id;
    }

    protected String getAutoNodeID()
    {
        return AutoNodeID;
    }

    protected void setOperationsInitStatus ( int operations_init_status )
    {
        OperationsInitStatus = operations_init_status;
    }

    protected int getOperationsInitStatus()
    {
        return OperationsInitStatus;
    }

    protected void setOperationsInitNodeID ( String operations_init_node_id )
    {
        OperationsInitNodeID = operations_init_node_id;
    }

    protected String getOperationsInitNodeID()
    {
        return OperationsInitNodeID;
    }

    protected void setOperationsInitAnnotationFlag ( int operations_init_annotation_flag )
    {
        OperationsInitAnnotationFlag = operations_init_annotation_flag;
    }

    protected int getOperationsInitAnnotationFlag()
    {
        return OperationsInitAnnotationFlag;
    }

    protected void setOperationsInitAcknowledgeFlag ( int operations_init_acknowledge_flag )
    {
        OperationsInitAcknowledgeFlag = operations_init_acknowledge_flag;
    }

    protected int getOperationsInitAcknowledgeFlag()
    {
        return OperationsInitAcknowledgeFlag;
    }

    protected void setEscalateFlag ( int escalate_flag )
    {
        EscalateFlag = escalate_flag;
    }

    protected int getEscalateFlag()
    {
        return EscalateFlag;
    }

    protected void setAssignFlag ( int assign_flag )
    {
        AssignFlag = assign_flag;
    }

    protected int getAssignFlag()
    {
        return AssignFlag;
    }

    protected void setInstructionType ( int instruction_type )
    {
        InstructionType = instruction_type;
    }

    protected int getInstructionType()
    {
        return InstructionType;
    }

    protected void setForward ( int forward )
    {
        Forward = forward;
    }

    protected int getForward()
    {
        return Forward;
    }

    protected void setReadOnly ( int read_only )
    {
        ReadOnly = read_only;
    }

    protected int getReadOnly()
    {
        return ReadOnly;
    }

    protected void setOriginalMessageID ( String original_message_id )
    {
        OriginalMessageID = original_message_id;
    }

    protected String getOriginalMessageID()
    {
        return OriginalMessageID;
    }

    protected void setBufferFlag ( int buffer_flag )
    {
        BufferFlag = buffer_flag;
    }

    protected int getBufferFlag()
    {
        return BufferFlag;
    }

    protected void setUnbufferTime ( long unbuffer_time )
    {
        UnbufferTime = unbuffer_time;
    }

    protected long getUnbufferTime()
    {
        return UnbufferTime;
    }

    protected void setLocalUnbufferTime ( Calendar local_unbuffer_time )
    {
        LocalUnbufferTime = local_unbuffer_time;
    }

    protected Calendar getLocalUnbufferTime()
    {
        return LocalUnbufferTime;
    }

    protected void setTimeDiff ( long time_diff )
    {
        TimeDiff = time_diff;
    }

    protected long getTimeDiff()
    {
        return TimeDiff;
    }

    protected void setLocalAgtCreationTime ( Calendar local_agt_creation_time )
    {
        LocalAgtCreationTime = local_agt_creation_time;
    }
    
    protected Calendar getLocalAgtCreationTime()
    {
        return LocalAgtCreationTime;
    }

    protected void setLocalAcknowledgeTime ( Calendar local_acknowledge_time )
    {
        LocalAcknowledgeTime = local_acknowledge_time;
    }

    protected Calendar getLocalAcknowledgeTime()
    {
        return LocalAcknowledgeTime;
    }

    protected void setAcknowledgeFlag ( int acknowledge_flag )
    {
        AcknowledgeFlag = acknowledge_flag;
    }

    protected int getAcknowledgeFlag()
    {
        return AcknowledgeFlag;
    }

    protected void setAcknowledgeTime ( long acknowledge_time )
    {
        AcknowledgeTime = acknowledge_time;
    }

    protected long getAcknowledgeTime()
    {
        return AcknowledgeTime;
    }

    protected void setAcknowledgeUser ( String acknowledge_user )
    {
        AcknowledgeUser = acknowledge_user;
    }

    protected String getAcknowledgeUser()
    {
        return AcknowledgeUser;
    }

    protected void setMessageSourceName ( String message_source_name )
    {
        MessageSourceName = message_source_name;
    }

    protected String getMessageSourceName()
    {
        return MessageSourceName;
    }

    protected void setApplication ( String application )
    {
        Application = application;
    }

    protected String getApplication()
    {
        return Application;
    }

    protected void setMessageGroup ( String message_group )
    {
        MessageGroup = message_group;
    }

    protected String getMessageGroup()
    {
        return MessageGroup;
    }

    protected void setObject ( String object )
    {
        Object = object;
    }

    protected String getObject()
    {
        return Object;
    }

    protected void setNotifyServices ( String notify_services )
    {
        NotifyServices = notify_services;
    }

    protected String getNotifyServices()
    {
        return NotifyServices;
    }

    protected void setAutoCall ( String auto_call )
    {
        AutoCall = auto_call;
    }

    protected String getAutoCall()
    {
        return AutoCall;
    }

    protected void setOperationsInitCall ( String operations_init_call )
    {
        OperationsInitCall = operations_init_call;
    }

    protected String getOperationsInitCall()
    {
        return OperationsInitCall;
    }

    protected void setMessageType ( String message_type )
    {
        MessageType = message_type;
    }

    protected String getMessageType()
    {
        return MessageType;
    }

    protected void setResolvedInstructionsParameter ( String resolved_instructions_parameter )
    {
        ResolvedInstructionsParameter = resolved_instructions_parameter;
    }

    protected String getResolvedInstructionsParameter()
    {
        return ResolvedInstructionsParameter;
    }

    protected void setServiceName ( String service_name )
    {
        ServiceName = service_name;
    }

    protected String getServiceName()
    {
        return ServiceName;
    }

    protected void setMessageKey ( String message_key )
    {
        MessageKey = message_key;
    }

    protected String getMessageKey()
    {
        return MessageKey;
    }

    protected void setDuplicateCount ( long duplicate_count )
    {
        DuplicateCount = duplicate_count;
    }

    protected long getDuplicateCount()
    {
        return DuplicateCount;
    }

    protected void setLastTimeReceived ( long last_time_received )
    {
        LastTimeReceived = last_time_received;
    }

    protected long getLastTimeReceived()
    {
        return LastTimeReceived;
    }

    protected void setLocalLastTimeReceived ( Calendar local_last_time_received )
    {
        LocalLastTimeReceived = local_last_time_received;
    }

    protected Calendar getLocalLastTimeReceived()
    {
        return LocalLastTimeReceived;
    }

    protected void setCMAFlag ( int cma_flag )
    {
        CMAFlag = cma_flag;
    }

    protected int getCMAFlag()
    {
        return CMAFlag;
    }
};
