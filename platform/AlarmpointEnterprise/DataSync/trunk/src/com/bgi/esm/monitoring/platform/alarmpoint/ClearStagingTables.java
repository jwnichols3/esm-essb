package com.bgi.esm.monitoring.platform.alarmpoint;

import java.lang.ref.WeakReference;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;

public class ClearStagingTables
{
    final private static Logger _log = Logger.getLogger ( SyncVoiceDevices.class );

    public static void main ( String args[] ) throws SQLException, ClassNotFoundException, IOException
    {
        Connection con       = Common.getDestConnection();
        Common.clearStagingTables();
    }
};

