package com.bgi.esm.monitoring.platform.alarmpoint.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncPeople;

public class TestSyncPeople extends TestCase
{
    public TestSyncPeople ( String param )
    {
        super ( param );
    }

    public void testSyncPeople() throws ClassNotFoundException, SQLException, IOException
    {
        SyncPeople.synchronizeDatabase();
    };
}
