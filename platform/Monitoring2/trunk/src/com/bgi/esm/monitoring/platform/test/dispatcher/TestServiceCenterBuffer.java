package com.bgi.esm.monitoring.platform.test.dispatcher;

import com.bgi.esm.monitoring.platform.shared.exception.BackEndFailure;
import com.bgi.esm.monitoring.platform.shared.value.ServiceCenter;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestServiceCenterBuffer extends CommonTestCase
{
    final private static Logger _log = Logger.getLogger ( TestServiceCenterBuffer.class );

    public TestServiceCenterBuffer ( String param )
    {
        super ( param );
    }

    private ServiceCenter createServiceCenter()
    {
        long timestamp = System.currentTimeMillis();

        ServiceCenter sc1  = new ServiceCenter ();
        sc1.setTicketNum          ( "TestIM-" + timestamp );
        sc1.setMessageId          ( "OVITestMessageId-" + timestamp );
        sc1.setTicketCategory     ( "test" );
        sc1.setTicketAssignment   ( "test-assignment" );
        sc1.setTicketAssigneeName ( "test-user" );
        sc1.setTicketMessage      ( "test ticket message" );
        sc1.setMessageKey         ( "test-message-key" );

        /*
        scParameters.setTicketCategory      ( ServiceCenter.DEFAULT_VALUE   );  //  category
        scParameters.setTicketOpenTime      ( ServiceCenter.DEFAULT_VALUE   );  //  open.time
        scParameters.setTicketOpenedBy      ( ServiceCenter.DEFAULT_VALUE   );  //  opened.by
        scParameters.setTicketPriorityCode  ( ServiceCenter.DEFAULT_VALUE   );  //  priority.code
        scParameters.setTicketSeverityCode  ( ServiceCenter.DEFAULT_VALUE   );  //  severity.code
        scParameters.setTicketUpdateTime    ( ServiceCenter.DEFAULT_VALUE   );  //  update.time
        scParameters.setTicketAssignment    ( ServiceCenter.DEFAULT_VALUE   );  //  assignment
        scParameters.setTicketAlertTime     ( ServiceCenter.DEFAULT_VALUE   );  //  alert.time
        scParameters.setTicketStatus        ( ServiceCenter.DEFAULT_VALUE   );  //  status
        scParameters.setTicketCloseTime     ( ServiceCenter.DEFAULT_VALUE   );  //  close.time
        scParameters.setTicketClosedBy      ( ServiceCenter.DEFAULT_VALUE   );  //  closed.by
        scParameters.setTicketFlag          ( ServiceCenter.DEFAULT_VALUE   );  //  flag
        scParameters.setTicketAssigneeName  ( ServiceCenter.DEFAULT_VALUE   );  //  assignee.name
        scParameters.setTicketRespondTime   ( ServiceCenter.DEFAULT_VALUE   );  //  respond.time
        scParameters.setTicketContactName   ( ServiceCenter.DEFAULT_VALUE   );  //  contact.name  -- IGI
        scParameters.setTicketActor         ( ServiceCenter.DEFAULT_VALUE   );  //  actor
        scParameters.setTicketFormat        ( ServiceCenter.DEFAULT_VALUE   );  //  format
        scParameters.setTicketDeadlineGroup ( ServiceCenter.DEFAULT_VALUE   );  //  deadline.group
        scParameters.setTicketDeadlineAlert ( ServiceCenter.DEFAULT_VALUE   );  //  deadline.alert
        //*/

        return sc1;
    }

    public void testAddServiceCenterBuffer() throws BackEndFailure
    {
        ServiceCenter sc    = createServiceCenter();
        ServiceCenter added = bef.addOrUpdateServiceCenterBuffer ( sc );

        assertNotNull ( "Could not add ServiceCenterBuffer", added );
    }

    public void testAddUpdateServiceCenterBuffer() throws BackEndFailure
    {
        ServiceCenter sc      = createServiceCenter();
        ServiceCenter added   = bef.addOrUpdateServiceCenterBuffer ( sc );
        assertNotNull ( "Could not add ServiceCenterBuffer", added );
        added.setTicketCategory ( "updated" );

        ServiceCenter changed = bef.addOrUpdateServiceCenterBuffer ( added );

        assertEquals ( "Could not update ServiceCenterBuffer", changed.getTicketCategory(), "updated" );
    }
};

