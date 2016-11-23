package com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter.test;

import com.bgi.esm.monitoring.platform.dispatcher.ServiceCenter.ServiceCenterTicket;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

public class TestCreateServiceCenterTicket extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestCreateServiceCenterTicket.class );

    public TestCreateServiceCenterTicket ( String param )
    {
        super ( param );
    }

    public void testCreateNewTicketForValidSystemAccount()
    {
        ServiceCenterTicket serviceCenterTicket = ServiceCenterTicket.createNewTicket ( "test_group", "zzito", "test_description" );

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );
    }

    public void testCreateNewTicketForValidSystemAccountAndRetrieve()
    {
        ServiceCenterTicket serviceCenterTicket = ServiceCenterTicket.createNewTicket ( "test_group", "zzito", "test_description" );

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );

        String ticket_number = serviceCenterTicket.getTicketNumber();

        ServiceCenterTicket retrievedTicket = new ServiceCenterTicket ( ticket_number );

        assertEquals ( serviceCenterTicket.getDescription(), retrievedTicket.getDescription() );
        assertEquals ( serviceCenterTicket.getEmployeeID(),  retrievedTicket.getEmployeeID()  );
    }

    public void testCreateNewTicketForValidUser()
    {
        ServiceCenterTicket serviceCenterTicket = ServiceCenterTicket.createNewTicket ( "test_group", "linden", "test_description" );

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );
    }
}
