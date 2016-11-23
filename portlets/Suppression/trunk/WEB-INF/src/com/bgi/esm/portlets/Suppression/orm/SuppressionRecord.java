package com.bgi.esm.portlets.Suppression.orm;

import java.util.Calendar;
import org.apache.log4j.Logger;

public class SuppressionRecord
{
    final private static Logger _log    = Logger.getLogger ( SuppressionRecord.class );

    protected long RowID                = 0L;
    protected long SuppressionID        = 0L;
    protected String NodeName           = null;
    protected String GroupName          = null;
    protected Calendar StartTime        = null;
    protected Calendar EndTime          = null;
    protected boolean NotificationFlag  = false;
    protected int NotificationMinutes   = 0;
    protected String Description        = null;
    protected boolean DeletedFlag       = false;
    protected String CreatorName        = null;
    protected Calendar CreationTime     = null;
    protected String LastUpdateName     = null;
    protected Calendar LastUpdateTime   = null;
    protected String DatabaseInstance   = null;
    protected String Message            = null;
    protected boolean RemoveOnReboot    = false;

    public SuppressionRecord()
    {
        CreationTime = Calendar.getInstance();
    }

    public void setValue ( SuppressionRecord object )
    {
        setSuppressionID        ( object.getSuppressionID()         );
        setNodeName             ( object.getNodeName()              );
        setGroupName            ( object.getGroupName()             );
        setStartTime            ( object.getStartTime()             );
        setEndTime              ( object.getEndTime()               );
        setNotificationFlag     ( object.getNotificationFlag()      );
        setNotificationMinutes  ( object.getNotificationMinutes()   );
        setDescription          ( object.getDescription()           );
        setDeletedFlag          ( object.getDeletedFlag()           );
        setCreatorName          ( object.getCreatorName()           );
        setCreationTime         ( object.getCreationTime()          );
        setLastUpdateName       ( object.getLastUpdateName()        );
        setDatabaseInstance     ( object.getDatabaseInstance()      );
        setMessage              ( object.getMessage()               );
        setRemoveOnReboot       ( object.getRemoveOnReboot()        );
    }

    public SuppressionRecord getValue()
    {
        SuppressionRecord object = new SuppressionRecord();

        object.setValue ( this );

        return object;
    }

    public void setRowID ( long row_id )
    {
        RowID = row_id;
    }

    public long getRowID()
    {
        return RowID;
    }

    public void setSuppressionID ( long suppression_id )
    {
        SuppressionID = suppression_id;
    }

    public long getSuppressionID()
    {
        return SuppressionID;
    }

    public void setNodeName ( String node_name )
    {
        NodeName = node_name;
    }

    public String getNodeName()
    {
        return NodeName;
    }

    public void setGroupName ( String group_name )
    {
        GroupName = group_name;
    }

    public String getGroupName()
    {
        return GroupName;
    }

    public void setStartTime ( Calendar timestamp )
    {
        StartTime = timestamp;
    }

    public Calendar getStartTime()
    {
        return StartTime;
    }

    public void setEndTime ( Calendar timestamp )
    {
        EndTime = timestamp;
    }

    public Calendar getEndTime()
    {
        return EndTime;
    }

    public void setNotificationFlag ( boolean flag )
    {
        NotificationFlag = flag;
    }

    public boolean getNotificationFlag()
    {
        return NotificationFlag;
    }

    public void setNotificationMinutes ( int minutes )
    {
        NotificationMinutes = minutes;
    }

    public int getNotificationMinutes()
    {
        return NotificationMinutes;
    }

    public void setDescription ( String description )
    {
        Description = description;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDeletedFlag ( boolean flag )
    {
        DeletedFlag = flag;
    }

    public boolean getDeletedFlag()
    {
        return DeletedFlag;
    }

    public void setCreatorName ( String creator_name )
    {
        CreatorName = creator_name;
    }

    public String getCreatorName()
    {
        return CreatorName;
    }

    public void setCreationTime ( Calendar timestamp )
    {
        CreationTime = timestamp;
    }

    public Calendar getCreationTime()
    {
        return CreationTime;
    }

    public void setLastUpdateName ( String last_update_name )
    {
        LastUpdateName = last_update_name;
    }

    public String getLastUpdateName()
    {
        return LastUpdateName;
    }

    public void setLastUpdateTime ( Calendar timestamp )
    {
        LastUpdateTime = timestamp;
    }

    public Calendar getLastUpdateTime()
    {
        return LastUpdateTime;
    }
    
    public void setDatabaseInstance ( String database_instance )
    {
        DatabaseInstance = database_instance;
    }

    public String getDatabaseInstance()
    {
        return DatabaseInstance;
    }
    
    public void setMessage ( String message )
    {
        Message = message;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setRemoveOnReboot ( boolean flag )
    {
        RemoveOnReboot = flag;
    }

    public boolean getRemoveOnReboot()
    {
        return RemoveOnReboot;
    }
};
