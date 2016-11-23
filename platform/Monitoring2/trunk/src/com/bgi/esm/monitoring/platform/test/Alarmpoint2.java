package com.bgi.esm.monitoring.platform.test;

import java.util.List;
import com.bgi.esm.monitoring.platform.client.BackEndFacade;
import com.bgi.esm.monitoring.platform.shared.value.Alarmpoint;

/**
 * Exercise Alarmpoint entity bean
 * 
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class Alarmpoint2
{
    public void runClient() throws Exception
    {
        Alarmpoint ap1 = new Alarmpoint();
        ap1.setEventId   ( "test-event-id"   );
        ap1.setMessageId ( "test-message-id" );
        ap1.setAlarmpointNotificationTarget ( "test-notification-target" );
        ap1.setAlarmpointMessage            ( "test-ap-message" );
        ap1.setAlarmpointDevice             ( "test-device" );

    	BackEndFacade bef = new BackEndFacade();

        Alarmpoint ap2 = bef.addOrUpdateAlarmpoint ( ap1 );

        Alarmpoint ap3 = bef.selectAlarmpointEventByEventID ( ap2.getEventId() );

        List list = bef.getAllAlarmpointEvents();

        System.out.println ( "List size: " + list.size() );
    }

    public static void main ( String[] args ) throws Exception 
    {
        System.out.println("begin");
	
	    Alarmpoint2 ap = new Alarmpoint2();
	    ap.runClient();
	
	    System.out.println("end");
    }
}
