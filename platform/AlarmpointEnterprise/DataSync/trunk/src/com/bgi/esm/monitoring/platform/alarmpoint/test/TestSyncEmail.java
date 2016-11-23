package com.bgi.esm.monitoring.platform.alarmpoint.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.alarmpoint.Common;
import com.bgi.esm.monitoring.platform.alarmpoint.SyncEmail;

public class TestSyncEmail extends TestCase
{
    public TestSyncEmail ( String param )
    {
        super ( param );
    }

    public void testSyncEmail() throws ClassNotFoundException, SQLException, IOException
    {
        SyncEmail.synchronizeDatabase();
    };
}
