package com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.test;

import java.io.IOException;
import com.bgi.esm.monitoring.platform.buss_module.dispatcher.ServiceCenter.ServiceCenterTicket;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

/**
 * @author Dennis Lin (dept=Enterprise Systems Management, username=linden)
 */
public class TestCreateServiceCenterTicket extends TestCase
{
    final private static Logger _log = Logger.getLogger ( TestCreateServiceCenterTicket.class );

    public TestCreateServiceCenterTicket ( String param )
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

    public void testRetrieveEebPropertiesForServiceCenter()
    {
        _log.info ( "**************************************** testRetrieveEebPropertiesForServiceCenter()" );

        String sc_property_hostname = ServiceCenterTicket.getServiceCenterPropertyHostname();

        assertNotNull ( "Could not retrieve Service Center Property (hostname)", sc_property_hostname );
    }

    public void testCreateNewTicketForValidSystemAccount()
    {
        _log.info ( "**************************************** testCreateNewTicketForValidSystemAccount()" );

        ServiceCenterTicket serviceCenterTicket = createTestTicket();

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );
    }

    public void testCreateNewTicketForValidSystemAccountAndRetrieve() throws IOException
    {
        _log.info ( "**************************************** testCreateNewTicketForValidSystemAccountAndRetrieve()" );

        ServiceCenterTicket serviceCenterTicket = createTestTicket();

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );

        String ticket_number = serviceCenterTicket.getTicketNumber();

        ServiceCenterTicket retrievedTicket = new ServiceCenterTicket ( ticket_number );

        assertNotNull ( "Unable to retrieve ticket: " + ticket_number, retrievedTicket );

        _log.info ( "Ticket status: " + retrievedTicket.getStatus() );

        //assertEquals ( serviceCenterTicket.getDescription(), retrievedTicket.getDescription() );
        //assertEquals ( serviceCenterTicket.getEmployeeID(),  retrievedTicket.getEmployeeID()  );
    }

    public void testCreateNewTicketForValidUser()
    {
        _log.info ( "**************************************** testCreateNewTicketForValidUser()" );

        ServiceCenterTicket serviceCenterTicket = createTestTicket();

        assertNotNull ( serviceCenterTicket );

        _log.info ( "Created ticket: " + serviceCenterTicket.getTicketNumber() );
    }

    public void testFunctionalOrmFacade()
    {
        _log.info ( "**************************************** testFunctionalOrmFacade()" );

        assertNotNull ( "Does not have a working ORMFacade object", ServiceCenterTicket.getOrmFacade() );
    }
}
