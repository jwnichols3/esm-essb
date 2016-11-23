package com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter.Toolkit;

import junit.framework.TestCase;

public class TestToolkit extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestToolkit.class );

    public TestToolkit ( String param )
    {
        super ( param );
    }

    /**
     * Validates craeting a database connection with default properties
     */
    public void testDatabaseConnection() throws ClassNotFoundException, SQLException, IOException
    {
        Connection con = Toolkit.getDatabaseConnection();

        assertNotNull ( con );
    }

    /**
     *  Expecting no results
     */
    public void testRetrieveEventsBefore2006() throws ClassNotFoundException, SQLException, IOException
    {
        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end   = Calendar.getInstance();

        calendar_start.set ( Calendar.YEAR,        2005 );
        calendar_start.set ( Calendar.MONTH,       11   );
        calendar_start.set ( Calendar.DATE,        1    );
        calendar_start.set ( Calendar.HOUR_OF_DAY, 0    );
        calendar_start.set ( Calendar.MINUTE,      0    );
        calendar_start.set ( Calendar.SECOND,      0    );

        calendar_end.set   ( Calendar.YEAR,        2005 );
        calendar_end.set   ( Calendar.MONTH,       12   );
        calendar_end.set   ( Calendar.DATE,        31   );
        calendar_end.set   ( Calendar.HOUR_OF_DAY, 23   );
        calendar_end.set   ( Calendar.MINUTE,      59   );
        calendar_end.set   ( Calendar.SECOND,      59   );

        ResultSet results = Toolkit.retrieveEvents ( calendar_start.getTime(), calendar_end.getTime() );
        int num_records   = 0;
        while ( results.next() )
        {
            num_records++;
        }

        assertTrue ( 0 == num_records );
    }

    public void testRetrieveEventsStartBeforeEventDatabase() throws ClassNotFoundException, SQLException, IOException
    {
        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end   = Calendar.getInstance();

        calendar_start.set ( Calendar.YEAR,        2005 );
        calendar_start.set ( Calendar.MONTH,       11   );
        calendar_start.set ( Calendar.DATE,        1    );
        calendar_start.set ( Calendar.HOUR_OF_DAY, 0    );
        calendar_start.set ( Calendar.MINUTE,      0    );
        calendar_start.set ( Calendar.SECOND,      0    );

        calendar_end.set   ( Calendar.YEAR,        2006 );
        calendar_end.set   ( Calendar.MONTH,        7   );
        calendar_end.set   ( Calendar.DATE,        31   );
        calendar_end.set   ( Calendar.HOUR_OF_DAY, 23   );
        calendar_end.set   ( Calendar.MINUTE,      59   );
        calendar_end.set   ( Calendar.SECOND,      59   );

        ResultSet results = Toolkit.retrieveEvents ( calendar_start.getTime(), calendar_end.getTime() );
        int num_records   = 0;
        while ( results.next() )
        {
            num_records++;
        }

        assertTrue ( num_records > 0 );
    }

    public void testRetrieveEventsFromOneMonthBefore() throws ClassNotFoundException, SQLException, IOException
    {
        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end   = Calendar.getInstance();

        calendar_start.add ( Calendar.MONTH, -1 );

        ResultSet results = Toolkit.retrieveEvents ( calendar_start.getTime(), calendar_end.getTime() );

        assertNotNull ( results );
    }
};
