package com.bgi.esm.monitoring.platform.test.events_by_group;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.EventsByGroup;
import com.bgi.esm.monitoring.platform.test.events_by_group.CommonTestCase;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestEventsByGroup extends CommonTestCase
{
    final private static Logger _log         = Logger.getLogger ( TestEventsByGroup.class );
    final public static SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MMM-yyyy, HH:mm:ss" );

    public TestEventsByGroup ( String param )
    {
        super ( param );
    }

    /*
    public void testSearchByMessageId() throws BackEndFailure
    {
        long timestamp        = System.currentTimeMillis();
        String row_id         = "row-" + timestamp;
        String message_id     = "message-" + timestamp;

        EventsByGroup ebg     = createEventsByGroup ( message_id );
        EventsByGroup add     = bef.addOrUpdateEventsByGroup ( ebg );
        assertNotNull ( "Could not add EventsByGroup object", add );

        EventsByGroup search  = bef.selectEventsByGroupByMessageId ( message_id );

        assertNotNull ( "Could not find events_by_group where message_id=" + message_id, search );
    }

    public void testAddUpdateDelete() throws BackEndFailure
    {
        long timestamp        = System.currentTimeMillis();
        String row_id         = "row-" + timestamp;
        String message_id     = "message-" + timestamp;

        EventsByGroup ebg     = createEventsByGroup ( message_id );
        EventsByGroup add     = bef.addOrUpdateEventsByGroup ( ebg );
        assertNotNull ( "Could not add EventsByGroup object", add );

        add.setGroupName ( "test-eeb2" );

        EventsByGroup updated = bef.addOrUpdateEventsByGroup ( add );
        assertNotNull ( "Could not update EventsByGroup", add );
        assertEquals ( "Could not correctly update EventsByGroup", add.getGroupName(), updated.getGroupName() );

        assertTrue ( "Could not delete EventsByGroup", bef.deleteEventsByGroup ( updated ) );
    }

    public void testFindAllEventsBetweenTime() throws BackEndFailure
    {
        long timestamp        = System.currentTimeMillis();
        String row_id         = "row-" + timestamp;
        String message_id     = "message-" + timestamp;

        EventsByGroup ebg     = createEventsByGroup ( message_id );
        EventsByGroup add     = bef.addOrUpdateEventsByGroup ( ebg );
        assertNotNull ( "Could not add EventsByGroup object", add );

        Calendar start_time   = Calendar.getInstance();
        Calendar end_time     = Calendar.getInstance();
        start_time.add ( Calendar.HOUR, -4 );

        List list             = bef.findAllEventsByGroupBetweenTimesByGroup ( DEFAULT_GROUP, start_time, end_time );

        assertTrue ( "Could not search for past events", list.size() > 0 );

        Iterator iterator     = list.iterator();
        while ( iterator.hasNext() )
        {
            EventsByGroup e = (EventsByGroup) iterator.next();

            StringBuilder message = new StringBuilder ( "EventsByGroup ( RowID, GroupName, Timestamp, MessageID ) = ( " );
            message.append ( e.getRowId() );
            message.append ( ", " );
            message.append ( e.getGroupName() );
            message.append ( ", " );
            message.append ( sdf.format ( e.getTimestamp().getTime() ) );
            message.append ( ", " );
            message.append ( e.getMessageId() );
            message.append ( " )" );

            _log.debug ( message.toString() );
        }

        assertTrue ( "Could not delete EventsByGroup", bef.deleteEventsByGroup ( add ) );
    }
    //*/

    public void testSearchEventsInPast15Minutes() throws BackEndFailure
    {
        logFunction ( "testSearchEventsInPast15Minutes" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -15 );

        List list = bef.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );
    }

    public void testSearchEventsInPast30Minutes() throws BackEndFailure
    {
        logFunction ( "testSearchEventsInPast30Minutes" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -30 );

        List list = bef.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );
    }

    public void testSearchEventsInPast45Minutes() throws BackEndFailure
    {
        logFunction ( "testSearchEventsInPast45Minutes" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -45 );

        List list = bef.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );
    }

    public void testSearchEventsInPast60Minutes() throws BackEndFailure
    {
        logFunction ( "testSearchEventsInPast60Minutes" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -60 );

        List list = bef.findAllEventsByGroupsBetweenTimesPaginate ( start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );
    }

    public void testSearchEventsByGroup_Group() throws BackEndFailure
    {
        logFunction ( "testSearchEventsByGroup_Group" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -60 );

        List list = bef.findAllBetweenTimesByGroupPaginate ( "esm", start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );

    }

    public void testSearchEventsByGroup_Application() throws BackEndFailure
    {
        logFunction ( "testSEarchEventsByGroup_Application" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -60 );

        List list = bef.findAllBetweenTimesByApplicationPaginate ( "SSM-no-app-def", start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );

    }

    public void testSearchEventsByGroup_GroupApplication() throws BackEndFailure
    {
        logFunction ( "testSEarchEventsByGroup_GroupApplication" );
        Calendar start_time = Calendar.getInstance();
        Calendar end_time   = Calendar.getInstance();
        int num_per_page    = 25;
        int page_num        = 1;

        start_time.add ( Calendar.MINUTE, -60 );

        List list = bef.findAllBetweenTimesByGroupApplicationPaginate ( "esm", "SSM-no-app-def", start_time, end_time, num_per_page, page_num );

        _log.info ( "Num entries returned: " + list.size() );
    }
}
