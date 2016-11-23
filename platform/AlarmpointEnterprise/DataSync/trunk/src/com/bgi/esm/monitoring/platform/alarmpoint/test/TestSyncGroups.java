package com.bgi.esm.monitoring.platform.alarmpoint.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncGroups;

public class TestSyncGroups extends TestCase
{
    public TestSyncGroups ( String param )
    {
        super ( param );
    }

    public void testSyncGroups() throws ClassNotFoundException, SQLException, IOException
    {
        SyncGroups.synchronizeDatabase();
    };
}
