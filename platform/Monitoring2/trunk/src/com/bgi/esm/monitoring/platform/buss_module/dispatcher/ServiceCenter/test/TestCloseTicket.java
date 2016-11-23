package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.test;

import java.io.IOException;
import org.apache.log4j.Logger;
import junit.framework.TestCase;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestCloseTicket extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestCloseTicket.class );

    public TestCloseTicket ( String param )
    {
        super ( param );
    }

    public ServiceCenterTicket createTestTicket()
    {
        String owner       = "VPO";
        String assignee    = null;
        String description = "Sample ticket description";
        String category    = "SERVERS";
        String subcategory = "ENTERPRISE SYSTEMS MANAGEMENT";
        String product     = "VPO";
        String problem     = "FAULT";

        ServiceCenterTicket serviceCenterTicket = ServiceCenterTicket.createNewTicket ( owner, assignee, description, category, subcategory, product, problem );

        return serviceCenterTicket;
    }

    public void testCreateAndCloseTicket() throws IOException
    {
        ServiceCenterTicket serviceCenterTicket = createTestTicket();

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );

        String ticket_number = serviceCenterTicket.getTicketNumber();

        ServiceCenterTicket retrievedTicket = new ServiceCenterTicket ( ticket_number );

        assertNotNull ( "Unable to retrieve ticket: " + ticket_number, retrievedTicket );

        _log.info ( "Ticket status: " + retrievedTicket.getStatus() );

        assertTrue ( "Could not close ticket: " + ticket_number, 
                ServiceCenterTicket.closeExistingTicket ( ticket_number, "test closing ticket", "VPO", "VPO" ) );

        retrievedTicket = new ServiceCenterTicket ( ticket_number );
        assertNotNull ( "Unable to retrieve ticket: " + ticket_number, retrievedTicket );
        _log.info ( "Ticket status: " + retrievedTicket.getStatus() );
    }

    public void testCreateAndCloseTicket2() throws IOException
    {
        ServiceCenterTicket serviceCenterTicket = createTestTicket();

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );

        String ticket_number = serviceCenterTicket.getTicketNumber();

        ServiceCenterTicket retrievedTicket = new ServiceCenterTicket ( ticket_number );

        assertNotNull ( "Unable to retrieve ticket: " + ticket_number, retrievedTicket );

        _log.info ( "Ticket status: " + retrievedTicket.getStatus() );

        assertTrue ( "Could not close ticket: " + ticket_number, 
                ServiceCenterTicket.closeExistingTicket ( ticket_number, "test closing ticket" ) );

        retrievedTicket = new ServiceCenterTicket ( ticket_number );
        assertNotNull ( "Unable to retrieve ticket: " + ticket_number, retrievedTicket );
        _log.info ( "Ticket status: " + retrievedTicket.getStatus() );
    }
}
