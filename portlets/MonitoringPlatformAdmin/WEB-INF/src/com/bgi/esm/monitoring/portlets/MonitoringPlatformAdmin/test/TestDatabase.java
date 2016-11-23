package com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.test;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.Toolkit;
import com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.test.CommonTestCase;

public class TestDatabase extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestDatabase.class );

    public TestDatabase ( String param )
    {
        super ( param );
    }

    public void testCommonProperties()
    {
        Toolkit.checkCommonProperties();

        assertNotNull ( Toolkit.getCommonProperties() );
    }

    public void testPrimaryDatabaseConnection() throws SQLException
    {
        Connection con = Toolkit.getPrimaryDatabaseConnection();

        assertNotNull ( con );
    }

    public void testBackupDatabaseConnection() throws SQLException
    {
        Connection con = Toolkit.getBackupDatabaseConnection();

        assertNotNull ( con );
    }

    public void testRetrieveTotalNumEvents() throws SQLException
    {
        Toolkit.checkCommonProperties();

        HashMap <String, Integer> results = Toolkit.retrieveTotalNumEvents();
        Properties common_properties      = Toolkit.getCommonProperties();
        Vector <String> queue_names       = new Vector <String> ();

        assertNotNull ( results );
        assertNotNull ( common_properties );

        //  Retrieve all the queue names defined by the user
        Enumeration names                 = common_properties.propertyNames();
        String queue_name                 = null;
        String property_name              = null;
        while ( names.hasMoreElements() )
        {
            property_name = names.nextElement().toString();

            if ( property_name.indexOf ( "message.queue.name." ) >= 0 )
            {
                queue_name = common_properties.getProperty ( property_name );
                _log.info ( "Found message queue in properties file: " + queue_name );

                queue_names.add ( queue_name );
            }
        }

        //  Logging all the message queues returned by the 
        if ( _log.isInfoEnabled() )
        {
            Iterator <String> keys = results.keySet().iterator();
            Integer num_events     = null;
            while ( keys.hasNext() )
            {
                queue_name = keys.next();
                num_events = results.get ( queue_name );

                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( "Found message queue: ( " );
                message.get().append ( queue_name );
                message.get().append ( ", " );
                message.get().append ( num_events.intValue() );
                message.get().append ( " )" );

                _log.info ( message.get().toString() );
            }
        }
    }

    public void testRetrieveNumEventsInTimeWindow() throws SQLException
    {
        Toolkit.checkCommonProperties();

        Calendar calendar = Calendar.getInstance();
        calendar.set ( Calendar.MINUTE, 0 );
        calendar.set ( Calendar.SECOND, 0 );
        calendar.set ( Calendar.MILLISECOND, 0 );
        calendar.add ( Calendar.HOUR, -1 );

        Date start_time = calendar.getTime();

        int num_events = Toolkit.retrieveNumEventsInTimeWindow ( "OvEventNotify", start_time, new Date() );

        assertTrue ( "No Openview events have been detected since " + start_time.toString(), num_events > 0 );

        _log.info ( "Number of OVO events detected since start of this hour: " + num_events );
    }

    public void testRetrieveNumEventsInPast24Hours() throws SQLException
    {
        Toolkit.checkCommonProperties();

        HashMap <Date, Integer> num_events = Toolkit.getNumEventsInPast24Hours ( "OvEventNotify" );
        
        if ( _log.isInfoEnabled() )
        {
            Iterator <Date> i_dates = num_events.keySet().iterator();
            Vector <Date> dates     = new Vector <Date> ();
            while ( i_dates.hasNext() )
            {
                Date start_date    = i_dates.next();
                dates.add ( start_date );
            }

            Collections.sort    ( dates );
            Collections.reverse ( dates );

            for ( int counter = 0; counter < dates.size(); counter++ )
            {
                Date start_date    = dates.elementAt ( counter );
                Integer events     = num_events.get ( start_date );

                WeakReference <StringBuilder> message = new WeakReference <StringBuilder> ( new StringBuilder() );
                message.get().append ( start_date.toString() );
                message.get().append ( " - " );
                message.get().append ( events.toString() );

                _log.info ( message.get().toString() );
            }
        }
    }

}
