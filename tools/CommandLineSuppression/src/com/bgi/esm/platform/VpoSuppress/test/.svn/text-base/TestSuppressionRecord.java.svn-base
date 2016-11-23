package com.bgi.esm.platform.VpoSuppress.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.bgi.esm.platform.VpoSuppress.SuppressionRecord;
import com.bgi.esm.platform.VpoSuppress.TestHarness;
import com.bgi.esm.platform.VpoSuppress.test.CommonTestCase;

public class TestSuppressionRecord extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestSuppressionRecord.class );

    public TestSuppressionRecord ( String param )
    {
        super ( param );
    }

    public void testDatabaseConnection() throws SQLException, IOException, ClassNotFoundException
    {
        Connection con = TestHarness.getDatabaseConnection();
    }

    public void testSuppressionRecord() throws SQLException, IOException, ClassNotFoundException
    {
        SuppressionRecord sr = new SuppressionRecord ( "500" );

        _log.info ( "Retrieving test suppression record"  );
        _log.info ( "    App:     " + sr.getApplication() );
        _log.info ( "    Node:    " + sr.getNode()        );
        _log.info ( "    Creator: " + sr.getCreator()     );
        _log.info ( "    Start:   " + sr.getStartTime()   );
        _log.info ( "    End:     " + sr.getEndTime()     );
    }
};
